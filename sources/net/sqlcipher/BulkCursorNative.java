package net.sqlcipher;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import net.sqlcipher.IContentObserver;

/* loaded from: classes2.dex */
public abstract class BulkCursorNative extends Binder implements IBulkCursor {
    public BulkCursorNative() {
        attachInterface(this, IBulkCursor.descriptor);
    }

    public static IBulkCursor asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IBulkCursor iBulkCursor = (IBulkCursor) iBinder.queryLocalInterface(IBulkCursor.descriptor);
        return iBulkCursor != null ? iBulkCursor : new BulkCursorProxy(iBinder);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) {
        try {
            switch (i9) {
                case 1:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    CursorWindow window = getWindow(parcel.readInt());
                    if (window != null) {
                        parcel2.writeNoException();
                        parcel2.writeInt(1);
                        window.writeToParcel(parcel2, 0);
                        break;
                    } else {
                        parcel2.writeInt(0);
                        break;
                    }
                case 2:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    int iCount = count();
                    parcel2.writeNoException();
                    parcel2.writeInt(iCount);
                    break;
                case 3:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    String[] columnNames = getColumnNames();
                    parcel2.writeNoException();
                    parcel2.writeInt(columnNames.length);
                    int length = columnNames.length;
                    while (i < length) {
                        parcel2.writeString(columnNames[i]);
                        i++;
                    }
                    break;
                case 4:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    boolean zUpdateRows = updateRows(parcel.readHashMap(null));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateRows ? 1 : 0);
                    break;
                case 5:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    boolean zDeleteRow = deleteRow(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteRow ? 1 : 0);
                    break;
                case 6:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    deactivate();
                    parcel2.writeNoException();
                    break;
                case 7:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    int iRequery = requery(IContentObserver.Stub.asInterface(parcel.readStrongBinder()), CursorWindow.CREATOR.createFromParcel(parcel));
                    parcel2.writeNoException();
                    parcel2.writeInt(iRequery);
                    parcel2.writeBundle(getExtras());
                    break;
                case 8:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    onMove(parcel.readInt());
                    parcel2.writeNoException();
                    break;
                case 9:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    boolean wantsAllOnMoveCalls = getWantsAllOnMoveCalls();
                    parcel2.writeNoException();
                    parcel2.writeInt(wantsAllOnMoveCalls ? 1 : 0);
                    break;
                case 10:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    Bundle extras = getExtras();
                    parcel2.writeNoException();
                    parcel2.writeBundle(extras);
                    break;
                case 11:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    Bundle bundleRespond = respond(parcel.readBundle(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    parcel2.writeBundle(bundleRespond);
                    break;
                case 12:
                    parcel.enforceInterface(IBulkCursor.descriptor);
                    close();
                    parcel2.writeNoException();
                    break;
            }
            return true;
        } catch (Exception e9) {
            DatabaseUtils.writeExceptionToParcel(parcel2, e9);
            return true;
        }
    }
}
