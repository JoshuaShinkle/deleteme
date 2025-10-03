package com.cyberlink.you.utility;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.ULogUtility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p209u2.AbstractC6378o;
import p209u2.AbstractC6381r;
import p209u2.C6369f;
import p209u2.C6383t;
import p209u2.C6384u;
import p209u2.C6385v;
import p209u2.ThreadFactoryC6373j;
import p227w2.C6518a;

/* loaded from: classes.dex */
public class ULogUtility {

    /* renamed from: a */
    public static final ExecutorService f14492a = Executors.newSingleThreadExecutor(new ThreadFactoryC6373j("ULogger"));

    /* renamed from: b */
    public static final SimpleDateFormat f14493b = new SimpleDateFormat("MM/dd HH:mm:ss.SSS", Locale.US);

    /* renamed from: c */
    public static BufferedWriter f14494c = null;

    public enum LogFileType {
        Log,
        Logcat,
        CLRTC,
        Webinars
    }

    public enum PingType {
        UServer,
        XmppServer
    }

    /* renamed from: com.cyberlink.you.utility.ULogUtility$a */
    public class C3160a extends AbstractC6378o<Void, Exception, String> {

        /* renamed from: d */
        public final /* synthetic */ OutputStream f14503d;

        /* renamed from: e */
        public final /* synthetic */ AbstractC6381r f14504e;

        public C3160a(OutputStream outputStream, AbstractC6381r abstractC6381r) {
            this.f14503d = outputStream;
            this.f14504e = abstractC6381r;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: p, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r12) {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public void m24504h(Exception exc) {
            this.f14504e.m24508f(exc);
        }

        @Override // p209u2.AbstractC6378o
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void m24498l(String str) throws IOException {
            try {
                this.f14503d.write(str.getBytes());
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.ULogUtility$b */
    public class C3161b extends AbstractC6381r<String, Exception> {
        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(String str) {
        }
    }

    /* renamed from: com.cyberlink.you.utility.ULogUtility$c */
    public class C3162c extends AbstractC6381r<String, Exception> {
        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(String str) {
        }
    }

    /* renamed from: com.cyberlink.you.utility.ULogUtility$d */
    public static class AsyncTaskC3163d extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final AbstractC6381r<String, Exception> f14505a;

        public /* synthetic */ AsyncTaskC3163d(AbstractC6381r abstractC6381r, C3160a c3160a) {
            this(abstractC6381r);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws IOException {
            ULogUtility.m16675k(this.f14505a);
            return null;
        }

        public AsyncTaskC3163d(AbstractC6381r<String, Exception> abstractC6381r) {
            this.f14505a = abstractC6381r;
        }
    }

    /* renamed from: A */
    public static void m16658A(long j9, long j10, String str, String str2) throws IOException {
        try {
            f14494c.write(String.format(Locale.US, "%s [%05d] [%s] %s\n", f14493b.format(new Date(j10)), Long.valueOf(j9), str, str2));
            f14494c.flush();
        } catch (Exception e9) {
            Log.e("ULogUtility", "Cannot log message", e9);
        }
    }

    /* renamed from: B */
    public static void m16659B(String str, String str2) {
        m16683s("Notification - " + str, str2);
    }

    /* renamed from: C */
    public static void m16660C(String str, String str2, PingType pingType) {
        m16683s("PingServer - " + pingType.toString() + ": " + str2, str);
    }

    /* renamed from: D */
    public static void m16661D(String str, String str2) {
        m16683s("SendPhoto - " + str, str2);
    }

    /* renamed from: E */
    public static void m16662E(String str, String str2) {
        m16683s("UnreadCnt - " + str, str2);
    }

    /* renamed from: F */
    public static void m16663F(String str, String str2) {
        m16683s("VoiceCall - " + str, str2);
    }

    /* renamed from: G */
    public static void m16664G(String str, String str2) {
        m16683s("XMPP - " + str2, str);
    }

    /* renamed from: c */
    public static void m16667c() {
        for (LogFileType logFileType : LogFileType.values()) {
            m16668d(m16678n(logFileType, true));
        }
        m16682r();
    }

    /* renamed from: d */
    public static void m16668d(File file) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (jCurrentTimeMillis - file2.lastModified() > 604800000) {
                file2.delete();
            }
        }
    }

    /* renamed from: e */
    public static void m16669e() {
        File fileM16678n = m16678n(LogFileType.Log, true);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
        try {
            f14494c = new BufferedWriter(new FileWriter(new File(fileM16678n, simpleDateFormat.format(new Date()) + (Globals.m7380c2() ? "_beta.txt" : "_u.txt")), false), 2048);
        } catch (IOException e9) {
            Log.w("ULogUtility", "Cannot create log writer", e9);
        }
    }

    /* renamed from: f */
    public static void m16670f(String str, String str2) {
        m16671g(str, str2);
    }

    /* renamed from: g */
    public static void m16671g(final String str, final String str2) {
        final long jMyTid = Process.myTid();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        f14492a.execute(new Runnable() { // from class: k4.s0
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                ULogUtility.m16681q(jMyTid, jCurrentTimeMillis, str, str2);
            }
        });
    }

    /* renamed from: h */
    public static void m16672h() {
        m16673i(new C3162c());
    }

    /* renamed from: i */
    public static void m16673i(AbstractC6381r<String, Exception> abstractC6381r) {
        new AsyncTaskC3163d(abstractC6381r, null).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: j */
    public static void m16674j() {
        m16675k(new C3161b());
    }

    /* renamed from: k */
    public static void m16675k(AbstractC6381r<String, Exception> abstractC6381r) throws IOException {
        File file = new File(m16678n(LogFileType.Logcat, true), new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(new Date()) + "_logcat.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(m16679o().getBytes());
                C6384u.m24522b("logcat -d", new C3160a(fileOutputStream, abstractC6381r));
                abstractC6381r.m24506d(file.toString());
                fileOutputStream.close();
            } finally {
            }
        } catch (Exception e9) {
            abstractC6381r.m24508f(e9);
        }
    }

    /* renamed from: l */
    public static void m16676l(String str, String str2) {
        m16671g(str, str2);
    }

    /* renamed from: m */
    public static String m16677m() {
        return C6383t.m24518g(5);
    }

    /* renamed from: n */
    public static File m16678n(LogFileType logFileType, boolean z8) {
        File file = new File(z8 ? CLUtility.m16502X() : CLUtility.m16561m1(C6518a.f21930b), logFileType.name());
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /* renamed from: o */
    public static String m16679o() {
        StringBuilder sb = new StringBuilder();
        sb.append("Start to Log U Android");
        sb.append(Globals.m7380c2() ? " Beta" : "");
        sb.append(": ");
        sb.append(Globals.m7391n1());
        sb.append(" (");
        sb.append(Globals.m7368J());
        sb.append(") (");
        sb.append(System.getProperty("os.arch"));
        sb.append(") (OS: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: p */
    public static void m16680p(String str, String str2) {
        m16671g(str, str2);
    }

    /* renamed from: q */
    public static /* synthetic */ void m16681q(long j9, long j10, String str, String str2) throws IOException {
        if (f14494c == null) {
            m16669e();
            m16658A(j9, j10, "App", m16679o());
        }
        m16658A(j9, j10, str, str2);
    }

    /* renamed from: r */
    public static void m16682r() {
        String[] strArr = {"DB", "gcm", "heartbeat", "notification", "okHttp", "ping", "sendphoto", "unreadcnt", "xmpp"};
        String strM16502X = CLUtility.m16502X();
        for (int i9 = 0; i9 < 9; i9++) {
            File file = new File(strM16502X, strArr[i9] + "Log");
            if (file.exists() && file.isDirectory()) {
                C6369f.m24464f(file);
            }
        }
    }

    /* renamed from: s */
    public static void m16683s(String str, String str2) {
        m16671g(str, str2);
    }

    /* renamed from: t */
    public static void m16684t(String str, String str2) {
        m16671g(str, str2);
    }

    /* renamed from: u */
    public static void m16685u(String str, String str2, Exception exc) {
        m16676l("DB - " + str, str2 + "Exception: " + exc.toString());
    }

    /* renamed from: v */
    public static void m16686v(String str, String str2) {
        m16683s("DB - " + str, str2);
    }

    /* renamed from: w */
    public static void m16687w(String str, String str2, String str3, String str4) {
        m16683s("DB - " + str, str2 + str3 + str4);
    }

    /* renamed from: x */
    public static void m16688x(String str, String str2) {
        m16683s("DouAccount - " + str, str2);
    }

    /* renamed from: y */
    public static void m16689y(String str) {
        m16683s("GCM", str);
    }

    /* renamed from: z */
    public static void m16690z(String str) {
        m16683s("Heartbeat", str);
    }
}
