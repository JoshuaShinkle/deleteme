package com.cyberlink.you.widgetpool.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;

/* renamed from: com.cyberlink.you.widgetpool.common.a */
/* loaded from: classes.dex */
public class C3241a extends RelativeLayout {

    /* renamed from: b */
    public ImageView f15093b;

    /* renamed from: c */
    public View f15094c;

    /* renamed from: d */
    public TextView f15095d;

    /* renamed from: e */
    public Context f15096e;

    public C3241a(Context context) {
        super(context);
        this.f15096e = context;
        m17313a(((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.camera_effect_grid_item, this));
    }

    /* renamed from: a */
    public final void m17313a(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.effectGridPhoto);
        this.f15093b = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.f15094c = view.findViewById(R.id.effectGridCheck);
        this.f15095d = (TextView) view.findViewById(R.id.effectThumbName);
    }

    public int getImageHeight() {
        return ((RelativeLayout.LayoutParams) this.f15093b.getLayoutParams()).height;
    }

    public int getImageWidth() {
        return ((RelativeLayout.LayoutParams) this.f15093b.getLayoutParams()).width;
    }

    public void setCheckd(boolean z8) {
        this.f15094c.setSelected(z8);
    }

    public void setImage(Bitmap bitmap) {
        this.f15093b.setImageBitmap(bitmap);
    }

    public void setName(CharSequence charSequence) {
        this.f15095d.setText(charSequence);
    }
}
