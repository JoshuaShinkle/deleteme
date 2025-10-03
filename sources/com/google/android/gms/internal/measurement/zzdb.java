package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class zzdb {
    public static zzdx<zzcy> zza(Context context) {
        String str = Build.TYPE;
        String str2 = Build.TAGS;
        boolean z8 = false;
        if ((str.equals("eng") || str.equals("userdebug")) && (str2.contains("dev-keys") || str2.contains("test-keys"))) {
            z8 = true;
        }
        if (!z8) {
            return zzdx.zzc();
        }
        if (zzcm.zza() && !context.isDeviceProtectedStorage()) {
            context = context.createDeviceProtectedStorageContext();
        }
        zzdx<File> zzdxVarZzb = zzb(context);
        return zzdxVarZzb.zza() ? zzdx.zza(zza(zzdxVarZzb.zzb())) : zzdx.zzc();
    }

    private static zzdx<File> zzb(Context context) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            StrictMode.allowThreadDiskWrites();
            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
            return file.exists() ? zzdx.zza(file) : zzdx.zzc();
        } catch (RuntimeException e9) {
            Log.e("HermeticFileOverrides", "no data dir", e9);
            return zzdx.zzc();
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    private static zzcy zza(File file) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            try {
                HashMap map = new HashMap();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        String[] strArrSplit = line.split(StringUtils.SPACE, 3);
                        if (strArrSplit.length != 3) {
                            Log.e("HermeticFileOverrides", line.length() != 0 ? "Invalid: ".concat(line) : new String("Invalid: "));
                        } else {
                            String str = strArrSplit[0];
                            String strDecode = Uri.decode(strArrSplit[1]);
                            String strDecode2 = Uri.decode(strArrSplit[2]);
                            if (!map.containsKey(str)) {
                                map.put(str, new HashMap());
                            }
                            ((Map) map.get(str)).put(strDecode, strDecode2);
                        }
                    } else {
                        String strValueOf = String.valueOf(file);
                        StringBuilder sb = new StringBuilder(strValueOf.length() + 7);
                        sb.append("Parsed ");
                        sb.append(strValueOf);
                        Log.i("HermeticFileOverrides", sb.toString());
                        zzcy zzcyVar = new zzcy(map);
                        bufferedReader.close();
                        return zzcyVar;
                    }
                }
            } finally {
            }
        } catch (IOException e9) {
            throw new RuntimeException(e9);
        }
    }
}
