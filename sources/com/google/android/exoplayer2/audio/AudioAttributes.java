package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.AudioAttributes;

/* loaded from: classes.dex */
public final class AudioAttributes {
    public static final AudioAttributes DEFAULT = new Builder().build();
    private android.media.AudioAttributes audioAttributesV21;
    public final int contentType;
    public final int flags;
    public final int usage;

    public static final class Builder {
        private int contentType = 0;
        private int flags = 0;
        private int usage = 1;

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage);
        }

        public Builder setContentType(int i9) {
            this.contentType = i9;
            return this;
        }

        public Builder setFlags(int i9) {
            this.flags = i9;
            return this;
        }

        public Builder setUsage(int i9) {
            this.usage = i9;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioAttributes.class != obj.getClass()) {
            return false;
        }
        AudioAttributes audioAttributes = (AudioAttributes) obj;
        return this.contentType == audioAttributes.contentType && this.flags == audioAttributes.flags && this.usage == audioAttributes.usage;
    }

    @TargetApi(21)
    public android.media.AudioAttributes getAudioAttributesV21() {
        if (this.audioAttributesV21 == null) {
            this.audioAttributesV21 = new AudioAttributes.Builder().setContentType(this.contentType).setFlags(this.flags).setUsage(this.usage).build();
        }
        return this.audioAttributesV21;
    }

    public int hashCode() {
        return ((((527 + this.contentType) * 31) + this.flags) * 31) + this.usage;
    }

    private AudioAttributes(int i9, int i10, int i11) {
        this.contentType = i9;
        this.flags = i10;
        this.usage = i11;
    }
}
