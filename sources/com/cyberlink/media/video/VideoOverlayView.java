package com.cyberlink.media.video;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.cyberlink.media.video.C1240d;

/* loaded from: classes.dex */
public final class VideoOverlayView extends TextView {

    /* renamed from: b */
    public C1240d f5993b;

    /* renamed from: c */
    public boolean f5994c;

    /* renamed from: d */
    public final C1235b f5995d;

    /* renamed from: e */
    public final Handler f5996e;

    /* renamed from: com.cyberlink.media.video.VideoOverlayView$b */
    public class C1235b implements Handler.Callback {
        public C1235b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            VideoOverlayView.this.invalidate();
            return true;
        }
    }

    /* renamed from: com.cyberlink.media.video.VideoOverlayView$c */
    public interface InterfaceC1236c {
    }

    public VideoOverlayView(Context context) {
        super(context);
        this.f5993b = C1240d.f6007q;
        C1235b c1235b = new C1235b();
        this.f5995d = c1235b;
        this.f5996e = new Handler(c1235b);
        m5451a();
    }

    /* renamed from: a */
    public final void m5451a() {
        setStyle(new C1240d.b().m5478r(this).m5476p());
    }

    /* renamed from: b */
    public final void m5452b() {
    }

    public C1240d getStyle() {
        return this.f5993b;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        this.f5996e.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
    }

    @Deprecated
    public void setLegacyRendering(boolean z8) {
        if (z8) {
            Log.w("VideoOverlayView", "Callback based timed text API in use, this usage is deprecated and will be removed in the future.");
        }
        this.f5994c = z8;
        m5452b();
        invalidate();
    }

    public void setOnDrawnListener(InterfaceC1236c interfaceC1236c) {
    }

    public void setRenderer(C1239c c1239c) {
        invalidate();
    }

    public void setStyle(C1240d c1240d) {
        if (c1240d == null) {
            throw new NullPointerException("style must not be null");
        }
        this.f5993b = c1240d;
        invalidate();
    }

    @Override // android.view.View
    public void setWillNotDraw(boolean z8) {
        super.setWillNotDraw(z8);
        m5452b();
        invalidate();
    }

    public VideoOverlayView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f5993b = C1240d.f6007q;
        C1235b c1235b = new C1235b();
        this.f5995d = c1235b;
        this.f5996e = new Handler(c1235b);
        m5451a();
    }
}
