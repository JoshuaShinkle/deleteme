package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import p132m.C5303b;

@VisibleForTesting
@KeepForSdk
/* loaded from: classes2.dex */
public final class ClientSettings {
    private final Account zaa;
    private final Set<Scope> zab;
    private final Set<Scope> zac;
    private final Map<Api<?>, zaa> zad;
    private final int zae;
    private final View zaf;
    private final String zag;
    private final String zah;
    private final SignInOptions zai;
    private final boolean zaj;
    private Integer zak;

    @KeepForSdk
    public static final class Builder {
        private Account zaa;
        private C5303b<Scope> zab;
        private String zad;
        private String zae;
        private int zac = 0;
        private SignInOptions zaf = SignInOptions.zaa;

        @RecentlyNonNull
        @KeepForSdk
        public final ClientSettings build() {
            return new ClientSettings(this.zaa, this.zab, null, 0, null, this.zad, this.zae, this.zaf, false);
        }

        @RecentlyNonNull
        @KeepForSdk
        public final Builder setRealClientPackageName(@RecentlyNonNull String str) {
            this.zad = str;
            return this;
        }

        @RecentlyNonNull
        public final Builder zaa(Account account) {
            this.zaa = account;
            return this;
        }

        @RecentlyNonNull
        public final Builder zaa(@RecentlyNonNull Collection<Scope> collection) {
            if (this.zab == null) {
                this.zab = new C5303b<>();
            }
            this.zab.addAll(collection);
            return this;
        }

        @RecentlyNonNull
        public final Builder zaa(@RecentlyNonNull String str) {
            this.zae = str;
            return this;
        }
    }

    public static final class zaa {
        public final Set<Scope> zaa;

        public zaa(Set<Scope> set) {
            Preconditions.checkNotNull(set);
            this.zaa = Collections.unmodifiableSet(set);
        }
    }

    @KeepForSdk
    public ClientSettings(@RecentlyNonNull Account account, @RecentlyNonNull Set<Scope> set, @RecentlyNonNull Map<Api<?>, zaa> map, @RecentlyNonNull int i9, @RecentlyNonNull View view, @RecentlyNonNull String str, @RecentlyNonNull String str2, @RecentlyNonNull SignInOptions signInOptions) {
        this(account, set, map, i9, view, str, str2, signInOptions, false);
    }

    @RecentlyNonNull
    @KeepForSdk
    public static ClientSettings createDefault(@RecentlyNonNull Context context) {
        return new GoogleApiClient.Builder(context).buildClientSettings();
    }

    @RecentlyNullable
    @KeepForSdk
    public final Account getAccount() {
        return this.zaa;
    }

    @RecentlyNullable
    @KeepForSdk
    @Deprecated
    public final String getAccountName() {
        Account account = this.zaa;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final Account getAccountOrDefault() {
        Account account = this.zaa;
        return account != null ? account : new Account("<<default account>>", "com.google");
    }

    @RecentlyNonNull
    @KeepForSdk
    public final Set<Scope> getAllRequestedScopes() {
        return this.zac;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final Set<Scope> getApplicableScopes(@RecentlyNonNull Api<?> api) {
        zaa zaaVar = this.zad.get(api);
        if (zaaVar == null || zaaVar.zaa.isEmpty()) {
            return this.zab;
        }
        HashSet hashSet = new HashSet(this.zab);
        hashSet.addAll(zaaVar.zaa);
        return hashSet;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final int getGravityForPopups() {
        return this.zae;
    }

    @RecentlyNullable
    @KeepForSdk
    public final String getRealClientPackageName() {
        return this.zag;
    }

    @RecentlyNonNull
    @KeepForSdk
    public final Set<Scope> getRequiredScopes() {
        return this.zab;
    }

    @RecentlyNullable
    @KeepForSdk
    public final View getViewForPopups() {
        return this.zaf;
    }

    @RecentlyNonNull
    public final Map<Api<?>, zaa> zaa() {
        return this.zad;
    }

    @RecentlyNullable
    public final String zab() {
        return this.zah;
    }

    @RecentlyNonNull
    public final SignInOptions zac() {
        return this.zai;
    }

    @RecentlyNullable
    public final Integer zad() {
        return this.zak;
    }

    public ClientSettings(Account account, @RecentlyNonNull Set<Scope> set, @RecentlyNonNull Map<Api<?>, zaa> map, @RecentlyNonNull int i9, @RecentlyNonNull View view, @RecentlyNonNull String str, @RecentlyNonNull String str2, @RecentlyNonNull SignInOptions signInOptions, @RecentlyNonNull boolean z8) {
        this.zaa = account;
        Set<Scope> setEmptySet = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.zab = setEmptySet;
        map = map == null ? Collections.emptyMap() : map;
        this.zad = map;
        this.zaf = view;
        this.zae = i9;
        this.zag = str;
        this.zah = str2;
        this.zai = signInOptions;
        this.zaj = false;
        HashSet hashSet = new HashSet(setEmptySet);
        Iterator<zaa> it = map.values().iterator();
        while (it.hasNext()) {
            hashSet.addAll(it.next().zaa);
        }
        this.zac = Collections.unmodifiableSet(hashSet);
    }

    public final void zaa(@RecentlyNonNull Integer num) {
        this.zak = num;
    }
}
