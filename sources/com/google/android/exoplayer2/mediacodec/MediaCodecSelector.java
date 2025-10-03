package com.google.android.exoplayer2.mediacodec;

/* loaded from: classes.dex */
public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT = new MediaCodecSelector() { // from class: com.google.android.exoplayer2.mediacodec.MediaCodecSelector.1
        @Override // com.google.android.exoplayer2.mediacodec.MediaCodecSelector
        public MediaCodecInfo getDecoderInfo(String str, boolean z8) {
            return MediaCodecUtil.getDecoderInfo(str, z8);
        }

        @Override // com.google.android.exoplayer2.mediacodec.MediaCodecSelector
        public MediaCodecInfo getPassthroughDecoderInfo() {
            return MediaCodecUtil.getPassthroughDecoderInfo();
        }
    };

    MediaCodecInfo getDecoderInfo(String str, boolean z8);

    MediaCodecInfo getPassthroughDecoderInfo();
}
