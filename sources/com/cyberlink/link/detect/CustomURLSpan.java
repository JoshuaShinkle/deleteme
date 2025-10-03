package com.cyberlink.link.detect;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupInfoActivity;
import com.cyberlink.you.p036ui.SelectServerDialog;
import com.cyberlink.you.utility.CLUtility;
import p116k4.C5170o0;

/* loaded from: classes.dex */
public class CustomURLSpan extends URLSpan {
    public static final Parcelable.Creator<CustomURLSpan> CREATOR = new C1207a();

    /* renamed from: b */
    public boolean f5841b;

    /* renamed from: com.cyberlink.link.detect.CustomURLSpan$a */
    public class C1207a implements Parcelable.Creator<CustomURLSpan> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CustomURLSpan createFromParcel(Parcel parcel) {
            return new CustomURLSpan(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CustomURLSpan[] newArray(int i9) {
            return new CustomURLSpan[i9];
        }
    }

    public /* synthetic */ CustomURLSpan(Parcel parcel, C1207a c1207a) {
        this(parcel);
    }

    /* renamed from: a */
    public static String m5291a() {
        return Globals.m7380c2() ? "https://u-demo2.cyberlink.com/gi/U/" : "https://u-api.cyberlink.com/gi/U/";
    }

    /* renamed from: b */
    public static String m5292b() {
        return Globals.m7380c2() ? "https://u-demo.cyberlink.com/meeting/" : "https://u.cyberlink.com/meeting/";
    }

    /* renamed from: c */
    public static String m5293c() {
        return Globals.m7380c2() ? "https://u-demo.cyberlink.com/live/" : "https://u.cyberlink.com/live/";
    }

    /* renamed from: d */
    public final void m5294d(View view, Uri uri) {
        Activity activityM16479Q = CLUtility.m16479Q(view);
        if (activityM16479Q == null) {
            return;
        }
        String string = uri.toString();
        if (string.startsWith(m5293c()) && !m5296f(uri)) {
            uri = Uri.parse(Globals.m7375Z0(R.string.webinars_scheme) + "://watch?liveId=" + uri.getLastPathSegment());
        } else if (string.startsWith(m5292b())) {
            uri = Uri.parse(Globals.m7375Z0(R.string.meeting_scheme) + "://watch?meetingId=" + uri.getLastPathSegment());
        } else {
            if (string.startsWith(m5291a())) {
                GroupInfoActivity.m8433T0(activityM16479Q, uri.toString());
                return;
            }
            if (string.startsWith("u://report-logs")) {
                Intent intent = new Intent();
                intent.setAction("Custom_Broadcast_Send_Log");
                activityM16479Q.sendBroadcast(intent);
                return;
            } else if (string.startsWith("u://change-region")) {
                new SelectServerDialog().m16355h(activityM16479Q);
                return;
            }
        }
        m5295e(activityM16479Q, uri);
    }

    /* renamed from: e */
    public final void m5295e(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w("CustomURLSpan", "Activity was not found for intent, " + intent.toString());
        }
    }

    /* renamed from: f */
    public final boolean m5296f(Uri uri) {
        return !C5170o0.m20169d(uri.getQuery()) && uri.getQuery().startsWith("a=");
    }

    /* renamed from: g */
    public void m5297g(boolean z8) {
        this.f5841b = z8;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View view) {
        if (this.f5841b) {
            m5294d(view, Uri.parse(getURL()));
        }
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(-16776961);
    }

    public CustomURLSpan(String str) {
        super(str);
        this.f5841b = true;
    }

    public CustomURLSpan(Parcel parcel) {
        super(parcel);
        this.f5841b = true;
    }
}
