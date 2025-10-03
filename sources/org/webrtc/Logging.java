package org.webrtc;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
public class Logging {
    private static final Logger fallbackLogger = Logger.getLogger("org.webrtc.Logging");
    private static LogBack logger;
    private static volatile boolean loggingEnabled;
    private static volatile boolean nativeLibLoaded;
    private static volatile boolean tracingEnabled;

    /* renamed from: org.webrtc.Logging$1 */
    public static /* synthetic */ class C58301 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$Logging$Severity;

        static {
            int[] iArr = new int[Severity.values().length];
            $SwitchMap$org$webrtc$Logging$Severity = iArr;
            try {
                iArr[Severity.LS_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$Logging$Severity[Severity.LS_WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$Logging$Severity[Severity.LS_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public interface LogBack {
        void log(String str, String str2);
    }

    public enum Severity {
        LS_SENSITIVE,
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR,
        LS_NONE
    }

    public enum TraceLevel {
        TRACE_NONE(0),
        TRACE_STATEINFO(1),
        TRACE_WARNING(2),
        TRACE_ERROR(4),
        TRACE_CRITICAL(8),
        TRACE_APICALL(16),
        TRACE_DEFAULT(255),
        TRACE_MODULECALL(32),
        TRACE_MEMORY(256),
        TRACE_TIMER(512),
        TRACE_STREAM(UserMetadata.MAX_ATTRIBUTE_SIZE),
        TRACE_DEBUG(2048),
        TRACE_INFO(4096),
        TRACE_TERSEINFO(UserMetadata.MAX_INTERNAL_KEY_SIZE),
        TRACE_ALL(65535);

        public final int level;

        TraceLevel(int i9) {
            this.level = i9;
        }
    }

    static {
        try {
            System.loadLibrary("jingle_peerconnection_so");
            nativeLibLoaded = true;
        } catch (UnsatisfiedLinkError e9) {
            Logger logger2 = fallbackLogger;
            logger2.setLevel(Level.ALL);
            logger2.log(Level.WARNING, "Failed to load jingle_peerconnection_so: ", (Throwable) e9);
        }
    }

    /* renamed from: d */
    public static void m23185d(String str, String str2) {
        log(Severity.LS_INFO, str, str2);
    }

    public static void disableWebRTCLogToFile() {
        logger = null;
    }

    /* renamed from: e */
    public static void m23186e(String str, String str2) {
        log(Severity.LS_ERROR, str, str2);
    }

    public static void enableLogThreads() {
        if (nativeLibLoaded) {
            nativeEnableLogThreads();
        } else {
            fallbackLogger.log(Level.WARNING, "Cannot enable log thread because native lib not loaded.");
        }
    }

    public static void enableLogTimeStamps() {
        if (nativeLibLoaded) {
            nativeEnableLogTimeStamps();
        } else {
            fallbackLogger.log(Level.WARNING, "Cannot enable log timestamps because native lib not loaded.");
        }
    }

    public static synchronized void enableLogToDebugOutput(Severity severity) {
        if (!nativeLibLoaded) {
            fallbackLogger.log(Level.WARNING, "Cannot enable logging because native lib not loaded.");
        } else {
            nativeEnableLogToDebugOutput(severity.ordinal());
            loggingEnabled = true;
        }
    }

    public static synchronized void enableTracing(String str, EnumSet<TraceLevel> enumSet) {
        if (!nativeLibLoaded) {
            fallbackLogger.log(Level.WARNING, "Cannot enable tracing because native lib not loaded.");
            return;
        }
        if (tracingEnabled) {
            return;
        }
        Iterator<TraceLevel> it = enumSet.iterator();
        int i9 = 0;
        while (it.hasNext()) {
            i9 |= it.next().level;
        }
        nativeEnableTracing(str, i9);
        tracingEnabled = true;
    }

    public static void enableWebRTCLogToFile(LogBack logBack) {
        logger = logBack;
    }

    private static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void log(Severity severity, String str, String str2) {
        if (loggingEnabled) {
            nativeLog(severity.ordinal(), str, str2);
            return;
        }
        int i9 = C58301.$SwitchMap$org$webrtc$Logging$Severity[severity.ordinal()];
        Level level = i9 != 1 ? i9 != 2 ? i9 != 3 ? Level.FINE : Level.INFO : Level.WARNING : Level.SEVERE;
        fallbackLogger.log(level, str + ": " + str2);
        LogBack logBack = logger;
        if (logBack != null) {
            logBack.log(level.getName(), str2);
        }
    }

    private static native void nativeEnableLogThreads();

    private static native void nativeEnableLogTimeStamps();

    private static native void nativeEnableLogToDebugOutput(int i9);

    private static native void nativeEnableTracing(String str, int i9);

    private static native void nativeLog(int i9, String str, String str2);

    /* renamed from: v */
    public static void m23188v(String str, String str2) {
        log(Severity.LS_VERBOSE, str, str2);
    }

    /* renamed from: w */
    public static void m23189w(String str, String str2) {
        log(Severity.LS_WARNING, str, str2);
    }

    /* renamed from: e */
    public static void m23187e(String str, String str2, Throwable th) {
        Severity severity = Severity.LS_ERROR;
        log(severity, str, str2);
        log(severity, str, th.toString());
        log(severity, str, getStackTraceString(th));
    }

    /* renamed from: w */
    public static void m23190w(String str, String str2, Throwable th) {
        Severity severity = Severity.LS_WARNING;
        log(severity, str, str2);
        log(severity, str, th.toString());
        log(severity, str, getStackTraceString(th));
    }
}
