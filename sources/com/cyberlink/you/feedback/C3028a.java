package com.cyberlink.you.feedback;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* renamed from: com.cyberlink.you.feedback.a */
/* loaded from: classes.dex */
public class C3028a {

    /* renamed from: a */
    public final Context f13455a;

    /* renamed from: b */
    public View f13456b;

    /* renamed from: c */
    public boolean f13457c;

    /* renamed from: d */
    public boolean f13458d = false;

    /* renamed from: e */
    public String f13459e = null;

    /* renamed from: f */
    public Uri f13460f = null;

    /* renamed from: g */
    public View f13461g = null;

    /* renamed from: h */
    public View f13462h = null;

    /* renamed from: i */
    public TextView f13463i = null;

    /* renamed from: j */
    public View f13464j = null;

    /* renamed from: k */
    public TextView f13465k = null;

    /* renamed from: l */
    public UICImageView f13466l = null;

    /* renamed from: m */
    public View f13467m = null;

    /* renamed from: n */
    public View.OnClickListener f13468n = new a();

    /* renamed from: o */
    public View.OnClickListener f13469o = new b();

    /* renamed from: com.cyberlink.you.feedback.a$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C3028a c3028a = C3028a.this;
            c3028a.f13459e = C3028a.m15484g(c3028a.f13455a);
        }
    }

    /* renamed from: com.cyberlink.you.feedback.a$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C3028a.this.f13460f = null;
            C3028a.this.m15489k(true);
        }
    }

    /* renamed from: com.cyberlink.you.feedback.a$c */
    public static class c {

        /* renamed from: a */
        public long f13472a = -1;

        /* renamed from: b */
        public String f13473b = null;

        /* renamed from: c */
        public Integer f13474c = null;

        /* renamed from: d */
        public Integer f13475d = null;

        /* renamed from: e */
        public Integer f13476e = null;

        /* renamed from: f */
        public String f13477f = null;
    }

    public C3028a(Activity activity, boolean z8) {
        this.f13457c = false;
        this.f13455a = activity.getApplicationContext();
        this.f13457c = z8;
    }

    /* renamed from: g */
    public static String m15484g(Context context) {
        if (context == null) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + "U_" + new SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(new Date()) + ".jpg";
    }

    /* renamed from: f */
    public final Uri m15485f(Uri uri) {
        if (uri == null || !uri.getScheme().equals("file")) {
            return uri;
        }
        return Uri.parse("file://" + uri.getPath());
    }

    /* renamed from: h */
    public Uri m15486h() {
        return this.f13460f;
    }

    /* renamed from: i */
    public View m15487i(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.bc_widget_feedback_image, viewGroup, false);
        this.f13461g = viewInflate.findViewById(R.id.write_post_add_photo_layout);
        UICImageView uICImageView = (UICImageView) viewInflate.findViewById(R.id.image_container);
        this.f13466l = uICImageView;
        uICImageView.setVisibility(8);
        View viewFindViewById = viewInflate.findViewById(R.id.write_post_add_photo);
        this.f13462h = viewFindViewById;
        viewFindViewById.setVisibility(0);
        this.f13462h.setOnClickListener(this.f13468n);
        TextView textView = (TextView) viewInflate.findViewById(R.id.write_post_add_photo_required);
        this.f13463i = textView;
        if (this.f13458d) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        View viewFindViewById2 = viewInflate.findViewById(R.id.delete_image_btn);
        this.f13464j = viewFindViewById2;
        viewFindViewById2.setVisibility(8);
        this.f13464j.setOnClickListener(this.f13469o);
        this.f13467m = viewInflate.findViewById(R.id.write_post_separator);
        this.f13465k = (TextView) viewInflate.findViewById(R.id.write_post_text);
        m15489k(false);
        this.f13456b = viewInflate;
        return viewInflate;
    }

    /* renamed from: j */
    public void m15488j(Uri uri, boolean z8) {
        if (uri == null) {
            return;
        }
        this.f13460f = m15485f(uri);
        m15489k(false);
    }

    /* renamed from: k */
    public final void m15489k(boolean z8) {
        Uri uri = this.f13460f;
        if (uri == null) {
            if (z8) {
                this.f13461g.setVisibility(8);
                return;
            }
            this.f13466l.setVisibility(8);
            this.f13464j.setVisibility(8);
            this.f13465k.setVisibility(8);
            this.f13465k.setText("");
            this.f13467m.setVisibility(8);
            this.f13462h.setVisibility(0);
            return;
        }
        this.f13466l.setImageURI(uri);
        this.f13466l.setVisibility(0);
        this.f13464j.setVisibility(0);
        this.f13465k.setVisibility(8);
        this.f13462h.setVisibility(8);
        if (this.f13457c) {
            this.f13464j.setVisibility(8);
            this.f13466l.setEnabled(false);
            if (this.f13465k.length() <= 0) {
                this.f13465k.setVisibility(8);
            } else {
                this.f13465k.setEnabled(false);
                this.f13465k.setBackgroundResource(0);
            }
        }
    }
}
