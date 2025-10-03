package p075g3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2993v0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import java.util.Date;
import java.util.List;
import p116k4.C5170o0;
import p173q2.C6127a;

/* renamed from: g3.e2 */
/* loaded from: classes.dex */
public class C4855e2 extends ArrayAdapter<C2993v0> {

    /* renamed from: b */
    public LayoutInflater f16858b;

    /* renamed from: c */
    public boolean f16859c;

    /* renamed from: d */
    public String f16860d;

    /* renamed from: e */
    public String f16861e;

    /* renamed from: g3.e2$b */
    public class b {

        /* renamed from: a */
        public ImageView f16862a;

        /* renamed from: b */
        public TextView f16863b;

        /* renamed from: c */
        public TextView f16864c;

        /* renamed from: d */
        public TextView f16865d;

        /* renamed from: e */
        public TextView f16866e;

        /* renamed from: f */
        public TextView f16867f;

        /* renamed from: g */
        public ImageView f16868g;

        public b() {
        }
    }

    public C4855e2(Context context, int i9) {
        super(context, i9);
        this.f16858b = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f16859c = false;
    }

    /* renamed from: b */
    public static String m19181b(String str, String str2) {
        int iIndexOf = str.toLowerCase().indexOf(str2.toLowerCase());
        if (iIndexOf < 0 || iIndexOf <= 10) {
            return str;
        }
        return "..." + str.substring(iIndexOf - 10);
    }

    /* renamed from: a */
    public void m19182a(String str, List<C2993v0> list) {
        clear();
        addAll(list);
        this.f16860d = str;
        notifyDataSetChanged();
    }

    /* renamed from: c */
    public String m19183c() {
        return this.f16861e;
    }

    /* renamed from: d */
    public boolean m19184d() {
        return this.f16859c;
    }

    /* renamed from: e */
    public void m19185e(String str) {
        this.f16861e = str;
    }

    /* renamed from: f */
    public void m19186f(boolean z8) {
        this.f16859c = z8;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.f16858b.inflate(R.layout.view_item_search_chat_item, (ViewGroup) null);
            bVar = new b();
            bVar.f16862a = (ImageView) view.findViewById(R.id.userAvatarView);
            bVar.f16863b = (TextView) view.findViewById(R.id.userNameView);
            bVar.f16864c = (TextView) view.findViewById(R.id.FriendsGroupsTextView_MemberCount);
            bVar.f16865d = (TextView) view.findViewById(R.id.messageView);
            bVar.f16866e = (TextView) view.findViewById(R.id.messageDateView);
            TextView textView = (TextView) view.findViewById(R.id.messageTimeView);
            bVar.f16867f = textView;
            textView.setVisibility(8);
            bVar.f16868g = (ImageView) view.findViewById(R.id.userLockView);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        C2993v0 c2993v0 = (C2993v0) getItem(i9);
        if (c2993v0 != null) {
            if (Integer.parseInt(c2993v0.m15253f()) > 1) {
                bVar.f16865d.setText(getContext().getString(R.string.search_more_messages_fount_in_group, c2993v0.m15253f()));
                bVar.f16866e.setVisibility(8);
            } else {
                bVar.f16865d.setText(C5170o0.m20168c(m19181b(c2993v0.m15252e(), this.f16860d), this.f16860d));
                bVar.f16866e.setText(CLUtility.m16450I2(new Date(Long.parseLong(c2993v0.m15255h()))));
                bVar.f16866e.setVisibility(0);
            }
            if (c2993v0.m15259l() || this.f16859c) {
                bVar.f16864c.setVisibility(8);
            } else {
                bVar.f16864c.setVisibility(0);
                bVar.f16864c.setText(String.valueOf(c2993v0.m15251d()));
            }
            if (this.f16859c) {
                bVar.f16863b.setText(c2993v0.m15256i());
                Friend friendM15003C = C2950b0.m14899A().m15003C(c2993v0.m15258k());
                C6127a.m23469j(getContext(), bVar.f16862a, friendM15003C);
                Group groupM15081r = C2950b0.m14912k().m15081r(friendM15003C.f13648f);
                if (groupM15081r != null) {
                    bVar.f16868g.setVisibility(groupM15081r.f13711J ? 0 : 8);
                }
            } else {
                bVar.f16863b.setText(c2993v0.m15248a());
                Group groupM15077n = C2950b0.m14912k().m15077n(c2993v0.m15250c());
                C6127a.m23470k(getContext(), bVar.f16862a, groupM15077n);
                bVar.f16868g.setVisibility(groupM15077n.f13711J ? 0 : 8);
            }
        }
        return view;
    }
}
