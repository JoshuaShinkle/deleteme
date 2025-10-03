package com.cyberlink.you.groupalbum;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.CreateAlbumActivity;
import com.cyberlink.you.activity.GroupAlbumActivity;
import com.cyberlink.you.activity.PhotoListActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.ulauncher.PageType;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.groupalbum.GroupAlbumFragment;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.PhotoImportActivity;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.time.StopWatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5152i0;
import p116k4.C5154j;
import p116k4.C5172p;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p201t3.C6301o;
import p209u2.AbstractRunnableC6364a;
import p218v2.C6456d;
import p218v2.DialogC6459g;

/* loaded from: classes.dex */
public class GroupAlbumFragment extends Fragment {

    /* renamed from: j */
    public Activity f13836j;

    /* renamed from: k */
    public ListView f13837k;

    /* renamed from: l */
    public C3072a f13838l;

    /* renamed from: m */
    public TextView f13839m;

    /* renamed from: n */
    public View f13840n;

    /* renamed from: o */
    public View f13841o;

    /* renamed from: p */
    public FriendsClient f13842p;

    /* renamed from: r */
    public Group f13844r;

    /* renamed from: s */
    public String f13845s;

    /* renamed from: t */
    public C3071i f13846t;

    /* renamed from: v */
    public ProgressDialog f13848v;

    /* renamed from: w */
    public DialogC6459g f13849w;

    /* renamed from: x */
    public C3199c f13850x;

    /* renamed from: b */
    public int f13828b = 0;

    /* renamed from: c */
    public boolean f13829c = false;

    /* renamed from: d */
    public boolean f13830d = false;

    /* renamed from: e */
    public boolean f13831e = false;

    /* renamed from: f */
    public boolean f13832f = false;

    /* renamed from: g */
    public boolean f13833g = false;

    /* renamed from: h */
    public boolean f13834h = false;

    /* renamed from: i */
    public String f13835i = "";

    /* renamed from: q */
    public final Object f13843q = new Object();

    /* renamed from: u */
    public Executor f13847u = Executors.newFixedThreadPool(2);

    /* renamed from: y */
    public GroupAlbumStatus f13851y = GroupAlbumStatus.Normal_Case;

    /* renamed from: z */
    public AdapterView.OnItemClickListener f13852z = new C3063a();

    /* renamed from: A */
    public View.OnClickListener f13822A = new View.OnClickListener() { // from class: u3.n
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f21615b.m15878w0(view);
        }
    };

    /* renamed from: B */
    public XMPPManager.InterfaceC2851b0 f13823B = new C3064b();

    /* renamed from: C */
    public C6456d.j f13824C = new C6456d.j() { // from class: u3.o
        @Override // p218v2.C6456d.j
        public final void onConnected() {
            this.f21616b.m15880x0();
        }
    };

    /* renamed from: D */
    public DialogC6459g.a f13825D = new C3068f();

    /* renamed from: E */
    public C3199c.b f13826E = new C3069g();

    /* renamed from: F */
    public AbsListView.OnScrollListener f13827F = new C3070h();

    public enum GroupAlbumStatus {
        Normal_Case,
        Select_Case
    }

    public enum SelectDataType {
        None_Select,
        External_Media_Select,
        Internal_Media_Select
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$a */
    public class C3063a implements AdapterView.OnItemClickListener {
        public C3063a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (GroupAlbumFragment.this.f13836j == null) {
                return;
            }
            try {
                GroupAlbumObj groupAlbumObj = (GroupAlbumObj) adapterView.getItemAtPosition(i9);
                if (!GroupAlbumFragment.this.f13851y.equals(GroupAlbumStatus.Select_Case)) {
                    Intent intent = new Intent(GroupAlbumFragment.this.f13836j, (Class<?>) PhotoListActivity.class);
                    boolean z8 = !(GroupAlbumFragment.this.f13836j instanceof ULauncherActivity);
                    intent.putExtra("Group", GroupAlbumFragment.this.f13844r);
                    intent.putExtra("ShowShareToMyAlbum", z8);
                    intent.putExtra("album", groupAlbumObj);
                    intent.putStringArrayListExtra("album_name_list", GroupAlbumFragment.this.m15913d0());
                    GroupAlbumFragment.this.startActivityForResult(intent, 0);
                } else if (GroupAlbumFragment.this.m15917h0().equals(SelectDataType.Internal_Media_Select)) {
                    GroupAlbumFragment.this.m15885C0(groupAlbumObj);
                } else if (GroupAlbumFragment.this.m15917h0().equals(SelectDataType.External_Media_Select)) {
                    GroupAlbumFragment.this.m15893K0(groupAlbumObj.m14675b());
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$b */
    public class C3064b implements XMPPManager.InterfaceC2851b0 {
        public C3064b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m15921c() {
            GroupAlbumFragment.this.m15901S0();
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2851b0
        /* renamed from: h0 */
        public void mo13974h0(boolean z8) {
            if (GroupAlbumFragment.this.f13836j == null) {
                return;
            }
            GroupAlbumFragment.this.f13836j.runOnUiThread(new Runnable() { // from class: u3.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f21631b.m15921c();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$c */
    public class AsyncTaskC3065c extends AsyncTask<Void, Void, List<GroupAlbumObj>> {
        public AsyncTaskC3065c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<GroupAlbumObj> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("initAlbumList");
            return GroupAlbumFragment.this.m15912c0();
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<GroupAlbumObj> list) {
            GroupAlbumFragment.this.m15895M0(list);
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$d */
    public class AsyncTaskC3066d extends AsyncTask<Void, Void, List<GroupAlbumObj>> {
        public AsyncTaskC3066d() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<GroupAlbumObj> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("updateBackupAlbumList");
            return GroupAlbumFragment.this.m15912c0();
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<GroupAlbumObj> list) {
            GroupAlbumFragment.this.m15895M0(list);
            GroupAlbumFragment.this.f13829c = true;
            if (GroupAlbumFragment.this.f13830d) {
                return;
            }
            GroupAlbumFragment.this.m15902T0();
            GroupAlbumFragment.this.f13838l.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$e */
    public class C3067e implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f13864a;

        public C3067e(Permission permission) {
            this.f13864a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(GroupAlbumFragment.this.f13836j, this.f13864a);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            try {
                if (C2950b0.m14909h().m15037b(C2950b0.f13117a) == null) {
                    C5187v0.m20267c(R.string.no_local_photos);
                } else {
                    GroupAlbumFragment.this.m15904V();
                }
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$f */
    public class C3068f implements DialogC6459g.a {
        public C3068f() {
        }

        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public void mo7918a() {
            if (GroupAlbumFragment.this.f13850x != null) {
                GroupAlbumFragment.this.f13850x.m17036d();
            }
            if ((GroupAlbumFragment.this.f13836j instanceof GroupAlbumActivity) && GroupAlbumFragment.this.f13851y.equals(GroupAlbumStatus.Select_Case)) {
                if (GroupAlbumFragment.this.m15917h0().equals(SelectDataType.External_Media_Select)) {
                    GroupAlbumFragment.this.f13835i = "ULauncherActivity";
                } else if (GroupAlbumFragment.this.m15917h0().equals(SelectDataType.Internal_Media_Select)) {
                    GroupAlbumFragment.this.f13835i = "";
                }
                GroupAlbumFragment groupAlbumFragment = GroupAlbumFragment.this;
                GroupAlbumStatus groupAlbumStatus = GroupAlbumStatus.Normal_Case;
                groupAlbumFragment.m15891I0(groupAlbumStatus);
                ((GroupAlbumActivity) GroupAlbumFragment.this.f13836j).m8239e1(groupAlbumStatus);
            }
            GroupAlbumFragment.this.m15899Q0();
            GroupAlbumFragment.this.m15911b0();
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$g */
    public class C3069g implements C3199c.b {
        public C3069g() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m15929g(int i9, int i10, UploadMediaHelper uploadMediaHelper) {
            if (GroupAlbumFragment.this.f13849w != null) {
                GroupAlbumFragment.this.f13849w.m24771n(Integer.toString(i9 + 1), Integer.toString(i10));
                GroupAlbumFragment.this.f13849w.m24764f(uploadMediaHelper.m16842f1());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m15930h() {
            if (GroupAlbumFragment.this.f13849w != null) {
                GroupAlbumFragment.this.f13849w.m24760b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m15931i() {
            if (GroupAlbumFragment.this.getActivity().isFinishing() || GroupAlbumFragment.this.f13849w == null || !GroupAlbumFragment.this.f13849w.isShowing()) {
                return;
            }
            GroupAlbumFragment.this.f13849w.dismiss();
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: a */
        public void mo7916a(final int i9, final int i10, final UploadMediaHelper uploadMediaHelper) {
            C3199c.m17015D(true);
            if (GroupAlbumFragment.this.f13836j != null) {
                GroupAlbumFragment.this.f13836j.runOnUiThread(new Runnable() { // from class: u3.x
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21634b.m15929g(i9, i10, uploadMediaHelper);
                    }
                });
            }
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: b */
        public void mo7917b(C3199c c3199c) {
            if (GroupAlbumFragment.this.f13849w != null && GroupAlbumFragment.this.f13836j != null) {
                GroupAlbumFragment.this.f13836j.runOnUiThread(new Runnable() { // from class: u3.w
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21633b.m15931i();
                    }
                });
            }
            if ((GroupAlbumFragment.this.f13836j instanceof GroupAlbumActivity) && GroupAlbumFragment.this.f13851y.equals(GroupAlbumStatus.Select_Case)) {
                if (GroupAlbumFragment.this.m15917h0().equals(SelectDataType.External_Media_Select)) {
                    GroupAlbumFragment.this.f13835i = "ULauncherActivity";
                } else if (GroupAlbumFragment.this.m15917h0().equals(SelectDataType.Internal_Media_Select)) {
                    GroupAlbumFragment.this.f13835i = "";
                }
                GroupAlbumFragment groupAlbumFragment = GroupAlbumFragment.this;
                GroupAlbumStatus groupAlbumStatus = GroupAlbumStatus.Normal_Case;
                groupAlbumFragment.m15891I0(groupAlbumStatus);
                ((GroupAlbumActivity) GroupAlbumFragment.this.f13836j).m8239e1(groupAlbumStatus);
            }
            List<UploadMediaHelper> listM17042t = c3199c.m17042t();
            C2973l0 c2973l0M16826X0 = null;
            int i9 = 0;
            for (UploadMediaHelper uploadMediaHelper : listM17042t) {
                if (uploadMediaHelper.m16828Y0().equals(UploadUtils.UploadResultType.STEP_3_SUCCESS)) {
                    i9++;
                    if (c2973l0M16826X0 == null) {
                        c2973l0M16826X0 = uploadMediaHelper.m16826X0();
                    }
                } else {
                    Log.d("BaseAlbumFragment", "[onComplete] resultType=" + uploadMediaHelper.m16828Y0());
                    if (GroupAlbumFragment.this.f13836j != null) {
                        C5187v0.m20267c(R.string.error_server_response);
                    }
                }
            }
            if (GroupAlbumFragment.this.f13836j instanceof GroupAlbumActivity) {
                if (i9 != listM17042t.size()) {
                    Log.d("BaseAlbumFragment", "[onUploadMultipleMediaCallback] All medias are not uploaded completed.");
                } else if (GroupAlbumFragment.this.f13844r != null && c2973l0M16826X0 != null) {
                    C2925v.m14630x0(GroupAlbumFragment.this.f13844r, c2973l0M16826X0, String.valueOf(i9));
                }
            }
            c3199c.m17034E();
            GroupAlbumFragment.this.m15899Q0();
            C3199c.m17015D(false);
            GroupAlbumFragment.this.m15911b0();
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        public void onCancel() {
            if (GroupAlbumFragment.this.f13836j != null) {
                GroupAlbumFragment.this.f13836j.runOnUiThread(new Runnable() { // from class: u3.v
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21632b.m15930h();
                    }
                });
            }
            C3199c.m17015D(false);
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$h */
    public class C3070h implements AbsListView.OnScrollListener {
        public C3070h() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                GroupAlbumFragment.this.m15903U0(true);
            }
        }
    }

    /* renamed from: com.cyberlink.you.groupalbum.GroupAlbumFragment$i */
    public class C3071i extends AbstractRunnableC6364a {

        /* renamed from: c */
        public final String f13869c = "GetAlbumRunnable";

        /* renamed from: d */
        public String f13870d;

        /* renamed from: e */
        public String f13871e;

        /* renamed from: f */
        public final WeakReference<FriendsClient> f13872f;

        public C3071i(String str) {
            this.f13872f = new WeakReference<>(GroupAlbumFragment.this.f13842p);
            str = str == null ? "" : str;
            this.f13870d = str;
            if (str.equals("backup")) {
                GroupAlbumFragment.this.f13834h = true;
                this.f13871e = "backup";
            } else {
                GroupAlbumFragment.this.f13834h = false;
                this.f13871e = "group";
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m15934g(List list, int i9, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("GetAlbumRunnable", "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d("GetAlbumRunnable", "statusCode=" + str3);
                return;
            }
            if (m24448b()) {
                Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                return;
            }
            synchronized (list) {
                List<GroupAlbumObj> listM15937i = m15937i(str4);
                if (listM15937i != null) {
                    list.addAll(listM15937i);
                }
                if (m24448b()) {
                    Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                } else {
                    if (list.size() == i9) {
                        m15938j(list);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m15935h(String str, String str2, String str3, String str4, String str5) {
            if (str4 == null) {
                Log.d("GetAlbumRunnable", "[queryAlbumList] Response is null");
                return;
            }
            if (!str4.equals("200")) {
                Log.d("GetAlbumRunnable", "[queryAlbumList] statusCode=" + str4);
                return;
            }
            Log.d("GetAlbumRunnable", "[queryAlbumList] Success.");
            if (m24448b()) {
                Log.d("GetAlbumRunnable", "[queryAlbumList] Force stop");
                return;
            }
            int iM16553k1 = CLUtility.m16553k1(str5);
            int iM16494U0 = CLUtility.m16494U0(str5);
            if (iM16553k1 != -1 && iM16494U0 != -1) {
                if (iM16553k1 != iM16494U0) {
                    m15939k(iM16553k1, str, str5);
                    return;
                } else {
                    m15938j(m15937i(str5));
                    return;
                }
            }
            Log.d("GetAlbumRunnable", "[queryAlbumList] totalSize(" + iM16553k1 + ") or resultsSize(" + iM16494U0 + ") is expected value");
        }

        /* renamed from: f */
        public final FriendsClient m15936f() {
            return this.f13872f.get();
        }

        /* renamed from: i */
        public final List<GroupAlbumObj> m15937i(String str) throws JSONException {
            try {
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
                    ArrayList arrayList = new ArrayList();
                    for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i9);
                            try {
                            } catch (JSONException unused) {
                            }
                            try {
                                arrayList.add(new GroupAlbumObj(-1L, this.f13870d, jSONObject.getString("albumId"), jSONObject.getString("albumName"), jSONObject.getLong("lastModified") * 1000, jSONObject.getInt("numberOfMedia"), jSONObject.getString("creatorId"), jSONObject.getString("albumType")));
                            } catch (JSONException unused2) {
                                Log.e("BaseAlbumFragment", "[backup.listAlbum] groupInfo attribute parse error. JSONstr=" + str);
                            }
                        } catch (JSONException unused3) {
                            Log.e("BaseAlbumFragment", "[backup.listAlbum] groupAlbum parse error. JSONstr=" + str);
                        }
                    }
                    return arrayList;
                } catch (JSONException unused4) {
                    Log.e("BaseAlbumFragment", "[backup.listAlbum] 'results' missing. JSONstr=" + str);
                    return null;
                }
            } catch (JSONException unused5) {
                Log.e("BaseAlbumFragment", "[backup.listAlbum] Parse error. JSONstr=" + str);
                return null;
            }
        }

        /* renamed from: j */
        public final void m15938j(List<GroupAlbumObj> list) {
            if (list != null) {
                for (GroupAlbumObj groupAlbumObj : list) {
                    GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(groupAlbumObj.m14675b());
                    if (groupAlbumObjM15056i == null || !groupAlbumObjM15056i.m14681h().equals(groupAlbumObj.m14681h())) {
                        C2950b0.m14911j().m15053f(groupAlbumObj);
                    } else if (groupAlbumObjM15056i.m14682i() != groupAlbumObj.m14682i()) {
                        C2950b0.m14911j().m15061n(groupAlbumObj.m14675b(), groupAlbumObj, "NumberOfMedia");
                    }
                }
            }
            if (m24448b()) {
                Log.d("GetAlbumRunnable", "[processAlbumList] Force stop");
            } else {
                GroupAlbumFragment.this.m15900R0();
            }
        }

        /* renamed from: k */
        public final void m15939k(final int i9, String str, String str2) {
            if (str == null || str.isEmpty()) {
                Log.d("GetAlbumRunnable", "There is no token.");
                return;
            }
            final ArrayList arrayList = new ArrayList(m15937i(str2));
            if (m24448b()) {
                Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                return;
            }
            int iM16559m = CLUtility.m16559m(i9, 100);
            for (int i10 = 2; i10 <= iM16559m; i10++) {
                if (m24448b()) {
                    Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new C6301o("token", str));
                arrayList2.add(new C6301o("groupId", this.f13870d));
                arrayList2.add(new C6301o("pageIndex", String.valueOf(i10)));
                arrayList2.add(new C6301o("pageSize", String.valueOf(100)));
                FriendsClient friendsClientM15936f = m15936f();
                if (friendsClientM15936f == null) {
                    return;
                }
                friendsClientM15936f.m15734m(this.f13871e, "listAlbum", arrayList2, new FriendsClient.InterfaceC3051i() { // from class: u3.z
                    @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                    /* renamed from: a */
                    public final void mo134a(String str3, String str4, String str5, String str6) {
                        this.f21640a.m15934g(arrayList, i9, str3, str4, str5, str6);
                    }
                });
            }
        }

        /* renamed from: l */
        public final void m15940l() {
            Log.d("GetAlbumRunnable", "[queryAlbumList] in");
            final String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("groupId", this.f13870d));
            arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
            arrayList.add(new C6301o("pageSize", String.valueOf(100)));
            FriendsClient friendsClientM15936f = m15936f();
            if (friendsClientM15936f == null) {
                return;
            }
            friendsClientM15936f.m15734m(this.f13871e, "listAlbum", arrayList, new FriendsClient.InterfaceC3051i() { // from class: u3.y
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f21638a.m15935h(strM7449L, str, str2, str3, str4);
                }
            });
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d("GetAlbumRunnable", "[run] in");
            if (m24448b() || this.f13870d.isEmpty()) {
                Log.d("GetAlbumRunnable", "[run] Don't continue.");
            } else {
                m15940l();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A0 */
    public /* synthetic */ void m15827A0() {
        C3072a c3072a = this.f13838l;
        if (c3072a != null) {
            c3072a.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B0 */
    public /* synthetic */ void m15829B0() {
        this.f13849w.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m15854k0(String str, String str2, String str3, String str4, String str5, String str6) {
        m15918i0(C5172p.m20180b(str, str6), C5172p.m20179a(str2, str6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l0 */
    public /* synthetic */ void m15856l0(String str, String str2, String str3, String str4, String str5) throws JSONException {
        boolean z8 = false;
        try {
            JSONObject jSONObject = new JSONObject(str5).getJSONObject("userBackup");
            if (jSONObject.optInt("currentAlbumCount", 0) >= jSONObject.optInt("maxAlbumLimit", CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)) {
                z8 = true;
            }
        } catch (Exception e9) {
            Log.e("BaseAlbumFragment", "isReachAlbumLimit exception" + e9);
        }
        m15918i0(z8, C5172p.m20179a(str, str5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m15858m0() {
        ProgressDialog progressDialog;
        if (this.f13836j.isFinishing() || (progressDialog = this.f13848v) == null || !progressDialog.isShowing()) {
            return;
        }
        this.f13848v.dismiss();
        this.f13848v = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n0 */
    public /* synthetic */ void m15860n0() {
        m15891I0(GroupAlbumStatus.Normal_Case);
        this.f13838l.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01af  */
    /* renamed from: o0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ void m15862o0(long[] jArr, String str, boolean z8, GroupAlbumObj groupAlbumObj, String str2, String str3, String str4, String str5) throws JSONException {
        int i9;
        JSONArray jSONArrayM20196r;
        C2973l0 c2973l0M15129a;
        Activity activity;
        if (!"200".equals(str4)) {
            if (str4 == null) {
                Log.d("BaseAlbumFragment", "[copyMedia] Response is null");
            } else {
                Log.d("BaseAlbumFragment", "[copyMedia] statusCode = " + str4);
            }
            if ("403".equals(str4) && str5.contains("Max media limit exceeded")) {
                C5187v0.m20267c(R.string.reach_photo_limit);
            }
            m15906W();
            return;
        }
        Log.d("BaseAlbumFragment", "Copy Media Success");
        if (this.f13851y.equals(GroupAlbumStatus.Select_Case)) {
            if (m15917h0().equals(SelectDataType.External_Media_Select)) {
                this.f13835i = "ULauncherActivity";
            } else if (m15917h0().equals(SelectDataType.Internal_Media_Select)) {
                this.f13835i = "";
            }
        }
        int length = jArr.length;
        if (length > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str5);
                ArrayList arrayList = new ArrayList();
                int length2 = jArr.length;
                int i10 = 0;
                C2973l0 c2973l0 = null;
                while (i10 < length2) {
                    JSONObject jSONObject2 = jSONObject;
                    long j9 = jArr[i10];
                    i9 = length;
                    try {
                        long j10 = jSONObject2.getLong(String.valueOf(j9));
                        if (j10 > 0) {
                            arrayList.add(Long.valueOf(j10));
                        }
                        C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                        if (c2973l0M14725v != null && (c2973l0M15129a = c2973l0M14725v.m15129a(str, j10, z8)) != null) {
                            c2973l0M15129a.m15149u().f13201e = "";
                            c2973l0M15129a.m15148t().f13201e = "";
                            C2950b0.m14914m().m14712i(c2973l0M15129a);
                            if (c2973l0 == null) {
                                c2973l0 = c2973l0M15129a;
                            }
                        }
                        i10++;
                        length = i9;
                        jSONObject = jSONObject2;
                    } catch (JSONException e9) {
                        e = e9;
                        Log.d("BaseAlbumFragment", Log.getStackTraceString(e));
                        m15906W();
                        activity = this.f13836j;
                        if ((activity == null ? activity.getIntent() : null) != null) {
                            return;
                        } else {
                            return;
                        }
                    }
                }
                i9 = length;
                JSONObject jSONObject3 = jSONObject;
                if (arrayList.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(new C6301o("mediaId", String.valueOf(((Long) it.next()).longValue())));
                    }
                    Pair<String, String> pairM15731j = this.f13842p.m15731j("media", "mediaInfo", arrayList2);
                    if ("200".equals((String) pairM15731j.first) && (jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second)) != null) {
                        for (int i11 = 0; i11 < jSONArrayM20196r.length(); i11++) {
                            try {
                                C2973l0 c2973l0M20188j = C5172p.m20188j(str, jSONArrayM20196r.getJSONObject(i11));
                                if (c2973l0M20188j != null) {
                                    C2950b0.m14914m().m14712i(c2973l0M20188j);
                                }
                            } catch (JSONException e10) {
                                Log.e("BaseAlbumFragment", "", e10);
                            }
                        }
                    }
                }
                Group group = this.f13844r;
                if (group != null && c2973l0 != null) {
                    C2925v.m14630x0(group, c2973l0, String.valueOf(i9));
                }
                groupAlbumObj.m14685l(groupAlbumObj.m14682i() + jSONObject3.length());
                C2950b0.m14911j().m15061n(groupAlbumObj.m14675b(), groupAlbumObj, "NumberOfMedia");
                Activity activity2 = this.f13836j;
                if (activity2 != null) {
                    activity2.runOnUiThread(new Runnable() { // from class: u3.h
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f21610b.m15860n0();
                        }
                    });
                }
            } catch (JSONException e11) {
                e = e11;
                i9 = length;
            }
        } else {
            i9 = length;
        }
        m15906W();
        activity = this.f13836j;
        if ((activity == null ? activity.getIntent() : null) != null || this.f13836j == null) {
            return;
        }
        if (this.f13832f || this.f13833g) {
            Intent intent = new Intent();
            intent.putExtra(this.f13832f ? "ShareToGroupAlbumOkCnt" : "CopyToMyAlbumOkCnt", i9);
            this.f13836j.setResult(-1, intent);
            this.f13836j.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m15864p0(long[] jArr, String str, String str2, String str3, String str4) {
        m15906W();
        if (isAdded()) {
            if (str3 == null) {
                Log.d("BaseAlbumFragment", "Response is null");
                C5187v0.m20267c(R.string.error_server_response);
                return;
            }
            if (!str3.equals("200")) {
                if (str3.equals("403") && str4.contains("Max album limit exceeded")) {
                    C5187v0.m20267c(R.string.reach_album_limit);
                } else {
                    C5187v0.m20267c(R.string.error_server_response);
                }
                Log.d("BaseAlbumFragment", "statusCode=" + str3);
                return;
            }
            GroupAlbumObj groupAlbumObjM20185g = C5172p.m20185g(C5172p.m20195q(str4), this.f13845s);
            if (groupAlbumObjM20185g == null) {
                Log.d("BaseAlbumFragment", "[createAlbum] groupAlbumObj is null");
                return;
            }
            String strM14675b = groupAlbumObjM20185g.m14675b();
            if (strM14675b == null || strM14675b.isEmpty()) {
                Log.d("BaseAlbumFragment", "[createAlbum] Album id is null or empty.");
                return;
            }
            C2950b0.m14911j().m15053f(groupAlbumObjM20185g);
            m15900R0();
            m15891I0(GroupAlbumStatus.Normal_Case);
            m15907X(groupAlbumObjM20185g, jArr, this.f13831e);
        }
    }

    /* renamed from: q0 */
    public static /* synthetic */ void m15866q0(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m15868r0() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(getActivity());
        builderM16382a.setMessage(R.string.reach_album_limit);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: u3.k
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                GroupAlbumFragment.m15866q0(dialogInterface, i9);
            }
        });
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.requestWindowFeature(1);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m15870s0(ArrayList arrayList, String str, String str2, String str3, String str4) {
        if (isAdded()) {
            m15906W();
            if (str3 == null) {
                Log.d("BaseAlbumFragment", "Response is null");
                C5187v0.m20267c(R.string.error_server_response);
                return;
            }
            if (!str3.equals("200")) {
                if (str3.equals("403") && str4.contains("Max album limit exceeded")) {
                    Activity activity = this.f13836j;
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() { // from class: u3.f
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f21608b.m15868r0();
                            }
                        });
                    }
                } else {
                    C5187v0.m20267c(R.string.error_server_response);
                }
                Log.d("BaseAlbumFragment", "statusCode=" + str3);
                return;
            }
            GroupAlbumObj groupAlbumObjM20185g = C5172p.m20185g(C5172p.m20195q(str4), this.f13845s);
            if (groupAlbumObjM20185g == null) {
                Log.d("BaseAlbumFragment", "[createAlbum] groupAlbumObj is null");
                return;
            }
            String strM14675b = groupAlbumObjM20185g.m14675b();
            if (TextUtils.isEmpty(strM14675b)) {
                Log.d("BaseAlbumFragment", "[createAlbum] Album id is null or empty.");
                return;
            }
            C2950b0.m14911j().m15053f(groupAlbumObjM20185g);
            m15900R0();
            if (arrayList != null) {
                m15905V0(strM14675b, arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t0 */
    public /* synthetic */ void m15872t0(GroupAlbumObj groupAlbumObj, String str, String str2, String str3, String str4) throws JSONException {
        if (str3 == null) {
            Log.d("BaseAlbumFragment", "[media.listMedia] Response is null");
            return;
        }
        if (str3.equals("200")) {
            CLUtility.m16501W1(groupAlbumObj.m14675b(), str4);
            m15899Q0();
        } else {
            Log.d("BaseAlbumFragment", "[media.listMedia] statusCode=" + str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m15874u0() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(getActivity());
        builderM16382a.setMessage(R.string.reach_album_limit);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: u3.j
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                GroupAlbumFragment.m15876v0(dialogInterface, i9);
            }
        });
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.requestWindowFeature(1);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
    }

    /* renamed from: v0 */
    public static /* synthetic */ void m15876v0(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w0 */
    public /* synthetic */ void m15878w0(View view) {
        m15886D0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ void m15880x0() {
        Log.d("BaseAlbumFragment", "[onConnected] in");
        if (isResumed()) {
            m15911b0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m15882y0(GroupAlbumObj groupAlbumObj) {
        C3072a c3072a = this.f13838l;
        if (c3072a != null) {
            c3072a.m15949h(groupAlbumObj.m14675b());
            this.f13838l.notifyDataSetChanged();
            if (this.f13838l.getCount() == 0) {
                m15902T0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z0 */
    public /* synthetic */ void m15884z0() {
        ProgressDialog progressDialog = this.f13848v;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.f13848v = null;
        }
        this.f13848v = ProgressDialog.show(this.f13836j, "", getString(R.string.loading), true);
    }

    /* renamed from: C0 */
    public void m15885C0(GroupAlbumObj groupAlbumObj) {
        long[] longArrayExtra;
        Activity activity = this.f13836j;
        Intent intent = activity != null ? activity.getIntent() : null;
        if (intent == null || (longArrayExtra = intent.getLongArrayExtra("share_media_id")) == null) {
            return;
        }
        m15891I0(GroupAlbumStatus.Normal_Case);
        m15907X(groupAlbumObj, longArrayExtra, this.f13831e);
    }

    /* renamed from: D0 */
    public void m15886D0() {
        if (this.f13836j == null) {
            return;
        }
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C3067e(permission), this.f13836j);
    }

    /* renamed from: E0 */
    public void m15887E0() {
        Intent intent = new Intent(getActivity(), (Class<?>) CreateAlbumActivity.class);
        if (this.f13851y.equals(GroupAlbumStatus.Select_Case)) {
            Activity activity = this.f13836j;
            Intent intent2 = activity != null ? activity.getIntent() : null;
            if (intent2 == null) {
                return;
            }
            long[] longArrayExtra = intent2.getLongArrayExtra("share_media_id");
            if (longArrayExtra != null) {
                intent.putExtra("import_mediaIds", longArrayExtra);
                intent.putExtra("is_mediaOjb", true);
            } else {
                intent.putExtra("import_images", m15915f0(intent2));
                intent.putExtra("is_mediaOjb", false);
            }
            intent.putStringArrayListExtra("album_name_list", m15913d0());
            if (m15917h0().equals(SelectDataType.Internal_Media_Select)) {
                startActivityForResult(intent, 4);
            } else if (m15917h0().equals(SelectDataType.External_Media_Select)) {
                startActivityForResult(intent, 5);
            }
        }
    }

    /* renamed from: F0 */
    public String m15888F0() {
        return this.f13835i;
    }

    /* renamed from: G0 */
    public SelectDataType m15889G0() {
        return m15917h0();
    }

    /* renamed from: H0 */
    public GroupAlbumStatus m15890H0() {
        return this.f13851y;
    }

    /* renamed from: I0 */
    public void m15891I0(GroupAlbumStatus groupAlbumStatus) {
        this.f13851y = groupAlbumStatus;
    }

    /* renamed from: J0 */
    public void m15892J0() {
        m15900R0();
    }

    /* renamed from: K0 */
    public void m15893K0(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        ArrayList<ImageItem> arrayList = new ArrayList<>();
        if (this.f13851y.equals(GroupAlbumStatus.Select_Case)) {
            Activity activity = this.f13836j;
            Intent intent = activity != null ? activity.getIntent() : null;
            if (intent != null && intent.getParcelableArrayListExtra("android.intent.extra.STREAM") != null) {
                arrayList = m15915f0(intent);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        m15905V0(str, arrayList);
    }

    /* renamed from: L0 */
    public final void m15894L0() {
        XMPPManager.m14184g0().m14211K(this.f13823B);
        C6456d.m24714D().m24744B(this.f13824C);
    }

    /* renamed from: M0 */
    public final void m15895M0(List<GroupAlbumObj> list) {
        C3072a c3072a = this.f13838l;
        if (c3072a == null || list == null) {
            return;
        }
        c3072a.clear();
        if (this.f13834h) {
            ArrayList arrayList = new ArrayList();
            for (GroupAlbumObj groupAlbumObj : list) {
                if (groupAlbumObj.m14677d().equals("Product")) {
                    this.f13838l.add(groupAlbumObj);
                } else {
                    arrayList.add(groupAlbumObj);
                }
            }
            this.f13838l.addAll(arrayList);
        } else {
            this.f13838l.addAll(list);
        }
        this.f13838l.notifyDataSetChanged();
        m15903U0(false);
    }

    /* renamed from: N0 */
    public final void m15896N0() {
        Activity activity = this.f13836j;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: u3.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f21607b.m15884z0();
            }
        });
    }

    /* renamed from: O0 */
    public final void m15897O0() {
        XMPPManager.m14184g0().m14235a1(this.f13823B);
        C6456d.m24714D().m24753M(this.f13824C);
    }

    /* renamed from: P0 */
    public final void m15898P0() {
        new AsyncTaskC3066d().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: Q0 */
    public final void m15899Q0() {
        Activity activity = this.f13836j;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: u3.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f21612b.m15827A0();
            }
        });
    }

    /* renamed from: R0 */
    public final void m15900R0() {
        Activity activity = this.f13836j;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: u3.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f21605b.m15898P0();
            }
        });
    }

    /* renamed from: S0 */
    public final void m15901S0() {
        if (this.f13836j instanceof ULauncherActivity) {
            boolean zM14204A0 = XMPPManager.m14184g0().m14204A0();
            if (!zM14204A0 && this.f13836j != null) {
                if (C6456d.m24714D().m24748G()) {
                    if (!Globals.m7388i0().m7534d2()) {
                        zM14204A0 = true;
                    }
                    this.f13839m.setText(this.f13836j.getResources().getString(R.string.connecting));
                } else {
                    this.f13839m.setText(this.f13836j.getResources().getString(R.string.error_no_network));
                }
            }
            this.f13839m.setVisibility(zM14204A0 ? 4 : 0);
        }
    }

    /* renamed from: T0 */
    public final void m15902T0() {
        if (this.f13838l.getCount() > 0) {
            m15910a0(this.f13840n, false);
        } else {
            m15910a0(this.f13840n, true);
        }
    }

    /* renamed from: U0 */
    public final void m15903U0(boolean z8) {
        C3072a c3072a = this.f13838l;
        if (c3072a == null || c3072a.getCount() == 0) {
            return;
        }
        int iMax = Math.max((z8 ? this.f13837k.getFirstVisiblePosition() : 0) - this.f13837k.getHeaderViewsCount(), 0);
        int iMin = Math.min(iMax + 3, this.f13838l.getCount() - 1);
        while (iMax <= iMin) {
            if (iMax <= this.f13838l.getCount() - 1) {
                m15916g0((GroupAlbumObj) this.f13838l.getItem(iMax));
            }
            iMax++;
        }
    }

    /* renamed from: V */
    public final void m15904V() {
        Group group = this.f13844r;
        if (group == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            final String str = "User:new";
            arrayList.add(new C6301o("albumId", "User:new"));
            arrayList.add(new C6301o("showUserBackup", String.valueOf(true)));
            this.f13842p.m15734m("media", "queryUsage", arrayList, new FriendsClient.InterfaceC3051i() { // from class: u3.r
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str2, String str3, String str4, String str5) throws JSONException {
                    this.f21622a.m15856l0(str, str2, str3, str4, str5);
                }
            });
            return;
        }
        final String strValueOf = String.valueOf(group.f13727n);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
        final String str2 = "Group:new";
        arrayList2.add(new C6301o("albumId", "Group:new"));
        arrayList2.add(new C6301o("groupId", strValueOf));
        this.f13842p.m15734m("media", "queryUsage", arrayList2, new FriendsClient.InterfaceC3051i() { // from class: u3.q
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str3, String str4, String str5, String str6) {
                this.f21619a.m15854k0(strValueOf, str2, str3, str4, str5, str6);
            }
        });
    }

    /* renamed from: V0 */
    public final void m15905V0(String str, List<ImageItem> list) {
        Activity activity;
        ImageItem imageItem;
        ImageItem imageItem2;
        JSONException e9;
        if (str == null || str.isEmpty() || list == null || list.isEmpty()) {
            return;
        }
        if (this.f13850x == null) {
            C3199c c3199c = new C3199c();
            this.f13850x = c3199c;
            c3199c.m17033C(this.f13826E);
        }
        for (ImageItem imageItem3 : list) {
            if (imageItem3 == null || (TextUtils.isEmpty(imageItem3.m16139l()) && TextUtils.isEmpty(imageItem3.m16140m()))) {
                imageItem = imageItem3;
            } else {
                try {
                    imageItem2 = new ImageItem(new JSONObject(imageItem3.m16127H()));
                    try {
                        imageItem2.m16122C(CLUtility.m16540h0());
                        imageItem2.m16124E(imageItem3.m16139l());
                        imageItem2.m16125F(imageItem3.m16145r());
                    } catch (JSONException e10) {
                        e9 = e10;
                        Log.d("BaseAlbumFragment", Log.getStackTraceString(e9));
                        imageItem = imageItem2;
                        if (imageItem == null) {
                        }
                        if (imageItem != null) {
                        }
                        this.f13850x.m17039q(str, imageItem, null, null, null, "0");
                    }
                } catch (JSONException e11) {
                    imageItem2 = imageItem3;
                    e9 = e11;
                }
                imageItem = imageItem2;
            }
            if (imageItem == null && imageItem.m16134g() != null && !imageItem.m16134g().isEmpty()) {
                this.f13850x.m17039q(str, imageItem, null, null, imageItem.m16134g(), "0");
            } else if (imageItem != null || imageItem.m16129b() == null || imageItem.m16129b().isEmpty()) {
                this.f13850x.m17039q(str, imageItem, null, null, null, "0");
            } else {
                this.f13850x.m17039q(str, imageItem, CLUtility.m16497V0(this.f13836j).f13787l, imageItem.m16129b(), null, imageItem.m16128a());
            }
        }
        if (!this.f13849w.isShowing() && (activity = this.f13836j) != null) {
            activity.runOnUiThread(new Runnable() { // from class: u3.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f21606b.m15829B0();
                }
            });
        }
        this.f13850x.m17044v();
        this.f13850x.m17035F();
    }

    /* renamed from: W */
    public final void m15906W() {
        Activity activity = this.f13836j;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: u3.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f21609b.m15858m0();
            }
        });
    }

    /* renamed from: X */
    public final void m15907X(final GroupAlbumObj groupAlbumObj, final long[] jArr, final boolean z8) {
        if (groupAlbumObj == null) {
            Log.d("BaseAlbumFragment", "[copyMedia] GroupAlbumObj is null.");
            return;
        }
        final String strM14675b = groupAlbumObj.m14675b();
        if (strM14675b == null || strM14675b.isEmpty()) {
            Log.d("BaseAlbumFragment", "[copyMedia] Album id is null or empty.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        for (long j9 : jArr) {
            arrayList.add(new C6301o("mediaId", String.valueOf(j9)));
        }
        arrayList.add(new C6301o("albumId", strM14675b));
        arrayList.add(new C6301o("includeComments", String.valueOf(z8)));
        m15896N0();
        this.f13842p.m15734m("media", "copyMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: u3.t
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f21626a.m15862o0(jArr, strM14675b, z8, groupAlbumObj, str, str2, str3, str4);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* renamed from: Y */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m15908Y(String str, final long[] jArr) {
        String str2;
        m15910a0(this.f13840n, false);
        String str3 = this.f13845s;
        if (str3 != null) {
            str2 = str3.equals("backup") ? "backup" : "group";
        }
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("groupId", this.f13845s));
        arrayList.add(new C6301o("albumName", str));
        m15896N0();
        this.f13842p.m15734m(str2, "createAlbum", arrayList, new FriendsClient.InterfaceC3051i() { // from class: u3.p
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str4, String str5, String str6, String str7) {
                this.f21617a.m15864p0(jArr, str4, str5, str6, str7);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* renamed from: Z */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m15909Z(String str, final ArrayList<ImageItem> arrayList) {
        String str2;
        m15910a0(this.f13840n, false);
        String str3 = this.f13845s;
        if (str3 != null) {
            str2 = str3.equals("backup") ? "backup" : "group";
        }
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new C6301o("token", strM7449L));
        arrayList2.add(new C6301o("groupId", this.f13845s));
        arrayList2.add(new C6301o("albumName", str));
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: u3.s
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str4, String str5, String str6, String str7) {
                this.f21624a.m15870s0(arrayList, str4, str5, str6, str7);
            }
        };
        if (this.f13842p != null) {
            m15896N0();
            this.f13842p.m15734m(str2, "createAlbum", arrayList2, interfaceC3051i);
        }
    }

    /* renamed from: a0 */
    public final void m15910a0(View view, boolean z8) {
        if (view == null) {
            return;
        }
        Group group = this.f13844r;
        if (group != null && (group.f13738y.equals("Official") || this.f13844r.f13738y.equals("Corporate"))) {
            view.setVisibility(8);
        } else if (z8) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    /* renamed from: b0 */
    public final void m15911b0() {
        Log.d("BaseAlbumFragment", "[getAlbumAsync] in");
        try {
            C3071i c3071i = this.f13846t;
            if (c3071i != null) {
                c3071i.m24447a();
            }
            C3071i c3071i2 = new C3071i(this.f13845s);
            this.f13846t = c3071i2;
            this.f13847u.execute(c3071i2);
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        Log.d("BaseAlbumFragment", "[getAlbumAsync] out");
    }

    /* renamed from: c0 */
    public final List<GroupAlbumObj> m15912c0() {
        List<GroupAlbumObj> listM15058k;
        synchronized (this.f13843q) {
            listM15058k = C2950b0.m14911j().m15058k(this.f13845s);
        }
        return listM15058k;
    }

    /* renamed from: d0 */
    public final ArrayList<String> m15913d0() {
        List<GroupAlbumObj> listM15950i;
        ArrayList<String> arrayList = new ArrayList<>();
        C3072a c3072a = this.f13838l;
        if (c3072a != null && (listM15950i = c3072a.m15950i()) != null) {
            Iterator<GroupAlbumObj> it = listM15950i.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().m14676c());
            }
        }
        return arrayList;
    }

    /* renamed from: e0 */
    public GroupAlbumStatus m15914e0() {
        return this.f13851y;
    }

    /* renamed from: f0 */
    public final ArrayList<ImageItem> m15915f0(Intent intent) {
        ArrayList<ImageItem> arrayList = new ArrayList<>();
        try {
            ArrayList arrayList2 = (ArrayList) intent.getSerializableExtra("shared_external_imageitem");
            if (arrayList2 != null && !arrayList2.isEmpty()) {
                arrayList.addAll(arrayList2);
                return arrayList;
            }
            ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            if (parcelableArrayListExtra == null || this.f13836j == null) {
                return arrayList;
            }
            Iterator it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                Uri uri = (Uri) it.next();
                File file = new File(CLUtility.m16576q0(this.f13836j, uri));
                ImageItem imageItemM16428D0 = file.exists() ? CLUtility.m16428D0(this.f13836j, file.getPath()) : null;
                if (imageItemM16428D0 == null) {
                    imageItemM16428D0 = CLUtility.m16432E0(this.f13836j, uri);
                }
                if (imageItemM16428D0 != null) {
                    imageItemM16428D0.m16151x(true);
                    arrayList.add(imageItemM16428D0);
                }
            }
            return arrayList;
        } catch (Exception e9) {
            e9.printStackTrace();
            return new ArrayList<>();
        }
    }

    /* renamed from: g0 */
    public final void m15916g0(final GroupAlbumObj groupAlbumObj) {
        if (this.f13842p == null) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", groupAlbumObj.m14675b()));
        arrayList.add(new C6301o("pageIndex", "1"));
        arrayList.add(new C6301o("pageSize", "8"));
        this.f13842p.m15734m("media", "listMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: u3.b
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f21590a.m15872t0(groupAlbumObj, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: h0 */
    public final SelectDataType m15917h0() {
        if (!this.f13851y.equals(GroupAlbumStatus.Select_Case)) {
            return SelectDataType.None_Select;
        }
        Activity activity = this.f13836j;
        Intent intent = activity != null ? activity.getIntent() : null;
        return (intent == null || intent.getLongArrayExtra("share_media_id") == null) ? SelectDataType.External_Media_Select : SelectDataType.Internal_Media_Select;
    }

    /* renamed from: i0 */
    public final void m15918i0(boolean z8, int i9) {
        if (z8) {
            Activity activity = this.f13836j;
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: u3.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21611b.m15874u0();
                    }
                });
                return;
            }
            return;
        }
        Intent intent = new Intent(this.f13836j, (Class<?>) PhotoImportActivity.class);
        intent.putExtra("isImportedToAlbums", true);
        intent.putExtra("selectLimitCount", i9);
        startActivityForResult(intent, 1);
    }

    /* renamed from: j0 */
    public final void m15919j0() {
        new AsyncTaskC3065c().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        GroupAlbumObj groupAlbumObjM15951j;
        super.onActivityResult(i9, i10, intent);
        if (this.f13836j == null) {
            return;
        }
        if (i9 == 0) {
            if (i10 == -1) {
                int intExtra = intent.getIntExtra("operationResult", 0);
                final GroupAlbumObj groupAlbumObj = (GroupAlbumObj) intent.getParcelableExtra("album");
                if (groupAlbumObj != null) {
                    if ((intExtra & 1) > 0) {
                        this.f13836j.runOnUiThread(new Runnable() { // from class: u3.m
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f21613b.m15882y0(groupAlbumObj);
                            }
                        });
                        return;
                    }
                    if ((intExtra & 2) > 0) {
                        C3072a c3072a = this.f13838l;
                        if (c3072a != null && (groupAlbumObjM15951j = c3072a.m15951j(groupAlbumObj.m14675b())) != null) {
                            GroupAlbumObj.m14674a(groupAlbumObjM15951j, groupAlbumObj);
                        }
                        m15899Q0();
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (i9 == 1) {
            if (i10 == -1) {
                ArrayList arrayList = (ArrayList) intent.getExtras().getSerializable("import_images");
                Intent intent2 = new Intent(this.f13836j, (Class<?>) CreateAlbumActivity.class);
                intent2.putExtra("import_images", arrayList);
                intent2.putStringArrayListExtra("album_name_list", m15913d0());
                startActivityForResult(intent2, 2);
                return;
            }
            return;
        }
        if (i9 != 2) {
            if (i9 == 3) {
                if (i10 == -1) {
                    Bundle extras = intent.getExtras();
                    if (!extras.getBoolean("isImport")) {
                        ArrayList arrayList2 = (ArrayList) extras.getSerializable("import_images");
                        Intent intent3 = new Intent(this.f13836j, (Class<?>) CreateAlbumActivity.class);
                        intent3.putExtra("import_images", arrayList2);
                        startActivityForResult(intent3, 2);
                        return;
                    }
                    String string = extras.getString("albumId");
                    ArrayList arrayList3 = (ArrayList) extras.getSerializable("import_images");
                    if (string == null || arrayList3 == null) {
                        return;
                    }
                    m15905V0(string, arrayList3);
                    return;
                }
                return;
            }
            if (i9 == 4) {
                if (i10 == -1) {
                    String stringExtra = intent.getStringExtra("create_album_name");
                    if (intent.getBooleanExtra("isImport", false) && intent.getBooleanExtra("is_mediaOjb", false)) {
                        m15908Y(stringExtra, intent.getLongArrayExtra("import_mediaIds"));
                        if (this.f13844r != null) {
                            Intent intent4 = new Intent(getActivity(), (Class<?>) ChatDialogActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("Group", this.f13844r);
                            intent4.putExtras(bundle);
                            startActivity(intent4);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (i9 != 5) {
                return;
            }
        }
        if (i10 == -1) {
            Bundle extras2 = intent.getExtras();
            if (!extras2.getBoolean("isImport")) {
                try {
                    Intent intent5 = new Intent(this.f13836j.getBaseContext(), (Class<?>) PhotoImportActivity.class);
                    intent5.putExtra("isImportedToAlbums", true);
                    startActivityForResult(intent5, 1);
                    return;
                } catch (Exception e9) {
                    C5154j.m20076j(e9);
                    return;
                }
            }
            if (extras2.getInt("selectPhotoOpType") == 1) {
                String string2 = extras2.getString("create_album_name");
                if (string2.trim().isEmpty()) {
                    C5187v0.m20267c(R.string.input_album_name);
                } else {
                    m15909Z(string2, (ArrayList) extras2.getSerializable("import_images"));
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f13836j = activity;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Log.v("BaseAlbumFragment", "[onCreateView] in");
        View viewInflate = layoutInflater.inflate(R.layout.fragment_group_album, viewGroup, false);
        Activity activity = this.f13836j;
        Intent intent = activity != null ? activity.getIntent() : null;
        this.f13851y = (intent == null || !intent.getBooleanExtra("select", false)) ? GroupAlbumStatus.Normal_Case : GroupAlbumStatus.Select_Case;
        this.f13831e = intent != null && intent.getBooleanExtra("withComment", false);
        this.f13844r = intent != null ? (Group) intent.getParcelableExtra("Group") : null;
        this.f13832f = intent != null && intent.getBooleanExtra("ShareToGroupAlbum", false);
        boolean z8 = intent != null && intent.getBooleanExtra("CopyToMyAlbum", false);
        this.f13833g = z8;
        if (z8) {
            this.f13845s = "backup";
        } else {
            Group group = this.f13844r;
            this.f13845s = group != null ? String.valueOf(group.f13727n) : "backup";
        }
        if ("backup".equals(this.f13845s)) {
            ((TextView) viewInflate.findViewById(R.id.PhotoListEmptyTextView)).setText(R.string.private_album_description);
        }
        ListView listView = (ListView) viewInflate.findViewById(R.id.AlbumListView);
        this.f13837k = listView;
        listView.setOnScrollListener(this.f13827F);
        this.f13837k.setOnItemClickListener(this.f13852z);
        if (this.f13836j != null) {
            C3072a c3072a = new C3072a(this.f13836j, R.layout.view_item_group_album, new ArrayList());
            this.f13838l = c3072a;
            c3072a.m15953m(this);
        }
        this.f13837k.setAdapter((ListAdapter) this.f13838l);
        this.f13839m = (TextView) viewInflate.findViewById(R.id.noConnectionText);
        this.f13840n = viewInflate.findViewById(R.id.GroupAlbumEmptyView);
        this.f13841o = getActivity().findViewById(R.id.ULauncherMyAlbumEmptyView);
        this.f13830d = false;
        Activity activity2 = this.f13836j;
        if (activity2 != null) {
            if (activity2 instanceof ULauncherActivity) {
                ((ULauncherActivity) activity2).mo12937q0(this.f13822A, PageType.MyAlbums);
                this.f13845s = "backup";
                this.f13840n = this.f13841o;
            }
            DialogC6459g dialogC6459g = new DialogC6459g(this.f13836j, R.style.FriendSelectorDialog);
            this.f13849w = dialogC6459g;
            dialogC6459g.m24770m(this.f13825D);
        }
        m15894L0();
        m15919j0();
        this.f13842p = new FriendsClient(true);
        if (FriendsClient.m15680p0()) {
            m15911b0();
        }
        stopWatch.stop();
        Log.d("BaseAlbumFragment", "[onCreateView] out : " + stopWatch.getTime() + " ms");
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        ProgressDialog progressDialog = this.f13848v;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.f13848v.dismiss();
            this.f13848v = null;
        }
        C5152i0.m20065b(this.f13849w);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f13830d = true;
        C3071i c3071i = this.f13846t;
        if (c3071i != null) {
            c3071i.m24447a();
            this.f13846t = null;
        }
        FriendsClient friendsClient = this.f13842p;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        this.f13829c = false;
        m15897O0();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.f13836j = null;
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        Log.v("BaseAlbumFragment", "[onPause] in");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        super.onPause();
        m15910a0(this.f13840n, false);
        stopWatch.stop();
        Log.v("BaseAlbumFragment", "[onPause] out: " + stopWatch.getTime() + " ms");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        Log.v("BaseAlbumFragment", "[onResume] in");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        super.onResume();
        if (this.f13829c) {
            m15902T0();
        }
        m15899Q0();
        m15901S0();
        stopWatch.stop();
        Log.v("BaseAlbumFragment", "[onResume] out : " + stopWatch.getTime() + " ms");
    }
}
