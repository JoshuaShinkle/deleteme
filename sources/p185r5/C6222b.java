package p185r5;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* renamed from: r5.b */
/* loaded from: classes2.dex */
public final class C6222b extends GeneratedMessageLite<C6222b, a> implements MessageLiteOrBuilder {

    /* renamed from: c */
    public static final C6222b f20934c;

    /* renamed from: d */
    public static volatile Parser<C6222b> f20935d;

    /* renamed from: b */
    public String f20936b = "";

    /* renamed from: r5.b$a */
    public static final class a extends GeneratedMessageLite.Builder<C6222b, a> implements MessageLiteOrBuilder {
        public /* synthetic */ a(C6221a c6221a) {
            this();
        }

        public a() {
            super(C6222b.f20934c);
        }
    }

    static {
        C6222b c6222b = new C6222b();
        f20934c = c6222b;
        GeneratedMessageLite.registerDefaultInstance(C6222b.class, c6222b);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        C6221a c6221a = null;
        switch (C6221a.f20933a[methodToInvoke.ordinal()]) {
            case 1:
                return new C6222b();
            case 2:
                return new a(c6221a);
            case 3:
                return GeneratedMessageLite.newMessageInfo(f20934c, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"experimentId_"});
            case 4:
                return f20934c;
            case 5:
                Parser<C6222b> defaultInstanceBasedParser = f20935d;
                if (defaultInstanceBasedParser == null) {
                    synchronized (C6222b.class) {
                        defaultInstanceBasedParser = f20935d;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(f20934c);
                            f20935d = defaultInstanceBasedParser;
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
}
