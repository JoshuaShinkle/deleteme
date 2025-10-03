package com.google.android.gms.internal.firebase_messaging;

/* loaded from: classes2.dex */
final class zzo extends zzn {
    private final zzm zza = new zzm();

    @Override // com.google.android.gms.internal.firebase_messaging.zzn
    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zza.zza(th, true).add(th2);
    }
}
