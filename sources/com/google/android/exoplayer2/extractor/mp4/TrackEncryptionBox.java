package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public final class TrackEncryptionBox {
    private static final String TAG = "TrackEncryptionBox";
    public final TrackOutput.CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final int initializationVectorSize;
    public final boolean isEncrypted;
    public final String schemeType;

    public TrackEncryptionBox(boolean z8, String str, int i9, byte[] bArr, int i10, int i11, byte[] bArr2) {
        Assertions.checkArgument((bArr2 == null) ^ (i9 == 0));
        this.isEncrypted = z8;
        this.schemeType = str;
        this.initializationVectorSize = i9;
        this.defaultInitializationVector = bArr2;
        this.cryptoData = new TrackOutput.CryptoData(schemeToCryptoMode(str), bArr, i10, i11);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static int schemeToCryptoMode(String str) {
        if (str == null) {
            return 1;
        }
        char c9 = 65535;
        switch (str.hashCode()) {
            case 3046605:
                if (str.equals(C3322C.CENC_TYPE_cbc1)) {
                    c9 = 0;
                    break;
                }
                break;
            case 3046671:
                if (str.equals(C3322C.CENC_TYPE_cbcs)) {
                    c9 = 1;
                    break;
                }
                break;
            case 3049879:
                if (str.equals(C3322C.CENC_TYPE_cenc)) {
                    c9 = 2;
                    break;
                }
                break;
            case 3049895:
                if (str.equals(C3322C.CENC_TYPE_cens)) {
                    c9 = 3;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
            case 1:
                return 2;
            default:
                Log.w(TAG, "Unsupported protection scheme type '" + str + "'. Assuming AES-CTR crypto mode.");
            case 2:
            case 3:
                return 1;
        }
    }
}
