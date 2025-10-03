package com.cyberlink.p033u.glide;

import android.net.Uri;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.p033u.glide.C1387b;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.p034e.C2889b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import p073g1.C4813g;
import p073g1.C4824r;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p083h1.C4980b;
import p209u2.C6369f;
import p209u2.C6370g;
import p243y0.C6592e;
import p252z0.InterfaceC6805d;

/* renamed from: com.cyberlink.u.glide.a */
/* loaded from: classes.dex */
public final class C1386a implements InterfaceC4820n<C1387b.c, InputStream> {

    /* renamed from: a */
    public final C4980b f7136a;

    /* renamed from: com.cyberlink.u.glide.a$b */
    public static class b implements InterfaceC6805d<InputStream> {

        /* renamed from: b */
        public final InterfaceC6805d<InputStream> f7137b;

        /* renamed from: c */
        public final C1199a f7138c;

        /* renamed from: com.cyberlink.u.glide.a$b$a */
        public class a implements InterfaceC6805d.a<InputStream> {

            /* renamed from: b */
            public final /* synthetic */ InterfaceC6805d.a f7139b;

            public a(InterfaceC6805d.a aVar) {
                this.f7139b = aVar;
            }

            @Override // p252z0.InterfaceC6805d.a
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void mo3903f(InputStream inputStream) {
                try {
                    this.f7139b.mo3903f(b.this.m7189d(inputStream));
                } catch (Exception e9) {
                    this.f7139b.mo3902c(e9);
                }
            }

            @Override // p252z0.InterfaceC6805d.a
            /* renamed from: c */
            public void mo3902c(Exception exc) {
                this.f7139b.mo3902c(exc);
            }
        }

        public b(InterfaceC6805d<InputStream> interfaceC6805d, C1199a c1199a) {
            this.f7137b = interfaceC6805d;
            this.f7138c = c1199a;
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: a */
        public Class<InputStream> mo56a() {
            return this.f7137b.mo56a();
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: b */
        public void mo57b() {
            this.f7137b.mo57b();
        }

        @Override // p252z0.InterfaceC6805d
        public void cancel() {
            this.f7137b.cancel();
        }

        /* renamed from: d */
        public final InputStream m7189d(InputStream inputStream) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (this.f7138c == null) {
                return inputStream;
            }
            File file = new File(Globals.m7372O().getExternalCacheDir(), UUID.randomUUID().toString());
            try {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        C2889b.m14298h().m14303e(inputStream, fileOutputStream, this.f7138c);
                        d dVar = new d(file);
                        fileOutputStream.close();
                        return dVar;
                    } catch (Throwable th) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (Exception e9) {
                    C6369f.m24463e(file);
                    throw e9;
                }
            } finally {
                C6370g.m24480b(inputStream);
            }
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: e */
        public DataSource mo58e() {
            return this.f7137b.mo58e();
        }

        @Override // p252z0.InterfaceC6805d
        /* renamed from: g */
        public void mo59g(Priority priority, InterfaceC6805d.a<? super InputStream> aVar) {
            this.f7137b.mo59g(priority, new a(aVar));
        }
    }

    /* renamed from: com.cyberlink.u.glide.a$c */
    public static class c implements InterfaceC4821o<C1387b.c, InputStream> {
        @Override // p073g1.InterfaceC4821o
        /* renamed from: a */
        public InterfaceC4820n<C1387b.c, InputStream> mo3831a(C4824r c4824r) {
            return new C1386a(new C4980b(c4824r.m19142d(C4813g.class, InputStream.class)));
        }

        @Override // p073g1.InterfaceC4821o
        /* renamed from: b */
        public void mo3832b() {
        }
    }

    /* renamed from: com.cyberlink.u.glide.a$d */
    public static class d extends FileInputStream {

        /* renamed from: b */
        public final File f7141b;

        public d(File file) {
            super(file);
            this.f7141b = file;
        }

        @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            C6369f.m24463e(this.f7141b);
        }
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC4820n.a<InputStream> mo3827b(C1387b.c cVar, int i9, int i10, C6592e c6592e) {
        InterfaceC4820n.a<InputStream> aVarMo3827b = this.f7136a.mo3827b(Uri.parse(cVar.f7158a), i9, i10, c6592e);
        if (aVarMo3827b == null) {
            return null;
        }
        return new InterfaceC4820n.a<>(aVarMo3827b.f16776a, aVarMo3827b.f16777b, new b(aVarMo3827b.f16778c, cVar.f7159b));
    }

    @Override // p073g1.InterfaceC4820n
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean mo3826a(C1387b.c cVar) {
        return this.f7136a.mo3826a(Uri.parse(cVar.f7158a));
    }

    public C1386a(C4980b c4980b) {
        this.f7136a = c4980b;
    }
}
