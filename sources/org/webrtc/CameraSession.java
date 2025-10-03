package org.webrtc;

/* loaded from: classes3.dex */
interface CameraSession {

    public interface CreateSessionCallback {
        void onDone(CameraSession cameraSession);

        void onFailure(FailureType failureType, String str);
    }

    public interface Events {
        void onByteBufferFrameCaptured(CameraSession cameraSession, byte[] bArr, int i9, int i10, int i11, long j9);

        void onCameraClosed(CameraSession cameraSession);

        void onCameraDisconnected(CameraSession cameraSession);

        void onCameraError(CameraSession cameraSession, String str);

        void onCameraOpening();

        void onTextureFrameCaptured(CameraSession cameraSession, int i9, int i10, int i11, float[] fArr, int i12, long j9);
    }

    public enum FailureType {
        ERROR,
        DISCONNECTED
    }

    void stop();
}
