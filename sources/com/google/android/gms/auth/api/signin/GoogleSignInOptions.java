package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GoogleSignInOptionsCreator")
/* loaded from: classes2.dex */
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;

    @RecentlyNonNull
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;

    @RecentlyNonNull
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;

    @RecentlyNonNull
    @VisibleForTesting
    public static final Scope zaa = new Scope(Scopes.PROFILE);

    @RecentlyNonNull
    @VisibleForTesting
    public static final Scope zab = new Scope(Scopes.EMAIL);

    @RecentlyNonNull
    @VisibleForTesting
    public static final Scope zac = new Scope(Scopes.OPEN_ID);

    @RecentlyNonNull
    @VisibleForTesting
    public static final Scope zad;

    @RecentlyNonNull
    @VisibleForTesting
    public static final Scope zae;
    private static Comparator<Scope> zaq;

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaf;

    @SafeParcelable.Field(getter = "getScopes", m17520id = 2)
    private final ArrayList<Scope> zag;

    @SafeParcelable.Field(getter = "getAccount", m17520id = 3)
    private Account zah;

    @SafeParcelable.Field(getter = "isIdTokenRequested", m17520id = 4)
    private boolean zai;

    @SafeParcelable.Field(getter = "isServerAuthCodeRequested", m17520id = 5)
    private final boolean zaj;

    @SafeParcelable.Field(getter = "isForceCodeForRefreshToken", m17520id = 6)
    private final boolean zak;

    @SafeParcelable.Field(getter = "getServerClientId", m17520id = 7)
    private String zal;

    @SafeParcelable.Field(getter = "getHostedDomain", m17520id = 8)
    private String zam;

    @SafeParcelable.Field(getter = "getExtensions", m17520id = 9)
    private ArrayList<GoogleSignInOptionsExtensionParcelable> zan;

    @SafeParcelable.Field(getter = "getLogSessionId", m17520id = 10)
    private String zao;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zap;

    public static final class Builder {
        private Set<Scope> zaa;
        private boolean zab;
        private boolean zac;
        private boolean zad;
        private String zae;
        private Account zaf;
        private String zag;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> zah;
        private String zai;

        public Builder() {
            this.zaa = new HashSet();
            this.zah = new HashMap();
        }

        private final String zaa(String str) {
            Preconditions.checkNotEmpty(str);
            String str2 = this.zae;
            Preconditions.checkArgument(str2 == null || str2.equals(str), "two different server client ids provided");
            return str;
        }

        @RecentlyNonNull
        public final Builder addExtension(@RecentlyNonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zah.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            List<Scope> impliedScopes = googleSignInOptionsExtension.getImpliedScopes();
            if (impliedScopes != null) {
                this.zaa.addAll(impliedScopes);
            }
            this.zah.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
            return this;
        }

        @RecentlyNonNull
        public final GoogleSignInOptions build() {
            if (this.zaa.contains(GoogleSignInOptions.zae)) {
                Set<Scope> set = this.zaa;
                Scope scope = GoogleSignInOptions.zad;
                if (set.contains(scope)) {
                    this.zaa.remove(scope);
                }
            }
            if (this.zad && (this.zaf == null || !this.zaa.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zaa), this.zaf, this.zad, this.zab, this.zac, this.zae, this.zag, this.zah, this.zai, null);
        }

        @RecentlyNonNull
        public final Builder requestEmail() {
            this.zaa.add(GoogleSignInOptions.zab);
            return this;
        }

        @RecentlyNonNull
        public final Builder requestId() {
            this.zaa.add(GoogleSignInOptions.zac);
            return this;
        }

        @RecentlyNonNull
        public final Builder requestIdToken(@RecentlyNonNull String str) {
            this.zad = true;
            this.zae = zaa(str);
            return this;
        }

        @RecentlyNonNull
        public final Builder requestProfile() {
            this.zaa.add(GoogleSignInOptions.zaa);
            return this;
        }

        @RecentlyNonNull
        public final Builder requestScopes(@RecentlyNonNull Scope scope, @RecentlyNonNull Scope... scopeArr) {
            this.zaa.add(scope);
            this.zaa.addAll(Arrays.asList(scopeArr));
            return this;
        }

        @RecentlyNonNull
        public final Builder requestServerAuthCode(@RecentlyNonNull String str) {
            return requestServerAuthCode(str, false);
        }

        @RecentlyNonNull
        public final Builder setAccountName(@RecentlyNonNull String str) {
            this.zaf = new Account(Preconditions.checkNotEmpty(str), "com.google");
            return this;
        }

        @RecentlyNonNull
        public final Builder setHostedDomain(@RecentlyNonNull String str) {
            this.zag = Preconditions.checkNotEmpty(str);
            return this;
        }

        @RecentlyNonNull
        @KeepForSdk
        public final Builder setLogSessionId(@RecentlyNonNull String str) {
            this.zai = str;
            return this;
        }

        @RecentlyNonNull
        public final Builder requestServerAuthCode(@RecentlyNonNull String str, @RecentlyNonNull boolean z8) {
            this.zab = true;
            this.zae = zaa(str);
            this.zac = z8;
            return this;
        }

        public Builder(@RecentlyNonNull GoogleSignInOptions googleSignInOptions) {
            this.zaa = new HashSet();
            this.zah = new HashMap();
            Preconditions.checkNotNull(googleSignInOptions);
            this.zaa = new HashSet(googleSignInOptions.zag);
            this.zab = googleSignInOptions.zaj;
            this.zac = googleSignInOptions.zak;
            this.zad = googleSignInOptions.zai;
            this.zae = googleSignInOptions.zal;
            this.zaf = googleSignInOptions.zah;
            this.zag = googleSignInOptions.zam;
            this.zah = GoogleSignInOptions.zab(googleSignInOptions.zan);
            this.zai = googleSignInOptions.zao;
        }
    }

    static {
        Scope scope = new Scope(Scopes.GAMES_LITE);
        zad = scope;
        zae = new Scope(Scopes.GAMES);
        DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(scope, new Scope[0]).build();
        CREATOR = new zad();
        zaq = new zac();
    }

    @SafeParcelable.Constructor
    public GoogleSignInOptions(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) ArrayList<Scope> arrayList, @SafeParcelable.Param(m17521id = 3) Account account, @SafeParcelable.Param(m17521id = 4) boolean z8, @SafeParcelable.Param(m17521id = 5) boolean z9, @SafeParcelable.Param(m17521id = 6) boolean z10, @SafeParcelable.Param(m17521id = 7) String str, @SafeParcelable.Param(m17521id = 8) String str2, @SafeParcelable.Param(m17521id = 9) ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2, @SafeParcelable.Param(m17521id = 10) String str3) {
        this(i9, arrayList, account, z8, z9, z10, str, str2, zab(arrayList2), str3);
    }

    @RecentlyNullable
    public static GoogleSignInOptions zaa(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i9 = 0; i9 < length; i9++) {
            hashSet.add(new Scope(jSONArray.getString(i9)));
        }
        String strOptString = jSONObject.has("accountName") ? jSONObject.optString("accountName") : null;
        return new GoogleSignInOptions(3, (ArrayList<Scope>) new ArrayList(hashSet), !TextUtils.isEmpty(strOptString) ? new Account(strOptString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.has("serverClientId") ? jSONObject.optString("serverClientId") : null, jSONObject.has("hostedDomain") ? jSONObject.optString("hostedDomain") : null, new HashMap(), (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Integer, GoogleSignInOptionsExtensionParcelable> zab(List<GoogleSignInOptionsExtensionParcelable> list) {
        HashMap map = new HashMap();
        if (list == null) {
            return map;
        }
        for (GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : list) {
            map.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
        }
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0052 A[Catch: ClassCastException -> 0x008f, TryCatch #0 {ClassCastException -> 0x008f, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004a, B:24:0x0052, B:29:0x0069, B:31:0x0071, B:33:0x0079, B:35:0x0081, B:27:0x005d, B:20:0x0040), top: B:41:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005d A[Catch: ClassCastException -> 0x008f, TryCatch #0 {ClassCastException -> 0x008f, blocks: (B:5:0x0004, B:7:0x000e, B:10:0x0018, B:12:0x0028, B:15:0x0035, B:17:0x0039, B:22:0x004a, B:24:0x0052, B:29:0x0069, B:31:0x0071, B:33:0x0079, B:35:0x0081, B:27:0x005d, B:20:0x0040), top: B:41:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008d A[RETURN] */
    @RecentlyNonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zan.size() <= 0 && googleSignInOptions.zan.size() <= 0 && this.zag.size() == googleSignInOptions.getScopes().size() && this.zag.containsAll(googleSignInOptions.getScopes())) {
                Account account = this.zah;
                if (account == null) {
                    if (googleSignInOptions.getAccount() == null) {
                        if (TextUtils.isEmpty(this.zal)) {
                            if (this.zal.equals(googleSignInOptions.getServerClientId())) {
                                if (this.zak == googleSignInOptions.isForceCodeForRefreshToken()) {
                                    if (!TextUtils.equals(this.zao, googleSignInOptions.getLogSessionId())) {
                                    }
                                }
                            }
                        } else if (TextUtils.isEmpty(googleSignInOptions.getServerClientId())) {
                            if (this.zak == googleSignInOptions.isForceCodeForRefreshToken() && this.zai == googleSignInOptions.isIdTokenRequested() && this.zaj == googleSignInOptions.isServerAuthCodeRequested()) {
                                if (!TextUtils.equals(this.zao, googleSignInOptions.getLogSessionId())) {
                                    return true;
                                }
                            }
                        }
                    }
                } else if (account.equals(googleSignInOptions.getAccount())) {
                    if (TextUtils.isEmpty(this.zal)) {
                    }
                }
            }
        } catch (ClassCastException unused) {
        }
        return false;
    }

    @RecentlyNullable
    @KeepForSdk
    public Account getAccount() {
        return this.zah;
    }

    @RecentlyNonNull
    @KeepForSdk
    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zan;
    }

    @RecentlyNullable
    @KeepForSdk
    public String getLogSessionId() {
        return this.zao;
    }

    @RecentlyNonNull
    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.zag;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    @RecentlyNonNull
    @KeepForSdk
    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zag);
    }

    @RecentlyNullable
    @KeepForSdk
    public String getServerClientId() {
        return this.zal;
    }

    @RecentlyNonNull
    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Scope> arrayList2 = this.zag;
        int size = arrayList2.size();
        int i9 = 0;
        while (i9 < size) {
            Scope scope = arrayList2.get(i9);
            i9++;
            arrayList.add(scope.getScopeUri());
        }
        Collections.sort(arrayList);
        return new HashAccumulator().addObject(arrayList).addObject(this.zah).addObject(this.zal).zaa(this.zak).zaa(this.zai).zaa(this.zaj).addObject(this.zao).hash();
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean isForceCodeForRefreshToken() {
        return this.zak;
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean isIdTokenRequested() {
        return this.zai;
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean isServerAuthCodeRequested() {
        return this.zaj;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaf);
        SafeParcelWriter.writeTypedList(parcel, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getAccount(), i9, false);
        SafeParcelWriter.writeBoolean(parcel, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zam, false);
        SafeParcelWriter.writeTypedList(parcel, 9, getExtensions(), false);
        SafeParcelWriter.writeString(parcel, 10, getLogSessionId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private GoogleSignInOptions(int i9, ArrayList<Scope> arrayList, Account account, boolean z8, boolean z9, boolean z10, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map, String str3) {
        this.zaf = i9;
        this.zag = arrayList;
        this.zah = account;
        this.zai = z8;
        this.zaj = z9;
        this.zak = z10;
        this.zal = str;
        this.zam = str2;
        this.zan = new ArrayList<>(map.values());
        this.zap = map;
        this.zao = str3;
    }

    private final JSONObject zab() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zag, zaq);
            ArrayList<Scope> arrayList = this.zag;
            int size = arrayList.size();
            int i9 = 0;
            while (i9 < size) {
                Scope scope = arrayList.get(i9);
                i9++;
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.zah;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.zai);
            jSONObject.put("forceCodeForRefreshToken", this.zak);
            jSONObject.put("serverAuthRequested", this.zaj);
            if (!TextUtils.isEmpty(this.zal)) {
                jSONObject.put("serverClientId", this.zal);
            }
            if (!TextUtils.isEmpty(this.zam)) {
                jSONObject.put("hostedDomain", this.zam);
            }
            return jSONObject;
        } catch (JSONException e9) {
            throw new RuntimeException(e9);
        }
    }

    public /* synthetic */ GoogleSignInOptions(int i9, ArrayList arrayList, Account account, boolean z8, boolean z9, boolean z10, String str, String str2, Map map, String str3, zac zacVar) {
        this(3, (ArrayList<Scope>) arrayList, account, z8, z9, z10, str, str2, (Map<Integer, GoogleSignInOptionsExtensionParcelable>) map, str3);
    }

    @RecentlyNonNull
    public final String zaa() {
        return zab().toString();
    }
}
