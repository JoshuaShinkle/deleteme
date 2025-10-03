package com.cyberlink.you.activity.filelist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.rockerhieu.emojicon.EmojiconTextView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import p004a3.C0013a;
import p116k4.C5178r;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p136m3.C5321e;
import p201t3.C6288b;
import p201t3.C6289c;
import p209u2.C6365b;
import p209u2.C6385v;
import p218v2.C6468p;

/* loaded from: classes.dex */
public class FileListActivity extends BaseActivity {

    /* renamed from: B */
    public static final String f10450B = "FileListActivity";

    /* renamed from: C */
    public static Group f10451C;

    /* renamed from: D */
    public static MyStatus f10452D;

    /* renamed from: c */
    public C6288b<C2973l0> f10454c;

    /* renamed from: d */
    public C0013a f10455d;

    /* renamed from: e */
    public ListView f10456e;

    /* renamed from: f */
    public View f10457f;

    /* renamed from: g */
    public View f10458g;

    /* renamed from: h */
    public Button f10459h;

    /* renamed from: i */
    public View f10460i;

    /* renamed from: j */
    public TextView f10461j;

    /* renamed from: k */
    public TextView f10462k;

    /* renamed from: l */
    public TextView f10463l;

    /* renamed from: m */
    public View f10464m;

    /* renamed from: n */
    public View f10465n;

    /* renamed from: o */
    public ViewGroup f10466o;

    /* renamed from: p */
    public C2114k f10467p = new C2114k(this, null);

    /* renamed from: q */
    public View.OnClickListener f10468q = new View.OnClickListener() { // from class: a3.h
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f102b.m12123x0(view);
        }
    };

    /* renamed from: r */
    public View.OnClickListener f10469r = new ViewOnClickListenerC2105b();

    /* renamed from: s */
    public View.OnClickListener f10470s = new ViewOnClickListenerC2106c();

    /* renamed from: t */
    public View.OnClickListener f10471t = new ViewOnClickListenerC2107d();

    /* renamed from: u */
    public final AdapterView.OnItemClickListener f10472u = new C2108e();

    /* renamed from: v */
    public C6468p.c f10473v = new C6468p.c() { // from class: a3.i
        @Override // p218v2.C6468p.c
        /* renamed from: a */
        public final void mo117a(String str, boolean z8) {
            this.f103a.m12125y0(str, z8);
        }
    };

    /* renamed from: w */
    public final C5321e.m f10474w = new C2109f();

    /* renamed from: x */
    public final List<C2973l0> f10475x = new ArrayList();

    /* renamed from: y */
    public C2907m0.g f10476y = new C2907m0.g() { // from class: a3.j
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f104b.m12135H0();
        }
    };

    /* renamed from: z */
    public C2907m0.h f10477z = new C2907m0.h() { // from class: a3.k
        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public final void mo119x(boolean z8) {
            this.f105b.m12115r0(z8);
        }
    };

    /* renamed from: A */
    public final View.OnClickListener f10453A = C6365b.m24452c(new View.OnClickListener() { // from class: a3.l
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f106b.m12117s0(view);
        }
    });

    public enum MyStatus {
        Normal,
        Select_Share,
        Select_Save,
        Select_Delete
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$a */
    public class C2104a implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f10483a;

        public C2104a(Permission permission) {
            this.f10483a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(FileListActivity.this, this.f10483a);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() throws Resources.NotFoundException {
            MyStatus unused = FileListActivity.f10452D = MyStatus.Select_Save;
            FileListActivity.this.m12131D0(true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$b */
    public class ViewOnClickListenerC2105b implements View.OnClickListener {
        public ViewOnClickListenerC2105b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            Intent intent = new Intent(FileListActivity.this, (Class<?>) ShareMediaActivity.class);
            intent.putExtra("shareType", ShareType.Internal_File.toString());
            intent.putExtra("share_media_id", FileListActivity.this.f10455d.m109b());
            FileListActivity.this.startActivity(intent);
            FileListActivity.this.m12131D0(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$c */
    public class ViewOnClickListenerC2106c implements View.OnClickListener {
        public ViewOnClickListenerC2106c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            FileListActivity fileListActivity = FileListActivity.this;
            C2925v.m14626v0(fileListActivity, fileListActivity.f10455d.m108a());
            FileListActivity.this.m12131D0(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$d */
    public class ViewOnClickListenerC2107d implements View.OnClickListener {
        public ViewOnClickListenerC2107d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m12148d(DialogInterface dialogInterface, int i9) throws Resources.NotFoundException {
            FileListActivity.this.m12131D0(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m12149e(DialogInterface dialogInterface, int i9) throws Resources.NotFoundException {
            List<C2973l0> listM108a = FileListActivity.this.f10455d.m108a();
            C2113j c2113j = FileListActivity.this.new C2113j(listM108a);
            C6289c.m24102a(listM108a, c2113j, c2113j).m24088p();
            FileListActivity.this.m12131D0(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m12150f(DialogInterface dialogInterface, int i9) throws Resources.NotFoundException {
            FileListActivity.this.m12131D0(false);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FileListActivity fileListActivity;
            int i9;
            FileListActivity fileListActivity2;
            int iM14720q = C2950b0.m14914m().m14720q(FileListActivity.this.f10455d.m109b(), String.valueOf(Globals.m7388i0().m7568k1()));
            boolean z8 = FileListActivity.f10451C.f13704C;
            int i10 = R.string.ok;
            if (!z8 && FileListActivity.f10451C.f13729p > 0 && iM14720q > 0) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(FileListActivity.this);
                builderM16382a.setMessage(R.string.delete_photo_error_of_not_creator);
                builderM16382a.setPositiveButton(FileListActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: a3.q
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i11) throws Resources.NotFoundException {
                        this.f112b.m12148d(dialogInterface, i11);
                    }
                });
                builderM16382a.setCancelable(false);
                AlertDialog alertDialogCreate = builderM16382a.create();
                alertDialogCreate.requestWindowFeature(1);
                alertDialogCreate.show();
                return;
            }
            if (FileListActivity.f10451C.m15750g()) {
                fileListActivity = FileListActivity.this;
                i9 = R.string.delete_file_text;
            } else {
                fileListActivity = FileListActivity.this;
                i9 = R.string.delete_file_reminder;
            }
            String string = fileListActivity.getString(i9);
            if (FileListActivity.f10451C.m15750g()) {
                fileListActivity2 = FileListActivity.this;
            } else {
                fileListActivity2 = FileListActivity.this;
                i10 = R.string.delete_from_this_group;
            }
            String string2 = fileListActivity2.getString(i10);
            AlertDialog.Builder builderM16382a2 = C3123g.m16382a(FileListActivity.this);
            builderM16382a2.setTitle(FileListActivity.this.getString(R.string.delete_files));
            builderM16382a2.setMessage(string);
            builderM16382a2.setNegativeButton(string2, new DialogInterface.OnClickListener() { // from class: a3.r
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i11) throws Resources.NotFoundException {
                    this.f113b.m12149e(dialogInterface, i11);
                }
            });
            builderM16382a2.setPositiveButton(FileListActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: a3.s
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i11) throws Resources.NotFoundException {
                    this.f114b.m12150f(dialogInterface, i11);
                }
            });
            AlertDialog alertDialogShow = builderM16382a2.show();
            if (FileListActivity.f10451C.m15750g()) {
                return;
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 2.0f);
            Button button = alertDialogShow.getButton(-1);
            if (button != null) {
                ((LinearLayout) button.getParent()).setOrientation(1);
                button.setLayoutParams(layoutParams);
                C5179r0.m20247b(button, 1);
            }
            Button button2 = alertDialogShow.getButton(-2);
            if (button2 != null) {
                button2.setLayoutParams(layoutParams);
                button2.setTextColor(FileListActivity.this.getResources().getColor(R.color.you_color_delete_red));
                C5179r0.m20247b(button2, 1);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$e */
    public class C2108e implements AdapterView.OnItemClickListener {
        public C2108e() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) throws Resources.NotFoundException {
            if (FileListActivity.this.f10455d.m113f()) {
                FileListActivity.this.f10455d.m115h(i9, !FileListActivity.this.f10455d.m111d(i9));
                int iM110c = FileListActivity.this.f10455d.m110c();
                FileListActivity.this.m12136I0(iM110c);
                FileListActivity.this.m12137J0(iM110c);
                return;
            }
            C2973l0 c2973l0 = (C2973l0) FileListActivity.this.f10455d.getItem(i9);
            if (c2973l0 == null) {
                return;
            }
            C6468p.m24787m().m24799w(FileListActivity.this, c2973l0.m15148t().f13200d, c2973l0.m15145q(), C1199a.m5277a(c2973l0.m15148t().f13204h, c2973l0.m15148t().f13205i), false, new Date(c2973l0.m15140l() * 1000));
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$f */
    public class C2109f implements C5321e.m {
        public C2109f() {
        }

        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public boolean mo8241A0(C2904l c2904l, Map<String, String> map) {
            String strM14418h = c2904l.m14418h();
            if (strM14418h.equals(Globals.m7388i0().m7587o0())) {
                strM14418h = c2904l.m14388C();
            }
            if (!strM14418h.equals(FileListActivity.f10451C.f13723j)) {
                Log.i(FileListActivity.f10450B, "[eventListener] group jId not match, skip it.");
                return false;
            }
            String str = map.get("eventType");
            if (str.equals("media.media.created")) {
                m12151b(map.get("mediaId"));
                return true;
            }
            if (!str.equals("media.media.deleted")) {
                return true;
            }
            m12152c(map.get("mediaId"));
            return true;
        }

        /* renamed from: b */
        public final void m12151b(String str) {
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.valueOf(str).longValue());
            if (c2973l0M14725v == null || !c2973l0M14725v.m15147s().equals("File")) {
                return;
            }
            FileListActivity.this.m12138Z(c2973l0M14725v);
        }

        /* renamed from: c */
        public final void m12152c(String str) {
            FileListActivity.this.m12129C0(str);
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$g */
    public class AsyncTaskC2110g extends AsyncTask<Void, Void, List<C2973l0>> {

        /* renamed from: a */
        public final /* synthetic */ boolean f10490a;

        public AsyncTaskC2110g(boolean z8) {
            this.f10490a = z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<C2973l0> doInBackground(Void... voidArr) {
            return C2950b0.m14914m().m14721r(FileListActivity.f10451C.f13718e);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<C2973l0> list) {
            FileListActivity.this.m12142e0(list);
            if (this.f10490a) {
                FileListActivity.this.m12140c0(1);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$h */
    public class C2111h implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f10492a;

        public C2111h(Permission permission) {
            this.f10492a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(FileListActivity.this, this.f10492a);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            C5178r.m20241j(FileListActivity.this, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$i */
    public static /* synthetic */ class C2112i {

        /* renamed from: a */
        public static final /* synthetic */ int[] f10494a;

        static {
            int[] iArr = new int[MyStatus.values().length];
            f10494a = iArr;
            try {
                iArr[MyStatus.Select_Share.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10494a[MyStatus.Select_Save.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10494a[MyStatus.Select_Delete.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$j */
    public class C2113j implements C6288b.d<String>, C6288b.h {

        /* renamed from: a */
        public List<C2973l0> f10495a;

        public C2113j(List<C2973l0> list) {
            this.f10495a = list;
        }

        @Override // p201t3.C6288b.d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str) {
            C2925v.m14632y0(FileListActivity.f10451C, FileListActivity.f10451C.f13718e, FileListActivity.f10451C.f13717d, String.valueOf(this.f10495a.size()));
            for (C2973l0 c2973l0 : this.f10495a) {
                C2950b0.m14914m().m14716m(String.valueOf(c2973l0.m15144p()));
                FileListActivity.this.f10455d.remove(c2973l0);
            }
            FileListActivity.this.m12139b0();
        }

        @Override // p201t3.C6288b.h
        public void onError(String str) {
        }
    }

    /* renamed from: j0 */
    public static boolean m12101j0() {
        Group group = f10451C;
        return group != null && C2925v.m14625v(group);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m12103k0() {
        int iM14480D = C2907m0.m14454I().m14480D();
        TextView textView = this.f10463l;
        if (textView == null || iM14480D < 0) {
            return;
        }
        if (iM14480D == 0) {
            textView.setVisibility(8);
        } else {
            textView.setText(iM14480D > 99 ? "N" : String.valueOf(iM14480D));
            this.f10463l.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l0 */
    public /* synthetic */ void m12105l0(C2973l0 c2973l0) {
        this.f10455d.insert(c2973l0, 0);
        m12139b0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m12106m0() {
        if (this.f10460i != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f10464m.getLayoutParams();
            if (this.f10455d.getCount() > 0) {
                this.f10457f.setVisibility(0);
                this.f10460i.setVisibility(8);
                layoutParams.setMargins(0, 0, 0, 0);
            } else {
                this.f10457f.setVisibility(8);
                this.f10460i.setVisibility(0);
                layoutParams.setMargins(0, 0, (int) getResources().getDimension(R.dimen.t10dp), 0);
            }
        }
        if (!m12101j0()) {
            this.f10457f.setVisibility(8);
            this.f10464m.setVisibility(8);
        }
        m12128C();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n0 */
    public /* synthetic */ void m12108n0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o0 */
    public /* synthetic */ void m12110o0(View view) throws Resources.NotFoundException {
        m12131D0(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m12111p0(Dialog dialog, View view) throws Resources.NotFoundException {
        dialog.dismiss();
        f10452D = MyStatus.Select_Share;
        m12131D0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m12113q0(Dialog dialog, View view) {
        dialog.dismiss();
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C2104a(permission), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m12115r0(boolean z8) {
        if (z8) {
            m12130D();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m12117s0(View view) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C2111h(permission), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m12119u0(Dialog dialog, View view) throws Resources.NotFoundException {
        dialog.dismiss();
        f10452D = MyStatus.Select_Delete;
        m12131D0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ void m12123x0(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_file_list_more);
        dialog.findViewById(R.id.shareFiles).setOnClickListener(new View.OnClickListener() { // from class: a3.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws Resources.NotFoundException {
                this.f93b.m12111p0(dialog, view2);
            }
        });
        dialog.findViewById(R.id.saveFiles).setOnClickListener(new View.OnClickListener() { // from class: a3.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f95b.m12113q0(dialog, view2);
            }
        });
        dialog.findViewById(R.id.deleteFiles).setOnClickListener(new View.OnClickListener() { // from class: a3.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws Resources.NotFoundException {
                this.f97b.m12119u0(dialog, view2);
            }
        });
        dialog.findViewById(R.id.DialogCancel).setOnClickListener(new View.OnClickListener() { // from class: a3.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dialog.dismiss();
            }
        });
        CLUtility.m16578q2(this, dialog);
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m12125y0(String str, boolean z8) {
        C6468p.m24793u(this, str, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z0 */
    public /* synthetic */ void m12127z0(String str) {
        this.f10455d.m114g(Long.valueOf(str).longValue());
        m12139b0();
    }

    /* renamed from: C */
    public final void m12128C() {
        TextView textView = (TextView) this.f10466o.findViewById(R.id.title);
        int iMax = Math.max(this.f10466o.findViewById(R.id.LeftBtnLayout).getWidth(), this.f10466o.findViewById(R.id.RightBtnLayout).getWidth());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(iMax, 0, iMax, 0);
        textView.setLayoutParams(layoutParams);
        textView.requestLayout();
    }

    /* renamed from: C0 */
    public final void m12129C0(final String str) {
        runOnUiThread(new Runnable() { // from class: a3.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f110b.m12127z0(str);
            }
        });
    }

    /* renamed from: D */
    public final void m12130D() {
        runOnUiThread(new Runnable() { // from class: a3.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f92b.m12103k0();
            }
        });
    }

    /* renamed from: D0 */
    public final void m12131D0(boolean z8) throws Resources.NotFoundException {
        this.f10455d.m116i(z8);
        m12133F0(z8);
        m12132E0(z8);
        if (this.f10475x.size() <= 0 || z8) {
            return;
        }
        m12142e0(this.f10475x);
    }

    /* renamed from: E0 */
    public final void m12132E0(boolean z8) {
        if (!z8) {
            this.f10459h.setVisibility(8);
            return;
        }
        int i9 = C2112i.f10494a[f10452D.ordinal()];
        if (i9 == 1) {
            this.f10459h.setOnClickListener(this.f10469r);
            this.f10459h.setBackgroundResource(R.drawable.image_selector_common_button);
        } else if (i9 == 2) {
            this.f10459h.setOnClickListener(this.f10470s);
            this.f10459h.setBackgroundResource(R.drawable.image_selector_common_button);
        } else if (i9 != 3) {
            this.f10459h.setOnClickListener(null);
            Log.d(f10450B, "[showOperationButton] unexpected status: " + f10452D);
        } else {
            this.f10459h.setOnClickListener(this.f10471t);
            this.f10459h.setBackgroundResource(R.drawable.image_selector_common_button_red);
        }
        this.f10459h.setVisibility(0);
        m12136I0(0);
    }

    /* renamed from: F0 */
    public final void m12133F0(boolean z8) throws Resources.NotFoundException {
        if (!z8) {
            this.f10458g.setVisibility(8);
        } else {
            this.f10458g.setVisibility(0);
            m12137J0(0);
        }
    }

    /* renamed from: G0 */
    public final void m12134G0() {
        this.f10456e.setOnItemClickListener(null);
        this.f10457f.setOnClickListener(null);
        this.f10459h.setOnClickListener(null);
        C6468p.m24787m().m24800x(this.f10473v);
        C5321e.m20824o().m20832B0(this.f10474w);
    }

    /* renamed from: H0 */
    public final void m12135H0() {
        if (C2907m0.m14454I().m14489N()) {
            m12130D();
        } else {
            C2907m0.m14454I().m14511u(this.f10477z);
        }
    }

    /* renamed from: I0 */
    public final void m12136I0(int i9) {
        String string;
        int i10 = C2112i.f10494a[f10452D.ordinal()];
        if (i10 == 1) {
            string = Globals.m7372O().getString(R.string.forward);
        } else if (i10 == 2) {
            string = Globals.m7372O().getString(R.string.menu_save_to_camera_roll);
        } else if (i10 != 3) {
            Log.d(f10450B, "[updateOperationButtonText] unexpected status: " + f10452D);
            string = "";
        } else {
            string = Globals.m7372O().getString(R.string.menu_delete);
        }
        if (i9 <= 0) {
            this.f10459h.setEnabled(false);
            this.f10459h.setText(string);
            return;
        }
        this.f10459h.setEnabled(true);
        this.f10459h.setText(string + " (" + i9 + ")");
    }

    /* renamed from: J0 */
    public final void m12137J0(int i9) throws Resources.NotFoundException {
        this.f10461j.setText(new SpannableString(String.format(getResources().getQuantityString(R.plurals.file_selected, i9), Integer.valueOf(i9))));
        C5179r0.m20247b(this.f10461j, 1);
    }

    /* renamed from: Z */
    public final void m12138Z(final C2973l0 c2973l0) {
        if (m7364e()) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: a3.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f100b.m12105l0(c2973l0);
            }
        });
    }

    /* renamed from: b0 */
    public final void m12139b0() {
        runOnUiThread(new Runnable() { // from class: a3.m
            @Override // java.lang.Runnable
            public final void run() {
                this.f107b.m12106m0();
            }
        });
    }

    /* renamed from: c0 */
    public final void m12140c0(int i9) {
        this.f10465n.setVisibility(0);
        this.f10467p.m12157b(i9);
        C6288b<C2973l0> c6288b = this.f10454c;
        if (c6288b != null) {
            c6288b.m24086n();
        }
        String str = f10451C.f13718e;
        C2114k c2114k = this.f10467p;
        C6288b<C2973l0> c6288bM24103b = C6289c.m24103b(str, i9, 100, c2114k, c2114k);
        this.f10454c = c6288bM24103b;
        c6288bM24103b.m24088p();
    }

    /* renamed from: d0 */
    public final void m12141d0(boolean z8) {
        this.f10465n.setVisibility(0);
        new AsyncTaskC2110g(z8).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: e0 */
    public final void m12142e0(List<C2973l0> list) {
        this.f10465n.setVisibility(8);
        this.f10475x.clear();
        Log.d(f10450B, "[initList] start. size=" + list.size());
        if (this.f10455d.m113f()) {
            this.f10475x.addAll(list);
            return;
        }
        this.f10455d.clear();
        this.f10455d.addAll(list);
        this.f10455d.notifyDataSetChanged();
        m12139b0();
    }

    /* renamed from: g0 */
    public final void m12143g0() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: a3.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f108b.m12108n0(view);
            }
        });
        this.f10462k.setOnClickListener(new View.OnClickListener() { // from class: a3.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                this.f109b.m12110o0(view);
            }
        });
        this.f10457f.setOnClickListener(this.f10468q);
        this.f10456e.setOnItemClickListener(this.f10472u);
        C6468p.m24787m().m24794k(this.f10473v);
        C5321e.m20824o().m20875k(this.f10474w);
    }

    /* renamed from: i0 */
    public final void m12144i0() {
        ((EmojiconTextView) findViewById(R.id.title)).setText(f10451C.f13717d);
        this.f10457f = findViewById(R.id.more);
        this.f10459h = (Button) findViewById(R.id.delete);
        this.f10460i = findViewById(R.id.FileListEmptyTextView);
        this.f10461j = (TextView) findViewById(R.id.numberOfSelectedItem);
        this.f10462k = (TextView) findViewById(R.id.cancel);
        this.f10458g = findViewById(R.id.toolbar);
        this.f10465n = findViewById(R.id.loading);
        this.f10456e = (ListView) findViewById(R.id.listview);
        C0013a c0013a = new C0013a(this, R.layout.view_item_file_list, new ArrayList());
        this.f10455d = c0013a;
        this.f10456e.setAdapter((ListAdapter) c0013a);
        this.f10463l = (TextView) findViewById(R.id.fileListBadge);
        View viewFindViewById = findViewById(R.id.addFile);
        this.f10464m = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f10453A);
        this.f10466o = (ViewGroup) findViewById(R.id.header);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 1000 && i10 == -1) {
            setResult(-1, intent);
            finish();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f10458g.getVisibility() == 0) {
            this.f10462k.callOnClick();
        } else {
            finish();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_file_list);
        f10451C = (Group) getIntent().getParcelableExtra("Group");
        m12144i0();
        m12143g0();
        m12141d0(true);
        m12135H0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m12134G0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        C2907m0.m14454I().m14494V(this.f10476y);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        C2907m0.m14454I().m14510t(this.f10476y);
    }

    /* renamed from: com.cyberlink.you.activity.filelist.FileListActivity$k */
    public class C2114k implements C6288b.d<List<C2973l0>>, C6288b.h {

        /* renamed from: a */
        public int f10497a;

        public C2114k() {
            this.f10497a = 0;
        }

        @Override // p201t3.C6288b.d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(List<C2973l0> list) {
            if (list.isEmpty()) {
                FileListActivity.this.m12141d0(false);
            } else {
                FileListActivity.this.m12140c0(this.f10497a + 1);
            }
        }

        /* renamed from: b */
        public void m12157b(int i9) {
            this.f10497a = i9;
        }

        @Override // p201t3.C6288b.h
        public void onError(String str) {
        }

        public /* synthetic */ C2114k(FileListActivity fileListActivity, C2104a c2104a) {
            this();
        }
    }
}
