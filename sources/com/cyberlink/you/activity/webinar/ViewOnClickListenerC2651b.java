package com.cyberlink.you.activity.webinar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import p116k4.C5179r0;

/* renamed from: com.cyberlink.you.activity.webinar.b */
/* loaded from: classes.dex */
public class ViewOnClickListenerC2651b extends RecyclerView.AbstractC0442c0 implements View.OnClickListener {

    /* renamed from: b */
    public final InterfaceC2674g2 f12099b;

    /* renamed from: c */
    public TextView f12100c;

    /* renamed from: d */
    public ImageView f12101d;

    public ViewOnClickListenerC2651b(View view, InterfaceC2674g2 interfaceC2674g2) {
        super(view);
        view.setOnClickListener(this);
        this.f12099b = interfaceC2674g2;
        this.f12100c = (TextView) view.findViewById(R.id.FriendsTitleDisplayName);
        this.f12101d = (ImageView) view.findViewById(R.id.FriendsTitleExpand);
    }

    /* renamed from: a */
    public void m13932a(ExpandableData expandableData) {
        this.f12100c.setText(expandableData.m13493b());
        C5179r0.m20247b(this.f12100c, 1);
        this.f12101d.setImageResource(expandableData.m13500i() ? R.drawable.icon_expand_up : R.drawable.icon_expand_down);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f12099b.mo13935b(getAdapterPosition());
    }
}
