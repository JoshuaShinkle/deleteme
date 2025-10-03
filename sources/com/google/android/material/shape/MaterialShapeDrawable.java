package com.google.android.material.shape;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: classes2.dex */
public class MaterialShapeDrawable extends Drawable {
    private int alpha;
    private final ShapePath[] cornerPaths;
    private final Matrix[] cornerTransforms;
    private final Matrix[] edgeTransforms;
    private float interpolation;
    private final Matrix matrix;
    private final Paint paint;
    private Paint.Style paintStyle;
    private final Path path;
    private final PointF pointF;
    private float scale;
    private final float[] scratch;
    private final float[] scratch2;
    private final Region scratchRegion;
    private int shadowColor;
    private int shadowElevation;
    private boolean shadowEnabled;
    private int shadowRadius;
    private final ShapePath shapePath;
    private ShapePathModel shapedViewModel;
    private float strokeWidth;
    private PorterDuffColorFilter tintFilter;
    private ColorStateList tintList;
    private PorterDuff.Mode tintMode;
    private final Region transparentRegion;
    private boolean useTintColorForShadow;

    public MaterialShapeDrawable() {
        this(null);
    }

    private float angleOfCorner(int i9, int i10, int i11) {
        getCoordinatesOfCorner(((i9 - 1) + 4) % 4, i10, i11, this.pointF);
        PointF pointF = this.pointF;
        float f9 = pointF.x;
        float f10 = pointF.y;
        getCoordinatesOfCorner((i9 + 1) % 4, i10, i11, pointF);
        PointF pointF2 = this.pointF;
        float f11 = pointF2.x;
        float f12 = pointF2.y;
        getCoordinatesOfCorner(i9, i10, i11, pointF2);
        PointF pointF3 = this.pointF;
        float f13 = pointF3.x;
        float f14 = pointF3.y;
        float fAtan2 = ((float) Math.atan2(f10 - f14, f9 - f13)) - ((float) Math.atan2(f12 - f14, f11 - f13));
        return fAtan2 < BitmapDescriptorFactory.HUE_RED ? (float) (fAtan2 + 6.283185307179586d) : fAtan2;
    }

    private float angleOfEdge(int i9, int i10, int i11) {
        int i12 = (i9 + 1) % 4;
        getCoordinatesOfCorner(i9, i10, i11, this.pointF);
        PointF pointF = this.pointF;
        float f9 = pointF.x;
        float f10 = pointF.y;
        getCoordinatesOfCorner(i12, i10, i11, pointF);
        PointF pointF2 = this.pointF;
        return (float) Math.atan2(pointF2.y - f10, pointF2.x - f9);
    }

    private void appendCornerPath(int i9, Path path) {
        float[] fArr = this.scratch;
        ShapePath shapePath = this.cornerPaths[i9];
        fArr[0] = shapePath.startX;
        fArr[1] = shapePath.startY;
        this.cornerTransforms[i9].mapPoints(fArr);
        if (i9 == 0) {
            float[] fArr2 = this.scratch;
            path.moveTo(fArr2[0], fArr2[1]);
        } else {
            float[] fArr3 = this.scratch;
            path.lineTo(fArr3[0], fArr3[1]);
        }
        this.cornerPaths[i9].applyToPath(this.cornerTransforms[i9], path);
    }

    private void appendEdgePath(int i9, Path path) {
        int i10 = (i9 + 1) % 4;
        float[] fArr = this.scratch;
        ShapePath shapePath = this.cornerPaths[i9];
        fArr[0] = shapePath.endX;
        fArr[1] = shapePath.endY;
        this.cornerTransforms[i9].mapPoints(fArr);
        float[] fArr2 = this.scratch2;
        ShapePath shapePath2 = this.cornerPaths[i10];
        fArr2[0] = shapePath2.startX;
        fArr2[1] = shapePath2.startY;
        this.cornerTransforms[i10].mapPoints(fArr2);
        float f9 = this.scratch[0];
        float[] fArr3 = this.scratch2;
        float fHypot = (float) Math.hypot(f9 - fArr3[0], r0[1] - fArr3[1]);
        this.shapePath.reset(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        getEdgeTreatmentForIndex(i9).getEdgePath(fHypot, this.interpolation, this.shapePath);
        this.shapePath.applyToPath(this.edgeTransforms[i9], path);
    }

    private void getCoordinatesOfCorner(int i9, int i10, int i11, PointF pointF) {
        if (i9 == 1) {
            pointF.set(i10, BitmapDescriptorFactory.HUE_RED);
            return;
        }
        if (i9 == 2) {
            pointF.set(i10, i11);
        } else if (i9 != 3) {
            pointF.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        } else {
            pointF.set(BitmapDescriptorFactory.HUE_RED, i11);
        }
    }

    private CornerTreatment getCornerTreatmentForIndex(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? this.shapedViewModel.getTopLeftCorner() : this.shapedViewModel.getBottomLeftCorner() : this.shapedViewModel.getBottomRightCorner() : this.shapedViewModel.getTopRightCorner();
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? this.shapedViewModel.getTopEdge() : this.shapedViewModel.getLeftEdge() : this.shapedViewModel.getBottomEdge() : this.shapedViewModel.getRightEdge();
    }

    private void getPath(int i9, int i10, Path path) {
        getPathForSize(i9, i10, path);
        if (this.scale == 1.0f) {
            return;
        }
        this.matrix.reset();
        Matrix matrix = this.matrix;
        float f9 = this.scale;
        matrix.setScale(f9, f9, i9 / 2, i10 / 2);
        path.transform(this.matrix);
    }

    private static int modulateAlpha(int i9, int i10) {
        return (i9 * (i10 + (i10 >>> 7))) >>> 8;
    }

    private void setCornerPathAndTransform(int i9, int i10, int i11) {
        getCoordinatesOfCorner(i9, i10, i11, this.pointF);
        getCornerTreatmentForIndex(i9).getCornerPath(angleOfCorner(i9, i10, i11), this.interpolation, this.cornerPaths[i9]);
        float fAngleOfEdge = angleOfEdge(((i9 - 1) + 4) % 4, i10, i11) + 1.5707964f;
        this.cornerTransforms[i9].reset();
        Matrix matrix = this.cornerTransforms[i9];
        PointF pointF = this.pointF;
        matrix.setTranslate(pointF.x, pointF.y);
        this.cornerTransforms[i9].preRotate((float) Math.toDegrees(fAngleOfEdge));
    }

    private void setEdgeTransform(int i9, int i10, int i11) {
        float[] fArr = this.scratch;
        ShapePath shapePath = this.cornerPaths[i9];
        fArr[0] = shapePath.endX;
        fArr[1] = shapePath.endY;
        this.cornerTransforms[i9].mapPoints(fArr);
        float fAngleOfEdge = angleOfEdge(i9, i10, i11);
        this.edgeTransforms[i9].reset();
        Matrix matrix = this.edgeTransforms[i9];
        float[] fArr2 = this.scratch;
        matrix.setTranslate(fArr2[0], fArr2[1]);
        this.edgeTransforms[i9].preRotate((float) Math.toDegrees(fAngleOfEdge));
    }

    private void updateTintFilter() {
        ColorStateList colorStateList = this.tintList;
        if (colorStateList == null || this.tintMode == null) {
            this.tintFilter = null;
            return;
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        this.tintFilter = new PorterDuffColorFilter(colorForState, this.tintMode);
        if (this.useTintColorForShadow) {
            this.shadowColor = colorForState;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.paint.setColorFilter(this.tintFilter);
        int alpha = this.paint.getAlpha();
        this.paint.setAlpha(modulateAlpha(alpha, this.alpha));
        this.paint.setStrokeWidth(this.strokeWidth);
        this.paint.setStyle(this.paintStyle);
        int i9 = this.shadowElevation;
        if (i9 > 0 && this.shadowEnabled) {
            this.paint.setShadowLayer(this.shadowRadius, BitmapDescriptorFactory.HUE_RED, i9, this.shadowColor);
        }
        if (this.shapedViewModel != null) {
            getPath(canvas.getWidth(), canvas.getHeight(), this.path);
            canvas.drawPath(this.path, this.paint);
        } else {
            canvas.drawRect(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, canvas.getWidth(), canvas.getHeight(), this.paint);
        }
        this.paint.setAlpha(alpha);
    }

    public float getInterpolation() {
        return this.interpolation;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public Paint.Style getPaintStyle() {
        return this.paintStyle;
    }

    public void getPathForSize(int i9, int i10, Path path) {
        path.rewind();
        if (this.shapedViewModel == null) {
            return;
        }
        for (int i11 = 0; i11 < 4; i11++) {
            setCornerPathAndTransform(i11, i9, i10);
            setEdgeTransform(i11, i9, i10);
        }
        for (int i12 = 0; i12 < 4; i12++) {
            appendCornerPath(i12, path);
            appendEdgePath(i12, path);
        }
        path.close();
    }

    public float getScale() {
        return this.scale;
    }

    public int getShadowElevation() {
        return this.shadowElevation;
    }

    public int getShadowRadius() {
        return this.shadowRadius;
    }

    public ShapePathModel getShapedViewModel() {
        return this.shapedViewModel;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public ColorStateList getTintList() {
        return this.tintList;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        Rect bounds = getBounds();
        this.transparentRegion.set(bounds);
        getPath(bounds.width(), bounds.height(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public boolean isPointInTransparentRegion(int i9, int i10) {
        return getTransparentRegion().contains(i9, i10);
    }

    public boolean isShadowEnabled() {
        return this.shadowEnabled;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        this.alpha = i9;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setInterpolation(float f9) {
        this.interpolation = f9;
        invalidateSelf();
    }

    public void setPaintStyle(Paint.Style style) {
        this.paintStyle = style;
        invalidateSelf();
    }

    public void setScale(float f9) {
        this.scale = f9;
        invalidateSelf();
    }

    public void setShadowColor(int i9) {
        this.shadowColor = i9;
        this.useTintColorForShadow = false;
        invalidateSelf();
    }

    public void setShadowElevation(int i9) {
        this.shadowElevation = i9;
        invalidateSelf();
    }

    public void setShadowEnabled(boolean z8) {
        this.shadowEnabled = z8;
        invalidateSelf();
    }

    public void setShadowRadius(int i9) {
        this.shadowRadius = i9;
        invalidateSelf();
    }

    public void setShapedViewModel(ShapePathModel shapePathModel) {
        this.shapedViewModel = shapePathModel;
        invalidateSelf();
    }

    public void setStrokeWidth(float f9) {
        this.strokeWidth = f9;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i9) {
        setTintList(ColorStateList.valueOf(i9));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.tintList = colorStateList;
        updateTintFilter();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.tintMode = mode;
        updateTintFilter();
        invalidateSelf();
    }

    public void setUseTintColorForShadow(boolean z8) {
        this.useTintColorForShadow = z8;
        invalidateSelf();
    }

    public MaterialShapeDrawable(ShapePathModel shapePathModel) {
        this.paint = new Paint();
        this.cornerTransforms = new Matrix[4];
        this.edgeTransforms = new Matrix[4];
        this.cornerPaths = new ShapePath[4];
        this.matrix = new Matrix();
        this.path = new Path();
        this.pointF = new PointF();
        this.shapePath = new ShapePath();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        this.scratch = new float[2];
        this.scratch2 = new float[2];
        this.shapedViewModel = null;
        this.shadowEnabled = false;
        this.useTintColorForShadow = false;
        this.interpolation = 1.0f;
        this.shadowColor = -16777216;
        this.shadowElevation = 5;
        this.shadowRadius = 10;
        this.alpha = 255;
        this.scale = 1.0f;
        this.strokeWidth = BitmapDescriptorFactory.HUE_RED;
        this.paintStyle = Paint.Style.FILL_AND_STROKE;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.tintList = null;
        this.shapedViewModel = shapePathModel;
        for (int i9 = 0; i9 < 4; i9++) {
            this.cornerTransforms[i9] = new Matrix();
            this.edgeTransforms[i9] = new Matrix();
            this.cornerPaths[i9] = new ShapePath();
        }
    }
}
