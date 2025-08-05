package com.mina.kartngo.data.util;

public class Helpers {
    public static String parseLocalizedString(String rawCategory, String language) {
        if (rawCategory == null) return "Uncategorized";


        String key = language.equals("ar") ? "ar" : "EN";

        String pattern = "\\[" + key + "=([^\\]]+)\\]";
        java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = r.matcher(rawCategory);

        if (m.find()) {
            return m.group(1).trim();
        } else {
            return rawCategory;
        }
    }
}
