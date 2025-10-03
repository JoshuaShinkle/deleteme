package com.cyberlink.clrtc;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.AbstractC5524y;
import okhttp3.C5520u;
import okhttp3.C5521v;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import p209u2.C6369f;

/* renamed from: com.cyberlink.clrtc.o */
/* loaded from: classes.dex */
public class C1073o {

    /* renamed from: a */
    public static final String f5423a = "o";

    /* renamed from: b */
    public static final SimpleDateFormat f5424b;

    /* renamed from: c */
    public static final SimpleDateFormat f5425c;

    /* renamed from: d */
    public static final ExecutorService f5426d;

    static {
        Locale locale = Locale.US;
        f5424b = new SimpleDateFormat("yyyyMMddHHmmss", locale);
        f5425c = new SimpleDateFormat("yyyy-MM-dd", locale);
        f5426d = new ThreadPoolExecutor(0, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    /* renamed from: f */
    public static ArrayList<File> m5024f(File file, long j9, long j10, String str) {
        System.currentTimeMillis();
        List<File> listM5029k = m5029k(file);
        ArrayList<File> arrayList = new ArrayList<>();
        for (File file2 : listM5029k) {
            long jLastModified = file2.lastModified();
            if (j9 <= jLastModified && jLastModified < j10) {
                arrayList.add(file2);
            } else if (file2.getName().contains(str)) {
                arrayList.add(file2);
            }
        }
        return arrayList;
    }

    /* renamed from: g */
    public static ArrayList<File> m5025g(File file, File file2, boolean z8) {
        System.currentTimeMillis();
        ArrayList<File> arrayList = new ArrayList<>();
        arrayList.addAll(m5030l(file, z8 ? 3 : 1));
        arrayList.addAll(m5030l(file2, z8 ? 2 : 1));
        return arrayList;
    }

    /* renamed from: h */
    public static File m5026h(Context context, int i9, C1019i c1019i, boolean z8) {
        String string;
        SimpleDateFormat simpleDateFormat = f5424b;
        synchronized (simpleDateFormat) {
            StringBuilder sb = new StringBuilder();
            sb.append(c1019i.f5251e);
            sb.append("_");
            sb.append(i9);
            sb.append("_");
            sb.append(c1019i.f5249c);
            sb.append("_");
            sb.append(simpleDateFormat.format(new Date()));
            sb.append("_");
            sb.append(z8 ? "pastLog" : "log");
            sb.append(".zip");
            string = sb.toString();
        }
        return new File(C6369f.m24468j(context), string);
    }

    /* renamed from: i */
    public static File m5027i(Context context, String str, long j9, String str2) {
        return new File(C6369f.m24468j(context), str + "_" + j9 + "_A_" + str2 + ".zip");
    }

    /* renamed from: j */
    public static String m5028j(long j9) {
        String str;
        SimpleDateFormat simpleDateFormat = f5425c;
        synchronized (simpleDateFormat) {
            str = simpleDateFormat.format(new Date(j9));
        }
        return str;
    }

    /* renamed from: k */
    public static List<File> m5029k(File file) {
        if (file == null || !file.isDirectory()) {
            return Collections.emptyList();
        }
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.cyberlink.clrtc.k
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str) {
                return C1073o.m5031m(file2, str);
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return Collections.emptyList();
        }
        List<File> listAsList = Arrays.asList(fileArrListFiles);
        Collections.sort(listAsList, new Comparator() { // from class: com.cyberlink.clrtc.l
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return C1073o.m5032n((File) obj, (File) obj2);
            }
        });
        return listAsList;
    }

    /* renamed from: l */
    public static List<File> m5030l(File file, int i9) {
        List<File> listM5029k = m5029k(file);
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < i9 && i10 < listM5029k.size(); i10++) {
            arrayList.add(listM5029k.get(i10));
        }
        return arrayList;
    }

    /* renamed from: m */
    public static /* synthetic */ boolean m5031m(File file, String str) {
        return str.endsWith(".txt") || str.endsWith(".log");
    }

    /* renamed from: n */
    public static /* synthetic */ int m5032n(File file, File file2) {
        long jLastModified = file.lastModified();
        long jLastModified2 = file2.lastModified();
        if (jLastModified > jLastModified2) {
            return -1;
        }
        return jLastModified < jLastModified2 ? 1 : 0;
    }

    /* renamed from: o */
    public static /* synthetic */ void m5033o(Context context, int i9, C1019i c1019i, boolean z8, ArrayList arrayList, String str) {
        File fileM5026h = m5026h(context, i9, c1019i, z8);
        try {
            try {
                C6369f.m24478t(fileM5026h, arrayList);
                m5039u(c1019i, str, fileM5026h);
            } catch (Exception e9) {
                Log.e(f5423a, "Something went wrong", e9);
            }
        } finally {
            fileM5026h.delete();
        }
    }

    /* renamed from: p */
    public static /* synthetic */ void m5034p(C1019i c1019i, String str, File file) {
        try {
            m5039u(c1019i, str, file);
        } catch (IOException e9) {
            Log.e(f5423a, "Something went wrong", e9);
        }
    }

    /* renamed from: q */
    public static /* synthetic */ void m5035q(Context context, String str, long j9, String str2, ArrayList arrayList) {
        File fileM5027i = m5027i(context, str, j9, str2);
        try {
            try {
                C6369f.m24478t(fileM5027i, arrayList);
                HashMap map = new HashMap();
                map.put("filename", fileM5027i.getName());
                m5040v(fileM5027i, "https://clmeet-demo-01.cyberlink.com/ManagementCenter_beta/uploadAppLog", map);
            } catch (Exception e9) {
                Log.e(f5423a, "Something went wrong", e9);
            }
        } finally {
            fileM5027i.delete();
        }
    }

    /* renamed from: r */
    public static void m5036r(Context context, int i9, final C1019i c1019i, final String str, File file) {
        final File fileM5026h = m5026h(context, i9, c1019i, false);
        try {
            C6369f.m24478t(fileM5026h, Collections.singletonList(file));
            f5426d.submit(new Runnable() { // from class: com.cyberlink.clrtc.n
                @Override // java.lang.Runnable
                public final void run() {
                    C1073o.m5034p(c1019i, str, fileM5026h);
                }
            });
        } catch (IOException e9) {
            Log.e(f5423a, "Something went wrong", e9);
        }
    }

    /* renamed from: s */
    public static void m5037s(final Context context, final int i9, final C1019i c1019i, final String str, File file, File file2, final boolean z8) {
        final ArrayList<File> arrayListM5025g = m5025g(file, file2, z8);
        f5426d.submit(new Runnable() { // from class: com.cyberlink.clrtc.m
            @Override // java.lang.Runnable
            public final void run() {
                C1073o.m5033o(context, i9, c1019i, z8, arrayListM5025g, str);
            }
        });
    }

    /* renamed from: t */
    public static void m5038t(final Context context, File file, final long j9, final String str, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, -i10);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            long timeInMillis = calendar.getTimeInMillis();
            final String strM5028j = m5028j(timeInMillis);
            calendar.add(5, 1);
            final ArrayList<File> arrayListM5024f = m5024f(file, timeInMillis, calendar.getTimeInMillis(), strM5028j);
            if (!arrayListM5024f.isEmpty()) {
                f5426d.submit(new Runnable() { // from class: com.cyberlink.clrtc.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        C1073o.m5035q(context, strM5028j, j9, str, arrayListM5024f);
                    }
                });
            }
        }
    }

    /* renamed from: u */
    public static void m5039u(C1019i c1019i, String str, File file) {
        HashMap map = new HashMap();
        map.put("event_id", c1019i.f5251e);
        map.put("join_token", c1019i.f5248b);
        map.put("filename", file.getName());
        try {
            m5040v(file, str, map);
        } finally {
            file.delete();
        }
    }

    /* renamed from: v */
    public static void m5040v(File file, String str, HashMap<String, String> map) {
        C5523x.a aVarM21818g = new C5523x.a().m21820i(str).m21818g(new C5521v.a().m21724e(C5521v.f18974l).m21721b("files", file.getName(), AbstractC5524y.m21822c(C5520u.m21709f("application/octet-stream"), file)).m21723d());
        for (String str2 : map.keySet()) {
            aVarM21818g.m21812a(str2, map.get(str2));
        }
        System.currentTimeMillis();
        C5525z c5525zExecute = new C5522w().mo21256a(aVarM21818g.m21813b()).execute();
        if (c5525zExecute.m21840D()) {
            return;
        }
        throw new IOException("HttpClient called unsuccessfully: " + c5525zExecute);
    }
}
