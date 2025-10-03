package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
/* loaded from: classes2.dex */
public class DataBufferSafeParcelable<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zaa = {"data"};
    private final Parcelable.Creator<T> zab;

    @KeepForSdk
    public DataBufferSafeParcelable(@RecentlyNonNull DataHolder dataHolder, @RecentlyNonNull Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.zab = creator;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void addValue(@RecentlyNonNull DataHolder.Builder builder, @RecentlyNonNull T t8) {
        Parcel parcelObtain = Parcel.obtain();
        t8.writeToParcel(parcelObtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", parcelObtain.marshall());
        builder.withRow(contentValues);
        parcelObtain.recycle();
    }

    @RecentlyNonNull
    @KeepForSdk
    public static DataHolder.Builder buildDataHolder() {
        return DataHolder.builder(zaa);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    @KeepForSdk
    public T get(@RecentlyNonNull int i9) {
        DataHolder dataHolder = (DataHolder) Preconditions.checkNotNull(this.mDataHolder);
        byte[] byteArray = dataHolder.getByteArray("data", i9, dataHolder.getWindowIndex(i9));
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(byteArray, 0, byteArray.length);
        parcelObtain.setDataPosition(0);
        T tCreateFromParcel = this.zab.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tCreateFromParcel;
    }
}
