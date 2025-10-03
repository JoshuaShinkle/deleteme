package com.cyberlink.you.feedback;

import android.util.Pair;
import com.cyberlink.you.feedback.NetworkManager;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.p159io.IOUtils;

/* renamed from: com.cyberlink.you.feedback.d */
/* loaded from: classes.dex */
public class C3031d {

    /* renamed from: a */
    public ArrayList<Pair<String, String>> f13482a;

    /* renamed from: b */
    public ArrayList<Pair<String, String>> f13483b;

    /* renamed from: c */
    public ArrayList<Pair<String, a>> f13484c;

    /* renamed from: d */
    public String f13485d;

    /* renamed from: e */
    public String f13486e;

    /* renamed from: f */
    public String f13487f;

    /* renamed from: com.cyberlink.you.feedback.d$a */
    public class a {

        /* renamed from: a */
        public String f13488a;

        /* renamed from: b */
        public byte[] f13489b;

        /* renamed from: c */
        public String f13490c;

        public a(byte[] bArr, String str, String str2) {
            this.f13489b = bArr;
            this.f13488a = str;
            this.f13490c = str2;
        }
    }

    public C3031d(String str) {
        this(str, 0);
    }

    /* renamed from: a */
    public final void m15497a(PrintWriter printWriter, String str, a aVar, OutputStream outputStream, String str2) throws IOException {
        try {
            printWriter.append((CharSequence) ("--" + str2)).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append((CharSequence) ("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + aVar.f13490c + "\"")).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            StringBuilder sb = new StringBuilder();
            sb.append("Content-Type: ");
            sb.append(aVar.f13488a);
            printWriter.append((CharSequence) sb.toString()).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.flush();
            try {
                outputStream.write(aVar.f13489b);
                outputStream.write(IOUtils.LINE_SEPARATOR_WINDOWS.getBytes());
                outputStream.flush();
            } catch (IOException e9) {
                e9.printStackTrace();
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: b */
    public final void m15498b(PrintWriter printWriter, String str, String str2, String str3) {
        try {
            printWriter.append((CharSequence) ("--" + str3)).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append((CharSequence) ("Content-Disposition: form-data; name=\"" + str + "\"")).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append((CharSequence) str2).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.flush();
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: c */
    public <T> void m15499c(String str, T t8) {
        if (t8 != null) {
            this.f13482a.add(new Pair<>(str, t8.toString()));
        }
    }

    /* renamed from: d */
    public <T> void m15500d(String str, T t8) {
        if (t8 != null) {
            this.f13483b.add(new Pair<>(str, t8.toString()));
        }
    }

    /* renamed from: e */
    public void m15501e(String str, byte[] bArr, String str2, String str3) {
        if (bArr != null) {
            this.f13484c.add(new Pair<>(str, new a(bArr, str2, str3)));
        }
    }

    /* renamed from: f */
    public String m15502f() {
        return this.f13485d;
    }

    /* renamed from: g */
    public ArrayList<Pair<String, String>> m15503g() {
        return this.f13482a;
    }

    /* renamed from: h */
    public String m15504h(String str) {
        Iterator<Pair<String, String>> it = this.f13483b.iterator();
        while (it.hasNext()) {
            Pair<String, String> next = it.next();
            if (((String) next.first).equals(str)) {
                return (String) next.second;
            }
        }
        return null;
    }

    /* renamed from: i */
    public String m15505i() {
        String str = "";
        if (this.f13483b.size() > 0) {
            Iterator<Pair<String, String>> it = this.f13483b.iterator();
            boolean z8 = false;
            while (it.hasNext()) {
                Pair<String, String> next = it.next();
                String str2 = (String) next.first;
                String str3 = (String) next.second;
                if (!str2.isEmpty()) {
                    if (z8) {
                        str = str + "&";
                    } else {
                        z8 = true;
                    }
                    str = str + str2 + "=" + str3;
                }
            }
        }
        return str;
    }

    /* renamed from: j */
    public String m15506j() {
        String str;
        String str2 = "".equals(this.f13485d) ? "" : this.f13485d;
        if (this.f13483b.size() > 0) {
            Iterator<Pair<String, String>> it = this.f13483b.iterator();
            boolean z8 = false;
            while (it.hasNext()) {
                Pair<String, String> next = it.next();
                String str3 = (String) next.first;
                String strReplaceAll = (String) next.second;
                if (!str3.isEmpty()) {
                    if (z8) {
                        str = str2 + "&";
                    } else {
                        str = str2 + "?";
                        z8 = true;
                    }
                    try {
                        strReplaceAll = URLEncoder.encode(strReplaceAll, "UTF-8").replaceAll("\\+", "%20");
                    } catch (UnsupportedEncodingException e9) {
                        e9.printStackTrace();
                    }
                    str2 = str + str3 + "=" + strReplaceAll;
                }
            }
        }
        return str2;
    }

    /* renamed from: k */
    public boolean m15507k(PrintWriter printWriter, OutputStream outputStream, String str) throws IOException {
        if (this.f13483b.size() <= 0) {
            return true;
        }
        Iterator<Pair<String, String>> it = this.f13483b.iterator();
        while (it.hasNext()) {
            Pair<String, String> next = it.next();
            String str2 = (String) next.first;
            String str3 = (String) next.second;
            if (!str2.isEmpty()) {
                m15498b(printWriter, str2, str3, str);
            }
        }
        Iterator<Pair<String, a>> it2 = this.f13484c.iterator();
        while (it2.hasNext()) {
            Pair<String, a> next2 = it2.next();
            String str4 = (String) next2.first;
            a aVar = (a) next2.second;
            if (!str4.isEmpty() && aVar != null) {
                m15497a(printWriter, str4, aVar, outputStream, str);
            }
        }
        printWriter.append((CharSequence) ("--" + str + "--")).append(IOUtils.LINE_SEPARATOR_WINDOWS).flush();
        printWriter.close();
        return true;
    }

    public C3031d(String str, int i9) {
        this.f13482a = new ArrayList<>();
        this.f13483b = new ArrayList<>();
        this.f13484c = new ArrayList<>();
        this.f13485d = "";
        this.f13486e = "";
        this.f13487f = "";
        NetworkManager.C3017d c3017d = NetworkManager.f13379e;
        Long l9 = c3017d.f13390c.get(str);
        c3017d.f13390c.put(str, l9 == null ? 1L : Long.valueOf(l9.longValue() + 1));
        try {
            if (str.length() == 0) {
                return;
            }
            String[] strArrSplit = str.split("\\?");
            if (strArrSplit.length == 0) {
                return;
            }
            this.f13485d = strArrSplit[0];
            if (strArrSplit.length > 1) {
                for (String str2 : strArrSplit[1].split("&")) {
                    String[] strArrSplit2 = str2.split("=", i9);
                    this.f13483b.add(new Pair<>(strArrSplit2[0], strArrSplit2.length == 2 ? strArrSplit2[1] : ""));
                }
            }
            this.f13486e = new URL(strArrSplit[0]).getHost();
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }
}
