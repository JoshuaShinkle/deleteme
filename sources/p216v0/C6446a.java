package p216v0;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/* renamed from: v0.a */
/* loaded from: classes.dex */
public final class C6446a implements Closeable {

    /* renamed from: b */
    public final File f21676b;

    /* renamed from: c */
    public final File f21677c;

    /* renamed from: d */
    public final File f21678d;

    /* renamed from: e */
    public final File f21679e;

    /* renamed from: f */
    public final int f21680f;

    /* renamed from: g */
    public long f21681g;

    /* renamed from: h */
    public final int f21682h;

    /* renamed from: j */
    public Writer f21684j;

    /* renamed from: l */
    public int f21686l;

    /* renamed from: i */
    public long f21683i = 0;

    /* renamed from: k */
    public final LinkedHashMap<String, d> f21685k = new LinkedHashMap<>(0, 0.75f, true);

    /* renamed from: m */
    public long f21687m = 0;

    /* renamed from: n */
    public final ThreadPoolExecutor f21688n = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new b(null));

    /* renamed from: o */
    public final Callable<Void> f21689o = new a();

    /* renamed from: v0.a$a */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() {
            synchronized (C6446a.this) {
                if (C6446a.this.f21684j == null) {
                    return null;
                }
                C6446a.this.m24664Q();
                if (C6446a.this.m24658I()) {
                    C6446a.this.m24662N();
                    C6446a.this.f21686l = 0;
                }
                return null;
            }
        }
    }

    /* renamed from: v0.a$b */
    public static final class b implements ThreadFactory {
        public b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }

    /* renamed from: v0.a$c */
    public final class c {

        /* renamed from: a */
        public final d f21691a;

        /* renamed from: b */
        public final boolean[] f21692b;

        /* renamed from: c */
        public boolean f21693c;

        public /* synthetic */ c(C6446a c6446a, d dVar, a aVar) {
            this(dVar);
        }

        /* renamed from: a */
        public void m24668a() {
            C6446a.this.m24653C(this, false);
        }

        /* renamed from: b */
        public void m24669b() {
            if (this.f21693c) {
                return;
            }
            try {
                m24668a();
            } catch (IOException unused) {
            }
        }

        /* renamed from: e */
        public void m24670e() {
            C6446a.this.m24653C(this, true);
            this.f21693c = true;
        }

        /* renamed from: f */
        public File m24671f(int i9) {
            File fileM24682k;
            synchronized (C6446a.this) {
                if (this.f21691a.f21700f != this) {
                    throw new IllegalStateException();
                }
                if (!this.f21691a.f21699e) {
                    this.f21692b[i9] = true;
                }
                fileM24682k = this.f21691a.m24682k(i9);
                if (!C6446a.this.f21676b.exists()) {
                    C6446a.this.f21676b.mkdirs();
                }
            }
            return fileM24682k;
        }

        public c(d dVar) {
            this.f21691a = dVar;
            this.f21692b = dVar.f21699e ? null : new boolean[C6446a.this.f21682h];
        }
    }

    /* renamed from: v0.a$d */
    public final class d {

        /* renamed from: a */
        public final String f21695a;

        /* renamed from: b */
        public final long[] f21696b;

        /* renamed from: c */
        public File[] f21697c;

        /* renamed from: d */
        public File[] f21698d;

        /* renamed from: e */
        public boolean f21699e;

        /* renamed from: f */
        public c f21700f;

        /* renamed from: g */
        public long f21701g;

        public /* synthetic */ d(C6446a c6446a, String str, a aVar) {
            this(str);
        }

        /* renamed from: j */
        public File m24681j(int i9) {
            return this.f21697c[i9];
        }

        /* renamed from: k */
        public File m24682k(int i9) {
            return this.f21698d[i9];
        }

        /* renamed from: l */
        public String m24683l() {
            StringBuilder sb = new StringBuilder();
            for (long j9 : this.f21696b) {
                sb.append(' ');
                sb.append(j9);
            }
            return sb.toString();
        }

        /* renamed from: m */
        public final IOException m24684m(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* renamed from: n */
        public final void m24685n(String[] strArr) throws IOException {
            if (strArr.length != C6446a.this.f21682h) {
                throw m24684m(strArr);
            }
            for (int i9 = 0; i9 < strArr.length; i9++) {
                try {
                    this.f21696b[i9] = Long.parseLong(strArr[i9]);
                } catch (NumberFormatException unused) {
                    throw m24684m(strArr);
                }
            }
        }

        public d(String str) {
            this.f21695a = str;
            this.f21696b = new long[C6446a.this.f21682h];
            this.f21697c = new File[C6446a.this.f21682h];
            this.f21698d = new File[C6446a.this.f21682h];
            StringBuilder sb = new StringBuilder(str);
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            int length = sb.length();
            for (int i9 = 0; i9 < C6446a.this.f21682h; i9++) {
                sb.append(i9);
                this.f21697c[i9] = new File(C6446a.this.f21676b, sb.toString());
                sb.append(".tmp");
                this.f21698d[i9] = new File(C6446a.this.f21676b, sb.toString());
                sb.setLength(length);
            }
        }
    }

    /* renamed from: v0.a$e */
    public final class e {

        /* renamed from: a */
        public final String f21703a;

        /* renamed from: b */
        public final long f21704b;

        /* renamed from: c */
        public final long[] f21705c;

        /* renamed from: d */
        public final File[] f21706d;

        public /* synthetic */ e(C6446a c6446a, String str, long j9, File[] fileArr, long[] jArr, a aVar) {
            this(str, j9, fileArr, jArr);
        }

        /* renamed from: a */
        public File m24686a(int i9) {
            return this.f21706d[i9];
        }

        public e(String str, long j9, File[] fileArr, long[] jArr) {
            this.f21703a = str;
            this.f21704b = j9;
            this.f21706d = fileArr;
            this.f21705c = jArr;
        }
    }

    public C6446a(File file, int i9, int i10, long j9) {
        this.f21676b = file;
        this.f21680f = i9;
        this.f21677c = new File(file, "journal");
        this.f21678d = new File(file, "journal.tmp");
        this.f21679e = new File(file, "journal.bkp");
        this.f21682h = i10;
        this.f21681g = j9;
    }

    /* renamed from: E */
    public static void m24642E(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    /* renamed from: J */
    public static C6446a m24643J(File file, int i9, int i10, long j9) throws IOException {
        if (j9 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i10 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                m24644P(file2, file3, false);
            }
        }
        C6446a c6446a = new C6446a(file, i9, i10, j9);
        if (c6446a.f21677c.exists()) {
            try {
                c6446a.m24660L();
                c6446a.m24659K();
                return c6446a;
            } catch (IOException e9) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e9.getMessage() + ", removing");
                c6446a.m24654D();
            }
        }
        file.mkdirs();
        C6446a c6446a2 = new C6446a(file, i9, i10, j9);
        c6446a2.m24662N();
        return c6446a2;
    }

    /* renamed from: P */
    public static void m24644P(File file, File file2, boolean z8) throws IOException {
        if (z8) {
            m24642E(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* renamed from: B */
    public final void m24652B() {
        if (this.f21684j == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* renamed from: C */
    public final synchronized void m24653C(c cVar, boolean z8) {
        d dVar = cVar.f21691a;
        if (dVar.f21700f != cVar) {
            throw new IllegalStateException();
        }
        if (z8 && !dVar.f21699e) {
            for (int i9 = 0; i9 < this.f21682h; i9++) {
                if (!cVar.f21692b[i9]) {
                    cVar.m24668a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i9);
                }
                if (!dVar.m24682k(i9).exists()) {
                    cVar.m24668a();
                    return;
                }
            }
        }
        for (int i10 = 0; i10 < this.f21682h; i10++) {
            File fileM24682k = dVar.m24682k(i10);
            if (!z8) {
                m24642E(fileM24682k);
            } else if (fileM24682k.exists()) {
                File fileM24681j = dVar.m24681j(i10);
                fileM24682k.renameTo(fileM24681j);
                long j9 = dVar.f21696b[i10];
                long length = fileM24681j.length();
                dVar.f21696b[i10] = length;
                this.f21683i = (this.f21683i - j9) + length;
            }
        }
        this.f21686l++;
        dVar.f21700f = null;
        if (dVar.f21699e || z8) {
            dVar.f21699e = true;
            this.f21684j.append((CharSequence) "CLEAN");
            this.f21684j.append(' ');
            this.f21684j.append((CharSequence) dVar.f21695a);
            this.f21684j.append((CharSequence) dVar.m24683l());
            this.f21684j.append('\n');
            if (z8) {
                long j10 = this.f21687m;
                this.f21687m = 1 + j10;
                dVar.f21701g = j10;
            }
        } else {
            this.f21685k.remove(dVar.f21695a);
            this.f21684j.append((CharSequence) "REMOVE");
            this.f21684j.append(' ');
            this.f21684j.append((CharSequence) dVar.f21695a);
            this.f21684j.append('\n');
        }
        this.f21684j.flush();
        if (this.f21683i > this.f21681g || m24658I()) {
            this.f21688n.submit(this.f21689o);
        }
    }

    /* renamed from: D */
    public void m24654D() throws IOException {
        close();
        C6448c.m24692b(this.f21676b);
    }

    /* renamed from: F */
    public c m24655F(String str) {
        return m24656G(str, -1L);
    }

    /* renamed from: G */
    public final synchronized c m24656G(String str, long j9) {
        m24652B();
        d dVar = this.f21685k.get(str);
        a aVar = null;
        if (j9 != -1 && (dVar == null || dVar.f21701g != j9)) {
            return null;
        }
        if (dVar == null) {
            dVar = new d(this, str, aVar);
            this.f21685k.put(str, dVar);
        } else if (dVar.f21700f != null) {
            return null;
        }
        c cVar = new c(this, dVar, aVar);
        dVar.f21700f = cVar;
        this.f21684j.append((CharSequence) "DIRTY");
        this.f21684j.append(' ');
        this.f21684j.append((CharSequence) str);
        this.f21684j.append('\n');
        this.f21684j.flush();
        return cVar;
    }

    /* renamed from: H */
    public synchronized e m24657H(String str) {
        m24652B();
        d dVar = this.f21685k.get(str);
        if (dVar == null) {
            return null;
        }
        if (!dVar.f21699e) {
            return null;
        }
        for (File file : dVar.f21697c) {
            if (!file.exists()) {
                return null;
            }
        }
        this.f21686l++;
        this.f21684j.append((CharSequence) "READ");
        this.f21684j.append(' ');
        this.f21684j.append((CharSequence) str);
        this.f21684j.append('\n');
        if (m24658I()) {
            this.f21688n.submit(this.f21689o);
        }
        return new e(this, str, dVar.f21701g, dVar.f21697c, dVar.f21696b, null);
    }

    /* renamed from: I */
    public final boolean m24658I() {
        int i9 = this.f21686l;
        return i9 >= 2000 && i9 >= this.f21685k.size();
    }

    /* renamed from: K */
    public final void m24659K() throws IOException {
        m24642E(this.f21678d);
        Iterator<d> it = this.f21685k.values().iterator();
        while (it.hasNext()) {
            d next = it.next();
            int i9 = 0;
            if (next.f21700f == null) {
                while (i9 < this.f21682h) {
                    this.f21683i += next.f21696b[i9];
                    i9++;
                }
            } else {
                next.f21700f = null;
                while (i9 < this.f21682h) {
                    m24642E(next.m24681j(i9));
                    m24642E(next.m24682k(i9));
                    i9++;
                }
                it.remove();
            }
        }
    }

    /* renamed from: L */
    public final void m24660L() throws IOException {
        C6447b c6447b = new C6447b(new FileInputStream(this.f21677c), C6448c.f21714a);
        try {
            String strM24690w = c6447b.m24690w();
            String strM24690w2 = c6447b.m24690w();
            String strM24690w3 = c6447b.m24690w();
            String strM24690w4 = c6447b.m24690w();
            String strM24690w5 = c6447b.m24690w();
            if (!"libcore.io.DiskLruCache".equals(strM24690w) || !"1".equals(strM24690w2) || !Integer.toString(this.f21680f).equals(strM24690w3) || !Integer.toString(this.f21682h).equals(strM24690w4) || !"".equals(strM24690w5)) {
                throw new IOException("unexpected journal header: [" + strM24690w + ", " + strM24690w2 + ", " + strM24690w4 + ", " + strM24690w5 + "]");
            }
            int i9 = 0;
            while (true) {
                try {
                    m24661M(c6447b.m24690w());
                    i9++;
                } catch (EOFException unused) {
                    this.f21686l = i9 - this.f21685k.size();
                    if (c6447b.m24689v()) {
                        m24662N();
                    } else {
                        this.f21684j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f21677c, true), C6448c.f21714a));
                    }
                    C6448c.m24691a(c6447b);
                    return;
                }
            }
        } catch (Throwable th) {
            C6448c.m24691a(c6447b);
            throw th;
        }
    }

    /* renamed from: M */
    public final void m24661M(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i9 = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i9);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i9);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.f21685k.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i9, iIndexOf2);
        }
        d dVar = this.f21685k.get(strSubstring);
        a aVar = null;
        if (dVar == null) {
            dVar = new d(this, strSubstring, aVar);
            this.f21685k.put(strSubstring, dVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(StringUtils.SPACE);
            dVar.f21699e = true;
            dVar.f21700f = null;
            dVar.m24685n(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
            dVar.f21700f = new c(this, dVar, aVar);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith("READ")) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    /* renamed from: N */
    public final synchronized void m24662N() {
        Writer writer = this.f21684j;
        if (writer != null) {
            writer.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f21678d), C6448c.f21714a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f21680f));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f21682h));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (d dVar : this.f21685k.values()) {
                if (dVar.f21700f != null) {
                    bufferedWriter.write("DIRTY " + dVar.f21695a + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + dVar.f21695a + dVar.m24683l() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f21677c.exists()) {
                m24644P(this.f21677c, this.f21679e, true);
            }
            m24644P(this.f21678d, this.f21677c, false);
            this.f21679e.delete();
            this.f21684j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f21677c, true), C6448c.f21714a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* renamed from: O */
    public synchronized boolean m24663O(String str) {
        m24652B();
        d dVar = this.f21685k.get(str);
        if (dVar != null && dVar.f21700f == null) {
            for (int i9 = 0; i9 < this.f21682h; i9++) {
                File fileM24681j = dVar.m24681j(i9);
                if (fileM24681j.exists() && !fileM24681j.delete()) {
                    throw new IOException("failed to delete " + fileM24681j);
                }
                this.f21683i -= dVar.f21696b[i9];
                dVar.f21696b[i9] = 0;
            }
            this.f21686l++;
            this.f21684j.append((CharSequence) "REMOVE");
            this.f21684j.append(' ');
            this.f21684j.append((CharSequence) str);
            this.f21684j.append('\n');
            this.f21685k.remove(str);
            if (m24658I()) {
                this.f21688n.submit(this.f21689o);
            }
            return true;
        }
        return false;
    }

    /* renamed from: Q */
    public final void m24664Q() {
        while (this.f21683i > this.f21681g) {
            m24663O(this.f21685k.entrySet().iterator().next().getKey());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.f21684j == null) {
            return;
        }
        Iterator it = new ArrayList(this.f21685k.values()).iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            if (dVar.f21700f != null) {
                dVar.f21700f.m24668a();
            }
        }
        m24664Q();
        this.f21684j.close();
        this.f21684j = null;
    }
}
