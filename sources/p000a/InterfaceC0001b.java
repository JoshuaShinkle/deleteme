package p000a;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;
import p000a.InterfaceC0000a;

/* renamed from: a.b */
/* loaded from: classes.dex */
public interface InterfaceC0001b extends IInterface {

    /* renamed from: a.b$a */
    public static abstract class a extends Binder implements InterfaceC0001b {
        public a() {
            attachInterface(this, "android.support.customtabs.ICustomTabsService");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) {
            if (i9 == 1598968902) {
                parcel2.writeString("android.support.customtabs.ICustomTabsService");
                return true;
            }
            switch (i9) {
                case 2:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    boolean zMo6f = mo6f(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zMo6f ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    boolean zMo5e = mo5e(InterfaceC0000a.a.m2m(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zMo5e ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    boolean zMo7g = mo7g(InterfaceC0000a.a.m2m(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(Bundle.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(zMo7g ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    Bundle bundleMo4d = mo4d(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (bundleMo4d != null) {
                        parcel2.writeInt(1);
                        bundleMo4d.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    boolean zMo9k = mo9k(InterfaceC0000a.a.m2m(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zMo9k ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    boolean zMo3a = mo3a(InterfaceC0000a.a.m2m(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zMo3a ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    int iMo8h = mo8h(InterfaceC0000a.a.m2m(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iMo8h);
                    return true;
                case 9:
                    parcel.enforceInterface("android.support.customtabs.ICustomTabsService");
                    boolean zMo10l = mo10l(InterfaceC0000a.a.m2m(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zMo10l ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i9, parcel, parcel2, i10);
            }
        }
    }

    /* renamed from: a */
    boolean mo3a(InterfaceC0000a interfaceC0000a, Uri uri);

    /* renamed from: d */
    Bundle mo4d(String str, Bundle bundle);

    /* renamed from: e */
    boolean mo5e(InterfaceC0000a interfaceC0000a);

    /* renamed from: f */
    boolean mo6f(long j9);

    /* renamed from: g */
    boolean mo7g(InterfaceC0000a interfaceC0000a, Uri uri, Bundle bundle, List<Bundle> list);

    /* renamed from: h */
    int mo8h(InterfaceC0000a interfaceC0000a, String str, Bundle bundle);

    /* renamed from: k */
    boolean mo9k(InterfaceC0000a interfaceC0000a, Bundle bundle);

    /* renamed from: l */
    boolean mo10l(InterfaceC0000a interfaceC0000a, int i9, Uri uri, Bundle bundle);
}
