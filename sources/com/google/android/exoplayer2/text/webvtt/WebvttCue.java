package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.util.Log;
import com.google.android.exoplayer2.text.Cue;

/* loaded from: classes.dex */
public final class WebvttCue extends Cue {
    public final long endTime;
    public final long startTime;

    /* renamed from: com.google.android.exoplayer2.text.webvtt.WebvttCue$1 */
    public static /* synthetic */ class C34211 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment;

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            $SwitchMap$android$text$Layout$Alignment = iArr;
            try {
                iArr[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class Builder {
        private static final String TAG = "WebvttCueBuilder";
        private long endTime;
        private float line;
        private int lineAnchor;
        private int lineType;
        private float position;
        private int positionAnchor;
        private long startTime;
        private SpannableStringBuilder text;
        private Layout.Alignment textAlignment;
        private float width;

        public Builder() {
            reset();
        }

        private Builder derivePositionAnchorFromAlignment() {
            Layout.Alignment alignment = this.textAlignment;
            if (alignment == null) {
                this.positionAnchor = Integer.MIN_VALUE;
            } else {
                int i9 = C34211.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()];
                if (i9 == 1) {
                    this.positionAnchor = 0;
                } else if (i9 == 2) {
                    this.positionAnchor = 1;
                } else if (i9 != 3) {
                    Log.w(TAG, "Unrecognized alignment: " + this.textAlignment);
                    this.positionAnchor = 0;
                } else {
                    this.positionAnchor = 2;
                }
            }
            return this;
        }

        public WebvttCue build() {
            if (this.position != Float.MIN_VALUE && this.positionAnchor == Integer.MIN_VALUE) {
                derivePositionAnchorFromAlignment();
            }
            return new WebvttCue(this.startTime, this.endTime, this.text, this.textAlignment, this.line, this.lineType, this.lineAnchor, this.position, this.positionAnchor, this.width);
        }

        public void reset() {
            this.startTime = 0L;
            this.endTime = 0L;
            this.text = null;
            this.textAlignment = null;
            this.line = Float.MIN_VALUE;
            this.lineType = Integer.MIN_VALUE;
            this.lineAnchor = Integer.MIN_VALUE;
            this.position = Float.MIN_VALUE;
            this.positionAnchor = Integer.MIN_VALUE;
            this.width = Float.MIN_VALUE;
        }

        public Builder setEndTime(long j9) {
            this.endTime = j9;
            return this;
        }

        public Builder setLine(float f9) {
            this.line = f9;
            return this;
        }

        public Builder setLineAnchor(int i9) {
            this.lineAnchor = i9;
            return this;
        }

        public Builder setLineType(int i9) {
            this.lineType = i9;
            return this;
        }

        public Builder setPosition(float f9) {
            this.position = f9;
            return this;
        }

        public Builder setPositionAnchor(int i9) {
            this.positionAnchor = i9;
            return this;
        }

        public Builder setStartTime(long j9) {
            this.startTime = j9;
            return this;
        }

        public Builder setText(SpannableStringBuilder spannableStringBuilder) {
            this.text = spannableStringBuilder;
            return this;
        }

        public Builder setTextAlignment(Layout.Alignment alignment) {
            this.textAlignment = alignment;
            return this;
        }

        public Builder setWidth(float f9) {
            this.width = f9;
            return this;
        }
    }

    public WebvttCue(CharSequence charSequence) {
        this(0L, 0L, charSequence);
    }

    public boolean isNormalCue() {
        return this.line == Float.MIN_VALUE && this.position == Float.MIN_VALUE;
    }

    public WebvttCue(long j9, long j10, CharSequence charSequence) {
        this(j9, j10, charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public WebvttCue(long j9, long j10, CharSequence charSequence, Layout.Alignment alignment, float f9, int i9, int i10, float f10, int i11, float f11) {
        super(charSequence, alignment, f9, i9, i10, f10, i11, f11);
        this.startTime = j9;
        this.endTime = j10;
    }
}
