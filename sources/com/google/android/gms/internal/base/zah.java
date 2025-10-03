package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

/* loaded from: classes2.dex */
final class zah extends Drawable.ConstantState {
    int zaa;
    int zab;

    public zah(zah zahVar) {
        if (zahVar != null) {
            this.zaa = zahVar.zaa;
            this.zab = zahVar.zab;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.zaa;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new zae(this);
    }
}
