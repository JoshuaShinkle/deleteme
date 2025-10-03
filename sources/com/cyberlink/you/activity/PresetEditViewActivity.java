package com.cyberlink.you.activity;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.VoiceRecordFragment;
import com.cyberlink.you.fingerpaint.FingerPaintActivity;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.panel.coloreffectpanel.FragmentC3244b;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import p025c4.C0724b;
import p116k4.C5152i0;
import p116k4.C5177q1;
import p116k4.C5187v0;
import p184r4.FragmentC6220a;

/* loaded from: classes.dex */
public class PresetEditViewActivity extends BaseFragmentActivity implements View.OnClickListener {

    /* renamed from: A */
    public static TextView f8502A;

    /* renamed from: y */
    public static ImageItem f8503y;

    /* renamed from: z */
    public static DevelopSetting f8504z;

    /* renamed from: f */
    public ProgressDialog f8508f;

    /* renamed from: g */
    public VoiceRecordFragment f8509g;

    /* renamed from: h */
    public EditText f8510h;

    /* renamed from: i */
    public TextView f8511i;

    /* renamed from: j */
    public View f8512j;

    /* renamed from: k */
    public View f8513k;

    /* renamed from: l */
    public View f8514l;

    /* renamed from: m */
    public View f8515m;

    /* renamed from: n */
    public View f8516n;

    /* renamed from: o */
    public Button f8517o;

    /* renamed from: p */
    public String f8518p;

    /* renamed from: q */
    public String f8519q;

    /* renamed from: r */
    public String f8520r;

    /* renamed from: c */
    public PageID f8505c = PageID.effect;

    /* renamed from: d */
    public Fragment f8506d = null;

    /* renamed from: e */
    public Fragment f8507e = null;

    /* renamed from: s */
    public final int f8521s = 4;

    /* renamed from: t */
    public final int f8522t = 1;

    /* renamed from: u */
    public boolean f8523u = false;

    /* renamed from: v */
    public boolean f8524v = false;

    /* renamed from: w */
    public boolean f8525w = false;

    /* renamed from: x */
    public VoiceRecordFragment.InterfaceC1861g f8526x = new C1630a();

    public enum PageID {
        effect,
        textNote
    }

    /* renamed from: com.cyberlink.you.activity.PresetEditViewActivity$a */
    public class C1630a implements VoiceRecordFragment.InterfaceC1861g {
        public C1630a() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: a */
        public void mo7976a() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: b */
        public void mo7977b(boolean z8) {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: c */
        public void mo7978c(String str, String str2) {
            PresetEditViewActivity.this.f8519q = str;
            PresetEditViewActivity.this.f8520r = str2;
            PresetEditViewActivity.this.m9366b1();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PresetEditViewActivity$b */
    public class C1631b extends C5177q1.b {
    }

    /* renamed from: com.cyberlink.you.activity.PresetEditViewActivity$c */
    public class AsyncTaskC1632c extends AsyncTask<Void, Void, Drawable> {
        public AsyncTaskC1632c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Drawable doInBackground(Void... voidArr) {
            try {
                return PresetEditViewActivity.this.getResources().getDrawable(R.drawable.color_effect_dark_mask);
            } catch (OutOfMemoryError unused) {
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Drawable drawable) {
            if (drawable != null) {
                PresetEditViewActivity.this.f8515m.setBackground(drawable);
            }
        }

        public /* synthetic */ AsyncTaskC1632c(PresetEditViewActivity presetEditViewActivity, C1630a c1630a) {
            this();
        }
    }

    /* renamed from: Q0 */
    public static void m9353Q0(String str) {
        f8502A.setText(str);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(f8502A, "alpha", 1.0f, BitmapDescriptorFactory.HUE_RED);
        objectAnimatorOfFloat.setRepeatCount(0);
        objectAnimatorOfFloat.setDuration(1500L);
        objectAnimatorOfFloat.start();
        objectAnimatorOfFloat.addListener(new C1631b());
    }

    /* renamed from: O0 */
    public final void m9354O0() {
        this.f8513k.setVisibility(0);
        if (this.f8511i.getText().length() > 0) {
            this.f8511i.setMaxLines(4);
            this.f8512j.setVisibility(8);
            m9356R0(false);
        } else {
            this.f8511i.setMaxLines(1);
            this.f8512j.setVisibility(0);
            m9356R0(true);
        }
        this.f8505c = PageID.effect;
    }

    /* renamed from: P0 */
    public final void m9355P0() {
        finish();
    }

    /* renamed from: R0 */
    public final void m9356R0(boolean z8) {
        this.f8512j.setEnabled(z8);
        this.f8512j.setAlpha(z8 ? 1.0f : 0.5f);
    }

    /* renamed from: S0 */
    public final void m9357S0() {
        if (this.f8506d != null) {
            return;
        }
        FragmentC6220a fragmentC6220a = new FragmentC6220a();
        FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.colorPresetViewerLayout, fragmentC6220a);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        this.f8506d = fragmentC6220a;
    }

    /* renamed from: T0 */
    public final void m9358T0() {
        if (this.f8507e != null) {
            return;
        }
        FragmentC3244b fragmentC3244b = new FragmentC3244b();
        this.f8507e = fragmentC3244b;
        m9368d1(fragmentC3244b, false);
        this.f8516n.setSelected(true);
    }

    /* renamed from: U0 */
    public final void m9359U0() {
        ImageView imageView;
        Button button = (Button) findViewById(R.id.post);
        this.f8517o = button;
        button.setOnClickListener(this);
        findViewById(R.id.btn_drawtool_pen).setOnClickListener(this);
        View viewFindViewById = findViewById(R.id.btn_applay_preset);
        this.f8516n = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        findViewById(R.id.btn_cancel_text_note).setOnClickListener(this);
        findViewById(R.id.btn_confirm_text_note).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        f8502A = (TextView) findViewById(R.id.effectName);
        this.f8511i = (TextView) findViewById(R.id.btn_add_text);
        View viewFindViewById2 = findViewById(R.id.btn_add_voice);
        this.f8512j = viewFindViewById2;
        if (this.f8523u) {
            viewFindViewById2.setVisibility(8);
        } else {
            viewFindViewById2.setOnClickListener(this);
        }
        if (this.f8524v) {
            findViewById(R.id.btn_add_text).setVisibility(8);
        } else {
            findViewById(R.id.btn_add_text).setOnClickListener(this);
        }
        if (this.f8525w && (imageView = (ImageView) findViewById(R.id.btn_drawtool_pen)) != null) {
            imageView.setVisibility(8);
        }
        this.f8513k = findViewById(R.id.toolbarLayout);
        this.f8510h = (EditText) findViewById(R.id.input);
        this.f8514l = findViewById(R.id.noteEditLayout);
        this.f8515m = findViewById(R.id.colorPresetViewerMask);
        new AsyncTaskC1632c(this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: V0 */
    public boolean m9360V0() {
        if (this.f8514l.isShown()) {
            m9364Z0(true);
            return false;
        }
        if (!this.f8509g.isVisible()) {
            return true;
        }
        m9370f1(false);
        m9354O0();
        return false;
    }

    /* renamed from: W0 */
    public void m9361W0(boolean z8, String str, Uri uri) {
        if (!isFinishing()) {
            C5152i0.m20065b(this.f8508f);
        }
        if (z8 && CLUtility.m16613z1(str, uri)) {
            Intent intent = new Intent();
            intent.putExtra("image", str);
            if (uri != null) {
                intent.putExtra("imageUri", uri.toString());
            }
            String string = this.f8511i.getText().toString();
            if (!string.isEmpty()) {
                intent.putExtra(FirebaseAnalytics.Param.CONTENT, string);
            }
            String str2 = this.f8519q;
            if (str2 != null && this.f8520r != null) {
                intent.putExtra("audioPath", str2);
                intent.putExtra("audioDuration", this.f8520r);
            }
            setResult(-1, intent);
        } else {
            C5187v0.m20267c(R.string.failed);
        }
        finish();
    }

    /* renamed from: X0 */
    public final void m9362X0() {
        Fragment fragment = this.f8507e;
        if (fragment != null && fragment.isVisible()) {
            m9369e1(false);
        }
        this.f8513k.setVisibility(4);
        this.f8514l.setVisibility(0);
        this.f8514l.requestFocus();
        CLUtility.m16582r2(true, getWindow());
        CLUtility.m16606x2(this);
        this.f8505c = PageID.textNote;
    }

    /* renamed from: Y0 */
    public final void m9363Y0() {
        Fragment fragment = this.f8507e;
        if (fragment != null && fragment.isVisible()) {
            m9369e1(false);
        }
        this.f8513k.setVisibility(4);
        m9370f1(true);
    }

    /* renamed from: Z0 */
    public final void m9364Z0(boolean z8) {
        CLUtility.m16589t1(this);
        this.f8514l.setVisibility(4);
        CLUtility.m16582r2(true, getWindow());
        if (z8) {
            this.f8511i.setText(this.f8510h.getText());
        } else {
            this.f8510h.setText(this.f8511i.getText());
            EditText editText = this.f8510h;
            editText.setSelection(editText.getText().length());
        }
        m9354O0();
    }

    /* renamed from: a1 */
    public final void m9365a1() {
        Fragment fragment = this.f8507e;
        if (fragment == null) {
            return;
        }
        if (fragment.isHidden()) {
            m9369e1(true);
        } else {
            m9369e1(false);
        }
    }

    /* renamed from: b1 */
    public final void m9366b1() {
        if (this.f8507e != null) {
            if (this.f8508f == null) {
                ProgressDialog progressDialog = new ProgressDialog(this);
                this.f8508f = progressDialog;
                progressDialog.setMessage(getResources().getString(R.string.processing));
                this.f8508f.setIndeterminate(true);
                this.f8508f.setCancelable(false);
                this.f8508f.show();
            }
            ((FragmentC3244b) this.f8507e).m17369N();
        }
    }

    /* renamed from: c1 */
    public final void m9367c1() {
        if (f8503y == null) {
            Log.e("PresetEditViewActivity", "curImageItem is null");
            return;
        }
        CLUtility.m16589t1(this);
        Intent intent = new Intent(getBaseContext(), (Class<?>) FingerPaintActivity.class);
        intent.putExtra("src", f8503y.m16144q());
        intent.putExtra("uri", f8503y.m16145r());
        intent.putExtra("doodleImg", this.f8518p);
        intent.putExtra("isDoodleOnly", true);
        intent.putExtra("toolbarMode", FingerPaintActivity.ToolbarMode.Mode2);
        if (((FragmentC3244b) this.f8507e).m17364E() != null) {
            intent.putExtra("isApplyPreset", true);
            f8504z = ((FragmentC3244b) this.f8507e).m17364E();
        }
        startActivityForResult(intent, 0);
    }

    /* renamed from: d1 */
    public final void m9368d1(Fragment fragment, boolean z8) {
        FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
        if (z8) {
            fragmentTransactionBeginTransaction.setCustomAnimations(R.animator.panel_slide_in_top, 0);
        }
        fragmentTransactionBeginTransaction.replace(R.id.autoBeautifierPanelContainer, fragment);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        Fragment fragment2 = this.f8506d;
        if (fragment2 instanceof FragmentC6220a) {
            ((FragmentC6220a) fragment2).m23772a(fragment);
        }
    }

    /* renamed from: e1 */
    public final void m9369e1(boolean z8) {
        if (z8) {
            getFragmentManager().beginTransaction().show(this.f8507e).commitAllowingStateLoss();
            this.f8515m.setVisibility(0);
        } else {
            getFragmentManager().beginTransaction().hide(this.f8507e).commitAllowingStateLoss();
            this.f8515m.setVisibility(4);
        }
        this.f8516n.setSelected(z8);
    }

    /* renamed from: f1 */
    public final void m9370f1(boolean z8) {
        if (z8) {
            getSupportFragmentManager().mo1844a().mo1802r(this.f8509g).mo1794h();
        } else {
            getSupportFragmentManager().mo1844a().mo1799n(this.f8509g).mo1794h();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        if (i9 == 0 && i10 == -1) {
            if (f8503y == null) {
                Log.e("PresetEditViewActivity", "curImageItem is null");
            } else {
                String string = intent.getExtras().getString("doodleImg");
                this.f8518p = string;
                ((FragmentC3244b) this.f8507e).m17375T(string);
                if (intent.getExtras().getBoolean("start_import") && Globals.m7383g1() == 1 && f8503y.m16132e()) {
                    m9366b1();
                }
            }
        }
        super.onActivityResult(i9, i10, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (m9360V0()) {
            m9355P0();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back /* 2131297178 */:
                m9355P0();
                break;
            case R.id.btn_add_text /* 2131297261 */:
                m9362X0();
                break;
            case R.id.btn_add_voice /* 2131297263 */:
                m9363Y0();
                break;
            case R.id.btn_applay_preset /* 2131297264 */:
                m9365a1();
                break;
            case R.id.btn_cancel_text_note /* 2131297267 */:
                m9364Z0(false);
                break;
            case R.id.btn_confirm_text_note /* 2131297268 */:
                m9364Z0(true);
                break;
            case R.id.btn_drawtool_pen /* 2131297272 */:
                m9367c1();
                break;
            case R.id.post /* 2131298351 */:
                m9366b1();
                break;
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_preset_edit);
        Intent intent = getIntent();
        int i9 = intent.getExtras().getInt("position");
        this.f8523u = intent.getExtras().getBoolean("hideVoice4ImportPhoto", false);
        this.f8524v = intent.getExtras().getBoolean("hideText4ImportPhoto", false);
        this.f8525w = intent.getExtras().getBoolean("enableMeetingShareDoc", false);
        Log.d("PresetEditViewActivity", "[onCreate] mIsEnableMeetingShareDoc = " + this.f8525w);
        if (this.f8525w) {
            this.f8524v = true;
            this.f8523u = true;
        }
        ArrayList<ImageItem> arrayListM3559b = C0724b.m3557c().m3559b();
        if (arrayListM3559b != null) {
            Log.d("PresetEditViewActivity", "[onCreate] mImageItemList size = " + arrayListM3559b.size());
        }
        Log.d("PresetEditViewActivity", "[onCreate] mPosition = " + i9);
        if (arrayListM3559b == null || i9 >= arrayListM3559b.size()) {
            C5187v0.m20267c(R.string.no_local_photos);
            finish();
            return;
        }
        f8503y = arrayListM3559b.get(i9);
        if (bundle == null) {
            VoiceRecordFragment voiceRecordFragment = new VoiceRecordFragment();
            this.f8509g = voiceRecordFragment;
            voiceRecordFragment.m11003X(this.f8526x);
            getSupportFragmentManager().mo1844a().m1981c(R.id.voiceRecordFragmentContainer, this.f8509g, "VoiceRecord").mo1799n(this.f8509g).mo1794h();
        } else {
            VoiceRecordFragment voiceRecordFragment2 = (VoiceRecordFragment) getSupportFragmentManager().mo1848e("VoiceRecord");
            this.f8509g = voiceRecordFragment2;
            voiceRecordFragment2.m11003X(this.f8526x);
            m9370f1(false);
        }
        m9359U0();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        f8503y = null;
        f8504z = null;
        f8502A = null;
        C5152i0.m20065b(this.f8508f);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m9357S0();
        m9358T0();
    }
}
