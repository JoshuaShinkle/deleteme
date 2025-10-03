package com.rockerhieu.emojicon;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class EmojiconTextView extends TextView {
    private int mEmojiconSize;
    private int maxLines;
    private CharSequence origText;
    private TextUtils.TruncateAt truncateAt;

    public EmojiconTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet == null) {
            this.mEmojiconSize = (int) getTextSize();
        } else {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C4611R.styleable.Emojicon);
            this.mEmojiconSize = (int) typedArrayObtainStyledAttributes.getDimension(C4611R.styleable.Emojicon_emojiconSize, getTextSize());
            typedArrayObtainStyledAttributes.recycle();
        }
        setText(getText());
    }

    @Override // android.widget.TextView
    public void append(CharSequence charSequence, int i9, int i10) {
        super.append(charSequence, i9, i10);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (this.maxLines <= 1 || this.truncateAt != TextUtils.TruncateAt.END) {
            return;
        }
        CharSequence charSequenceSubSequence = this.origText;
        while (getLineCount() > this.maxLines) {
            charSequenceSubSequence = charSequenceSubSequence.subSequence(0, charSequenceSubSequence.length() - 1);
            super.setText(((Object) charSequenceSubSequence) + "...");
            super.onMeasure(i9, i10);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        super.setEllipsize(truncateAt);
        this.truncateAt = truncateAt;
    }

    public void setEmojiconSize(int i9) {
        this.mEmojiconSize = i9;
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i9) {
        super.setMaxLines(i9);
        this.maxLines = i9;
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        this.origText = charSequence;
        super.setText(charSequence, bufferType);
    }

    public EmojiconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public EmojiconTextView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        init(attributeSet);
    }
}
