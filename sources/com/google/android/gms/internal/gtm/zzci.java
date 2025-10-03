package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Map;

/* loaded from: classes2.dex */
public class zzci extends zzan {
    private static zzci zzabl;

    public zzci(zzap zzapVar) {
        super(zzapVar);
    }

    @VisibleForTesting
    private static String zzd(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        if (!(obj instanceof Long)) {
            return obj instanceof Boolean ? String.valueOf(obj) : obj instanceof Throwable ? obj.getClass().getCanonicalName() : "-";
        }
        Long l9 = (Long) obj;
        if (Math.abs(l9.longValue()) < 100) {
            return String.valueOf(obj);
        }
        String str = String.valueOf(obj).charAt(0) != '-' ? "" : "-";
        String strValueOf = String.valueOf(Math.abs(l9.longValue()));
        return str + Math.round(Math.pow(10.0d, strValueOf.length() - 1)) + "..." + str + Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d);
    }

    public static zzci zzfn() {
        return zzabl;
    }

    public final void zza(zzcd zzcdVar, String str) {
        String string = zzcdVar != null ? zzcdVar.toString() : "no hit data";
        String strValueOf = String.valueOf(str);
        zzd(strValueOf.length() != 0 ? "Discarding hit. ".concat(strValueOf) : new String("Discarding hit. "), string);
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
        synchronized (zzci.class) {
            zzabl = this;
        }
    }

    public final synchronized void zzb(int i9, String str, Object obj, Object obj2, Object obj3) {
        Preconditions.checkNotNull(str);
        if (i9 < 0) {
            i9 = 0;
        }
        if (i9 >= 9) {
            i9 = 8;
        }
        char c9 = zzcp().zzem() ? 'C' : 'c';
        char cCharAt = "01VDIWEA?".charAt(i9);
        String str2 = zzao.VERSION;
        String strZzc = zzam.zzc(str, zzd(obj), zzd(obj2), zzd(obj3));
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 4 + String.valueOf(strZzc).length());
        sb.append("3");
        sb.append(cCharAt);
        sb.append(c9);
        sb.append(str2);
        sb.append(":");
        sb.append(strZzc);
        String string = sb.toString();
        if (string.length() > 1024) {
            string = string.substring(0, UserMetadata.MAX_ATTRIBUTE_SIZE);
        }
        zzcm zzcmVarZzdf = zzcm().zzdf();
        if (zzcmVarZzdf != null) {
            zzcmVarZzdf.zzga().zzae(string);
        }
    }

    public final void zza(Map<String, String> map, String str) {
        String string;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            string = sb.toString();
        } else {
            string = "no hit data";
        }
        String strValueOf = String.valueOf(str);
        zzd(strValueOf.length() != 0 ? "Discarding hit. ".concat(strValueOf) : new String("Discarding hit. "), string);
    }
}
