package com.cyberlink.you.pages.photoimport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import androidx.viewpager.widget.ViewPager;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.PresetEditViewActivity;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.TouchImageActivity;
import com.cyberlink.you.pages.photoimport.view.TouchViewPager;
import com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView;
import com.cyberlink.you.utility.CLUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import p025c4.C0724b;
import p116k4.C5187v0;
import p173q2.C6136j;
import p189s0.AbstractC6243a;

/* loaded from: classes.dex */
public class TouchImageActivity extends BaseFragmentActivity {

    /* renamed from: o */
    public ArrayList<ImageItem> f14232o;

    /* renamed from: r */
    public TouchViewPager f14235r;

    /* renamed from: s */
    public C3103c f14236s;

    /* renamed from: c */
    public ImageButton f14220c = null;

    /* renamed from: d */
    public Button f14221d = null;

    /* renamed from: e */
    public CheckBox f14222e = null;

    /* renamed from: f */
    public int f14223f = -1;

    /* renamed from: g */
    public boolean f14224g = false;

    /* renamed from: h */
    public boolean f14225h = false;

    /* renamed from: i */
    public boolean f14226i = false;

    /* renamed from: j */
    public boolean f14227j = false;

    /* renamed from: k */
    public boolean f14228k = false;

    /* renamed from: l */
    public int f14229l = -1;

    /* renamed from: m */
    public boolean f14230m = false;

    /* renamed from: n */
    public boolean f14231n = false;

    /* renamed from: p */
    public ImageItem f14233p = null;

    /* renamed from: q */
    public int f14234q = -1;

    /* renamed from: t */
    public boolean f14237t = false;

    /* renamed from: u */
    public boolean f14238u = false;

    /* renamed from: v */
    public CompoundButton.OnCheckedChangeListener f14239v = new CompoundButton.OnCheckedChangeListener() { // from class: c4.m
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
            this.f3443a.m16201e1(compoundButton, z8);
        }
    };

    /* renamed from: w */
    public View.OnClickListener f14240w = new View.OnClickListener() { // from class: c4.n
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) throws FileNotFoundException {
            this.f3444b.m16202f1(view);
        }
    };

    /* renamed from: x */
    public View.OnClickListener f14241x = new ViewOnClickListenerC3101a();

    /* renamed from: y */
    public ViewPager.InterfaceC0557j f14242y = new C3102b();

    /* renamed from: com.cyberlink.you.pages.photoimport.TouchImageActivity$a */
    public class ViewOnClickListenerC3101a implements View.OnClickListener {
        public ViewOnClickListenerC3101a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iM7383g1 = Globals.m7383g1();
            if (!TouchImageActivity.this.f14237t && iM7383g1 == 0) {
                TouchImageActivity touchImageActivity = TouchImageActivity.this;
                touchImageActivity.m16207h1(touchImageActivity.f14234q);
                return;
            }
            if (TouchImageActivity.this.f14237t || iM7383g1 != 1 || TouchImageActivity.this.f14230m) {
                Intent intent = new Intent();
                C0724b.m3557c().m3560d(TouchImageActivity.this.f14232o);
                intent.putExtra("start_import", true);
                TouchImageActivity.this.setResult(-1, intent);
                TouchImageActivity.this.finish();
                return;
            }
            for (int i9 = 0; i9 < TouchImageActivity.this.f14232o.size(); i9++) {
                if (((ImageItem) TouchImageActivity.this.f14232o.get(i9)).m16132e()) {
                    TouchImageActivity.this.m16207h1(i9);
                    return;
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.TouchImageActivity$b */
    public class C3102b implements ViewPager.InterfaceC0557j {
        public C3102b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            TouchImageActivity.this.f14234q = i9;
            TouchImageActivity touchImageActivity = TouchImageActivity.this;
            touchImageActivity.f14233p = touchImageActivity.f14236s.m16211b(i9);
            TouchImageActivity.this.f14222e.setChecked(TouchImageActivity.this.f14233p.m16132e());
            TouchImageActivity.this.m16209j1();
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.TouchImageActivity$c */
    public class C3103c extends AbstractC6243a {

        /* renamed from: a */
        public List<ImageItem> f14245a;

        /* renamed from: b */
        public Activity f14246b;

        public C3103c(Activity activity, List<ImageItem> list) {
            this.f14246b = activity;
            this.f14245a = list;
        }

        /* renamed from: a */
        public void m16210a() {
            this.f14245a = null;
            notifyDataSetChanged();
        }

        /* renamed from: b */
        public ImageItem m16211b(int i9) {
            return this.f14245a.get(i9);
        }

        @Override // p189s0.AbstractC6243a
        public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            List<ImageItem> list = this.f14245a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // p189s0.AbstractC6243a
        public Object instantiateItem(ViewGroup viewGroup, int i9) {
            View viewInflate = this.f14246b.getLayoutInflater().inflate(R.layout.view_item_show_media, viewGroup, false);
            TouchImageView touchImageView = (TouchImageView) viewInflate.findViewById(R.id.imageViewPhoto);
            viewInflate.setTag(touchImageView);
            List<ImageItem> list = this.f14245a;
            if (list != null) {
                Uri uri = Uri.parse(list.get(i9).m16136i());
                if (uri != null) {
                    C6136j.m23594n(TouchImageActivity.this, touchImageView, uri);
                } else {
                    C6136j.m23599s(TouchImageActivity.this, touchImageView, this.f14245a.get(i9).m16135h());
                }
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
            TouchImageActivity.this.f14235r.f14275k0 = (TouchImageView) ((View) obj).getTag();
        }
    }

    /* renamed from: d1 */
    public static /* synthetic */ void m16200d1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e1 */
    public /* synthetic */ void m16201e1(CompoundButton compoundButton, boolean z8) {
        ImageItem imageItem = this.f14233p;
        if (imageItem != null) {
            imageItem.m16151x(z8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f1 */
    public /* synthetic */ void m16202f1(View view) throws FileNotFoundException {
        m16205b1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g1 */
    public /* synthetic */ void m16203g1(View view) {
        m16204a1();
    }

    /* renamed from: a1 */
    public void m16204a1() {
        Intent intent = new Intent();
        C0724b.m3557c().m3560d(this.f14232o);
        intent.putExtra("start_import", false);
        setResult(-1, intent);
        finish();
        C3103c c3103c = this.f14236s;
        if (c3103c != null) {
            c3103c.m16210a();
        }
    }

    /* renamed from: b1 */
    public final void m16205b1() throws FileNotFoundException {
        ImageItem imageItem = this.f14233p;
        if (imageItem == null) {
            return;
        }
        try {
            Pair<Integer, Integer> pairM16506Y0 = CLUtility.m16506Y0(imageItem.m16144q(), CLUtility.m16510Z1(this.f14233p.m16145r()), false);
            int iIntValue = ((Integer) pairM16506Y0.first).intValue();
            int iIntValue2 = ((Integer) pairM16506Y0.second).intValue();
            if (iIntValue < 1 || iIntValue2 < 1) {
                m16208i1();
                this.f14222e.setChecked(false);
                this.f14232o.get(this.f14234q).m16151x(false);
                return;
            }
            if (this.f14229l != -1 && this.f14222e.isChecked() && Globals.m7383g1() >= this.f14229l) {
                C5187v0.m20267c(R.string.reach_photo_limit);
                this.f14222e.setChecked(false);
                this.f14232o.get(this.f14234q).m16151x(false);
                return;
            }
            if (!this.f14228k || Globals.m7383g1() < 1 || !this.f14222e.isChecked()) {
                this.f14232o.get(this.f14234q).m16151x(this.f14222e.isChecked());
                if (this.f14222e.isChecked()) {
                    Globals.m7384g4(Globals.m7383g1() + 1);
                } else {
                    Globals.m7384g4(Globals.m7383g1() - 1);
                }
                m16209j1();
                return;
            }
            this.f14222e.setChecked(false);
            this.f14232o.get(this.f14234q).m16151x(false);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
            builderM16382a.setMessage(getString(R.string.bulletin_topic_post_single_select_warnning_message));
            builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: c4.o
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    TouchImageActivity.m16200d1(dialogInterface, i9);
                }
            });
            builderM16382a.create().show();
        } catch (OutOfMemoryError e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: c1 */
    public final boolean m16206c1() {
        return !this.f14224g && Globals.m7383g1() == 0;
    }

    /* renamed from: h1 */
    public final void m16207h1(int i9) {
        Intent intent = new Intent(getBaseContext(), (Class<?>) PresetEditViewActivity.class);
        intent.putExtra("position", i9);
        intent.putExtra("hideVoice4ImportPhoto", this.f14226i);
        intent.putExtra("hideText4ImportPhoto", this.f14227j);
        intent.putExtra("enableMeetingShareDoc", this.f14238u);
        startActivityForResult(intent, 0);
    }

    /* renamed from: i1 */
    public final void m16208i1() {
        C5187v0.m20267c(R.string.error_load_photo_fail);
    }

    /* renamed from: j1 */
    public final void m16209j1() {
        int iM7383g1 = Globals.m7383g1();
        if (this.f14230m) {
            if (iM7383g1 == 0) {
                this.f14221d.setEnabled(false);
                this.f14221d.setText(R.string.select_album_title);
                return;
            }
            this.f14221d.setEnabled(true);
            this.f14221d.setText(getString(R.string.add_btn) + " (" + iM7383g1 + ")");
            return;
        }
        if (!this.f14237t && m16206c1()) {
            this.f14221d.setEnabled(true);
            this.f14221d.setText(R.string.registration_phone_registration_next_btn);
            return;
        }
        if (iM7383g1 == 0) {
            this.f14221d.setEnabled(false);
            this.f14221d.setText(R.string.select_album_title);
            return;
        }
        if (iM7383g1 == 1) {
            this.f14221d.setEnabled(true);
            if (!this.f14237t) {
                this.f14221d.setText(R.string.registration_phone_registration_next_btn);
                return;
            }
            this.f14221d.setText(getString(R.string.send_btn) + " (1)");
            return;
        }
        this.f14221d.setEnabled(true);
        if (this.f14231n) {
            this.f14221d.setText(getString(R.string.add_btn) + " (" + iM7383g1 + ")");
            return;
        }
        this.f14221d.setText(getString(R.string.send_btn) + " (" + iM7383g1 + ")");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        if (i9 == 0) {
            if (i10 == -1) {
                if (Globals.m7383g1() == 0) {
                    this.f14233p.m16151x(true);
                }
                String string = intent.getExtras().getString("image");
                String string2 = intent.getExtras().getString("imageUri");
                this.f14233p.m16120A(string);
                this.f14233p.m16121B(string2);
                Intent intent2 = new Intent();
                intent2.putExtra("position", this.f14234q);
                intent2.putExtra("start_import", true);
                String string3 = intent.getExtras().getString(FirebaseAnalytics.Param.CONTENT);
                if (string3 != null) {
                    this.f14233p.m16153z(string3);
                    intent2.putExtra("update_notes", true);
                }
                String string4 = intent.getExtras().getString("audioPath");
                String string5 = intent.getExtras().getString("audioDuration");
                if (string4 != null && string5 != null) {
                    this.f14233p.m16150w(string4);
                    this.f14233p.m16149v(string5);
                    intent2.putExtra("update_notes", true);
                }
                setResult(-1, intent2);
                finish();
                C3103c c3103c = this.f14236s;
                if (c3103c != null) {
                    c3103c.m16210a();
                }
            } else if (i10 == 0 && this.f14225h) {
                finish();
            }
        }
        super.onActivityResult(i9, i10, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        m16204a1();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Bundle extras = getIntent().getExtras();
        int i9 = extras.getInt("position");
        this.f14223f = i9;
        this.f14234q = i9;
        this.f14224g = extras.getBoolean("isSelfDestruct", false);
        this.f14226i = extras.getBoolean("hideVoice4ImportPhoto", false);
        this.f14227j = extras.getBoolean("hideText4ImportPhoto", false);
        this.f14228k = extras.getBoolean("singleSelect4ImportPhoto", false);
        this.f14230m = extras.getBoolean("isImportedToAlbums", false);
        this.f14231n = extras.getBoolean("isCallFromForumsOrPolls", false);
        this.f14237t = extras.getBoolean("isEnableE2EE", false);
        this.f14232o = C0724b.m3557c().m3559b();
        this.f14229l = extras.getInt("selectLimitCount", -1);
        setContentView(R.layout.touch_image_activity);
        ImageButton imageButton = (ImageButton) findViewById(R.id.back);
        this.f14220c = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: c4.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3442b.m16203g1(view);
            }
        });
        Button button = (Button) findViewById(R.id.btnBottomDone);
        this.f14221d = button;
        button.setOnClickListener(this.f14241x);
        CheckBox checkBox = (CheckBox) findViewById(R.id.itemCheckBox);
        this.f14222e = checkBox;
        checkBox.setOnCheckedChangeListener(this.f14239v);
        this.f14222e.setOnClickListener(this.f14240w);
        if (this.f14232o != null) {
            Log.d("TouchImageActivity", "[mImageItemList] mImageItemList.size = " + this.f14232o.size());
            if (this.f14223f < this.f14232o.size()) {
                TouchViewPager touchViewPager = (TouchViewPager) findViewById(R.id.viewpager);
                this.f14235r = touchViewPager;
                touchViewPager.setOnPageChangeListener(this.f14242y);
                C3103c c3103c = new C3103c(this, this.f14232o);
                this.f14236s = c3103c;
                this.f14235r.setAdapter(c3103c);
                this.f14233p = this.f14232o.get(this.f14223f);
                this.f14235r.setCurrentItem(this.f14223f);
                if (this.f14223f == 0) {
                    this.f14242y.onPageSelected(0);
                }
            } else {
                Log.d("TouchImageActivity", "[OnCreate] The mPosition(" + this.f14223f + ") is larger than the size of mImageItemList.");
                C5187v0.m20267c(R.string.no_local_photos);
            }
        }
        m16209j1();
        this.f14238u = extras.getBoolean("enableMeetingShareDoc", false);
        if (extras.getBoolean("isEnterColorPresetPage", false)) {
            this.f14225h = true;
            m16207h1(this.f14234q);
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ImageButton imageButton = this.f14220c;
        if (imageButton != null) {
            imageButton.setOnClickListener(null);
        }
        Button button = this.f14221d;
        if (button != null) {
            button.setOnClickListener(null);
        }
        CheckBox checkBox = this.f14222e;
        if (checkBox != null) {
            checkBox.setOnCheckedChangeListener(null);
        }
        this.f14220c = null;
        this.f14221d = null;
        this.f14222e = null;
        this.f14223f = -1;
        System.gc();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }
}
