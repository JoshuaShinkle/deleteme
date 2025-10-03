package org.webrtc;

import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class RendererCommon {
    private static float BALANCED_VISIBLE_FRACTION = 0.5625f;

    /* renamed from: org.webrtc.RendererCommon$1 */
    public static /* synthetic */ class C58351 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$RendererCommon$ScalingType;

        static {
            int[] iArr = new int[ScalingType.values().length];
            $SwitchMap$org$webrtc$RendererCommon$ScalingType = iArr;
            try {
                iArr[ScalingType.SCALE_ASPECT_FIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$RendererCommon$ScalingType[ScalingType.SCALE_ASPECT_FILL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$RendererCommon$ScalingType[ScalingType.SCALE_ASPECT_BALANCED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public interface GlDrawer {
        void drawOes(int i9, float[] fArr, int i10, int i11, int i12, int i13, int i14, int i15);

        void drawRgb(int i9, float[] fArr, int i10, int i11, int i12, int i13, int i14, int i15);

        void drawYuv(int[] iArr, float[] fArr, int i9, int i10, int i11, int i12, int i13, int i14);

        void release();
    }

    public interface RendererEvents {
        void onFirstFrameRendered();

        void onFrameResolutionChanged(int i9, int i10, int i11);
    }

    public enum ScalingType {
        SCALE_ASPECT_FIT,
        SCALE_ASPECT_FILL,
        SCALE_ASPECT_BALANCED
    }

    public static class YuvUploader {
        private ByteBuffer copyBuffer;

        public void uploadYuvData(int[] iArr, int i9, int i10, int[] iArr2, ByteBuffer[] byteBufferArr) {
            ByteBuffer byteBuffer;
            ByteBuffer byteBuffer2;
            int i11 = i9 / 2;
            int[] iArr3 = {i9, i11, i11};
            int i12 = i10 / 2;
            int[] iArr4 = {i10, i12, i12};
            int iMax = 0;
            for (int i13 = 0; i13 < 3; i13++) {
                int i14 = iArr2[i13];
                int i15 = iArr3[i13];
                if (i14 > i15) {
                    iMax = Math.max(iMax, i15 * iArr4[i13]);
                }
            }
            if (iMax > 0 && ((byteBuffer2 = this.copyBuffer) == null || byteBuffer2.capacity() < iMax)) {
                this.copyBuffer = ByteBuffer.allocateDirect(iMax);
            }
            for (int i16 = 0; i16 < 3; i16++) {
                GLES20.glActiveTexture(33984 + i16);
                GLES20.glBindTexture(3553, iArr[i16]);
                int i17 = iArr2[i16];
                int i18 = iArr3[i16];
                if (i17 == i18) {
                    byteBuffer = byteBufferArr[i16];
                } else {
                    VideoRenderer.nativeCopyPlane(byteBufferArr[i16], i18, iArr4[i16], i17, this.copyBuffer, i18);
                    byteBuffer = this.copyBuffer;
                }
                GLES20.glTexImage2D(3553, 0, 6409, iArr3[i16], iArr4[i16], 0, 6409, 5121, byteBuffer);
            }
        }
    }

    private static void adjustOrigin(float[] fArr) {
        float f9 = fArr[12] - ((fArr[0] + fArr[4]) * 0.5f);
        fArr[12] = f9;
        float f10 = fArr[13] - ((fArr[1] + fArr[5]) * 0.5f);
        fArr[13] = f10;
        fArr[12] = f9 + 0.5f;
        fArr[13] = f10 + 0.5f;
    }

    private static float convertScalingTypeToVisibleFraction(ScalingType scalingType) {
        int i9 = C58351.$SwitchMap$org$webrtc$RendererCommon$ScalingType[scalingType.ordinal()];
        if (i9 == 1) {
            return 1.0f;
        }
        if (i9 == 2) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        if (i9 == 3) {
            return BALANCED_VISIBLE_FRACTION;
        }
        throw new IllegalArgumentException();
    }

    public static Point getDisplaySize(ScalingType scalingType, float f9, int i9, int i10) {
        return getDisplaySize(convertScalingTypeToVisibleFraction(scalingType), f9, i9, i10);
    }

    public static float[] getLayoutMatrix(boolean z8, float f9, float f10) {
        float f11;
        float f12;
        if (f10 > f9) {
            f12 = f9 / f10;
            f11 = 1.0f;
        } else {
            f11 = f10 / f9;
            f12 = 1.0f;
        }
        if (z8) {
            f11 *= -1.0f;
        }
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        Matrix.scaleM(fArr, 0, f11, f12, 1.0f);
        adjustOrigin(fArr);
        return fArr;
    }

    public static final float[] horizontalFlipMatrix() {
        return new float[]{-1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};
    }

    public static final float[] identityMatrix() {
        return new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};
    }

    public static float[] multiplyMatrices(float[] fArr, float[] fArr2) {
        float[] fArr3 = new float[16];
        Matrix.multiplyMM(fArr3, 0, fArr, 0, fArr2, 0);
        return fArr3;
    }

    public static float[] rotateTextureMatrix(float[] fArr, float f9) {
        float[] fArr2 = new float[16];
        Matrix.setRotateM(fArr2, 0, f9, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f);
        adjustOrigin(fArr2);
        return multiplyMatrices(fArr, fArr2);
    }

    public static final float[] verticalFlipMatrix() {
        return new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f};
    }

    public static class VideoLayoutMeasure {
        private ScalingType scalingTypeMatchOrientation;
        private ScalingType scalingTypeMismatchOrientation;

        public VideoLayoutMeasure() {
            ScalingType scalingType = ScalingType.SCALE_ASPECT_BALANCED;
            this.scalingTypeMatchOrientation = scalingType;
            this.scalingTypeMismatchOrientation = scalingType;
        }

        public Point measure(int i9, int i10, int i11, int i12) {
            int defaultSize = View.getDefaultSize(Integer.MAX_VALUE, i9);
            int defaultSize2 = View.getDefaultSize(Integer.MAX_VALUE, i10);
            if (i11 == 0 || i12 == 0 || defaultSize == 0 || defaultSize2 == 0) {
                return new Point(defaultSize, defaultSize2);
            }
            float f9 = i11 / i12;
            Point displaySize = RendererCommon.getDisplaySize(((f9 > 1.0f ? 1 : (f9 == 1.0f ? 0 : -1)) > 0) == (((float) defaultSize) / ((float) defaultSize2) > 1.0f) ? this.scalingTypeMatchOrientation : this.scalingTypeMismatchOrientation, f9, defaultSize, defaultSize2);
            if (View.MeasureSpec.getMode(i9) == 1073741824) {
                displaySize.x = defaultSize;
            }
            if (View.MeasureSpec.getMode(i10) == 1073741824) {
                displaySize.y = defaultSize2;
            }
            return displaySize;
        }

        public void setScalingType(ScalingType scalingType) {
            this.scalingTypeMatchOrientation = scalingType;
            this.scalingTypeMismatchOrientation = scalingType;
        }

        public void setScalingType(ScalingType scalingType, ScalingType scalingType2) {
            this.scalingTypeMatchOrientation = scalingType;
            this.scalingTypeMismatchOrientation = scalingType2;
        }
    }

    private static Point getDisplaySize(float f9, float f10, int i9, int i10) {
        return (f9 == BitmapDescriptorFactory.HUE_RED || f10 == BitmapDescriptorFactory.HUE_RED) ? new Point(i9, i10) : new Point(Math.min(i9, Math.round((i10 / f9) * f10)), Math.min(i10, Math.round((i9 / f9) / f10)));
    }
}
