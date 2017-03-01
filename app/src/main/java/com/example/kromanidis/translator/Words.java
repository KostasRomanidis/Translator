package com.example.kromanidis.translator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kromanidis on 12/3/2016.
 */
public class Words {
    private Map<String, Translation> translationMap;

    public Words() {
        translationMap = new HashMap<>();
    }

    public Map<String, Translation> getTranslationMap() {
        return translationMap;
    }


}
