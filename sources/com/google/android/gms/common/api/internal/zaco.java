package com.google.android.gms.common.api.internal;

/* loaded from: classes2.dex */
final class zaco implements zacn {
    private final /* synthetic */ zacl zaa;

    public zaco(zacl zaclVar) {
        this.zaa = zaclVar;
    }

    @Override // com.google.android.gms.common.api.internal.zacn
    public final void zaa(BasePendingResult<?> basePendingResult) {
        this.zaa.zab.remove(basePendingResult);
    }
}
