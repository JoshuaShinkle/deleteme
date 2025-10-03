package org.webrtc;

import android.content.Context;

/* loaded from: classes3.dex */
public interface VideoCapturer {

    public static class AndroidVideoTrackSourceObserver implements CapturerObserver {
        private final long nativeSource;

        public AndroidVideoTrackSourceObserver(long j9) {
            this.nativeSource = j9;
        }

        private native void nativeCapturerStarted(long j9, boolean z8);

        private native void nativeCapturerStopped(long j9);

        private native void nativeOnByteBufferFrameCaptured(long j9, byte[] bArr, int i9, int i10, int i11, int i12, long j10);

        private native void nativeOnTextureFrameCaptured(long j9, int i9, int i10, int i11, float[] fArr, int i12, long j10);

        @Override // org.webrtc.VideoCapturer.CapturerObserver
        public void onByteBufferFrameCaptured(byte[] bArr, int i9, int i10, int i11, long j9) {
            nativeOnByteBufferFrameCaptured(this.nativeSource, bArr, bArr.length, i9, i10, i11, j9);
        }

        @Override // org.webrtc.VideoCapturer.CapturerObserver
        public void onCapturerStarted(boolean z8) {
            nativeCapturerStarted(this.nativeSource, z8);
        }

        @Override // org.webrtc.VideoCapturer.CapturerObserver
        public void onCapturerStopped() {
            nativeCapturerStopped(this.nativeSource);
        }

        @Override // org.webrtc.VideoCapturer.CapturerObserver
        public void onTextureFrameCaptured(int i9, int i10, int i11, float[] fArr, int i12, long j9) {
            nativeOnTextureFrameCaptured(this.nativeSource, i9, i10, i11, fArr, i12, j9);
        }
    }

    public interface CapturerObserver {
        void onByteBufferFrameCaptured(byte[] bArr, int i9, int i10, int i11, long j9);

        void onCapturerStarted(boolean z8);

        void onCapturerStopped();

        void onTextureFrameCaptured(int i9, int i10, int i11, float[] fArr, int i12, long j9);
    }

    void changeCaptureFormat(int i9, int i10, int i11);

    void dispose();

    void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver);

    boolean isScreencast();

    void resetFormat(int i9, int i10, int i11);

    void resumeCapture();

    void startCapture(int i9, int i10, int i11);

    void stopCapture();
}
