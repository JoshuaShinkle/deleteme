package com.cyberlink.meeting.page;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.cyberlink.clrtc.C1121t;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import p044d2.C4664a;
import p044d2.C4665b;
import p116k4.C5179r0;
import p135m2.C5314a;
import p135m2.C5315b;

/* loaded from: classes.dex */
public class MeetingSettingActivity extends BaseActivity {

    /* renamed from: com.cyberlink.meeting.page.MeetingSettingActivity$a */
    public class C1278a extends AbstractC1281d {

        /* renamed from: a */
        public final /* synthetic */ TextView f6410a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1278a(TextView textView) {
            super(null);
            this.f6410a = textView;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i9, boolean z8) {
            int i10 = i9 - 100;
            C1121t.m5161j(i10);
            this.f6410a.setText(MeetingSettingActivity.this.getString(R.string.debug_meeting_setting_pink_noise_max_volume, Integer.valueOf(i10)));
            C5179r0.m20247b(this.f6410a, 1);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.MeetingSettingActivity$b */
    public class C1279b extends AbstractC1281d {

        /* renamed from: a */
        public final /* synthetic */ TextView f6412a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1279b(TextView textView) {
            super(null);
            this.f6412a = textView;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i9, boolean z8) {
            int i10 = i9 - 100;
            C1121t.m5162k(i10);
            this.f6412a.setText(MeetingSettingActivity.this.getString(R.string.debug_meeting_setting_pink_noise_min_volume, Integer.valueOf(i10)));
            C5179r0.m20247b(this.f6412a, 1);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.MeetingSettingActivity$c */
    public class C1280c extends AbstractC1281d {

        /* renamed from: a */
        public final /* synthetic */ TextView f6414a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1280c(TextView textView) {
            super(null);
            this.f6414a = textView;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i9, boolean z8) {
            int i10 = i9 - 50;
            C1121t.m5165n(i10);
            this.f6414a.setText(MeetingSettingActivity.this.getString(R.string.debug_meeting_setting_poor_connection_audio_hint_volume, Integer.valueOf(i10)));
            C5179r0.m20247b(this.f6414a, 1);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.MeetingSettingActivity$d */
    public static abstract class AbstractC1281d implements SeekBar.OnSeekBarChangeListener {
        public AbstractC1281d() {
        }

        public /* synthetic */ AbstractC1281d(C1278a c1278a) {
            this();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m5822C(CompoundButton compoundButton, boolean z8) {
        C1121t.m5160i(z8);
        if (z8) {
            ((Switch) findViewById(R.id.aospAec)).setChecked(false);
            C4665b.m18663o(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m5823D(CompoundButton compoundButton, boolean z8) {
        if (!z8 && !C1121t.m5156e()) {
            ((Switch) findViewById(R.id.swtAecm)).setChecked(true);
            return;
        }
        C4665b.m18663o(z8);
        if (z8) {
            ((Switch) findViewById(R.id.swtAecm)).setChecked(false);
            C1121t.m5160i(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m5824E(CompoundButton compoundButton, boolean z8) {
        m5864r0((ViewGroup) findViewById(R.id.layoutAutoReject), !z8);
        C5315b.m20808x(z8);
        if (z8) {
            C5315b.m20810z(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m5826I(CompoundButton compoundButton, boolean z8) {
        m5864r0((ViewGroup) findViewById(R.id.layoutAutoAnswer), !z8);
        C5315b.m20810z(z8);
        if (z8) {
            C5315b.m20808x(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m5829N(ViewGroup viewGroup, ViewGroup viewGroup2, CompoundButton compoundButton, boolean z8) {
        C1121t.m5163l(z8);
        m5864r0(viewGroup, z8);
        m5864r0(viewGroup2, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m5830O(ViewGroup viewGroup, CompoundButton compoundButton, boolean z8) {
        C1121t.m5164m(z8);
        m5864r0(viewGroup, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ void m5832Q(View view) {
        finish();
    }

    /* renamed from: T */
    public final void m5845T() {
        Switch r02 = (Switch) findViewById(R.id.force16khzSampling);
        r02.setChecked(C5314a.m20794d());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.m
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                C1121t.m5159h(z8);
            }
        });
    }

    /* renamed from: V */
    public final void m5846V() {
        m5865y();
        m5845T();
        m5847X();
        m5848Y();
    }

    /* renamed from: X */
    public final void m5847X() {
        Switch r02 = (Switch) findViewById(R.id.swtAecm);
        r02.setChecked(C1121t.m5156e());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.p
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                this.f18357a.m5822C(compoundButton, z8);
            }
        });
    }

    /* renamed from: Y */
    public final void m5848Y() {
        Switch r02 = (Switch) findViewById(R.id.aospAec);
        if (!C4664a.m18637i()) {
            m5864r0((ViewGroup) findViewById(R.id.layoutAospAec), false);
        } else {
            r02.setChecked(C4665b.m18666r());
            r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.q
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                    this.f18358a.m5823D(compoundButton, z8);
                }
            });
        }
    }

    /* renamed from: Z */
    public final void m5849Z() {
        Switch r02 = (Switch) findViewById(R.id.autoAnswer);
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.t
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                this.f18359a.m5824E(compoundButton, z8);
            }
        });
        r02.setChecked(C5315b.m20802r());
    }

    /* renamed from: b0 */
    public final void m5850b0() {
        Switch r02 = (Switch) findViewById(R.id.autoHangUp);
        r02.setChecked(C5315b.m20803s());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.s
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                C5315b.m20809y(z8);
            }
        });
    }

    /* renamed from: c0 */
    public final void m5851c0() {
        Switch r02 = (Switch) findViewById(R.id.autoReject);
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.v
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                this.f18362a.m5826I(compoundButton, z8);
            }
        });
        r02.setChecked(C5315b.m20804t());
    }

    /* renamed from: d0 */
    public final void m5852d0() {
        m5861o0();
        m5849Z();
        m5851c0();
        m5850b0();
        m5853e0();
    }

    /* renamed from: e0 */
    public final void m5853e0() {
        Switch r02 = (Switch) findViewById(R.id.forceE2EE);
        r02.setChecked(C5315b.m20806v());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.n
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                C5315b.m20797B(z8);
            }
        });
    }

    /* renamed from: g0 */
    public final void m5854g0() {
        Switch r02 = (Switch) findViewById(R.id.enableLowResolution);
        r02.setChecked(C5315b.m20805u());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.r
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                C5315b.m20796A(z8);
            }
        });
    }

    /* renamed from: i0 */
    public final void m5855i0() {
        m5856j0();
        m5857k0();
    }

    /* renamed from: j0 */
    public final void m5856j0() {
        final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.pinkNoiseMaxVolumeLayout);
        final ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.pinkNoiseMinVolumeLayout);
        m5864r0(viewGroup, C1121t.m5157f());
        m5864r0(viewGroup2, C1121t.m5157f());
        Switch r22 = (Switch) findViewById(R.id.pinkNoiseSwitch);
        r22.setChecked(C1121t.m5157f());
        r22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.o
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                this.f18353a.m5829N(viewGroup, viewGroup2, compoundButton, z8);
            }
        });
    }

    /* renamed from: k0 */
    public final void m5857k0() {
        TextView textViewM5866z = m5866z(R.id.pinkNoiseMaxVolumeText);
        TextView textViewM5866z2 = m5866z(R.id.pinkNoiseMinVolumeText);
        int iM5152a = C1121t.m5152a();
        int iM5153b = C1121t.m5153b();
        textViewM5866z.setText(getString(R.string.debug_meeting_setting_pink_noise_max_volume, Integer.valueOf(iM5152a)));
        textViewM5866z2.setText(getString(R.string.debug_meeting_setting_pink_noise_min_volume, Integer.valueOf(iM5153b)));
        C5179r0.m20247b(textViewM5866z, 1);
        C5179r0.m20247b(textViewM5866z2, 1);
        SeekBar seekBar = (SeekBar) findViewById(R.id.pinkNoiseMaxVolumeSeekBar);
        seekBar.setMax(50);
        seekBar.setProgress(iM5152a + 100);
        seekBar.setOnSeekBarChangeListener(new C1278a(textViewM5866z));
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.pinkNoiseMinVolumeSeekBar);
        seekBar2.setMax(50);
        seekBar2.setProgress(iM5153b + 100);
        seekBar2.setOnSeekBarChangeListener(new C1279b(textViewM5866z2));
    }

    /* renamed from: l0 */
    public final void m5858l0() {
        m5859m0();
        m5860n0();
    }

    /* renamed from: m0 */
    public final void m5859m0() {
        final ViewGroup viewGroup = (ViewGroup) findViewById(R.id.poorConnectionAudioHintVolumeLayout);
        m5864r0(viewGroup, C1121t.m5158g());
        Switch r12 = (Switch) findViewById(R.id.poorConnectionAudioHintSwitch);
        r12.setChecked(C1121t.m5158g());
        r12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.k
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                this.f18346a.m5830O(viewGroup, compoundButton, z8);
            }
        });
    }

    /* renamed from: n0 */
    public final void m5860n0() {
        TextView textViewM5866z = m5866z(R.id.poorConnectionAudioHintVolumeText);
        SeekBar seekBar = (SeekBar) findViewById(R.id.poorConnectionAudioHintSeekBar);
        int iM5154c = C1121t.m5154c();
        textViewM5866z.setText(getString(R.string.debug_meeting_setting_poor_connection_audio_hint_volume, Integer.valueOf(iM5154c)));
        C5179r0.m20247b(textViewM5866z, 1);
        seekBar.setMax(40);
        seekBar.setProgress(iM5154c + 50);
        seekBar.setOnSeekBarChangeListener(new C1280c(textViewM5866z));
    }

    /* renamed from: o0 */
    public final void m5861o0() {
        Switch r02 = (Switch) findViewById(R.id.repeatCall);
        r02.setChecked(C5315b.m20807w());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: o2.l
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                C5315b.m20798C(z8);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_meeting_settings);
        C5314a.m20793c();
        m5862p0();
        m5855i0();
        m5858l0();
        m5863q0();
        m5846V();
        m5852d0();
    }

    /* renamed from: p0 */
    public final void m5862p0() {
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() { // from class: o2.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18360b.m5832Q(view);
            }
        });
    }

    /* renamed from: q0 */
    public final void m5863q0() {
        m5854g0();
    }

    /* renamed from: r0 */
    public final void m5864r0(ViewGroup viewGroup, boolean z8) {
        for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
            viewGroup.getChildAt(i9).setEnabled(z8);
        }
        viewGroup.setBackgroundResource(z8 ? R.color.transparent : R.color.you_color_light_gray);
    }

    /* renamed from: y */
    public final void m5865y() {
        m5866z(R.id.txtAecBuiltInInfo).setText(C5314a.m20792b());
        m5866z(R.id.txtAecBuiltInDetail).setText(C5314a.m20791a());
    }

    /* renamed from: z */
    public final TextView m5866z(int i9) {
        return (TextView) findViewById(i9);
    }
}
