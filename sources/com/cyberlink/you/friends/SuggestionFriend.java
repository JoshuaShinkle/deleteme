package com.cyberlink.you.friends;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SuggestionFriend implements Parcelable {
    public static final Parcelable.Creator<SuggestionFriend> CREATOR = new C3059a();

    /* renamed from: b */
    public String f13758b;

    /* renamed from: c */
    public long f13759c;

    /* renamed from: d */
    public String f13760d;

    /* renamed from: e */
    public String f13761e;

    /* renamed from: f */
    public String f13762f;

    /* renamed from: g */
    public String f13763g;

    /* renamed from: h */
    public String f13764h;

    /* renamed from: i */
    public long f13765i;

    /* renamed from: j */
    public String f13766j;

    /* renamed from: k */
    public String f13767k;

    /* renamed from: l */
    public boolean f13768l;

    /* renamed from: com.cyberlink.you.friends.SuggestionFriend$a */
    public class C3059a implements Parcelable.Creator<SuggestionFriend> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SuggestionFriend createFromParcel(Parcel parcel) {
            return new SuggestionFriend(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SuggestionFriend[] newArray(int i9) {
            return new SuggestionFriend[i9];
        }
    }

    public SuggestionFriend() {
        this.f13758b = "";
        this.f13759c = -1L;
        this.f13760d = "";
        this.f13761e = "";
        this.f13762f = "";
        this.f13765i = -1L;
        this.f13764h = "";
        this.f13766j = "";
        this.f13763g = "";
        this.f13767k = "";
        this.f13768l = false;
    }

    /* renamed from: a */
    public String m15767a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("inviteStatus", this.f13758b);
            jSONObject.put("userId", this.f13759c);
            jSONObject.put("avatar", this.f13760d);
            jSONObject.put("displayName", this.f13761e);
            jSONObject.put("sourceType", this.f13762f);
            jSONObject.put("createdTime", this.f13765i);
            jSONObject.put("cover", this.f13764h);
            jSONObject.put("statusMessage", this.f13766j);
            jSONObject.put("country", this.f13763g);
            jSONObject.put("publicId", this.f13767k);
            jSONObject.put("isFriend", this.f13768l);
            return jSONObject.toString();
        } catch (JSONException e9) {
            e9.printStackTrace();
            return "";
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof SuggestionFriend) && this.f13759c == ((SuggestionFriend) obj).f13759c;
    }

    public int hashCode() {
        return Long.valueOf(this.f13759c).hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f13758b);
        parcel.writeLong(this.f13759c);
        parcel.writeString(this.f13760d);
        parcel.writeString(this.f13761e);
        parcel.writeString(this.f13762f);
        parcel.writeLong(this.f13765i);
        parcel.writeString(this.f13764h);
        parcel.writeString(this.f13766j);
        parcel.writeString(this.f13763g);
        parcel.writeString(this.f13767k);
        parcel.writeInt(this.f13768l ? 1 : 0);
    }

    public SuggestionFriend(JSONObject jSONObject) throws JSONException {
        try {
            this.f13758b = jSONObject.getString("inviteStatus");
            this.f13759c = jSONObject.getLong("userId");
            this.f13760d = jSONObject.getString("avatar");
            this.f13761e = jSONObject.getString("displayName");
            String string = "";
            if (jSONObject.has("sourceType")) {
                this.f13762f = jSONObject.isNull("sourceType") ? "" : jSONObject.getString("sourceType");
            }
            this.f13765i = jSONObject.getLong("createdTime");
            if (jSONObject.has("cover")) {
                this.f13764h = jSONObject.isNull("cover") ? "" : jSONObject.getString("cover");
            }
            if (jSONObject.has("statusMessage")) {
                this.f13766j = jSONObject.isNull("statusMessage") ? "" : jSONObject.getString("statusMessage");
            }
            if (jSONObject.has("country")) {
                this.f13763g = jSONObject.isNull("country") ? "" : jSONObject.getString("country");
            }
            if (jSONObject.has("publicId")) {
                if (!jSONObject.isNull("publicId")) {
                    string = jSONObject.getString("publicId");
                }
                this.f13767k = string;
            }
            if (jSONObject.has("isFriend")) {
                this.f13768l = !jSONObject.isNull("isFriend") && jSONObject.getBoolean("isFriend");
            }
        } catch (Exception unused) {
            Log.e("SuggestionFriend", "Parse SuggestionFriendObject Error");
        }
    }

    public SuggestionFriend(Parcel parcel) {
        this.f13758b = parcel.readString();
        this.f13759c = parcel.readLong();
        this.f13760d = parcel.readString();
        this.f13761e = parcel.readString();
        this.f13762f = parcel.readString();
        this.f13765i = parcel.readLong();
        this.f13764h = parcel.readString();
        this.f13766j = parcel.readString();
        this.f13763g = parcel.readString();
        this.f13767k = parcel.readString();
        this.f13768l = parcel.readInt() != 0;
    }
}
