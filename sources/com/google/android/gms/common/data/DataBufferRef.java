package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes2.dex */
public class DataBufferRef {

    @RecentlyNonNull
    @KeepForSdk
    protected final DataHolder mDataHolder;

    @RecentlyNonNull
    @KeepForSdk
    protected int mDataRow;
    private int zaa;

    @KeepForSdk
    public DataBufferRef(@RecentlyNonNull DataHolder dataHolder, @RecentlyNonNull int i9) {
        this.mDataHolder = (DataHolder) Preconditions.checkNotNull(dataHolder);
        zaa(i9);
    }

    @KeepForSdk
    public void copyToBuffer(@RecentlyNonNull String str, @RecentlyNonNull CharArrayBuffer charArrayBuffer) {
        this.mDataHolder.zaa(str, this.mDataRow, this.zaa, charArrayBuffer);
    }

    @RecentlyNonNull
    public boolean equals(Object obj) {
        if (obj instanceof DataBufferRef) {
            DataBufferRef dataBufferRef = (DataBufferRef) obj;
            if (Objects.equal(Integer.valueOf(dataBufferRef.mDataRow), Integer.valueOf(this.mDataRow)) && Objects.equal(Integer.valueOf(dataBufferRef.zaa), Integer.valueOf(this.zaa)) && dataBufferRef.mDataHolder == this.mDataHolder) {
                return true;
            }
        }
        return false;
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean getBoolean(@RecentlyNonNull String str) {
        return this.mDataHolder.getBoolean(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public byte[] getByteArray(@RecentlyNonNull String str) {
        return this.mDataHolder.getByteArray(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public int getDataRow() {
        return this.mDataRow;
    }

    @RecentlyNonNull
    @KeepForSdk
    public double getDouble(@RecentlyNonNull String str) {
        return this.mDataHolder.zab(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public float getFloat(@RecentlyNonNull String str) {
        return this.mDataHolder.zaa(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public int getInteger(@RecentlyNonNull String str) {
        return this.mDataHolder.getInteger(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public long getLong(@RecentlyNonNull String str) {
        return this.mDataHolder.getLong(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public String getString(@RecentlyNonNull String str) {
        return this.mDataHolder.getString(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean hasColumn(@RecentlyNonNull String str) {
        return this.mDataHolder.hasColumn(str);
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean hasNull(@RecentlyNonNull String str) {
        return this.mDataHolder.hasNull(str, this.mDataRow, this.zaa);
    }

    @RecentlyNonNull
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mDataRow), Integer.valueOf(this.zaa), this.mDataHolder);
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    @RecentlyNullable
    @KeepForSdk
    public Uri parseUri(@RecentlyNonNull String str) {
        String string = this.mDataHolder.getString(str, this.mDataRow, this.zaa);
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public final void zaa(@RecentlyNonNull int i9) {
        Preconditions.checkState(i9 >= 0 && i9 < this.mDataHolder.getCount());
        this.mDataRow = i9;
        this.zaa = this.mDataHolder.getWindowIndex(i9);
    }
}
