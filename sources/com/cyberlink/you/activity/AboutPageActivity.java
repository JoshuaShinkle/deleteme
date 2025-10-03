package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.cyberlink.meeting.page.MeetingSettingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.AboutPageActivity;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import p116k4.C5154j;
import p116k4.C5166n;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p209u2.AbstractC6381r;
import p209u2.C6366c;
import p209u2.C6369f;
import p209u2.C6385v;
import p227w2.C6518a;

/* loaded from: classes.dex */
public class AboutPageActivity extends BaseActivity {

    /* renamed from: C */
    public static final String f7326C = "AboutPageActivity";

    /* renamed from: c */
    public final C6366c f7329c = new C6366c(10);

    /* renamed from: d */
    public final C6366c f7330d = new C6366c(10, 300);

    /* renamed from: e */
    public final View.OnClickListener f7331e = new View.OnClickListener() { // from class: com.cyberlink.you.activity.a
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9699b.m7699n0(view);
        }
    };

    /* renamed from: f */
    public final View.OnClickListener f7332f = new View.OnClickListener() { // from class: com.cyberlink.you.activity.c
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9765b.m7701o0(view);
        }
    };

    /* renamed from: g */
    public final View.OnClickListener f7333g = new View.OnClickListener() { // from class: com.cyberlink.you.activity.h
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            AboutPageActivity.m7664D0(view);
        }
    };

    /* renamed from: h */
    public final View.OnClickListener f7334h = new View.OnClickListener() { // from class: com.cyberlink.you.activity.i
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10736b.m7672I0(view);
        }
    };

    /* renamed from: i */
    public final View.OnClickListener f7335i = new View.OnClickListener() { // from class: com.cyberlink.you.activity.j
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10767b.m7674J0(view);
        }
    };

    /* renamed from: j */
    public final View.OnClickListener f7336j = new View.OnClickListener() { // from class: com.cyberlink.you.activity.k
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10805b.m7675K0(view);
        }
    };

    /* renamed from: k */
    public final View.OnClickListener f7337k = new View.OnClickListener() { // from class: com.cyberlink.you.activity.m
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10875b.m7677L0(view);
        }
    };

    /* renamed from: l */
    public final View.OnClickListener f7338l = new View.OnClickListener() { // from class: com.cyberlink.you.activity.n
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10910b.m7678M0(view);
        }
    };

    /* renamed from: m */
    public final View.OnClickListener f7339m = new View.OnClickListener() { // from class: com.cyberlink.you.activity.o
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10937b.m7680N0(view);
        }
    };

    /* renamed from: n */
    public final View.OnClickListener f7340n = new View.OnClickListener() { // from class: com.cyberlink.you.activity.p
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11024b.m7682O0(view);
        }
    };

    /* renamed from: o */
    public final View.OnClickListener f7341o = new View.OnClickListener() { // from class: com.cyberlink.you.activity.l
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10837b.m7702p0(view);
        }
    };

    /* renamed from: p */
    public final View.OnClickListener f7342p = new View.OnClickListener() { // from class: com.cyberlink.you.activity.w
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11833b.m7704q0(view);
        }
    };

    /* renamed from: q */
    public final View.OnClickListener f7343q = new View.OnClickListener() { // from class: com.cyberlink.you.activity.x
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12240b.m7706r0(view);
        }
    };

    /* renamed from: r */
    public final View.OnClickListener f7344r = new View.OnClickListener() { // from class: com.cyberlink.you.activity.y
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12264b.m7708s0(view);
        }
    };

    /* renamed from: s */
    public final View.OnClickListener f7345s = new View.OnClickListener() { // from class: com.cyberlink.you.activity.z
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12291b.m7710u0(view);
        }
    };

    /* renamed from: t */
    public final View.OnClickListener f7346t = new View.OnClickListener() { // from class: com.cyberlink.you.activity.a0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9700b.m7713w0(view);
        }
    };

    /* renamed from: u */
    public final View.OnClickListener f7347u = new View.OnClickListener() { // from class: com.cyberlink.you.activity.b0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9734b.m7714x0(view);
        }
    };

    /* renamed from: v */
    public final View.OnClickListener f7348v = new View.OnClickListener() { // from class: com.cyberlink.you.activity.c0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9766b.m7716y0(view);
        }
    };

    /* renamed from: w */
    public final View.OnClickListener f7349w = new View.OnClickListener() { // from class: com.cyberlink.you.activity.d0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10357b.m7718z0(view);
        }
    };

    /* renamed from: x */
    public final View.OnClickListener f7350x = new View.OnClickListener() { // from class: com.cyberlink.you.activity.b
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9733b.m7662C0(view);
        }
    };

    /* renamed from: y */
    public final View.OnClickListener f7351y = new View.OnClickListener() { // from class: com.cyberlink.you.activity.d
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10356b.m7666E0(view);
        }
    };

    /* renamed from: z */
    public final View.OnClickListener f7352z = new View.OnClickListener() { // from class: com.cyberlink.you.activity.e
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            AboutPageActivity.m7667F0(view);
        }
    };

    /* renamed from: A */
    public final View.OnClickListener f7327A = new View.OnClickListener() { // from class: com.cyberlink.you.activity.f
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10418b.m7668G0(view);
        }
    };

    /* renamed from: B */
    public final View.OnClickListener f7328B = new View.OnClickListener() { // from class: com.cyberlink.you.activity.g
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10677b.m7670H0(view);
        }
    };

    /* renamed from: com.cyberlink.you.activity.AboutPageActivity$a */
    public class C1409a implements InterfaceC5288c {
        public C1409a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m7735d(DialogInterface dialogInterface, int i9) throws Throwable {
            ULogUtility.m16680p(AboutPageActivity.f7326C, "---start import DB---");
            String str = CLUtility.m16561m1(C6518a.f21930b) + "pht.sqlite";
            String str2 = "//data//data//" + Globals.m7387h1() + "//databases//pht.sqlite";
            if (C6369f.m24459a(new File(str), new File(str2))) {
                MessageObj messageObjM15174m = C2950b0.m14916o().m15174m();
                if (messageObjM15174m != null) {
                    Globals.m7388i0().m7509X3(messageObjM15174m.m14788z().getTime());
                    Globals.m7388i0().m7461N2(messageObjM15174m.m14788z().getTime(), true);
                }
                AlertDialog.Builder builderM16382a = C3123g.m16382a(AboutPageActivity.this);
                builderM16382a.setTitle("ImportDB complete");
                builderM16382a.setMessage("Please restart U now to reopen DB !");
                builderM16382a.setPositiveButton(AboutPageActivity.this.getString(R.string.ok), (DialogInterface.OnClickListener) null);
                builderM16382a.create().show();
                File file = new File(str + "-shm");
                File file2 = new File(str2 + "-shm");
                if (file2.exists()) {
                    file2.delete();
                }
                if (file.exists()) {
                    C6369f.m24459a(file, file2);
                }
                File file3 = new File(str + "-wal");
                File file4 = new File(str2 + "-wal");
                if (file4.exists()) {
                    file4.delete();
                }
                if (file3.exists()) {
                    C6369f.m24459a(file3, file4);
                }
            } else {
                C5187v0.m20268d("Import DB fail");
            }
            ULogUtility.m16680p(AboutPageActivity.f7326C, "---End import DB---");
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            AlertDialog.Builder builderM16382a = C3123g.m16382a(AboutPageActivity.this);
            builderM16382a.setTitle("ImportDB");
            builderM16382a.setMessage("Please be sure on airplane mode (飛航模式) when import DB, or it may cause DB crash.\n\nDo you want to start import DB ?");
            builderM16382a.setNegativeButton(AboutPageActivity.this.getString(R.string.cancel), (DialogInterface.OnClickListener) null);
            builderM16382a.setPositiveButton(AboutPageActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.e0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) throws Throwable {
                    this.f10385b.m7735d(dialogInterface, i9);
                }
            });
            builderM16382a.create().show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.AboutPageActivity$b */
    public class C1410b implements InterfaceC5288c {
        public C1410b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m7739f() {
            CLUtility.m16575q(AboutPageActivity.this.getApplicationContext());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m7740g(DialogC3133q dialogC3133q) {
            dialogC3133q.dismiss();
            File file = new File(CLUtility.m16499W());
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.SUBJECT", "U DB").putExtra("android.intent.extra.TEXT", "Collected U DB in a zip file.").setType("application/zip").putExtra("android.intent.extra.STREAM", C6369f.m24471m(file));
            AboutPageActivity.this.startActivity(Intent.createChooser(intent, "Send U DB via..."));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m7741h(final DialogC3133q dialogC3133q) {
            C2950b0.m14902a(new Runnable() { // from class: com.cyberlink.you.activity.g0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10678b.m7739f();
                }
            });
            C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.activity.h0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10704b.m7740g(dialogC3133q);
                }
            });
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(AboutPageActivity.this).m16411b();
            C6385v.m24525c(new Runnable() { // from class: com.cyberlink.you.activity.f0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10419b.m7741h(dialogC3133qM16411b);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.AboutPageActivity$c */
    public class C1411c implements InterfaceC5288c {
        public C1411c() {
        }

        /* renamed from: d */
        public static /* synthetic */ void m7743d(DialogC3133q dialogC3133q) throws Throwable {
            String str = "//data//data//" + Globals.m7387h1() + "//shared_prefs//";
            String strM16561m1 = CLUtility.m16561m1(C6518a.f21930b);
            try {
                C6369f.m24462d(new File(str), new File(strM16561m1));
                C5187v0.m20271g(str + " to " + strM16561m1);
            } catch (Exception e9) {
                C5154j.m20076j(e9);
                C5187v0.m20271g(str + " to " + strM16561m1);
            }
            dialogC3133q.dismiss();
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(AboutPageActivity.this).m16411b();
            C6385v.f21553a.execute(new Runnable() { // from class: com.cyberlink.you.activity.i0
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    AboutPageActivity.C1411c.m7743d(dialogC3133qM16411b);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.AboutPageActivity$d */
    public class C1412d extends AbstractC6381r<String, Exception> {
        public C1412d(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(String str) {
            C5187v0.m20271g("Dump logcat in " + str);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Exception exc) {
            C5187v0.m20271g("Dump logcat failed: " + exc.getMessage());
        }
    }

    /* renamed from: com.cyberlink.you.activity.AboutPageActivity$e */
    public class C1413e implements InterfaceC5288c {
        public C1413e() {
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() throws IOException {
            File file = new File(CLUtility.m16561m1(C6518a.f21930b) + "debug");
            if (file.exists()) {
                file.delete();
                C5187v0.m20268d("Disable Advance Debug Mode");
            } else {
                try {
                    file.createNewFile();
                    C5187v0.m20268d("Enable Advance Debug Mode");
                } catch (Exception e9) {
                    Log.e(AboutPageActivity.f7326C, "Cannot create file", e9);
                    C5187v0.m20268d("Disable Advance Debug Mode: " + e9.getMessage());
                }
            }
            Globals.m7388i0().m7637w4();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C0 */
    public /* synthetic */ void m7662C0(View view) {
        ULogUtility.m16673i(new C1412d(new Handler(Looper.getMainLooper())));
    }

    /* renamed from: D0 */
    public static /* synthetic */ void m7664D0(View view) {
        throw new NullPointerException("Fabric testing.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E0 */
    public /* synthetic */ void m7666E0(View view) {
        try {
            boolean zM7539e2 = Globals.m7388i0().m7539e2();
            boolean z8 = true;
            m7727X0(!zM7539e2);
            Globals globalsM7388i0 = Globals.m7388i0();
            if (zM7539e2) {
                z8 = false;
            }
            globalsM7388i0.m7554h4(z8);
        } catch (IllegalStateException e9) {
            C5187v0.m20268d(e9.getMessage());
            if (Globals.m7388i0().m7414D1()) {
                C5187v0.m20268d("It may cause by LogAgentHelper isDebuggable return true");
            }
        }
    }

    /* renamed from: F0 */
    public static /* synthetic */ void m7667F0(View view) {
        try {
            C2950b0.m14927z().m14965e();
            C5187v0.m20268d("UrlPreview table delete data success");
        } catch (IllegalStateException e9) {
            C5187v0.m20268d(e9.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G0 */
    public /* synthetic */ void m7668G0(View view) {
        try {
            boolean zM7434H1 = Globals.m7388i0().m7434H1();
            boolean z8 = true;
            m7722S0(!zM7434H1);
            Globals globalsM7388i0 = Globals.m7388i0();
            if (zM7434H1) {
                z8 = false;
            }
            globalsM7388i0.m7457M2(z8);
        } catch (IllegalStateException e9) {
            C5187v0.m20268d(e9.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H0 */
    public /* synthetic */ void m7670H0(View view) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.gcm.GcmDiagnostics"));
            startActivity(intent);
        } catch (Exception unused) {
            C5187v0.m20268d("Open FCM diagnostics failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I0 */
    public /* synthetic */ void m7672I0(View view) {
        boolean zM7451L1 = Globals.m7388i0().m7451L1();
        Globals.m7388i0().m7500V2(!zM7451L1);
        m7725V0(!zM7451L1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J0 */
    public /* synthetic */ void m7674J0(View view) {
        boolean zM7582n = Globals.m7388i0().m7582n();
        Globals.m7388i0().m7508X2(!zM7582n);
        m7721R0(!zM7582n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K0 */
    public /* synthetic */ void m7675K0(View view) {
        boolean zM7586o = Globals.m7388i0().m7586o();
        Globals.m7388i0().m7513Y2(!zM7586o);
        m7724U0(!zM7586o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L0 */
    public /* synthetic */ void m7677L0(View view) {
        m7719P0();
        Toast.makeText(getBaseContext(), "Restore Achived Group Done", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M0 */
    public /* synthetic */ void m7678M0(View view) {
        startActivityForResult(new Intent(this, (Class<?>) GCMFilterSettingActivity.class), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m7680N0(View view) {
        boolean zBooleanValue = Globals.m7388i0().m7499V1().booleanValue();
        Globals.m7388i0().m7518Z3(!zBooleanValue);
        m7726W0(!zBooleanValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O0 */
    public /* synthetic */ void m7682O0(View view) {
        startActivity(new Intent(this, (Class<?>) MeetingSettingActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i0 */
    public /* synthetic */ boolean m7690i0(View view) {
        C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new C1413e(), this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j0 */
    public /* synthetic */ void m7692j0(View view) {
        if (view.getId() == R.id.AboutPageCloseBtn) {
            setResult(-1);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m7694k0(View view) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(1);
            File file = new File(C6369f.m24468j(getApplicationContext()), "LICENSE.txt");
            if (file.exists()) {
                intent.setDataAndType(C6369f.m24471m(file), "text/plain");
                startActivity(intent);
            }
        } catch (ActivityNotFoundException e9) {
            C5187v0.m20271g("No app to open it: " + e9.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l0 */
    public /* synthetic */ void m7696l0(View view) {
        startActivity(new Intent(this, (Class<?>) SuUsersListActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m7697m0(View view) {
        if (Globals.m7388i0().m7534d2() && this.f7330d.m24458a()) {
            findViewById(R.id.AboutSuperviseTool).setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n0 */
    public /* synthetic */ void m7699n0(View view) {
        if (this.f7329c.m24458a()) {
            DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16413d(1500L).m16411b();
            C5187v0.m20268d("Enable Debug Mode");
            m7720Q0();
            Globals.m7388i0().m7648z();
            Handler handlerM7363d = m7363d();
            Objects.requireNonNull(dialogC3133qM16411b);
            handlerM7363d.postDelayed(new RunnableC2569v(dialogC3133qM16411b), 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o0 */
    public /* synthetic */ void m7701o0(View view) {
        startActivity(new Intent(this, (Class<?>) SignOutActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m7702p0(View view) {
        Intent intent = new Intent(this, (Class<?>) LogBrowserActivity.class);
        intent.putExtra("type", ULogUtility.LogFileType.Log.toString());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m7704q0(View view) {
        startActivity(new Intent(this, (Class<?>) ScrollTextViewActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m7706r0(View view) {
        Intent intent = new Intent(this, (Class<?>) LogBrowserActivity.class);
        intent.putExtra("type", ULogUtility.LogFileType.Logcat.toString());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m7708s0(View view) {
        Intent intent = new Intent(this, (Class<?>) LogBrowserActivity.class);
        intent.putExtra("type", ULogUtility.LogFileType.CLRTC.toString());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m7710u0(View view) {
        C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new C1409a(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w0 */
    public /* synthetic */ void m7713w0(View view) {
        C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new C1410b(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ void m7714x0(View view) {
        C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new C1411c(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m7716y0(View view) {
        startActivity(new Intent(this, (Class<?>) SelectXmppServerActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z0 */
    public /* synthetic */ void m7718z0(View view) {
        C5166n.m20137h(this);
    }

    /* renamed from: P0 */
    public final void m7719P0() {
        Iterator<Group> it = C2950b0.m14912k().m15073j().iterator();
        while (it.hasNext()) {
            Group groupM15080q = C2950b0.m14912k().m15080q(it.next().f13723j);
            if (groupM15080q != null && groupM15080q.f13734u != 0) {
                C2907m0.m14454I().m14498Z(groupM15080q.f13723j, groupM15080q.f13731r ? 0 : groupM15080q.f13734u);
            }
        }
        C2950b0.m14912k().m15088y();
    }

    /* renamed from: Q0 */
    public final void m7720Q0() {
        findViewById(R.id.about_adv_setting).setVisibility(0);
    }

    /* renamed from: R0 */
    public final void m7721R0(boolean z8) {
        ((TextView) findViewById(R.id.AchiveGroupInChatsOptionTextView)).setText(z8 ? "On" : "Off");
    }

    /* renamed from: S0 */
    public final void m7722S0(boolean z8) {
        ((TextView) findViewById(R.id.AboutFaceIdLogTextView)).setText(z8 ? "On" : "Off");
    }

    /* renamed from: T0 */
    public final void m7723T0(boolean z8) {
        ((TextView) findViewById(R.id.GCMFilterOptionTextView)).setText(z8 ? "On" : "Off");
    }

    /* renamed from: U0 */
    public final void m7724U0(boolean z8) {
        ((TextView) findViewById(R.id.AchiveGroupInFriendsOptionTextView)).setText(z8 ? "On" : "Off");
    }

    /* renamed from: V0 */
    public final void m7725V0(boolean z8) {
        ((TextView) findViewById(R.id.AboutHeartbeatTextView)).setText(z8 ? "On" : "Off");
    }

    /* renamed from: W0 */
    public final void m7726W0(boolean z8) {
        ((TextView) findViewById(R.id.HideSearchChatTextView)).setText(z8 ? "On" : "Off");
    }

    /* renamed from: X0 */
    public final void m7727X0(boolean z8) {
        ((TextView) findViewById(R.id.AboutUNOLogTextView)).setText(z8 ? "On" : "Off");
    }

    /* renamed from: Y */
    public final void m7728Y() {
        if (Globals.m7388i0().m7534d2()) {
            m7720Q0();
        }
        m7733g0();
        m7729b0();
        m7731d0();
        m7732e0();
    }

    /* renamed from: b0 */
    public final void m7729b0() {
        ((TextView) findViewById(R.id.AboutPageVersionTextView)).setText(Globals.m7391n1());
        TextView textView = (TextView) findViewById(R.id.AboutRevisionTextView);
        textView.setText(Globals.m7368J() + "(" + System.getProperty("os.arch") + ")");
        textView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.cyberlink.you.activity.t
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f11384b.m7690i0(view);
            }
        });
    }

    /* renamed from: c0 */
    public final void m7730c0() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.cyberlink.you.activity.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11418b.m7692j0(view);
            }
        };
        findViewById(R.id.AboutPageBackBtn).setOnClickListener(onClickListener);
        findViewById(R.id.AboutPageCloseBtn).setOnClickListener(onClickListener);
    }

    /* renamed from: d0 */
    public final void m7731d0() {
        findViewById(R.id.AboutPageLegalNoticesArea).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11066b.m7694k0(view);
            }
        });
    }

    /* renamed from: e0 */
    public final void m7732e0() {
        View viewFindViewById = findViewById(R.id.AboutSuperviseTool);
        viewFindViewById.setVisibility(8);
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11158b.m7696l0(view);
            }
        });
    }

    /* renamed from: g0 */
    public final void m7733g0() {
        findViewById(R.id.aboutTitle).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11128b.m7697m0(view);
            }
        });
        m7730c0();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 2) {
            m7723T0(Globals.m7388i0().m7443J1());
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_about_page);
        findViewById(android.R.id.content).setOnClickListener(this.f7331e);
        ((TextView) findViewById(R.id.AboutQueryMessageVersionTextView)).setText(Globals.m7388i0().m7483S0());
        ((TextView) findViewById(R.id.AboutUserIdTextView)).setText(String.valueOf(Globals.m7388i0().m7568k1()));
        m7727X0(Globals.m7388i0().m7539e2());
        m7722S0(Globals.m7388i0().m7434H1());
        m7725V0(Globals.m7388i0().m7451L1());
        m7721R0(Globals.m7388i0().m7582n());
        m7724U0(Globals.m7388i0().m7586o());
        m7723T0(Globals.m7388i0().m7443J1());
        m7726W0(Globals.m7388i0().m7499V1().booleanValue());
        findViewById(R.id.AboutDumpLogcat).setOnClickListener(this.f7350x);
        findViewById(R.id.AboutLog).setOnClickListener(this.f7341o);
        findViewById(R.id.AboutLogcat).setOnClickListener(this.f7343q);
        findViewById(R.id.AboutCLRTC).setOnClickListener(this.f7344r);
        findViewById(R.id.AboutPingLog).setOnClickListener(this.f7342p);
        findViewById(R.id.AboutImportDB).setOnClickListener(this.f7345s);
        findViewById(R.id.AboutExportDB).setOnClickListener(this.f7346t);
        findViewById(R.id.AboutExportSharedPrefs).setOnClickListener(this.f7347u);
        findViewById(R.id.AboutUNOLog).setOnClickListener(this.f7351y);
        findViewById(R.id.AboutUrlPreviewTable).setOnClickListener(this.f7352z);
        findViewById(R.id.AboutFaceIdLog).setOnClickListener(this.f7327A);
        findViewById(R.id.AboutHeartbeat).setOnClickListener(this.f7334h);
        findViewById(R.id.AboutAchiveGroupInChatsOption).setOnClickListener(this.f7335i);
        findViewById(R.id.AboutAchiveGroupInFriendsOption).setOnClickListener(this.f7336j);
        findViewById(R.id.AboutRestoreAchiveGroupOption).setOnClickListener(this.f7337k);
        findViewById(R.id.AboutGCMFilterOption).setOnClickListener(this.f7338l);
        findViewById(R.id.AboutSearchChatOption).setOnClickListener(this.f7339m);
        findViewById(R.id.AboutPinkNoiseChatOption).setOnClickListener(this.f7340n);
        findViewById(R.id.ThrowCrashBtn).setOnClickListener(this.f7333g);
        findViewById(R.id.LogOutBtn).setOnClickListener(this.f7332f);
        findViewById(R.id.AboutDebugModeSelectXmppServer).setOnClickListener(this.f7348v);
        findViewById(R.id.AboutDebugModeSendData).setOnClickListener(this.f7349w);
        findViewById(R.id.AboutFCMDiagnostics).setOnClickListener(this.f7328B);
        m7728Y();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C5166n.m20136g();
    }
}
