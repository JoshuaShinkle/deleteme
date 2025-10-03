package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* loaded from: classes2.dex */
public final class zzad {
    public static final zzad zza = new zzad(null, null);
    private final Boolean zzb;
    private final Boolean zzc;

    public zzad(Boolean bool, Boolean bool2) {
        this.zzb = bool;
        this.zzc = bool2;
    }

    private static int zza(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : 2;
    }

    public static boolean zza(int i9, int i10) {
        return i9 <= i10;
    }

    public static zzad zzb(Bundle bundle) {
        return bundle == null ? zza : new zzad(zzb(bundle.getString("ad_storage")), zzb(bundle.getString("analytics_storage")));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzadVar = (zzad) obj;
        return zza(this.zzb) == zza(zzadVar.zzb) && zza(this.zzc) == zza(zzadVar.zzc);
    }

    public final int hashCode() {
        return ((zza(this.zzb) + 527) * 31) + zza(this.zzc);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ConsentSettings: ");
        sb.append("adStorage=");
        Boolean bool = this.zzb;
        if (bool == null) {
            sb.append("uninitialized");
        } else {
            sb.append(bool.booleanValue() ? "granted" : "denied");
        }
        sb.append(", analyticsStorage=");
        Boolean bool2 = this.zzc;
        if (bool2 == null) {
            sb.append("uninitialized");
        } else {
            sb.append(bool2.booleanValue() ? "granted" : "denied");
        }
        return sb.toString();
    }

    public final boolean zzc() {
        Boolean bool = this.zzb;
        return bool == null || bool.booleanValue();
    }

    public final Boolean zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        Boolean bool = this.zzc;
        return bool == null || bool.booleanValue();
    }

    public static String zza(Bundle bundle) {
        String string = bundle.getString("ad_storage");
        if (string != null && zzb(string) == null) {
            return string;
        }
        String string2 = bundle.getString("analytics_storage");
        if (string2 == null || zzb(string2) != null) {
            return null;
        }
        return string2;
    }

    public final zzad zzc(zzad zzadVar) {
        Boolean bool = this.zzb;
        if (bool == null) {
            bool = zzadVar.zzb;
        }
        Boolean bool2 = this.zzc;
        if (bool2 == null) {
            bool2 = zzadVar.zzc;
        }
        return new zzad(bool, bool2);
    }

    private static Boolean zzb(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("granted")) {
            return Boolean.TRUE;
        }
        if (str.equals("denied")) {
            return Boolean.FALSE;
        }
        return null;
    }

    public static zzad zza(String str) {
        Boolean boolZza;
        if (str != null) {
            Boolean boolZza2 = str.length() >= 3 ? zza(str.charAt(2)) : null;
            boolZza = str.length() >= 4 ? zza(str.charAt(3)) : null;
            bool = boolZza2;
        } else {
            boolZza = null;
        }
        return new zzad(bool, boolZza);
    }

    private static char zzb(Boolean bool) {
        if (bool == null) {
            return '-';
        }
        return bool.booleanValue() ? '1' : '0';
    }

    public final Boolean zzb() {
        return this.zzb;
    }

    private static Boolean zza(char c9) {
        if (c9 == '0') {
            return Boolean.FALSE;
        }
        if (c9 != '1') {
            return null;
        }
        return Boolean.TRUE;
    }

    public final zzad zzb(zzad zzadVar) {
        return new zzad(zza(this.zzb, zzadVar.zzb), zza(this.zzc, zzadVar.zzc));
    }

    public final String zza() {
        return "G1" + zzb(this.zzb) + zzb(this.zzc);
    }

    public final boolean zza(zzad zzadVar) {
        Boolean bool = this.zzb;
        Boolean bool2 = Boolean.FALSE;
        if (bool != bool2 || zzadVar.zzb == bool2) {
            return this.zzc == bool2 && zzadVar.zzc != bool2;
        }
        return true;
    }

    private static Boolean zza(Boolean bool, Boolean bool2) {
        if (bool != null) {
            return bool.booleanValue() ? bool2 : Boolean.FALSE;
        }
        if (bool2 == null || bool2.booleanValue()) {
            return null;
        }
        return Boolean.FALSE;
    }
}
