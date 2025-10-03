package com.google.android.gms.internal.gtm;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes2.dex */
public final class zzbh extends zzan {
    private volatile String zzut;
    private Future<String> zzyh;

    public zzbh(zzap zzapVar) {
        super(zzapVar);
    }

    private final boolean zzb(Context context, String str) throws IOException {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotMainThread("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            try {
                zza("Storing clientId", str);
                fileOutputStreamOpenFileOutput = context.openFileOutput("gaClientId", 0);
                fileOutputStreamOpenFileOutput.write(str.getBytes());
                try {
                    fileOutputStreamOpenFileOutput.close();
                    return true;
                } catch (IOException e9) {
                    zze("Failed to close clientId writing stream", e9);
                    return true;
                }
            } catch (Throwable th) {
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (IOException e10) {
                        zze("Failed to close clientId writing stream", e10);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e11) {
            zze("Error creating clientId file", e11);
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (IOException e12) {
                    zze("Failed to close clientId writing stream", e12);
                }
            }
            return false;
        } catch (IOException e13) {
            zze("Error writing to clientId file", e13);
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (IOException e14) {
                    zze("Failed to close clientId writing stream", e14);
                }
            }
            return false;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0079: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:41:0x0079 */
    /* JADX WARN: Removed duplicated region for block: B:63:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String zzd(Context context) throws Throwable {
        FileInputStream fileInputStreamOpenFileInput;
        FileInputStream fileInputStream;
        Preconditions.checkNotMainThread("ClientId should be loaded from worker thread");
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStreamOpenFileInput = context.openFileInput("gaClientId");
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e9) {
                        zze("Failed to close client id reading stream", e9);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            fileInputStreamOpenFileInput = null;
        } catch (IOException e10) {
            e = e10;
            fileInputStreamOpenFileInput = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream2 != null) {
            }
            throw th;
        }
        try {
            byte[] bArr = new byte[36];
            int i9 = fileInputStreamOpenFileInput.read(bArr, 0, 36);
            if (fileInputStreamOpenFileInput.available() > 0) {
                zzt("clientId file seems corrupted, deleting it.");
                fileInputStreamOpenFileInput.close();
                context.deleteFile("gaClientId");
                try {
                    fileInputStreamOpenFileInput.close();
                } catch (IOException e11) {
                    zze("Failed to close client id reading stream", e11);
                }
                return null;
            }
            if (i9 < 14) {
                zzt("clientId file is empty, deleting it.");
                fileInputStreamOpenFileInput.close();
                context.deleteFile("gaClientId");
                try {
                    fileInputStreamOpenFileInput.close();
                } catch (IOException e12) {
                    zze("Failed to close client id reading stream", e12);
                }
                return null;
            }
            fileInputStreamOpenFileInput.close();
            String str = new String(bArr, 0, i9);
            zza("Read client id from disk", str);
            try {
                fileInputStreamOpenFileInput.close();
            } catch (IOException e13) {
                zze("Failed to close client id reading stream", e13);
            }
            return str;
        } catch (FileNotFoundException unused2) {
            if (fileInputStreamOpenFileInput != null) {
                try {
                    fileInputStreamOpenFileInput.close();
                } catch (IOException e14) {
                    zze("Failed to close client id reading stream", e14);
                }
            }
            return null;
        } catch (IOException e15) {
            e = e15;
            zze("Error reading client id file, deleting it", e);
            context.deleteFile("gaClientId");
            if (fileInputStreamOpenFileInput != null) {
                try {
                    fileInputStreamOpenFileInput.close();
                } catch (IOException e16) {
                    zze("Failed to close client id reading stream", e16);
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @VisibleForTesting
    public final String zzek() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
        try {
            return !zzb(zzcq().getContext(), lowerCase) ? "0" : lowerCase;
        } catch (Exception e9) {
            zze("Error saving clientId file", e9);
            return "0";
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
    }

    public final String zzeh() {
        String str;
        zzdb();
        synchronized (this) {
            if (this.zzut == null) {
                this.zzyh = zzcq().zza(new zzbi(this));
            }
            Future<String> future = this.zzyh;
            if (future != null) {
                try {
                    this.zzut = future.get();
                } catch (InterruptedException e9) {
                    zzd("ClientId loading or generation was interrupted", e9);
                    this.zzut = "0";
                } catch (ExecutionException e10) {
                    zze("Failed to load or generate client id", e10);
                    this.zzut = "0";
                }
                if (this.zzut == null) {
                    this.zzut = "0";
                }
                zza("Loaded clientId", this.zzut);
                this.zzyh = null;
                str = this.zzut;
            } else {
                str = this.zzut;
            }
        }
        return str;
    }

    public final String zzei() {
        synchronized (this) {
            this.zzut = null;
            this.zzyh = zzcq().zza(new zzbj(this));
        }
        return zzeh();
    }

    @VisibleForTesting
    public final String zzej() throws Throwable {
        String strZzd = zzd(zzcq().getContext());
        return strZzd == null ? zzek() : strZzd;
    }
}
