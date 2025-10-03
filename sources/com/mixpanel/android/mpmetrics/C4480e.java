package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* renamed from: com.mixpanel.android.mpmetrics.e */
/* loaded from: classes2.dex */
public class C4480e {

    /* renamed from: a */
    public final Executor f15792a = Executors.newSingleThreadExecutor();

    /* renamed from: com.mixpanel.android.mpmetrics.e$a */
    public static class a implements Callable<SharedPreferences> {

        /* renamed from: b */
        public final Context f15793b;

        /* renamed from: c */
        public final String f15794c;

        /* renamed from: d */
        public final b f15795d;

        public a(Context context, String str, b bVar) {
            this.f15793b = context;
            this.f15794c = str;
            this.f15795d = bVar;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SharedPreferences call() {
            SharedPreferences sharedPreferences = this.f15793b.getSharedPreferences(this.f15794c, 0);
            b bVar = this.f15795d;
            if (bVar != null) {
                bVar.mo17967a(sharedPreferences);
            }
            return sharedPreferences;
        }
    }

    /* renamed from: com.mixpanel.android.mpmetrics.e$b */
    public interface b {
        /* renamed from: a */
        void mo17967a(SharedPreferences sharedPreferences);
    }

    /* renamed from: a */
    public Future<SharedPreferences> m17982a(Context context, String str, b bVar) {
        FutureTask futureTask = new FutureTask(new a(context, str, bVar));
        this.f15792a.execute(futureTask);
        return futureTask;
    }
}
