package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zabk;
import com.google.android.gms.common.api.internal.zacb;
import com.google.android.gms.common.api.internal.zax;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

@KeepForSdk
/* loaded from: classes2.dex */
public class GoogleApi<O extends Api.ApiOptions> implements HasApiKey<O> {
    private final Context zaa;
    private final String zab;
    private final Api<O> zac;
    private final O zad;
    private final ApiKey<O> zae;
    private final Looper zaf;
    private final int zag;

    @NotOnlyInitialized
    private final GoogleApiClient zah;
    private final StatusExceptionMapper zai;
    private final GoogleApiManager zaj;

    @KeepForSdk
    public static class Settings {

        @RecentlyNonNull
        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS = new Builder().build();

        @RecentlyNonNull
        public final StatusExceptionMapper zaa;

        @RecentlyNonNull
        public final Looper zab;

        @KeepForSdk
        public static class Builder {
            private StatusExceptionMapper zaa;
            private Looper zab;

            @KeepForSdk
            public Builder() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @RecentlyNonNull
            @KeepForSdk
            public Settings build() {
                if (this.zaa == null) {
                    this.zaa = new ApiExceptionMapper();
                }
                if (this.zab == null) {
                    this.zab = Looper.getMainLooper();
                }
                return new Settings(this.zaa, this.zab);
            }

            @RecentlyNonNull
            @KeepForSdk
            public Builder setLooper(@RecentlyNonNull Looper looper) {
                Preconditions.checkNotNull(looper, "Looper must not be null.");
                this.zab = looper;
                return this;
            }

            @RecentlyNonNull
            @KeepForSdk
            public Builder setMapper(@RecentlyNonNull StatusExceptionMapper statusExceptionMapper) {
                Preconditions.checkNotNull(statusExceptionMapper, "StatusExceptionMapper must not be null.");
                this.zaa = statusExceptionMapper;
                return this;
            }
        }

        @KeepForSdk
        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.zaa = statusExceptionMapper;
            this.zab = looper;
        }
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(@RecentlyNonNull Context context, @RecentlyNonNull Api<O> api, @RecentlyNonNull O o8, @RecentlyNonNull Looper looper, @RecentlyNonNull StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o8, new Settings.Builder().setLooper(looper).setMapper(statusExceptionMapper).build());
    }

    private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zaa(int i9, T t8) {
        t8.zab();
        this.zaj.zaa(this, i9, t8);
        return t8;
    }

    @RecentlyNonNull
    @KeepForSdk
    public GoogleApiClient asGoogleApiClient() {
        return this.zah;
    }

    @RecentlyNonNull
    @KeepForSdk
    public ClientSettings.Builder createClientSettingsBuilder() {
        Account account;
        GoogleSignInAccount googleSignInAccount;
        GoogleSignInAccount googleSignInAccount2;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        O o8 = this.zad;
        if (!(o8 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o8).getGoogleSignInAccount()) == null) {
            O o9 = this.zad;
            account = o9 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) o9).getAccount() : null;
        } else {
            account = googleSignInAccount2.getAccount();
        }
        ClientSettings.Builder builderZaa = builder.zaa(account);
        O o10 = this.zad;
        return builderZaa.zaa((!(o10 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o10).getGoogleSignInAccount()) == null) ? Collections.emptySet() : googleSignInAccount.getRequestedScopes()).zaa(this.zaa.getClass().getName()).setRealClientPackageName(this.zaa.getPackageName());
    }

    @RecentlyNonNull
    @KeepForSdk
    public Task<Boolean> disconnectService() {
        return this.zaj.zab((GoogleApi<?>) this);
    }

    @RecentlyNonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(@RecentlyNonNull T t8) {
        return (T) zaa(2, (int) t8);
    }

    @RecentlyNonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(@RecentlyNonNull T t8) {
        return (T) zaa(0, (int) t8);
    }

    @RecentlyNonNull
    @KeepForSdk
    @Deprecated
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(@RecentlyNonNull T t8, @RecentlyNonNull U u8) {
        Preconditions.checkNotNull(t8);
        Preconditions.checkNotNull(u8);
        Preconditions.checkNotNull(t8.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(u8.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(Objects.equal(t8.getListenerKey(), u8.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zaj.zaa(this, t8, u8, zad.zaa);
    }

    @RecentlyNonNull
    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@RecentlyNonNull ListenerHolder.ListenerKey<?> listenerKey) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return this.zaj.zaa(this, listenerKey);
    }

    @RecentlyNonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(@RecentlyNonNull T t8) {
        return (T) zaa(1, (int) t8);
    }

    @Override // com.google.android.gms.common.api.HasApiKey
    @RecentlyNonNull
    public ApiKey<O> getApiKey() {
        return this.zae;
    }

    @RecentlyNonNull
    @KeepForSdk
    public O getApiOptions() {
        return this.zad;
    }

    @RecentlyNonNull
    @KeepForSdk
    public Context getApplicationContext() {
        return this.zaa;
    }

    @RecentlyNullable
    @KeepForSdk
    public String getContextAttributionTag() {
        return this.zab;
    }

    @RecentlyNullable
    @KeepForSdk
    @Deprecated
    public String getContextFeatureId() {
        return this.zab;
    }

    @RecentlyNonNull
    @KeepForSdk
    public Looper getLooper() {
        return this.zaf;
    }

    @RecentlyNonNull
    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@RecentlyNonNull L l9, @RecentlyNonNull String str) {
        return ListenerHolders.createListenerHolder(l9, this.zaf, str);
    }

    @KeepForSdk
    public GoogleApi(@RecentlyNonNull Activity activity, @RecentlyNonNull Api<O> api, @RecentlyNonNull O o8, @RecentlyNonNull Settings settings) {
        Preconditions.checkNotNull(activity, "Null activity is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = activity.getApplicationContext();
        this.zaa = applicationContext;
        this.zab = zaa(activity);
        this.zac = api;
        this.zad = o8;
        this.zaf = settings.zab;
        ApiKey<O> sharedApiKey = ApiKey.getSharedApiKey(api, o8);
        this.zae = sharedApiKey;
        this.zah = new zabk(this);
        GoogleApiManager googleApiManagerZaa = GoogleApiManager.zaa(applicationContext);
        this.zaj = googleApiManagerZaa;
        this.zag = googleApiManagerZaa.zab();
        this.zai = settings.zaa;
        if (!(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            zax.zaa(activity, googleApiManagerZaa, sharedApiKey);
        }
        googleApiManagerZaa.zaa((GoogleApi<?>) this);
    }

    @RecentlyNonNull
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(@RecentlyNonNull TaskApiCall<A, TResult> taskApiCall) {
        return zaa(2, taskApiCall);
    }

    @RecentlyNonNull
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(@RecentlyNonNull TaskApiCall<A, TResult> taskApiCall) {
        return zaa(0, taskApiCall);
    }

    @RecentlyNonNull
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(@RecentlyNonNull TaskApiCall<A, TResult> taskApiCall) {
        return zaa(1, taskApiCall);
    }

    private final <TResult, A extends Api.AnyClient> Task<TResult> zaa(int i9, TaskApiCall<A, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zaj.zaa(this, i9, taskApiCall, taskCompletionSource, this.zai);
        return taskCompletionSource.getTask();
    }

    public final Api.Client zaa(Looper looper, GoogleApiManager.zaa<O> zaaVar) {
        return ((Api.AbstractClientBuilder) Preconditions.checkNotNull(this.zac.zab())).buildClient(this.zaa, looper, createClientSettingsBuilder().build(), (ClientSettings) this.zad, (GoogleApiClient.ConnectionCallbacks) zaaVar, (GoogleApiClient.OnConnectionFailedListener) zaaVar);
    }

    @RecentlyNonNull
    public final int zaa() {
        return this.zag;
    }

    private static String zaa(Object obj) {
        if (!PlatformVersion.isAtLeastR()) {
            return null;
        }
        try {
            return (String) Context.class.getMethod("getAttributionTag", new Class[0]).invoke(obj, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    @RecentlyNonNull
    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(@RecentlyNonNull RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.zaa.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zab.getListenerKey(), "Listener has already been released.");
        return this.zaj.zaa(this, registrationMethods.zaa, registrationMethods.zab, registrationMethods.zac);
    }

    public final zacb zaa(Context context, Handler handler) {
        return new zacb(context, handler, createClientSettingsBuilder().build());
    }

    @KeepForSdk
    public GoogleApi(@RecentlyNonNull Context context, @RecentlyNonNull Api<O> api, @RecentlyNonNull O o8, @RecentlyNonNull Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        this.zaa = applicationContext;
        this.zab = zaa(context);
        this.zac = api;
        this.zad = o8;
        this.zaf = settings.zab;
        this.zae = ApiKey.getSharedApiKey(api, o8);
        this.zah = new zabk(this);
        GoogleApiManager googleApiManagerZaa = GoogleApiManager.zaa(applicationContext);
        this.zaj = googleApiManagerZaa;
        this.zag = googleApiManagerZaa.zab();
        this.zai = settings.zaa;
        googleApiManagerZaa.zaa((GoogleApi<?>) this);
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(@RecentlyNonNull Activity activity, @RecentlyNonNull Api<O> api, @RecentlyNonNull O o8, @RecentlyNonNull StatusExceptionMapper statusExceptionMapper) {
        this(activity, (Api) api, (Api.ApiOptions) o8, new Settings.Builder().setMapper(statusExceptionMapper).setLooper(activity.getMainLooper()).build());
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(@RecentlyNonNull Context context, @RecentlyNonNull Api<O> api, @RecentlyNonNull O o8, @RecentlyNonNull StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o8, new Settings.Builder().setMapper(statusExceptionMapper).build());
    }
}
