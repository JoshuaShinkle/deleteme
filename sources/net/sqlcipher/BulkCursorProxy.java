package net.sqlcipher;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes2.dex */
final class BulkCursorProxy implements IBulkCursor {
    private Bundle mExtras = null;
    private IBinder mRemote;

    public BulkCursorProxy(IBinder iBinder) {
        this.mRemote = iBinder;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.mRemote;
    }

    @Override // net.sqlcipher.IBulkCursor
    public void close() throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        parcelObtain.recycle();
        parcelObtain2.recycle();
    }

    @Override // net.sqlcipher.IBulkCursor
    public int count() throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        boolean zTransact = this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        int i9 = !zTransact ? -1 : parcelObtain2.readInt();
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return i9;
    }

    @Override // net.sqlcipher.IBulkCursor
    public void deactivate() throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        parcelObtain.recycle();
        parcelObtain2.recycle();
    }

    @Override // net.sqlcipher.IBulkCursor
    public boolean deleteRow(int i9) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        parcelObtain.writeInt(i9);
        this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        boolean z8 = parcelObtain2.readInt() == 1;
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return z8;
    }

    @Override // net.sqlcipher.IBulkCursor
    public String[] getColumnNames() throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        int i9 = parcelObtain2.readInt();
        String[] strArr = new String[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            strArr[i10] = parcelObtain2.readString();
        }
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return strArr;
    }

    @Override // net.sqlcipher.IBulkCursor
    public Bundle getExtras() throws RemoteException {
        if (this.mExtras == null) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
            this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
            DatabaseUtils.readExceptionFromParcel(parcelObtain2);
            this.mExtras = parcelObtain2.readBundle(BulkCursorProxy.class.getClassLoader());
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
        return this.mExtras;
    }

    @Override // net.sqlcipher.IBulkCursor
    public boolean getWantsAllOnMoveCalls() throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        int i9 = parcelObtain2.readInt();
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return i9 != 0;
    }

    @Override // net.sqlcipher.IBulkCursor
    public CursorWindow getWindow(int i9) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        parcelObtain.writeInt(i9);
        this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        CursorWindow cursorWindowNewFromParcel = parcelObtain2.readInt() == 1 ? CursorWindow.newFromParcel(parcelObtain2) : null;
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return cursorWindowNewFromParcel;
    }

    @Override // net.sqlcipher.IBulkCursor
    public void onMove(int i9) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        parcelObtain.writeInt(i9);
        this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        parcelObtain.recycle();
        parcelObtain2.recycle();
    }

    @Override // net.sqlcipher.IBulkCursor
    public int requery(IContentObserver iContentObserver, CursorWindow cursorWindow) throws RemoteException {
        int i9;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        parcelObtain.writeStrongInterface(iContentObserver);
        cursorWindow.writeToParcel(parcelObtain, 0);
        boolean zTransact = this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        if (zTransact) {
            i9 = parcelObtain2.readInt();
            this.mExtras = parcelObtain2.readBundle(BulkCursorProxy.class.getClassLoader());
        } else {
            i9 = -1;
        }
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return i9;
    }

    @Override // net.sqlcipher.IBulkCursor
    public Bundle respond(Bundle bundle) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        parcelObtain.writeBundle(bundle);
        this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        Bundle bundle2 = parcelObtain2.readBundle(BulkCursorProxy.class.getClassLoader());
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return bundle2;
    }

    @Override // net.sqlcipher.IBulkCursor
    public boolean updateRows(Map map) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain.writeInterfaceToken(IBulkCursor.descriptor);
        parcelObtain.writeMap(map);
        this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
        DatabaseUtils.readExceptionFromParcel(parcelObtain2);
        boolean z8 = parcelObtain2.readInt() == 1;
        parcelObtain.recycle();
        parcelObtain2.recycle();
        return z8;
    }
}
