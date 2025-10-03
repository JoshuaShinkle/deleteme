package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@KeepForSdk
/* loaded from: classes2.dex */
public class ProcessUtils {
    private static String zza;
    private static int zzb;

    private ProcessUtils() {
    }

    @KeepForSdk
    public static String getMyProcessName() throws Throwable {
        BufferedReader bufferedReader;
        if (zza == null) {
            int iMyPid = zzb;
            if (iMyPid == 0) {
                iMyPid = Process.myPid();
                zzb = iMyPid;
            }
            String strTrim = null;
            strTrim = null;
            strTrim = null;
            BufferedReader bufferedReader2 = null;
            if (iMyPid > 0) {
                try {
                    String str = "/proc/" + iMyPid + "/cmdline";
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        bufferedReader = new BufferedReader(new FileReader(str));
                        try {
                            String line = bufferedReader.readLine();
                            Preconditions.checkNotNull(line);
                            strTrim = line.trim();
                        } catch (IOException unused) {
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader2 = bufferedReader;
                            IOUtils.closeQuietly(bufferedReader2);
                            throw th;
                        }
                    } finally {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    }
                } catch (IOException unused2) {
                    bufferedReader = null;
                } catch (Throwable th2) {
                    th = th2;
                }
                IOUtils.closeQuietly(bufferedReader);
            }
            zza = strTrim;
        }
        return zza;
    }
}
