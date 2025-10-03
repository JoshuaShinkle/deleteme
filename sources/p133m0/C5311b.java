package p133m0;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* renamed from: m0.b */
/* loaded from: classes.dex */
public final class C5311b {

    /* renamed from: m0.b$a */
    public static class a {

        /* renamed from: a */
        public long f18051a;

        /* renamed from: b */
        public long f18052b;
    }

    /* renamed from: a */
    public static long m20786a(RandomAccessFile randomAccessFile, a aVar) throws IOException {
        CRC32 crc32 = new CRC32();
        long j9 = aVar.f18052b;
        randomAccessFile.seek(aVar.f18051a);
        byte[] bArr = new byte[16384];
        int i9 = randomAccessFile.read(bArr, 0, (int) Math.min(PlaybackStateCompat.ACTION_PREPARE, j9));
        while (i9 != -1) {
            crc32.update(bArr, 0, i9);
            j9 -= i9;
            if (j9 == 0) {
                break;
            }
            i9 = randomAccessFile.read(bArr, 0, (int) Math.min(PlaybackStateCompat.ACTION_PREPARE, j9));
        }
        return crc32.getValue();
    }

    /* renamed from: b */
    public static a m20787b(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + randomAccessFile.length());
        }
        long j9 = length - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        long j10 = j9 >= 0 ? j9 : 0L;
        int iReverseBytes = Integer.reverseBytes(101010256);
        do {
            randomAccessFile.seek(length);
            if (randomAccessFile.readInt() == iReverseBytes) {
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                a aVar = new a();
                aVar.f18052b = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                aVar.f18051a = Integer.reverseBytes(randomAccessFile.readInt()) & 4294967295L;
                return aVar;
            }
            length--;
        } while (length >= j10);
        throw new ZipException("End Of Central Directory signature not found");
    }

    /* renamed from: c */
    public static long m20788c(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            return m20786a(randomAccessFile, m20787b(randomAccessFile));
        } finally {
            randomAccessFile.close();
        }
    }
}
