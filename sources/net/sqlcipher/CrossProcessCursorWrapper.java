package net.sqlcipher;

import android.database.CrossProcessCursor;

/* loaded from: classes2.dex */
public class CrossProcessCursorWrapper extends CursorWrapper implements CrossProcessCursor {
    public CrossProcessCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override // android.database.CrossProcessCursor
    public void fillWindow(int i9, android.database.CursorWindow cursorWindow) {
        DatabaseUtils.cursorFillWindow(this, i9, cursorWindow);
    }

    @Override // android.database.CrossProcessCursor
    public android.database.CursorWindow getWindow() {
        return null;
    }

    @Override // android.database.CrossProcessCursor
    public boolean onMove(int i9, int i10) {
        return true;
    }
}
