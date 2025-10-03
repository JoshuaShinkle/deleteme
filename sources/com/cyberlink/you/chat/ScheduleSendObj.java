package com.cyberlink.you.chat;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

/* loaded from: classes.dex */
public class ScheduleSendObj implements Parcelable {
    public static final Parcelable.Creator<ScheduleSendObj> CREATOR = new C2842a();

    /* renamed from: b */
    public String f12447b;

    /* renamed from: c */
    public String f12448c;

    /* renamed from: d */
    public int f12449d;

    /* renamed from: e */
    public Date f12450e;

    /* renamed from: f */
    public String f12451f;

    /* renamed from: g */
    public String f12452g;

    /* renamed from: com.cyberlink.you.chat.ScheduleSendObj$a */
    public class C2842a implements Parcelable.Creator<ScheduleSendObj> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ScheduleSendObj createFromParcel(Parcel parcel) {
            return new ScheduleSendObj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ScheduleSendObj[] newArray(int i9) {
            return new ScheduleSendObj[i9];
        }
    }

    public ScheduleSendObj() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f12447b);
        parcel.writeString(this.f12448c);
        parcel.writeInt(this.f12449d);
        parcel.writeLong(this.f12450e.getTime());
        parcel.writeString(this.f12451f);
        parcel.writeString(this.f12452g);
    }

    public ScheduleSendObj(Parcel parcel) {
        this.f12447b = parcel.readString();
        this.f12448c = parcel.readString();
        this.f12449d = parcel.readInt();
        this.f12450e = new Date(parcel.readLong());
        this.f12451f = parcel.readString();
        this.f12452g = parcel.readString();
    }
}
