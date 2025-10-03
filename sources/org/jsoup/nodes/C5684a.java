package org.jsoup.nodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.jsoup.SerializationException;
import org.jsoup.nodes.Document;
import p060e8.C4772d;

/* renamed from: org.jsoup.nodes.a */
/* loaded from: classes.dex */
public class C5684a implements Map.Entry<String, String>, Cloneable {

    /* renamed from: e */
    public static final String[] f20001e = {"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};

    /* renamed from: b */
    public String f20002b;

    /* renamed from: c */
    public String f20003c;

    /* renamed from: d */
    public C5685b f20004d;

    public C5684a(String str, String str2) {
        this(str, str2, null);
    }

    /* renamed from: g */
    public static void m22898g(String str, String str2, Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        appendable.append(str);
        if (m22900j(str, str2, outputSettings)) {
            return;
        }
        appendable.append("=\"");
        Entities.m22883e(appendable, C5685b.m22909h(str2), outputSettings, true, false, false);
        appendable.append('\"');
    }

    /* renamed from: h */
    public static boolean m22899h(String str) {
        return Arrays.binarySearch(f20001e, str) >= 0;
    }

    /* renamed from: j */
    public static boolean m22900j(String str, String str2, Document.OutputSettings outputSettings) {
        return outputSettings.m22841l() == Document.OutputSettings.Syntax.html && (str2 == null || (("".equals(str2) || str2.equalsIgnoreCase(str)) && m22899h(str)));
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C5684a clone() {
        try {
            return (C5684a) super.clone();
        } catch (CloneNotSupportedException e9) {
            throw new RuntimeException(e9);
        }
    }

    @Override // java.util.Map.Entry
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String getKey() {
        return this.f20002b;
    }

    @Override // java.util.Map.Entry
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String getValue() {
        return this.f20003c;
    }

    /* renamed from: d */
    public String m22904d() {
        StringBuilder sb = new StringBuilder();
        try {
            m22905e(sb, new Document("").m22830z0());
            return sb.toString();
        } catch (IOException e9) {
            throw new SerializationException(e9);
        }
    }

    /* renamed from: e */
    public void m22905e(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        m22898g(this.f20002b, this.f20003c, appendable, outputSettings);
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C5684a c5684a = (C5684a) obj;
        String str = this.f20002b;
        if (str == null ? c5684a.f20002b != null : !str.equals(c5684a.f20002b)) {
            return false;
        }
        String str2 = this.f20003c;
        String str3 = c5684a.f20003c;
        return str2 != null ? str2.equals(str3) : str3 == null;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        String str = this.f20002b;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f20003c;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public String setValue(String str) {
        int iM22922q;
        String strM22916k = this.f20004d.m22916k(this.f20002b);
        C5685b c5685b = this.f20004d;
        if (c5685b != null && (iM22922q = c5685b.m22922q(this.f20002b)) != -1) {
            this.f20004d.f20008d[iM22922q] = str;
        }
        this.f20003c = str;
        return strM22916k;
    }

    public String toString() {
        return m22904d();
    }

    public C5684a(String str, String str2, C5685b c5685b) {
        C4772d.m19004j(str);
        this.f20002b = str.trim();
        C4772d.m19002h(str);
        this.f20003c = str2;
        this.f20004d = c5685b;
    }
}
