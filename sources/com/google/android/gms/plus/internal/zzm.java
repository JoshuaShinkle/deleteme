package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.plus.PlusOneDummyView;

/* loaded from: classes2.dex */
public final class zzm extends RemoteCreator<zzd> {
    private static final zzm zzz = new zzm();

    private zzm() {
        super("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl");
    }

    public static View zza(Context context, int i9, int i10, String str, int i11) {
        try {
            if (str != null) {
                return (View) ObjectWrapper.unwrap(zzz.getRemoteCreatorInstance(context).zza(ObjectWrapper.wrap(context), i9, i10, str, i11));
            }
            throw new NullPointerException();
        } catch (Exception unused) {
            return new PlusOneDummyView(context, i9);
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final /* synthetic */ zzd getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusOneButtonCreator");
        return iInterfaceQueryLocalInterface instanceof zzd ? (zzd) iInterfaceQueryLocalInterface : new zze(iBinder);
    }
}
