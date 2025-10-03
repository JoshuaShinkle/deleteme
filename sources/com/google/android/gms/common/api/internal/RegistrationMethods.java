package com.google.android.gms.common.api.internal;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes2.dex */
public class RegistrationMethods<A extends Api.AnyClient, L> {

    @RecentlyNonNull
    public final RegisterListenerMethod<A, L> zaa;

    @RecentlyNonNull
    public final UnregisterListenerMethod<A, L> zab;

    @RecentlyNonNull
    public final Runnable zac;

    @KeepForSdk
    public static class Builder<A extends Api.AnyClient, L> {
        private RemoteCall<A, TaskCompletionSource<Void>> zaa;
        private RemoteCall<A, TaskCompletionSource<Boolean>> zab;
        private Runnable zac;
        private ListenerHolder<L> zad;
        private Feature[] zae;
        private boolean zaf;

        private Builder() {
            this.zac = zabv.zaa;
            this.zaf = true;
        }

        @RecentlyNonNull
        @KeepForSdk
        public RegistrationMethods<A, L> build() {
            Preconditions.checkArgument(this.zaa != null, "Must set register function");
            Preconditions.checkArgument(this.zab != null, "Must set unregister function");
            Preconditions.checkArgument(this.zad != null, "Must set holder");
            return new RegistrationMethods<>(new zabw(this, this.zad, this.zae, this.zaf), new zaby(this, (ListenerHolder.ListenerKey) Preconditions.checkNotNull(this.zad.getListenerKey(), "Key must not be null")), this.zac);
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, L> onConnectionSuspended(@RecentlyNonNull Runnable runnable) {
            this.zac = runnable;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        @Deprecated
        public Builder<A, L> register(@RecentlyNonNull final BiConsumer<A, TaskCompletionSource<Void>> biConsumer) {
            this.zaa = new RemoteCall(biConsumer) { // from class: com.google.android.gms.common.api.internal.zabu
                private final BiConsumer zaa;

                {
                    this.zaa = biConsumer;
                }

                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) {
                    this.zaa.accept((Api.AnyClient) obj, (TaskCompletionSource) obj2);
                }
            };
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, L> setAutoResolveMissingFeatures(@RecentlyNonNull boolean z8) {
            this.zaf = z8;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, L> setFeatures(@RecentlyNonNull Feature... featureArr) {
            this.zae = featureArr;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        @Deprecated
        public Builder<A, L> unregister(@RecentlyNonNull BiConsumer<A, TaskCompletionSource<Boolean>> biConsumer) {
            this.zaa = new RemoteCall(this) { // from class: com.google.android.gms.common.api.internal.zabx
                private final RegistrationMethods.Builder zaa;

                {
                    this.zaa = this;
                }

                @Override // com.google.android.gms.common.api.internal.RemoteCall
                public final void accept(Object obj, Object obj2) {
                    this.zaa.zaa((Api.AnyClient) obj, (TaskCompletionSource) obj2);
                }
            };
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, L> withHolder(@RecentlyNonNull ListenerHolder<L> listenerHolder) {
            this.zad = listenerHolder;
            return this;
        }

        public final /* synthetic */ void zaa(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
            this.zaa.accept(anyClient, taskCompletionSource);
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, L> register(@RecentlyNonNull RemoteCall<A, TaskCompletionSource<Void>> remoteCall) {
            this.zaa = remoteCall;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, L> unregister(@RecentlyNonNull RemoteCall<A, TaskCompletionSource<Boolean>> remoteCall) {
            this.zab = remoteCall;
            return this;
        }
    }

    private RegistrationMethods(RegisterListenerMethod<A, L> registerListenerMethod, UnregisterListenerMethod<A, L> unregisterListenerMethod, Runnable runnable) {
        this.zaa = registerListenerMethod;
        this.zab = unregisterListenerMethod;
        this.zac = runnable;
    }

    @RecentlyNonNull
    @KeepForSdk
    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        return new Builder<>();
    }
}
