package com.cyberlink.you.activity.ulauncher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.page.ScheduleMeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.JoinCLMWActivity;
import com.cyberlink.you.activity.ScheduleSendActivity;
import com.cyberlink.you.activity.ulauncher.AbstractC2555a;
import com.cyberlink.you.activity.webinar.MyWebinarActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.feedback.PromisedTask;
import com.google.android.exoplayer2.util.MimeTypes;
import org.apache.commons.lang3.time.StopWatch;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.activity.ulauncher.c */
/* loaded from: classes.dex */
public class C2557c extends AbstractC2555a {

    /* renamed from: l */
    public static final String f11590l = "c";

    /* renamed from: d */
    public final View.OnClickListener f11591d = new View.OnClickListener() { // from class: g3.r0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16995b.m13154v(view);
        }
    };

    /* renamed from: e */
    public final View.OnClickListener f11592e = new View.OnClickListener() { // from class: g3.s0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17003b.m13155w(view);
        }
    };

    /* renamed from: f */
    public final View.OnClickListener f11593f = new View.OnClickListener() { // from class: g3.t0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17010b.m13156x(view);
        }
    };

    /* renamed from: g */
    public final View.OnClickListener f11594g = new View.OnClickListener() { // from class: g3.u0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17019b.m13157y(view);
        }
    };

    /* renamed from: h */
    public final View.OnClickListener f11595h = new View.OnClickListener() { // from class: g3.v0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17027b.m13158z(view);
        }
    };

    /* renamed from: i */
    public final View.OnClickListener f11596i = new View.OnClickListener() { // from class: g3.w0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17033b.m13142A(view);
        }
    };

    /* renamed from: j */
    public final View.OnClickListener f11597j = new View.OnClickListener() { // from class: g3.x0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17039b.m13143B(view);
        }
    };

    /* renamed from: k */
    public View f11598k;

    /* renamed from: com.cyberlink.you.activity.ulauncher.c$a */
    public class a extends PromisedTask.AbstractC3021b<String> {
        public a() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            Log.v(C2557c.f11590l, "[querySSOSession] onError: " + i9 + " / error: " + str);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            if (Globals.m7380c2()) {
                intent.setData(Uri.parse("https://u-demo.cyberlink.com/user/meeting/upcoming"));
            } else {
                intent.setData(Uri.parse("https://u.cyberlink.com/user/meeting/upcoming"));
            }
            C2557c.this.startActivity(intent);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(String str) {
            Log.v(C2557c.f11590l, "[querySSOSession] onDone token:" + str);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            if (Globals.m7380c2()) {
                intent.setData(Uri.parse("https://u-demo.cyberlink.com/user/meeting/upcoming?_a=" + str));
            } else {
                intent.setData(Uri.parse("https://u.cyberlink.com/user/meeting/upcoming?_a=" + str));
            }
            C2557c.this.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A */
    public /* synthetic */ void m13142A(View view) {
        startActivity(new Intent(getContext(), (Class<?>) MyWebinarActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m13143B(View view) {
        Intent intent = new Intent(getContext(), (Class<?>) ScheduleSendActivity.class);
        Bundle bundle = new Bundle();
        ScheduleSendActivity.SendType sendType = ScheduleSendActivity.SendType.BROADCAST;
        bundle.putString(sendType.name(), sendType.name());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m13144C() {
        if (this.f11598k == null) {
            return;
        }
        if (!m13160u()) {
            this.f11598k.setVisibility(8);
        } else {
            this.f11598k.setVisibility(0);
            this.f11598k.setOnClickListener(this.f11597j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m13154v(View view) {
        startActivity(new Intent(getContext(), (Class<?>) JoinCLMWActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m13155w(View view) {
        view.setSelected(false);
        C2925v.m14571O0(getActivity(), null, false, MimeTypes.BASE_TYPE_VIDEO, "meeting tab video meeting button");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x */
    public /* synthetic */ void m13156x(View view) {
        view.setSelected(false);
        C2925v.m14571O0(getActivity(), null, false, MimeTypes.BASE_TYPE_AUDIO, "meeting tab audio meeting button");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m13157y(View view) {
        startActivity(new Intent(getContext(), (Class<?>) ScheduleMeetingActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m13158z(View view) {
        C1260a.m5683u(Globals.m7388i0().m7506X()).m15439e(new a());
    }

    /* renamed from: D */
    public void m13159D() {
        C6385v.m24527e(new Runnable() { // from class: g3.q0
            @Override // java.lang.Runnable
            public final void run() {
                this.f16990b.m13144C();
            }
        });
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: g */
    public int mo12959g() {
        return R.layout.fragment_clmw_function_list;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        super.onCreate(bundle);
        stopWatch.stop();
        Log.v(f11590l, "onCreate exit " + stopWatch.getTime() + " ms");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        super.onResume();
        m13159D();
        stopWatch.stop();
        Log.v(f11590l, "onResume exit " + stopWatch.getTime() + " ms");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        view.findViewById(R.id.joinMeetingsOrWebinars).setOnClickListener(this.f11591d);
        view.findViewById(R.id.startVideoMeetings).setOnClickListener(this.f11592e);
        view.findViewById(R.id.startVoiceMeetings).setOnClickListener(this.f11593f);
        view.findViewById(R.id.scheduleMeeting).setOnClickListener(this.f11594g);
        view.findViewById(R.id.upcomingMeeting).setOnClickListener(this.f11595h);
        view.findViewById(R.id.recordedWebinars).setOnClickListener(this.f11596i);
        this.f11598k = view.findViewById(R.id.broadcast);
        if (m13160u()) {
            this.f11598k.setVisibility(0);
            this.f11598k.setOnClickListener(this.f11597j);
        }
        AbstractC2555a.a aVar = this.f11504b;
        if (aVar != null) {
            aVar.mo12937q0(null, PageType.Meetings_And_Webinars);
        }
        stopWatch.stop();
        Log.v(f11590l, "onCreateView exit " + stopWatch.getTime() + " ms");
    }

    /* renamed from: u */
    public final boolean m13160u() {
        for (String str : Globals.m7388i0().m7455M0().split(",")) {
            if ("BROADCASTER".equals(str)) {
                return true;
            }
        }
        return false;
    }
}
