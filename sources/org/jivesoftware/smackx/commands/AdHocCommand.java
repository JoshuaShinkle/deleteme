package org.jivesoftware.smackx.commands;

import java.util.List;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import p259z7.C6837a;

/* loaded from: classes.dex */
public abstract class AdHocCommand {

    /* renamed from: a */
    public AdHocCommandData f19629a = new AdHocCommandData();

    public enum Action {
        execute,
        cancel,
        prev,
        next,
        complete,
        unknown
    }

    public enum SpecificErrorCondition {
        badAction("bad-action"),
        malformedAction("malformed-action"),
        badLocale("bad-locale"),
        badPayload("bad-payload"),
        badSessionid("bad-sessionid"),
        sessionExpired("session-expired");

        private String value;

        SpecificErrorCondition(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    public enum Status {
        executing,
        completed,
        canceled
    }

    /* renamed from: a */
    public abstract void m22438a();

    /* renamed from: b */
    public abstract void m22439b(C6837a c6837a);

    /* renamed from: c */
    public abstract void m22440c();

    /* renamed from: d */
    public List<Action> m22441d() {
        return this.f19629a.m22468J();
    }

    /* renamed from: e */
    public AdHocCommandData m22442e() {
        return this.f19629a;
    }

    /* renamed from: f */
    public Action m22443f() {
        return this.f19629a.m22470L();
    }

    /* renamed from: g */
    public boolean m22444g(Action action) {
        return m22441d().contains(action) || Action.cancel.equals(action);
    }

    /* renamed from: h */
    public abstract void m22445h(C6837a c6837a);

    /* renamed from: i */
    public abstract void m22446i();

    /* renamed from: j */
    public void mo20339j(AdHocCommandData adHocCommandData) {
        this.f19629a = adHocCommandData;
    }

    /* renamed from: k */
    public void m22447k(String str) {
        this.f19629a.m22478T(str);
    }

    /* renamed from: l */
    public void m22448l(String str) {
        this.f19629a.m22479U(str);
    }
}
