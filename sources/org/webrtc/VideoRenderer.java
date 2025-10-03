package org.webrtc;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class VideoRenderer {
    long nativeVideoRenderer;

    public interface Callbacks {
        void renderFrame(I420Frame i420Frame);
    }

    public VideoRenderer(Callbacks callbacks) {
        this.nativeVideoRenderer = nativeWrapVideoRenderer(callbacks);
    }

    private static native void freeWrappedVideoRenderer(long j9);

    public static native void nativeCopyPlane(ByteBuffer byteBuffer, int i9, int i10, int i11, ByteBuffer byteBuffer2, int i12);

    private static native long nativeWrapVideoRenderer(Callbacks callbacks);

    private static native void releaseNativeFrame(long j9);

    public static void renderFrameDone(I420Frame i420Frame) {
        i420Frame.yuvPlanes = null;
        i420Frame.textureId = 0;
        if (i420Frame.nativeFramePointer != 0) {
            releaseNativeFrame(i420Frame.nativeFramePointer);
            i420Frame.nativeFramePointer = 0L;
        }
    }

    public void dispose() {
        long j9 = this.nativeVideoRenderer;
        if (j9 == 0) {
            return;
        }
        freeWrappedVideoRenderer(j9);
        this.nativeVideoRenderer = 0L;
    }

    public static class I420Frame {
        public final int height;
        private long nativeFramePointer;
        public int rotationDegree;
        public final float[] samplingMatrix;
        public int textureId;
        public final int width;
        public final boolean yuvFrame;
        public ByteBuffer[] yuvPlanes;
        public final int[] yuvStrides;

        public I420Frame(int i9, int i10, int i11, int[] iArr, ByteBuffer[] byteBufferArr, long j9) {
            this.width = i9;
            this.height = i10;
            this.yuvStrides = iArr;
            this.yuvPlanes = byteBufferArr;
            this.yuvFrame = true;
            this.rotationDegree = i11;
            this.nativeFramePointer = j9;
            if (i11 % 90 == 0) {
                this.samplingMatrix = new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f};
                return;
            }
            throw new IllegalArgumentException("Rotation degree not multiple of 90: " + i11);
        }

        public int rotatedHeight() {
            return this.rotationDegree % 180 == 0 ? this.height : this.width;
        }

        public int rotatedWidth() {
            return this.rotationDegree % 180 == 0 ? this.width : this.height;
        }

        public String toString() {
            return this.width + "x" + this.height + ":" + this.yuvStrides[0] + ":" + this.yuvStrides[1] + ":" + this.yuvStrides[2];
        }

        public I420Frame(int i9, int i10, int i11, int i12, float[] fArr, long j9) {
            this.width = i9;
            this.height = i10;
            this.yuvStrides = null;
            this.yuvPlanes = null;
            this.samplingMatrix = fArr;
            this.textureId = i12;
            this.yuvFrame = false;
            this.rotationDegree = i11;
            this.nativeFramePointer = j9;
            if (i11 % 90 == 0) {
                return;
            }
            throw new IllegalArgumentException("Rotation degree not multiple of 90: " + i11);
        }
    }
}
