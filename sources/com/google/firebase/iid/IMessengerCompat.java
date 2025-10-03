package com.google.firebase.iid;

import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;

/* loaded from: classes2.dex */
interface IMessengerCompat extends IInterface {

    public static class Impl extends Binder implements IMessengerCompat {
        private final Handler handler;

        public Impl(Handler handler) {
            this.handler = handler;
            attachInterface(this, "com.google.android.gms.iid.IMessengerCompat");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) {
            parcel.enforceInterface(getInterfaceDescriptor());
            if (i9 != 1) {
                return false;
            }
            send(parcel.readInt() != 0 ? (Message) Message.CREATOR.createFromParcel(parcel) : null);
            return true;
        }

        @Override // com.google.firebase.iid.IMessengerCompat
        public void send(Message message) {
            message.arg2 = Binder.getCallingUid();
            this.handler.dispatchMessage(message);
        }
    }

    public static class Proxy implements IMessengerCompat {
        private final IBinder remote;

        public Proxy(IBinder iBinder) {
            this.remote = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.remote;
        }

        @Override // com.google.firebase.iid.IMessengerCompat
        public void send(Message message) {
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
            parcelObtain.writeInt(1);
            message.writeToParcel(parcelObtain, 0);
            try {
                this.remote.transact(1, parcelObtain, null, 1);
            } finally {
                parcelObtain.recycle();
            }
        }
    }

    void send(Message message);
}
