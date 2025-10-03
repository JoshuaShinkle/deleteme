package com.cyberlink.you.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupEditActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p042d0.C4619d;
import p116k4.C5152i0;
import p116k4.C5154j;
import p116k4.C5172p;
import p116k4.C5178r;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p173q2.C6127a;
import p201t3.C6301o;
import p209u2.C6383t;
import p209u2.C6385v;
import p244y1.C6596a;

/* loaded from: classes.dex */
public class GroupEditActivity extends BaseActivity {

    /* renamed from: c */
    public FriendsClient f7766c;

    /* renamed from: g */
    public EditText f7770g;

    /* renamed from: h */
    public EditText f7771h;

    /* renamed from: i */
    public RelativeLayout f7772i;

    /* renamed from: j */
    public ImageView f7773j;

    /* renamed from: k */
    public ProgressDialog f7774k;

    /* renamed from: l */
    public ImageItem f7775l;

    /* renamed from: m */
    public Button f7776m;

    /* renamed from: n */
    public Group f7777n;

    /* renamed from: o */
    public ExpandableListView f7778o;

    /* renamed from: p */
    public C6596a f7779p;

    /* renamed from: t */
    public String f7783t;

    /* renamed from: u */
    public boolean f7784u;

    /* renamed from: v */
    public UserInfo f7785v;

    /* renamed from: w */
    public boolean f7786w;

    /* renamed from: x */
    public boolean f7787x;

    /* renamed from: d */
    public C4619d f7767d = null;

    /* renamed from: e */
    public List<Friend> f7768e = new ArrayList();

    /* renamed from: f */
    public List<Friend> f7769f = new ArrayList();

    /* renamed from: q */
    public String f7780q = "EditStatus";

    /* renamed from: r */
    public String f7781r = "member";

    /* renamed from: s */
    public Uri f7782s = null;

    /* renamed from: y */
    @SuppressLint({"UseSparseArrays"})
    public Map<Long, Map<String, Object>> f7788y = new HashMap();

    /* renamed from: z */
    public View.OnClickListener f7789z = new View.OnClickListener() { // from class: com.cyberlink.you.activity.o4
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10998b.m8309Y0(view);
        }
    };

    /* renamed from: A */
    public View.OnClickListener f7760A = new ViewOnClickListenerC1493f();

    /* renamed from: B */
    public View.OnClickListener f7761B = new ViewOnClickListenerC1494g();

    /* renamed from: C */
    public View.OnClickListener f7762C = new ViewOnClickListenerC1495h();

    /* renamed from: D */
    public View.OnClickListener f7763D = new ViewOnClickListenerC1496i();

    /* renamed from: E */
    public final View.OnClickListener f7764E = new View.OnClickListener() { // from class: com.cyberlink.you.activity.q4
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11071b.m8314b1(view);
        }
    };

    /* renamed from: F */
    public TextWatcher f7765F = new C1490c();

    public enum EditStatus {
        Added,
        Removed
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$a */
    public class C1488a extends UploadMediaHelper.AbstractC3185v {

        /* renamed from: a */
        public final /* synthetic */ UploadMediaHelper f7793a;

        public C1488a(UploadMediaHelper uploadMediaHelper) {
            this.f7793a = uploadMediaHelper;
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            if (this.f7793a.m16828Y0() == UploadUtils.UploadResultType.STEP_1_FAIL) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.m8378x1(groupEditActivity.f7774k);
            }
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) {
            int i9 = C1492e.f7800a[uploadMediaHelper.m16828Y0().ordinal()];
            if (i9 == 1 || i9 == 2) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.m8378x1(groupEditActivity.f7774k);
            } else {
                if (i9 != 3) {
                    return;
                }
                GroupEditActivity.this.f7784u = true;
                if (GroupEditActivity.this.f7777n != null) {
                    GroupEditActivity.this.m8359I0();
                    if (GroupEditActivity.this.f7786w) {
                        GroupEditActivity groupEditActivity2 = GroupEditActivity.this;
                        groupEditActivity2.m8365j1(groupEditActivity2.f7777n);
                    }
                }
                C5152i0.m20065b(GroupEditActivity.this.f7774k);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$b */
    public class C1489b extends UploadMediaHelper.AbstractC3185v {

        /* renamed from: a */
        public final /* synthetic */ UploadMediaHelper f7795a;

        public C1489b(UploadMediaHelper uploadMediaHelper) {
            this.f7795a = uploadMediaHelper;
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            if (this.f7795a.m16828Y0() == UploadUtils.UploadResultType.STEP_1_FAIL) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.m8378x1(groupEditActivity.f7774k);
            }
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) throws Throwable {
            int i9 = C1492e.f7800a[uploadMediaHelper.m16828Y0().ordinal()];
            if (i9 == 1 || i9 == 2) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.m8378x1(groupEditActivity.f7774k);
            } else {
                if (i9 != 3) {
                    return;
                }
                GroupEditActivity groupEditActivity2 = GroupEditActivity.this;
                groupEditActivity2.m8355E0(groupEditActivity2.f7777n.f13719f, GroupEditActivity.this.f7775l);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$c */
    public class C1490c implements TextWatcher {
        public C1490c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            GroupEditActivity.this.f7779p.getFilter().filter(charSequence.toString());
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$d */
    public class AsyncTaskC1491d extends AsyncTask<Void, Void, Group> {

        /* renamed from: a */
        public final /* synthetic */ String f7798a;

        public AsyncTaskC1491d(String str) {
            this.f7798a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Group doInBackground(Void... voidArr) {
            Group groupM15650P = FriendsClient.m15650P(this.f7798a);
            if (groupM15650P != null) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.f7783t = groupEditActivity.f7777n.f13705D;
                C2950b0.m14912k().m15062A(String.valueOf(groupM15650P.f13727n), groupM15650P, "circleType");
            }
            return groupM15650P;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Group group) {
            if (group != null) {
                GroupEditActivity.this.m8361K0();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$e */
    public static /* synthetic */ class C1492e {

        /* renamed from: a */
        public static final /* synthetic */ int[] f7800a;

        static {
            int[] iArr = new int[UploadUtils.UploadResultType.values().length];
            f7800a = iArr;
            try {
                iArr[UploadUtils.UploadResultType.STEP_3_SMALL_FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7800a[UploadUtils.UploadResultType.STEP_3_BIG_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7800a[UploadUtils.UploadResultType.STEP_3_SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$f */
    public class ViewOnClickListenerC1493f implements View.OnClickListener {
        public ViewOnClickListenerC1493f() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m8386b(String str, String str2, String str3, String str4) {
            if (!"200".equals(str3)) {
                Log.d("GroupEditActivity", "Create group fail.");
                if (GroupEditActivity.this.isFinishing()) {
                    return;
                }
                C5152i0.m20065b(GroupEditActivity.this.f7774k);
                return;
            }
            Group groupM8330k1 = GroupEditActivity.m8330k1(str4);
            GroupEditActivity.this.f7777n = groupM8330k1;
            C2950b0.m14912k().m15069f(groupM8330k1);
            if (GroupEditActivity.this.f7775l != null) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.m8356F0(groupM8330k1.f13722i, groupEditActivity.f7775l);
                return;
            }
            C2950b0.m14912k().m15069f(groupM8330k1);
            if (!GroupEditActivity.this.isFinishing()) {
                C5152i0.m20065b(GroupEditActivity.this.f7774k);
            }
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Group", groupM8330k1);
            intent.putExtras(bundle);
            GroupEditActivity.this.setResult(-1, intent);
            GroupEditActivity.this.finish();
            GroupEditActivity groupEditActivity2 = GroupEditActivity.this;
            groupEditActivity2.m8365j1(groupEditActivity2.f7777n);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string = GroupEditActivity.this.f7770g.getText().toString();
            if (string.trim().length() == 0) {
                C5187v0.m20267c(R.string.group_empty_title);
                return;
            }
            CLUtility.m16589t1(GroupEditActivity.this);
            GroupEditActivity groupEditActivity = GroupEditActivity.this;
            groupEditActivity.f7774k = ProgressDialog.show(groupEditActivity.m8357G0(), "", GroupEditActivity.this.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            Iterator<Friend> it = GroupEditActivity.this.f7779p.m25219h().iterator();
            while (it.hasNext()) {
                arrayList.add(new C6301o("adminId", Long.toString(it.next().f13645c)));
            }
            Iterator<Friend> it2 = GroupEditActivity.this.f7779p.m25221j().iterator();
            while (it2.hasNext()) {
                arrayList.add(new C6301o("userId", Long.toString(it2.next().f13645c)));
            }
            arrayList.add(new C6301o("groupType", "Circle"));
            arrayList.add(new C6301o("displayName", string));
            arrayList.add(new C6301o("circleType", GroupEditActivity.this.f7783t));
            GroupEditActivity.this.f7766c.m15734m("group", "create", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.y4
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f12268a.m8386b(str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$g */
    public class ViewOnClickListenerC1494g implements View.OnClickListener {
        public ViewOnClickListenerC1494g() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m8388b(String str, String str2, String str3, String str4) {
            Log.d("GroupEditActivity", "[onAnonymousGroupUpdateClick] statusCode = " + str3);
            Log.d("GroupEditActivity", "[onAnonymousGroupUpdateClick] jsonStr = " + str4);
            if (!"200".equals(str3)) {
                Log.d("GroupEditActivity", "Create group fail.");
                if (GroupEditActivity.this.isFinishing()) {
                    return;
                }
                C5152i0.m20065b(GroupEditActivity.this.f7774k);
                return;
            }
            Group groupM8330k1 = GroupEditActivity.m8330k1(str4);
            GroupEditActivity.this.f7777n = groupM8330k1;
            C2950b0.m14912k().m15069f(groupM8330k1);
            if (GroupEditActivity.this.f7775l != null) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.m8356F0(groupM8330k1.f13722i, groupEditActivity.f7775l);
                return;
            }
            C2950b0.m14912k().m15069f(groupM8330k1);
            if (!GroupEditActivity.this.isFinishing()) {
                C5152i0.m20065b(GroupEditActivity.this.f7774k);
            }
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Group", groupM8330k1);
            intent.putExtras(bundle);
            GroupEditActivity.this.setResult(-1, intent);
            GroupEditActivity.this.finish();
            GroupEditActivity groupEditActivity2 = GroupEditActivity.this;
            groupEditActivity2.m8365j1(groupEditActivity2.f7777n);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string = GroupEditActivity.this.f7770g.getText().toString();
            if (C6383t.m24517f(string)) {
                return;
            }
            CLUtility.m16589t1(GroupEditActivity.this);
            GroupEditActivity groupEditActivity = GroupEditActivity.this;
            groupEditActivity.f7774k = ProgressDialog.show(groupEditActivity.m8357G0(), "", GroupEditActivity.this.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("groupId", String.valueOf(GroupEditActivity.this.f7777n.f13727n)));
            arrayList.add(new C6301o("groupType", "Circle"));
            arrayList.add(new C6301o("displayName", string));
            Log.d("GroupEditActivity", "[onAnonymousGroupUpdateClick] params = " + arrayList);
            GroupEditActivity.this.f7766c.m15734m("group", "update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.z4
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f12295a.m8388b(str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$h */
    public class ViewOnClickListenerC1495h implements View.OnClickListener {
        public ViewOnClickListenerC1495h() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m8391c(Friend friend, DialogInterface dialogInterface, int i9) {
            GroupEditActivity.this.m8367m1(friend);
        }

        /* renamed from: d */
        public static /* synthetic */ void m8392d(DialogInterface dialogInterface, int i9) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final Friend friend = (Friend) view.getTag();
            if (GroupEditActivity.this.f7777n == null) {
                GroupEditActivity.this.f7779p.m25223l(friend);
                GroupEditActivity.this.f7778o.invalidateViews();
            } else {
                if (Globals.m7388i0().m7474Q1(String.valueOf(friend.f13645c))) {
                    GroupEditActivity.this.m8367m1(friend);
                    return;
                }
                AlertDialog.Builder builderM16382a = C3123g.m16382a(GroupEditActivity.this);
                builderM16382a.setMessage(GroupEditActivity.this.getString(R.string.S_is_one_of_the_admins_are_you_sure_to_remove_him_her_from_the_admins, friend.m15620a()));
                builderM16382a.setPositiveButton(GroupEditActivity.this.getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.a5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f9706b.m8391c(friend, dialogInterface, i9);
                    }
                });
                builderM16382a.setNegativeButton(GroupEditActivity.this.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.b5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        GroupEditActivity.ViewOnClickListenerC1495h.m8392d(dialogInterface, i9);
                    }
                });
                builderM16382a.show();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$i */
    public class ViewOnClickListenerC1496i implements View.OnClickListener {
        public ViewOnClickListenerC1496i() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m8399g(Friend friend, DialogInterface dialogInterface, int i9) {
            GroupEditActivity.this.m8363h1(friend);
        }

        /* renamed from: h */
        public static /* synthetic */ void m8400h(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m8401i(Friend friend, DialogInterface dialogInterface, int i9) {
            GroupEditActivity.this.m8363h1(friend);
        }

        /* renamed from: j */
        public static /* synthetic */ void m8402j(DialogInterface dialogInterface, int i9) {
        }

        /* renamed from: k */
        public static /* synthetic */ void m8403k(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l */
        public /* synthetic */ void m8404l(Friend friend, DialogInterface dialogInterface, int i9) {
            GroupEditActivity.this.m8368n1(friend);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final Friend friend = (Friend) view.getTag();
            ULogUtility.m16670f("GroupEditActivity", "Remove member click : " + friend.f13645c);
            if (GroupEditActivity.this.f7777n == null) {
                GroupEditActivity.this.f7779p.m25224m(friend);
                GroupEditActivity.this.f7779p.m25223l(friend);
                GroupEditActivity.this.m8375u1(friend);
                GroupEditActivity.this.f7778o.invalidateViews();
                return;
            }
            if (!Globals.m7388i0().m7474Q1(String.valueOf(friend.f13645c))) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(GroupEditActivity.this);
                builderM16382a.setMessage(GroupEditActivity.this.getString(R.string.this_will_remove_S_from_the_group_do_you_want_to_continue, friend.m15620a()));
                builderM16382a.setPositiveButton(GroupEditActivity.this.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.g5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        GroupEditActivity.ViewOnClickListenerC1496i.m8403k(dialogInterface, i9);
                    }
                });
                builderM16382a.setNegativeButton(GroupEditActivity.this.getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.h5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f10712b.m8404l(friend, dialogInterface, i9);
                    }
                });
                builderM16382a.show();
                return;
            }
            AlertDialog.Builder builderM16382a2 = C3123g.m16382a(GroupEditActivity.this);
            builderM16382a2.setTitle(GroupEditActivity.this.getString(R.string.chat_group_more_dialog_leave_chat));
            if (GroupEditActivity.this.f7777n.f13704C && GroupEditActivity.this.f7777n.f13729p > 1) {
                builderM16382a2.setMessage(GroupEditActivity.this.getString(R.string.you_are_one_of_the_admins_if_you_leave_this_group_youll_no_longer_be_an_administrator));
                builderM16382a2.setPositiveButton(GroupEditActivity.this.getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.c5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f9771b.m8399g(friend, dialogInterface, i9);
                    }
                });
                builderM16382a2.setNegativeButton(GroupEditActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.d5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        GroupEditActivity.ViewOnClickListenerC1496i.m8400h(dialogInterface, i9);
                    }
                });
                builderM16382a2.show();
                return;
            }
            if (!GroupEditActivity.this.f7777n.f13704C || GroupEditActivity.this.f7777n.f13729p != 1) {
                GroupEditActivity.this.m8363h1(friend);
                return;
            }
            builderM16382a2.setMessage(GroupEditActivity.this.getString(R.string.if_you_leave_this_group_this_group_will_be_closed_all_the_chats_text_and_photos_will_be_removed));
            builderM16382a2.setPositiveButton(GroupEditActivity.this.getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.e5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f10390b.m8401i(friend, dialogInterface, i9);
                }
            });
            builderM16382a2.setNegativeButton(GroupEditActivity.this.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.f5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    GroupEditActivity.ViewOnClickListenerC1496i.m8402j(dialogInterface, i9);
                }
            });
            builderM16382a2.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$j */
    public class C1497j extends GestureDetector.SimpleOnGestureListener {
        public C1497j() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(GroupEditActivity.this);
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$k */
    public class C1498k implements TextWatcher {
        public C1498k() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            GroupEditActivity.this.f7776m.setEnabled((charSequence == null || charSequence.toString().isEmpty()) ? false : true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$l */
    public class AsyncTaskC1499l extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1499l() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            if (GroupEditActivity.this.f7777n.f13705D != null && GroupEditActivity.this.f7777n.f13705D.equals("Community")) {
                GroupEditActivity groupEditActivity = GroupEditActivity.this;
                groupEditActivity.f7769f = groupEditActivity.f7777n.m15746b();
            }
            GroupEditActivity groupEditActivity2 = GroupEditActivity.this;
            groupEditActivity2.f7768e = groupEditActivity2.f7777n.m15747c();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r22) {
            if (GroupEditActivity.this.f7777n.f13705D != null && GroupEditActivity.this.f7777n.f13705D.equals("Community") && GroupEditActivity.this.f7769f != null) {
                Collections.sort(GroupEditActivity.this.f7769f, new Friend.C3041b());
                GroupEditActivity.this.f7779p.m25225n(GroupEditActivity.this.f7769f);
            }
            if (GroupEditActivity.this.f7768e != null) {
                Collections.sort(GroupEditActivity.this.f7768e, new Friend.C3041b());
                GroupEditActivity.this.f7779p.m25227p(GroupEditActivity.this.f7768e);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$m */
    public class C1500m implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Runnable f7808a;

        /* renamed from: b */
        public final /* synthetic */ Permission f7809b;

        public C1500m(Runnable runnable, Permission permission) {
            this.f7808a = runnable;
            this.f7809b = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(GroupEditActivity.this, this.f7809b);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            this.f7808a.run();
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupEditActivity$n */
    public class C1501n implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Runnable f7811a;

        /* renamed from: b */
        public final /* synthetic */ int f7812b;

        public C1501n(Runnable runnable, int i9) {
            this.f7811a = runnable;
            this.f7812b = i9;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20261a(GroupEditActivity.this, this.f7812b);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            this.f7811a.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M0 */
    public /* synthetic */ void m8289M0(Group group, int i9, List list) {
        this.f7777n = group;
        if (i9 == 4) {
            this.f7779p.m25217f(list);
        } else {
            this.f7779p.m25218g(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m8291N0(final int i9, final List list, DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            final Group groupM8366l1 = m8366l1(str4);
            if (groupM8366l1 == null) {
                finish();
            } else {
                C2950b0.m14912k().m15069f(groupM8366l1);
                runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.w4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11838b.m8289M0(groupM8366l1, i9, list);
                    }
                });
            }
        }
        dialogC3133q.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O0 */
    public /* synthetic */ void m8293O0() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            Uri uriM16468N0 = CLUtility.m16468N0();
            this.f7782s = uriM16468N0;
            if (uriM16468N0 == null) {
                return;
            }
            intent.putExtra("output", uriM16468N0);
            startActivityForResult(intent, 2);
            Globals.m7388i0().m7526b3(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P0 */
    public /* synthetic */ void m8295P0() {
        if (C5178r.m20243l(this, 1)) {
            Globals.m7388i0().m7526b3(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q0 */
    public /* synthetic */ void m8297Q0() {
        C6127a.m23470k(this, this.f7773j, this.f7777n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R0 */
    public /* synthetic */ void m8298R0(FriendsClient friendsClient, String str, String str2, String str3, String str4) {
        Group groupM20186h;
        friendsClient.m15717U0();
        if (!"200".equals(str3) || (groupM20186h = C5172p.m20186h(C5172p.m20195q(str4))) == null) {
            return;
        }
        Group group = this.f7777n;
        group.f13724k = groupM20186h.f13724k;
        group.f13725l = groupM20186h.f13725l;
        C2950b0.m14912k().m15062A(String.valueOf(this.f7777n.f13727n), this.f7777n, "Avatar");
        C2950b0.m14912k().m15062A(String.valueOf(this.f7777n.f13727n), this.f7777n, "cover");
        m8357G0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.g4
            @Override // java.lang.Runnable
            public final void run() {
                this.f10683b.m8297Q0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S0 */
    public /* synthetic */ boolean m8299S0(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f7767d;
        if (c4619d == null) {
            return false;
        }
        return c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T0 */
    public /* synthetic */ void m8301T0(View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) EditGroupNameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f7777n);
        intent.putExtras(bundle);
        startActivityForResult(intent, 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U0 */
    public /* synthetic */ void m8302U0(CheckBox checkBox, View view) {
        boolean z8 = !checkBox.isChecked();
        checkBox.setChecked(z8);
        if (z8) {
            this.f7783t = "Community";
        } else {
            this.f7783t = "Small";
        }
        this.f7779p.m25226o(this.f7783t);
        this.f7779p.m25225n(this.f7769f);
        this.f7778o.setAdapter(this.f7779p);
        this.f7778o.expandGroup(0);
        if (this.f7783t.equals("Community")) {
            this.f7778o.expandGroup(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V0 */
    public /* synthetic */ boolean m8304V0(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 != 3) {
            return false;
        }
        CLUtility.m16589t1(this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W0 */
    public /* synthetic */ void m8305W0() {
        Intent intent = new Intent(this, (Class<?>) ULauncherActivity.class);
        intent.setFlags(67108864);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X0 */
    public /* synthetic */ void m8307X0(String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            C2950b0.m14912k().m15072i(this.f7777n.f13727n);
            C2950b0.m14913l().m15095f(this.f7777n.f13727n);
            if (!isFinishing()) {
                C5152i0.m20065b(this.f7774k);
            }
            m8357G0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.m4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10879b.m8305W0();
                }
            });
            return;
        }
        if (str3 == null) {
            Log.d("GroupEditActivity", "Response is null");
        } else {
            Log.d("GroupEditActivity", "statusCode=" + str3);
        }
        if (isFinishing()) {
            return;
        }
        C5152i0.m20065b(this.f7774k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y0 */
    public /* synthetic */ void m8309Y0(View view) {
        CLUtility.m16589t1(this);
        m8364i1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z0 */
    public /* synthetic */ void m8311Z0(Dialog dialog, View view) {
        m8353C0();
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a1 */
    public /* synthetic */ void m8312a1(Dialog dialog, View view) {
        m8380z0();
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b1 */
    public /* synthetic */ void m8314b1(View view) {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_profile_edit_avatar_opt);
        dialogM16384c.findViewById(R.id.itemPhotoLibrary).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.x4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f12245b.m8311Z0(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.itemTakePhoto).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.e4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f10388b.m8312a1(dialogM16384c, view2);
            }
        });
        CLUtility.m16578q2(m8357G0(), dialogM16384c);
        dialogM16384c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c1 */
    public /* synthetic */ void m8316c1(Group group, Friend friend) {
        this.f7777n = group;
        this.f7779p.m25223l(friend);
        this.f7778o.invalidateViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d1 */
    public /* synthetic */ void m8318d1(final Friend friend, DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            final Group groupM8366l1 = m8366l1(str4);
            if (groupM8366l1 == null) {
                finish();
            } else {
                C2950b0.m14912k().m15069f(groupM8366l1);
                C2950b0.m14910i().m15042e(Long.valueOf(groupM8366l1.f13727n), Long.valueOf(friend.f13645c));
                this.f7784u = true;
                if (this.f7785v.f13777b != friend.f13645c || groupM8366l1.f13704C) {
                    m8357G0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.n4
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f10915b.m8316c1(groupM8366l1, friend);
                        }
                    });
                } else {
                    m8364i1();
                }
            }
        }
        dialogC3133q.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e1 */
    public /* synthetic */ void m8320e1(Group group, Friend friend) {
        this.f7777n = group;
        this.f7779p.m25224m(friend);
        this.f7779p.m25223l(friend);
        m8375u1(friend);
        this.f7778o.invalidateViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f1 */
    public /* synthetic */ void m8321f1(final Friend friend, DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            final Group groupM8366l1 = m8366l1(str4);
            if (groupM8366l1 == null) {
                finish();
            } else {
                C2950b0.m14912k().m15069f(groupM8366l1);
                C2950b0.m14913l().m15094e(Long.valueOf(groupM8366l1.f13727n), Long.valueOf(friend.f13645c));
                this.f7784u = true;
                runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.p4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11031b.m8320e1(groupM8366l1, friend);
                    }
                });
            }
        }
        dialogC3133q.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g1 */
    public /* synthetic */ void m8323g1(Uri uri, boolean z8) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ImageItem imageItemM16432E0 = CLUtility.m16432E0(Globals.m7372O(), uri);
        if (imageItemM16432E0 != null) {
            imageItemM16432E0.m16123D(z8);
        }
        m8377w1(imageItemM16432E0);
    }

    /* renamed from: k1 */
    public static Group m8330k1(String str) {
        try {
            try {
                return C5172p.m20186h(new JSONObject(str).getJSONObject("result"));
            } catch (JSONException unused) {
                Log.e("GroupEditActivity", "[FriendListFriends] 'results' missing. JSONstr=" + str);
                return null;
            }
        } catch (JSONException unused2) {
            Log.e("GroupEditActivity", "[FriendListFriends] Parse error. JSONstr=" + str);
            return null;
        }
    }

    /* renamed from: C0 */
    public final void m8353C0() {
        m8369o1(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new Runnable() { // from class: com.cyberlink.you.activity.k4
            @Override // java.lang.Runnable
            public final void run() {
                this.f10810b.m8295P0();
            }
        });
    }

    /* renamed from: D0 */
    public final boolean m8354D0(Uri uri, Uri uri2) {
        if (!CLUtility.m16613z1(null, uri)) {
            return false;
        }
        boolean zM20240i = C5178r.m20240i(this, 3, uri, uri2, 1080);
        if (zM20240i) {
            this.f7782s = uri2;
            Globals.m7388i0().m7526b3(true);
        }
        return zM20240i;
    }

    /* renamed from: E0 */
    public final void m8355E0(String str, ImageItem imageItem) throws Throwable {
        if (imageItem == null) {
            return;
        }
        boolean z8 = false;
        String strM16495U1 = CLUtility.m16495U1(String.valueOf(imageItem.m16142o()), imageItem.m16144q(), CLUtility.m16510Z1(imageItem.m16145r()), 0, true);
        if (strM16495U1 != null) {
            try {
                ImageItem imageItem2 = new ImageItem(new JSONObject(imageItem.m16127H()));
                imageItem2.m16126G(new File(strM16495U1).getName());
                imageItem2.m16124E(strM16495U1);
                m8371q1(str, imageItem2);
            } catch (JSONException e9) {
                Log.d("GroupEditActivity", Log.getStackTraceString(e9));
            }
        } else {
            z8 = true;
        }
        if (z8) {
            C5152i0.m20065b(this.f7774k);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /* renamed from: F0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m8356F0(String str, ImageItem imageItem) {
        JSONException e9;
        ImageItem imageItem2;
        if (imageItem == null) {
            return;
        }
        try {
            imageItem2 = new ImageItem(new JSONObject(imageItem.m16127H()));
            try {
                imageItem2.m16123D(false);
            } catch (JSONException e10) {
                e9 = e10;
                Log.e("GroupEditActivity", "[doUpdateCover] JSONException:" + e9.getMessage());
                if (imageItem2 != null) {
                }
            }
        } catch (JSONException e11) {
            e9 = e11;
            imageItem2 = null;
        }
        if (imageItem2 != null) {
            return;
        }
        String strM16144q = imageItem2.m16144q();
        Uri uriM16510Z1 = CLUtility.m16510Z1(imageItem2.m16145r());
        if (CLUtility.m16613z1(strM16144q, uriM16510Z1)) {
            imageItem2.m16126G(CLUtility.m16552k0(strM16144q, uriM16510Z1));
            m8372r1(str, imageItem2);
        } else {
            if (isFinishing()) {
                return;
            }
            C5152i0.m20065b(this.f7774k);
        }
    }

    /* renamed from: G0 */
    public final Activity m8357G0() {
        return this;
    }

    /* renamed from: H0 */
    public final void m8358H0(String str) {
        new AsyncTaskC1491d(str).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: I0 */
    public final void m8359I0() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(this.f7777n.f13727n)));
        final FriendsClient friendsClient = new FriendsClient();
        friendsClient.m15734m("group", "update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.f4
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10428a.m8298R0(friendsClient, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: J0 */
    public final void m8360J0() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f7777n = (Group) extras.getParcelable("Group");
            this.f7786w = extras.getBoolean("INTENT_IS_NEW_GROUP");
            this.f7787x = extras.getBoolean("anonymousGroupUpdate", false);
            if (!this.f7786w) {
                this.f7783t = this.f7777n.f13705D;
                this.f7769f = extras.getParcelableArrayList("addAdmins");
                this.f7768e = extras.getParcelableArrayList("addMembers");
                return;
            }
            this.f7783t = extras.getString("circleType", "Small");
            Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(Globals.m7388i0().m7568k1()));
            if (friendM15003C == null) {
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(m8357G0());
                Friend friend = new Friend();
                friend.f13645c = userInfoM16497V0.f13777b;
                friend.f13647e = userInfoM16497V0.f13779d;
                friend.m15624e(userInfoM16497V0.f13778c);
                friendM15003C = friend;
            }
            if (this.f7787x) {
                this.f7768e = extras.getParcelableArrayList("addMembers");
            }
            if (!this.f7768e.contains(friendM15003C)) {
                this.f7768e.add(friendM15003C);
            }
            this.f7769f.add(friendM15003C);
        }
    }

    /* renamed from: K0 */
    public final void m8361K0() {
        if (this.f7777n != null) {
            new AsyncTaskC1499l().executeOnExecutor(C6385v.f21554b, new Void[0]);
        } else {
            try {
                Collections.sort(this.f7768e, new Friend.C3041b());
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        C6596a c6596a = new C6596a(m8357G0(), this.f7777n, this.f7783t, this.f7769f, this.f7768e);
        this.f7779p = c6596a;
        c6596a.m25228q(this.f7762C);
        this.f7779p.m25229r(this.f7763D);
        this.f7778o.setAdapter(this.f7779p);
        this.f7778o.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.d4
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f10361b.m8299S0(view, motionEvent);
            }
        });
        this.f7778o.expandGroup(0);
        if (this.f7783t.equals("Community")) {
            this.f7778o.expandGroup(1);
        }
    }

    /* renamed from: L0 */
    public final void m8362L0() {
        EditText editText = (EditText) findViewById(R.id.GroupEditMainGroupNameEditText);
        this.f7770g = editText;
        editText.setImeOptions(6);
        View viewFindViewById = findViewById(R.id.btnEditGroupName);
        this.f7778o = (ExpandableListView) findViewById(R.id.GroupEditListView);
        this.f7771h = (EditText) findViewById(R.id.SearchEditText);
        this.f7776m = (Button) findViewById(R.id.buttonCreate);
        TextView textView = (TextView) findViewById(R.id.textViewTitle);
        if (this.f7777n == null || this.f7787x) {
            textView.setText(R.string.create_group);
            viewFindViewById.setVisibility(8);
            if (!this.f7787x) {
                View viewInflate = getLayoutInflater().inflate(R.layout.view_item_admin_control, (ViewGroup) this.f7778o, false);
                this.f7778o.addFooterView(viewInflate);
                final CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.imageViewCheck);
                viewInflate.findViewById(R.id.relativeLayoutAdminControl).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.s4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f11163b.m8302U0(checkBox, view);
                    }
                });
            }
            this.f7770g.addTextChangedListener(new C1498k());
        } else {
            textView.setText(R.string.edit_group);
            this.f7770g.setFocusable(false);
            this.f7770g.setFocusableInTouchMode(false);
            this.f7770g.setClickable(false);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.r4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f11133b.m8301T0(view);
                }
            });
        }
        this.f7771h.addTextChangedListener(this.f7765F);
        this.f7771h.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cyberlink.you.activity.t4
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i9, KeyEvent keyEvent) {
                return this.f11390b.m8304V0(textView2, i9, keyEvent);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.avatarArea);
        this.f7772i = relativeLayout;
        relativeLayout.setOnClickListener(this.f7764E);
        this.f7773j = (ImageView) findViewById(R.id.avatarData);
        findViewById(R.id.GroupEditMainBackBtn).setOnClickListener(this.f7789z);
        if (this.f7787x) {
            this.f7776m.setOnClickListener(this.f7761B);
        } else {
            if (this.f7777n == null) {
                this.f7776m.setOnClickListener(this.f7760A);
                return;
            }
            this.f7776m.setVisibility(8);
            this.f7770g.setText(this.f7777n.f13717d);
            C6127a.m23470k(this, this.f7773j, this.f7777n);
        }
    }

    /* renamed from: h1 */
    public final void m8363h1(Friend friend) {
        this.f7774k = ProgressDialog.show(m8357G0(), "", getString(R.string.loading), true);
        this.f7766c.m15697A0(this.f7777n.f13727n, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.i4
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10742a.m8307X0(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: i1 */
    public final void m8364i1() {
        String string = this.f7770g.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(AppMeasurementSdk.ConditionalUserProperty.NAME, string);
        if (this.f7784u) {
            setResult(-1, intent);
        } else {
            setResult(0, intent);
        }
        finish();
    }

    /* renamed from: j1 */
    public final void m8365j1(Group group) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", group);
        Intent intent = new Intent(this, (Class<?>) ChatDialogActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* renamed from: l1 */
    public final Group m8366l1(String str) {
        try {
            try {
                Group groupM20186h = C5172p.m20186h(new JSONObject(str).getJSONObject("result"));
                Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(groupM20186h.f13727n));
                if (groupM15077n != null) {
                    groupM20186h.f13714M = groupM15077n.f13714M;
                }
                return groupM20186h;
            } catch (JSONException e9) {
                C5154j.m20076j(e9);
                return null;
            }
        } catch (JSONException e10) {
            C5154j.m20076j(e10);
            return null;
        }
    }

    /* renamed from: m1 */
    public final void m8367m1(final Friend friend) {
        final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        this.f7766c.m15709O0(this.f7777n.f13727n, friend.f13645c, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.j4
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10774a.m8318d1(friend, dialogC3133qM16411b, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: n1 */
    public final void m8368n1(final Friend friend) {
        final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        this.f7766c.m15710P0(this.f7777n.f13727n, friend.f13645c, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.l4
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10842a.m8321f1(friend, dialogC3133qM16411b, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: o1 */
    public final void m8369o1(Permission permission, Runnable runnable) {
        C5287b.m20583f(permission, new C1500m(runnable, permission), this);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Bundle extras;
        super.onActivityResult(i9, i10, intent);
        switch (i9) {
            case 1:
                Globals.m7388i0().m7526b3(false);
                if (i10 == -1) {
                    Uri uriM16587t = CLUtility.m16587t(intent.getData());
                    if (uriM16587t == null) {
                        uriM16587t = intent.getData();
                    }
                    if (!"image/gif".equals(CLUtility.m16452J0(null, uriM16587t))) {
                        Uri uriM16468N0 = CLUtility.m16468N0();
                        if (!(uriM16468N0 != null ? m8354D0(uriM16587t, uriM16468N0) : false)) {
                            this.f7782s = uriM16587t;
                            m8376v1(uriM16587t, false, false);
                            if (uriM16468N0 != null) {
                                Globals.m7372O().getContentResolver().delete(uriM16468N0, null, null);
                                break;
                            }
                        }
                    } else {
                        C5187v0.m20268d(getString(R.string.error_media_type_unsupported));
                        break;
                    }
                }
                break;
            case 2:
                Globals.m7388i0().m7526b3(false);
                if (i10 == -1) {
                    Uri uriM16468N02 = CLUtility.m16468N0();
                    if (!m8354D0(this.f7782s, uriM16468N02)) {
                        m8376v1(this.f7782s, true, false);
                        if (uriM16468N02 != null) {
                            Globals.m7372O().getContentResolver().delete(uriM16468N02, null, null);
                            break;
                        }
                    }
                }
                break;
            case 3:
                Globals.m7388i0().m7526b3(false);
                if (i10 != -1) {
                    if (this.f7782s != null) {
                        Globals.m7372O().getContentResolver().delete(this.f7782s, null, null);
                        break;
                    }
                } else {
                    m8376v1(this.f7782s, true, true);
                    break;
                }
                break;
            case 4:
                if (i10 == -1 && intent.getExtras() != null) {
                    List<Friend> listM7656c = Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST");
                    if (this.f7786w) {
                        listM7656c.add(C2950b0.m14899A().m15003C(String.valueOf(Globals.m7388i0().m7568k1().longValue())));
                        this.f7779p.m25225n(listM7656c);
                    } else {
                        m8379y0(i9, listM7656c);
                    }
                    this.f7784u = true;
                    break;
                }
                break;
            case 5:
                if (i10 == -1 && intent.getExtras() != null) {
                    List<Friend> listM7656c2 = Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST");
                    if (this.f7786w) {
                        this.f7779p.m25227p(listM7656c2);
                    } else {
                        m8379y0(i9, listM7656c2);
                    }
                    this.f7784u = true;
                    break;
                }
                break;
            case 6:
                if (i10 == -1 && (extras = intent.getExtras()) != null) {
                    String string = extras.getString("displayName");
                    this.f7770g.setText(string);
                    this.f7777n.f13717d = string;
                    this.f7784u = true;
                    break;
                }
                break;
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_group_create_main);
        this.f7785v = CLUtility.m16497V0(this);
        this.f7766c = new FriendsClient(true);
        this.f7767d = new C4619d(this, new C1497j());
        m8360J0();
        m8362L0();
        Group group = this.f7777n;
        if (group == null) {
            m8361K0();
            return;
        }
        String str = group.f13705D;
        if (str == null) {
            m8358H0(String.valueOf(group.f13727n));
        } else {
            this.f7783t = str;
            m8361K0();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f7772i.setOnClickListener(null);
        this.f7771h.removeTextChangedListener(this.f7765F);
        this.f7771h.setOnEditorActionListener(null);
        this.f7778o.setOnTouchListener(null);
        C5152i0.m20065b(this.f7774k);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            return super.onKeyUp(i9, keyEvent);
        }
        m8364i1();
        return true;
    }

    /* renamed from: p1 */
    public final void m8370p1(Permission[] permissionArr, Runnable runnable, int i9) {
        C5287b.m20584g(permissionArr, new C1501n(runnable, i9), this);
    }

    /* renamed from: q1 */
    public final void m8371q1(String str, ImageItem imageItem) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(str, imageItem);
        uploadMediaHelper.m16815R1(new C1488a(uploadMediaHelper));
        uploadMediaHelper.m16819T1();
    }

    /* renamed from: r1 */
    public final void m8372r1(String str, ImageItem imageItem) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(str, imageItem);
        uploadMediaHelper.m16815R1(new C1489b(uploadMediaHelper));
        uploadMediaHelper.m16819T1();
    }

    /* renamed from: s1 */
    public void m8373s1(ArrayList<Friend> arrayList, int i9) {
        Intent intent = new Intent(this, (Class<?>) SelectFromFriendGroupActivity.class);
        Bundle bundle = new Bundle();
        Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS", arrayList);
        if (!this.f7786w) {
            Globals.C1408b.m7655b().m7657d("INTENT_CANNOT_MODIFIED_USERS_LIST", arrayList);
            bundle.putParcelable("Group", this.f7777n);
        }
        intent.putExtras(bundle);
        startActivityForResult(intent, i9);
    }

    /* renamed from: t1 */
    public void m8374t1(ArrayList<Friend> arrayList, ArrayList<Friend> arrayList2, int i9) {
        Intent intent = new Intent(this, (Class<?>) SelectUsersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("INTENT_TITLE", getString(R.string.group_add_admin_title));
        Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS_LIST", arrayList2);
        Globals.C1408b.m7655b().m7657d("INTENT_USER_LIST", arrayList);
        if (!this.f7786w) {
            Globals.C1408b.m7655b().m7657d("INTENT_CANNOT_MODIFIED_USERS_LIST", arrayList2);
        }
        intent.putExtras(bundle);
        startActivityForResult(intent, i9);
    }

    /* renamed from: u1 */
    public final void m8375u1(Friend friend) {
        if (this.f7788y.containsKey(Long.valueOf(friend.f13645c))) {
            if (this.f7788y.get(Long.valueOf(friend.f13645c)).get(this.f7780q).equals(EditStatus.Added)) {
                this.f7788y.remove(Long.valueOf(friend.f13645c));
            }
        } else {
            HashMap map = new HashMap();
            map.put(this.f7780q, EditStatus.Removed);
            map.put(this.f7781r, null);
            this.f7788y.put(Long.valueOf(friend.f13645c), map);
        }
    }

    /* renamed from: v1 */
    public final void m8376v1(final Uri uri, boolean z8, final boolean z9) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (z8) {
            UploadMediaHelper.m16715O1(uri, new Runnable() { // from class: com.cyberlink.you.activity.v4
                @Override // java.lang.Runnable
                public final void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                    this.f11807b.m8323g1(uri, z9);
                }
            });
            return;
        }
        ImageItem imageItemM16432E0 = CLUtility.m16432E0(Globals.m7372O(), uri);
        if (imageItemM16432E0 != null) {
            imageItemM16432E0.m16123D(z9);
        }
        m8377w1(imageItemM16432E0);
    }

    /* renamed from: w1 */
    public final void m8377w1(ImageItem imageItem) {
        if (imageItem == null) {
            return;
        }
        this.f7775l = imageItem;
        Uri uriM16510Z1 = CLUtility.m16510Z1(imageItem.m16136i());
        if (uriM16510Z1 != null) {
            C6127a.m23465f(this, this.f7773j, uriM16510Z1, R.drawable.pic_default_group);
        } else {
            C6127a.m23474o(this, this.f7773j, imageItem.m16135h(), R.drawable.pic_default_group);
        }
        if (this.f7777n == null || this.f7787x) {
            return;
        }
        this.f7774k = ProgressDialog.show(m8357G0(), "", getString(R.string.loading), true);
        m8356F0(this.f7777n.f13722i, imageItem);
    }

    /* renamed from: x1 */
    public final void m8378x1(Dialog dialog) {
        C5152i0.m20065b(dialog);
        C5187v0.m20267c(R.string.error_server_response);
    }

    /* renamed from: y0 */
    public final void m8379y0(final int i9, final List<Friend> list) {
        final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.u4
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f11423a.m8291N0(i9, list, dialogC3133qM16411b, str, str2, str3, str4);
            }
        };
        if (i9 == 5) {
            this.f7766c.m15737x(this.f7777n.f13727n, list, interfaceC3051i);
        } else if (i9 == 4) {
            this.f7766c.m15736w(this.f7777n.f13727n, list, interfaceC3051i);
        }
    }

    /* renamed from: z0 */
    public final void m8380z0() {
        Permission[] permissionArr;
        int i9;
        if (Build.VERSION.SDK_INT >= 33) {
            permissionArr = new Permission[]{Permission.IMAGE, Permission.CAMERA};
            i9 = R.string.permission_ask_photo_video_camera;
        } else {
            permissionArr = new Permission[]{Permission.STORAGE, Permission.CAMERA};
            i9 = R.string.permission_ask_storage_camera;
        }
        m8370p1(permissionArr, new Runnable() { // from class: com.cyberlink.you.activity.h4
            @Override // java.lang.Runnable
            public final void run() {
                this.f10711b.m8293O0();
            }
        }, i9);
    }
}
