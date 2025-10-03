package net.sqlcipher;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObservable;
import android.database.ContentObserver;
import android.database.CrossProcessCursor;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class AbstractCursor implements CrossProcessCursor, Cursor {
    private static final String TAG = "Cursor";
    protected ContentResolver mContentResolver;
    private Uri mNotifyUri;
    private ContentObserver mSelfObserver;
    private boolean mSelfObserverRegistered;
    DataSetObservable mDataSetObservable = new DataSetObservable();
    ContentObservable mContentObservable = new ContentObservable();
    private Bundle mExtras = Bundle.EMPTY;
    protected boolean mClosed = false;
    private final Object mSelfObserverLock = new Object();
    protected int mPos = -1;
    protected int mRowIdColumnIndex = -1;
    protected Long mCurrentRowID = null;
    protected HashMap<Long, Map<String, Object>> mUpdatedRows = new HashMap<>();

    public static class SelfContentObserver extends ContentObserver {
        WeakReference<AbstractCursor> mCursor;

        public SelfContentObserver(AbstractCursor abstractCursor) {
            super(null);
            this.mCursor = new WeakReference<>(abstractCursor);
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return false;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z8) {
            AbstractCursor abstractCursor = this.mCursor.get();
            if (abstractCursor != null) {
                abstractCursor.onChange(false);
            }
        }
    }

    public void abortUpdates() {
        synchronized (this.mUpdatedRows) {
            this.mUpdatedRows.clear();
        }
    }

    public void checkPosition() {
        if (-1 == this.mPos || getCount() == this.mPos) {
            throw new CursorIndexOutOfBoundsException(this.mPos, getCount());
        }
    }

    @Override // android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mClosed = true;
        this.mContentObservable.unregisterAll();
        deactivateInternal();
    }

    public boolean commitUpdates() {
        return commitUpdates(null);
    }

    public boolean commitUpdates(Map<? extends Long, ? extends Map<String, Object>> map) {
        return false;
    }

    @Override // android.database.Cursor
    public void copyStringToBuffer(int i9, CharArrayBuffer charArrayBuffer) {
        String string = getString(i9);
        if (string == null) {
            charArrayBuffer.sizeCopied = 0;
            return;
        }
        char[] cArr = charArrayBuffer.data;
        if (cArr == null || cArr.length < string.length()) {
            charArrayBuffer.data = string.toCharArray();
        } else {
            string.getChars(0, string.length(), cArr, 0);
        }
        charArrayBuffer.sizeCopied = string.length();
    }

    @Override // android.database.Cursor
    public void deactivate() {
        deactivateInternal();
    }

    public void deactivateInternal() {
        ContentObserver contentObserver = this.mSelfObserver;
        if (contentObserver != null) {
            this.mContentResolver.unregisterContentObserver(contentObserver);
            this.mSelfObserverRegistered = false;
        }
        this.mDataSetObservable.notifyInvalidated();
    }

    public boolean deleteRow() {
        return false;
    }

    @Override // android.database.CrossProcessCursor
    public void fillWindow(int i9, android.database.CursorWindow cursorWindow) {
        DatabaseUtils.cursorFillWindow(this, i9, cursorWindow);
    }

    public void finalize() {
        ContentObserver contentObserver = this.mSelfObserver;
        if (contentObserver == null || !this.mSelfObserverRegistered) {
            return;
        }
        this.mContentResolver.unregisterContentObserver(contentObserver);
    }

    @Override // android.database.Cursor
    public byte[] getBlob(int i9) {
        throw new UnsupportedOperationException("getBlob is not supported");
    }

    @Override // android.database.Cursor
    public int getColumnCount() {
        return getColumnNames().length;
    }

    @Override // android.database.Cursor
    public int getColumnIndex(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf != -1) {
            Log.e(TAG, "requesting column name with table name -- " + str, new Exception());
            str = str.substring(iLastIndexOf + 1);
        }
        String[] columnNames = getColumnNames();
        int length = columnNames.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (columnNames[i9].equalsIgnoreCase(str)) {
                return i9;
            }
        }
        return -1;
    }

    @Override // android.database.Cursor
    public int getColumnIndexOrThrow(String str) {
        int columnIndex = getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist");
    }

    @Override // android.database.Cursor
    public String getColumnName(int i9) {
        return getColumnNames()[i9];
    }

    @Override // android.database.Cursor
    public abstract String[] getColumnNames();

    @Override // android.database.Cursor
    public abstract int getCount();

    public DataSetObservable getDataSetObservable() {
        return this.mDataSetObservable;
    }

    @Override // android.database.Cursor
    public abstract double getDouble(int i9);

    @Override // android.database.Cursor
    public Bundle getExtras() {
        return this.mExtras;
    }

    @Override // android.database.Cursor
    public abstract float getFloat(int i9);

    @Override // android.database.Cursor
    public abstract int getInt(int i9);

    @Override // android.database.Cursor
    public abstract long getLong(int i9);

    @Override // android.database.Cursor
    public Uri getNotificationUri() {
        return this.mNotifyUri;
    }

    @Override // android.database.Cursor
    public final int getPosition() {
        return this.mPos;
    }

    @Override // android.database.Cursor
    public abstract short getShort(int i9);

    @Override // android.database.Cursor
    public abstract String getString(int i9);

    @Override // android.database.Cursor, net.sqlcipher.Cursor
    public abstract int getType(int i9);

    public Object getUpdatedField(int i9) {
        return this.mUpdatedRows.get(this.mCurrentRowID).get(getColumnNames()[i9]);
    }

    @Override // android.database.Cursor
    public boolean getWantsAllOnMoveCalls() {
        return false;
    }

    @Override // android.database.CrossProcessCursor
    public CursorWindow getWindow() {
        return null;
    }

    public boolean hasUpdates() {
        boolean z8;
        synchronized (this.mUpdatedRows) {
            z8 = this.mUpdatedRows.size() > 0;
        }
        return z8;
    }

    @Override // android.database.Cursor
    public final boolean isAfterLast() {
        return getCount() == 0 || this.mPos == getCount();
    }

    @Override // android.database.Cursor
    public final boolean isBeforeFirst() {
        return getCount() == 0 || this.mPos == -1;
    }

    @Override // android.database.Cursor
    public boolean isClosed() {
        return this.mClosed;
    }

    public boolean isFieldUpdated(int i9) {
        Map<String, Object> map;
        return this.mRowIdColumnIndex != -1 && this.mUpdatedRows.size() > 0 && (map = this.mUpdatedRows.get(this.mCurrentRowID)) != null && map.containsKey(getColumnNames()[i9]);
    }

    @Override // android.database.Cursor
    public final boolean isFirst() {
        return this.mPos == 0 && getCount() != 0;
    }

    @Override // android.database.Cursor
    public final boolean isLast() {
        int count = getCount();
        return this.mPos == count + (-1) && count != 0;
    }

    @Override // android.database.Cursor
    public abstract boolean isNull(int i9);

    @Override // android.database.Cursor
    public final boolean move(int i9) {
        return moveToPosition(this.mPos + i9);
    }

    @Override // android.database.Cursor
    public final boolean moveToFirst() {
        return moveToPosition(0);
    }

    @Override // android.database.Cursor
    public final boolean moveToLast() {
        return moveToPosition(getCount() - 1);
    }

    @Override // android.database.Cursor
    public final boolean moveToNext() {
        return moveToPosition(this.mPos + 1);
    }

    @Override // android.database.Cursor
    public final boolean moveToPosition(int i9) {
        int count = getCount();
        if (i9 >= count) {
            this.mPos = count;
            return false;
        }
        if (i9 < 0) {
            this.mPos = -1;
            return false;
        }
        int i10 = this.mPos;
        if (i9 == i10) {
            return true;
        }
        boolean zOnMove = onMove(i10, i9);
        if (zOnMove) {
            this.mPos = i9;
            int i11 = this.mRowIdColumnIndex;
            if (i11 != -1) {
                this.mCurrentRowID = Long.valueOf(getLong(i11));
            }
        } else {
            this.mPos = -1;
        }
        return zOnMove;
    }

    @Override // android.database.Cursor
    public final boolean moveToPrevious() {
        return moveToPosition(this.mPos - 1);
    }

    public void notifyDataSetChange() {
        this.mDataSetObservable.notifyChanged();
    }

    public void onChange(boolean z8) {
        synchronized (this.mSelfObserverLock) {
            this.mContentObservable.dispatchChange(z8);
            Uri uri = this.mNotifyUri;
            if (uri != null && z8) {
                this.mContentResolver.notifyChange(uri, this.mSelfObserver);
            }
        }
    }

    @Override // android.database.CrossProcessCursor
    public boolean onMove(int i9, int i10) {
        return true;
    }

    @Override // android.database.Cursor
    public void registerContentObserver(ContentObserver contentObserver) {
        this.mContentObservable.registerObserver(contentObserver);
    }

    @Override // android.database.Cursor
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.registerObserver(dataSetObserver);
    }

    @Override // android.database.Cursor
    public boolean requery() {
        ContentObserver contentObserver = this.mSelfObserver;
        if (contentObserver != null && !this.mSelfObserverRegistered) {
            this.mContentResolver.registerContentObserver(this.mNotifyUri, true, contentObserver);
            this.mSelfObserverRegistered = true;
        }
        this.mDataSetObservable.notifyChanged();
        return true;
    }

    @Override // android.database.Cursor
    public Bundle respond(Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // android.database.Cursor
    public void setExtras(Bundle bundle) {
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        this.mExtras = bundle;
    }

    @Override // android.database.Cursor
    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        synchronized (this.mSelfObserverLock) {
            this.mNotifyUri = uri;
            this.mContentResolver = contentResolver;
            ContentObserver contentObserver = this.mSelfObserver;
            if (contentObserver != null) {
                contentResolver.unregisterContentObserver(contentObserver);
            }
            SelfContentObserver selfContentObserver = new SelfContentObserver(this);
            this.mSelfObserver = selfContentObserver;
            this.mContentResolver.registerContentObserver(this.mNotifyUri, true, selfContentObserver);
            this.mSelfObserverRegistered = true;
        }
    }

    public boolean supportsUpdates() {
        return this.mRowIdColumnIndex != -1;
    }

    @Override // android.database.Cursor
    public void unregisterContentObserver(ContentObserver contentObserver) {
        if (this.mClosed) {
            return;
        }
        this.mContentObservable.unregisterObserver(contentObserver);
    }

    @Override // android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.unregisterObserver(dataSetObserver);
    }

    public boolean update(int i9, Object obj) {
        if (!supportsUpdates()) {
            return false;
        }
        Long lValueOf = Long.valueOf(getLong(this.mRowIdColumnIndex));
        if (lValueOf == null) {
            throw new IllegalStateException("null rowid. mRowIdColumnIndex = " + this.mRowIdColumnIndex);
        }
        synchronized (this.mUpdatedRows) {
            Map<String, Object> map = this.mUpdatedRows.get(lValueOf);
            if (map == null) {
                map = new HashMap<>();
                this.mUpdatedRows.put(lValueOf, map);
            }
            map.put(getColumnNames()[i9], obj);
        }
        return true;
    }

    public boolean updateBlob(int i9, byte[] bArr) {
        return update(i9, bArr);
    }

    public boolean updateDouble(int i9, double d9) {
        return update(i9, Double.valueOf(d9));
    }

    public boolean updateFloat(int i9, float f9) {
        return update(i9, Float.valueOf(f9));
    }

    public boolean updateInt(int i9, int i10) {
        return update(i9, Integer.valueOf(i10));
    }

    public boolean updateLong(int i9, long j9) {
        return update(i9, Long.valueOf(j9));
    }

    public boolean updateShort(int i9, short s8) {
        return update(i9, Short.valueOf(s8));
    }

    public boolean updateString(int i9, String str) {
        return update(i9, str);
    }

    public boolean updateToNull(int i9) {
        return update(i9, null);
    }
}
