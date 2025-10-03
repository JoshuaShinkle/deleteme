package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

@KeepForSdk
/* loaded from: classes2.dex */
public class ListenerHolders {
    private final Set<ListenerHolder<?>> zaa = Collections.newSetFromMap(new WeakHashMap());

    @RecentlyNonNull
    @KeepForSdk
    public static <L> ListenerHolder<L> createListenerHolder(@RecentlyNonNull L l9, @RecentlyNonNull Looper looper, @RecentlyNonNull String str) {
        Preconditions.checkNotNull(l9, "Listener must not be null");
        Preconditions.checkNotNull(looper, "Looper must not be null");
        Preconditions.checkNotNull(str, "Listener type must not be null");
        return new ListenerHolder<>(looper, l9, str);
    }

    @RecentlyNonNull
    @KeepForSdk
    public static <L> ListenerHolder.ListenerKey<L> createListenerKey(@RecentlyNonNull L l9, @RecentlyNonNull String str) {
        Preconditions.checkNotNull(l9, "Listener must not be null");
        Preconditions.checkNotNull(str, "Listener type must not be null");
        Preconditions.checkNotEmpty(str, "Listener type must not be empty");
        return new ListenerHolder.ListenerKey<>(l9, str);
    }

    @RecentlyNonNull
    public final <L> ListenerHolder<L> zaa(@RecentlyNonNull L l9, @RecentlyNonNull Looper looper, @RecentlyNonNull String str) {
        ListenerHolder<L> listenerHolderCreateListenerHolder = createListenerHolder(l9, looper, str);
        this.zaa.add(listenerHolderCreateListenerHolder);
        return listenerHolderCreateListenerHolder;
    }

    public final void zaa() {
        Iterator<ListenerHolder<?>> it = this.zaa.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.zaa.clear();
    }
}
