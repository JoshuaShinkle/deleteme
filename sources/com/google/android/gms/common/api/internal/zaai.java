package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes2.dex */
final class zaai extends zaan {
    final /* synthetic */ zaad zaa;
    private final Map<Api.Client, zaaf> zab;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaai(zaad zaadVar, Map<Api.Client, zaaf> map) {
        super(zaadVar, null);
        this.zaa = zaadVar;
        this.zab = map;
    }

    @Override // com.google.android.gms.common.api.internal.zaan
    public final void zaa() {
        com.google.android.gms.common.internal.zaj zajVar = new com.google.android.gms.common.internal.zaj(this.zaa.zad);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.zab.keySet()) {
            if (!client.requiresGooglePlayServices() || this.zab.get(client).zac) {
                arrayList2.add(client);
            } else {
                arrayList.add(client);
            }
        }
        int i9 = 0;
        int iZaa = -1;
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            while (i9 < size) {
                Object obj = arrayList.get(i9);
                i9++;
                iZaa = zajVar.zaa(this.zaa.zac, (Api.Client) obj);
                if (iZaa != 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList2.size();
            while (i9 < size2) {
                Object obj2 = arrayList2.get(i9);
                i9++;
                iZaa = zajVar.zaa(this.zaa.zac, (Api.Client) obj2);
                if (iZaa == 0) {
                    break;
                }
            }
        }
        if (iZaa != 0) {
            this.zaa.zaa.zaa(new zaah(this, this.zaa, new ConnectionResult(iZaa, null)));
            return;
        }
        if (this.zaa.zam && this.zaa.zak != null) {
            this.zaa.zak.zab();
        }
        for (Api.Client client2 : this.zab.keySet()) {
            zaaf zaafVar = this.zab.get(client2);
            if (!client2.requiresGooglePlayServices() || zajVar.zaa(this.zaa.zac, client2) == 0) {
                client2.connect(zaafVar);
            } else {
                this.zaa.zaa.zaa(new zaak(this, this.zaa, zaafVar));
            }
        }
    }
}
