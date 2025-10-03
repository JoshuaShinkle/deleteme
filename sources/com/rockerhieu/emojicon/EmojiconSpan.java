package com.rockerhieu.emojicon;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/* loaded from: classes2.dex */
class EmojiconSpan extends DynamicDrawableSpan {
    private final Context mContext;
    private Drawable mDrawable;
    private final int mResourceId;
    private final int mSize;

    public EmojiconSpan(Context context, int i9, int i10) {
        this.mContext = context;
        this.mResourceId = i9;
        this.mSize = i10;
    }

    @Override // android.text.style.DynamicDrawableSpan
    public Drawable getDrawable() throws Resources.NotFoundException {
        if (this.mDrawable == null) {
            try {
                Drawable drawable = this.mContext.getResources().getDrawable(this.mResourceId);
                this.mDrawable = drawable;
                int i9 = this.mSize;
                drawable.setBounds(0, 0, i9, i9);
            } catch (Exception unused) {
            }
        }
        return this.mDrawable;
    }
}
