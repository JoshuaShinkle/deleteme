package p082h0;

import android.content.res.AssetManager;
import android.util.Log;
import android.util.Pair;
import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.p159io.IOUtils;

/* renamed from: h0.a */
/* loaded from: classes.dex */
public class C4978a {

    /* renamed from: D */
    public static final d[] f17089D;

    /* renamed from: E */
    public static final d[] f17090E;

    /* renamed from: F */
    public static final d[] f17091F;

    /* renamed from: G */
    public static final d[] f17092G;

    /* renamed from: H */
    public static final d[] f17093H;

    /* renamed from: I */
    public static final d f17094I;

    /* renamed from: J */
    public static final d[] f17095J;

    /* renamed from: K */
    public static final d[] f17096K;

    /* renamed from: L */
    public static final d[] f17097L;

    /* renamed from: M */
    public static final d[] f17098M;

    /* renamed from: N */
    public static final d[][] f17099N;

    /* renamed from: O */
    public static final d[] f17100O;

    /* renamed from: P */
    public static final d f17101P;

    /* renamed from: Q */
    public static final d f17102Q;

    /* renamed from: R */
    public static final HashMap<Integer, d>[] f17103R;

    /* renamed from: S */
    public static final HashMap<String, d>[] f17104S;

    /* renamed from: T */
    public static final HashSet<String> f17105T;

    /* renamed from: U */
    public static final HashMap<Integer, Integer> f17106U;

    /* renamed from: V */
    public static final Charset f17107V;

    /* renamed from: W */
    public static final byte[] f17108W;

    /* renamed from: X */
    public static final Pattern f17109X;

    /* renamed from: Y */
    public static final Pattern f17110Y;

    /* renamed from: z */
    public static SimpleDateFormat f17119z;

    /* renamed from: a */
    public final String f17120a;

    /* renamed from: b */
    public final AssetManager.AssetInputStream f17121b;

    /* renamed from: c */
    public int f17122c;

    /* renamed from: d */
    public final HashMap<String, c>[] f17123d;

    /* renamed from: e */
    public Set<Integer> f17124e;

    /* renamed from: f */
    public ByteOrder f17125f;

    /* renamed from: g */
    public boolean f17126g;

    /* renamed from: h */
    public int f17127h;

    /* renamed from: i */
    public int f17128i;

    /* renamed from: j */
    public byte[] f17129j;

    /* renamed from: k */
    public int f17130k;

    /* renamed from: l */
    public int f17131l;

    /* renamed from: m */
    public int f17132m;

    /* renamed from: n */
    public int f17133n;

    /* renamed from: o */
    public int f17134o;

    /* renamed from: p */
    public int f17135p;

    /* renamed from: q */
    public boolean f17136q;

    /* renamed from: r */
    public static final List<Integer> f17111r = Arrays.asList(1, 6, 3, 8);

    /* renamed from: s */
    public static final List<Integer> f17112s = Arrays.asList(2, 7, 4, 5);

    /* renamed from: t */
    public static final int[] f17113t = {8, 8, 8};

    /* renamed from: u */
    public static final int[] f17114u = {4};

    /* renamed from: v */
    public static final int[] f17115v = {8};

    /* renamed from: w */
    public static final byte[] f17116w = {-1, -40, -1};

    /* renamed from: x */
    public static final byte[] f17117x = {79, 76, 89, 77, 80, 0};

    /* renamed from: y */
    public static final byte[] f17118y = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};

    /* renamed from: A */
    public static final String[] f17086A = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};

    /* renamed from: B */
    public static final int[] f17087B = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};

    /* renamed from: C */
    public static final byte[] f17088C = {65, 83, 67, 73, 73, 0, 0, 0};

    /* renamed from: h0.a$b */
    public static class b extends FilterOutputStream {

        /* renamed from: b */
        public final OutputStream f17143b;

        /* renamed from: c */
        public ByteOrder f17144c;

        public b(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.f17143b = outputStream;
            this.f17144c = byteOrder;
        }

        /* renamed from: f */
        public void m19311f(ByteOrder byteOrder) {
            this.f17144c = byteOrder;
        }

        /* renamed from: u */
        public void m19312u(int i9) throws IOException {
            this.f17143b.write(i9);
        }

        /* renamed from: v */
        public void m19313v(int i9) throws IOException {
            ByteOrder byteOrder = this.f17144c;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.f17143b.write((i9 >>> 0) & 255);
                this.f17143b.write((i9 >>> 8) & 255);
                this.f17143b.write((i9 >>> 16) & 255);
                this.f17143b.write((i9 >>> 24) & 255);
                return;
            }
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.f17143b.write((i9 >>> 24) & 255);
                this.f17143b.write((i9 >>> 16) & 255);
                this.f17143b.write((i9 >>> 8) & 255);
                this.f17143b.write((i9 >>> 0) & 255);
            }
        }

        /* renamed from: w */
        public void m19314w(short s8) throws IOException {
            ByteOrder byteOrder = this.f17144c;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.f17143b.write((s8 >>> 0) & 255);
                this.f17143b.write((s8 >>> 8) & 255);
            } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.f17143b.write((s8 >>> 8) & 255);
                this.f17143b.write((s8 >>> 0) & 255);
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.f17143b.write(bArr);
        }

        /* renamed from: x */
        public void m19315x(long j9) throws IOException {
            m19313v((int) j9);
        }

        /* renamed from: y */
        public void m19316y(int i9) throws IOException {
            m19314w((short) i9);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i9, int i10) throws IOException {
            this.f17143b.write(bArr, i9, i10);
        }
    }

    /* renamed from: h0.a$c */
    public static class c {

        /* renamed from: a */
        public final int f17145a;

        /* renamed from: b */
        public final int f17146b;

        /* renamed from: c */
        public final byte[] f17147c;

        public c(int i9, int i10, byte[] bArr) {
            this.f17145a = i9;
            this.f17146b = i10;
            this.f17147c = bArr;
        }

        /* renamed from: a */
        public static c m19317a(String str) {
            if (str.length() == 1 && str.charAt(0) >= '0' && str.charAt(0) <= '1') {
                return new c(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
            }
            byte[] bytes = str.getBytes(C4978a.f17107V);
            return new c(1, bytes.length, bytes);
        }

        /* renamed from: b */
        public static c m19318b(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[C4978a.f17087B[12] * dArr.length]);
            byteBufferWrap.order(byteOrder);
            for (double d9 : dArr) {
                byteBufferWrap.putDouble(d9);
            }
            return new c(12, dArr.length, byteBufferWrap.array());
        }

        /* renamed from: c */
        public static c m19319c(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[C4978a.f17087B[9] * iArr.length]);
            byteBufferWrap.order(byteOrder);
            for (int i9 : iArr) {
                byteBufferWrap.putInt(i9);
            }
            return new c(9, iArr.length, byteBufferWrap.array());
        }

        /* renamed from: d */
        public static c m19320d(e[] eVarArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[C4978a.f17087B[10] * eVarArr.length]);
            byteBufferWrap.order(byteOrder);
            for (e eVar : eVarArr) {
                byteBufferWrap.putInt((int) eVar.f17152a);
                byteBufferWrap.putInt((int) eVar.f17153b);
            }
            return new c(10, eVarArr.length, byteBufferWrap.array());
        }

        /* renamed from: e */
        public static c m19321e(String str) {
            byte[] bytes = (str + (char) 0).getBytes(C4978a.f17107V);
            return new c(2, bytes.length, bytes);
        }

        /* renamed from: f */
        public static c m19322f(long j9, ByteOrder byteOrder) {
            return m19323g(new long[]{j9}, byteOrder);
        }

        /* renamed from: g */
        public static c m19323g(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[C4978a.f17087B[4] * jArr.length]);
            byteBufferWrap.order(byteOrder);
            for (long j9 : jArr) {
                byteBufferWrap.putInt((int) j9);
            }
            return new c(4, jArr.length, byteBufferWrap.array());
        }

        /* renamed from: h */
        public static c m19324h(e eVar, ByteOrder byteOrder) {
            return m19325i(new e[]{eVar}, byteOrder);
        }

        /* renamed from: i */
        public static c m19325i(e[] eVarArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[C4978a.f17087B[5] * eVarArr.length]);
            byteBufferWrap.order(byteOrder);
            for (e eVar : eVarArr) {
                byteBufferWrap.putInt((int) eVar.f17152a);
                byteBufferWrap.putInt((int) eVar.f17153b);
            }
            return new c(5, eVarArr.length, byteBufferWrap.array());
        }

        /* renamed from: j */
        public static c m19326j(int i9, ByteOrder byteOrder) {
            return m19327k(new int[]{i9}, byteOrder);
        }

        /* renamed from: k */
        public static c m19327k(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[C4978a.f17087B[3] * iArr.length]);
            byteBufferWrap.order(byteOrder);
            for (int i9 : iArr) {
                byteBufferWrap.putShort((short) i9);
            }
            return new c(3, iArr.length, byteBufferWrap.array());
        }

        /* renamed from: l */
        public double m19328l(ByteOrder byteOrder) throws Throwable {
            Object objM19331o = m19331o(byteOrder);
            if (objM19331o == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            }
            if (objM19331o instanceof String) {
                return Double.parseDouble((String) objM19331o);
            }
            if (objM19331o instanceof long[]) {
                if (((long[]) objM19331o).length == 1) {
                    return r5[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (objM19331o instanceof int[]) {
                if (((int[]) objM19331o).length == 1) {
                    return r5[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (objM19331o instanceof double[]) {
                double[] dArr = (double[]) objM19331o;
                if (dArr.length == 1) {
                    return dArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(objM19331o instanceof e[])) {
                throw new NumberFormatException("Couldn't find a double value");
            }
            e[] eVarArr = (e[]) objM19331o;
            if (eVarArr.length == 1) {
                return eVarArr[0].m19334a();
            }
            throw new NumberFormatException("There are more than one component");
        }

        /* renamed from: m */
        public int m19329m(ByteOrder byteOrder) throws Throwable {
            Object objM19331o = m19331o(byteOrder);
            if (objM19331o == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            }
            if (objM19331o instanceof String) {
                return Integer.parseInt((String) objM19331o);
            }
            if (objM19331o instanceof long[]) {
                long[] jArr = (long[]) objM19331o;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(objM19331o instanceof int[])) {
                throw new NumberFormatException("Couldn't find a integer value");
            }
            int[] iArr = (int[]) objM19331o;
            if (iArr.length == 1) {
                return iArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }

        /* renamed from: n */
        public String m19330n(ByteOrder byteOrder) throws Throwable {
            Object objM19331o = m19331o(byteOrder);
            if (objM19331o == null) {
                return null;
            }
            if (objM19331o instanceof String) {
                return (String) objM19331o;
            }
            StringBuilder sb = new StringBuilder();
            int i9 = 0;
            if (objM19331o instanceof long[]) {
                long[] jArr = (long[]) objM19331o;
                while (i9 < jArr.length) {
                    sb.append(jArr[i9]);
                    i9++;
                    if (i9 != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (objM19331o instanceof int[]) {
                int[] iArr = (int[]) objM19331o;
                while (i9 < iArr.length) {
                    sb.append(iArr[i9]);
                    i9++;
                    if (i9 != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (objM19331o instanceof double[]) {
                double[] dArr = (double[]) objM19331o;
                while (i9 < dArr.length) {
                    sb.append(dArr[i9]);
                    i9++;
                    if (i9 != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (!(objM19331o instanceof e[])) {
                return null;
            }
            e[] eVarArr = (e[]) objM19331o;
            while (i9 < eVarArr.length) {
                sb.append(eVarArr[i9].f17152a);
                sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                sb.append(eVarArr[i9].f17153b);
                i9++;
                if (i9 != eVarArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        /* JADX WARN: Not initialized variable reg: 3, insn: 0x0198: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:151:0x0198 */
        /* JADX WARN: Removed duplicated region for block: B:182:0x019b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* renamed from: o */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object m19331o(ByteOrder byteOrder) throws Throwable {
            a aVar;
            InputStream inputStream;
            byte b9;
            byte b10;
            byte[] bArr;
            InputStream inputStream2 = null;
            try {
                try {
                    aVar = new a(this.f17147c);
                    try {
                        aVar.m19310w(byteOrder);
                        int length = 0;
                        boolean z8 = true;
                        switch (this.f17145a) {
                            case 1:
                            case 6:
                                byte[] bArr2 = this.f17147c;
                                if (bArr2.length != 1 || (b9 = bArr2[0]) < 0 || b9 > 1) {
                                    String str = new String(bArr2, C4978a.f17107V);
                                    try {
                                        aVar.close();
                                    } catch (IOException e9) {
                                        Log.e("ExifInterface", "IOException occurred while closing InputStream", e9);
                                    }
                                    return str;
                                }
                                String str2 = new String(new char[]{(char) (b9 + 48)});
                                try {
                                    aVar.close();
                                } catch (IOException e10) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e10);
                                }
                                return str2;
                            case 2:
                            case 7:
                                if (this.f17146b >= C4978a.f17088C.length) {
                                    int i9 = 0;
                                    while (true) {
                                        bArr = C4978a.f17088C;
                                        if (i9 < bArr.length) {
                                            if (this.f17147c[i9] != bArr[i9]) {
                                                z8 = false;
                                            } else {
                                                i9++;
                                            }
                                        }
                                    }
                                    if (z8) {
                                        length = bArr.length;
                                    }
                                }
                                StringBuilder sb = new StringBuilder();
                                while (length < this.f17146b && (b10 = this.f17147c[length]) != 0) {
                                    if (b10 >= 32) {
                                        sb.append((char) b10);
                                    } else {
                                        sb.append('?');
                                    }
                                    length++;
                                }
                                String string = sb.toString();
                                try {
                                    aVar.close();
                                } catch (IOException e11) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e11);
                                }
                                return string;
                            case 3:
                                int[] iArr = new int[this.f17146b];
                                while (length < this.f17146b) {
                                    iArr[length] = aVar.readUnsignedShort();
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e12) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e12);
                                }
                                return iArr;
                            case 4:
                                long[] jArr = new long[this.f17146b];
                                while (length < this.f17146b) {
                                    jArr[length] = aVar.m19308u();
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e13) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e13);
                                }
                                return jArr;
                            case 5:
                                e[] eVarArr = new e[this.f17146b];
                                while (length < this.f17146b) {
                                    eVarArr[length] = new e(aVar.m19308u(), aVar.m19308u());
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e14) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e14);
                                }
                                return eVarArr;
                            case 8:
                                int[] iArr2 = new int[this.f17146b];
                                while (length < this.f17146b) {
                                    iArr2[length] = aVar.readShort();
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e15) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e15);
                                }
                                return iArr2;
                            case 9:
                                int[] iArr3 = new int[this.f17146b];
                                while (length < this.f17146b) {
                                    iArr3[length] = aVar.readInt();
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e16) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e16);
                                }
                                return iArr3;
                            case 10:
                                e[] eVarArr2 = new e[this.f17146b];
                                while (length < this.f17146b) {
                                    eVarArr2[length] = new e(aVar.readInt(), aVar.readInt());
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e17) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e17);
                                }
                                return eVarArr2;
                            case 11:
                                double[] dArr = new double[this.f17146b];
                                while (length < this.f17146b) {
                                    dArr[length] = aVar.readFloat();
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e18) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e18);
                                }
                                return dArr;
                            case 12:
                                double[] dArr2 = new double[this.f17146b];
                                while (length < this.f17146b) {
                                    dArr2[length] = aVar.readDouble();
                                    length++;
                                }
                                try {
                                    aVar.close();
                                } catch (IOException e19) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e19);
                                }
                                return dArr2;
                            default:
                                try {
                                    aVar.close();
                                } catch (IOException e20) {
                                    Log.e("ExifInterface", "IOException occurred while closing InputStream", e20);
                                }
                                return null;
                        }
                    } catch (IOException e21) {
                        e = e21;
                        Log.w("ExifInterface", "IOException occurred during reading a value", e);
                        if (aVar != null) {
                            try {
                                aVar.close();
                            } catch (IOException e22) {
                                Log.e("ExifInterface", "IOException occurred while closing InputStream", e22);
                            }
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e23) {
                            Log.e("ExifInterface", "IOException occurred while closing InputStream", e23);
                        }
                    }
                    throw th;
                }
            } catch (IOException e24) {
                e = e24;
                aVar = null;
            } catch (Throwable th2) {
                th = th2;
                if (inputStream2 != null) {
                }
                throw th;
            }
        }

        /* renamed from: p */
        public int m19332p() {
            return C4978a.f17087B[this.f17145a] * this.f17146b;
        }

        public String toString() {
            return "(" + C4978a.f17086A[this.f17145a] + ", data length:" + this.f17147c.length + ")";
        }
    }

    /* renamed from: h0.a$e */
    public static class e {

        /* renamed from: a */
        public final long f17152a;

        /* renamed from: b */
        public final long f17153b;

        public e(double d9) {
            this((long) (d9 * 10000.0d), 10000L);
        }

        /* renamed from: a */
        public double m19334a() {
            return this.f17152a / this.f17153b;
        }

        public String toString() {
            return this.f17152a + "/" + this.f17153b;
        }

        public e(long j9, long j10) {
            if (j10 == 0) {
                this.f17152a = 0L;
                this.f17153b = 1L;
            } else {
                this.f17152a = j9;
                this.f17153b = j10;
            }
        }
    }

    static {
        d[] dVarArr = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d("ImageWidth", 256, 3, 4), new d("ImageLength", 257, 3, 4), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d("StripOffsets", 273, 3, 4), new d("Orientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d("RowsPerStrip", 278, 3, 4), new d("StripByteCounts", 279, 3, 4), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d(NetworkManager.REPORT_TARGET_RESON.COPYRIGHT, 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("SensorTopBorder", 4, 4), new d("SensorLeftBorder", 5, 4), new d("SensorBottomBorder", 6, 4), new d("SensorRightBorder", 7, 4), new d("ISO", 23, 3), new d("JpgFromRaw", 46, 7)};
        f17089D = dVarArr;
        d[] dVarArr2 = {new d("ExposureTime", 33434, 5), new d("FNumber", 33437, 5), new d("ExposureProgram", 34850, 3), new d("SpectralSensitivity", 34852, 2), new d("PhotographicSensitivity", 34855, 3), new d("OECF", 34856, 7), new d("ExifVersion", 36864, 2), new d("DateTimeOriginal", 36867, 2), new d("DateTimeDigitized", 36868, 2), new d("ComponentsConfiguration", 37121, 7), new d("CompressedBitsPerPixel", 37122, 5), new d("ShutterSpeedValue", 37377, 10), new d("ApertureValue", 37378, 5), new d("BrightnessValue", 37379, 10), new d("ExposureBiasValue", 37380, 10), new d("MaxApertureValue", 37381, 5), new d("SubjectDistance", 37382, 5), new d("MeteringMode", 37383, 3), new d("LightSource", 37384, 3), new d("Flash", 37385, 3), new d("FocalLength", 37386, 5), new d("SubjectArea", 37396, 3), new d("MakerNote", 37500, 7), new d("UserComment", 37510, 7), new d("SubSecTime", 37520, 2), new d("SubSecTimeOriginal", 37521, 2), new d("SubSecTimeDigitized", 37522, 2), new d("FlashpixVersion", 40960, 7), new d("ColorSpace", 40961, 3), new d("PixelXDimension", 40962, 3, 4), new d("PixelYDimension", 40963, 3, 4), new d("RelatedSoundFile", 40964, 2), new d("InteroperabilityIFDPointer", 40965, 4), new d("FlashEnergy", 41483, 5), new d("SpatialFrequencyResponse", 41484, 7), new d("FocalPlaneXResolution", 41486, 5), new d("FocalPlaneYResolution", 41487, 5), new d("FocalPlaneResolutionUnit", 41488, 3), new d("SubjectLocation", 41492, 3), new d("ExposureIndex", 41493, 5), new d("SensingMethod", 41495, 3), new d("FileSource", 41728, 7), new d("SceneType", 41729, 7), new d("CFAPattern", 41730, 7), new d("CustomRendered", 41985, 3), new d("ExposureMode", 41986, 3), new d("WhiteBalance", 41987, 3), new d("DigitalZoomRatio", 41988, 5), new d("FocalLengthIn35mmFilm", 41989, 3), new d("SceneCaptureType", 41990, 3), new d("GainControl", 41991, 3), new d("Contrast", 41992, 3), new d("Saturation", 41993, 3), new d("Sharpness", 41994, 3), new d("DeviceSettingDescription", 41995, 7), new d("SubjectDistanceRange", 41996, 3), new d("ImageUniqueID", 42016, 2), new d("DNGVersion", 50706, 1), new d("DefaultCropSize", 50720, 3, 4)};
        f17090E = dVarArr2;
        d[] dVarArr3 = {new d("GPSVersionID", 0, 1), new d("GPSLatitudeRef", 1, 2), new d("GPSLatitude", 2, 5), new d("GPSLongitudeRef", 3, 2), new d("GPSLongitude", 4, 5), new d("GPSAltitudeRef", 5, 1), new d("GPSAltitude", 6, 5), new d("GPSTimeStamp", 7, 5), new d("GPSSatellites", 8, 2), new d("GPSStatus", 9, 2), new d("GPSMeasureMode", 10, 2), new d("GPSDOP", 11, 5), new d("GPSSpeedRef", 12, 2), new d("GPSSpeed", 13, 5), new d("GPSTrackRef", 14, 2), new d("GPSTrack", 15, 5), new d("GPSImgDirectionRef", 16, 2), new d("GPSImgDirection", 17, 5), new d("GPSMapDatum", 18, 2), new d("GPSDestLatitudeRef", 19, 2), new d("GPSDestLatitude", 20, 5), new d("GPSDestLongitudeRef", 21, 2), new d("GPSDestLongitude", 22, 5), new d("GPSDestBearingRef", 23, 2), new d("GPSDestBearing", 24, 5), new d("GPSDestDistanceRef", 25, 2), new d("GPSDestDistance", 26, 5), new d("GPSProcessingMethod", 27, 7), new d("GPSAreaInformation", 28, 7), new d("GPSDateStamp", 29, 2), new d("GPSDifferential", 30, 3)};
        f17091F = dVarArr3;
        d[] dVarArr4 = {new d("InteroperabilityIndex", 1, 2)};
        f17092G = dVarArr4;
        d[] dVarArr5 = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d("ThumbnailImageWidth", 256, 3, 4), new d("ThumbnailImageLength", 257, 3, 4), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d("StripOffsets", 273, 3, 4), new d("Orientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d("RowsPerStrip", 278, 3, 4), new d("StripByteCounts", 279, 3, 4), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d(NetworkManager.REPORT_TARGET_RESON.COPYRIGHT, 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("DNGVersion", 50706, 1), new d("DefaultCropSize", 50720, 3, 4)};
        f17093H = dVarArr5;
        f17094I = new d("StripOffsets", 273, 3);
        d[] dVarArr6 = {new d("ThumbnailImage", 256, 7), new d("CameraSettingsIFDPointer", 8224, 4), new d("ImageProcessingIFDPointer", 8256, 4)};
        f17095J = dVarArr6;
        d[] dVarArr7 = {new d("PreviewImageStart", 257, 4), new d("PreviewImageLength", 258, 4)};
        f17096K = dVarArr7;
        d[] dVarArr8 = {new d("AspectFrame", 4371, 3)};
        f17097L = dVarArr8;
        d[] dVarArr9 = {new d("ColorSpace", 55, 3)};
        f17098M = dVarArr9;
        d[][] dVarArr10 = {dVarArr, dVarArr2, dVarArr3, dVarArr4, dVarArr5, dVarArr, dVarArr6, dVarArr7, dVarArr8, dVarArr9};
        f17099N = dVarArr10;
        f17100O = new d[]{new d("SubIFDPointer", 330, 4), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("InteroperabilityIFDPointer", 40965, 4), new d("CameraSettingsIFDPointer", 8224, 1), new d("ImageProcessingIFDPointer", 8256, 1)};
        f17101P = new d("JPEGInterchangeFormat", 513, 4);
        f17102Q = new d("JPEGInterchangeFormatLength", 514, 4);
        f17103R = new HashMap[dVarArr10.length];
        f17104S = new HashMap[dVarArr10.length];
        f17105T = new HashSet<>(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        f17106U = new HashMap<>();
        Charset charsetForName = Charset.forName("US-ASCII");
        f17107V = charsetForName;
        f17108W = "Exif\u0000\u0000".getBytes(charsetForName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        f17119z = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i9 = 0;
        while (true) {
            d[][] dVarArr11 = f17099N;
            if (i9 >= dVarArr11.length) {
                HashMap<Integer, Integer> map = f17106U;
                d[] dVarArr12 = f17100O;
                map.put(Integer.valueOf(dVarArr12[0].f17148a), 5);
                map.put(Integer.valueOf(dVarArr12[1].f17148a), 1);
                map.put(Integer.valueOf(dVarArr12[2].f17148a), 2);
                map.put(Integer.valueOf(dVarArr12[3].f17148a), 3);
                map.put(Integer.valueOf(dVarArr12[4].f17148a), 7);
                map.put(Integer.valueOf(dVarArr12[5].f17148a), 8);
                f17109X = Pattern.compile(".*[1-9].*");
                f17110Y = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
                return;
            }
            f17103R[i9] = new HashMap<>();
            f17104S[i9] = new HashMap<>();
            for (d dVar : dVarArr11[i9]) {
                f17103R[i9].put(Integer.valueOf(dVar.f17148a), dVar);
                f17104S[i9].put(dVar.f17149b, dVar);
            }
            i9++;
        }
    }

    public C4978a(String str) throws Throwable {
        d[][] dVarArr = f17099N;
        this.f17123d = new HashMap[dVarArr.length];
        this.f17124e = new HashSet(dVarArr.length);
        this.f17125f = ByteOrder.BIG_ENDIAN;
        if (str == null) {
            throw new IllegalArgumentException("filename cannot be null");
        }
        FileInputStream fileInputStream = null;
        this.f17121b = null;
        this.f17120a = str;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                m19305y(fileInputStream2);
                m19268b(fileInputStream2);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                m19268b(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: b */
    public static void m19268b(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e9) {
                throw e9;
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: c */
    public static long[] m19269c(Object obj) {
        if (!(obj instanceof int[])) {
            if (obj instanceof long[]) {
                return (long[]) obj;
            }
            return null;
        }
        int[] iArr = (int[]) obj;
        long[] jArr = new long[iArr.length];
        for (int i9 = 0; i9 < iArr.length; i9++) {
            jArr[i9] = iArr[i9];
        }
        return jArr;
    }

    /* renamed from: d */
    public static int m19270d(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
        int i9 = 0;
        while (true) {
            int i10 = inputStream.read(bArr);
            if (i10 == -1) {
                return i9;
            }
            i9 += i10;
            outputStream.write(bArr, 0, i10);
        }
    }

    /* renamed from: p */
    public static Pair<Integer, Integer> m19271p(String str) throws NumberFormatException {
        if (str.contains(",")) {
            String[] strArrSplit = str.split(",", -1);
            Pair<Integer, Integer> pairM19271p = m19271p(strArrSplit[0]);
            if (((Integer) pairM19271p.first).intValue() == 2) {
                return pairM19271p;
            }
            for (int i9 = 1; i9 < strArrSplit.length; i9++) {
                Pair<Integer, Integer> pairM19271p2 = m19271p(strArrSplit[i9]);
                int iIntValue = (((Integer) pairM19271p2.first).equals(pairM19271p.first) || ((Integer) pairM19271p2.second).equals(pairM19271p.first)) ? ((Integer) pairM19271p.first).intValue() : -1;
                int iIntValue2 = (((Integer) pairM19271p.second).intValue() == -1 || !(((Integer) pairM19271p2.first).equals(pairM19271p.second) || ((Integer) pairM19271p2.second).equals(pairM19271p.second))) ? -1 : ((Integer) pairM19271p.second).intValue();
                if (iIntValue == -1 && iIntValue2 == -1) {
                    return new Pair<>(2, -1);
                }
                if (iIntValue == -1) {
                    pairM19271p = new Pair<>(Integer.valueOf(iIntValue2), -1);
                } else if (iIntValue2 == -1) {
                    pairM19271p = new Pair<>(Integer.valueOf(iIntValue), -1);
                }
            }
            return pairM19271p;
        }
        if (!str.contains("/")) {
            try {
                try {
                    Long lValueOf = Long.valueOf(Long.parseLong(str));
                    return (lValueOf.longValue() < 0 || lValueOf.longValue() > 65535) ? lValueOf.longValue() < 0 ? new Pair<>(9, -1) : new Pair<>(4, -1) : new Pair<>(3, 4);
                } catch (NumberFormatException unused) {
                    return new Pair<>(2, -1);
                }
            } catch (NumberFormatException unused2) {
                Double.parseDouble(str);
                return new Pair<>(12, -1);
            }
        }
        String[] strArrSplit2 = str.split("/", -1);
        if (strArrSplit2.length == 2) {
            try {
                long j9 = (long) Double.parseDouble(strArrSplit2[0]);
                long j10 = (long) Double.parseDouble(strArrSplit2[1]);
                if (j9 >= 0 && j10 >= 0) {
                    if (j9 <= 2147483647L && j10 <= 2147483647L) {
                        return new Pair<>(10, 5);
                    }
                    return new Pair<>(5, -1);
                }
                return new Pair<>(10, -1);
            } catch (NumberFormatException unused3) {
            }
        }
        return new Pair<>(2, -1);
    }

    /* renamed from: s */
    public static boolean m19272s(byte[] bArr) {
        int i9 = 0;
        while (true) {
            byte[] bArr2 = f17116w;
            if (i9 >= bArr2.length) {
                return true;
            }
            if (bArr[i9] != bArr2[i9]) {
                return false;
            }
            i9++;
        }
    }

    /* renamed from: A */
    public final ByteOrder m19273A(a aVar) throws IOException {
        short s8 = aVar.readShort();
        if (s8 == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (s8 == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(s8));
    }

    /* renamed from: B */
    public final void m19274B(byte[] bArr, int i9) throws IOException {
        a aVar = new a(bArr);
        m19306z(aVar, bArr.length);
        m19275C(aVar, i9);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x022f  */
    /* renamed from: C */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m19275C(a aVar, int i9) throws IOException {
        short s8;
        String str;
        long j9;
        boolean z8;
        short s9;
        d dVar;
        int i10;
        int i11;
        int unsignedShort;
        long jM19308u;
        long j10;
        this.f17124e.add(Integer.valueOf(aVar.f17142e));
        if (aVar.f17142e + 2 > aVar.f17141d) {
            return;
        }
        short s10 = aVar.readShort();
        if (aVar.f17142e + (s10 * 12) > aVar.f17141d || s10 <= 0) {
            return;
        }
        short s11 = 0;
        while (s11 < s10) {
            int unsignedShort2 = aVar.readUnsignedShort();
            int unsignedShort3 = aVar.readUnsignedShort();
            int i12 = aVar.readInt();
            long jM19307f = aVar.m19307f() + 4;
            d dVar2 = f17103R[i9].get(Integer.valueOf(unsignedShort2));
            if (dVar2 == null) {
                Log.w("ExifInterface", "Skip the tag entry since tag number is not defined: " + unsignedShort2);
            } else {
                if (unsignedShort3 > 0) {
                    if (unsignedShort3 < f17087B.length) {
                        if (dVar2.m19333a(unsignedShort3)) {
                            if (unsignedShort3 == 7) {
                                unsignedShort3 = dVar2.f17150c;
                            }
                            str = "ExifInterface";
                            s8 = s11;
                            j9 = r6[unsignedShort3] * i12;
                            if (j9 < 0 || j9 > 2147483647L) {
                                Log.w(str, "Skip the tag entry since the number of components is invalid: " + i12);
                                z8 = false;
                                if (z8) {
                                    aVar.m19309v(jM19307f);
                                    s9 = s10;
                                } else {
                                    if (j9 > 4) {
                                        int i13 = aVar.readInt();
                                        int i14 = this.f17122c;
                                        s9 = s10;
                                        if (i14 == 7) {
                                            if ("MakerNote".equals(dVar2.f17149b)) {
                                                this.f17132m = i13;
                                            } else if (i9 == 6 && "ThumbnailImage".equals(dVar2.f17149b)) {
                                                this.f17133n = i13;
                                                this.f17134o = i12;
                                                c cVarM19326j = c.m19326j(6, this.f17125f);
                                                i10 = unsignedShort3;
                                                i11 = i12;
                                                c cVarM19322f = c.m19322f(this.f17133n, this.f17125f);
                                                j10 = jM19307f;
                                                c cVarM19322f2 = c.m19322f(this.f17134o, this.f17125f);
                                                this.f17123d[4].put("Compression", cVarM19326j);
                                                this.f17123d[4].put("JPEGInterchangeFormat", cVarM19322f);
                                                this.f17123d[4].put("JPEGInterchangeFormatLength", cVarM19322f2);
                                            }
                                            i10 = unsignedShort3;
                                            i11 = i12;
                                            j10 = jM19307f;
                                        } else {
                                            i10 = unsignedShort3;
                                            i11 = i12;
                                            j10 = jM19307f;
                                            if (i14 == 10 && "JpgFromRaw".equals(dVar2.f17149b)) {
                                                this.f17135p = i13;
                                            }
                                        }
                                        long j11 = i13;
                                        dVar = dVar2;
                                        if (j11 + j9 <= aVar.f17141d) {
                                            aVar.m19309v(j11);
                                            jM19307f = j10;
                                        } else {
                                            Log.w(str, "Skip the tag entry since data offset is invalid: " + i13);
                                            aVar.m19309v(j10);
                                        }
                                    } else {
                                        s9 = s10;
                                        dVar = dVar2;
                                        i10 = unsignedShort3;
                                        i11 = i12;
                                    }
                                    Integer num = f17106U.get(Integer.valueOf(unsignedShort2));
                                    if (num != null) {
                                        int i15 = i10;
                                        if (i15 != 3) {
                                            if (i15 == 4) {
                                                jM19308u = aVar.m19308u();
                                            } else if (i15 == 8) {
                                                unsignedShort = aVar.readShort();
                                            } else if (i15 == 9 || i15 == 13) {
                                                unsignedShort = aVar.readInt();
                                            } else {
                                                jM19308u = -1;
                                            }
                                            if (jM19308u > 0 || jM19308u >= aVar.f17141d) {
                                                Log.w(str, "Skip jump into the IFD since its offset is invalid: " + jM19308u);
                                            } else if (this.f17124e.contains(Integer.valueOf((int) jM19308u))) {
                                                Log.w(str, "Skip jump into the IFD since it has already been read: IfdType " + num + " (at " + jM19308u + ")");
                                            } else {
                                                aVar.m19309v(jM19308u);
                                                m19275C(aVar, num.intValue());
                                            }
                                            aVar.m19309v(jM19307f);
                                        } else {
                                            unsignedShort = aVar.readUnsignedShort();
                                        }
                                        jM19308u = unsignedShort;
                                        if (jM19308u > 0) {
                                            Log.w(str, "Skip jump into the IFD since its offset is invalid: " + jM19308u);
                                            aVar.m19309v(jM19307f);
                                        }
                                    } else {
                                        byte[] bArr = new byte[(int) j9];
                                        aVar.readFully(bArr);
                                        c cVar = new c(i10, i11, bArr);
                                        d dVar3 = dVar;
                                        this.f17123d[i9].put(dVar3.f17149b, cVar);
                                        if ("DNGVersion".equals(dVar3.f17149b)) {
                                            this.f17122c = 3;
                                        }
                                        if ((("Make".equals(dVar3.f17149b) || "Model".equals(dVar3.f17149b)) && cVar.m19330n(this.f17125f).contains("PENTAX")) || ("Compression".equals(dVar3.f17149b) && cVar.m19329m(this.f17125f) == 65535)) {
                                            this.f17122c = 8;
                                        }
                                        if (aVar.m19307f() != jM19307f) {
                                            aVar.m19309v(jM19307f);
                                        }
                                    }
                                }
                                s11 = (short) (s8 + 1);
                                s10 = s9;
                            } else {
                                z8 = true;
                                if (z8) {
                                }
                                s11 = (short) (s8 + 1);
                                s10 = s9;
                            }
                        } else {
                            Log.w("ExifInterface", "Skip the tag entry since data format (" + f17086A[unsignedShort3] + ") is unexpected for tag: " + dVar2.f17149b);
                        }
                    }
                }
                s8 = s11;
                str = "ExifInterface";
                Log.w(str, "Skip the tag entry since data format is invalid: " + unsignedShort3);
                j9 = 0;
                z8 = false;
                if (z8) {
                }
                s11 = (short) (s8 + 1);
                s10 = s9;
            }
            s8 = s11;
            str = "ExifInterface";
            j9 = 0;
            z8 = false;
            if (z8) {
            }
            s11 = (short) (s8 + 1);
            s10 = s9;
        }
        if (aVar.m19307f() + 4 <= aVar.f17141d) {
            int i16 = aVar.readInt();
            long j12 = i16;
            if (j12 <= 0 || i16 >= aVar.f17141d) {
                Log.w("ExifInterface", "Stop reading file since a wrong offset may cause an infinite loop: " + i16);
                return;
            }
            if (this.f17124e.contains(Integer.valueOf(i16))) {
                Log.w("ExifInterface", "Stop reading file since re-reading an IFD may cause an infinite loop: " + i16);
                return;
            }
            aVar.m19309v(j12);
            if (this.f17123d[4].isEmpty()) {
                m19275C(aVar, 4);
            } else if (this.f17123d[5].isEmpty()) {
                m19275C(aVar, 5);
            }
        }
    }

    /* renamed from: D */
    public final void m19276D(String str) {
        for (int i9 = 0; i9 < f17099N.length; i9++) {
            this.f17123d[i9].remove(str);
        }
    }

    /* renamed from: E */
    public final void m19277E(a aVar, int i9) throws IOException {
        c cVar;
        c cVar2 = this.f17123d[i9].get("ImageLength");
        c cVar3 = this.f17123d[i9].get("ImageWidth");
        if ((cVar2 == null || cVar3 == null) && (cVar = this.f17123d[i9].get("JPEGInterchangeFormat")) != null) {
            m19290h(aVar, cVar.m19329m(this.f17125f), i9);
        }
    }

    /* renamed from: F */
    public void m19278F() throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        if (!this.f17136q || this.f17122c != 4) {
            throw new IOException("ExifInterface only supports saving attributes on JPEG formats.");
        }
        if (this.f17120a == null) {
            throw new IOException("ExifInterface does not support saving attributes for the current input.");
        }
        this.f17129j = m19296n();
        File file = new File(this.f17120a + ".tmp");
        if (!new File(this.f17120a).renameTo(file)) {
            throw new IOException("Could not rename to " + file.getAbsolutePath());
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(this.f17120a);
                try {
                    m19279G(fileInputStream, fileOutputStream);
                    m19268b(fileInputStream);
                    m19268b(fileOutputStream);
                    file.delete();
                    this.f17129j = null;
                } catch (Throwable th2) {
                    th = th2;
                    m19268b(fileInputStream);
                    m19268b(fileOutputStream);
                    file.delete();
                    throw th;
                }
            } catch (Throwable th3) {
                fileOutputStream = null;
                th = th3;
            }
        } catch (Throwable th4) {
            fileOutputStream = null;
            th = th4;
            fileInputStream = null;
        }
    }

    /* renamed from: G */
    public final void m19279G(InputStream inputStream, OutputStream outputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        b bVar = new b(outputStream, ByteOrder.BIG_ENDIAN);
        if (dataInputStream.readByte() != -1) {
            throw new IOException("Invalid marker");
        }
        bVar.m19312u(-1);
        if (dataInputStream.readByte() != -40) {
            throw new IOException("Invalid marker");
        }
        bVar.m19312u(-40);
        bVar.m19312u(-1);
        bVar.m19312u(-31);
        m19285M(bVar, 6);
        byte[] bArr = new byte[4096];
        while (dataInputStream.readByte() == -1) {
            byte b9 = dataInputStream.readByte();
            if (b9 == -39 || b9 == -38) {
                bVar.m19312u(-1);
                bVar.m19312u(b9);
                m19270d(dataInputStream, bVar);
                return;
            }
            if (b9 != -31) {
                bVar.m19312u(-1);
                bVar.m19312u(b9);
                int unsignedShort = dataInputStream.readUnsignedShort();
                bVar.m19316y(unsignedShort);
                int i9 = unsignedShort - 2;
                if (i9 < 0) {
                    throw new IOException("Invalid length");
                }
                while (i9 > 0) {
                    int i10 = dataInputStream.read(bArr, 0, Math.min(i9, 4096));
                    if (i10 >= 0) {
                        bVar.write(bArr, 0, i10);
                        i9 -= i10;
                    }
                }
            } else {
                int unsignedShort2 = dataInputStream.readUnsignedShort() - 2;
                if (unsignedShort2 < 0) {
                    throw new IOException("Invalid length");
                }
                byte[] bArr2 = new byte[6];
                if (unsignedShort2 >= 6) {
                    if (dataInputStream.read(bArr2) != 6) {
                        throw new IOException("Invalid exif");
                    }
                    if (Arrays.equals(bArr2, f17108W)) {
                        int i11 = unsignedShort2 - 6;
                        if (dataInputStream.skipBytes(i11) != i11) {
                            throw new IOException("Invalid length");
                        }
                    }
                }
                bVar.m19312u(-1);
                bVar.m19312u(b9);
                bVar.m19316y(unsignedShort2 + 2);
                if (unsignedShort2 >= 6) {
                    unsignedShort2 -= 6;
                    bVar.write(bArr2);
                }
                while (unsignedShort2 > 0) {
                    int i12 = dataInputStream.read(bArr, 0, Math.min(unsignedShort2, 4096));
                    if (i12 >= 0) {
                        bVar.write(bArr, 0, i12);
                        unsignedShort2 -= i12;
                    }
                }
            }
        }
        throw new IOException("Invalid marker");
    }

    /* renamed from: H */
    public void m19280H(String str, String str2) throws NumberFormatException {
        d dVar;
        int i9;
        String str3;
        int i10;
        String str4;
        String string = str2;
        String str5 = "ISOSpeedRatings".equals(str) ? "PhotographicSensitivity" : str;
        int i11 = 2;
        String str6 = "ExifInterface";
        int i12 = 1;
        if (string != null && f17105T.contains(str5)) {
            if (str5.equals("GPSTimeStamp")) {
                Matcher matcher = f17110Y.matcher(string);
                if (!matcher.find()) {
                    Log.w("ExifInterface", "Invalid value for " + str5 + " : " + string);
                    return;
                }
                string = Integer.parseInt(matcher.group(1)) + "/1," + Integer.parseInt(matcher.group(2)) + "/1," + Integer.parseInt(matcher.group(3)) + "/1";
            } else {
                try {
                    string = new e(Double.parseDouble(str2)).toString();
                } catch (NumberFormatException unused) {
                    Log.w("ExifInterface", "Invalid value for " + str5 + " : " + string);
                    return;
                }
            }
        }
        int i13 = 0;
        int i14 = 0;
        while (i14 < f17099N.length) {
            if ((i14 != 4 || this.f17126g) && (dVar = f17104S[i14].get(str5)) != null) {
                if (string != null) {
                    Pair<Integer, Integer> pairM19271p = m19271p(string);
                    int i15 = -1;
                    if (dVar.f17150c == ((Integer) pairM19271p.first).intValue() || dVar.f17150c == ((Integer) pairM19271p.second).intValue()) {
                        i9 = dVar.f17150c;
                    } else {
                        int i16 = dVar.f17151d;
                        if (i16 == -1 || !(i16 == ((Integer) pairM19271p.first).intValue() || dVar.f17151d == ((Integer) pairM19271p.second).intValue())) {
                            int i17 = dVar.f17150c;
                            if (i17 == i12 || i17 == 7 || i17 == i11) {
                                i9 = i17;
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Given tag (");
                                sb.append(str5);
                                sb.append(") value didn't match with one of expected ");
                                sb.append("formats: ");
                                String[] strArr = f17086A;
                                sb.append(strArr[dVar.f17150c]);
                                sb.append(dVar.f17151d == -1 ? "" : ", " + strArr[dVar.f17151d]);
                                sb.append(" (guess: ");
                                sb.append(strArr[((Integer) pairM19271p.first).intValue()]);
                                sb.append(((Integer) pairM19271p.second).intValue() != -1 ? ", " + strArr[((Integer) pairM19271p.second).intValue()] : "");
                                sb.append(")");
                                Log.w(str6, sb.toString());
                            }
                        } else {
                            i9 = dVar.f17151d;
                        }
                    }
                    switch (i9) {
                        case 1:
                            str3 = str6;
                            i10 = i12;
                            this.f17123d[i14].put(str5, c.m19317a(string));
                            str6 = str3;
                            break;
                        case 2:
                        case 7:
                            str3 = str6;
                            i10 = i12;
                            this.f17123d[i14].put(str5, c.m19321e(string));
                            str6 = str3;
                            break;
                        case 3:
                            str3 = str6;
                            i10 = i12;
                            String[] strArrSplit = string.split(",", -1);
                            int[] iArr = new int[strArrSplit.length];
                            for (int i18 = 0; i18 < strArrSplit.length; i18++) {
                                iArr[i18] = Integer.parseInt(strArrSplit[i18]);
                            }
                            this.f17123d[i14].put(str5, c.m19327k(iArr, this.f17125f));
                            str6 = str3;
                            break;
                        case 4:
                            str3 = str6;
                            i10 = i12;
                            String[] strArrSplit2 = string.split(",", -1);
                            long[] jArr = new long[strArrSplit2.length];
                            for (int i19 = 0; i19 < strArrSplit2.length; i19++) {
                                jArr[i19] = Long.parseLong(strArrSplit2[i19]);
                            }
                            this.f17123d[i14].put(str5, c.m19323g(jArr, this.f17125f));
                            str6 = str3;
                            break;
                        case 5:
                            str3 = str6;
                            String[] strArrSplit3 = string.split(",", -1);
                            e[] eVarArr = new e[strArrSplit3.length];
                            int i20 = 0;
                            while (i20 < strArrSplit3.length) {
                                String[] strArrSplit4 = strArrSplit3[i20].split("/", i15);
                                eVarArr[i20] = new e((long) Double.parseDouble(strArrSplit4[0]), (long) Double.parseDouble(strArrSplit4[1]));
                                i20++;
                                i15 = -1;
                            }
                            i10 = 1;
                            this.f17123d[i14].put(str5, c.m19325i(eVarArr, this.f17125f));
                            str6 = str3;
                            break;
                        case 6:
                        case 8:
                        case 11:
                        default:
                            i10 = i12;
                            str6 = str6;
                            Log.w(str6, "Data format isn't one of expected formats: " + i9);
                            break;
                        case 9:
                            str4 = str6;
                            String[] strArrSplit5 = string.split(",", -1);
                            int[] iArr2 = new int[strArrSplit5.length];
                            for (int i21 = 0; i21 < strArrSplit5.length; i21++) {
                                iArr2[i21] = Integer.parseInt(strArrSplit5[i21]);
                            }
                            this.f17123d[i14].put(str5, c.m19319c(iArr2, this.f17125f));
                            str6 = str4;
                            i10 = 1;
                            break;
                        case 10:
                            String[] strArrSplit6 = string.split(",", -1);
                            e[] eVarArr2 = new e[strArrSplit6.length];
                            int i22 = i13;
                            while (i22 < strArrSplit6.length) {
                                String[] strArrSplit7 = strArrSplit6[i22].split("/", -1);
                                eVarArr2[i22] = new e((long) Double.parseDouble(strArrSplit7[i13]), (long) Double.parseDouble(strArrSplit7[i12]));
                                i22++;
                                str6 = str6;
                                i12 = 1;
                                i13 = 0;
                            }
                            str4 = str6;
                            this.f17123d[i14].put(str5, c.m19320d(eVarArr2, this.f17125f));
                            str6 = str4;
                            i10 = 1;
                            break;
                        case 12:
                            String[] strArrSplit8 = string.split(",", -1);
                            double[] dArr = new double[strArrSplit8.length];
                            for (int i23 = i13; i23 < strArrSplit8.length; i23++) {
                                dArr[i23] = Double.parseDouble(strArrSplit8[i23]);
                            }
                            this.f17123d[i14].put(str5, c.m19318b(dArr, this.f17125f));
                            break;
                    }
                } else {
                    this.f17123d[i14].remove(str5);
                }
                i10 = i12;
            } else {
                i10 = i12;
            }
            i14++;
            i12 = i10;
            i11 = 2;
            i13 = 0;
        }
    }

    /* renamed from: I */
    public final void m19281I(a aVar) throws Throwable {
        HashMap<String, c> map = this.f17123d[4];
        c cVar = map.get("Compression");
        if (cVar == null) {
            this.f17130k = 6;
            m19298q(aVar, map);
            return;
        }
        int iM19329m = cVar.m19329m(this.f17125f);
        this.f17130k = iM19329m;
        if (iM19329m != 1) {
            if (iM19329m == 6) {
                m19298q(aVar, map);
                return;
            } else if (iM19329m != 7) {
                return;
            }
        }
        if (m19303w(map)) {
            m19299r(aVar, map);
        }
    }

    /* renamed from: J */
    public final void m19282J(int i9, int i10) throws Throwable {
        if (this.f17123d[i9].isEmpty() || this.f17123d[i10].isEmpty()) {
            return;
        }
        c cVar = this.f17123d[i9].get("ImageLength");
        c cVar2 = this.f17123d[i9].get("ImageWidth");
        c cVar3 = this.f17123d[i10].get("ImageLength");
        c cVar4 = this.f17123d[i10].get("ImageWidth");
        if (cVar == null || cVar2 == null || cVar3 == null || cVar4 == null) {
            return;
        }
        int iM19329m = cVar.m19329m(this.f17125f);
        int iM19329m2 = cVar2.m19329m(this.f17125f);
        int iM19329m3 = cVar3.m19329m(this.f17125f);
        int iM19329m4 = cVar4.m19329m(this.f17125f);
        if (iM19329m >= iM19329m3 || iM19329m2 >= iM19329m4) {
            return;
        }
        HashMap<String, c>[] mapArr = this.f17123d;
        HashMap<String, c> map = mapArr[i9];
        mapArr[i9] = mapArr[i10];
        mapArr[i10] = map;
    }

    /* renamed from: K */
    public final void m19283K(a aVar, int i9) throws Throwable {
        c cVarM19326j;
        c cVarM19326j2;
        c cVar = this.f17123d[i9].get("DefaultCropSize");
        c cVar2 = this.f17123d[i9].get("SensorTopBorder");
        c cVar3 = this.f17123d[i9].get("SensorLeftBorder");
        c cVar4 = this.f17123d[i9].get("SensorBottomBorder");
        c cVar5 = this.f17123d[i9].get("SensorRightBorder");
        if (cVar == null) {
            if (cVar2 == null || cVar3 == null || cVar4 == null || cVar5 == null) {
                m19277E(aVar, i9);
                return;
            }
            int iM19329m = cVar2.m19329m(this.f17125f);
            int iM19329m2 = cVar4.m19329m(this.f17125f);
            int iM19329m3 = cVar5.m19329m(this.f17125f);
            int iM19329m4 = cVar3.m19329m(this.f17125f);
            if (iM19329m2 <= iM19329m || iM19329m3 <= iM19329m4) {
                return;
            }
            c cVarM19326j3 = c.m19326j(iM19329m2 - iM19329m, this.f17125f);
            c cVarM19326j4 = c.m19326j(iM19329m3 - iM19329m4, this.f17125f);
            this.f17123d[i9].put("ImageLength", cVarM19326j3);
            this.f17123d[i9].put("ImageWidth", cVarM19326j4);
            return;
        }
        if (cVar.f17145a == 5) {
            e[] eVarArr = (e[]) cVar.m19331o(this.f17125f);
            if (eVarArr == null || eVarArr.length != 2) {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(eVarArr));
                return;
            }
            cVarM19326j = c.m19324h(eVarArr[0], this.f17125f);
            cVarM19326j2 = c.m19324h(eVarArr[1], this.f17125f);
        } else {
            int[] iArr = (int[]) cVar.m19331o(this.f17125f);
            if (iArr == null || iArr.length != 2) {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                return;
            }
            cVarM19326j = c.m19326j(iArr[0], this.f17125f);
            cVarM19326j2 = c.m19326j(iArr[1], this.f17125f);
        }
        this.f17123d[i9].put("ImageWidth", cVarM19326j);
        this.f17123d[i9].put("ImageLength", cVarM19326j2);
    }

    /* renamed from: L */
    public final void m19284L(InputStream inputStream) throws Throwable {
        m19282J(0, 5);
        m19282J(0, 4);
        m19282J(5, 4);
        c cVar = this.f17123d[1].get("PixelXDimension");
        c cVar2 = this.f17123d[1].get("PixelYDimension");
        if (cVar != null && cVar2 != null) {
            this.f17123d[0].put("ImageWidth", cVar);
            this.f17123d[0].put("ImageLength", cVar2);
        }
        if (this.f17123d[4].isEmpty() && m19304x(this.f17123d[5])) {
            HashMap<String, c>[] mapArr = this.f17123d;
            mapArr[4] = mapArr[5];
            mapArr[5] = new HashMap<>();
        }
        if (m19304x(this.f17123d[4])) {
            return;
        }
        Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
    }

    /* renamed from: M */
    public final int m19285M(b bVar, int i9) throws IOException {
        d[][] dVarArr = f17099N;
        int[] iArr = new int[dVarArr.length];
        int[] iArr2 = new int[dVarArr.length];
        for (d dVar : f17100O) {
            m19276D(dVar.f17149b);
        }
        m19276D(f17101P.f17149b);
        m19276D(f17102Q.f17149b);
        for (int i10 = 0; i10 < f17099N.length; i10++) {
            for (Object obj : this.f17123d[i10].entrySet().toArray()) {
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getValue() == null) {
                    this.f17123d[i10].remove(entry.getKey());
                }
            }
        }
        if (!this.f17123d[1].isEmpty()) {
            this.f17123d[0].put(f17100O[1].f17149b, c.m19322f(0L, this.f17125f));
        }
        if (!this.f17123d[2].isEmpty()) {
            this.f17123d[0].put(f17100O[2].f17149b, c.m19322f(0L, this.f17125f));
        }
        if (!this.f17123d[3].isEmpty()) {
            this.f17123d[1].put(f17100O[3].f17149b, c.m19322f(0L, this.f17125f));
        }
        if (this.f17126g) {
            this.f17123d[4].put(f17101P.f17149b, c.m19322f(0L, this.f17125f));
            this.f17123d[4].put(f17102Q.f17149b, c.m19322f(this.f17128i, this.f17125f));
        }
        for (int i11 = 0; i11 < f17099N.length; i11++) {
            Iterator<Map.Entry<String, c>> it = this.f17123d[i11].entrySet().iterator();
            int i12 = 0;
            while (it.hasNext()) {
                int iM19332p = it.next().getValue().m19332p();
                if (iM19332p > 4) {
                    i12 += iM19332p;
                }
            }
            iArr2[i11] = iArr2[i11] + i12;
        }
        int size = 8;
        for (int i13 = 0; i13 < f17099N.length; i13++) {
            if (!this.f17123d[i13].isEmpty()) {
                iArr[i13] = size;
                size += (this.f17123d[i13].size() * 12) + 2 + 4 + iArr2[i13];
            }
        }
        if (this.f17126g) {
            this.f17123d[4].put(f17101P.f17149b, c.m19322f(size, this.f17125f));
            this.f17127h = i9 + size;
            size += this.f17128i;
        }
        int i14 = size + 8;
        if (!this.f17123d[1].isEmpty()) {
            this.f17123d[0].put(f17100O[1].f17149b, c.m19322f(iArr[1], this.f17125f));
        }
        if (!this.f17123d[2].isEmpty()) {
            this.f17123d[0].put(f17100O[2].f17149b, c.m19322f(iArr[2], this.f17125f));
        }
        if (!this.f17123d[3].isEmpty()) {
            this.f17123d[1].put(f17100O[3].f17149b, c.m19322f(iArr[3], this.f17125f));
        }
        bVar.m19316y(i14);
        bVar.write(f17108W);
        bVar.m19314w(this.f17125f == ByteOrder.BIG_ENDIAN ? (short) 19789 : (short) 18761);
        bVar.m19311f(this.f17125f);
        bVar.m19316y(42);
        bVar.m19315x(8L);
        for (int i15 = 0; i15 < f17099N.length; i15++) {
            if (!this.f17123d[i15].isEmpty()) {
                bVar.m19316y(this.f17123d[i15].size());
                int size2 = iArr[i15] + 2 + (this.f17123d[i15].size() * 12) + 4;
                for (Map.Entry<String, c> entry2 : this.f17123d[i15].entrySet()) {
                    int i16 = f17104S[i15].get(entry2.getKey()).f17148a;
                    c value = entry2.getValue();
                    int iM19332p2 = value.m19332p();
                    bVar.m19316y(i16);
                    bVar.m19316y(value.f17145a);
                    bVar.m19313v(value.f17146b);
                    if (iM19332p2 > 4) {
                        bVar.m19315x(size2);
                        size2 += iM19332p2;
                    } else {
                        bVar.write(value.f17147c);
                        if (iM19332p2 < 4) {
                            while (iM19332p2 < 4) {
                                bVar.m19312u(0);
                                iM19332p2++;
                            }
                        }
                    }
                }
                if (i15 != 0 || this.f17123d[4].isEmpty()) {
                    bVar.m19315x(0L);
                } else {
                    bVar.m19315x(iArr[4]);
                }
                Iterator<Map.Entry<String, c>> it2 = this.f17123d[i15].entrySet().iterator();
                while (it2.hasNext()) {
                    byte[] bArr = it2.next().getValue().f17147c;
                    if (bArr.length > 4) {
                        bVar.write(bArr, 0, bArr.length);
                    }
                }
            }
        }
        if (this.f17126g) {
            bVar.write(m19297o());
        }
        bVar.m19311f(ByteOrder.BIG_ENDIAN);
        return i14;
    }

    /* renamed from: a */
    public final void m19286a() {
        String strM19287e = m19287e("DateTimeOriginal");
        if (strM19287e != null && m19287e("DateTime") == null) {
            this.f17123d[0].put("DateTime", c.m19321e(strM19287e));
        }
        if (m19287e("ImageWidth") == null) {
            this.f17123d[0].put("ImageWidth", c.m19322f(0L, this.f17125f));
        }
        if (m19287e("ImageLength") == null) {
            this.f17123d[0].put("ImageLength", c.m19322f(0L, this.f17125f));
        }
        if (m19287e("Orientation") == null) {
            this.f17123d[0].put("Orientation", c.m19322f(0L, this.f17125f));
        }
        if (m19287e("LightSource") == null) {
            this.f17123d[1].put("LightSource", c.m19322f(0L, this.f17125f));
        }
    }

    /* renamed from: e */
    public String m19287e(String str) {
        c cVarM19289g = m19289g(str);
        if (cVarM19289g != null) {
            if (!f17105T.contains(str)) {
                return cVarM19289g.m19330n(this.f17125f);
            }
            if (str.equals("GPSTimeStamp")) {
                int i9 = cVarM19289g.f17145a;
                if (i9 != 5 && i9 != 10) {
                    Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + cVarM19289g.f17145a);
                    return null;
                }
                e[] eVarArr = (e[]) cVarM19289g.m19331o(this.f17125f);
                if (eVarArr != null && eVarArr.length == 3) {
                    e eVar = eVarArr[0];
                    e eVar2 = eVarArr[1];
                    e eVar3 = eVarArr[2];
                    return String.format("%02d:%02d:%02d", Integer.valueOf((int) (eVar.f17152a / eVar.f17153b)), Integer.valueOf((int) (eVar2.f17152a / eVar2.f17153b)), Integer.valueOf((int) (eVar3.f17152a / eVar3.f17153b)));
                }
                Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(eVarArr));
                return null;
            }
            try {
                return Double.toString(cVarM19289g.m19328l(this.f17125f));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    /* renamed from: f */
    public int m19288f(String str, int i9) {
        c cVarM19289g = m19289g(str);
        if (cVarM19289g == null) {
            return i9;
        }
        try {
            return cVarM19289g.m19329m(this.f17125f);
        } catch (NumberFormatException unused) {
            return i9;
        }
    }

    /* renamed from: g */
    public final c m19289g(String str) {
        if ("ISOSpeedRatings".equals(str)) {
            str = "PhotographicSensitivity";
        }
        for (int i9 = 0; i9 < f17099N.length; i9++) {
            c cVar = this.f17123d[i9].get(str);
            if (cVar != null) {
                return cVar;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0115, code lost:
    
        r10.m19310w(r9.f17125f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x011a, code lost:
    
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0057 A[FALL_THROUGH] */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m19290h(a aVar, int i9, int i10) throws IOException {
        aVar.m19310w(ByteOrder.BIG_ENDIAN);
        aVar.m19309v(i9);
        byte b9 = aVar.readByte();
        if (b9 != -1) {
            throw new IOException("Invalid marker: " + Integer.toHexString(b9 & UnsignedBytes.MAX_VALUE));
        }
        int i11 = i9 + 1;
        if (aVar.readByte() != -40) {
            throw new IOException("Invalid marker: " + Integer.toHexString(b9 & UnsignedBytes.MAX_VALUE));
        }
        int i12 = i11 + 1;
        while (true) {
            byte b10 = aVar.readByte();
            if (b10 != -1) {
                throw new IOException("Invalid marker:" + Integer.toHexString(b10 & UnsignedBytes.MAX_VALUE));
            }
            byte b11 = aVar.readByte();
            int i13 = i12 + 1 + 1;
            if (b11 != -39 && b11 != -38) {
                int unsignedShort = aVar.readUnsignedShort() - 2;
                int i14 = i13 + 2;
                if (unsignedShort < 0) {
                    throw new IOException("Invalid length");
                }
                if (b11 != -31) {
                    if (b11 != -2) {
                        switch (b11) {
                            default:
                                switch (b11) {
                                    default:
                                        switch (b11) {
                                            default:
                                                switch (b11) {
                                                }
                                            case -55:
                                            case -54:
                                            case -53:
                                                if (aVar.skipBytes(1) != 1) {
                                                    throw new IOException("Invalid SOFx");
                                                }
                                                this.f17123d[i10].put("ImageLength", c.m19322f(aVar.readUnsignedShort(), this.f17125f));
                                                this.f17123d[i10].put("ImageWidth", c.m19322f(aVar.readUnsignedShort(), this.f17125f));
                                                unsignedShort -= 5;
                                                break;
                                        }
                                    case -59:
                                    case -58:
                                    case -57:
                                        break;
                                }
                            case -64:
                            case -63:
                            case -62:
                            case -61:
                                break;
                        }
                    } else {
                        byte[] bArr = new byte[unsignedShort];
                        if (aVar.read(bArr) != unsignedShort) {
                            throw new IOException("Invalid exif");
                        }
                        if (m19287e("UserComment") == null) {
                            this.f17123d[1].put("UserComment", c.m19321e(new String(bArr, f17107V)));
                        }
                        unsignedShort = 0;
                    }
                } else if (unsignedShort >= 6) {
                    byte[] bArr2 = new byte[6];
                    if (aVar.read(bArr2) != 6) {
                        throw new IOException("Invalid exif");
                    }
                    i14 += 6;
                    unsignedShort -= 6;
                    if (Arrays.equals(bArr2, f17108W)) {
                        if (unsignedShort <= 0) {
                            throw new IOException("Invalid exif");
                        }
                        this.f17131l = i14;
                        byte[] bArr3 = new byte[unsignedShort];
                        if (aVar.read(bArr3) != unsignedShort) {
                            throw new IOException("Invalid exif");
                        }
                        i14 += unsignedShort;
                        m19274B(bArr3, i10);
                        unsignedShort = 0;
                    }
                }
                if (unsignedShort < 0) {
                    throw new IOException("Invalid length");
                }
                if (aVar.skipBytes(unsignedShort) != unsignedShort) {
                    throw new IOException("Invalid JPEG segment");
                }
                i12 = i14 + unsignedShort;
            }
        }
    }

    /* renamed from: i */
    public final int m19291i(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(5000);
        byte[] bArr = new byte[5000];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (m19272s(bArr)) {
            return 4;
        }
        if (m19301u(bArr)) {
            return 9;
        }
        if (m19300t(bArr)) {
            return 7;
        }
        return m19302v(bArr) ? 10 : 0;
    }

    /* renamed from: j */
    public final void m19292j(a aVar) throws Throwable {
        int i9;
        int i10;
        m19294l(aVar);
        c cVar = this.f17123d[1].get("MakerNote");
        if (cVar != null) {
            a aVar2 = new a(cVar.f17147c);
            aVar2.m19310w(this.f17125f);
            byte[] bArr = f17117x;
            byte[] bArr2 = new byte[bArr.length];
            aVar2.readFully(bArr2);
            aVar2.m19309v(0L);
            byte[] bArr3 = f17118y;
            byte[] bArr4 = new byte[bArr3.length];
            aVar2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                aVar2.m19309v(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                aVar2.m19309v(12L);
            }
            m19275C(aVar2, 6);
            c cVar2 = this.f17123d[7].get("PreviewImageStart");
            c cVar3 = this.f17123d[7].get("PreviewImageLength");
            if (cVar2 != null && cVar3 != null) {
                this.f17123d[5].put("JPEGInterchangeFormat", cVar2);
                this.f17123d[5].put("JPEGInterchangeFormatLength", cVar3);
            }
            c cVar4 = this.f17123d[8].get("AspectFrame");
            if (cVar4 != null) {
                int[] iArr = (int[]) cVar4.m19331o(this.f17125f);
                if (iArr == null || iArr.length != 4) {
                    Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                    return;
                }
                int i11 = iArr[2];
                int i12 = iArr[0];
                if (i11 <= i12 || (i9 = iArr[3]) <= (i10 = iArr[1])) {
                    return;
                }
                int i13 = (i11 - i12) + 1;
                int i14 = (i9 - i10) + 1;
                if (i13 < i14) {
                    int i15 = i13 + i14;
                    i14 = i15 - i14;
                    i13 = i15 - i14;
                }
                c cVarM19326j = c.m19326j(i13, this.f17125f);
                c cVarM19326j2 = c.m19326j(i14, this.f17125f);
                this.f17123d[0].put("ImageWidth", cVarM19326j);
                this.f17123d[0].put("ImageLength", cVarM19326j2);
            }
        }
    }

    /* renamed from: k */
    public final void m19293k(a aVar) throws IOException {
        aVar.skipBytes(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        aVar.read(bArr);
        aVar.skipBytes(4);
        aVar.read(bArr2);
        int i9 = ByteBuffer.wrap(bArr).getInt();
        int i10 = ByteBuffer.wrap(bArr2).getInt();
        m19290h(aVar, i9, 5);
        aVar.m19309v(i10);
        aVar.m19310w(ByteOrder.BIG_ENDIAN);
        int i11 = aVar.readInt();
        for (int i12 = 0; i12 < i11; i12++) {
            int unsignedShort = aVar.readUnsignedShort();
            int unsignedShort2 = aVar.readUnsignedShort();
            if (unsignedShort == f17094I.f17148a) {
                short s8 = aVar.readShort();
                short s9 = aVar.readShort();
                c cVarM19326j = c.m19326j(s8, this.f17125f);
                c cVarM19326j2 = c.m19326j(s9, this.f17125f);
                this.f17123d[0].put("ImageLength", cVarM19326j);
                this.f17123d[0].put("ImageWidth", cVarM19326j2);
                return;
            }
            aVar.skipBytes(unsignedShort2);
        }
    }

    /* renamed from: l */
    public final void m19294l(a aVar) throws Throwable {
        c cVar;
        m19306z(aVar, aVar.available());
        m19275C(aVar, 0);
        m19283K(aVar, 0);
        m19283K(aVar, 5);
        m19283K(aVar, 4);
        m19284L(aVar);
        if (this.f17122c != 8 || (cVar = this.f17123d[1].get("MakerNote")) == null) {
            return;
        }
        a aVar2 = new a(cVar.f17147c);
        aVar2.m19310w(this.f17125f);
        aVar2.m19309v(6L);
        m19275C(aVar2, 9);
        c cVar2 = this.f17123d[9].get("ColorSpace");
        if (cVar2 != null) {
            this.f17123d[1].put("ColorSpace", cVar2);
        }
    }

    /* renamed from: m */
    public final void m19295m(a aVar) throws Throwable {
        m19294l(aVar);
        if (this.f17123d[0].get("JpgFromRaw") != null) {
            m19290h(aVar, this.f17135p, 5);
        }
        c cVar = this.f17123d[0].get("ISO");
        c cVar2 = this.f17123d[1].get("PhotographicSensitivity");
        if (cVar == null || cVar2 != null) {
            return;
        }
        this.f17123d[1].put("PhotographicSensitivity", cVar);
    }

    /* renamed from: n */
    public byte[] m19296n() {
        int i9 = this.f17130k;
        if (i9 == 6 || i9 == 7) {
            return m19297o();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: o */
    public byte[] m19297o() throws Throwable {
        InputStream fileInputStream;
        Closeable closeable = null;
        if (!this.f17126g) {
            return null;
        }
        byte[] bArr = this.f17129j;
        try {
            if (bArr != 0) {
                return bArr;
            }
            try {
                fileInputStream = this.f17121b;
                if (fileInputStream != null) {
                    try {
                        if (!fileInputStream.markSupported()) {
                            Log.d("ExifInterface", "Cannot read thumbnail from inputstream without mark/reset support");
                            m19268b(fileInputStream);
                            return null;
                        }
                        fileInputStream.reset();
                    } catch (IOException e9) {
                        e = e9;
                        Log.d("ExifInterface", "Encountered exception while getting thumbnail", e);
                        m19268b(fileInputStream);
                        return null;
                    }
                } else {
                    fileInputStream = this.f17120a != null ? new FileInputStream(this.f17120a) : null;
                }
                if (fileInputStream == null) {
                    throw new FileNotFoundException();
                }
                if (fileInputStream.skip(this.f17127h) != this.f17127h) {
                    throw new IOException("Corrupted image");
                }
                byte[] bArr2 = new byte[this.f17128i];
                if (fileInputStream.read(bArr2) != this.f17128i) {
                    throw new IOException("Corrupted image");
                }
                this.f17129j = bArr2;
                m19268b(fileInputStream);
                return bArr2;
            } catch (IOException e10) {
                e = e10;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                m19268b(closeable);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            closeable = bArr;
        }
    }

    /* renamed from: q */
    public final void m19298q(a aVar, HashMap map) throws Throwable {
        int i9;
        c cVar = (c) map.get("JPEGInterchangeFormat");
        c cVar2 = (c) map.get("JPEGInterchangeFormatLength");
        if (cVar == null || cVar2 == null) {
            return;
        }
        int iM19329m = cVar.m19329m(this.f17125f);
        int iMin = Math.min(cVar2.m19329m(this.f17125f), aVar.available() - iM19329m);
        int i10 = this.f17122c;
        if (i10 != 4 && i10 != 9 && i10 != 10) {
            if (i10 == 7) {
                i9 = this.f17132m;
            }
            if (iM19329m > 0 || iMin <= 0) {
            }
            this.f17126g = true;
            this.f17127h = iM19329m;
            this.f17128i = iMin;
            if (this.f17120a == null && this.f17121b == null) {
                byte[] bArr = new byte[iMin];
                aVar.m19309v(iM19329m);
                aVar.readFully(bArr);
                this.f17129j = bArr;
                return;
            }
            return;
        }
        i9 = this.f17131l;
        iM19329m += i9;
        if (iM19329m > 0) {
        }
    }

    /* renamed from: r */
    public final void m19299r(a aVar, HashMap map) throws IOException {
        c cVar = (c) map.get("StripOffsets");
        c cVar2 = (c) map.get("StripByteCounts");
        if (cVar == null || cVar2 == null) {
            return;
        }
        long[] jArrM19269c = m19269c(cVar.m19331o(this.f17125f));
        long[] jArrM19269c2 = m19269c(cVar2.m19331o(this.f17125f));
        if (jArrM19269c == null) {
            Log.w("ExifInterface", "stripOffsets should not be null.");
            return;
        }
        if (jArrM19269c2 == null) {
            Log.w("ExifInterface", "stripByteCounts should not be null.");
            return;
        }
        long j9 = 0;
        for (long j10 : jArrM19269c2) {
            j9 += j10;
        }
        int i9 = (int) j9;
        byte[] bArr = new byte[i9];
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < jArrM19269c.length; i12++) {
            int i13 = (int) jArrM19269c[i12];
            int i14 = (int) jArrM19269c2[i12];
            int i15 = i13 - i10;
            if (i15 < 0) {
                Log.d("ExifInterface", "Invalid strip offset value");
            }
            aVar.m19309v(i15);
            int i16 = i10 + i15;
            byte[] bArr2 = new byte[i14];
            aVar.read(bArr2);
            i10 = i16 + i14;
            System.arraycopy(bArr2, 0, bArr, i11, i14);
            i11 += i14;
        }
        this.f17126g = true;
        this.f17129j = bArr;
        this.f17128i = i9;
    }

    /* renamed from: t */
    public final boolean m19300t(byte[] bArr) throws IOException {
        a aVar = new a(bArr);
        ByteOrder byteOrderM19273A = m19273A(aVar);
        this.f17125f = byteOrderM19273A;
        aVar.m19310w(byteOrderM19273A);
        short s8 = aVar.readShort();
        aVar.close();
        return s8 == 20306 || s8 == 21330;
    }

    /* renamed from: u */
    public final boolean m19301u(byte[] bArr) {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i9 = 0; i9 < bytes.length; i9++) {
            if (bArr[i9] != bytes[i9]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: v */
    public final boolean m19302v(byte[] bArr) throws IOException {
        a aVar = new a(bArr);
        ByteOrder byteOrderM19273A = m19273A(aVar);
        this.f17125f = byteOrderM19273A;
        aVar.m19310w(byteOrderM19273A);
        short s8 = aVar.readShort();
        aVar.close();
        return s8 == 85;
    }

    /* renamed from: w */
    public final boolean m19303w(HashMap map) throws Throwable {
        c cVar;
        c cVar2 = (c) map.get("BitsPerSample");
        if (cVar2 == null) {
            return false;
        }
        int[] iArr = (int[]) cVar2.m19331o(this.f17125f);
        int[] iArr2 = f17113t;
        if (Arrays.equals(iArr2, iArr)) {
            return true;
        }
        if (this.f17122c != 3 || (cVar = (c) map.get("PhotometricInterpretation")) == null) {
            return false;
        }
        int iM19329m = cVar.m19329m(this.f17125f);
        return (iM19329m == 1 && Arrays.equals(iArr, f17115v)) || (iM19329m == 6 && Arrays.equals(iArr, iArr2));
    }

    /* renamed from: x */
    public final boolean m19304x(HashMap map) {
        c cVar = (c) map.get("ImageLength");
        c cVar2 = (c) map.get("ImageWidth");
        if (cVar == null || cVar2 == null) {
            return false;
        }
        return cVar.m19329m(this.f17125f) <= 512 && cVar2.m19329m(this.f17125f) <= 512;
    }

    /* renamed from: y */
    public final void m19305y(InputStream inputStream) {
        for (int i9 = 0; i9 < f17099N.length; i9++) {
            try {
                try {
                    this.f17123d[i9] = new HashMap<>();
                } catch (IOException unused) {
                    this.f17136q = false;
                }
            } finally {
                m19286a();
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
        this.f17122c = m19291i(bufferedInputStream);
        a aVar = new a(bufferedInputStream);
        switch (this.f17122c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
                m19294l(aVar);
                break;
            case 4:
                m19290h(aVar, 0, 0);
                break;
            case 7:
                m19292j(aVar);
                break;
            case 9:
                m19293k(aVar);
                break;
            case 10:
                m19295m(aVar);
                break;
        }
        m19281I(aVar);
        this.f17136q = true;
    }

    /* renamed from: z */
    public final void m19306z(a aVar, int i9) throws IOException {
        ByteOrder byteOrderM19273A = m19273A(aVar);
        this.f17125f = byteOrderM19273A;
        aVar.m19310w(byteOrderM19273A);
        int unsignedShort = aVar.readUnsignedShort();
        int i10 = this.f17122c;
        if (i10 != 7 && i10 != 10 && unsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(unsignedShort));
        }
        int i11 = aVar.readInt();
        if (i11 < 8 || i11 >= i9) {
            throw new IOException("Invalid first Ifd offset: " + i11);
        }
        int i12 = i11 - 8;
        if (i12 <= 0 || aVar.skipBytes(i12) == i12) {
            return;
        }
        throw new IOException("Couldn't jump to first Ifd: " + i12);
    }

    /* renamed from: h0.a$a */
    public static class a extends InputStream implements DataInput {

        /* renamed from: f */
        public static final ByteOrder f17137f = ByteOrder.LITTLE_ENDIAN;

        /* renamed from: g */
        public static final ByteOrder f17138g = ByteOrder.BIG_ENDIAN;

        /* renamed from: b */
        public DataInputStream f17139b;

        /* renamed from: c */
        public ByteOrder f17140c;

        /* renamed from: d */
        public final int f17141d;

        /* renamed from: e */
        public int f17142e;

        public a(InputStream inputStream) throws IOException {
            this.f17140c = ByteOrder.BIG_ENDIAN;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.f17139b = dataInputStream;
            int iAvailable = dataInputStream.available();
            this.f17141d = iAvailable;
            this.f17142e = 0;
            this.f17139b.mark(iAvailable);
        }

        @Override // java.io.InputStream
        public int available() {
            return this.f17139b.available();
        }

        /* renamed from: f */
        public int m19307f() {
            return this.f17142e;
        }

        @Override // java.io.InputStream
        public int read() {
            this.f17142e++;
            return this.f17139b.read();
        }

        @Override // java.io.DataInput
        public boolean readBoolean() {
            this.f17142e++;
            return this.f17139b.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            int i9 = this.f17142e + 1;
            this.f17142e = i9;
            if (i9 > this.f17141d) {
                throw new EOFException();
            }
            int i10 = this.f17139b.read();
            if (i10 >= 0) {
                return (byte) i10;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public char readChar() {
            this.f17142e += 2;
            return this.f17139b.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i9, int i10) throws IOException {
            int i11 = this.f17142e + i10;
            this.f17142e = i11;
            if (i11 > this.f17141d) {
                throw new EOFException();
            }
            if (this.f17139b.read(bArr, i9, i10) != i10) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            int i9 = this.f17142e + 4;
            this.f17142e = i9;
            if (i9 > this.f17141d) {
                throw new EOFException();
            }
            int i10 = this.f17139b.read();
            int i11 = this.f17139b.read();
            int i12 = this.f17139b.read();
            int i13 = this.f17139b.read();
            if ((i10 | i11 | i12 | i13) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f17140c;
            if (byteOrder == f17137f) {
                return (i13 << 24) + (i12 << 16) + (i11 << 8) + i10;
            }
            if (byteOrder == f17138g) {
                return (i10 << 24) + (i11 << 16) + (i12 << 8) + i13;
            }
            throw new IOException("Invalid byte order: " + this.f17140c);
        }

        @Override // java.io.DataInput
        public String readLine() {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            int i9 = this.f17142e + 8;
            this.f17142e = i9;
            if (i9 > this.f17141d) {
                throw new EOFException();
            }
            int i10 = this.f17139b.read();
            int i11 = this.f17139b.read();
            int i12 = this.f17139b.read();
            int i13 = this.f17139b.read();
            int i14 = this.f17139b.read();
            int i15 = this.f17139b.read();
            int i16 = this.f17139b.read();
            int i17 = this.f17139b.read();
            if ((i10 | i11 | i12 | i13 | i14 | i15 | i16 | i17) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f17140c;
            if (byteOrder == f17137f) {
                return (i17 << 56) + (i16 << 48) + (i15 << 40) + (i14 << 32) + (i13 << 24) + (i12 << 16) + (i11 << 8) + i10;
            }
            if (byteOrder == f17138g) {
                return (i10 << 56) + (i11 << 48) + (i12 << 40) + (i13 << 32) + (i14 << 24) + (i15 << 16) + (i16 << 8) + i17;
            }
            throw new IOException("Invalid byte order: " + this.f17140c);
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            int i9 = this.f17142e + 2;
            this.f17142e = i9;
            if (i9 > this.f17141d) {
                throw new EOFException();
            }
            int i10 = this.f17139b.read();
            int i11 = this.f17139b.read();
            if ((i10 | i11) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f17140c;
            if (byteOrder == f17137f) {
                return (short) ((i11 << 8) + i10);
            }
            if (byteOrder == f17138g) {
                return (short) ((i10 << 8) + i11);
            }
            throw new IOException("Invalid byte order: " + this.f17140c);
        }

        @Override // java.io.DataInput
        public String readUTF() {
            this.f17142e += 2;
            return this.f17139b.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() {
            this.f17142e++;
            return this.f17139b.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            int i9 = this.f17142e + 2;
            this.f17142e = i9;
            if (i9 > this.f17141d) {
                throw new EOFException();
            }
            int i10 = this.f17139b.read();
            int i11 = this.f17139b.read();
            if ((i10 | i11) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f17140c;
            if (byteOrder == f17137f) {
                return (i11 << 8) + i10;
            }
            if (byteOrder == f17138g) {
                return (i10 << 8) + i11;
            }
            throw new IOException("Invalid byte order: " + this.f17140c);
        }

        @Override // java.io.DataInput
        public int skipBytes(int i9) {
            int iMin = Math.min(i9, this.f17141d - this.f17142e);
            int iSkipBytes = 0;
            while (iSkipBytes < iMin) {
                iSkipBytes += this.f17139b.skipBytes(iMin - iSkipBytes);
            }
            this.f17142e += iSkipBytes;
            return iSkipBytes;
        }

        /* renamed from: u */
        public long m19308u() {
            return readInt() & 4294967295L;
        }

        /* renamed from: v */
        public void m19309v(long j9) throws IOException {
            int i9 = this.f17142e;
            if (i9 > j9) {
                this.f17142e = 0;
                this.f17139b.reset();
                this.f17139b.mark(this.f17141d);
            } else {
                j9 -= i9;
            }
            int i10 = (int) j9;
            if (skipBytes(i10) != i10) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        /* renamed from: w */
        public void m19310w(ByteOrder byteOrder) {
            this.f17140c = byteOrder;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i9, int i10) throws IOException {
            int i11 = this.f17139b.read(bArr, i9, i10);
            this.f17142e += i11;
            return i11;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            int length = this.f17142e + bArr.length;
            this.f17142e = length;
            if (length <= this.f17141d) {
                if (this.f17139b.read(bArr, 0, bArr.length) != bArr.length) {
                    throw new IOException("Couldn't read up to the length of buffer");
                }
                return;
            }
            throw new EOFException();
        }

        public a(byte[] bArr) {
            this(new ByteArrayInputStream(bArr));
        }
    }

    /* renamed from: h0.a$d */
    public static class d {

        /* renamed from: a */
        public final int f17148a;

        /* renamed from: b */
        public final String f17149b;

        /* renamed from: c */
        public final int f17150c;

        /* renamed from: d */
        public final int f17151d;

        public d(String str, int i9, int i10) {
            this.f17149b = str;
            this.f17148a = i9;
            this.f17150c = i10;
            this.f17151d = -1;
        }

        /* renamed from: a */
        public boolean m19333a(int i9) {
            int i10;
            int i11 = this.f17150c;
            if (i11 == 7 || i9 == 7 || i11 == i9 || (i10 = this.f17151d) == i9) {
                return true;
            }
            if ((i11 == 4 || i10 == 4) && i9 == 3) {
                return true;
            }
            if ((i11 == 9 || i10 == 9) && i9 == 8) {
                return true;
            }
            return (i11 == 12 || i10 == 12) && i9 == 11;
        }

        public d(String str, int i9, int i10, int i11) {
            this.f17149b = str;
            this.f17148a = i9;
            this.f17150c = i10;
            this.f17151d = i11;
        }
    }

    public C4978a(InputStream inputStream) {
        d[][] dVarArr = f17099N;
        this.f17123d = new HashMap[dVarArr.length];
        this.f17124e = new HashSet(dVarArr.length);
        this.f17125f = ByteOrder.BIG_ENDIAN;
        if (inputStream != null) {
            this.f17120a = null;
            if (inputStream instanceof AssetManager.AssetInputStream) {
                this.f17121b = (AssetManager.AssetInputStream) inputStream;
            } else {
                this.f17121b = null;
            }
            m19305y(inputStream);
            return;
        }
        throw new IllegalArgumentException("inputStream cannot be null");
    }
}
