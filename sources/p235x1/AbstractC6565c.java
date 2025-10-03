package p235x1;

/* renamed from: x1.c */
/* loaded from: classes.dex */
public abstract class AbstractC6565c {

    /* renamed from: x1.c$b */
    public static class b extends AbstractC6565c {

        /* renamed from: a */
        public volatile boolean f22073a;

        public b() {
            super();
        }

        @Override // p235x1.AbstractC6565c
        /* renamed from: b */
        public void mo25139b(boolean z8) {
            this.f22073a = z8;
        }

        @Override // p235x1.AbstractC6565c
        /* renamed from: c */
        public void mo25140c() {
            if (this.f22073a) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    public AbstractC6565c() {
    }

    /* renamed from: a */
    public static AbstractC6565c m25138a() {
        return new b();
    }

    /* renamed from: b */
    public abstract void mo25139b(boolean z8);

    /* renamed from: c */
    public abstract void mo25140c();
}
