package p177q6;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/* renamed from: q6.g */
/* loaded from: classes.dex */
public final class C6169g {
    /* renamed from: b */
    public static final int m23630b(LogRecord logRecord) {
        if (logRecord.getLevel().intValue() > Level.INFO.intValue()) {
            return 5;
        }
        return logRecord.getLevel().intValue() == Level.INFO.intValue() ? 4 : 3;
    }
}
