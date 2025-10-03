package com.google.firebase.iid;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes2.dex */
public class FirebaseIidMessengerCompat implements Parcelable {
    public static final Parcelable.Creator<FirebaseIidMessengerCompat> CREATOR = new Parcelable.Creator<FirebaseIidMessengerCompat>() { // from class: com.google.firebase.iid.FirebaseIidMessengerCompat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FirebaseIidMessengerCompat createFromParcel(Parcel parcel) {
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                return new FirebaseIidMessengerCompat(strongBinder);
            }
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FirebaseIidMessengerCompat[] newArray(int i9) {
            return new FirebaseIidMessengerCompat[i9];
        }
    };
    Messenger messenger;
    IMessengerCompat messengerCompat;

    public static final class HandleOldParcelNameClassLoader extends ClassLoader {
        @Override // java.lang.ClassLoader
        public final Class<?> loadClass(String str, boolean z8) {
            if (!"com.google.android.gms.iid.MessengerCompat".equals(str)) {
                return super.loadClass(str, z8);
            }
            if (!FirebaseInstanceId.isDebugLogEnabled()) {
                return FirebaseIidMessengerCompat.class;
            }
            Log.d("FirebaseInstanceId", "Using renamed FirebaseIidMessengerCompat class");
            return FirebaseIidMessengerCompat.class;
        }
    }

    @KeepForSdk
    public FirebaseIidMessengerCompat(Handler handler) {
        this.messenger = new Messenger(handler);
    }

    public static int getSendingUid(Message message) {
        return getSendingUidNew(message);
    }

    @TargetApi(21)
    private static int getSendingUidNew(Message message) {
        return message.sendingUid;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return getBinder().equals(((FirebaseIidMessengerCompat) obj).getBinder());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @KeepForSdk
    public IBinder getBinder() {
        Messenger messenger = this.messenger;
        return messenger != null ? messenger.getBinder() : this.messengerCompat.asBinder();
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public void send(Message message) throws RemoteException {
        Messenger messenger = this.messenger;
        if (messenger != null) {
            messenger.send(message);
        } else {
            this.messengerCompat.send(message);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        Messenger messenger = this.messenger;
        if (messenger != null) {
            parcel.writeStrongBinder(messenger.getBinder());
        } else {
            parcel.writeStrongBinder(this.messengerCompat.asBinder());
        }
    }

    public FirebaseIidMessengerCompat(IBinder iBinder) {
        this.messenger = new Messenger(iBinder);
    }
}
