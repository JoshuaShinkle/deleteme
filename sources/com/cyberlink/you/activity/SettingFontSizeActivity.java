package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import p116k4.C5169o;

/* loaded from: classes.dex */
public class SettingFontSizeActivity extends BaseActivity {

    /* renamed from: c */
    public ImageView f8994c;

    /* renamed from: d */
    public ImageView f8995d;

    /* renamed from: e */
    public ImageView f8996e;

    /* renamed from: f */
    public ImageView f8997f;

    /* renamed from: g */
    public View f8998g;

    /* renamed from: h */
    public View f8999h;

    /* renamed from: i */
    public View f9000i;

    /* renamed from: j */
    public View f9001j;

    /* renamed from: k */
    public View.OnClickListener f9002k = new ViewOnClickListenerC1727a();

    /* renamed from: l */
    public View.OnClickListener f9003l = new ViewOnClickListenerC1728b();

    /* renamed from: com.cyberlink.you.activity.SettingFontSizeActivity$a */
    public class ViewOnClickListenerC1727a implements View.OnClickListener {
        public ViewOnClickListenerC1727a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SettingFontSizeActivity.this.m9941w();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingFontSizeActivity$b */
    public class ViewOnClickListenerC1728b implements View.OnClickListener {
        public ViewOnClickListenerC1728b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.isSelected()) {
            }
            SettingFontSizeActivity.this.f8998g.setSelected(false);
            SettingFontSizeActivity.this.f8994c.setSelected(false);
            SettingFontSizeActivity.this.f8999h.setSelected(false);
            SettingFontSizeActivity.this.f8995d.setSelected(false);
            SettingFontSizeActivity.this.f9000i.setSelected(false);
            SettingFontSizeActivity.this.f8996e.setSelected(false);
            SettingFontSizeActivity.this.f9001j.setSelected(false);
            SettingFontSizeActivity.this.f8997f.setSelected(false);
            switch (view.getId()) {
                case R.id.FontSizeExtraLargeArea /* 2131296513 */:
                    SettingFontSizeActivity.this.f8997f.setSelected(true);
                    Globals.m7388i0().m7465O2(3);
                    Globals.m7388i0().m7517Z2(3);
                    break;
                case R.id.FontSizeItem /* 2131296514 */:
                case R.id.FontSizeNote /* 2131296517 */:
                case R.id.FontSizeSettingArea /* 2131296518 */:
                default:
                    SettingFontSizeActivity.this.f8995d.setSelected(true);
                    Globals.m7388i0().m7465O2(1);
                    Globals.m7388i0().m7517Z2(1);
                    break;
                case R.id.FontSizeLargeArea /* 2131296515 */:
                    SettingFontSizeActivity.this.f8996e.setSelected(true);
                    Globals.m7388i0().m7465O2(2);
                    Globals.m7388i0().m7517Z2(2);
                    break;
                case R.id.FontSizeNormalArea /* 2131296516 */:
                    SettingFontSizeActivity.this.f8995d.setSelected(true);
                    Globals.m7388i0().m7465O2(1);
                    Globals.m7388i0().m7517Z2(1);
                    break;
                case R.id.FontSizeSmailArea /* 2131296519 */:
                    SettingFontSizeActivity.this.f8994c.setSelected(true);
                    Globals.m7388i0().m7465O2(0);
                    Globals.m7388i0().m7517Z2(0);
                    break;
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9941w();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting_font_size);
        m9940v();
        Globals.m7388i0().m7470P2(true);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8998g.setOnClickListener(null);
        this.f8999h.setOnClickListener(null);
        this.f9000i.setOnClickListener(null);
        this.f9001j.setOnClickListener(null);
        super.onDestroy();
    }

    /* renamed from: u */
    public final void m9939u() {
        int iM7519a0 = Globals.m7388i0().m7519a0();
        if (iM7519a0 == 0) {
            this.f8998g.setSelected(true);
            this.f8994c.setSelected(true);
            return;
        }
        if (iM7519a0 == 1) {
            this.f8999h.setSelected(true);
            this.f8995d.setSelected(true);
        } else if (iM7519a0 == 2) {
            this.f9000i.setSelected(true);
            this.f8996e.setSelected(true);
        } else if (iM7519a0 == 3) {
            this.f9001j.setSelected(true);
            this.f8997f.setSelected(true);
        }
    }

    /* renamed from: v */
    public final void m9940v() {
        ((ImageView) findViewById(R.id.backBtn)).setOnClickListener(this.f9002k);
        View viewFindViewById = findViewById(R.id.FontSizeSmailArea);
        this.f8998g = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f9003l);
        ((TextView) this.f8998g.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_small));
        C5169o.m20159d((TextView) this.f8998g.findViewById(R.id.EditCheckTextView), 0);
        this.f8994c = (ImageView) this.f8998g.findViewById(R.id.EditCheckImageView);
        View viewFindViewById2 = findViewById(R.id.FontSizeNormalArea);
        this.f8999h = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f9003l);
        ((TextView) this.f8999h.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_normal));
        C5169o.m20159d((TextView) this.f8999h.findViewById(R.id.EditCheckTextView), 1);
        this.f8995d = (ImageView) this.f8999h.findViewById(R.id.EditCheckImageView);
        View viewFindViewById3 = findViewById(R.id.FontSizeLargeArea);
        this.f9000i = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f9003l);
        ((TextView) this.f9000i.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_large));
        C5169o.m20159d((TextView) this.f9000i.findViewById(R.id.EditCheckTextView), 2);
        this.f8996e = (ImageView) this.f9000i.findViewById(R.id.EditCheckImageView);
        View viewFindViewById4 = findViewById(R.id.FontSizeExtraLargeArea);
        this.f9001j = viewFindViewById4;
        viewFindViewById4.setOnClickListener(this.f9003l);
        ((TextView) this.f9001j.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_extra_large));
        C5169o.m20159d((TextView) this.f9001j.findViewById(R.id.EditCheckTextView), 3);
        this.f8997f = (ImageView) this.f9001j.findViewById(R.id.EditCheckImageView);
        m9939u();
    }

    /* renamed from: w */
    public final void m9941w() {
        setResult(-1, new Intent());
        finish();
    }
}
