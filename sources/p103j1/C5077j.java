package p103j1;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import p022c1.InterfaceC0705b;
import p226w1.C6516i;

/* renamed from: j1.j */
/* loaded from: classes.dex */
public final class C5077j implements ImageHeaderParser {

    /* renamed from: a */
    public static final byte[] f17506a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    /* renamed from: b */
    public static final int[] f17507b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    /* renamed from: j1.j$a */
    public static final class a implements c {

        /* renamed from: a */
        public final ByteBuffer f17508a;

        public a(ByteBuffer byteBuffer) {
            this.f17508a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // p103j1.C5077j.c
        /* renamed from: a */
        public int mo19871a() {
            return ((mo19874d() << 8) & 65280) | (mo19874d() & 255);
        }

        @Override // p103j1.C5077j.c
        /* renamed from: b */
        public int mo19872b(byte[] bArr, int i9) {
            int iMin = Math.min(i9, this.f17508a.remaining());
            if (iMin == 0) {
                return -1;
            }
            this.f17508a.get(bArr, 0, iMin);
            return iMin;
        }

        @Override // p103j1.C5077j.c
        /* renamed from: c */
        public short mo19873c() {
            return (short) (mo19874d() & 255);
        }

        @Override // p103j1.C5077j.c
        /* renamed from: d */
        public int mo19874d() {
            if (this.f17508a.remaining() < 1) {
                return -1;
            }
            return this.f17508a.get();
        }

        @Override // p103j1.C5077j.c
        public long skip(long j9) {
            int iMin = (int) Math.min(this.f17508a.remaining(), j9);
            ByteBuffer byteBuffer = this.f17508a;
            byteBuffer.position(byteBuffer.position() + iMin);
            return iMin;
        }
    }

    /* renamed from: j1.j$b */
    public static final class b {

        /* renamed from: a */
        public final ByteBuffer f17509a;

        public b(byte[] bArr, int i9) {
            this.f17509a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i9);
        }

        /* renamed from: a */
        public short m19875a(int i9) {
            if (m19877c(i9, 2)) {
                return this.f17509a.getShort(i9);
            }
            return (short) -1;
        }

        /* renamed from: b */
        public int m19876b(int i9) {
            if (m19877c(i9, 4)) {
                return this.f17509a.getInt(i9);
            }
            return -1;
        }

        /* renamed from: c */
        public final boolean m19877c(int i9, int i10) {
            return this.f17509a.remaining() - i9 >= i10;
        }

        /* renamed from: d */
        public int m19878d() {
            return this.f17509a.remaining();
        }

        /* renamed from: e */
        public void m19879e(ByteOrder byteOrder) {
            this.f17509a.order(byteOrder);
        }
    }

    /* renamed from: j1.j$c */
    public interface c {
        /* renamed from: a */
        int mo19871a();

        /* renamed from: b */
        int mo19872b(byte[] bArr, int i9);

        /* renamed from: c */
        short mo19873c();

        /* renamed from: d */
        int mo19874d();

        long skip(long j9);
    }

    /* renamed from: j1.j$d */
    public static final class d implements c {

        /* renamed from: a */
        public final InputStream f17510a;

        public d(InputStream inputStream) {
            this.f17510a = inputStream;
        }

        @Override // p103j1.C5077j.c
        /* renamed from: a */
        public int mo19871a() {
            return ((this.f17510a.read() << 8) & 65280) | (this.f17510a.read() & 255);
        }

        @Override // p103j1.C5077j.c
        /* renamed from: b */
        public int mo19872b(byte[] bArr, int i9) throws IOException {
            int i10 = i9;
            while (i10 > 0) {
                int i11 = this.f17510a.read(bArr, i9 - i10, i10);
                if (i11 == -1) {
                    break;
                }
                i10 -= i11;
            }
            return i9 - i10;
        }

        @Override // p103j1.C5077j.c
        /* renamed from: c */
        public short mo19873c() {
            return (short) (this.f17510a.read() & 255);
        }

        @Override // p103j1.C5077j.c
        /* renamed from: d */
        public int mo19874d() {
            return this.f17510a.read();
        }

        @Override // p103j1.C5077j.c
        public long skip(long j9) throws IOException {
            if (j9 < 0) {
                return 0L;
            }
            long j10 = j9;
            while (j10 > 0) {
                long jSkip = this.f17510a.skip(j10);
                if (jSkip <= 0) {
                    if (this.f17510a.read() == -1) {
                        break;
                    }
                    jSkip = 1;
                }
                j10 -= jSkip;
            }
            return j9 - j10;
        }
    }

    /* renamed from: d */
    public static int m19863d(int i9, int i10) {
        return i9 + 2 + (i10 * 12);
    }

    /* renamed from: g */
    public static boolean m19864g(int i9) {
        return (i9 & 65496) == 65496 || i9 == 19789 || i9 == 18761;
    }

    /* renamed from: j */
    public static int m19865j(b bVar) {
        ByteOrder byteOrder;
        short sM19875a = bVar.m19875a(6);
        if (sM19875a != 18761) {
            if (sM19875a != 19789 && Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Unknown endianness = " + ((int) sM19875a));
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        bVar.m19879e(byteOrder);
        int iM19876b = bVar.m19876b(10) + 6;
        short sM19875a2 = bVar.m19875a(iM19876b);
        for (int i9 = 0; i9 < sM19875a2; i9++) {
            int iM19863d = m19863d(iM19876b, i9);
            short sM19875a3 = bVar.m19875a(iM19863d);
            if (sM19875a3 == 274) {
                short sM19875a4 = bVar.m19875a(iM19863d + 2);
                if (sM19875a4 >= 1 && sM19875a4 <= 12) {
                    int iM19876b2 = bVar.m19876b(iM19863d + 4);
                    if (iM19876b2 >= 0) {
                        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Got tagIndex=" + i9 + " tagType=" + ((int) sM19875a3) + " formatCode=" + ((int) sM19875a4) + " componentCount=" + iM19876b2);
                        }
                        int i10 = iM19876b2 + f17507b[sM19875a4];
                        if (i10 <= 4) {
                            int i11 = iM19863d + 8;
                            if (i11 >= 0 && i11 <= bVar.m19878d()) {
                                if (i10 >= 0 && i10 + i11 <= bVar.m19878d()) {
                                    return bVar.m19875a(i11);
                                }
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    Log.d("DfltImageHeaderParser", "Illegal number of bytes for TI tag data tagType=" + ((int) sM19875a3));
                                }
                            } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                Log.d("DfltImageHeaderParser", "Illegal tagValueOffset=" + i11 + " tagType=" + ((int) sM19875a3));
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Got byte count > 4, not orientation, continuing, formatCode=" + ((int) sM19875a4));
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        Log.d("DfltImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Got invalid format code = " + ((int) sM19875a4));
                }
            }
        }
        return -1;
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    /* renamed from: a */
    public ImageHeaderParser.ImageType mo3833a(ByteBuffer byteBuffer) {
        return m19867f(new a((ByteBuffer) C6516i.m24938d(byteBuffer)));
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    /* renamed from: b */
    public ImageHeaderParser.ImageType mo3834b(InputStream inputStream) {
        return m19867f(new d((InputStream) C6516i.m24938d(inputStream)));
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    /* renamed from: c */
    public int mo3835c(InputStream inputStream, InterfaceC0705b interfaceC0705b) {
        return m19866e(new d((InputStream) C6516i.m24938d(inputStream)), (InterfaceC0705b) C6516i.m24938d(interfaceC0705b));
    }

    /* renamed from: e */
    public final int m19866e(c cVar, InterfaceC0705b interfaceC0705b) {
        int iMo19871a = cVar.mo19871a();
        if (!m19864g(iMo19871a)) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Parser doesn't handle magic number: " + iMo19871a);
            }
            return -1;
        }
        int iM19869i = m19869i(cVar);
        if (iM19869i == -1) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        byte[] bArr = (byte[]) interfaceC0705b.mo3481d(iM19869i, byte[].class);
        try {
            return m19870k(cVar, bArr, iM19869i);
        } finally {
            interfaceC0705b.put(bArr);
        }
    }

    /* renamed from: f */
    public final ImageHeaderParser.ImageType m19867f(c cVar) {
        int iMo19871a = cVar.mo19871a();
        if (iMo19871a == 65496) {
            return ImageHeaderParser.ImageType.JPEG;
        }
        int iMo19871a2 = ((iMo19871a << 16) & (-65536)) | (cVar.mo19871a() & 65535);
        if (iMo19871a2 == -1991225785) {
            cVar.skip(21L);
            return cVar.mo19874d() >= 3 ? ImageHeaderParser.ImageType.PNG_A : ImageHeaderParser.ImageType.PNG;
        }
        if ((iMo19871a2 >> 8) == 4671814) {
            return ImageHeaderParser.ImageType.GIF;
        }
        if (iMo19871a2 != 1380533830) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        cVar.skip(4L);
        if ((((cVar.mo19871a() << 16) & (-65536)) | (cVar.mo19871a() & 65535)) != 1464156752) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int iMo19871a3 = ((cVar.mo19871a() << 16) & (-65536)) | (cVar.mo19871a() & 65535);
        if ((iMo19871a3 & (-256)) != 1448097792) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int i9 = iMo19871a3 & 255;
        if (i9 == 88) {
            cVar.skip(4L);
            return (cVar.mo19874d() & 16) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
        }
        if (i9 != 76) {
            return ImageHeaderParser.ImageType.WEBP;
        }
        cVar.skip(4L);
        return (cVar.mo19874d() & 8) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
    }

    /* renamed from: h */
    public final boolean m19868h(byte[] bArr, int i9) {
        boolean z8 = bArr != null && i9 > f17506a.length;
        if (z8) {
            int i10 = 0;
            while (true) {
                byte[] bArr2 = f17506a;
                if (i10 >= bArr2.length) {
                    break;
                }
                if (bArr[i10] != bArr2[i10]) {
                    return false;
                }
                i10++;
            }
        }
        return z8;
    }

    /* renamed from: i */
    public final int m19869i(c cVar) {
        short sMo19873c;
        int iMo19871a;
        long j9;
        long jSkip;
        do {
            short sMo19873c2 = cVar.mo19873c();
            if (sMo19873c2 != 255) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Unknown segmentId=" + ((int) sMo19873c2));
                }
                return -1;
            }
            sMo19873c = cVar.mo19873c();
            if (sMo19873c == 218) {
                return -1;
            }
            if (sMo19873c == 217) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            iMo19871a = cVar.mo19871a() - 2;
            if (sMo19873c == 225) {
                return iMo19871a;
            }
            j9 = iMo19871a;
            jSkip = cVar.skip(j9);
        } while (jSkip == j9);
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Unable to skip enough data, type: " + ((int) sMo19873c) + ", wanted to skip: " + iMo19871a + ", but actually skipped: " + jSkip);
        }
        return -1;
    }

    /* renamed from: k */
    public final int m19870k(c cVar, byte[] bArr, int i9) {
        int iMo19872b = cVar.mo19872b(bArr, i9);
        if (iMo19872b == i9) {
            if (m19868h(bArr, i9)) {
                return m19865j(new b(bArr, i9));
            }
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
            }
            return -1;
        }
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Unable to read exif segment data, length: " + i9 + ", actually read: " + iMo19872b);
        }
        return -1;
    }
}
