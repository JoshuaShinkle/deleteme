package p116k4;

import android.view.ViewTreeObserver;
import android.widget.TextView;

/* renamed from: k4.r0 */
/* loaded from: classes.dex */
public class C5179r0 {

    /* renamed from: a */
    public static volatile long f17745a;

    /* renamed from: k4.r0$a */
    public class a implements ViewTreeObserver.OnPreDrawListener {

        /* renamed from: b */
        public final /* synthetic */ TextView f17746b;

        /* renamed from: c */
        public final /* synthetic */ int f17747c;

        public a(TextView textView, int i9) {
            this.f17746b = textView;
            this.f17747c = i9;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (this.f17746b.getWidth() > 0 && this.f17746b.getTextSize() > 0.01d && this.f17746b.getLineCount() > this.f17747c) {
                this.f17746b.setTextSize(0, (float) (this.f17746b.getTextSize() * 0.9d));
                return true;
            }
            if (this.f17746b.getWidth() == 0 && this.f17746b.getTextSize() > 0.9f && this.f17746b.getText().length() > 0) {
                return true;
            }
            this.f17746b.setVisibility(0);
            this.f17746b.getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }

    /* renamed from: a */
    public static boolean m20246a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - f17745a > 0 && jCurrentTimeMillis - f17745a < 700) {
            return true;
        }
        f17745a = jCurrentTimeMillis;
        return false;
    }

    /* renamed from: b */
    public static void m20247b(TextView textView, int i9) {
        a aVar = new a(textView, i9);
        textView.setMaxLines(i9 + 1);
        textView.getViewTreeObserver().addOnPreDrawListener(aVar);
        textView.requestLayout();
    }
}
