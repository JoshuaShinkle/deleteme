package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class VINResultParser extends ResultParser {
    private static final Pattern IOQ = Pattern.compile("[IOQ]");
    private static final Pattern AZ09 = Pattern.compile("[A-Z0-9]{17}");

    private static char checkChar(int i9) {
        if (i9 < 10) {
            return (char) (i9 + 48);
        }
        if (i9 == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    private static boolean checkChecksum(CharSequence charSequence) {
        int i9 = 0;
        int iVinPositionWeight = 0;
        while (i9 < charSequence.length()) {
            int i10 = i9 + 1;
            iVinPositionWeight += vinPositionWeight(i10) * vinCharValue(charSequence.charAt(i9));
            i9 = i10;
        }
        return charSequence.charAt(8) == checkChar(iVinPositionWeight % 11);
    }

    private static String countryCode(CharSequence charSequence) {
        char cCharAt = charSequence.charAt(0);
        char cCharAt2 = charSequence.charAt(1);
        if (cCharAt == '9') {
            if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                return "BR";
            }
            if (cCharAt2 < '3' || cCharAt2 > '9') {
                return null;
            }
            return "BR";
        }
        if (cCharAt == 'S') {
            if (cCharAt2 >= 'A' && cCharAt2 <= 'M') {
                return "UK";
            }
            if (cCharAt2 < 'N' || cCharAt2 > 'T') {
                return null;
            }
            return "DE";
        }
        if (cCharAt == 'Z') {
            if (cCharAt2 < 'A' || cCharAt2 > 'R') {
                return null;
            }
            return "IT";
        }
        switch (cCharAt) {
            case '1':
            case '4':
            case '5':
                return "US";
            case '2':
                return "CA";
            case '3':
                if (cCharAt2 < 'A' || cCharAt2 > 'W') {
                    return null;
                }
                return "MX";
            default:
                switch (cCharAt) {
                    case 'J':
                        if (cCharAt2 < 'A' || cCharAt2 > 'T') {
                            return null;
                        }
                        return "JP";
                    case 'K':
                        if (cCharAt2 < 'L' || cCharAt2 > 'R') {
                            return null;
                        }
                        return "KO";
                    case 'L':
                        return "CN";
                    case 'M':
                        if (cCharAt2 < 'A' || cCharAt2 > 'E') {
                            return null;
                        }
                        return "IN";
                    default:
                        switch (cCharAt) {
                            case 'V':
                                if (cCharAt2 >= 'F' && cCharAt2 <= 'R') {
                                    return "FR";
                                }
                                if (cCharAt2 < 'S' || cCharAt2 > 'W') {
                                    return null;
                                }
                                return "ES";
                            case 'W':
                                return "DE";
                            case 'X':
                                if (cCharAt2 == '0') {
                                    return "RU";
                                }
                                if (cCharAt2 < '3' || cCharAt2 > '9') {
                                    return null;
                                }
                                return "RU";
                            default:
                                return null;
                        }
                }
        }
    }

    private static int modelYear(char c9) {
        if (c9 >= 'E' && c9 <= 'H') {
            return (c9 - 'E') + 1984;
        }
        if (c9 >= 'J' && c9 <= 'N') {
            return (c9 - 'J') + 1988;
        }
        if (c9 == 'P') {
            return 1993;
        }
        if (c9 >= 'R' && c9 <= 'T') {
            return (c9 - 'R') + 1994;
        }
        if (c9 >= 'V' && c9 <= 'Y') {
            return (c9 - 'V') + 1997;
        }
        if (c9 >= '1' && c9 <= '9') {
            return (c9 - '1') + 2001;
        }
        if (c9 < 'A' || c9 > 'D') {
            throw new IllegalArgumentException();
        }
        return (c9 - 'A') + 2010;
    }

    private static int vinCharValue(char c9) {
        if (c9 >= 'A' && c9 <= 'I') {
            return (c9 - 'A') + 1;
        }
        if (c9 >= 'J' && c9 <= 'R') {
            return (c9 - 'J') + 1;
        }
        if (c9 >= 'S' && c9 <= 'Z') {
            return (c9 - 'S') + 2;
        }
        if (c9 < '0' || c9 > '9') {
            throw new IllegalArgumentException();
        }
        return c9 - '0';
    }

    private static int vinPositionWeight(int i9) {
        if (i9 >= 1 && i9 <= 7) {
            return 9 - i9;
        }
        if (i9 == 8) {
            return 10;
        }
        if (i9 == 9) {
            return 0;
        }
        if (i9 < 10 || i9 > 17) {
            throw new IllegalArgumentException();
        }
        return 19 - i9;
    }

    @Override // com.google.zxing.client.result.ResultParser
    public VINParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.CODE_39) {
            return null;
        }
        String strTrim = IOQ.matcher(result.getText()).replaceAll("").trim();
        if (!AZ09.matcher(strTrim).matches()) {
            return null;
        }
        try {
            if (!checkChecksum(strTrim)) {
                return null;
            }
            String strSubstring = strTrim.substring(0, 3);
            return new VINParsedResult(strTrim, strSubstring, strTrim.substring(3, 9), strTrim.substring(9, 17), countryCode(strSubstring), strTrim.substring(3, 8), modelYear(strTrim.charAt(9)), strTrim.charAt(10), strTrim.substring(11));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
