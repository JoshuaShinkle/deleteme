package com.google.gson.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class PreJava9DateFormatProvider {
    private static String getDateFormatPattern(int i9) {
        if (i9 == 0) {
            return "EEEE, MMMM d, y";
        }
        if (i9 == 1) {
            return "MMMM d, y";
        }
        if (i9 == 2) {
            return "MMM d, y";
        }
        if (i9 == 3) {
            return "M/d/yy";
        }
        throw new IllegalArgumentException("Unknown DateFormat style: " + i9);
    }

    private static String getDatePartOfDateTimePattern(int i9) {
        if (i9 == 0) {
            return "EEEE, MMMM d, yyyy";
        }
        if (i9 == 1) {
            return "MMMM d, yyyy";
        }
        if (i9 == 2) {
            return "MMM d, yyyy";
        }
        if (i9 == 3) {
            return "M/d/yy";
        }
        throw new IllegalArgumentException("Unknown DateFormat style: " + i9);
    }

    private static String getTimePartOfDateTimePattern(int i9) {
        if (i9 == 0 || i9 == 1) {
            return "h:mm:ss a z";
        }
        if (i9 == 2) {
            return "h:mm:ss a";
        }
        if (i9 == 3) {
            return "h:mm a";
        }
        throw new IllegalArgumentException("Unknown DateFormat style: " + i9);
    }

    public static DateFormat getUSDateFormat(int i9) {
        return new SimpleDateFormat(getDateFormatPattern(i9), Locale.US);
    }

    public static DateFormat getUSDateTimeFormat(int i9, int i10) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(i9) + StringUtils.SPACE + getTimePartOfDateTimePattern(i10), Locale.US);
    }
}
