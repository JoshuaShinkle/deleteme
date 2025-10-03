package net.sqlcipher;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface IContentObserver extends IInterface {

    public static class Default implements IContentObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // net.sqlcipher.IContentObserver
        public void onChange(boolean z8) {
        }
    }

    public static abstract class Stub extends Binder implements IContentObserver {
        private static final String DESCRIPTOR = "net.sqlcipher.IContentObserver";
        static final int TRANSACTION_onChange = 1;

        public static class Proxy implements IContentObserver {
            public static IContentObserver sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // net.sqlcipher.IContentObserver
            public void onChange(boolean z8) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z8 ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChange(z8);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContentObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IContentObserver)) ? new Proxy(iBinder) : (IContentObserver) iInterfaceQueryLocalInterface;
        }

        public static IContentObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IContentObserver iContentObserver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentObserver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentObserver;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) {
            if (i9 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onChange(parcel.readInt() != 0);
                return true;
            }
            if (i9 != 1598968902) {
                return super.onTransact(i9, parcel, parcel2, i10);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void onChange(boolean z8);
}
