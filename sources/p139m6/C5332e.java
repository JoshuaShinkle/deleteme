package p139m6;

import com.google.common.net.HttpHeaders;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.C5239v;
import kotlin.text.C5255l;
import okhttp3.C5490g;
import okhttp3.C5511l;
import okhttp3.C5517r;
import okhttp3.C5518s;
import okhttp3.C5525z;
import okhttp3.InterfaceC5512m;
import okio.ByteString;
import p007a6.C0042f;
import p098i6.C5057d;
import p168p6.C6113j;
import p204t6.C6322c;

/* renamed from: m6.e */
/* loaded from: classes.dex */
public final class C5332e {

    /* renamed from: a */
    public static final ByteString f18166a;

    /* renamed from: b */
    public static final ByteString f18167b;

    static {
        ByteString.C5526a c5526a = ByteString.f19095d;
        f18166a = c5526a.m21901d("\"\\");
        f18167b = c5526a.m21901d("\t ,=");
    }

    /* renamed from: a */
    public static final List<C5490g> m20945a(C5517r c5517r, String str) {
        C0042f.m158e(c5517r, "<this>");
        C0042f.m158e(str, "headerName");
        ArrayList arrayList = new ArrayList();
        int size = c5517r.size();
        for (int i9 = 0; i9 < size; i9++) {
            if (C5255l.m20513l(str, c5517r.m21627b(i9), true)) {
                try {
                    m20947c(new C6322c().mo24221j(c5517r.m21629d(i9)), arrayList);
                } catch (EOFException e9) {
                    C6113j.f20745a.m23447g().m23440j("Unable to parse challenge", 5, e9);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static final boolean m20946b(C5525z c5525z) {
        C0042f.m158e(c5525z, "<this>");
        if (C0042f.m154a(c5525z.m21847K().m21809g(), "HEAD")) {
            return false;
        }
        int iM21853x = c5525z.m21853x();
        return (((iM21853x >= 100 && iM21853x < 200) || iM21853x == 204 || iM21853x == 304) && C5057d.m19808v(c5525z) == -1 && !C5255l.m20513l("chunked", C5525z.m21837B(c5525z, HttpHeaders.TRANSFER_ENCODING, null, 2, null), true)) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ba, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ba, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0083  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m20947c(C6322c c6322c, List<C5490g> list) throws EOFException {
        String strM20949e;
        int iM19771K;
        LinkedHashMap linkedHashMap;
        while (true) {
            String strM20949e2 = null;
            while (true) {
                if (strM20949e2 == null) {
                    m20951g(c6322c);
                    strM20949e2 = m20949e(c6322c);
                    if (strM20949e2 == null) {
                        return;
                    }
                }
                boolean zM20951g = m20951g(c6322c);
                strM20949e = m20949e(c6322c);
                if (strM20949e == null) {
                    if (c6322c.mo24218g()) {
                        list.add(new C5490g(strM20949e2, C5239v.m20435c()));
                        return;
                    }
                    return;
                }
                iM19771K = C5057d.m19771K(c6322c, (byte) 61);
                boolean zM20951g2 = m20951g(c6322c);
                if (zM20951g || (!zM20951g2 && !c6322c.mo24218g())) {
                    linkedHashMap = new LinkedHashMap();
                    int iM19771K2 = iM19771K + C5057d.m19771K(c6322c, (byte) 61);
                    while (true) {
                        if (strM20949e == null) {
                            strM20949e = m20949e(c6322c);
                            if (!m20951g(c6322c)) {
                                iM19771K2 = C5057d.m19771K(c6322c, (byte) 61);
                                if (iM19771K2 == 0) {
                                    if (iM19771K2 > 1 || m20951g(c6322c)) {
                                        return;
                                    }
                                    String strM20948d = m20952h(c6322c, (byte) 34) ? m20948d(c6322c) : m20949e(c6322c);
                                    if (strM20948d == null || ((String) linkedHashMap.put(strM20949e, strM20948d)) != null) {
                                        return;
                                    }
                                    if (!m20951g(c6322c) && !c6322c.mo24218g()) {
                                        return;
                                    } else {
                                        strM20949e = null;
                                    }
                                }
                            }
                        } else if (iM19771K2 == 0) {
                            break;
                        }
                    }
                }
                list.add(new C5490g(strM20949e2, linkedHashMap));
                strM20949e2 = strM20949e;
            }
            Map mapSingletonMap = Collections.singletonMap(null, strM20949e + C5255l.m20517p("=", iM19771K));
            C0042f.m157d(mapSingletonMap, "singletonMap<String, Str…ek + \"=\".repeat(eqCount))");
            list.add(new C5490g(strM20949e2, mapSingletonMap));
        }
    }

    /* renamed from: d */
    public static final String m20948d(C6322c c6322c) throws EOFException {
        if (!(c6322c.readByte() == 34)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        C6322c c6322c2 = new C6322c();
        while (true) {
            long jM24189B = c6322c.m24189B(f18166a);
            if (jM24189B == -1) {
                return null;
            }
            if (c6322c.m24236z(jM24189B) == 34) {
                c6322c2.mo21082q(c6322c, jM24189B);
                c6322c.readByte();
                return c6322c2.m24197J();
            }
            if (c6322c.size() == jM24189B + 1) {
                return null;
            }
            c6322c2.mo21082q(c6322c, jM24189B);
            c6322c.readByte();
            c6322c2.mo21082q(c6322c, 1L);
        }
    }

    /* renamed from: e */
    public static final String m20949e(C6322c c6322c) {
        long jM24189B = c6322c.m24189B(f18167b);
        if (jM24189B == -1) {
            jM24189B = c6322c.size();
        }
        if (jM24189B != 0) {
            return c6322c.m24198K(jM24189B);
        }
        return null;
    }

    /* renamed from: f */
    public static final void m20950f(InterfaceC5512m interfaceC5512m, C5518s c5518s, C5517r c5517r) {
        C0042f.m158e(interfaceC5512m, "<this>");
        C0042f.m158e(c5518s, "url");
        C0042f.m158e(c5517r, "headers");
        if (interfaceC5512m == InterfaceC5512m.f18923b) {
            return;
        }
        List<C5511l> listM21581e = C5511l.f18908j.m21581e(c5518s, c5517r);
        if (listM21581e.isEmpty()) {
            return;
        }
        interfaceC5512m.mo21585a(c5518s, listM21581e);
    }

    /* renamed from: g */
    public static final boolean m20951g(C6322c c6322c) throws EOFException {
        boolean z8 = false;
        while (!c6322c.mo24218g()) {
            byte bM24236z = c6322c.m24236z(0L);
            boolean z9 = true;
            if (bM24236z != 44) {
                if (bM24236z != 32 && bM24236z != 9) {
                    z9 = false;
                }
                if (!z9) {
                    break;
                }
                c6322c.readByte();
            } else {
                c6322c.readByte();
                z8 = true;
            }
        }
        return z8;
    }

    /* renamed from: h */
    public static final boolean m20952h(C6322c c6322c, byte b9) {
        return !c6322c.mo24218g() && c6322c.m24236z(0L) == b9;
    }
}
