package p086h4;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.cyberlink.p030U.R;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AbsListView;
import java.util.List;

/* renamed from: h4.h */
/* loaded from: classes.dex */
public class C5004h extends ArrayAdapter<AbstractC5003g> {

    /* renamed from: b */
    public boolean f17242b;

    /* renamed from: c */
    public int f17243c;

    /* renamed from: d */
    public Activity f17244d;

    /* renamed from: h4.h$b */
    public class b {

        /* renamed from: a */
        public ImageView f17245a;

        /* renamed from: b */
        public RelativeLayout f17246b;

        public b() {
        }
    }

    public C5004h(Activity activity, int i9, List<AbstractC5003g> list) {
        super(activity, i9, list);
        this.f17242b = false;
        this.f17243c = -1;
        this.f17244d = activity;
    }

    @Override // android.widget.ArrayAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(AbstractC5003g abstractC5003g) {
        super.add(abstractC5003g);
        if (abstractC5003g instanceof C4995b) {
            this.f17242b = true;
        }
    }

    /* renamed from: b */
    public int m19442b() {
        return 0;
    }

    /* renamed from: c */
    public int m19443c(long j9) {
        if (j9 == Long.parseLong("-999")) {
            return m19444d();
        }
        for (int i9 = 0; i9 < getCount(); i9++) {
            StickerPackObj stickerPackObjM19439e = ((AbstractC5003g) getItem(i9)).m19439e();
            if (stickerPackObjM19439e != null && j9 == stickerPackObjM19439e.m14803g()) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: d */
    public int m19444d() {
        return !this.f17242b ? 0 : 1;
    }

    /* renamed from: e */
    public int m19445e() {
        int i9 = 0;
        for (int i10 = 0; i10 < getCount(); i10++) {
            if (getItem(i10) instanceof C5018v) {
                i9++;
            }
        }
        return i9;
    }

    /* renamed from: f */
    public int m19446f(AbstractC5003g abstractC5003g) {
        if (abstractC5003g instanceof C4995b) {
            return m19442b();
        }
        if (abstractC5003g instanceof C4999d) {
            return m19444d();
        }
        long jM14803g = abstractC5003g.m19439e().m14803g();
        for (int i9 = 0; i9 < getCount(); i9++) {
            StickerPackObj stickerPackObjM19439e = ((AbstractC5003g) getItem(i9)).m19439e();
            if (stickerPackObjM19439e != null && jM14803g == stickerPackObjM19439e.m14803g()) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: g */
    public int m19447g() {
        for (int i9 = 0; i9 < getCount(); i9++) {
            if (getItem(i9) instanceof C5018v) {
                return i9;
            }
        }
        return -1;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        View viewInflate;
        b bVar;
        if (view == null) {
            bVar = new b();
            if (this.f17244d != null) {
                viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_sticker_category, viewGroup, false);
                ImageView imageView = (ImageView) viewInflate.findViewById(R.id.sticker_category_image);
                bVar.f17245a = imageView;
                imageView.setContentDescription("[AID]Sticker_Category" + i9);
                bVar.f17246b = (RelativeLayout) viewInflate.findViewById(R.id.sticker_category_background);
                AbsListView.C3217g c3217g = new AbsListView.C3217g(-1, -1);
                this.f17244d.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
                ((ViewGroup.LayoutParams) c3217g).width = (int) ((r1.widthPixels / 1080.0f) * 158.0f);
                viewInflate.setLayoutParams(c3217g);
                viewInflate.setTag(bVar);
            } else {
                bVar.f17246b = new RelativeLayout(getContext());
                ImageView imageView2 = new ImageView(getContext());
                bVar.f17245a = imageView2;
                RelativeLayout relativeLayout = bVar.f17246b;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13, -1);
                imageView2.setLayoutParams(layoutParams);
                relativeLayout.addView(imageView2);
                relativeLayout.setLayoutParams(new AbsListView.C3217g(-1, -1));
                relativeLayout.setTag(bVar);
                viewInflate = relativeLayout;
            }
        } else {
            viewInflate = view;
            bVar = (b) view.getTag();
        }
        Activity activity = this.f17244d;
        if (activity != null) {
            RelativeLayout relativeLayout2 = bVar.f17246b;
            if (this.f17243c == i9) {
                relativeLayout2.setBackgroundResource(R.drawable.bg_sticker_category_selected);
            } else {
                relativeLayout2.setBackgroundColor(activity.getResources().getColor(R.color.you_color_light_gray));
            }
        }
        ((AbstractC5003g) getItem(i9)).mo19378g(bVar.f17245a);
        return viewInflate;
    }

    /* renamed from: h */
    public void m19448h(int i9) {
        this.f17243c = i9;
    }
}
