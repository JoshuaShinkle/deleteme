package com.cyberlink.you.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.ULogUtility;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class ScrollTextViewActivity extends BaseActivity {

    /* renamed from: c */
    public FriendsClient f8748c;

    /* renamed from: d */
    public TextView f8749d;

    /* renamed from: e */
    public TextView f8750e;

    /* renamed from: f */
    public String f8751f = "==== XMPP Server ====";

    /* renamed from: g */
    public String f8752g = "==== U Server ====";

    /* renamed from: h */
    public View.OnClickListener f8753h = new ViewOnClickListenerC1675a();

    /* renamed from: i */
    public View.OnClickListener f8754i = new ViewOnClickListenerC1676b();

    /* renamed from: j */
    public View.OnClickListener f8755j = new ViewOnClickListenerC1677c();

    /* renamed from: k */
    public XMPPManager.InterfaceC2870u f8756k = new C1678d();

    public enum ServerType {
        UServer,
        XMPPServer
    }

    /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$a */
    public class ViewOnClickListenerC1675a implements View.OnClickListener {
        public ViewOnClickListenerC1675a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScrollTextViewActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$b */
    public class ViewOnClickListenerC1676b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$b$a */
        public class a extends AsyncTask<Void, Void, Void> {
            public a() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                Thread.currentThread().setName("ScrollTextViewActivity.pingUServer AsyncTask");
                ScrollTextViewActivity.this.m9671r();
                return null;
            }
        }

        public ViewOnClickListenerC1676b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new a().executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$c */
    public class ViewOnClickListenerC1677c implements View.OnClickListener {
        public ViewOnClickListenerC1677c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScrollTextViewActivity.this.m9672s();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$d */
    public class C1678d implements XMPPManager.InterfaceC2870u {
        public C1678d() {
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2870u
        /* renamed from: a */
        public void mo9677a(String str) {
            ScrollTextViewActivity scrollTextViewActivity = ScrollTextViewActivity.this;
            scrollTextViewActivity.m9673u(scrollTextViewActivity.f8751f, str, ServerType.XMPPServer);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$e */
    public class C1679e implements XMPPManager.InterfaceC2873x {

        /* renamed from: a */
        public final /* synthetic */ AbstractC5586IQ f8766a;

        public C1679e(AbstractC5586IQ abstractC5586IQ) {
            this.f8766a = abstractC5586IQ;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
            ULogUtility.m16660C("Ping fail by sendPack eception:\nSend packet error.", "Send", ULogUtility.PingType.XmppServer);
            ScrollTextViewActivity scrollTextViewActivity = ScrollTextViewActivity.this;
            scrollTextViewActivity.m9673u(scrollTextViewActivity.f8751f, "Ping fail by sendPack eception:\nSend packet error.", ServerType.XMPPServer);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Ping " + FriendsClient.m15642G("chat", "xmpp.server") + "\n");
            StringBuilder sb = new StringBuilder();
            sb.append("messageId: ");
            sb.append(this.f8766a.m22161k());
            stringBuffer.append(sb.toString());
            ULogUtility.m16660C(stringBuffer.toString(), "Send", ULogUtility.PingType.XmppServer);
            ScrollTextViewActivity scrollTextViewActivity = ScrollTextViewActivity.this;
            scrollTextViewActivity.m9673u(scrollTextViewActivity.f8751f, stringBuffer.toString(), ServerType.XMPPServer);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$f */
    public class RunnableC1680f implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ ServerType f8768b;

        /* renamed from: c */
        public final /* synthetic */ String f8769c;

        /* renamed from: d */
        public final /* synthetic */ String f8770d;

        public RunnableC1680f(ServerType serverType, String str, String str2) {
            this.f8768b = serverType;
            this.f8769c = str;
            this.f8770d = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i9 = C1681g.f8772a[this.f8768b.ordinal()];
            if (i9 == 1) {
                ScrollTextViewActivity.this.f8749d.setText(this.f8769c + "\n" + this.f8770d + "\n" + ScrollTextViewActivity.this.f8749d.getText().toString());
                return;
            }
            if (i9 != 2) {
                return;
            }
            ScrollTextViewActivity.this.f8750e.setText(this.f8769c + "\n" + this.f8770d + "\n" + ScrollTextViewActivity.this.f8750e.getText().toString());
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScrollTextViewActivity$g */
    public static /* synthetic */ class C1681g {

        /* renamed from: a */
        public static final /* synthetic */ int[] f8772a;

        static {
            int[] iArr = new int[ServerType.values().length];
            f8772a = iArr;
            try {
                iArr[ServerType.UServer.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8772a[ServerType.XMPPServer.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scroll_text_view);
        findViewById(R.id.ScrollTextViewBackBtn).setOnClickListener(this.f8753h);
        findViewById(R.id.ScrollTextViewPingHttpBtn).setOnClickListener(this.f8754i);
        findViewById(R.id.ScrollTextViewPingXmppBtn).setOnClickListener(this.f8755j);
        this.f8749d = (TextView) findViewById(R.id.ScrollTextViewHttpText);
        this.f8750e = (TextView) findViewById(R.id.ScrollTextViewXmppText);
        this.f8748c = new FriendsClient();
        XMPPManager.m14184g0().m14246h1(this.f8756k);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        XMPPManager.m14184g0().m14246h1(null);
        super.onDestroy();
    }

    /* renamed from: q */
    public Activity m9670q() {
        return this;
    }

    /* renamed from: r */
    public final void m9671r() {
        String str = FriendsClient.f13672d;
        String str2 = "Ping " + str;
        ULogUtility.PingType pingType = ULogUtility.PingType.UServer;
        ULogUtility.m16660C(str2, "Send", pingType);
        String str3 = this.f8752g;
        ServerType serverType = ServerType.UServer;
        m9673u(str3, str2, serverType);
        StringBuffer stringBuffer = new StringBuffer();
        String strM15705L0 = this.f8748c.m15705L0(m9670q());
        if (strM15705L0 == null || !strM15705L0.equals("200")) {
            stringBuffer.append("No Response or status code is not 200 from " + str);
            ULogUtility.m16660C(stringBuffer.toString(), "Receive", pingType);
            m9673u(this.f8752g, stringBuffer.toString(), serverType);
            return;
        }
        stringBuffer.append("Response from " + str);
        ULogUtility.m16660C(stringBuffer.toString(), "Receive", pingType);
        m9673u(this.f8752g, stringBuffer.toString(), serverType);
    }

    /* renamed from: s */
    public final void m9672s() {
        AbstractC5586IQ abstractC5586IQ = new AbstractC5586IQ() { // from class: com.cyberlink.you.activity.ScrollTextViewActivity.5
            @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
            /* renamed from: G, reason: merged with bridge method [inline-methods] */
            public String mo9675y() {
                return "<ping xmlns='urn:xmpp:ping'/>";
            }
        };
        String strM15642G = FriendsClient.m15642G("chat", "xmpp.domain");
        abstractC5586IQ.m22165r(XMPPManager.m14184g0().m14239d0());
        abstractC5586IQ.m22167t(strM15642G);
        C2925v.m14547C0(abstractC5586IQ, new C1679e(abstractC5586IQ));
    }

    /* renamed from: u */
    public final void m9673u(String str, String str2, ServerType serverType) {
        synchronized (this) {
            m9670q().runOnUiThread(new RunnableC1680f(serverType, str, str2));
        }
    }
}
