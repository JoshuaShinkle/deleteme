package com.cyberlink.you.database;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.plus.PlusShare;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StickerPackObj implements Parcelable {
    public static final Parcelable.Creator<StickerPackObj> CREATOR = new C2942a();

    /* renamed from: b */
    public long f13050b;

    /* renamed from: c */
    public long f13051c;

    /* renamed from: d */
    public String f13052d;

    /* renamed from: e */
    public String f13053e;

    /* renamed from: f */
    public String f13054f;

    /* renamed from: g */
    public String f13055g;

    /* renamed from: h */
    public String f13056h;

    /* renamed from: i */
    public C2943b f13057i;

    /* renamed from: j */
    public Date f13058j;

    /* renamed from: k */
    public Status f13059k;

    /* renamed from: l */
    public long f13060l;

    /* renamed from: m */
    public String f13061m;

    /* renamed from: n */
    public String f13062n;

    /* renamed from: o */
    public String f13063o;

    /* renamed from: p */
    public boolean f13064p;

    /* renamed from: q */
    public String f13065q;

    /* renamed from: r */
    public int f13066r;

    /* renamed from: s */
    public boolean f13067s;

    public enum Status {
        NONE,
        BUILTIN,
        DOWNLOADED,
        NOT_DOWNLOADED
    }

    /* renamed from: com.cyberlink.you.database.StickerPackObj$a */
    public class C2942a implements Parcelable.Creator<StickerPackObj> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StickerPackObj createFromParcel(Parcel parcel) {
            return new StickerPackObj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public StickerPackObj[] newArray(int i9) {
            return new StickerPackObj[i9];
        }
    }

    /* renamed from: com.cyberlink.you.database.StickerPackObj$b */
    public static class C2943b {

        /* renamed from: a */
        public String f13073a;

        /* renamed from: b */
        public String f13074b;

        /* renamed from: c */
        public String f13075c;

        /* renamed from: d */
        public String f13076d;

        /* renamed from: e */
        public String f13077e;

        /* renamed from: f */
        public String f13078f;

        /* renamed from: g */
        public String f13079g;

        public C2943b() {
            this("", "", "_", "", "_", "", "_");
        }

        /* renamed from: b */
        public static C2943b m14824b(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            String[] strArrSplit = str.split(",");
            if (strArrSplit.length != 7) {
                return null;
            }
            return new C2943b(strArrSplit[0], strArrSplit[1], strArrSplit[2], strArrSplit[3], strArrSplit[4], strArrSplit[5], strArrSplit[6]);
        }

        /* renamed from: a */
        public String m14825a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("zip", this.f13073a);
                jSONObject.put("cover", this.f13074b);
                jSONObject.put("coverLocalFilePath", this.f13075c);
                jSONObject.put("thumbnail", this.f13076d);
                jSONObject.put("thumbnailLocalFilePath", this.f13077e);
                jSONObject.put("preview", this.f13078f);
                jSONObject.put("previewLocalFilePath", this.f13079g);
                return jSONObject.toString();
            } catch (JSONException e9) {
                e9.printStackTrace();
                return "";
            }
        }

        public String toString() {
            return this.f13073a + "," + this.f13074b + "," + this.f13075c + "," + this.f13076d + "," + this.f13077e + "," + this.f13078f + "," + this.f13079g;
        }

        public C2943b(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.f13073a = str;
            this.f13074b = str2;
            this.f13075c = str3;
            this.f13076d = str4;
            this.f13077e = str5;
            this.f13078f = str6;
            this.f13079g = str7;
        }

        public C2943b(JSONObject jSONObject) {
            try {
                this.f13073a = jSONObject.getString("zip");
                this.f13074b = jSONObject.getString("cover");
                this.f13075c = jSONObject.getString("coverLocalFilePath");
                this.f13076d = jSONObject.getString("thumbnail");
                this.f13077e = jSONObject.getString("thumbnailLocalFilePath");
                this.f13078f = jSONObject.getString("preview");
                this.f13079g = jSONObject.getString("previewLocalFilePath");
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
    }

    public StickerPackObj(long j9, long j10, String str, String str2, String str3, String str4, String str5, C2943b c2943b, Status status, long j11, long j12, String str6, String str7, String str8, boolean z8, String str9) {
        this.f13050b = j9;
        this.f13051c = j10;
        this.f13052d = str;
        this.f13053e = str2;
        this.f13058j = new Date(j11);
        this.f13055g = str4;
        this.f13056h = str5;
        this.f13057i = c2943b;
        this.f13059k = status;
        this.f13054f = str3;
        this.f13060l = j12;
        this.f13061m = str6;
        this.f13062n = str7;
        this.f13063o = str8;
        this.f13064p = z8;
        this.f13065q = str9;
    }

    /* renamed from: a */
    public String m14797a() {
        return this.f13055g;
    }

    /* renamed from: b */
    public int m14798b() {
        return this.f13066r;
    }

    /* renamed from: c */
    public String m14799c() {
        return this.f13056h;
    }

    /* renamed from: d */
    public String m14800d() {
        return this.f13065q;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public long m14801e() {
        return this.f13050b;
    }

    /* renamed from: f */
    public Date m14802f() {
        return this.f13058j;
    }

    /* renamed from: g */
    public long m14803g() {
        return this.f13051c;
    }

    /* renamed from: h */
    public String m14804h() {
        return this.f13054f;
    }

    /* renamed from: i */
    public String m14805i() {
        return this.f13052d;
    }

    /* renamed from: j */
    public String m14806j() {
        return this.f13053e;
    }

    /* renamed from: k */
    public long m14807k() {
        return this.f13060l;
    }

    /* renamed from: l */
    public String m14808l() {
        return this.f13061m;
    }

    /* renamed from: m */
    public String m14809m() {
        return this.f13062n;
    }

    /* renamed from: n */
    public String m14810n() {
        return this.f13063o;
    }

    /* renamed from: o */
    public Status m14811o() {
        return this.f13059k;
    }

    /* renamed from: p */
    public C2943b m14812p() {
        return this.f13057i;
    }

    /* renamed from: q */
    public boolean m14813q() {
        return this.f13067s;
    }

    /* renamed from: r */
    public boolean m14814r() {
        return !m14811o().equals(Status.NONE);
    }

    /* renamed from: s */
    public boolean m14815s() {
        return this.f13064p;
    }

    /* renamed from: t */
    public void m14816t(int i9) {
        this.f13066r = i9;
    }

    public String toString() {
        return "ID: " + this.f13050b + ", " + super.toString();
    }

    /* renamed from: u */
    public void m14817u(boolean z8) {
        this.f13067s = z8;
    }

    /* renamed from: v */
    public void m14818v(boolean z8) {
        this.f13064p = z8;
    }

    /* renamed from: w */
    public void m14819w(Status status) {
        this.f13059k = status;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13050b);
        parcel.writeLong(this.f13051c);
        parcel.writeString(this.f13052d);
        parcel.writeString(this.f13053e);
        parcel.writeLong(this.f13058j.getTime());
        parcel.writeString(this.f13055g);
        parcel.writeString(this.f13056h);
        parcel.writeString(this.f13057i.toString());
        parcel.writeString(this.f13059k.toString());
        parcel.writeString(this.f13054f);
        parcel.writeLong(this.f13060l);
        parcel.writeString(this.f13061m);
        parcel.writeString(this.f13062n);
        parcel.writeString(this.f13063o);
        parcel.writeByte(this.f13064p ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f13065q);
    }

    /* renamed from: x */
    public ContentValues m14820x() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m14801e()));
        contentValues.put("PackId", Long.valueOf(m14803g()));
        contentValues.put("PackType", m14805i());
        contentValues.put("PurchaseType", m14806j());
        contentValues.put("PackName", m14804h());
        contentValues.put("Description", m14797a());
        contentValues.put("Expiration", m14799c());
        contentValues.put("Url", m14812p().toString());
        contentValues.put("Status", m14811o().toString());
        contentValues.put("LastModified", Long.valueOf(m14802f().getTime()));
        contentValues.put("PublisherLastModified", Long.valueOf(m14807k()));
        contentValues.put("PublisherName", m14808l());
        contentValues.put("PublisherTitleOfUrl", m14809m());
        contentValues.put("PublisherUrl", m14810n());
        contentValues.put("isShowed", Boolean.valueOf(m14815s()));
        contentValues.put("iapItem", m14800d());
        return contentValues;
    }

    /* renamed from: y */
    public String m14821y() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TtmlNode.ATTR_ID, this.f13050b);
            jSONObject.put("packId", this.f13051c);
            jSONObject.put("packType", this.f13052d);
            jSONObject.put("purchaseType", this.f13053e);
            jSONObject.put("packName", this.f13054f);
            jSONObject.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.f13055g);
            jSONObject.put("expiration", this.f13056h);
            jSONObject.put("url", this.f13057i.m14825a());
            jSONObject.put("lastModified", this.f13058j.getTime());
            jSONObject.put("status", this.f13059k.toString());
            jSONObject.put("publishLastModified", this.f13060l);
            jSONObject.put("publisherName", this.f13061m);
            jSONObject.put("publisherTitleOfUrl", this.f13062n);
            jSONObject.put("publisherUrl", this.f13063o);
            jSONObject.put("isShowed", this.f13064p);
            jSONObject.put("iapItem", this.f13065q);
            return jSONObject.toString();
        } catch (JSONException e9) {
            e9.printStackTrace();
            return "";
        }
    }

    public StickerPackObj(JSONObject jSONObject) {
        try {
            this.f13050b = jSONObject.getLong(TtmlNode.ATTR_ID);
            this.f13051c = jSONObject.getLong("packId");
            this.f13052d = jSONObject.getString("packType");
            this.f13053e = jSONObject.getString("purchaseType");
            this.f13054f = jSONObject.getString("packName");
            this.f13055g = jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
            this.f13056h = jSONObject.getString("expiration");
            this.f13057i = new C2943b(new JSONObject(jSONObject.getString("url")));
            this.f13058j = new Date(jSONObject.getLong("lastModified"));
            this.f13059k = Status.valueOf(jSONObject.getString("status"));
            this.f13060l = jSONObject.getLong("publishLastModified");
            this.f13061m = jSONObject.getString("publisherName");
            this.f13062n = jSONObject.getString("publisherTitleOfUrl");
            this.f13063o = jSONObject.getString("publisherUrl");
            this.f13064p = jSONObject.getBoolean("isShowed");
            this.f13065q = jSONObject.getString("iapItem");
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    public StickerPackObj(Parcel parcel) {
        this.f13050b = parcel.readLong();
        this.f13051c = parcel.readLong();
        this.f13052d = parcel.readString();
        this.f13053e = parcel.readString();
        this.f13058j = new Date(parcel.readLong());
        this.f13055g = parcel.readString();
        this.f13056h = parcel.readString();
        this.f13057i = C2943b.m14824b(parcel.readString());
        this.f13059k = Status.valueOf(parcel.readString());
        this.f13054f = parcel.readString();
        this.f13060l = parcel.readLong();
        this.f13061m = parcel.readString();
        this.f13062n = parcel.readString();
        this.f13063o = parcel.readString();
        this.f13064p = parcel.readByte() != 0;
        this.f13065q = parcel.readString();
    }

    public StickerPackObj(long j9, long j10, String str, String str2, String str3, String str4, String str5, String str6, Status status, long j11, long j12, String str7, String str8, String str9, boolean z8, String str10) {
        this(j9, j10, str, str2, str3, str4, str5, C2943b.m14824b(str6), status, j11, j12, str7, str8, str9, z8, str10);
    }
}
