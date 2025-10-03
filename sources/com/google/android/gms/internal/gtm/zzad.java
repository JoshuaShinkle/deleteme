package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzad extends zzan {
    private static boolean zzvo;
    private AdvertisingIdClient.Info zzvp;
    private final zzcv zzvq;
    private String zzvr;
    private boolean zzvs;
    private final Object zzvt;

    public zzad(zzap zzapVar) {
        super(zzapVar);
        this.zzvs = false;
        this.zzvt = new Object();
        this.zzvq = new zzcv(zzapVar.zzcn());
    }

    private final boolean zza(AdvertisingIdClient.Info info, AdvertisingIdClient.Info info2) {
        String id = null;
        String id2 = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id2)) {
            return true;
        }
        String strZzeh = zzcw().zzeh();
        synchronized (this.zzvt) {
            if (!this.zzvs) {
                this.zzvr = zzcg();
                this.zzvs = true;
            } else if (TextUtils.isEmpty(this.zzvr)) {
                if (info != null) {
                    id = info.getId();
                }
                if (id == null) {
                    String strValueOf = String.valueOf(id2);
                    String strValueOf2 = String.valueOf(strZzeh);
                    return zzp(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
                }
                String strValueOf3 = String.valueOf(strZzeh);
                this.zzvr = zzo(strValueOf3.length() != 0 ? id.concat(strValueOf3) : new String(id));
            }
            String strValueOf4 = String.valueOf(id2);
            String strValueOf5 = String.valueOf(strZzeh);
            String strZzo = zzo(strValueOf5.length() != 0 ? strValueOf4.concat(strValueOf5) : new String(strValueOf4));
            if (TextUtils.isEmpty(strZzo)) {
                return false;
            }
            if (strZzo.equals(this.zzvr)) {
                return true;
            }
            if (!TextUtils.isEmpty(this.zzvr)) {
                zzq("Resetting the client id because Advertising Id changed.");
                strZzeh = zzcw().zzei();
                zza("New client Id", strZzeh);
            }
            String strValueOf6 = String.valueOf(id2);
            String strValueOf7 = String.valueOf(strZzeh);
            return zzp(strValueOf7.length() != 0 ? strValueOf6.concat(strValueOf7) : new String(strValueOf6));
        }
    }

    private final synchronized AdvertisingIdClient.Info zzce() {
        if (this.zzvq.zzj(1000L)) {
            this.zzvq.start();
            AdvertisingIdClient.Info infoZzcf = zzcf();
            if (zza(this.zzvp, infoZzcf)) {
                this.zzvp = infoZzcf;
            } else {
                zzu("Failed to reset client id on adid change. Not using adid");
                this.zzvp = new AdvertisingIdClient.Info("", false);
            }
        }
        return this.zzvp;
    }

    private final AdvertisingIdClient.Info zzcf() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException unused) {
            zzt("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        } catch (Exception e9) {
            if (!zzvo) {
                zzvo = true;
                zzd("Error getting advertiser id", e9);
            }
            return null;
        }
    }

    private final String zzcg() throws IOException {
        String str = null;
        try {
            FileInputStream fileInputStreamOpenFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int i9 = fileInputStreamOpenFileInput.read(bArr, 0, 128);
            if (fileInputStreamOpenFileInput.available() > 0) {
                zzt("Hash file seems corrupted, deleting it.");
                fileInputStreamOpenFileInput.close();
                getContext().deleteFile("gaClientIdData");
            } else if (i9 <= 0) {
                zzq("Hash file is empty.");
                fileInputStreamOpenFileInput.close();
            } else {
                String str2 = new String(bArr, 0, i9);
                try {
                    fileInputStreamOpenFileInput.close();
                } catch (FileNotFoundException unused) {
                } catch (IOException e9) {
                    e = e9;
                    str = str2;
                    zzd("Error reading Hash file, deleting it", e);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
                str = str2;
            }
        } catch (FileNotFoundException unused2) {
        } catch (IOException e10) {
            e = e10;
        }
        return str;
    }

    private static String zzo(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigestZzai = zzcz.zzai("MD5");
        if (messageDigestZzai == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestZzai.digest(str.getBytes())));
    }

    private final boolean zzp(String str) throws NoSuchAlgorithmException, IOException {
        try {
            String strZzo = zzo(str);
            zzq("Storing hashed adid.");
            FileOutputStream fileOutputStreamOpenFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            fileOutputStreamOpenFileOutput.write(strZzo.getBytes());
            fileOutputStreamOpenFileOutput.close();
            this.zzvr = strZzo;
            return true;
        } catch (IOException e9) {
            zze("Error creating hash file", e9);
            return false;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
    }

    public final boolean zzbw() {
        zzdb();
        AdvertisingIdClient.Info infoZzce = zzce();
        return (infoZzce == null || infoZzce.isLimitAdTrackingEnabled()) ? false : true;
    }

    public final String zzcd() {
        zzdb();
        AdvertisingIdClient.Info infoZzce = zzce();
        String id = infoZzce != null ? infoZzce.getId() : null;
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        return id;
    }
}
