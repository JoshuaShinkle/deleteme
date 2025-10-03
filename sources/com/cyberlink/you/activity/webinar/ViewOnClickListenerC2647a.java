package com.cyberlink.you.activity.webinar;

import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.webinar.ExpandableData;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.WatchHistoryList;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import java.util.Date;
import p116k4.C5157k;
import p116k4.C5179r0;

/* renamed from: com.cyberlink.you.activity.webinar.a */
/* loaded from: classes.dex */
public class ViewOnClickListenerC2647a extends RecyclerView.AbstractC0442c0 implements View.OnClickListener {

    /* renamed from: b */
    public final InterfaceC2670f2 f12084b;

    /* renamed from: c */
    public MyWebinarActivity f12085c;

    /* renamed from: d */
    public TextView f12086d;

    /* renamed from: e */
    public TextView f12087e;

    /* renamed from: f */
    public TextView f12088f;

    /* renamed from: g */
    public TextView f12089g;

    /* renamed from: h */
    public TextView f12090h;

    /* renamed from: i */
    public ImageView f12091i;

    /* renamed from: com.cyberlink.you.activity.webinar.a$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f12092a;

        static {
            int[] iArr = new int[ExpandableData.DataType.values().length];
            f12092a = iArr;
            try {
                iArr[ExpandableData.DataType.REGISTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12092a[ExpandableData.DataType.HISTORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ViewOnClickListenerC2647a(View view, MyWebinarActivity myWebinarActivity, InterfaceC2670f2 interfaceC2670f2) {
        super(view);
        view.setOnClickListener(this);
        this.f12085c = myWebinarActivity;
        this.f12084b = interfaceC2670f2;
        this.f12086d = (TextView) view.findViewById(R.id.webinarsHistoryTitle);
        this.f12087e = (TextView) view.findViewById(R.id.webinarsHistoryDate);
        this.f12088f = (TextView) view.findViewById(R.id.webinarsHistoryTime);
        this.f12089g = (TextView) view.findViewById(R.id.secondSeparateLine);
        this.f12090h = (TextView) view.findViewById(R.id.webinarsHistoryLength);
        this.f12091i = (ImageView) view.findViewById(R.id.deleteCheckbox);
    }

    /* renamed from: a */
    public void m13931a(ExpandableData expandableData, C2666e2 c2666e2) {
        int i9 = a.f12092a[expandableData.m13492a().ordinal()];
        if (i9 == 1) {
            this.f12088f.setVisibility(8);
            this.f12089g.setVisibility(8);
            Live liveM13499h = expandableData.m13499h();
            Date dateM13954f = C2698m2.m13954f(liveM13499h.scheduleInterval.startDate);
            String str = "" + DateFormat.format("hh:mm a", dateM13954f).toString();
            this.f12086d.setText(liveM13499h.title);
            if (dateM13954f != null) {
                this.f12087e.setText(DateFormat.format("yyyy/MM/dd", dateM13954f));
            }
            this.f12090h.setText(str + " - " + DateFormat.format("hh:mm a", C2698m2.m13954f(liveM13499h.scheduleInterval.endDate)).toString());
            this.f12090h.setTextColor(this.f12085c.getResources().getColor(R.color.you_color_normal_gray_text));
            C5179r0.m20247b(this.f12090h, 1);
        } else {
            if (i9 != 2) {
                throw new IllegalArgumentException("dataType is not valid");
            }
            this.f12088f.setVisibility(0);
            this.f12089g.setVisibility(0);
            WatchHistoryList.LiveHistoryInfo liveHistoryInfoM13497f = expandableData.m13497f();
            Date dateM13954f2 = C2698m2.m13954f(liveHistoryInfoM13497f.publishDate);
            this.f12086d.setText(liveHistoryInfoM13497f.title);
            if (dateM13954f2 != null) {
                this.f12087e.setText(DateFormat.format("yyyy/MM/dd", dateM13954f2));
                this.f12088f.setText(DateFormat.format("hh:mm a", dateM13954f2));
            }
            String str2 = liveHistoryInfoM13497f.state;
            str2.hashCode();
            if (str2.equals(NetworkLive.STATE.VIDEO_ON_DEMAND)) {
                this.f12090h.setText(NetworkLive.STATE.VIDEO_ON_DEMAND);
                this.f12090h.setTextColor(this.f12085c.getResources().getColor(R.color.you_color_normal_gray_text));
            } else if (str2.equals(NetworkLive.STATE.LIVE)) {
                this.f12090h.setText(R.string.clw_status_on_live);
                this.f12090h.setTextColor(Color.parseColor("#d40050"));
            } else {
                this.f12090h.setText(C5157k.m20086b(liveHistoryInfoM13497f.length.longValue()));
                this.f12090h.setTextColor(this.f12085c.getResources().getColor(R.color.you_color_normal_gray_text));
            }
        }
        this.f12091i.setSelected(expandableData.m13501j());
        this.f12091i.setVisibility((expandableData.m13492a() == ExpandableData.DataType.HISTORY && c2666e2.m13944k()) ? 0 : 4);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f12084b.mo13934a(getAdapterPosition(), this.f12085c);
    }
}
