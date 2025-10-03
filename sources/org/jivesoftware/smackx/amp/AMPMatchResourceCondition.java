package org.jivesoftware.smackx.amp;

import org.jivesoftware.smackx.amp.packet.AMPExtension;

/* loaded from: classes.dex */
public class AMPMatchResourceCondition implements AMPExtension.InterfaceC5619a {

    /* renamed from: a */
    public final Value f19538a;

    public enum Value {
        any,
        exact,
        other
    }

    public AMPMatchResourceCondition(Value value) {
        if (value == null) {
            throw new NullPointerException("Can't create AMPMatchResourceCondition with null value");
        }
        this.f19538a = value;
    }

    @Override // org.jivesoftware.smackx.amp.packet.AMPExtension.InterfaceC5619a
    public String getName() {
        return "match-resource";
    }

    @Override // org.jivesoftware.smackx.amp.packet.AMPExtension.InterfaceC5619a
    public String getValue() {
        return this.f19538a.toString();
    }
}
