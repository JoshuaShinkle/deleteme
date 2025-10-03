package com.cyberlink.you.database;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public class GroupAlbumObj implements Parcelable {
    public static final Parcelable.Creator<GroupAlbumObj> CREATOR = new C2935a();

    /* renamed from: b */
    public long f12903b;

    /* renamed from: c */
    public String f12904c;

    /* renamed from: d */
    public String f12905d;

    /* renamed from: e */
    public String f12906e;

    /* renamed from: f */
    public Date f12907f;

    /* renamed from: g */
    public int f12908g;

    /* renamed from: h */
    public String f12909h;

    /* renamed from: i */
    public String f12910i;

    /* renamed from: j */
    public String f12911j;

    /* renamed from: k */
    public int f12912k;

    /* renamed from: l */
    public String f12913l;

    /* renamed from: com.cyberlink.you.database.GroupAlbumObj$a */
    public class C2935a implements Parcelable.Creator<GroupAlbumObj> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public GroupAlbumObj createFromParcel(Parcel parcel) {
            return new GroupAlbumObj(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public GroupAlbumObj[] newArray(int i9) {
            return new GroupAlbumObj[i9];
        }
    }

    public GroupAlbumObj(long j9, String str, String str2, String str3, long j10, int i9, String str4, String str5) {
        this.f12903b = j9;
        this.f12904c = str;
        this.f12905d = str2;
        this.f12906e = str3;
        this.f12907f = new Date(j10);
        this.f12908g = i9;
        this.f12909h = str4;
        this.f12910i = "";
        this.f12911j = "";
        this.f12912k = 0;
        this.f12913l = str5;
    }

    /* renamed from: a */
    public static void m14674a(GroupAlbumObj groupAlbumObj, GroupAlbumObj groupAlbumObj2) {
        if (groupAlbumObj2 == null || groupAlbumObj == null) {
            return;
        }
        groupAlbumObj.f12903b = groupAlbumObj2.f12903b;
        groupAlbumObj.f12904c = groupAlbumObj2.f12904c;
        groupAlbumObj.f12905d = groupAlbumObj2.f12905d;
        groupAlbumObj.f12906e = groupAlbumObj2.f12906e;
        groupAlbumObj.f12907f = groupAlbumObj2.f12907f;
        groupAlbumObj.f12908g = groupAlbumObj2.f12908g;
        groupAlbumObj.f12909h = groupAlbumObj2.f12909h;
        groupAlbumObj.f12910i = groupAlbumObj2.f12910i;
        groupAlbumObj.f12911j = groupAlbumObj2.f12911j;
        groupAlbumObj.f12913l = groupAlbumObj2.f12913l;
    }

    /* renamed from: b */
    public String m14675b() {
        return this.f12905d;
    }

    /* renamed from: c */
    public String m14676c() {
        return this.f12906e;
    }

    /* renamed from: d */
    public String m14677d() {
        return this.f12913l;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public String m14678e() {
        return this.f12909h;
    }

    /* renamed from: f */
    public String m14679f() {
        return this.f12904c;
    }

    /* renamed from: g */
    public long m14680g() {
        return this.f12903b;
    }

    /* renamed from: h */
    public Date m14681h() {
        return this.f12907f;
    }

    /* renamed from: i */
    public int m14682i() {
        return this.f12908g;
    }

    /* renamed from: j */
    public boolean m14683j() {
        return "backup".equalsIgnoreCase(this.f12904c);
    }

    /* renamed from: k */
    public void m14684k(String str) {
        this.f12906e = str;
    }

    /* renamed from: l */
    public void m14685l(int i9) {
        this.f12908g = i9;
    }

    /* renamed from: m */
    public ContentValues m14686m() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m14680g()));
        contentValues.put("GroupId", m14679f());
        contentValues.put("AlbumId", m14675b());
        contentValues.put("AlbumName", m14676c());
        contentValues.put("LastModified", Long.valueOf(m14681h().getTime()));
        contentValues.put("NumberOfMedia", Integer.valueOf(m14682i()));
        contentValues.put("CreatorId", m14678e());
        contentValues.put("AlbumType", m14677d());
        return contentValues;
    }

    /* renamed from: n */
    public ContentValues m14687n(String str) {
        if (str == null || str.isEmpty()) {
            return new ContentValues();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return m14688o(arrayList);
    }

    /* renamed from: o */
    public ContentValues m14688o(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                switch (str) {
                    case "AlbumName":
                        contentValues.put("AlbumName", m14676c());
                        break;
                    case "AlbumType":
                        contentValues.put("AlbumType", m14677d());
                        break;
                    case "CreatorId":
                        contentValues.put("CreatorId", m14678e());
                        break;
                    case "NumberOfMedia":
                        contentValues.put("NumberOfMedia", Integer.valueOf(m14682i()));
                        break;
                    case "AlbumId":
                        contentValues.put("AlbumId", m14675b());
                        break;
                    case "GroupId":
                        contentValues.put("GroupId", m14679f());
                        break;
                    case "LastModified":
                        contentValues.put("LastModified", Long.valueOf(m14681h().getTime()));
                        break;
                }
            }
        }
        return contentValues;
    }

    public String toString() {
        return "ID: " + this.f12903b + ", " + super.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f12905d);
        parcel.writeString(this.f12906e);
        parcel.writeString(this.f12913l);
        parcel.writeString(this.f12904c);
        if (this.f12913l.equals("Chat")) {
            return;
        }
        parcel.writeLong(this.f12903b);
        parcel.writeLong(this.f12907f.getTime());
        parcel.writeInt(this.f12908g);
        parcel.writeString(this.f12909h);
        parcel.writeString(this.f12910i);
        parcel.writeString(this.f12911j);
        parcel.writeInt(this.f12912k);
    }

    public GroupAlbumObj(String str, String str2, String str3, String str4) {
        this.f12904c = str;
        this.f12905d = str2;
        this.f12906e = str3;
        this.f12910i = "";
        this.f12911j = "";
        this.f12912k = 0;
        this.f12913l = str4;
    }

    public GroupAlbumObj() {
        this.f12903b = -1L;
        this.f12904c = "";
        this.f12905d = "";
        this.f12906e = "";
        this.f12907f = new Date();
        this.f12908g = 0;
        this.f12909h = "";
        this.f12910i = "";
        this.f12911j = "";
        this.f12912k = 0;
        this.f12913l = "";
    }

    public GroupAlbumObj(Parcel parcel) {
        this.f12905d = parcel.readString();
        this.f12906e = parcel.readString();
        this.f12913l = parcel.readString();
        this.f12904c = parcel.readString();
        if (this.f12913l.equals("Chat")) {
            return;
        }
        this.f12903b = parcel.readLong();
        this.f12907f = new Date(parcel.readLong());
        this.f12908g = parcel.readInt();
        this.f12909h = parcel.readString();
        this.f12910i = parcel.readString();
        this.f12911j = parcel.readString();
        this.f12912k = parcel.readInt();
    }
}
