package p245y2;

import android.view.View;
import android.widget.TextView;
import com.cyberlink.you.database.MessageObj;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: y2.w5 */
/* loaded from: classes.dex */
public final class C6766w5 {

    /* renamed from: a */
    public final TextView f22475a;

    /* renamed from: b */
    public final Runnable f22476b;

    /* renamed from: c */
    public MessageObj f22477c = null;

    /* renamed from: y2.w5$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6766w5.this.m25313e();
            if (C6766w5.this.f22476b != null) {
                C6766w5.this.f22476b.run();
            }
        }
    }

    /* renamed from: y2.w5$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C6766w5.this.f22475a.setVisibility(8);
        }
    }

    /* renamed from: y2.w5$c */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C6766w5.this.f22475a.setVisibility(0);
        }
    }

    /* renamed from: y2.w5$d */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C6766w5.this.f22475a.setVisibility(0);
        }
    }

    public C6766w5(TextView textView, Runnable runnable) {
        this.f22475a = textView;
        this.f22476b = runnable;
        m25315g();
    }

    /* renamed from: c */
    public String m25311c() {
        MessageObj messageObj = this.f22477c;
        return messageObj != null ? messageObj.m14777o() : "";
    }

    /* renamed from: d */
    public final float m25312d() {
        float height = this.f22475a.getHeight();
        if (height == BitmapDescriptorFactory.HUE_RED) {
            return 200.0f;
        }
        return height;
    }

    /* renamed from: e */
    public void m25313e() {
        if (this.f22475a.getVisibility() != 0) {
            return;
        }
        m25314f(300L);
    }

    /* renamed from: f */
    public final void m25314f(long j9) {
        this.f22475a.animate().translationY(m25312d()).setDuration(j9).withStartAction(new c()).withEndAction(new b()).start();
    }

    /* renamed from: g */
    public final void m25315g() {
        this.f22475a.setOnClickListener(new a());
        m25314f(0L);
    }

    /* renamed from: h */
    public void m25316h(String str, MessageObj messageObj) {
        this.f22475a.setText(str);
        this.f22477c = messageObj;
        if (this.f22475a.getVisibility() == 0) {
            return;
        }
        this.f22475a.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(300L).withStartAction(new d()).start();
    }
}
