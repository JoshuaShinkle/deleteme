package com.cyberlink.you.feedback;

import android.app.Activity;
import android.util.Log;
import com.cyberlink.you.feedback.C3032e;
import com.cyberlink.you.feedback.NetworkManager;
import com.google.android.gms.common.Scopes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import p120k8.InterfaceC5203a;

/* loaded from: classes.dex */
public class NetworkFeedback {

    public static class FeedbackConfig implements Serializable {
        private static final long serialVersionUID = 1;
        public String apiUri;
        public String appversion;
        public ArrayList<String> attachmentPath;
        public String extrainfo;
        public String hwid;
        public String phoneid;
        public String product;

        /* renamed from: sr */
        public String f13354sr;
        public String umaid;
        public String version;
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkFeedback$a */
    public class C3010a extends PromisedTask<String, Float, C3013d> {
        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3013d mo5659d(String str) {
            return (C3013d) Model.parseFromJSON(C3013d.class, str);
        }
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkFeedback$b */
    public class C3011b extends PromisedTask<NetworkManager, Void, C3031d> {

        /* renamed from: j */
        public final /* synthetic */ String f13355j;

        /* renamed from: k */
        public final /* synthetic */ C3012c f13356k;

        public C3011b(String str, C3012c c3012c) {
            this.f13355j = str;
            this.f13356k = c3012c;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public C3031d mo5659d(NetworkManager networkManager) {
            String str = this.f13355j;
            if (str == null) {
                m15446n(NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m15410a(), null);
                return null;
            }
            if (this.f13356k == null) {
                m15446n(NetworkManager.NetworkErrorCode.E_BAD_REQUEST.m15410a(), null);
                return null;
            }
            C3031d c3031d = new C3031d(str);
            c3031d.m15500d("product", this.f13356k.f13357a);
            c3031d.m15500d("version", this.f13356k.f13358b);
            c3031d.m15500d("versiontype", this.f13356k.f13359c);
            c3031d.m15500d("timezone", this.f13356k.f13360d);
            c3031d.m15500d("platform", this.f13356k.f13361e);
            c3031d.m15500d("osversion", this.f13356k.f13362f);
            c3031d.m15500d("sr", this.f13356k.f13363g);
            c3031d.m15500d("lang", this.f13356k.f13364h);
            c3031d.m15500d("model", this.f13356k.f13365i);
            c3031d.m15500d("vendor", this.f13356k.f13366j);
            c3031d.m15500d("resolution", this.f13356k.f13367k);
            c3031d.m15500d("hwid", this.f13356k.f13368l);
            c3031d.m15500d("phoneid", this.f13356k.f13369m);
            c3031d.m15500d("appversion", this.f13356k.f13370n);
            c3031d.m15500d(Scopes.EMAIL, this.f13356k.f13371o);
            c3031d.m15500d("question", this.f13356k.f13372p);
            c3031d.m15500d("extrainfo", this.f13356k.f13373q);
            ArrayList<C3032e.a> arrayList = this.f13356k.f13374r;
            if (arrayList != null) {
                Iterator<C3032e.a> it = arrayList.iterator();
                int i9 = 0;
                while (it.hasNext()) {
                    C3032e.a next = it.next();
                    Log.d("NetworkFeedback", "file.fileBean = " + next.f13496e + "; file.mimeType = " + next.f13494c + "; file.name = " + next.f13492a);
                    StringBuilder sb = new StringBuilder();
                    sb.append("attachment");
                    i9++;
                    sb.append(i9);
                    c3031d.m15501e(sb.toString(), next.f13496e, next.f13494c, next.f13492a);
                }
            }
            return c3031d;
        }
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkFeedback$c */
    public static class C3012c {

        /* renamed from: a */
        public String f13357a;

        /* renamed from: b */
        public String f13358b;

        /* renamed from: c */
        public String f13359c;

        /* renamed from: d */
        public String f13360d;

        /* renamed from: e */
        public String f13361e;

        /* renamed from: f */
        public String f13362f;

        /* renamed from: g */
        public String f13363g;

        /* renamed from: h */
        public String f13364h;

        /* renamed from: i */
        public String f13365i;

        /* renamed from: j */
        public String f13366j;

        /* renamed from: k */
        public String f13367k;

        /* renamed from: l */
        public String f13368l;

        /* renamed from: m */
        public String f13369m;

        /* renamed from: n */
        public String f13370n;

        /* renamed from: o */
        public String f13371o;

        /* renamed from: p */
        public String f13372p;

        /* renamed from: q */
        public String f13373q;

        /* renamed from: r */
        public ArrayList<C3032e.a> f13374r;

        public C3012c(FeedbackConfig feedbackConfig) throws Throwable {
            if (feedbackConfig == null) {
                return;
            }
            this.f13357a = feedbackConfig.product;
            this.f13358b = feedbackConfig.version;
            this.f13363g = feedbackConfig.f13354sr;
            this.f13368l = feedbackConfig.hwid;
            this.f13369m = feedbackConfig.phoneid;
            this.f13370n = feedbackConfig.appversion;
            this.f13373q = feedbackConfig.extrainfo;
            if (feedbackConfig.attachmentPath != null) {
                this.f13374r = new ArrayList<>();
                Iterator<String> it = feedbackConfig.attachmentPath.iterator();
                while (it.hasNext()) {
                    this.f13374r.add(C3032e.m15508a(it.next(), null));
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.feedback.NetworkFeedback$d */
    public static class C3013d extends Model {

        @InterfaceC5203a
        public String status;
    }

    /* renamed from: a */
    public static PromisedTask<?, ?, C3013d> m15401a(String str, C3012c c3012c, Activity activity) {
        return NetworkManager.m15407d(NetworkManager.f13378d).m15447o(new C3011b(str, c3012c)).m15447o(NetworkManager.m15408e(activity)).m15447o(new C3010a());
    }
}
