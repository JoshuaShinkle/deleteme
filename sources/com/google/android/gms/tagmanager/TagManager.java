package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@VisibleForTesting
/* loaded from: classes2.dex */
public class TagManager {
    private static TagManager zzalg;
    private final DataLayer zzaed;
    private final zzal zzajg;
    private final zza zzald;
    private final zzfm zzale;
    private final ConcurrentMap<String, zzv> zzalf;
    private final Context zzrm;

    @VisibleForTesting
    public interface zza {
        zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i9, zzal zzalVar);
    }

    @VisibleForTesting
    private TagManager(Context context, zza zzaVar, DataLayer dataLayer, zzfm zzfmVar) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        Context applicationContext = context.getApplicationContext();
        this.zzrm = applicationContext;
        this.zzale = zzfmVar;
        this.zzald = zzaVar;
        this.zzalf = new ConcurrentHashMap();
        this.zzaed = dataLayer;
        dataLayer.zza(new zzga(this));
        dataLayer.zza(new zzg(applicationContext));
        this.zzajg = new zzal();
        applicationContext.registerComponentCallbacks(new zzgc(this));
        com.google.android.gms.tagmanager.zza.zzf(applicationContext);
    }

    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzalg == null) {
                if (context == null) {
                    zzdi.zzav("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzalg = new TagManager(context, new zzgb(), new DataLayer(new zzat(context)), zzfn.zzjq());
            }
            tagManager = zzalg;
        }
        return tagManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbl(String str) {
        Iterator<zzv> it = this.zzalf.values().iterator();
        while (it.hasNext()) {
            it.next().zzan(str);
        }
    }

    public void dispatch() {
        this.zzale.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzaed;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, int i9) {
        zzy zzyVarZza = this.zzald.zza(this.zzrm, this, null, str, i9, this.zzajg);
        zzyVarZza.zzhf();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, int i9) {
        zzy zzyVarZza = this.zzald.zza(this.zzrm, this, null, str, i9, this.zzajg);
        zzyVarZza.zzhh();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, int i9) {
        zzy zzyVarZza = this.zzald.zza(this.zzrm, this, null, str, i9, this.zzajg);
        zzyVarZza.zzhg();
        return zzyVarZza;
    }

    public void setVerboseLoggingEnabled(boolean z8) {
        zzdi.setLogLevel(z8 ? 2 : 5);
    }

    public final synchronized boolean zza(Uri uri) {
        zzeh zzehVarZziy = zzeh.zziy();
        if (!zzehVarZziy.zza(uri)) {
            return false;
        }
        String containerId = zzehVarZziy.getContainerId();
        int i9 = zzgd.zzali[zzehVarZziy.zziz().ordinal()];
        if (i9 == 1) {
            zzv zzvVar = this.zzalf.get(containerId);
            if (zzvVar != null) {
                zzvVar.zzao(null);
                zzvVar.refresh();
            }
        } else if (i9 == 2 || i9 == 3) {
            for (String str : this.zzalf.keySet()) {
                zzv zzvVar2 = this.zzalf.get(str);
                if (str.equals(containerId)) {
                    zzvVar2.zzao(zzehVarZziy.zzja());
                    zzvVar2.refresh();
                } else if (zzvVar2.zzhc() != null) {
                    zzvVar2.zzao(null);
                    zzvVar2.refresh();
                }
            }
        }
        return true;
    }

    @VisibleForTesting
    public final boolean zzb(zzv zzvVar) {
        return this.zzalf.remove(zzvVar.getContainerId()) != null;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, int i9, Handler handler) {
        zzy zzyVarZza = this.zzald.zza(this.zzrm, this, handler.getLooper(), str, i9, this.zzajg);
        zzyVarZza.zzhf();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, int i9, Handler handler) {
        zzy zzyVarZza = this.zzald.zza(this.zzrm, this, handler.getLooper(), str, i9, this.zzajg);
        zzyVarZza.zzhh();
        return zzyVarZza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, int i9, Handler handler) {
        zzy zzyVarZza = this.zzald.zza(this.zzrm, this, handler.getLooper(), str, i9, this.zzajg);
        zzyVarZza.zzhg();
        return zzyVarZza;
    }

    @VisibleForTesting
    public final int zza(zzv zzvVar) {
        this.zzalf.put(zzvVar.getContainerId(), zzvVar);
        return this.zzalf.size();
    }
}
