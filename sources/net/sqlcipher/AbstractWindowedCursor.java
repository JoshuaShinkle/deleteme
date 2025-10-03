package net.sqlcipher;

import android.database.CharArrayBuffer;

/* loaded from: classes2.dex */
public abstract class AbstractWindowedCursor extends AbstractCursor {
    protected CursorWindow mWindow;

    @Override // net.sqlcipher.AbstractCursor
    public void checkPosition() {
        super.checkPosition();
        if (this.mWindow == null) {
            throw new StaleDataException("Access closed cursor");
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public void copyStringToBuffer(int i9, CharArrayBuffer charArrayBuffer) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (isFieldUpdated(i9)) {
                super.copyStringToBuffer(i9, charArrayBuffer);
            }
        }
        this.mWindow.copyStringToBuffer(this.mPos, i9, charArrayBuffer);
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public byte[] getBlob(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.getBlob(this.mPos, i9);
            }
            return (byte[]) getUpdatedField(i9);
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public double getDouble(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.getDouble(this.mPos, i9);
            }
            return ((Number) getUpdatedField(i9)).doubleValue();
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public float getFloat(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.getFloat(this.mPos, i9);
            }
            return ((Number) getUpdatedField(i9)).floatValue();
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public int getInt(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.getInt(this.mPos, i9);
            }
            return ((Number) getUpdatedField(i9)).intValue();
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public long getLong(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.getLong(this.mPos, i9);
            }
            return ((Number) getUpdatedField(i9)).longValue();
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public short getShort(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.getShort(this.mPos, i9);
            }
            return ((Number) getUpdatedField(i9)).shortValue();
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public String getString(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.getString(this.mPos, i9);
            }
            return (String) getUpdatedField(i9);
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor, net.sqlcipher.Cursor
    public int getType(int i9) {
        checkPosition();
        return this.mWindow.getType(this.mPos, i9);
    }

    public boolean hasWindow() {
        return this.mWindow != null;
    }

    public boolean isBlob(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.isBlob(this.mPos, i9);
            }
            Object updatedField = getUpdatedField(i9);
            return updatedField == null || (updatedField instanceof byte[]);
        }
    }

    public boolean isFloat(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.isFloat(this.mPos, i9);
            }
            Object updatedField = getUpdatedField(i9);
            return updatedField != null && ((updatedField instanceof Float) || (updatedField instanceof Double));
        }
    }

    public boolean isLong(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.isLong(this.mPos, i9);
            }
            Object updatedField = getUpdatedField(i9);
            return updatedField != null && ((updatedField instanceof Integer) || (updatedField instanceof Long));
        }
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public boolean isNull(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (isFieldUpdated(i9)) {
                return getUpdatedField(i9) == null;
            }
            return this.mWindow.isNull(this.mPos, i9);
        }
    }

    public boolean isString(int i9) {
        checkPosition();
        synchronized (this.mUpdatedRows) {
            if (!isFieldUpdated(i9)) {
                return this.mWindow.isString(this.mPos, i9);
            }
            Object updatedField = getUpdatedField(i9);
            return updatedField == null || (updatedField instanceof String);
        }
    }

    public void setWindow(CursorWindow cursorWindow) {
        CursorWindow cursorWindow2 = this.mWindow;
        if (cursorWindow2 != null) {
            cursorWindow2.close();
        }
        this.mWindow = cursorWindow;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.CrossProcessCursor
    public CursorWindow getWindow() {
        return this.mWindow;
    }
}
