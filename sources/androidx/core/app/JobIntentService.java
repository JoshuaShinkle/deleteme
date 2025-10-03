package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class JobIntentService extends Service {

    /* renamed from: i */
    public static final Object f1746i = new Object();

    /* renamed from: j */
    public static final HashMap<ComponentName, AbstractC0320g> f1747j = new HashMap<>();

    /* renamed from: b */
    public InterfaceC0315b f1748b;

    /* renamed from: c */
    public AbstractC0320g f1749c;

    /* renamed from: d */
    public AsyncTaskC0314a f1750d;

    /* renamed from: e */
    public boolean f1751e = false;

    /* renamed from: f */
    public boolean f1752f = false;

    /* renamed from: g */
    public boolean f1753g = false;

    /* renamed from: h */
    public final ArrayList<C0316c> f1754h = null;

    /* renamed from: androidx.core.app.JobIntentService$a */
    public final class AsyncTaskC0314a extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC0314a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                InterfaceC0317d interfaceC0317dM1474a = JobIntentService.this.m1474a();
                if (interfaceC0317dM1474a == null) {
                    return null;
                }
                JobIntentService.this.mo1477g(interfaceC0317dM1474a.getIntent());
                interfaceC0317dM1474a.mo1485a();
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Void r12) {
            JobIntentService.this.m1479i();
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
            JobIntentService.this.m1479i();
        }
    }

    /* renamed from: androidx.core.app.JobIntentService$b */
    public interface InterfaceC0315b {
        /* renamed from: a */
        IBinder mo1483a();

        /* renamed from: b */
        InterfaceC0317d mo1484b();
    }

    /* renamed from: androidx.core.app.JobIntentService$c */
    public final class C0316c implements InterfaceC0317d {

        /* renamed from: a */
        public final Intent f1756a;

        /* renamed from: b */
        public final int f1757b;

        public C0316c(Intent intent, int i9) {
            this.f1756a = intent;
            this.f1757b = i9;
        }

        @Override // androidx.core.app.JobIntentService.InterfaceC0317d
        /* renamed from: a */
        public void mo1485a() {
            JobIntentService.this.stopSelf(this.f1757b);
        }

        @Override // androidx.core.app.JobIntentService.InterfaceC0317d
        public Intent getIntent() {
            return this.f1756a;
        }
    }

    /* renamed from: androidx.core.app.JobIntentService$d */
    public interface InterfaceC0317d {
        /* renamed from: a */
        void mo1485a();

        Intent getIntent();
    }

    /* renamed from: androidx.core.app.JobIntentService$e */
    public static final class JobServiceEngineC0318e extends JobServiceEngine implements InterfaceC0315b {

        /* renamed from: a */
        public final JobIntentService f1759a;

        /* renamed from: b */
        public final Object f1760b;

        /* renamed from: c */
        public JobParameters f1761c;

        /* renamed from: androidx.core.app.JobIntentService$e$a */
        public final class a implements InterfaceC0317d {

            /* renamed from: a */
            public final JobWorkItem f1762a;

            public a(JobWorkItem jobWorkItem) {
                this.f1762a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.InterfaceC0317d
            /* renamed from: a */
            public void mo1485a() {
                synchronized (JobServiceEngineC0318e.this.f1760b) {
                    JobParameters jobParameters = JobServiceEngineC0318e.this.f1761c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f1762a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.InterfaceC0317d
            public Intent getIntent() {
                return this.f1762a.getIntent();
            }
        }

        public JobServiceEngineC0318e(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f1760b = new Object();
            this.f1759a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.InterfaceC0315b
        /* renamed from: a */
        public IBinder mo1483a() {
            return getBinder();
        }

        @Override // androidx.core.app.JobIntentService.InterfaceC0315b
        /* renamed from: b */
        public InterfaceC0317d mo1484b() {
            synchronized (this.f1760b) {
                JobParameters jobParameters = this.f1761c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem jobWorkItemDequeueWork = jobParameters.dequeueWork();
                if (jobWorkItemDequeueWork == null) {
                    return null;
                }
                jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.f1759a.getClassLoader());
                return new a(jobWorkItemDequeueWork);
            }
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f1761c = jobParameters;
            this.f1759a.m1476e(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zM1475b = this.f1759a.m1475b();
            synchronized (this.f1760b) {
                this.f1761c = null;
            }
            return zM1475b;
        }
    }

    /* renamed from: androidx.core.app.JobIntentService$f */
    public static final class C0319f extends AbstractC0320g {

        /* renamed from: d */
        public final JobInfo f1764d;

        /* renamed from: e */
        public final JobScheduler f1765e;

        public C0319f(Context context, ComponentName componentName, int i9) {
            super(componentName);
            m1487b(i9);
            this.f1764d = new JobInfo.Builder(i9, this.f1766a).setOverrideDeadline(0L).build();
            this.f1765e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override // androidx.core.app.JobIntentService.AbstractC0320g
        /* renamed from: a */
        public void mo1486a(Intent intent) {
            this.f1765e.enqueue(this.f1764d, new JobWorkItem(intent));
        }
    }

    /* renamed from: androidx.core.app.JobIntentService$g */
    public static abstract class AbstractC0320g {

        /* renamed from: a */
        public final ComponentName f1766a;

        /* renamed from: b */
        public boolean f1767b;

        /* renamed from: c */
        public int f1768c;

        public AbstractC0320g(ComponentName componentName) {
            this.f1766a = componentName;
        }

        /* renamed from: a */
        public abstract void mo1486a(Intent intent);

        /* renamed from: b */
        public void m1487b(int i9) {
            if (!this.f1767b) {
                this.f1767b = true;
                this.f1768c = i9;
            } else {
                if (this.f1768c == i9) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i9 + " is different than previous " + this.f1768c);
            }
        }

        /* renamed from: c */
        public void m1488c() {
        }

        /* renamed from: d */
        public void m1489d() {
        }

        /* renamed from: e */
        public void m1490e() {
        }
    }

    /* renamed from: c */
    public static void m1471c(Context context, ComponentName componentName, int i9, Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized (f1746i) {
            AbstractC0320g abstractC0320gM1473f = m1473f(context, componentName, true, i9);
            abstractC0320gM1473f.m1487b(i9);
            abstractC0320gM1473f.mo1486a(intent);
        }
    }

    /* renamed from: d */
    public static void m1472d(Context context, Class<?> cls, int i9, Intent intent) {
        m1471c(context, new ComponentName(context, cls), i9, intent);
    }

    /* renamed from: f */
    public static AbstractC0320g m1473f(Context context, ComponentName componentName, boolean z8, int i9) {
        HashMap<ComponentName, AbstractC0320g> map = f1747j;
        AbstractC0320g abstractC0320g = map.get(componentName);
        if (abstractC0320g != null) {
            return abstractC0320g;
        }
        if (!z8) {
            throw new IllegalArgumentException("Can't be here without a job id");
        }
        C0319f c0319f = new C0319f(context, componentName, i9);
        map.put(componentName, c0319f);
        return c0319f;
    }

    /* renamed from: a */
    public InterfaceC0317d m1474a() {
        InterfaceC0315b interfaceC0315b = this.f1748b;
        if (interfaceC0315b != null) {
            return interfaceC0315b.mo1484b();
        }
        synchronized (this.f1754h) {
            if (this.f1754h.size() <= 0) {
                return null;
            }
            return this.f1754h.remove(0);
        }
    }

    /* renamed from: b */
    public boolean m1475b() {
        AsyncTaskC0314a asyncTaskC0314a = this.f1750d;
        if (asyncTaskC0314a != null) {
            asyncTaskC0314a.cancel(this.f1751e);
        }
        this.f1752f = true;
        return m1478h();
    }

    /* renamed from: e */
    public void m1476e(boolean z8) {
        if (this.f1750d == null) {
            this.f1750d = new AsyncTaskC0314a();
            AbstractC0320g abstractC0320g = this.f1749c;
            if (abstractC0320g != null && z8) {
                abstractC0320g.m1489d();
            }
            this.f1750d.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: g */
    public abstract void mo1477g(Intent intent);

    /* renamed from: h */
    public boolean m1478h() {
        return true;
    }

    /* renamed from: i */
    public void m1479i() {
        ArrayList<C0316c> arrayList = this.f1754h;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1750d = null;
                ArrayList<C0316c> arrayList2 = this.f1754h;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    m1476e(false);
                } else if (!this.f1753g) {
                    this.f1749c.m1488c();
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        InterfaceC0315b interfaceC0315b = this.f1748b;
        if (interfaceC0315b != null) {
            return interfaceC0315b.mo1483a();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f1748b = new JobServiceEngineC0318e(this);
        this.f1749c = null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<C0316c> arrayList = this.f1754h;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f1753g = true;
                this.f1749c.m1488c();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i9, int i10) {
        if (this.f1754h == null) {
            return 2;
        }
        this.f1749c.m1490e();
        synchronized (this.f1754h) {
            ArrayList<C0316c> arrayList = this.f1754h;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new C0316c(intent, i10));
            m1476e(true);
        }
        return 3;
    }
}
