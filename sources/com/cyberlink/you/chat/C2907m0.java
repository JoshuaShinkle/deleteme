package com.cyberlink.you.chat;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.chat.XMPPArchiveHelper;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import p116k4.C5154j;
import p116k4.C5170o0;
import p136m3.C5321e;
import p193s4.C6265c;
import p209u2.C6383t;
import p209u2.C6385v;
import p209u2.C6389z;
import p209u2.ThreadFactoryC6373j;

/* renamed from: com.cyberlink.you.chat.m0 */
/* loaded from: classes.dex */
public class C2907m0 {

    /* renamed from: r */
    public static final ThreadPoolExecutor f12750r = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("Unread.SingleThread"));

    /* renamed from: a */
    public final Map<String, j> f12751a;

    /* renamed from: b */
    public final ArrayList<Long> f12752b;

    /* renamed from: c */
    public final Map<Long, List<Long>> f12753c;

    /* renamed from: d */
    public final Map<Long, Integer> f12754d;

    /* renamed from: e */
    public final Map<Long, Integer> f12755e;

    /* renamed from: f */
    public final List<String> f12756f;

    /* renamed from: g */
    public final Map<String, Boolean> f12757g;

    /* renamed from: h */
    public boolean f12758h;

    /* renamed from: i */
    public boolean f12759i;

    /* renamed from: j */
    public final Object f12760j;

    /* renamed from: k */
    public AsyncTask<Void, Void, Object[]> f12761k;

    /* renamed from: l */
    public AsyncTask<Void, Void, Object[]> f12762l;

    /* renamed from: m */
    public XMPPManager.AbstractC2868s f12763m;

    /* renamed from: n */
    public C5321e.m f12764n;

    /* renamed from: o */
    public XMPPArchiveHelper.InterfaceC2846c f12765o;

    /* renamed from: p */
    public final C6389z<g> f12766p;

    /* renamed from: q */
    public final C6389z<h> f12767q;

    /* renamed from: com.cyberlink.you.chat.m0$a */
    public class a extends XMPPManager.AbstractC2868s {
        public a(boolean z8) {
            super(z8);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: e */
        public String mo8240e(C2904l c2904l) {
            DeliveryReceipt deliveryReceipt;
            MessageObj messageObjM15179r;
            Group groupM15079p;
            if (!c2904l.m14394I() || C6383t.m24512a(c2904l.m14418h(), Globals.m7388i0().m7587o0()) || String.valueOf(Globals.m7388i0().m7568k1()).equals(c2904l.m14430n()) || !c2904l.m14422j().after(Globals.m7388i0().m7502W0())) {
                if (!c2904l.m14389D().equals(MessageObj.MessageType.DeliveryReceipt) || (deliveryReceipt = (DeliveryReceipt) c2904l.m14426l("received", "urn:xmpp:receipts")) == null || !String.valueOf(Globals.m7388i0().m7568k1()).equals(c2904l.m14430n()) || (messageObjM15179r = C2950b0.m14916o().m15179r(deliveryReceipt.m22697d())) == null || (groupM15079p = C2950b0.m14912k().m15079p(messageObjM15179r.m14772j())) == null) {
                    return null;
                }
                C2907m0.this.m14498Z(groupM15079p.f13723j, groupM15079p.f13731r ? 0 : groupM15079p.f13734u);
                if (!c2904l.m14399N()) {
                    C2907m0.this.m14492T();
                }
                C2907m0.this.m14506i0(Globals.m7388i0());
            } else {
                if (c2904l.m14432o() == null || c2904l.m14389D() == MessageObj.MessageType.DeleteMedia) {
                    return null;
                }
                C2907m0.this.m14503e0(c2904l.m14418h(), true, c2904l.m14386A().getTime());
                if (!c2904l.m14399N()) {
                    C2907m0.this.m14492T();
                }
                if (C2907m0.this.f12759i) {
                    C2907m0.this.m14506i0(Globals.m7388i0());
                }
            }
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.chat.m0$b */
    public class b implements XMPPArchiveHelper.InterfaceC2846c {

        /* renamed from: com.cyberlink.you.chat.m0$b$a */
        public class a extends AsyncTask<Void, Void, Object[]> {
            public a() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Object[] doInBackground(Void... voidArr) {
                ULogUtility.m16662E("UnreadCountManager", "start second init task");
                return new Object[]{C2950b0.m14912k().m15075l(), C2950b0.m14912k().m15084u(), C2950b0.m14906e().m14981k()};
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Object[] objArr) {
                Map map = (Map) objArr[0];
                List<Group> list = (List) objArr[1];
                List<TopicObj> list2 = (List) objArr[2];
                ULogUtility.m16662E("UnreadCountManager", "second init update unread count from database start");
                if (map != null) {
                    for (Group group : map.keySet()) {
                        int i9 = group.f13734u;
                        if (i9 != 0) {
                            C2907m0 c2907m0 = C2907m0.this;
                            String str = group.f13723j;
                            if (group.f13731r) {
                                i9 = 0;
                            }
                            c2907m0.m14498Z(str, i9);
                        }
                        C2907m0.this.m14500b0(group.f13727n, group.f13708G);
                    }
                    C2907m0.this.m14506i0(Globals.m7388i0());
                }
                if (list != null) {
                    for (Group group2 : list) {
                        C2907m0.this.m14497Y(group2.f13723j, group2.f13714M);
                    }
                }
                if (list2 != null) {
                    for (TopicObj topicObj : list2) {
                        C2907m0.this.m14499a0(topicObj.m14849o(), topicObj.m14850p());
                        C2907m0.this.m14509s(topicObj.m14843h(), topicObj.m14849o());
                    }
                }
                C2907m0.this.m14492T();
                ULogUtility.m16662E("UnreadCountManager", "second init update unread count from database end");
                synchronized (C2907m0.this.f12760j) {
                    C2907m0.this.f12758h = true;
                }
                C2907m0.this.f12759i = true;
                C2907m0.this.m14491S();
            }
        }

        public b() {
        }

        @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2846c
        /* renamed from: a */
        public void mo11967a() {
        }

        @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2846c
        /* renamed from: b */
        public void mo11968b() {
            C2907m0.this.f12762l = new a();
            C2907m0.this.f12762l.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }

        @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2846c
        /* renamed from: c */
        public void mo11969c() {
            synchronized (C2907m0.this.f12760j) {
                C2907m0.this.f12758h = false;
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.m0$c */
    public class c extends AsyncTask<Void, Void, Object[]> {
        public c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Object[] doInBackground(Void... voidArr) {
            ULogUtility.m16662E("UnreadCountManager", "start first init task");
            return new Object[]{C2950b0.m14912k().m15075l(), C2950b0.m14912k().m15084u(), C2950b0.m14906e().m14981k()};
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Object[] objArr) {
            Map map = (Map) objArr[0];
            List<Group> list = (List) objArr[1];
            List<TopicObj> list2 = (List) objArr[2];
            ULogUtility.m16662E("UnreadCountManager", "first init unread count from database start");
            if (map != null) {
                for (Group group : map.keySet()) {
                    C2907m0.this.m14488M(group, ((Long) map.get(group)).longValue());
                    if (group.f13711J || group.f13712K) {
                        C2907m0.this.f12752b.add(Long.valueOf(group.f13727n));
                    }
                }
            }
            if (list != null) {
                for (Group group2 : list) {
                    C2907m0.this.m14497Y(group2.f13723j, group2.f13714M);
                }
            }
            if (list2 != null) {
                for (TopicObj topicObj : list2) {
                    C2907m0.this.m14499a0(topicObj.m14849o(), topicObj.m14850p());
                    C2907m0.this.m14509s(topicObj.m14843h(), topicObj.m14849o());
                }
            }
            ULogUtility.m16662E("UnreadCountManager", "first init unread count from database end");
            C2907m0.this.f12759i = true;
            C2907m0.this.m14491S();
        }
    }

    /* renamed from: com.cyberlink.you.chat.m0$d */
    public class d extends C6389z.a<g> {
        public d() {
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(g gVar) {
            gVar.mo118A();
        }
    }

    /* renamed from: com.cyberlink.you.chat.m0$e */
    public class e extends C6389z.a<h> {

        /* renamed from: a */
        public final /* synthetic */ boolean f12773a;

        public e(boolean z8) {
            this.f12773a = z8;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(h hVar) {
            hVar.mo119x(this.f12773a);
        }
    }

    /* renamed from: com.cyberlink.you.chat.m0$f */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C2907m0.this.m14492T();
        }

        public /* synthetic */ f(C2907m0 c2907m0, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.chat.m0$g */
    public interface g extends C6389z.b {
        /* renamed from: A */
        void mo118A();
    }

    /* renamed from: com.cyberlink.you.chat.m0$h */
    public interface h extends C6389z.b {
        /* renamed from: x */
        void mo119x(boolean z8);
    }

    /* renamed from: com.cyberlink.you.chat.m0$i */
    public static class i {

        /* renamed from: a */
        public static final C2907m0 f12776a = new C2907m0(null);
    }

    /* renamed from: com.cyberlink.you.chat.m0$j */
    public class j {

        /* renamed from: a */
        public int f12777a;

        /* renamed from: b */
        public List<Long> f12778b = new ArrayList();

        public j(int i9, long j9) {
            m14528e(i9, j9);
        }

        /* renamed from: e */
        public final void m14528e(int i9, long j9) {
            this.f12777a = i9;
            this.f12778b.add(Long.valueOf(j9));
        }

        /* renamed from: f */
        public final int m14529f() {
            return this.f12777a;
        }

        /* renamed from: g */
        public final void m14530g(int i9, long j9) {
            this.f12777a = i9;
            this.f12778b.remove(Long.valueOf(j9));
        }
    }

    public /* synthetic */ C2907m0(a aVar) {
        this();
    }

    /* renamed from: I */
    public static C2907m0 m14454I() {
        return i.f12776a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: P */
    public /* synthetic */ boolean m14455P(C2904l c2904l, Map map) throws NumberFormatException {
        String str;
        long j9;
        TopicObj topicObjM14984n;
        str = (String) map.get("eventType");
        str.hashCode();
        switch (str) {
            case "bulletin.topic.created":
            case "bulletin.post.created":
                try {
                } catch (NumberFormatException e9) {
                    C5154j.m20076j(e9);
                }
                if (Long.parseLong((String) map.get("actor")) == Globals.m7388i0().m7568k1().longValue() || (topicObjM14984n = C2950b0.m14906e().m14984n((j9 = Long.parseLong((String) map.get("topicId"))))) == null) {
                    return false;
                }
                if ("Poll".equals(topicObjM14984n.f13106u)) {
                    m14504f0(topicObjM14984n, true);
                } else {
                    boolean zEquals = "bulletin.topic.created".equals(str);
                    if (zEquals && topicObjM14984n.m14840e() < Globals.m7388i0().m7502W0().getTime()) {
                        return false;
                    }
                    if (!zEquals) {
                        TopicCommentObj topicCommentObjM14952o = C2950b0.m14905d().m14952o(Long.parseLong((String) map.get("postId")));
                        if (topicCommentObjM14952o == null) {
                            Log.w("UnreadCountManager", "Should have post in local database before notify me.");
                            return false;
                        }
                        if (topicCommentObjM14952o.m14030e() < Globals.m7388i0().m7502W0().getTime()) {
                            return false;
                        }
                    }
                    boolean z8 = topicObjM14984n.f13103r;
                    long j10 = Long.parseLong((String) map.get("groupId"));
                    m14508k0(j9, z8, zEquals, true);
                    m14479C(j10);
                    m14492T();
                    m14506i0(Globals.m7388i0());
                }
                return false;
            case "group.member.created":
            case "group.reminder.unset.event":
            case "group.member.deleted":
            case "group.member.leaved":
            case "group.member.created.v2":
            case "group.group.updated":
            case "group.reminder.set.event":
                String str2 = (String) map.get("groupId");
                Long lValueOf = Long.valueOf(str2);
                Group groupM15077n = C2950b0.m14912k().m15077n(str2);
                if (str.equals("group.reminder.set.event") || str.equals("group.reminder.unset.event")) {
                    if (groupM15077n != null) {
                        m14497Y(groupM15077n.f13723j, groupM15077n.f13714M);
                        m14492T();
                        m14506i0(Globals.m7388i0());
                    }
                } else if (groupM15077n == null || !(groupM15077n.f13711J || groupM15077n.f13712K)) {
                    this.f12752b.remove(lValueOf);
                } else if (!this.f12752b.contains(lValueOf)) {
                    this.f12752b.add(lValueOf);
                }
                return false;
            case "bulletin.topic.lastRead":
                m14492T();
                m14506i0(Globals.m7388i0());
                return false;
            default:
                return false;
        }
    }

    /* renamed from: Q */
    public static /* synthetic */ void m14456Q(int i9) {
        m14466h0(Globals.m7388i0(), i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R */
    public /* synthetic */ void m14457R() {
        final int iM14480D;
        synchronized (this.f12760j) {
            if (this.f12751a.isEmpty()) {
                Map<Group, Long> mapM15075l = C2950b0.m14912k().m15075l();
                iM14480D = 0;
                if (mapM15075l != null) {
                    for (Group group : mapM15075l.keySet()) {
                        int i9 = group.f13734u;
                        iM14480D += i9;
                        if (i9 == 0) {
                            iM14480D += C2950b0.m14906e().m14982l(group.f13727n);
                        }
                    }
                }
            } else {
                iM14480D = m14480D();
            }
        }
        ULogUtility.m16662E("UnreadCountManager", "updateShortcutBadge count:" + iM14480D);
        C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.chat.l0
            @Override // java.lang.Runnable
            public final void run() {
                C2907m0.m14456Q(iM14480D);
            }
        });
    }

    /* renamed from: h0 */
    public static void m14466h0(Context context, int i9) {
        C6265c.m24009a(context, i9);
    }

    /* renamed from: A */
    public void m14477A(long j9) {
        synchronized (this.f12760j) {
            this.f12754d.put(Long.valueOf(j9), 0);
            m14506i0(Globals.m7388i0());
        }
        f12750r.execute(new f(this, null));
    }

    /* renamed from: B */
    public void m14478B(long j9) {
        synchronized (this.f12755e) {
            this.f12755e.put(Long.valueOf(j9), 0);
        }
        Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(j9));
        if (groupM15077n != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("unreadPollsCount");
            groupM15077n.f13708G = 0;
            C2950b0.m14912k().m15063B(String.valueOf(groupM15077n.f13727n), groupM15077n, arrayList);
        }
    }

    /* renamed from: C */
    public void m14479C(long j9) {
        String str = String.valueOf(j9) + "@conference.u.cyberlink.com";
        synchronized (this.f12760j) {
            if (!this.f12751a.containsKey(str)) {
                m14498Z(str, 0);
            }
        }
    }

    /* renamed from: D */
    public int m14480D() {
        int i9;
        int iM14529f;
        synchronized (this.f12760j) {
            i9 = 0;
            for (Map.Entry<String, j> entry : this.f12751a.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue().m14529f() > 0) {
                    iM14529f = entry.getValue().m14529f();
                } else {
                    iM14529f = m14482F(key);
                    if (iM14529f <= 0) {
                        try {
                            iM14529f = m14481E(Long.parseLong(m14490O(key)), true);
                        } catch (Exception e9) {
                            Log.d("UnreadCountManager", Log.getStackTraceString(e9));
                        }
                    }
                }
                i9 += iM14529f;
            }
        }
        return i9;
    }

    /* renamed from: E */
    public int m14481E(long j9, boolean z8) {
        int i9;
        List<Long> list;
        synchronized (this.f12760j) {
            i9 = 0;
            if (!this.f12752b.contains(Long.valueOf(j9)) && (list = this.f12753c.get(Long.valueOf(j9))) != null) {
                Iterator<Long> it = list.iterator();
                int i10 = 0;
                boolean z9 = false;
                while (it.hasNext()) {
                    int iM14486K = m14486K(it.next().longValue());
                    if (iM14486K == -1) {
                        z9 = true;
                        iM14486K = 0;
                    }
                    i10 += iM14486K;
                }
                i9 = (!z8 && i10 == 0 && z9) ? -1 : i10;
            }
        }
        return i9;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: F */
    public int m14482F(String str) {
        int iBooleanValue;
        synchronized (this.f12760j) {
            iBooleanValue = this.f12757g.containsKey(str) ? this.f12757g.get(str).booleanValue() : 0;
        }
        return iBooleanValue;
    }

    /* renamed from: G */
    public int m14483G(String str) {
        int iM14529f;
        if (str == null) {
            return 0;
        }
        synchronized (this.f12760j) {
            iM14529f = this.f12751a.containsKey(str) ? this.f12751a.get(str).m14529f() : 0;
        }
        return iM14529f;
    }

    /* renamed from: H */
    public List<String> m14484H() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f12760j) {
            for (Map.Entry<String, j> entry : this.f12751a.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue().m14529f() > 0) {
                    arrayList.add(key);
                } else {
                    try {
                        if (m14481E(Long.parseLong(m14490O(key)), true) > 0) {
                            arrayList.add(key);
                        }
                    } catch (Exception e9) {
                        Log.d("UnreadCountManager", Log.getStackTraceString(e9));
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: J */
    public int m14485J(String str) {
        int iM14481E = 0;
        if (str == null) {
            return 0;
        }
        synchronized (this.f12760j) {
            for (Map.Entry<String, j> entry : this.f12751a.entrySet()) {
                String key = entry.getKey();
                if (!key.equals(str)) {
                    int iM14529f = entry.getValue().m14529f();
                    iM14481E += iM14529f;
                    if (iM14529f == 0) {
                        try {
                            iM14481E += m14481E(Long.parseLong(m14490O(key)), true);
                        } catch (Exception e9) {
                            Log.d("UnreadCountManager", Log.getStackTraceString(e9));
                        }
                    }
                }
            }
        }
        return iM14481E;
    }

    /* renamed from: K */
    public final int m14486K(long j9) {
        int iIntValue;
        synchronized (this.f12760j) {
            iIntValue = this.f12754d.containsKey(Long.valueOf(j9)) ? this.f12754d.get(Long.valueOf(j9)).intValue() : 0;
        }
        return iIntValue;
    }

    /* renamed from: L */
    public int m14487L(long j9) {
        int iIntValue;
        synchronized (this.f12755e) {
            Integer num = this.f12755e.get(Long.valueOf(j9));
            iIntValue = num == null ? 0 : num.intValue();
        }
        return iIntValue;
    }

    /* renamed from: M */
    public final void m14488M(Group group, long j9) {
        if (group == null || C5170o0.m20170e(group.f13723j)) {
            return;
        }
        synchronized (this.f12760j) {
            int i9 = group.f13734u;
            if (this.f12751a.containsKey(group.f13723j)) {
                Iterator it = this.f12751a.get(group.f13723j).f12778b.iterator();
                long j10 = j9;
                while (it.hasNext()) {
                    long jLongValue = ((Long) it.next()).longValue();
                    if (jLongValue > j9) {
                        i9++;
                        j10 = jLongValue;
                    }
                }
                this.f12751a.put(group.f13723j, new j(i9, j10));
            } else {
                this.f12751a.put(group.f13723j, new j(i9, j9));
            }
            if (i9 > 0) {
                ULogUtility.m16662E("UnreadCountManager", "reset unread(whendoarchive) jid=" + group.f13723j + " count=" + i9);
            }
        }
    }

    /* renamed from: N */
    public boolean m14489N() {
        return this.f12759i;
    }

    /* renamed from: O */
    public final String m14490O(String str) {
        try {
            return str.split("@")[0];
        } catch (Exception e9) {
            Log.d("UnreadCountManager", Log.getStackTraceString(e9));
            return null;
        }
    }

    /* renamed from: S */
    public final void m14491S() {
        this.f12767q.m24540f(new e(this.f12759i));
    }

    /* renamed from: T */
    public final void m14492T() {
        this.f12766p.m24540f(new d());
    }

    /* renamed from: U */
    public void m14493U(long j9, String str) {
        if (str == null) {
            return;
        }
        synchronized (this.f12760j) {
            this.f12751a.remove(str);
            this.f12756f.remove(str);
            this.f12757g.remove(str);
            if (this.f12753c.containsKey(Long.valueOf(j9))) {
                List<Long> list = this.f12753c.get(Long.valueOf(j9));
                if (list != null) {
                    Iterator<Long> it = list.iterator();
                    while (it.hasNext()) {
                        long jLongValue = it.next().longValue();
                        this.f12754d.remove(Long.valueOf(jLongValue));
                        this.f12756f.remove(String.valueOf(jLongValue));
                    }
                }
                this.f12753c.remove(Long.valueOf(j9));
            }
            m14506i0(Globals.m7388i0());
        }
        f12750r.execute(new f(this, null));
    }

    /* renamed from: V */
    public void m14494V(g gVar) {
        this.f12766p.m24541g(gVar);
    }

    /* renamed from: W */
    public void m14495W(h hVar) {
        this.f12767q.m24541g(hVar);
    }

    /* renamed from: X */
    public synchronized void m14496X() {
        m14502d0();
        m14501c0();
    }

    /* renamed from: Y */
    public final void m14497Y(String str, boolean z8) {
        synchronized (this.f12760j) {
            this.f12757g.put(str, Boolean.valueOf(z8));
            if (z8) {
                ULogUtility.m16662E("UnreadCountManager", "reset reminder id=" + str + " setAsReminder true");
            }
        }
    }

    /* renamed from: Z */
    public void m14498Z(String str, int i9) {
        if (str == null) {
            return;
        }
        synchronized (this.f12760j) {
            this.f12751a.put(str, new j(i9, 0L));
            if (i9 > 0) {
                ULogUtility.m16662E("UnreadCountManager", "reset unread jid=" + str + " count=" + i9);
            }
        }
    }

    /* renamed from: a0 */
    public final void m14499a0(long j9, int i9) {
        synchronized (this.f12760j) {
            this.f12754d.put(Long.valueOf(j9), Integer.valueOf(i9));
            if (i9 > 0) {
                ULogUtility.m16662E("UnreadCountManager", "reset topic unread jid=" + j9 + " count=" + i9);
            }
        }
    }

    /* renamed from: b0 */
    public final void m14500b0(long j9, int i9) {
        synchronized (this.f12755e) {
            this.f12755e.put(Long.valueOf(j9), Integer.valueOf(i9));
            if (i9 > 0) {
                ULogUtility.m16662E("UnreadCountManager", "reset poll unread jid=" + j9 + " count=" + i9);
            }
        }
    }

    /* renamed from: c0 */
    public final void m14501c0() {
        XMPPManager.m14184g0().m14207H(this.f12763m);
        C5321e.m20824o().m20875k(this.f12764n);
        XMPPArchiveHelper.m14132i(this.f12765o);
        AsyncTask<Void, Void, Object[]> asyncTask = this.f12762l;
        if (asyncTask == null || (!asyncTask.getStatus().equals(AsyncTask.Status.RUNNING) && !this.f12762l.getStatus().equals(AsyncTask.Status.FINISHED))) {
            c cVar = new c();
            this.f12761k = cVar;
            cVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        Log.d("UnreadCountManager", "init UnreadManager");
    }

    /* renamed from: d0 */
    public final void m14502d0() {
        this.f12759i = false;
        synchronized (this.f12760j) {
            this.f12758h = false;
        }
        m14491S();
        synchronized (this.f12760j) {
            this.f12751a.clear();
            this.f12753c.clear();
            this.f12754d.clear();
            this.f12756f.clear();
            this.f12757g.clear();
        }
        synchronized (this.f12755e) {
            this.f12755e.clear();
        }
        this.f12761k = null;
        this.f12762l = null;
        XMPPManager.m14184g0().m14233Z0(this.f12763m);
        XMPPArchiveHelper.m14122D(this.f12765o);
        C5321e.m20824o().m20832B0(this.f12764n);
    }

    /* renamed from: e0 */
    public void m14503e0(String str, boolean z8, long j9) {
        int iM14529f;
        if (str == null) {
            return;
        }
        synchronized (this.f12760j) {
            if (!this.f12758h || this.f12756f.contains(str)) {
                int i9 = 1;
                if (this.f12751a.containsKey(str)) {
                    j jVar = this.f12751a.get(str);
                    iM14529f = jVar.m14529f();
                    if (z8) {
                        if (j9 > (jVar.f12778b.size() > 0 ? ((Long) jVar.f12778b.get(jVar.f12778b.size() - 1)).longValue() : 0L)) {
                            iM14529f++;
                            jVar.m14528e(iM14529f, j9);
                        }
                    } else if (iM14529f > 0) {
                        iM14529f--;
                        jVar.m14530g(iM14529f, j9);
                    }
                } else {
                    if (!z8) {
                        i9 = 0;
                    }
                    this.f12751a.put(str, new j(i9, j9));
                    iM14529f = i9;
                }
                if (iM14529f > 0) {
                    ULogUtility.m16662E("UnreadCountManager", "update unread jid=" + str + " count=" + iM14529f);
                }
            } else {
                Group groupM15080q = C2950b0.m14912k().m15080q(str);
                if (groupM15080q != null) {
                    this.f12751a.put(str, new j(groupM15080q.f13734u, 0L));
                    this.f12756f.add(groupM15080q.f13723j);
                    if (groupM15080q.f13734u > 0) {
                        ULogUtility.m16662E("UnreadCountManager", "reset update unread jid=" + str + " count=" + groupM15080q.f13734u);
                    }
                }
            }
        }
    }

    /* renamed from: f0 */
    public void m14504f0(TopicObj topicObj, boolean z8) {
        synchronized (this.f12755e) {
            Integer num = this.f12755e.get(Long.valueOf(topicObj.m14843h()));
            int iIntValue = z8 ? 1 : -1;
            if (num != null) {
                iIntValue += num.intValue();
            }
            Integer numValueOf = Integer.valueOf(Math.max(0, iIntValue));
            this.f12755e.put(Long.valueOf(topicObj.m14843h()), numValueOf);
            Log.d("UnreadCountManager", "update unread poll topic id=" + topicObj.m14843h() + " poll unread=" + numValueOf);
        }
    }

    /* renamed from: g0 */
    public void m14505g0() {
        f12750r.submit(new Runnable() { // from class: com.cyberlink.you.chat.k0
            @Override // java.lang.Runnable
            public final void run() {
                this.f12698b.m14457R();
            }
        });
    }

    /* renamed from: i0 */
    public final void m14506i0(Context context) {
        int iM14480D = m14480D();
        ULogUtility.m16662E("UnreadCountManager", "updateShortcutBadgeInternal totalCount:" + iM14480D);
        m14466h0(context, iM14480D);
    }

    /* renamed from: j0 */
    public void m14507j0(long j9, long j10, int i9) {
        synchronized (this.f12760j) {
            this.f12754d.put(Long.valueOf(j10), Integer.valueOf(i9));
            m14509s(j9, j10);
        }
    }

    /* renamed from: k0 */
    public void m14508k0(long j9, boolean z8, boolean z9, boolean z10) {
        synchronized (this.f12760j) {
            TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(j9);
            if (!this.f12758h || this.f12756f.contains(String.valueOf(j9))) {
                Integer numValueOf = this.f12754d.get(Long.valueOf(j9));
                if (z8) {
                    if (z10) {
                        if (numValueOf == null || numValueOf.intValue() == -1 || numValueOf.intValue() == 0) {
                            numValueOf = -1;
                        }
                    } else if (z9 || numValueOf.intValue() == -1) {
                        numValueOf = 0;
                    }
                } else if (z10) {
                    if (numValueOf == null || numValueOf.intValue() <= 0) {
                        numValueOf = 1;
                    } else if (!z9 || topicObjM14984n.m14845j() != 0) {
                        numValueOf = Integer.valueOf(numValueOf.intValue() + 1);
                    }
                } else if (z9) {
                    numValueOf = 0;
                } else {
                    numValueOf = Integer.valueOf(numValueOf.intValue() - 1);
                    if (numValueOf.intValue() < 0) {
                        numValueOf = 0;
                    }
                }
                Log.d("UnreadCountManager", "update unread topic id=" + j9 + " bulletinCount=" + numValueOf);
                this.f12754d.put(Long.valueOf(j9), numValueOf);
                if (topicObjM14984n != null) {
                    m14509s(topicObjM14984n.m14843h(), topicObjM14984n.m14849o());
                }
            } else {
                if (topicObjM14984n != null) {
                    int iM14850p = topicObjM14984n.m14850p();
                    this.f12754d.put(Long.valueOf(topicObjM14984n.m14849o()), Integer.valueOf(iM14850p));
                    this.f12756f.add(String.valueOf(topicObjM14984n.m14849o()));
                    m14509s(topicObjM14984n.m14843h(), topicObjM14984n.m14849o());
                    Log.d("UnreadCountManager", "reset update unread group id=" + j9 + " bulletin count=" + iM14850p);
                }
                if (!z10 && z9) {
                    this.f12754d.put(Long.valueOf(j9), 0);
                }
            }
        }
    }

    /* renamed from: s */
    public final void m14509s(long j9, long j10) {
        List<Long> arrayList = this.f12753c.get(Long.valueOf(j9));
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f12753c.put(Long.valueOf(j9), arrayList);
        }
        if (arrayList.contains(Long.valueOf(j10))) {
            return;
        }
        arrayList.add(Long.valueOf(j10));
    }

    /* renamed from: t */
    public void m14510t(g gVar) {
        this.f12766p.m24539c(gVar);
    }

    /* renamed from: u */
    public void m14511u(h hVar) {
        this.f12767q.m24539c(hVar);
    }

    /* renamed from: v */
    public void m14512v(long j9) {
        synchronized (this.f12760j) {
            List<Long> list = this.f12753c.get(Long.valueOf(j9));
            if (list != null) {
                Iterator<Long> it = list.iterator();
                while (it.hasNext()) {
                    m14477A(it.next().longValue());
                }
            }
            m14506i0(Globals.m7388i0());
        }
        f12750r.execute(new f(this, null));
    }

    /* renamed from: w */
    public void m14513w(String str, boolean z8) {
        if (str == null) {
            return;
        }
        ULogUtility.m16662E("UnreadCountManager", "clearGroupReminder | jid = " + str);
        synchronized (this.f12760j) {
            m14497Y(str, false);
            m14506i0(Globals.m7388i0());
        }
        if (z8) {
            f12750r.execute(new f(this, null));
        }
    }

    /* renamed from: x */
    public void m14514x(String str) {
        m14515y(str, true);
    }

    /* renamed from: y */
    public void m14515y(String str, boolean z8) {
        if (str == null) {
            return;
        }
        ULogUtility.m16662E("UnreadCountManager", "clearGroupUnread | jid = " + str);
        synchronized (this.f12760j) {
            m14498Z(str, 0);
            m14506i0(Globals.m7388i0());
        }
        if (z8) {
            f12750r.execute(new f(this, null));
        }
    }

    /* renamed from: z */
    public void m14516z(long j9) {
        synchronized (this.f12760j) {
            List<Long> list = this.f12753c.get(Long.valueOf(j9));
            if (list != null) {
                Iterator<Long> it = list.iterator();
                while (it.hasNext()) {
                    long jLongValue = it.next().longValue();
                    if (this.f12754d.get(Long.valueOf(jLongValue)).intValue() == -1) {
                        this.f12754d.put(Long.valueOf(jLongValue), 0);
                    }
                }
            }
        }
        f12750r.execute(new f(this, null));
    }

    public C2907m0() {
        this.f12751a = new HashMap();
        this.f12752b = new ArrayList<>();
        this.f12753c = new HashMap();
        this.f12754d = new HashMap();
        this.f12755e = new HashMap();
        this.f12756f = new ArrayList();
        this.f12757g = new HashMap();
        this.f12758h = false;
        this.f12759i = false;
        this.f12760j = new Object();
        this.f12763m = new a(true);
        this.f12764n = new C5321e.m() { // from class: com.cyberlink.you.chat.j0
            @Override // p136m3.C5321e.m
            /* renamed from: A0 */
            public final boolean mo8241A0(C2904l c2904l, Map map) {
                return this.f12695b.m14455P(c2904l, map);
            }
        };
        this.f12765o = new b();
        this.f12766p = new C6389z<>("UnreadMgr");
        this.f12767q = new C6389z<>("UnreadReady");
        m14501c0();
    }
}
