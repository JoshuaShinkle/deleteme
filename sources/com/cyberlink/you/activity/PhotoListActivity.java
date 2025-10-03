package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.PhotoListActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.MediaDao;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.C3062b;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.PhotoImportActivity;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p105j3.C5094a;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p136m3.C5321e;
import p201t3.C6301o;
import p209u2.C6385v;
import p210u3.C6391a0;
import p218v2.C6456d;
import p218v2.DialogC6459g;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class PhotoListActivity extends BaseFragmentActivity {

    /* renamed from: s */
    public static GroupAlbumObj f8431s;

    /* renamed from: u */
    public static MyStatus f8433u;

    /* renamed from: w */
    public static TextView f8435w;

    /* renamed from: x */
    public static View f8436x;

    /* renamed from: y */
    public static View f8437y;

    /* renamed from: z */
    public static Group f8438z;

    /* renamed from: e */
    public TextView f8441e;

    /* renamed from: f */
    public Dialog f8442f;

    /* renamed from: i */
    public TextView f8445i;

    /* renamed from: t */
    public static Boolean f8432t = Boolean.FALSE;

    /* renamed from: v */
    public static int f8434v = 0;

    /* renamed from: c */
    public DialogC6459g f8439c = null;

    /* renamed from: d */
    public C1629g f8440d = null;

    /* renamed from: g */
    public C3199c f8443g = null;

    /* renamed from: h */
    public boolean f8444h = false;

    /* renamed from: j */
    public View.OnClickListener f8446j = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ba
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9745b.m9186Z1(view);
        }
    };

    /* renamed from: k */
    public final View.OnClickListener f8447k = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ca
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9778b.m9188a2(view);
        }
    };

    /* renamed from: l */
    public View.OnClickListener f8448l = new View.OnClickListener() { // from class: com.cyberlink.you.activity.da
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10368b.m9190b2(view);
        }
    };

    /* renamed from: m */
    public View.OnClickListener f8449m = new ViewOnClickListenerC1624b();

    /* renamed from: n */
    public C3199c.b f8450n = new C1625c();

    /* renamed from: o */
    public DialogC6459g.a f8451o = new DialogC6459g.a() { // from class: com.cyberlink.you.activity.ea
        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public final void mo7918a() {
            this.f10401a.m9192c2();
        }
    };

    /* renamed from: p */
    public C2907m0.h f8452p = new C2907m0.h() { // from class: com.cyberlink.you.activity.fa
        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public final void mo119x(boolean z8) {
            this.f10436b.m9194d2(z8);
        }
    };

    /* renamed from: q */
    public XMPPManager.AbstractC2868s f8453q = new C1626d(false);

    /* renamed from: r */
    public C2907m0.g f8454r = new C2907m0.g() { // from class: com.cyberlink.you.activity.ga
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f10690b.m9236q2();
        }
    };

    public enum MyStatus {
        Normal,
        Select_Share,
        Select_Share_With_Comment,
        Select_Save,
        Select_Delete,
        Select_ShareToMyAlbum,
        Select_ShareToGroupAlbum
    }

    /* renamed from: com.cyberlink.you.activity.PhotoListActivity$a */
    public class C1623a implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f8463a;

        public C1623a(Permission permission) {
            this.f8463a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(PhotoListActivity.this, this.f8463a);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            if (PhotoListActivity.m9164O1()) {
                PhotoListActivity.this.m9226G1();
            } else {
                PhotoListActivity.this.m9225F1();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoListActivity$b */
    public class ViewOnClickListenerC1624b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$b$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ Permission f8466a;

            public a(Permission permission) {
                this.f8466a = permission;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(PhotoListActivity.this, this.f8466a);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                MyStatus unused = PhotoListActivity.f8433u = MyStatus.Select_Save;
                PhotoListActivity.this.f8440d.m9319N();
                PhotoListActivity.f8436x.setVisibility(0);
                PhotoListActivity.this.f8440d.m9334s0();
            }
        }

        public ViewOnClickListenerC1624b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j */
        public /* synthetic */ void m9247j(View view) {
            PhotoListActivity.this.f8442f.dismiss();
            MyStatus unused = PhotoListActivity.f8433u = MyStatus.Select_ShareToMyAlbum;
            PhotoListActivity.this.f8440d.m9319N();
            PhotoListActivity.f8436x.setVisibility(0);
            PhotoListActivity.this.f8440d.m9334s0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void m9248k(View view) {
            PhotoListActivity.this.f8442f.dismiss();
            MyStatus unused = PhotoListActivity.f8433u = MyStatus.Select_Share;
            PhotoListActivity.this.f8440d.m9319N();
            PhotoListActivity.f8436x.setVisibility(0);
            PhotoListActivity.this.f8440d.m9334s0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l */
        public /* synthetic */ void m9249l(View view) {
            PhotoListActivity.this.f8442f.dismiss();
            MyStatus unused = PhotoListActivity.f8433u = MyStatus.Select_Share_With_Comment;
            PhotoListActivity.this.f8440d.m9319N();
            PhotoListActivity.f8436x.setVisibility(0);
            PhotoListActivity.this.f8440d.m9334s0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: m */
        public /* synthetic */ void m9250m(View view) {
            PhotoListActivity.this.f8442f.dismiss();
            Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
            C5287b.m20583f(permission, new a(permission), PhotoListActivity.this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: n */
        public /* synthetic */ void m9251n(View view) {
            PhotoListActivity.this.f8442f.dismiss();
            MyStatus unused = PhotoListActivity.f8433u = MyStatus.Select_Delete;
            PhotoListActivity.this.f8440d.m9319N();
            PhotoListActivity.f8436x.setVisibility(0);
            PhotoListActivity.this.f8440d.m9334s0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o */
        public /* synthetic */ void m9252o(View view) {
            PhotoListActivity.this.f8442f.dismiss();
            MyStatus unused = PhotoListActivity.f8433u = MyStatus.Select_ShareToGroupAlbum;
            PhotoListActivity.this.f8440d.m9319N();
            PhotoListActivity.f8436x.setVisibility(0);
            PhotoListActivity.this.f8440d.m9334s0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: p */
        public /* synthetic */ void m9253p(View view) throws Resources.NotFoundException {
            PhotoListActivity.this.f8442f.dismiss();
            PhotoListActivity.this.m9233l2();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: q */
        public /* synthetic */ void m9254q(View view) {
            PhotoListActivity.this.f8442f.dismiss();
            PhotoListActivity.this.m9228I1();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: r */
        public /* synthetic */ void m9255r(View view) {
            PhotoListActivity.this.f8442f.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Group groupM15077n;
            PhotoListActivity.this.f8442f = new Dialog(PhotoListActivity.this.m9231N1());
            PhotoListActivity.this.f8442f.requestWindowFeature(1);
            PhotoListActivity.this.f8442f.setContentView(R.layout.dialog_photo_list_more);
            if (!PhotoListActivity.f8432t.booleanValue() || PhotoListActivity.this.f8440d.m9320O() == 0) {
                PhotoListActivity.this.f8442f.findViewById(R.id.copyToMyAlbumLayout).setVisibility(8);
            } else {
                PhotoListActivity.this.f8442f.findViewById(R.id.copyToMyAlbumLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ja
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10783b.m9247j(view2);
                    }
                });
            }
            if (PhotoListActivity.this.f8440d.m9320O() == 0) {
                PhotoListActivity.this.f8442f.findViewById(R.id.sharePhotosLayout).setVisibility(8);
                PhotoListActivity.this.f8442f.findViewById(R.id.sharePhotosWithCommentLayout).setVisibility(8);
                PhotoListActivity.this.f8442f.findViewById(R.id.copyToMyAlbumLayout).setVisibility(8);
                PhotoListActivity.this.f8442f.findViewById(R.id.savePhotosLayout).setVisibility(8);
                PhotoListActivity.this.f8442f.findViewById(R.id.saveToGroupAlbumLayout).setVisibility(8);
                PhotoListActivity.this.f8442f.findViewById(R.id.deletePhotosLayout).setVisibility(8);
            } else {
                PhotoListActivity.this.f8442f.findViewById(R.id.sharePhotos).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ka
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10822b.m9248k(view2);
                    }
                });
                PhotoListActivity.this.f8442f.findViewById(R.id.sharePhotosWithComment).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.la
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10853b.m9249l(view2);
                    }
                });
                PhotoListActivity.this.f8442f.findViewById(R.id.savePhotos).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ma
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10889b.m9250m(view2);
                    }
                });
                PhotoListActivity.this.f8442f.findViewById(R.id.deletePhotos).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.na
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10923b.m9251n(view2);
                    }
                });
                if (Group.m15744h(PhotoListActivity.f8438z)) {
                    PhotoListActivity.this.f8442f.findViewById(R.id.sharePhotosLayout).setVisibility(8);
                    PhotoListActivity.this.f8442f.findViewById(R.id.sharePhotosWithCommentLayout).setVisibility(0);
                    PhotoListActivity.this.f8442f.findViewById(R.id.savePhotosLayout).setVisibility(8);
                    PhotoListActivity.this.f8442f.findViewById(R.id.deletePhotosLayout).setVisibility(8);
                    PhotoListActivity.this.f8442f.findViewById(R.id.copyToMyAlbumLayout).setVisibility(8);
                }
                if (Group.m15744h(PhotoListActivity.f8438z) || (!(PhotoListActivity.f8431s == null || PhotoListActivity.f8431s.m14677d().equals("Chat")) || PhotoListActivity.f8438z.m15751i())) {
                    PhotoListActivity.this.f8442f.findViewById(R.id.saveToGroupAlbumLayout).setVisibility(8);
                } else {
                    PhotoListActivity.this.f8442f.findViewById(R.id.saveToGroupAlbumLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.oa
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f11008b.m9252o(view2);
                        }
                    });
                }
            }
            String strM14677d = PhotoListActivity.f8431s != null ? PhotoListActivity.f8431s.m14677d() : "";
            LinearLayout linearLayout = (LinearLayout) PhotoListActivity.this.f8442f.findViewById(R.id.renameAlbumLayout);
            if (Group.m15744h(PhotoListActivity.f8438z) || "Product".equals(strM14677d) || PhotoListActivity.m9164O1()) {
                linearLayout.setVisibility(8);
            } else {
                linearLayout.setVisibility(0);
                PhotoListActivity.this.f8442f.findViewById(R.id.renameAlbum).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.pa
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) throws Resources.NotFoundException {
                        this.f11039b.m9253p(view2);
                    }
                });
            }
            LinearLayout linearLayout2 = (LinearLayout) PhotoListActivity.this.f8442f.findViewById(R.id.deleteAlbumLayout);
            if (Group.m15744h(PhotoListActivity.f8438z) || (!(strM14677d == null || !strM14677d.equals("Product") || PhotoListActivity.this.f8444h) || PhotoListActivity.m9164O1())) {
                linearLayout2.setVisibility(8);
            } else {
                linearLayout2.setVisibility(0);
                PhotoListActivity.this.f8442f.findViewById(R.id.deleteAlbum).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.qa
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f11082b.m9254q(view2);
                    }
                });
            }
            PhotoListActivity.this.f8442f.findViewById(R.id.DialogCancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ra
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f11141b.m9255r(view2);
                }
            });
            if (PhotoListActivity.f8438z != null && "Community".equals(PhotoListActivity.f8438z.f13705D) && !PhotoListActivity.f8438z.f13704C) {
                PhotoListActivity.this.f8442f.findViewById(R.id.renameAlbumLayout).setVisibility(8);
                PhotoListActivity.this.f8442f.findViewById(R.id.deleteAlbumLayout).setVisibility(8);
            }
            if (PhotoListActivity.f8431s != null && !C5170o0.m20170e(PhotoListActivity.f8431s.m14679f()) && (groupM15077n = C2950b0.m14912k().m15077n(PhotoListActivity.f8431s.m14679f())) != null && "Community".equals(groupM15077n.f13705D) && !groupM15077n.f13704C) {
                PhotoListActivity.this.f8442f.findViewById(R.id.renameAlbumLayout).setVisibility(8);
                PhotoListActivity.this.f8442f.findViewById(R.id.deleteAlbumLayout).setVisibility(8);
            }
            if (PhotoListActivity.f8431s != null && !C5170o0.m20170e(PhotoListActivity.f8431s.m14678e()) && Globals.m7388i0().m7474Q1(PhotoListActivity.f8431s.m14678e())) {
                PhotoListActivity.this.f8442f.findViewById(R.id.renameAlbumLayout).setVisibility(0);
                PhotoListActivity.this.f8442f.findViewById(R.id.deleteAlbumLayout).setVisibility(0);
            }
            CLUtility.m16578q2(PhotoListActivity.this.m9231N1(), PhotoListActivity.this.f8442f);
            PhotoListActivity.this.f8442f.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoListActivity$c */
    public class C1625c implements C3199c.b {
        public C1625c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j */
        public /* synthetic */ void m9262j(int i9, int i10, UploadMediaHelper uploadMediaHelper) {
            if (PhotoListActivity.this.f8439c != null) {
                PhotoListActivity.this.f8439c.m24771n(Integer.toString(i9 + 1), Integer.toString(i10));
                PhotoListActivity.this.f8439c.m24764f(uploadMediaHelper.m16842f1());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void m9263k() {
            PhotoListActivity.this.f8439c.m24760b();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l */
        public /* synthetic */ void m9264l() {
            try {
                if (PhotoListActivity.this.isFinishing() || PhotoListActivity.this.f8439c == null || !PhotoListActivity.this.f8439c.isShowing()) {
                    return;
                }
                PhotoListActivity.this.f8439c.dismiss();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: m */
        public /* synthetic */ void m9265m() {
            PhotoListActivity.this.f8440d.m9323h0();
        }

        /* renamed from: n */
        public static /* synthetic */ void m9266n(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o */
        public /* synthetic */ void m9267o() {
            if (PhotoListActivity.this.f8439c != null) {
                PhotoListActivity.this.f8439c.m24760b();
            }
            C3199c.m17015D(false);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(PhotoListActivity.this.m9231N1());
            builderM16382a.setMessage(R.string.reach_photo_limit);
            builderM16382a.setPositiveButton(PhotoListActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.xa
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    PhotoListActivity.C1625c.m9266n(dialogInterface, i9);
                }
            });
            AlertDialog alertDialogCreate = builderM16382a.create();
            alertDialogCreate.requestWindowFeature(1);
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.show();
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: a */
        public void mo7916a(final int i9, final int i10, final UploadMediaHelper uploadMediaHelper) {
            C3199c.m17015D(true);
            PhotoListActivity.this.m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.sa
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11169b.m9262j(i9, i10, uploadMediaHelper);
                }
            });
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: b */
        public void mo7917b(C3199c c3199c) {
            PhotoListActivity.this.m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ua
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11433b.m9264l();
                }
            });
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
                    Log.d("PhotoListActivity", "upload fail resultType=" + uploadMediaHelper.m16828Y0());
                    C5187v0.m20267c(R.string.error_upload_response);
                }
            }
            if (i9 != listM17042t.size()) {
                Log.d("PhotoListActivity", "[onUploadMultipleMediaCallback] All medias are not uploaded completed.");
            } else {
                Group groupM15077n = PhotoListActivity.f8431s != null ? C2950b0.m14912k().m15077n(PhotoListActivity.f8431s.m14679f()) : null;
                if (groupM15077n != null && c2973l0M16826X0 != null) {
                    C2925v.m14630x0(groupM15077n, c2973l0M16826X0, String.valueOf(i9));
                }
            }
            c3199c.m17034E();
            PhotoListActivity.m9210m2(2);
            PhotoListActivity.this.m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.va
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11815b.m9265m();
                }
            });
            C3199c.m17015D(false);
            PhotoListActivity.this.m9234o2();
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: c */
        public void mo9268c() {
            PhotoListActivity.this.m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ta
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11395b.m9267o();
                }
            });
            C3199c.m17015D(false);
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        public void onCancel() {
            if (PhotoListActivity.this.f8439c != null) {
                PhotoListActivity.this.m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.wa
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11847b.m9263k();
                    }
                });
            }
            C3199c.m17015D(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoListActivity$d */
    public class C1626d extends XMPPManager.AbstractC2868s {
        public C1626d(boolean z8) {
            super(z8);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: e */
        public String mo8240e(C2904l c2904l) {
            Group groupM15077n;
            String str;
            if (c2904l == null || c2904l.m14399N() || c2904l.m14391F() || c2904l.m14389D() == MessageObj.MessageType.Event || c2904l.m14398M() || PhotoListActivity.f8431s == null) {
                return null;
            }
            String strM14418h = c2904l.m14418h();
            String strM14679f = PhotoListActivity.f8431s.m14679f();
            if (strM14418h != null && strM14679f != null && !strM14418h.isEmpty() && !strM14679f.isEmpty() && (groupM15077n = C2950b0.m14912k().m15077n(strM14679f)) != null && (str = groupM15077n.f13723j) != null && !str.isEmpty()) {
                strM14418h.equals(groupM15077n.f13723j);
            }
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoListActivity$e */
    public class AsyncTaskC1627e extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1627e() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            FriendsClient friendsClient = new FriendsClient();
            GroupAlbumObj groupAlbumObjM15708O = friendsClient.m15708O(PhotoListActivity.f8431s.m14675b(), PhotoListActivity.f8431s.m14679f());
            friendsClient.m15717U0();
            if (groupAlbumObjM15708O == null) {
                return null;
            }
            C2950b0.m14911j().m15053f(groupAlbumObjM15708O);
            PhotoListActivity.f8431s.m14685l(groupAlbumObjM15708O.m14682i());
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
            PhotoListActivity.m9210m2(2);
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoListActivity$f */
    public static /* synthetic */ class C1628f {

        /* renamed from: a */
        public static final /* synthetic */ int[] f8471a;

        static {
            int[] iArr = new int[MyStatus.values().length];
            f8471a = iArr;
            try {
                iArr[MyStatus.Select_Share.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8471a[MyStatus.Select_Share_With_Comment.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8471a[MyStatus.Select_Save.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8471a[MyStatus.Select_Delete.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8471a[MyStatus.Select_ShareToMyAlbum.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8471a[MyStatus.Select_ShareToGroupAlbum.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g */
    public static class C1629g extends Fragment {

        /* renamed from: b */
        public C6391a0 f8472b;

        /* renamed from: c */
        public GridView f8473c;

        /* renamed from: d */
        public Button f8474d;

        /* renamed from: e */
        public SwipeRefreshLayout f8475e;

        /* renamed from: f */
        public g f8476f;

        /* renamed from: g */
        public View f8477g;

        /* renamed from: h */
        public AdapterView.OnItemClickListener f8478h = new a();

        /* renamed from: i */
        public C6391a0.c f8479i = new b();

        /* renamed from: j */
        public C5321e.m f8480j = new C5321e.m() { // from class: com.cyberlink.you.activity.ya
            @Override // p136m3.C5321e.m
            /* renamed from: A0 */
            public final boolean mo8241A0(C2904l c2904l, Map map) {
                return this.f12275b.m9284U(c2904l, map);
            }
        };

        /* renamed from: k */
        public View.OnClickListener f8481k = new View.OnClickListener() { // from class: com.cyberlink.you.activity.fb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10437b.m9285V(view);
            }
        };

        /* renamed from: l */
        public View.OnClickListener f8482l = new View.OnClickListener() { // from class: com.cyberlink.you.activity.gb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10691b.m9286W(view);
            }
        };

        /* renamed from: m */
        public View.OnClickListener f8483m = new View.OnClickListener() { // from class: com.cyberlink.you.activity.hb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10722b.m9287X(view);
            }
        };

        /* renamed from: n */
        public View.OnClickListener f8484n = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ib
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10752b.m9288Y(view);
            }
        };

        /* renamed from: o */
        public View.OnClickListener f8485o = new View.OnClickListener() { // from class: com.cyberlink.you.activity.jb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10784b.m9289Z(view);
            }
        };

        /* renamed from: p */
        public View.OnClickListener f8486p = new c();

        /* renamed from: q */
        public C6391a0.d f8487q = new f();

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$a */
        public class a implements AdapterView.OnItemClickListener {

            /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$a$a, reason: collision with other inner class name */
            public class AsyncTaskC6847a extends AsyncTask<Void, Void, Void> {

                /* renamed from: a */
                public final /* synthetic */ C2973l0 f8489a;

                /* renamed from: b */
                public final /* synthetic */ DialogC3133q f8490b;

                public AsyncTaskC6847a(C2973l0 c2973l0, DialogC3133q dialogC3133q) {
                    this.f8489a = c2973l0;
                    this.f8490b = dialogC3133q;
                }

                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Void doInBackground(Void... voidArr) {
                    String strM15131c = this.f8489a.m15131c();
                    if (strM15131c == null) {
                        return null;
                    }
                    Globals.C1408b.m7655b().m7657d(strM15131c, Collections.singletonList(C2950b0.m14914m().m14694C(strM15131c, C2950b0.m14914m().m14693B(strM15131c, this.f8489a.m15144p()), 50, MediaDao.SelectType.BOTH)));
                    return null;
                }

                @Override // android.os.AsyncTask
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onPostExecute(Void r42) {
                    DialogC3133q dialogC3133q = this.f8490b;
                    if (dialogC3133q != null) {
                        dialogC3133q.cancel();
                    }
                    Intent intent = new Intent(C1629g.this.getActivity(), (Class<?>) ShowMediaActivity.class);
                    intent.putExtra("albumId", this.f8489a.m15131c());
                    intent.putExtra("mediaId", this.f8489a.m15144p());
                    intent.putExtra("Group", PhotoListActivity.f8431s != null ? C2950b0.m14912k().m15077n(PhotoListActivity.f8431s.m14679f()) : null);
                    intent.putExtra("ShowShareToMyAlbum", PhotoListActivity.f8432t);
                    C1629g.this.startActivityForResult(intent, 2);
                }
            }

            public a() {
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
                C2973l0 c2973l0 = (C2973l0) adapterView.getItemAtPosition(i9);
                if (PhotoListActivity.f8433u.equals(MyStatus.Normal)) {
                    PhotoListActivity.f8437y.setVisibility(8);
                    DialogC3133q dialogC3133qM16410a = new DialogC3133q.b(C1629g.this.getActivity()).m16413d(1500L).m16410a();
                    dialogC3133qM16410a.show();
                    new AsyncTaskC6847a(c2973l0, dialogC3133qM16410a).executeOnExecutor(C6385v.f21553a, new Void[0]);
                    return;
                }
                Intent intent = new Intent(C1629g.this.getActivity(), (Class<?>) SelectMediaActivity.class);
                intent.putExtra("albumId", c2973l0.m15131c());
                intent.putExtra("mediaId", c2973l0.m15144p());
                if (PhotoListActivity.f8433u.equals(MyStatus.Select_Share)) {
                    intent.putExtra("operationType", 0);
                } else if (PhotoListActivity.f8433u.equals(MyStatus.Select_Save)) {
                    intent.putExtra("operationType", 1);
                } else if (PhotoListActivity.f8433u.equals(MyStatus.Select_Delete)) {
                    intent.putExtra("operationType", 2);
                } else if (PhotoListActivity.f8433u.equals(MyStatus.Select_ShareToMyAlbum)) {
                    intent.putExtra("operationType", 5);
                } else if (PhotoListActivity.f8433u.equals(MyStatus.Select_Share_With_Comment)) {
                    intent.putExtra("operationType", 6);
                } else if (PhotoListActivity.f8433u.equals(MyStatus.Select_ShareToGroupAlbum)) {
                    intent.putExtra("operationType", 7);
                }
                intent.putExtra("checked", C1629g.this.f8472b.m24546e(i9));
                intent.putExtra("checkedMediaList", C1629g.this.f8472b.m24547f());
                C1629g.this.startActivityForResult(intent, 1);
            }
        }

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$b */
        public class b implements C6391a0.c {
            public b() {
            }

            @Override // p210u3.C6391a0.c
            /* renamed from: a */
            public void mo9338a(int i9) {
                if (i9 <= 0) {
                    C1629g.this.f8474d.setEnabled(false);
                    if (PhotoListActivity.f8433u.equals(MyStatus.Select_Share) || PhotoListActivity.f8433u.equals(MyStatus.Select_Share_With_Comment)) {
                        C1629g.this.f8474d.setText(Globals.m7372O().getString(R.string.chat_dialog_send_button));
                        return;
                    }
                    if (PhotoListActivity.f8433u.equals(MyStatus.Select_Save) || PhotoListActivity.f8433u.equals(MyStatus.Select_ShareToMyAlbum) || PhotoListActivity.f8433u.equals(MyStatus.Select_ShareToGroupAlbum)) {
                        C1629g.this.f8474d.setText(Globals.m7372O().getString(R.string.menu_save_to_camera_roll));
                        return;
                    } else {
                        if (PhotoListActivity.f8433u.equals(MyStatus.Select_Delete)) {
                            C1629g.this.f8474d.setText(Globals.m7372O().getString(R.string.menu_delete));
                            return;
                        }
                        return;
                    }
                }
                C1629g.this.f8474d.setEnabled(true);
                if (PhotoListActivity.f8433u.equals(MyStatus.Select_Share) || PhotoListActivity.f8433u.equals(MyStatus.Select_Share_With_Comment)) {
                    C1629g.this.f8474d.setText(Globals.m7372O().getString(R.string.chat_dialog_send_button) + " (" + i9 + ")");
                    return;
                }
                if (PhotoListActivity.f8433u.equals(MyStatus.Select_Save) || PhotoListActivity.f8433u.equals(MyStatus.Select_ShareToMyAlbum) || PhotoListActivity.f8433u.equals(MyStatus.Select_ShareToGroupAlbum)) {
                    C1629g.this.f8474d.setText(Globals.m7372O().getString(R.string.menu_save_to_camera_roll) + " (" + i9 + ")");
                    return;
                }
                if (PhotoListActivity.f8433u.equals(MyStatus.Select_Delete)) {
                    C1629g.this.f8474d.setText(Globals.m7372O().getString(R.string.menu_delete) + " (" + i9 + ")");
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$c */
        public class c implements View.OnClickListener {
            public c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhotoListActivity.f8437y.setVisibility(0);
                C1629g.this.m9324i0();
                C1629g.this.m9316K();
            }
        }

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$d */
        public class d extends AsyncTask<Void, Void, Group> {
            public d() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Group doInBackground(Void... voidArr) {
                if (PhotoListActivity.f8431s != null) {
                    return C2950b0.m14912k().m15077n(PhotoListActivity.f8431s.m14679f());
                }
                return null;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Group group) {
                if (C1629g.this.isAdded()) {
                    if (group != null && !group.f13738y.equals("General")) {
                        C1629g.this.f8472b.m24555n(true);
                    }
                    if (PhotoListActivity.f8431s != null && group != null) {
                        C1629g.this.m9322Q(PhotoListActivity.f8431s.m14675b(), String.valueOf(group.f13727n));
                    }
                    C1629g.this.m9323h0();
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$e */
        public class e implements C3062b.b {

            /* renamed from: a */
            public int f8495a = 0;

            /* renamed from: b */
            public final /* synthetic */ boolean f8496b;

            public e(boolean z8) {
                this.f8496b = z8;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: e */
            public /* synthetic */ void m9342e() {
                C1629g.this.m9329n0(false);
                C1629g.this.f8475e.setRefreshing(false);
            }

            @Override // com.cyberlink.you.friends.C3062b.b
            /* renamed from: a */
            public void mo9343a() {
                this.f8495a++;
                m9345d();
            }

            @Override // com.cyberlink.you.friends.C3062b.b
            /* renamed from: b */
            public void mo9344b(int i9, int i10) {
                int i11 = this.f8495a + 1;
                this.f8495a = i11;
                if (i9 == 0 || i9 == i11) {
                    m9345d();
                    C1629g.this.m9330o0(this.f8496b);
                }
            }

            /* renamed from: d */
            public void m9345d() {
                FragmentActivity activity = C1629g.this.getActivity();
                if (activity == null) {
                    return;
                }
                activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ob
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11009b.m9342e();
                    }
                });
            }
        }

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$f */
        public class f implements C6391a0.d {
            public f() {
            }

            @Override // p210u3.C6391a0.d
            /* renamed from: a */
            public void mo9346a(int i9) {
                C1629g.this.m9333r0();
                if (i9 > 0) {
                    C1629g.this.f8473c.setSelection(i9 - 1);
                } else {
                    C1629g.this.m9325j0();
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.PhotoListActivity$g$g */
        public class g extends AsyncTask<Void, Void, Boolean> {

            /* renamed from: a */
            public String f8499a;

            /* renamed from: b */
            public String f8500b;

            public g(String str, String str2) {
                this.f8499a = str;
                this.f8500b = str2;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) {
                Thread.currentThread().setName("getMediaTask");
                if (this.f8499a == null || this.f8500b == null || PhotoListActivity.f8431s == null) {
                    Log.e("PhotoListActivity", "[getMediaTask] groupId/albumId is null.");
                    return Boolean.FALSE;
                }
                int iM14719p = C2950b0.m14914m().m14719p(this.f8500b);
                GroupAlbumObj groupAlbumObjM15055h = PhotoListActivity.m9164O1() ? C2950b0.m14911j().m15055h(this.f8499a, this.f8500b) : C2950b0.m14911j().m15056i(this.f8500b);
                if (groupAlbumObjM15055h == null || groupAlbumObjM15055h.m14682i() != iM14719p) {
                    C1629g.this.m9329n0(true);
                    FriendsClient friendsClient = new FriendsClient();
                    GroupAlbumObj groupAlbumObjM15708O = friendsClient.m15708O(this.f8500b, this.f8499a);
                    friendsClient.m15717U0();
                    if (groupAlbumObjM15708O != null) {
                        C2950b0.m14911j().m15053f(groupAlbumObjM15708O);
                    }
                }
                return Boolean.TRUE;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    C1629g.this.m9321P(this.f8500b, true);
                }
            }
        }

        /* renamed from: R */
        public static /* synthetic */ void m9281R(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: S */
        public /* synthetic */ void m9282S() {
            m9323h0();
            m9318M();
            m9316K();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: T */
        public /* synthetic */ void m9283T(long[] jArr, Group group, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("PhotoListActivity", "[media.deleteMedia] Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d("PhotoListActivity", "[media.deleteMedia] statusCode=" + str3);
                return;
            }
            if (PhotoListActivity.f8431s != null) {
                int i9 = 0;
                for (long j9 : jArr) {
                    i9++;
                    C2950b0.m14914m().m14716m(String.valueOf(j9));
                }
                if (!PhotoListActivity.f8431s.m14677d().equals("Chat")) {
                    PhotoListActivity.f8431s.m14685l(PhotoListActivity.f8431s.m14682i() - i9);
                    C2950b0.m14911j().m15053f(PhotoListActivity.f8431s);
                }
                if (group != null) {
                    C2925v.m14634z0(group, PhotoListActivity.f8431s.m14675b(), PhotoListActivity.f8431s.m14676c(), String.valueOf(i9));
                }
                PhotoListActivity.m9210m2(2);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.db
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f10369b.m9282S();
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* renamed from: U */
        public /* synthetic */ boolean m9284U(C2904l c2904l, Map map) {
            String str = (String) map.get("eventType");
            ULogUtility.m16670f("PhotoListActivity", "[mEventListener] eventType = " + str);
            str.hashCode();
            switch (str) {
                case "media.album.updated":
                    m9331p0();
                    return true;
                case "media.media.created":
                case "media.media.deleted":
                    m9323h0();
                    return true;
                case "media.comment.created":
                case "media.comment.deleted":
                    m9332q0(map);
                    return true;
                default:
                    return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: V */
        public /* synthetic */ void m9285V(View view) {
            m9326k0(false);
            m9316K();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: W */
        public /* synthetic */ void m9286W(View view) {
            m9326k0(true);
            m9316K();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: X */
        public /* synthetic */ void m9287X(View view) {
            m9328m0();
            m9316K();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: Y */
        public /* synthetic */ void m9288Y(View view) {
            m9327l0();
            m9316K();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: Z */
        public /* synthetic */ void m9289Z(View view) {
            m9317L();
            m9316K();
        }

        /* renamed from: a0 */
        public static /* synthetic */ void m9290a0(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b0 */
        public /* synthetic */ void m9291b0() {
            m9321P(PhotoListActivity.f8431s.m14675b(), false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c0 */
        public /* synthetic */ void m9292c0() {
            if (this.f8472b == null || PhotoListActivity.f8431s == null) {
                return;
            }
            this.f8472b.m24550i(PhotoListActivity.f8431s.m14675b(), this.f8487q);
        }

        /* renamed from: d0 */
        public static /* synthetic */ void m9293d0(boolean z8) {
            if (z8) {
                PhotoListActivity.f8437y.setVisibility(0);
            } else {
                PhotoListActivity.f8437y.setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e0 */
        public /* synthetic */ void m9294e0(boolean z8) {
            int count;
            if (this.f8472b == null || PhotoListActivity.f8431s == null) {
                return;
            }
            this.f8472b.m24556o(PhotoListActivity.f8431s.m14675b());
            m9333r0();
            if (z8 && (count = this.f8472b.getCount()) > 0) {
                this.f8473c.setSelection(count - 1);
            }
            Log.d("PhotoListActivity", "--------------------updateAlbum finish! mAlbumObj.getNumberOfMedia()" + PhotoListActivity.f8431s.m14682i());
        }

        /* renamed from: f0 */
        public static /* synthetic */ void m9295f0() {
            GroupAlbumObj unused = PhotoListActivity.f8431s = C2950b0.m14911j().m15056i(PhotoListActivity.f8431s.m14675b());
            if (PhotoListActivity.f8431s != null) {
                PhotoListActivity.f8435w.setText(PhotoListActivity.f8431s.m14676c());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g0 */
        public /* synthetic */ void m9297g0(Activity activity) {
            C6391a0 c6391a0 = this.f8472b;
            if (c6391a0 != null) {
                int count = c6391a0.getCount();
                View viewFindViewById = activity.findViewById(R.id.OptionBtn);
                View viewFindViewById2 = activity.findViewById(R.id.addImageBtn);
                if (viewFindViewById != null && viewFindViewById2 != null) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById2.getLayoutParams();
                    if (count > 0 || !PhotoListActivity.m9164O1()) {
                        viewFindViewById.setVisibility(0);
                        layoutParams.setMargins(0, 0, 0, 0);
                    } else {
                        viewFindViewById.setVisibility(8);
                        layoutParams.setMargins(0, 0, (int) activity.getResources().getDimension(R.dimen.t10dp), 0);
                    }
                    viewFindViewById2.setLayoutParams(layoutParams);
                    if (PhotoListActivity.m9166P1()) {
                        viewFindViewById.setVisibility(8);
                        viewFindViewById2.setVisibility(8);
                    }
                }
                m9335w(activity);
            }
        }

        /* renamed from: K */
        public final void m9316K() {
            MyStatus unused = PhotoListActivity.f8433u = MyStatus.Normal;
            m9318M();
            PhotoListActivity.f8436x.setVisibility(8);
        }

        /* renamed from: L */
        public final void m9317L() {
            final long[] jArrM24547f = this.f8472b.m24547f();
            if (jArrM24547f.length != 0) {
                final Group groupM15077n = (PhotoListActivity.f8431s == null || PhotoListActivity.f8431s.m14679f() == null) ? null : C2950b0.m14912k().m15077n(PhotoListActivity.f8431s.m14679f());
                int iM14720q = C2950b0.m14914m().m14720q(jArrM24547f, String.valueOf(Globals.m7388i0().m7568k1()));
                if (groupM15077n == null || groupM15077n.f13704C || groupM15077n.f13729p <= 0 || iM14720q <= 0) {
                    PhotoListActivity.m9159K1(getActivity(), groupM15077n, jArrM24547f, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.nb
                        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                        /* renamed from: a */
                        public final void mo134a(String str, String str2, String str3, String str4) {
                            this.f10924a.m9283T(jArrM24547f, groupM15077n, str, str2, str3, str4);
                        }
                    });
                    return;
                }
                AlertDialog.Builder builderM16382a = C3123g.m16382a(getActivity());
                builderM16382a.setMessage(R.string.delete_photo_error_of_not_creator);
                builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.mb
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        PhotoListActivity.C1629g.m9281R(dialogInterface, i9);
                    }
                });
                builderM16382a.setCancelable(false);
                AlertDialog alertDialogCreate = builderM16382a.create();
                alertDialogCreate.requestWindowFeature(1);
                alertDialogCreate.show();
            }
        }

        /* renamed from: M */
        public void m9318M() {
            this.f8474d.setVisibility(8);
            this.f8472b.m24553l(false);
            this.f8474d.setOnClickListener(null);
            this.f8474d.setText("");
        }

        /* renamed from: N */
        public void m9319N() {
            this.f8474d.setVisibility(0);
            this.f8472b.m24553l(true);
        }

        /* renamed from: O */
        public int m9320O() {
            return this.f8473c.getCount();
        }

        /* renamed from: P */
        public final void m9321P(String str, boolean z8) {
            m9329n0(z8);
            C3062b.m15824x(str, new e(z8));
        }

        /* renamed from: Q */
        public final void m9322Q(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return;
            }
            g gVar = new g(str2, str);
            this.f8476f = gVar;
            gVar.executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        /* renamed from: h0 */
        public final void m9323h0() {
            if (getActivity() == null) {
                return;
            }
            try {
                getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.cb
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f9779b.m9292c0();
                    }
                });
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* renamed from: i0 */
        public final void m9324i0() {
            C2925v.m14626v0(getActivity(), this.f8472b.m24548g());
            PhotoListActivity.f8437y.setVisibility(8);
        }

        /* renamed from: j0 */
        public final void m9325j0() {
            View view = this.f8477g;
            if (view == null) {
                return;
            }
            TextView textView = (TextView) view.findViewById(R.id.PhotoListEmptyTextView);
            View viewFindViewById = this.f8477g.findViewById(R.id.bottom_to_right_top_indicate_arrow);
            if (PhotoListActivity.f8431s == null || PhotoListActivity.f8431s.m14682i() != 0) {
                return;
            }
            if (PhotoListActivity.m9164O1()) {
                textView.setText(R.string.photos_description);
            } else {
                textView.setText(R.string.albums_description);
            }
            viewFindViewById.setVisibility(0);
            this.f8477g.setVisibility(0);
        }

        /* renamed from: k0 */
        public final void m9326k0(boolean z8) {
            long[] jArrM24547f = this.f8472b.m24547f();
            if (jArrM24547f.length != 0) {
                Intent intent = new Intent(getActivity(), (Class<?>) ShareMediaActivity.class);
                intent.putExtra("share_media_id", jArrM24547f);
                intent.putExtra("shareType", ShareType.Internal_Media.toString());
                intent.putExtra("withComment", z8);
                startActivity(intent);
            }
        }

        /* renamed from: l0 */
        public final void m9327l0() {
            long[] jArrM24547f = this.f8472b.m24547f();
            if (jArrM24547f.length != 0) {
                Intent intent = new Intent(getActivity(), (Class<?>) GroupAlbumActivity.class);
                intent.putExtra("Group", PhotoListActivity.f8431s != null ? C2950b0.m14912k().m15077n(PhotoListActivity.f8431s.m14679f()) : null);
                intent.putExtra("select", true);
                intent.putExtra("share_media_id", jArrM24547f);
                intent.putExtra("withComment", true);
                intent.putExtra("ShareToGroupAlbum", true);
                startActivityForResult(intent, 5);
            }
        }

        /* renamed from: m0 */
        public final void m9328m0() {
            long[] jArrM24547f = this.f8472b.m24547f();
            if (jArrM24547f.length != 0) {
                Intent intent = new Intent(getActivity(), (Class<?>) GroupAlbumActivity.class);
                intent.putExtra("CopyToMyAlbum", true);
                intent.putExtra("select", true);
                intent.putExtra("share_media_id", jArrM24547f);
                intent.putExtra("withComment", true);
                intent.putExtra("ShowShareToMyAlbum", true);
                startActivityForResult(intent, 4);
            }
        }

        /* renamed from: n0 */
        public final void m9329n0(final boolean z8) {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bb
                    @Override // java.lang.Runnable
                    public final void run() {
                        PhotoListActivity.C1629g.m9293d0(z8);
                    }
                });
            }
        }

        /* renamed from: o0 */
        public final void m9330o0(final boolean z8) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ab
                @Override // java.lang.Runnable
                public final void run() {
                    this.f9718b.m9294e0(z8);
                }
            });
        }

        @Override // androidx.fragment.app.Fragment
        public void onActivityResult(int i9, int i10, Intent intent) {
            super.onActivityResult(i9, i10, intent);
            if (i9 == 1) {
                if (i10 == -1) {
                    int intExtra = intent.getIntExtra("type", -1);
                    long[] longArrayExtra = intent.getLongArrayExtra("checkedMediaList");
                    if (intExtra == -1) {
                        return;
                    }
                    this.f8472b.m24557p(longArrayExtra);
                    if (intExtra == 0) {
                        m9326k0(false);
                        return;
                    }
                    if (intExtra == 1) {
                        m9324i0();
                        return;
                    }
                    if (intExtra == 2) {
                        m9317L();
                        return;
                    }
                    if (intExtra == 5) {
                        m9328m0();
                        return;
                    } else if (intExtra == 6) {
                        m9326k0(true);
                        return;
                    } else {
                        if (intExtra != 7) {
                            return;
                        }
                        m9327l0();
                        return;
                    }
                }
                return;
            }
            if (i9 == 2) {
                if (i10 == -1) {
                    int intExtra2 = intent.getIntExtra("operationResult", 0);
                    int i11 = intExtra2 & 1;
                    if (i11 > 0 || (intExtra2 & 2) > 0) {
                        m9323h0();
                    }
                    if (i11 > 0) {
                        PhotoListActivity.m9210m2(2);
                    }
                    Boolean unused = PhotoListActivity.f8432t = Boolean.valueOf(intent.getBooleanExtra("ShowShareToMyAlbum", false));
                    this.f8472b.notifyDataSetChanged();
                    return;
                }
                return;
            }
            int i12 = R.string.the_photo_was_saved_successfully;
            if (i9 != 4) {
                if (i9 == 5 && i10 == -1) {
                    int intExtra3 = intent.getIntExtra("ShareToGroupAlbumOkCnt", 0);
                    m9316K();
                    Context contextM7372O = Globals.m7372O();
                    if (intExtra3 >= 2) {
                        i12 = R.string.the_photos_were_saved_successfully;
                    }
                    C5187v0.m20268d(contextM7372O.getString(i12));
                    return;
                }
                return;
            }
            if (i10 == -1) {
                int intExtra4 = intent.getIntExtra("CopyToMyAlbumOkCnt", 0);
                m9316K();
                Context contextM7372O2 = Globals.m7372O();
                if (intExtra4 >= 2) {
                    i12 = R.string.the_photos_were_saved_successfully;
                }
                String string = contextM7372O2.getString(i12);
                try {
                    AlertDialog.Builder builderM16382a = C3123g.m16382a(getActivity());
                    builderM16382a.setMessage(string);
                    builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.kb
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i13) {
                            PhotoListActivity.C1629g.m9290a0(dialogInterface, i13);
                        }
                    });
                    AlertDialog alertDialogCreate = builderM16382a.create();
                    alertDialogCreate.requestWindowFeature(1);
                    alertDialogCreate.show();
                    ((TextView) alertDialogCreate.findViewById(android.R.id.message)).setGravity(17);
                } catch (Exception e9) {
                    Log.d("PhotoListActivity", "[onActivityResult] Create AlertDialog fail = " + e9.getMessage());
                    C5187v0.m20268d(string);
                }
            }
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View viewInflate = layoutInflater.inflate(R.layout.fragment_photo_list, viewGroup, false);
            C5321e.m20824o().m20875k(this.f8480j);
            return viewInflate;
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            g gVar = this.f8476f;
            if (gVar != null && !gVar.isCancelled()) {
                this.f8476f.cancel(true);
                this.f8476f = null;
            }
            GroupAlbumObj unused = PhotoListActivity.f8431s = null;
            this.f8472b = null;
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroyView() {
            super.onDestroyView();
            C5321e.m20824o().m20832B0(this.f8480j);
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
            this.f8474d = (Button) view.findViewById(R.id.menuOperation);
            GridView gridView = (GridView) view.findViewById(R.id.MyPhotoListView);
            this.f8473c = gridView;
            gridView.setOnItemClickListener(this.f8478h);
            C6391a0 c6391a0 = new C6391a0(getActivity(), R.layout.view_item_group_photo, new ArrayList());
            this.f8472b = c6391a0;
            c6391a0.m24551j((TextView) getActivity().findViewById(R.id.numberOfSelectedItem));
            this.f8472b.m24554m(this.f8479i);
            View viewFindViewById = view.findViewById(R.id.PhotoListEmptyView);
            this.f8477g = viewFindViewById;
            View viewFindViewById2 = viewFindViewById.findViewById(R.id.bottom_to_right_top_indicate_arrow);
            this.f8473c.setEmptyView(this.f8477g);
            this.f8473c.setAdapter((ListAdapter) this.f8472b);
            viewFindViewById2.setVisibility(8);
            View unused = PhotoListActivity.f8437y = view.findViewById(R.id.loading);
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.photoRefreshLayout);
            this.f8475e = swipeRefreshLayout;
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.InterfaceC0510j() { // from class: com.cyberlink.you.activity.lb
                @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.InterfaceC0510j
                /* renamed from: a */
                public final void mo3015a() {
                    this.f10854a.m9291b0();
                }
            });
            m9325j0();
            new d().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }

        /* renamed from: p0 */
        public void m9331p0() {
            if (getActivity() == null) {
                return;
            }
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.za
                @Override // java.lang.Runnable
                public final void run() {
                    PhotoListActivity.C1629g.m9295f0();
                }
            });
        }

        /* renamed from: q0 */
        public void m9332q0(Map<String, String> map) {
            if (map.containsKey("mediaId")) {
                String str = map.get("mediaId");
                ArrayList arrayList = new ArrayList();
                FriendsClient friendsClient = new FriendsClient();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("mediaId", String.valueOf(str)));
                Pair<String, String> pairM15731j = friendsClient.m15731j("media", "mediaInfo", arrayList);
                if (!"200".equals(pairM15731j.first)) {
                    Log.d("PhotoListActivity", "[updateCommentCount] query media status code is '" + ((String) pairM15731j.first) + ";");
                    return;
                }
                String str2 = (String) pairM15731j.second;
                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.parseLong(str));
                if (c2973l0M14725v != null) {
                    C2950b0.m14914m().m14713j(C5172p.m20190l(c2973l0M14725v.m15131c(), C5172p.m20196r(str2), false));
                }
                friendsClient.m15717U0();
            }
            m9330o0(false);
        }

        /* renamed from: r0 */
        public final void m9333r0() {
            final FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.eb
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10402b.m9297g0(activity);
                }
            });
        }

        /* renamed from: s0 */
        public void m9334s0() {
            switch (C1628f.f8471a[PhotoListActivity.f8433u.ordinal()]) {
                case 1:
                    this.f8474d.setOnClickListener(this.f8481k);
                    this.f8474d.setText(R.string.chat_dialog_send_button);
                    this.f8474d.setBackgroundResource(R.drawable.image_selector_common_button);
                    break;
                case 2:
                    this.f8474d.setOnClickListener(this.f8482l);
                    this.f8474d.setText(R.string.chat_dialog_send_button);
                    this.f8474d.setBackgroundResource(R.drawable.image_selector_common_button);
                    break;
                case 3:
                    this.f8474d.setOnClickListener(this.f8486p);
                    this.f8474d.setText(R.string.menu_save_to_camera_roll);
                    this.f8474d.setBackgroundResource(R.drawable.image_selector_common_button);
                    break;
                case 4:
                    this.f8474d.setOnClickListener(this.f8485o);
                    this.f8474d.setText(R.string.menu_delete);
                    this.f8474d.setBackgroundResource(R.drawable.image_selector_common_button_red);
                    break;
                case 5:
                    this.f8474d.setOnClickListener(this.f8483m);
                    this.f8474d.setText(R.string.menu_save_to_camera_roll);
                    this.f8474d.setBackgroundResource(R.drawable.image_selector_common_button);
                    break;
                case 6:
                    this.f8474d.setOnClickListener(this.f8484n);
                    this.f8474d.setText(R.string.menu_save_to_camera_roll);
                    this.f8474d.setBackgroundResource(R.drawable.image_selector_common_button);
                    break;
                default:
                    this.f8474d.setOnClickListener(null);
                    this.f8474d.setText("");
                    Log.d("PhotoListActivity", "[updateOperation] unexpected status");
                    break;
            }
        }

        /* renamed from: w */
        public final void m9335w(Activity activity) {
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(R.id.header);
            TextView textView = (TextView) viewGroup.findViewById(R.id.PhotoListTitleText);
            int iMax = Math.max(viewGroup.findViewById(R.id.LeftBtnLayout).getWidth(), viewGroup.findViewById(R.id.RightBtnLayout).getWidth());
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.setMargins(iMax, 0, iMax, 0);
            textView.setLayoutParams(layoutParams);
            textView.requestLayout();
        }
    }

    /* renamed from: J1 */
    public static void m9157J1(Activity activity, Group group, long j9, FriendsClient.InterfaceC3051i interfaceC3051i) {
        m9159K1(activity, group, new long[]{j9}, interfaceC3051i);
    }

    /* renamed from: K1 */
    public static void m9159K1(Activity activity, Group group, final long[] jArr, final FriendsClient.InterfaceC3051i interfaceC3051i) {
        GroupAlbumObj groupAlbumObj = f8431s;
        boolean z8 = false;
        boolean z9 = groupAlbumObj != null && groupAlbumObj.m14683j();
        if (group != null && group.m15750g()) {
            z8 = true;
        }
        String string = activity.getString((z9 || z8) ? R.string.delete_photo_text : R.string.delete_photo_reminder);
        String string2 = activity.getString((z9 || z8) ? R.string.ok : R.string.delete_from_this_group);
        AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
        builderM16382a.setTitle(activity.getString(R.string.delete_photo_guide));
        builderM16382a.setMessage(string);
        builderM16382a.setNegativeButton(string2, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.o9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                PhotoListActivity.m9180W1(jArr, interfaceC3051i, dialogInterface, i9);
            }
        });
        builderM16382a.setPositiveButton(activity.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.p9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                PhotoListActivity.m9178V1(dialogInterface, i9);
            }
        });
        AlertDialog alertDialogShow = builderM16382a.show();
        if (z9 || z8) {
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

    /* renamed from: O1 */
    public static boolean m9164O1() {
        GroupAlbumObj groupAlbumObj = f8431s;
        return (groupAlbumObj == null || groupAlbumObj.m14677d() == null || !f8431s.m14677d().equals("Chat")) ? false : true;
    }

    /* renamed from: P1 */
    public static boolean m9166P1() {
        Group group = f8438z;
        return (group == null || C2925v.m14625v(group)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q1 */
    public /* synthetic */ void m9168Q1() {
        ULogUtility.m16670f("PhotoListActivity", "photo UpdateBadges isReady:" + C2907m0.m14454I().m14489N());
        int iM14480D = C2907m0.m14454I().m14480D();
        TextView textView = this.f8445i;
        if (textView == null || iM14480D < 0) {
            return;
        }
        if (iM14480D == 0) {
            textView.setVisibility(8);
        } else {
            textView.setText(iM14480D > 99 ? "N" : String.valueOf(iM14480D));
            this.f8445i.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R1 */
    public /* synthetic */ void m9170R1(FriendsClient friendsClient, String str, String str2, String str3, String str4, String str5) throws JSONException {
        friendsClient.m15717U0();
        int iM20179a = C5172p.m20179a(str, str5);
        if (iM20179a <= 0) {
            C5187v0.m20267c(R.string.reach_photo_limit);
            return;
        }
        Intent intent = new Intent(m9231N1(), (Class<?>) PhotoImportActivity.class);
        intent.putExtra("isImportedToAlbums", true);
        intent.putExtra("selectLimitCount", iM20179a);
        startActivityForResult(intent, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S1 */
    public /* synthetic */ void m9172S1(GroupAlbumObj groupAlbumObj, String str, String str2, String str3, String str4) {
        if (str3 == null) {
            Log.d("PhotoListActivity", "Response is null");
            return;
        }
        if (!str3.equals("200")) {
            Log.d("PhotoListActivity", "statusCode=" + str3);
            return;
        }
        C2950b0.m14911j().m15054g(groupAlbumObj.m14675b());
        C2950b0.m14914m().m14717n(groupAlbumObj.m14675b());
        if (f8431s != null) {
            m9210m2(1);
            Log.d("PhotoListActivity", "Delete success");
        }
        m9230M1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T1 */
    public /* synthetic */ void m9174T1(DialogInterface dialogInterface, int i9) {
        final GroupAlbumObj groupAlbumObj = f8431s;
        FriendsClient friendsClient = new FriendsClient();
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", groupAlbumObj.m14675b()));
        friendsClient.m15734m("group", "deleteAlbum", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.z9
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f12300a.m9172S1(groupAlbumObj, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: U1 */
    public static /* synthetic */ void m9176U1(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: V1 */
    public static /* synthetic */ void m9178V1(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: W1 */
    public static /* synthetic */ void m9180W1(long[] jArr, FriendsClient.InterfaceC3051i interfaceC3051i, DialogInterface dialogInterface, int i9) {
        FriendsClient friendsClient = new FriendsClient();
        C6566a.m25159r("Delete");
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        for (long j9 : jArr) {
            arrayList.add(new C6301o("mediaId", String.valueOf(j9)));
        }
        friendsClient.m15734m("media", "deleteMedia", arrayList, interfaceC3051i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X1 */
    public /* synthetic */ void m9182X1(String str) {
        this.f8440d.m9323h0();
        f8435w.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y1 */
    public /* synthetic */ void m9184Y1(final String str, String str2, String str3, String str4, String str5) {
        GroupAlbumObj groupAlbumObj;
        if ("200".equals(str4) && (groupAlbumObj = f8431s) != null) {
            groupAlbumObj.m14684k(str);
            C2950b0.m14911j().m15061n(f8431s.m14675b(), f8431s, "AlbumName");
            m9210m2(2);
            m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.aa
                @Override // java.lang.Runnable
                public final void run() {
                    this.f9716b.m9182X1(str);
                }
            });
        }
        Log.d("PhotoListActivity", "Rename success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z1 */
    public /* synthetic */ void m9186Z1(View view) {
        m9230M1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a2 */
    public /* synthetic */ void m9188a2(View view) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C1623a(permission), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b2 */
    public /* synthetic */ void m9190b2(View view) {
        C1629g c1629g = this.f8440d;
        if (c1629g != null) {
            c1629g.m9316K();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c2 */
    public /* synthetic */ void m9192c2() {
        C3199c c3199c = this.f8443g;
        if (c3199c != null) {
            c3199c.m17036d();
        }
        m9235p2();
        m9234o2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d2 */
    public /* synthetic */ void m9194d2(boolean z8) {
        if (z8) {
            m9232f1(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e2 */
    public /* synthetic */ void m9196e2(EditText editText, ArrayList arrayList, Dialog dialog, View view) {
        String strTrim = editText.getText().toString().trim();
        if (strTrim.isEmpty()) {
            C5187v0.m20267c(R.string.input_album_name);
        } else if (arrayList != null && arrayList.contains(strTrim)) {
            C5187v0.m20267c(R.string.duplicate_album_name_notification);
        } else {
            m9229L1(strTrim);
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h2 */
    public /* synthetic */ void m9201h2() {
        ((InputMethodManager) m9231N1().getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i2 */
    public /* synthetic */ void m9203i2(DialogInterface dialogInterface) {
        new Handler().postDelayed(new Runnable() { // from class: com.cyberlink.you.activity.x9
            @Override // java.lang.Runnable
            public final void run() {
                this.f12251b.m9201h2();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j2 */
    public /* synthetic */ void m9205j2() {
        C1629g c1629g = this.f8440d;
        if (c1629g != null) {
            c1629g.m9323h0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k2 */
    public /* synthetic */ void m9207k2() {
        this.f8439c.show();
    }

    /* renamed from: m2 */
    public static void m9210m2(int i9) {
        f8434v = i9 | f8434v;
    }

    /* renamed from: n2 */
    public static void m9212n2(Activity activity, C2973l0 c2973l0, C2925v.d dVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(c2973l0);
        C2925v.m14628w0(activity, arrayList, dVar);
    }

    /* renamed from: F1 */
    public final void m9225F1() {
        try {
            final String strM14675b = f8431s.m14675b();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("albumId", strM14675b));
            final FriendsClient friendsClient = new FriendsClient();
            friendsClient.m15734m("media", "queryUsage", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.ha
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                    this.f10719a.m9170R1(friendsClient, strM14675b, str, str2, str3, str4);
                }
            });
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: G1 */
    public final void m9226G1() {
        try {
            startActivityForResult(new Intent(m9231N1(), (Class<?>) PhotoImportActivity.class), 3);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: H1 */
    public final void m9227H1() {
        Intent intent;
        if (f8438z != null) {
            Log.d("PhotoListActivity", "Back to ChatDialogActivity");
            intent = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
            intent.putExtra("Group", f8438z);
        } else {
            Log.d("PhotoListActivity", "Back to ULauncherActivity");
            intent = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
        }
        intent.setFlags(67108864);
        startActivity(intent);
    }

    /* renamed from: I1 */
    public final void m9228I1() {
        Activity activityM9231N1;
        int i9;
        Activity activityM9231N12;
        int i10;
        if (f8431s == null) {
            return;
        }
        Group groupM15077n = C2950b0.m14912k().m15077n(f8431s.m14679f());
        boolean z8 = groupM15077n != null && groupM15077n.m15750g();
        if (f8431s.m14683j() || z8) {
            activityM9231N1 = m9231N1();
            i9 = R.string.delete_album_text;
        } else {
            activityM9231N1 = m9231N1();
            i9 = R.string.delete_album_reminder;
        }
        String string = activityM9231N1.getString(i9);
        if (f8431s.m14683j() || z8) {
            activityM9231N12 = m9231N1();
            i10 = R.string.ok;
        } else {
            activityM9231N12 = m9231N1();
            i10 = R.string.delete_from_this_group;
        }
        String string2 = activityM9231N12.getString(i10);
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(m9231N1().getString(R.string.delete_album_guide));
        builderM16382a.setMessage(string);
        builderM16382a.setNegativeButton(string2, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.u9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i11) {
                this.f11432b.m9174T1(dialogInterface, i11);
            }
        });
        builderM16382a.setPositiveButton(m9231N1().getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.v9
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i11) {
                PhotoListActivity.m9176U1(dialogInterface, i11);
            }
        });
        AlertDialog alertDialogShow = builderM16382a.show();
        if (f8431s.m14683j() || z8) {
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
            button2.setTextColor(m9231N1().getResources().getColor(R.color.you_color_delete_red));
            C5179r0.m20247b(button2, 1);
        }
    }

    /* renamed from: L1 */
    public final void m9229L1(final String str) {
        String strM7449L = Globals.m7388i0().m7449L();
        FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", f8431s.m14675b()));
        arrayList.add(new C6301o("albumName", str));
        friendsClient.m15734m("group", "updateAlbum", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.w9
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f11845a.m9184Y1(str, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: M1 */
    public final void m9230M1() {
        if (getCallingActivity() != null) {
            if (getCallingActivity().getClassName().equals(GroupInfoActivity.class.getName())) {
                m9227H1();
            } else {
                Intent intent = new Intent();
                intent.putExtra("operationResult", f8434v);
                intent.putExtra("album", f8431s);
                setResult(-1, intent);
            }
        } else if (getApplicationContext() != null) {
            m9227H1();
        }
        finish();
    }

    /* renamed from: N1 */
    public final Activity m9231N1() {
        return this;
    }

    /* renamed from: f1 */
    public final void m9232f1(boolean z8) {
        if (z8) {
            C2907m0.m14454I().m14495W(this.f8452p);
        }
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.y9
            @Override // java.lang.Runnable
            public final void run() {
                this.f12274b.m9168Q1();
            }
        });
    }

    /* renamed from: l2 */
    public final void m9233l2() throws Resources.NotFoundException {
        if (f8431s == null) {
            Log.d("PhotoListActivity", "[renameAlbum] mAlbumObj is null");
            return;
        }
        final Dialog dialogM16384c = C3123g.m16384c(this);
        View viewInflate = m9231N1().getLayoutInflater().inflate(R.layout.dialog_album_rename, (ViewGroup) null);
        final EditText editText = (EditText) viewInflate.findViewById(R.id.renameEditText);
        String strM14676c = f8431s.m14676c();
        int integer = getResources().getInteger(R.integer.max_input_length_album_name);
        if (strM14676c.length() > integer) {
            strM14676c = strM14676c.substring(0, integer);
        }
        editText.requestFocus();
        editText.setText(strM14676c);
        editText.setSelection(strM14676c.length());
        final ArrayList<String> stringArrayListExtra = m9231N1().getIntent().getStringArrayListExtra("album_name_list");
        ((Button) viewInflate.findViewById(R.id.renameOkBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.q9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11078b.m9196e2(editText, stringArrayListExtra, dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.renameCancelBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.r9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16384c.dismiss();
            }
        });
        viewInflate.findViewById(R.id.clearAlbumNameBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.s9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText("");
            }
        });
        dialogM16384c.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.cyberlink.you.activity.t9
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f11394b.m9203i2(dialogInterface);
            }
        });
        dialogM16384c.setContentView(viewInflate);
        CLUtility.m16578q2(m9231N1(), dialogM16384c);
        if (dialogM16384c.getWindow() != null) {
            dialogM16384c.getWindow().setSoftInputMode(4);
        }
        dialogM16384c.show();
    }

    /* renamed from: o2 */
    public final void m9234o2() {
        if (f8431s != null) {
            new AsyncTaskC1627e().executeOnExecutor(C6385v.f21553a, new Void[0]);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        ArrayList arrayList;
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0) {
            if (i10 == -1) {
                m9237r2((ArrayList) intent.getExtras().getSerializable("import_images"));
            }
        } else if (i9 == 3 && i10 == -1 && (arrayList = (ArrayList) intent.getExtras().getSerializable("import_images")) != null && arrayList.size() > 0) {
            setResult(-1, intent);
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (f8436x.getVisibility() == 0) {
            this.f8441e.callOnClick();
        } else {
            m9230M1();
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.activity_photo_list);
        f8433u = MyStatus.Normal;
        boolean z8 = false;
        f8434v = 0;
        DialogC6459g dialogC6459g = new DialogC6459g(m9231N1(), R.style.FriendSelectorDialog);
        this.f8439c = dialogC6459g;
        dialogC6459g.m24770m(this.f8451o);
        findViewById(R.id.BackBtn).setOnClickListener(this.f8446j);
        findViewById(R.id.OptionBtn).setOnClickListener(this.f8449m);
        TextView textView = (TextView) findViewById(R.id.cancel);
        this.f8441e = textView;
        textView.setOnClickListener(this.f8448l);
        f8436x = findViewById(R.id.toolbar);
        Intent intent = getIntent();
        f8438z = intent != null ? (Group) intent.getParcelableExtra("Group") : null;
        GroupAlbumObj groupAlbumObj = intent != null ? (GroupAlbumObj) intent.getParcelableExtra("album") : null;
        f8431s = groupAlbumObj;
        if (groupAlbumObj != null) {
            if (bundle != null) {
                Log.d("PhotoListActivity", "[onCreate] get previous PhotoListFragment instance.");
                this.f8440d = (C1629g) getSupportFragmentManager().mo1850g(bundle, C1629g.class.getName());
            }
            if (this.f8440d == null) {
                Log.d("PhotoListActivity", "[onCreate] mPhotoListFragment is null, create a new PhotoListFragment instance.");
                this.f8440d = new C1629g();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("albumobj", f8431s);
                this.f8440d.setArguments(bundle2);
            }
            if (bundle == null) {
                getSupportFragmentManager().mo1844a().m1980b(R.id.PhotoListFragmentContainer, this.f8440d).mo1794h();
            }
            f8435w = (TextView) findViewById(R.id.PhotoListTitleText);
            GroupAlbumObj groupAlbumObj2 = f8431s;
            String strM14676c = groupAlbumObj2 != null ? groupAlbumObj2.m14676c() : null;
            str = "";
            if (strM14676c != null) {
                GroupAlbumObj groupAlbumObj3 = f8431s;
                if ("Product".equals(groupAlbumObj3 != null ? groupAlbumObj3.m14677d() : "")) {
                    strM14676c = C5094a.m19947a(this, strM14676c);
                }
                str = strM14676c;
            }
            f8435w.setText(str);
        }
        if (intent != null && intent.getBooleanExtra("ShowShareToMyAlbum", false)) {
            z8 = true;
        }
        f8432t = Boolean.valueOf(z8);
        if (Globals.m7388i0().m7534d2()) {
            this.f8444h = true;
        }
        if (Group.m15744h(f8438z) || m9166P1()) {
            findViewById(R.id.addImageBtn).setVisibility(8);
        } else {
            findViewById(R.id.addImageBtn).setOnClickListener(this.f8447k);
        }
        GroupAlbumObj groupAlbumObj4 = f8431s;
        if (groupAlbumObj4 == null || groupAlbumObj4.m14679f().equals("backup")) {
            return;
        }
        this.f8445i = (TextView) findViewById(R.id.photoListBadge);
        XMPPManager.m14184g0().m14207H(this.f8453q);
        m9236q2();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C2925v.m14631y();
        GroupAlbumObj groupAlbumObj = f8431s;
        if (groupAlbumObj == null || groupAlbumObj.m14679f().equals("backup")) {
            return;
        }
        XMPPManager.m14184g0().m14233Z0(this.f8453q);
        this.f8453q = null;
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        C2907m0.m14454I().m14494V(this.f8454r);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        C2907m0.m14454I().m14510t(this.f8454r);
        m9236q2();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Log.d("PhotoListActivity", "[onSaveInstanceState] in");
        super.onSaveInstanceState(bundle);
        if (this.f8440d != null) {
            getSupportFragmentManager().mo1855l(bundle, C1629g.class.getName(), this.f8440d);
        }
    }

    /* renamed from: p2 */
    public final void m9235p2() {
        m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ia
            @Override // java.lang.Runnable
            public final void run() {
                this.f10751b.m9205j2();
            }
        });
    }

    /* renamed from: q2 */
    public void m9236q2() {
        if (C2907m0.m14454I().m14489N()) {
            m9232f1(false);
        } else {
            C2907m0.m14454I().m14511u(this.f8452p);
        }
    }

    /* renamed from: r2 */
    public final void m9237r2(List<ImageItem> list) {
        ImageItem imageItem;
        C3199c c3199c;
        GroupAlbumObj groupAlbumObj;
        ImageItem imageItem2;
        if (list.isEmpty()) {
            return;
        }
        if (this.f8443g == null) {
            C3199c c3199c2 = new C3199c();
            this.f8443g = c3199c2;
            c3199c2.m17033C(this.f8450n);
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
                        imageItem2.m16125F(imageItem3.m16140m());
                    } catch (JSONException e9) {
                        e = e9;
                        Log.d("PhotoListActivity", Log.getStackTraceString(e));
                        imageItem = imageItem2;
                        if (f8431s == null) {
                        }
                        if (imageItem != null) {
                        }
                        c3199c = this.f8443g;
                        if (c3199c == null) {
                        }
                    }
                } catch (JSONException e10) {
                    e = e10;
                    imageItem2 = imageItem3;
                }
                imageItem = imageItem2;
            }
            if (f8431s == null && imageItem != null && !TextUtils.isEmpty(imageItem.m16134g())) {
                this.f8443g.m17039q(f8431s.m14675b(), imageItem, null, null, imageItem3.m16134g(), "0");
            } else if (imageItem != null || TextUtils.isEmpty(imageItem.m16129b())) {
                c3199c = this.f8443g;
                if (c3199c == null && (groupAlbumObj = f8431s) != null) {
                    c3199c.m17039q(groupAlbumObj.m14675b(), imageItem, null, null, null, "0");
                }
            } else {
                Group groupM15077n = f8431s != null ? C2950b0.m14912k().m15077n(f8431s.m14679f()) : null;
                if (groupM15077n != null) {
                    this.f8443g.m17039q(f8431s.m14675b(), imageItem, groupM15077n.f13720g, imageItem.m16129b(), null, imageItem.m16128a());
                } else {
                    this.f8443g.m17039q(f8431s.m14675b(), imageItem, CLUtility.m16497V0(m9231N1()).f13787l, imageItem.m16129b(), null, imageItem.m16128a());
                }
            }
        }
        if (!this.f8439c.isShowing()) {
            m9231N1().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.n9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10922b.m9207k2();
                }
            });
        }
        this.f8443g.m17044v();
        this.f8443g.m17035F();
    }
}
