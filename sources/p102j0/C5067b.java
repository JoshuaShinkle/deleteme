package p102j0;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.AbstractC0394o;
import androidx.lifecycle.C0390k;
import androidx.lifecycle.C0395p;
import androidx.lifecycle.C0396q;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.InterfaceC0391l;
import androidx.loader.content.C0406b;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import p021c0.C0695a;
import p102j0.AbstractC5066a;
import p132m.C5309h;

/* renamed from: j0.b */
/* loaded from: classes.dex */
public class C5067b extends AbstractC5066a {

    /* renamed from: c */
    public static boolean f17478c = false;

    /* renamed from: a */
    public final InterfaceC0385f f17479a;

    /* renamed from: b */
    public final c f17480b;

    /* renamed from: j0.b$a */
    public static class a<D> extends C0390k<D> implements C0406b.b<D> {

        /* renamed from: k */
        public final int f17481k;

        /* renamed from: l */
        public final Bundle f17482l;

        /* renamed from: m */
        public final C0406b<D> f17483m;

        /* renamed from: n */
        public InterfaceC0385f f17484n;

        /* renamed from: o */
        public b<D> f17485o;

        /* renamed from: p */
        public C0406b<D> f17486p;

        public a(int i9, Bundle bundle, C0406b<D> c0406b, C0406b<D> c0406b2) {
            this.f17481k = i9;
            this.f17482l = bundle;
            this.f17483m = c0406b;
            this.f17486p = c0406b2;
            c0406b.registerListener(i9, this);
        }

        @Override // androidx.loader.content.C0406b.b
        /* renamed from: a */
        public void mo2134a(C0406b<D> c0406b, D d9) {
            if (C5067b.f17478c) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                mo2061l(d9);
                return;
            }
            if (C5067b.f17478c) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            mo2059j(d9);
        }

        @Override // androidx.lifecycle.LiveData
        /* renamed from: h */
        public void mo2057h() {
            if (C5067b.f17478c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.f17483m.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        /* renamed from: i */
        public void mo2058i() {
            if (C5067b.f17478c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.f17483m.stopLoading();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        /* renamed from: k */
        public void mo2060k(InterfaceC0391l<? super D> interfaceC0391l) {
            super.mo2060k(interfaceC0391l);
            this.f17484n = null;
            this.f17485o = null;
        }

        @Override // androidx.lifecycle.C0390k, androidx.lifecycle.LiveData
        /* renamed from: l */
        public void mo2061l(D d9) {
            super.mo2061l(d9);
            C0406b<D> c0406b = this.f17486p;
            if (c0406b != null) {
                c0406b.reset();
                this.f17486p = null;
            }
        }

        /* renamed from: m */
        public C0406b<D> m19838m(boolean z8) {
            if (C5067b.f17478c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.f17483m.cancelLoad();
            this.f17483m.abandon();
            b<D> bVar = this.f17485o;
            if (bVar != null) {
                mo2060k(bVar);
                if (z8) {
                    bVar.m19845d();
                }
            }
            this.f17483m.unregisterListener(this);
            if ((bVar == null || bVar.m19844c()) && !z8) {
                return this.f17483m;
            }
            this.f17483m.reset();
            return this.f17486p;
        }

        /* renamed from: n */
        public void m19839n(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f17481k);
            printWriter.print(" mArgs=");
            printWriter.println(this.f17482l);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f17483m);
            this.f17483m.dump(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.f17485o != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.f17485o);
                this.f17485o.m19843b(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(m19840o().dataToString(m2054e()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(m2055f());
        }

        /* renamed from: o */
        public C0406b<D> m19840o() {
            return this.f17483m;
        }

        /* renamed from: p */
        public void m19841p() {
            InterfaceC0385f interfaceC0385f = this.f17484n;
            b<D> bVar = this.f17485o;
            if (interfaceC0385f == null || bVar == null) {
                return;
            }
            super.mo2060k(bVar);
            m2056g(interfaceC0385f, bVar);
        }

        /* renamed from: q */
        public C0406b<D> m19842q(InterfaceC0385f interfaceC0385f, AbstractC5066a.a<D> aVar) {
            b<D> bVar = new b<>(this.f17483m, aVar);
            m2056g(interfaceC0385f, bVar);
            b<D> bVar2 = this.f17485o;
            if (bVar2 != null) {
                mo2060k(bVar2);
            }
            this.f17484n = interfaceC0385f;
            this.f17485o = bVar;
            return this.f17483m;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.f17481k);
            sb.append(" : ");
            C0695a.m3459a(this.f17483m, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* renamed from: j0.b$b */
    public static class b<D> implements InterfaceC0391l<D> {

        /* renamed from: a */
        public final C0406b<D> f17487a;

        /* renamed from: b */
        public final AbstractC5066a.a<D> f17488b;

        /* renamed from: c */
        public boolean f17489c = false;

        public b(C0406b<D> c0406b, AbstractC5066a.a<D> aVar) {
            this.f17487a = c0406b;
            this.f17488b = aVar;
        }

        @Override // androidx.lifecycle.InterfaceC0391l
        /* renamed from: a */
        public void mo2104a(D d9) {
            if (C5067b.f17478c) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.f17487a + ": " + this.f17487a.dataToString(d9));
            }
            this.f17488b.onLoadFinished(this.f17487a, d9);
            this.f17489c = true;
        }

        /* renamed from: b */
        public void m19843b(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.f17489c);
        }

        /* renamed from: c */
        public boolean m19844c() {
            return this.f17489c;
        }

        /* renamed from: d */
        public void m19845d() {
            if (this.f17489c) {
                if (C5067b.f17478c) {
                    Log.v("LoaderManager", "  Resetting: " + this.f17487a);
                }
                this.f17488b.onLoaderReset(this.f17487a);
            }
        }

        public String toString() {
            return this.f17488b.toString();
        }
    }

    /* renamed from: j0.b$c */
    public static class c extends AbstractC0394o {

        /* renamed from: e */
        public static final C0395p.a f17490e = new a();

        /* renamed from: c */
        public C5309h<a> f17491c = new C5309h<>();

        /* renamed from: d */
        public boolean f17492d = false;

        /* renamed from: j0.b$c$a */
        public static class a implements C0395p.a {
            @Override // androidx.lifecycle.C0395p.a
            /* renamed from: a */
            public <T extends AbstractC0394o> T mo1979a(Class<T> cls) {
                return new c();
            }
        }

        /* renamed from: f */
        public static c m19846f(C0396q c0396q) {
            return (c) new C0395p(c0396q, f17490e).m2113a(c.class);
        }

        @Override // androidx.lifecycle.AbstractC0394o
        /* renamed from: c */
        public void mo1970c() {
            super.mo1970c();
            int iM20768n = this.f17491c.m20768n();
            for (int i9 = 0; i9 < iM20768n; i9++) {
                this.f17491c.m20769o(i9).m19838m(true);
            }
            this.f17491c.m20757b();
        }

        /* renamed from: d */
        public void m19847d(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f17491c.m20768n() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i9 = 0; i9 < this.f17491c.m20768n(); i9++) {
                    a aVarM20769o = this.f17491c.m20769o(i9);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f17491c.m20764j(i9));
                    printWriter.print(": ");
                    printWriter.println(aVarM20769o.toString());
                    aVarM20769o.m19839n(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        /* renamed from: e */
        public void m19848e() {
            this.f17492d = false;
        }

        /* renamed from: g */
        public <D> a<D> m19849g(int i9) {
            return this.f17491c.m20760e(i9);
        }

        /* renamed from: h */
        public boolean m19850h() {
            return this.f17492d;
        }

        /* renamed from: i */
        public void m19851i() {
            int iM20768n = this.f17491c.m20768n();
            for (int i9 = 0; i9 < iM20768n; i9++) {
                this.f17491c.m20769o(i9).m19841p();
            }
        }

        /* renamed from: j */
        public void m19852j(int i9, a aVar) {
            this.f17491c.m20765k(i9, aVar);
        }

        /* renamed from: k */
        public void m19853k() {
            this.f17492d = true;
        }
    }

    public C5067b(InterfaceC0385f interfaceC0385f, C0396q c0396q) {
        this.f17479a = interfaceC0385f;
        this.f17480b = c.m19846f(c0396q);
    }

    @Override // p102j0.AbstractC5066a
    @Deprecated
    /* renamed from: a */
    public void mo19834a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f17480b.m19847d(str, fileDescriptor, printWriter, strArr);
    }

    @Override // p102j0.AbstractC5066a
    /* renamed from: c */
    public <D> C0406b<D> mo19835c(int i9, Bundle bundle, AbstractC5066a.a<D> aVar) {
        if (this.f17480b.m19850h()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        a<D> aVarM19849g = this.f17480b.m19849g(i9);
        if (f17478c) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
        }
        if (aVarM19849g == null) {
            return m19837e(i9, bundle, aVar, null);
        }
        if (f17478c) {
            Log.v("LoaderManager", "  Re-using existing loader " + aVarM19849g);
        }
        return aVarM19849g.m19842q(this.f17479a, aVar);
    }

    @Override // p102j0.AbstractC5066a
    /* renamed from: d */
    public void mo19836d() {
        this.f17480b.m19851i();
    }

    /* renamed from: e */
    public final <D> C0406b<D> m19837e(int i9, Bundle bundle, AbstractC5066a.a<D> aVar, C0406b<D> c0406b) {
        try {
            this.f17480b.m19853k();
            C0406b<D> c0406bOnCreateLoader = aVar.onCreateLoader(i9, bundle);
            if (c0406bOnCreateLoader == null) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
            }
            if (c0406bOnCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(c0406bOnCreateLoader.getClass().getModifiers())) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + c0406bOnCreateLoader);
            }
            a aVar2 = new a(i9, bundle, c0406bOnCreateLoader, c0406b);
            if (f17478c) {
                Log.v("LoaderManager", "  Created new loader " + aVar2);
            }
            this.f17480b.m19852j(i9, aVar2);
            this.f17480b.m19848e();
            return aVar2.m19842q(this.f17479a, aVar);
        } catch (Throwable th) {
            this.f17480b.m19848e();
            throw th;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        C0695a.m3459a(this.f17479a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
