package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.MessageGeneratorFragment;
import com.cyberlink.you.activity.chatdialog.C2027b;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.friend.FriendSelectedActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.PhotoImportActivity;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.transcode.C3114a;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import p056e4.C4755b;
import p116k4.C5154j;
import p116k4.C5178r;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p209u2.C6365b;

/* renamed from: com.cyberlink.you.activity.o0 */
/* loaded from: classes.dex */
public class C2337o0 extends Fragment {

    /* renamed from: G */
    public static final String f10938G = "o0";

    /* renamed from: H */
    public static Uri f10939H;

    /* renamed from: I */
    public static String f10940I;

    /* renamed from: b */
    public View f10947b;

    /* renamed from: c */
    public View f10948c;

    /* renamed from: d */
    public View f10949d;

    /* renamed from: e */
    public View f10950e;

    /* renamed from: f */
    public View f10951f;

    /* renamed from: g */
    public View f10952g;

    /* renamed from: h */
    public View f10953h;

    /* renamed from: i */
    public View f10954i;

    /* renamed from: j */
    public View f10955j;

    /* renamed from: k */
    public View f10956k;

    /* renamed from: l */
    public View f10957l;

    /* renamed from: m */
    public View f10958m;

    /* renamed from: n */
    public l f10959n;

    /* renamed from: o */
    public C3114a f10960o;

    /* renamed from: p */
    public Group f10961p;

    /* renamed from: q */
    public boolean f10962q;

    /* renamed from: u */
    public k f10966u;

    /* renamed from: r */
    public boolean f10963r = false;

    /* renamed from: s */
    public boolean f10964s = false;

    /* renamed from: t */
    public boolean f10965t = false;

    /* renamed from: v */
    public View.OnClickListener f10967v = C6365b.m24452c(new a());

    /* renamed from: w */
    public View.OnClickListener f10968w = C6365b.m24452c(new c());

    /* renamed from: x */
    public View.OnClickListener f10969x = C6365b.m24452c(new d());

    /* renamed from: y */
    public View.OnClickListener f10970y = C6365b.m24452c(new View.OnClickListener() { // from class: com.cyberlink.you.activity.j0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10768b.m12440B(view);
        }
    });

    /* renamed from: z */
    public View.OnClickListener f10971z = C6365b.m24452c(new e());

    /* renamed from: A */
    public View.OnClickListener f10941A = C6365b.m24452c(new View.OnClickListener() { // from class: com.cyberlink.you.activity.k0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10806b.m12441C(view);
        }
    });

    /* renamed from: B */
    public final View.OnClickListener f10942B = C6365b.m24452c(new View.OnClickListener() { // from class: com.cyberlink.you.activity.l0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10838b.m12442D(view);
        }
    });

    /* renamed from: C */
    public View.OnClickListener f10943C = C6365b.m24452c(new h());

    /* renamed from: D */
    public View.OnClickListener f10944D = C6365b.m24452c(new i());

    /* renamed from: E */
    public View.OnClickListener f10945E = C6365b.m24452c(new j());

    /* renamed from: F */
    public final View.OnClickListener f10946F = C6365b.m24452c(new View.OnClickListener() { // from class: com.cyberlink.you.activity.m0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10876b.m12443E(view);
        }
    });

    /* renamed from: com.cyberlink.you.activity.o0$a */
    public class a implements View.OnClickListener {

        /* renamed from: b */
        public InterfaceC5288c f10972b = new C6849a();

        /* renamed from: com.cyberlink.you.activity.o0$a$a, reason: collision with other inner class name */
        public class C6849a implements InterfaceC5288c {
            public C6849a() {
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (!z8 || C2337o0.this.getActivity() == null) {
                    C5187v0.m20267c(R.string.permission_denied);
                } else {
                    C5183t0.m20262b(C2337o0.this.getActivity(), Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                a.this.m12472c();
            }
        }

        public a() {
        }

        /* renamed from: b */
        public final void m12471b() {
            C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, this.f10972b, C2337o0.this.getActivity());
        }

        /* renamed from: c */
        public final void m12472c() {
            Intent intent = new Intent(C2337o0.this.getActivity(), (Class<?>) PhotoImportActivity.class);
            intent.putExtra("hideVoice4ImportPhoto", C2337o0.this.f10963r);
            intent.putExtra("singleSelect4ImportPhoto", C2337o0.this.f10964s);
            intent.putExtra("enableMeetingShareDoc", C2337o0.this.f10965t);
            if (C2337o0.this.f10961p != null && C2337o0.this.f10961p.f13711J) {
                intent.putExtra("isEnableE2EE", true);
            }
            if (C2337o0.this.isAdded()) {
                if (C2337o0.this.getParentFragment() != null) {
                    C2337o0.this.getParentFragment().startActivityForResult(intent, 0);
                } else {
                    UploadUtils.m16965l(C2337o0.f10938G, "[startPhotoLib] getParentFragment is null");
                    C5154j.m20075i("[ChatPlusFragment] startPhotoLib getParentFragment is null");
                }
            }
            C2337o0.this.m12463A();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2337o0.this.getActivity() == null) {
                return;
            }
            m12471b();
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$b */
    public class b implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f10975a;

        public b(Permission permission) {
            this.f10975a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (!z8 || C2337o0.this.getActivity() == null) {
                C5187v0.m20267c(R.string.permission_denied);
            } else {
                C5183t0.m20262b(C2337o0.this.getActivity(), this.f10975a);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            if (C2337o0.this.getParentFragment() != null) {
                C5178r.m20242k(C2337o0.this.getParentFragment(), 8);
            } else {
                UploadUtils.m16965l(C2337o0.f10938G, "[startFilePick] getParentFragment is null");
                C5154j.m20075i("[ChatPlusFragment] startFilePick getParentFragment is null");
            }
            C2337o0.this.m12463A();
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$c */
    public class c implements View.OnClickListener {

        /* renamed from: b */
        public InterfaceC5288c f10977b = new a();

        /* renamed from: com.cyberlink.you.activity.o0$c$a */
        public class a implements InterfaceC5288c {
            public a() {
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (!z8 || C2337o0.this.getActivity() == null) {
                    C5187v0.m20267c(R.string.permission_denied);
                } else if (Build.VERSION.SDK_INT >= 33) {
                    C5183t0.m20261a(C2337o0.this.getActivity(), R.string.permission_ask_photo_video_camera);
                } else {
                    C5183t0.m20261a(C2337o0.this.getActivity(), R.string.permission_ask_storage_camera);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                c.this.m12475c();
            }
        }

        public c() {
        }

        /* renamed from: b */
        public final void m12474b() {
            C5287b.m20584g(Build.VERSION.SDK_INT >= 33 ? new Permission[]{Permission.IMAGE, Permission.CAMERA} : new Permission[]{Permission.STORAGE, Permission.CAMERA}, this.f10977b, C2337o0.this.getActivity());
        }

        /* renamed from: c */
        public final void m12475c() {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            if (intent.resolveActivity(C2337o0.this.getActivity().getPackageManager()) == null) {
                C5187v0.m20267c(R.string.error_no_camera_app);
                return;
            }
            Uri unused = C2337o0.f10939H = CLUtility.m16468N0();
            if (C2337o0.f10939H == null) {
                return;
            }
            intent.putExtra("output", C2337o0.f10939H);
            if (C2337o0.this.isAdded()) {
                if (C2337o0.this.getParentFragment() != null) {
                    C2337o0.this.getParentFragment().startActivityForResult(intent, 1);
                } else {
                    UploadUtils.m16965l(C2337o0.f10938G, "[startCameraIntent] getParentFragment is null");
                    C5154j.m20075i("[ChatPlusFragment] startCameraIntent getParentFragment is null");
                }
            }
            Globals.m7388i0().m7526b3(true);
            C2337o0.this.m12463A();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2337o0.this.getActivity() == null) {
                return;
            }
            m12474b();
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$d */
    public class d implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.o0$d$a */
        public class a implements InterfaceC5288c {
            public a() {
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(C2337o0.this.getActivity(), Permission.LOCATION);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                d.this.m12477b();
            }
        }

        public d() {
        }

        /* renamed from: b */
        public final void m12477b() {
            if (((ChatDialogActivity) C2337o0.this.getActivity()) != null) {
                C2337o0.this.getParentFragment().startActivityForResult(new Intent(C2337o0.this.getActivity(), (Class<?>) SelectLocationActivity.class), 7);
                C2337o0.this.m12463A();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2337o0.this.getActivity() == null) {
                return;
            }
            C5287b.m20583f(Permission.LOCATION, new a(), C2337o0.this.getActivity());
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$e */
    public class e implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.o0$e$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ Runnable f10983a;

            /* renamed from: b */
            public final /* synthetic */ Permission f10984b;

            public a(Runnable runnable, Permission permission) {
                this.f10983a = runnable;
                this.f10984b = permission;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (!z8) {
                    C5187v0.m20267c(R.string.permission_denied);
                } else {
                    Permission permission = this.f10984b;
                    C5183t0.m20261a(C2337o0.this.getActivity(), permission == Permission.CAMERA ? R.string.permission_ask_camera : permission == Permission.MICROPHONE ? R.string.permission_ask_microphone : permission == Permission.VIDEO ? R.string.permission_ask_photo_video : R.string.permission_ask_storage);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                this.f10983a.run();
            }
        }

        public e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m12481e() {
            m12483d(Build.VERSION.SDK_INT >= 33 ? Permission.VIDEO : Permission.STORAGE, new Runnable() { // from class: com.cyberlink.you.activity.r0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11129b.m12484g();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m12482f() {
            m12483d(Permission.MICROPHONE, new Runnable() { // from class: com.cyberlink.you.activity.q0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11067b.m12481e();
                }
            });
        }

        /* renamed from: d */
        public final void m12483d(Permission permission, Runnable runnable) {
            C5287b.m20583f(permission, new a(runnable, permission), C2337o0.this.getActivity());
        }

        /* renamed from: g */
        public final void m12484g() {
            String unused = C2337o0.f10940I = CLUtility.m16581r1();
            Intent intent = new Intent(C2337o0.this.getActivity(), (Class<?>) VideoRecordCamera2Activity.class);
            intent.putExtra("videoPath", C2337o0.f10940I);
            if (C2337o0.this.getParentFragment() != null) {
                C2337o0.this.getParentFragment().startActivityForResult(intent, 5);
            } else {
                UploadUtils.m16965l(C2337o0.f10938G, "[startVideoRecordActivity] getParentFragment is null");
                C5154j.m20075i("[ChatPlusFragment] startVideoRecordActivity getParentFragment is null");
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2337o0.this.getActivity() == null) {
                return;
            }
            m12483d(Permission.CAMERA, new Runnable() { // from class: com.cyberlink.you.activity.p0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11025b.m12482f();
                }
            });
            C2337o0.this.m12463A();
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$f */
    public class f implements InterfaceC5288c {
        public f() {
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(C2337o0.this.getActivity(), Permission.MICROPHONE);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            C2337o0.this.m12467J();
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$g */
    public class g implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f10987a;

        public g(Permission permission) {
            this.f10987a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (!z8 || C2337o0.this.getActivity() == null) {
                C5187v0.m20267c(R.string.permission_denied);
            } else {
                C5183t0.m20262b(C2337o0.this.getActivity(), this.f10987a);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            if (C2337o0.this.getParentFragment() != null) {
                C5178r.m20245n(C2337o0.this.getParentFragment(), 6);
            } else {
                UploadUtils.m16965l(C2337o0.f10938G, "[startVideoPick] getParentFragment is null");
                C5154j.m20075i("[ChatPlusFragment] startVideoPick getParentFragment is null");
            }
            C2337o0.this.m12463A();
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$h */
    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2337o0.this.getActivity() == null) {
                return;
            }
            C2337o0.this.f10966u = new k(C2337o0.this);
            C2337o0.this.f10966u.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$i */
    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2337o0.this.getActivity() == null) {
                return;
            }
            C2925v.m14571O0(C2337o0.this.getActivity(), C2337o0.this.f10961p, true, MimeTypes.BASE_TYPE_AUDIO, "chatPlus audio meeting button");
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$j */
    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2337o0.this.getActivity() == null) {
                return;
            }
            C2925v.m14571O0(C2337o0.this.getActivity(), C2337o0.this.f10961p, true, MimeTypes.BASE_TYPE_VIDEO, "chatPlus video meeting button");
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$k */
    public static class k extends AsyncTask<Void, Void, List<Friend>> {

        /* renamed from: a */
        public final WeakReference<C2337o0> f10992a;

        /* renamed from: b */
        public DialogC3133q f10993b = null;

        public k(C2337o0 c2337o0) {
            this.f10992a = new WeakReference<>(c2337o0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m12486e(DialogC3133q dialogC3133q) {
            cancel(true);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public List<Friend> doInBackground(Void... voidArr) {
            if (m12489d()) {
                return C2950b0.m14899A().m15025q();
            }
            cancel(true);
            return null;
        }

        /* renamed from: c */
        public final Activity m12488c() {
            C2337o0 c2337o0 = this.f10992a.get();
            if (c2337o0 == null) {
                return null;
            }
            return c2337o0.getActivity();
        }

        /* renamed from: d */
        public final boolean m12489d() {
            return this.f10992a.get() != null && this.f10992a.get().isAdded();
        }

        @Override // android.os.AsyncTask
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Friend> list) {
            ArrayList<? extends Parcelable> arrayList;
            Activity activityM12488c = m12488c();
            C2337o0 c2337o0 = this.f10992a.get();
            if (activityM12488c == null || c2337o0 == null || !m12489d() || list == null) {
                return;
            }
            try {
                arrayList = (ArrayList) list;
            } catch (Exception unused) {
                arrayList = new ArrayList<>(list);
            }
            Intent intent = new Intent(activityM12488c, (Class<?>) FriendSelectedActivity.class);
            intent.putExtra("listSelectMode", 1);
            intent.putExtra("buttonDisplayingString", activityM12488c.getString(R.string.done_btn));
            intent.putParcelableArrayListExtra("originalMembers", arrayList);
            if (c2337o0.getParentFragment() != null) {
                c2337o0.getParentFragment().startActivityForResult(intent, 4);
            } else {
                activityM12488c.startActivityForResult(intent, 4);
            }
            c2337o0.m12463A();
            DialogC3133q dialogC3133q = this.f10993b;
            if (dialogC3133q != null) {
                dialogC3133q.dismiss();
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            Activity activityM12488c = m12488c();
            if (activityM12488c == null || !m12489d()) {
                cancel(true);
            } else {
                this.f10993b = new DialogC3133q.b(activityM12488c).m16412c(true).m16414e(new DialogC3133q.c() { // from class: com.cyberlink.you.activity.s0
                    @Override // com.cyberlink.you.p036ui.DialogC3133q.c
                    /* renamed from: a */
                    public final void mo10190a(DialogC3133q dialogC3133q) {
                        this.f11159a.m12486e(dialogC3133q);
                    }
                }).m16411b();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.o0$l */
    public interface l {
        /* renamed from: a */
        void mo12491a(String str, Map<String, Object> map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m12440B(View view) {
        ChatDialogActivity chatDialogActivity;
        if (getActivity() == null || (chatDialogActivity = (ChatDialogActivity) getActivity()) == null) {
            return;
        }
        Intent intent = new Intent(getActivity(), (Class<?>) ScheduleSendActivity.class);
        intent.putExtra("Group", chatDialogActivity.m11597s7());
        intent.putExtra("chatText", chatDialogActivity.m11594r7());
        getParentFragment().startActivityForResult(intent, 2);
        m12463A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m12441C(View view) {
        C5287b.m20583f(Permission.MICROPHONE, new f(), getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m12442D(View view) {
        if (getActivity() == null) {
            return;
        }
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.VIDEO : Permission.STORAGE;
        C5287b.m20583f(permission, new g(permission), getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m12443E(View view) {
        if (getActivity() == null) {
            return;
        }
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new b(permission), getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F */
    public /* synthetic */ void m12444F(Map map) {
        m12464G("sendVideo", map);
    }

    /* renamed from: A */
    public final void m12463A() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity instanceof ChatDialogActivity) {
                C2027b c2027bM11611w7 = ((ChatDialogActivity) activity).m11611w7();
                Boolean bool = Boolean.FALSE;
                c2027bM11611w7.m12075a0(bool, bool, bool);
            } else if (activity instanceof TopicContentActivity) {
                C2027b c2027bM10427t3 = ((TopicContentActivity) activity).m10427t3();
                Boolean bool2 = Boolean.FALSE;
                c2027bM10427t3.m12075a0(bool2, bool2, bool2);
            }
        }
    }

    /* renamed from: G */
    public void m12464G(String str, Map<String, Object> map) {
        l lVar = this.f10959n;
        if (lVar != null) {
            lVar.mo12491a(str, map);
        }
    }

    /* renamed from: H */
    public final void m12465H(int i9, Intent intent) {
        String str;
        String str2;
        if (i9 == 3 || i9 == 2) {
            String stringExtra = intent.getStringExtra("type");
            long longExtra = intent.getLongExtra("time", -1L);
            int i10 = (int) longExtra;
            if (stringExtra != null) {
                if (i10 == -1 && longExtra == -1) {
                    return;
                }
                if (MessageGeneratorFragment.InputType.valueOf(stringExtra).equals(MessageGeneratorFragment.InputType.Text)) {
                    String stringExtra2 = intent.getStringExtra("message");
                    if (stringExtra2 != null) {
                        HashMap map = new HashMap();
                        map.put(MimeTypes.BASE_TYPE_TEXT, stringExtra2);
                        if (i9 == 3) {
                            map.put(Constants.FirelogAnalytics.PARAM_TTL, Integer.valueOf(i10));
                            m12464G("selfDestruct", map);
                            return;
                        } else {
                            map.put("scheduleTime", new Date(longExtra));
                            m12464G("scheduleSend", map);
                            return;
                        }
                    }
                    return;
                }
                if (MessageGeneratorFragment.InputType.valueOf(stringExtra).equals(MessageGeneratorFragment.InputType.Sticker)) {
                    StickerObj stickerObj = (StickerObj) intent.getParcelableExtra("sticker");
                    if (stickerObj != null) {
                        HashMap map2 = new HashMap();
                        map2.put("sticer", stickerObj);
                        if (i9 == 3) {
                            map2.put(Constants.FirelogAnalytics.PARAM_TTL, Integer.valueOf(i10));
                            m12464G("selfDestruct", map2);
                            return;
                        } else {
                            map2.put("scheduleTime", new Date(longExtra));
                            m12464G("scheduleSend", map2);
                            return;
                        }
                    }
                    return;
                }
                if (MessageGeneratorFragment.InputType.valueOf(stringExtra).equals(MessageGeneratorFragment.InputType.Photo)) {
                    String stringExtra3 = intent.getStringExtra("subtype");
                    if (stringExtra3 == null || !Arrays.asList(MessageGeneratorFragment.PhotoType.Camera, MessageGeneratorFragment.PhotoType.Gallery).contains(MessageGeneratorFragment.PhotoType.valueOf(stringExtra3))) {
                        return;
                    }
                    ArrayList arrayList = (ArrayList) intent.getExtras().getSerializable("photo");
                    HashMap map3 = new HashMap();
                    map3.put("importImages", arrayList);
                    if (i9 == 3) {
                        map3.put(Constants.FirelogAnalytics.PARAM_TTL, Integer.valueOf(i10));
                        m12464G("selfDestruct", map3);
                        return;
                    } else {
                        map3.put("scheduleTime", new Date(longExtra));
                        m12464G("scheduleSend", map3);
                        return;
                    }
                }
                if (MessageGeneratorFragment.InputType.valueOf(stringExtra).equals(MessageGeneratorFragment.InputType.Voice)) {
                    try {
                        JSONObject jSONObject = new JSONObject((String) intent.getExtras().getSerializable("voice"));
                        str = (String) jSONObject.get("data");
                        try {
                            str2 = (String) jSONObject.get("duration");
                        } catch (Exception e9) {
                            e = e9;
                            Log.d(f10938G, "Parse voice exception : " + e.getMessage());
                            str2 = null;
                            if (str != null) {
                                return;
                            } else {
                                return;
                            }
                        }
                    } catch (Exception e10) {
                        e = e10;
                        str = null;
                    }
                    if (str != null || str2 == null) {
                        return;
                    }
                    HashMap map4 = new HashMap();
                    map4.put("voicePath", str);
                    map4.put("duration", str2);
                    if (i9 == 3) {
                        map4.put(Constants.FirelogAnalytics.PARAM_TTL, Integer.valueOf(i10));
                        m12464G("selfDestruct", map4);
                    } else {
                        map4.put("scheduleTime", new Date(longExtra));
                        m12464G("scheduleSend", map4);
                    }
                }
            }
        }
    }

    /* renamed from: I */
    public void m12466I(l lVar) {
        this.f10959n = lVar;
    }

    /* renamed from: J */
    public final void m12467J() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity instanceof ChatDialogActivity) {
                C2027b c2027bM11611w7 = ((ChatDialogActivity) activity).m11611w7();
                Boolean bool = Boolean.FALSE;
                c2027bM11611w7.m12075a0(bool, bool, Boolean.TRUE);
            } else if (activity instanceof TopicContentActivity) {
                C2027b c2027bM10427t3 = ((TopicContentActivity) activity).m10427t3();
                Boolean bool2 = Boolean.FALSE;
                c2027bM10427t3.m12075a0(bool2, bool2, Boolean.TRUE);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) throws SecurityException, IOException, IllegalArgumentException {
        ArrayList parcelableArrayListExtra;
        super.onActivityResult(i9, i10, intent);
        ULogUtility.m16680p(f10938G, "[onActivityResult] requestCode : " + i9 + " resultCode : " + i10);
        if (getActivity() == null) {
        }
        switch (i9) {
            case 0:
                if (i10 == -1) {
                    Object obj = (ArrayList) intent.getExtras().getSerializable("import_images");
                    Map<String, Object> map = new HashMap<>();
                    map.put("importImages", obj);
                    m12464G("sendPhoto", map);
                    break;
                }
                break;
            case 1:
                if (i10 == -1) {
                    Globals.m7388i0().m7526b3(false);
                    FragmentActivity activity = getActivity();
                    try {
                        ImageItem imageItemM16432E0 = CLUtility.m16432E0(Globals.m7372O(), f10939H);
                        if (imageItemM16432E0 != null && activity != null) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(imageItemM16432E0);
                            Map<String, Object> map2 = new HashMap<>();
                            map2.put("importImages", arrayList);
                            m12464G("sendPhoto", map2);
                            break;
                        }
                    } catch (Exception unused) {
                        Log.e(f10938G, "TODO: Handle Exception");
                        return;
                    }
                }
                break;
            case 2:
                if (i10 == -1) {
                    m12465H(2, intent);
                    break;
                }
                break;
            case 3:
                if (i10 == -1) {
                    m12465H(3, intent);
                    break;
                }
                break;
            case 4:
                if (i10 == -1 && intent != null && (parcelableArrayListExtra = intent.getParcelableArrayListExtra("results")) != null && parcelableArrayListExtra.size() > 0) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("selectFriends", parcelableArrayListExtra);
                    m12464G("introduceFriend", map3);
                    break;
                }
                break;
            case 5:
                if (i10 == -1) {
                    Uri uri = null;
                    if (intent != null) {
                        Uri uriM16510Z1 = CLUtility.m16510Z1(intent.getStringExtra("videoUri"));
                        f10940I = null;
                        uri = uriM16510Z1;
                    }
                    this.f10960o.m16335s(f10940I, uri, false);
                    break;
                }
                break;
            case 6:
                if (i10 == -1) {
                    Uri uriM16587t = CLUtility.m16587t(intent.getData());
                    if (uriM16587t == null) {
                        uriM16587t = intent.getData();
                    }
                    this.f10960o.m16339w(uriM16587t, getActivity() instanceof TopicContentActivity);
                    break;
                }
                break;
            case 7:
                if (i10 == -1) {
                    Object obj2 = (Location) intent.getParcelableExtra(FirebaseAnalytics.Param.LOCATION);
                    Object parcelableArrayListExtra2 = intent.getParcelableArrayListExtra("location_addresses");
                    Object obj3 = (Uri) intent.getParcelableExtra("snapshotUrl");
                    Map<String, Object> map4 = new HashMap<>();
                    map4.put(FirebaseAnalytics.Param.LOCATION, obj2);
                    map4.put("addresses", parcelableArrayListExtra2);
                    map4.put("snapshotPath", obj3);
                    m12464G("shareLocation", map4);
                    break;
                }
                break;
            case 8:
                if (i10 == -1) {
                    Object data = intent.getData();
                    Map<String, Object> map5 = new HashMap<>();
                    map5.put("filePath", data);
                    m12464G("sendFile", map5);
                    break;
                }
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        Log.d(f10938G, "onAttach");
        super.onAttach(activity);
        this.f10960o = new C3114a(activity, new C3114a.d() { // from class: com.cyberlink.you.activity.n0
            @Override // com.cyberlink.you.transcode.C3114a.d
            /* renamed from: a */
            public final void mo12439a(Map map) {
                this.f10911a.m12444F(map);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Log.d(f10938G, "onCreate");
        this.f10961p = (Group) getArguments().getParcelable("Group");
        this.f10962q = getArguments().getBoolean("enableExtraOp", true);
        this.f10963r = getArguments().getBoolean("hideVoice4ImportPhoto", false);
        this.f10964s = getArguments().getBoolean("singleSelect4ImportPhoto", false);
        this.f10965t = getArguments().getBoolean("enableMeetingShareDoc", false);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_chat_plusv2, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.f10950e.setOnClickListener(null);
        this.f10951f.setOnClickListener(null);
        this.f10948c.setOnClickListener(null);
        this.f10949d.setOnClickListener(null);
        this.f10952g.setOnClickListener(null);
        this.f10953h.setOnClickListener(null);
        this.f10954i.setOnClickListener(null);
        this.f10955j.setOnClickListener(null);
        k kVar = this.f10966u;
        if (kVar != null && !kVar.isCancelled()) {
            this.f10966u.cancel(true);
            this.f10966u = null;
        }
        C3114a c3114a = this.f10960o;
        if (c3114a != null) {
            c3114a.m16332p();
        }
        super.onDestroy();
        Log.d(f10938G, "onDestroy");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Log.d(f10938G, "onDetach");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.f10960o.m16333q();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.f10960o.m16334r();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        String str = f10938G;
        Log.d(str, "onViewCreated IN mIsEnableMeetingShareDoc:" + this.f10965t);
        if (this.f10965t) {
            View viewFindViewById = view.findViewById(R.id.base_container);
            this.f10947b = viewFindViewById;
            if (viewFindViewById != null && (viewFindViewById instanceof LinearLayout)) {
                LinearLayout linearLayout = (LinearLayout) viewFindViewById;
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.height = (int) TypedValue.applyDimension(1, 95.0f, getResources().getDisplayMetrics());
                Log.d(str, "onViewCreated baseLayout.setLayoutParams:" + layoutParams.height);
                linearLayout.setLayoutParams(layoutParams);
            }
        }
        View viewFindViewById2 = view.findViewById(R.id.photoLibrary);
        this.f10950e = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f10967v);
        View viewFindViewById3 = view.findViewById(R.id.camera);
        this.f10951f = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f10968w);
        View viewFindViewById4 = view.findViewById(R.id.scheduleSend);
        this.f10952g = viewFindViewById4;
        viewFindViewById4.setOnClickListener(this.f10970y);
        View viewFindViewById5 = view.findViewById(R.id.voiceMessage);
        this.f10953h = viewFindViewById5;
        viewFindViewById5.setOnClickListener(this.f10941A);
        View viewFindViewById6 = view.findViewById(R.id.introduceFriend);
        this.f10954i = viewFindViewById6;
        viewFindViewById6.setOnClickListener(this.f10943C);
        View viewFindViewById7 = view.findViewById(R.id.shareLocation);
        this.f10955j = viewFindViewById7;
        viewFindViewById7.setOnClickListener(this.f10969x);
        View viewFindViewById8 = view.findViewById(R.id.voiceCall);
        this.f10956k = viewFindViewById8;
        viewFindViewById8.setOnClickListener(this.f10944D);
        View viewFindViewById9 = view.findViewById(R.id.videoMeeting);
        this.f10957l = viewFindViewById9;
        viewFindViewById9.setOnClickListener(this.f10945E);
        View viewFindViewById10 = view.findViewById(R.id.videoCapture);
        this.f10948c = viewFindViewById10;
        viewFindViewById10.setOnClickListener(this.f10971z);
        View viewFindViewById11 = view.findViewById(R.id.videoLibrary);
        this.f10949d = viewFindViewById11;
        viewFindViewById11.setOnClickListener(this.f10942B);
        View viewFindViewById12 = view.findViewById(R.id.shareFile);
        this.f10958m = viewFindViewById12;
        viewFindViewById12.setOnClickListener(this.f10946F);
        if (this.f10965t) {
            this.f10953h.setVisibility(8);
            this.f10955j.setVisibility(8);
            this.f10956k.setVisibility(8);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.first_row_container);
            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.second_row_container);
            linearLayout2.removeView(this.f10953h);
            linearLayout2.removeView(this.f10955j);
            linearLayout2.removeView(this.f10956k);
            linearLayout3.setVisibility(8);
            m12469z(view);
            return;
        }
        Group group = this.f10961p;
        if (group != null && group.f13711J) {
            this.f10953h.setVisibility(8);
            this.f10955j.setVisibility(8);
            this.f10949d.setVisibility(8);
            this.f10948c.setVisibility(4);
            this.f10952g.setVisibility(4);
            this.f10954i.setVisibility(4);
            LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.first_row_container);
            LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.second_row_container);
            linearLayout4.removeView(this.f10958m);
            linearLayout5.removeView(this.f10956k);
            linearLayout5.removeView(this.f10957l);
            linearLayout4.addView(this.f10956k);
            linearLayout4.addView(this.f10957l);
            linearLayout5.addView(this.f10958m, 0);
            m12469z(view);
            return;
        }
        if (group != null && !group.f13738y.equals("General")) {
            this.f10952g.setVisibility(8);
            this.f10953h.setVisibility(8);
            this.f10954i.setVisibility(8);
            return;
        }
        if (!this.f10962q) {
            this.f10952g.setVisibility(8);
            this.f10954i.setVisibility(8);
            this.f10955j.setVisibility(8);
            this.f10956k.setVisibility(8);
            this.f10957l.setVisibility(8);
            this.f10958m.setVisibility(8);
            this.f10953h.setOnClickListener(this.f10941A);
            m12469z(view);
            return;
        }
        Group group2 = this.f10961p;
        if (group2 == null || !group2.m15751i()) {
            return;
        }
        this.f10956k.setVisibility(8);
        this.f10957l.setVisibility(8);
        LinearLayout linearLayout6 = (LinearLayout) view.findViewById(R.id.first_row_container);
        LinearLayout linearLayout7 = (LinearLayout) view.findViewById(R.id.second_row_container);
        linearLayout6.removeView(this.f10958m);
        linearLayout7.addView(this.f10958m);
        m12469z(view);
    }

    /* renamed from: y */
    public final void m12468y(ViewGroup... viewGroupArr) {
        int i9 = C4755b.m18878a(getActivity())[0];
        int dimension = (int) getResources().getDimension(R.dimen.f2dp);
        int i10 = (i9 - (dimension * 5)) - dimension;
        for (ViewGroup viewGroup : viewGroupArr) {
            for (int i11 = 0; i11 < viewGroup.getChildCount(); i11++) {
                viewGroup.getChildAt(i11).getLayoutParams().width = i10 / 4;
            }
        }
    }

    /* renamed from: z */
    public final void m12469z(View view) {
        m12468y((LinearLayout) view.findViewById(R.id.first_row_container), (LinearLayout) view.findViewById(R.id.second_row_container));
    }
}
