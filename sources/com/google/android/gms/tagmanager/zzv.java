package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.ContainerHolder;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzv implements ContainerHolder {
    private final Looper zzaek;
    private Container zzael;
    private Container zzaem;
    private Status zzaen;
    private zzx zzaeo;
    private zzw zzaep;
    private boolean zzaeq;
    private TagManager zzaer;

    public zzv(Status status) {
        this.zzaen = status;
        this.zzaek = null;
    }

    private final void zzhd() {
        zzx zzxVar = this.zzaeo;
        if (zzxVar != null) {
            zzxVar.sendMessage(zzxVar.obtainMessage(1, this.zzaem.zzha()));
        }
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized Container getContainer() {
        if (this.zzaeq) {
            zzdi.zzav("ContainerHolder is released.");
            return null;
        }
        Container container = this.zzaem;
        if (container != null) {
            this.zzael = container;
            this.zzaem = null;
        }
        return this.zzael;
    }

    public final String getContainerId() {
        if (!this.zzaeq) {
            return this.zzael.getContainerId();
        }
        zzdi.zzav("getContainerId called on a released ContainerHolder.");
        return "";
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzaen;
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void refresh() {
        if (this.zzaeq) {
            zzdi.zzav("Refreshing a released ContainerHolder.");
        } else {
            this.zzaep.zzhe();
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        if (this.zzaeq) {
            zzdi.zzav("Releasing a released ContainerHolder.");
            return;
        }
        this.zzaeq = true;
        this.zzaer.zzb(this);
        this.zzael.release();
        this.zzael = null;
        this.zzaem = null;
        this.zzaep = null;
        this.zzaeo = null;
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener containerAvailableListener) {
        if (this.zzaeq) {
            zzdi.zzav("ContainerHolder is released.");
        } else {
            if (containerAvailableListener == null) {
                this.zzaeo = null;
                return;
            }
            this.zzaeo = new zzx(this, containerAvailableListener, this.zzaek);
            if (this.zzaem != null) {
                zzhd();
            }
        }
    }

    public final synchronized void zza(Container container) {
        if (this.zzaeq) {
            return;
        }
        this.zzaem = container;
        zzhd();
    }

    public final synchronized void zzan(String str) {
        if (this.zzaeq) {
            return;
        }
        this.zzael.zzan(str);
    }

    public final void zzao(String str) {
        if (this.zzaeq) {
            zzdi.zzav("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzaep.zzao(str);
        }
    }

    public final String zzhc() {
        if (!this.zzaeq) {
            return this.zzaep.zzhc();
        }
        zzdi.zzav("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public zzv(TagManager tagManager, Looper looper, Container container, zzw zzwVar) {
        this.zzaer = tagManager;
        this.zzaek = looper == null ? Looper.getMainLooper() : looper;
        this.zzael = container;
        this.zzaep = zzwVar;
        this.zzaen = Status.RESULT_SUCCESS;
        tagManager.zza(this);
    }
}
