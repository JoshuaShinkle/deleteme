package com.cyberlink.you.activity.poll;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class PollVoter implements Parcelable {
    public static final Parcelable.Creator<PollVoter> CREATOR = new C2384a();

    /* renamed from: b */
    public long f11055b;

    /* renamed from: c */
    public String f11056c;

    /* renamed from: d */
    public String f11057d;

    /* renamed from: com.cyberlink.you.activity.poll.PollVoter$a */
    public class C2384a implements Parcelable.Creator<PollVoter> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PollVoter createFromParcel(Parcel parcel) {
            return new PollVoter(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PollVoter[] newArray(int i9) {
            return new PollVoter[i9];
        }
    }

    public PollVoter() {
        this.f11055b = -1L;
        this.f11056c = "";
        this.f11057d = "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f11055b);
        parcel.writeString(this.f11056c);
        parcel.writeString(this.f11057d);
    }

    public PollVoter(Parcel parcel) {
        this.f11055b = parcel.readLong();
        this.f11056c = parcel.readString();
        this.f11057d = parcel.readString();
    }
}
