package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.activity.MessageGeneratorFragment;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import java.util.ArrayList;
import java.util.Arrays;
import p042d0.C4619d;
import p173q2.C6127a;

/* loaded from: classes.dex */
public class SelfDestructActivity extends BaseFragmentActivity {

    /* renamed from: l */
    public static final int[] f8919l = {3, 5, 10, 20, 30, 45, 60, 120, 180, 300, 600, 1200, 1800, 3600, 7200, 10800, 14400, 18000, 36000, 43200, 86400, 172800, 259200, 345600, 432000, 518400, 604800};

    /* renamed from: c */
    public TextView f8920c = null;

    /* renamed from: d */
    public SeekBar f8921d = null;

    /* renamed from: e */
    public View f8922e = null;

    /* renamed from: f */
    public Group f8923f = null;

    /* renamed from: g */
    public C4619d f8924g = null;

    /* renamed from: h */
    public MessageGeneratorFragment f8925h = null;

    /* renamed from: i */
    public View.OnClickListener f8926i = new ViewOnClickListenerC1712a();

    /* renamed from: j */
    public View.OnClickListener f8927j = new ViewOnClickListenerC1713b();

    /* renamed from: k */
    public SeekBar.OnSeekBarChangeListener f8928k = new C1714c();

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$a */
    public class ViewOnClickListenerC1712a implements View.OnClickListener {
        public ViewOnClickListenerC1712a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SelfDestructActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$b */
    public class ViewOnClickListenerC1713b implements View.OnClickListener {
        public ViewOnClickListenerC1713b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Object objM8738o = SelfDestructActivity.this.f8925h.m8738o();
            if (objM8738o == null) {
                return;
            }
            SelfDestructActivity selfDestructActivity = SelfDestructActivity.this;
            selfDestructActivity.m9851W0(selfDestructActivity.f8925h.m8739p(), null, SelfDestructActivity.f8919l[SelfDestructActivity.this.f8921d.getProgress()], objM8738o);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$c */
    public class C1714c implements SeekBar.OnSeekBarChangeListener {
        public C1714c() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i9, boolean z8) {
            int i10 = SelfDestructActivity.f8919l[i9];
            Pair<Integer, Long> pairM16562m2 = CLUtility.m16562m2(i10);
            String quantityString = ((Integer) pairM16562m2.first).intValue() == 3 ? SelfDestructActivity.this.getResources().getQuantityString(R.plurals.self_destruct_seconds, i10) : ((Integer) pairM16562m2.first).intValue() == 2 ? SelfDestructActivity.this.getResources().getQuantityString(R.plurals.self_destruct_minutes, i10) : ((Integer) pairM16562m2.first).intValue() == 1 ? SelfDestructActivity.this.getResources().getQuantityString(R.plurals.self_destruct_hours, i10) : SelfDestructActivity.this.getResources().getQuantityString(R.plurals.self_destruct_days, i10);
            String strValueOf = String.valueOf(pairM16562m2.second);
            SpannableString spannableString = new SpannableString(String.format(quantityString, pairM16562m2.second));
            spannableString.setSpan(new RelativeSizeSpan(1.4f), 0, strValueOf.length(), 33);
            SelfDestructActivity.this.f8920c.setText(spannableString);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$d */
    public class C1715d extends GestureDetector.SimpleOnGestureListener {
        public C1715d() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16602w2(SelfDestructActivity.this.m9848T0(), false);
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$e */
    public class ViewOnTouchListenerC1716e implements View.OnTouchListener {
        public ViewOnTouchListenerC1716e() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (SelfDestructActivity.this.f8924g == null) {
                return false;
            }
            return SelfDestructActivity.this.f8924g.m18406a(motionEvent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$f */
    public class ViewOnLayoutChangeListenerC1717f implements View.OnLayoutChangeListener {

        /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$f$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ((ScrollView) SelfDestructActivity.this.findViewById(R.id.scrollView)).fullScroll(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
            }
        }

        public ViewOnLayoutChangeListenerC1717f() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
            if (i9 == i13 && i10 == i14 && i11 == i15 && i12 == i16) {
                return;
            }
            view.post(new a());
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$g */
    public class C1718g implements MessageGeneratorFragment.InterfaceC1555g {
        public C1718g() {
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: a */
        public void mo8751a(CharSequence charSequence) {
            SelfDestructActivity.this.f8922e.setVisibility(0);
            if (charSequence.toString().isEmpty()) {
                SelfDestructActivity.this.f8922e.setEnabled(false);
            } else {
                SelfDestructActivity.this.f8922e.setEnabled(true);
            }
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: b */
        public void mo8752b(String str) {
            SelfDestructActivity.this.f8922e.setVisibility(8);
            if (str == null || str.isEmpty()) {
                return;
            }
            SelfDestructActivity.this.m9851W0(MessageGeneratorFragment.InputType.Voice, null, SelfDestructActivity.f8919l[SelfDestructActivity.this.f8921d.getProgress()], str);
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: c */
        public void mo8753c(StickerObj stickerObj) {
            SelfDestructActivity.this.f8922e.setVisibility(0);
            if (stickerObj != null) {
                SelfDestructActivity.this.f8922e.setEnabled(true);
            } else {
                SelfDestructActivity.this.f8922e.setEnabled(false);
            }
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: d */
        public void mo8754d(MessageGeneratorFragment.PhotoType photoType, ArrayList<ImageItem> arrayList) {
            if (photoType == null || arrayList == null) {
                SelfDestructActivity.this.f8922e.setVisibility(8);
            } else {
                SelfDestructActivity.this.m9851W0(MessageGeneratorFragment.InputType.Photo, photoType, SelfDestructActivity.f8919l[SelfDestructActivity.this.f8921d.getProgress()], arrayList);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$h */
    public class DialogInterfaceOnClickListenerC1719h implements DialogInterface.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ MessageGeneratorFragment.InputType f8937b;

        /* renamed from: c */
        public final /* synthetic */ Object f8938c;

        /* renamed from: d */
        public final /* synthetic */ MessageGeneratorFragment.PhotoType f8939d;

        public DialogInterfaceOnClickListenerC1719h(MessageGeneratorFragment.InputType inputType, Object obj, MessageGeneratorFragment.PhotoType photoType) {
            this.f8937b = inputType;
            this.f8938c = obj;
            this.f8939d = photoType;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            if (this.f8937b.equals(MessageGeneratorFragment.InputType.Text)) {
                Intent intent = new Intent();
                intent.putExtra("type", this.f8937b.toString());
                intent.putExtra("time", SelfDestructActivity.f8919l[SelfDestructActivity.this.f8921d.getProgress()]);
                intent.putExtra("message", (String) this.f8938c);
                SelfDestructActivity.this.setResult(-1, intent);
                SelfDestructActivity.this.finish();
            } else {
                MessageGeneratorFragment.InputType inputType = this.f8937b;
                MessageGeneratorFragment.InputType inputType2 = MessageGeneratorFragment.InputType.Photo;
                if (inputType.equals(inputType2)) {
                    ArrayList arrayList = (ArrayList) this.f8938c;
                    if (Arrays.asList(MessageGeneratorFragment.PhotoType.Camera, MessageGeneratorFragment.PhotoType.Gallery).contains(this.f8939d)) {
                        Intent intent2 = new Intent();
                        intent2.putExtra("type", inputType2.toString());
                        intent2.putExtra("subtype", this.f8939d.toString());
                        intent2.putExtra("time", SelfDestructActivity.f8919l[SelfDestructActivity.this.f8921d.getProgress()]);
                        intent2.putExtra("photo", arrayList);
                        SelfDestructActivity.this.setResult(-1, intent2);
                        SelfDestructActivity.this.finish();
                    }
                } else if (this.f8937b.equals(MessageGeneratorFragment.InputType.Sticker)) {
                    Intent intent3 = new Intent();
                    intent3.putExtra("type", this.f8937b.toString());
                    intent3.putExtra("time", SelfDestructActivity.f8919l[SelfDestructActivity.this.f8921d.getProgress()]);
                    intent3.putExtra("sticker", (StickerObj) this.f8938c);
                    SelfDestructActivity.this.setResult(-1, intent3);
                    SelfDestructActivity.this.finish();
                } else if (this.f8937b.equals(MessageGeneratorFragment.InputType.Voice)) {
                    Intent intent4 = new Intent();
                    intent4.putExtra("type", this.f8937b.toString());
                    intent4.putExtra("time", SelfDestructActivity.f8919l[SelfDestructActivity.this.f8921d.getProgress()]);
                    intent4.putExtra("voice", (String) this.f8938c);
                    SelfDestructActivity.this.setResult(-1, intent4);
                    SelfDestructActivity.this.finish();
                }
            }
            SharedPreferences.Editor editorEdit = SelfDestructActivity.this.getSharedPreferences("U", 0).edit();
            SelfDestructActivity selfDestructActivity = SelfDestructActivity.this;
            editorEdit.putInt("last_time_self_destruct", selfDestructActivity.m9850V0(selfDestructActivity.f8921d.getProgress())).apply();
            SelfDestructActivity.this.f8925h.m8741s();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelfDestructActivity$i */
    public class DialogInterfaceOnClickListenerC1720i implements DialogInterface.OnClickListener {
        public DialogInterfaceOnClickListenerC1720i() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            SelfDestructActivity.this.f8925h.m8741s();
        }
    }

    /* renamed from: T0 */
    public final Activity m9848T0() {
        return this;
    }

    /* renamed from: U0 */
    public final int m9849U0(int i9) {
        int i10 = 0;
        while (true) {
            int[] iArr = f8919l;
            if (i10 >= iArr.length) {
                return -1;
            }
            if (iArr[i10] == i9) {
                return i10;
            }
            i10++;
        }
    }

    /* renamed from: V0 */
    public final int m9850V0(int i9) {
        return f8919l[i9];
    }

    /* renamed from: W0 */
    public final void m9851W0(MessageGeneratorFragment.InputType inputType, MessageGeneratorFragment.PhotoType photoType, int i9, Object obj) {
        Pair<Integer, Long> pairM16562m2 = CLUtility.m16562m2(i9);
        int iIntValue = ((Long) pairM16562m2.second).intValue();
        String quantityString = ((Integer) pairM16562m2.first).intValue() == 3 ? getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_s, iIntValue) : ((Integer) pairM16562m2.first).intValue() == 2 ? getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_m, iIntValue) : ((Integer) pairM16562m2.first).intValue() == 1 ? getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_h, iIntValue) : getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_d, iIntValue);
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.self_destruct));
        builderM16382a.setMessage(String.format(quantityString, Integer.valueOf(iIntValue)));
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterfaceOnClickListenerC1719h(inputType, obj, photoType));
        builderM16382a.setNegativeButton(getString(R.string.cancel_text), new DialogInterfaceOnClickListenerC1720i());
        builderM16382a.show();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_self_destruct);
        this.f8924g = new C4619d(this, new C1715d());
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setOnTouchListener(new ViewOnTouchListenerC1716e());
        scrollView.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC1717f());
        findViewById(R.id.back).setOnClickListener(this.f8926i);
        View viewFindViewById = findViewById(R.id.send);
        this.f8922e = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f8927j);
        TextView textView = (TextView) findViewById(R.id.guideText);
        ImageView imageView = (ImageView) findViewById(R.id.avatar);
        Intent intent = getIntent();
        String stringExtra = "";
        if (intent != null) {
            Group group = (Group) intent.getParcelableExtra("Group");
            this.f8923f = group;
            if (group != null) {
                textView.setText(getString(R.string.self_destruct_guide, group.f13717d));
                C6127a.m23470k(this, imageView, this.f8923f);
            } else {
                textView.setText(getString(R.string.self_destruct_guide, ""));
                imageView.setImageResource(R.drawable.pic_default_group);
            }
            stringExtra = intent.getStringExtra("chatText");
        } else {
            textView.setText(getString(R.string.self_destruct_guide, ""));
            imageView.setImageResource(R.drawable.pic_default_group);
        }
        this.f8920c = (TextView) findViewById(R.id.time);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.f8921d = seekBar;
        seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress));
        this.f8921d.setOnSeekBarChangeListener(this.f8928k);
        this.f8921d.setMax(f8919l.length - 1);
        int iM9849U0 = m9849U0(getSharedPreferences("U", 0).getInt("last_time_self_destruct", 60));
        if (iM9849U0 < 0) {
            this.f8921d.setProgress(6);
        } else {
            this.f8921d.setProgress(iM9849U0);
        }
        Fragment fragmentMo1848e = getSupportFragmentManager().mo1848e("SD_MSG_FRAG");
        if (fragmentMo1848e != null) {
            this.f8925h = (MessageGeneratorFragment) fragmentMo1848e;
        } else {
            this.f8925h = new MessageGeneratorFragment();
            getSupportFragmentManager().mo1844a().m1981c(R.id.messageFragmentContainer, this.f8925h, "SD_MSG_FRAG").mo1794h();
        }
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("isSelfDestruct", true);
            this.f8925h.setArguments(bundle2);
        } else {
            this.f8921d.setProgress(bundle.getInt("progress"));
        }
        this.f8925h.m8744v(new C1718g());
        this.f8925h.m8742t(stringExtra);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f8924g = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("progress", this.f8921d.getProgress());
        super.onSaveInstanceState(bundle);
    }
}
