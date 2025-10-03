package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.RecentlyNonNull;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zaap;
import com.google.android.gms.common.api.internal.zack;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.api.internal.zap;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import p132m.C5302a;

@KeepForSdk
@Deprecated
/* loaded from: classes2.dex */
public abstract class GoogleApiClient {

    @RecentlyNonNull
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";

    @RecentlyNonNull
    public static final int SIGN_IN_MODE_OPTIONAL = 2;

    @RecentlyNonNull
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Set<GoogleApiClient> zaa = Collections.newSetFromMap(new WeakHashMap());

    @Deprecated
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {

        @RecentlyNonNull
        public static final int CAUSE_NETWORK_LOST = 2;

        @RecentlyNonNull
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    }

    @Deprecated
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    public static void dumpAll(@RecentlyNonNull String str, @RecentlyNonNull FileDescriptor fileDescriptor, @RecentlyNonNull PrintWriter printWriter, @RecentlyNonNull String[] strArr) {
        Set<GoogleApiClient> set = zaa;
        synchronized (set) {
            String strConcat = String.valueOf(str).concat("  ");
            int i9 = 0;
            for (GoogleApiClient googleApiClient : set) {
                printWriter.append((CharSequence) str).append("GoogleApiClient#").println(i9);
                googleApiClient.dump(strConcat, fileDescriptor, printWriter, strArr);
                i9++;
            }
        }
    }

    @RecentlyNonNull
    @KeepForSdk
    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> set = zaa;
        synchronized (set) {
        }
        return set;
    }

    @RecentlyNonNull
    public abstract ConnectionResult blockingConnect();

    @RecentlyNonNull
    public abstract ConnectionResult blockingConnect(@RecentlyNonNull long j9, @RecentlyNonNull TimeUnit timeUnit);

    @RecentlyNonNull
    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(@RecentlyNonNull int i9) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(@RecentlyNonNull String str, @RecentlyNonNull FileDescriptor fileDescriptor, @RecentlyNonNull PrintWriter printWriter, @RecentlyNonNull String[] strArr);

    @RecentlyNonNull
    @KeepForSdk
    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@RecentlyNonNull T t8) {
        throw new UnsupportedOperationException();
    }

    @RecentlyNonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@RecentlyNonNull T t8) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <C extends Api.Client> C getClient(@RecentlyNonNull Api.AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }

    public abstract ConnectionResult getConnectionResult(@RecentlyNonNull Api<?> api);

    @RecentlyNonNull
    @KeepForSdk
    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    @RecentlyNonNull
    @KeepForSdk
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean hasApi(@RecentlyNonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    @RecentlyNonNull
    public abstract boolean hasConnectedApi(@RecentlyNonNull Api<?> api);

    @RecentlyNonNull
    public abstract boolean isConnected();

    @RecentlyNonNull
    public abstract boolean isConnecting();

    @RecentlyNonNull
    public abstract boolean isConnectionCallbacksRegistered(@RecentlyNonNull ConnectionCallbacks connectionCallbacks);

    @RecentlyNonNull
    public abstract boolean isConnectionFailedListenerRegistered(@RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener);

    @RecentlyNonNull
    @KeepForSdk
    public boolean maybeSignIn(@RecentlyNonNull SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@RecentlyNonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener);

    @RecentlyNonNull
    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@RecentlyNonNull L l9) {
        throw new UnsupportedOperationException();
    }

    public abstract void stopAutoManage(@RecentlyNonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@RecentlyNonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener);

    public void zaa(zack zackVar) {
        throw new UnsupportedOperationException();
    }

    public void zab(zack zackVar) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    @Deprecated
    public static final class Builder {
        private Account zaa;
        private final Set<Scope> zab;
        private final Set<Scope> zac;
        private int zad;
        private View zae;
        private String zaf;
        private String zag;
        private final Map<Api<?>, ClientSettings.zaa> zah;
        private boolean zai;
        private final Context zaj;
        private final Map<Api<?>, Api.ApiOptions> zak;
        private LifecycleActivity zal;
        private int zam;
        private OnConnectionFailedListener zan;
        private Looper zao;
        private GoogleApiAvailability zap;
        private Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaq;
        private final ArrayList<ConnectionCallbacks> zar;
        private final ArrayList<OnConnectionFailedListener> zas;

        @KeepForSdk
        public Builder(@RecentlyNonNull Context context) {
            this.zab = new HashSet();
            this.zac = new HashSet();
            this.zah = new C5302a();
            this.zai = false;
            this.zak = new C5302a();
            this.zam = -1;
            this.zap = GoogleApiAvailability.getInstance();
            this.zaq = com.google.android.gms.signin.zaa.zaa;
            this.zar = new ArrayList<>();
            this.zas = new ArrayList<>();
            this.zaj = context;
            this.zao = context.getMainLooper();
            this.zaf = context.getPackageName();
            this.zag = context.getClass().getName();
        }

        private final <O extends Api.ApiOptions> void zaa(Api<O> api, O o8, Scope... scopeArr) {
            HashSet hashSet = new HashSet(((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zaa(), "Base client builder must not be null")).getImpliedScopes(o8));
            for (Scope scope : scopeArr) {
                hashSet.add(scope);
            }
            this.zah.put(api, new ClientSettings.zaa(hashSet));
        }

        @RecentlyNonNull
        public final Builder addApi(@RecentlyNonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zak.put(api, null);
            List<Scope> impliedScopes = ((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zaa(), "Base client builder must not be null")).getImpliedScopes(null);
            this.zac.addAll(impliedScopes);
            this.zab.addAll(impliedScopes);
            return this;
        }

        @RecentlyNonNull
        public final <T extends Api.ApiOptions.NotRequiredOptions> Builder addApiIfAvailable(@RecentlyNonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api, @RecentlyNonNull Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zak.put(api, null);
            zaa(api, null, scopeArr);
            return this;
        }

        @RecentlyNonNull
        public final Builder addConnectionCallbacks(@RecentlyNonNull ConnectionCallbacks connectionCallbacks) {
            Preconditions.checkNotNull(connectionCallbacks, "Listener must not be null");
            this.zar.add(connectionCallbacks);
            return this;
        }

        @RecentlyNonNull
        public final Builder addOnConnectionFailedListener(@RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener) {
            Preconditions.checkNotNull(onConnectionFailedListener, "Listener must not be null");
            this.zas.add(onConnectionFailedListener);
            return this;
        }

        @RecentlyNonNull
        public final Builder addScope(@RecentlyNonNull Scope scope) {
            Preconditions.checkNotNull(scope, "Scope must not be null");
            this.zab.add(scope);
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public final Builder addScopeNames(@RecentlyNonNull String[] strArr) {
            for (String str : strArr) {
                this.zab.add(new Scope(str));
            }
            return this;
        }

        @RecentlyNonNull
        public final GoogleApiClient build() {
            Preconditions.checkArgument(!this.zak.isEmpty(), "must call addApi() to add at least one API");
            ClientSettings clientSettingsBuildClientSettings = buildClientSettings();
            Map<Api<?>, ClientSettings.zaa> mapZaa = clientSettingsBuildClientSettings.zaa();
            C5302a c5302a = new C5302a();
            C5302a c5302a2 = new C5302a();
            ArrayList arrayList = new ArrayList();
            Api<?> api = null;
            boolean z8 = false;
            for (Api<?> api2 : this.zak.keySet()) {
                Api.ApiOptions apiOptions = this.zak.get(api2);
                boolean z9 = mapZaa.get(api2) != null;
                c5302a.put(api2, Boolean.valueOf(z9));
                zap zapVar = new zap(api2, z9);
                arrayList.add(zapVar);
                Api.AbstractClientBuilder abstractClientBuilder = (Api.AbstractClientBuilder) Preconditions.checkNotNull(api2.zab());
                Api.Client clientBuildClient = abstractClientBuilder.buildClient(this.zaj, this.zao, clientSettingsBuildClientSettings, (ClientSettings) apiOptions, (ConnectionCallbacks) zapVar, (OnConnectionFailedListener) zapVar);
                c5302a2.put(api2.zac(), clientBuildClient);
                if (abstractClientBuilder.getPriority() == 1) {
                    z8 = apiOptions != null;
                }
                if (clientBuildClient.providesSignIn()) {
                    if (api != null) {
                        String strZad = api2.zad();
                        String strZad2 = api.zad();
                        StringBuilder sb = new StringBuilder(String.valueOf(strZad).length() + 21 + String.valueOf(strZad2).length());
                        sb.append(strZad);
                        sb.append(" cannot be used with ");
                        sb.append(strZad2);
                        throw new IllegalStateException(sb.toString());
                    }
                    api = api2;
                }
            }
            if (api != null) {
                if (z8) {
                    String strZad3 = api.zad();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(strZad3).length() + 82);
                    sb2.append("With using ");
                    sb2.append(strZad3);
                    sb2.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
                    throw new IllegalStateException(sb2.toString());
                }
                Preconditions.checkState(this.zaa == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.zad());
                Preconditions.checkState(this.zab.equals(this.zac), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.zad());
            }
            zaap zaapVar = new zaap(this.zaj, new ReentrantLock(), this.zao, clientSettingsBuildClientSettings, this.zap, this.zaq, c5302a, this.zar, this.zas, c5302a2, this.zam, zaap.zaa((Iterable<Api.Client>) c5302a2.values(), true), arrayList);
            synchronized (GoogleApiClient.zaa) {
                GoogleApiClient.zaa.add(zaapVar);
            }
            if (this.zam >= 0) {
                zai.zaa(this.zal).zaa(this.zam, zaapVar, this.zan);
            }
            return zaapVar;
        }

        @RecentlyNonNull
        @VisibleForTesting
        @KeepForSdk
        public final ClientSettings buildClientSettings() {
            SignInOptions signInOptions = SignInOptions.zaa;
            Map<Api<?>, Api.ApiOptions> map = this.zak;
            Api<SignInOptions> api = com.google.android.gms.signin.zaa.zab;
            if (map.containsKey(api)) {
                signInOptions = (SignInOptions) this.zak.get(api);
            }
            return new ClientSettings(this.zaa, this.zab, this.zah, this.zad, this.zae, this.zaf, this.zag, signInOptions, false);
        }

        @RecentlyNonNull
        public final Builder enableAutoManage(@RecentlyNonNull FragmentActivity fragmentActivity, @RecentlyNonNull int i9, OnConnectionFailedListener onConnectionFailedListener) {
            LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
            Preconditions.checkArgument(i9 >= 0, "clientId must be non-negative");
            this.zam = i9;
            this.zan = onConnectionFailedListener;
            this.zal = lifecycleActivity;
            return this;
        }

        @RecentlyNonNull
        public final Builder setAccountName(@RecentlyNonNull String str) {
            this.zaa = str == null ? null : new Account(str, "com.google");
            return this;
        }

        @RecentlyNonNull
        public final Builder setGravityForPopups(@RecentlyNonNull int i9) {
            this.zad = i9;
            return this;
        }

        @RecentlyNonNull
        public final Builder setHandler(@RecentlyNonNull Handler handler) {
            Preconditions.checkNotNull(handler, "Handler must not be null");
            this.zao = handler.getLooper();
            return this;
        }

        @RecentlyNonNull
        public final Builder setViewForPopups(@RecentlyNonNull View view) {
            Preconditions.checkNotNull(view, "View must not be null");
            this.zae = view;
            return this;
        }

        @RecentlyNonNull
        public final Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        @RecentlyNonNull
        public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@RecentlyNonNull Api<O> api, @RecentlyNonNull O o8, @RecentlyNonNull Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o8, "Null options are not permitted for this Api");
            this.zak.put(api, o8);
            zaa(api, o8, scopeArr);
            return this;
        }

        @RecentlyNonNull
        public final Builder enableAutoManage(@RecentlyNonNull FragmentActivity fragmentActivity, OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        @RecentlyNonNull
        public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@RecentlyNonNull Api<O> api, @RecentlyNonNull O o8) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o8, "Null options are not permitted for this Api");
            this.zak.put(api, o8);
            List<Scope> impliedScopes = ((Api.BaseClientBuilder) Preconditions.checkNotNull(api.zaa(), "Base client builder must not be null")).getImpliedScopes(o8);
            this.zac.addAll(impliedScopes);
            this.zab.addAll(impliedScopes);
            return this;
        }

        @KeepForSdk
        public Builder(@RecentlyNonNull Context context, @RecentlyNonNull ConnectionCallbacks connectionCallbacks, @RecentlyNonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            Preconditions.checkNotNull(connectionCallbacks, "Must provide a connected listener");
            this.zar.add(connectionCallbacks);
            Preconditions.checkNotNull(onConnectionFailedListener, "Must provide a connection failed listener");
            this.zas.add(onConnectionFailedListener);
        }
    }
}
