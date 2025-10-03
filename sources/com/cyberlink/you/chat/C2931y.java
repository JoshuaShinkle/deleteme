package com.cyberlink.you.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5170o0;
import p116k4.C5180s;
import p173q2.C6127a;
import p209u2.C6383t;
import p244y1.C6597b;

/* renamed from: com.cyberlink.you.chat.y */
/* loaded from: classes.dex */
public class C2931y extends C6597b<Group> {

    /* renamed from: g */
    public final C2907m0.h f12855g;

    /* renamed from: com.cyberlink.you.chat.y$a */
    public class a implements C2907m0.h {
        public a() {
        }

        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public void mo119x(boolean z8) {
            if (z8) {
                C2931y.this.notifyDataSetChanged();
                C2907m0.m14454I().m14495W(C2931y.this.f12855g);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.y$b */
    public class b extends Filter {
        public b() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList arrayList = new ArrayList();
            for (Group group : C2931y.this.f22176e) {
                if (group != null && group.f13717d.toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(group);
                }
            }
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            C2931y.this.f22175d = (ArrayList) filterResults.values;
            C2931y.this.notifyDataSetChanged();
        }

        public /* synthetic */ b(C2931y c2931y, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.chat.y$c */
    public class c {

        /* renamed from: a */
        public ImageView f12858a;

        /* renamed from: b */
        public TextView f12859b;

        /* renamed from: c */
        public TextView f12860c;

        /* renamed from: d */
        public TextView f12861d;

        /* renamed from: e */
        public TextView f12862e;

        /* renamed from: f */
        public TextView f12863f;

        /* renamed from: g */
        public LinearLayout f12864g;

        /* renamed from: h */
        public CheckBox f12865h;

        /* renamed from: i */
        public View f12866i;

        /* renamed from: j */
        public ImageView f12867j;

        /* renamed from: k */
        public View f12868k;

        /* renamed from: l */
        public ImageView f12869l;

        public c() {
        }
    }

    public C2931y(Context context, int i9, List<Group> list) {
        super(context, i9, list);
        a aVar = new a();
        this.f12855g = aVar;
        if (C2907m0.m14454I().m14489N()) {
            return;
        }
        C2907m0.m14454I().m14511u(aVar);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public Filter getFilter() {
        if (this.f22177f == null) {
            this.f22177f = new b(this, null);
        }
        return this.f22177f;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0245  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View getView(int i9, View view, ViewGroup viewGroup) throws JSONException {
        c cVar;
        View view2;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.view_item_chat_group, viewGroup, false);
            cVar = new c();
            cVar.f12858a = (ImageView) viewInflate.findViewById(R.id.chatGroupAvatarView);
            cVar.f12859b = (TextView) viewInflate.findViewById(R.id.chatGroupNameView);
            cVar.f12860c = (TextView) viewInflate.findViewById(R.id.chatGroupLastMessageUpperView);
            cVar.f12861d = (TextView) viewInflate.findViewById(R.id.chatGroupTimeView);
            cVar.f12862e = (TextView) viewInflate.findViewById(R.id.chatGroupUnreadCountView);
            cVar.f12864g = (LinearLayout) viewInflate.findViewById(R.id.chatGroupRightLayout);
            cVar.f12865h = (CheckBox) viewInflate.findViewById(R.id.chatGroupItemCheckBox);
            cVar.f12863f = (TextView) viewInflate.findViewById(R.id.chatGroupCountViewText);
            cVar.f12866i = viewInflate.findViewById(R.id.bulletin_notification_disable_unread);
            cVar.f12868k = viewInflate.findViewById(R.id.UnreadLayout);
            cVar.f12867j = (ImageView) viewInflate.findViewById(R.id.ChatListNotificationDisableBtn);
            cVar.f12869l = (ImageView) viewInflate.findViewById(R.id.chatGroupLockView);
            viewInflate.setTag(cVar);
            view2 = viewInflate;
        } else {
            cVar = (c) view.getTag();
            view2 = view;
        }
        c cVar2 = cVar;
        Group group = (Group) this.f22175d.get(i9);
        C6127a.m23470k(getContext(), cVar2.f12858a, group);
        if (group.f13728o == 1 && C5170o0.m20169d(group.f13717d)) {
            group.f13717d = getContext().getString(R.string.group_empty_room);
        }
        cVar2.f12859b.setText(group.f13717d);
        if (group.f13716c.equals("Dual")) {
            cVar2.f12863f.setVisibility(8);
        } else {
            cVar2.f12863f.setVisibility(0);
            m14659p(cVar2.f12863f, group.f13728o, 999L, "999+");
        }
        if (group.f13733t) {
            cVar2.f12867j.setVisibility(0);
        } else {
            cVar2.f12867j.setVisibility(8);
        }
        if (m25234c()) {
            cVar2.f12864g.setVisibility(4);
            cVar2.f12865h.setVisibility(0);
            cVar2.f12865h.setChecked(this.f22174c.contains(group));
        } else {
            cVar2.f12864g.setVisibility(0);
            cVar2.f12865h.setVisibility(4);
            if (C2907m0.m14454I().m14489N()) {
                int iM14483G = C2907m0.m14454I().m14483G(group.f13723j);
                int iM14482F = C2907m0.m14454I().m14482F(group.f13723j);
                int iM14481E = C2907m0.m14454I().m14481E(group.f13727n, false);
                if (iM14483G > 0) {
                    m14659p(cVar2.f12862e, iM14483G, 99L, "N");
                    cVar2.f12862e.setVisibility(0);
                    cVar2.f12866i.setVisibility(8);
                    cVar2.f12868k.setVisibility(0);
                    cVar2.f12862e.setBackgroundResource(R.drawable.chat_unread);
                } else if (iM14482F > 0) {
                    cVar2.f12862e.setVisibility(0);
                    cVar2.f12866i.setVisibility(8);
                    cVar2.f12868k.setVisibility(0);
                    cVar2.f12862e.setBackgroundResource(R.drawable.chat_reminder);
                    cVar2.f12862e.setText(String.valueOf(iM14482F));
                } else if (iM14481E == 0) {
                    cVar2.f12868k.setVisibility(4);
                } else if (iM14481E == -1) {
                    cVar2.f12862e.setVisibility(4);
                    cVar2.f12866i.setVisibility(0);
                    cVar2.f12868k.setVisibility(0);
                } else {
                    m14659p(cVar2.f12862e, iM14481E, 99L, "N");
                    cVar2.f12862e.setVisibility(0);
                    cVar2.f12866i.setVisibility(8);
                    cVar2.f12868k.setVisibility(0);
                    cVar2.f12862e.setBackgroundResource(R.drawable.bulletin_unread);
                }
            }
        }
        String str = group.f13739z;
        boolean z8 = true;
        if (!C5170o0.m20169d(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(FirebaseAnalytics.Param.CONTENT);
                long j9 = jSONObject.getLong("time");
                if ((C5170o0.m20169d(string) || j9 == 0) ? false : true) {
                    try {
                        cVar2.f12860c.setText(string);
                        cVar2.f12861d.setText(CLUtility.m16450I2(new Date(j9)));
                    } catch (JSONException e9) {
                        e = e9;
                        e.printStackTrace();
                        if (!z8) {
                        }
                        cVar2.f12869l.setVisibility(group.f13711J ? 0 : 8);
                        return view2;
                    }
                } else {
                    z8 = false;
                }
            } catch (JSONException e10) {
                e = e10;
                z8 = false;
            }
        }
        if (!z8) {
            cVar2.f12860c.setText("");
            cVar2.f12861d.setText("");
        }
        cVar2.f12869l.setVisibility(group.f13711J ? 0 : 8);
        return view2;
    }

    /* renamed from: k */
    public Group m14654k(String str) {
        for (T t8 : this.f22176e) {
            if (C6383t.m24513b(t8.f13723j, str)) {
                return t8;
            }
        }
        return null;
    }

    /* renamed from: l */
    public Group m14655l(String str) {
        for (T t8 : this.f22176e) {
            if (String.valueOf(t8.f13727n).equals(str)) {
                return t8;
            }
        }
        return null;
    }

    /* renamed from: m */
    public Group m14656m(String... strArr) {
        for (T t8 : this.f22176e) {
            for (String str : strArr) {
                if (String.valueOf(t8.f13723j).equals(str)) {
                    return t8;
                }
            }
        }
        return null;
    }

    @Override // android.widget.ArrayAdapter
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public int getPosition(Group group) {
        for (int i9 = 0; i9 < this.f22176e.size(); i9++) {
            if (((Group) this.f22176e.get(i9)).f13727n == group.f13727n) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: o */
    public int m14658o() {
        if (this.f22176e != null && C2907m0.m14454I().m14489N()) {
            for (int i9 = 0; i9 < this.f22176e.size(); i9++) {
                Group group = (Group) this.f22176e.get(i9);
                int iM14483G = C2907m0.m14454I().m14483G(group.f13723j);
                int iM14482F = C2907m0.m14454I().m14482F(group.f13723j);
                int iM14481E = C2907m0.m14454I().m14481E(group.f13727n, false);
                if (iM14483G != 0 || iM14482F != 0 || iM14481E != 0) {
                    return i9;
                }
            }
        }
        return -1;
    }

    /* renamed from: p */
    public final void m14659p(TextView textView, long j9, long j10, String str) {
        if (j9 <= j10) {
            str = String.valueOf(j9);
        }
        textView.setText(str);
    }

    /* renamed from: q */
    public void m14660q(int i9, int i10) {
        Collections.swap(this.f22176e, i9, i10);
    }

    /* renamed from: r */
    public void m14661r(Group group) {
        Group group2;
        int position = getPosition(group);
        if (position == -1) {
            this.f22176e.add(0, group);
            group2 = group;
        } else {
            group2 = (Group) this.f22176e.get(position);
        }
        group2.m15758p(group);
        notifyDataSetChanged();
    }

    /* renamed from: s */
    public void m14662s(Group group) {
        Group groupM14655l;
        if (group == null || (groupM14655l = m14655l(String.valueOf(group.f13727n))) == null) {
            return;
        }
        groupM14655l.f13739z = group.f13739z;
    }

    /* renamed from: t */
    public void m14663t(String str, TopicCommentObj topicCommentObj) {
        if (str == null || topicCommentObj == null) {
            return;
        }
        Group groupM14655l = m14655l(str);
        if (groupM14655l != null && !C5170o0.m20169d(groupM14655l.f13739z)) {
            try {
                long j9 = new JSONObject(groupM14655l.f13739z).getLong("time");
                if (j9 != 0) {
                    if (topicCommentObj.m14030e() <= j9) {
                        return;
                    }
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        if (groupM14655l != null) {
            groupM14655l.f13739z = C5180s.m20249b(getContext(), topicCommentObj);
        }
    }

    /* renamed from: u */
    public void m14664u(String str, TopicObj topicObj) {
        if (str == null || topicObj == null) {
            return;
        }
        Group groupM14655l = m14655l(str);
        if (groupM14655l != null && !C5170o0.m20169d(groupM14655l.f13739z)) {
            try {
                long j9 = new JSONObject(groupM14655l.f13739z).getLong("time");
                if (j9 != 0) {
                    if (topicObj.m14840e() <= j9) {
                        return;
                    }
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        if (groupM14655l != null) {
            groupM14655l.f13739z = C5180s.m20252e(getContext(), topicObj);
        }
    }

    /* renamed from: v */
    public void m14665v(C2904l c2904l) {
        MessageObj messageObjM14449w0;
        Group groupM14656m = m14656m(c2904l.m14418h(), C5616j.m22345j(c2904l.m14388C()));
        if (groupM14656m == null || (messageObjM14449w0 = c2904l.m14449w0(String.valueOf(groupM14656m.f13727n))) == null) {
            return;
        }
        if (!C5170o0.m20169d(groupM14656m.f13739z)) {
            try {
                long j9 = new JSONObject(groupM14656m.f13739z).getLong("time");
                if (j9 != 0) {
                    if (messageObjM14449w0.m14788z().before(new Date(j9))) {
                        return;
                    }
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        groupM14656m.f13739z = C5180s.m20250c(getContext(), messageObjM14449w0);
    }
}
