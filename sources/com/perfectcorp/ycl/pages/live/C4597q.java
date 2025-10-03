package com.perfectcorp.ycl.pages.live;

import android.app.Activity;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.ycl.p040bc.model.network.Key;
import com.perfectcorp.ycl.p040bc.widgetpool.common.ObservableScrollView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p047d5.C4677a;
import p047d5.C4678b;
import p047d5.C4679c;
import p047d5.C4680d;
import p047d5.C4681e;
import p047d5.C4682f;

/* renamed from: com.perfectcorp.ycl.pages.live.q */
/* loaded from: classes2.dex */
public class C4597q {

    /* renamed from: b */
    public WeakReference<Context> f16130b;

    /* renamed from: c */
    public final View f16131c;

    /* renamed from: d */
    public final ObservableScrollView f16132d;

    /* renamed from: e */
    public final LinearLayout f16133e;

    /* renamed from: g */
    public final TextView f16135g;

    /* renamed from: j */
    public i f16138j;

    /* renamed from: k */
    public String f16139k;

    /* renamed from: m */
    public String f16141m;

    /* renamed from: a */
    public final long f16129a = 500;

    /* renamed from: f */
    public boolean f16134f = true;

    /* renamed from: h */
    public int f16136h = 0;

    /* renamed from: i */
    public boolean f16137i = false;

    /* renamed from: l */
    public HashMap<String, Long> f16140l = new HashMap<>();

    /* renamed from: n */
    public Set<String> f16142n = new HashSet();

    /* renamed from: o */
    public final View.OnClickListener f16143o = new a();

    /* renamed from: com.perfectcorp.ycl.pages.live.q$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!(view.getTag() instanceof c) || C4597q.this.f16138j == null) {
                return;
            }
            c cVar = (c) view.getTag();
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (!C4597q.this.f16140l.containsKey(cVar.f16152g) || jCurrentTimeMillis - ((Long) C4597q.this.f16140l.get(cVar.f16152g)).longValue() >= 500) {
                if (cVar.f16159n) {
                    C4597q.this.f16138j.mo13747a(cVar.f16152g);
                } else {
                    C4597q.this.f16138j.mo13748b(cVar.f16152g);
                }
                C4597q.this.f16140l.put(cVar.f16152g, Long.valueOf(jCurrentTimeMillis));
            }
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$b */
    public class b implements ObservableScrollView.InterfaceC4566a {
        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m18299d() {
            if (C4597q.this.f16135g != null) {
                C4597q.this.f16135g.setVisibility(4);
            }
        }

        @Override // com.perfectcorp.ycl.p040bc.widgetpool.common.ObservableScrollView.InterfaceC4566a
        /* renamed from: a */
        public void mo18145a(ObservableScrollView observableScrollView, int i9) {
        }

        @Override // com.perfectcorp.ycl.p040bc.widgetpool.common.ObservableScrollView.InterfaceC4566a
        /* renamed from: b */
        public void mo18146b(ObservableScrollView observableScrollView, int i9, int i10, int i11, int i12) {
            C4597q c4597q = C4597q.this;
            c4597q.f16134f = c4597q.m18297z(i10);
            if (C4597q.this.f16134f) {
                ((Activity) C4597q.this.f16130b.get()).runOnUiThread(new Runnable() { // from class: com.perfectcorp.ycl.pages.live.r
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16171b.m18299d();
                    }
                });
            }
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$c */
    public static class c {

        /* renamed from: a */
        public boolean f16146a;

        /* renamed from: b */
        public boolean f16147b;

        /* renamed from: c */
        public boolean f16148c;

        /* renamed from: d */
        public final String f16149d;

        /* renamed from: e */
        public final String f16150e;

        /* renamed from: f */
        public final HashSet<String> f16151f;

        /* renamed from: g */
        public String f16152g;

        /* renamed from: h */
        public TextView f16153h;

        /* renamed from: i */
        public TextView f16154i;

        /* renamed from: j */
        public TextView f16155j;

        /* renamed from: k */
        public TextView f16156k;

        /* renamed from: l */
        public ImageView f16157l;

        /* renamed from: m */
        public View f16158m;

        /* renamed from: n */
        public boolean f16159n;

        /* renamed from: o */
        public TextView f16160o;

        /* renamed from: p */
        public boolean f16161p;

        /* renamed from: q */
        public String f16162q;

        /* renamed from: r */
        public String f16163r;

        public /* synthetic */ c(Context context, String str, String str2, View.OnClickListener onClickListener, a aVar) {
            this(context, str, str2, onClickListener);
        }

        /* renamed from: v */
        public void m18321v(String str) {
            String strIntern = str.intern();
            if (this.f16151f.contains(strIntern)) {
                return;
            }
            this.f16151f.add(strIntern);
            m18323x();
        }

        /* renamed from: w */
        public boolean m18322w() {
            return this.f16146a;
        }

        /* renamed from: x */
        public final void m18323x() {
            int size = this.f16151f.size();
            this.f16156k.setText(String.valueOf(size));
            this.f16156k.setVisibility(size == 0 ? 4 : 0);
            boolean zContains = this.f16151f.contains(!C4509d.m18120b(this.f16162q) ? this.f16162q.intern() : Key.Init.Parameter.uuid.intern());
            this.f16159n = zContains;
            this.f16157l.setSelected(zContains);
            this.f16156k.setSelected(this.f16159n);
        }

        /* renamed from: y */
        public void m18324y(String str) {
            String strIntern = str.intern();
            if (this.f16151f.contains(strIntern)) {
                this.f16151f.remove(strIntern);
                m18323x();
            }
        }

        /* renamed from: z */
        public void m18325z(Context context) {
            this.f16153h.setVisibility(8);
            this.f16154i.setVisibility(8);
            this.f16155j.setVisibility(8);
            this.f16156k.setVisibility(8);
            this.f16157l.setVisibility(8);
            this.f16160o.setVisibility(0);
            this.f16161p = true;
            if (context.getResources().getConfiguration().orientation == 1) {
                this.f16158m.setBackgroundResource(C4678b.bc_color_white);
            } else {
                this.f16158m.setBackgroundResource(C4678b.bc_color_transparent);
                this.f16160o.setTextColor(context.getResources().getColor(C4678b.bc_color_white));
            }
        }

        public c(Context context, String str, String str2, View.OnClickListener onClickListener) {
            this.f16146a = false;
            this.f16147b = false;
            this.f16148c = false;
            this.f16151f = new HashSet<>();
            this.f16158m = LayoutInflater.from(context).inflate(C4681e.view_item_webinar_message, (ViewGroup) null);
            this.f16149d = str == null ? "" : str.intern();
            this.f16150e = str2 == null ? "" : str2.intern();
            this.f16153h = (TextView) this.f16158m.findViewById(C4680d.displayName);
            this.f16154i = (TextView) this.f16158m.findViewById(C4680d.label);
            this.f16155j = (TextView) this.f16158m.findViewById(C4680d.message);
            TextView textView = (TextView) this.f16158m.findViewById(C4680d.likeCountTextView);
            this.f16156k = textView;
            textView.setText("0");
            this.f16156k.setVisibility(4);
            ImageView imageView = (ImageView) this.f16158m.findViewById(C4680d.likeImageView);
            this.f16157l = imageView;
            imageView.setOnClickListener(onClickListener);
            this.f16157l.setTag(this);
            this.f16160o = (TextView) this.f16158m.findViewById(C4680d.commentDeleteTextView);
            this.f16159n = false;
            this.f16161p = false;
            this.f16163r = "";
            this.f16158m.setTag(this);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$d */
    public static abstract class d implements e {

        /* renamed from: a */
        public final String f16164a;

        /* renamed from: b */
        public final String f16165b;

        /* renamed from: c */
        public final String f16166c;

        /* renamed from: d */
        public final String f16167d;

        public d(String str, String str2, String str3, String str4) {
            this.f16164a = str;
            this.f16165b = str2;
            this.f16167d = str3;
            this.f16166c = str4;
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$e */
    public interface e {

        /* renamed from: com.perfectcorp.ycl.pages.live.q$e$a */
        public interface a {
            /* renamed from: a */
            View mo18327a(f fVar);

            /* renamed from: b */
            View mo18328b(g gVar);
        }

        /* renamed from: a */
        View mo18326a(a aVar);
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$f */
    public static class f extends d {
        public f(String str) {
            super(C4677a.m18710n().getResources().getString(C4682f.ycl_system_message), null, null, str);
        }

        @Override // com.perfectcorp.ycl.pages.live.C4597q.e
        /* renamed from: a */
        public View mo18326a(e.a aVar) {
            return aVar.mo18327a(this);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$g */
    public static class g extends d {

        /* renamed from: e */
        public final String f16168e;

        public g(String str, String str2, String str3, String str4, String str5) {
            super(str, str2, str3, str4);
            this.f16168e = str5;
        }

        @Override // com.perfectcorp.ycl.pages.live.C4597q.e
        /* renamed from: a */
        public View mo18326a(e.a aVar) {
            return aVar.mo18328b(this);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$h */
    public class h implements e.a {

        /* renamed from: a */
        public final WeakReference<Context> f16169a;

        public /* synthetic */ h(C4597q c4597q, Context context, a aVar) {
            this(context);
        }

        @Override // com.perfectcorp.ycl.pages.live.C4597q.e.a
        /* renamed from: a */
        public View mo18327a(f fVar) {
            return m18332f(fVar);
        }

        @Override // com.perfectcorp.ycl.pages.live.C4597q.e.a
        /* renamed from: b */
        public View mo18328b(g gVar) {
            return m18334h(gVar);
        }

        /* renamed from: c */
        public final LinearLayout m18329c(Context context) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(19);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(20, 10, 20, 10);
            linearLayout.setLayoutParams(layoutParams);
            return linearLayout;
        }

        /* renamed from: d */
        public final c m18330d(Context context, d dVar) {
            c cVar = new c(context, dVar.f16164a, dVar.f16166c, C4597q.this.f16143o, null);
            cVar.f16157l.setImageResource(C4597q.this.f16136h == 0 ? C4679c.image_selector_like : C4679c.image_selector_like_landscape);
            cVar.f16156k.setTextColor(context.getResources().getColorStateList(C4597q.this.f16136h == 0 ? C4678b.like_count_color : C4678b.like_count_color_landscape));
            Key.Init._Parameter _parameter = Key.Init.Parameter;
            int i9 = 0;
            cVar.f16146a = (_parameter != null && _parameter.uuid.equals(dVar.f16167d)) || dVar.f16165b.equals(C4597q.this.f16141m);
            cVar.f16148c = C4597q.this.f16142n.contains(dVar.f16165b);
            cVar.f16147b = !C4509d.m18120b(C4597q.this.f16139k) && dVar.f16165b.equals(C4597q.this.f16139k);
            cVar.f16162q = C4597q.this.f16141m;
            cVar.f16163r = dVar.f16165b;
            cVar.f16153h.setText(dVar.f16164a);
            TextView textView = cVar.f16154i;
            if (!cVar.f16147b && !cVar.f16148c) {
                i9 = 8;
            }
            textView.setVisibility(i9);
            if (cVar.f16147b) {
                cVar.f16154i.setText(C4682f.wbn_assistant_label_host);
                cVar.f16154i.setBackgroundColor(context.getResources().getColor(C4678b.you_color_normal_blue));
            } else if (cVar.f16148c) {
                cVar.f16154i.setText(C4682f.wbn_assistant_label_assistant);
                cVar.f16154i.setBackgroundColor(context.getResources().getColor(C4678b.ycl_live_assistant_label_background_color));
            }
            if (context.getResources().getConfiguration().orientation == 1) {
                cVar.f16153h.setTextColor(context.getResources().getColor(cVar.m18322w() ? C4678b.ycl_text_style_h : C4678b.bc_color_black));
                if (cVar.f16147b) {
                    cVar.f16158m.setBackgroundResource(C4679c.bg_message_host);
                } else if (cVar.f16148c) {
                    cVar.f16158m.setBackgroundResource(C4679c.bg_message_assistant);
                }
            } else {
                cVar.f16153h.setText(m18331e(dVar.f16164a, context.getResources().getColor(cVar.m18322w() ? C4678b.ycl_text_style_h : C4678b.ycl_live_chat_background_color)));
                cVar.f16158m.setBackgroundResource(C4679c.bg_message_normal_landcape);
            }
            return cVar;
        }

        /* renamed from: e */
        public final Spannable m18331e(CharSequence charSequence, int i9) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(new ForegroundColorSpan(i9), 0, spannableString.length(), 33);
            return spannableString;
        }

        /* renamed from: f */
        public final View m18332f(f fVar) {
            Context context = this.f16169a.get();
            LinearLayout linearLayoutM18329c = m18329c(context);
            linearLayoutM18329c.addView(m18333g(context, fVar));
            return linearLayoutM18329c;
        }

        /* renamed from: g */
        public final View m18333g(Context context, f fVar) {
            c cVarM18330d = m18330d(context, fVar);
            cVarM18330d.f16155j.append(m18331e(fVar.f16166c, context.getResources().getColor(C4678b.ycl_live_system_message_color)));
            return cVarM18330d.f16158m;
        }

        /* renamed from: h */
        public final View m18334h(g gVar) {
            Context context = this.f16169a.get();
            LinearLayout linearLayoutM18329c = m18329c(context);
            linearLayoutM18329c.addView(m18335i(context, gVar));
            return linearLayoutM18329c;
        }

        /* renamed from: i */
        public final View m18335i(Context context, g gVar) {
            c cVarM18330d = m18330d(context, gVar);
            cVarM18330d.f16152g = gVar.f16168e;
            if (context.getResources().getConfiguration().orientation == 1) {
                cVarM18330d.f16155j.append(m18331e(gVar.f16166c, context.getResources().getColor(C4678b.ycl_live_text_color)));
            } else {
                cVarM18330d.f16155j.append(m18331e(gVar.f16166c, context.getResources().getColor(C4678b.ycl_live_chat_background_color)));
            }
            return cVarM18330d.f16158m;
        }

        public h(Context context) {
            this.f16169a = new WeakReference<>(context);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.q$i */
    public interface i {
        /* renamed from: a */
        void mo13747a(String str);

        /* renamed from: b */
        void mo13748b(String str);
    }

    public C4597q(Context context, View view, i iVar) {
        this.f16130b = new WeakReference<>(context);
        this.f16131c = view;
        ObservableScrollView observableScrollView = (ObservableScrollView) view.findViewById(C4680d.chat_window);
        this.f16132d = observableScrollView;
        observableScrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.perfectcorp.ycl.pages.live.m
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
                this.f16124b.m18254A(view2, i9, i10, i11, i12, i13, i14, i15, i16);
            }
        });
        observableScrollView.setOnScrollChangeListener(new b());
        TextView textView = (TextView) view.findViewById(C4680d.message_notify);
        this.f16135g = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.perfectcorp.ycl.pages.live.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f16125b.m18255B(view2);
            }
        });
        this.f16133e = (LinearLayout) view.findViewById(C4680d.chat_container);
        this.f16138j = iVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A */
    public /* synthetic */ void m18254A(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        if (i9 == i13 && i10 == i14 && i11 == i15 && i12 == i16) {
            return;
        }
        m18278H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m18255B(View view) {
        m18278H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m18256C() {
        this.f16132d.fullScroll(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
    }

    /* renamed from: F */
    public void m18276F(String str) {
        m18296y(str, !C4509d.m18120b(this.f16141m) ? this.f16141m : Key.Init.Parameter.uuid);
    }

    /* renamed from: G */
    public void m18277G(String str) {
        if (this.f16142n.remove(str)) {
            m18290s(str, false);
        }
    }

    /* renamed from: H */
    public final void m18278H() {
        this.f16132d.post(new Runnable() { // from class: com.perfectcorp.ycl.pages.live.o
            @Override // java.lang.Runnable
            public final void run() {
                this.f16126b.m18256C();
            }
        });
    }

    /* renamed from: I */
    public void m18279I(Set<String> set) {
        this.f16142n.clear();
        this.f16142n.addAll(set);
    }

    /* renamed from: J */
    public void m18280J(boolean z8) {
        this.f16137i = z8;
    }

    /* renamed from: K */
    public void m18281K(String str) {
        this.f16139k = str;
    }

    /* renamed from: L */
    public void m18282L(String str) {
        this.f16141m = str;
    }

    /* renamed from: M */
    public void m18283M(String str) {
        m18293v(str, !C4509d.m18120b(this.f16141m) ? this.f16141m : Key.Init.Parameter.uuid);
    }

    /* renamed from: N */
    public void m18284N(List<d> list) {
        final ArrayList arrayList = new ArrayList();
        Iterator<d> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().mo18326a(new h(this, this.f16130b.get(), null)));
        }
        ((Activity) this.f16130b.get()).runOnUiThread(new Runnable() { // from class: com.perfectcorp.ycl.pages.live.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f16122b.m18257D(arrayList);
            }
        });
    }

    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public final void m18258E(View view) {
        this.f16133e.addView(view);
        if (!this.f16134f || this.f16137i) {
            m18288R();
        } else {
            m18278H();
        }
    }

    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public final void m18257D(List<View> list) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            this.f16133e.addView(list.get(i9), i9);
        }
        if (!this.f16134f || this.f16137i) {
            m18288R();
        } else {
            m18278H();
        }
    }

    /* renamed from: Q */
    public void m18287Q(d dVar) {
        final View viewMo18326a = dVar.mo18326a(new h(this, this.f16130b.get(), null));
        ((Activity) this.f16130b.get()).runOnUiThread(new Runnable() { // from class: com.perfectcorp.ycl.pages.live.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f16127b.m18258E(viewMo18326a);
            }
        });
    }

    /* renamed from: R */
    public final void m18288R() {
        Context context;
        if (this.f16135g == null || (context = this.f16130b.get()) == null || this.f16133e.getChildCount() <= 0) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) this.f16133e.getChildAt(r1.getChildCount() - 1);
        if (linearLayout.getChildCount() > 0) {
            View childAt = linearLayout.getChildAt(linearLayout.getChildCount() - 1);
            if (childAt.getTag() instanceof c) {
                c cVar = (c) childAt.getTag();
                this.f16135g.setText(cVar.f16149d + context.getString(C4682f.ycl_chat_double_colon) + cVar.f16150e);
                if (this.f16136h == 0) {
                    this.f16135g.setVisibility(0);
                }
            }
        }
    }

    /* renamed from: r */
    public void m18289r(String str) {
        this.f16142n.add(str);
        m18290s(str, true);
    }

    /* renamed from: s */
    public void m18290s(String str, boolean z8) {
        Context context = this.f16130b.get();
        if (context == null) {
            return;
        }
        for (int i9 = 0; i9 < this.f16133e.getChildCount(); i9++) {
            LinearLayout linearLayout = (LinearLayout) this.f16133e.getChildAt(i9);
            for (int i10 = 0; i10 < linearLayout.getChildCount(); i10++) {
                View childAt = linearLayout.getChildAt(i10);
                if (childAt.getTag() instanceof c) {
                    c cVar = (c) childAt.getTag();
                    if (!C4509d.m18120b(str) && str.equals(cVar.f16163r)) {
                        cVar.f16148c = z8;
                        if (!cVar.f16149d.isEmpty()) {
                            cVar.f16153h.setTextColor(context.getResources().getColor(cVar.m18322w() ? C4678b.ycl_text_style_h : this.f16136h == 0 ? C4678b.bc_color_black : C4678b.ycl_live_chat_background_color));
                            cVar.f16154i.setVisibility(cVar.f16148c ? 0 : 8);
                            if (cVar.f16148c) {
                                cVar.f16154i.setText(C4682f.wbn_assistant_label_assistant);
                                cVar.f16154i.setBackgroundColor(context.getResources().getColor(C4678b.ycl_live_assistant_label_background_color));
                            }
                            cVar.f16158m.setBackgroundResource(this.f16136h == 0 ? cVar.f16148c ? C4679c.bg_message_assistant : C4679c.bg_message_normal : C4679c.bg_message_normal_landcape);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: t */
    public void m18291t(Integer num) {
        this.f16136h = num.intValue();
        Context context = this.f16130b.get();
        if (context == null) {
            return;
        }
        for (int i9 = 0; i9 < this.f16133e.getChildCount(); i9++) {
            LinearLayout linearLayout = (LinearLayout) this.f16133e.getChildAt(i9);
            for (int i10 = 0; i10 < linearLayout.getChildCount(); i10++) {
                View childAt = linearLayout.getChildAt(i10);
                if (childAt.getTag() instanceof c) {
                    c cVar = (c) childAt.getTag();
                    if (!cVar.f16149d.isEmpty()) {
                        cVar.f16153h.setTextColor(context.getResources().getColor(cVar.m18322w() ? C4678b.ycl_text_style_h : num.intValue() == 0 ? C4678b.bc_color_black : C4678b.ycl_live_chat_background_color));
                        cVar.f16153h.setText(cVar.f16149d);
                    }
                    if (!cVar.f16150e.isEmpty()) {
                        cVar.f16155j.setTextColor(context.getResources().getColor(num.intValue() == 0 ? C4678b.ycl_live_text_color : C4678b.ycl_live_chat_background_color));
                        cVar.f16155j.setText(cVar.f16150e);
                    }
                    if (cVar.f16161p) {
                        cVar.f16158m.setBackgroundResource(num.intValue() == 0 ? C4678b.bc_color_white : C4678b.bc_color_transparent);
                        cVar.f16160o.setTextColor(context.getResources().getColor(num.intValue() == 0 ? C4678b.bc_color_black : C4678b.bc_color_white));
                        cVar.f16160o.setText(C4682f.wbn_comment_delete);
                    }
                    cVar.f16156k.setTextColor(context.getResources().getColorStateList(num.intValue() == 0 ? C4678b.like_count_color : C4678b.like_count_color_landscape));
                    cVar.f16157l.setImageResource(num.intValue() == 0 ? C4679c.image_selector_like : C4679c.image_selector_like_landscape);
                    if (num.intValue() == 1) {
                        cVar.f16158m.setBackgroundResource(C4679c.bg_message_normal_landcape);
                    } else if (cVar.f16147b) {
                        cVar.f16158m.setBackgroundResource(C4679c.bg_message_host);
                    } else if (cVar.f16148c) {
                        cVar.f16158m.setBackgroundResource(C4679c.bg_message_assistant);
                    } else {
                        cVar.f16158m.setBackgroundResource(C4679c.bg_message_normal);
                    }
                }
            }
        }
    }

    /* renamed from: u */
    public void m18292u() {
        this.f16133e.removeAllViews();
    }

    /* renamed from: v */
    public void m18293v(String str, String str2) {
        c cVarM18295x = m18295x(str);
        if (cVarM18295x != null) {
            cVarM18295x.m18324y(str2);
        }
    }

    /* renamed from: w */
    public void m18294w(String str) {
        c cVarM18295x = m18295x(str);
        Context context = this.f16130b.get();
        if (cVarM18295x == null || context == null) {
            return;
        }
        cVarM18295x.m18325z(context);
    }

    /* renamed from: x */
    public final c m18295x(String str) {
        for (int i9 = 0; i9 < this.f16133e.getChildCount(); i9++) {
            LinearLayout linearLayout = (LinearLayout) this.f16133e.getChildAt(i9);
            for (int i10 = 0; i10 < linearLayout.getChildCount(); i10++) {
                View childAt = linearLayout.getChildAt(i10);
                if ((childAt.getTag() instanceof c) && ((c) childAt.getTag()).f16152g.equals(str)) {
                    return (c) childAt.getTag();
                }
            }
        }
        return null;
    }

    /* renamed from: y */
    public void m18296y(String str, String str2) {
        c cVarM18295x = m18295x(str);
        if (cVarM18295x != null) {
            cVarM18295x.m18321v(str2);
        }
    }

    /* renamed from: z */
    public final boolean m18297z(int i9) {
        if (this.f16132d.getChildCount() > 0) {
            return i9 + this.f16132d.getHeight() >= this.f16132d.getChildAt(0).getHeight();
        }
        return true;
    }
}
