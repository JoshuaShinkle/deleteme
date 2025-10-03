package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* loaded from: classes2.dex */
public final class zaw extends RemoteCreator<zam> {
    private static final zaw zaa = new zaw();

    private zaw() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zaa(Context context, int i9, int i10) {
        return zaa.zab(context, i9, i10);
    }

    private final View zab(Context context, int i9, int i10) throws RemoteCreator.RemoteCreatorException {
        try {
            zau zauVar = new zau(i9, i10, null);
            return (View) ObjectWrapper.unwrap(getRemoteCreatorInstance(context).zaa(ObjectWrapper.wrap(context), zauVar));
        } catch (Exception e9) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("Could not get button with size ");
            sb.append(i9);
            sb.append(" and color ");
            sb.append(i10);
            throw new RemoteCreator.RemoteCreatorException(sb.toString(), e9);
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final /* synthetic */ zam getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        return iInterfaceQueryLocalInterface instanceof zam ? (zam) iInterfaceQueryLocalInterface : new zal(iBinder);
    }
}
