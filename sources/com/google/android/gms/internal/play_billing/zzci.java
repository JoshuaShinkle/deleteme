package com.google.android.gms.internal.play_billing;

import java.io.IOException;

/* loaded from: classes2.dex */
public class zzci extends IOException {
    private zzdf zza;

    public zzci(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    public static zzch zza() {
        return new zzch("Protocol message tag had invalid wire type.");
    }

    public static zzci zzb() {
        return new zzci("Protocol message contained an invalid tag (zero).");
    }

    public static zzci zzc() {
        return new zzci("Protocol message had invalid UTF-8.");
    }

    public static zzci zzd() {
        return new zzci("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzci zze() {
        return new zzci("Failed to parse the message.");
    }

    public static zzci zzg() {
        return new zzci("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzci zzf(zzdf zzdfVar) {
        this.zza = zzdfVar;
        return this;
    }

    public zzci(String str) {
        super(str);
        this.zza = null;
    }
}
