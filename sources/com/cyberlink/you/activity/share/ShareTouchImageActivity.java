package com.cyberlink.you.activity.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.activity.VoiceRecordFragment;
import com.cyberlink.you.fingerpaint.FingerPaintActivity;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.view.TouchViewPager;
import com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.List;
import p173q2.C6136j;
import p189s0.AbstractC6243a;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class ShareTouchImageActivity extends BaseFragmentActivity {

    /* renamed from: c */
    public View f11324c;

    /* renamed from: d */
    public EditText f11325d;

    /* renamed from: e */
    public Button f11326e;

    /* renamed from: f */
    public CheckBox f11327f;

    /* renamed from: g */
    public ArrayList<ImageItem> f11328g;

    /* renamed from: i */
    public TouchViewPager f11330i;

    /* renamed from: j */
    public C2482d f11331j;

    /* renamed from: k */
    public VoiceRecordFragment f11332k;

    /* renamed from: h */
    public int f11329h = -1;

    /* renamed from: l */
    public VoiceRecordFragment.InterfaceC1861g f11333l = new C2479a();

    /* renamed from: m */
    public View.OnClickListener f11334m = new ViewOnClickListenerC2480b();

    /* renamed from: com.cyberlink.you.activity.share.ShareTouchImageActivity$a */
    public class C2479a implements VoiceRecordFragment.InterfaceC1861g {
        public C2479a() {
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
            if (ShareTouchImageActivity.this.f11328g == null || ShareTouchImageActivity.this.f11328g.size() != 1) {
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("voicePath", str);
            intent.putExtra("duration", str2);
            intent.putExtra("position", ShareTouchImageActivity.this.f11329h);
            intent.putExtra("start_import", true);
            ShareTouchImageActivity.this.setResult(-1, intent);
            ShareTouchImageActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareTouchImageActivity$b */
    public class ViewOnClickListenerC2480b implements View.OnClickListener {
        public ViewOnClickListenerC2480b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ShareTouchImageActivity.this.f11328g == null || ShareTouchImageActivity.this.f11328g.size() != 1) {
                return;
            }
            String string = ShareTouchImageActivity.this.f11325d.getText().toString();
            Intent intent = new Intent();
            intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, string);
            intent.putExtra("position", ShareTouchImageActivity.this.f11329h);
            intent.putExtra("handDrawImg", ((ImageItem) ShareTouchImageActivity.this.f11328g.get(ShareTouchImageActivity.this.f11329h)).m16139l());
            intent.putExtra("start_import", true);
            ShareTouchImageActivity.this.setResult(-1, intent);
            CLUtility.m16589t1(ShareTouchImageActivity.this.m12815U0());
            ShareTouchImageActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareTouchImageActivity$c */
    public class C2481c implements TextWatcher {
        public C2481c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            ShareTouchImageActivity.this.f11326e.setEnabled(!C6383t.m24517f(editable.toString().trim()));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareTouchImageActivity$d */
    public class C2482d extends AbstractC6243a {

        /* renamed from: a */
        public List<ImageItem> f11338a;

        /* renamed from: b */
        public Activity f11339b;

        public C2482d(Activity activity, List<ImageItem> list) {
            this.f11339b = activity;
            this.f11338a = list;
        }

        /* renamed from: a */
        public void m12819a() {
            this.f11338a = null;
            notifyDataSetChanged();
        }

        @Override // p189s0.AbstractC6243a
        public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            List<ImageItem> list = this.f11338a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // p189s0.AbstractC6243a
        public Object instantiateItem(ViewGroup viewGroup, int i9) {
            View viewInflate = this.f11339b.getLayoutInflater().inflate(R.layout.view_item_show_media, viewGroup, false);
            TouchImageView touchImageView = (TouchImageView) viewInflate.findViewById(R.id.imageViewPhoto);
            viewInflate.setTag(touchImageView);
            String strM16144q = this.f11338a.get(i9).m16144q();
            Uri uriM16510Z1 = CLUtility.m16510Z1(this.f11338a.get(i9).m16145r());
            if (uriM16510Z1 != null) {
                C6136j.m23596p(ShareTouchImageActivity.this, touchImageView, uriM16510Z1, 0, 1280);
            } else {
                C6136j.m23601u(ShareTouchImageActivity.this, touchImageView, strM16144q, 0, 1280);
            }
            viewGroup.addView(viewInflate, 0);
            return viewInflate;
        }

        @Override // p189s0.AbstractC6243a
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // p189s0.AbstractC6243a
        public void setPrimaryItem(ViewGroup viewGroup, int i9, Object obj) {
            ShareTouchImageActivity.this.f11330i.f14275k0 = (TouchImageView) ((View) obj).getTag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V0 */
    public /* synthetic */ void m12811V0(View view) {
        CLUtility.m16589t1(m12815U0());
        Intent intent = new Intent(getBaseContext(), (Class<?>) FingerPaintActivity.class);
        intent.putExtra("mergedImage", this.f11328g.get(this.f11329h).m16135h());
        intent.putExtra("mergedImageUri", this.f11328g.get(this.f11329h).m16136i());
        startActivityForResult(intent, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W0 */
    public /* synthetic */ void m12812W0(View view, boolean z8) {
        if (z8) {
            CLUtility.m16606x2(m12815U0());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X0 */
    public /* synthetic */ boolean m12813X0(View view, MotionEvent motionEvent) {
        CLUtility.m16589t1(m12815U0());
        return false;
    }

    /* renamed from: T0 */
    public final void m12814T0(boolean z8) {
        ImageItem imageItem = this.f11328g.get(this.f11329h);
        if (imageItem != null) {
            if (TextUtils.isEmpty(imageItem.m16139l()) && TextUtils.isEmpty(imageItem.m16140m())) {
                return;
            }
            imageItem.m16120A(null);
            imageItem.m16121B(null);
            if (z8) {
                String strM16135h = imageItem.m16135h();
                Uri uriM16510Z1 = CLUtility.m16510Z1(imageItem.m16136i());
                if (uriM16510Z1 != null) {
                    C6136j.m23596p(this, this.f11330i.f14275k0, uriM16510Z1, 0, 1280);
                } else {
                    C6136j.m23601u(this, this.f11330i.f14275k0, strM16135h, 0, 1280);
                }
            }
        }
    }

    /* renamed from: U0 */
    public final Activity m12815U0() {
        return this;
    }

    /* renamed from: Y0 */
    public final void m12816Y0() {
        this.f11325d.requestFocus();
        this.f11324c.setVisibility(0);
    }

    /* renamed from: Z0 */
    public final void m12817Z0() {
        m12818a1(true);
    }

    /* renamed from: a1 */
    public final void m12818a1(boolean z8) {
        if (z8) {
            getSupportFragmentManager().mo1844a().mo1802r(this.f11332k).mo1794h();
        } else {
            getSupportFragmentManager().mo1844a().mo1799n(this.f11332k).mo1794h();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        if (i9 == 0) {
            if (i10 == -1) {
                String string = intent.getExtras().getString("handDrawImg");
                this.f11328g.get(this.f11329h).m16120A(string);
                if (intent.getExtras().getBoolean("start_import")) {
                    String string2 = this.f11325d.getText().toString();
                    Intent intent2 = new Intent();
                    intent2.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, string2);
                    intent2.putExtra("position", this.f11329h);
                    intent2.putExtra("handDrawImg", string);
                    intent2.putExtra("start_import", true);
                    setResult(-1, intent2);
                    finish();
                } else {
                    if (this.f11324c.getVisibility() != 0) {
                        CLUtility.m16606x2(m12815U0());
                    } else {
                        m12816Y0();
                    }
                    C6136j.m23601u(this, this.f11330i.f14275k0, string, 0, 1280);
                }
            } else if (i10 == 0) {
                CLUtility.m16589t1(m12815U0());
                if (this.f11324c.getVisibility() == 0) {
                    this.f11324c.setVisibility(8);
                }
                m12814T0(true);
            }
        }
        super.onActivityResult(i9, i10, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        CLUtility.m16589t1(m12815U0());
        m12814T0(false);
        Intent intent = new Intent();
        intent.putExtra("start_import", false);
        setResult(-1, intent);
        C2482d c2482d = this.f11331j;
        if (c2482d != null) {
            c2482d.m12819a();
        }
        finish();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Intent intent = getIntent();
        boolean z8 = intent.getExtras().getBoolean("isVoiceNote", false);
        boolean z9 = intent.getExtras().getBoolean("isTextNote", false);
        this.f11328g = (ArrayList) intent.getExtras().getSerializable("sharedImageItemList");
        this.f11329h = intent.getExtras().getInt("position", -1);
        setContentView(R.layout.share_touch_image_activity);
        findViewById(R.id.btn_drawtool_pen).setOnClickListener(new View.OnClickListener() { // from class: e3.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f16515b.m12811V0(view);
            }
        });
        CheckBox checkBox = (CheckBox) findViewById(R.id.itemCheckBox);
        this.f11327f = checkBox;
        checkBox.setVisibility(8);
        this.f11324c = findViewById(R.id.noteEditLayout);
        EditText editText = (EditText) findViewById(R.id.input);
        this.f11325d = editText;
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: e3.m0
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z10) {
                this.f16518b.m12812W0(view, z10);
            }
        });
        this.f11325d.addTextChangedListener(new C2481c());
        Button button = (Button) findViewById(R.id.post);
        this.f11326e = button;
        button.setOnClickListener(this.f11334m);
        if (this.f11328g != null) {
            this.f11330i = (TouchViewPager) findViewById(R.id.viewpager);
            C2482d c2482d = new C2482d(this, this.f11328g);
            this.f11331j = c2482d;
            this.f11330i.setAdapter(c2482d);
            int i9 = this.f11329h;
            if (i9 >= 0 && i9 < this.f11328g.size()) {
                this.f11330i.setCurrentItem(this.f11329h);
            }
        }
        if (bundle == null) {
            VoiceRecordFragment voiceRecordFragment = new VoiceRecordFragment();
            this.f11332k = voiceRecordFragment;
            voiceRecordFragment.m11003X(this.f11333l);
            getSupportFragmentManager().mo1844a().m1981c(R.id.voiceRecordFragmentContainer, this.f11332k, "VoiceRecord").mo1799n(this.f11332k).mo1794h();
        } else {
            VoiceRecordFragment voiceRecordFragment2 = (VoiceRecordFragment) getSupportFragmentManager().mo1848e("VoiceRecord");
            this.f11332k = voiceRecordFragment2;
            voiceRecordFragment2.m11003X(this.f11333l);
            m12818a1(false);
        }
        if (z8) {
            m12817Z0();
        }
        if (z9) {
            m12816Y0();
        }
        if (z9 || z8) {
            this.f11330i.setPagingEnabled(false);
        }
        findViewById(R.id.commentEditLayout).setOnTouchListener(new View.OnTouchListener() { // from class: e3.n0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f16520b.m12813X0(view, motionEvent);
            }
        });
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f11327f.setOnCheckedChangeListener(null);
        this.f11327f = null;
        System.gc();
    }
}
