package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p096i4.C5050a;
import p116k4.C5145g;
import p116k4.C5155j0;
import p116k4.C5170o0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class GroupJoinLinkActivity extends BaseActivity {

    /* renamed from: c */
    public FriendsClient f7862c;

    /* renamed from: d */
    public TextView f7863d;

    /* renamed from: e */
    public TextView f7864e;

    /* renamed from: f */
    public ImageView f7865f;

    /* renamed from: g */
    public Bitmap f7866g;

    /* renamed from: h */
    public Group f7867h;

    /* renamed from: i */
    public String f7868i = null;

    /* renamed from: j */
    public View.OnClickListener f7869j = new View.OnClickListener() { // from class: com.cyberlink.you.activity.c6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9773b.m8498P(view);
        }
    };

    /* renamed from: k */
    public View.OnClickListener f7870k = new ViewOnClickListenerC1508a();

    /* renamed from: l */
    public View.OnClickListener f7871l = new ViewOnClickListenerC1509b();

    /* renamed from: com.cyberlink.you.activity.GroupJoinLinkActivity$a */
    public class ViewOnClickListenerC1508a implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.GroupJoinLinkActivity$a$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ Permission f7873a;

            public a(Permission permission) {
                this.f7873a = permission;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(GroupJoinLinkActivity.this, this.f7873a);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                if (CLUtility.m16613z1(null, CLUtility.m16550j2(GroupJoinLinkActivity.this.f7866g, true, C5050a.m19750c(GroupJoinLinkActivity.this.m8517D(), GroupJoinLinkActivity.this.f7867h.f13717d) ? "" : GroupJoinLinkActivity.this.f7867h.f13717d))) {
                    C5187v0.m20267c(R.string.group_qrcode_save_success);
                }
            }
        }

        public ViewOnClickListenerC1508a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m8528d(Dialog dialog, View view) {
            dialog.dismiss();
            GroupJoinLinkActivity.this.m8524d0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m8529e(Dialog dialog, View view) {
            dialog.dismiss();
            Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
            C5287b.m20583f(permission, new a(permission), GroupJoinLinkActivity.this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final Dialog dialogM16384c = C3123g.m16384c(GroupJoinLinkActivity.this);
            dialogM16384c.setContentView(R.layout.dialog_qrcode_more);
            dialogM16384c.findViewById(R.id.item_change_qrcode_link).setVisibility(8);
            dialogM16384c.findViewById(R.id.item_change_qrcode).setVisibility(8);
            dialogM16384c.findViewById(R.id.item_send_qrcode).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.l6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f10847b.m8528d(dialogM16384c, view2);
                }
            });
            dialogM16384c.findViewById(R.id.item_save_qrcode).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.m6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f10883b.m8529e(dialogM16384c, view2);
                }
            });
            dialogM16384c.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.n6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    dialogM16384c.dismiss();
                }
            });
            CLUtility.m16578q2(GroupJoinLinkActivity.this.m8517D(), dialogM16384c);
            dialogM16384c.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupJoinLinkActivity$b */
    public class ViewOnClickListenerC1509b implements View.OnClickListener {
        public ViewOnClickListenerC1509b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m8532b(CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, DialogInterface dialogInterface, int i9) {
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
            if (GroupJoinLinkActivity.this.getString(R.string.friends_invitation_selector_string_sms).contentEquals(charSequenceArr[i9])) {
                CLUtility.m16614z2(GroupJoinLinkActivity.this, null, CLUtility.InviteType.SMS);
                return;
            }
            if (GroupJoinLinkActivity.this.getString(R.string.friends_invitation_selector_string_email).contentEquals(charSequenceArr[i9])) {
                GroupJoinLinkActivity.this.m8524d0();
            } else if (!GroupJoinLinkActivity.this.getString(R.string.copy_link).contentEquals(charSequenceArr[i9])) {
                GroupJoinLinkActivity.this.m8520Y(charSequenceArr[i9], charSequenceArr2[i9]);
            } else {
                ((ClipboardManager) GroupJoinLinkActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, GroupJoinLinkActivity.this.f7864e.getText()));
                C5187v0.m20267c(R.string.group_invite_link_is_copied);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Activity activityM8517D = GroupJoinLinkActivity.this.m8517D();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setType("text/plain");
            List<ResolveInfo> listQueryIntentActivities = activityM8517D.getPackageManager().queryIntentActivities(intent, 0);
            HashMap map = new HashMap();
            PackageManager packageManager = GroupJoinLinkActivity.this.getApplicationContext().getPackageManager();
            for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.contains("jp.naver.line.android") || resolveInfo.activityInfo.packageName.contains("com.whatsapp") || resolveInfo.activityInfo.packageName.contains("com.tencent.mm")) {
                    try {
                        map.put(packageManager.getApplicationLabel(packageManager.getApplicationInfo(resolveInfo.activityInfo.packageName, 0)).toString(), resolveInfo.activityInfo.packageName);
                    } catch (PackageManager.NameNotFoundException e9) {
                        e9.printStackTrace();
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Map.Entry entry : map.entrySet()) {
                arrayList.add((CharSequence) entry.getKey());
                arrayList2.add((CharSequence) entry.getValue());
            }
            arrayList.add(GroupJoinLinkActivity.this.getString(R.string.friends_invitation_selector_string_sms));
            arrayList.add(GroupJoinLinkActivity.this.getString(R.string.friends_invitation_selector_string_email));
            arrayList.add(GroupJoinLinkActivity.this.getString(R.string.copy_link));
            arrayList2.add("CL_Reserved");
            arrayList2.add("CL_Reserved");
            arrayList2.add("CL_Reserved");
            final CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[0]);
            final CharSequence[] charSequenceArr2 = (CharSequence[]) arrayList2.toArray(new CharSequence[0]);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(GroupJoinLinkActivity.this);
            builderM16382a.setTitle(GroupJoinLinkActivity.this.getString(R.string.friends_invitation_chooser_title)).setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.o6
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f11001b.m8532b(charSequenceArr, charSequenceArr2, dialogInterface, i9);
                }
            });
            builderM16382a.create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m8493I(Dialog dialog, View view) {
        m8518E(true);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m8495L(String str, String str2, String str3, String str4) throws JSONException {
        if (str3 == null) {
            Log.d("GroupJoinLinkActivity", "Response is null");
            return;
        }
        if (!str3.equals("200")) {
            Log.d("GroupJoinLinkActivity", "statusCode=" + str3);
            return;
        }
        if (str4 == null) {
            Log.d("GroupJoinLinkActivity", "Response JSONstr is null");
            return;
        }
        try {
            final String string = new JSONObject(str4).getString("inviteURL");
            if (C5170o0.m20169d(string)) {
                Log.d("GroupJoinLinkActivity", "get mFriendLink from JSONstr fail");
                return;
            }
            m8517D().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.k6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10812b.m8494J(string);
                }
            });
            this.f7867h.f13707F = string;
            Log.d("GroupJoinLinkActivity", "[getFriendLink] Renew inviteGroupLink = " + this.f7867h.f13707F + ", update to database.");
            C2950b0.m14912k().m15062A(String.valueOf(this.f7867h.f13727n), this.f7867h, "inviteGroupLink");
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m8496N(Dialog dialog, View view) {
        dialog.dismiss();
        m8516C();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m8498P(View view) {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_qrcode_more);
        dialogM16384c.findViewById(R.id.item_change_qrcode_link).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.e6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f10392b.m8496N(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.item_change_qrcode).setVisibility(8);
        dialogM16384c.findViewById(R.id.item_send_qrcode).setVisibility(8);
        dialogM16384c.findViewById(R.id.item_save_qrcode).setVisibility(8);
        dialogM16384c.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.f6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dialogM16384c.dismiss();
            }
        });
        CLUtility.m16578q2(m8517D(), dialogM16384c);
        dialogM16384c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ void m8499Q(View view) {
        m8519X();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T */
    public /* synthetic */ void m8500T(CharSequence charSequence, DialogInterface dialogInterface, int i9) {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(charSequence.toString());
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
        }
    }

    /* renamed from: V */
    public static /* synthetic */ void m8501V(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: C */
    public final void m8516C() {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_renew_group_invite_link);
        ((TextView) dialogM16384c.findViewById(R.id.warning_message)).setText(R.string.group_invite_qrcode_renew_dialog_messsage);
        m8523c0(dialogM16384c);
        dialogM16384c.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.g6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16384c.dismiss();
            }
        });
        dialogM16384c.findViewById(R.id.renew).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.h6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10714b.m8493I(dialogM16384c, view);
            }
        });
        dialogM16384c.show();
    }

    /* renamed from: D */
    public Activity m8517D() {
        return this;
    }

    /* renamed from: E */
    public final void m8518E(boolean z8) {
        String str;
        Group groupM15077n;
        if (this.f7867h == null) {
            ULogUtility.m16680p("GroupJoinLinkActivity", "[getFriendLink] mGroup is null");
            return;
        }
        if (z8) {
            str = "renewInviteURL";
        } else {
            String str2 = null;
            try {
                groupM15077n = C2950b0.m14912k().m15077n(Long.toString(this.f7867h.f13727n));
                this.f7867h = groupM15077n;
            } catch (Exception e9) {
                e9.printStackTrace();
            }
            if (groupM15077n == null) {
                ULogUtility.m16680p("GroupJoinLinkActivity", "[getFriendLink] mGroup is null from Group.groupId");
                return;
            }
            str2 = groupM15077n.f13707F;
            Log.d("GroupJoinLinkActivity", "[getFriendLink] inviteGroupLink = " + str2);
            if (!C5170o0.m20169d(str2)) {
                m8494J(str2);
                return;
            }
            str = "genInviteURL";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(this.f7867h.f13727n)));
        this.f7862c.m15734m("group", str, arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.d6
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str3, String str4, String str5, String str6) throws JSONException {
                this.f10362a.m8495L(str3, str4, str5, str6);
            }
        });
    }

    /* renamed from: X */
    public final void m8519X() {
        finish();
    }

    /* renamed from: Y */
    public final void m8520Y(CharSequence charSequence, CharSequence charSequence2) {
        String str = this.f7867h.f13707F;
        Log.d("GroupJoinLinkActivity", "[getFriendLink] inviteGroupLink = " + this.f7867h.f13707F);
        if (str != null) {
            this.f7868i = String.format(getString(R.string.group_invite_sms_new), this.f7867h.f13717d, str);
        }
        String str2 = this.f7868i;
        if (str2 == null) {
            Log.d("GroupJoinLinkActivity", "Invitation link is not available");
        } else {
            C5145g.m20042a(str2);
            m8521Z(charSequence, charSequence2);
        }
    }

    /* renamed from: Z */
    public final void m8521Z(CharSequence charSequence, final CharSequence charSequence2) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(String.format(getString(R.string.friends_invitation_chooser_prompt), charSequence)).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.i6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10745b.m8500T(charSequence2, dialogInterface, i9);
            }
        }).setNegativeButton(getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.j6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                GroupJoinLinkActivity.m8501V(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    /* renamed from: b0, reason: merged with bridge method [inline-methods] */
    public final void m8494J(String str) {
        this.f7866g = C5155j0.m20084b(str);
        this.f7864e.setText(str);
        this.f7865f.setImageBitmap(this.f7866g);
        this.f7864e.setLinkTextColor(Color.rgb(0, 122, 255));
    }

    /* renamed from: c0 */
    public final void m8523c0(Dialog dialog) {
        m8517D().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        dialog.getWindow().getAttributes().width = (int) (930 * (r0.widthPixels / 1080.0f));
    }

    /* renamed from: d0 */
    public final void m8524d0() {
        CLUtility.m16614z2(this, this.f7867h, CLUtility.InviteType.EMail);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_group_invitation_qrcode);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f7867h = (Group) extras.getParcelable("Group");
        }
        this.f7862c = new FriendsClient(true);
        this.f7863d = (TextView) findViewById(R.id.textView1);
        TextView textView = (TextView) findViewById(R.id.textviewInvitationLink);
        this.f7864e = textView;
        textView.setOnClickListener(this.f7871l);
        this.f7865f = (ImageView) findViewById(R.id.QRCodeGenerateImageView);
        findViewById(R.id.QRCodeShareBtn).setOnClickListener(this.f7870k);
        findViewById(R.id.GroupQRCodeMoreBtn).setOnClickListener(this.f7869j);
        findViewById(R.id.InvitationLinkBtn).setOnClickListener(this.f7871l);
        Group group = this.f7867h;
        if (group != null) {
            this.f7863d.setText(group.f13717d);
        }
        m8518E(false);
        findViewById(R.id.QRCodeBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.b6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9740b.m8499Q(view);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        findViewById(R.id.QRCodeBackBtn).setOnClickListener(null);
        findViewById(R.id.QRCodeShareBtn).setOnClickListener(null);
        findViewById(R.id.GroupQRCodeMoreBtn).setOnClickListener(null);
        findViewById(R.id.InvitationLinkBtn).setOnClickListener(null);
        FriendsClient friendsClient = this.f7862c;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            return super.onKeyUp(i9, keyEvent);
        }
        m8519X();
        return true;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        CLUtility.m16589t1(this);
    }
}
