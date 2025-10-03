package com.perfectcorp.ycl.pages.live;

import android.content.Intent;
import android.net.Uri;
import com.perfectcorp.ycl.p040bc.model.Live;

/* renamed from: com.perfectcorp.ycl.pages.live.t */
/* loaded from: classes2.dex */
public final class C4600t {

    /* renamed from: i */
    public static final String f16182i = Uri.EMPTY.toString();

    /* renamed from: a */
    public final long f16183a;

    /* renamed from: b */
    public final String f16184b;

    /* renamed from: c */
    public final String f16185c;

    /* renamed from: d */
    public final String f16186d;

    /* renamed from: e */
    public final String f16187e;

    /* renamed from: f */
    public final String f16188f;

    /* renamed from: g */
    public final long f16189g;

    /* renamed from: h */
    public final int f16190h;

    public C4600t(Live live) {
        this.f16187e = live.liveId;
        this.f16186d = live.title;
        this.f16183a = live.creator.uid.longValue();
        Live.Creator creator = live.creator;
        this.f16184b = creator.displayName;
        this.f16185c = creator.avator;
        this.f16188f = "";
        this.f16189g = 0L;
        this.f16190h = 0;
    }

    /* renamed from: a */
    public static C4600t m18339a(Intent intent) {
        return new C4600t(intent);
    }

    public C4600t(Intent intent) {
        this.f16183a = intent.getLongExtra("INTENT_KEY_CREATOR_ID", 0L);
        this.f16185c = intent.getStringExtra("INTENT_KEY_CREATOR_COVER");
        this.f16184b = intent.getStringExtra("INTENT_KEY_CREATOR_NAME");
        this.f16186d = intent.getStringExtra("INTENT_KEY_TITLE");
        this.f16187e = intent.getStringExtra("INTENT_KEY_LIVE_ID");
        String stringExtra = intent.getStringExtra("INTENT_KEY_LOOK_ID");
        this.f16188f = stringExtra == null ? "" : stringExtra;
        this.f16189g = intent.getLongExtra("INTENT_KEY_BRAND_ID", 0L);
        this.f16190h = intent.getIntExtra("INTENT_KEY_ROOM_ID", 0);
    }
}
