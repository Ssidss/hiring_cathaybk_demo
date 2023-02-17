package com.cathaybk.hiring.helper;

import com.cathaybk.hiring.model.Currency;
import com.cathaybk.hiring.repository.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.cathaybk.hiring.constant.CurrencyInfo.CURRENCY_EN_TO_ZH;

@Component
public class CoindeskHelper {

    private static CurrencyRepository currencyRepository;

    public CoindeskHelper(CurrencyRepository currencyRepository) {
        CoindeskHelper.currencyRepository = currencyRepository;
    }

    private static final SimpleDateFormat newFormatSdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final SimpleDateFormat updatedFormatSdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss z", Locale.US);

    /**
     *
     * @return {
     *     "time" -> {LinkedHashMap@3956}  size = 3 {
     *         "updated" -> "Feb 16, 2023 12:59:00 UTC"
     *         "updatedISO" -> "2023-02-16T12:59:00+00:00"
     *         "updateduk" -> "Feb 16, 2023 at 12:59 GMT"
     *     }
     *     "disclaimer" -> "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org"
     *     "chartName" -> "Bitcoin"
     *     "bpi" -> {LinkedHashMap@3962}  size = 3 {
     *         "USD" -> {LinkedHashMap@4074}  size = 5 {
     *             "code" -> "USD"
     *             "symbol" -> "&#36;"
     *             "rate" -> "24,595.3374"
     *             "description" -> "United States Dollar"
     *             "rate_float" -> {Double@4095} 24595.3374
     *         }
     *         "GBP" -> {LinkedHashMap@4076}  size = 5
     *         "EUR" -> {LinkedHashMap@4078}  size = 5
     *     }
     * }
     * @throws JsonProcessingException
     */
    public static ResponseEntity<GetCurrentPriceRes> getCurrentPrice() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> apiRes;
        ResponseEntity<GetCurrentPriceRes> ret;
        try {
            apiRes = restTemplate.getForEntity(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            ret = new ResponseEntity<>(objectMapper.readValue(apiRes.getBody(), GetCurrentPriceRes.class), HttpStatus.OK);
//            throw new Exception();
        } catch (JsonProcessingException jsonProcessingException) {
            ret = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            ret = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(ret.getBody());

        return ret;
    }

    public static NewGetCurrentPriceRes getNewResult(GetCurrentPriceRes getCurrentPriceRes) throws ParseException {
        NewGetCurrentPriceRes res = new NewGetCurrentPriceRes();
        res.setUpdateTime(newFormatSdf.format(updatedFormatSdf.parse(getCurrentPriceRes.getTime().getUpdated())));

        List<Currency> currencyTranslations = CoindeskHelper.currencyRepository.findAll();
        Map<String, String> enToZh = new HashMap<>();
        for (Currency currencyTranslation: currencyTranslations) {
            enToZh.put(currencyTranslation.getEnCode(), currencyTranslation.getZh());
        }
        BpiData bpiData;
        BpiData newBpiData;
        Map<String, BpiData> newBpiDataMap = new HashMap<>();

        for (String bpiKey: getCurrentPriceRes.getBpi().keySet()) {
            bpiData = getCurrentPriceRes.getBpi().get(bpiKey);
            newBpiData = new BpiData().setCode(bpiData.getCode())
                    .setRate_float(bpiData.getRate_float())
                    .setSymbol(bpiData.getSymbol())
                    .setRate(bpiData.getRate())
                    .setDescription(enToZh.getOrDefault(bpiData.getCode(),
                            CURRENCY_EN_TO_ZH.getOrDefault(bpiData.getCode(), "")));
            newBpiDataMap.put(bpiKey, newBpiData);
        }
        res.setBpi(newBpiDataMap);
        return res;
    }

    public static class NewGetCurrentPriceRes {
        private String updateTime;
        private Map<String, BpiData> bpi;

        public String getUpdateTime() {
            return updateTime;
        }

        public NewGetCurrentPriceRes setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Map<String, BpiData> getBpi() {
            return bpi;
        }

        public NewGetCurrentPriceRes setBpi(Map<String, BpiData> bpi) {
            this.bpi = bpi;
            return this;
        }
    }

    public static class GetCurrentPriceRes {

        private CurrentPriceTime time;
        private String disclaimer;
        private String chartName;
        private Map<String, BpiData> bpi;

        public static class CurrentPriceTime {
            private String updated;
            private String updatedISO;
            private String updateduk;

            public String getUpdated() {
                return updated;
            }

            public CurrentPriceTime setUpdated(String updated) {
                this.updated = updated;
                return this;
            }

            public String getUpdatedISO() {
                return updatedISO;
            }

            public CurrentPriceTime setUpdatedISO(String updatedISO) {
                this.updatedISO = updatedISO;
                return this;
            }

            public String getUpdateduk() {
                return updateduk;
            }

            public CurrentPriceTime setUpdateduk(String updateduk) {
                this.updateduk = updateduk;
                return this;
            }
        }

        public CurrentPriceTime getTime() {
            return time;
        }

        public GetCurrentPriceRes setTime(CurrentPriceTime time) {
            this.time = time;
            return this;
        }

        public String getDisclaimer() {
            return disclaimer;
        }

        public GetCurrentPriceRes setDisclaimer(String disclaimer) {
            this.disclaimer = disclaimer;
            return this;
        }

        public String getChartName() {
            return chartName;
        }

        public GetCurrentPriceRes setChartName(String chartName) {
            this.chartName = chartName;
            return this;
        }

        public Map<String, BpiData> getBpi() {
            return bpi;
        }

        public GetCurrentPriceRes setBpi(Map<String, BpiData> bpi) {
            this.bpi = bpi;
            return this;
        }
    }

    public static class BpiData {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private Float rate_float;

        public String getCode() {
            return code;
        }

        public BpiData setCode(String code) {
            this.code = code;
            return this;
        }

        public String getSymbol() {
            return symbol;
        }

        public BpiData setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public String getRate() {
            return rate;
        }

        public BpiData setRate(String rate) {
            this.rate = rate;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public BpiData setDescription(String description) {
            this.description = description;
            return this;
        }

        public Float getRate_float() {
            return rate_float;
        }

        public BpiData setRate_float(Float rate_float) {
            this.rate_float = rate_float;
            return this;
        }
    }
}
