package org.jivesoftware.smackx.commands;

/* loaded from: classes.dex */
public class AdHocCommandNote {

    /* renamed from: a */
    public Type f19659a;

    /* renamed from: b */
    public String f19660b;

    public enum Type {
        info,
        warn,
        error
    }

    public AdHocCommandNote(Type type, String str) {
        this.f19659a = type;
        this.f19660b = str;
    }

    /* renamed from: a */
    public Type m22463a() {
        return this.f19659a;
    }

    /* renamed from: b */
    public String m22464b() {
        return this.f19660b;
    }
}
