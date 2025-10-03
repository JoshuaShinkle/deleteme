package org.webrtc;

import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class DataChannel {
    private final long nativeDataChannel;
    private long nativeObserver;

    public static class Buffer {
        public final boolean binary;
        public final ByteBuffer data;

        public Buffer(ByteBuffer byteBuffer, boolean z8) {
            this.data = byteBuffer;
            this.binary = z8;
        }
    }

    public interface Observer {
        void onBufferedAmountChange(long j9);

        void onMessage(Buffer buffer);

        void onStateChange();
    }

    public enum State {
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    public DataChannel(long j9) {
        this.nativeDataChannel = j9;
    }

    private native long registerObserverNative(Observer observer);

    private native boolean sendNative(byte[] bArr, boolean z8);

    private native void unregisterObserverNative(long j9);

    public native long bufferedAmount();

    public native void close();

    public native void dispose();

    /* renamed from: id */
    public native int m23184id();

    public native String label();

    public void registerObserver(Observer observer) {
        long j9 = this.nativeObserver;
        if (j9 != 0) {
            unregisterObserverNative(j9);
        }
        this.nativeObserver = registerObserverNative(observer);
    }

    public boolean send(Buffer buffer) {
        byte[] bArr = new byte[buffer.data.remaining()];
        buffer.data.get(bArr);
        return sendNative(bArr, buffer.binary);
    }

    public native State state();

    public void unregisterObserver() {
        unregisterObserverNative(this.nativeObserver);
    }

    public static class Init {

        /* renamed from: id */
        public int f20237id;
        public int maxRetransmitTimeMs;
        public int maxRetransmits;
        public boolean negotiated;
        public boolean ordered;
        public String protocol;

        public Init() {
            this.ordered = true;
            this.maxRetransmitTimeMs = -1;
            this.maxRetransmits = -1;
            this.protocol = "";
            this.negotiated = false;
            this.f20237id = -1;
        }

        private Init(boolean z8, int i9, int i10, String str, boolean z9, int i11) {
            this.ordered = z8;
            this.maxRetransmitTimeMs = i9;
            this.maxRetransmits = i10;
            this.protocol = str;
            this.negotiated = z9;
            this.f20237id = i11;
        }
    }
}
