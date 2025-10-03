package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
@KeepName
@SafeParcelable.Class(creator = "DataHolderCreator", validate = true)
/* loaded from: classes2.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {

    @RecentlyNonNull
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zac();
    private static final Builder zak = new zab(new String[0], null);

    @SafeParcelable.VersionField(m17523id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int zaa;

    @SafeParcelable.Field(getter = "getColumns", m17520id = 1)
    private final String[] zab;
    private Bundle zac;

    @SafeParcelable.Field(getter = "getWindows", m17520id = 2)
    private final CursorWindow[] zad;

    @SafeParcelable.Field(getter = "getStatusCode", m17520id = 3)
    private final int zae;

    @SafeParcelable.Field(getter = "getMetadata", m17520id = 4)
    private final Bundle zaf;
    private int[] zag;
    private int zah;
    private boolean zai;
    private boolean zaj;

    @KeepForSdk
    public static class Builder {
        private final String[] zaa;
        private final ArrayList<HashMap<String, Object>> zab;
        private final String zac;
        private final HashMap<Object, Integer> zad;
        private boolean zae;
        private String zaf;

        private Builder(String[] strArr, String str) {
            this.zaa = (String[]) Preconditions.checkNotNull(strArr);
            this.zab = new ArrayList<>();
            this.zac = null;
            this.zad = new HashMap<>();
            this.zae = false;
            this.zaf = null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @RecentlyNonNull
        @KeepForSdk
        public DataHolder build(@RecentlyNonNull int i9) {
            return new DataHolder(this, i9, (Bundle) null, (zab) (0 == true ? 1 : 0));
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder withRow(@RecentlyNonNull ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap<String, Object> map = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return zaa(map);
        }

        @RecentlyNonNull
        public Builder zaa(@RecentlyNonNull HashMap<String, Object> map) {
            Object obj;
            int iIntValue;
            Asserts.checkNotNull(map);
            String str = this.zac;
            if (str == null || (obj = map.get(str)) == null) {
                iIntValue = -1;
            } else {
                Integer num = this.zad.get(obj);
                if (num == null) {
                    this.zad.put(obj, Integer.valueOf(this.zab.size()));
                    iIntValue = -1;
                } else {
                    iIntValue = num.intValue();
                }
            }
            if (iIntValue == -1) {
                this.zab.add(map);
            } else {
                this.zab.remove(iIntValue);
                this.zab.add(iIntValue, map);
            }
            this.zae = false;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public DataHolder build(@RecentlyNonNull int i9, @RecentlyNonNull Bundle bundle) {
            return new DataHolder(this, i9, bundle, -1, (zab) null);
        }

        public /* synthetic */ Builder(String[] strArr, String str, zab zabVar) {
            this(strArr, null);
        }
    }

    public static class zaa extends RuntimeException {
        public zaa(String str) {
            super(str);
        }
    }

    @SafeParcelable.Constructor
    public DataHolder(@SafeParcelable.Param(m17521id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i9, @SafeParcelable.Param(m17521id = 1) String[] strArr, @SafeParcelable.Param(m17521id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(m17521id = 3) int i10, @SafeParcelable.Param(m17521id = 4) Bundle bundle) {
        this.zai = false;
        this.zaj = true;
        this.zaa = i9;
        this.zab = strArr;
        this.zad = cursorWindowArr;
        this.zae = i10;
        this.zaf = bundle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @RecentlyNonNull
    @KeepForSdk
    public static Builder builder(@RecentlyNonNull String[] strArr) {
        return new Builder(strArr, null, 0 == true ? 1 : 0);
    }

    @RecentlyNonNull
    @KeepForSdk
    public static DataHolder empty(@RecentlyNonNull int i9) {
        return new DataHolder(zak, i9, (Bundle) null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @KeepForSdk
    public final void close() {
        synchronized (this) {
            if (!this.zai) {
                this.zai = true;
                int i9 = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.zad;
                    if (i9 >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i9].close();
                    i9++;
                }
            }
        }
    }

    public final void finalize() throws Throwable {
        try {
            if (this.zaj && this.zad.length > 0 && !isClosed()) {
                close();
                String string = toString();
                StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 178);
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(string);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    @RecentlyNonNull
    @KeepForSdk
    public final boolean getBoolean(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return Long.valueOf(this.zad[i10].getLong(i9, this.zac.getInt(str))).longValue() == 1;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final byte[] getByteArray(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return this.zad[i10].getBlob(i9, this.zac.getInt(str));
    }

    @RecentlyNonNull
    @KeepForSdk
    public final int getCount() {
        return this.zah;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final int getInteger(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return this.zad[i10].getInt(i9, this.zac.getInt(str));
    }

    @RecentlyNonNull
    @KeepForSdk
    public final long getLong(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return this.zad[i10].getLong(i9, this.zac.getInt(str));
    }

    @RecentlyNullable
    @KeepForSdk
    public final Bundle getMetadata() {
        return this.zaf;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final int getStatusCode() {
        return this.zae;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final String getString(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return this.zad[i10].getString(i9, this.zac.getInt(str));
    }

    @RecentlyNonNull
    @KeepForSdk
    public final int getWindowIndex(@RecentlyNonNull int i9) {
        int[] iArr;
        int i10 = 0;
        Preconditions.checkState(i9 >= 0 && i9 < this.zah);
        while (true) {
            iArr = this.zag;
            if (i10 >= iArr.length) {
                break;
            }
            if (i9 < iArr[i10]) {
                i10--;
                break;
            }
            i10++;
        }
        return i10 == iArr.length ? i10 - 1 : i10;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final boolean hasColumn(@RecentlyNonNull String str) {
        return this.zac.containsKey(str);
    }

    @RecentlyNonNull
    @KeepForSdk
    public final boolean hasNull(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return this.zad[i10].isNull(i9, this.zac.getInt(str));
    }

    @RecentlyNonNull
    @KeepForSdk
    public final boolean isClosed() {
        boolean z8;
        synchronized (this) {
            z8 = this.zai;
        }
        return z8;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zab, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zad, i9, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.zaa);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        if ((i9 & 1) != 0) {
            close();
        }
    }

    public final void zaa() {
        this.zac = new Bundle();
        int i9 = 0;
        int i10 = 0;
        while (true) {
            String[] strArr = this.zab;
            if (i10 >= strArr.length) {
                break;
            }
            this.zac.putInt(strArr[i10], i10);
            i10++;
        }
        this.zag = new int[this.zad.length];
        int numRows = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zad;
            if (i9 >= cursorWindowArr.length) {
                this.zah = numRows;
                return;
            }
            this.zag[i9] = numRows;
            numRows += this.zad[i9].getNumRows() - (numRows - cursorWindowArr[i9].getStartPosition());
            i9++;
        }
    }

    @RecentlyNonNull
    public final double zab(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return this.zad[i10].getDouble(i9, this.zac.getInt(str));
    }

    @KeepForSdk
    public DataHolder(@RecentlyNonNull String[] strArr, @RecentlyNonNull CursorWindow[] cursorWindowArr, @RecentlyNonNull int i9, Bundle bundle) {
        this.zai = false;
        this.zaj = true;
        this.zaa = 1;
        this.zab = (String[]) Preconditions.checkNotNull(strArr);
        this.zad = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zae = i9;
        this.zaf = bundle;
        zaa();
    }

    private static CursorWindow[] zaa(CursorWrapper cursorWrapper) {
        int startPosition;
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                startPosition = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                startPosition = window.getNumRows();
            }
            while (startPosition < count) {
                if (!cursorWrapper.moveToPosition(startPosition)) {
                    break;
                }
                CursorWindow window2 = cursorWrapper.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(startPosition);
                    cursorWrapper.fillWindow(startPosition, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                startPosition = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    private DataHolder(CursorWrapper cursorWrapper, int i9, Bundle bundle) {
        this(cursorWrapper.getColumnNames(), zaa(cursorWrapper), i9, bundle);
    }

    @KeepForSdk
    public DataHolder(@RecentlyNonNull Cursor cursor, @RecentlyNonNull int i9, Bundle bundle) {
        this(new CursorWrapper(cursor), i9, bundle);
    }

    private DataHolder(Builder builder, int i9, Bundle bundle) {
        this(builder.zaa, zaa(builder, -1), i9, (Bundle) null);
    }

    private DataHolder(Builder builder, int i9, Bundle bundle, int i10) {
        this(builder.zaa, zaa(builder, -1), i9, bundle);
    }

    public /* synthetic */ DataHolder(Builder builder, int i9, Bundle bundle, zab zabVar) {
        this(builder, i9, (Bundle) null);
    }

    public /* synthetic */ DataHolder(Builder builder, int i9, Bundle bundle, int i10, zab zabVar) {
        this(builder, i9, bundle, -1);
    }

    private static CursorWindow[] zaa(Builder builder, int i9) {
        if (builder.zaa.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList arrayList = builder.zab;
        int size = arrayList.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zaa.length);
        int i10 = 0;
        boolean z8 = false;
        while (i10 < size) {
            try {
                if (!cursorWindow.allocRow()) {
                    StringBuilder sb = new StringBuilder(72);
                    sb.append("Allocating additional cursor window for large data set (row ");
                    sb.append(i10);
                    sb.append(")");
                    Log.d("DataHolder", sb.toString());
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i10);
                    cursorWindow.setNumColumns(builder.zaa.length);
                    arrayList2.add(cursorWindow);
                    if (!cursorWindow.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList2.remove(cursorWindow);
                        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
                    }
                }
                Map map = (Map) arrayList.get(i10);
                boolean zPutDouble = true;
                for (int i11 = 0; i11 < builder.zaa.length && zPutDouble; i11++) {
                    String str = builder.zaa[i11];
                    Object obj = map.get(str);
                    if (obj == null) {
                        zPutDouble = cursorWindow.putNull(i10, i11);
                    } else if (obj instanceof String) {
                        zPutDouble = cursorWindow.putString((String) obj, i10, i11);
                    } else if (obj instanceof Long) {
                        zPutDouble = cursorWindow.putLong(((Long) obj).longValue(), i10, i11);
                    } else if (obj instanceof Integer) {
                        zPutDouble = cursorWindow.putLong(((Integer) obj).intValue(), i10, i11);
                    } else if (obj instanceof Boolean) {
                        zPutDouble = cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i10, i11);
                    } else if (obj instanceof byte[]) {
                        zPutDouble = cursorWindow.putBlob((byte[]) obj, i10, i11);
                    } else if (obj instanceof Double) {
                        zPutDouble = cursorWindow.putDouble(((Double) obj).doubleValue(), i10, i11);
                    } else if (obj instanceof Float) {
                        zPutDouble = cursorWindow.putDouble(((Float) obj).floatValue(), i10, i11);
                    } else {
                        String strValueOf = String.valueOf(obj);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 32 + strValueOf.length());
                        sb2.append("Unsupported object for column ");
                        sb2.append(str);
                        sb2.append(": ");
                        sb2.append(strValueOf);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                }
                if (zPutDouble) {
                    z8 = false;
                } else if (!z8) {
                    StringBuilder sb3 = new StringBuilder(74);
                    sb3.append("Couldn't populate window data for row ");
                    sb3.append(i10);
                    sb3.append(" - allocating new window.");
                    Log.d("DataHolder", sb3.toString());
                    cursorWindow.freeLastRow();
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i10);
                    cursorWindow.setNumColumns(builder.zaa.length);
                    arrayList2.add(cursorWindow);
                    i10--;
                    z8 = true;
                } else {
                    throw new zaa("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                }
                i10++;
            } catch (RuntimeException e9) {
                int size2 = arrayList2.size();
                for (int i12 = 0; i12 < size2; i12++) {
                    ((CursorWindow) arrayList2.get(i12)).close();
                }
                throw e9;
            }
        }
        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
    }

    private final void zaa(String str, int i9) {
        Bundle bundle = this.zac;
        if (bundle != null && bundle.containsKey(str)) {
            if (!isClosed()) {
                if (i9 < 0 || i9 >= this.zah) {
                    throw new CursorIndexOutOfBoundsException(i9, this.zah);
                }
                return;
            }
            throw new IllegalArgumentException("Buffer is closed.");
        }
        String strValueOf = String.valueOf(str);
        throw new IllegalArgumentException(strValueOf.length() != 0 ? "No such column: ".concat(strValueOf) : new String("No such column: "));
    }

    @RecentlyNonNull
    public final float zaa(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        zaa(str, i9);
        return this.zad[i10].getFloat(i9, this.zac.getInt(str));
    }

    public final void zaa(@RecentlyNonNull String str, @RecentlyNonNull int i9, @RecentlyNonNull int i10, @RecentlyNonNull CharArrayBuffer charArrayBuffer) {
        zaa(str, i9);
        this.zad[i10].copyStringToBuffer(i9, this.zac.getInt(str), charArrayBuffer);
    }
}
