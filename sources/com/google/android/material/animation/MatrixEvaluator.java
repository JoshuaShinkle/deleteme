package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* loaded from: classes2.dex */
public class MatrixEvaluator implements TypeEvaluator<Matrix> {
    private final float[] tempStartValues = new float[9];
    private final float[] tempEndValues = new float[9];
    private final Matrix tempMatrix = new Matrix();

    @Override // android.animation.TypeEvaluator
    public Matrix evaluate(float f9, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.tempStartValues);
        matrix2.getValues(this.tempEndValues);
        for (int i9 = 0; i9 < 9; i9++) {
            float[] fArr = this.tempEndValues;
            float f10 = fArr[i9];
            float f11 = this.tempStartValues[i9];
            fArr[i9] = f11 + ((f10 - f11) * f9);
        }
        this.tempMatrix.setValues(this.tempEndValues);
        return this.tempMatrix;
    }
}
