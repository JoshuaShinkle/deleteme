package com.cyberlink.meeting.page;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.meeting.page.ConfirmPwdActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import p116k4.C5152i0;
import p116k4.C5170o0;

/* loaded from: classes.dex */
public class ConfirmPwdActivity extends BaseActivity {

    /* renamed from: o */
    public static final String f6341o = "ConfirmPwdActivity";

    /* renamed from: c */
    public String f6342c;

    /* renamed from: d */
    public String f6343d;

    /* renamed from: e */
    public TextView f6344e;

    /* renamed from: f */
    public EditText f6345f;

    /* renamed from: g */
    public TextView f6346g;

    /* renamed from: h */
    public EditText f6347h;

    /* renamed from: i */
    public Button f6348i;

    /* renamed from: k */
    public ArrayList<String> f6350k;

    /* renamed from: j */
    public boolean f6349j = false;

    /* renamed from: l */
    public int f6351l = -1;

    /* renamed from: m */
    public int f6352m = -1;

    /* renamed from: n */
    public String f6353n = "";

    /* renamed from: com.cyberlink.meeting.page.ConfirmPwdActivity$a */
    public class C1264a extends GestureDetector.SimpleOnGestureListener {
        public C1264a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(ConfirmPwdActivity.this);
            return false;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ConfirmPwdActivity$b */
    public class C1265b implements TextWatcher {
        public C1265b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if ("needUserPasswordVerification".equals(ConfirmPwdActivity.this.f6353n)) {
                ConfirmPwdActivity.this.f6348i.setEnabled(editable.length() >= 1);
            } else {
                ConfirmPwdActivity.this.f6348i.setEnabled(editable.length() >= 4);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ConfirmPwdActivity$c */
    public class C1266c extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ ProgressDialog f6356j;

        /* renamed from: k */
        public final /* synthetic */ String f6357k;

        public C1266c(ProgressDialog progressDialog, String str) {
            this.f6356j = progressDialog;
            this.f6357k = str;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            ConfirmPwdActivity.this.m5755w(this.f6356j, i9, str);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            ConfirmPwdActivity.this.m5754v(this.f6356j, meeting, this.f6357k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m5738B(View view) {
        m5752s();
    }

    /* renamed from: C */
    public static /* synthetic */ boolean m5739C(GestureDetector gestureDetector, View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ boolean m5748y(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 != 6) {
            return false;
        }
        this.f6348i.performClick();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m5749z(View view) {
        m5753u();
    }

    /* renamed from: D */
    public final void m5750D(TextView textView) {
        textView.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: o2.e
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i9, KeyEvent keyEvent) {
                return this.f18318b.m5748y(textView2, i9, keyEvent);
            }
        });
        textView.addTextChangedListener(new C1265b());
    }

    /* renamed from: E */
    public final void m5751E() {
        TextView textView = (TextView) findViewById(R.id.verifyPasswordTitle);
        TextView textView2 = (TextView) findViewById(R.id.txtMeetingPwdDesc);
        this.f6344e = (TextView) findViewById(R.id.txtMeetingPwdErr);
        Button button = (Button) findViewById(R.id.btnMeetingPwd);
        this.f6348i = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: o2.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18310b.m5749z(view);
            }
        });
        findViewById(R.id.btnMeetingPwdBack).setOnClickListener(new View.OnClickListener() { // from class: o2.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18313b.m5738B(view);
            }
        });
        this.f6345f = (EditText) findViewById(R.id.txtMeetingPwd);
        this.f6346g = (TextView) findViewById(R.id.txtEmail);
        this.f6347h = (EditText) findViewById(R.id.txtUserPwd);
        if ("needUserPasswordVerification".equals(this.f6353n)) {
            textView2.setText(R.string.clm_meeting_user_verification);
            this.f6345f.setVisibility(8);
            textView.setText(R.string.clmw_function_fragment_item_title_join_meetings_webinars);
            findViewById(R.id.verifyUserPassword).setVisibility(0);
            ((TextView) findViewById(R.id.txtEmail)).setText(Globals.m7388i0().m7498V0());
            this.f6348i.setText(R.string.clmw_join_join_meeting);
            m5750D(this.f6347h);
            this.f6348i.setEnabled(this.f6347h.getText().length() >= 1);
        } else {
            textView2.setText(R.string.clm_meeting_pwd_desc);
            this.f6345f.setVisibility(0);
            textView.setText(R.string.clm_meeting_pwd);
            findViewById(R.id.verifyUserPassword).setVisibility(8);
            this.f6348i.setText(R.string.clm_meeting_pwd_continue);
            m5750D(this.f6345f);
            this.f6348i.setEnabled(this.f6347h.getText().length() >= 4);
        }
        final GestureDetector gestureDetector = new GestureDetector(this, new C1264a());
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() { // from class: o2.d
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ConfirmPwdActivity.m5739C(gestureDetector, view, motionEvent);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Window window = getWindow();
        window.addFlags(2097152);
        window.addFlags(524288);
        super.onCreate(bundle);
        setContentView(R.layout.activity_meeting_pwd);
        this.f6342c = getIntent().getStringExtra("c.c.m.p.ConfirmPwd_EXTRA_EVENT_ID");
        this.f6343d = getIntent().getStringExtra("meetingLtiToken");
        Log.d("joinFace", "ConfirmPwdActivity");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (C5170o0.m20170e(this.f6342c)) {
                this.f6342c = extras.getString("meetingId");
            }
            this.f6353n = extras.getString("type");
            boolean z8 = extras.getBoolean("needUserFaceVerification", false);
            this.f6349j = z8;
            if (z8) {
                this.f6350k = extras.getStringArrayList("faceFeatures");
                this.f6351l = extras.getInt("featureType", -1);
                this.f6352m = extras.getInt("featureSubType", -1);
            }
        }
        if (C5170o0.m20170e(this.f6342c)) {
            m5752s();
        } else {
            m5751E();
        }
    }

    /* renamed from: s */
    public final void m5752s() {
        setResult(0);
        CLUtility.m16589t1(this);
        finish();
    }

    /* renamed from: u */
    public final void m5753u() {
        ArrayList<String> arrayList;
        ProgressDialog progressDialogShow = ProgressDialog.show(this, "", getString(R.string.loading), true);
        this.f6346g.getText().toString();
        this.f6347h.getText().toString();
        String string = this.f6345f.getText().toString();
        if (this.f6349j && "needMeetingPasswordVerification".equals(this.f6353n) && ((arrayList = this.f6350k) == null || arrayList.isEmpty() || this.f6351l == -1 || this.f6352m == -1)) {
            C5152i0.m20065b(progressDialogShow);
        } else {
            C1260a.m5675m(this.f6342c, string, this.f6343d, Globals.m7388i0().m7506X()).m15439e(new C1266c(progressDialogShow, string));
        }
    }

    /* renamed from: v */
    public final void m5754v(ProgressDialog progressDialog, Meeting meeting, String str) {
        C5152i0.m20065b(progressDialog);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("c.c.m.p.ConfirmPwd_EXTRA_PASSWD", str);
        bundle.putString("meetingMServerAddress", meeting.mserverAddr);
        bundle.putString("meetingMServerToken", meeting.token);
        intent.putExtras(bundle);
        setResult(-1, intent);
        CLUtility.m16589t1(this);
        finish();
    }

    /* renamed from: w */
    public final void m5755w(ProgressDialog progressDialog, int i9, String str) {
        Log.w(f6341o, "error: " + i9 + ", " + str);
        C5152i0.m20065b(progressDialog);
        int iM5671h = C1260a.m5671h(i9, str);
        if (i9 == 403 && (iM5671h == R.string.clm_error_pwd || iM5671h == R.string.clm_error_user_invalid)) {
            if (iM5671h != R.string.clm_error_pwd || !this.f6345f.getText().toString().isEmpty()) {
                this.f6344e.setText(iM5671h);
                return;
            }
            this.f6353n = "needMeetingPasswordVerification";
            this.f6349j = false;
            m5751E();
            this.f6344e.setText("");
            return;
        }
        Intent intent = new Intent();
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putInt("joinMeetingErrorCode", i9);
        extras.putString("meetingErrorBody", str);
        intent.putExtras(extras);
        setResult(-1, intent);
        finish();
    }
}
