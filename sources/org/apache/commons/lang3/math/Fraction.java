package org.apache.commons.lang3.math;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes.dex */
public final class Fraction extends Number implements Comparable<Fraction> {
    private static final long serialVersionUID = 65382027393090L;
    private final int denominator;
    private final int numerator;
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    private transient int hashCode = 0;
    private transient String toString = null;
    private transient String toProperString = null;

    private Fraction(int i9, int i10) {
        this.numerator = i9;
        this.denominator = i10;
    }

    private static int addAndCheck(int i9, int i10) {
        long j9 = i9 + i10;
        if (j9 < -2147483648L || j9 > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j9;
    }

    private Fraction addSub(Fraction fraction, boolean z8) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        }
        if (this.numerator == 0) {
            return z8 ? fraction : fraction.negate();
        }
        if (fraction.numerator == 0) {
            return this;
        }
        int iGreatestCommonDivisor = greatestCommonDivisor(this.denominator, fraction.denominator);
        if (iGreatestCommonDivisor == 1) {
            int iMulAndCheck = mulAndCheck(this.numerator, fraction.denominator);
            int iMulAndCheck2 = mulAndCheck(fraction.numerator, this.denominator);
            return new Fraction(z8 ? addAndCheck(iMulAndCheck, iMulAndCheck2) : subAndCheck(iMulAndCheck, iMulAndCheck2), mulPosAndCheck(this.denominator, fraction.denominator));
        }
        BigInteger bigIntegerMultiply = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / iGreatestCommonDivisor));
        BigInteger bigIntegerMultiply2 = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / iGreatestCommonDivisor));
        BigInteger bigIntegerAdd = z8 ? bigIntegerMultiply.add(bigIntegerMultiply2) : bigIntegerMultiply.subtract(bigIntegerMultiply2);
        int iIntValue = bigIntegerAdd.mod(BigInteger.valueOf(iGreatestCommonDivisor)).intValue();
        int iGreatestCommonDivisor2 = iIntValue == 0 ? iGreatestCommonDivisor : greatestCommonDivisor(iIntValue, iGreatestCommonDivisor);
        BigInteger bigIntegerDivide = bigIntegerAdd.divide(BigInteger.valueOf(iGreatestCommonDivisor2));
        if (bigIntegerDivide.bitLength() <= 31) {
            return new Fraction(bigIntegerDivide.intValue(), mulPosAndCheck(this.denominator / iGreatestCommonDivisor, fraction.denominator / iGreatestCommonDivisor2));
        }
        throw new ArithmeticException("overflow: numerator too large after multiply");
    }

    public static Fraction getFraction(int i9, int i10) {
        if (i10 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (i10 < 0) {
            if (i9 == Integer.MIN_VALUE || i10 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            i9 = -i9;
            i10 = -i10;
        }
        return new Fraction(i9, i10);
    }

    public static Fraction getReducedFraction(int i9, int i10) {
        if (i10 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (i9 == 0) {
            return ZERO;
        }
        if (i10 == Integer.MIN_VALUE && (i9 & 1) == 0) {
            i9 /= 2;
            i10 /= 2;
        }
        if (i10 < 0) {
            if (i9 == Integer.MIN_VALUE || i10 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            i9 = -i9;
            i10 = -i10;
        }
        int iGreatestCommonDivisor = greatestCommonDivisor(i9, i10);
        return new Fraction(i9 / iGreatestCommonDivisor, i10 / iGreatestCommonDivisor);
    }

    private static int greatestCommonDivisor(int i9, int i10) {
        int i11;
        if (i9 == 0 || i10 == 0) {
            if (i9 == Integer.MIN_VALUE || i10 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: gcd is 2^31");
            }
            return Math.abs(i9) + Math.abs(i10);
        }
        if (Math.abs(i9) == 1 || Math.abs(i10) == 1) {
            return 1;
        }
        if (i9 > 0) {
            i9 = -i9;
        }
        if (i10 > 0) {
            i10 = -i10;
        }
        int i12 = 0;
        while (true) {
            i11 = i9 & 1;
            if (i11 != 0 || (i10 & 1) != 0 || i12 >= 31) {
                break;
            }
            i9 /= 2;
            i10 /= 2;
            i12++;
        }
        if (i12 == 31) {
            throw new ArithmeticException("overflow: gcd is 2^31");
        }
        int i13 = i11 == 1 ? i10 : -(i9 / 2);
        while (true) {
            if ((i13 & 1) == 0) {
                i13 /= 2;
            } else {
                if (i13 > 0) {
                    i9 = -i13;
                } else {
                    i10 = i13;
                }
                i13 = (i10 - i9) / 2;
                if (i13 == 0) {
                    return (-i9) * (1 << i12);
                }
            }
        }
    }

    private static int mulAndCheck(int i9, int i10) {
        long j9 = i9 * i10;
        if (j9 < -2147483648L || j9 > 2147483647L) {
            throw new ArithmeticException("overflow: mul");
        }
        return (int) j9;
    }

    private static int mulPosAndCheck(int i9, int i10) {
        long j9 = i9 * i10;
        if (j9 <= 2147483647L) {
            return (int) j9;
        }
        throw new ArithmeticException("overflow: mulPos");
    }

    private static int subAndCheck(int i9, int i10) {
        long j9 = i9 - i10;
        if (j9 < -2147483648L || j9 > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j9;
    }

    public Fraction abs() {
        return this.numerator >= 0 ? this : negate();
    }

    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    public Fraction divideBy(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        }
        if (fraction.numerator != 0) {
            return multiplyBy(fraction.invert());
        }
        throw new ArithmeticException("The fraction to divide by must not be zero");
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.numerator / this.denominator;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        return getNumerator() == fraction.getNumerator() && getDenominator() == fraction.getDenominator();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.numerator / this.denominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getProperNumerator() {
        return Math.abs(this.numerator % this.denominator);
    }

    public int getProperWhole() {
        return this.numerator / this.denominator;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((getNumerator() + 629) * 37) + getDenominator();
        }
        return this.hashCode;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.numerator / this.denominator;
    }

    public Fraction invert() {
        int i9 = this.numerator;
        if (i9 == 0) {
            throw new ArithmeticException("Unable to invert zero.");
        }
        if (i9 != Integer.MIN_VALUE) {
            return i9 < 0 ? new Fraction(-this.denominator, -i9) : new Fraction(this.denominator, i9);
        }
        throw new ArithmeticException("overflow: can't negate numerator");
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.numerator / this.denominator;
    }

    public Fraction multiplyBy(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        }
        int i9 = this.numerator;
        if (i9 == 0 || fraction.numerator == 0) {
            return ZERO;
        }
        int iGreatestCommonDivisor = greatestCommonDivisor(i9, fraction.denominator);
        int iGreatestCommonDivisor2 = greatestCommonDivisor(fraction.numerator, this.denominator);
        return getReducedFraction(mulAndCheck(this.numerator / iGreatestCommonDivisor, fraction.numerator / iGreatestCommonDivisor2), mulPosAndCheck(this.denominator / iGreatestCommonDivisor2, fraction.denominator / iGreatestCommonDivisor));
    }

    public Fraction negate() {
        int i9 = this.numerator;
        if (i9 != Integer.MIN_VALUE) {
            return new Fraction(-i9, this.denominator);
        }
        throw new ArithmeticException("overflow: too large to negate");
    }

    public Fraction pow(int i9) {
        if (i9 == 1) {
            return this;
        }
        if (i9 == 0) {
            return ONE;
        }
        if (i9 < 0) {
            return i9 == Integer.MIN_VALUE ? invert().pow(2).pow(-(i9 / 2)) : invert().pow(-i9);
        }
        Fraction fractionMultiplyBy = multiplyBy(this);
        return i9 % 2 == 0 ? fractionMultiplyBy.pow(i9 / 2) : fractionMultiplyBy.pow(i9 / 2).multiplyBy(this);
    }

    public Fraction reduce() {
        int i9 = this.numerator;
        if (i9 == 0) {
            Fraction fraction = ZERO;
            return equals(fraction) ? this : fraction;
        }
        int iGreatestCommonDivisor = greatestCommonDivisor(Math.abs(i9), this.denominator);
        return iGreatestCommonDivisor == 1 ? this : getFraction(this.numerator / iGreatestCommonDivisor, this.denominator / iGreatestCommonDivisor);
    }

    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public String toProperString() {
        if (this.toProperString == null) {
            int i9 = this.numerator;
            if (i9 == 0) {
                this.toProperString = "0";
            } else {
                int i10 = this.denominator;
                if (i9 == i10) {
                    this.toProperString = "1";
                } else if (i9 == i10 * (-1)) {
                    this.toProperString = "-1";
                } else {
                    if (i9 > 0) {
                        i9 = -i9;
                    }
                    if (i9 < (-i10)) {
                        int properNumerator = getProperNumerator();
                        if (properNumerator == 0) {
                            this.toProperString = Integer.toString(getProperWhole());
                        } else {
                            StringBuilder sb = new StringBuilder(32);
                            sb.append(getProperWhole());
                            sb.append(' ');
                            sb.append(properNumerator);
                            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                            sb.append(getDenominator());
                            this.toProperString = sb.toString();
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder(32);
                        sb2.append(getNumerator());
                        sb2.append(IOUtils.DIR_SEPARATOR_UNIX);
                        sb2.append(getDenominator());
                        this.toProperString = sb2.toString();
                    }
                }
            }
        }
        return this.toProperString;
    }

    public String toString() {
        if (this.toString == null) {
            StringBuilder sb = new StringBuilder(32);
            sb.append(getNumerator());
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(getDenominator());
            this.toString = sb.toString();
        }
        return this.toString;
    }

    @Override // java.lang.Comparable
    public int compareTo(Fraction fraction) {
        if (this == fraction) {
            return 0;
        }
        int i9 = this.numerator;
        int i10 = fraction.numerator;
        if (i9 == i10 && this.denominator == fraction.denominator) {
            return 0;
        }
        long j9 = i9 * fraction.denominator;
        long j10 = i10 * this.denominator;
        if (j9 == j10) {
            return 0;
        }
        return j9 < j10 ? -1 : 1;
    }

    public static Fraction getFraction(int i9, int i10, int i11) {
        if (i11 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (i11 < 0) {
            throw new ArithmeticException("The denominator must not be negative");
        }
        if (i10 < 0) {
            throw new ArithmeticException("The numerator must not be negative");
        }
        long j9 = i9 < 0 ? (i9 * i11) - i10 : (i9 * i11) + i10;
        if (j9 >= -2147483648L && j9 <= 2147483647L) {
            return new Fraction((int) j9, i11);
        }
        throw new ArithmeticException("Numerator too large to represent as an Integer.");
    }

    public static Fraction getFraction(double d9) {
        int i9;
        int i10 = d9 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? -1 : 1;
        double dAbs = Math.abs(d9);
        if (dAbs > 2.147483647E9d || Double.isNaN(dAbs)) {
            throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN");
        }
        int i11 = (int) dAbs;
        double d10 = dAbs - i11;
        int i12 = (int) d10;
        double d11 = d10 - i12;
        double d12 = d10;
        double d13 = Double.MAX_VALUE;
        int i13 = 1;
        int i14 = 1;
        double d14 = 1.0d;
        int i15 = 0;
        int i16 = 0;
        int i17 = 1;
        while (true) {
            int i18 = (int) (d14 / d11);
            double d15 = d14 - (i18 * d11);
            int i19 = (i12 * i17) + i15;
            int i20 = (i12 * i16) + i13;
            double d16 = d11;
            double d17 = d12;
            double dAbs2 = Math.abs(d17 - (i19 / i20));
            i9 = i14 + 1;
            if (d13 <= dAbs2 || i20 > 10000 || i20 <= 0 || i9 >= 25) {
                break;
            }
            d13 = dAbs2;
            d12 = d17;
            i13 = i16;
            i14 = i9;
            d11 = d15;
            i16 = i20;
            i12 = i18;
            i15 = i17;
            i17 = i19;
            d14 = d16;
        }
        if (i9 != 25) {
            return getReducedFraction((i17 + (i11 * i16)) * i10, i16);
        }
        throw new ArithmeticException("Unable to convert double to fraction");
    }

    public static Fraction getFraction(String str) throws NumberFormatException {
        if (str != null) {
            if (str.indexOf(46) >= 0) {
                return getFraction(Double.parseDouble(str));
            }
            int iIndexOf = str.indexOf(32);
            if (iIndexOf > 0) {
                int i9 = Integer.parseInt(str.substring(0, iIndexOf));
                String strSubstring = str.substring(iIndexOf + 1);
                int iIndexOf2 = strSubstring.indexOf(47);
                if (iIndexOf2 >= 0) {
                    return getFraction(i9, Integer.parseInt(strSubstring.substring(0, iIndexOf2)), Integer.parseInt(strSubstring.substring(iIndexOf2 + 1)));
                }
                throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
            }
            int iIndexOf3 = str.indexOf(47);
            if (iIndexOf3 < 0) {
                return getFraction(Integer.parseInt(str), 1);
            }
            return getFraction(Integer.parseInt(str.substring(0, iIndexOf3)), Integer.parseInt(str.substring(iIndexOf3 + 1)));
        }
        throw new IllegalArgumentException("The string must not be null");
    }
}
