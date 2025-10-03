package com.cyberlink.you.utility;

import android.os.AsyncTask;
import android.util.Log;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.you.chat.p034e.C2889b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import p116k4.C5154j;
import p209u2.C6369f;
import p209u2.C6370g;

/* renamed from: com.cyberlink.you.utility.a */
/* loaded from: classes.dex */
public class C3197a {

    /* renamed from: h */
    public static final Object f14748h = new Object();

    /* renamed from: i */
    public static final Map<String, a> f14749i = new HashMap();

    /* renamed from: a */
    public String f14750a;

    /* renamed from: b */
    public String f14751b;

    /* renamed from: c */
    public b f14752c;

    /* renamed from: d */
    public boolean f14753d;

    /* renamed from: e */
    public final String f14754e = CLUtility.m16537g1() + File.separator;

    /* renamed from: f */
    public int f14755f = 0;

    /* renamed from: g */
    public int f14756g = -1;

    /* renamed from: com.cyberlink.you.utility.a$a */
    public class a extends AsyncTask<String, Integer, Boolean> {

        /* renamed from: a */
        public String f14757a = null;

        /* renamed from: b */
        public String f14758b = null;

        /* renamed from: d */
        public int f14760d = 0;

        /* renamed from: e */
        public C1199a f14761e = null;

        /* renamed from: c */
        public Map<String, b> f14759c = new HashMap();

        public a() {
        }

        /* renamed from: d */
        public final void m17000d(String str, b bVar) {
            if (str == null) {
                str = UUID.randomUUID().toString();
            }
            this.f14759c.put(str, bVar);
        }

        /* renamed from: e */
        public final void m17001e(String str) throws IOException {
            if (this.f14761e == null) {
                return;
            }
            File file = new File(str);
            File file2 = new File(str + ".dec");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        C2889b.m14298h().m14303e(fileInputStream, fileOutputStream, this.f14761e);
                        C6369f.m24463e(file);
                        file2.renameTo(file);
                        fileOutputStream.close();
                        fileInputStream.close();
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:102:0x01b6  */
        /* JADX WARN: Removed duplicated region for block: B:112:0x01e7  */
        /* JADX WARN: Removed duplicated region for block: B:122:0x0203  */
        /* JADX WARN: Removed duplicated region for block: B:125:0x0218  */
        /* JADX WARN: Removed duplicated region for block: B:155:0x01f6 A[ADDED_TO_REGION, EDGE_INSN: B:155:0x01f6->B:116:0x01f6 BREAK  A[LOOP:0: B:3:0x000c->B:115:0x01f2], REMOVE, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:161:? A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x018f  */
        /* JADX WARN: Removed duplicated region for block: B:95:0x01a4 A[Catch: Exception -> 0x01a8, TRY_LEAVE, TryCatch #14 {Exception -> 0x01a8, blocks: (B:93:0x0192, B:95:0x01a4), top: B:144:0x0192 }] */
        /* renamed from: f */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean m17002f(String str, String... strArr) throws Throwable {
            HttpURLConnection httpURLConnection;
            Throwable th;
            boolean z8;
            boolean z9;
            File file;
            File file2;
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            HttpURLConnection httpURLConnection2 = null;
            while (true) {
                boolean z10 = true;
                try {
                    try {
                        if (this.f14760d > 0) {
                            Thread.sleep(500L);
                        }
                        this.f14757a = strArr[0];
                        httpURLConnection = (HttpURLConnection) new URL(this.f14757a).openConnection();
                        try {
                            if (C3197a.this.f14756g > 0) {
                                httpURLConnection.setConnectTimeout(C3197a.this.f14756g);
                                httpURLConnection.setReadTimeout(C3197a.this.f14756g);
                            }
                            httpURLConnection.connect();
                        } catch (SocketTimeoutException e9) {
                            e = e9;
                        } catch (Exception e10) {
                            e = e10;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        C6370g.m24480b(inputStream);
                        C6370g.m24480b(fileOutputStream);
                        if (httpURLConnection2 != null) {
                        }
                        try {
                            file = new File(str);
                            File file3 = new File(this.f14758b);
                            if (!file.exists()) {
                            }
                        } catch (Exception e11) {
                            e11.printStackTrace();
                            throw th;
                        }
                    }
                } catch (SocketTimeoutException e12) {
                    e = e12;
                    httpURLConnection = httpURLConnection2;
                } catch (Exception e13) {
                    e = e13;
                }
                if (httpURLConnection.getResponseCode() != 200) {
                    C6370g.m24480b(inputStream);
                    C6370g.m24480b(fileOutputStream);
                    httpURLConnection.disconnect();
                    try {
                        File file4 = new File(str);
                        File file5 = new File(this.f14758b);
                        if (file4.exists()) {
                            if (file5.exists()) {
                                CLUtility.m16447I(file5);
                            }
                            file4.renameTo(file5);
                        }
                    } catch (Exception e14) {
                        e14.printStackTrace();
                    }
                    return false;
                }
                int contentLength = httpURLConnection.getContentLength();
                inputStream = httpURLConnection.getInputStream();
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    byte[] bArr = new byte[4096];
                    int i9 = 0;
                    while (true) {
                        int i10 = inputStream.read(bArr);
                        if (i10 == -1) {
                            z9 = true;
                            break;
                        }
                        if (isCancelled()) {
                            inputStream.close();
                            z9 = false;
                            break;
                        }
                        i9 += i10;
                        if (contentLength > 0) {
                            publishProgress(Integer.valueOf((i9 * 100) / contentLength), Integer.valueOf(i9), Integer.valueOf(contentLength));
                        }
                        fileOutputStream2.write(bArr, 0, i10);
                    }
                } catch (SocketTimeoutException e15) {
                    e = e15;
                    fileOutputStream = fileOutputStream2;
                    z8 = true;
                    try {
                        this.f14760d++;
                        Log.d("test", "exception = " + e.toString());
                        e.printStackTrace();
                        C6370g.m24480b(inputStream);
                        C6370g.m24480b(fileOutputStream);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        try {
                            file2 = new File(str);
                            new File(this.f14758b);
                            if (file2.exists()) {
                                file2.delete();
                            }
                        } catch (Exception e16) {
                            e16.printStackTrace();
                        }
                        fileOutputStream2 = fileOutputStream;
                        z9 = false;
                        if (C3197a.this.f14753d) {
                        }
                        if (!z10) {
                            break;
                        }
                        break;
                        return z9;
                    } catch (Throwable th4) {
                        th = th4;
                        z10 = z8;
                        httpURLConnection2 = httpURLConnection;
                        C6370g.m24480b(inputStream);
                        C6370g.m24480b(fileOutputStream);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        file = new File(str);
                        File file32 = new File(this.f14758b);
                        if (!file.exists()) {
                            throw th;
                        }
                        if (!z10) {
                            file.delete();
                            throw th;
                        }
                        if (file32.exists()) {
                            CLUtility.m16447I(file32);
                        }
                        file.renameTo(file32);
                        throw th;
                    }
                } catch (Exception e17) {
                    e = e17;
                } catch (Throwable th5) {
                    th = th5;
                }
                try {
                    m17001e(str);
                    C6370g.m24480b(inputStream);
                    C6370g.m24480b(fileOutputStream2);
                    httpURLConnection.disconnect();
                    try {
                        File file6 = new File(str);
                        File file7 = new File(this.f14758b);
                        if (file6.exists()) {
                            if (z9) {
                                if (file7.exists()) {
                                    CLUtility.m16447I(file7);
                                }
                                file6.renameTo(file7);
                            } else {
                                file6.delete();
                            }
                        }
                    } catch (Exception e18) {
                        e18.printStackTrace();
                    }
                    z10 = false;
                } catch (SocketTimeoutException e19) {
                    e = e19;
                    z8 = z9;
                    fileOutputStream = fileOutputStream2;
                    this.f14760d++;
                    Log.d("test", "exception = " + e.toString());
                    e.printStackTrace();
                    C6370g.m24480b(inputStream);
                    C6370g.m24480b(fileOutputStream);
                    if (httpURLConnection != null) {
                    }
                    file2 = new File(str);
                    new File(this.f14758b);
                    if (file2.exists()) {
                    }
                    fileOutputStream2 = fileOutputStream;
                    z9 = false;
                    if (C3197a.this.f14753d) {
                    }
                    if (!z10) {
                    }
                    return z9;
                } catch (Exception e20) {
                    e = e20;
                    fileOutputStream = fileOutputStream2;
                    httpURLConnection2 = httpURLConnection;
                    Log.d("test", "exception = " + e.toString());
                    e.printStackTrace();
                    C6370g.m24480b(inputStream);
                    C6370g.m24480b(fileOutputStream);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    try {
                        File file8 = new File(str);
                        new File(this.f14758b);
                        if (file8.exists()) {
                            file8.delete();
                        }
                    } catch (Exception e21) {
                        e21.printStackTrace();
                    }
                    httpURLConnection = httpURLConnection2;
                    z10 = false;
                    fileOutputStream2 = fileOutputStream;
                    z9 = false;
                    if (C3197a.this.f14753d) {
                    }
                    if (!z10) {
                    }
                    return z9;
                } catch (Throwable th6) {
                    th = th6;
                    z10 = z9;
                    fileOutputStream = fileOutputStream2;
                    httpURLConnection2 = httpURLConnection;
                    C6370g.m24480b(inputStream);
                    C6370g.m24480b(fileOutputStream);
                    if (httpURLConnection2 != null) {
                    }
                    file = new File(str);
                    File file322 = new File(this.f14758b);
                    if (!file.exists()) {
                    }
                }
                if (C3197a.this.f14753d) {
                    boolean z11 = (!z9 || CLUtility.m16474O2(C3197a.this.f14751b, C3197a.this.f14754e)) ? z9 : false;
                    if (!CLUtility.m16447I(new File(C3197a.this.f14751b))) {
                        Log.d("DownloadMediaHelper", "[doDownloadSticker] Delete Zip file fail");
                    }
                    z9 = z11;
                }
                if (!z10 || this.f14760d > C3197a.this.f14755f) {
                    break;
                    break;
                }
                fileOutputStream = fileOutputStream2;
                httpURLConnection2 = httpURLConnection;
            }
            return z9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(String... strArr) {
            Thread.currentThread().setName("DownloadTask AsyncTask");
            if (this.f14758b == null) {
                return Boolean.FALSE;
            }
            String str = this.f14758b + ".temp";
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
                return Boolean.valueOf(m17002f(str, strArr));
            } catch (Exception e9) {
                e9.printStackTrace();
                return Boolean.FALSE;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                Iterator<b> it = this.f14759c.values().iterator();
                while (it.hasNext()) {
                    it.next().mo9123b(this.f14758b);
                }
            } else {
                Iterator<b> it2 = this.f14759c.values().iterator();
                while (it2.hasNext()) {
                    it2.next().mo9122a();
                }
            }
            synchronized (C3197a.f14748h) {
                C3197a.f14749i.remove(this.f14757a + this.f14758b);
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public void onProgressUpdate(Integer... numArr) {
            Iterator<b> it = this.f14759c.values().iterator();
            while (it.hasNext()) {
                it.next().mo9124c(numArr[0].intValue(), numArr[1].intValue(), numArr[2].intValue());
            }
        }

        /* renamed from: j */
        public final void m17006j(String str) {
            this.f14758b = str;
        }

        /* renamed from: k */
        public final void m17007k(C1199a c1199a) {
            this.f14761e = c1199a;
        }
    }

    /* renamed from: com.cyberlink.you.utility.a$b */
    public interface b {
        /* renamed from: a */
        void mo9122a();

        /* renamed from: b */
        void mo9123b(String str);

        /* renamed from: c */
        void mo9124c(int i9, int i10, int i11);
    }

    /* renamed from: i */
    public static boolean m16985i(String str, String str2) {
        synchronized (f14748h) {
            if (str == null || str2 == null) {
                return false;
            }
            return f14749i.containsKey(str + str2);
        }
    }

    /* renamed from: h */
    public boolean m16986h() {
        synchronized (f14748h) {
            if (this.f14750a != null && this.f14751b != null) {
                return f14749i.containsKey(this.f14750a + this.f14751b);
            }
            return false;
        }
    }

    /* renamed from: j */
    public void m16987j(b bVar) {
        this.f14752c = bVar;
    }

    /* renamed from: k */
    public void m16988k(int i9) {
        this.f14756g = i9;
    }

    /* renamed from: l */
    public void m16989l(boolean z8) {
        this.f14753d = z8;
    }

    /* renamed from: m */
    public void m16990m(String str) {
        this.f14751b = str;
    }

    /* renamed from: n */
    public void m16991n(int i9) {
        this.f14755f = i9;
    }

    /* renamed from: o */
    public void m16992o(String str) {
        this.f14750a = str;
    }

    /* renamed from: p */
    public boolean m16993p() {
        return m16995r(null, this.f14750a, null, this.f14751b, this.f14752c);
    }

    /* renamed from: q */
    public boolean m16994q(String str, C1199a c1199a, String str2, b bVar) {
        return m16995r(null, str, c1199a, str2, bVar);
    }

    /* renamed from: r */
    public final boolean m16995r(String str, String str2, C1199a c1199a, String str3, b bVar) {
        synchronized (f14748h) {
            String str4 = str2 + str3;
            if (m16985i(str2, str3)) {
                a aVar = f14749i.get(str4);
                if (aVar != null) {
                    aVar.m17000d(str, bVar);
                } else {
                    Log.d("DownloadMediaHelper", "the url is downloaded");
                }
                return true;
            }
            Log.d("DownloadMediaHelper", "start download=" + str2);
            a aVar2 = new a();
            aVar2.m17006j(str3);
            aVar2.m17000d(str, bVar);
            aVar2.m17007k(c1199a);
            aVar2.execute(str2);
            f14749i.put(str4, aVar2);
            return false;
        }
    }

    /* renamed from: s */
    public boolean m16996s(String str, String str2, b bVar) {
        return m16995r(null, str, null, str2, bVar);
    }
}
