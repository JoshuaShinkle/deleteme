package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private static final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache = new ConcurrentHashMap(7);
    private final ConcurrentMap<MultipartKey, F> cInstanceCache = new ConcurrentHashMap(7);

    public static class MultipartKey {
        private int hashCode;
        private final Object[] keys;

        public MultipartKey(Object... objArr) {
            this.keys = objArr;
        }

        public boolean equals(Object obj) {
            return Arrays.equals(this.keys, ((MultipartKey) obj).keys);
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int iHashCode = 0;
                for (Object obj : this.keys) {
                    if (obj != null) {
                        iHashCode = (iHashCode * 7) + obj.hashCode();
                    }
                }
                this.hashCode = iHashCode;
            }
            return this.hashCode;
        }
    }

    private F getDateTimeInstance(Integer num, Integer num2, TimeZone timeZone, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return (F) getInstance(getPatternForStyle(num, num2, locale), timeZone, locale);
    }

    public static String getPatternForStyle(Integer num, Integer num2, Locale locale) {
        MultipartKey multipartKey = new MultipartKey(num, num2, locale);
        ConcurrentMap<MultipartKey, String> concurrentMap = cDateTimeInstanceCache;
        String str = concurrentMap.get(multipartKey);
        if (str != null) {
            return str;
        }
        try {
            String pattern = ((SimpleDateFormat) (num == null ? DateFormat.getTimeInstance(num2.intValue(), locale) : num2 == null ? DateFormat.getDateInstance(num.intValue(), locale) : DateFormat.getDateTimeInstance(num.intValue(), num2.intValue(), locale))).toPattern();
            String strPutIfAbsent = concurrentMap.putIfAbsent(multipartKey, pattern);
            return strPutIfAbsent != null ? strPutIfAbsent : pattern;
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException("No date time pattern for locale: " + locale);
        }
    }

    public abstract F createInstance(String str, TimeZone timeZone, Locale locale);

    public F getDateInstance(int i9, TimeZone timeZone, Locale locale) {
        return (F) getDateTimeInstance(Integer.valueOf(i9), (Integer) null, timeZone, locale);
    }

    public F getInstance() {
        return (F) getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    public F getTimeInstance(int i9, TimeZone timeZone, Locale locale) {
        return (F) getDateTimeInstance((Integer) null, Integer.valueOf(i9), timeZone, locale);
    }

    public F getInstance(String str, TimeZone timeZone, Locale locale) {
        if (str == null) {
            throw new NullPointerException("pattern must not be null");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        MultipartKey multipartKey = new MultipartKey(str, timeZone, locale);
        F f9 = this.cInstanceCache.get(multipartKey);
        if (f9 != null) {
            return f9;
        }
        F f10 = (F) createInstance(str, timeZone, locale);
        F f11 = (F) this.cInstanceCache.putIfAbsent(multipartKey, f10);
        return f11 != null ? f11 : f10;
    }

    public F getDateTimeInstance(int i9, int i10, TimeZone timeZone, Locale locale) {
        return (F) getDateTimeInstance(Integer.valueOf(i9), Integer.valueOf(i10), timeZone, locale);
    }
}
