package net.sqlcipher;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MatrixCursor extends AbstractCursor {
    private final int columnCount;
    private final String[] columnNames;
    private Object[] data;
    private int rowCount;

    public class RowBuilder {
        private final int endIndex;
        private int index;

        public RowBuilder(int i9, int i10) {
            this.index = i9;
            this.endIndex = i10;
        }

        public RowBuilder add(Object obj) {
            if (this.index == this.endIndex) {
                throw new CursorIndexOutOfBoundsException("No more columns left.");
            }
            Object[] objArr = MatrixCursor.this.data;
            int i9 = this.index;
            this.index = i9 + 1;
            objArr[i9] = obj;
            return this;
        }
    }

    public MatrixCursor(String[] strArr, int i9) {
        this.rowCount = 0;
        this.columnNames = strArr;
        int length = strArr.length;
        this.columnCount = length;
        this.data = new Object[length * (i9 < 1 ? 1 : i9)];
    }

    private void ensureCapacity(int i9) {
        Object[] objArr = this.data;
        if (i9 > objArr.length) {
            int length = objArr.length * 2;
            if (length >= i9) {
                i9 = length;
            }
            Object[] objArr2 = new Object[i9];
            this.data = objArr2;
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        }
    }

    private Object get(int i9) {
        int i10;
        if (i9 < 0 || i9 >= (i10 = this.columnCount)) {
            throw new CursorIndexOutOfBoundsException("Requested column: " + i9 + ", # of columns: " + this.columnCount);
        }
        int i11 = this.mPos;
        if (i11 < 0) {
            throw new CursorIndexOutOfBoundsException("Before first row.");
        }
        if (i11 < this.rowCount) {
            return this.data[(i11 * i10) + i9];
        }
        throw new CursorIndexOutOfBoundsException("After last row.");
    }

    public void addRow(Object[] objArr) {
        int length = objArr.length;
        int i9 = this.columnCount;
        if (length == i9) {
            int i10 = this.rowCount;
            this.rowCount = i10 + 1;
            int i11 = i10 * i9;
            ensureCapacity(i9 + i11);
            System.arraycopy(objArr, 0, this.data, i11, this.columnCount);
            return;
        }
        throw new IllegalArgumentException("columnNames.length = " + this.columnCount + ", columnValues.length = " + objArr.length);
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.columnNames;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public int getCount() {
        return this.rowCount;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public double getDouble(int i9) {
        Object obj = get(i9);
        return obj == null ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : obj instanceof Number ? ((Number) obj).doubleValue() : Double.parseDouble(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public float getFloat(int i9) {
        Object obj = get(i9);
        return obj == null ? BitmapDescriptorFactory.HUE_RED : obj instanceof Number ? ((Number) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public int getInt(int i9) {
        Object obj = get(i9);
        if (obj == null) {
            return 0;
        }
        return obj instanceof Number ? ((Number) obj).intValue() : Integer.parseInt(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public long getLong(int i9) {
        Object obj = get(i9);
        if (obj == null) {
            return 0L;
        }
        return obj instanceof Number ? ((Number) obj).longValue() : Long.parseLong(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public short getShort(int i9) {
        Object obj = get(i9);
        if (obj == null) {
            return (short) 0;
        }
        return obj instanceof Number ? ((Number) obj).shortValue() : Short.parseShort(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public String getString(int i9) {
        Object obj = get(i9);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor, net.sqlcipher.Cursor
    public int getType(int i9) {
        return DatabaseUtils.getTypeOfObject(get(i9));
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public boolean isNull(int i9) {
        return get(i9) == null;
    }

    public RowBuilder newRow() {
        int i9 = this.rowCount + 1;
        this.rowCount = i9;
        int i10 = i9 * this.columnCount;
        ensureCapacity(i10);
        return new RowBuilder(i10 - this.columnCount, i10);
    }

    public MatrixCursor(String[] strArr) {
        this(strArr, 16);
    }

    public void addRow(Iterable<?> iterable) {
        int i9 = this.rowCount;
        int i10 = this.columnCount;
        int i11 = i9 * i10;
        int i12 = i10 + i11;
        ensureCapacity(i12);
        if (iterable instanceof ArrayList) {
            addRow((ArrayList) iterable, i11);
            return;
        }
        Object[] objArr = this.data;
        for (Object obj : iterable) {
            if (i11 != i12) {
                objArr[i11] = obj;
                i11++;
            } else {
                throw new IllegalArgumentException("columnValues.size() > columnNames.length");
            }
        }
        if (i11 == i12) {
            this.rowCount++;
            return;
        }
        throw new IllegalArgumentException("columnValues.size() < columnNames.length");
    }

    private void addRow(ArrayList<?> arrayList, int i9) {
        int size = arrayList.size();
        if (size == this.columnCount) {
            this.rowCount++;
            Object[] objArr = this.data;
            for (int i10 = 0; i10 < size; i10++) {
                objArr[i9 + i10] = arrayList.get(i10);
            }
            return;
        }
        throw new IllegalArgumentException("columnNames.length = " + this.columnCount + ", columnValues.size() = " + size);
    }
}
