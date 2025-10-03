package com.cyberlink.you.friends;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CorpAccount implements Parcelable {
    public static final Parcelable.Creator<CorpAccount> CREATOR = new C3038a();

    /* renamed from: b */
    public final String f13613b;

    /* renamed from: c */
    public long f13614c;

    /* renamed from: d */
    public String f13615d;

    /* renamed from: e */
    public String f13616e;

    /* renamed from: f */
    public String f13617f;

    /* renamed from: g */
    public String f13618g;

    /* renamed from: h */
    public String f13619h;

    /* renamed from: i */
    public String f13620i;

    /* renamed from: j */
    public String f13621j;

    /* renamed from: k */
    public String f13622k;

    /* renamed from: l */
    public String f13623l;

    /* renamed from: m */
    public String f13624m;

    /* renamed from: n */
    public long f13625n;

    /* renamed from: com.cyberlink.you.friends.CorpAccount$a */
    public class C3038a implements Parcelable.Creator<CorpAccount> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CorpAccount createFromParcel(Parcel parcel) {
            return new CorpAccount(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CorpAccount[] newArray(int i9) {
            return new CorpAccount[i9];
        }
    }

    public CorpAccount() {
        this.f13613b = "CorpAccount";
        this.f13614c = 0L;
        this.f13615d = "";
        this.f13616e = "";
        this.f13617f = "";
        this.f13618g = "";
        this.f13619h = "";
        this.f13620i = "";
        this.f13621j = "";
        this.f13622k = "";
        this.f13623l = "";
        this.f13624m = "";
        this.f13625n = 0L;
    }

    /* renamed from: a */
    public List<Long> m15615a() {
        ArrayList arrayList = new ArrayList();
        String str = this.f13624m;
        if (str != null && !str.equals("")) {
            try {
                JSONArray jSONArray = new JSONArray(this.f13624m);
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    arrayList.add(Long.valueOf(jSONArray.getString(i9)));
                }
            } catch (Exception e9) {
                Log.d("CorpAccount", "[getPackIds] error = " + e9.getMessage());
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13614c);
        parcel.writeString(this.f13615d);
        parcel.writeString(this.f13616e);
        parcel.writeString(this.f13617f);
        parcel.writeString(this.f13618g);
        parcel.writeString(this.f13619h);
        parcel.writeString(this.f13620i);
        parcel.writeString(this.f13621j);
        parcel.writeString(this.f13622k);
        parcel.writeString(this.f13623l);
        parcel.writeString(this.f13624m);
        parcel.writeLong(this.f13625n);
    }

    public CorpAccount(JSONObject jSONObject) {
        this.f13613b = "CorpAccount";
        try {
            this.f13614c = jSONObject.getLong("userId");
            this.f13615d = jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
            this.f13616e = jSONObject.getString("displayName");
            this.f13617f = jSONObject.getString("avatar");
            this.f13618g = jSONObject.getString("category");
            this.f13619h = jSONObject.getString("cover");
            this.f13620i = jSONObject.getString("statusMessage");
            this.f13621j = jSONObject.getString("address");
            this.f13622k = jSONObject.getString("phoneNumber");
            this.f13623l = jSONObject.getString("siteUrl");
            this.f13624m = jSONObject.getString("packIds");
            this.f13625n = jSONObject.getLong("firstPublishTime");
        } catch (Exception unused) {
            Log.d("CorpAccount", "Parse CorpAccountObject Error");
        }
    }

    public CorpAccount(Parcel parcel) {
        this.f13613b = "CorpAccount";
        this.f13614c = parcel.readLong();
        this.f13615d = parcel.readString();
        this.f13616e = parcel.readString();
        this.f13617f = parcel.readString();
        this.f13618g = parcel.readString();
        this.f13619h = parcel.readString();
        this.f13620i = parcel.readString();
        this.f13621j = parcel.readString();
        this.f13622k = parcel.readString();
        this.f13623l = parcel.readString();
        this.f13624m = parcel.readString();
        this.f13625n = parcel.readLong();
    }
}
