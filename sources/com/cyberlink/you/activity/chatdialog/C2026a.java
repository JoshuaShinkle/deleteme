package com.cyberlink.you.activity.chatdialog;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.sticker.StickerObj;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;
import p135m2.C5315b;
import p136m3.C5321e;
import p209u2.C6383t;
import p209u2.ThreadFactoryC6373j;

/* renamed from: com.cyberlink.you.activity.chatdialog.a */
/* loaded from: classes.dex */
public final class C2026a {

    /* renamed from: a */
    public final boolean f10269a;

    /* renamed from: b */
    public final e f10270b;

    /* renamed from: c */
    public final Handler f10271c;

    /* renamed from: d */
    public volatile boolean f10272d = false;

    /* renamed from: e */
    public volatile int f10273e = 0;

    /* renamed from: f */
    public final d f10274f = new a("AutoText");

    /* renamed from: g */
    public final d f10275g = new b("AutoSticker");

    /* renamed from: h */
    public final f f10276h = new c("AutoCaller");

    /* renamed from: com.cyberlink.you.activity.chatdialog.a$a */
    public class a extends d {
        public a(String str) {
            super(str);
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.f
        /* renamed from: a */
        public void mo12024a(String str, int i9) {
            C2026a.this.f10270b.mo11633a("Auto sent message (" + str + ") #" + i9);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.a$b */
    public class b extends d {

        /* renamed from: i */
        public final ArrayList<StickerObj> f10278i;

        /* renamed from: j */
        public AsyncTask<Void, Void, Void> f10279j;

        /* renamed from: com.cyberlink.you.activity.chatdialog.a$b$a */
        public class a extends AsyncTask<Void, Void, Void> {

            /* renamed from: a */
            public long f10281a;

            /* renamed from: b */
            public final /* synthetic */ Runnable f10282b;

            public a(Runnable runnable) {
                this.f10282b = runnable;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                if (isCancelled()) {
                    return null;
                }
                b.this.f10278i.clear();
                for (StickerPackObj stickerPackObj : C2950b0.m14925x().m15292j()) {
                    if (isCancelled()) {
                        break;
                    }
                    b.this.f10278i.addAll(C2950b0.m14924w().m15279g(stickerPackObj.m14803g()));
                }
                isCancelled();
                return null;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Void r12) {
                if (isCancelled()) {
                    return;
                }
                this.f10282b.run();
            }

            @Override // android.os.AsyncTask
            public void onPreExecute() {
                this.f10281a = System.currentTimeMillis();
            }
        }

        public b(String str) {
            super(str);
            this.f10278i = new ArrayList<>();
            this.f10279j = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m12027g(int i9) {
            if (this.f10278i.isEmpty()) {
                mo12028b();
                return;
            }
            e eVar = C2026a.this.f10270b;
            ArrayList<StickerObj> arrayList = this.f10278i;
            eVar.mo11635c(arrayList.get(i9 % arrayList.size()));
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.f
        /* renamed from: a */
        public void mo12024a(String str, final int i9) {
            m12029h(new Runnable() { // from class: y2.l5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22351b.m12027g(i9);
                }
            });
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.d, com.cyberlink.you.activity.chatdialog.C2026a.f
        /* renamed from: b */
        public void mo12028b() {
            super.mo12028b();
            AsyncTask<Void, Void, Void> asyncTask = this.f10279j;
            if (asyncTask != null) {
                asyncTask.cancel(true);
                this.f10279j = null;
            }
        }

        /* renamed from: h */
        public final void m12029h(Runnable runnable) {
            AsyncTask<Void, Void, Void> asyncTask = this.f10279j;
            if (asyncTask != null) {
                if (AsyncTask.Status.FINISHED == asyncTask.getStatus()) {
                    runnable.run();
                    return;
                }
                this.f10279j.cancel(true);
            }
            this.f10279j = new a(runnable).executeOnExecutor(Executors.newSingleThreadExecutor(new ThreadFactoryC6373j(this.f10288b)), new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.a$c */
    public class c extends f {
        public c(String str) {
            super(str);
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.f
        /* renamed from: a */
        public void mo12024a(String str, int i9) {
            C2026a.this.f10270b.mo11633a("(CALLER) (" + str + ") #" + i9);
            C2026a.this.f10270b.mo11634b(C2026a.this.f10273e == 1);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.a$d */
    public abstract class d extends f {

        /* renamed from: f */
        public long f10285f;

        /* renamed from: g */
        public int f10286g;

        public d(String str) {
            super(str);
            this.f10285f = 1000L;
            this.f10286g = 0;
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.f
        /* renamed from: b */
        public void mo12028b() {
            super.mo12028b();
            this.f10285f = 1000L;
            this.f10286g = 0;
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.f, java.lang.Runnable
        public final void run() {
            super.run();
            if (this.f10290d >= this.f10286g) {
                mo12028b();
            } else {
                C2026a.this.f10271c.postDelayed(this, this.f10285f);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.a$e */
    public interface e {
        /* renamed from: a */
        void mo11633a(String str);

        /* renamed from: b */
        void mo11634b(boolean z8);

        /* renamed from: c */
        void mo11635c(StickerObj stickerObj);
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.a$f */
    public abstract class f implements Runnable {

        /* renamed from: b */
        public final String f10288b;

        /* renamed from: c */
        public String f10289c;

        /* renamed from: d */
        public int f10290d = 0;

        public f(String str) {
            this.f10289c = null;
            this.f10288b = str;
            this.f10289c = C2026a.this.m12018o();
        }

        /* renamed from: a */
        public abstract void mo12024a(String str, int i9);

        /* renamed from: b */
        public void mo12028b() {
            this.f10289c = C2026a.this.m12018o();
            this.f10290d = 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i9 = this.f10290d + 1;
            this.f10290d = i9;
            mo12024a(this.f10289c, i9);
        }
    }

    public C2026a(boolean z8, e eVar) {
        boolean z9 = !z8;
        this.f10269a = z9;
        if (z9) {
            this.f10270b = null;
            this.f10271c = null;
        } else {
            this.f10270b = eVar;
            this.f10271c = new Handler(Looper.getMainLooper());
        }
    }

    /* renamed from: t */
    public static boolean m12007t(String str) throws NumberFormatException {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public /* synthetic */ void m12008u(String str) {
        this.f10270b.mo11633a(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0053 A[RETURN] */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m12009f(String... strArr) {
        boolean z8;
        if (!C5315b.m20807w()) {
            return false;
        }
        if (strArr.length != 2) {
            this.f10273e = 1;
        } else if ("stop".equals(strArr[1])) {
            this.f10273e = 0;
        } else if ("voice".equals(strArr[1])) {
            this.f10273e = 1;
        } else {
            if (!MimeTypes.BASE_TYPE_VIDEO.equals(strArr[1])) {
                z8 = false;
                if (z8) {
                    return false;
                }
                this.f10271c.removeCallbacks(this.f10276h);
                this.f10276h.mo12028b();
                if (this.f10273e != 0) {
                    this.f10271c.post(this.f10276h);
                }
                return true;
            }
            this.f10273e = 2;
        }
        z8 = true;
        if (z8) {
        }
    }

    /* renamed from: g */
    public final boolean m12010g(String... strArr) {
        if (strArr.length != 2) {
            this.f10272d = true;
            return true;
        }
        if (!"stop".equals(strArr[1])) {
            return false;
        }
        this.f10272d = false;
        return true;
    }

    /* renamed from: h */
    public final boolean m12011h(d dVar, String... strArr) {
        if (strArr.length == 2) {
            if (m12007t(strArr[1])) {
                dVar.f10286g = Math.min(Integer.parseInt(strArr[1]), CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
                this.f10271c.removeCallbacks(dVar);
                this.f10271c.post(dVar);
                return true;
            }
            if (!"stop".equals(strArr[1])) {
                return false;
            }
            this.f10271c.removeCallbacks(dVar);
            dVar.mo12028b();
            return true;
        }
        if (strArr.length != 3) {
            return false;
        }
        if (m12007t(strArr[1]) && m12007t(strArr[2])) {
            dVar.f10286g = Math.min(Integer.parseInt(strArr[1]), CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
            dVar.f10285f = Math.max(Long.parseLong(strArr[2]), 100L);
            this.f10271c.removeCallbacks(dVar);
            this.f10271c.post(dVar);
            return true;
        }
        if (!"delay".equals(strArr[1]) || !m12007t(strArr[2])) {
            return false;
        }
        dVar.f10285f = Math.max(Long.parseLong(strArr[2]), 100L);
        return true;
    }

    /* renamed from: i */
    public final boolean m12012i(String... strArr) {
        return m12011h(this.f10275g, strArr);
    }

    /* renamed from: j */
    public final boolean m12013j(String... strArr) {
        return m12011h(this.f10274f, strArr);
    }

    /* renamed from: k */
    public final boolean m12014k(String[] strArr) {
        if (strArr.length == 0) {
            return false;
        }
        String str = strArr[0];
        str.hashCode();
        switch (str) {
        }
        return false;
    }

    /* renamed from: l */
    public void m12015l() {
        if (this.f10269a) {
            return;
        }
        this.f10272d = false;
        this.f10273e = 0;
        this.f10271c.removeCallbacksAndMessages(null);
        this.f10274f.mo12028b();
        this.f10275g.mo12028b();
    }

    /* renamed from: m */
    public final boolean m12016m() {
        C5321e.m20824o().m20891u();
        return true;
    }

    /* renamed from: n */
    public final boolean m12017n() {
        Globals.m7388i0().m7402B();
        return true;
    }

    /* renamed from: o */
    public final String m12018o() {
        return "A" + C6383t.m24518g(2) + "-" + C6383t.m24518g(3);
    }

    /* renamed from: p */
    public final void m12019p() {
        this.f10271c.postDelayed(this.f10276h, 2500L);
    }

    /* renamed from: q */
    public boolean m12020q(String str) {
        return !this.f10269a && m12014k(str.split(StringUtils.SPACE));
    }

    /* renamed from: r */
    public final boolean m12021r(String str) {
        if (!str.startsWith("Auto sent message ")) {
            return false;
        }
        final String str2 = "(ECHO) " + str.substring(18);
        this.f10271c.post(new Runnable() { // from class: y2.k5
            @Override // java.lang.Runnable
            public final void run() {
                this.f22340b.m12008u(str2);
            }
        });
        return true;
    }

    /* renamed from: s */
    public boolean m12022s(String str) {
        return !this.f10269a && this.f10272d && m12021r(str);
    }

    /* renamed from: v */
    public void m12023v() {
        if (this.f10269a || this.f10273e == 0) {
            return;
        }
        m12019p();
    }
}
