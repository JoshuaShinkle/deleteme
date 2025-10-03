package org.jivesoftware.smackx.carbons.packet;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import p149n7.C5375a;

/* loaded from: classes.dex */
public class CarbonExtension implements InterfaceC5595c {

    /* renamed from: a */
    public Direction f19617a;

    /* renamed from: b */
    public C5375a f19618b;

    public enum Direction {
        received,
        sent
    }

    public CarbonExtension(Direction direction, C5375a c5375a) {
        this.f19617a = direction;
        this.f19618b = c5375a;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return this.f19617a.toString();
    }

    /* renamed from: c */
    public C5375a m22435c() {
        return this.f19618b;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<" + mo191b() + " xmlns=\"" + getNamespace() + "\">" + this.f19618b.mo190a() + "</" + mo191b() + ">";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "urn:xmpp:carbons:2";
    }
}
