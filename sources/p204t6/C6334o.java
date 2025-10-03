package p204t6;

import com.google.common.primitives.UnsignedBytes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import kotlin.collections.AbstractC5218a;
import kotlin.collections.C5223f;
import kotlin.collections.C5226i;
import kotlin.collections.C5230m;
import okio.ByteString;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: t6.o */
/* loaded from: classes.dex */
public final class C6334o extends AbstractC5218a<ByteString> implements RandomAccess {

    /* renamed from: e */
    public static final a f21354e = new a(null);

    /* renamed from: c */
    public final ByteString[] f21355c;

    /* renamed from: d */
    public final int[] f21356d;

    /* renamed from: t6.o$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: b */
        public static /* synthetic */ void m24276b(a aVar, long j9, C6322c c6322c, int i9, List list, int i10, int i11, List list2, int i12, Object obj) {
            aVar.m24277a((i12 & 1) != 0 ? 0L : j9, c6322c, (i12 & 4) != 0 ? 0 : i9, list, (i12 & 16) != 0 ? 0 : i10, (i12 & 32) != 0 ? list.size() : i11, list2);
        }

        /* renamed from: a */
        public final void m24277a(long j9, C6322c c6322c, int i9, List<? extends ByteString> list, int i10, int i11, List<Integer> list2) {
            int i12;
            int i13;
            int i14;
            int i15;
            C6322c c6322c2;
            int i16 = i9;
            if (!(i10 < i11)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            for (int i17 = i10; i17 < i11; i17++) {
                if (!(list.get(i17).m21892r() >= i16)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            ByteString byteString = list.get(i10);
            ByteString byteString2 = list.get(i11 - 1);
            int i18 = -1;
            if (i16 == byteString.m21892r()) {
                int iIntValue = list2.get(i10).intValue();
                int i19 = i10 + 1;
                ByteString byteString3 = list.get(i19);
                i12 = i19;
                i13 = iIntValue;
                byteString = byteString3;
            } else {
                i12 = i10;
                i13 = -1;
            }
            if (byteString.m21878d(i16) == byteString2.m21878d(i16)) {
                int iMin = Math.min(byteString.m21892r(), byteString2.m21892r());
                int i20 = 0;
                for (int i21 = i16; i21 < iMin && byteString.m21878d(i21) == byteString2.m21878d(i21); i21++) {
                    i20++;
                }
                long jM24278c = j9 + m24278c(c6322c) + 2 + i20 + 1;
                c6322c.writeInt(-i20);
                c6322c.writeInt(i13);
                int i22 = i16 + i20;
                while (i16 < i22) {
                    c6322c.writeInt(byteString.m21878d(i16) & UnsignedBytes.MAX_VALUE);
                    i16++;
                }
                if (i12 + 1 == i11) {
                    if (!(i22 == list.get(i12).m21892r())) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    c6322c.writeInt(list2.get(i12).intValue());
                    return;
                } else {
                    C6322c c6322c3 = new C6322c();
                    c6322c.writeInt(((int) (m24278c(c6322c3) + jM24278c)) * (-1));
                    m24277a(jM24278c, c6322c3, i22, list, i12, i11, list2);
                    c6322c.mo24219h(c6322c3);
                    return;
                }
            }
            int i23 = 1;
            for (int i24 = i12 + 1; i24 < i11; i24++) {
                if (list.get(i24 - 1).m21878d(i16) != list.get(i24).m21878d(i16)) {
                    i23++;
                }
            }
            long jM24278c2 = j9 + m24278c(c6322c) + 2 + (i23 * 2);
            c6322c.writeInt(i23);
            c6322c.writeInt(i13);
            for (int i25 = i12; i25 < i11; i25++) {
                byte bM21878d = list.get(i25).m21878d(i16);
                if (i25 == i12 || bM21878d != list.get(i25 - 1).m21878d(i16)) {
                    c6322c.writeInt(bM21878d & UnsignedBytes.MAX_VALUE);
                }
            }
            C6322c c6322c4 = new C6322c();
            while (i12 < i11) {
                byte bM21878d2 = list.get(i12).m21878d(i16);
                int i26 = i12 + 1;
                int i27 = i26;
                while (true) {
                    if (i27 >= i11) {
                        i14 = i11;
                        break;
                    } else {
                        if (bM21878d2 != list.get(i27).m21878d(i16)) {
                            i14 = i27;
                            break;
                        }
                        i27++;
                    }
                }
                if (i26 == i14 && i16 + 1 == list.get(i12).m21892r()) {
                    c6322c.writeInt(list2.get(i12).intValue());
                    i15 = i14;
                    c6322c2 = c6322c4;
                } else {
                    c6322c.writeInt(((int) (jM24278c2 + m24278c(c6322c4))) * i18);
                    i15 = i14;
                    c6322c2 = c6322c4;
                    m24277a(jM24278c2, c6322c4, i16 + 1, list, i12, i14, list2);
                }
                c6322c4 = c6322c2;
                i12 = i15;
                i18 = -1;
            }
            c6322c.mo24219h(c6322c4);
        }

        /* renamed from: c */
        public final long m24278c(C6322c c6322c) {
            return c6322c.size() / 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x00e9, code lost:
        
            continue;
         */
        /* renamed from: d */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final C6334o m24279d(ByteString... byteStringArr) {
            C0042f.m158e(byteStringArr, "byteStrings");
            int i9 = 0;
            C0040d c0040d = null;
            if (byteStringArr.length == 0) {
                return new C6334o(new ByteString[0], new int[]{0, -1}, c0040d);
            }
            List listM20393p = C5223f.m20393p(byteStringArr);
            C5230m.m20409o(listM20393p);
            ArrayList arrayList = new ArrayList(byteStringArr.length);
            for (ByteString byteString : byteStringArr) {
                arrayList.add(-1);
            }
            Integer[] numArr = (Integer[]) arrayList.toArray(new Integer[0]);
            List listM20404j = C5226i.m20404j(Arrays.copyOf(numArr, numArr.length));
            int length = byteStringArr.length;
            int i10 = 0;
            int i11 = 0;
            while (i10 < length) {
                listM20404j.set(C5226i.m20399e(listM20393p, byteStringArr[i10], 0, 0, 6, null), Integer.valueOf(i11));
                i10++;
                i11++;
            }
            if (!(((ByteString) listM20393p.get(0)).m21892r() > 0)) {
                throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
            }
            int i12 = 0;
            while (i12 < listM20393p.size()) {
                ByteString byteString2 = (ByteString) listM20393p.get(i12);
                int i13 = i12 + 1;
                int i14 = i13;
                while (i14 < listM20393p.size()) {
                    ByteString byteString3 = (ByteString) listM20393p.get(i14);
                    if (byteString3.m21893s(byteString2)) {
                        if (!(byteString3.m21892r() != byteString2.m21892r())) {
                            throw new IllegalArgumentException(("duplicate option: " + byteString3).toString());
                        }
                        if (((Number) listM20404j.get(i14)).intValue() > ((Number) listM20404j.get(i12)).intValue()) {
                            listM20393p.remove(i14);
                            listM20404j.remove(i14);
                        } else {
                            i14++;
                        }
                    }
                }
                i12 = i13;
            }
            C6322c c6322c = new C6322c();
            m24276b(this, 0L, c6322c, 0, listM20393p, 0, 0, listM20404j, 53, null);
            int[] iArr = new int[(int) m24278c(c6322c)];
            while (!c6322c.mo24218g()) {
                iArr[i9] = c6322c.readInt();
                i9++;
            }
            Object[] objArrCopyOf = Arrays.copyOf(byteStringArr, byteStringArr.length);
            C0042f.m157d(objArrCopyOf, "copyOf(this, size)");
            return new C6334o((ByteString[]) objArrCopyOf, iArr, c0040d);
        }
    }

    public C6334o(ByteString[] byteStringArr, int[] iArr) {
        this.f21355c = byteStringArr;
        this.f21356d = iArr;
    }

    public /* synthetic */ C6334o(ByteString[] byteStringArr, int[] iArr, C0040d c0040d) {
        this(byteStringArr, iArr);
    }

    @Override // kotlin.collections.AbstractCollection
    /* renamed from: a */
    public int mo20352a() {
        return this.f21355c.length;
    }

    /* renamed from: b */
    public /* bridge */ boolean m24270b(ByteString byteString) {
        return super.contains(byteString);
    }

    @Override // kotlin.collections.AbstractC5218a, java.util.List
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public ByteString get(int i9) {
        return this.f21355c[i9];
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return m24270b((ByteString) obj);
        }
        return false;
    }

    /* renamed from: d */
    public final ByteString[] m24272d() {
        return this.f21355c;
    }

    /* renamed from: e */
    public final int[] m24273e() {
        return this.f21356d;
    }

    /* renamed from: f */
    public /* bridge */ int m24274f(ByteString byteString) {
        return super.indexOf(byteString);
    }

    /* renamed from: g */
    public /* bridge */ int m24275g(ByteString byteString) {
        return super.lastIndexOf(byteString);
    }

    @Override // kotlin.collections.AbstractC5218a, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return m24274f((ByteString) obj);
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractC5218a, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return m24275g((ByteString) obj);
        }
        return -1;
    }
}
