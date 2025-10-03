package p180r0;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.versionedparcelable.VersionedParcel;
import java.lang.reflect.Method;
import p132m.C5302a;

/* renamed from: r0.a */
/* loaded from: classes.dex */
public class C6181a extends VersionedParcel {

    /* renamed from: d */
    public final SparseIntArray f20827d;

    /* renamed from: e */
    public final Parcel f20828e;

    /* renamed from: f */
    public final int f20829f;

    /* renamed from: g */
    public final int f20830g;

    /* renamed from: h */
    public final String f20831h;

    /* renamed from: i */
    public int f20832i;

    /* renamed from: j */
    public int f20833j;

    /* renamed from: k */
    public int f20834k;

    public C6181a(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new C5302a(), new C5302a(), new C5302a());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: A */
    public void mo3127A(byte[] bArr) {
        if (bArr == null) {
            this.f20828e.writeInt(-1);
        } else {
            this.f20828e.writeInt(bArr.length);
            this.f20828e.writeByteArray(bArr);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: C */
    public void mo3129C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f20828e, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: E */
    public void mo3131E(int i9) {
        this.f20828e.writeInt(i9);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: G */
    public void mo3133G(Parcelable parcelable) {
        this.f20828e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: I */
    public void mo3135I(String str) {
        this.f20828e.writeString(str);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: a */
    public void mo3141a() {
        int i9 = this.f20832i;
        if (i9 >= 0) {
            int i10 = this.f20827d.get(i9);
            int iDataPosition = this.f20828e.dataPosition();
            this.f20828e.setDataPosition(i10);
            this.f20828e.writeInt(iDataPosition - i10);
            this.f20828e.setDataPosition(iDataPosition);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: b */
    public VersionedParcel mo3142b() {
        Parcel parcel = this.f20828e;
        int iDataPosition = parcel.dataPosition();
        int i9 = this.f20833j;
        if (i9 == this.f20829f) {
            i9 = this.f20830g;
        }
        return new C6181a(parcel, iDataPosition, i9, this.f20831h + "  ", this.f2977a, this.f2978b, this.f2979c);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: g */
    public boolean mo3147g() {
        return this.f20828e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: i */
    public byte[] mo3149i() {
        int i9 = this.f20828e.readInt();
        if (i9 < 0) {
            return null;
        }
        byte[] bArr = new byte[i9];
        this.f20828e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: k */
    public CharSequence mo3151k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f20828e);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: m */
    public boolean mo3153m(int i9) {
        while (this.f20833j < this.f20830g) {
            int i10 = this.f20834k;
            if (i10 == i9) {
                return true;
            }
            if (String.valueOf(i10).compareTo(String.valueOf(i9)) > 0) {
                return false;
            }
            this.f20828e.setDataPosition(this.f20833j);
            int i11 = this.f20828e.readInt();
            this.f20834k = this.f20828e.readInt();
            this.f20833j += i11;
        }
        return this.f20834k == i9;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: o */
    public int mo3155o() {
        return this.f20828e.readInt();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: q */
    public <T extends Parcelable> T mo3157q() {
        return (T) this.f20828e.readParcelable(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: s */
    public String mo3159s() {
        return this.f20828e.readString();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: w */
    public void mo3163w(int i9) {
        mo3141a();
        this.f20832i = i9;
        this.f20827d.put(i9, this.f20828e.dataPosition());
        mo3131E(0);
        mo3131E(i9);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    /* renamed from: y */
    public void mo3165y(boolean z8) {
        this.f20828e.writeInt(z8 ? 1 : 0);
    }

    public C6181a(Parcel parcel, int i9, int i10, String str, C5302a<String, Method> c5302a, C5302a<String, Method> c5302a2, C5302a<String, Class> c5302a3) {
        super(c5302a, c5302a2, c5302a3);
        this.f20827d = new SparseIntArray();
        this.f20832i = -1;
        this.f20834k = -1;
        this.f20828e = parcel;
        this.f20829f = i9;
        this.f20830g = i10;
        this.f20833j = i9;
        this.f20831h = str;
    }
}
