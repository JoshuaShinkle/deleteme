package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes2.dex */
public final class ListenerHolder<L> {
    private final zaa zaa;
    private volatile L zab;
    private volatile ListenerKey<L> zac;

    @KeepForSdk
    public static final class ListenerKey<L> {
        private final L zaa;
        private final String zab;

        @KeepForSdk
        public ListenerKey(L l9, String str) {
            this.zaa = l9;
            this.zab = str;
        }

        @RecentlyNonNull
        @KeepForSdk
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zaa == listenerKey.zaa && this.zab.equals(listenerKey.zab);
        }

        @RecentlyNonNull
        @KeepForSdk
        public final int hashCode() {
            return (System.identityHashCode(this.zaa) * 31) + this.zab.hashCode();
        }
    }

    @KeepForSdk
    public interface Notifier<L> {
        @KeepForSdk
        void notifyListener(@RecentlyNonNull L l9);

        @KeepForSdk
        void onNotifyListenerFailed();
    }

    public final class zaa extends com.google.android.gms.internal.base.zap {
        public zaa(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Preconditions.checkArgument(message.what == 1);
            ListenerHolder.this.notifyListenerInternal((Notifier) message.obj);
        }
    }

    @KeepForSdk
    public ListenerHolder(Looper looper, L l9, String str) {
        this.zaa = new zaa(looper);
        this.zab = (L) Preconditions.checkNotNull(l9, "Listener must not be null");
        this.zac = new ListenerKey<>(l9, Preconditions.checkNotEmpty(str));
    }

    @KeepForSdk
    public final void clear() {
        this.zab = null;
        this.zac = null;
    }

    @RecentlyNullable
    @KeepForSdk
    public final ListenerKey<L> getListenerKey() {
        return this.zac;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final boolean hasListener() {
        return this.zab != null;
    }

    @KeepForSdk
    public final void notifyListener(@RecentlyNonNull Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zaa.sendMessage(this.zaa.obtainMessage(1, notifier));
    }

    @KeepForSdk
    public final void notifyListenerInternal(Notifier<? super L> notifier) {
        L l9 = this.zab;
        if (l9 == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(l9);
        } catch (RuntimeException e9) {
            notifier.onNotifyListenerFailed();
            throw e9;
        }
    }
}
