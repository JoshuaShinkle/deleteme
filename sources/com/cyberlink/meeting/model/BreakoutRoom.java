package com.cyberlink.meeting.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BreakoutRoom implements Parcelable {
    public static final Parcelable.Creator<BreakoutRoom> CREATOR = new C1261a();

    /* renamed from: b */
    public String f6327b;

    /* renamed from: c */
    public int f6328c;

    /* renamed from: d */
    public String f6329d;

    /* renamed from: e */
    public String f6330e;

    /* renamed from: f */
    public long f6331f;

    /* renamed from: g */
    public long f6332g;

    /* renamed from: com.cyberlink.meeting.model.BreakoutRoom$a */
    public class C1261a implements Parcelable.Creator<BreakoutRoom> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public BreakoutRoom createFromParcel(Parcel parcel) {
            return new BreakoutRoom(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public BreakoutRoom[] newArray(int i9) {
            return new BreakoutRoom[i9];
        }
    }

    public BreakoutRoom() {
        this.f6327b = "";
        this.f6328c = 0;
        this.f6329d = "";
        this.f6330e = "";
        this.f6331f = 0L;
        this.f6332g = 0L;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f6327b);
        parcel.writeInt(this.f6328c);
        parcel.writeString(this.f6329d);
        parcel.writeString(this.f6330e);
        parcel.writeLong(this.f6331f);
        parcel.writeLong(this.f6332g);
    }

    public BreakoutRoom(Parcel parcel) {
        this.f6327b = parcel.readString();
        this.f6328c = parcel.readInt();
        this.f6329d = parcel.readString();
        this.f6330e = parcel.readString();
        this.f6331f = parcel.readLong();
        this.f6332g = parcel.readLong();
    }
}
