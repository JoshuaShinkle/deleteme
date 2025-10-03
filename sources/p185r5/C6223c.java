package p185r5;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* renamed from: r5.c */
/* loaded from: classes2.dex */
public final class C6223c extends GeneratedMessageLite<C6223c, a> implements MessageLiteOrBuilder {

    /* renamed from: n */
    public static final C6223c f20937n;

    /* renamed from: o */
    public static volatile Parser<C6223c> f20938o;

    /* renamed from: d */
    public long f20941d;

    /* renamed from: f */
    public long f20943f;

    /* renamed from: g */
    public long f20944g;

    /* renamed from: b */
    public String f20939b = "";

    /* renamed from: c */
    public String f20940c = "";

    /* renamed from: e */
    public String f20942e = "";

    /* renamed from: h */
    public String f20945h = "";

    /* renamed from: i */
    public String f20946i = "";

    /* renamed from: j */
    public String f20947j = "";

    /* renamed from: k */
    public String f20948k = "";

    /* renamed from: l */
    public String f20949l = "";

    /* renamed from: m */
    public Internal.ProtobufList<C6222b> f20950m = GeneratedMessageLite.emptyProtobufList();

    /* renamed from: r5.c$a */
    public static final class a extends GeneratedMessageLite.Builder<C6223c, a> implements MessageLiteOrBuilder {
        public /* synthetic */ a(C6221a c6221a) {
            this();
        }

        public a() {
            super(C6223c.f20937n);
        }
    }

    static {
        C6223c c6223c = new C6223c();
        f20937n = c6223c;
        GeneratedMessageLite.registerDefaultInstance(C6223c.class, c6223c);
    }

    /* renamed from: h */
    public static C6223c m23775h(byte[] bArr) {
        return (C6223c) GeneratedMessageLite.parseFrom(f20937n, bArr);
    }

    /* renamed from: b */
    public String m23776b() {
        return this.f20939b;
    }

    /* renamed from: c */
    public long m23777c() {
        return this.f20941d;
    }

    /* renamed from: d */
    public long m23778d() {
        return this.f20944g;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        C6221a c6221a = null;
        switch (C6221a.f20933a[methodToInvoke.ordinal()]) {
            case 1:
                return new C6223c();
            case 2:
                return new a(c6221a);
            case 3:
                return GeneratedMessageLite.newMessageInfo(f20937n, "\u0000\r\u0000\u0000\u0001\r\r\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003\u0002\u0004Ȉ\u0005\u0002\u0006\u0002\u0007Ȉ\bȈ\tȈ\nȈ\u000bȈ\f\f\r\u001b", new Object[]{"experimentId_", "variantId_", "experimentStartTimeMillis_", "triggerEvent_", "triggerTimeoutMillis_", "timeToLiveMillis_", "setEventToLog_", "activateEventToLog_", "clearEventToLog_", "timeoutEventToLog_", "ttlExpiryEventToLog_", "overflowPolicy_", "ongoingExperiments_", C6222b.class});
            case 4:
                return f20937n;
            case 5:
                Parser<C6223c> defaultInstanceBasedParser = f20938o;
                if (defaultInstanceBasedParser == null) {
                    synchronized (C6223c.class) {
                        defaultInstanceBasedParser = f20938o;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(f20937n);
                            f20938o = defaultInstanceBasedParser;
                        }
                    }
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /* renamed from: e */
    public String m23779e() {
        return this.f20942e;
    }

    /* renamed from: f */
    public long m23780f() {
        return this.f20943f;
    }

    /* renamed from: g */
    public String m23781g() {
        return this.f20940c;
    }
}
