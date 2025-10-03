package com.cyberlink.you.friends;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.Scopes;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class Friend implements Parcelable {

    /* renamed from: A */
    public static List<String> f13643A = Arrays.asList("UserId", "Jid", "DisplayName", "Avatar", "AvatarAlbumId", "Cover", "CoverAlbumId", "NickName", "IsHidden", "IsBlocked", "isBrokenUp", "LastModified", "FriendshipCreatedTime", "UserType", "PublicId", "StatusMessage", "Country", "isDeleted", "orgName", "department", "orgTitle", Scopes.EMAIL);
    public static final Parcelable.Creator<Friend> CREATOR = new C3040a();

    /* renamed from: b */
    public long f13644b;

    /* renamed from: c */
    public long f13645c;

    /* renamed from: d */
    public String f13646d;

    /* renamed from: e */
    public String f13647e;

    /* renamed from: f */
    public String f13648f;

    /* renamed from: g */
    public String f13649g;

    /* renamed from: h */
    public String f13650h;

    /* renamed from: i */
    public String f13651i;

    /* renamed from: j */
    public String f13652j;

    /* renamed from: k */
    public String f13653k;

    /* renamed from: l */
    public boolean f13654l;

    /* renamed from: m */
    public boolean f13655m;

    /* renamed from: n */
    public boolean f13656n;

    /* renamed from: o */
    public long f13657o;

    /* renamed from: p */
    public boolean f13658p;

    /* renamed from: q */
    public long f13659q;

    /* renamed from: r */
    public String f13660r;

    /* renamed from: s */
    public String f13661s;

    /* renamed from: t */
    public String f13662t;

    /* renamed from: u */
    public String f13663u;

    /* renamed from: v */
    public boolean f13664v;

    /* renamed from: w */
    public String f13665w;

    /* renamed from: x */
    public String f13666x;

    /* renamed from: y */
    public String f13667y;

    /* renamed from: z */
    public String f13668z;

    /* renamed from: com.cyberlink.you.friends.Friend$a */
    public class C3040a implements Parcelable.Creator<Friend> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Friend createFromParcel(Parcel parcel) {
            return new Friend(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Friend[] newArray(int i9) {
            return new Friend[i9];
        }
    }

    /* renamed from: com.cyberlink.you.friends.Friend$b */
    public static class C3041b implements Comparator<Friend> {

        /* renamed from: b */
        public final Collator f13669b = Collator.getInstance();

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Friend friend, Friend friend2) {
            return this.f13669b.compare(friend.m15621b(), friend2.m15621b());
        }
    }

    public Friend(long j9, long j10, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z8, boolean z9, boolean z10, long j11, Boolean bool, long j12, String str8, String str9, String str10, String str11, boolean z11, String str12, String str13, String str14, String str15) {
        this.f13644b = j9;
        this.f13645c = j10;
        this.f13646d = str2;
        this.f13647e = str3;
        this.f13648f = str;
        this.f13649g = str4;
        this.f13650h = str6;
        this.f13651i = str7;
        this.f13652j = "";
        this.f13657o = j11;
        this.f13658p = bool.booleanValue();
        this.f13653k = str5;
        this.f13654l = z8;
        this.f13655m = z9;
        this.f13656n = z10;
        this.f13659q = j12;
        this.f13660r = str8;
        this.f13661s = str9;
        this.f13662t = str10;
        this.f13663u = str11;
        this.f13664v = z11;
        this.f13665w = str12;
        this.f13666x = str13;
        this.f13667y = str14;
        this.f13668z = str15;
    }

    /* renamed from: a */
    public String m15620a() {
        return this.f13646d;
    }

    /* renamed from: b */
    public String m15621b() {
        return !TextUtils.isEmpty(this.f13653k) ? this.f13653k : this.f13646d;
    }

    /* renamed from: c */
    public String m15622c() {
        return this.f13653k;
    }

    /* renamed from: d */
    public String m15623d() {
        return this.f13660r;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public void m15624e(String str) {
        this.f13646d = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f13645c == ((Friend) obj).f13645c;
    }

    /* renamed from: f */
    public void m15625f(String str) {
        this.f13653k = str;
    }

    /* renamed from: g */
    public ContentValues m15626g() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(this.f13644b));
        contentValues.put("UserId", Long.valueOf(this.f13645c));
        contentValues.put("Jid", this.f13648f);
        contentValues.put("DisplayName", this.f13646d);
        contentValues.put("Avatar", this.f13647e);
        contentValues.put("Cover", this.f13650h);
        contentValues.put("AvatarAlbumId", this.f13649g);
        contentValues.put("NickName", this.f13653k);
        contentValues.put("IsHidden", Boolean.valueOf(this.f13654l));
        contentValues.put("IsBlocked", Boolean.valueOf(this.f13655m));
        contentValues.put("isBrokenUp", Boolean.valueOf(this.f13656n));
        contentValues.put("LastModified", Long.valueOf(this.f13657o));
        contentValues.put("FriendshipCreatedTime", Long.valueOf(this.f13659q));
        contentValues.put("UserType", this.f13660r);
        contentValues.put("PublicId", this.f13661s);
        contentValues.put("StatusMessage", this.f13662t);
        contentValues.put("Country", this.f13663u);
        contentValues.put("isDeleted", Boolean.valueOf(this.f13664v));
        contentValues.put("orgName", this.f13665w);
        contentValues.put("department", this.f13666x);
        contentValues.put("orgTitle", this.f13667y);
        contentValues.put(Scopes.EMAIL, this.f13668z);
        return contentValues;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: h */
    public ContentValues m15627h(List<String> list) {
        String str;
        Friend friend;
        ContentValues contentValues;
        ContentValues contentValues2;
        Friend friend2 = this;
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues3 = new ContentValues();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!TextUtils.isEmpty(next)) {
                next.hashCode();
                Iterator<String> it2 = it;
                ContentValues contentValues4 = contentValues3;
                String str2 = "IsHidden";
                String str3 = "FriendshipCreatedTime";
                String str4 = "AvatarAlbumId";
                String str5 = "DisplayName";
                String str6 = "orgName";
                String str7 = "Country";
                switch (next.hashCode()) {
                    case -1752163738:
                        str = "UserId";
                        if (next.equals(str)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1672482954:
                        c = next.equals(str7) ? (char) 1 : (char) 65535;
                        str7 = str7;
                        str = "UserId";
                        break;
                    case -1205040241:
                        c = next.equals(str6) ? (char) 2 : (char) 65535;
                        str6 = str6;
                        str = "UserId";
                        break;
                    case -912949683:
                        c = next.equals(str5) ? (char) 3 : (char) 65535;
                        str5 = str5;
                        str = "UserId";
                        break;
                    case -744085455:
                        c = next.equals(str4) ? (char) 4 : (char) 65535;
                        str4 = str4;
                        str = "UserId";
                        break;
                    case -576465029:
                        c = next.equals(str3) ? (char) 5 : (char) 65535;
                        str3 = str3;
                        str = "UserId";
                        break;
                    case -478809196:
                        c = next.equals(str2) ? (char) 6 : (char) 65535;
                        str2 = str2;
                        str = "UserId";
                        break;
                    case -420575078:
                        if (next.equals("isBrokenUp")) {
                            c = 7;
                        }
                        str = "UserId";
                        break;
                    case -201820731:
                        if (next.equals("UserType")) {
                            c = '\b';
                        }
                        str = "UserId";
                        break;
                    case -159743083:
                        if (next.equals("StatusMessage")) {
                            c = '\t';
                        }
                        str = "UserId";
                        break;
                    case 74469:
                        if (next.equals("Jid")) {
                            c = '\n';
                        }
                        str = "UserId";
                        break;
                    case 65299351:
                        if (next.equals("Cover")) {
                            c = 11;
                        }
                        str = "UserId";
                        break;
                    case 96619420:
                        if (next.equals(Scopes.EMAIL)) {
                            c = '\f';
                        }
                        str = "UserId";
                        break;
                    case 134381742:
                        if (next.equals("NickName")) {
                            c = CharUtils.f19105CR;
                        }
                        str = "UserId";
                        break;
                    case 438563923:
                        if (next.equals("CoverAlbumId")) {
                            c = 14;
                        }
                        str = "UserId";
                        break;
                    case 848184146:
                        if (next.equals("department")) {
                            c = 15;
                        }
                        str = "UserId";
                        break;
                    case 970684303:
                        if (next.equals("isDeleted")) {
                            c = 16;
                        }
                        str = "UserId";
                        break;
                    case 1304244692:
                        if (next.equals("orgTitle")) {
                            c = 17;
                        }
                        str = "UserId";
                        break;
                    case 1358244228:
                        if (next.equals("PublicId")) {
                            c = 18;
                        }
                        str = "UserId";
                        break;
                    case 1402751298:
                        if (next.equals("IsBlocked")) {
                            c = 19;
                        }
                        str = "UserId";
                        break;
                    case 1972874617:
                        if (next.equals("Avatar")) {
                            c = 20;
                        }
                        str = "UserId";
                        break;
                    case 2123323295:
                        if (next.equals("LastModified")) {
                            c = 21;
                        }
                        str = "UserId";
                        break;
                    default:
                        str = "UserId";
                        break;
                }
                switch (c) {
                    case 0:
                        friend = this;
                        contentValues = contentValues4;
                        contentValues.put(str, Long.valueOf(friend.f13645c));
                        break;
                    case 1:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str7, friend.f13663u);
                        contentValues = contentValues2;
                        break;
                    case 2:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str6, friend.f13665w);
                        contentValues = contentValues2;
                        break;
                    case 3:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str5, friend.f13646d);
                        contentValues = contentValues2;
                        break;
                    case 4:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str4, friend.f13649g);
                        contentValues = contentValues2;
                        break;
                    case 5:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str3, Long.valueOf(friend.f13659q));
                        contentValues = contentValues2;
                        break;
                    case 6:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put(str2, Boolean.valueOf(friend.f13654l));
                        contentValues = contentValues2;
                        break;
                    case 7:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isBrokenUp", Boolean.valueOf(friend.f13656n));
                        contentValues = contentValues2;
                        break;
                    case '\b':
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("UserType", friend.f13660r);
                        contentValues = contentValues2;
                        break;
                    case '\t':
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("StatusMessage", friend.f13662t);
                        contentValues = contentValues2;
                        break;
                    case '\n':
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("Jid", friend.f13648f);
                        contentValues = contentValues2;
                        break;
                    case 11:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("Cover", friend.f13650h);
                        contentValues = contentValues2;
                        break;
                    case '\f':
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("orgTitle", friend.f13668z);
                        contentValues = contentValues2;
                        break;
                    case '\r':
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("NickName", friend.f13653k);
                        contentValues = contentValues2;
                        break;
                    case 14:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("CoverAlbumId", friend.f13651i);
                        contentValues = contentValues2;
                        break;
                    case 15:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("department", friend.f13666x);
                        contentValues = contentValues2;
                        break;
                    case 16:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("isDeleted", Boolean.valueOf(friend.f13664v));
                        contentValues = contentValues2;
                        break;
                    case 17:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("orgTitle", friend.f13667y);
                        contentValues = contentValues2;
                        break;
                    case 18:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("PublicId", friend.f13661s);
                        contentValues = contentValues2;
                        break;
                    case 19:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("IsBlocked", Boolean.valueOf(friend.f13655m));
                        contentValues = contentValues2;
                        break;
                    case 20:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("Avatar", friend.f13647e);
                        contentValues = contentValues2;
                        break;
                    case 21:
                        friend = this;
                        contentValues2 = contentValues4;
                        contentValues2.put("LastModified", Long.valueOf(friend.f13657o));
                        contentValues = contentValues2;
                        break;
                    default:
                        friend = this;
                        contentValues = contentValues4;
                        break;
                }
                it = it2;
                contentValues3 = contentValues;
                friend2 = friend;
            }
        }
        return contentValues3;
    }

    public int hashCode() {
        long j9 = this.f13645c;
        return 31 + ((int) (j9 ^ (j9 >>> 32)));
    }

    /* renamed from: i */
    public void m15628i(Friend friend) {
        this.f13644b = friend.f13644b;
        this.f13645c = friend.f13645c;
        this.f13646d = friend.f13646d;
        this.f13647e = friend.f13647e;
        this.f13648f = friend.f13648f;
        this.f13649g = friend.f13649g;
        this.f13650h = friend.f13650h;
        this.f13651i = friend.f13651i;
        this.f13652j = friend.f13652j;
        this.f13657o = friend.f13657o;
        this.f13658p = friend.f13658p;
        this.f13653k = friend.f13653k;
        this.f13654l = friend.f13654l;
        this.f13655m = friend.f13655m;
        this.f13659q = friend.f13659q;
        this.f13660r = friend.f13660r;
        this.f13661s = friend.f13661s;
        this.f13662t = friend.f13662t;
        this.f13663u = friend.f13663u;
        this.f13664v = friend.f13664v;
        this.f13665w = friend.f13665w;
        this.f13666x = friend.f13666x;
        this.f13667y = friend.f13667y;
        this.f13668z = friend.f13668z;
    }

    public String toString() {
        return "Friend{displayName='" + this.f13646d + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeLong(this.f13645c);
        parcel.writeString(this.f13646d);
        parcel.writeString(this.f13647e);
        parcel.writeString(this.f13648f);
        parcel.writeString(this.f13649g);
        parcel.writeString(this.f13650h);
        parcel.writeString(this.f13651i);
        parcel.writeString(this.f13652j);
        parcel.writeLong(this.f13657o);
        parcel.writeByte(this.f13658p ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f13653k);
        parcel.writeByte(this.f13654l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13655m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f13656n ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f13659q);
        parcel.writeString(this.f13660r);
        parcel.writeString(this.f13661s);
        parcel.writeString(this.f13662t);
        parcel.writeString(this.f13663u);
        parcel.writeByte(this.f13664v ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f13665w);
        parcel.writeString(this.f13666x);
        parcel.writeString(this.f13667y);
        parcel.writeString(this.f13668z);
    }

    public Friend() {
        this.f13645c = 0L;
        this.f13646d = "";
        this.f13647e = "";
        this.f13648f = "";
        this.f13649g = "";
        this.f13650h = "";
        this.f13651i = "";
        this.f13652j = "";
        this.f13657o = 0L;
        this.f13653k = "";
        this.f13654l = false;
        this.f13655m = false;
        this.f13656n = false;
        this.f13659q = -1L;
        this.f13660r = "General";
        this.f13661s = "";
        this.f13662t = "";
        this.f13663u = "";
        this.f13664v = false;
        this.f13665w = "";
        this.f13666x = "";
        this.f13667y = "";
        this.f13668z = "";
    }

    public Friend(Parcel parcel) {
        this.f13645c = parcel.readLong();
        this.f13646d = parcel.readString();
        this.f13647e = parcel.readString();
        this.f13648f = parcel.readString();
        this.f13649g = parcel.readString();
        this.f13650h = parcel.readString();
        this.f13651i = parcel.readString();
        this.f13652j = parcel.readString();
        this.f13657o = parcel.readLong();
        this.f13658p = parcel.readByte() != 0;
        this.f13653k = parcel.readString();
        this.f13654l = parcel.readByte() != 0;
        this.f13655m = parcel.readByte() != 0;
        this.f13656n = parcel.readByte() != 0;
        this.f13659q = parcel.readLong();
        this.f13660r = parcel.readString();
        this.f13661s = parcel.readString();
        this.f13662t = parcel.readString();
        this.f13663u = parcel.readString();
        this.f13664v = parcel.readByte() != 0;
        this.f13665w = parcel.readString();
        this.f13666x = parcel.readString();
        this.f13667y = parcel.readString();
        this.f13668z = parcel.readString();
    }
}
