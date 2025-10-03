package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* loaded from: classes2.dex */
final class zaaj extends zaan {
    private final ArrayList<Api.Client> zaa;
    private final /* synthetic */ zaad zab;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaj(zaad zaadVar, ArrayList<Api.Client> arrayList) {
        super(zaadVar, null);
        this.zab = zaadVar;
        this.zaa = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaan
    public final void zaa() {
        this.zab.zaa.zad.zac = this.zab.zai();
        ArrayList<Api.Client> arrayList = this.zaa;
        int size = arrayList.size();
        int i9 = 0;
        while (i9 < size) {
            Api.Client client = arrayList.get(i9);
            i9++;
            client.getRemoteService(this.zab.zao, this.zab.zaa.zad.zac);
        }
    }
}
