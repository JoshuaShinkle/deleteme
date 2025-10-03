package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GoogleSignInAccountCreator")
/* loaded from: classes2.dex */
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zab();

    @VisibleForTesting
    private static Clock zaa = DefaultClock.getInstance();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zab;

    @SafeParcelable.Field(getter = "getId", m17520id = 2)
    private String zac;

    @SafeParcelable.Field(getter = "getIdToken", m17520id = 3)
    private String zad;

    @SafeParcelable.Field(getter = "getEmail", m17520id = 4)
    private String zae;

    @SafeParcelable.Field(getter = "getDisplayName", m17520id = 5)
    private String zaf;

    @SafeParcelable.Field(getter = "getPhotoUrl", m17520id = 6)
    private Uri zag;

    @SafeParcelable.Field(getter = "getServerAuthCode", m17520id = 7)
    private String zah;

    @SafeParcelable.Field(getter = "getExpirationTimeSecs", m17520id = 8)
    private long zai;

    @SafeParcelable.Field(getter = "getObfuscatedIdentifier", m17520id = 9)
    private String zaj;

    @SafeParcelable.Field(m17520id = 10)
    private List<Scope> zak;

    @SafeParcelable.Field(getter = "getGivenName", m17520id = 11)
    private String zal;

    @SafeParcelable.Field(getter = "getFamilyName", m17520id = 12)
    private String zam;
    private Set<Scope> zan = new HashSet();

    @SafeParcelable.Constructor
    public GoogleSignInAccount(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) String str2, @SafeParcelable.Param(m17521id = 4) String str3, @SafeParcelable.Param(m17521id = 5) String str4, @SafeParcelable.Param(m17521id = 6) Uri uri, @SafeParcelable.Param(m17521id = 7) String str5, @SafeParcelable.Param(m17521id = 8) long j9, @SafeParcelable.Param(m17521id = 9) String str6, @SafeParcelable.Param(m17521id = 10) List<Scope> list, @SafeParcelable.Param(m17521id = 11) String str7, @SafeParcelable.Param(m17521id = 12) String str8) {
        this.zab = i9;
        this.zac = str;
        this.zad = str2;
        this.zae = str3;
        this.zaf = str4;
        this.zag = uri;
        this.zah = str5;
        this.zai = j9;
        this.zaj = str6;
        this.zak = list;
        this.zal = str7;
        this.zam = str8;
    }

    @RecentlyNonNull
    @KeepForSdk
    public static GoogleSignInAccount createDefault() {
        Account account = new Account("<<default account>>", "com.google");
        return zaa(null, null, account.name, null, null, null, null, 0L, account.name, new HashSet());
    }

    @RecentlyNullable
    public static GoogleSignInAccount zaa(String str) throws JSONException, NumberFormatException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("photoUrl");
        Uri uri = !TextUtils.isEmpty(strOptString) ? Uri.parse(strOptString) : null;
        long j9 = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i9 = 0; i9 < length; i9++) {
            hashSet.add(new Scope(jSONArray.getString(i9)));
        }
        GoogleSignInAccount googleSignInAccountZaa = zaa(jSONObject.optString(TtmlNode.ATTR_ID), jSONObject.has("tokenId") ? jSONObject.optString("tokenId") : null, jSONObject.has(Scopes.EMAIL) ? jSONObject.optString(Scopes.EMAIL) : null, jSONObject.has("displayName") ? jSONObject.optString("displayName") : null, jSONObject.has("givenName") ? jSONObject.optString("givenName") : null, jSONObject.has("familyName") ? jSONObject.optString("familyName") : null, uri, Long.valueOf(j9), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        googleSignInAccountZaa.zah = jSONObject.has("serverAuthCode") ? jSONObject.optString("serverAuthCode") : null;
        return googleSignInAccountZaa;
    }

    private final JSONObject zac() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put(TtmlNode.ATTR_ID, getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put(Scopes.EMAIL, getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            Uri photoUrl = getPhotoUrl();
            if (photoUrl != null) {
                jSONObject.put("photoUrl", photoUrl.toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zai);
            jSONObject.put("obfuscatedIdentifier", this.zaj);
            JSONArray jSONArray = new JSONArray();
            List<Scope> list = this.zak;
            Scope[] scopeArr = (Scope[]) list.toArray(new Scope[list.size()]);
            Arrays.sort(scopeArr, zaa.zaa);
            for (Scope scope : scopeArr) {
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (JSONException e9) {
            throw new RuntimeException(e9);
        }
    }

    @RecentlyNonNull
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.zaj.equals(this.zaj) && googleSignInAccount.getRequestedScopes().equals(getRequestedScopes());
    }

    @RecentlyNullable
    public Account getAccount() {
        if (this.zae == null) {
            return null;
        }
        return new Account(this.zae, "com.google");
    }

    @RecentlyNullable
    public String getDisplayName() {
        return this.zaf;
    }

    @RecentlyNullable
    public String getEmail() {
        return this.zae;
    }

    @RecentlyNullable
    public String getFamilyName() {
        return this.zam;
    }

    @RecentlyNullable
    public String getGivenName() {
        return this.zal;
    }

    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zak);
    }

    @RecentlyNullable
    public String getId() {
        return this.zac;
    }

    @RecentlyNullable
    public String getIdToken() {
        return this.zad;
    }

    @RecentlyNullable
    public Uri getPhotoUrl() {
        return this.zag;
    }

    @KeepForSdk
    public Set<Scope> getRequestedScopes() {
        HashSet hashSet = new HashSet(this.zak);
        hashSet.addAll(this.zan);
        return hashSet;
    }

    @RecentlyNullable
    public String getServerAuthCode() {
        return this.zah;
    }

    @RecentlyNonNull
    public int hashCode() {
        return ((this.zaj.hashCode() + 527) * 31) + getRequestedScopes().hashCode();
    }

    @RecentlyNonNull
    @KeepForSdk
    public boolean isExpired() {
        return zaa.currentTimeMillis() / 1000 >= this.zai - 300;
    }

    @RecentlyNonNull
    @KeepForSdk
    public GoogleSignInAccount requestExtraScopes(@RecentlyNonNull Scope... scopeArr) {
        if (scopeArr != null) {
            Collections.addAll(this.zan, scopeArr);
        }
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zab);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, getPhotoUrl(), i9, false);
        SafeParcelWriter.writeString(parcel, 7, getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel, 8, this.zai);
        SafeParcelWriter.writeString(parcel, 9, this.zaj, false);
        SafeParcelWriter.writeTypedList(parcel, 10, this.zak, false);
        SafeParcelWriter.writeString(parcel, 11, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 12, getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @RecentlyNonNull
    public final String zab() throws JSONException {
        JSONObject jSONObjectZac = zac();
        jSONObjectZac.remove("serverAuthCode");
        return jSONObjectZac.toString();
    }

    private static GoogleSignInAccount zaa(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l9, String str7, Set<Scope> set) {
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, (l9 == null ? Long.valueOf(zaa.currentTimeMillis() / 1000) : l9).longValue(), Preconditions.checkNotEmpty(str7), new ArrayList((Collection) Preconditions.checkNotNull(set)), str5, str6);
    }

    public final String zaa() {
        return this.zaj;
    }
}
