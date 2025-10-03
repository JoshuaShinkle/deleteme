package okhttp3.internal.http2;

import java.io.IOException;
import p007a6.C0042f;

/* loaded from: classes.dex */
public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamResetException(ErrorCode errorCode) {
        super("stream was reset: " + errorCode);
        C0042f.m158e(errorCode, "errorCode");
        this.errorCode = errorCode;
    }
}
