package com.mina.kartngo.data.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {
    public static String parseLocalizedString(String input, String language) {
        if (input == null || input.trim().isEmpty()) return "Uncategorized";

        // Normalize language key
        String targetLang = language.equalsIgnoreCase("ar") ? "ar" : "en";

        // Regex to extract all key=value pairs
        Pattern pattern = Pattern.compile("\\[(\\w+)=([^\\]]+)]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        Map<String, String> values = new HashMap<>();

        // Collect all keys and values into a map (case-insensitive)
        while (matcher.find()) {
            String key = matcher.group(1).toLowerCase();
            String value = matcher.group(2).trim();
            values.put(key, value);
        }

        // Return the target language if exists
        if (values.containsKey(targetLang)) {
            return values.get(targetLang);
        }

        // Try fallback to any available value
        if (!values.isEmpty()) {
            return values.values().iterator().next();
        }

        // If no pattern matched, return the input as-is
        return input;
    }

}
