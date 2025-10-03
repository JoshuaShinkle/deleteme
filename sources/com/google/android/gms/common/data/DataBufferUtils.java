package com.google.android.gms.common.data;

import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.select.Elements;

/* loaded from: classes2.dex */
public final class DataBufferUtils {

    @RecentlyNonNull
    @KeepForSdk
    public static final String KEY_NEXT_PAGE_TOKEN = "next_page_token";

    @RecentlyNonNull
    @KeepForSdk
    public static final String KEY_PREV_PAGE_TOKEN = "prev_page_token";

    private DataBufferUtils() {
    }

    @RecentlyNonNull
    public static <T, E extends Freezable<T>> ArrayList<T> freezeAndClose(@RecentlyNonNull DataBuffer<E> dataBuffer) {
        Elements elements = (ArrayList<T>) new ArrayList(dataBuffer.getCount());
        try {
            Iterator<E> it = dataBuffer.iterator();
            while (it.hasNext()) {
                elements.add(it.next().freeze());
            }
            return elements;
        } finally {
            dataBuffer.close();
        }
    }

    @RecentlyNonNull
    public static boolean hasData(@RecentlyNonNull DataBuffer<?> dataBuffer) {
        return dataBuffer != null && dataBuffer.getCount() > 0;
    }

    @RecentlyNonNull
    public static boolean hasNextPage(@RecentlyNonNull DataBuffer<?> dataBuffer) {
        Bundle metadata = dataBuffer.getMetadata();
        return (metadata == null || metadata.getString(KEY_NEXT_PAGE_TOKEN) == null) ? false : true;
    }

    @RecentlyNonNull
    public static boolean hasPrevPage(@RecentlyNonNull DataBuffer<?> dataBuffer) {
        Bundle metadata = dataBuffer.getMetadata();
        return (metadata == null || metadata.getString(KEY_PREV_PAGE_TOKEN) == null) ? false : true;
    }
}
