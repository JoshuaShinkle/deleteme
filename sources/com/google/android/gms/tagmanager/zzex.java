package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzop;
import com.google.android.gms.internal.gtm.zzor;
import com.google.android.gms.internal.gtm.zzov;
import com.google.android.gms.internal.gtm.zzoz;
import com.google.android.gms.internal.gtm.zzuv;
import com.google.android.gms.internal.gtm.zzuw;
import com.google.android.gms.tagmanager.zzeh;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;

/* loaded from: classes2.dex */
final class zzex implements zzah {
    private final String zzaec;
    private zzdh<zzop> zzajf;
    private final ExecutorService zzajm = com.google.android.gms.internal.gtm.zzdf.zzgp().zzr(com.google.android.gms.internal.gtm.zzdi.zzadg);
    private final Context zzrm;

    public zzex(Context context, String str) {
        this.zzrm = context;
        this.zzaec = str;
    }

    private static zzov zzb(byte[] bArr) {
        try {
            zzov zzovVarZza = zzor.zza((com.google.android.gms.internal.gtm.zzi) zzuw.zza(new com.google.android.gms.internal.gtm.zzi(), bArr));
            if (zzovVarZza != null) {
                zzdi.zzab("The container was successfully loaded from the resource (using binary file)");
            }
            return zzovVarZza;
        } catch (zzoz unused) {
            zzdi.zzac("The resource file is invalid. The container from the binary file is invalid");
            return null;
        } catch (zzuv unused2) {
            zzdi.zzav("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        }
    }

    @VisibleForTesting
    private final File zzje() {
        String strValueOf = String.valueOf(this.zzaec);
        return new File(this.zzrm.getDir("google_tagmanager", 0), strValueOf.length() != 0 ? "resource_".concat(strValueOf) : new String("resource_"));
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        this.zzajm.shutdown();
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zza(zzdh<zzop> zzdhVar) {
        this.zzajf = zzdhVar;
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zzhk() {
        this.zzajm.execute(new zzey(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzjd() {
        FileInputStream fileInputStream;
        zzdh<zzop> zzdhVar = this.zzajf;
        if (zzdhVar == null) {
            throw new IllegalStateException("Callback must be set before execute");
        }
        zzdhVar.zzhj();
        zzdi.zzab("Attempting to load resource from disk");
        if (zzeh.zziy().zziz() != zzeh.zza.CONTAINER) {
            zzeh.zza zzaVarZziz = zzeh.zziy().zziz();
            fileInputStream = zzaVarZziz;
            if (zzaVarZziz == zzeh.zza.CONTAINER_DEBUG) {
                boolean zEquals = this.zzaec.equals(zzeh.zziy().getContainerId());
                fileInputStream = zEquals;
                if (zEquals) {
                    this.zzajf.zzs(zzcz.zzaht);
                    return;
                }
            }
        }
        try {
            try {
                fileInputStream = new FileInputStream(zzje());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    zzor.zza(fileInputStream, byteArrayOutputStream);
                    zzop zzopVar = (zzop) zzuw.zza(new zzop(), byteArrayOutputStream.toByteArray());
                    if (zzopVar.zzqk == null && zzopVar.zzauy == null) {
                        throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
                    }
                    this.zzajf.zze(zzopVar);
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                        zzdi.zzac("Error closing stream for reading resource from disk");
                    }
                } catch (IOException unused2) {
                    this.zzajf.zzs(zzcz.zzahu);
                    zzdi.zzac("Failed to read the resource from disk");
                } catch (IllegalArgumentException unused3) {
                    this.zzajf.zzs(zzcz.zzahu);
                    zzdi.zzac("Failed to read the resource from disk. The resource is inconsistent");
                    try {
                        fileInputStream.close();
                    } catch (IOException unused4) {
                        zzdi.zzac("Error closing stream for reading resource from disk");
                    }
                }
                zzdi.zzab("The Disk resource was successfully read.");
            } catch (FileNotFoundException unused5) {
                zzdi.zzax("Failed to find the resource in the disk");
                this.zzajf.zzs(zzcz.zzaht);
            }
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException unused6) {
                zzdi.zzac("Error closing stream for reading resource from disk");
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final zzov zzt(int i9) throws Resources.NotFoundException {
        try {
            InputStream inputStreamOpenRawResource = this.zzrm.getResources().openRawResource(i9);
            String resourceName = this.zzrm.getResources().getResourceName(i9);
            StringBuilder sb = new StringBuilder(String.valueOf(resourceName).length() + 66);
            sb.append("Attempting to load a container from the resource ID ");
            sb.append(i9);
            sb.append(" (");
            sb.append(resourceName);
            sb.append(")");
            zzdi.zzab(sb.toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzor.zza(inputStreamOpenRawResource, byteArrayOutputStream);
                zzov zzovVarZza = zza(byteArrayOutputStream);
                if (zzovVarZza == null) {
                    return zzb(byteArrayOutputStream.toByteArray());
                }
                zzdi.zzab("The container was successfully loaded from the resource (using JSON file format)");
                return zzovVarZza;
            } catch (IOException unused) {
                String resourceName2 = this.zzrm.getResources().getResourceName(i9);
                StringBuilder sb2 = new StringBuilder(String.valueOf(resourceName2).length() + 67);
                sb2.append("Error reading the default container with resource ID ");
                sb2.append(i9);
                sb2.append(" (");
                sb2.append(resourceName2);
                sb2.append(")");
                zzdi.zzac(sb2.toString());
                return null;
            }
        } catch (Resources.NotFoundException unused2) {
            StringBuilder sb3 = new StringBuilder(98);
            sb3.append("Failed to load the container. No default container resource found with the resource ID ");
            sb3.append(i9);
            zzdi.zzac(sb3.toString());
            return null;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzah
    public final void zza(zzop zzopVar) {
        this.zzajm.execute(new zzez(this, zzopVar));
    }

    private static zzov zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzda.zzbf(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            zzdi.zzax("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException unused2) {
            zzdi.zzac("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    @VisibleForTesting
    public final boolean zzb(zzop zzopVar) {
        File fileZzje = zzje();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileZzje);
            try {
                try {
                    int iZzpe = zzopVar.zzpe();
                    byte[] bArr = new byte[iZzpe];
                    zzuw.zza(zzopVar, bArr, 0, iZzpe);
                    fileOutputStream.write(bArr);
                    try {
                        fileOutputStream.close();
                        return true;
                    } catch (IOException unused) {
                        zzdi.zzac("error closing stream for writing resource to disk");
                        return true;
                    }
                } finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused2) {
                        zzdi.zzac("error closing stream for writing resource to disk");
                    }
                }
            } catch (IOException unused3) {
                zzdi.zzac("Error writing resource to disk. Removing resource from disk.");
                fileZzje.delete();
                return false;
            }
        } catch (FileNotFoundException unused4) {
            zzdi.zzav("Error opening resource file for writing");
            return false;
        }
    }
}
