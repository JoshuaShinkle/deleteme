package com.google.zxing.common.reedsolomon;

/* loaded from: classes2.dex */
final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    public GenericGFPoly(GenericGF genericGF, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = genericGF;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.coefficients = iArr;
            return;
        }
        int i9 = 1;
        while (i9 < length && iArr[i9] == 0) {
            i9++;
        }
        if (i9 == length) {
            this.coefficients = new int[]{0};
            return;
        }
        int[] iArr2 = new int[length - i9];
        this.coefficients = iArr2;
        System.arraycopy(iArr, i9, iArr2, 0, iArr2.length);
    }

    public GenericGFPoly addOrSubtract(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero()) {
            return genericGFPoly;
        }
        if (genericGFPoly.isZero()) {
            return this;
        }
        int[] iArr = this.coefficients;
        int[] iArr2 = genericGFPoly.coefficients;
        if (iArr.length <= iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length);
        for (int i9 = length; i9 < iArr.length; i9++) {
            iArr3[i9] = GenericGF.addOrSubtract(iArr2[i9 - length], iArr[i9]);
        }
        return new GenericGFPoly(this.field, iArr3);
    }

    public GenericGFPoly[] divide(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (genericGFPoly.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        GenericGFPoly zero = this.field.getZero();
        int iInverse = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
        GenericGFPoly genericGFPolyAddOrSubtract = this;
        while (genericGFPolyAddOrSubtract.getDegree() >= genericGFPoly.getDegree() && !genericGFPolyAddOrSubtract.isZero()) {
            int degree = genericGFPolyAddOrSubtract.getDegree() - genericGFPoly.getDegree();
            int iMultiply = this.field.multiply(genericGFPolyAddOrSubtract.getCoefficient(genericGFPolyAddOrSubtract.getDegree()), iInverse);
            GenericGFPoly genericGFPolyMultiplyByMonomial = genericGFPoly.multiplyByMonomial(degree, iMultiply);
            zero = zero.addOrSubtract(this.field.buildMonomial(degree, iMultiply));
            genericGFPolyAddOrSubtract = genericGFPolyAddOrSubtract.addOrSubtract(genericGFPolyMultiplyByMonomial);
        }
        return new GenericGFPoly[]{zero, genericGFPolyAddOrSubtract};
    }

    public int evaluateAt(int i9) {
        if (i9 == 0) {
            return getCoefficient(0);
        }
        int[] iArr = this.coefficients;
        int length = iArr.length;
        if (i9 != 1) {
            int iAddOrSubtract = iArr[0];
            for (int i10 = 1; i10 < length; i10++) {
                iAddOrSubtract = GenericGF.addOrSubtract(this.field.multiply(i9, iAddOrSubtract), this.coefficients[i10]);
            }
            return iAddOrSubtract;
        }
        int iAddOrSubtract2 = 0;
        for (int i11 : iArr) {
            iAddOrSubtract2 = GenericGF.addOrSubtract(iAddOrSubtract2, i11);
        }
        return iAddOrSubtract2;
    }

    public int getCoefficient(int i9) {
        return this.coefficients[(r0.length - 1) - i9];
    }

    public int[] getCoefficients() {
        return this.coefficients;
    }

    public int getDegree() {
        return this.coefficients.length - 1;
    }

    public boolean isZero() {
        return this.coefficients[0] == 0;
    }

    public GenericGFPoly multiply(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero() || genericGFPoly.isZero()) {
            return this.field.getZero();
        }
        int[] iArr = this.coefficients;
        int length = iArr.length;
        int[] iArr2 = genericGFPoly.coefficients;
        int length2 = iArr2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        for (int i9 = 0; i9 < length; i9++) {
            int i10 = iArr[i9];
            for (int i11 = 0; i11 < length2; i11++) {
                int i12 = i9 + i11;
                iArr3[i12] = GenericGF.addOrSubtract(iArr3[i12], this.field.multiply(i10, iArr2[i11]));
            }
        }
        return new GenericGFPoly(this.field, iArr3);
    }

    public GenericGFPoly multiplyByMonomial(int i9, int i10) {
        if (i9 < 0) {
            throw new IllegalArgumentException();
        }
        if (i10 == 0) {
            return this.field.getZero();
        }
        int length = this.coefficients.length;
        int[] iArr = new int[i9 + length];
        for (int i11 = 0; i11 < length; i11++) {
            iArr[i11] = this.field.multiply(this.coefficients[i11], i10);
        }
        return new GenericGFPoly(this.field, iArr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    sb.append(" - ");
                    coefficient = -coefficient;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    int iLog = this.field.log(coefficient);
                    if (iLog == 0) {
                        sb.append('1');
                    } else if (iLog == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(iLog);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(degree);
                    }
                }
            }
        }
        return sb.toString();
    }

    public GenericGFPoly multiply(int i9) {
        if (i9 == 0) {
            return this.field.getZero();
        }
        if (i9 == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i10 = 0; i10 < length; i10++) {
            iArr[i10] = this.field.multiply(this.coefficients[i10], i9);
        }
        return new GenericGFPoly(this.field, iArr);
    }
}
