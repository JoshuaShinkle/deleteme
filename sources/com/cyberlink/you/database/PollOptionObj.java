package com.cyberlink.you.database;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class PollOptionObj extends BaseObj {

    /* renamed from: b */
    public long f13041b;

    /* renamed from: c */
    public long f13042c;

    /* renamed from: d */
    public int f13043d;

    /* renamed from: e */
    public String f13044e;

    /* renamed from: f */
    public int f13045f;

    /* renamed from: g */
    public boolean f13046g;

    /* renamed from: h */
    public long f13047h;

    /* renamed from: i */
    public String f13048i;

    /* renamed from: j */
    public long f13049j;

    /* renamed from: k */
    public static List<String> f13040k = Arrays.asList("optionId", "topicId", "optionOrder", "optionDescription", "numberOfPolls", "isVoted", "lastModified", "optionComment", "lastVotedTime");
    public static final Parcelable.Creator<PollOptionObj> CREATOR = new C2941a();

    /* renamed from: com.cyberlink.you.database.PollOptionObj$a */
    public class C2941a implements Parcelable.Creator<PollOptionObj> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PollOptionObj createFromParcel(Parcel parcel) {
            return new PollOptionObj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PollOptionObj[] newArray(int i9) {
            return new PollOptionObj[i9];
        }
    }

    public PollOptionObj(long j9, long j10, int i9, String str, int i10, boolean z8, long j11, String str2, long j12) {
        this.f13041b = j9;
        this.f13042c = j10;
        this.f13043d = i9;
        this.f13044e = str;
        this.f13045f = i10;
        this.f13046g = z8;
        this.f13047h = j11;
        this.f13048i = str2;
        this.f13049j = j12;
    }

    @Override // com.cyberlink.you.database.BaseObj
    /* renamed from: a */
    public ContentValues mo14672a() {
        return mo14673b(m14793c());
    }

    @Override // com.cyberlink.you.database.BaseObj
    /* renamed from: b */
    public ContentValues mo14673b(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                if (str.equals("optionId")) {
                    contentValues.put("optionId", Long.valueOf(this.f13041b));
                } else if (str.equals("topicId")) {
                    contentValues.put("topicId", Long.valueOf(this.f13042c));
                } else if (str.equals("optionOrder")) {
                    contentValues.put("optionOrder", Integer.valueOf(this.f13043d));
                } else if (str.equals("optionDescription")) {
                    contentValues.put("optionDescription", this.f13044e);
                } else if (str.equals("numberOfPolls")) {
                    contentValues.put("numberOfPolls", Integer.valueOf(this.f13045f));
                } else if (str.equals("isVoted")) {
                    contentValues.put("isVoted", Boolean.valueOf(this.f13046g));
                } else if (str.equals("lastModified")) {
                    contentValues.put("lastModified", Long.valueOf(this.f13047h));
                } else if (str.equals("optionComment")) {
                    contentValues.put("optionComment", this.f13048i);
                } else if (str.equals("lastVotedTime")) {
                    contentValues.put("lastVotedTime", Long.valueOf(this.f13049j));
                }
            }
        }
        return contentValues;
    }

    /* renamed from: c */
    public List<String> m14793c() {
        return f13040k;
    }

    /* renamed from: d */
    public void m14794d(PollOptionObj pollOptionObj) {
        this.f13041b = pollOptionObj.f13041b;
        this.f13042c = pollOptionObj.f13042c;
        this.f13043d = pollOptionObj.f13043d;
        this.f13044e = pollOptionObj.f13044e;
        this.f13045f = pollOptionObj.f13045f;
        this.f13046g = pollOptionObj.f13046g;
        this.f13047h = pollOptionObj.f13047h;
        this.f13048i = pollOptionObj.f13048i;
        this.f13049j = pollOptionObj.f13049j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f13041b == ((PollOptionObj) obj).f13041b;
    }

    public int hashCode() {
        long j9 = this.f13041b;
        return 31 + ((int) (j9 ^ (j9 >>> 32)));
    }

    public String toString() {
        return "PollOptionObj [optionId=" + this.f13041b + "\n topicId=" + this.f13042c + "\n optionOrder=" + this.f13043d + "\n optionDescription=" + this.f13044e + "\n numberOfPolls=" + this.f13045f + "\n isVoted=" + this.f13046g + "\n lastModified=" + this.f13047h + "\n optionComment=" + this.f13048i + "\n lastVotedTime=" + this.f13049j + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13041b);
        parcel.writeLong(this.f13042c);
        parcel.writeInt(this.f13043d);
        parcel.writeString(this.f13044e);
        parcel.writeInt(this.f13045f);
        parcel.writeInt(this.f13046g ? 1 : 0);
        parcel.writeLong(this.f13047h);
        parcel.writeString(this.f13048i);
        parcel.writeLong(this.f13049j);
    }

    public PollOptionObj(Parcel parcel) {
        this.f13041b = parcel.readLong();
        this.f13042c = parcel.readLong();
        this.f13043d = parcel.readInt();
        this.f13044e = parcel.readString();
        this.f13045f = parcel.readInt();
        this.f13046g = parcel.readInt() != 0;
        this.f13047h = parcel.readLong();
        this.f13048i = parcel.readString();
        this.f13049j = parcel.readLong();
    }
}
