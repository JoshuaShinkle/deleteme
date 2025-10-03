package p115k3;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p116k4.C5168n1;

/* renamed from: k3.e */
/* loaded from: classes.dex */
public class C5126e extends ArrayAdapter<TopicObj> {

    /* renamed from: b */
    public LayoutInflater f17607b;

    /* renamed from: c */
    public FriendsClient f17608c;

    /* renamed from: d */
    public Context f17609d;

    /* renamed from: e */
    public final int f17610e;

    /* renamed from: f */
    public final int f17611f;

    /* renamed from: g */
    public final int f17612g;

    /* renamed from: h */
    public final int f17613h;

    /* renamed from: i */
    public final int f17614i;

    /* renamed from: j */
    public Map<String, StateListDrawable> f17615j;

    /* renamed from: k */
    public final Object f17616k;

    /* renamed from: k3.e$b */
    public class b {

        /* renamed from: a */
        public RelativeLayout f17617a;

        /* renamed from: b */
        public ImageView f17618b;

        /* renamed from: c */
        public TextView f17619c;

        /* renamed from: d */
        public TextView f17620d;

        /* renamed from: e */
        public TextView f17621e;

        /* renamed from: f */
        public TextView f17622f;

        /* renamed from: g */
        public TextView f17623g;

        /* renamed from: h */
        public TextView f17624h;

        /* renamed from: i */
        public TextView f17625i;

        /* renamed from: j */
        public TextView f17626j;

        /* renamed from: k */
        public View f17627k;

        /* renamed from: l */
        public ImageView f17628l;

        public b() {
        }
    }

    public C5126e(Context context, int i9, List<TopicObj> list) {
        super(context, i9, list);
        this.f17615j = new HashMap();
        this.f17616k = new Object();
        this.f17609d = context;
        this.f17607b = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f17610e = this.f17609d.getResources().getColor(R.color.topic_list_even_item);
        this.f17611f = this.f17609d.getResources().getColor(R.color.topic_list_odd_item);
        this.f17612g = this.f17609d.getResources().getColor(R.color.topic_list_item_pressed);
        this.f17613h = this.f17609d.getResources().getColor(R.color.you_color_normal_blue_text);
        this.f17614i = this.f17609d.getResources().getColor(R.color.text_normal);
    }

    /* renamed from: a */
    public void m19988a(TopicObj topicObj) {
        try {
            synchronized (this.f17616k) {
                boolean z8 = false;
                int i9 = 0;
                while (true) {
                    if (i9 < getCount()) {
                        TopicObj topicObj2 = (TopicObj) getItem(i9);
                        if (topicObj2 != null && topicObj2.m14849o() == topicObj.m14849o()) {
                            topicObj2.m14860z(topicObj);
                            z8 = true;
                            break;
                        }
                        i9++;
                    } else {
                        break;
                    }
                }
                if (!z8) {
                    add(topicObj);
                }
                notifyDataSetChanged();
            }
        } catch (ConcurrentModificationException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: b */
    public int m19989b() {
        int iM14850p = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < getCount(); i10++) {
            try {
                TopicObj topicObj = (TopicObj) getItem(i10);
                if (topicObj == null || topicObj.m14850p() == -1) {
                    i9 = -1;
                } else {
                    iM14850p += topicObj.m14850p();
                }
            } catch (ConcurrentModificationException e9) {
                e9.printStackTrace();
            }
        }
        return (iM14850p == 0 && i9 == -1) ? i9 : iM14850p;
    }

    /* renamed from: c */
    public void m19990c(long j9) {
        try {
            int count = getCount();
            synchronized (this.f17616k) {
                int i9 = 0;
                while (true) {
                    if (i9 >= count) {
                        break;
                    }
                    TopicObj topicObj = (TopicObj) getItem(i9);
                    if (topicObj != null && topicObj.m14849o() == j9) {
                        remove(topicObj);
                        notifyDataSetChanged();
                        break;
                    }
                    i9++;
                }
            }
        } catch (ConcurrentModificationException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: d */
    public void m19991d(long j9) {
        try {
            synchronized (this.f17616k) {
                int i9 = 0;
                while (true) {
                    if (i9 < getCount()) {
                        TopicObj topicObj = (TopicObj) getItem(i9);
                        if (topicObj != null && topicObj.m14849o() == j9) {
                            topicObj.m14834F(0);
                            notifyDataSetChanged();
                            break;
                        }
                        i9++;
                    } else {
                        break;
                    }
                }
            }
        } catch (ConcurrentModificationException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: e */
    public void m19992e(FriendsClient friendsClient) {
        this.f17608c = friendsClient;
    }

    /* renamed from: f */
    public void m19993f() {
        try {
            synchronized (this.f17616k) {
                int count = getCount();
                for (int i9 = 0; i9 < count; i9++) {
                    TopicObj topicObj = (TopicObj) getItem(i9);
                    if (topicObj != null && topicObj.f13103r) {
                        topicObj.m14834F(0);
                    }
                }
                notifyDataSetChanged();
            }
        } catch (ConcurrentModificationException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: g */
    public void m19994g(TopicObj topicObj) {
        try {
            synchronized (this.f17616k) {
                boolean z8 = false;
                int i9 = 0;
                while (true) {
                    if (i9 < getCount()) {
                        TopicObj topicObj2 = (TopicObj) getItem(i9);
                        if (topicObj2 != null && topicObj2.m14849o() == topicObj.m14849o()) {
                            topicObj2.m14834F(topicObj.m14850p());
                            topicObj2.m14830B(topicObj.m14844i());
                            topicObj2.f13092g = topicObj.f13092g;
                            notifyDataSetChanged();
                            z8 = true;
                            break;
                        }
                        i9++;
                    } else {
                        break;
                    }
                }
                if (!z8) {
                    add(topicObj);
                }
                sort(new TopicObj.C2945b());
            }
        } catch (ConcurrentModificationException e9) {
            e9.printStackTrace();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:33:0x01e9. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0321  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View getView(int i9, View view, ViewGroup viewGroup) {
        b bVar;
        StateListDrawable stateListDrawable;
        Object[] objArr;
        Object[] objArr2 = 0;
        if (view == null) {
            view = this.f17607b.inflate(R.layout.view_item_bulletin, viewGroup, false);
            bVar = new b();
            bVar.f17617a = (RelativeLayout) view.findViewById(R.id.root_view);
            bVar.f17618b = (ImageView) view.findViewById(R.id.item_avatar);
            bVar.f17619c = (TextView) view.findViewById(R.id.item_name);
            bVar.f17620d = (TextView) view.findViewById(R.id.item_title);
            bVar.f17621e = (TextView) view.findViewById(R.id.item_content);
            bVar.f17622f = (TextView) view.findViewById(R.id.bulletin_item_string_like_count);
            bVar.f17623g = (TextView) view.findViewById(R.id.bulletin_item_string_comment_count);
            bVar.f17624h = (TextView) view.findViewById(R.id.bulletin_item_time);
            bVar.f17625i = (TextView) view.findViewById(R.id.item_unread);
            bVar.f17626j = (TextView) view.findViewById(R.id.item_small_unread);
            bVar.f17627k = view.findViewById(R.id.bulletin_content_like_icon);
            bVar.f17628l = (ImageView) view.findViewById(R.id.item_sticky);
            view.setTag(bVar);
            StateListDrawable stateListDrawable2 = new StateListDrawable();
            stateListDrawable2.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(this.f17612g));
            stateListDrawable2.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(this.f17610e));
            stateListDrawable2.addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(this.f17610e));
            StateListDrawable stateListDrawable3 = new StateListDrawable();
            stateListDrawable3.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(this.f17612g));
            stateListDrawable3.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(this.f17611f));
            stateListDrawable3.addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(this.f17611f));
            this.f17615j.put(bVar.hashCode() + "EVEN", stateListDrawable2);
            this.f17615j.put(bVar.hashCode() + "ODD", stateListDrawable3);
        } else {
            bVar = (b) view.getTag();
        }
        if (i9 % 2 == 0) {
            stateListDrawable = this.f17615j.get(bVar.hashCode() + "ODD");
        } else {
            stateListDrawable = this.f17615j.get(bVar.hashCode() + "EVEN");
        }
        bVar.f17617a.setBackground(stateListDrawable);
        TopicObj topicObj = (TopicObj) getItem(i9);
        bVar.f17620d.setText(topicObj.m14848n());
        if (topicObj.f13097l) {
            bVar.f17628l.setVisibility(0);
        } else {
            bVar.f17628l.setVisibility(8);
        }
        if (!topicObj.m14842g().isEmpty()) {
            bVar.f17621e.setVisibility(0);
            bVar.f17621e.setText(topicObj.m14842g());
        } else {
            if (topicObj.m14839d().size() > 0) {
                String str = topicObj.m14839d().get(0).f12408a;
                str.hashCode();
                switch (str) {
                    case "Audio":
                        bVar.f17621e.setText(this.f17609d.getResources().getString(R.string.bulletin_user_posted_voice));
                        objArr = true;
                        break;
                    case "Photo":
                        bVar.f17621e.setText(this.f17609d.getString(topicObj.m14839d().size() < 2 ? R.string.posted_a_photo : R.string.posted_some_photos));
                        objArr = true;
                        break;
                    case "Video":
                        bVar.f17621e.setText(this.f17609d.getResources().getString(R.string.bulletin_user_posted_video));
                        objArr = true;
                        break;
                    default:
                        bVar.f17621e.setVisibility(0);
                        bVar.f17621e.setText("");
                        break;
                }
                bVar.f17622f.setText(this.f17609d.getResources().getString(R.string.like_D, Integer.valueOf(topicObj.m14846k())));
                if (topicObj.m14853s()) {
                    bVar.f17622f.setTextColor(this.f17614i);
                } else {
                    bVar.f17622f.setTextColor(this.f17613h);
                }
                bVar.f17623g.setText(this.f17609d.getResources().getString(R.string.comment_D, Integer.valueOf(topicObj.f13092g)));
                bVar.f17624h.setText(String.valueOf(CLUtility.m16446H2(this.f17609d, topicObj.m14840e(), false)));
                if (topicObj.m14850p() <= 0) {
                    bVar.f17625i.setVisibility(0);
                    bVar.f17626j.setVisibility(4);
                    if (topicObj.m14850p() <= 99) {
                        bVar.f17625i.setText(String.valueOf(topicObj.m14850p()));
                    } else {
                        bVar.f17625i.setText("N");
                    }
                } else if (topicObj.m14850p() == 0) {
                    bVar.f17625i.setVisibility(4);
                    bVar.f17626j.setVisibility(4);
                } else {
                    bVar.f17625i.setVisibility(4);
                    bVar.f17626j.setVisibility(0);
                }
                bVar.f17627k.setBackground(view.getResources().getDrawable(!topicObj.m14853s() ? R.drawable.icons_heart_s : R.drawable.icons_heart_n));
                C5168n1.m20151h(bVar.f17618b, bVar.f17619c, objArr == true ? bVar.f17621e : null, String.valueOf(topicObj.m14841f()), this.f17608c);
                return view;
            }
            bVar.f17621e.setVisibility(8);
            bVar.f17621e.setText("");
        }
        objArr = false;
        bVar.f17622f.setText(this.f17609d.getResources().getString(R.string.like_D, Integer.valueOf(topicObj.m14846k())));
        if (topicObj.m14853s()) {
        }
        bVar.f17623g.setText(this.f17609d.getResources().getString(R.string.comment_D, Integer.valueOf(topicObj.f13092g)));
        bVar.f17624h.setText(String.valueOf(CLUtility.m16446H2(this.f17609d, topicObj.m14840e(), false)));
        if (topicObj.m14850p() <= 0) {
        }
        bVar.f17627k.setBackground(view.getResources().getDrawable(!topicObj.m14853s() ? R.drawable.icons_heart_s : R.drawable.icons_heart_n));
        C5168n1.m20151h(bVar.f17618b, bVar.f17619c, objArr == true ? bVar.f17621e : null, String.valueOf(topicObj.m14841f()), this.f17608c);
        return view;
    }

    /* renamed from: h */
    public void m19995h(TopicObj topicObj) {
        for (int i9 = 0; i9 < getCount(); i9++) {
            try {
                TopicObj topicObj2 = (TopicObj) getItem(i9);
                if (topicObj2 != null && topicObj2.m14849o() == topicObj.m14849o()) {
                    topicObj2.f13100o = topicObj.f13100o;
                    return;
                }
            } catch (ConcurrentModificationException e9) {
                e9.printStackTrace();
                return;
            }
        }
    }

    /* renamed from: i */
    public void m19996i(TopicObj topicObj) {
        for (int i9 = 0; i9 < getCount(); i9++) {
            try {
                TopicObj topicObj2 = (TopicObj) getItem(i9);
                if (topicObj2 != null && topicObj2.m14849o() == topicObj.m14849o()) {
                    topicObj2.m14832D(topicObj.m14846k());
                    topicObj2.m14829A(topicObj.m14853s());
                    notifyDataSetChanged();
                    return;
                }
            } catch (ConcurrentModificationException e9) {
                e9.printStackTrace();
                return;
            }
        }
    }
}
