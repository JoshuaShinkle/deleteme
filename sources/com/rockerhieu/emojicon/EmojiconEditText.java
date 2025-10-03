package com.rockerhieu.emojicon;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/* loaded from: classes2.dex */
public class EmojiconEditText extends EditText {
    private OnKeyEventPreImeListener listener;
    private int mEmojiconSize;

    public interface OnKeyEventPreImeListener {
        void onPreImeKeyEvent(int i9);
    }

    public EmojiconEditText(Context context) {
        super(context);
        this.mEmojiconSize = (int) getTextSize();
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C4611R.styleable.Emojicon);
        this.mEmojiconSize = (int) typedArrayObtainStyledAttributes.getDimension(C4611R.styleable.Emojicon_emojiconSize, getTextSize());
        typedArrayObtainStyledAttributes.recycle();
        setText(getText());
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i9, KeyEvent keyEvent) {
        OnKeyEventPreImeListener onKeyEventPreImeListener = this.listener;
        if (onKeyEventPreImeListener != null) {
            onKeyEventPreImeListener.onPreImeKeyEvent(i9);
        }
        return super.onKeyPreIme(i9, keyEvent);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        super.onTextChanged(charSequence, i9, i10, i11);
    }

    public void setEmojiconSize(int i9) {
        this.mEmojiconSize = i9;
    }

    public void setOnPreImeKeyListener(OnKeyEventPreImeListener onKeyEventPreImeListener) {
        this.listener = onKeyEventPreImeListener;
    }

    public EmojiconEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public EmojiconEditText(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        init(attributeSet);
    }
}
