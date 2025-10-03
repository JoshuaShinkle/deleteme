package net.sqlcipher.database;

import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import net.sqlcipher.AbstractWindowedCursor;
import net.sqlcipher.CursorWindow;
import net.sqlcipher.SQLException;

/* loaded from: classes2.dex */
public class SQLiteCursor extends AbstractWindowedCursor {
    static final int NO_COUNT = -1;
    static final String TAG = "Cursor";
    private String[] mColumns;
    private SQLiteDatabase mDatabase;
    private SQLiteCursorDriver mDriver;
    private String mEditTable;
    protected MainThreadNotificationHandler mNotificationHandler;
    private SQLiteQuery mQuery;
    private int mCount = -1;
    private int mCursorWindowCapacity = 0;
    private boolean fillWindowForwardOnly = false;
    private int mMaxRead = Integer.MAX_VALUE;
    private int mInitialRead = Integer.MAX_VALUE;
    private int mCursorState = 0;
    private ReentrantLock mLock = null;
    private boolean mPendingData = false;
    private Throwable mStackTrace = new DatabaseObjectNotClosedException().fillInStackTrace();
    private Map<String, Integer> mColumnNameMap = null;

    public static class MainThreadNotificationHandler extends Handler {
        private final WeakReference<SQLiteCursor> wrappedCursor;

        public MainThreadNotificationHandler(SQLiteCursor sQLiteCursor) {
            this.wrappedCursor = new WeakReference<>(sQLiteCursor);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SQLiteCursor sQLiteCursor = this.wrappedCursor.get();
            if (sQLiteCursor != null) {
                sQLiteCursor.notifyDataSetChange();
            }
        }
    }

    public final class QueryThread implements Runnable {
        private final int mThreadState;

        public QueryThread(int i9) {
            this.mThreadState = i9;
        }

        private void sendMessage() {
            SQLiteCursor sQLiteCursor = SQLiteCursor.this;
            MainThreadNotificationHandler mainThreadNotificationHandler = sQLiteCursor.mNotificationHandler;
            if (mainThreadNotificationHandler == null) {
                sQLiteCursor.mPendingData = true;
            } else {
                mainThreadNotificationHandler.sendEmptyMessage(1);
                SQLiteCursor.this.mPendingData = false;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0070, code lost:
        
            r4.this$0.mCount = r1;
            sendMessage();
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() throws SecurityException, IllegalArgumentException {
            CursorWindow cursorWindow = ((AbstractWindowedCursor) SQLiteCursor.this).mWindow;
            Process.setThreadPriority(Process.myTid(), 10);
            while (true) {
                if (SQLiteCursor.this.mLock == null) {
                    SQLiteCursor.this.mLock = new ReentrantLock(true);
                }
                SQLiteCursor.this.mLock.lock();
                if (SQLiteCursor.this.mCursorState != this.mThreadState) {
                    SQLiteCursor.this.mLock.unlock();
                    return;
                }
                try {
                    int iFillWindow = SQLiteCursor.this.mQuery.fillWindow(cursorWindow, SQLiteCursor.this.mMaxRead, SQLiteCursor.this.mCount);
                    if (iFillWindow != 0) {
                        if (iFillWindow != -1) {
                            break;
                        }
                        SQLiteCursor sQLiteCursor = SQLiteCursor.this;
                        SQLiteCursor.access$512(sQLiteCursor, sQLiteCursor.mMaxRead);
                        sendMessage();
                        SQLiteCursor.this.mLock.unlock();
                    } else {
                        break;
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    SQLiteCursor.this.mLock.unlock();
                    throw th;
                }
            }
            SQLiteCursor.this.mLock.unlock();
        }
    }

    public SQLiteCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        this.mDatabase = sQLiteDatabase;
        this.mDriver = sQLiteCursorDriver;
        this.mEditTable = str;
        this.mQuery = sQLiteQuery;
        try {
            sQLiteDatabase.lock();
            int iColumnCountLocked = this.mQuery.columnCountLocked();
            this.mColumns = new String[iColumnCountLocked];
            for (int i9 = 0; i9 < iColumnCountLocked; i9++) {
                String strColumnNameLocked = this.mQuery.columnNameLocked(i9);
                this.mColumns[i9] = strColumnNameLocked;
                if ("_id".equals(strColumnNameLocked)) {
                    this.mRowIdColumnIndex = i9;
                }
            }
        } finally {
            sQLiteDatabase.unlock();
        }
    }

    public static /* synthetic */ int access$512(SQLiteCursor sQLiteCursor, int i9) {
        int i10 = sQLiteCursor.mCount + i9;
        sQLiteCursor.mCount = i10;
        return i10;
    }

    private void deactivateCommon() {
        this.mCursorState = 0;
        CursorWindow cursorWindow = this.mWindow;
        if (cursorWindow != null) {
            cursorWindow.close();
            this.mWindow = null;
        }
    }

    private void fillWindow(int i9) {
        if (this.mWindow == null) {
            this.mWindow = new CursorWindow(true);
        } else {
            this.mCursorState++;
            queryThreadLock();
            try {
                this.mWindow.clear();
            } finally {
                queryThreadUnlock();
            }
        }
        int iCursorPickFillWindowStartPosition = this.fillWindowForwardOnly ? i9 : this.mCount == -1 ? cursorPickFillWindowStartPosition(i9, 0) : cursorPickFillWindowStartPosition(i9, this.mCursorWindowCapacity);
        this.mWindow.setStartPosition(iCursorPickFillWindowStartPosition);
        this.mWindow.setRequiredPosition(i9);
        this.mCount = this.mQuery.fillWindow(this.mWindow, this.mInitialRead, 0);
        if (this.mCursorWindowCapacity == 0) {
            this.mCursorWindowCapacity = this.mWindow.getNumRows();
        }
        if (this.mCount == -1) {
            this.mCount = iCursorPickFillWindowStartPosition + this.mInitialRead;
            new Thread(new QueryThread(this.mCursorState), "query thread").start();
        }
    }

    private void queryThreadLock() {
        ReentrantLock reentrantLock = this.mLock;
        if (reentrantLock != null) {
            reentrantLock.lock();
        }
    }

    private void queryThreadUnlock() {
        ReentrantLock reentrantLock = this.mLock;
        if (reentrantLock != null) {
            reentrantLock.unlock();
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        deactivateCommon();
        this.mQuery.close();
        this.mDriver.cursorClosed();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b A[Catch: all -> 0x0128, DONT_GENERATE, TryCatch #1 {, blocks: (B:8:0x000d, B:9:0x0012, B:11:0x001b, B:13:0x001d, B:34:0x0112, B:35:0x011c, B:39:0x0122, B:40:0x0127, B:14:0x0022, B:15:0x0033, B:17:0x0039, B:20:0x004f, B:23:0x0056, B:24:0x0087, B:26:0x008d, B:28:0x00ad, B:29:0x00b2, B:30:0x00b5, B:31:0x00ee, B:32:0x010c, B:33:0x010d), top: B:45:0x000d, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001d A[Catch: all -> 0x0128, TRY_LEAVE, TryCatch #1 {, blocks: (B:8:0x000d, B:9:0x0012, B:11:0x001b, B:13:0x001d, B:34:0x0112, B:35:0x011c, B:39:0x0122, B:40:0x0127, B:14:0x0022, B:15:0x0033, B:17:0x0039, B:20:0x004f, B:23:0x0056, B:24:0x0087, B:26:0x008d, B:28:0x00ad, B:29:0x00b2, B:30:0x00b5, B:31:0x00ee, B:32:0x010c, B:33:0x010d), top: B:45:0x000d, inners: #0 }] */
    @Override // net.sqlcipher.AbstractCursor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean commitUpdates(Map<? extends Long, ? extends Map<String, Object>> map) {
        if (!supportsUpdates()) {
            return false;
        }
        synchronized (this.mUpdatedRows) {
            if (map != null) {
                this.mUpdatedRows.putAll(map);
                if (this.mUpdatedRows.size() != 0) {
                    return true;
                }
                this.mDatabase.beginTransaction();
                try {
                    StringBuilder sb = new StringBuilder(128);
                    for (Map.Entry<Long, Map<String, Object>> entry : this.mUpdatedRows.entrySet()) {
                        Map<String, Object> value = entry.getValue();
                        Long key = entry.getKey();
                        if (key == null || value == null) {
                            throw new IllegalStateException("null rowId or values found! rowId = " + key + ", values = " + value);
                        }
                        if (value.size() != 0) {
                            long jLongValue = key.longValue();
                            Iterator<Map.Entry<String, Object>> it = value.entrySet().iterator();
                            sb.setLength(0);
                            sb.append("UPDATE " + this.mEditTable + " SET ");
                            Object[] objArr = new Object[value.size()];
                            int i9 = 0;
                            while (it.hasNext()) {
                                Map.Entry<String, Object> next = it.next();
                                sb.append(next.getKey());
                                sb.append("=?");
                                objArr[i9] = next.getValue();
                                if (it.hasNext()) {
                                    sb.append(", ");
                                }
                                i9++;
                            }
                            sb.append(" WHERE " + this.mColumns[this.mRowIdColumnIndex] + '=' + jLongValue);
                            sb.append(';');
                            this.mDatabase.execSQL(sb.toString(), objArr);
                            this.mDatabase.rowUpdated(this.mEditTable, jLongValue);
                        }
                    }
                    this.mDatabase.setTransactionSuccessful();
                    this.mDatabase.endTransaction();
                    this.mUpdatedRows.clear();
                    onChange(true);
                    return true;
                } catch (Throwable th) {
                    this.mDatabase.endTransaction();
                    throw th;
                }
            }
            if (this.mUpdatedRows.size() != 0) {
            }
        }
    }

    public int cursorPickFillWindowStartPosition(int i9, int i10) {
        return Math.max(i9 - (i10 / 3), 0);
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public void deactivate() {
        super.deactivate();
        deactivateCommon();
        this.mDriver.cursorDeactivated();
    }

    @Override // net.sqlcipher.AbstractCursor
    public boolean deleteRow() {
        boolean z8;
        checkPosition();
        if (this.mRowIdColumnIndex == -1 || this.mCurrentRowID == null) {
            return false;
        }
        this.mDatabase.lock();
        try {
            try {
                this.mDatabase.delete(this.mEditTable, this.mColumns[this.mRowIdColumnIndex] + "=?", new String[]{this.mCurrentRowID.toString()});
                z8 = true;
            } catch (SQLException unused) {
                z8 = false;
            }
            int i9 = this.mPos;
            requery();
            moveToPosition(i9);
            if (!z8) {
                return false;
            }
            onChange(true);
            return true;
        } finally {
            this.mDatabase.unlock();
        }
    }

    @Override // net.sqlcipher.AbstractCursor
    public void finalize() {
        try {
            if (this.mWindow != null) {
                this.mQuery.mSql.length();
                close();
                SQLiteDebug.notifyActiveCursorFinalized();
            }
        } finally {
            super.finalize();
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public int getColumnIndex(String str) {
        if (this.mColumnNameMap == null) {
            String[] strArr = this.mColumns;
            int length = strArr.length;
            HashMap map = new HashMap(length, 1.0f);
            for (int i9 = 0; i9 < length; i9++) {
                map.put(strArr[i9], Integer.valueOf(i9));
            }
            this.mColumnNameMap = map;
        }
        if (str.lastIndexOf(46) != -1) {
            new Exception();
        }
        Integer num = this.mColumnNameMap.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.mColumns;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public int getCount() {
        if (this.mCount == -1) {
            fillWindow(0);
        }
        return this.mCount;
    }

    public SQLiteDatabase getDatabase() {
        return this.mDatabase;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.CrossProcessCursor
    public boolean onMove(int i9, int i10) {
        CursorWindow cursorWindow = this.mWindow;
        if (cursorWindow != null && i10 >= cursorWindow.getStartPosition() && i10 < this.mWindow.getStartPosition() + this.mWindow.getNumRows()) {
            return true;
        }
        fillWindow(i10);
        return true;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        super.registerDataSetObserver(dataSetObserver);
        if (!(Integer.MAX_VALUE == this.mMaxRead && Integer.MAX_VALUE == this.mInitialRead) && this.mNotificationHandler == null) {
            queryThreadLock();
            try {
                this.mNotificationHandler = new MainThreadNotificationHandler(this);
                if (this.mPendingData) {
                    notifyDataSetChange();
                    this.mPendingData = false;
                }
            } finally {
                queryThreadUnlock();
            }
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public boolean requery() {
        if (isClosed()) {
            return false;
        }
        this.mDatabase.lock();
        try {
            CursorWindow cursorWindow = this.mWindow;
            if (cursorWindow != null) {
                cursorWindow.clear();
            }
            this.mPos = -1;
            this.mDriver.cursorRequeried(this);
            this.mCount = -1;
            this.mCursorState++;
            queryThreadLock();
            try {
                this.mQuery.requery();
                this.mDatabase.unlock();
                return super.requery();
            } finally {
                queryThreadUnlock();
            }
        } catch (Throwable th) {
            this.mDatabase.unlock();
            throw th;
        }
    }

    public void setFillWindowForwardOnly(boolean z8) {
        this.fillWindowForwardOnly = z8;
    }

    public void setLoadStyle(int i9, int i10) {
        this.mMaxRead = i10;
        this.mInitialRead = i9;
        this.mLock = new ReentrantLock(true);
    }

    public void setSelectionArguments(String[] strArr) {
        this.mDriver.setBindArguments(strArr);
    }

    @Override // net.sqlcipher.AbstractWindowedCursor
    public void setWindow(CursorWindow cursorWindow) {
        if (this.mWindow != null) {
            this.mCursorState++;
            queryThreadLock();
            try {
                this.mWindow.close();
                queryThreadUnlock();
                this.mCount = -1;
            } catch (Throwable th) {
                queryThreadUnlock();
                throw th;
            }
        }
        this.mWindow = cursorWindow;
    }

    @Override // net.sqlcipher.AbstractCursor
    public boolean supportsUpdates() {
        return !TextUtils.isEmpty(this.mEditTable);
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.CrossProcessCursor
    public void fillWindow(int i9, android.database.CursorWindow cursorWindow) {
        int iCursorPickFillWindowStartPosition;
        if (this.mWindow == null) {
            this.mWindow = new CursorWindow(true);
        } else {
            this.mCursorState++;
            queryThreadLock();
            try {
                this.mWindow.clear();
            } finally {
                queryThreadUnlock();
            }
        }
        if (this.fillWindowForwardOnly) {
            iCursorPickFillWindowStartPosition = i9;
        } else if (this.mCount == -1) {
            iCursorPickFillWindowStartPosition = cursorPickFillWindowStartPosition(i9, 0);
        } else {
            iCursorPickFillWindowStartPosition = cursorPickFillWindowStartPosition(i9, this.mCursorWindowCapacity);
        }
        this.mWindow.setStartPosition(iCursorPickFillWindowStartPosition);
        this.mWindow.setRequiredPosition(i9);
        this.mCount = this.mQuery.fillWindow(this.mWindow, this.mInitialRead, 0);
        if (this.mCursorWindowCapacity == 0) {
            this.mCursorWindowCapacity = this.mWindow.getNumRows();
        }
        if (this.mCount == -1) {
            this.mCount = iCursorPickFillWindowStartPosition + this.mInitialRead;
            new Thread(new QueryThread(this.mCursorState), "query thread").start();
        }
    }
}
