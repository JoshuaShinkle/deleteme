package com.cyberlink.you.friends;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.SettingActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.AbstractC5146g0;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class UserInfo implements Parcelable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new C3060a();

    /* renamed from: G */
    public static boolean f13769G = true;

    /* renamed from: H */
    public static boolean f13770H = false;

    /* renamed from: A */
    public boolean f13771A;

    /* renamed from: B */
    public boolean f13772B;

    /* renamed from: C */
    public boolean f13773C;

    /* renamed from: D */
    public boolean f13774D;

    /* renamed from: E */
    public boolean f13775E;

    /* renamed from: F */
    public boolean f13776F;

    /* renamed from: b */
    public long f13777b;

    /* renamed from: c */
    public String f13778c;

    /* renamed from: d */
    public String f13779d;

    /* renamed from: e */
    public String f13780e;

    /* renamed from: f */
    public String f13781f;

    /* renamed from: g */
    public String f13782g;

    /* renamed from: h */
    public String f13783h;

    /* renamed from: i */
    public String f13784i;

    /* renamed from: j */
    public String f13785j;

    /* renamed from: k */
    public String f13786k;

    /* renamed from: l */
    public String f13787l;

    /* renamed from: m */
    public String f13788m;

    /* renamed from: n */
    public String f13789n;

    /* renamed from: o */
    public String f13790o;

    /* renamed from: p */
    public String f13791p;

    /* renamed from: q */
    public boolean f13792q;

    /* renamed from: r */
    public boolean f13793r;

    /* renamed from: s */
    public boolean f13794s;

    /* renamed from: t */
    public boolean f13795t;

    /* renamed from: u */
    public boolean f13796u;

    /* renamed from: v */
    public boolean f13797v;

    /* renamed from: w */
    public String f13798w;

    /* renamed from: x */
    public String f13799x;

    /* renamed from: y */
    public boolean f13800y;

    /* renamed from: z */
    public String f13801z;

    /* renamed from: com.cyberlink.you.friends.UserInfo$a */
    public class C3060a implements Parcelable.Creator<UserInfo> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public UserInfo[] newArray(int i9) {
            return new UserInfo[i9];
        }
    }

    public UserInfo() {
        this.f13772B = true;
        this.f13773C = false;
        this.f13774D = false;
        this.f13775E = false;
        this.f13776F = true;
        this.f13777b = 0L;
        this.f13778c = "";
        this.f13779d = "";
        this.f13780e = "";
        this.f13781f = "";
        this.f13782g = "";
        this.f13783h = "";
        this.f13785j = "";
        this.f13784i = "";
        this.f13786k = "";
        this.f13788m = "";
        this.f13787l = "";
        this.f13789n = "";
        this.f13790o = "";
        this.f13791p = "";
        this.f13792q = f13769G;
        this.f13793r = true;
        this.f13794s = f13770H;
        this.f13795t = false;
        this.f13796u = false;
        this.f13797v = false;
        this.f13798w = "";
        this.f13799x = "ON";
        this.f13801z = "OFF";
        this.f13800y = true;
    }

    /* renamed from: a */
    public JSONObject m15770a(String str) throws JSONException {
        String str2;
        JSONObject jSONObject = null;
        if (this.f13788m != null) {
            try {
                JSONArray jSONArray = new JSONArray(this.f13788m);
                int i9 = 0;
                while (true) {
                    if (i9 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i9);
                    if (jSONObject2.has("accountSource") && jSONObject2.getString("accountSource").equals(str)) {
                        jSONObject = jSONObject2;
                        break;
                    }
                    i9++;
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        if (str.equals("Phone") && jSONObject == null && (str2 = this.f13786k) != null && !str2.isEmpty()) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("account", this.f13786k);
                jSONObject.put("accountSource", "Phone");
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    public boolean m15771b() {
        return "ON".equalsIgnoreCase(this.f13799x) || "FORCE_ON".equalsIgnoreCase(this.f13799x);
    }

    /* renamed from: c */
    public boolean m15772c() {
        return "FORCE_OFF".equalsIgnoreCase(this.f13799x);
    }

    /* renamed from: d */
    public boolean m15773d() {
        return "ON".equalsIgnoreCase(this.f13801z) || "FORCE_ON".equalsIgnoreCase(this.f13801z);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public void m15774e(String str) {
        if (C6383t.m24517f(str)) {
            return;
        }
        this.f13801z = str;
    }

    /* renamed from: f */
    public String m15775f() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userId", this.f13777b);
            jSONObject.put("displayName", this.f13778c);
            jSONObject.put("avatar", this.f13779d);
            jSONObject.put("statusMessage", this.f13780e);
            jSONObject.put("publicId", this.f13781f);
            jSONObject.put("jid", this.f13782g);
            jSONObject.put("avatarAlbumId", this.f13783h);
            jSONObject.put("cover", this.f13785j);
            jSONObject.put("coverAlbumId", this.f13784i);
            jSONObject.put("accountPhone", this.f13786k);
            jSONObject.put("accounts", this.f13788m);
            jSONObject.put("hiddenAlbumId", this.f13787l);
            jSONObject.put("orgName", this.f13789n);
            jSONObject.put("department", this.f13790o);
            jSONObject.put("orgTitle", this.f13791p);
            jSONObject.put("attrs_notification_enabled", this.f13792q);
            jSONObject.put("attrs_profile_publicId_enabled", this.f13793r);
            jSONObject.put("attrs_profile_pin_enabled", this.f13794s);
            jSONObject.put("attrs_friend_autoInvite_enabled", this.f13795t);
            jSONObject.put("attrs_friend_autoAccept_enabled", this.f13796u);
            jSONObject.put("attrs_notification_hide_message_enabled", this.f13797v);
            jSONObject.put("user.email", this.f13798w);
            jSONObject.put("dou.allow.meeting.recording", this.f13799x);
            jSONObject.put("dou.e2ee.update.status", this.f13801z);
            jSONObject.put("profile.forward.include.sender", this.f13800y);
            jSONObject.put("peopleSearch.allowed", this.f13771A);
            return jSONObject.toString();
        } catch (Exception unused) {
            Log.d("UserInfo", "[toJsonString]convert to json string fail");
            return "";
        }
    }

    /* renamed from: g */
    public boolean m15776g(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f13777b = jSONObject.getLong("userId");
            this.f13778c = jSONObject.getString("displayName");
            this.f13779d = jSONObject.getString("avatar");
            this.f13780e = jSONObject.getString("statusMessage");
            this.f13781f = jSONObject.getString("publicId");
            this.f13782g = jSONObject.getString("jid");
            this.f13783h = jSONObject.getString("avatarAlbumId");
            this.f13785j = jSONObject.getString("cover");
            this.f13784i = jSONObject.getString("coverAlbumId");
            this.f13786k = jSONObject.getString("accountPhone");
            if (jSONObject.has("accounts")) {
                this.f13788m = jSONObject.getString("accounts");
            }
            try {
                this.f13787l = jSONObject.getString("hiddenAlbumId");
            } catch (Exception unused) {
                this.f13787l = "UserHidden:" + String.valueOf(this.f13777b);
            }
            this.f13789n = jSONObject.getString("orgName");
            this.f13790o = jSONObject.getString("department");
            this.f13791p = jSONObject.getString("orgTitle");
            this.f13792q = jSONObject.getBoolean("attrs_notification_enabled");
            this.f13793r = jSONObject.getBoolean("attrs_profile_publicId_enabled");
            if (jSONObject.has("attrs_profile_pin_enabled")) {
                this.f13794s = jSONObject.getBoolean("attrs_profile_pin_enabled");
            } else {
                this.f13794s = f13770H;
            }
            this.f13795t = jSONObject.getBoolean("attrs_friend_autoInvite_enabled");
            this.f13796u = jSONObject.getBoolean("attrs_friend_autoAccept_enabled");
            if (jSONObject.has("attrs_notification_hide_message_enabled")) {
                this.f13797v = jSONObject.getBoolean("attrs_notification_hide_message_enabled");
            } else {
                Context applicationContext = Globals.m7388i0().getApplicationContext();
                if (applicationContext != null) {
                    this.f13797v = !AbstractC5146g0.m20046d("setting_notification_show_preview", SettingActivity.f8945T, applicationContext);
                } else {
                    this.f13797v = !SettingActivity.f8945T;
                }
            }
            if (jSONObject.has("user.email")) {
                this.f13798w = jSONObject.getString("user.email");
            }
            if (jSONObject.has("dou.allow.meeting.recording")) {
                this.f13799x = jSONObject.getString("dou.allow.meeting.recording");
            }
            if (jSONObject.has("dou.e2ee.update.status")) {
                this.f13801z = jSONObject.getString("dou.e2ee.update.status");
            }
            if (jSONObject.has("profile.forward.include.sender")) {
                this.f13800y = jSONObject.getBoolean("profile.forward.include.sender");
            } else {
                this.f13800y = true;
            }
            if (jSONObject.has("peopleSearch.allowed")) {
                this.f13771A = jSONObject.getBoolean("peopleSearch.allowed");
            }
            return true;
        } catch (Exception unused2) {
            Log.d("UserInfo", "[updateByJsonString]convert to json string fail");
            return false;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13777b);
        parcel.writeString(this.f13778c);
        parcel.writeString(this.f13779d);
        parcel.writeString(this.f13780e);
        parcel.writeString(this.f13781f);
        parcel.writeString(this.f13782g);
        parcel.writeString(this.f13783h);
        parcel.writeString(this.f13785j);
        parcel.writeString(this.f13784i);
        parcel.writeString(this.f13786k);
        parcel.writeString(this.f13788m);
        parcel.writeString(this.f13787l);
        parcel.writeString(this.f13789n);
        parcel.writeString(this.f13790o);
        parcel.writeString(this.f13791p);
        parcel.writeByte(this.f13792q ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13793r ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13794s ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13795t ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13796u ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13797v ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f13798w);
        parcel.writeString(this.f13799x);
        parcel.writeString(this.f13801z);
        parcel.writeByte(this.f13800y ? (byte) 1 : (byte) 0);
    }

    public UserInfo(Parcel parcel) {
        this.f13772B = true;
        this.f13773C = false;
        this.f13774D = false;
        this.f13775E = false;
        this.f13776F = true;
        this.f13777b = parcel.readLong();
        this.f13778c = parcel.readString();
        this.f13779d = parcel.readString();
        this.f13780e = parcel.readString();
        this.f13781f = parcel.readString();
        this.f13782g = parcel.readString();
        this.f13783h = parcel.readString();
        this.f13785j = parcel.readString();
        this.f13784i = parcel.readString();
        this.f13786k = parcel.readString();
        this.f13788m = parcel.readString();
        this.f13787l = parcel.readString();
        this.f13789n = parcel.readString();
        this.f13790o = parcel.readString();
        this.f13791p = parcel.readString();
        this.f13792q = parcel.readByte() != 0;
        this.f13793r = parcel.readByte() != 0;
        this.f13794s = parcel.readByte() != 0;
        this.f13795t = parcel.readByte() != 0;
        this.f13796u = parcel.readByte() != 0;
        this.f13797v = parcel.readByte() != 0;
        this.f13798w = parcel.readString();
        this.f13799x = parcel.readString();
        this.f13801z = parcel.readString();
        this.f13800y = parcel.readByte() != 0;
    }
}
