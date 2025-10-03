package com.cyberlink.you.activity.chatdialog.message;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class SelfDestructView extends ImageView {

    /* renamed from: b */
    public String f10347b;

    public SelfDestructView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String getMessageId() {
        return this.f10347b;
    }

    public void setMessageId(String str) {
        this.f10347b = str;
    }

    public SelfDestructView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }
}
