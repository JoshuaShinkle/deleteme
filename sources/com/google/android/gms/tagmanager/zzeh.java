package com.google.android.gms.tagmanager;

import android.net.Uri;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
class zzeh {
    private static zzeh zzaip;
    private volatile zza zzaiq = zza.NONE;
    private volatile String zzair = null;
    private volatile String zzaec = null;
    private volatile String zzais = null;

    public enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    private static String zzbh(String str) {
        return str.split("&")[0].split("=")[1];
    }

    @ShowFirstParty
    public static zzeh zziy() {
        zzeh zzehVar;
        synchronized (zzeh.class) {
            if (zzaip == null) {
                zzaip = new zzeh();
            }
            zzehVar = zzaip;
        }
        return zzehVar;
    }

    public final String getContainerId() {
        return this.zzaec;
    }

    public final synchronized boolean zza(Uri uri) {
        try {
            String strDecode = URLDecoder.decode(uri.toString(), "UTF-8");
            if (!strDecode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                if (!strDecode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    zzdi.zzac(strDecode.length() != 0 ? "Invalid preview uri: ".concat(strDecode) : new String("Invalid preview uri: "));
                    return false;
                }
                if (!zzbh(uri.getQuery()).equals(this.zzaec)) {
                    return false;
                }
                String strValueOf = String.valueOf(this.zzaec);
                zzdi.zzab(strValueOf.length() != 0 ? "Exit preview mode for container: ".concat(strValueOf) : new String("Exit preview mode for container: "));
                this.zzaiq = zza.NONE;
                this.zzair = null;
                return true;
            }
            zzdi.zzab(strDecode.length() != 0 ? "Container preview url: ".concat(strDecode) : new String("Container preview url: "));
            if (strDecode.matches(".*?&gtm_debug=x$")) {
                this.zzaiq = zza.CONTAINER_DEBUG;
            } else {
                this.zzaiq = zza.CONTAINER;
            }
            this.zzais = uri.getQuery().replace("&gtm_debug=x", "");
            if (this.zzaiq == zza.CONTAINER || this.zzaiq == zza.CONTAINER_DEBUG) {
                String strValueOf2 = String.valueOf(this.zzais);
                this.zzair = strValueOf2.length() != 0 ? "/r?".concat(strValueOf2) : new String("/r?");
            }
            this.zzaec = zzbh(this.zzais);
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    public final zza zziz() {
        return this.zzaiq;
    }

    public final String zzja() {
        return this.zzair;
    }
}
