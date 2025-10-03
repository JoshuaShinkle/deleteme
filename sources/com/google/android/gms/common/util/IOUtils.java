package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@ShowFirstParty
@KeepForSdk
@Deprecated
/* loaded from: classes2.dex */
public final class IOUtils {
    private IOUtils() {
    }

    @KeepForSdk
    public static void closeQuietly(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public static long copyStream(InputStream inputStream, OutputStream outputStream) {
        return copyStream(inputStream, outputStream, false, UserMetadata.MAX_ATTRIBUTE_SIZE);
    }

    @KeepForSdk
    public static boolean isGzipByteBuffer(byte[] bArr) {
        if (bArr.length > 1) {
            if ((((bArr[1] & UnsignedBytes.MAX_VALUE) << 8) | (bArr[0] & UnsignedBytes.MAX_VALUE)) == 35615) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    @Deprecated
    public static byte[] readInputStreamFully(InputStream inputStream) {
        return readInputStreamFully(inputStream, true);
    }

    @KeepForSdk
    @Deprecated
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(byteArrayOutputStream);
        byte[] bArr = new byte[4096];
        while (true) {
            int i9 = inputStream.read(bArr);
            if (i9 == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i9);
        }
    }

    @KeepForSdk
    public static void closeQuietly(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean z8, int i9) throws IOException {
        byte[] bArr = new byte[i9];
        long j9 = 0;
        while (true) {
            try {
                int i10 = inputStream.read(bArr, 0, i9);
                if (i10 == -1) {
                    break;
                }
                j9 += i10;
                outputStream.write(bArr, 0, i10);
            } catch (Throwable th) {
                if (z8) {
                    closeQuietly(inputStream);
                    closeQuietly(outputStream);
                }
                throw th;
            }
        }
        if (z8) {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }
        return j9;
    }

    @KeepForSdk
    @Deprecated
    public static byte[] readInputStreamFully(InputStream inputStream, boolean z8) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyStream(inputStream, byteArrayOutputStream, z8, UserMetadata.MAX_ATTRIBUTE_SIZE);
        return byteArrayOutputStream.toByteArray();
    }
}
