package com.google.android.gms.common.api.internal;

/* loaded from: classes2.dex */
final class zaag implements Runnable {
    private final /* synthetic */ zaad zaa;

    public zaag(zaad zaadVar) {
        this.zaa = zaadVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zad.cancelAvailabilityErrorNotifications(this.zaa.zac);
    }
}
