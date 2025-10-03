package com.cyberlink.you.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import p116k4.C5170o0;
import p173q2.C6127a;

/* renamed from: com.cyberlink.you.chat.z */
/* loaded from: classes.dex */
public class C2933z extends ArrayAdapter<Group> {

    /* renamed from: b */
    public List<b> f12873b;

    /* renamed from: c */
    public List<Group> f12874c;

    /* renamed from: d */
    public Context f12875d;

    /* renamed from: e */
    public final int f12876e;

    /* renamed from: f */
    public C2907m0.h f12877f;

    /* renamed from: com.cyberlink.you.chat.z$a */
    public class a implements C2907m0.h {
        public a() {
        }

        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public void mo119x(boolean z8) {
            if (z8) {
                C2933z.this.notifyDataSetChanged();
                C2907m0.m14454I().m14495W(C2933z.this.f12877f);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.z$b */
    public static class b {

        /* renamed from: a */
        public boolean f12879a;

        /* renamed from: b */
        public boolean f12880b;

        /* renamed from: c */
        public String f12881c;

        /* renamed from: d */
        public String f12882d;

        /* renamed from: e */
        public String f12883e;

        /* renamed from: f */
        public String f12884f;

        /* renamed from: g */
        public String f12885g;

        /* renamed from: h */
        public long f12886h;

        /* renamed from: i */
        public String f12887i;

        /* renamed from: j */
        public boolean f12888j;
    }

    /* renamed from: com.cyberlink.you.chat.z$c */
    public class c {

        /* renamed from: a */
        public ImageView f12889a;

        /* renamed from: b */
        public TextView f12890b;

        /* renamed from: c */
        public TextView f12891c;

        /* renamed from: d */
        public TextView f12892d;

        /* renamed from: e */
        public TextView f12893e;

        /* renamed from: f */
        public View f12894f;

        /* renamed from: g */
        public View f12895g;

        public c() {
        }
    }

    public C2933z(Context context, int i9, List<Group> list) {
        super(context, i9, list);
        this.f12876e = 3;
        this.f12877f = new a();
        this.f12874c = list;
        this.f12875d = context;
        this.f12873b = new ArrayList();
    }

    /* renamed from: b */
    public b m14667b(int i9) {
        return this.f12873b.get(i9);
    }

    @Override // android.widget.ArrayAdapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int getPosition(Group group) {
        for (int i9 = 0; i9 < this.f12874c.size(); i9++) {
            if (this.f12874c.get(i9).f13727n == group.f13727n) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: d */
    public final void m14669d(TextView textView, long j9, long j10, String str) {
        if (j9 <= j10) {
            str = String.valueOf(j9);
        }
        textView.setText(str);
    }

    /* renamed from: e */
    public void m14670e(int i9, b bVar, boolean z8) {
        if (z8) {
            this.f12873b.add(i9, bVar);
        } else {
            this.f12873b.set(i9, bVar);
        }
    }

    /* renamed from: f */
    public void m14671f(Group group, boolean z8, b bVar) {
        int position = getPosition(group);
        if (position == -1) {
            this.f12874c.add(0, group);
            position = getPosition(group);
            m14670e(position, bVar, true);
        } else {
            m14670e(position, bVar, false);
        }
        Group group2 = this.f12874c.get(position);
        if (group2 == null) {
            return;
        }
        group2.m15758p(group);
        if (this.f12874c.size() > 3) {
            this.f12874c.remove(3);
        }
        if (z8) {
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        c cVar;
        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_lock_screen, viewGroup, false);
            cVar = new c();
            cVar.f12889a = (ImageView) view.findViewById(R.id.lockMessageAvatar);
            cVar.f12890b = (TextView) view.findViewById(R.id.lockMessageTitle);
            cVar.f12891c = (TextView) view.findViewById(R.id.lockMessageLastMessage);
            cVar.f12892d = (TextView) view.findViewById(R.id.lockMessageTimeView);
            cVar.f12893e = (TextView) view.findViewById(R.id.LastMessageUnreadCountView);
            cVar.f12894f = view.findViewById(R.id.LastMessageBulletinUnreadCountView);
            cVar.f12895g = view.findViewById(R.id.UnreadLayout);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        Group group = this.f12874c.get(i9);
        C6127a.m23474o(this.f12875d, cVar.f12889a, this.f12873b.get(i9).f12887i, R.drawable.pic_default);
        if (group.f13728o == 1 && C5170o0.m20169d(group.f13717d)) {
            group.f13717d = this.f12875d.getString(R.string.group_empty_room);
        }
        cVar.f12890b.setText(group.f13717d);
        cVar.f12890b.setTag(new Object());
        C2907m0.m14454I().m14511u(this.f12877f);
        if (C2907m0.m14454I().m14489N()) {
            int iM14483G = C2907m0.m14454I().m14483G(group.f13723j);
            int iM14481E = C2907m0.m14454I().m14481E(group.f13727n, false);
            if (iM14483G > 0) {
                m14669d(cVar.f12893e, iM14483G, 99L, "N");
                cVar.f12893e.setVisibility(0);
                cVar.f12894f.setVisibility(8);
                cVar.f12895g.setVisibility(0);
                cVar.f12893e.setBackgroundResource(R.drawable.chat_unread);
            } else if (iM14481E == 0) {
                cVar.f12895g.setVisibility(4);
            } else if (iM14481E == -1) {
                cVar.f12893e.setVisibility(8);
                cVar.f12894f.setVisibility(0);
                cVar.f12895g.setVisibility(0);
            } else {
                m14669d(cVar.f12893e, iM14481E, 99L, "N");
                cVar.f12893e.setVisibility(0);
                cVar.f12894f.setVisibility(8);
                cVar.f12895g.setVisibility(0);
                cVar.f12893e.setBackgroundResource(R.drawable.bulletin_unread);
            }
        }
        if (this.f12873b.get(i9).f12885g != null) {
            cVar.f12891c.setText(this.f12873b.get(i9).f12885g);
            cVar.f12892d.setText(CLUtility.m16450I2(new Date(this.f12873b.get(i9).f12886h)));
        } else {
            cVar.f12891c.setText("");
            cVar.f12892d.setText("");
        }
        return view;
    }
}
