package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public final class Api<O extends ApiOptions> {
    private final AbstractClientBuilder<?, O> zaa;
    private final ClientKey<?> zab;
    private final String zac;

    @VisibleForTesting
    @KeepForSdk
    public static abstract class AbstractClientBuilder<T extends Client, O> extends BaseClientBuilder<T, O> {
        @RecentlyNonNull
        @KeepForSdk
        @Deprecated
        public T buildClient(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull ClientSettings clientSettings, @RecentlyNonNull O o8, @RecentlyNonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks, @RecentlyNonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return (T) buildClient(context, looper, clientSettings, (ClientSettings) o8, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
        }

        @RecentlyNonNull
        @KeepForSdk
        public T buildClient(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull ClientSettings clientSettings, @RecentlyNonNull O o8, @RecentlyNonNull ConnectionCallbacks connectionCallbacks, @RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    @KeepForSdk
    public interface AnyClient {
    }

    @KeepForSdk
    public static class AnyClientKey<C extends AnyClient> {
    }

    public interface ApiOptions {

        @RecentlyNonNull
        public static final NoOptions NO_OPTIONS = new NoOptions();

        public interface HasAccountOptions extends HasOptions, NotRequiredOptions {
            @RecentlyNonNull
            Account getAccount();
        }

        public interface HasGoogleSignInAccountOptions extends HasOptions {
            @RecentlyNonNull
            GoogleSignInAccount getGoogleSignInAccount();
        }

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public static abstract class BaseClientBuilder<T extends AnyClient, O> {

        @RecentlyNonNull
        @KeepForSdk
        public static final int API_PRIORITY_GAMES = 1;

        @RecentlyNonNull
        @KeepForSdk
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;

        @RecentlyNonNull
        @KeepForSdk
        public static final int API_PRIORITY_PLUS = 2;

        @RecentlyNonNull
        @KeepForSdk
        public List<Scope> getImpliedScopes(O o8) {
            return Collections.emptyList();
        }

        @RecentlyNonNull
        @KeepForSdk
        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    }

    @KeepForSdk
    public interface Client extends AnyClient {
        @KeepForSdk
        void connect(@RecentlyNonNull BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

        @KeepForSdk
        void disconnect();

        @KeepForSdk
        void disconnect(@RecentlyNonNull String str);

        @KeepForSdk
        void dump(@RecentlyNonNull String str, FileDescriptor fileDescriptor, @RecentlyNonNull PrintWriter printWriter, String[] strArr);

        @RecentlyNonNull
        @KeepForSdk
        Feature[] getAvailableFeatures();

        @RecentlyNonNull
        @KeepForSdk
        String getEndpointPackageName();

        @RecentlyNullable
        @KeepForSdk
        String getLastDisconnectMessage();

        @RecentlyNonNull
        @KeepForSdk
        int getMinApkVersion();

        @KeepForSdk
        void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set);

        @RecentlyNonNull
        @KeepForSdk
        Feature[] getRequiredFeatures();

        @KeepForSdk
        Set<Scope> getScopesForConnectionlessNonSignIn();

        @RecentlyNullable
        @KeepForSdk
        IBinder getServiceBrokerBinder();

        @RecentlyNonNull
        @KeepForSdk
        Intent getSignInIntent();

        @RecentlyNonNull
        @KeepForSdk
        boolean isConnected();

        @RecentlyNonNull
        @KeepForSdk
        boolean isConnecting();

        @KeepForSdk
        void onUserSignOut(@RecentlyNonNull BaseGmsClient.SignOutCallbacks signOutCallbacks);

        @RecentlyNonNull
        @KeepForSdk
        boolean providesSignIn();

        @RecentlyNonNull
        @KeepForSdk
        boolean requiresAccount();

        @RecentlyNonNull
        @KeepForSdk
        boolean requiresGooglePlayServices();

        @RecentlyNonNull
        @KeepForSdk
        boolean requiresSignIn();
    }

    @VisibleForTesting
    @KeepForSdk
    public static final class ClientKey<C extends Client> extends AnyClientKey<C> {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @KeepForSdk
    public <C extends Client> Api(@RecentlyNonNull String str, @RecentlyNonNull AbstractClientBuilder<C, O> abstractClientBuilder, @RecentlyNonNull ClientKey<C> clientKey) {
        Preconditions.checkNotNull(abstractClientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(clientKey, "Cannot construct an Api with a null ClientKey");
        this.zac = str;
        this.zaa = abstractClientBuilder;
        this.zab = clientKey;
    }

    @RecentlyNonNull
    public final BaseClientBuilder<?, O> zaa() {
        return this.zaa;
    }

    @RecentlyNonNull
    public final AbstractClientBuilder<?, O> zab() {
        return this.zaa;
    }

    @RecentlyNonNull
    public final AnyClientKey<?> zac() {
        return this.zab;
    }

    @RecentlyNonNull
    public final String zad() {
        return this.zac;
    }
}
