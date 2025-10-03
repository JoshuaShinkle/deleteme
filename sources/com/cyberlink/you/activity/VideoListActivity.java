package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.VideoListActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import p116k4.C5152i0;
import p116k4.C5178r;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p136m3.C5321e;
import p201t3.C6301o;
import p209u2.C6385v;
import p210u3.C6393b0;
import p218v2.C6456d;

/* loaded from: classes.dex */
public class VideoListActivity extends BaseFragmentActivity {

    /* renamed from: n */
    public static ExecutorService f9462n = Executors.newSingleThreadExecutor();

    /* renamed from: o */
    public static List<C2973l0> f9463o = new ArrayList();

    /* renamed from: p */
    public static MyStatus f9464p;

    /* renamed from: q */
    public static View f9465q;

    /* renamed from: r */
    public static Group f9466r;

    /* renamed from: d */
    public View f9468d;

    /* renamed from: e */
    public TextView f9469e;

    /* renamed from: f */
    public Dialog f9470f;

    /* renamed from: g */
    public TextView f9471g;

    /* renamed from: c */
    public C1828e f9467c = null;

    /* renamed from: h */
    public View.OnClickListener f9472h = new View.OnClickListener() { // from class: com.cyberlink.you.activity.dl
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10382b.m10743o1(view);
        }
    };

    /* renamed from: i */
    public View.OnClickListener f9473i = new View.OnClickListener() { // from class: com.cyberlink.you.activity.el
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10415b.m10744p1(view);
        }
    };

    /* renamed from: j */
    public final View.OnClickListener f9474j = new View.OnClickListener() { // from class: com.cyberlink.you.activity.fl
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10502b.m10745q1(view);
        }
    };

    /* renamed from: k */
    public final View.OnClickListener f9475k = new ViewOnClickListenerC1825b();

    /* renamed from: l */
    public C2907m0.h f9476l = new C2907m0.h() { // from class: com.cyberlink.you.activity.gl
        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public final void mo119x(boolean z8) {
            this.f10702b.m10746r1(z8);
        }
    };

    /* renamed from: m */
    public C2907m0.g f9477m = new C2907m0.g() { // from class: com.cyberlink.you.activity.hl
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f10734b.m10751u1();
        }
    };

    public enum MyStatus {
        Normal,
        Select_Share,
        Select_Save,
        Select_Delete
    }

    /* renamed from: com.cyberlink.you.activity.VideoListActivity$a */
    public class C1824a implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f9483a;

        public C1824a(Permission permission) {
            this.f9483a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(VideoListActivity.this, this.f9483a);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            C5178r.m20244m(VideoListActivity.this, 0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoListActivity$b */
    public class ViewOnClickListenerC1825b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$b$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ Permission f9486a;

            public a(Permission permission) {
                this.f9486a = permission;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(VideoListActivity.this, this.f9486a);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                MyStatus unused = VideoListActivity.f9464p = MyStatus.Select_Save;
                VideoListActivity.this.f9467c.m10784u();
                VideoListActivity.this.f9468d.setVisibility(0);
                VideoListActivity.this.f9467c.m10779D();
            }
        }

        public ViewOnClickListenerC1825b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m10756e(View view) {
            VideoListActivity.this.f9470f.dismiss();
            MyStatus unused = VideoListActivity.f9464p = MyStatus.Select_Share;
            VideoListActivity.this.f9467c.m10784u();
            VideoListActivity.this.f9468d.setVisibility(0);
            VideoListActivity.this.f9467c.m10779D();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m10757f(View view) {
            VideoListActivity.this.f9470f.dismiss();
            Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.VIDEO : Permission.STORAGE;
            C5287b.m20583f(permission, new a(permission), VideoListActivity.this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m10758g(View view) {
            VideoListActivity.this.f9470f.dismiss();
            MyStatus unused = VideoListActivity.f9464p = MyStatus.Select_Delete;
            VideoListActivity.this.f9467c.m10784u();
            VideoListActivity.this.f9468d.setVisibility(0);
            VideoListActivity.this.f9467c.m10779D();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m10759h(View view) {
            VideoListActivity.this.f9470f.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (VideoListActivity.this.f9467c.m10785v() != 0) {
                VideoListActivity.this.f9470f = new Dialog(VideoListActivity.this);
                VideoListActivity.this.f9470f.requestWindowFeature(1);
                VideoListActivity.this.f9470f.setContentView(R.layout.dialog_video_list_more);
                VideoListActivity.this.f9470f.findViewById(R.id.shareVideos).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ml
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10908b.m10756e(view2);
                    }
                });
                VideoListActivity.this.f9470f.findViewById(R.id.saveVideos).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.nl
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10935b.m10757f(view2);
                    }
                });
                VideoListActivity.this.f9470f.findViewById(R.id.deleteVideos).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ol
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f11022b.m10758g(view2);
                    }
                });
                VideoListActivity.this.f9470f.findViewById(R.id.DialogCancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.pl
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f11053b.m10759h(view2);
                    }
                });
                VideoListActivity videoListActivity = VideoListActivity.this;
                CLUtility.m16578q2(videoListActivity, videoListActivity.f9470f);
                VideoListActivity.this.f9470f.show();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoListActivity$c */
    public class C1826c implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ long[] f9488a;

        /* renamed from: b */
        public final /* synthetic */ Activity f9489b;

        /* renamed from: c */
        public final /* synthetic */ Permission f9490c;

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$c$a */
        public class a extends AsyncTask<Void, Void, List<C2973l0>> {
            public a() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public List<C2973l0> doInBackground(Void... voidArr) {
                Thread.currentThread().setName("selectedMediaIdList");
                return C2950b0.m14914m().m14726w(C1826c.this.f9488a);
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(List<C2973l0> list) {
                C2925v.m14626v0(C1826c.this.f9489b, list);
            }
        }

        public C1826c(long[] jArr, Activity activity, Permission permission) {
            this.f9488a = jArr;
            this.f9489b = activity;
            this.f9490c = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(this.f9489b, this.f9490c);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            new a().executeOnExecutor(VideoListActivity.f9462n, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoListActivity$d */
    public static /* synthetic */ class C1827d {

        /* renamed from: a */
        public static final /* synthetic */ int[] f9492a;

        static {
            int[] iArr = new int[MyStatus.values().length];
            f9492a = iArr;
            try {
                iArr[MyStatus.Select_Share.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9492a[MyStatus.Select_Save.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9492a[MyStatus.Select_Delete.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoListActivity$e */
    public static class C1828e extends Fragment {

        /* renamed from: b */
        public C6393b0 f9493b;

        /* renamed from: c */
        public GridView f9494c;

        /* renamed from: d */
        public TextView f9495d;

        /* renamed from: e */
        public g f9496e;

        /* renamed from: f */
        public AdapterView.OnItemClickListener f9497f = new a();

        /* renamed from: g */
        public C6393b0.b f9498g = new b();

        /* renamed from: h */
        public View.OnClickListener f9499h = new c();

        /* renamed from: i */
        public View.OnClickListener f9500i = new d();

        /* renamed from: j */
        public View.OnClickListener f9501j = new e();

        /* renamed from: k */
        public C5321e.m f9502k = new C5321e.m() { // from class: com.cyberlink.you.activity.ql
            @Override // p136m3.C5321e.m
            /* renamed from: A0 */
            public final boolean mo8241A0(C2904l c2904l, Map map) {
                return this.f11126b.m10772w(c2904l, map);
            }
        };

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$e$a */
        public class a implements AdapterView.OnItemClickListener {
            public a() {
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
                VideoListActivity.f9465q.setVisibility(8);
                C2973l0 c2973l0 = (C2973l0) adapterView.getItemAtPosition(i9);
                Intent intent = new Intent(C1828e.this.getActivity(), (Class<?>) VideoPlaybackActivity.class);
                intent.putExtra("Group", C2950b0.m14912k().m15077n(String.valueOf(VideoListActivity.f9466r.f13727n)));
                intent.putExtra("video_playback_url", c2973l0.m15148t().f13200d);
                intent.putExtra("mediaId", c2973l0.m15144p());
                intent.putExtra("videoMessage", false);
                C1828e.this.startActivityForResult(intent, 2);
            }
        }

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$e$b */
        public class b implements C6393b0.b {
            public b() {
            }

            @Override // p210u3.C6393b0.b
            /* renamed from: a */
            public void mo10786a(int i9) {
                if (i9 <= 0) {
                    C1828e.this.f9495d.setEnabled(false);
                    if (VideoListActivity.f9464p.equals(MyStatus.Select_Share)) {
                        C1828e.this.f9495d.setText(Globals.m7372O().getString(R.string.chat_dialog_send_button));
                        return;
                    } else if (VideoListActivity.f9464p.equals(MyStatus.Select_Save)) {
                        C1828e.this.f9495d.setText(Globals.m7372O().getString(R.string.menu_save_to_camera_roll));
                        return;
                    } else {
                        if (VideoListActivity.f9464p.equals(MyStatus.Select_Delete)) {
                            C1828e.this.f9495d.setText(Globals.m7372O().getString(R.string.menu_delete));
                            return;
                        }
                        return;
                    }
                }
                C1828e.this.f9495d.setEnabled(true);
                if (VideoListActivity.f9464p.equals(MyStatus.Select_Share)) {
                    C1828e.this.f9495d.setText(Globals.m7372O().getString(R.string.chat_dialog_send_button) + " (" + i9 + ")");
                    return;
                }
                if (VideoListActivity.f9464p.equals(MyStatus.Select_Save)) {
                    C1828e.this.f9495d.setText(Globals.m7372O().getString(R.string.menu_save_to_camera_roll) + " (" + i9 + ")");
                    return;
                }
                if (VideoListActivity.f9464p.equals(MyStatus.Select_Delete)) {
                    C1828e.this.f9495d.setText(Globals.m7372O().getString(R.string.menu_delete) + " (" + i9 + ")");
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$e$c */
        public class c implements View.OnClickListener {
            public c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long[] jArrM24562c = C1828e.this.f9493b.m24562c();
                FragmentActivity activity = C1828e.this.getActivity();
                if (activity != null && jArrM24562c != null && jArrM24562c.length > 0) {
                    VideoListActivity.m10736h1(activity, jArrM24562c);
                }
                C1828e.this.m10782s();
            }
        }

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$e$d */
        public class d implements View.OnClickListener {
            public d() {
            }

            /* renamed from: b */
            public static /* synthetic */ void m10788b(DialogInterface dialogInterface, int i9) {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long[] jArrM24562c = C1828e.this.f9493b.m24562c();
                FragmentActivity activity = C1828e.this.getActivity();
                if (activity != null && jArrM24562c != null && jArrM24562c.length > 0) {
                    int iM14720q = C2950b0.m14914m().m14720q(jArrM24562c, String.valueOf(Globals.m7388i0().m7568k1()));
                    if (VideoListActivity.f9466r.f13704C || VideoListActivity.f9466r.f13729p <= 0 || iM14720q <= 0) {
                        VideoListActivity.m10735f1(activity, VideoListActivity.f9466r, jArrM24562c, null);
                    } else {
                        AlertDialog.Builder builderM16382a = C3123g.m16382a(C1828e.this.getActivity());
                        builderM16382a.setMessage(R.string.delete_photo_error_of_not_creator);
                        builderM16382a.setPositiveButton(C1828e.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.ul
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i9) {
                                VideoListActivity.C1828e.d.m10788b(dialogInterface, i9);
                            }
                        });
                        builderM16382a.setCancelable(false);
                        AlertDialog alertDialogCreate = builderM16382a.create();
                        alertDialogCreate.requestWindowFeature(1);
                        alertDialogCreate.show();
                    }
                }
                C1828e.this.m10782s();
            }
        }

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$e$e */
        public class e implements View.OnClickListener {
            public e() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long[] jArrM24562c = C1828e.this.f9493b.m24562c();
                FragmentActivity activity = C1828e.this.getActivity();
                if (activity != null && jArrM24562c != null && jArrM24562c.length > 0) {
                    VideoListActivity.m10747s1(activity, jArrM24562c);
                }
                C1828e.this.m10782s();
            }
        }

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$e$f */
        public class f extends AsyncTask<Void, Void, Void> {
            public f() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                List unused = VideoListActivity.f9463o = C2950b0.m14914m().m14695D(VideoListActivity.f9466r.f13718e, "ASC");
                return null;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Void r12) {
                C1828e.this.m10776A();
                C1828e.this.m10781l();
            }
        }

        /* renamed from: com.cyberlink.you.activity.VideoListActivity$e$g */
        public class g extends AsyncTask<Void, Void, List<C2973l0>> {
            public g() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public List<C2973l0> doInBackground(Void... voidArr) {
                Log.d("VideoListActivity", "[SyncVideoListTask] in");
                FriendsClient friendsClient = new FriendsClient(true);
                List<C2973l0> listM15732j0 = friendsClient.m15732j0(VideoListActivity.f9466r.f13718e);
                friendsClient.m15717U0();
                List<C2973l0> listM14695D = C2950b0.m14914m().m14695D(VideoListActivity.f9466r.f13718e, "ASC");
                if (listM15732j0 != null) {
                    Log.d("VideoListActivity", "[SyncVideoListTask] in mediaObjListFromServer size = " + listM15732j0.size());
                    boolean zM14713j = C2950b0.m14914m().m14713j(listM15732j0);
                    Log.d("VideoListActivity", "[SyncVideoListTask] Is success to get video list from server? = " + zM14713j);
                    if (listM15732j0.size() != listM14695D.size() || zM14713j) {
                        VideoListActivity.f9463o.clear();
                        List unused = VideoListActivity.f9463o = C2950b0.m14914m().m14695D(VideoListActivity.f9466r.f13718e, "ASC");
                    }
                }
                return VideoListActivity.f9463o;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(List<C2973l0> list) {
                super.onPostExecute(list);
                C1828e.this.m10777B(false);
                C1828e.this.m10776A();
            }

            public /* synthetic */ g(C1828e c1828e, C1824a c1824a) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: w */
        public /* synthetic */ boolean m10772w(C2904l c2904l, Map map) throws JSONException, NumberFormatException {
            String str = (String) map.get("eventType");
            String str2 = (String) map.get("albumName");
            if (!VideoListActivity.f9466r.f13718e.equals(str2)) {
                return false;
            }
            if (str.equals("media.media.created")) {
                try {
                    long j9 = Long.parseLong((String) map.get("mediaId"));
                    C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                    if (c2973l0M14725v == null || TextUtils.isEmpty(c2973l0M14725v.m15147s())) {
                        FriendsClient.m15657X(str2, j9);
                    }
                } catch (NumberFormatException unused) {
                    ULogUtility.m16676l("VideoListActivity", "[mEventListener] NumberFormatException media id:" + ((String) map.get("mediaId")));
                }
            }
            if (!str.equals("media.media.created") && !str.equals("media.media.deleted")) {
                return true;
            }
            List unused2 = VideoListActivity.f9463o = C2950b0.m14914m().m14695D(str2, "ASC");
            m10776A();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: x */
        public /* synthetic */ void m10773x() {
            C6393b0 c6393b0 = this.f9493b;
            if (c6393b0 == null) {
                return;
            }
            c6393b0.m24565f(VideoListActivity.f9463o);
            m10778C();
            int count = this.f9493b.getCount();
            if (count > 0) {
                this.f9494c.setSelection(count - 1);
            }
        }

        /* renamed from: y */
        public static /* synthetic */ void m10774y(boolean z8) {
            if (z8) {
                VideoListActivity.f9465q.setVisibility(0);
            } else {
                VideoListActivity.f9465q.setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: z */
        public /* synthetic */ void m10775z(Activity activity) {
            C6393b0 c6393b0 = this.f9493b;
            if (c6393b0 != null) {
                int count = c6393b0.getCount();
                View viewFindViewById = activity.findViewById(R.id.OptionBtn);
                View viewFindViewById2 = activity.findViewById(R.id.addVideoBtn);
                if (viewFindViewById != null && viewFindViewById2 != null) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById2.getLayoutParams();
                    if (count > 0) {
                        viewFindViewById.setVisibility(0);
                        layoutParams.setMargins(0, 0, 0, 0);
                    } else {
                        viewFindViewById.setVisibility(8);
                        layoutParams.setMargins(0, 0, (int) activity.getResources().getDimension(R.dimen.t10dp), 0);
                    }
                    viewFindViewById2.setLayoutParams(layoutParams);
                    if (!VideoListActivity.m10738j1()) {
                        viewFindViewById.setVisibility(8);
                        viewFindViewById2.setVisibility(8);
                    }
                }
                m10780k(activity);
            }
        }

        /* renamed from: A */
        public final void m10776A() {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.sl
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11358b.m10773x();
                }
            });
        }

        /* renamed from: B */
        public final void m10777B(final boolean z8) {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.rl
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoListActivity.C1828e.m10774y(z8);
                    }
                });
            }
        }

        /* renamed from: C */
        public final void m10778C() {
            final FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.tl
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11415b.m10775z(activity);
                }
            });
        }

        /* renamed from: D */
        public void m10779D() {
            int i9 = C1827d.f9492a[VideoListActivity.f9464p.ordinal()];
            if (i9 == 1) {
                this.f9495d.setOnClickListener(this.f9499h);
                this.f9495d.setText(Globals.m7372O().getString(R.string.chat_dialog_send_button));
                this.f9495d.setBackgroundResource(R.drawable.image_selector_common_button);
            } else if (i9 == 2) {
                this.f9495d.setOnClickListener(this.f9501j);
                this.f9495d.setText(Globals.m7372O().getString(R.string.menu_save_to_camera_roll));
                this.f9495d.setBackgroundResource(R.drawable.image_selector_common_button);
            } else if (i9 != 3) {
                this.f9495d.setOnClickListener(null);
                this.f9495d.setText("");
                Log.d("VideoListActivity", "[updateOperation] unexpected status");
            } else {
                this.f9495d.setOnClickListener(this.f9500i);
                this.f9495d.setText(Globals.m7372O().getString(R.string.menu_delete));
                this.f9495d.setBackgroundResource(R.drawable.image_selector_common_button_red);
            }
        }

        /* renamed from: k */
        public final void m10780k(Activity activity) {
            TextView textView = (TextView) activity.findViewById(R.id.VideoListTitleText);
            int iMax = Math.max(activity.findViewById(R.id.VideoListLeftBtnLayout).getWidth(), activity.findViewById(R.id.RightBtnLayout).getWidth());
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.setMargins(iMax, 0, iMax, 0);
            textView.setLayoutParams(layoutParams);
            textView.requestLayout();
        }

        /* renamed from: l */
        public final void m10781l() {
            Log.d("VideoListActivity", " [SyncVideoListTask] start");
            m10777B(true);
            g gVar = new g(this, null);
            this.f9496e = gVar;
            gVar.executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        @Override // androidx.fragment.app.Fragment
        public void onActivityResult(int i9, int i10, Intent intent) {
            super.onActivityResult(i9, i10, intent);
            if (i9 != 1) {
                if (i9 == 2 && i10 == -1) {
                    if (intent.getBooleanExtra("operationResult", false)) {
                        m10776A();
                    }
                    this.f9493b.notifyDataSetChanged();
                    return;
                }
                return;
            }
            if (i10 == -1) {
                int intExtra = intent.getIntExtra("type", -1);
                long[] longArrayExtra = intent.getLongArrayExtra("checkedMediaList");
                if (intExtra != -1) {
                    this.f9493b.m24570k(longArrayExtra);
                    if (intExtra == 3) {
                        m10782s();
                    }
                }
            }
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View viewInflate = layoutInflater.inflate(R.layout.fragment_video_list, viewGroup, false);
            C5321e.m20824o().m20875k(this.f9502k);
            return viewInflate;
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            this.f9493b = null;
            g gVar = this.f9496e;
            if (gVar == null || gVar.isCancelled()) {
                return;
            }
            this.f9496e.cancel(true);
            this.f9496e = null;
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroyView() {
            super.onDestroyView();
            C5321e.m20824o().m20832B0(this.f9502k);
        }

        @Override // androidx.fragment.app.Fragment
        public void onPause() {
            super.onPause();
            C6456d.m24714D().m24751K(getClass().getSimpleName());
        }

        @Override // androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            C6456d.m24714D().m24743A(getClass().getSimpleName());
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            this.f9495d = (TextView) view.findViewById(R.id.menuOperation);
            GridView gridView = (GridView) view.findViewById(R.id.MyVideoListView);
            this.f9494c = gridView;
            gridView.setOnItemClickListener(this.f9497f);
            C6393b0 c6393b0 = new C6393b0(getActivity(), R.layout.view_item_group_video, new ArrayList());
            this.f9493b = c6393b0;
            c6393b0.m24566g((TextView) getActivity().findViewById(R.id.numberOfSelectedItem));
            this.f9493b.m24569j(this.f9498g);
            this.f9494c.setEmptyView(view.findViewById(R.id.VideoListEmptyView));
            this.f9494c.setAdapter((ListAdapter) this.f9493b);
            new f().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }

        /* renamed from: s */
        public final void m10782s() {
            MyStatus unused = VideoListActivity.f9464p = MyStatus.Normal;
            m10783t();
            getActivity().findViewById(R.id.toolbar).setVisibility(8);
        }

        /* renamed from: t */
        public void m10783t() {
            this.f9495d.setVisibility(8);
            this.f9493b.m24568i(false);
            this.f9495d.setOnClickListener(null);
            this.f9495d.setText("");
        }

        /* renamed from: u */
        public void m10784u() {
            this.f9495d.setVisibility(0);
            this.f9493b.m24568i(true);
        }

        /* renamed from: v */
        public int m10785v() {
            return this.f9494c.getCount();
        }
    }

    /* renamed from: e1 */
    public static void m10734e1(Activity activity, Group group, long j9, FriendsClient.InterfaceC3051i interfaceC3051i) {
        m10735f1(activity, group, new long[]{j9}, interfaceC3051i);
    }

    /* renamed from: f1 */
    public static void m10735f1(final Activity activity, final Group group, final long[] jArr, final FriendsClient.InterfaceC3051i interfaceC3051i) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
        String string = activity.getString(group.m15750g() ? R.string.delete_video_text : R.string.delete_video_reminder);
        String string2 = activity.getString(group.m15750g() ? R.string.ok : R.string.delete_from_this_group);
        builderM16382a.setTitle(activity.getString(R.string.delete_videos));
        builderM16382a.setMessage(string);
        builderM16382a.setNegativeButton(string2, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.jl
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                VideoListActivity.m10741m1(jArr, activity, group, interfaceC3051i, dialogInterface, i9);
            }
        });
        builderM16382a.setPositiveButton(activity.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.kl
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                VideoListActivity.m10742n1(dialogInterface, i9);
            }
        });
        AlertDialog alertDialogShow = builderM16382a.show();
        if (group.m15750g()) {
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
            button2.setTextColor(activity.getResources().getColor(R.color.you_color_delete_red));
            C5179r0.m20247b(button2, 1);
        }
    }

    /* renamed from: h1 */
    public static void m10736h1(Activity activity, long[] jArr) {
        Intent intent = new Intent(activity, (Class<?>) ShareMediaActivity.class);
        intent.putExtra("shareType", ShareType.Internal_Video.toString());
        intent.putExtra("share_media_id", jArr);
        activity.startActivity(intent);
    }

    /* renamed from: i1 */
    public static Group m10737i1() {
        return f9466r;
    }

    /* renamed from: j1 */
    public static boolean m10738j1() {
        Group group = f9466r;
        return group != null && C2925v.m14625v(group);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k1 */
    public /* synthetic */ void m10739k1() {
        int iM14480D = C2907m0.m14454I().m14480D();
        TextView textView = this.f9471g;
        if (textView == null || iM14480D < 0) {
            return;
        }
        if (iM14480D == 0) {
            textView.setVisibility(8);
        } else {
            textView.setText(iM14480D > 99 ? "N" : String.valueOf(iM14480D));
            this.f9471g.setVisibility(0);
        }
    }

    /* renamed from: l1 */
    public static /* synthetic */ void m10740l1(Group group, long[] jArr, ProgressDialog progressDialog, FriendsClient.InterfaceC3051i interfaceC3051i, String str, String str2, String str3, String str4) {
        C2925v.m14543A0(group, group.f13718e, group.f13717d, String.valueOf(jArr.length));
        C5152i0.m20065b(progressDialog);
        if (interfaceC3051i != null) {
            interfaceC3051i.mo134a(str, str2, str3, str4);
        }
    }

    /* renamed from: m1 */
    public static /* synthetic */ void m10741m1(final long[] jArr, Activity activity, final Group group, final FriendsClient.InterfaceC3051i interfaceC3051i, DialogInterface dialogInterface, int i9) {
        FriendsClient friendsClient = new FriendsClient();
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        for (long j9 : jArr) {
            arrayList.add(new C6301o("mediaId", String.valueOf(j9)));
        }
        final ProgressDialog progressDialogShow = ProgressDialog.show(activity, "", activity.getString(R.string.loading), true);
        friendsClient.m15734m("media", "deleteMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.ll
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                VideoListActivity.m10740l1(group, jArr, progressDialogShow, interfaceC3051i, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: n1 */
    public static /* synthetic */ void m10742n1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o1 */
    public /* synthetic */ void m10743o1(View view) {
        m10749g1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p1 */
    public /* synthetic */ void m10744p1(View view) {
        C1828e c1828e = this.f9467c;
        if (c1828e != null) {
            c1828e.m10782s();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q1 */
    public /* synthetic */ void m10745q1(View view) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.VIDEO : Permission.STORAGE;
        C5287b.m20583f(permission, new C1824a(permission), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r1 */
    public /* synthetic */ void m10746r1(boolean z8) {
        if (z8) {
            m10748S0(true);
        }
    }

    /* renamed from: s1 */
    public static void m10747s1(Activity activity, long[] jArr) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.VIDEO : Permission.STORAGE;
        C5287b.m20583f(permission, new C1826c(jArr, activity, permission), activity);
    }

    /* renamed from: S0 */
    public final void m10748S0(boolean z8) {
        if (z8) {
            C2907m0.m14454I().m14495W(this.f9476l);
        }
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.il
            @Override // java.lang.Runnable
            public final void run() {
                this.f10765b.m10739k1();
            }
        });
    }

    /* renamed from: g1 */
    public final void m10749g1() {
        Intent intent;
        if (getCallingActivity() != null) {
            setResult(-1, new Intent());
        } else if (getApplicationContext() != null) {
            if (f9466r != null) {
                Log.d("VideoListActivity", "Back to ChatDialogActivity");
                intent = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
                intent.putExtra("Group", f9466r);
            } else {
                Log.d("VideoListActivity", "Back to ULauncherActivity");
                intent = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
            }
            intent.setFlags(67108864);
            startActivity(intent);
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0 && i10 == -1) {
            setResult(-1, intent);
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.f9468d.getVisibility() == 0) {
            this.f9469e.callOnClick();
        } else {
            m10749g1();
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_list);
        f9464p = MyStatus.Normal;
        findViewById(R.id.BackBtn).setOnClickListener(this.f9472h);
        TextView textView = (TextView) findViewById(R.id.cancel);
        this.f9469e = textView;
        textView.setOnClickListener(this.f9473i);
        findViewById(R.id.addVideoBtn).setOnClickListener(this.f9474j);
        findViewById(R.id.OptionBtn).setOnClickListener(this.f9475k);
        this.f9468d = findViewById(R.id.toolbar);
        f9465q = findViewById(R.id.loading);
        Intent intent = getIntent();
        Group group = intent != null ? (Group) intent.getParcelableExtra("Group") : null;
        f9466r = group;
        if (group == null) {
            ULogUtility.m16676l("VideoListActivity", "[onCreate] mGroupInfo is null");
            finish();
        } else {
            m10750t1(bundle);
            ((TextView) findViewById(R.id.VideoListTitleText)).setText(f9466r.f13717d);
            this.f9471g = (TextView) findViewById(R.id.videoListBadge);
            m10751u1();
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Log.d("VideoListActivity", "onDestroy");
        super.onDestroy();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        C2907m0.m14454I().m14494V(this.f9477m);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        C2907m0.m14454I().m14510t(this.f9477m);
        m10751u1();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Log.d("VideoListActivity", "[onSaveInstanceState] in");
        super.onSaveInstanceState(bundle);
        if (this.f9467c != null) {
            getSupportFragmentManager().mo1855l(bundle, C1828e.class.getName(), this.f9467c);
        }
    }

    /* renamed from: t1 */
    public final void m10750t1(Bundle bundle) {
        if (bundle != null) {
            Log.d("VideoListActivity", "[onCreate] get previous VideoListFragment instance.");
            this.f9467c = (C1828e) getSupportFragmentManager().mo1850g(bundle, C1828e.class.getName());
        }
        if (this.f9467c == null) {
            Log.d("VideoListActivity", "[setVideoFragment] mVideoListFragment is null, create a new VideoListFragment instance.");
            this.f9467c = new C1828e();
        }
        try {
            if (this.f9467c.isAdded()) {
                return;
            }
            getSupportFragmentManager().mo1844a().m1980b(R.id.VideoListFragmentContainer, this.f9467c).mo1794h();
        } catch (IllegalStateException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: u1 */
    public void m10751u1() {
        if (C2907m0.m14454I().m14489N()) {
            m10748S0(false);
        } else {
            C2907m0.m14454I().m14511u(this.f9476l);
        }
    }
}
