package p226w1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: w1.a */
/* loaded from: classes.dex */
public final class C6508a {

    /* renamed from: a */
    public static final AtomicReference<byte[]> f21905a = new AtomicReference<>();

    /* renamed from: w1.a$b */
    public static final class b {

        /* renamed from: a */
        public final int f21908a;

        /* renamed from: b */
        public final int f21909b;

        /* renamed from: c */
        public final byte[] f21910c;

        public b(byte[] bArr, int i9, int i10) {
            this.f21910c = bArr;
            this.f21908a = i9;
            this.f21909b = i10;
        }
    }

    /* renamed from: a */
    public static ByteBuffer m24912a(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        FileChannel channel = null;
        try {
            long length = file.length();
            if (length > 2147483647L) {
                throw new IOException("File too large to map into memory");
            }
            if (length == 0) {
                throw new IOException("File unsuitable for memory mapping");
            }
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                channel = randomAccessFile.getChannel();
                MappedByteBuffer mappedByteBufferLoad = channel.map(FileChannel.MapMode.READ_ONLY, 0L, length).load();
                try {
                    channel.close();
                } catch (IOException unused) {
                }
                try {
                    randomAccessFile.close();
                } catch (IOException unused2) {
                }
                return mappedByteBufferLoad;
            } catch (Throwable th) {
                th = th;
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException unused3) {
                    }
                }
                if (randomAccessFile == null) {
                    throw th;
                }
                try {
                    randomAccessFile.close();
                    throw th;
                } catch (IOException unused4) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }

    /* renamed from: b */
    public static b m24913b(ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly() || !byteBuffer.hasArray()) {
            return null;
        }
        return new b(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit());
    }

    /* renamed from: c */
    public static byte[] m24914c(ByteBuffer byteBuffer) {
        b bVarM24913b = m24913b(byteBuffer);
        if (bVarM24913b != null && bVarM24913b.f21908a == 0 && bVarM24913b.f21909b == bVarM24913b.f21910c.length) {
            return byteBuffer.array();
        }
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        byte[] bArr = new byte[byteBufferAsReadOnlyBuffer.limit()];
        byteBufferAsReadOnlyBuffer.position(0);
        byteBufferAsReadOnlyBuffer.get(bArr);
        return bArr;
    }

    /* renamed from: d */
    public static void m24915d(ByteBuffer byteBuffer, File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        byteBuffer.position(0);
        FileChannel channel = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                channel = randomAccessFile.getChannel();
                channel.write(byteBuffer);
                channel.force(false);
                channel.close();
                randomAccessFile.close();
                try {
                    channel.close();
                } catch (IOException unused) {
                }
                try {
                    randomAccessFile.close();
                } catch (IOException unused2) {
                }
            } catch (Throwable th) {
                th = th;
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException unused3) {
                    }
                }
                if (randomAccessFile == null) {
                    throw th;
                }
                try {
                    randomAccessFile.close();
                    throw th;
                } catch (IOException unused4) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }

    /* renamed from: e */
    public static InputStream m24916e(ByteBuffer byteBuffer) {
        return new a(byteBuffer);
    }

    /* renamed from: w1.a$a */
    public static class a extends InputStream {

        /* renamed from: b */
        public final ByteBuffer f21906b;

        /* renamed from: c */
        public int f21907c = -1;

        public a(ByteBuffer byteBuffer) {
            this.f21906b = byteBuffer;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.f21906b.remaining();
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i9) {
            this.f21907c = this.f21906b.position();
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.f21906b.hasRemaining()) {
                return this.f21906b.get();
            }
            return -1;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            int i9 = this.f21907c;
            if (i9 == -1) {
                throw new IOException("Cannot reset to unset mark position");
            }
            this.f21906b.position(i9);
        }

        @Override // java.io.InputStream
        public long skip(long j9) {
            if (!this.f21906b.hasRemaining()) {
                return -1L;
            }
            long jMin = Math.min(j9, available());
            this.f21906b.position((int) (r0.position() + jMin));
            return jMin;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i9, int i10) {
            if (!this.f21906b.hasRemaining()) {
                return -1;
            }
            int iMin = Math.min(i10, available());
            this.f21906b.get(bArr, i9, iMin);
            return iMin;
        }
    }
}
