package com.cyberlink.you.chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: com.cyberlink.you.chat.i */
/* loaded from: classes.dex */
public class C2898i implements InterfaceC5595c {

    /* renamed from: a */
    public String f12680a;

    /* renamed from: b */
    public String f12681b;

    /* renamed from: c */
    public String f12682c = null;

    /* renamed from: d */
    public Map<String, String> f12683d;

    public C2898i(String str, String str2) {
        this.f12680a = str;
        this.f12681b = str2;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return this.f12680a;
    }

    /* renamed from: c */
    public synchronized Iterator<String> m14371c() {
        if (this.f12683d == null) {
            return Collections.emptyList().iterator();
        }
        return Collections.unmodifiableMap(new HashMap(this.f12683d)).keySet().iterator();
    }

    /* renamed from: d */
    public synchronized String m14372d(String str) {
        Map<String, String> map = this.f12683d;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: e */
    public synchronized Map<String, String> m14373e() {
        Map<String, String> map = this.f12683d;
        if (map == null) {
            return null;
        }
        return map;
    }

    /* renamed from: f */
    public String m14374f() {
        return this.f12682c;
    }

    /* renamed from: g */
    public synchronized void m14375g(String str, String str2) {
        if (this.f12683d == null) {
            this.f12683d = new HashMap();
        }
        this.f12683d.put(str, StringEscapeUtils.escapeXml(str2));
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return this.f12681b;
    }

    /* renamed from: h */
    public synchronized void m14376h(String str) {
        this.f12682c = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<");
        stringBuffer.append(this.f12680a);
        stringBuffer.append(" xmlns=\"");
        stringBuffer.append(this.f12681b);
        stringBuffer.append("\"");
        Iterator<String> itM14371c = m14371c();
        while (itM14371c.hasNext()) {
            String next = itM14371c.next();
            String strM14372d = m14372d(next);
            stringBuffer.append(StringUtils.SPACE);
            stringBuffer.append(next);
            stringBuffer.append("=\"");
            stringBuffer.append(strM14372d);
            stringBuffer.append("\"");
        }
        stringBuffer.append(">");
        String str = this.f12682c;
        if (str != null && !str.isEmpty()) {
            stringBuffer.append(this.f12682c);
        }
        stringBuffer.append("</");
        stringBuffer.append(this.f12680a);
        stringBuffer.append(">");
        return stringBuffer.toString();
    }
}
