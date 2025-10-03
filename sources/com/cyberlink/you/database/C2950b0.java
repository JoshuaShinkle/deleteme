package com.cyberlink.you.database;

import android.net.Uri;
import android.provider.MediaStore;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.ULogUtility;
import java.util.concurrent.ConcurrentHashMap;
import net.sqlcipher.DatabaseErrorHandler;
import net.sqlcipher.database.SQLiteDatabase;
import p116k4.C5154j;
import p227w2.C6518a;

/* renamed from: com.cyberlink.you.database.b0 */
/* loaded from: classes.dex */
public class C2950b0 {

    /* renamed from: d */
    public static volatile C2956d0 f13120d;

    /* renamed from: a */
    public static final Uri f13117a = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    /* renamed from: b */
    public static final String f13118b = String.valueOf(1);

    /* renamed from: c */
    public static final Object f13119c = new Object();

    /* renamed from: e */
    public static final ConcurrentHashMap<String, Object> f13121e = new ConcurrentHashMap<>();

    /* renamed from: com.cyberlink.you.database.b0$b */
    public static class b implements DatabaseErrorHandler {
        public b() {
        }

        @Override // net.sqlcipher.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            try {
                String str = "Cannot migrate database from ver:" + sQLiteDatabase.getVersion() + " to ver:" + C2956d0.m14992c() + ", path:" + sQLiteDatabase.getPath();
                ULogUtility.m16687w("Database", "onCorruption ", "Corruption", str);
                throw new IllegalStateException(str);
            } catch (IllegalStateException e9) {
                C5154j.m20076j(e9);
            }
        }
    }

    /* renamed from: com.cyberlink.you.database.b0$c */
    public interface c<DAO> {
        /* renamed from: a */
        DAO mo14874a();
    }

    /* renamed from: A */
    public static C2957d1 m14899A() {
        return (C2957d1) m14907f(C2957d1.class, new c() { // from class: com.cyberlink.you.database.g
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2957d1();
            }
        });
    }

    /* renamed from: B */
    public static SQLiteDatabase m14900B() {
        String strConcat;
        if (Globals.m7388i0().m7409C1().booleanValue()) {
            strConcat = "";
            for (String str : C6518a.f21935g) {
                strConcat = strConcat.concat(str);
            }
        } else {
            strConcat = null;
        }
        return m14908g().getWritableDatabase(strConcat);
    }

    /* renamed from: C */
    public static boolean m14901C(Runnable runnable) {
        SQLiteDatabase sQLiteDatabaseM14900B = m14900B();
        try {
            sQLiteDatabaseM14900B.beginTransaction();
            runnable.run();
            sQLiteDatabaseM14900B.setTransactionSuccessful();
            sQLiteDatabaseM14900B.endTransaction();
            return true;
        } catch (Throwable th) {
            try {
                C5154j.m20076j(th);
                sQLiteDatabaseM14900B.endTransaction();
                return false;
            } catch (Throwable th2) {
                sQLiteDatabaseM14900B.endTransaction();
                throw th2;
            }
        }
    }

    /* renamed from: a */
    public static void m14902a(Runnable runnable) {
        if (f13120d != null) {
            synchronized (f13119c) {
                C2956d0 c2956d0 = f13120d;
                if (c2956d0 != null) {
                    c2956d0.close();
                }
                f13120d = null;
                f13121e.clear();
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }

    /* renamed from: b */
    public static boolean m14903b() {
        if (!Globals.m7388i0().m7409C1().booleanValue() || Globals.m7388i0().m7489T1().booleanValue()) {
            return false;
        }
        ULogUtility.m16670f("Database", "[DB][doRefreshDataBase] auto clear user data for fix crash issue");
        Globals.m7388i0().m7638x();
        Globals.m7388i0().m7496U3(true);
        return true;
    }

    /* renamed from: c */
    public static C2946a m14904c() {
        return (C2946a) m14907f(C2946a.class, new c() { // from class: com.cyberlink.you.database.a0
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2946a();
            }
        });
    }

    /* renamed from: d */
    public static C2952c m14905d() {
        return (C2952c) m14907f(C2952c.class, new c() { // from class: com.cyberlink.you.database.v
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2952c();
            }
        });
    }

    /* renamed from: e */
    public static C2955d m14906e() {
        return (C2955d) m14907f(C2955d.class, new c() { // from class: com.cyberlink.you.database.u
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2955d();
            }
        });
    }

    /* renamed from: f */
    public static <C> C m14907f(Class<C> cls, c<C> cVar) {
        String name = cls.getName();
        ConcurrentHashMap<String, Object> concurrentHashMap = f13121e;
        Object obj = (C) concurrentHashMap.get(name);
        if (obj == null) {
            synchronized (f13119c) {
                obj = concurrentHashMap.get(name);
                if (obj == null) {
                    try {
                        C cMo14874a = cVar.mo14874a();
                        concurrentHashMap.put(name, cMo14874a);
                        obj = cMo14874a;
                    } catch (Exception e9) {
                        Globals.m7388i0().m7496U3(false);
                        m14903b();
                        throw new IllegalStateException(e9);
                    }
                }
            }
        }
        return (C) obj;
    }

    /* renamed from: g */
    public static C2956d0 m14908g() {
        C2956d0 c2956d0 = f13120d;
        if (c2956d0 == null) {
            synchronized (f13119c) {
                c2956d0 = f13120d;
                if (c2956d0 == null) {
                    c2956d0 = new C2956d0(Globals.m7388i0(), new b());
                    f13120d = c2956d0;
                }
            }
        }
        return c2956d0;
    }

    /* renamed from: h */
    public static C2959e0 m14909h() {
        return (C2959e0) m14907f(C2959e0.class, new c() { // from class: com.cyberlink.you.database.o
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2959e0();
            }
        });
    }

    /* renamed from: i */
    public static C2961f0 m14910i() {
        return (C2961f0) m14907f(C2961f0.class, new c() { // from class: com.cyberlink.you.database.h
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2961f0();
            }
        });
    }

    /* renamed from: j */
    public static C2963g0 m14911j() {
        return (C2963g0) m14907f(C2963g0.class, new c() { // from class: com.cyberlink.you.database.w
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2963g0();
            }
        });
    }

    /* renamed from: k */
    public static C2965h0 m14912k() {
        return (C2965h0) m14907f(C2965h0.class, new c() { // from class: com.cyberlink.you.database.j
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2965h0();
            }
        });
    }

    /* renamed from: l */
    public static C2967i0 m14913l() {
        return (C2967i0) m14907f(C2967i0.class, new c() { // from class: com.cyberlink.you.database.k
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2967i0();
            }
        });
    }

    /* renamed from: m */
    public static MediaDao m14914m() {
        return (MediaDao) m14907f(MediaDao.class, new c() { // from class: com.cyberlink.you.database.p
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new MediaDao();
            }
        });
    }

    /* renamed from: n */
    public static C2969j0 m14915n() {
        return (C2969j0) m14907f(C2969j0.class, new c() { // from class: com.cyberlink.you.database.i
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2969j0();
            }
        });
    }

    /* renamed from: o */
    public static C2975m0 m14916o() {
        return (C2975m0) m14907f(C2975m0.class, new c() { // from class: com.cyberlink.you.database.y
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2975m0();
            }
        });
    }

    /* renamed from: p */
    public static C2977n0 m14917p() {
        return (C2977n0) m14907f(C2977n0.class, new c() { // from class: com.cyberlink.you.database.m
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2977n0();
            }
        });
    }

    /* renamed from: q */
    public static C2979o0 m14918q() {
        return (C2979o0) m14907f(C2979o0.class, new c() { // from class: com.cyberlink.you.database.t
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2979o0();
            }
        });
    }

    /* renamed from: r */
    public static C2983q0 m14919r() {
        return (C2983q0) m14907f(C2983q0.class, new c() { // from class: com.cyberlink.you.database.q
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2983q0();
            }
        });
    }

    /* renamed from: s */
    public static C2985r0 m14920s() {
        return (C2985r0) m14907f(C2985r0.class, new c() { // from class: com.cyberlink.you.database.x
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2985r0();
            }
        });
    }

    /* renamed from: t */
    public static C2987s0 m14921t() {
        return (C2987s0) m14907f(C2987s0.class, new c() { // from class: com.cyberlink.you.database.e
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2987s0();
            }
        });
    }

    /* renamed from: u */
    public static C2991u0 m14922u() {
        return (C2991u0) m14907f(C2991u0.class, new c() { // from class: com.cyberlink.you.database.z
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2991u0();
            }
        });
    }

    /* renamed from: v */
    public static C2995w0 m14923v() {
        return (C2995w0) m14907f(C2995w0.class, new c() { // from class: com.cyberlink.you.database.s
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2995w0();
            }
        });
    }

    /* renamed from: w */
    public static C2999y0 m14924w() {
        return (C2999y0) m14907f(C2999y0.class, new c() { // from class: com.cyberlink.you.database.l
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2999y0();
            }
        });
    }

    /* renamed from: x */
    public static C3001z0 m14925x() {
        return (C3001z0) m14907f(C3001z0.class, new c() { // from class: com.cyberlink.you.database.f
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C3001z0();
            }
        });
    }

    /* renamed from: y */
    public static C2948a1 m14926y() {
        return (C2948a1) m14907f(C2948a1.class, new c() { // from class: com.cyberlink.you.database.r
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2948a1();
            }
        });
    }

    /* renamed from: z */
    public static C2954c1 m14927z() {
        return (C2954c1) m14907f(C2954c1.class, new c() { // from class: com.cyberlink.you.database.n
            @Override // com.cyberlink.you.database.C2950b0.c
            /* renamed from: a */
            public final Object mo14874a() {
                return new C2954c1();
            }
        });
    }
}
