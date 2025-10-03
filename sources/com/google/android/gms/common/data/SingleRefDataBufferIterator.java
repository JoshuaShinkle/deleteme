package com.google.android.gms.common.data;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.NoSuchElementException;

@KeepForSdk
/* loaded from: classes2.dex */
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private T zac;

    public SingleRefDataBufferIterator(@RecentlyNonNull DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.DataBufferIterator, java.util.Iterator
    @RecentlyNonNull
    public T next() {
        if (!hasNext()) {
            int i9 = this.zab;
            StringBuilder sb = new StringBuilder(46);
            sb.append("Cannot advance the iterator beyond ");
            sb.append(i9);
            throw new NoSuchElementException(sb.toString());
        }
        int i10 = this.zab + 1;
        this.zab = i10;
        if (i10 == 0) {
            T t8 = (T) Preconditions.checkNotNull(this.zaa.get(0));
            this.zac = t8;
            if (!(t8 instanceof DataBufferRef)) {
                String strValueOf = String.valueOf(this.zac.getClass());
                StringBuilder sb2 = new StringBuilder(strValueOf.length() + 44);
                sb2.append("DataBuffer reference of type ");
                sb2.append(strValueOf);
                sb2.append(" is not movable");
                throw new IllegalStateException(sb2.toString());
            }
        } else {
            ((DataBufferRef) Preconditions.checkNotNull(this.zac)).zaa(this.zab);
        }
        return this.zac;
    }
}
