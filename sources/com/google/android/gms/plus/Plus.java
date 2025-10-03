package com.google.android.gms.plus;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.plus.zze;
import com.google.android.gms.internal.plus.zzi;
import com.google.android.gms.internal.plus.zzj;
import com.google.android.gms.plus.internal.zzh;
import java.util.HashSet;
import java.util.Set;

@Deprecated
/* loaded from: classes2.dex */
public final class Plus {

    @Deprecated
    public static final Api<PlusOptions> API;

    @Deprecated
    public static final Account AccountApi;
    private static final Api.AbstractClientBuilder<zzh, PlusOptions> CLIENT_BUILDER;
    public static final Api.ClientKey<zzh> CLIENT_KEY;

    @Deprecated
    public static final People PeopleApi;

    @Deprecated
    public static final Scope SCOPE_PLUS_LOGIN;

    @Deprecated
    public static final Scope SCOPE_PLUS_PROFILE;

    @Deprecated
    private static final zzb zze;
    private static final com.google.android.gms.plus.zza zzf;

    @Deprecated
    public static final class PlusOptions implements Api.ApiOptions.Optional {
        private final String zzg;
        final Set<String> zzh;

        @Deprecated
        public static final class Builder {
            String zzg;
            final Set<String> zzh = new HashSet();

            @VisibleForTesting
            @Deprecated
            public final Builder addActivityTypes(String... strArr) {
                Preconditions.checkNotNull(strArr, "activityTypes may not be null.");
                for (String str : strArr) {
                    this.zzh.add(str);
                }
                return this;
            }

            @VisibleForTesting
            @Deprecated
            public final PlusOptions build() {
                return new PlusOptions(this, null);
            }

            @Deprecated
            public final Builder setServerClientId(String str) {
                this.zzg = str;
                return this;
            }
        }

        private PlusOptions() {
            this.zzg = null;
            this.zzh = new HashSet();
        }

        private PlusOptions(Builder builder) {
            this.zzg = builder.zzg;
            this.zzh = builder.zzh;
        }

        public /* synthetic */ PlusOptions(Builder builder, zzc zzcVar) {
            this(builder);
        }

        public /* synthetic */ PlusOptions(zzc zzcVar) {
            this();
        }

        @VisibleForTesting
        @Deprecated
        public static Builder builder() {
            return new Builder();
        }
    }

    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzh> {
        public zza(GoogleApiClient googleApiClient) {
            super(Plus.CLIENT_KEY, googleApiClient);
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.google.android.gms.internal.plus.zzi, com.google.android.gms.plus.zzb] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.gms.internal.plus.zzh, com.google.android.gms.plus.zza] */
    static {
        Api.ClientKey<zzh> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzc zzcVar = new zzc();
        CLIENT_BUILDER = zzcVar;
        API = new Api<>("Plus.API", zzcVar, clientKey);
        SCOPE_PLUS_LOGIN = new Scope(Scopes.PLUS_LOGIN);
        SCOPE_PLUS_PROFILE = new Scope(Scopes.PLUS_ME);
        PeopleApi = new zzj();
        AccountApi = new zze();
        zze = new zzi();
        zzf = new com.google.android.gms.internal.plus.zzh();
    }

    private Plus() {
    }

    public static zzh zza(GoogleApiClient googleApiClient, boolean z8) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        Preconditions.checkState(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        Api<PlusOptions> api = API;
        Preconditions.checkState(googleApiClient.hasApi(api), "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean zHasConnectedApi = googleApiClient.hasConnectedApi(api);
        if (z8 && !zHasConnectedApi) {
            throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
        }
        if (zHasConnectedApi) {
            return (zzh) googleApiClient.getClient(CLIENT_KEY);
        }
        return null;
    }
}
