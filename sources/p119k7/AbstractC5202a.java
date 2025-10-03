package p119k7;

import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;

/* renamed from: k7.a */
/* loaded from: classes.dex */
public abstract class AbstractC5202a extends AdHocCommand {

    /* renamed from: c */
    public String f17826c;

    /* renamed from: b */
    public long f17825b = System.currentTimeMillis();

    /* renamed from: d */
    public int f17827d = -1;

    @Override // org.jivesoftware.smackx.commands.AdHocCommand
    /* renamed from: j */
    public void mo20339j(AdHocCommandData adHocCommandData) {
        adHocCommandData.m22480V(this.f17826c);
        super.mo20339j(adHocCommandData);
    }

    /* renamed from: m */
    public void m20340m() {
        this.f17827d--;
    }

    /* renamed from: n */
    public long m20341n() {
        return this.f17825b;
    }

    /* renamed from: o */
    public abstract boolean m20342o(String str);

    /* renamed from: p */
    public void m20343p() {
        this.f17827d++;
    }

    /* renamed from: q */
    public abstract boolean m20344q();

    /* renamed from: r */
    public void m20345r(String str) {
        this.f17826c = str;
        m22442e().m22480V(str);
    }
}
