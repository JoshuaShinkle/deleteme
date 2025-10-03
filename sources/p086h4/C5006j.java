package p086h4;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cyberlink.p030U.R;

/* renamed from: h4.j */
/* loaded from: classes.dex */
public class C5006j {

    /* renamed from: a */
    public LinearLayout f17250a;

    /* renamed from: b */
    public int f17251b;

    public C5006j(LinearLayout linearLayout) {
        this.f17250a = linearLayout;
    }

    /* renamed from: a */
    public void m19450a(Context context, int i9) {
        if (context == null) {
            return;
        }
        int childCount = this.f17250a.getChildCount();
        if (childCount < i9) {
            for (int i10 = 0; i10 < i9 - childCount; i10++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.drawable.image_selector_dot_index);
                imageView.setContentDescription("[AID]Sticker_Indicator[" + (childCount + i10) + "]");
                this.f17250a.addView(imageView);
            }
        }
        int i11 = this.f17251b;
        if (i11 < i9) {
            while (i11 < i9) {
                this.f17250a.getChildAt(i11).setVisibility(0);
                i11++;
            }
        } else if (i11 > i9) {
            for (int i12 = i9; i12 < childCount; i12++) {
                this.f17250a.getChildAt(i12).setVisibility(8);
            }
        }
        if (i9 == 1) {
            this.f17250a.getChildAt(0).setVisibility(8);
        } else {
            this.f17250a.getChildAt(0).setVisibility(0);
        }
        this.f17251b = i9;
        m19451b(0);
    }

    /* renamed from: b */
    public void m19451b(int i9) {
        int i10 = 0;
        while (i10 < this.f17250a.getChildCount()) {
            this.f17250a.getChildAt(i10).setSelected(i10 == i9);
            i10++;
        }
    }

    /* renamed from: c */
    public void m19452c(int i9) {
        this.f17250a.setVisibility(i9);
    }
}
