package net.sqlcipher.database;

/* loaded from: classes2.dex */
public abstract class SQLiteClosable {
    private int mReferenceCount = 1;
    private Object mLock = new Object();

    private String getObjInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" (");
        if (this instanceof SQLiteDatabase) {
            sb.append("database = ");
            sb.append(((SQLiteDatabase) this).getPath());
        } else if ((this instanceof SQLiteProgram) || (this instanceof SQLiteStatement) || (this instanceof SQLiteQuery)) {
            sb.append("mSql = ");
            sb.append(((SQLiteProgram) this).mSql);
        }
        sb.append(") ");
        return sb.toString();
    }

    public void acquireReference() {
        synchronized (this.mLock) {
            int i9 = this.mReferenceCount;
            if (i9 <= 0) {
                throw new IllegalStateException("attempt to re-open an already-closed object: " + getObjInfo());
            }
            this.mReferenceCount = i9 + 1;
        }
    }

    public abstract void onAllReferencesReleased();

    public void onAllReferencesReleasedFromContainer() {
    }

    public void releaseReference() {
        synchronized (this.mLock) {
            int i9 = this.mReferenceCount - 1;
            this.mReferenceCount = i9;
            if (i9 == 0) {
                onAllReferencesReleased();
            }
        }
    }

    public void releaseReferenceFromContainer() {
        synchronized (this.mLock) {
            int i9 = this.mReferenceCount - 1;
            this.mReferenceCount = i9;
            if (i9 == 0) {
                onAllReferencesReleasedFromContainer();
            }
        }
    }
}
