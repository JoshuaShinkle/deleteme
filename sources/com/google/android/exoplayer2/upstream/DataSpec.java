package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class DataSpec {
    public static final int FLAG_ALLOW_CACHING_UNKNOWN_LENGTH = 2;
    public static final int FLAG_ALLOW_GZIP = 1;
    public final long absoluteStreamPosition;
    public final int flags;
    public final String key;
    public final long length;
    public final long position;
    public final byte[] postBody;
    public final Uri uri;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DataSpec(Uri uri) {
        this(uri, 0);
    }

    public boolean isFlagSet(int i9) {
        return (this.flags & i9) == i9;
    }

    public DataSpec subrange(long j9) {
        long j10 = this.length;
        return subrange(j9, j10 != -1 ? j10 - j9 : -1L);
    }

    public String toString() {
        return "DataSpec[" + this.uri + ", " + Arrays.toString(this.postBody) + ", " + this.absoluteStreamPosition + ", " + this.position + ", " + this.length + ", " + this.key + ", " + this.flags + "]";
    }

    public DataSpec(Uri uri, int i9) {
        this(uri, 0L, -1L, null, i9);
    }

    public DataSpec subrange(long j9, long j10) {
        return (j9 == 0 && this.length == j10) ? this : new DataSpec(this.uri, this.postBody, this.absoluteStreamPosition + j9, this.position + j9, j10, this.key, this.flags);
    }

    public DataSpec(Uri uri, long j9, long j10, String str) {
        this(uri, j9, j9, j10, str, 0);
    }

    public DataSpec(Uri uri, long j9, long j10, String str, int i9) {
        this(uri, j9, j9, j10, str, i9);
    }

    public DataSpec(Uri uri, long j9, long j10, long j11, String str, int i9) {
        this(uri, null, j9, j10, j11, str, i9);
    }

    public DataSpec(Uri uri, byte[] bArr, long j9, long j10, long j11, String str, int i9) {
        boolean z8 = true;
        Assertions.checkArgument(j9 >= 0);
        Assertions.checkArgument(j10 >= 0);
        if (j11 <= 0 && j11 != -1) {
            z8 = false;
        }
        Assertions.checkArgument(z8);
        this.uri = uri;
        this.postBody = bArr;
        this.absoluteStreamPosition = j9;
        this.position = j10;
        this.length = j11;
        this.key = str;
        this.flags = i9;
    }
}
