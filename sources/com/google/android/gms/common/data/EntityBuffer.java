package com.google.android.gms.common.data;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zaa;
    private ArrayList<Integer> zab;

    @KeepForSdk
    public EntityBuffer(@RecentlyNonNull DataHolder dataHolder) {
        super(dataHolder);
        this.zaa = false;
    }

    private final void zaa() {
        synchronized (this) {
            if (!this.zaa) {
                int count = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();
                ArrayList<Integer> arrayList = new ArrayList<>();
                this.zab = arrayList;
                if (count > 0) {
                    arrayList.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    for (int i9 = 1; i9 < count; i9++) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i9);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i9, windowIndex);
                        if (string2 == null) {
                            StringBuilder sb = new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(primaryDataMarkerColumn);
                            sb.append(", at row: ");
                            sb.append(i9);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                        if (!string2.equals(string)) {
                            this.zab.add(Integer.valueOf(i9));
                            string = string2;
                        }
                    }
                }
                this.zaa = true;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0072  */
    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final T get(@RecentlyNonNull int i9) {
        int iIntValue;
        int iIntValue2;
        zaa();
        int iZaa = zaa(i9);
        int i10 = 0;
        if (i9 >= 0 && i9 != this.zab.size()) {
            if (i9 == this.zab.size() - 1) {
                iIntValue = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();
                iIntValue2 = this.zab.get(i9).intValue();
            } else {
                iIntValue = this.zab.get(i9 + 1).intValue();
                iIntValue2 = this.zab.get(i9).intValue();
            }
            int i11 = iIntValue - iIntValue2;
            if (i11 == 1) {
                int iZaa2 = zaa(i9);
                int windowIndex = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getWindowIndex(iZaa2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn == null || this.mDataHolder.getString(childDataMarkerColumn, iZaa2, windowIndex) != null) {
                    i10 = i11;
                }
            }
        }
        return getEntry(iZaa, i10);
    }

    @RecentlyNullable
    @KeepForSdk
    public String getChildDataMarkerColumn() {
        return null;
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @RecentlyNonNull
    @KeepForSdk
    public int getCount() {
        zaa();
        return this.zab.size();
    }

    @RecentlyNonNull
    @KeepForSdk
    public abstract T getEntry(@RecentlyNonNull int i9, @RecentlyNonNull int i10);

    @RecentlyNonNull
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    private final int zaa(int i9) {
        if (i9 >= 0 && i9 < this.zab.size()) {
            return this.zab.get(i9).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i9);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }
}
