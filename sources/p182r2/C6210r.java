package p182r2;

import java.util.concurrent.atomic.AtomicInteger;
import org.jivesoftware.smack.packet.Message;

/* renamed from: r2.r */
/* loaded from: classes.dex */
public class C6210r extends AbstractC6189a {

    /* renamed from: b */
    public final String f20911b;

    /* renamed from: c */
    public final Message f20912c;

    /* renamed from: d */
    public final AtomicInteger f20913d;

    public C6210r(String str, Message message) {
        super(message.m22161k());
        this.f20913d = new AtomicInteger(3);
        this.f20911b = str;
        this.f20912c = message;
    }
}
