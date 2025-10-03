package com.cyberlink.you.chat;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class AdvancedMessage extends Message {

    /* renamed from: w */
    public String f12414w;

    /* renamed from: x */
    public String f12415x;

    public AdvancedMessage(String str, Message.Type type) {
        super(str, type);
        this.f12414w = null;
        this.f12415x = null;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: a */
    public void mo14052a(C5618l c5618l) {
        super.mo14052a(c5618l);
        c5618l.m22367r("isScheduled", m14053b0());
        c5618l.m22367r("isTtl", m14054c0());
    }

    /* renamed from: b0 */
    public String m14053b0() {
        return this.f12414w;
    }

    /* renamed from: c0 */
    public String m14054c0() {
        return this.f12415x;
    }

    /* renamed from: d0 */
    public void m14055d0(String str) {
        this.f12414w = str;
    }

    /* renamed from: e0 */
    public void m14056e0(String str) {
        this.f12415x = str;
    }
}
