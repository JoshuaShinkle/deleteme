package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes2.dex */
public class BaseImplementation {

    @KeepForSdk
    public interface ResultHolder<R> {
        @KeepForSdk
        void setFailedResult(@RecentlyNonNull Status status);

        @KeepForSdk
        void setResult(@RecentlyNonNull R r8);
    }

    @KeepForSdk
    public static abstract class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {

        @KeepForSdk
        private final Api<?> mApi;

        @KeepForSdk
        private final Api.AnyClientKey<A> mClientKey;

        @KeepForSdk
        @Deprecated
        public ApiMethodImpl(@RecentlyNonNull Api.AnyClientKey<A> anyClientKey, @RecentlyNonNull GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            this.mClientKey = (Api.AnyClientKey) Preconditions.checkNotNull(anyClientKey);
            this.mApi = null;
        }

        @KeepForSdk
        public abstract void doExecute(@RecentlyNonNull A a9);

        @RecentlyNullable
        @KeepForSdk
        public final Api<?> getApi() {
            return this.mApi;
        }

        @RecentlyNonNull
        @KeepForSdk
        public final Api.AnyClientKey<A> getClientKey() {
            return this.mClientKey;
        }

        @KeepForSdk
        public void onSetFailedResult(@RecentlyNonNull R r8) {
        }

        @KeepForSdk
        public final void run(@RecentlyNonNull A a9) throws DeadObjectException {
            try {
                doExecute(a9);
            } catch (DeadObjectException e9) {
                setFailedResult(e9);
                throw e9;
            } catch (RemoteException e10) {
                setFailedResult(e10);
            }
        }

        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
        @KeepForSdk
        public final void setFailedResult(@RecentlyNonNull Status status) {
            Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success");
            R rCreateFailedResult = createFailedResult(status);
            setResult((ApiMethodImpl<R, A>) rCreateFailedResult);
            onSetFailedResult(rCreateFailedResult);
        }

        @KeepForSdk
        public /* bridge */ /* synthetic */ void setResult(@RecentlyNonNull Object obj) {
            super.setResult((ApiMethodImpl<R, A>) obj);
        }

        @KeepForSdk
        public ApiMethodImpl(@RecentlyNonNull Api<?> api, @RecentlyNonNull GoogleApiClient googleApiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            Preconditions.checkNotNull(api, "Api must not be null");
            this.mClientKey = (Api.AnyClientKey<A>) api.zac();
            this.mApi = api;
        }

        @KeepForSdk
        private void setFailedResult(RemoteException remoteException) {
            setFailedResult(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        @KeepForSdk
        public ApiMethodImpl(@RecentlyNonNull BasePendingResult.CallbackHandler<R> callbackHandler) {
            super(callbackHandler);
            this.mClientKey = new Api.AnyClientKey<>();
            this.mApi = null;
        }
    }
}
