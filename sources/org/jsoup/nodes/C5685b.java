package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jsoup.SerializationException;
import org.jsoup.nodes.Document;
import p060e8.C4772d;
import p070f8.C4794b;
import p080g8.C4972a;

/* renamed from: org.jsoup.nodes.b */
/* loaded from: classes.dex */
public class C5685b implements Iterable<C5684a>, Cloneable {

    /* renamed from: e */
    public static final String[] f20005e = new String[0];

    /* renamed from: b */
    public int f20006b = 0;

    /* renamed from: c */
    public String[] f20007c;

    /* renamed from: d */
    public String[] f20008d;

    /* renamed from: org.jsoup.nodes.b$a */
    public class a implements Iterator<C5684a> {

        /* renamed from: b */
        public int f20009b = 0;

        public a() {
        }

        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C5684a next() {
            C5685b c5685b = C5685b.this;
            String[] strArr = c5685b.f20007c;
            int i9 = this.f20009b;
            C5684a c5684a = new C5684a(strArr[i9], c5685b.f20008d[i9], c5685b);
            this.f20009b++;
            return c5684a;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20009b < C5685b.this.f20006b;
        }

        @Override // java.util.Iterator
        public void remove() {
            C5685b c5685b = C5685b.this;
            int i9 = this.f20009b - 1;
            this.f20009b = i9;
            c5685b.m22928w(i9);
        }
    }

    public C5685b() {
        String[] strArr = f20005e;
        this.f20007c = strArr;
        this.f20008d = strArr;
    }

    /* renamed from: h */
    public static String m22909h(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: j */
    public static String[] m22910j(String[] strArr, int i9) {
        String[] strArr2 = new String[i9];
        System.arraycopy(strArr, 0, strArr2, 0, Math.min(strArr.length, i9));
        return strArr2;
    }

    /* renamed from: c */
    public final void m22911c(String str, String str2) {
        m22914g(this.f20006b + 1);
        String[] strArr = this.f20007c;
        int i9 = this.f20006b;
        strArr[i9] = str;
        this.f20008d[i9] = str2;
        this.f20006b = i9 + 1;
    }

    /* renamed from: d */
    public void m22912d(C5685b c5685b) {
        if (c5685b.size() == 0) {
            return;
        }
        m22914g(this.f20006b + c5685b.f20006b);
        Iterator<C5684a> it = c5685b.iterator();
        while (it.hasNext()) {
            m22926u(it.next());
        }
    }

    /* renamed from: e */
    public List<C5684a> m22913e() {
        ArrayList arrayList = new ArrayList(this.f20006b);
        for (int i9 = 0; i9 < this.f20006b; i9++) {
            arrayList.add(this.f20008d[i9] == null ? new C4972a(this.f20007c[i9]) : new C5684a(this.f20007c[i9], this.f20008d[i9], this));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C5685b c5685b = (C5685b) obj;
        if (this.f20006b == c5685b.f20006b && Arrays.equals(this.f20007c, c5685b.f20007c)) {
            return Arrays.equals(this.f20008d, c5685b.f20008d);
        }
        return false;
    }

    /* renamed from: g */
    public final void m22914g(int i9) {
        C4772d.m18998d(i9 >= this.f20006b);
        String[] strArr = this.f20007c;
        int length = strArr.length;
        if (length >= i9) {
            return;
        }
        int i10 = length >= 4 ? this.f20006b * 2 : 4;
        if (i9 <= i10) {
            i9 = i10;
        }
        this.f20007c = m22910j(strArr, i9);
        this.f20008d = m22910j(this.f20008d, i9);
    }

    public int hashCode() {
        return (((this.f20006b * 31) + Arrays.hashCode(this.f20007c)) * 31) + Arrays.hashCode(this.f20008d);
    }

    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public C5685b clone() {
        try {
            C5685b c5685b = (C5685b) super.clone();
            c5685b.f20006b = this.f20006b;
            this.f20007c = m22910j(this.f20007c, this.f20006b);
            this.f20008d = m22910j(this.f20008d, this.f20006b);
            return c5685b;
        } catch (CloneNotSupportedException e9) {
            throw new RuntimeException(e9);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<C5684a> iterator() {
        return new a();
    }

    /* renamed from: k */
    public String m22916k(String str) {
        int iM22922q = m22922q(str);
        return iM22922q == -1 ? "" : m22909h(this.f20008d[iM22922q]);
    }

    /* renamed from: l */
    public String m22917l(String str) {
        int iM22923r = m22923r(str);
        return iM22923r == -1 ? "" : m22909h(this.f20008d[iM22923r]);
    }

    /* renamed from: m */
    public boolean m22918m(String str) {
        return m22922q(str) != -1;
    }

    /* renamed from: n */
    public boolean m22919n(String str) {
        return m22923r(str) != -1;
    }

    /* renamed from: o */
    public String m22920o() {
        StringBuilder sb = new StringBuilder();
        try {
            m22921p(sb, new Document("").m22830z0());
            return sb.toString();
        } catch (IOException e9) {
            throw new SerializationException(e9);
        }
    }

    /* renamed from: p */
    public final void m22921p(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        int i9 = this.f20006b;
        for (int i10 = 0; i10 < i9; i10++) {
            String str = this.f20007c[i10];
            String str2 = this.f20008d[i10];
            appendable.append(' ').append(str);
            if (!C5684a.m22900j(str, str2, outputSettings)) {
                appendable.append("=\"");
                if (str2 == null) {
                    str2 = "";
                }
                Entities.m22883e(appendable, str2, outputSettings, true, false, false);
                appendable.append('\"');
            }
        }
    }

    /* renamed from: q */
    public int m22922q(String str) {
        C4772d.m19004j(str);
        for (int i9 = 0; i9 < this.f20006b; i9++) {
            if (str.equals(this.f20007c[i9])) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: r */
    public final int m22923r(String str) {
        C4772d.m19004j(str);
        for (int i9 = 0; i9 < this.f20006b; i9++) {
            if (str.equalsIgnoreCase(this.f20007c[i9])) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: s */
    public void m22924s() {
        for (int i9 = 0; i9 < this.f20006b; i9++) {
            String[] strArr = this.f20007c;
            strArr[i9] = C4794b.m19030a(strArr[i9]);
        }
    }

    public int size() {
        return this.f20006b;
    }

    /* renamed from: t */
    public C5685b m22925t(String str, String str2) {
        int iM22922q = m22922q(str);
        if (iM22922q != -1) {
            this.f20008d[iM22922q] = str2;
        } else {
            m22911c(str, str2);
        }
        return this;
    }

    public String toString() {
        return m22920o();
    }

    /* renamed from: u */
    public C5685b m22926u(C5684a c5684a) {
        C4772d.m19004j(c5684a);
        m22925t(c5684a.getKey(), c5684a.getValue());
        c5684a.f20004d = this;
        return this;
    }

    /* renamed from: v */
    public void m22927v(String str, String str2) {
        int iM22923r = m22923r(str);
        if (iM22923r == -1) {
            m22911c(str, str2);
            return;
        }
        this.f20008d[iM22923r] = str2;
        if (this.f20007c[iM22923r].equals(str)) {
            return;
        }
        this.f20007c[iM22923r] = str;
    }

    /* renamed from: w */
    public final void m22928w(int i9) {
        C4772d.m18996b(i9 >= this.f20006b);
        int i10 = (this.f20006b - i9) - 1;
        if (i10 > 0) {
            String[] strArr = this.f20007c;
            int i11 = i9 + 1;
            System.arraycopy(strArr, i11, strArr, i9, i10);
            String[] strArr2 = this.f20008d;
            System.arraycopy(strArr2, i11, strArr2, i9, i10);
        }
        int i12 = this.f20006b - 1;
        this.f20006b = i12;
        this.f20007c[i12] = null;
        this.f20008d[i12] = null;
    }
}
