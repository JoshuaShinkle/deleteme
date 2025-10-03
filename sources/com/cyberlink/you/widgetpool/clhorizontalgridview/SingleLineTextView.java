package com.cyberlink.you.widgetpool.clhorizontalgridview;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SingleLineTextView extends TextView {
    public SingleLineTextView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int lineCount;
        Layout layout = getLayout();
        if (layout != null && (lineCount = layout.getLineCount()) > 0 && layout.getEllipsisCount(lineCount - 1) > 0) {
            setTextSize(0, getTextSize() - 1.0f);
        }
        super.onDraw(canvas);
    }
}
