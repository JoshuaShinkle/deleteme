package com.google.android.exoplayer2.upstream.crypto;

import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class AesFlushingCipher {
    private final int blockSize;
    private final Cipher cipher;
    private final byte[] flushedBlock;
    private int pendingXorBytes;
    private final byte[] zerosBlock;

    public AesFlushingCipher(int i9, byte[] bArr, long j9, long j10) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            this.cipher = cipher;
            int blockSize = cipher.getBlockSize();
            this.blockSize = blockSize;
            this.zerosBlock = new byte[blockSize];
            this.flushedBlock = new byte[blockSize];
            long j11 = j10 / blockSize;
            int i10 = (int) (j10 % blockSize);
            cipher.init(i9, new SecretKeySpec(bArr, cipher.getAlgorithm().split("/")[0]), new IvParameterSpec(getInitializationVector(j9, j11)));
            if (i10 != 0) {
                updateInPlace(new byte[i10], 0, i10);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e9) {
            throw new RuntimeException(e9);
        }
    }

    private byte[] getInitializationVector(long j9, long j10) {
        return ByteBuffer.allocate(16).putLong(j9).putLong(j10).array();
    }

    private int nonFlushingUpdate(byte[] bArr, int i9, int i10, byte[] bArr2, int i11) {
        try {
            return this.cipher.update(bArr, i9, i10, bArr2, i11);
        } catch (ShortBufferException e9) {
            throw new RuntimeException(e9);
        }
    }

    public void update(byte[] bArr, int i9, int i10, byte[] bArr2, int i11) {
        int i12 = i9;
        do {
            int i13 = this.pendingXorBytes;
            if (i13 <= 0) {
                int iNonFlushingUpdate = nonFlushingUpdate(bArr, i12, i10, bArr2, i11);
                if (i10 == iNonFlushingUpdate) {
                    return;
                }
                int i14 = i10 - iNonFlushingUpdate;
                int i15 = 0;
                Assertions.checkState(i14 < this.blockSize);
                int i16 = i11 + iNonFlushingUpdate;
                int i17 = this.blockSize - i14;
                this.pendingXorBytes = i17;
                Assertions.checkState(nonFlushingUpdate(this.zerosBlock, 0, i17, this.flushedBlock, 0) == this.blockSize);
                while (i15 < i14) {
                    bArr2[i16] = this.flushedBlock[i15];
                    i15++;
                    i16++;
                }
                return;
            }
            bArr2[i11] = (byte) (bArr[i12] ^ this.flushedBlock[this.blockSize - i13]);
            i11++;
            i12++;
            this.pendingXorBytes = i13 - 1;
            i10--;
        } while (i10 != 0);
    }

    public void updateInPlace(byte[] bArr, int i9, int i10) {
        update(bArr, i9, i10, bArr, i9);
    }
}
