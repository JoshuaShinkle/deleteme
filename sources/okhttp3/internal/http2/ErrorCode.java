package okhttp3.internal.http2;

import p007a6.C0040d;

/* loaded from: classes.dex */
public enum ErrorCode {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    SETTINGS_TIMEOUT(4),
    STREAM_CLOSED(5),
    FRAME_SIZE_ERROR(6),
    REFUSED_STREAM(7),
    CANCEL(8),
    COMPRESSION_ERROR(9),
    CONNECT_ERROR(10),
    ENHANCE_YOUR_CALM(11),
    INADEQUATE_SECURITY(12),
    HTTP_1_1_REQUIRED(13);


    /* renamed from: b */
    public static final C5503a f18750b = new C5503a(null);
    private final int httpCode;

    /* renamed from: okhttp3.internal.http2.ErrorCode$a */
    public static final class C5503a {
        public C5503a() {
        }

        public /* synthetic */ C5503a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final ErrorCode m21395a(int i9) {
            for (ErrorCode errorCode : ErrorCode.values()) {
                if (errorCode.m21394b() == i9) {
                    return errorCode;
                }
            }
            return null;
        }
    }

    ErrorCode(int i9) {
        this.httpCode = i9;
    }

    /* renamed from: b */
    public final int m21394b() {
        return this.httpCode;
    }
}
