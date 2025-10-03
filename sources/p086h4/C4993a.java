package p086h4;

import android.view.View;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.utility.C3197a;

/* renamed from: h4.a */
/* loaded from: classes.dex */
public class C4993a {

    /* renamed from: a */
    public String f17195a;

    /* renamed from: b */
    public View f17196b;

    /* renamed from: c */
    public StickerPackObj f17197c;

    /* renamed from: d */
    public C3197a f17198d;

    /* renamed from: e */
    public View.OnClickListener f17199e;

    public C4993a(String str, View view) {
        this.f17195a = str;
        this.f17196b = view;
    }

    /* renamed from: a */
    public String m19368a() {
        return this.f17195a;
    }

    /* renamed from: b */
    public View.OnClickListener m19369b() {
        return this.f17199e;
    }

    /* renamed from: c */
    public C3197a m19370c() {
        return this.f17198d;
    }

    /* renamed from: d */
    public View m19371d() {
        return this.f17196b;
    }

    /* renamed from: e */
    public StickerPackObj m19372e() {
        return this.f17197c;
    }

    /* renamed from: f */
    public boolean m19373f(C4993a c4993a) {
        return c4993a.m19368a().equals(this.f17195a);
    }

    /* renamed from: g */
    public void m19374g(View.OnClickListener onClickListener) {
        this.f17199e = onClickListener;
    }

    /* renamed from: h */
    public void m19375h(C3197a c3197a) {
        this.f17198d = c3197a;
    }

    /* renamed from: i */
    public void m19376i(StickerPackObj stickerPackObj) {
        this.f17197c = stickerPackObj;
    }

    public C4993a(long j9, View view) {
        this(String.valueOf(j9), view);
    }
}
