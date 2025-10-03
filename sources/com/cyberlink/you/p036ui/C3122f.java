package com.cyberlink.you.p036ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.friend.FriendAddActivity;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p005a4.C0032a;
import p116k4.C5145g;
import p193s4.C6263a;

/* renamed from: com.cyberlink.you.ui.f */
/* loaded from: classes.dex */
public class C3122f {

    /* renamed from: c */
    public static final String f14390c = "f";

    /* renamed from: a */
    public FriendAddActivity f14391a;

    /* renamed from: b */
    public String f14392b;

    /* renamed from: com.cyberlink.you.ui.f$a */
    public class a implements DialogInterface.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ CharSequence[] f14393b;

        /* renamed from: c */
        public final /* synthetic */ FriendAddActivity f14394c;

        /* renamed from: d */
        public final /* synthetic */ CharSequence[] f14395d;

        public a(CharSequence[] charSequenceArr, FriendAddActivity friendAddActivity, CharSequence[] charSequenceArr2) {
            this.f14393b = charSequenceArr;
            this.f14394c = friendAddActivity;
            this.f14395d = charSequenceArr2;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
            if (C3122f.this.m16378g(R.string.friends_invitation_selector_string_sms).equals(this.f14393b[i9])) {
                CLUtility.m16614z2(this.f14394c, null, CLUtility.InviteType.SMS);
                return;
            }
            if (C3122f.this.m16378g(R.string.friends_invitation_selector_string_email).equals(this.f14393b[i9])) {
                CLUtility.m16614z2(this.f14394c, null, CLUtility.InviteType.EMail);
            } else if (C3122f.this.m16378g(R.string.friends_invitation_selector_string_google_contact).equals(this.f14393b[i9])) {
                C3122f.this.m16379h();
            } else {
                C3122f.this.m16380i(this.f14393b[i9], this.f14395d[i9]);
            }
        }
    }

    /* renamed from: com.cyberlink.you.ui.f$b */
    public class b implements DialogInterface.OnClickListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
        }
    }

    /* renamed from: com.cyberlink.you.ui.f$c */
    public class c implements DialogInterface.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ CharSequence f14398b;

        public c(CharSequence charSequence) {
            this.f14398b = charSequence;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            Intent launchIntentForPackage = C3122f.this.f14391a.getPackageManager().getLaunchIntentForPackage(this.f14398b.toString());
            if (launchIntentForPackage != null) {
                C3122f.this.f14391a.startActivity(launchIntentForPackage);
            }
        }
    }

    /* renamed from: e */
    public AlertDialog m16376e(FriendAddActivity friendAddActivity, String str) {
        this.f14391a = friendAddActivity;
        this.f14392b = str;
        HashMap<String, String> mapM16377f = m16377f(friendAddActivity);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<String, String> entry : mapM16377f.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        arrayList.add(m16378g(R.string.friends_invitation_selector_string_sms));
        arrayList.add(m16378g(R.string.friends_invitation_selector_string_email));
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(friendAddActivity) == 0) {
            arrayList.add(m16378g(R.string.friends_invitation_selector_string_google_contact));
        }
        arrayList2.add("CL_Reserved");
        arrayList2.add("CL_Reserved");
        CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]);
        return C3123g.m16382a(friendAddActivity).setTitle(m16378g(R.string.friends_invitation_chooser_title)).setItems(charSequenceArr, new a(charSequenceArr, friendAddActivity, (CharSequence[]) arrayList2.toArray(new CharSequence[arrayList2.size()]))).create();
    }

    /* renamed from: f */
    public final HashMap<String, String> m16377f(Context context) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        HashMap<String, String> map = new HashMap<>();
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (resolveInfo.activityInfo.packageName.contains("jp.naver.line.android") || resolveInfo.activityInfo.packageName.contains("com.whatsapp") || resolveInfo.activityInfo.packageName.contains("com.tencent.mm") || resolveInfo.activityInfo.packageName.contains("com.facebook.katana") || resolveInfo.activityInfo.packageName.contains("com.facebook.orca")) {
                try {
                    map.put(packageManager.getApplicationLabel(packageManager.getApplicationInfo(resolveInfo.activityInfo.packageName, 0)).toString(), resolveInfo.activityInfo.packageName);
                } catch (PackageManager.NameNotFoundException e9) {
                    e9.printStackTrace();
                }
            }
        }
        return map;
    }

    /* renamed from: g */
    public final String m16378g(int i9) {
        return this.f14391a.getString(i9);
    }

    /* renamed from: h */
    public final void m16379h() {
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(this.f14391a);
        this.f14391a.startActivityForResult(C6263a.m24003a(this.f14391a.getString(R.string.friends_invitation_google_title), C0032a.m125g(userInfoM16497V0, this.f14391a), this.f14391a.getString(R.string.friends_invitation_google_message, userInfoM16497V0.f13778c)), 4);
    }

    /* renamed from: i */
    public final void m16380i(CharSequence charSequence, CharSequence charSequence2) {
        String string;
        if (this.f14392b == null && (string = this.f14391a.getSharedPreferences("U", 0).getString("inviteFriendLink", null)) != null) {
            this.f14392b = String.format(m16378g(R.string.invite_sms_new), CLUtility.m16497V0(this.f14391a).f13778c, string);
        }
        String str = this.f14392b;
        if (str == null) {
            Log.d(f14390c, "Invitation link is not available");
        } else {
            C5145g.m20042a(str);
            m16381j(charSequence, charSequence2);
        }
    }

    /* renamed from: j */
    public final void m16381j(CharSequence charSequence, CharSequence charSequence2) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this.f14391a);
        builderM16382a.setMessage(String.format(m16378g(R.string.friends_invitation_chooser_prompt), charSequence)).setPositiveButton(m16378g(R.string.ok), new c(charSequence2)).setNegativeButton(m16378g(R.string.cancel_text), new b());
        builderM16382a.create().show();
    }
}
