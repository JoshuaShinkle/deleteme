package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.doserver.NetworkManager;
import com.cyberlink.meeting.model.PhoneExtensionData;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import p116k4.C5170o0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;

/* loaded from: classes.dex */
public class DialPhoneActivity extends BaseActivity {

    /* renamed from: d */
    public EditText f7595d;

    /* renamed from: f */
    public ToneGenerator f7597f;

    /* renamed from: c */
    public final String f7594c = DialPhoneActivity.class.getSimpleName();

    /* renamed from: e */
    public boolean f7596e = false;

    /* renamed from: g */
    public TextWatcher f7598g = new C1461a();

    /* renamed from: h */
    public View.OnClickListener f7599h = new ViewOnClickListenerC1462b();

    /* renamed from: com.cyberlink.you.activity.DialPhoneActivity$a */
    public class C1461a implements TextWatcher {

        /* renamed from: b */
        public boolean f7600b = false;

        public C1461a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            DialPhoneActivity.this.findViewById(R.id.keypad_remove).setVisibility(editable.length() > 0 ? 0 : 8);
            if (this.f7600b) {
                return;
            }
            this.f7600b = true;
            String string = editable.toString();
            if (Locale.getDefault().getCountry().equals(Locale.TAIWAN.getCountry()) && string.replaceAll("[^a-zA-Z0-9]+", "").length() == 8) {
                string = editable.toString().replaceAll("[^a-zA-Z0-9]+", "");
            }
            if (string.length() != 8 || !Locale.getDefault().getCountry().equals(Locale.TAIWAN.getCountry())) {
                string = PhoneNumberUtils.formatNumber(editable.toString(), Locale.getDefault().getCountry());
            }
            if (!TextUtils.isEmpty(string)) {
                DialPhoneActivity.this.f7595d.setText(string);
                DialPhoneActivity.this.f7595d.setSelection(DialPhoneActivity.this.f7595d.getText().length());
            }
            this.f7600b = false;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.DialPhoneActivity$b */
    public class ViewOnClickListenerC1462b implements View.OnClickListener {
        public ViewOnClickListenerC1462b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.keypad_0 /* 2131298012 */:
                    DialPhoneActivity.this.f7595d.append("0");
                    DialPhoneActivity.this.f7597f.startTone(0, 100);
                    break;
                case R.id.keypad_1 /* 2131298013 */:
                    DialPhoneActivity.this.f7595d.append("1");
                    DialPhoneActivity.this.f7597f.startTone(1, 100);
                    break;
                case R.id.keypad_2 /* 2131298014 */:
                    DialPhoneActivity.this.f7595d.append("2");
                    DialPhoneActivity.this.f7597f.startTone(2, 100);
                    break;
                case R.id.keypad_3 /* 2131298015 */:
                    DialPhoneActivity.this.f7595d.append("3");
                    DialPhoneActivity.this.f7597f.startTone(3, 100);
                    break;
                case R.id.keypad_4 /* 2131298016 */:
                    DialPhoneActivity.this.f7595d.append("4");
                    DialPhoneActivity.this.f7597f.startTone(4, 100);
                    break;
                case R.id.keypad_5 /* 2131298017 */:
                    DialPhoneActivity.this.f7595d.append("5");
                    DialPhoneActivity.this.f7597f.startTone(5, 100);
                    break;
                case R.id.keypad_6 /* 2131298018 */:
                    DialPhoneActivity.this.f7595d.append("6");
                    DialPhoneActivity.this.f7597f.startTone(6, 100);
                    break;
                case R.id.keypad_7 /* 2131298019 */:
                    DialPhoneActivity.this.f7595d.append("7");
                    DialPhoneActivity.this.f7597f.startTone(7, 100);
                    break;
                case R.id.keypad_8 /* 2131298020 */:
                    DialPhoneActivity.this.f7595d.append("8");
                    DialPhoneActivity.this.f7597f.startTone(8, 100);
                    break;
                case R.id.keypad_9 /* 2131298021 */:
                    DialPhoneActivity.this.f7595d.append("9");
                    DialPhoneActivity.this.f7597f.startTone(9, 100);
                    break;
                case R.id.keypad_asterisk /* 2131298022 */:
                    DialPhoneActivity.this.f7595d.append("*");
                    DialPhoneActivity.this.f7597f.startTone(10, 100);
                    break;
                case R.id.keypad_call /* 2131298023 */:
                    DialPhoneActivity.this.m8042E();
                    break;
                case R.id.keypad_remove /* 2131298025 */:
                    String string = DialPhoneActivity.this.f7595d.getText().toString();
                    if (!TextUtils.isEmpty(string)) {
                        DialPhoneActivity.this.f7595d.setText(string.substring(0, string.length() - 1));
                        break;
                    }
                    break;
                case R.id.keypad_well /* 2131298026 */:
                    DialPhoneActivity.this.f7595d.append("#");
                    DialPhoneActivity.this.f7597f.startTone(11, 100);
                    break;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.DialPhoneActivity$c */
    public class C1463c implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ String f7603a;

        public C1463c(String str) {
            this.f7603a = str;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(DialPhoneActivity.this, Permission.GET_ACCOUNTS);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            Intent intent = new Intent("android.intent.action.CALL");
            intent.setData(Uri.parse("tel:" + this.f7603a));
            DialPhoneActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.DialPhoneActivity$d */
    public class C1464d extends PromisedTask.AbstractC3021b<PhoneExtensionData> {

        /* renamed from: j */
        public final /* synthetic */ String f7605j;

        public C1464d(String str) {
            this.f7605j = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: s */
        public /* synthetic */ void m8046s(DialogInterface dialogInterface, int i9) {
            DialPhoneActivity.this.f7596e = false;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            ULogUtility.m16670f(DialPhoneActivity.this.f7594c, "queryUserByExtension error : " + i9 + StringUtils.SPACE + str);
            String str2 = DialPhoneActivity.this.f7594c;
            StringBuilder sb = new StringBuilder();
            sb.append("dial number from keypad : ");
            sb.append(this.f7605j);
            ULogUtility.m16670f(str2, sb.toString());
            if (i9 == NetworkManager.NetworkErrorCode.E_BAD_REQUEST.m5658a() || i9 == NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.m5658a()) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(DialPhoneActivity.this);
                builderM16382a.setMessage(R.string.phone_fragment_dial_connection_error).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.p2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        this.f11027b.m8046s(dialogInterface, i10);
                    }
                });
                builderM16382a.create().show();
            } else {
                Group group = new Group();
                String str3 = this.f7605j;
                group.f13717d = str3;
                group.f13716c = "Dual";
                MeetingActivity.m6382g9(DialPhoneActivity.this, group, true, MimeTypes.BASE_TYPE_AUDIO, "KeyPad", true, "", str3, "");
            }
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: t, reason: merged with bridge method [inline-methods] */
        public void mo5703q(PhoneExtensionData phoneExtensionData) {
            ULogUtility.m16670f(DialPhoneActivity.this.f7594c, "dial number from keypad : " + this.f7605j + " name : " + phoneExtensionData.name + " type : " + phoneExtensionData.type);
            if (String.valueOf(Globals.m7388i0().m7568k1()).equalsIgnoreCase(phoneExtensionData.uid)) {
                DialPhoneActivity.this.m8044Y();
                return;
            }
            if ("IVR".equalsIgnoreCase(phoneExtensionData.type)) {
                DialPhoneActivity.this.m8043X();
                return;
            }
            Group group = new Group();
            String str = this.f7605j;
            if (!C5170o0.m20170e(phoneExtensionData.name)) {
                str = phoneExtensionData.name + "(#" + this.f7605j + ")";
            }
            group.f13717d = str;
            group.f13716c = "Dual";
            Friend friendM15001A = C2950b0.m14899A().m15001A(phoneExtensionData.uid);
            if (friendM15001A != null) {
                group.f13724k = friendM15001A.f13647e;
            }
            MeetingActivity.m6382g9(DialPhoneActivity.this, group, true, MimeTypes.BASE_TYPE_AUDIO, "KeyPad", true, this.f7605j, phoneExtensionData.name, phoneExtensionData.uid);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m8018H(Dialog dialog, String str, View view) {
        dialog.dismiss();
        C5287b.m20583f(Permission.CALL_PHONE, new C1463c(str), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m8020J(DialogInterface dialogInterface) {
        ULogUtility.m16670f(this.f7594c, "emergency dialog disappear reset isDial");
        this.f7596e = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ boolean m8021L(View view) {
        this.f7595d.setText("");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m8022N(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m8023O(View view) {
        startActivity(new Intent(this, (Class<?>) JoinCLMWActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ void m8025Q(DialogInterface dialogInterface) {
        ULogUtility.m16670f(this.f7594c, "company extension dialog disappear reset isDial");
        this.f7596e = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V */
    public /* synthetic */ void m8027V(DialogInterface dialogInterface) {
        ULogUtility.m16670f(this.f7594c, "self extension dialog disappear reset isDial");
        this.f7596e = false;
    }

    /* renamed from: E */
    public final void m8042E() {
        ULogUtility.m16670f(this.f7594c, "enter dialPhone , isDial : " + this.f7596e);
        if (this.f7595d.length() <= 0 || this.f7596e) {
            return;
        }
        this.f7596e = true;
        ULogUtility.m16670f(this.f7594c, "enter dialPhone set isDial true ");
        final String strTrim = this.f7595d.getText().toString().trim();
        if (!Arrays.asList("110", "119", "112").contains(strTrim)) {
            C1260a.m5684v(Globals.m7388i0().m7506X(), strTrim).m15439e(new C1464d(strTrim));
            return;
        }
        final Dialog dialogM16386e = C3123g.m16386e(this, getString(R.string.phone_fragment_dial_emergency_call_title), getString(R.string.phone_fragment_dial_emergency_call_content), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView2.setText(getResources().getString(R.string.continue_btn));
        textView2.setTypeface(textView2.getTypeface(), 1);
        textView2.setTextSize(2, 16.0f);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.h2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10707b.m8018H(dialogM16386e, strTrim, view);
            }
        });
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(R.string.cancel_text);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.i2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16386e.dismiss();
            }
        });
        dialogM16386e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.cyberlink.you.activity.j2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f10771b.m8020J(dialogInterface);
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: X */
    public final void m8043X() {
        final Dialog dialogM16386e = C3123g.m16386e(this, getString(R.string.phone_fragment_dial_emergency_call_title), getString(R.string.phone_fragment_dial_company_exchange_content), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView2.setText(getResources().getString(R.string.continue_btn));
        textView2.setTypeface(textView2.getTypeface(), 1);
        textView2.setTextSize(2, 16.0f);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.m2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10877b.m8023O(view);
            }
        });
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(R.string.cancel_text);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.n2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16386e.dismiss();
            }
        });
        dialogM16386e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.cyberlink.you.activity.o2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f10995b.m8025Q(dialogInterface);
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: Y */
    public final void m8044Y() {
        final Dialog dialogM16386e = C3123g.m16386e(this, getString(R.string.phone_fragment_dial_title_failed), getString(R.string.phone_fragment_dial_self_extension_content), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        textView2.setVisibility(8);
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(getResources().getString(R.string.close));
        textView3.setTypeface(textView2.getTypeface(), 1);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.k2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16386e.dismiss();
            }
        });
        dialogM16386e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.cyberlink.you.activity.l2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f10840b.m8027V(dialogInterface);
            }
        });
        dialogM16386e.show();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Resources.NotFoundException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dial_phone);
        EditText editText = (EditText) findViewById(R.id.phoneNumber);
        this.f7595d = editText;
        editText.addTextChangedListener(this.f7598g);
        this.f7595d.setKeyListener(null);
        this.f7597f = new ToneGenerator(8, 70);
        findViewById(R.id.keypad_1).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_2).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_3).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_4).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_5).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_6).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_7).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_8).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_9).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_0).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_well).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_asterisk).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_call).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_remove).setOnClickListener(this.f7599h);
        findViewById(R.id.keypad_remove).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.cyberlink.you.activity.f2
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f10425b.m8021L(view);
            }
        });
        findViewById(R.id.keypad_remove).setVisibility(8);
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.g2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10680b.m8022N(view);
            }
        });
        if (getResources().getDisplayMetrics().heightPixels < 1920) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.keypad_layout);
            int childCount = linearLayout.getChildCount();
            int dimensionPixelSize = Globals.m7374X0().getDimensionPixelSize(R.dimen.t70dp);
            for (int i9 = 0; i9 < childCount; i9++) {
                LinearLayout linearLayout2 = (LinearLayout) linearLayout.getChildAt(i9);
                int childCount2 = linearLayout2.getChildCount();
                for (int i10 = 0; i10 < childCount2; i10++) {
                    View childAt = linearLayout2.getChildAt(i10);
                    childAt.getLayoutParams().width = dimensionPixelSize;
                    childAt.getLayoutParams().height = dimensionPixelSize;
                }
            }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f7597f.release();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStop() {
        EditText editText;
        super.onStop();
        if (!Globals.m7396z1() && (editText = this.f7595d) != null) {
            editText.setText("");
        }
        this.f7596e = false;
        ULogUtility.m16670f(this.f7594c, "onStop reset isDial");
    }
}
