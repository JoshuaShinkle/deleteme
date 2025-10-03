package com.google.android.gms.internal.gcm;

import java.io.PrintStream;

/* loaded from: classes2.dex */
public final class zzq {
    private static final zzr zzdq;
    private static final int zzdr;

    public static final class zzd extends zzr {
        @Override // com.google.android.gms.internal.gcm.zzr
        public final void zzd(Throwable th, Throwable th2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0015 A[Catch: all -> 0x002a, TryCatch #0 {all -> 0x002a, blocks: (B:5:0x0007, B:7:0x000f, B:8:0x0015, B:10:0x001e, B:11:0x0024), top: B:25:0x0007 }] */
    static {
        Integer numZzac;
        zzr zzdVar;
        try {
            numZzac = zzac();
            if (numZzac != null) {
                try {
                    zzdVar = numZzac.intValue() >= 19 ? new zzv() : Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ true ? new zzu() : new zzd();
                } catch (Throwable th) {
                    th = th;
                    PrintStream printStream = System.err;
                    String name = zzd.class.getName();
                    StringBuilder sb = new StringBuilder(name.length() + 133);
                    sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
                    sb.append(name);
                    sb.append("will be used. The error is: ");
                    printStream.println(sb.toString());
                    th.printStackTrace(System.err);
                    zzdVar = new zzd();
                    zzdq = zzdVar;
                    zzdr = numZzac != null ? numZzac.intValue() : 1;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            numZzac = null;
        }
        zzdq = zzdVar;
        zzdr = numZzac != null ? numZzac.intValue() : 1;
    }

    private static Integer zzac() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e9) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e9.printStackTrace(System.err);
            return null;
        }
    }

    public static void zzd(Throwable th, Throwable th2) {
        zzdq.zzd(th, th2);
    }
}
