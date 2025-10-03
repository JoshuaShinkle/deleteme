package com.google.android.exoplayer2.upstream;

/* loaded from: classes.dex */
public interface LoaderErrorThrower {

    public static final class Dummy implements LoaderErrorThrower {
        @Override // com.google.android.exoplayer2.upstream.LoaderErrorThrower
        public void maybeThrowError() {
        }

        @Override // com.google.android.exoplayer2.upstream.LoaderErrorThrower
        public void maybeThrowError(int i9) {
        }
    }

    void maybeThrowError();

    void maybeThrowError(int i9);
}
