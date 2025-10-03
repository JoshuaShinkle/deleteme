package com.cyberlink.you.database;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.net.HttpHeaders;

/* loaded from: classes.dex */
public class PhoneCallObj implements Parcelable {
    public static final Parcelable.Creator<PhoneCallObj> CREATOR = new C2940a();

    /* renamed from: b */
    public long f13031b;

    /* renamed from: c */
    public String f13032c;

    /* renamed from: d */
    public String f13033d;

    /* renamed from: e */
    public boolean f13034e;

    /* renamed from: f */
    public boolean f13035f;

    /* renamed from: g */
    public boolean f13036g;

    /* renamed from: h */
    public long f13037h;

    /* renamed from: i */
    public boolean f13038i;

    /* renamed from: j */
    public String f13039j;

    /* renamed from: com.cyberlink.you.database.PhoneCallObj$a */
    public class C2940a implements Parcelable.Creator<PhoneCallObj> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PhoneCallObj createFromParcel(Parcel parcel) {
            return new PhoneCallObj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PhoneCallObj[] newArray(int i9) {
            return new PhoneCallObj[i9];
        }
    }

    public PhoneCallObj(long j9, String str, String str2, boolean z8, boolean z9, boolean z10, long j10, boolean z11, String str3) {
        this.f13031b = j9;
        this.f13032c = str;
        this.f13033d = str2;
        this.f13034e = z8;
        this.f13035f = z9;
        this.f13036g = z10;
        this.f13037h = j10;
        this.f13038i = z11;
        this.f13039j = str3;
    }

    /* renamed from: a */
    public ContentValues m14790a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(this.f13031b));
        contentValues.put("Number", this.f13032c);
        contentValues.put("DisplayName", this.f13033d);
        contentValues.put("IsExtension", Boolean.valueOf(this.f13035f));
        contentValues.put("IsReceive", Boolean.valueOf(this.f13034e));
        contentValues.put("IsMissing", Boolean.valueOf(this.f13036g));
        contentValues.put(HttpHeaders.DATE, Long.valueOf(this.f13037h));
        contentValues.put("isFromContact", Boolean.valueOf(this.f13038i));
        contentValues.put("UserId", this.f13039j);
        return contentValues;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f13031b == ((PhoneCallObj) obj).f13031b;
    }

    public int hashCode() {
        long j9 = this.f13031b;
        return 31 + ((int) (j9 ^ (j9 >>> 32)));
    }

    public String toString() {
        return "PhoneCallObj [number=" + this.f13032c + "\n displayName=" + this.f13033d + "\n isReceive=" + this.f13034e + "\n isExtension=" + this.f13035f + "\n isMissing=" + this.f13036g + "\n date=" + this.f13037h + "\n isContact=" + this.f13038i + "\n userId=" + this.f13039j + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13031b);
        parcel.writeString(this.f13032c);
        parcel.writeString(this.f13033d);
        parcel.writeInt(this.f13034e ? 1 : 0);
        parcel.writeInt(this.f13035f ? 1 : 0);
        parcel.writeInt(this.f13036g ? 1 : 0);
        parcel.writeLong(this.f13037h);
        parcel.writeInt(this.f13038i ? 1 : 0);
        parcel.writeString(this.f13039j);
    }

    public PhoneCallObj(Parcel parcel) {
        this.f13031b = parcel.readLong();
        this.f13032c = parcel.readString();
        this.f13033d = parcel.readString();
        this.f13034e = parcel.readInt() != 0;
        this.f13035f = parcel.readInt() != 0;
        this.f13036g = parcel.readInt() != 0;
        this.f13037h = parcel.readLong();
        this.f13038i = parcel.readInt() != 0;
        this.f13039j = parcel.readString();
    }
}
