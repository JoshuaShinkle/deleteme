package com.perfectcorp.ycl.pages.live;

import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.MimeTypes;
import java.lang.ref.WeakReference;
import java.util.Locale;
import p047d5.C4680d;
import p047d5.C4682f;

/* renamed from: com.perfectcorp.ycl.pages.live.k */
/* loaded from: classes2.dex */
public abstract class AbstractC4591k {

    /* renamed from: a */
    public WeakReference<Context> f16107a;

    /* renamed from: b */
    public final View f16108b;

    /* renamed from: c */
    public final Button f16109c;

    /* renamed from: d */
    public final Button f16110d;

    /* renamed from: e */
    public final Button f16111e;

    /* renamed from: f */
    public final InterfaceC4605y f16112f;

    /* renamed from: g */
    public boolean f16113g;

    /* renamed from: com.perfectcorp.ycl.pages.live.k$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractC4591k.this.m18252q(view);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.k$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractC4591k.this.m18249n(view);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.k$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractC4591k.this.m18251p(view);
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.k$d */
    public class d implements PopupMenu.OnMenuItemClickListener {

        /* renamed from: a */
        public final /* synthetic */ MenuItem f16117a;

        public d(MenuItem menuItem) {
            this.f16117a = menuItem;
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem != this.f16117a) {
                return false;
            }
            AbstractC4591k.this.f16113g = !menuItem.isChecked();
            return true;
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.k$e */
    public class e implements PopupMenu.OnMenuItemClickListener {

        /* renamed from: a */
        public final /* synthetic */ PopupMenu.OnMenuItemClickListener f16119a;

        /* renamed from: b */
        public final /* synthetic */ int f16120b;

        public e(PopupMenu.OnMenuItemClickListener onMenuItemClickListener, int i9) {
            this.f16119a = onMenuItemClickListener;
            this.f16120b = i9;
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            PopupMenu.OnMenuItemClickListener onMenuItemClickListener = this.f16119a;
            return (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(menuItem)) || AbstractC4591k.this.mo13771m(menuItem, this.f16120b);
        }
    }

    public AbstractC4591k(Context context, View view, InterfaceC4605y interfaceC4605y) {
        this.f16107a = new WeakReference<>(context);
        this.f16112f = interfaceC4605y;
        this.f16108b = view;
        Button button = (Button) view.findViewById(C4680d.video_controls);
        this.f16109c = button;
        button.setOnClickListener(new a());
        Button button2 = (Button) view.findViewById(C4680d.audio_controls);
        this.f16110d = button2;
        button2.setOnClickListener(new b());
        Button button3 = (Button) view.findViewById(C4680d.text_controls);
        this.f16111e = button3;
        button3.setOnClickListener(new c());
    }

    /* renamed from: e */
    public static String m18241e(Format format) {
        if (format.channelCount == -1 || format.sampleRate == -1) {
            return "";
        }
        return format.channelCount + "ch, " + format.sampleRate + "Hz";
    }

    /* renamed from: f */
    public static String m18242f(Format format) {
        int i9 = format.bitrate;
        return i9 == -1 ? "" : String.format(Locale.US, "%.2fMbit", Float.valueOf(i9 / 1000000.0f));
    }

    /* renamed from: g */
    public static String m18243g(Format format) {
        return (TextUtils.isEmpty(format.language) || C3322C.LANGUAGE_UNDETERMINED.equals(format.language)) ? "" : format.language;
    }

    /* renamed from: h */
    public static String m18244h(Format format) {
        if (format.width == -1 || format.height == -1) {
            return "";
        }
        return format.width + "x" + format.height;
    }

    /* renamed from: i */
    public static String m18245i(Format format) {
        return "";
    }

    /* renamed from: j */
    public static String m18246j(Format format) {
        String strM18247l = MimeTypes.isVideo(format.sampleMimeType) ? m18247l(m18247l(m18244h(format), m18242f(format)), m18245i(format)) : MimeTypes.isAudio(format.sampleMimeType) ? m18247l(m18247l(m18247l(m18243g(format), m18241e(format)), m18242f(format)), m18245i(format)) : m18247l(m18247l(m18243g(format), m18242f(format)), m18245i(format));
        return strM18247l.length() == 0 ? "unknown" : strM18247l;
    }

    /* renamed from: l */
    public static String m18247l(String str, String str2) {
        if (str.length() == 0) {
            return str2;
        }
        if (str2.length() == 0) {
            return str;
        }
        return str + ", " + str2;
    }

    /* renamed from: k */
    public final void m18248k(InterfaceC4605y interfaceC4605y, PopupMenu popupMenu, PopupMenu.OnMenuItemClickListener onMenuItemClickListener, int i9) {
        int iMo18171b;
        if (interfaceC4605y == null || (iMo18171b = interfaceC4605y.mo18171b(i9)) == 0) {
            return;
        }
        popupMenu.setOnMenuItemClickListener(new e(onMenuItemClickListener, i9));
        Menu menu = popupMenu.getMenu();
        menu.add(1, 1, 0, C4682f.off);
        for (int i10 = 0; i10 < iMo18171b; i10++) {
            menu.add(1, i10 + 2, 0, m18246j(interfaceC4605y.mo18170a(i9, i10)));
        }
        menu.setGroupCheckable(1, true, true);
        menu.findItem(interfaceC4605y.mo18172c(i9) + 2).setChecked(true);
    }

    /* renamed from: m */
    public abstract boolean mo13771m(MenuItem menuItem, int i9);

    /* renamed from: n */
    public final void m18249n(View view) {
        Context context = this.f16107a.get();
        if (context == null) {
            return;
        }
        PopupMenu popupMenu = new PopupMenu(context, view);
        Menu menu = popupMenu.getMenu();
        menu.add(0, 0, 0, C4682f.enable_background_audio);
        MenuItem menuItemFindItem = menu.findItem(0);
        menuItemFindItem.setCheckable(true);
        menuItemFindItem.setChecked(this.f16113g);
        m18248k(this.f16112f, popupMenu, new d(menuItemFindItem), 1);
        popupMenu.show();
    }

    /* renamed from: o */
    public void m18250o(boolean z8) {
        this.f16108b.setVisibility(z8 ? 0 : 8);
    }

    /* renamed from: p */
    public final void m18251p(View view) {
        Context context = this.f16107a.get();
        if (context == null) {
            return;
        }
        PopupMenu popupMenu = new PopupMenu(context, view);
        m18248k(this.f16112f, popupMenu, null, 3);
        popupMenu.show();
    }

    /* renamed from: q */
    public final void m18252q(View view) {
        Context context = this.f16107a.get();
        if (context == null) {
            return;
        }
        PopupMenu popupMenu = new PopupMenu(context, view);
        m18248k(this.f16112f, popupMenu, null, 2);
        popupMenu.show();
    }

    /* renamed from: r */
    public void m18253r(boolean z8, boolean z9, boolean z10) {
        this.f16109c.setVisibility(z8 ? 0 : 8);
        this.f16110d.setVisibility(z9 ? 0 : 8);
        this.f16111e.setVisibility(z10 ? 0 : 8);
    }
}
