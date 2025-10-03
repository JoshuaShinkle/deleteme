package org.apache.commons.lang3.math;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = 0;
    public static final Short SHORT_ONE = 1;
    public static final Short SHORT_MINUS_ONE = -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Float FLOAT_ZERO = Float.valueOf(BitmapDescriptorFactory.HUE_RED);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (!str.trim().startsWith("--")) {
            return new BigDecimal(str);
        }
        throw new NumberFormatException(str + " is not a valid number.");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static BigInteger createBigInteger(String str) {
        int i9;
        if (str == null) {
            return null;
        }
        boolean zStartsWith = str.startsWith("-");
        int i10 = 16;
        if (str.startsWith("0x", zStartsWith ? 1 : 0) || str.startsWith("0x", zStartsWith ? 1 : 0)) {
            i9 = (zStartsWith ? 1 : 0) + 2;
        } else if (str.startsWith("#", zStartsWith ? 1 : 0)) {
            i9 = (zStartsWith ? 1 : 0) + 1;
        } else if (str.startsWith("0", zStartsWith ? 1 : 0)) {
            int length = str.length();
            int i11 = (zStartsWith ? 1 : 0) + 1;
            if (length > i11) {
                i10 = 8;
                i9 = i11;
            } else {
                i10 = 10;
                i9 = zStartsWith ? 1 : 0;
            }
        }
        BigInteger bigInteger = new BigInteger(str.substring(i9), i10);
        return zStartsWith ? bigInteger.negate() : bigInteger;
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x013a, code lost:
    
        if (r2 == 'l') goto L84;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0181 A[Catch: NumberFormatException -> 0x018c, TRY_LEAVE, TryCatch #7 {NumberFormatException -> 0x018c, blocks: (B:98:0x0177, B:100:0x0181), top: B:172:0x0177 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0196 A[Catch: NumberFormatException -> 0x01a2, TRY_LEAVE, TryCatch #3 {NumberFormatException -> 0x01a2, blocks: (B:105:0x018c, B:107:0x0196), top: B:165:0x018c }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x018c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Number createNumber(String str) {
        int length;
        String strSubstring;
        String strSubstring2;
        int length2;
        Double dCreateDouble;
        Float fCreateFloat;
        String strSubstring3 = null;
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        String[] strArr = {"0x", "0X", "-0x", "-0X", "#", "-#"};
        char cCharAt = 0;
        int i9 = 0;
        while (true) {
            if (i9 >= 6) {
                length = 0;
                break;
            }
            String str2 = strArr[i9];
            if (str.startsWith(str2)) {
                length = str2.length() + 0;
                break;
            }
            i9++;
        }
        if (length > 0) {
            int i10 = length;
            while (length < str.length() && (cCharAt = str.charAt(length)) == '0') {
                i10++;
                length++;
            }
            int length3 = str.length() - i10;
            return (length3 > 16 || (length3 == 16 && cCharAt > '7')) ? createBigInteger(str) : (length3 > 8 || (length3 == 8 && cCharAt > '7')) ? createLong(str) : createInteger(str);
        }
        char cCharAt2 = str.charAt(str.length() - 1);
        int iIndexOf = str.indexOf(46);
        int iIndexOf2 = str.indexOf(101) + str.indexOf(69) + 1;
        if (iIndexOf > -1) {
            if (iIndexOf2 <= -1) {
                strSubstring2 = str.substring(iIndexOf + 1);
            } else {
                if (iIndexOf2 < iIndexOf || iIndexOf2 > str.length()) {
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                strSubstring2 = str.substring(iIndexOf + 1, iIndexOf2);
            }
            strSubstring = str.substring(0, iIndexOf);
            length2 = strSubstring2.length();
        } else {
            if (iIndexOf2 <= -1) {
                strSubstring = str;
            } else {
                if (iIndexOf2 > str.length()) {
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                strSubstring = str.substring(0, iIndexOf2);
            }
            strSubstring2 = null;
            length2 = 0;
        }
        if (Character.isDigit(cCharAt2) || cCharAt2 == '.') {
            if (iIndexOf2 > -1 && iIndexOf2 < str.length() - 1) {
                strSubstring3 = str.substring(iIndexOf2 + 1, str.length());
            }
            if (strSubstring2 == null && strSubstring3 == null) {
                try {
                    try {
                        return createInteger(str);
                    } catch (NumberFormatException unused) {
                        return createLong(str);
                    }
                } catch (NumberFormatException unused2) {
                    return createBigInteger(str);
                }
            }
            if (isAllZeros(strSubstring) && isAllZeros(strSubstring3)) {
                cCharAt = 1;
            }
            if (length2 <= 7) {
                try {
                    Float fCreateFloat2 = createFloat(str);
                    if (!fCreateFloat2.isInfinite()) {
                        if (fCreateFloat2.floatValue() != BitmapDescriptorFactory.HUE_RED || cCharAt != 0) {
                            return fCreateFloat2;
                        }
                    }
                } catch (NumberFormatException unused3) {
                }
            }
            if (length2 <= 16) {
                try {
                    Double dCreateDouble2 = createDouble(str);
                    if (!dCreateDouble2.isInfinite()) {
                        if (dCreateDouble2.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || cCharAt != 0) {
                            return dCreateDouble2;
                        }
                    }
                } catch (NumberFormatException unused4) {
                }
            }
            return createBigDecimal(str);
        }
        if (iIndexOf2 > -1 && iIndexOf2 < str.length() - 1) {
            strSubstring3 = str.substring(iIndexOf2 + 1, str.length() - 1);
        }
        String strSubstring4 = str.substring(0, str.length() - 1);
        boolean z8 = isAllZeros(strSubstring) && isAllZeros(strSubstring3);
        if (cCharAt2 != 'D') {
            if (cCharAt2 == 'F') {
                try {
                    fCreateFloat = createFloat(strSubstring4);
                    if (!fCreateFloat.isInfinite()) {
                        if (fCreateFloat.floatValue() != BitmapDescriptorFactory.HUE_RED || z8) {
                            return fCreateFloat;
                        }
                    }
                } catch (NumberFormatException unused5) {
                }
                dCreateDouble = createDouble(strSubstring4);
                if (!dCreateDouble.isInfinite()) {
                }
                return createBigDecimal(strSubstring4);
            }
            if (cCharAt2 != 'L') {
                if (cCharAt2 != 'd') {
                    if (cCharAt2 != 'f') {
                    }
                    fCreateFloat = createFloat(strSubstring4);
                    if (!fCreateFloat.isInfinite()) {
                    }
                    dCreateDouble = createDouble(strSubstring4);
                    if (!dCreateDouble.isInfinite()) {
                    }
                    return createBigDecimal(strSubstring4);
                }
            }
            if (strSubstring2 == null && strSubstring3 == null && ((strSubstring4.charAt(0) == '-' && isDigits(strSubstring4.substring(1))) || isDigits(strSubstring4))) {
                try {
                    return createLong(strSubstring4);
                } catch (NumberFormatException unused6) {
                    return createBigInteger(strSubstring4);
                }
            }
            throw new NumberFormatException(str + " is not a valid number.");
        }
        try {
            dCreateDouble = createDouble(strSubstring4);
            if (!dCreateDouble.isInfinite()) {
                if (dCreateDouble.floatValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || z8) {
                    return dCreateDouble;
                }
            }
        } catch (NumberFormatException unused7) {
        }
        try {
            return createBigDecimal(strSubstring4);
        } catch (NumberFormatException unused8) {
        }
        throw new NumberFormatException(str + " is not a valid number.");
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) != '0') {
                return false;
            }
        }
        return str.length() > 0;
    }

    public static boolean isDigits(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (int i9 = 0; i9 < str.length(); i9++) {
            if (!Character.isDigit(str.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x00d9, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0083, code lost:
    
        if (r3 >= r0.length) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0085, code lost:
    
        r0 = r0[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0087, code lost:
    
        if (r0 < '0') goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0089, code lost:
    
        if (r0 > '9') goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x008b, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x008c, code lost:
    
        if (r0 == 'e') goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x008e, code lost:
    
        if (r0 != 'E') goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0091, code lost:
    
        if (r0 != '.') goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0093, code lost:
    
        if (r13 != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0095, code lost:
    
        if (r12 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0098, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0099, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x009a, code lost:
    
        if (r6 != false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x009e, code lost:
    
        if (r0 == 'd') goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00a2, code lost:
    
        if (r0 == 'D') goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00a4, code lost:
    
        if (r0 == 'f') goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00a8, code lost:
    
        if (r0 != 'F') goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00aa, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00ad, code lost:
    
        if (r0 == 'l') goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00b1, code lost:
    
        if (r0 != 'L') goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00b4, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00b5, code lost:
    
        if (r11 == false) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00b7, code lost:
    
        if (r12 != false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00b9, code lost:
    
        if (r13 != false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00bc, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00bd, code lost:
    
        if (r6 != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00bf, code lost:
    
        if (r11 == false) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00c2, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isNumber(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        boolean z8 = true;
        int i9 = charArray[0] == '-' ? 1 : 0;
        int i10 = i9 + 1;
        if (length > i10 && charArray[i9] == '0') {
            char c9 = charArray[i10];
            if (c9 == 'x' || c9 == 'X') {
                int i11 = i9 + 2;
                if (i11 == length) {
                    return false;
                }
                while (i11 < charArray.length) {
                    char c10 = charArray[i11];
                    if ((c10 < '0' || c10 > '9') && ((c10 < 'a' || c10 > 'f') && (c10 < 'A' || c10 > 'F'))) {
                        return false;
                    }
                    i11++;
                }
                return true;
            }
            if (Character.isDigit(c9)) {
                while (i10 < charArray.length) {
                    char c11 = charArray[i10];
                    if (c11 < '0' || c11 > '7') {
                        return false;
                    }
                    i10++;
                }
                return true;
            }
        }
        int i12 = length - 1;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        while (true) {
            if (i9 >= i12 && (i9 >= i12 + 1 || !z9 || z10)) {
                break;
            }
            char c12 = charArray[i9];
            if (c12 >= '0' && c12 <= '9') {
                z9 = false;
                z10 = true;
            } else if (c12 == '.') {
                if (z12 || z11) {
                    break;
                }
                z12 = true;
            } else if (c12 != 'e' && c12 != 'E') {
                if (c12 != '+' && c12 != '-') {
                    return false;
                }
                if (!z9) {
                    return false;
                }
                z9 = false;
                z10 = false;
            } else {
                if (z11 || !z10) {
                    return false;
                }
                z9 = true;
                z11 = true;
            }
            i9++;
            z8 = true;
        }
    }

    public static byte max(byte b9, byte b10, byte b11) {
        if (b10 > b9) {
            b9 = b10;
        }
        return b11 > b9 ? b11 : b9;
    }

    public static int max(int i9, int i10, int i11) {
        if (i10 > i9) {
            i9 = i10;
        }
        return i11 > i9 ? i11 : i9;
    }

    public static long max(long j9, long j10, long j11) {
        if (j10 > j9) {
            j9 = j10;
        }
        return j11 > j9 ? j11 : j9;
    }

    public static long max(long[] jArr) {
        validateArray(jArr);
        long j9 = jArr[0];
        for (int i9 = 1; i9 < jArr.length; i9++) {
            long j10 = jArr[i9];
            if (j10 > j9) {
                j9 = j10;
            }
        }
        return j9;
    }

    public static short max(short s8, short s9, short s10) {
        if (s9 > s8) {
            s8 = s9;
        }
        return s10 > s8 ? s10 : s8;
    }

    public static byte min(byte b9, byte b10, byte b11) {
        if (b10 < b9) {
            b9 = b10;
        }
        return b11 < b9 ? b11 : b9;
    }

    public static int min(int i9, int i10, int i11) {
        if (i10 < i9) {
            i9 = i10;
        }
        return i11 < i9 ? i11 : i9;
    }

    public static long min(long j9, long j10, long j11) {
        if (j10 < j9) {
            j9 = j10;
        }
        return j11 < j9 ? j11 : j9;
    }

    public static long min(long[] jArr) {
        validateArray(jArr);
        long j9 = jArr[0];
        for (int i9 = 1; i9 < jArr.length; i9++) {
            long j10 = jArr[i9];
            if (j10 < j9) {
                j9 = j10;
            }
        }
        return j9;
    }

    public static short min(short s8, short s9, short s10) {
        if (s9 < s8) {
            s8 = s9;
        }
        return s10 < s8 ? s10 : s8;
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static double toDouble(String str) {
        return toDouble(str, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public static float toFloat(String str) {
        return toFloat(str, BitmapDescriptorFactory.HUE_RED);
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    private static void validateArray(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (Array.getLength(obj) == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }

    public static byte toByte(String str, byte b9) {
        if (str == null) {
            return b9;
        }
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b9;
        }
    }

    public static double toDouble(String str, double d9) {
        if (str == null) {
            return d9;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d9;
        }
    }

    public static float toFloat(String str, float f9) {
        if (str == null) {
            return f9;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f9;
        }
    }

    public static int toInt(String str, int i9) {
        if (str == null) {
            return i9;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i9;
        }
    }

    public static long toLong(String str, long j9) {
        if (str == null) {
            return j9;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j9;
        }
    }

    public static short toShort(String str, short s8) {
        if (str == null) {
            return s8;
        }
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s8;
        }
    }

    public static int max(int[] iArr) {
        validateArray(iArr);
        int i9 = iArr[0];
        for (int i10 = 1; i10 < iArr.length; i10++) {
            int i11 = iArr[i10];
            if (i11 > i9) {
                i9 = i11;
            }
        }
        return i9;
    }

    public static int min(int[] iArr) {
        validateArray(iArr);
        int i9 = iArr[0];
        for (int i10 = 1; i10 < iArr.length; i10++) {
            int i11 = iArr[i10];
            if (i11 < i9) {
                i9 = i11;
            }
        }
        return i9;
    }

    public static short max(short[] sArr) {
        validateArray(sArr);
        short s8 = sArr[0];
        for (int i9 = 1; i9 < sArr.length; i9++) {
            short s9 = sArr[i9];
            if (s9 > s8) {
                s8 = s9;
            }
        }
        return s8;
    }

    public static short min(short[] sArr) {
        validateArray(sArr);
        short s8 = sArr[0];
        for (int i9 = 1; i9 < sArr.length; i9++) {
            short s9 = sArr[i9];
            if (s9 < s8) {
                s8 = s9;
            }
        }
        return s8;
    }

    public static byte max(byte[] bArr) {
        validateArray(bArr);
        byte b9 = bArr[0];
        for (int i9 = 1; i9 < bArr.length; i9++) {
            byte b10 = bArr[i9];
            if (b10 > b9) {
                b9 = b10;
            }
        }
        return b9;
    }

    public static byte min(byte[] bArr) {
        validateArray(bArr);
        byte b9 = bArr[0];
        for (int i9 = 1; i9 < bArr.length; i9++) {
            byte b10 = bArr[i9];
            if (b10 < b9) {
                b9 = b10;
            }
        }
        return b9;
    }

    public static double max(double[] dArr) {
        validateArray(dArr);
        double d9 = dArr[0];
        for (int i9 = 1; i9 < dArr.length; i9++) {
            if (Double.isNaN(dArr[i9])) {
                return Double.NaN;
            }
            double d10 = dArr[i9];
            if (d10 > d9) {
                d9 = d10;
            }
        }
        return d9;
    }

    public static double min(double[] dArr) {
        validateArray(dArr);
        double d9 = dArr[0];
        for (int i9 = 1; i9 < dArr.length; i9++) {
            if (Double.isNaN(dArr[i9])) {
                return Double.NaN;
            }
            double d10 = dArr[i9];
            if (d10 < d9) {
                d9 = d10;
            }
        }
        return d9;
    }

    public static float max(float[] fArr) {
        validateArray(fArr);
        float f9 = fArr[0];
        for (int i9 = 1; i9 < fArr.length; i9++) {
            if (Float.isNaN(fArr[i9])) {
                return Float.NaN;
            }
            float f10 = fArr[i9];
            if (f10 > f9) {
                f9 = f10;
            }
        }
        return f9;
    }

    public static float min(float[] fArr) {
        validateArray(fArr);
        float f9 = fArr[0];
        for (int i9 = 1; i9 < fArr.length; i9++) {
            if (Float.isNaN(fArr[i9])) {
                return Float.NaN;
            }
            float f10 = fArr[i9];
            if (f10 < f9) {
                f9 = f10;
            }
        }
        return f9;
    }

    public static double max(double d9, double d10, double d11) {
        return Math.max(Math.max(d9, d10), d11);
    }

    public static double min(double d9, double d10, double d11) {
        return Math.min(Math.min(d9, d10), d11);
    }

    public static float max(float f9, float f10, float f11) {
        return Math.max(Math.max(f9, f10), f11);
    }

    public static float min(float f9, float f10, float f11) {
        return Math.min(Math.min(f9, f10), f11);
    }
}
