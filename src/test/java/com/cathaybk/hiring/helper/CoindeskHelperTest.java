package com.cathaybk.hiring.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;


public class CoindeskHelperTest {

    @Test
    // 5. 測試呼叫 coindesk API,並顯示其內容。
    public void getTest() throws JsonProcessingException {
        ResponseEntity<CoindeskHelper.GetCurrentPriceRes> res = CoindeskHelper.getCurrentPrice();
        assert res.getStatusCode().is2xxSuccessful();
        System.out.println(res);
    }
//
//    @Test
//    public void formatTest() throws ParseException {
//        String timeString = "Feb 17, 2023 06:02:00 UTC";
//        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss z", Locale.US);
//        formatter.parse(timeString);
//    }

    public static String getExampleResStr() {
        return "{\n" +
                "  \"time\": {\n" +
                "    \"updated\": \"Feb 16, 2023 12:39:00 UTC\",\n" +
                "    \"updatedISO\": \"2023-02-16T12:39:00+00:00\",\n" +
                "    \"updateduk\": \"Feb 16, 2023 at 12:39 GMT\"\n" +
                "  },\n" +
                "  \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\n" +
                "  \"chartName\": \"Bitcoin\",\n" +
                "  \"bpi\": {\n" +
                "    \"USD\": {\n" +
                "      \"code\": \"USD\",\n" +
                "      \"symbol\": \"&#36;\",\n" +
                "      \"rate\": \"24,556.2941\",\n" +
                "      \"description\": \"United States Dollar\",\n" +
                "      \"rate_float\": 24556.2941\n" +
                "    },\n" +
                "    \"GBP\": {\n" +
                "      \"code\": \"GBP\",\n" +
                "      \"symbol\": \"&pound;\",\n" +
                "      \"rate\": \"20,519.0429\",\n" +
                "      \"description\": \"British Pound Sterling\",\n" +
                "      \"rate_float\": 20519.0429\n" +
                "    },\n" +
                "    \"EUR\": {\n" +
                "      \"code\": \"EUR\",\n" +
                "      \"symbol\": \"&euro;\",\n" +
                "      \"rate\": \"23,921.4157\",\n" +
                "      \"description\": \"Euro\",\n" +
                "      \"rate_float\": 23921.4157\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

}
