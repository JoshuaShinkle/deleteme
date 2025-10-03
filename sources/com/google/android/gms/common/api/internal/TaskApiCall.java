package com.google.android.gms.common.api.internal;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
    private final Feature[] zaa;
    private final boolean zab;
    private final int zac;

    @KeepForSdk
    public static class Builder<A extends Api.AnyClient, ResultT> {
        private RemoteCall<A, TaskCompletionSource<ResultT>> zaa;
        private boolean zab;
        private Feature[] zac;
        private int zad;

        private Builder() {
            this.zab = true;
            this.zad = 0;
        }

        @RecentlyNonNull
        @KeepForSdk
        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(this.zaa != null, "execute parameter required");
            return new zacg(this, this.zac, this.zab, this.zad);
        }

        @RecentlyNonNull
        @KeepForSdk
        @Deprecated
        public Builder<A, ResultT> execute(@RecentlyNonNull final BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
            this.zaa = new RemoteCall(biConsumer) { // from class: com.google.android.gms.common.api.internal.zach
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
        public Builder<A, ResultT> run(@RecentlyNonNull RemoteCall<A, TaskCompletionSource<ResultT>> remoteCall) {
            this.zaa = remoteCall;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, ResultT> setAutoResolveMissingFeatures(@RecentlyNonNull boolean z8) {
            this.zab = z8;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, ResultT> setFeatures(@RecentlyNonNull Feature... featureArr) {
            this.zac = featureArr;
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public Builder<A, ResultT> setMethodKey(@RecentlyNonNull int i9) {
            Preconditions.checkArgument(i9 != 0);
            this.zad = i9;
            return this;
        }
    }

    @KeepForSdk
    @Deprecated
    public TaskApiCall() {
        this.zaa = null;
        this.zab = false;
        this.zac = 0;
    }

    @RecentlyNonNull
    @KeepForSdk
    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<>();
    }

    @KeepForSdk
    public abstract void doExecute(@RecentlyNonNull A a9, @RecentlyNonNull TaskCompletionSource<ResultT> taskCompletionSource);

    @RecentlyNonNull
    @KeepForSdk
    public boolean shouldAutoResolveMissingFeatures() {
        return this.zab;
    }

    @RecentlyNullable
    public final Feature[] zaa() {
        return this.zaa;
    }

    @KeepForSdk
    private TaskApiCall(Feature[] featureArr, boolean z8, int i9) {
        this.zaa = featureArr;
        this.zab = z8;
        this.zac = i9;
    }
}
