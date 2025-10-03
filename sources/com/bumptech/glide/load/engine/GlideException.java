package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p243y0.InterfaceC6589b;

/* loaded from: classes.dex */
public final class GlideException extends Exception {

    /* renamed from: b */
    public static final StackTraceElement[] f3754b = new StackTraceElement[0];
    private static final long serialVersionUID = 1;
    private final List<Throwable> causes;
    private Class<?> dataClass;
    private DataSource dataSource;
    private String detailMessage;
    private InterfaceC6589b key;

    public GlideException(String str) {
        this(str, (List<Throwable>) Collections.emptyList());
    }

    /* renamed from: b */
    public static void m3881b(List<Throwable> list, Appendable appendable) {
        try {
            m3882c(list, appendable);
        } catch (IOException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: c */
    public static void m3882c(List<Throwable> list, Appendable appendable) throws IOException {
        int size = list.size();
        int i9 = 0;
        while (i9 < size) {
            int i10 = i9 + 1;
            appendable.append("Cause (").append(String.valueOf(i10)).append(" of ").append(String.valueOf(size)).append("): ");
            Throwable th = list.get(i9);
            if (th instanceof GlideException) {
                ((GlideException) th).m3888h(appendable);
            } else {
                m3883d(th, appendable);
            }
            i9 = i10;
        }
    }

    /* renamed from: d */
    public static void m3883d(Throwable th, Appendable appendable) throws IOException {
        try {
            appendable.append(th.getClass().toString()).append(": ").append(th.getMessage()).append('\n');
        } catch (IOException unused) {
            throw new RuntimeException(th);
        }
    }

    /* renamed from: a */
    public final void m3884a(Throwable th, List<Throwable> list) {
        if (!(th instanceof GlideException)) {
            list.add(th);
            return;
        }
        Iterator<Throwable> it = ((GlideException) th).m3885e().iterator();
        while (it.hasNext()) {
            m3884a(it.next(), list);
        }
    }

    /* renamed from: e */
    public List<Throwable> m3885e() {
        return this.causes;
    }

    /* renamed from: f */
    public List<Throwable> m3886f() {
        ArrayList arrayList = new ArrayList();
        m3884a(this, arrayList);
        return arrayList;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }

    /* renamed from: g */
    public void m3887g(String str) {
        List<Throwable> listM3886f = m3886f();
        int size = listM3886f.size();
        int i9 = 0;
        while (i9 < size) {
            StringBuilder sb = new StringBuilder();
            sb.append("Root cause (");
            int i10 = i9 + 1;
            sb.append(i10);
            sb.append(" of ");
            sb.append(size);
            sb.append(")");
            Log.i(str, sb.toString(), listM3886f.get(i9));
            i9 = i10;
        }
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb = new StringBuilder(71);
        sb.append(this.detailMessage);
        sb.append(this.dataClass != null ? ", " + this.dataClass : "");
        sb.append(this.dataSource != null ? ", " + this.dataSource : "");
        sb.append(this.key != null ? ", " + this.key : "");
        List<Throwable> listM3886f = m3886f();
        if (listM3886f.isEmpty()) {
            return sb.toString();
        }
        if (listM3886f.size() == 1) {
            sb.append("\nThere was 1 cause:");
        } else {
            sb.append("\nThere were ");
            sb.append(listM3886f.size());
            sb.append(" causes:");
        }
        for (Throwable th : listM3886f) {
            sb.append('\n');
            sb.append(th.getClass().getName());
            sb.append('(');
            sb.append(th.getMessage());
            sb.append(')');
        }
        sb.append("\n call GlideException#logRootCauses(String) for more detail");
        return sb.toString();
    }

    /* renamed from: h */
    public final void m3888h(Appendable appendable) throws IOException {
        m3883d(this, appendable);
        m3881b(m3885e(), new C0831a(appendable));
    }

    /* renamed from: i */
    public void m3889i(InterfaceC6589b interfaceC6589b, DataSource dataSource) {
        m3890j(interfaceC6589b, dataSource, null);
    }

    /* renamed from: j */
    public void m3890j(InterfaceC6589b interfaceC6589b, DataSource dataSource, Class<?> cls) {
        this.key = interfaceC6589b;
        this.dataSource = dataSource;
        this.dataClass = cls;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() throws IOException {
        printStackTrace(System.err);
    }

    public GlideException(String str, Throwable th) {
        this(str, (List<Throwable>) Collections.singletonList(th));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) throws IOException {
        m3888h(printStream);
    }

    public GlideException(String str, List<Throwable> list) {
        this.detailMessage = str;
        setStackTrace(f3754b);
        this.causes = list;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) throws IOException {
        m3888h(printWriter);
    }

    /* renamed from: com.bumptech.glide.load.engine.GlideException$a */
    public static final class C0831a implements Appendable {

        /* renamed from: b */
        public final Appendable f3755b;

        /* renamed from: c */
        public boolean f3756c = true;

        public C0831a(Appendable appendable) {
            this.f3755b = appendable;
        }

        /* renamed from: a */
        public final CharSequence m3891a(CharSequence charSequence) {
            return charSequence == null ? "" : charSequence;
        }

        @Override // java.lang.Appendable
        public Appendable append(char c9) throws IOException {
            if (this.f3756c) {
                this.f3756c = false;
                this.f3755b.append("  ");
            }
            this.f3756c = c9 == '\n';
            this.f3755b.append(c9);
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence) {
            CharSequence charSequenceM3891a = m3891a(charSequence);
            return append(charSequenceM3891a, 0, charSequenceM3891a.length());
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence charSequence, int i9, int i10) throws IOException {
            CharSequence charSequenceM3891a = m3891a(charSequence);
            boolean z8 = false;
            if (this.f3756c) {
                this.f3756c = false;
                this.f3755b.append("  ");
            }
            if (charSequenceM3891a.length() > 0 && charSequenceM3891a.charAt(i10 - 1) == '\n') {
                z8 = true;
            }
            this.f3756c = z8;
            this.f3755b.append(charSequenceM3891a, i9, i10);
            return this;
        }
    }
}
