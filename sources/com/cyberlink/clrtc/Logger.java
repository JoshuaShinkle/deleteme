package com.cyberlink.clrtc;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Keep;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;
import p209u2.C6370g;

/* loaded from: classes.dex */
public final class Logger {

    /* renamed from: q */
    public static final String f4845q = "Logger";

    /* renamed from: a */
    public final SimpleDateFormat f4846a;

    /* renamed from: b */
    public final SimpleDateFormat f4847b;

    /* renamed from: c */
    public final long f4848c;

    /* renamed from: d */
    public final AtomicInteger f4849d;

    /* renamed from: e */
    public final Context f4850e;

    /* renamed from: f */
    public final File f4851f;

    /* renamed from: g */
    public final ScheduledThreadPoolExecutor f4852g;

    /* renamed from: h */
    public BufferedWriter f4853h;

    /* renamed from: i */
    public File f4854i;

    /* renamed from: j */
    public C1019i f4855j;

    /* renamed from: k */
    public int f4856k;

    /* renamed from: l */
    public File f4857l;

    /* renamed from: m */
    public boolean f4858m;

    /* renamed from: n */
    public boolean f4859n;

    /* renamed from: o */
    public String f4860o;

    /* renamed from: p */
    public final String f4861p;

    public enum Severity {
        S(0, 2),
        V(1, 2),
        D(-2, 3),
        I(2, 4),
        W(3, 5),
        E(4, 6),
        S_D(-1, 2),
        S_I(102, 4),
        S_W(103, 5),
        S_E(104, 6),
        N(-99, 0);

        private final int level;
        private final int priority;

        Severity(int i9, int i10) {
            this.level = i9;
            this.priority = i10;
        }

        /* renamed from: b */
        public static Severity m4470b(int i9) {
            for (Severity severity : values()) {
                if (severity.level == i9) {
                    return severity;
                }
            }
            return N;
        }
    }

    public Logger(Context context, File file) {
        Locale locale = Locale.US;
        this.f4846a = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", locale);
        this.f4847b = new SimpleDateFormat("HH:mm:ss.SSS", locale);
        this.f4848c = SystemClock.elapsedRealtime();
        this.f4849d = new AtomicInteger(0);
        this.f4853h = null;
        this.f4854i = null;
        this.f4855j = null;
        this.f4856k = 0;
        this.f4858m = false;
        this.f4859n = false;
        this.f4860o = null;
        this.f4861p = "%08d\t%10.6f\t[%05d]\t%s";
        this.f4850e = context;
        this.f4851f = file;
        this.f4852g = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.DiscardPolicy());
    }

    @Keep
    private void OnLog(int i9, String str) {
        Severity severityM4470b = Severity.m4470b(i9);
        if (Severity.N == severityM4470b) {
            return;
        }
        m4461m(severityM4470b, "LogJni", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public /* synthetic */ void m4451j() {
        File file = this.f4854i;
        if (file == null) {
            return;
        }
        C1073o.m5036r(this.f4850e, this.f4856k, this.f4855j, this.f4860o, file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public /* synthetic */ void m4452k(long j9, long j10, String str) throws IOException {
        if (this.f4853h == null) {
            m4457f();
        }
        try {
            this.f4853h.write(String.format(Locale.US, "%08d\t%10.6f\t[%05d]\t%s", Integer.valueOf(this.f4849d.incrementAndGet()), Float.valueOf(j9 / 1000.0f), Long.valueOf(j10), str));
            if (!str.endsWith("\n") && !str.endsWith(StringUtils.f19107CR)) {
                this.f4853h.newLine();
            }
            this.f4853h.flush();
        } catch (IOException e9) {
            Log.w(f4845q, "Cannot log message", e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m4453l() throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        m4455d();
        this.f4852g.shutdownNow();
        if (this.f4858m) {
            C1073o.m5037s(this.f4850e, this.f4856k, this.f4855j, this.f4860o, this.f4851f, this.f4857l, this.f4859n);
        }
    }

    /* renamed from: n */
    public static void m4454n(int i9, String str, String str2) {
    }

    /* renamed from: d */
    public final void m4455d() throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        BufferedWriter bufferedWriter = this.f4853h;
        if (bufferedWriter != null) {
            try {
                bufferedWriter.flush();
            } catch (IOException unused) {
            }
            C6370g.m24480b(this.f4853h);
            this.f4853h = null;
            this.f4854i = null;
        }
    }

    /* renamed from: e */
    public void m4456e() {
        this.f4852g.execute(new Runnable() { // from class: com.cyberlink.clrtc.q
            @Override // java.lang.Runnable
            public final void run() {
                this.f5471b.m4451j();
            }
        });
    }

    /* renamed from: f */
    public final void m4457f() {
        if (!this.f4851f.exists()) {
            this.f4851f.mkdirs();
        }
        this.f4854i = new File(this.f4851f, this.f4846a.format(new Date()) + "_clrtc.txt");
        try {
            this.f4853h = new BufferedWriter(new FileWriter(this.f4854i, false), 2048);
        } catch (IOException e9) {
            Log.w(f4845q, "Cannot create log writer", e9);
        }
    }

    /* renamed from: g */
    public void m4458g(String str, String str2) {
        m4461m(Severity.D, str, str2);
    }

    /* renamed from: h */
    public void m4459h(String str, String str2) {
        m4461m(Severity.E, str, str2);
    }

    /* renamed from: i */
    public void m4460i(String str, String str2) {
        m4461m(Severity.I, str, str2);
    }

    /* renamed from: m */
    public final void m4461m(Severity severity, String str, String str2) {
        if (this.f4851f == null) {
            return;
        }
        m4454n(severity.priority, str, str2);
        final long jMyTid = Process.myTid();
        final long jElapsedRealtime = SystemClock.elapsedRealtime() - this.f4848c;
        if (!"LogJni".equals(str)) {
            str2 = this.f4847b.format(new Date()) + StringUtils.SPACE + str2;
        } else if (str2.endsWith("\n\n")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        final String str3 = str2;
        this.f4852g.execute(new Runnable() { // from class: com.cyberlink.clrtc.p
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                this.f5443b.m4452k(jElapsedRealtime, jMyTid, str3);
            }
        });
    }

    /* renamed from: o */
    public void m4462o() {
        this.f4852g.execute(new Runnable() { // from class: com.cyberlink.clrtc.r
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
                this.f5498b.m4453l();
            }
        });
    }

    /* renamed from: p */
    public void m4463p(File file) {
        this.f4857l = file;
    }

    /* renamed from: q */
    public void m4464q(C1019i c1019i, int i9) {
        this.f4855j = c1019i;
        this.f4856k = i9;
    }

    /* renamed from: r */
    public void m4465r(C1019i c1019i, int i9, boolean z8) {
        m4464q(c1019i, i9);
        this.f4858m = true;
        if (z8) {
            this.f4859n = true;
        }
    }

    /* renamed from: s */
    public void m4466s(String str) {
        this.f4860o = str;
    }

    /* renamed from: t */
    public void m4467t(String str, String str2) {
        m4461m(Severity.V, str, str2);
    }

    /* renamed from: u */
    public void m4468u(String str, String str2) {
        m4461m(Severity.W, str, str2);
    }
}
