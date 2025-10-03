package org.apache.commons.lang3;

import java.util.Random;

/* loaded from: classes.dex */
public class RandomStringUtils {
    private static final Random RANDOM = new Random();

    public static String random(int i9) {
        return random(i9, false, false);
    }

    public static String randomAlphabetic(int i9) {
        return random(i9, true, false);
    }

    public static String randomAlphanumeric(int i9) {
        return random(i9, true, true);
    }

    public static String randomAscii(int i9) {
        return random(i9, 32, 127, false, false);
    }

    public static String randomNumeric(int i9) {
        return random(i9, false, true);
    }

    public static String random(int i9, boolean z8, boolean z9) {
        return random(i9, 0, 0, z8, z9);
    }

    public static String random(int i9, int i10, int i11, boolean z8, boolean z9) {
        return random(i9, i10, i11, z8, z9, null, RANDOM);
    }

    public static String random(int i9, int i10, int i11, boolean z8, boolean z9, char... cArr) {
        return random(i9, i10, i11, z8, z9, cArr, RANDOM);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String random(int i9, int i10, int i11, boolean z8, boolean z9, char[] cArr, Random random) {
        char cNextInt;
        if (i9 == 0) {
            return "";
        }
        if (i9 >= 0) {
            if (cArr != null && cArr.length == 0) {
                throw new IllegalArgumentException("The chars array must not be empty");
            }
            if (i10 == 0 && i11 == 0) {
                if (cArr != null) {
                    i11 = cArr.length;
                } else if (z8 || z9) {
                    i11 = 123;
                    i10 = 32;
                } else {
                    i11 = Integer.MAX_VALUE;
                }
            } else if (i11 <= i10) {
                throw new IllegalArgumentException("Parameter end (" + i11 + ") must be greater than start (" + i10 + ")");
            }
            char[] cArr2 = new char[i9];
            int i12 = i11 - i10;
            while (true) {
                int i13 = i9 - 1;
                if (i9 != 0) {
                    if (cArr == null) {
                        cNextInt = (char) (random.nextInt(i12) + i10);
                    } else {
                        cNextInt = cArr[random.nextInt(i12) + i10];
                    }
                    if (!(z8 && Character.isLetter(cNextInt)) && (!(z9 && Character.isDigit(cNextInt)) && (z8 || z9))) {
                        i13++;
                    } else if (cNextInt < 56320 || cNextInt > 57343) {
                        if (cNextInt < 55296 || cNextInt > 56191) {
                            if (cNextInt < 56192 || cNextInt > 56319) {
                                cArr2[i13] = cNextInt;
                            }
                        } else if (i13 != 0) {
                            cArr2[i13] = (char) (random.nextInt(128) + 56320);
                            i13--;
                            cArr2[i13] = cNextInt;
                        }
                    } else if (i13 != 0) {
                        cArr2[i13] = cNextInt;
                        i13--;
                        cArr2[i13] = (char) (random.nextInt(128) + 55296);
                    }
                    i9 = i13;
                } else {
                    return new String(cArr2);
                }
            }
        } else {
            throw new IllegalArgumentException("Requested random string length " + i9 + " is less than 0.");
        }
    }

    public static String random(int i9, String str) {
        if (str == null) {
            return random(i9, 0, 0, false, false, null, RANDOM);
        }
        return random(i9, str.toCharArray());
    }

    public static String random(int i9, char... cArr) {
        if (cArr == null) {
            return random(i9, 0, 0, false, false, null, RANDOM);
        }
        return random(i9, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
