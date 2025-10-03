package p012b1;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;

/* renamed from: b1.c */
/* loaded from: classes.dex */
public abstract class AbstractC0588c {

    /* renamed from: a */
    public static final AbstractC0588c f3107a = new a();

    /* renamed from: b */
    public static final AbstractC0588c f3108b = new b();

    /* renamed from: c */
    public static final AbstractC0588c f3109c = new c();

    /* renamed from: d */
    public static final AbstractC0588c f3110d = new d();

    /* renamed from: e */
    public static final AbstractC0588c f3111e = new e();

    /* renamed from: b1.c$a */
    public class a extends AbstractC0588c {
        @Override // p012b1.AbstractC0588c
        /* renamed from: a */
        public boolean mo3267a() {
            return true;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: b */
        public boolean mo3268b() {
            return true;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: c */
        public boolean mo3269c(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: d */
        public boolean mo3270d(boolean z8, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    }

    /* renamed from: b1.c$b */
    public class b extends AbstractC0588c {
        @Override // p012b1.AbstractC0588c
        /* renamed from: a */
        public boolean mo3267a() {
            return false;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: b */
        public boolean mo3268b() {
            return false;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: c */
        public boolean mo3269c(DataSource dataSource) {
            return false;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: d */
        public boolean mo3270d(boolean z8, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }
    }

    /* renamed from: b1.c$c */
    public class c extends AbstractC0588c {
        @Override // p012b1.AbstractC0588c
        /* renamed from: a */
        public boolean mo3267a() {
            return true;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: b */
        public boolean mo3268b() {
            return false;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: c */
        public boolean mo3269c(DataSource dataSource) {
            return (dataSource == DataSource.DATA_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: d */
        public boolean mo3270d(boolean z8, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }
    }

    /* renamed from: b1.c$d */
    public class d extends AbstractC0588c {
        @Override // p012b1.AbstractC0588c
        /* renamed from: a */
        public boolean mo3267a() {
            return false;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: b */
        public boolean mo3268b() {
            return true;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: c */
        public boolean mo3269c(DataSource dataSource) {
            return false;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: d */
        public boolean mo3270d(boolean z8, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    }

    /* renamed from: b1.c$e */
    public class e extends AbstractC0588c {
        @Override // p012b1.AbstractC0588c
        /* renamed from: a */
        public boolean mo3267a() {
            return true;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: b */
        public boolean mo3268b() {
            return true;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: c */
        public boolean mo3269c(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        @Override // p012b1.AbstractC0588c
        /* renamed from: d */
        public boolean mo3270d(boolean z8, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return ((z8 && dataSource == DataSource.DATA_DISK_CACHE) || dataSource == DataSource.LOCAL) && encodeStrategy == EncodeStrategy.TRANSFORMED;
        }
    }

    /* renamed from: a */
    public abstract boolean mo3267a();

    /* renamed from: b */
    public abstract boolean mo3268b();

    /* renamed from: c */
    public abstract boolean mo3269c(DataSource dataSource);

    /* renamed from: d */
    public abstract boolean mo3270d(boolean z8, DataSource dataSource, EncodeStrategy encodeStrategy);
}
