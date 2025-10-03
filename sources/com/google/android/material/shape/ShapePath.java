package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.internal.Experimental;
import java.util.ArrayList;
import java.util.List;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes2.dex */
public class ShapePath {
    public float endX;
    public float endY;
    private final List<PathOperation> operations = new ArrayList();
    public float startX;
    public float startY;

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = new RectF();
        public float bottom;
        public float left;
        public float right;
        public float startAngle;
        public float sweepAngle;
        public float top;

        public PathArcOperation(float f9, float f10, float f11, float f12) {
            this.left = f9;
            this.top = f10;
            this.right = f11;
            this.bottom = f12;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix matrix, Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF2 = rectF;
            rectF2.set(this.left, this.top, this.right, this.bottom);
            path.arcTo(rectF2, this.startAngle, this.sweepAngle, false);
            path.transform(matrix);
        }
    }

    public static class PathLineOperation extends PathOperation {

        /* renamed from: x */
        private float f15377x;

        /* renamed from: y */
        private float f15378y;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix matrix, Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f15377x, this.f15378y);
            path.transform(matrix);
        }
    }

    public static abstract class PathOperation {
        protected final Matrix matrix = new Matrix();

        public abstract void applyToPath(Matrix matrix, Path path);
    }

    public static class PathQuadOperation extends PathOperation {
        public float controlX;
        public float controlY;
        public float endX;
        public float endY;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix matrix, Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(this.controlX, this.controlY, this.endX, this.endY);
            path.transform(matrix);
        }
    }

    public ShapePath() {
        reset(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
    }

    public void addArc(float f9, float f10, float f11, float f12, float f13, float f14) {
        PathArcOperation pathArcOperation = new PathArcOperation(f9, f10, f11, f12);
        pathArcOperation.startAngle = f13;
        pathArcOperation.sweepAngle = f14;
        this.operations.add(pathArcOperation);
        double d9 = f13 + f14;
        this.endX = ((f9 + f11) * 0.5f) + (((f11 - f9) / 2.0f) * ((float) Math.cos(Math.toRadians(d9))));
        this.endY = ((f10 + f12) * 0.5f) + (((f12 - f10) / 2.0f) * ((float) Math.sin(Math.toRadians(d9))));
    }

    public void applyToPath(Matrix matrix, Path path) {
        int size = this.operations.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.operations.get(i9).applyToPath(matrix, path);
        }
    }

    public void lineTo(float f9, float f10) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.f15377x = f9;
        pathLineOperation.f15378y = f10;
        this.operations.add(pathLineOperation);
        this.endX = f9;
        this.endY = f10;
    }

    public void quadToPoint(float f9, float f10, float f11, float f12) {
        PathQuadOperation pathQuadOperation = new PathQuadOperation();
        pathQuadOperation.controlX = f9;
        pathQuadOperation.controlY = f10;
        pathQuadOperation.endX = f11;
        pathQuadOperation.endY = f12;
        this.operations.add(pathQuadOperation);
        this.endX = f11;
        this.endY = f12;
    }

    public void reset(float f9, float f10) {
        this.startX = f9;
        this.startY = f10;
        this.endX = f9;
        this.endY = f10;
        this.operations.clear();
    }

    public ShapePath(float f9, float f10) {
        reset(f9, f10);
    }
}
