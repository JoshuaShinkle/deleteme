package com.cyberlink.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import com.cyberlink.p030U.R;
import java.util.ArrayList;
import java.util.List;
import p197t.C6273a;

/* loaded from: classes.dex */
public class FaceView extends View {

    /* renamed from: b */
    public final int f7265b;

    /* renamed from: c */
    public final int f7266c;

    /* renamed from: d */
    public final int f7267d;

    /* renamed from: e */
    public final int f7268e;

    /* renamed from: f */
    public final Pair<Integer, Integer> f7269f;

    /* renamed from: g */
    public final Pair<Integer, Integer> f7270g;

    /* renamed from: h */
    public final List<Object> f7271h;

    /* renamed from: i */
    public final Paint f7272i;

    /* renamed from: j */
    public final Paint f7273j;

    /* renamed from: k */
    public final TextPaint f7274k;

    /* renamed from: l */
    public int f7275l;

    /* renamed from: m */
    public int f7276m;

    /* renamed from: n */
    public float f7277n;

    /* renamed from: o */
    public float f7278o;

    public FaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: a */
    public final void m7358a(Canvas canvas) {
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        this.f7277n = (width * 1.0f) / this.f7275l;
        this.f7278o = (height * 1.0f) / this.f7276m;
        m7358a(canvas);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        if (jCurrentTimeMillis2 > 16) {
            Log.w("FaceView", "onDraw faces[" + this.f7271h.size() + "] took " + jCurrentTimeMillis2 + "ms");
        }
    }

    public FaceView(Context context, AttributeSet attributeSet, int i9) throws Resources.NotFoundException {
        super(context, attributeSet, i9);
        this.f7271h = new ArrayList();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.face_border_width);
        this.f7265b = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.face_attr_default_text_size);
        this.f7266c = dimensionPixelSize2;
        this.f7267d = context.getResources().getDimensionPixelSize(R.dimen.face_attr_min_text_size);
        this.f7268e = context.getResources().getDimensionPixelSize(R.dimen.face_attr_padding);
        this.f7269f = Pair.create(Integer.valueOf(C6273a.m24024c(context, R.color.face_noname_border)), Integer.valueOf(C6273a.m24024c(context, R.color.face_noname_corner_border)));
        this.f7270g = Pair.create(Integer.valueOf(C6273a.m24024c(context, R.color.face_name_border)), Integer.valueOf(C6273a.m24024c(context, R.color.face_name_corner_border)));
        Paint paint = new Paint();
        this.f7272i = paint;
        paint.setStrokeWidth(dimensionPixelSize);
        paint.setStyle(Paint.Style.STROKE);
        Paint paint2 = new Paint();
        this.f7273j = paint2;
        paint2.setColor(C6273a.m24024c(context, R.color.face_label_bg));
        paint2.setStyle(Paint.Style.FILL);
        TextPaint textPaint = new TextPaint();
        this.f7274k = textPaint;
        textPaint.setColor(-1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dimensionPixelSize2);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setLinearText(true);
    }
}
