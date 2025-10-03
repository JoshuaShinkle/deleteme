package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
import p197t.C6273a;

/* loaded from: classes2.dex */
final class zzn {
    private static void zzd(Context context, String str, zzo zzoVar) throws IOException {
        try {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Writing key to properties file");
            }
            File fileZzj = zzj(context, str);
            fileZzj.createNewFile();
            Properties properties = new Properties();
            properties.setProperty("pub", zzoVar.zzq());
            properties.setProperty("pri", zzoVar.zzr());
            properties.setProperty("cre", String.valueOf(zzoVar.zzcc));
            FileOutputStream fileOutputStream = new FileOutputStream(fileZzj);
            try {
                properties.store(fileOutputStream, (String) null);
                zzd((Throwable) null, fileOutputStream);
            } finally {
            }
        } catch (IOException e9) {
            String strValueOf = String.valueOf(e9);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 21);
            sb.append("Failed to write key: ");
            sb.append(strValueOf);
            Log.w("InstanceID", sb.toString());
        }
    }

    public static void zzg(Context context, String str) {
        File fileZzj = zzj(context, str);
        if (fileZzj.exists()) {
            fileZzj.delete();
        }
    }

    private final zzo zzh(Context context, String str) throws zzp, IOException {
        zzo zzoVarZzi;
        try {
            zzoVarZzi = zzi(context, str);
        } catch (zzp e9) {
            e = e9;
        }
        if (zzoVarZzi != null) {
            zze(context, str, zzoVarZzi);
            return zzoVarZzi;
        }
        e = null;
        try {
            zzo zzoVarZzd = zzd(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
            if (zzoVarZzd != null) {
                zzd(context, str, zzoVarZzd);
                return zzoVarZzd;
            }
        } catch (zzp e10) {
            e = e10;
        }
        if (e == null) {
            return null;
        }
        throw e;
    }

    public static void zzi(Context context) {
        for (File file : zzj(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private static File zzj(Context context) {
        File fileM24028g = C6273a.m24028g(context);
        if (fileM24028g != null && fileM24028g.isDirectory()) {
            return fileM24028g;
        }
        Log.w("InstanceID", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    public final zzo zze(Context context, String str) throws zzp, IOException {
        zzo zzoVarZzh = zzh(context, str);
        return zzoVarZzh != null ? zzoVarZzh : zzf(context, str);
    }

    public final zzo zzf(Context context, String str) throws IOException {
        zzo zzoVar = new zzo(zzd.zzl(), System.currentTimeMillis());
        try {
            zzo zzoVarZzh = zzh(context, str);
            if (zzoVarZzh != null) {
                if (Log.isLoggable("InstanceID", 3)) {
                    Log.d("InstanceID", "Loaded key after generating new one, using loaded one");
                }
                return zzoVarZzh;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Generated new key");
        }
        zzd(context, str, zzoVar);
        zze(context, str, zzoVar);
        return zzoVar;
    }

    private final void zze(Context context, String str, zzo zzoVar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzoVar.equals(zzd(sharedPreferences, str))) {
                return;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Writing key to shared preferences");
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(zzak.zzh(str, "|P|"), zzoVar.zzq());
        editorEdit.putString(zzak.zzh(str, "|K|"), zzoVar.zzr());
        editorEdit.putString(zzak.zzh(str, "cre"), String.valueOf(zzoVar.zzcc));
        editorEdit.commit();
    }

    private static KeyPair zzg(String str, String str2) throws NoSuchAlgorithmException, zzp {
        try {
            byte[] bArrDecode = Base64.decode(str, 8);
            byte[] bArrDecode2 = Base64.decode(str2, 8);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                return new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(bArrDecode)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArrDecode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e9) {
                String strValueOf = String.valueOf(e9);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 19);
                sb.append("Invalid key stored ");
                sb.append(strValueOf);
                Log.w("InstanceID", sb.toString());
                throw new zzp(e9);
            }
        } catch (IllegalArgumentException e10) {
            throw new zzp(e10);
        }
    }

    private final zzo zzi(Context context, String str) throws zzp {
        File fileZzj = zzj(context, str);
        if (!fileZzj.exists()) {
            return null;
        }
        try {
            return zzd(fileZzj);
        } catch (IOException e9) {
            if (Log.isLoggable("InstanceID", 3)) {
                String strValueOf = String.valueOf(e9);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 40);
                sb.append("Failed to read key from file, retrying: ");
                sb.append(strValueOf);
                Log.d("InstanceID", sb.toString());
            }
            try {
                return zzd(fileZzj);
            } catch (IOException e10) {
                String strValueOf2 = String.valueOf(e10);
                StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 45);
                sb2.append("IID file exists, but failed to read from it: ");
                sb2.append(strValueOf2);
                Log.w("InstanceID", sb2.toString());
                throw new zzp(e10);
            }
        }
    }

    private static File zzj(Context context, String str) {
        String string;
        if (TextUtils.isEmpty(str)) {
            string = "com.google.InstanceId.properties";
        } else {
            try {
                String strEncodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                StringBuilder sb = new StringBuilder(String.valueOf(strEncodeToString).length() + 33);
                sb.append("com.google.InstanceId_");
                sb.append(strEncodeToString);
                sb.append(".properties");
                string = sb.toString();
            } catch (UnsupportedEncodingException e9) {
                throw new AssertionError(e9);
            }
        }
        return new File(zzj(context), string);
    }

    private static zzo zzd(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String property = properties.getProperty("pub");
            String property2 = properties.getProperty("pri");
            if (property != null && property2 != null) {
                try {
                    zzo zzoVar = new zzo(zzg(property, property2), Long.parseLong(properties.getProperty("cre")));
                    zzd((Throwable) null, fileInputStream);
                    return zzoVar;
                } catch (NumberFormatException e9) {
                    throw new zzp(e9);
                }
            }
            zzd((Throwable) null, fileInputStream);
            return null;
        } finally {
        }
    }

    private static long zze(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzak.zzh(str, "cre"), null);
        if (string == null) {
            return 0L;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    private static zzo zzd(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzak.zzh(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzak.zzh(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzo(zzg(string, string2), zze(sharedPreferences, str));
    }

    private static /* synthetic */ void zzd(Throwable th, FileOutputStream fileOutputStream) throws IOException {
        if (th == null) {
            fileOutputStream.close();
            return;
        }
        try {
            fileOutputStream.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.gcm.zzq.zzd(th, th2);
        }
    }

    private static /* synthetic */ void zzd(Throwable th, FileInputStream fileInputStream) throws IOException {
        if (th == null) {
            fileInputStream.close();
            return;
        }
        try {
            fileInputStream.close();
        } catch (Throwable th2) {
            com.google.android.gms.internal.gcm.zzq.zzd(th, th2);
        }
    }
}
