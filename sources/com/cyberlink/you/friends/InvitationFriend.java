package com.cyberlink.you.friends;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.Collator;
import java.util.Comparator;

/* loaded from: classes.dex */
public class InvitationFriend implements Parcelable {
    public static final Parcelable.Creator<InvitationFriend> CREATOR = new C3057a();

    /* renamed from: b */
    public String f13741b;

    /* renamed from: c */
    public String f13742c;

    /* renamed from: d */
    public String f13743d;

    /* renamed from: e */
    public long f13744e;

    /* renamed from: f */
    public String f13745f;

    /* renamed from: g */
    public long f13746g;

    /* renamed from: h */
    public long f13747h;

    /* renamed from: i */
    public String f13748i;

    /* renamed from: j */
    public String f13749j;

    /* renamed from: k */
    public String f13750k;

    /* renamed from: l */
    public String f13751l;

    /* renamed from: m */
    public String f13752m;

    /* renamed from: n */
    public String f13753n;

    /* renamed from: o */
    public String f13754o;

    /* renamed from: p */
    public String f13755p;

    /* renamed from: q */
    public String f13756q;

    /* renamed from: com.cyberlink.you.friends.InvitationFriend$a */
    public class C3057a implements Parcelable.Creator<InvitationFriend> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public InvitationFriend createFromParcel(Parcel parcel) {
            return new InvitationFriend(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public InvitationFriend[] newArray(int i9) {
            return new InvitationFriend[i9];
        }
    }

    /* renamed from: com.cyberlink.you.friends.InvitationFriend$b */
    public static class C3058b implements Comparator<InvitationFriend> {

        /* renamed from: b */
        public final Collator f13757b = Collator.getInstance();

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(InvitationFriend invitationFriend, InvitationFriend invitationFriend2) {
            return this.f13757b.compare(invitationFriend.f13741b, invitationFriend2.f13741b);
        }
    }

    public InvitationFriend() {
        this.f13741b = "";
        this.f13742c = "";
        this.f13743d = "";
        this.f13744e = -1L;
        this.f13745f = "";
        this.f13746g = -1L;
        this.f13747h = -1L;
        this.f13748i = "";
        this.f13749j = "";
        this.f13750k = "";
        this.f13751l = "";
        this.f13752m = "";
        this.f13753n = "";
        this.f13754o = "";
        this.f13755p = "";
        this.f13756q = "";
    }

    /* renamed from: a */
    public String m15763a() {
        String str = this.f13748i;
        return (str == null || str.isEmpty()) ? this.f13741b : this.f13748i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f13741b);
        parcel.writeString(this.f13742c);
        parcel.writeString(this.f13743d);
        parcel.writeLong(this.f13744e);
        parcel.writeString(this.f13745f);
        parcel.writeLong(this.f13746g);
        parcel.writeLong(this.f13747h);
        parcel.writeString(this.f13748i);
        parcel.writeString(this.f13749j);
        parcel.writeString(this.f13750k);
        parcel.writeString(this.f13751l);
        parcel.writeString(this.f13752m);
        parcel.writeString(this.f13753n);
        parcel.writeString(this.f13754o);
        parcel.writeString(this.f13755p);
        parcel.writeString(this.f13756q);
    }

    public InvitationFriend(Parcel parcel) {
        this.f13741b = parcel.readString();
        this.f13742c = parcel.readString();
        this.f13743d = parcel.readString();
        this.f13744e = parcel.readLong();
        this.f13745f = parcel.readString();
        this.f13746g = parcel.readLong();
        this.f13747h = parcel.readLong();
        this.f13748i = parcel.readString();
        this.f13749j = parcel.readString();
        this.f13750k = parcel.readString();
        this.f13751l = parcel.readString();
        this.f13752m = parcel.readString();
        this.f13753n = parcel.readString();
        this.f13754o = parcel.readString();
        this.f13755p = parcel.readString();
        this.f13756q = parcel.readString();
    }
}
