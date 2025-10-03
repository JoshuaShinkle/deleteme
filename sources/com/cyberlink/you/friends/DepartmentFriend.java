package com.cyberlink.you.friends;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class DepartmentFriend implements Parcelable {
    public static final Parcelable.Creator<DepartmentFriend> CREATOR = new C3039a();

    /* renamed from: b */
    public long f13626b;

    /* renamed from: c */
    public String f13627c;

    /* renamed from: d */
    public long f13628d;

    /* renamed from: e */
    public String f13629e;

    /* renamed from: f */
    public String f13630f;

    /* renamed from: g */
    public String f13631g;

    /* renamed from: h */
    public String f13632h;

    /* renamed from: i */
    public String f13633i;

    /* renamed from: j */
    public String f13634j;

    /* renamed from: k */
    public boolean f13635k;

    /* renamed from: l */
    public boolean f13636l;

    /* renamed from: m */
    public boolean f13637m;

    /* renamed from: n */
    public String f13638n;

    /* renamed from: o */
    public String f13639o;

    /* renamed from: p */
    public String f13640p;

    /* renamed from: q */
    public boolean f13641q;

    /* renamed from: r */
    public long f13642r;

    /* renamed from: com.cyberlink.you.friends.DepartmentFriend$a */
    public class C3039a implements Parcelable.Creator<DepartmentFriend> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DepartmentFriend createFromParcel(Parcel parcel) {
            return new DepartmentFriend(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DepartmentFriend[] newArray(int i9) {
            return new DepartmentFriend[i9];
        }
    }

    public DepartmentFriend() {
        this.f13626b = -1L;
        this.f13627c = "";
        this.f13628d = -1L;
        this.f13629e = "";
        this.f13630f = "";
        this.f13631g = "";
        this.f13632h = "";
        this.f13633i = "";
        this.f13634j = "";
        this.f13635k = false;
        this.f13636l = false;
        this.f13637m = false;
        this.f13638n = "";
        this.f13639o = "";
        this.f13640p = "";
        this.f13641q = false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13626b);
        parcel.writeString(this.f13627c);
        parcel.writeLong(this.f13628d);
        parcel.writeString(this.f13629e);
        parcel.writeString(this.f13630f);
        parcel.writeString(this.f13631g);
        parcel.writeString(this.f13632h);
        parcel.writeString(this.f13633i);
        parcel.writeString(this.f13634j);
        parcel.writeInt(this.f13635k ? 1 : 0);
        parcel.writeInt(this.f13636l ? 1 : 0);
        parcel.writeInt(this.f13637m ? 1 : 0);
        parcel.writeString(this.f13638n);
        parcel.writeString(this.f13639o);
        parcel.writeString(this.f13640p);
        parcel.writeInt(this.f13641q ? 1 : 0);
    }

    public DepartmentFriend(Parcel parcel) {
        this.f13626b = parcel.readLong();
        this.f13627c = parcel.readString();
        this.f13628d = parcel.readLong();
        this.f13629e = parcel.readString();
        this.f13630f = parcel.readString();
        this.f13631g = parcel.readString();
        this.f13632h = parcel.readString();
        this.f13633i = parcel.readString();
        this.f13634j = parcel.readString();
        this.f13635k = parcel.readInt() != 0;
        this.f13636l = parcel.readInt() != 0;
        this.f13637m = parcel.readInt() != 0;
        this.f13638n = parcel.readString();
        this.f13639o = parcel.readString();
        this.f13640p = parcel.readString();
        this.f13641q = parcel.readInt() != 0;
    }
}
