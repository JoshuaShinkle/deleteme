package p000a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import p000a.InterfaceC0000a;

/* renamed from: a.c */
/* loaded from: classes.dex */
public interface InterfaceC0002c extends IInterface {

    /* renamed from: a.c$a */
    public static abstract class a extends Binder implements InterfaceC0002c {
        public a() {
            attachInterface(this, "android.support.customtabs.IPostMessageService");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) {
            if (i9 == 2) {
                parcel.enforceInterface("android.support.customtabs.IPostMessageService");
                mo11b(InterfaceC0000a.a.m2m(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i9 != 3) {
                if (i9 != 1598968902) {
                    return super.onTransact(i9, parcel, parcel2, i10);
                }
                parcel2.writeString("android.support.customtabs.IPostMessageService");
                return true;
            }
            parcel.enforceInterface("android.support.customtabs.IPostMessageService");
            mo12c(InterfaceC0000a.a.m2m(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    /* renamed from: b */
    void mo11b(InterfaceC0000a interfaceC0000a, Bundle bundle);

    /* renamed from: c */
    void mo12c(InterfaceC0000a interfaceC0000a, String str, Bundle bundle);
}
