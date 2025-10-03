package com.cyberlink.you.activity;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* renamed from: com.cyberlink.you.activity.aj */
/* loaded from: classes.dex */
public class C1886aj extends FrameLayout {

    /* renamed from: b */
    public a f9728b;

    /* renamed from: com.cyberlink.you.activity.aj$a */
    public interface a {
        /* renamed from: a */
        void mo9756a();

        /* renamed from: b */
        void mo9757b();
    }

    public C1886aj(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f9728b.mo9756a();
        } else if (action == 1) {
            this.f9728b.mo9757b();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setTouchListener(a aVar) {
        this.f9728b = aVar;
    }
}
