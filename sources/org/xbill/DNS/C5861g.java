package org.xbill.DNS;

import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

/* renamed from: org.xbill.DNS.g */
/* loaded from: classes3.dex */
public class C5861g {

    /* renamed from: h */
    public static Integer[] f20265h = new Integer[64];

    /* renamed from: c */
    public String f20268c;

    /* renamed from: d */
    public int f20269d;

    /* renamed from: e */
    public String f20270e;

    /* renamed from: g */
    public boolean f20272g;

    /* renamed from: a */
    public HashMap f20266a = new HashMap();

    /* renamed from: b */
    public HashMap f20267b = new HashMap();

    /* renamed from: f */
    public int f20271f = Integer.MAX_VALUE;

    static {
        int i9 = 0;
        while (true) {
            Integer[] numArr = f20265h;
            if (i9 >= numArr.length) {
                return;
            }
            numArr[i9] = new Integer(i9);
            i9++;
        }
    }

    public C5861g(String str, int i9) {
        this.f20268c = str;
        this.f20269d = i9;
    }

    /* renamed from: j */
    public static Integer m23250j(int i9) {
        if (i9 >= 0) {
            Integer[] numArr = f20265h;
            if (i9 < numArr.length) {
                return numArr[i9];
            }
        }
        return new Integer(i9);
    }

    /* renamed from: a */
    public void m23251a(int i9, String str) {
        mo23232d(i9);
        Integer numM23250j = m23250j(i9);
        String strM23255f = m23255f(str);
        this.f20266a.put(strM23255f, numM23250j);
        this.f20267b.put(numM23250j, strM23255f);
    }

    /* renamed from: b */
    public void m23252b(int i9, String str) {
        mo23232d(i9);
        Integer numM23250j = m23250j(i9);
        this.f20266a.put(m23255f(str), numM23250j);
    }

    /* renamed from: c */
    public void m23253c(C5861g c5861g) {
        if (this.f20269d == c5861g.f20269d) {
            this.f20266a.putAll(c5861g.f20266a);
            this.f20267b.putAll(c5861g.f20267b);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(c5861g.f20268c);
            stringBuffer.append(": wordcases do not match");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    /* renamed from: d */
    public void mo23232d(int i9) {
        if (i9 < 0 || i9 > this.f20271f) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.f20268c);
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(i9);
            stringBuffer.append("is out of range");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    /* renamed from: e */
    public String m23254e(int i9) {
        mo23232d(i9);
        String str = (String) this.f20267b.get(m23250j(i9));
        if (str != null) {
            return str;
        }
        String string = Integer.toString(i9);
        if (this.f20270e == null) {
            return string;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f20270e);
        stringBuffer.append(string);
        return stringBuffer.toString();
    }

    /* renamed from: f */
    public final String m23255f(String str) {
        int i9 = this.f20269d;
        return i9 == 2 ? str.toUpperCase() : i9 == 3 ? str.toLowerCase() : str;
    }

    /* renamed from: g */
    public void m23256g(int i9) {
        this.f20271f = i9;
    }

    /* renamed from: h */
    public void m23257h(boolean z8) {
        this.f20272g = z8;
    }

    /* renamed from: i */
    public void m23258i(String str) {
        this.f20270e = m23255f(str);
    }
}
