package com.cathaybk.hiring.constant;

import java.util.HashMap;
import java.util.Map;

public class CurrencyInfo {

    public final static Map<String, String> CURRENCY_EN_TO_ZH;
    static {
        CURRENCY_EN_TO_ZH = new HashMap<>();
        CURRENCY_EN_TO_ZH.put("USD", "美元");
        CURRENCY_EN_TO_ZH.put("GBP", "英鎊");
        CURRENCY_EN_TO_ZH.put("EUR", "歐元");
    }

}
