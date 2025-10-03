package com.perfectcorp.utility;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.perfectcorp.utility.b */
/* loaded from: classes2.dex */
public class C4507b {

    /* renamed from: a */
    public static boolean f15929a = false;

    /* renamed from: b */
    public static boolean f15930b = false;

    /* renamed from: c */
    public static boolean f15931c = false;

    /* renamed from: d */
    public static boolean f15932d = false;

    /* renamed from: e */
    public static int f15933e;

    /* renamed from: g */
    public static FileOutputStream f15935g;

    /* renamed from: j */
    public static Context f15938j;

    /* renamed from: f */
    public static final SimpleDateFormat f15934f = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);

    /* renamed from: h */
    public static BlockingQueue<Runnable> f15936h = new LinkedBlockingQueue();

    /* renamed from: i */
    public static ThreadPoolExecutor f15937i = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS, f15936h, new a());

    /* renamed from: com.perfectcorp.utility.b$a */
    public class a implements ThreadFactory {

        /* renamed from: a */
        public int f15939a = 0;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            int i9;
            synchronized (this) {
                i9 = this.f15939a;
                this.f15939a = i9 + 1;
            }
            return new Thread(runnable, "BCLog #" + i9);
        }
    }

    /* renamed from: com.perfectcorp.utility.b$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ String f15940b;

        /* renamed from: c */
        public final /* synthetic */ String f15941c;

        public b(String str, String str2) {
            this.f15940b = str;
            this.f15941c = str2;
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            C4507b.m18113n(this.f15940b, this.f15941c);
        }
    }

    /* renamed from: com.perfectcorp.utility.b$c */
    public static class c {

        /* renamed from: a */
        public String f15942a;

        /* renamed from: b */
        public String f15943b;

        public c(StackTraceElement stackTraceElement, Object... objArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(stackTraceElement.getMethodName());
            sb.append("][");
            sb.append(stackTraceElement.getLineNumber());
            sb.append("] ");
            int length = objArr.length;
            for (int i9 = 0; i9 < length; i9++) {
                Object obj = objArr[i9];
                sb.append(obj == null ? "null" : obj.toString());
            }
            this.f15943b = sb.toString();
            this.f15942a = stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(46) + 1).split("\\$")[0];
        }
    }

    /* renamed from: b */
    public static int m18101b(Object... objArr) {
        if (m18109j(3)) {
            return 0;
        }
        c cVar = new c(Thread.currentThread().getStackTrace()[3], objArr);
        m18112m(cVar.f15943b, cVar.f15942a);
        return Log.d(cVar.f15942a, cVar.f15943b);
    }

    /* renamed from: c */
    public static int m18102c(Object... objArr) {
        if (m18109j(6)) {
            return 0;
        }
        c cVar = new c(Thread.currentThread().getStackTrace()[3], objArr);
        m18112m(cVar.f15943b, cVar.f15942a);
        return Log.e(cVar.f15942a, cVar.f15943b);
    }

    /* renamed from: d */
    public static String m18103d() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory());
            String str = File.separator;
            sb.append(str);
            sb.append("U");
            sb.append(str);
            sb.append("Webinars");
            sb.append(str);
            String string = sb.toString();
            File file = new File(string);
            if (file.exists()) {
                return string;
            }
            file.mkdir();
            return string;
        } catch (Exception e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: e */
    public static String m18104e() {
        if (f15938j == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(f15938j.getFilesDir().toString());
            String str = File.separator;
            sb.append(str);
            sb.append("DebugLog");
            sb.append(str);
            sb.append("Webinars");
            sb.append(str);
            return sb.toString();
        } catch (Exception e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: f */
    public static File m18105f() {
        return m18106g(m18107h(true).toString());
    }

    /* renamed from: g */
    public static File m18106g(String str) throws IOException {
        String str2;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = f15934f;
        synchronized (simpleDateFormat) {
            str2 = simpleDateFormat.format(date) + "_webinars.txt";
        }
        File file = new File(str, str2);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException unused) {
            return null;
        }
    }

    /* renamed from: h */
    public static String m18107h(boolean z8) {
        String strM18104e = z8 ? m18104e() : m18103d();
        File file = new File(strM18104e);
        if (!file.exists()) {
            file.mkdir();
        }
        return strM18104e;
    }

    /* renamed from: i */
    public static int m18108i(Object... objArr) {
        if (m18109j(4)) {
            return 0;
        }
        c cVar = new c(Thread.currentThread().getStackTrace()[3], objArr);
        m18112m(cVar.f15943b, cVar.f15942a);
        return Log.i(cVar.f15942a, cVar.f15943b);
    }

    /* renamed from: j */
    public static boolean m18109j(int i9) {
        if (f15929a) {
            return false;
        }
        int i10 = f15933e;
        return (i10 == -1 || i9 < i10) && !Log.isLoggable("LOG_TAG_BC", 2);
    }

    /* renamed from: k */
    public static int m18110k(Object... objArr) {
        if (m18109j(2)) {
            return 0;
        }
        c cVar = new c(Thread.currentThread().getStackTrace()[3], objArr);
        m18112m(cVar.f15943b, cVar.f15942a);
        return Log.v(cVar.f15942a, cVar.f15943b);
    }

    /* renamed from: l */
    public static int m18111l(Object... objArr) {
        if (m18109j(5)) {
            return 0;
        }
        c cVar = new c(Thread.currentThread().getStackTrace()[3], objArr);
        m18112m(cVar.f15943b, cVar.f15942a);
        return Log.w(cVar.f15942a, cVar.f15943b);
    }

    /* renamed from: m */
    public static void m18112m(String str, String str2) {
        ThreadPoolExecutor threadPoolExecutor = f15937i;
        if (threadPoolExecutor == null) {
            return;
        }
        threadPoolExecutor.execute(new b(str, str2));
    }

    /* renamed from: n */
    public static void m18113n(String str, String str2) throws IOException {
        String str3 = new SimpleDateFormat("MM/dd HH:mm:ss.S", Locale.US).format(new Date(System.currentTimeMillis()));
        if (f15935g == null) {
            File fileM18105f = m18105f();
            if (fileM18105f == null) {
                return;
            } else {
                try {
                    f15935g = new FileOutputStream(fileM18105f, true);
                } catch (FileNotFoundException unused) {
                }
            }
        }
        if (f15935g == null) {
            return;
        }
        try {
            f15935g.write(((("======== " + str2 + " ========\n") + "timestamp: " + str3 + "\n") + str + "\n\n").getBytes());
            f15935g.flush();
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }
}
