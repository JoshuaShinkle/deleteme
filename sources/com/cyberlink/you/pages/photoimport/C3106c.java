package com.cyberlink.you.pages.photoimport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.C3105b;
import com.cyberlink.you.pages.photoimport.C3106c;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import p116k4.C5187v0;
import p173q2.C6136j;

/* renamed from: com.cyberlink.you.pages.photoimport.c */
/* loaded from: classes.dex */
public class C3106c extends ArrayAdapter<ImageItem> {

    /* renamed from: b */
    public Context f14265b;

    /* renamed from: c */
    public GridView f14266c;

    /* renamed from: d */
    public int f14267d;

    /* renamed from: e */
    public ArrayList<ImageItem> f14268e;

    /* renamed from: f */
    public boolean f14269f;

    /* renamed from: g */
    public int f14270g;

    /* renamed from: h */
    public C3105b.b f14271h;

    /* renamed from: com.cyberlink.you.pages.photoimport.c$b */
    public static class b {

        /* renamed from: a */
        public CheckBox f14272a;

        /* renamed from: b */
        public ImageView f14273b;

        /* renamed from: c */
        public View f14274c;

        public b() {
        }
    }

    public C3106c(Context context, int i9, ArrayList<ImageItem> arrayList, GridView gridView) {
        super(context, i9, arrayList);
        this.f14269f = false;
        this.f14270g = -1;
        this.f14265b = context;
        this.f14267d = i9;
        this.f14268e = arrayList;
        this.f14266c = gridView;
    }

    /* renamed from: d */
    public static /* synthetic */ void m16241d(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public /* synthetic */ void m16242e(int i9, View view) {
        if (m16244f(i9)) {
            m16248j();
            m16249k(((Integer) view.getTag()).intValue(), false);
            return;
        }
        boolean zM16132e = this.f14268e.get(i9).m16132e();
        if (this.f14270g != -1 && !zM16132e && Globals.m7383g1() >= this.f14270g) {
            C5187v0.m20267c(R.string.reach_photo_limit);
            m16249k(((Integer) view.getTag()).intValue(), false);
            return;
        }
        if (this.f14269f && Globals.m7383g1() >= 1 && !zM16132e) {
            ((CheckBox) view).setChecked(false);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this.f14265b);
            builderM16382a.setMessage(this.f14265b.getString(R.string.bulletin_topic_post_single_select_warnning_message));
            builderM16382a.setPositiveButton(this.f14265b.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: c4.j
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    C3106c.m16241d(dialogInterface, i10);
                }
            });
            builderM16382a.create().show();
            return;
        }
        boolean z8 = !zM16132e;
        m16252n(z8);
        m16250l(((Integer) view.getTag()).intValue(), z8);
        m16251m(((Integer) view.getTag()).intValue(), z8);
        this.f14268e.get(i9).m16151x(z8);
        if (z8) {
            this.f14271h.mo16180a(this.f14268e.get(i9));
        } else {
            this.f14271h.mo16181b(this.f14268e.get(i9));
        }
    }

    /* renamed from: c */
    public final View.OnClickListener m16243c(final int i9) {
        return new View.OnClickListener() { // from class: c4.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3440b.m16242e(i9, view);
            }
        };
    }

    /* renamed from: f */
    public boolean m16244f(int i9) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            CLUtility.m16509Z0(this.f14268e.get(i9).m16144q(), CLUtility.m16510Z1(this.f14268e.get(i9).m16145r()), options);
            return options.outWidth < 1 || options.outHeight < 1;
        } catch (OutOfMemoryError unused) {
            return true;
        }
    }

    /* renamed from: g */
    public void m16245g(int i9) {
        this.f14270g = i9;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        b bVar;
        View viewInflate;
        if (view == null) {
            viewInflate = ((Activity) this.f14265b).getLayoutInflater().inflate(this.f14267d, viewGroup, false);
            bVar = new b();
            bVar.f14272a = (CheckBox) viewInflate.findViewById(R.id.itemCheckBox);
            bVar.f14273b = (ImageView) viewInflate.findViewById(R.id.image);
            bVar.f14274c = viewInflate.findViewById(R.id.imageBorder);
            viewInflate.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
            viewInflate = view;
        }
        ImageItem imageItem = this.f14268e.get(i9);
        CheckBox checkBox = bVar.f14272a;
        if (checkBox != null) {
            checkBox.setTag(Integer.valueOf(i9));
            bVar.f14272a.setChecked(imageItem.m16132e());
            bVar.f14272a.setOnClickListener(m16243c(i9));
        }
        if (bVar.f14274c != null) {
            if (imageItem.m16132e()) {
                bVar.f14274c.setBackgroundColor(Color.argb(255, 0, 150, 225));
            } else {
                bVar.f14274c.setBackgroundColor(Color.argb(255, 225, 225, 225));
            }
        }
        if (TextUtils.isEmpty(imageItem.m16136i())) {
            C6136j.m23602v(this.f14265b, bVar.f14273b, imageItem.m16135h(), R.drawable.doc_thumbnail_default, R.drawable.icon_photo_failed, 384, 384, false);
        } else {
            C6136j.m23597q(this.f14265b, bVar.f14273b, Uri.parse(imageItem.m16136i()), R.drawable.doc_thumbnail_default, R.drawable.icon_photo_failed, 384, 384, false);
        }
        return viewInflate;
    }

    /* renamed from: h */
    public void m16246h(C3105b.b bVar) {
        this.f14271h = bVar;
    }

    /* renamed from: i */
    public void m16247i(boolean z8) {
        this.f14269f = z8;
    }

    /* renamed from: j */
    public void m16248j() {
        C5187v0.m20267c(R.string.error_load_photo_fail);
    }

    /* renamed from: k */
    public void m16249k(int i9, boolean z8) {
        CheckBox checkBox;
        int childCount = this.f14266c.getChildCount();
        int firstVisiblePosition = this.f14266c.getFirstVisiblePosition();
        for (int i10 = 0; i10 < childCount; i10++) {
            if (firstVisiblePosition + i10 == i9 && (checkBox = ((b) this.f14266c.getChildAt(i10).getTag()).f14272a) != null) {
                checkBox.setChecked(z8);
            }
        }
    }

    /* renamed from: l */
    public void m16250l(int i9, boolean z8) {
        if (this.f14268e.size() <= 0 || i9 >= this.f14268e.size()) {
            return;
        }
        this.f14268e.get(i9).m16151x(z8);
    }

    /* renamed from: m */
    public void m16251m(int i9, boolean z8) {
        View view;
        int childCount = this.f14266c.getChildCount();
        int firstVisiblePosition = this.f14266c.getFirstVisiblePosition();
        for (int i10 = 0; i10 < childCount; i10++) {
            if (firstVisiblePosition + i10 == i9 && (view = ((b) this.f14266c.getChildAt(i10).getTag()).f14274c) != null) {
                if (z8) {
                    view.setBackgroundColor(Color.argb(255, 0, 150, 225));
                } else {
                    view.setBackgroundColor(Color.argb(255, 255, 255, 255));
                }
            }
        }
    }

    /* renamed from: n */
    public final void m16252n(boolean z8) {
        int iM7383g1 = Globals.m7383g1();
        Globals.m7384g4(z8 ? iM7383g1 + 1 : iM7383g1 - 1);
    }
}
