package p099i7;

import org.jivesoftware.smack.util.C5609c;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import p069f7.InterfaceC4792b;

/* renamed from: i7.b */
/* loaded from: classes.dex */
public class C5059b implements InterfaceC4792b {

    /* renamed from: e */
    public static final C5609c<String, Integer> f17458e = new C5609c<>(100, 7200000);

    /* renamed from: f */
    public static int f17459f = 2;

    /* renamed from: a */
    public Bytestream f17460a;

    /* renamed from: b */
    public Socks5BytestreamManager f17461b;

    /* renamed from: c */
    public int f17462c = 10000;

    /* renamed from: d */
    public int f17463d = 2000;

    public C5059b(Socks5BytestreamManager socks5BytestreamManager, Bytestream bytestream) {
        this.f17461b = socks5BytestreamManager;
        this.f17460a = bytestream;
    }
}
