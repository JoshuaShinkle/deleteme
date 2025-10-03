package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.gms.plus.PlusShare;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5152i0;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5178r;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p173q2.C6127a;
import p173q2.C6129c;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class UserProfileActivity extends BaseActivity {

    /* renamed from: d */
    public FriendsClient f9393d;

    /* renamed from: e */
    public UserInfo f9394e;

    /* renamed from: f */
    public ProgressDialog f9395f;

    /* renamed from: g */
    public ImageView f9396g;

    /* renamed from: h */
    public ImageView f9397h;

    /* renamed from: i */
    public ImageView f9398i;

    /* renamed from: j */
    public RelativeLayout f9399j;

    /* renamed from: k */
    public View f9400k;

    /* renamed from: l */
    public TextView f9401l;

    /* renamed from: m */
    public TextView f9402m;

    /* renamed from: n */
    public TextView f9403n;

    /* renamed from: o */
    public View f9404o;

    /* renamed from: p */
    public TextView f9405p;

    /* renamed from: q */
    public View f9406q;

    /* renamed from: r */
    public TextView f9407r;

    /* renamed from: v */
    public EditText f9411v;

    /* renamed from: w */
    public String f9412w;

    /* renamed from: x */
    public EditText f9413x;

    /* renamed from: y */
    public String f9414y;

    /* renamed from: c */
    public int f9392c = 0;

    /* renamed from: s */
    public boolean f9408s = false;

    /* renamed from: t */
    public Uri f9409t = null;

    /* renamed from: u */
    public Uri f9410u = null;

    /* renamed from: z */
    public View.OnTouchListener f9415z = new ViewOnTouchListenerC1807b();

    /* renamed from: A */
    public TextView.OnEditorActionListener f9382A = new TextView.OnEditorActionListener() { // from class: com.cyberlink.you.activity.ik
        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
            return this.f10764b.m10620u0(textView, i9, keyEvent);
        }
    };

    /* renamed from: B */
    public View.OnClickListener f9383B = new View.OnClickListener() { // from class: com.cyberlink.you.activity.jk
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10799b.m10580H0(view);
        }
    };

    /* renamed from: C */
    public final View.OnClickListener f9384C = new View.OnClickListener() { // from class: com.cyberlink.you.activity.kk
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10835b.m10624x0(view);
        }
    };

    /* renamed from: D */
    public final View.OnClickListener f9385D = new View.OnClickListener() { // from class: com.cyberlink.you.activity.lk
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10869b.m10574D0(view);
        }
    };

    /* renamed from: E */
    public View.OnClickListener f9386E = new View.OnClickListener() { // from class: com.cyberlink.you.activity.mk
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10907b.m10576E0(view);
        }
    };

    /* renamed from: F */
    public View.OnClickListener f9387F = new ViewOnClickListenerC1810e();

    /* renamed from: G */
    public View.OnClickListener f9388G = new ViewOnClickListenerC1811f();

    /* renamed from: H */
    public View.OnClickListener f9389H = new View.OnClickListener() { // from class: com.cyberlink.you.activity.nk
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10934b.m10577F0(view);
        }
    };

    /* renamed from: I */
    public View.OnClickListener f9390I = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ok
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11021b.m10578G0(view);
        }
    };

    /* renamed from: J */
    public final View.OnClickListener f9391J = new ViewOnClickListenerC1812g();

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$a */
    public static /* synthetic */ class C1806a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f9416a;

        static {
            int[] iArr = new int[UploadUtils.UploadResultType.values().length];
            f9416a = iArr;
            try {
                iArr[UploadUtils.UploadResultType.STEP_3_SMALL_FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9416a[UploadUtils.UploadResultType.STEP_3_BIG_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9416a[UploadUtils.UploadResultType.STEP_3_SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$b */
    public class ViewOnTouchListenerC1807b implements View.OnTouchListener {
        public ViewOnTouchListenerC1807b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (view.getId() == UserProfileActivity.this.f9411v.getId()) {
                UserProfileActivity.this.f9411v.setFocusable(true);
                UserProfileActivity.this.f9411v.setFocusableInTouchMode(true);
                return false;
            }
            if (view.getId() != UserProfileActivity.this.f9413x.getId()) {
                return false;
            }
            UserProfileActivity.this.f9413x.setFocusable(true);
            UserProfileActivity.this.f9413x.setFocusableInTouchMode(true);
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$c */
    public class C1808c implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Runnable f9418a;

        /* renamed from: b */
        public final /* synthetic */ Permission f9419b;

        public C1808c(Runnable runnable, Permission permission) {
            this.f9418a = runnable;
            this.f9419b = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(UserProfileActivity.this, this.f9419b);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            this.f9418a.run();
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$d */
    public class C1809d implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Runnable f9421a;

        /* renamed from: b */
        public final /* synthetic */ int f9422b;

        public C1809d(Runnable runnable, int i9) {
            this.f9421a = runnable;
            this.f9422b = i9;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20261a(UserProfileActivity.this, this.f9422b);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            this.f9421a.run();
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$e */
    public class ViewOnClickListenerC1810e implements View.OnClickListener {
        public ViewOnClickListenerC1810e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (UserProfileActivity.this.f9394e == null) {
                return;
            }
            Intent intent = new Intent(UserProfileActivity.this, (Class<?>) ProfileInfoUpdateActivity.class);
            intent.putExtra("KeyItem", "displayName");
            intent.putExtra("Data", UserProfileActivity.this.f9394e.f13778c);
            intent.putExtra("Title", UserProfileActivity.this.getString(R.string.profile_display_name_update_title));
            UserProfileActivity.this.startActivityForResult(intent, 0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$f */
    public class ViewOnClickListenerC1811f implements View.OnClickListener {
        public ViewOnClickListenerC1811f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (UserProfileActivity.this.f9394e == null) {
                return;
            }
            Intent intent = new Intent(UserProfileActivity.this, (Class<?>) ProfileInfoUpdateActivity.class);
            intent.putExtra("KeyItem", "statusMessage");
            intent.putExtra("Data", UserProfileActivity.this.f9394e.f13780e);
            intent.putExtra("Title", UserProfileActivity.this.getString(R.string.profile_whats_up_title));
            UserProfileActivity.this.startActivityForResult(intent, 1);
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$g */
    public class ViewOnClickListenerC1812g implements View.OnClickListener {
        public ViewOnClickListenerC1812g() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m10651c(boolean z8) {
            UserProfileActivity.this.f9398i.setSelected(z8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m10652d(final boolean z8, String str, String str2, String str3, String str4) {
            UserProfileActivity.this.m10645l0();
            if (!"200".equals(str3)) {
                UserProfileActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.rk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11154b.m10651c(z8);
                    }
                });
                return;
            }
            UserProfileActivity userProfileActivity = UserProfileActivity.this;
            userProfileActivity.f9394e = userProfileActivity.m10630R0(str4);
            UserProfileActivity userProfileActivity2 = UserProfileActivity.this;
            CLUtility.m16527e(userProfileActivity2, userProfileActivity2.f9394e);
            UserProfileActivity.this.m10640b1();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws JSONException {
            final boolean zIsSelected = UserProfileActivity.this.f9398i.isSelected();
            UserProfileActivity.this.f9398i.setSelected(!zIsSelected);
            JSONObject jSONObjectM10629Q0 = UserProfileActivity.this.m10629Q0();
            if (jSONObjectM10629Q0 == null) {
                UserProfileActivity.this.f9398i.setSelected(zIsSelected);
                ULogUtility.m16676l("UserProfileActivity", "packageAttrsObject fail");
                return;
            }
            UserProfileActivity.this.m10636X0(R.string.processing, true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("attrs", jSONObjectM10629Q0.toString()));
            UserProfileActivity.this.f9393d.m15734m("user", "updateUser", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.qk
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f11124a.m10652d(zIsSelected, str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$h */
    public class C1813h extends UploadMediaHelper.AbstractC3185v {

        /* renamed from: a */
        public final /* synthetic */ UploadMediaHelper f9427a;

        public C1813h(UploadMediaHelper uploadMediaHelper) {
            this.f9427a = uploadMediaHelper;
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            if (this.f9427a.m16828Y0() == UploadUtils.UploadResultType.STEP_1_FAIL) {
                UserProfileActivity.this.m10641c1();
                ULogUtility.m16670f("UserProfileActivity", "sendUserAvatar onStep1 fail");
            }
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) {
            UploadUtils.UploadResultType uploadResultTypeM16828Y0 = uploadMediaHelper.m16828Y0();
            int i9 = C1806a.f9416a[uploadResultTypeM16828Y0.ordinal()];
            if (i9 == 1 || i9 == 2) {
                UserProfileActivity.this.m10641c1();
            } else if (i9 == 3) {
                UserProfileActivity.this.m10645l0();
                UserProfileActivity.this.m10647n0();
            }
            ULogUtility.m16670f("UserProfileActivity", "sendUserAvatar onStep3 resultType:" + uploadResultTypeM16828Y0.name());
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$i */
    public class C1814i extends UploadMediaHelper.AbstractC3185v {

        /* renamed from: a */
        public final /* synthetic */ UploadMediaHelper f9429a;

        public C1814i(UploadMediaHelper uploadMediaHelper) {
            this.f9429a = uploadMediaHelper;
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            if (this.f9429a.m16828Y0() == UploadUtils.UploadResultType.STEP_1_FAIL) {
                UserProfileActivity.this.m10641c1();
            }
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) {
            int i9 = C1806a.f9416a[uploadMediaHelper.m16828Y0().ordinal()];
            if (i9 == 1 || i9 == 2) {
                UserProfileActivity.this.m10641c1();
            } else {
                if (i9 != 3) {
                    return;
                }
                UserProfileActivity.this.m10645l0();
                UserProfileActivity.this.m10647n0();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.UserProfileActivity$j */
    public class C1815j implements FriendsClient.InterfaceC3051i {
        public C1815j() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            Log.d("UserProfileActivity", "[removeCustomAvatar] Response is " + str3);
            if (!"200".equals(str3)) {
                Log.d("UserProfileActivity", "[removeCustomAvatar] fail ");
            } else {
                UserProfileActivity.this.m10647n0();
                Log.d("UserProfileActivity", "[removeCustomAvatar] success ");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C0 */
    public /* synthetic */ void m10572C0(Dialog dialog, View view) {
        m10631S0();
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public /* synthetic */ void m10574D0(View view) {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_profile_edit_avatar_opt);
        dialogM16384c.findViewById(R.id.itemPhotoLibrary).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.uj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f11439b.m10626y0(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.itemTakePhoto).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.vj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f11828b.m10628z0(dialogM16384c, view2);
            }
        });
        if (C5170o0.m20170e(this.f9394e.f13779d)) {
            dialogM16384c.findViewById(R.id.itemRemovePhoto).setVisibility(8);
        } else {
            dialogM16384c.findViewById(R.id.itemRemovePhoto).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.wj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f12235b.m10572C0(dialogM16384c, view2);
                }
            });
        }
        CLUtility.m16578q2(this, dialogM16384c);
        dialogM16384c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E0 */
    public /* synthetic */ void m10576E0(View view) {
        startActivityForResult(new Intent(this, (Class<?>) ProfileInfoUpdateUserIDActivity.class), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F0 */
    public /* synthetic */ void m10577F0(View view) {
        startActivity(new Intent(getApplicationContext(), (Class<?>) QRCodeInviteActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G0 */
    public /* synthetic */ void m10578G0(View view) {
        startActivity(new Intent(this, (Class<?>) SignOutActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H0 */
    public /* synthetic */ void m10580H0(View view) {
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I0 */
    public /* synthetic */ void m10582I0(Dialog dialog, View view) {
        m10643j0(4);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J0 */
    public /* synthetic */ void m10584J0() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ImageItem imageItemM16432E0 = CLUtility.m16432E0(Globals.m7372O(), this.f9409t);
        if (imageItemM16432E0 != null) {
            imageItemM16432E0.m16123D(true);
        }
        int i9 = this.f9392c;
        if (i9 == 1) {
            m10646m0(imageItemM16432E0);
        } else if (i9 == 2) {
            m10636X0(R.string.uploading, true);
            m10635W0(imageItemM16432E0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K0 */
    public /* synthetic */ void m10585K0(View view) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int height = view.getRootView().getHeight();
        int i9 = height - rect.bottom;
        Log.d("UserProfileActivity", "keypadHeight = " + i9);
        if (i9 <= height * 0.15d) {
            this.f9411v.setFocusable(false);
            this.f9413x.setFocusable(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L0 */
    public /* synthetic */ void m10587L0(int i9, boolean z8) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        this.f9395f = ProgressDialog.show(this, "", getString(i9), z8);
    }

    /* renamed from: M0 */
    public static /* synthetic */ void m10588M0(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m10590N0(String str) {
        this.f9411v.setText(this.f9412w);
        this.f9413x.setText(this.f9414y);
        if ("403".equals(str)) {
            m10637Y0();
        } else {
            C5187v0.m20267c(R.string.error_server_response);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O0 */
    public /* synthetic */ void m10592O0(ArrayList arrayList, DialogC3133q dialogC3133q) throws JSONException {
        FriendsClient friendsClient = new FriendsClient();
        Pair<String, String> pairM15731j = friendsClient.m15731j("user", "updateUser", arrayList);
        final String str = (String) pairM15731j.first;
        String str2 = (String) pairM15731j.second;
        if ("200".equals(str)) {
            UserInfo userInfoM10630R0 = m10630R0(str2);
            if (userInfoM10630R0 != null) {
                this.f9394e = userInfoM10630R0;
                CLUtility.m16527e(this, userInfoM10630R0);
                m10640b1();
            }
        } else {
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.gk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10700b.m10590N0(str);
                }
            });
        }
        Objects.requireNonNull(dialogC3133q);
        runOnUiThread(new RunnableC2569v(dialogC3133q));
        friendsClient.m15717U0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P0 */
    public /* synthetic */ void m10594P0() {
        if (C5170o0.m20169d(this.f9394e.f13781f)) {
            this.f9399j.setOnClickListener(this.f9386E);
        } else {
            this.f9400k.setVisibility(8);
        }
        C6129c.m23486g(this, this.f9396g, this.f9394e);
        C6127a.m23473n(this, this.f9397h, this.f9394e);
        this.f9398i.setSelected(this.f9394e.f13793r);
        this.f9401l.setText(this.f9394e.f13781f);
        this.f9402m.setText(this.f9394e.f13778c);
        this.f9403n.setText(this.f9394e.f13780e);
        this.f9411v.setText(this.f9394e.f13789n);
        this.f9412w = this.f9411v.getText().toString();
        this.f9413x.setText(this.f9394e.f13791p);
        this.f9414y = this.f9413x.getText().toString();
        m10638Z0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m10612p0(int i9) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            Uri uriM16468N0 = CLUtility.m16468N0();
            this.f9409t = uriM16468N0;
            if (uriM16468N0 == null) {
                return;
            }
            intent.putExtra("output", uriM16468N0);
            startActivityForResult(intent, i9);
            Globals.m7388i0().m7526b3(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m10614q0(int i9) {
        if (C5178r.m20243l(this, i9)) {
            Globals.m7388i0().m7526b3(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m10616r0() {
        C5152i0.m20065b(this.f9395f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m10618s0(String str, String str2, String str3, String str4) throws JSONException {
        if ("200".equals(str3)) {
            UserInfo userInfoM10630R0 = m10630R0(str4);
            this.f9394e = userInfoM10630R0;
            CLUtility.m16527e(this, userInfoM10630R0);
            Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(this.f9394e.f13777b));
            if (friendM15003C != null) {
                UserInfo userInfo = this.f9394e;
                friendM15003C.f13647e = userInfo.f13779d;
                friendM15003C.f13650h = userInfo.f13785j;
                C2950b0.m14899A().m15019k(friendM15003C, false, true);
            }
            m10640b1();
            Log.i("UserProfileActivity", "[getSelfInfo] succeed");
        } else {
            ULogUtility.m16670f("UserProfileActivity", "getSelfInfo fail statusCode:" + str3);
        }
        if (isFinishing()) {
            return;
        }
        m10645l0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ boolean m10620u0(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 == 6) {
            this.f9411v.setFocusable(false);
            this.f9413x.setFocusable(false);
            m10639a1();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w0 */
    public /* synthetic */ void m10623w0(Dialog dialog, View view) {
        m10642i0(6);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ void m10624x0(View view) {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_profile_edit_avatar_opt);
        dialogM16384c.findViewById(R.id.itemPhotoLibrary).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.yj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f12285b.m10582I0(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.itemTakePhoto).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.zj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f12313b.m10623w0(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.itemRemovePhoto).setVisibility(8);
        CLUtility.m16578q2(this, dialogM16384c);
        dialogM16384c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m10626y0(Dialog dialog, View view) {
        m10643j0(3);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z0 */
    public /* synthetic */ void m10628z0(Dialog dialog, View view) {
        m10642i0(5);
        dialog.dismiss();
    }

    /* renamed from: Q0 */
    public final JSONObject m10629Q0() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("profile.publicId.enabled", this.f9398i.isSelected() ? "1" : "0");
            return jSONObject;
        } catch (JSONException unused) {
            Log.e("UserProfileActivity", "[packageAttrsObject] Fail");
            return null;
        }
    }

    /* renamed from: R0 */
    public final UserInfo m10630R0(String str) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            Log.e("UserProfileActivity", "[SuggestionListFriends] Parse error. JSONstr=" + str);
            jSONObject = null;
        }
        if (jSONObject != null) {
            try {
                jSONObject2 = jSONObject.getJSONObject("result");
            } catch (JSONException unused2) {
                Log.e("UserProfileActivity", "[SuggestionListFriends] 'results' missing. JSONstr=" + str);
            }
        } else {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            return C5172p.m20197s(jSONObject2);
        }
        return null;
    }

    /* renamed from: S0 */
    public final void m10631S0() {
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        this.f9393d.m15734m("media", "deleteUserAvatar", arrayList, new C1815j());
    }

    /* renamed from: T0 */
    public final void m10632T0(Permission permission, Runnable runnable) {
        C5287b.m20583f(permission, new C1808c(runnable, permission), this);
    }

    /* renamed from: U0 */
    public final void m10633U0(Permission[] permissionArr, Runnable runnable, int i9) {
        C5287b.m20584g(permissionArr, new C1809d(runnable, i9), this);
    }

    /* renamed from: V0 */
    public final void m10634V0(ImageItem imageItem) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(this.f9394e.f13783h, imageItem);
        uploadMediaHelper.m16815R1(new C1813h(uploadMediaHelper));
        uploadMediaHelper.m16819T1();
    }

    /* renamed from: W0 */
    public final void m10635W0(ImageItem imageItem) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(this.f9394e.f13784i, imageItem);
        uploadMediaHelper.m16815R1(new C1814i(uploadMediaHelper));
        uploadMediaHelper.m16819T1();
    }

    /* renamed from: X0 */
    public final void m10636X0(final int i9, final boolean z8) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bk
            @Override // java.lang.Runnable
            public final void run() {
                this.f9759b.m10587L0(i9, z8);
            }
        });
    }

    /* renamed from: Y0 */
    public final void m10637Y0() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.profile_update_organization_error));
        builderM16382a.setPositiveButton(getString(R.string.close), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.hk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                UserProfileActivity.m10588M0(dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.show();
    }

    /* renamed from: Z0 */
    public final void m10638Z0() {
        this.f9405p.setText(getString(R.string.friends_invitation_selector_string_email));
        boolean zM7583n0 = Globals.m7388i0().m7583n0();
        this.f9404o.findViewById(R.id.EditGotoImageView).setVisibility(8);
        if (zM7583n0) {
            this.f9407r.setText(Globals.m7388i0().m7498V0());
            this.f9406q.setVisibility(8);
        }
    }

    /* renamed from: a1 */
    public final void m10639a1() {
        final ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (!this.f9411v.getText().toString().equalsIgnoreCase(this.f9412w)) {
            arrayList.add(new C6301o("organization", this.f9411v.getText().toString()));
        }
        if (!this.f9413x.getText().toString().equalsIgnoreCase(this.f9414y)) {
            arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.f9413x.getText().toString()));
        }
        if (arrayList.size() < 2) {
            return;
        }
        final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16413d(0L).m16411b();
        C6385v.m24526d(new Runnable() { // from class: com.cyberlink.you.activity.ck
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                this.f10351b.m10592O0(arrayList, dialogC3133qM16411b);
            }
        });
    }

    /* renamed from: b1 */
    public final void m10640b1() {
        if (this.f9394e == null) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.xj
            @Override // java.lang.Runnable
            public final void run() {
                this.f12259b.m10594P0();
            }
        });
    }

    /* renamed from: c1 */
    public final void m10641c1() {
        m10645l0();
        C5187v0.m20267c(R.string.error_server_response);
    }

    /* renamed from: i0 */
    public final void m10642i0(final int i9) {
        Permission[] permissionArr;
        int i10;
        if (Build.VERSION.SDK_INT >= 33) {
            permissionArr = new Permission[]{Permission.IMAGE, Permission.CAMERA};
            i10 = R.string.permission_ask_photo_video_camera;
        } else {
            permissionArr = new Permission[]{Permission.STORAGE, Permission.CAMERA};
            i10 = R.string.permission_ask_storage_camera;
        }
        m10633U0(permissionArr, new Runnable() { // from class: com.cyberlink.you.activity.dk
            @Override // java.lang.Runnable
            public final void run() {
                this.f10380b.m10612p0(i9);
            }
        }, i10);
    }

    /* renamed from: j0 */
    public final void m10643j0(final int i9) {
        m10632T0(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new Runnable() { // from class: com.cyberlink.you.activity.fk
            @Override // java.lang.Runnable
            public final void run() {
                this.f10500b.m10614q0(i9);
            }
        });
    }

    /* renamed from: k0 */
    public final boolean m10644k0(Uri uri, Uri uri2, int i9) {
        if (!CLUtility.m16613z1(null, uri)) {
            return false;
        }
        boolean zM20240i = C5178r.m20240i(this, 7, uri, uri2, i9);
        if (zM20240i) {
            this.f9409t = uri2;
            Globals.m7388i0().m7526b3(true);
        }
        return zM20240i;
    }

    /* renamed from: l0 */
    public final void m10645l0() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.pk
            @Override // java.lang.Runnable
            public final void run() {
                this.f11052b.m10616r0();
            }
        });
    }

    /* renamed from: m0 */
    public final void m10646m0(ImageItem imageItem) {
        if (imageItem == null) {
            ULogUtility.m16670f("UserProfileActivity", "doUpdateAvatar can not get image");
            return;
        }
        imageItem.m16122C(this.f9394e.f13777b);
        imageItem.m16126G(CLUtility.m16552k0(imageItem.m16144q(), CLUtility.m16510Z1(imageItem.m16145r())));
        if (isFinishing()) {
            return;
        }
        m10636X0(R.string.uploading, true);
        m10634V0(imageItem);
    }

    /* renamed from: n0 */
    public final void m10647n0() {
        m10636X0(R.string.processing, true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        this.f9393d.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.ak
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f9729a.m10618s0(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: o0 */
    public final void m10648o0() {
        findViewById(R.id.UserProfileBackBtn).setOnClickListener(this.f9383B);
        findViewById(R.id.UserProfileCoverArea).setOnClickListener(this.f9384C);
        findViewById(R.id.UserProfileAvatarArea).setOnClickListener(this.f9385D);
        this.f9396g = (ImageView) findViewById(R.id.cover);
        this.f9397h = (ImageView) findViewById(R.id.UserProfileAvatarData);
        View viewFindViewById = findViewById(R.id.UserEmailArea);
        this.f9404o = viewFindViewById;
        this.f9405p = (TextView) viewFindViewById.findViewById(R.id.EditGotoTitleTextView);
        this.f9407r = (TextView) this.f9404o.findViewById(R.id.EditGotoTextView);
        this.f9406q = this.f9404o.findViewById(R.id.EditWarningImageView);
        View viewFindViewById2 = findViewById(R.id.UserProfileSharePublicIdArea);
        viewFindViewById2.setOnClickListener(this.f9391J);
        ((TextView) viewFindViewById2.findViewById(R.id.EditCheckTextView)).setText(getString(R.string.profile_share_public_id_title));
        this.f9398i = (ImageView) viewFindViewById2.findViewById(R.id.EditCheckImageView);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.UserProfilePublicId);
        this.f9399j = relativeLayout;
        ((TextView) relativeLayout.findViewById(R.id.EditGotoTitleTextView)).setText(getString(R.string.profile_public_id_title));
        this.f9401l = (TextView) this.f9399j.findViewById(R.id.EditGotoTextView);
        this.f9400k = this.f9399j.findViewById(R.id.EditGotoImageView);
        View viewFindViewById3 = findViewById(R.id.UserProfileDisplayNameArea);
        viewFindViewById3.setOnClickListener(this.f9387F);
        this.f9402m = (TextView) viewFindViewById3.findViewById(R.id.EditGotoTextView);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.UserProfileWhatUpInfoArea);
        this.f9403n = (TextView) relativeLayout2.findViewById(R.id.EditGotoTitleTextView);
        relativeLayout2.setOnClickListener(this.f9388G);
        View viewFindViewById4 = findViewById(R.id.UserProfileQrCodeArea);
        viewFindViewById4.setOnClickListener(this.f9389H);
        ((TextView) viewFindViewById4.findViewById(R.id.EditGotoTitleTextView)).setText(getString(R.string.profile_qr_code_title));
        View viewFindViewById5 = findViewById(R.id.SignOut);
        viewFindViewById5.setOnClickListener(this.f9390I);
        TextView textView = (TextView) viewFindViewById5.findViewById(R.id.EditGotoTitleTextView);
        textView.setTextColor(getResources().getColor(R.color.you_color_red));
        textView.setText(getString(R.string.setting_sign_out));
        EditText editText = (EditText) findViewById(R.id.CompanyEditText);
        this.f9411v = editText;
        editText.setImeOptions(6);
        this.f9411v.setRawInputType(1);
        this.f9411v.setOnEditorActionListener(this.f9382A);
        this.f9411v.setOnTouchListener(this.f9415z);
        EditText editText2 = (EditText) findViewById(R.id.TitleEditText);
        this.f9413x = editText2;
        editText2.setImeOptions(6);
        this.f9413x.setRawInputType(1);
        this.f9413x.setOnEditorActionListener(this.f9382A);
        this.f9413x.setOnTouchListener(this.f9415z);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(getApplicationContext());
        this.f9394e = userInfoM16497V0;
        if (-1 == i10) {
            this.f9408s = true;
        }
        if (i9 == 0) {
            if (i10 == -1) {
                userInfoM16497V0.f13778c = intent.getStringExtra("Data");
                CLUtility.m16527e(this, this.f9394e);
            }
            m10640b1();
            return;
        }
        if (i9 == 1) {
            if (i10 == -1) {
                userInfoM16497V0.f13780e = intent.getStringExtra("Data");
                CLUtility.m16527e(this, this.f9394e);
            }
            m10640b1();
            return;
        }
        if (i9 == 2) {
            if (i10 == -1) {
                userInfoM16497V0.f13781f = intent.getStringExtra("Data");
                if (!this.f9394e.f13781f.equals("")) {
                    this.f9399j.setClickable(false);
                    this.f9400k.setVisibility(8);
                }
            }
            m10640b1();
            return;
        }
        if (i9 == 3) {
            Globals.m7388i0().m7526b3(false);
            if (i10 == -1) {
                this.f9392c = 1;
                Uri uriM16587t = CLUtility.m16587t(intent.getData());
                this.f9410u = uriM16587t;
                if (uriM16587t == null) {
                    uriM16587t = intent.getData();
                    this.f9410u = uriM16587t;
                }
                if (uriM16587t == null) {
                    ULogUtility.m16670f("UserProfileActivity", "select avatar is null");
                    return;
                }
                Uri uriM16468N0 = CLUtility.m16468N0();
                if (m10644k0(uriM16587t, uriM16468N0, 512)) {
                    return;
                }
                ULogUtility.m16670f("UserProfileActivity", "crop image fail");
                this.f9409t = uriM16587t;
                ImageItem imageItemM16432E0 = CLUtility.m16432E0(this, uriM16587t);
                if (imageItemM16432E0 != null) {
                    imageItemM16432E0.m16123D(false);
                }
                m10646m0(imageItemM16432E0);
                if (uriM16468N0 != null) {
                    Globals.m7372O().getContentResolver().delete(uriM16468N0, null, null);
                    return;
                }
                return;
            }
            return;
        }
        if (i9 == 4) {
            if (i10 == -1) {
                this.f9392c = 2;
                Uri uriM16587t2 = CLUtility.m16587t(intent.getData());
                if (uriM16587t2 == null) {
                    uriM16587t2 = intent.getData();
                }
                Uri uriM16468N02 = CLUtility.m16468N0();
                if (uriM16468N02 != null ? m10644k0(uriM16587t2, uriM16468N02, 1080) : false) {
                    return;
                }
                this.f9409t = uriM16587t2;
                m10636X0(R.string.uploading, true);
                ImageItem imageItemM16432E02 = CLUtility.m16432E0(this, uriM16587t2);
                if (imageItemM16432E02 != null) {
                    imageItemM16432E02.m16123D(false);
                }
                m10635W0(imageItemM16432E02);
                if (uriM16468N02 != null) {
                    Globals.m7372O().getContentResolver().delete(uriM16468N02, null, null);
                    return;
                }
                return;
            }
            return;
        }
        if (i9 == 5) {
            Globals.m7388i0().m7526b3(false);
            if (i10 == -1) {
                this.f9392c = 1;
                Uri uriM16468N03 = CLUtility.m16468N0();
                Uri uri = this.f9409t;
                this.f9410u = uri;
                if (m10644k0(uri, uriM16468N03, 512)) {
                    return;
                }
                ImageItem imageItemM16432E03 = CLUtility.m16432E0(Globals.m7372O(), this.f9409t);
                if (imageItemM16432E03 != null) {
                    imageItemM16432E03.m16123D(false);
                }
                m10646m0(imageItemM16432E03);
                if (uriM16468N03 != null) {
                    Globals.m7372O().getContentResolver().delete(uriM16468N03, null, null);
                    return;
                }
                return;
            }
            return;
        }
        if (i9 == 6) {
            Globals.m7388i0().m7526b3(false);
            if (i10 != -1 || this.f9409t == null) {
                return;
            }
            this.f9392c = 2;
            Uri uriM16468N04 = CLUtility.m16468N0();
            if (m10644k0(this.f9409t, uriM16468N04, 1080)) {
                return;
            }
            ImageItem imageItemM16432E04 = CLUtility.m16432E0(Globals.m7372O(), this.f9409t);
            if (imageItemM16432E04 != null) {
                imageItemM16432E04.m16123D(false);
            }
            m10635W0(imageItemM16432E04);
            if (uriM16468N04 != null) {
                Globals.m7372O().getContentResolver().delete(uriM16468N04, null, null);
                return;
            }
            return;
        }
        if (i9 == 7) {
            Globals.m7388i0().m7526b3(false);
            if (i10 == -1) {
                this.f9410u = null;
                UploadMediaHelper.m16715O1(this.f9409t, new Runnable() { // from class: com.cyberlink.you.activity.ek
                    @Override // java.lang.Runnable
                    public final void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                        this.f10414b.m10584J0();
                    }
                });
                return;
            }
            if (this.f9410u != null) {
                this.f9392c = 1;
                Uri uriM16468N05 = CLUtility.m16468N0();
                ULogUtility.m16670f("UserProfileActivity", "'crop image fail' from inbuilt corp editor");
                ImageItem imageItemM16432E05 = CLUtility.m16432E0(this, this.f9410u);
                if (imageItemM16432E05 != null) {
                    imageItemM16432E05.m16123D(false);
                }
                m10646m0(imageItemM16432E05);
                if (uriM16468N05 != null) {
                    Globals.m7372O().getContentResolver().delete(uriM16468N05, null, null);
                }
                this.f9410u = null;
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        CLUtility.m16589t1(this);
        setResult(this.f9408s ? -1 : 0);
        finish();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_profile);
        if (bundle != null && bundle.containsKey("cameraUri")) {
            this.f9409t = CLUtility.m16510Z1(bundle.getString("cameraUri"));
        }
        m10648o0();
        this.f9393d = new FriendsClient(true);
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(this);
        this.f9394e = userInfoM16497V0;
        if (userInfoM16497V0 != null) {
            m10640b1();
        } else {
            finish();
        }
        final View viewFindViewById = findViewById(android.R.id.content);
        viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.cyberlink.you.activity.tj
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f11412b.m10585K0(viewFindViewById);
            }
        });
        m10647n0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        FriendsClient friendsClient = this.f9393d;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        m10645l0();
        super.onDestroy();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m10638Z0();
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Uri uri = this.f9409t;
        if (uri != null) {
            bundle.putString("cameraUri", uri.toString());
        }
        super.onSaveInstanceState(bundle);
    }
}
