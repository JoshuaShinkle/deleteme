package org.webrtc;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.webrtc.VideoCapturer;

/* loaded from: classes3.dex */
public class FileVideoCapturer implements VideoCapturer {
    private static final String TAG = "FileVideoCapturer";
    private VideoCapturer.CapturerObserver capturerObserver;
    private final VideoReader videoReader;
    private final Timer timer = new Timer();
    private final TimerTask tickTask = new TimerTask() { // from class: org.webrtc.FileVideoCapturer.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            FileVideoCapturer.this.tick();
        }
    };

    public interface VideoReader {
        void close();

        int getFrameHeight();

        int getFrameWidth();

        byte[] getNextFrame();
    }

    public static class VideoReaderY4M implements VideoReader {
        private static final String TAG = "VideoReaderY4M";
        private static final String Y4M_FRAME_DELIMETER = "FRAME";
        private final int frameHeight;
        private final int frameSize;
        private final int frameWidth;
        private final RandomAccessFile mediaFileStream;
        private final long videoStart;

        public VideoReaderY4M(String str) throws IOException, NumberFormatException {
            this.mediaFileStream = new RandomAccessFile(str, "r");
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i9 = this.mediaFileStream.read();
                if (i9 == -1) {
                    throw new RuntimeException("Found end of file before end of header for file: " + str);
                }
                if (i9 == 10) {
                    this.videoStart = this.mediaFileStream.getFilePointer();
                    String strSubstring = "";
                    int i10 = 0;
                    int i11 = 0;
                    for (String str2 : sb.toString().split("[ ]")) {
                        char cCharAt = str2.charAt(0);
                        if (cCharAt == 'C') {
                            strSubstring = str2.substring(1);
                        } else if (cCharAt == 'H') {
                            i11 = Integer.parseInt(str2.substring(1));
                        } else if (cCharAt == 'W') {
                            i10 = Integer.parseInt(str2.substring(1));
                        }
                    }
                    Logging.m23185d(TAG, "Color space: " + strSubstring);
                    if (!strSubstring.equals("420") && !strSubstring.equals("420mpeg2")) {
                        throw new IllegalArgumentException("Does not support any other color space than I420 or I420mpeg2");
                    }
                    if (i10 % 2 == 1 || i11 % 2 == 1) {
                        throw new IllegalArgumentException("Does not support odd width or height");
                    }
                    this.frameWidth = i10;
                    this.frameHeight = i11;
                    int i12 = ((i10 * i11) * 3) / 2;
                    this.frameSize = i12;
                    Logging.m23185d(TAG, "frame dim: (" + i10 + ", " + i11 + ") frameSize: " + i12);
                    return;
                }
                sb.append((char) i9);
            }
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public void close() throws IOException {
            try {
                this.mediaFileStream.close();
            } catch (IOException e9) {
                Logging.m23187e(TAG, "Problem closing file", e9);
            }
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public int getFrameHeight() {
            return this.frameHeight;
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public int getFrameWidth() {
            return this.frameWidth;
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public byte[] getNextFrame() throws IOException {
            byte[] bArr = new byte[this.frameSize];
            try {
                byte[] bArr2 = new byte[6];
                if (this.mediaFileStream.read(bArr2) < 6) {
                    this.mediaFileStream.seek(this.videoStart);
                    if (this.mediaFileStream.read(bArr2) < 6) {
                        throw new RuntimeException("Error looping video");
                    }
                }
                String str = new String(bArr2);
                if (str.equals("FRAME\n")) {
                    this.mediaFileStream.readFully(bArr);
                    byte[] bArr3 = new byte[this.frameSize];
                    FileVideoCapturer.nativeI420ToNV21(bArr, this.frameWidth, this.frameHeight, bArr3);
                    return bArr3;
                }
                throw new RuntimeException("Frames should be delimited by FRAME plus newline, found delimter was: '" + str + "'");
            } catch (IOException e9) {
                throw new RuntimeException(e9);
            }
        }
    }

    public FileVideoCapturer(String str) throws IOException {
        try {
            this.videoReader = new VideoReaderY4M(str);
        } catch (IOException e9) {
            Logging.m23185d(TAG, "Could not open video file: " + str);
            throw e9;
        }
    }

    private int getFrameHeight() {
        return this.videoReader.getFrameHeight();
    }

    private int getFrameWidth() {
        return this.videoReader.getFrameWidth();
    }

    private byte[] getNextFrame() {
        return this.videoReader.getNextFrame();
    }

    public static native void nativeI420ToNV21(byte[] bArr, int i9, int i10, byte[] bArr2);

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(int i9, int i10, int i11) {
    }

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        this.videoReader.close();
    }

    @Override // org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        this.capturerObserver = capturerObserver;
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    @Override // org.webrtc.VideoCapturer
    public void resetFormat(int i9, int i10, int i11) {
    }

    @Override // org.webrtc.VideoCapturer
    public void resumeCapture() {
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int i9, int i10, int i11) {
        this.timer.schedule(this.tickTask, 0L, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT / i11);
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() {
        this.timer.cancel();
    }

    public void tick() {
        long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
        this.capturerObserver.onByteBufferFrameCaptured(getNextFrame(), getFrameWidth(), getFrameHeight(), 0, nanos);
    }
}
