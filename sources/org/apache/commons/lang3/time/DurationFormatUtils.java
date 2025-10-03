package org.apache.commons.lang3.time;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class DurationFormatUtils {
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";

    /* renamed from: y */
    static final Object f19118y = "y";

    /* renamed from: M */
    static final Object f19113M = "M";

    /* renamed from: d */
    static final Object f19115d = "d";

    /* renamed from: H */
    static final Object f19112H = "H";

    /* renamed from: m */
    static final Object f19116m = "m";

    /* renamed from: s */
    static final Object f19117s = "s";

    /* renamed from: S */
    static final Object f19114S = "S";

    public static String format(Token[] tokenArr, long j9, long j10, long j11, long j12, long j13, long j14, long j15, boolean z8) {
        int i9;
        int i10;
        long j16;
        Token[] tokenArr2 = tokenArr;
        long j17 = j15;
        StringBuilder sb = new StringBuilder();
        int length = tokenArr2.length;
        int i11 = 0;
        boolean z9 = false;
        while (i11 < length) {
            Token token = tokenArr2[i11];
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuilder) {
                sb.append(value.toString());
                j16 = j17;
                i10 = length;
                i9 = i11;
            } else {
                if (value == f19118y) {
                    sb.append(paddedValue(j9, z8, count));
                } else if (value == f19113M) {
                    sb.append(paddedValue(j10, z8, count));
                } else if (value == f19115d) {
                    i9 = i11;
                    sb.append(paddedValue(j11, z8, count));
                    j16 = j17;
                    i10 = length;
                    z9 = false;
                } else {
                    i9 = i11;
                    if (value == f19112H) {
                        i10 = length;
                        sb.append(paddedValue(j12, z8, count));
                    } else {
                        i10 = length;
                        if (value == f19116m) {
                            sb.append(paddedValue(j13, z8, count));
                        } else {
                            if (value == f19117s) {
                                sb.append(paddedValue(j14, z8, count));
                                j16 = j15;
                                z9 = true;
                            } else if (value == f19114S) {
                                if (z9) {
                                    j16 = j15;
                                    sb.append(paddedValue(j16, true, z8 ? Math.max(3, count) : 3));
                                } else {
                                    j16 = j15;
                                    sb.append(paddedValue(j16, z8, count));
                                }
                                z9 = false;
                            } else {
                                j16 = j15;
                            }
                            i11 = i9 + 1;
                            j17 = j16;
                            length = i10;
                            tokenArr2 = tokenArr;
                        }
                    }
                    j16 = j17;
                    z9 = false;
                    i11 = i9 + 1;
                    j17 = j16;
                    length = i10;
                    tokenArr2 = tokenArr;
                }
                j16 = j17;
                i10 = length;
                i9 = i11;
                z9 = false;
            }
            i11 = i9 + 1;
            j17 = j16;
            length = i10;
            tokenArr2 = tokenArr;
        }
        return sb.toString();
    }

    public static String formatDuration(long j9, String str) {
        return formatDuration(j9, str, true);
    }

    public static String formatDurationHMS(long j9) {
        return formatDuration(j9, "H:mm:ss.SSS");
    }

    public static String formatDurationISO(long j9) {
        return formatDuration(j9, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDurationWords(long j9, boolean z8, boolean z9) {
        String duration = formatDuration(j9, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z8) {
            duration = StringUtils.SPACE + duration;
            String strReplaceOnce = StringUtils.replaceOnce(duration, " 0 days", "");
            if (strReplaceOnce.length() != duration.length()) {
                String strReplaceOnce2 = StringUtils.replaceOnce(strReplaceOnce, " 0 hours", "");
                if (strReplaceOnce2.length() != strReplaceOnce.length()) {
                    duration = StringUtils.replaceOnce(strReplaceOnce2, " 0 minutes", "");
                    if (duration.length() != duration.length()) {
                        duration = StringUtils.replaceOnce(duration, " 0 seconds", "");
                    }
                } else {
                    duration = strReplaceOnce;
                }
            }
            if (duration.length() != 0) {
                duration = duration.substring(1);
            }
        }
        if (z9) {
            String strReplaceOnce3 = StringUtils.replaceOnce(duration, " 0 seconds", "");
            if (strReplaceOnce3.length() != duration.length()) {
                duration = StringUtils.replaceOnce(strReplaceOnce3, " 0 minutes", "");
                if (duration.length() != strReplaceOnce3.length()) {
                    String strReplaceOnce4 = StringUtils.replaceOnce(duration, " 0 hours", "");
                    if (strReplaceOnce4.length() != duration.length()) {
                        duration = StringUtils.replaceOnce(strReplaceOnce4, " 0 days", "");
                    }
                } else {
                    duration = strReplaceOnce3;
                }
            }
        }
        return StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.SPACE + duration, " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriod(long j9, long j10, String str) {
        return formatPeriod(j9, j10, str, true, TimeZone.getDefault());
    }

    public static String formatPeriodISO(long j9, long j10) {
        return formatPeriod(j9, j10, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0097 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Token[] lexx(String str) {
        Object obj;
        ArrayList arrayList = new ArrayList(str.length());
        boolean z8 = false;
        StringBuilder sb = null;
        Token token = null;
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if (z8 && cCharAt != '\'') {
                sb.append(cCharAt);
            } else if (cCharAt != '\'') {
                if (cCharAt == 'H') {
                    obj = f19112H;
                } else if (cCharAt == 'M') {
                    obj = f19113M;
                } else if (cCharAt == 'S') {
                    obj = f19114S;
                } else if (cCharAt == 'd') {
                    obj = f19115d;
                } else if (cCharAt == 'm') {
                    obj = f19116m;
                } else if (cCharAt == 's') {
                    obj = f19117s;
                } else if (cCharAt != 'y') {
                    if (sb == null) {
                        sb = new StringBuilder();
                        arrayList.add(new Token(sb));
                    }
                    sb.append(cCharAt);
                    obj = null;
                } else {
                    obj = f19118y;
                }
                if (obj != null) {
                    if (token == null || token.getValue() != obj) {
                        Token token2 = new Token(obj);
                        arrayList.add(token2);
                        token = token2;
                    } else {
                        token.increment();
                    }
                    sb = null;
                }
            } else if (z8) {
                z8 = false;
                sb = null;
                obj = null;
                if (obj != null) {
                }
            } else {
                sb = new StringBuilder();
                arrayList.add(new Token(sb));
                z8 = true;
                obj = null;
                if (obj != null) {
                }
            }
        }
        if (!z8) {
            return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
        }
        throw new IllegalArgumentException("Unmatched quote in format: " + str);
    }

    private static String paddedValue(long j9, boolean z8, int i9) {
        String string = Long.toString(j9);
        return z8 ? StringUtils.leftPad(string, i9, '0') : string;
    }

    public static String formatDuration(long j9, String str, boolean z8) {
        long j10;
        long j11;
        long j12;
        long j13;
        long j14;
        long j15;
        Token[] tokenArrLexx = lexx(str);
        if (Token.containsTokenWithValue(tokenArrLexx, f19115d)) {
            j11 = j9 / DateUtils.MILLIS_PER_DAY;
            j10 = j9 - (DateUtils.MILLIS_PER_DAY * j11);
        } else {
            j10 = j9;
            j11 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, f19112H)) {
            j12 = j10 / DateUtils.MILLIS_PER_HOUR;
            j10 -= DateUtils.MILLIS_PER_HOUR * j12;
        } else {
            j12 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, f19116m)) {
            j13 = j10 / 60000;
            j10 -= 60000 * j13;
        } else {
            j13 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, f19117s)) {
            long j16 = j10 / 1000;
            j15 = j10 - (1000 * j16);
            j14 = j16;
        } else {
            j14 = 0;
            j15 = j10;
        }
        return format(tokenArrLexx, 0L, 0L, j11, j12, j13, j14, j15, z8);
    }

    public static String formatPeriod(long j9, long j10, String str, boolean z8, TimeZone timeZone) {
        Token[] tokenArrLexx = lexx(str);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(new Date(j9));
        Calendar calendar2 = Calendar.getInstance(timeZone);
        calendar2.setTime(new Date(j10));
        int i9 = calendar2.get(14) - calendar.get(14);
        int i10 = calendar2.get(13) - calendar.get(13);
        int i11 = calendar2.get(12) - calendar.get(12);
        int i12 = calendar2.get(11) - calendar.get(11);
        int actualMaximum = calendar2.get(5) - calendar.get(5);
        int i13 = calendar2.get(2) - calendar.get(2);
        int i14 = calendar2.get(1) - calendar.get(1);
        while (i9 < 0) {
            i9 += CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
            i10--;
        }
        while (i10 < 0) {
            i10 += 60;
            i11--;
        }
        while (i11 < 0) {
            i11 += 60;
            i12--;
        }
        while (i12 < 0) {
            i12 += 24;
            actualMaximum--;
        }
        int i15 = 0;
        if (Token.containsTokenWithValue(tokenArrLexx, f19113M)) {
            while (actualMaximum < 0) {
                actualMaximum += calendar.getActualMaximum(5);
                i13--;
                calendar.add(2, 1);
            }
            while (i13 < 0) {
                i13 += 12;
                i14--;
            }
            if (!Token.containsTokenWithValue(tokenArrLexx, f19118y) && i14 != 0) {
                while (i14 != 0) {
                    i13 += i14 * 12;
                    i14 = 0;
                }
            }
        } else {
            if (!Token.containsTokenWithValue(tokenArrLexx, f19118y)) {
                int i16 = calendar2.get(1);
                if (i13 < 0) {
                    i16--;
                }
                while (calendar.get(1) != i16) {
                    int actualMaximum2 = actualMaximum + (calendar.getActualMaximum(6) - calendar.get(6));
                    if ((calendar instanceof GregorianCalendar) && calendar.get(2) == 1 && calendar.get(5) == 29) {
                        actualMaximum2++;
                    }
                    calendar.add(1, 1);
                    actualMaximum = actualMaximum2 + calendar.get(6);
                }
                i14 = 0;
            }
            while (calendar.get(2) != calendar2.get(2)) {
                actualMaximum += calendar.getActualMaximum(5);
                calendar.add(2, 1);
            }
            i13 = 0;
            while (actualMaximum < 0) {
                actualMaximum += calendar.getActualMaximum(5);
                i13--;
                calendar.add(2, 1);
            }
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, f19115d)) {
            i12 += actualMaximum * 24;
            actualMaximum = 0;
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, f19112H)) {
            i11 += i12 * 60;
            i12 = 0;
        }
        if (!Token.containsTokenWithValue(tokenArrLexx, f19116m)) {
            i10 += i11 * 60;
            i11 = 0;
        }
        if (Token.containsTokenWithValue(tokenArrLexx, f19117s)) {
            i15 = i10;
        } else {
            i9 += i10 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
        }
        return format(tokenArrLexx, i14, i13, actualMaximum, i12, i11, i15, i9, z8);
    }

    public static class Token {
        private int count;
        private final Object value;

        public Token(Object obj) {
            this.value = obj;
            this.count = 1;
        }

        public static boolean containsTokenWithValue(Token[] tokenArr, Object obj) {
            for (Token token : tokenArr) {
                if (token.getValue() == obj) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.value.getClass() != token.value.getClass() || this.count != token.count) {
                return false;
            }
            Object obj2 = this.value;
            return obj2 instanceof StringBuilder ? obj2.toString().equals(token.value.toString()) : obj2 instanceof Number ? obj2.equals(token.value) : obj2 == token.value;
        }

        public int getCount() {
            return this.count;
        }

        public Object getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public void increment() {
            this.count++;
        }

        public String toString() {
            return StringUtils.repeat(this.value.toString(), this.count);
        }

        public Token(Object obj, int i9) {
            this.value = obj;
            this.count = i9;
        }
    }
}
