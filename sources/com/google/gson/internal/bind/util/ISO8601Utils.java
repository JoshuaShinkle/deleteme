package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    private static boolean checkOffset(String str, int i9, char c9) {
        return i9 < str.length() && str.charAt(i9) == c9;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    private static int indexOfNonDigit(String str, int i9) {
        while (i9 < str.length()) {
            char cCharAt = str.charAt(i9);
            if (cCharAt < '0' || cCharAt > '9') {
                return i9;
            }
            i9++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb, int i9, int i10) {
        String string = Integer.toString(i9);
        for (int length = i10 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00cf A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bc, NumberFormatException -> 0x01be, IndexOutOfBoundsException -> 0x01c0, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bc, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c9, B:49:0x00cf, B:51:0x00d6, B:75:0x0183, B:55:0x00e0, B:56:0x00fb, B:57:0x00fc, B:61:0x0118, B:63:0x0125, B:66:0x012e, B:68:0x014d, B:71:0x015c, B:72:0x017e, B:74:0x0181, B:60:0x0107, B:77:0x01b4, B:78:0x01bb, B:40:0x00b0, B:41:0x00b3), top: B:94:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b4 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bc, NumberFormatException -> 0x01be, IndexOutOfBoundsException -> 0x01c0, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bc, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:47:0x00c9, B:49:0x00cf, B:51:0x00d6, B:75:0x0183, B:55:0x00e0, B:56:0x00fb, B:57:0x00fc, B:61:0x0118, B:63:0x0125, B:66:0x012e, B:68:0x014d, B:71:0x015c, B:72:0x017e, B:74:0x0181, B:60:0x0107, B:77:0x01b4, B:78:0x01bb, B:40:0x00b0, B:41:0x00b3), top: B:94:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i9;
        int i10;
        int i11;
        int i12;
        int length;
        TimeZone timeZone;
        char cCharAt;
        try {
            int index = parsePosition.getIndex();
            int i13 = index + 4;
            int i14 = parseInt(str, index, i13);
            if (checkOffset(str, i13, '-')) {
                i13++;
            }
            int i15 = i13 + 2;
            int i16 = parseInt(str, i13, i15);
            if (checkOffset(str, i15, '-')) {
                i15++;
            }
            int i17 = i15 + 2;
            int i18 = parseInt(str, i15, i17);
            boolean zCheckOffset = checkOffset(str, i17, 'T');
            if (!zCheckOffset && str.length() <= i17) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(i14, i16 - 1, i18);
                parsePosition.setIndex(i17);
                return gregorianCalendar.getTime();
            }
            if (zCheckOffset) {
                int i19 = i17 + 1;
                int i20 = i19 + 2;
                int i21 = parseInt(str, i19, i20);
                if (checkOffset(str, i20, ':')) {
                    i20++;
                }
                int i22 = i20 + 2;
                int i23 = parseInt(str, i20, i22);
                if (checkOffset(str, i22, ':')) {
                    i22++;
                }
                if (str.length() > i22 && (cCharAt = str.charAt(i22)) != 'Z' && cCharAt != '+' && cCharAt != '-') {
                    int i24 = i22 + 2;
                    i12 = parseInt(str, i22, i24);
                    if (i12 > 59 && i12 < 63) {
                        i12 = 59;
                    }
                    if (checkOffset(str, i24, ClassUtils.PACKAGE_SEPARATOR_CHAR)) {
                        int i25 = i24 + 1;
                        int iIndexOfNonDigit = indexOfNonDigit(str, i25 + 1);
                        int iMin = Math.min(iIndexOfNonDigit, i25 + 3);
                        int i26 = parseInt(str, i25, iMin);
                        int i27 = iMin - i25;
                        if (i27 == 1) {
                            i26 *= 100;
                        } else if (i27 == 2) {
                            i26 *= 10;
                        }
                        i10 = i23;
                        i11 = i26;
                        i9 = i21;
                        i17 = iIndexOfNonDigit;
                    } else {
                        i10 = i23;
                        i9 = i21;
                        i17 = i24;
                        i11 = 0;
                    }
                    if (str.length() > i17) {
                        throw new IllegalArgumentException("No time zone indicator");
                    }
                    char cCharAt2 = str.charAt(i17);
                    if (cCharAt2 == 'Z') {
                        timeZone = TIMEZONE_UTC;
                        length = i17 + 1;
                    } else {
                        if (cCharAt2 != '+' && cCharAt2 != '-') {
                            throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt2 + "'");
                        }
                        String strSubstring = str.substring(i17);
                        if (strSubstring.length() < 5) {
                            strSubstring = strSubstring + "00";
                        }
                        length = i17 + strSubstring.length();
                        if ("+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                            timeZone = TIMEZONE_UTC;
                        } else {
                            String str3 = "GMT" + strSubstring;
                            TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                            String id = timeZone2.getID();
                            if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                                throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                            }
                            timeZone = timeZone2;
                        }
                    }
                    GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
                    gregorianCalendar2.setLenient(false);
                    gregorianCalendar2.set(1, i14);
                    gregorianCalendar2.set(2, i16 - 1);
                    gregorianCalendar2.set(5, i18);
                    gregorianCalendar2.set(11, i9);
                    gregorianCalendar2.set(12, i10);
                    gregorianCalendar2.set(13, i12);
                    gregorianCalendar2.set(14, i11);
                    parsePosition.setIndex(length);
                    return gregorianCalendar2.getTime();
                }
                i10 = i23;
                i11 = 0;
                i9 = i21;
                i17 = i22;
            } else {
                i9 = 0;
                i10 = 0;
                i11 = 0;
            }
            i12 = 0;
            if (str.length() > i17) {
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException e9) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + '\"';
            }
            String message = e9.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e9.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e9);
            throw parseException;
        }
    }

    private static int parseInt(String str, int i9, int i10) {
        int i11;
        int i12;
        if (i9 < 0 || i10 > str.length() || i9 > i10) {
            throw new NumberFormatException(str);
        }
        if (i9 < i10) {
            i12 = i9 + 1;
            int iDigit = Character.digit(str.charAt(i9), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i9, i10));
            }
            i11 = -iDigit;
        } else {
            i11 = 0;
            i12 = i9;
        }
        while (i12 < i10) {
            int i13 = i12 + 1;
            int iDigit2 = Character.digit(str.charAt(i12), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i9, i10));
            }
            i11 = (i11 * 10) - iDigit2;
            i12 = i13;
        }
        return -i11;
    }

    public static String format(Date date, boolean z8) {
        return format(date, z8, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z8, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z8 ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z8) {
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i9 = offset / 60000;
            int iAbs = Math.abs(i9 / 60);
            int iAbs2 = Math.abs(i9 % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, iAbs, 2);
            sb.append(':');
            padInt(sb, iAbs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }
}
