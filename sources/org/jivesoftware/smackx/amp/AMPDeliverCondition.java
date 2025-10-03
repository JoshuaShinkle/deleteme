package org.jivesoftware.smackx.amp;

import org.jivesoftware.smackx.amp.packet.AMPExtension;

/* loaded from: classes.dex */
public class AMPDeliverCondition implements AMPExtension.InterfaceC5619a {

    /* renamed from: a */
    public final Value f19531a;

    public enum Value {
        direct,
        forward,
        gateway,
        none,
        stored
    }

    public AMPDeliverCondition(Value value) {
        if (value == null) {
            throw new NullPointerException("Can't create AMPDeliverCondition with null value");
        }
        this.f19531a = value;
    }

    @Override // org.jivesoftware.smackx.amp.packet.AMPExtension.InterfaceC5619a
    public String getName() {
        return "deliver";
    }

    @Override // org.jivesoftware.smackx.amp.packet.AMPExtension.InterfaceC5619a
    public String getValue() {
        return this.f19531a.toString();
    }
}
