package okio;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.collections.C5222e;
import kotlin.text.C5255l;
import org.apache.commons.lang3.StringUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p204t6.C6320a;
import p204t6.C6322c;
import p204t6.C6345z;
import p213u6.C6424b;

/* loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {

    /* renamed from: d */
    public static final C5526a f19095d = new C5526a(null);

    /* renamed from: e */
    public static final ByteString f19096e = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;

    /* renamed from: b */
    public transient int f19097b;

    /* renamed from: c */
    public transient String f19098c;
    private final byte[] data;

    /* renamed from: okio.ByteString$a */
    public static final class C5526a {
        public C5526a() {
        }

        public /* synthetic */ C5526a(C0040d c0040d) {
            this();
        }

        /* renamed from: f */
        public static /* synthetic */ ByteString m21897f(C5526a c5526a, byte[] bArr, int i9, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i9 = 0;
            }
            if ((i11 & 2) != 0) {
                i10 = C6320a.m24154c();
            }
            return c5526a.m21902e(bArr, i9, i10);
        }

        /* renamed from: a */
        public final ByteString m21898a(String str) {
            C0042f.m158e(str, "<this>");
            byte[] bArrM21908a = C5527a.m21908a(str);
            if (bArrM21908a != null) {
                return new ByteString(bArrM21908a);
            }
            return null;
        }

        /* renamed from: b */
        public final ByteString m21899b(String str) {
            C0042f.m158e(str, "<this>");
            if (!(str.length() % 2 == 0)) {
                throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
            }
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i9 = 0; i9 < length; i9++) {
                int i10 = i9 * 2;
                bArr[i9] = (byte) ((C6424b.m24583e(str.charAt(i10)) << 4) + C6424b.m24583e(str.charAt(i10 + 1)));
            }
            return new ByteString(bArr);
        }

        /* renamed from: c */
        public final ByteString m21900c(String str, Charset charset) {
            C0042f.m158e(str, "<this>");
            C0042f.m158e(charset, "charset");
            byte[] bytes = str.getBytes(charset);
            C0042f.m157d(bytes, "this as java.lang.String).getBytes(charset)");
            return new ByteString(bytes);
        }

        /* renamed from: d */
        public final ByteString m21901d(String str) {
            C0042f.m158e(str, "<this>");
            ByteString byteString = new ByteString(C6345z.m24298a(str));
            byteString.m21889o(str);
            return byteString;
        }

        /* renamed from: e */
        public final ByteString m21902e(byte[] bArr, int i9, int i10) {
            C0042f.m158e(bArr, "<this>");
            int iM24156e = C6320a.m24156e(bArr, i10);
            C6320a.m24153b(bArr.length, i9, iM24156e);
            return new ByteString(C5222e.m20382e(bArr, i9, iM24156e + i9));
        }

        /* renamed from: g */
        public final ByteString m21903g(InputStream inputStream, int i9) throws IOException {
            C0042f.m158e(inputStream, "<this>");
            int i10 = 0;
            if (!(i9 >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + i9).toString());
            }
            byte[] bArr = new byte[i9];
            while (i10 < i9) {
                int i11 = inputStream.read(bArr, i10, i9 - i10);
                if (i11 == -1) {
                    throw new EOFException();
                }
                i10 += i11;
            }
            return new ByteString(bArr);
        }
    }

    public ByteString(byte[] bArr) {
        C0042f.m158e(bArr, "data");
        this.data = bArr;
    }

    private final void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException, SecurityException, IllegalArgumentException {
        ByteString byteStringM21903g = f19095d.m21903g(objectInputStream, objectInputStream.readInt());
        Field declaredField = ByteString.class.getDeclaredField("data");
        declaredField.setAccessible(true);
        declaredField.set(this, byteStringM21903g.data);
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    /* renamed from: a */
    public String mo21875a() {
        return C5527a.m21910c(m21879e(), null, 1, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r0 < r1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0028, code lost:
    
        if (r7 < r8) goto L13;
     */
    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int compareTo(ByteString byteString) {
        C0042f.m158e(byteString, "other");
        int iM21892r = m21892r();
        int iM21892r2 = byteString.m21892r();
        int iMin = Math.min(iM21892r, iM21892r2);
        for (int i9 = 0; i9 < iMin; i9++) {
            int iM21878d = m21878d(i9) & UnsignedBytes.MAX_VALUE;
            int iM21878d2 = byteString.m21878d(i9) & UnsignedBytes.MAX_VALUE;
            if (iM21878d == iM21878d2) {
            }
        }
        if (iM21892r == iM21892r2) {
            return 0;
        }
    }

    /* renamed from: c */
    public ByteString mo21877c(String str) throws NoSuchAlgorithmException {
        C0042f.m158e(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(this.data, 0, m21892r());
        byte[] bArrDigest = messageDigest.digest();
        C0042f.m155b(bArrDigest);
        return new ByteString(bArrDigest);
    }

    /* renamed from: d */
    public final byte m21878d(int i9) {
        return mo21885k(i9);
    }

    /* renamed from: e */
    public final byte[] m21879e() {
        return this.data;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.m21892r() == m21879e().length && byteString.mo21887m(0, m21879e(), 0, m21879e().length)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public final int m21880f() {
        return this.f19097b;
    }

    /* renamed from: g */
    public int mo21881g() {
        return m21879e().length;
    }

    /* renamed from: h */
    public final String m21882h() {
        return this.f19098c;
    }

    public int hashCode() {
        int iM21880f = m21880f();
        if (iM21880f != 0) {
            return iM21880f;
        }
        int iHashCode = Arrays.hashCode(m21879e());
        m21888n(iHashCode);
        return iHashCode;
    }

    /* renamed from: i */
    public String mo21883i() {
        char[] cArr = new char[m21879e().length * 2];
        int i9 = 0;
        for (byte b9 : m21879e()) {
            int i10 = i9 + 1;
            cArr[i9] = C6424b.m24584f()[(b9 >> 4) & 15];
            i9 = i10 + 1;
            cArr[i10] = C6424b.m24584f()[b9 & Ascii.f15389SI];
        }
        return C5255l.m20509h(cArr);
    }

    /* renamed from: j */
    public byte[] mo21884j() {
        return m21879e();
    }

    /* renamed from: k */
    public byte mo21885k(int i9) {
        return m21879e()[i9];
    }

    /* renamed from: l */
    public boolean mo21886l(int i9, ByteString byteString, int i10, int i11) {
        C0042f.m158e(byteString, "other");
        return byteString.mo21887m(i10, m21879e(), i9, i11);
    }

    /* renamed from: m */
    public boolean mo21887m(int i9, byte[] bArr, int i10, int i11) {
        C0042f.m158e(bArr, "other");
        return i9 >= 0 && i9 <= m21879e().length - i11 && i10 >= 0 && i10 <= bArr.length - i11 && C6320a.m24152a(m21879e(), i9, bArr, i10, i11);
    }

    /* renamed from: n */
    public final void m21888n(int i9) {
        this.f19097b = i9;
    }

    /* renamed from: o */
    public final void m21889o(String str) {
        this.f19098c = str;
    }

    /* renamed from: p */
    public final ByteString m21890p() {
        return mo21877c("SHA-1");
    }

    /* renamed from: q */
    public final ByteString m21891q() {
        return mo21877c("SHA-256");
    }

    /* renamed from: r */
    public final int m21892r() {
        return mo21881g();
    }

    /* renamed from: s */
    public final boolean m21893s(ByteString byteString) {
        C0042f.m158e(byteString, "prefix");
        return mo21886l(0, byteString, 0, byteString.m21892r());
    }

    /* renamed from: t */
    public ByteString mo21894t() {
        for (int i9 = 0; i9 < m21879e().length; i9++) {
            byte b9 = m21879e()[i9];
            if (b9 >= 65 && b9 <= 90) {
                byte[] bArrM21879e = m21879e();
                byte[] bArrCopyOf = Arrays.copyOf(bArrM21879e, bArrM21879e.length);
                C0042f.m157d(bArrCopyOf, "copyOf(this, size)");
                bArrCopyOf[i9] = (byte) (b9 + 32);
                for (int i10 = i9 + 1; i10 < bArrCopyOf.length; i10++) {
                    byte b10 = bArrCopyOf[i10];
                    if (b10 >= 65 && b10 <= 90) {
                        bArrCopyOf[i10] = (byte) (b10 + 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }

    public String toString() {
        String str;
        if (m21879e().length == 0) {
            str = "[size=0]";
        } else {
            int iM24581c = C6424b.m24581c(m21879e(), 64);
            if (iM24581c != -1) {
                String strM21895u = m21895u();
                String strSubstring = strM21895u.substring(0, iM24581c);
                C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                String strM20521t = C5255l.m20521t(C5255l.m20521t(C5255l.m20521t(strSubstring, "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null), StringUtils.f19107CR, "\\r", false, 4, null);
                if (iM24581c >= strM21895u.length()) {
                    return "[text=" + strM20521t + ']';
                }
                return "[size=" + m21879e().length + " text=" + strM20521t + "…]";
            }
            if (m21879e().length > 64) {
                StringBuilder sb = new StringBuilder();
                sb.append("[size=");
                sb.append(m21879e().length);
                sb.append(" hex=");
                int iM24155d = C6320a.m24155d(this, 64);
                if (iM24155d <= m21879e().length) {
                    if (!(iM24155d + 0 >= 0)) {
                        throw new IllegalArgumentException("endIndex < beginIndex".toString());
                    }
                    sb.append((iM24155d == m21879e().length ? this : new ByteString(C5222e.m20382e(m21879e(), 0, iM24155d))).mo21883i());
                    sb.append("…]");
                    return sb.toString();
                }
                throw new IllegalArgumentException(("endIndex > length(" + m21879e().length + ')').toString());
            }
            str = "[hex=" + mo21883i() + ']';
        }
        return str;
    }

    /* renamed from: u */
    public String m21895u() {
        String strM21882h = m21882h();
        if (strM21882h != null) {
            return strM21882h;
        }
        String strM24299b = C6345z.m24299b(mo21884j());
        m21889o(strM24299b);
        return strM24299b;
    }

    /* renamed from: v */
    public void mo21896v(C6322c c6322c, int i9, int i10) {
        C0042f.m158e(c6322c, "buffer");
        C6424b.m24582d(this, c6322c, i9, i10);
    }
}
