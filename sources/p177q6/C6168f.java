package p177q6;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import p007a6.C0042f;

/* renamed from: q6.f */
/* loaded from: classes.dex */
public final class C6168f extends Handler {

    /* renamed from: a */
    public static final C6168f f20805a = new C6168f();

    @Override // java.util.logging.Handler
    public void close() {
    }

    @Override // java.util.logging.Handler
    public void flush() {
    }

    @Override // java.util.logging.Handler
    public void publish(LogRecord logRecord) {
        C0042f.m158e(logRecord, "record");
        C6167e c6167e = C6167e.f20802a;
        String loggerName = logRecord.getLoggerName();
        C0042f.m157d(loggerName, "record.loggerName");
        int iM23630b = C6169g.m23630b(logRecord);
        String message = logRecord.getMessage();
        C0042f.m157d(message, "record.message");
        c6167e.m23625a(loggerName, iM23630b, message, logRecord.getThrown());
    }
}
