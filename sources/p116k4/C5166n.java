package p116k4;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p209u2.C6369f;
import p209u2.C6383t;
import p209u2.C6385v;

/* renamed from: k4.n */
/* loaded from: classes.dex */
public final class C5166n {

    /* renamed from: a */
    public static List<Uri> f17702a = new ArrayList();

    /* renamed from: k4.n$a */
    public class a implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Activity f17703a;

        /* renamed from: b */
        public final /* synthetic */ int f17704b;

        /* renamed from: c */
        public final /* synthetic */ String f17705c;

        public a(Activity activity, int i9, String str) {
            this.f17703a = activity;
            this.f17704b = i9;
            this.f17705c = str;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            new b(this.f17703a, this.f17704b, this.f17705c, null).executeOnExecutor(C6385v.f21553a, new Void[0]);
        }
    }

    /* renamed from: k4.n$b */
    public static class b extends AsyncTask<Void, Void, File> {

        /* renamed from: a */
        public final WeakReference<Activity> f17706a;

        /* renamed from: b */
        public final int f17707b;

        /* renamed from: c */
        public final String f17708c;

        /* renamed from: d */
        public DialogC3133q f17709d;

        public /* synthetic */ b(Activity activity, int i9, String str, a aVar) {
            this(activity, i9, str);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public File doInBackground(Void... voidArr) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
            Activity activity = this.f17706a.get();
            if (activity == null) {
                return null;
            }
            CLUtility.m16571p(activity.getApplicationContext(), false);
            return new File(CLUtility.m16496V());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(File file) {
            Activity activity = this.f17706a.get();
            if (activity == null) {
                return;
            }
            if ((activity instanceof BaseActivity) && ((BaseActivity) activity).m7364e()) {
                return;
            }
            DialogC3133q dialogC3133q = this.f17709d;
            if (dialogC3133q != null) {
                dialogC3133q.dismiss();
            }
            Uri uriM24471m = C6369f.m24471m(file);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(1);
            intent.putExtra("android.intent.extra.SUBJECT", "U log data").putExtra("android.intent.extra.TEXT", "Collected U log data in a zip file.").putExtra("android.intent.extra.STREAM", uriM24471m).setDataAndType(uriM24471m, "application/zip");
            if (!C6383t.m24517f(this.f17708c)) {
                intent.putExtra("android.intent.extra.EMAIL", new String[]{this.f17708c});
            }
            C5166n.m20133d(uriM24471m);
            if (this.f17707b == 0) {
                activity.startActivity(Intent.createChooser(intent, "Send U log data via..."));
            } else {
                activity.startActivityForResult(Intent.createChooser(intent, "Send U log data via..."), this.f17707b);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            Activity activity = this.f17706a.get();
            if (activity == null) {
                cancel(true);
            } else {
                this.f17709d = new DialogC3133q.b(activity).m16411b();
            }
        }

        public b(Activity activity, int i9, String str) {
            this.f17706a = new WeakReference<>(activity);
            this.f17707b = i9;
            this.f17708c = str;
        }
    }

    /* renamed from: d */
    public static void m20133d(Uri uri) {
        String str = "com.cyberlink.UBeta";
        if ("com.cyberlink.UBeta".equals(Globals.m7372O().getApplicationInfo().packageName)) {
            str = "com.cyberlink.U";
        } else if (!"com.cyberlink.U".equals(Globals.m7372O().getApplicationInfo().packageName)) {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Globals.m7372O().grantUriPermission(str, uri, 1);
        f17702a.add(uri);
    }

    /* renamed from: f */
    public static /* synthetic */ void m20135f(BaseActivity baseActivity, DialogInterface dialogInterface, int i9) {
        if (baseActivity.m7364e()) {
            return;
        }
        dialogInterface.dismiss();
    }

    /* renamed from: g */
    public static void m20136g() {
        Iterator<Uri> it = f17702a.iterator();
        while (it.hasNext()) {
            Globals.m7372O().revokeUriPermission(it.next(), 1);
        }
        f17702a.clear();
    }

    /* renamed from: h */
    public static void m20137h(Activity activity) {
        m20138i(activity, 0);
    }

    /* renamed from: i */
    public static void m20138i(Activity activity, int i9) {
        m20139j(activity, i9, null);
    }

    /* renamed from: j */
    public static void m20139j(Activity activity, int i9, String str) {
        C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new a(activity, i9, str), activity);
    }

    /* renamed from: k */
    public static void m20140k(final BaseActivity baseActivity) {
        C3123g.m16382a(baseActivity).setMessage("Do you want to send U data and log to improve user experience?").setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: k4.l
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                C5166n.m20137h(baseActivity);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: k4.m
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                C5166n.m20135f(baseActivity, dialogInterface, i9);
            }
        }).setCancelable(false).create().show();
    }
}
