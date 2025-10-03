package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.pages.photoimport.view.TouchViewPager;
import com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import p116k4.C5179r0;
import p189s0.AbstractC6243a;
import p209u2.AbstractC6378o;

/* loaded from: classes.dex */
public class SelectMediaActivity extends BaseActivity {

    /* renamed from: c */
    public C1698c f8836c;

    /* renamed from: d */
    public TouchViewPager f8837d;

    /* renamed from: e */
    public View f8838e;

    /* renamed from: f */
    public int f8839f;

    /* renamed from: g */
    public View f8840g;

    /* renamed from: h */
    public TextView f8841h;

    /* renamed from: i */
    public TextView f8842i;

    /* renamed from: j */
    public List<Long> f8843j = new ArrayList();

    /* renamed from: k */
    public C2973l0 f8844k = null;

    /* renamed from: l */
    public View.OnTouchListener f8845l = new View.OnTouchListener() { // from class: com.cyberlink.you.activity.he
        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return this.f10725b.m9773y(view, motionEvent);
        }
    };

    /* renamed from: m */
    public View.OnClickListener f8846m = new ViewOnClickListenerC1696a();

    /* renamed from: n */
    public View.OnClickListener f8847n = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ie
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10755b.m9774z(view);
        }
    };

    /* renamed from: o */
    public ViewPager.InterfaceC0557j f8848o = new C1697b();

    /* renamed from: com.cyberlink.you.activity.SelectMediaActivity$a */
    public class ViewOnClickListenerC1696a implements View.OnClickListener {
        public ViewOnClickListenerC1696a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.back) {
                SelectMediaActivity.this.m9778k(4);
                return;
            }
            if (id == R.id.cancel) {
                SelectMediaActivity.this.m9778k(3);
            } else {
                if (id != R.id.menuOperation) {
                    return;
                }
                SelectMediaActivity selectMediaActivity = SelectMediaActivity.this;
                selectMediaActivity.m9778k(selectMediaActivity.f8839f);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectMediaActivity$b */
    public class C1697b implements ViewPager.InterfaceC0557j {
        public C1697b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            if (SelectMediaActivity.this.f8837d.f14275k0 != null) {
                SelectMediaActivity.this.f8837d.f14275k0.m16273r();
            }
            SelectMediaActivity.this.f8837d.setCurrentItem(i9);
            SelectMediaActivity selectMediaActivity = SelectMediaActivity.this;
            selectMediaActivity.f8844k = selectMediaActivity.f8836c.m9781c(i9);
            if (SelectMediaActivity.this.f8844k != null) {
                if (SelectMediaActivity.this.f8843j.contains(Long.valueOf(SelectMediaActivity.this.f8844k.m15144p()))) {
                    SelectMediaActivity.this.f8840g.setSelected(true);
                } else {
                    SelectMediaActivity.this.f8840g.setSelected(false);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectMediaActivity$c */
    public class C1698c extends AbstractC6243a {

        /* renamed from: a */
        public List<C2973l0> f8851a;

        /* renamed from: b */
        public Activity f8852b;

        /* renamed from: c */
        public int f8853c = -1;

        /* renamed from: d */
        public final HashSet<Long> f8854d = new HashSet<>();

        /* renamed from: com.cyberlink.you.activity.SelectMediaActivity$c$a */
        public class a extends AbstractC6378o<Bitmap, Void, Void> {

            /* renamed from: d */
            public final /* synthetic */ int f8856d;

            /* renamed from: e */
            public final /* synthetic */ C2973l0 f8857e;

            /* renamed from: f */
            public final /* synthetic */ AtomicBoolean f8858f;

            /* renamed from: g */
            public final /* synthetic */ TouchImageView f8859g;

            public a(int i9, C2973l0 c2973l0, AtomicBoolean atomicBoolean, TouchImageView touchImageView) {
                this.f8856d = i9;
                this.f8857e = c2973l0;
                this.f8858f = atomicBoolean;
                this.f8859g = touchImageView;
            }

            /* renamed from: p */
            public final boolean m9782p() {
                return (SelectMediaActivity.this.f8837d != null ? SelectMediaActivity.this.f8837d.getCurrentItem() : -1) == this.f8856d;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: q, reason: merged with bridge method [inline-methods] */
            public void m24503g(Bitmap bitmap) {
                if (m9782p()) {
                    SelectMediaActivity.this.f8838e.setVisibility(8);
                }
                this.f8858f.set(true);
                C1698c.this.f8854d.remove(Long.valueOf(this.f8857e.m15144p()));
                this.f8859g.setImageBitmap(bitmap);
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: r, reason: merged with bridge method [inline-methods] */
            public void m24504h(Void r12) {
            }

            @Override // p209u2.AbstractC6378o
            /* renamed from: s, reason: merged with bridge method [inline-methods] */
            public void m24498l(Void r32) {
                C1698c.this.f8854d.add(Long.valueOf(this.f8857e.m15144p()));
                if (m9782p()) {
                    SelectMediaActivity.this.f8838e.setVisibility(0);
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.SelectMediaActivity$c$b */
        public class b extends AbstractC6378o<Bitmap, Void, Void> {

            /* renamed from: d */
            public final /* synthetic */ AtomicBoolean f8861d;

            /* renamed from: e */
            public final /* synthetic */ TouchImageView f8862e;

            public b(AtomicBoolean atomicBoolean, TouchImageView touchImageView) {
                this.f8861d = atomicBoolean;
                this.f8862e = touchImageView;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: p, reason: merged with bridge method [inline-methods] */
            public void m24503g(Bitmap bitmap) {
                if (this.f8861d.get()) {
                    return;
                }
                this.f8862e.setImageBitmap(bitmap);
            }

            @Override // p209u2.AbstractC6378o
            /* renamed from: q, reason: merged with bridge method [inline-methods] */
            public void m24498l(Void r12) {
            }
        }

        public C1698c(Activity activity, List<C2973l0> list) {
            this.f8852b = activity;
            this.f8851a = list;
        }

        /* renamed from: c */
        public final C2973l0 m9781c(int i9) {
            return this.f8851a.get(i9);
        }

        @Override // p189s0.AbstractC6243a
        public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            return this.f8851a.size();
        }

        @Override // p189s0.AbstractC6243a
        public Object instantiateItem(ViewGroup viewGroup, int i9) {
            View viewInflate = this.f8852b.getLayoutInflater().inflate(R.layout.view_item_show_media, viewGroup, false);
            TouchImageView touchImageView = (TouchImageView) viewInflate.findViewById(R.id.imageViewPhoto);
            viewInflate.setTag(touchImageView);
            C2973l0 c2973l0 = this.f8851a.get(i9);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            MediaLoader.m7146e(this.f8852b, c2973l0, false, new a(i9, c2973l0, atomicBoolean, touchImageView));
            MediaLoader.m7146e(this.f8852b, c2973l0, true, new b(atomicBoolean, touchImageView));
            viewGroup.addView(viewInflate, 0);
            return viewInflate;
        }

        @Override // p189s0.AbstractC6243a
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // p189s0.AbstractC6243a
        public void setPrimaryItem(ViewGroup viewGroup, int i9, Object obj) {
            SelectMediaActivity.this.f8837d.f14275k0 = (TouchImageView) ((View) obj).getTag();
            if (this.f8853c == i9) {
                return;
            }
            C2973l0 c2973l0M9781c = SelectMediaActivity.this.f8836c.m9781c(i9);
            if (c2973l0M9781c != null) {
                if (this.f8854d.contains(Long.valueOf(c2973l0M9781c.m15144p()))) {
                    SelectMediaActivity.this.f8838e.setVisibility(0);
                } else {
                    SelectMediaActivity.this.f8838e.setVisibility(8);
                }
            }
            this.f8853c = i9;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ boolean m9773y(View view, MotionEvent motionEvent) {
        ImageView imageView = (ImageView) findViewById(R.id.back);
        if (motionEvent.getAction() == 0) {
            imageView.setImageResource(R.drawable.icon_gride_p);
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return false;
        }
        imageView.setImageResource(R.drawable.icon_gride);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m9774z(View view) {
        boolean z8 = !view.isSelected();
        view.setSelected(z8);
        C2973l0 c2973l0 = this.f8844k;
        if (c2973l0 != null) {
            if (!z8) {
                this.f8843j.remove(Long.valueOf(c2973l0.m15144p()));
            } else if (!this.f8843j.contains(Long.valueOf(c2973l0.m15144p()))) {
                this.f8843j.add(Long.valueOf(this.f8844k.m15144p()));
            }
        }
        m9775B();
    }

    /* renamed from: B */
    public final void m9775B() {
        int size = this.f8843j.size();
        this.f8841h.setText(new SpannableString(String.format(getResources().getQuantityString(R.plurals.photo_selected, size), Integer.valueOf(size))));
        C5179r0.m20247b(this.f8841h, 1);
        m9777D(size);
    }

    /* renamed from: C */
    public final void m9776C(List<C2973l0> list, int i9) {
        C1698c c1698c = new C1698c(this, list);
        this.f8836c = c1698c;
        this.f8837d.setAdapter(c1698c);
        this.f8837d.m3187c(this.f8848o);
        this.f8837d.setCurrentItem(i9);
    }

    /* renamed from: D */
    public final void m9777D(int i9) {
        if (i9 > 0) {
            this.f8842i.setEnabled(true);
        } else {
            this.f8842i.setEnabled(false);
        }
        int i10 = this.f8839f;
        if (i10 == 0 || i10 == 6) {
            if (i9 <= 0) {
                this.f8842i.setText(getString(R.string.chat_dialog_send_button));
                return;
            }
            this.f8842i.setText(getString(R.string.chat_dialog_send_button) + "(" + i9 + ")");
            return;
        }
        if (i10 == 1 || i10 == 5 || i10 == 7) {
            if (i9 <= 0) {
                this.f8842i.setText(getString(R.string.menu_save_to_camera_roll));
                return;
            }
            this.f8842i.setText(getString(R.string.menu_save_to_camera_roll) + "(" + i9 + ")");
            return;
        }
        if (i10 == 2) {
            if (i9 <= 0) {
                this.f8842i.setText(getString(R.string.menu_delete));
                return;
            }
            this.f8842i.setText(getString(R.string.menu_delete) + "(" + i9 + ")");
        }
    }

    /* renamed from: k */
    public final void m9778k(int i9) {
        Intent intent = new Intent();
        long[] jArr = new long[this.f8843j.size()];
        for (int i10 = 0; i10 < this.f8843j.size(); i10++) {
            jArr[i10] = this.f8843j.get(i10).longValue();
        }
        intent.putExtra("checkedMediaList", jArr);
        intent.putExtra("type", i9);
        setResult(-1, intent);
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9778k(4);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_media);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("albumId");
        long longExtra = intent.getLongExtra("mediaId", -1L);
        boolean booleanExtra = intent.getBooleanExtra("checked", false);
        for (long j9 : intent.getLongArrayExtra("checkedMediaList")) {
            this.f8843j.add(Long.valueOf(j9));
        }
        if (stringExtra == null || stringExtra.isEmpty() || longExtra == -1) {
            return;
        }
        this.f8839f = intent.getIntExtra("operationType", -1);
        TextView textView = (TextView) findViewById(R.id.menuOperation);
        this.f8842i = textView;
        textView.setOnClickListener(this.f8846m);
        int i9 = this.f8839f;
        if (i9 == 0 || i9 == 6) {
            this.f8842i.setText(getString(R.string.chat_dialog_send_button));
        } else if (i9 == 1 || i9 == 5 || i9 == 7) {
            this.f8842i.setText(getString(R.string.menu_save_to_camera_roll));
        } else if (i9 == 2) {
            this.f8842i.setText(getString(R.string.menu_delete));
            this.f8842i.setBackgroundResource(R.drawable.image_selector_common_button_red);
        }
        TextView textView2 = (TextView) findViewById(R.id.cancel);
        textView2.setOnClickListener(this.f8846m);
        textView2.setText(R.string.back_text);
        findViewById(R.id.back).setOnClickListener(this.f8846m);
        findViewById(R.id.back).setOnTouchListener(this.f8845l);
        View viewFindViewById = findViewById(R.id.selectBtn);
        this.f8840g = viewFindViewById;
        viewFindViewById.setSelected(booleanExtra);
        this.f8840g.setOnClickListener(this.f8847n);
        findViewById(R.id.toolbar).setVisibility(0);
        this.f8841h = (TextView) findViewById(R.id.numberOfSelectedItem);
        m9775B();
        this.f8837d = (TouchViewPager) findViewById(R.id.viewpager);
        this.f8838e = findViewById(R.id.loading);
        List<C2973l0> listM14727x = C2950b0.m14914m().m14727x(stringExtra);
        for (int i10 = 0; i10 < listM14727x.size(); i10++) {
            C2973l0 c2973l0 = listM14727x.get(i10);
            if (c2973l0.m15144p() == longExtra) {
                m9776C(listM14727x, i10);
                this.f8844k = c2973l0;
                return;
            }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8842i.setOnClickListener(null);
        findViewById(R.id.cancel).setOnClickListener(null);
        findViewById(R.id.back).setOnClickListener(null);
        findViewById(R.id.back).setOnTouchListener(null);
        this.f8840g.setOnClickListener(this.f8847n);
        TouchViewPager touchViewPager = this.f8837d;
        if (touchViewPager != null) {
            touchViewPager.m3176I(this.f8848o);
            this.f8837d.setAdapter(null);
            this.f8837d = null;
        }
        super.onDestroy();
        System.gc();
    }
}
