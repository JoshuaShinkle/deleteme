package com.cyberlink.you.chat;

import android.util.Pair;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.Presence;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p095i3.C5049b;
import p116k4.C5154j;
import p116k4.C5172p;
import p201t3.C6301o;
import p209u2.C6383t;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class XMPPArchiveHelper {

    /* renamed from: c */
    public static LinkedBlockingQueue<String> f12455c = null;

    /* renamed from: d */
    public static boolean f12456d = false;

    /* renamed from: g */
    public static C2845b f12459g;

    /* renamed from: a */
    public static final Object f12453a = new Object();

    /* renamed from: b */
    public static final Object f12454b = new Object();

    /* renamed from: e */
    public static List<InterfaceC2846c> f12457e = new ArrayList();

    /* renamed from: f */
    public static final AtomicInteger f12458f = new AtomicInteger(0);

    /* renamed from: com.cyberlink.you.chat.XMPPArchiveHelper$a */
    public class C2844a implements XMPPManager.InterfaceC2873x {

        /* renamed from: a */
        public final /* synthetic */ C2845b f12461a;

        /* renamed from: b */
        public final /* synthetic */ boolean f12462b;

        public C2844a(C2845b c2845b, boolean z8) {
            this.f12461a = c2845b;
            this.f12462b = z8;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
            ULogUtility.m16676l("XMPPArchiveHelper", "Query message IQ sent but failed");
            XMPPArchiveHelper.f12458f.set(0);
            C2845b unused = XMPPArchiveHelper.f12459g = null;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            ULogUtility.m16670f("XMPPArchiveHelper", "Query message IQ sent: " + this.f12461a.f12464b.m22161k());
            if (XMPPArchiveHelper.f12459g == null) {
                ULogUtility.m16684t("XMPPArchiveHelper", " > IQ was gone");
                return;
            }
            Object obj = XMPPArchiveHelper.f12459g.f12468f;
            if (this.f12462b) {
                XMPPArchiveHelper.m14146w();
            }
            ULogUtility.m16683s("XMPPArchiveHelper", " > token: " + obj);
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPArchiveHelper$c */
    public interface InterfaceC2846c {
        /* renamed from: a */
        default void mo11967a() {
        }

        /* renamed from: b */
        default void mo11968b() {
        }

        /* renamed from: c */
        default void mo11969c() {
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPArchiveHelper$d */
    public interface InterfaceC2847d {
        /* renamed from: a */
        void mo14151a(String str, Date date);
    }

    /* renamed from: A */
    public static boolean m14119A(String str, Date date, InterfaceC2847d interfaceC2847d) {
        return m14120B(str, date, 0, interfaceC2847d);
    }

    /* renamed from: B */
    public static boolean m14120B(String str, Date date, int i9, InterfaceC2847d interfaceC2847d) {
        if (date != null) {
            long jConvert = TimeUnit.DAYS.convert(date.getTime() - new Date().getTime(), TimeUnit.MILLISECONDS);
            if (!Globals.m7388i0().m7469P1() && Math.abs(jConvert) > 15) {
                ULogUtility.m16676l("XMPPArchiveHelper", "Does not query history msg due to is not messenger pro");
                XMPPManager.m14184g0().m14255m0();
                if (interfaceC2847d != null) {
                    interfaceC2847d.mo14151a(str, date);
                }
                return false;
            }
        }
        String str2 = "<query xmlns='urn:xmpp:mam:3'>";
        if (!C6383t.m24517f(str)) {
            str2 = "<query xmlns='urn:xmpp:mam:3'><with>" + str + "</with>";
        }
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            str2 = str2 + "<end>" + simpleDateFormat.format(date) + "</end>";
        }
        String str3 = str2 + "<set xmlns='http://jabber.org/protocol/rsm'><max>" + (date == null ? 100 : 101) + "</max><before/></set></query>";
        C2845b c2845b = new C2845b(null);
        c2845b.f12464b = m14134k(str3);
        c2845b.f12463a = 2;
        if (date != null) {
            c2845b.f12468f = date;
        } else {
            c2845b.f12468f = new Date(FriendsClient.m15646K());
        }
        c2845b.f12470h = str;
        c2845b.f12471i = interfaceC2847d;
        c2845b.f12469g = "v1";
        c2845b.f12467e = i9;
        return m14123E(c2845b, false);
    }

    /* renamed from: C */
    public static void m14121C() {
        ULogUtility.m16683s("XMPPArchiveHelper", "recentGroups");
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(Globals.m7372O());
        if (userInfoM16497V0 == null || !userInfoM16497V0.m15773d()) {
            C6385v.m24526d(new Runnable() { // from class: com.cyberlink.you.chat.p0
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    XMPPArchiveHelper.m14143t();
                }
            });
        } else {
            ULogUtility.m16683s("XMPPArchiveHelper", " > skip for security");
        }
    }

    /* renamed from: D */
    public static void m14122D(InterfaceC2846c interfaceC2846c) {
        synchronized (f12454b) {
            f12457e.remove(interfaceC2846c);
        }
    }

    /* renamed from: E */
    public static boolean m14123E(C2845b c2845b, boolean z8) {
        if (f12459g != null) {
            return false;
        }
        ULogUtility.m16670f("XMPPArchiveHelper", "sendIQ before IQ_LOCK");
        synchronized (f12453a) {
            ULogUtility.m16670f("XMPPArchiveHelper", "sendIQ after IQ_LOCK");
            f12458f.set(c2845b.f12463a);
            f12459g = c2845b;
            C2925v.m14547C0(c2845b.f12464b, new C2844a(c2845b, z8));
        }
        return true;
    }

    /* renamed from: i */
    public static void m14132i(InterfaceC2846c interfaceC2846c) {
        synchronized (f12454b) {
            f12457e.add(interfaceC2846c);
        }
        if (f12456d) {
            interfaceC2846c.mo11967a();
        }
    }

    /* renamed from: j */
    public static void m14133j() {
        synchronized (f12453a) {
            ULogUtility.m16683s("XMPPArchiveHelper", "clearIQ()");
            if (f12459g != null) {
                ULogUtility.m16670f("XMPPArchiveHelper", " > " + f12459g.f12470h);
            }
            f12458f.set(0);
            f12459g = null;
        }
    }

    /* renamed from: k */
    public static AbstractC5586IQ m14134k(final String str) {
        AbstractC5586IQ abstractC5586IQ = new AbstractC5586IQ() { // from class: com.cyberlink.you.chat.XMPPArchiveHelper.2
            @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
            /* renamed from: G, reason: merged with bridge method [inline-methods] */
            public String mo9675y() {
                return str;
            }
        };
        abstractC5586IQ.m22166s(C2925v.m14542A());
        abstractC5586IQ.m22070F(AbstractC5586IQ.a.f19231b);
        return abstractC5586IQ;
    }

    /* renamed from: l */
    public static Date m14135l(String str, Date date) {
        if (str == null) {
            return date;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException unused) {
            return date;
        }
    }

    /* renamed from: m */
    public static boolean m14136m(AbstractC5586IQ abstractC5586IQ) {
        if (!C2925v.m14608m0(abstractC5586IQ.m22161k())) {
            return false;
        }
        ULogUtility.m16670f("XMPPArchiveHelper", "Handle result IQ: " + abstractC5586IQ.m22161k());
        synchronized (f12453a) {
            C2845b c2845b = f12459g;
            if (c2845b == null) {
                ULogUtility.m16684t("XMPPArchiveHelper", " > IQ was gone");
                return false;
            }
            if (!C6383t.m24512a(abstractC5586IQ.m22161k(), c2845b.f12464b.m22161k())) {
                ULogUtility.m16684t("XMPPArchiveHelper", " > Incorrect IQ id, expected: " + c2845b.f12464b.m22161k());
                return false;
            }
            f12458f.set(0);
            f12459g = null;
            if (!c2845b.f12469g.equals("v1")) {
                if (!c2845b.f12469g.equals("v2")) {
                    return false;
                }
                ULogUtility.m16683s("XMPPArchiveHelper", " > result cv: " + abstractC5586IQ.m22071x() + ", empty: " + abstractC5586IQ.m22072z());
                if (abstractC5586IQ.m22071x() != null && abstractC5586IQ.m22072z() != null) {
                    if (c2845b.f12463a == 1) {
                        if (abstractC5586IQ.m22072z().equals("0")) {
                            ULogUtility.m16670f("XMPPArchiveHelper", " > query next archive cv: " + abstractC5586IQ.m22071x());
                            XMPPManager.m14184g0().m14255m0();
                            m14145v();
                            m14148y(abstractC5586IQ.m22071x(), "v2", false);
                        } else if (abstractC5586IQ.m22072z().equals("1")) {
                            ULogUtility.m16680p("XMPPArchiveHelper", " > All archive query end cv: " + abstractC5586IQ.m22071x());
                            f12456d = true;
                            XMPPManager.m14184g0().m14255m0();
                            C2876a.m14293b(XMPPManager.HandleType.XMPP);
                            m14145v();
                            m14144u();
                            m14149z();
                            XMPPManager.m14184g0().m14252k1(Presence.Type.available);
                        }
                        Globals.m7388i0().m7410C2(abstractC5586IQ.m22071x(), "Archive");
                    }
                    return true;
                }
                if (C6383t.m24512a(c2845b.f12464b.m22161k(), abstractC5586IQ.m22161k())) {
                    ULogUtility.m16684t("XMPPArchiveHelper", " > Could got IQ error");
                    m14145v();
                    m14144u();
                }
                return false;
            }
            ULogUtility.m16683s("XMPPArchiveHelper", " > result#: " + c2845b.f12465c + ", visible#: " + c2845b.f12466d.get() + ", last time: " + c2845b.f12468f);
            int i9 = c2845b.f12463a;
            if (i9 == 1) {
                Object obj = c2845b.f12468f;
                if (c2845b.f12465c >= 100) {
                    m14145v();
                    m14148y(obj, "v1", false);
                } else {
                    ULogUtility.m16680p("XMPPArchiveHelper", " > All archive query end: " + c2845b.f12468f);
                    f12456d = true;
                    m14145v();
                    XMPPManager.m14184g0().m14252k1(Presence.Type.available);
                }
            } else if (i9 == 2) {
                Date dateM14135l = m14135l(abstractC5586IQ.m22065A(), (Date) c2845b.f12468f);
                int i10 = c2845b.f12466d.get() + c2845b.f12467e;
                if (i10 >= 40 || c2845b.f12465c < 100) {
                    ULogUtility.m16680p("XMPPArchiveHelper", " > All history query end: " + dateM14135l);
                    XMPPManager.m14184g0().m14255m0();
                    InterfaceC2847d interfaceC2847d = c2845b.f12471i;
                    if (interfaceC2847d != null) {
                        interfaceC2847d.mo14151a(c2845b.f12470h, dateM14135l);
                    }
                } else {
                    ULogUtility.m16670f("XMPPArchiveHelper", " > query next history: " + dateM14135l);
                    m14120B(c2845b.f12470h, dateM14135l, i10, c2845b.f12471i);
                }
            }
            return true;
        }
    }

    /* renamed from: n */
    public static void m14137n(C2904l c2904l) {
        if (c2904l.m14399N()) {
            C2845b c2845b = f12459g;
            if (c2845b == null) {
                ULogUtility.m16684t("XMPPArchiveHelper", "IQ was gone");
                return;
            }
            if ("v1".equals(c2845b.f12469g)) {
                C2845b c2845b2 = f12459g;
                if (c2845b2.f12463a != 2) {
                    c2845b2.f12465c++;
                    if (c2904l.m14422j().after((Date) f12459g.f12468f)) {
                        f12459g.f12468f = c2904l.m14422j();
                        return;
                    }
                    return;
                }
                c2845b2.f12465c++;
                if (c2904l.m14394I() && c2904l.m14428m() != null && c2904l.m14388C() != null) {
                    f12459g.f12466d.incrementAndGet();
                }
                if (c2904l.m14422j().before((Date) f12459g.f12468f)) {
                    f12459g.f12468f = c2904l.m14422j();
                }
            }
        }
    }

    /* renamed from: o */
    public static boolean m14138o() {
        return f12458f.get() == 2;
    }

    /* renamed from: p */
    public static boolean m14139p() {
        return f12458f.get() == 1;
    }

    /* renamed from: q */
    public static /* synthetic */ void m14140q() {
        synchronized (f12454b) {
            Iterator<InterfaceC2846c> it = f12457e.iterator();
            while (it.hasNext()) {
                it.next().mo11968b();
            }
        }
    }

    /* renamed from: r */
    public static /* synthetic */ void m14141r() {
        synchronized (f12454b) {
            Iterator<InterfaceC2846c> it = f12457e.iterator();
            while (it.hasNext()) {
                it.next().mo11967a();
            }
        }
    }

    /* renamed from: s */
    public static /* synthetic */ void m14142s(String str, Date date) {
        synchronized (f12453a) {
            f12455c.remove(str);
            m14149z();
        }
    }

    /* renamed from: t */
    public static /* synthetic */ void m14143t() throws JSONException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("maxSize", "20"));
        Pair<String, String> pairM15676n = FriendsClient.m15676n("group", "listRecent", arrayList);
        if (!"200".equals(pairM15676n.first)) {
            ULogUtility.m16676l("XMPPArchiveHelper", " > recentGroups failed: " + ((String) pairM15676n.first));
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject((String) pairM15676n.second).getJSONArray("results");
            ULogUtility.m16683s("XMPPArchiveHelper", " > recentGroups found: " + jSONArray.length());
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                Group groupM20186h = C5172p.m20186h(jSONArray.getJSONObject(i9));
                if (groupM20186h != null && !groupM20186h.f13711J && !arrayList2.contains(groupM20186h.f13723j)) {
                    ULogUtility.m16683s("XMPPArchiveHelper", " > candidate: " + groupM20186h.f13717d);
                    arrayList2.add(groupM20186h.f13723j);
                    if (arrayList2.size() >= 10) {
                        break;
                    }
                }
            }
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
        if (arrayList2.isEmpty()) {
            ULogUtility.m16670f("XMPPArchiveHelper", " > nothing we can do");
            return;
        }
        synchronized (f12453a) {
            if (f12455c == null) {
                f12455c = new LinkedBlockingQueue<>();
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!f12455c.contains(str)) {
                    f12455c.add(str);
                }
            }
            m14149z();
        }
    }

    /* renamed from: u */
    public static void m14144u() {
        XMPPManager.m14184g0().m14245h0().execute(new Runnable() { // from class: com.cyberlink.you.chat.r0
            @Override // java.lang.Runnable
            public final void run() {
                XMPPArchiveHelper.m14140q();
            }
        });
    }

    /* renamed from: v */
    public static void m14145v() {
        XMPPManager.m14184g0().m14245h0().execute(new Runnable() { // from class: com.cyberlink.you.chat.o0
            @Override // java.lang.Runnable
            public final void run() {
                XMPPArchiveHelper.m14141r();
            }
        });
    }

    /* renamed from: w */
    public static void m14146w() {
        synchronized (f12454b) {
            Iterator<InterfaceC2846c> it = f12457e.iterator();
            while (it.hasNext()) {
                it.next().mo11969c();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: x */
    public static void m14147x() {
        String strM7454M;
        m14133j();
        C5049b.m19737p();
        String strM7483S0 = Globals.m7388i0().m7483S0();
        if (strM7483S0.equals("v1")) {
            long jM7515Z = Globals.m7388i0().m7515Z();
            Date dateM7502W0 = Globals.m7388i0().m7502W0();
            if (jM7515Z != 0) {
                Date date = new Date(jM7515Z);
                boolean zAfter = date.after(dateM7502W0);
                strM7454M = dateM7502W0;
                if (zAfter) {
                    strM7454M = date;
                }
            } else {
                XMPPManager.m14184g0().m14252k1(Presence.Type.available);
                strM7454M = dateM7502W0;
            }
        } else {
            strM7454M = Globals.m7388i0().m7454M();
        }
        m14148y(strM7454M, strM7483S0, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.cyberlink.you.chat.XMPPArchiveHelper$a] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* renamed from: y */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m14148y(Object obj, String str, boolean z8) {
        C2845b c2845b;
        C2845b c2845b2 = 0;
        c2845b2 = 0;
        if (!str.equals("v1") || !(obj instanceof Date)) {
            if (str.equals("v2") && (obj instanceof String)) {
                String str2 = (String) obj;
                c2845b = new C2845b(c2845b2);
                c2845b.f12464b = m14134k("<query xmlns='urn:xmpp:mam:0'><cv>" + str2 + "</cv><set xmlns='http://jabber.org/protocol/rsm'><max>100</max></set></query>");
                c2845b.f12463a = 1;
                c2845b.f12468f = str2;
                c2845b.f12469g = "v2";
            }
            if (c2845b2 == 0) {
                m14123E(c2845b2, z8);
                return;
            }
            return;
        }
        Date date = (Date) obj;
        String str3 = "<query xmlns='urn:xmpp:mam:3'><start>" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).format(date) + "</start><set xmlns='http://jabber.org/protocol/rsm'><max>100</max></set></query>";
        c2845b = new C2845b(c2845b2);
        c2845b.f12464b = m14134k(str3);
        c2845b.f12463a = 1;
        c2845b.f12468f = date;
        c2845b.f12469g = "v1";
        c2845b2 = c2845b;
        if (c2845b2 == 0) {
        }
    }

    /* renamed from: z */
    public static void m14149z() {
        synchronized (f12453a) {
            LinkedBlockingQueue<String> linkedBlockingQueue = f12455c;
            if (linkedBlockingQueue != null && !linkedBlockingQueue.isEmpty()) {
                if (f12459g != null) {
                    ULogUtility.m16670f("XMPPArchiveHelper", "There is a IQ executing now. Wait for its result then try again");
                    return;
                }
                String strPeek = f12455c.peek();
                ULogUtility.m16683s("XMPPArchiveHelper", " > recentGroups: " + strPeek);
                m14119A(strPeek, null, new InterfaceC2847d() { // from class: com.cyberlink.you.chat.q0
                    @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2847d
                    /* renamed from: a */
                    public final void mo14151a(String str, Date date) {
                        XMPPArchiveHelper.m14142s(str, date);
                    }
                });
                return;
            }
            if (f12455c == null) {
                ULogUtility.m16670f("XMPPArchiveHelper", " > mRecentGroupJids is null");
            } else {
                ULogUtility.m16670f("XMPPArchiveHelper", " > mRecentGroupJids is empty");
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPArchiveHelper$b */
    public static class C2845b {

        /* renamed from: a */
        public int f12463a;

        /* renamed from: b */
        public AbstractC5586IQ f12464b;

        /* renamed from: c */
        public int f12465c;

        /* renamed from: d */
        public final AtomicInteger f12466d;

        /* renamed from: e */
        public int f12467e;

        /* renamed from: f */
        public Object f12468f;

        /* renamed from: g */
        public String f12469g;

        /* renamed from: h */
        public String f12470h;

        /* renamed from: i */
        public InterfaceC2847d f12471i;

        public C2845b() {
            this.f12466d = new AtomicInteger();
            this.f12470h = null;
        }

        public /* synthetic */ C2845b(C2844a c2844a) {
            this();
        }
    }
}
