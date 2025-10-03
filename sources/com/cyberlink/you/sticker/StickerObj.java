package com.cyberlink.you.sticker;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.utility.CLUtility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p056e4.C4754a;

/* loaded from: classes.dex */
public class StickerObj implements Parcelable {
    public static final Parcelable.Creator<StickerObj> CREATOR = new C3113a();

    /* renamed from: b */
    public long f14335b;

    /* renamed from: c */
    public long f14336c;

    /* renamed from: d */
    public long f14337d;

    /* renamed from: e */
    public long f14338e;

    /* renamed from: f */
    public Date f14339f;

    /* renamed from: g */
    public String f14340g;

    /* renamed from: h */
    public String f14341h;

    /* renamed from: i */
    public String f14342i;

    /* renamed from: j */
    public String f14343j;

    /* renamed from: k */
    public int f14344k;

    /* renamed from: l */
    public int f14345l;

    /* renamed from: m */
    public List<String> f14346m;

    /* renamed from: n */
    public int f14347n;

    /* renamed from: com.cyberlink.you.sticker.StickerObj$a */
    public class C3113a implements Parcelable.Creator<StickerObj> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StickerObj createFromParcel(Parcel parcel) {
            return new StickerObj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public StickerObj[] newArray(int i9) {
            return new StickerObj[i9];
        }
    }

    public StickerObj(long j9, long j10, long j11, long j12, long j13, String str, String str2, int i9, int i10) {
        this(j9, j10, j11, j12, j13, str, str2, i9, i10, "", 0);
    }

    /* renamed from: a */
    public int m16276a() {
        return this.f14347n;
    }

    /* renamed from: b */
    public List<String> m16277b() {
        return this.f14346m;
    }

    /* renamed from: c */
    public String m16278c() {
        List<String> list = this.f14346m;
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.f14346m.iterator();
        while (it.hasNext()) {
            sb.append(it.next() + ",");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }

    /* renamed from: d */
    public int m16279d() {
        return this.f14345l;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public long m16280e() {
        return this.f14335b;
    }

    /* renamed from: f */
    public Date m16281f() {
        return this.f14339f;
    }

    /* renamed from: g */
    public String m16282g() {
        return this.f14341h;
    }

    /* renamed from: h */
    public String m16283h() {
        return this.f14340g;
    }

    /* renamed from: i */
    public long m16284i() {
        return this.f14337d;
    }

    /* renamed from: j */
    public long m16285j() {
        return this.f14336c;
    }

    /* renamed from: k */
    public long m16286k() {
        return this.f14338e;
    }

    /* renamed from: l */
    public String m16287l() {
        return this.f14343j;
    }

    /* renamed from: m */
    public String m16288m() {
        return this.f14342i;
    }

    /* renamed from: n */
    public int m16289n() {
        return this.f14344k;
    }

    /* renamed from: o */
    public void m16290o() throws JSONException, IOException {
        if (this.f14346m == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(CLUtility.m16541h1(this.f14337d));
            String str = File.separator;
            sb.append(str);
            String strM16526d2 = CLUtility.m16526d2((sb.toString() + String.valueOf(this.f14336c)) + str + "info.json");
            if (strM16526d2 == null) {
                this.f14347n = 0;
                return;
            }
            this.f14346m = new ArrayList();
            try {
                JSONObject jSONObject = new JSONObject(strM16526d2);
                JSONArray jSONArray = jSONObject.getJSONArray("images");
                this.f14347n = ((int) Math.round(jSONObject.getDouble("duration") * 1000.0d)) / jSONArray.length();
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    try {
                        String string = jSONArray.getString(i9);
                        if (C4754a.m18874d(string)) {
                            this.f14346m.add(string);
                        }
                    } catch (JSONException e9) {
                        e9.printStackTrace();
                    }
                }
                C2950b0.m14924w().m15281i(this.f14336c, this);
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
        }
    }

    /* renamed from: p */
    public final void m16291p(String str) {
        if (str.isEmpty()) {
            return;
        }
        this.f14346m = new ArrayList();
        for (String str2 : str.split(",")) {
            if (C4754a.m18874d(str2)) {
                this.f14346m.add(str2);
            }
        }
    }

    /* renamed from: q */
    public void m16292q(int i9) {
        this.f14345l = i9;
    }

    /* renamed from: r */
    public void m16293r(String str) {
        this.f14341h = str;
    }

    /* renamed from: s */
    public void m16294s(String str) {
        this.f14343j = str;
    }

    /* renamed from: t */
    public void m16295t(int i9) {
        this.f14344k = i9;
    }

    public String toString() {
        return "ID: " + this.f14335b + ", " + super.toString();
    }

    /* renamed from: u */
    public ContentValues m16296u() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m16280e()));
        contentValues.put("StickerId", Long.valueOf(m16285j()));
        contentValues.put("PackId", Long.valueOf(m16284i()));
        contentValues.put("StickerOrder", Long.valueOf(m16286k()));
        contentValues.put("LastModified", Long.valueOf(m16281f().getTime()));
        contentValues.put("OriginalURL", m16283h());
        contentValues.put("OriginalLocalFilePath", m16282g());
        contentValues.put("ThumbnailURL", m16288m());
        contentValues.put("ThumbnailLocalFilePath", m16287l());
        contentValues.put("Height", Integer.valueOf(m16279d()));
        contentValues.put("Width", Integer.valueOf(m16289n()));
        contentValues.put("AnimPngFilename", m16278c());
        contentValues.put("Duration", Integer.valueOf(m16276a()));
        return contentValues;
    }

    /* renamed from: v */
    public ContentValues m16297v(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                if (str.equals("_id")) {
                    contentValues.put("_id", Long.valueOf(m16280e()));
                } else if (str.equals("StickerId")) {
                    contentValues.put("StickerId", Long.valueOf(m16285j()));
                } else if (str.equals("PackId")) {
                    contentValues.put("PackId", Long.valueOf(m16284i()));
                } else if (str.equals("StickerOrder")) {
                    contentValues.put("StickerOrder", Long.valueOf(m16286k()));
                } else if (str.equals("LastModified")) {
                    contentValues.put("LastModified", Long.valueOf(m16281f().getTime()));
                } else if (str.equals("OriginalURL")) {
                    contentValues.put("OriginalURL", m16283h());
                } else if (str.equals("OriginalLocalFilePath")) {
                    contentValues.put("OriginalLocalFilePath", m16282g());
                } else if (str.equals("ThumbnailURL")) {
                    contentValues.put("ThumbnailURL", m16288m());
                } else if (str.equals("ThumbnailLocalFilePath")) {
                    contentValues.put("ThumbnailLocalFilePath", m16287l());
                } else if (str.equals("Height")) {
                    contentValues.put("Height", Integer.valueOf(m16279d()));
                } else if (str.equals("Width")) {
                    contentValues.put("Width", Integer.valueOf(m16289n()));
                } else if (str.equals("AnimPngFilename")) {
                    contentValues.put("AnimPngFilename", m16278c());
                } else if (str.equals("Duration")) {
                    contentValues.put("Duration", Integer.valueOf(m16276a()));
                }
            }
        }
        return contentValues;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f14336c);
        parcel.writeLong(this.f14337d);
        parcel.writeLong(this.f14338e);
        Date date = this.f14339f;
        parcel.writeLong(date == null ? 0L : date.getTime());
        parcel.writeString(this.f14340g);
        parcel.writeString(this.f14341h);
        parcel.writeString(this.f14342i);
        parcel.writeString(this.f14343j);
        parcel.writeInt(this.f14344k);
        parcel.writeInt(this.f14345l);
    }

    public StickerObj(long j9, long j10, long j11, long j12, long j13, String str, String str2, int i9, int i10, String str3, int i11) {
        this.f14335b = j9;
        this.f14336c = j10;
        this.f14337d = j11;
        this.f14338e = j12;
        this.f14339f = new Date(j13);
        this.f14340g = str;
        this.f14342i = str2;
        this.f14341h = "_";
        this.f14343j = "_";
        this.f14344k = i9;
        this.f14345l = i10;
        m16291p(str3);
        this.f14347n = i11;
    }

    public StickerObj(long j9, long j10, long j11, long j12, long j13, String str, String str2, String str3, String str4, int i9, int i10) {
        this(j9, j10, j11, j12, j13, str, str2, str3, str4, i9, i10, "", 0);
    }

    public StickerObj(long j9, long j10, long j11, long j12, long j13, String str, String str2, String str3, String str4, int i9, int i10, String str5, int i11) {
        this.f14335b = j9;
        this.f14336c = j10;
        this.f14337d = j11;
        this.f14338e = j12;
        this.f14339f = new Date(j13);
        this.f14340g = str;
        this.f14341h = str2;
        this.f14342i = str3;
        this.f14343j = str4;
        this.f14344k = i9;
        this.f14345l = i10;
        m16291p(str5);
        this.f14347n = i11;
    }

    public StickerObj(Parcel parcel) {
        this.f14336c = parcel.readLong();
        this.f14337d = parcel.readLong();
        this.f14338e = parcel.readLong();
        this.f14339f = new Date(parcel.readLong());
        this.f14340g = parcel.readString();
        this.f14341h = parcel.readString();
        this.f14342i = parcel.readString();
        this.f14343j = parcel.readString();
        this.f14344k = parcel.readInt();
        this.f14345l = parcel.readInt();
    }
}
