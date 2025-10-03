package com.cyberlink.meeting.page.p032m;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.widget.FaceView;
import org.webrtc.SurfaceViewRenderer;

/* renamed from: com.cyberlink.meeting.page.m.f */
/* loaded from: classes.dex */
public class C1376f {

    /* renamed from: a */
    public final FrameLayout f7078a;

    /* renamed from: b */
    public final ViewGroup f7079b;

    /* renamed from: c */
    public final SurfaceViewRenderer f7080c;

    /* renamed from: d */
    public final ViewGroup f7081d;

    /* renamed from: e */
    public final TextView f7082e;

    /* renamed from: f */
    public final View f7083f;

    /* renamed from: g */
    public final ImageView f7084g;

    /* renamed from: h */
    public final ImageView f7085h;

    /* renamed from: i */
    public final ImageView f7086i;

    /* renamed from: j */
    public final FaceView f7087j;

    /* renamed from: k */
    public final ImageView f7088k;

    /* renamed from: l */
    public ValueAnimator f7089l;

    /* renamed from: m */
    public ValueAnimator f7090m;

    /* renamed from: n */
    public MeetingActivity.AspectRatio f7091n;

    /* renamed from: o */
    public final ImageView f7092o;

    /* renamed from: p */
    public int f7093p = 0;

    public C1376f(FrameLayout frameLayout) {
        this.f7078a = frameLayout;
        this.f7079b = (ViewGroup) frameLayout.findViewById(R.id.rendererContainer);
        this.f7080c = (SurfaceViewRenderer) frameLayout.findViewById(R.id.remoteRendererView);
        this.f7081d = (ViewGroup) frameLayout.findViewById(R.id.speakerInfoContainer);
        this.f7082e = (TextView) frameLayout.findViewById(R.id.remoteSpeakerName);
        this.f7083f = frameLayout.findViewById(R.id.remoteSpeakerSpeechStatus);
        this.f7084g = (ImageView) frameLayout.findViewById(R.id.remoteSpeakerAudioMute);
        this.f7085h = (ImageView) frameLayout.findViewById(R.id.remoteSpeakerVideoMute);
        this.f7086i = (ImageView) frameLayout.findViewById(R.id.remoteSpeakerRaiseHand);
        this.f7087j = (FaceView) frameLayout.findViewById(R.id.faceView);
        this.f7088k = (ImageView) frameLayout.findViewById(R.id.remoteSpeakerAvatar);
        this.f7092o = (ImageView) frameLayout.findViewById(R.id.pinVideo);
    }

    /* renamed from: a */
    public void m7138a() {
        m7140c();
        m7139b();
    }

    /* renamed from: b */
    public void m7139b() {
        ValueAnimator valueAnimator = this.f7090m;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /* renamed from: c */
    public void m7140c() {
        ValueAnimator valueAnimator = this.f7089l;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /* renamed from: d */
    public void m7141d() {
        this.f7080c.release();
    }
}
