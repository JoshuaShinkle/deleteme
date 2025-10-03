package com.google.android.gms.dynamite;

import android.os.Looper;
import android.util.Log;

/* loaded from: classes2.dex */
public final class zzb {
    private static volatile ClassLoader zza;
    private static volatile Thread zzb;

    public static synchronized ClassLoader zza() {
        if (zza == null) {
            zza = zzb();
        }
        return zza;
    }

    private static synchronized ClassLoader zzb() {
        ClassLoader contextClassLoader = null;
        if (zzb == null) {
            zzb = zzc();
            if (zzb == null) {
                return null;
            }
        }
        synchronized (zzb) {
            try {
                contextClassLoader = zzb.getContextClassLoader();
            } catch (SecurityException e9) {
                Log.w("DynamiteLoaderV2CL", "Failed to get thread context classloader " + e9.getMessage());
            }
        }
        return contextClassLoader;
    }

    private static synchronized Thread zzc() {
        SecurityException e9;
        Thread zzaVar;
        Thread thread;
        ThreadGroup threadGroup;
        ThreadGroup threadGroup2 = Looper.getMainLooper().getThread().getThreadGroup();
        if (threadGroup2 == null) {
            return null;
        }
        synchronized (Void.class) {
            try {
                int iActiveGroupCount = threadGroup2.activeGroupCount();
                ThreadGroup[] threadGroupArr = new ThreadGroup[iActiveGroupCount];
                threadGroup2.enumerate(threadGroupArr);
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    if (i10 >= iActiveGroupCount) {
                        threadGroup = null;
                        break;
                    }
                    threadGroup = threadGroupArr[i10];
                    if ("dynamiteLoader".equals(threadGroup.getName())) {
                        break;
                    }
                    i10++;
                }
                if (threadGroup == null) {
                    threadGroup = new ThreadGroup(threadGroup2, "dynamiteLoader");
                }
                int iActiveCount = threadGroup.activeCount();
                Thread[] threadArr = new Thread[iActiveCount];
                threadGroup.enumerate(threadArr);
                while (true) {
                    if (i9 >= iActiveCount) {
                        thread = null;
                        break;
                    }
                    thread = threadArr[i9];
                    if ("GmsDynamite".equals(thread.getName())) {
                        break;
                    }
                    i9++;
                }
            } catch (SecurityException e10) {
                e9 = e10;
                zzaVar = null;
            }
            if (thread == null) {
                try {
                    zzaVar = new zza(threadGroup, "GmsDynamite");
                    try {
                        zzaVar.setContextClassLoader(null);
                        zzaVar.start();
                    } catch (SecurityException e11) {
                        e9 = e11;
                        Log.w("DynamiteLoaderV2CL", "Failed to enumerate thread/threadgroup " + e9.getMessage());
                        thread = zzaVar;
                        return thread;
                    }
                } catch (SecurityException e12) {
                    e9 = e12;
                    zzaVar = thread;
                }
                thread = zzaVar;
            }
        }
        return thread;
    }
}
