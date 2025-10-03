package com.google.zxing.client.android;

import android.content.Context;
import android.preference.PreferenceManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public final class LocaleManager {
    private static final String DEFAULT_COUNTRY = "US";
    private static final String DEFAULT_LANGUAGE = "en";
    private static final String DEFAULT_TLD = "com";
    private static final Map<String, String> GOOGLE_BOOK_SEARCH_COUNTRY_TLD;
    private static final Map<String, String> GOOGLE_COUNTRY_TLD;
    private static final Map<String, String> GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD;
    private static final Collection<String> TRANSLATED_HELP_ASSET_LANGUAGES;

    static {
        HashMap map = new HashMap();
        GOOGLE_COUNTRY_TLD = map;
        map.put("AR", "com.ar");
        map.put("AU", "com.au");
        map.put("BR", "com.br");
        map.put("BG", "bg");
        map.put(Locale.CANADA.getCountry(), "ca");
        map.put(Locale.CHINA.getCountry(), "cn");
        map.put("CZ", "cz");
        map.put("DK", "dk");
        map.put("FI", "fi");
        map.put(Locale.FRANCE.getCountry(), "fr");
        map.put(Locale.GERMANY.getCountry(), "de");
        map.put("GR", "gr");
        map.put("HU", "hu");
        map.put("ID", "co.id");
        map.put("IL", "co.il");
        map.put(Locale.ITALY.getCountry(), "it");
        map.put(Locale.JAPAN.getCountry(), "co.jp");
        map.put(Locale.KOREA.getCountry(), "co.kr");
        map.put("NL", "nl");
        map.put("PL", "pl");
        map.put("PT", "pt");
        map.put("RO", "ro");
        map.put("RU", "ru");
        map.put("SK", "sk");
        map.put("SI", "si");
        map.put("ES", "es");
        map.put("SE", "se");
        map.put("CH", "ch");
        map.put(Locale.TAIWAN.getCountry(), "tw");
        map.put("TR", "com.tr");
        map.put("UA", "com.ua");
        map.put(Locale.UK.getCountry(), "co.uk");
        Locale locale = Locale.US;
        map.put(locale.getCountry(), DEFAULT_TLD);
        HashMap map2 = new HashMap();
        GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD = map2;
        map2.put("AU", "com.au");
        map2.put(Locale.FRANCE.getCountry(), "fr");
        map2.put(Locale.GERMANY.getCountry(), "de");
        map2.put(Locale.ITALY.getCountry(), "it");
        map2.put(Locale.JAPAN.getCountry(), "co.jp");
        map2.put("NL", "nl");
        map2.put("ES", "es");
        map2.put("CH", "ch");
        map2.put(Locale.UK.getCountry(), "co.uk");
        map2.put(locale.getCountry(), DEFAULT_TLD);
        GOOGLE_BOOK_SEARCH_COUNTRY_TLD = map;
        TRANSLATED_HELP_ASSET_LANGUAGES = Arrays.asList("de", DEFAULT_LANGUAGE, "es", "fr", "it", "ja", "ko", "nl", "pt", "ru", "uk", "zh-rCN", "zh-rTW", "zh-rHK");
    }

    private LocaleManager() {
    }

    private static String doGetTLD(Map<String, String> map, Context context) {
        String str = map.get(getCountry(context));
        return str == null ? DEFAULT_TLD : str;
    }

    public static String getBookSearchCountryTLD(Context context) {
        return doGetTLD(GOOGLE_BOOK_SEARCH_COUNTRY_TLD, context);
    }

    public static String getCountry(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(PreferencesActivity.KEY_SEARCH_COUNTRY, "-");
        return (string == null || string.isEmpty() || "-".equals(string)) ? getSystemCountry() : string;
    }

    public static String getCountryTLD(Context context) {
        return doGetTLD(GOOGLE_COUNTRY_TLD, context);
    }

    public static String getProductSearchCountryTLD(Context context) {
        return doGetTLD(GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD, context);
    }

    private static String getSystemCountry() {
        Locale locale = Locale.getDefault();
        return locale == null ? DEFAULT_COUNTRY : locale.getCountry();
    }

    private static String getSystemLanguage() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return DEFAULT_LANGUAGE;
        }
        String language = locale.getLanguage();
        if (!Locale.SIMPLIFIED_CHINESE.getLanguage().equals(language)) {
            return language;
        }
        return language + "-r" + getSystemCountry();
    }

    public static String getTranslatedAssetLanguage() {
        String systemLanguage = getSystemLanguage();
        return TRANSLATED_HELP_ASSET_LANGUAGES.contains(systemLanguage) ? systemLanguage : DEFAULT_LANGUAGE;
    }

    public static boolean isBookSearchUrl(String str) {
        return str.startsWith("http://google.com/books") || str.startsWith("http://books.google.");
    }
}
