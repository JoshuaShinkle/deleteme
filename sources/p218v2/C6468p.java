package p218v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.LongSparseArray;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p116k4.C5154j;
import p116k4.C5187v0;
import p209u2.AbstractC6381r;
import p209u2.C6369f;
import p209u2.C6370g;
import p209u2.C6385v;
import p218v2.C6468p;

/* renamed from: v2.p */
/* loaded from: classes.dex */
public class C6468p {

    /* renamed from: f */
    public static final C6468p f21789f = new C6468p();

    /* renamed from: a */
    public final DownloadManager f21790a;

    /* renamed from: b */
    public final SharedPreferences f21791b;

    /* renamed from: c */
    public final LongSparseArray<C1199a> f21792c = new LongSparseArray<>();

    /* renamed from: d */
    public final b f21793d;

    /* renamed from: e */
    public final ArrayList<c> f21794e;

    /* renamed from: v2.p$b */
    public class b extends BroadcastReceiver {

        /* renamed from: v2.p$b$a */
        public class a extends AbstractC6381r<String, Void> {

            /* renamed from: c */
            public final /* synthetic */ long f21796c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Handler handler, long j9) {
                super(handler);
                this.f21796c = j9;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(String str) {
                C6468p.this.f21791b.edit().putString(String.valueOf(this.f21796c), str).apply();
                C6468p.this.m24797t(str, false);
            }
        }

        public b() {
        }

        /* renamed from: c */
        public static /* synthetic */ void m24803c(String str, File file, C1199a c1199a, long j9, AbstractC6381r abstractC6381r) throws IOException {
            try {
                FileInputStream fileInputStream = new FileInputStream(str);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        C2889b.m14298h().m14303e(fileInputStream, fileOutputStream, c1199a);
                        C6369f.m24463e(new File(str));
                        String absolutePath = file.getAbsolutePath();
                        ULogUtility.m16680p("DownloadManager", " > decrypt: " + j9 + " > " + absolutePath);
                        abstractC6381r.m24506d(absolutePath);
                        fileOutputStream.close();
                        fileInputStream.close();
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
        }

        /* renamed from: b */
        public final void m24804b(final long j9, final String str, final AbstractC6381r<String, Void> abstractC6381r) {
            final C1199a c1199a = (C1199a) C6468p.this.f21792c.get(j9);
            if (c1199a == null || !str.endsWith(".clsm")) {
                abstractC6381r.m24506d(str);
            } else {
                final File fileM24467i = C6369f.m24467i(new File(str.substring(0, str.length() - 5)));
                C6385v.m24525c(new Runnable() { // from class: v2.q
                    @Override // java.lang.Runnable
                    public final void run() throws IOException {
                        C6468p.b.m24803c(str, fileM24467i, c1199a, j9, abstractC6381r);
                    }
                });
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction()) && C6468p.this.f21790a != null) {
                long longExtra = intent.getLongExtra("extra_download_id", 0L);
                Cursor cursorQuery = C6468p.this.f21790a.query(new DownloadManager.Query().setFilterById(longExtra));
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    if (cursorQuery.getInt(cursorQuery.getColumnIndex("status")) == 8) {
                        String strM24795l = C6468p.this.m24795l(longExtra);
                        ULogUtility.m16680p("DownloadManager", " > complete: " + longExtra + " > " + strM24795l);
                        if (strM24795l != null) {
                            m24804b(longExtra, strM24795l, new a(new Handler(Looper.getMainLooper()), longExtra));
                        }
                    } else {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("uri"));
                        int i9 = cursorQuery.getInt(cursorQuery.getColumnIndex("reason"));
                        C6468p.this.f21791b.edit().remove(string).apply();
                        C6468p.this.f21791b.edit().remove(String.valueOf(longExtra)).apply();
                        ULogUtility.m16684t("DownloadManager", " > failed: " + longExtra + " > " + i9);
                    }
                }
                C6370g.m24480b(cursorQuery);
            }
        }
    }

    /* renamed from: v2.p$c */
    public interface c {
        /* renamed from: a */
        void mo117a(String str, boolean z8);
    }

    public C6468p() {
        b bVar = new b();
        this.f21793d = bVar;
        this.f21794e = new ArrayList<>();
        Context contextM7372O = Globals.m7372O();
        this.f21790a = (DownloadManager) contextM7372O.getSystemService("download");
        SharedPreferences sharedPreferences = contextM7372O.getSharedPreferences("cached_download_file_map", 0);
        this.f21791b = sharedPreferences;
        contextM7372O.registerReceiver(bVar, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), 2);
        if ("2018.05.24".equals(sharedPreferences.getString("V", ""))) {
            return;
        }
        sharedPreferences.edit().clear().putString("V", "2018.05.24").apply();
    }

    /* renamed from: m */
    public static C6468p m24787m() {
        return f21789f;
    }

    /* renamed from: o */
    public static /* synthetic */ void m24788o(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: p */
    public static /* synthetic */ void m24789p(DownloadManager downloadManager, long j9, DialogInterface dialogInterface) {
        downloadManager.remove(j9);
    }

    /* renamed from: r */
    public static /* synthetic */ void m24791r(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m24792s(DownloadManager downloadManager, long j9, Activity activity, final DialogC6459g dialogC6459g, ScheduledExecutorService scheduledExecutorService) throws Throwable {
        int[] iArrM24796n = m24796n(downloadManager, j9);
        int i9 = iArrM24796n[0];
        int i10 = iArrM24796n[1];
        int i11 = iArrM24796n[2];
        final int i12 = (int) (((i9 * 1.0d) / i10) * 100.0d);
        activity.runOnUiThread(new Runnable() { // from class: v2.m
            @Override // java.lang.Runnable
            public final void run() {
                dialogC6459g.m24772o(i12);
            }
        });
        if (i11 == 1 || i11 == 2 || i11 == 4) {
            return;
        }
        Objects.requireNonNull(dialogC6459g);
        activity.runOnUiThread(new Runnable() { // from class: v2.n
            @Override // java.lang.Runnable
            public final void run() {
                dialogC6459g.dismiss();
            }
        });
        scheduledExecutorService.shutdown();
        if (i11 == 16) {
            Context contextM7372O = Globals.m7372O();
            AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
            builderM16382a.setMessage(contextM7372O.getString(R.string.open_file_no_exist_on_server));
            builderM16382a.setPositiveButton(contextM7372O.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: v2.o
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i13) {
                    C6468p.m24791r(dialogInterface, i13);
                }
            });
            builderM16382a.show();
        }
    }

    /* renamed from: u */
    public static void m24793u(Activity activity, String str, boolean z8) {
        Intent intent = new Intent(z8 ? "android.intent.action.SEND" : "android.intent.action.VIEW");
        File file = new File(str);
        Uri uriM24471m = C6369f.m24471m(file);
        try {
            String strM24469k = C6369f.m24469k(file);
            String mimeTypeFromExtension = !strM24469k.equals("") ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(strM24469k) : null;
            if (mimeTypeFromExtension == null) {
                mimeTypeFromExtension = "*/*";
            }
            if (z8) {
                intent.putExtra("android.intent.extra.STREAM", uriM24471m);
                intent.setType(mimeTypeFromExtension);
                activity.startActivity(Intent.createChooser(intent, null));
            } else {
                intent.setFlags(603979777);
                intent.setDataAndType(uriM24471m, mimeTypeFromExtension);
                activity.startActivity(intent);
            }
        } catch (ActivityNotFoundException unused) {
            if (z8) {
                return;
            }
            C3123g.m16382a(activity).setMessage(R.string.error_install_app_to_view_file).setPositiveButton(R.string.got_it, (DialogInterface.OnClickListener) null).create().show();
        }
    }

    /* renamed from: k */
    public void m24794k(c cVar) {
        if (this.f21794e.contains(cVar)) {
            return;
        }
        this.f21794e.add(cVar);
    }

    /* renamed from: l */
    public final String m24795l(long j9) {
        Uri uriForDownloadedFile = this.f21790a.getUriForDownloadedFile(j9);
        if (uriForDownloadedFile == null) {
            return null;
        }
        return CLUtility.m16576q0(Globals.m7372O(), uriForDownloadedFile);
    }

    /* renamed from: n */
    public final int[] m24796n(DownloadManager downloadManager, long j9) throws Throwable {
        Cursor cursorQuery;
        int[] iArr = {-1, -1, 0};
        try {
            cursorQuery = downloadManager.query(new DownloadManager.Query().setFilterById(j9));
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        iArr[0] = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("bytes_so_far"));
                        iArr[1] = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("total_size"));
                        iArr[2] = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                    }
                } catch (Throwable th) {
                    th = th;
                    C6370g.m24480b(cursorQuery);
                    throw th;
                }
            }
            C6370g.m24480b(cursorQuery);
            return iArr;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
    }

    /* renamed from: t */
    public final void m24797t(String str, boolean z8) {
        Iterator<c> it = this.f21794e.iterator();
        while (it.hasNext()) {
            it.next().mo117a(str, z8);
        }
    }

    /* renamed from: v */
    public void m24798v(Activity activity, String str, String str2, C1199a c1199a, boolean z8) {
        m24799w(activity, str, str2, c1199a, z8, null);
    }

    /* renamed from: w */
    public void m24799w(Activity activity, String str, String str2, C1199a c1199a, boolean z8, Date date) {
        String str3;
        ULogUtility.m16683s("DownloadManager", "open: " + str2);
        ULogUtility.m16683s("DownloadManager", "url: " + str);
        Context contextM7372O = Globals.m7372O();
        final AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
        builderM16382a.setMessage(contextM7372O.getString(R.string.open_file_no_exist_on_server));
        builderM16382a.setPositiveButton(contextM7372O.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: v2.h
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                C6468p.m24788o(dialogInterface, i9);
            }
        });
        long j9 = this.f21791b.getLong(str, -1L);
        if (j9 >= 0) {
            new Date();
            if (m24795l(j9) != null) {
                String string = this.f21791b.getString(String.valueOf(j9), null);
                if (string != null && new File(string).exists()) {
                    ULogUtility.m16670f("DownloadManager", " > found in cache: " + string);
                    m24797t(string, z8);
                    return;
                }
                if (date != null && new Date().after(date)) {
                    activity.runOnUiThread(new Runnable() { // from class: v2.i
                        @Override // java.lang.Runnable
                        public final void run() {
                            builderM16382a.show();
                        }
                    });
                    return;
                }
            } else if (date != null && new Date().after(date)) {
                activity.runOnUiThread(new Runnable() { // from class: v2.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        builderM16382a.show();
                    }
                });
                return;
            }
            Cursor cursorQuery = this.f21790a.query(new DownloadManager.Query().setFilterById(j9));
            if (cursorQuery != null && cursorQuery.moveToFirst() && cursorQuery.getInt(cursorQuery.getColumnIndex("status")) == 2) {
                return;
            }
        }
        if (!C6456d.m24714D().m24748G()) {
            C5187v0.m20267c(R.string.error_no_network);
            return;
        }
        if (!URLUtil.isNetworkUrl(str)) {
            builderM16382a.show();
            return;
        }
        Uri uri = Uri.parse(str);
        if (c1199a == null) {
            str3 = str2;
        } else {
            str3 = str2 + ".clsm";
        }
        String strEncode = Uri.encode(str3);
        try {
            long jEnqueue = this.f21790a.enqueue(new DownloadManager.Request(uri).setAllowedNetworkTypes(3).setAllowedOverRoaming(true).setTitle(str2).setDestinationInExternalFilesDir(contextM7372O, null, strEncode));
            if (c1199a != null) {
                this.f21792c.put(jEnqueue, c1199a);
            }
            ULogUtility.m16683s("DownloadManager", " > request: " + jEnqueue + " > " + strEncode);
            this.f21791b.edit().putLong(str, jEnqueue).apply();
            m24801y(activity, this.f21790a, jEnqueue, str2);
        } catch (Exception e9) {
            C5187v0.m20268d(String.format(Globals.m7372O().getString(R.string.error_download_file_failed), -1001));
            ULogUtility.m16670f("DownloadManager", "DownloadManager enqueue download exception:" + e9);
        }
    }

    /* renamed from: x */
    public void m24800x(c cVar) {
        this.f21794e.remove(cVar);
    }

    /* renamed from: y */
    public final void m24801y(final Activity activity, final DownloadManager downloadManager, final long j9, String str) {
        final ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1);
        final DialogC6459g dialogC6459g = new DialogC6459g(activity, R.style.FriendSelectorDialog);
        dialogC6459g.m24766h(true);
        dialogC6459g.m24772o(0);
        dialogC6459g.m24761c(R.drawable.icon_file_default_upload);
        dialogC6459g.setCancelable(true);
        dialogC6459g.m24770m(new C6462j(dialogC6459g));
        dialogC6459g.m24776s(str);
        dialogC6459g.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: v2.k
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                C6468p.m24789p(downloadManager, j9, dialogInterface);
            }
        });
        dialogC6459g.show();
        scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(new Runnable() { // from class: v2.l
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f21780b.m24792s(downloadManager, j9, activity, dialogC6459g, scheduledExecutorServiceNewScheduledThreadPool);
            }
        }, 0L, 500L, TimeUnit.MILLISECONDS);
    }
}
