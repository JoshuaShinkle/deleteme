package p000a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: a.a */
/* loaded from: classes.dex */
public interface InterfaceC0000a extends IInterface {

    /* renamed from: a.a$a */
    public static abstract class a extends Binder implements InterfaceC0000a {

        /* renamed from: a.a$a$a, reason: collision with other inner class name */
        public static class C6839a implements InterfaceC0000a {

            /* renamed from: a */
            public IBinder f0a;

            public C6839a(IBinder iBinder) {
                this.f0a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f0a;
            }

            @Override // p000a.InterfaceC0000a
            /* renamed from: i */
            public void mo0i(String str, Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f0a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // p000a.InterfaceC0000a
            /* renamed from: j */
            public void mo1j(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f0a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        /* renamed from: m */
        public static InterfaceC0000a m2m(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.customtabs.ICustomTabsCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC0000a)) ? new C6839a(iBinder) : (InterfaceC0000a) iInterfaceQueryLocalInterface;
        }
    }

    /* renamed from: i */
    void mo0i(String str, Bundle bundle);

    /* renamed from: j */
    void mo1j(Bundle bundle);
}
