package p245y2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.CreateTopicActivity;
import com.cyberlink.you.activity.TopicContentActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import p116k4.C5168n1;
import p136m3.C5321e;
import p209u2.C6385v;

/* renamed from: y2.h6 */
/* loaded from: classes.dex */
public class C6661h6 extends Fragment {

    /* renamed from: b */
    public b f22277b;

    /* renamed from: c */
    public ListView f22278c;

    /* renamed from: d */
    public c f22279d;

    /* renamed from: e */
    public ImageView f22280e;

    /* renamed from: f */
    public RelativeLayout f22281f;

    /* renamed from: g */
    public FriendsClient f22282g;

    /* renamed from: h */
    public Group f22283h;

    /* renamed from: i */
    public List<TopicObj> f22284i;

    /* renamed from: j */
    public C5321e.m f22285j = new a();

    /* renamed from: y2.h6$a */
    public class a implements C5321e.m {
        public a() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public boolean mo8241A0(C2904l c2904l, Map<String, String> map) throws NumberFormatException {
            String str = map.get("eventType");
            String str2 = map.get("groupId");
            if (str.equals("bulletin.poll.option.casted") || str.equals("bulletin.poll.option.uncasted") || str.equals("bulletin.topic.casted") || str.equals("bulletin.topic.uncasted")) {
                C6661h6.this.m25280C(map.get("topicId"));
                return true;
            }
            if (str2 == null || Long.parseLong(str2) != C6661h6.this.f22283h.f13727n) {
                return false;
            }
            switch (str) {
                case "bulletin.topic.created":
                    if ("Poll".equals(map.get("topicType"))) {
                        C6661h6.this.m25283F(map.get("topicId"));
                    }
                    return true;
                case "bulletin.topic.deleted":
                    C6661h6.this.m25284G(map.get("topicId"));
                    return true;
                case "bulletin.post.created":
                case "bulletin.post.deleted":
                    C6661h6.this.m25281D(map.get("topicId"));
                    return true;
                case "bulletin.topic.unliked":
                case "bulletin.topic.liked":
                    C6661h6.this.m25285H(map.get("topicId"));
                    return true;
                case "bulletin.topic.closed":
                    C6661h6.this.m25282E(map.get("topicId"), true);
                    return true;
                case "bulletin.topic.reopened":
                    C6661h6.this.m25282E(map.get("topicId"), false);
                    return true;
                default:
                    return true;
            }
        }
    }

    /* renamed from: y2.h6$b */
    public class b extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public DialogC3133q f22287a;

        public b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            publishProgress(new Void[0]);
            C6661h6 c6661h6 = C6661h6.this;
            c6661h6.f22284i = c6661h6.f22282g.m15721a0(C6661h6.this.f22283h.f13727n, true);
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r22) {
            DialogC3133q dialogC3133q = this.f22287a;
            if (dialogC3133q != null) {
                dialogC3133q.dismiss();
            }
            C6661h6.this.m25289U();
            C6661h6.this.f22278c.setAdapter((ListAdapter) C6661h6.this.f22279d);
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onProgressUpdate(Void... voidArr) {
            this.f22287a = new DialogC3133q.b(C6661h6.this.getActivity()).m16411b();
        }

        public /* synthetic */ b(C6661h6 c6661h6, a aVar) {
            this();
        }
    }

    /* renamed from: y2.h6$c */
    public class c extends BaseAdapter {

        /* renamed from: b */
        public Context f22289b;

        /* renamed from: c */
        public final int f22290c;

        /* renamed from: d */
        public final int f22291d;

        /* renamed from: e */
        public final int f22292e;

        /* renamed from: f */
        public Map<String, StateListDrawable> f22293f = new HashMap();

        /* renamed from: g */
        public final Object f22294g = new Object();

        /* renamed from: y2.h6$c$a */
        public class a {

            /* renamed from: a */
            public RelativeLayout f22296a;

            /* renamed from: b */
            public ImageView f22297b;

            /* renamed from: c */
            public TextView f22298c;

            /* renamed from: d */
            public TextView f22299d;

            /* renamed from: e */
            public TextView f22300e;

            /* renamed from: f */
            public TextView f22301f;

            public a() {
            }

            public /* synthetic */ a(c cVar, a aVar) {
                this();
            }
        }

        public c(Context context) {
            this.f22289b = context;
            this.f22290c = context.getResources().getColor(R.color.topic_list_even_item);
            this.f22291d = this.f22289b.getResources().getColor(R.color.topic_list_odd_item);
            this.f22292e = this.f22289b.getResources().getColor(R.color.topic_list_item_pressed);
        }

        /* renamed from: a */
        public void m25294a(TopicObj topicObj) {
            synchronized (this.f22294g) {
                if (C6661h6.this.f22284i == null) {
                    C6661h6.this.f22284i = new ArrayList();
                }
                Iterator it = C6661h6.this.f22284i.iterator();
                while (it.hasNext()) {
                    if (((TopicObj) it.next()).m14849o() == topicObj.m14849o()) {
                        return;
                    }
                }
                C6661h6.this.f22284i.add(topicObj);
                Collections.sort(C6661h6.this.f22284i, new TopicObj.C2945b());
                notifyDataSetChanged();
            }
        }

        /* renamed from: b */
        public void m25295b(long j9) {
            synchronized (this.f22294g) {
                int size = C6661h6.this.f22284i.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    TopicObj topicObj = (TopicObj) C6661h6.this.f22284i.get(size);
                    if (topicObj.m14849o() == j9) {
                        C6661h6.this.f22284i.remove(topicObj);
                        break;
                    }
                    size--;
                }
            }
            notifyDataSetChanged();
        }

        /* renamed from: c */
        public void m25296c(long j9, boolean z8) {
            synchronized (this.f22294g) {
                if (C6661h6.this.f22284i == null) {
                    return;
                }
                int size = C6661h6.this.f22284i.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    TopicObj topicObj = (TopicObj) C6661h6.this.f22284i.get(size);
                    if (topicObj.m14849o() == j9) {
                        topicObj.f13100o = z8;
                        Collections.sort(C6661h6.this.f22284i, new TopicObj.C2945b());
                        break;
                    }
                    size--;
                }
                notifyDataSetChanged();
            }
        }

        /* renamed from: d */
        public void m25297d(TopicObj topicObj) {
            synchronized (this.f22294g) {
                if (C6661h6.this.f22284i != null) {
                    Iterator it = C6661h6.this.f22284i.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TopicObj topicObj2 = (TopicObj) it.next();
                        if (topicObj2.m14849o() == topicObj.m14849o()) {
                            topicObj2.m14833E(topicObj.f13092g);
                            topicObj2.m14830B(topicObj.m14844i());
                            Collections.sort(C6661h6.this.f22284i, new TopicObj.C2945b());
                            break;
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }

        /* renamed from: e */
        public void m25298e(TopicObj topicObj) {
            synchronized (this.f22294g) {
                if (C6661h6.this.f22284i != null) {
                    Iterator it = C6661h6.this.f22284i.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TopicObj topicObj2 = (TopicObj) it.next();
                        if (topicObj2.m14849o() == topicObj.m14849o()) {
                            topicObj2.m14829A(topicObj.m14853s());
                            topicObj2.m14832D(topicObj.m14846k());
                            break;
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }

        /* renamed from: f */
        public void m25299f(TopicObj topicObj) {
            synchronized (this.f22294g) {
                if (C6661h6.this.f22284i != null) {
                    Iterator it = C6661h6.this.f22284i.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TopicObj topicObj2 = (TopicObj) it.next();
                        if (topicObj2.m14849o() == topicObj.m14849o()) {
                            topicObj2.f13109x = topicObj.f13109x;
                            topicObj2.f13110y = topicObj.f13110y;
                            break;
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (C6661h6.this.f22284i == null) {
                return 0;
            }
            return C6661h6.this.f22284i.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            if (C6661h6.this.f22284i == null) {
                return null;
            }
            return C6661h6.this.f22284i.get(i9);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return 0L;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:33:0x01a1. Please report as an issue. */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0213  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0231  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x027c  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x02bc  */
        @Override // android.widget.Adapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            StateListDrawable stateListDrawable;
            Object[] objArr;
            Object[] objArr2 = 0;
            if (view == null) {
                view = ((LayoutInflater) this.f22289b.getSystemService("layout_inflater")).inflate(R.layout.pollslist_item, viewGroup, false);
                aVar = new a(this, objArr2 == true ? 1 : 0);
                aVar.f22296a = (RelativeLayout) view.findViewById(R.id.root_view);
                aVar.f22297b = (ImageView) view.findViewById(R.id.item_avatar);
                aVar.f22298c = (TextView) view.findViewById(R.id.item_name);
                aVar.f22299d = (TextView) view.findViewById(R.id.item_title);
                aVar.f22300e = (TextView) view.findViewById(R.id.textViewCreatedTime);
                aVar.f22301f = (TextView) view.findViewById(R.id.textViewVotesCount);
                view.setTag(aVar);
                StateListDrawable stateListDrawable2 = new StateListDrawable();
                stateListDrawable2.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(this.f22292e));
                stateListDrawable2.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(this.f22290c));
                stateListDrawable2.addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(this.f22290c));
                StateListDrawable stateListDrawable3 = new StateListDrawable();
                stateListDrawable3.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(this.f22292e));
                stateListDrawable3.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(this.f22291d));
                stateListDrawable3.addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(this.f22291d));
                this.f22293f.put(aVar.hashCode() + "EVEN", stateListDrawable2);
                this.f22293f.put(aVar.hashCode() + "ODD", stateListDrawable3);
            } else {
                aVar = (a) view.getTag();
            }
            if (i9 % 2 == 0) {
                stateListDrawable = this.f22293f.get(aVar.hashCode() + "ODD");
            } else {
                stateListDrawable = this.f22293f.get(aVar.hashCode() + "EVEN");
            }
            aVar.f22296a.setBackground(stateListDrawable);
            TopicObj topicObj = (TopicObj) getItem(i9);
            if (topicObj == null) {
                ULogUtility.m16670f("PollsFragment", "getView method topicObj is null");
                return view;
            }
            String str = "";
            if (!topicObj.m14842g().isEmpty()) {
                aVar.f22299d.setVisibility(0);
                aVar.f22299d.setText(topicObj.m14842g());
            } else {
                if (topicObj.m14839d().size() > 0) {
                    String str2 = topicObj.m14839d().get(0).f12408a;
                    str2.hashCode();
                    switch (str2) {
                        case "Audio":
                            aVar.f22299d.setText(this.f22289b.getResources().getString(R.string.bulletin_user_posted_voice));
                            objArr = true;
                            break;
                        case "Photo":
                            aVar.f22299d.setText(this.f22289b.getString(topicObj.m14839d().size() < 2 ? R.string.posted_a_photo : R.string.posted_some_photos));
                            objArr = true;
                            break;
                        case "Video":
                            aVar.f22299d.setText(this.f22289b.getResources().getString(R.string.bulletin_user_posted_video));
                            objArr = true;
                            break;
                        default:
                            aVar.f22299d.setVisibility(0);
                            aVar.f22299d.setText("");
                            break;
                    }
                    if (topicObj.f13100o) {
                        str = StringUtils.SPACE + C6661h6.this.getString(R.string.closed);
                    }
                    if (topicObj.f13110y) {
                        aVar.f22301f.setText(C6661h6.this.getString(R.string.D_people_voted, Integer.valueOf(topicObj.f13109x)) + str);
                    } else if (topicObj.f13109x == 1) {
                        aVar.f22301f.setText(C6661h6.this.getString(R.string.you_voted) + str);
                    } else {
                        aVar.f22301f.setText(C6661h6.this.getString(R.string.you_and_D_others_voted, Integer.valueOf(topicObj.f13109x - 1)) + str);
                    }
                    aVar.f22300e.setText(String.valueOf(CLUtility.m16446H2(this.f22289b, topicObj.m14840e(), false)));
                    C5168n1.m20151h(aVar.f22297b, aVar.f22298c, objArr == true ? aVar.f22299d : null, String.valueOf(topicObj.m14841f()), C6661h6.this.f22282g);
                    return view;
                }
                aVar.f22299d.setVisibility(8);
                aVar.f22299d.setText("");
            }
            objArr = false;
            if (topicObj.f13100o) {
            }
            if (topicObj.f13110y) {
            }
            aVar.f22300e.setText(String.valueOf(CLUtility.m16446H2(this.f22289b, topicObj.m14840e(), false)));
            C5168n1.m20151h(aVar.f22297b, aVar.f22298c, objArr == true ? aVar.f22299d : null, String.valueOf(topicObj.m14841f()), C6661h6.this.f22282g);
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K */
    public /* synthetic */ void m25250K(TopicObj topicObj) {
        this.f22279d.m25299f(topicObj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m25251L(TopicObj topicObj) {
        this.f22279d.m25297d(topicObj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ void m25252M(long j9, boolean z8) {
        this.f22279d.m25296c(j9, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m25253N(TopicObj topicObj) {
        this.f22279d.m25294a(topicObj);
        m25289U();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m25254O(long j9) {
        this.f22279d.m25295b(j9);
        m25289U();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m25255P(TopicObj topicObj) {
        this.f22279d.m25298e(topicObj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ void m25256Q(AdapterView adapterView, View view, int i9, long j9) {
        TopicObj topicObj = this.f22284i.get(i9);
        Intent intent = new Intent(getActivity(), (Class<?>) TopicContentActivity.class);
        intent.putExtra("Group", this.f22283h);
        intent.putExtra("intent_topic", topicObj);
        startActivity(intent);
    }

    /* renamed from: R */
    public static /* synthetic */ void m25257R(AdapterView adapterView, View view, int i9, long j9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S */
    public /* synthetic */ void m25258S(View view) {
        Intent intent = new Intent(getActivity(), (Class<?>) CreateTopicActivity.class);
        intent.putExtra("INTENT_MODE", 1);
        intent.putExtra("Group", this.f22283h);
        startActivity(intent);
    }

    /* renamed from: A */
    public final void m25278A(View view) {
        this.f22278c = (ListView) view.findViewById(R.id.listViewPolls);
        this.f22280e = (ImageView) view.findViewById(R.id.imageViewCreate);
        this.f22281f = (RelativeLayout) view.findViewById(R.id.relativeLayoutIntroPoll);
    }

    /* renamed from: B */
    public final void m25279B() {
        this.f22283h = (Group) getArguments().getParcelable("Group");
    }

    /* renamed from: C */
    public final void m25280C(String str) throws NumberFormatException {
        final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
        if (topicObjM14984n != null && topicObjM14984n.m14843h() == this.f22283h.f13727n) {
            getActivity().runOnUiThread(new Runnable() { // from class: y2.y5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22515b.m25250K(topicObjM14984n);
                }
            });
        }
    }

    /* renamed from: D */
    public void m25281D(String str) throws NumberFormatException {
        final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
        if (topicObjM14984n != null && isAdded()) {
            getActivity().runOnUiThread(new Runnable() { // from class: y2.z5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22527b.m25251L(topicObjM14984n);
                }
            });
        }
    }

    /* renamed from: E */
    public final void m25282E(String str, final boolean z8) throws NumberFormatException {
        final long j9 = Long.parseLong(str);
        getActivity().runOnUiThread(new Runnable() { // from class: y2.g6
            @Override // java.lang.Runnable
            public final void run() {
                this.f22262b.m25252M(j9, z8);
            }
        });
    }

    /* renamed from: F */
    public final void m25283F(String str) throws NumberFormatException {
        final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
        if (topicObjM14984n == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: y2.c6
            @Override // java.lang.Runnable
            public final void run() {
                this.f22208b.m25253N(topicObjM14984n);
            }
        });
    }

    /* renamed from: G */
    public void m25284G(String str) throws NumberFormatException {
        final long j9 = Long.parseLong(str);
        getActivity().runOnUiThread(new Runnable() { // from class: y2.e6
            @Override // java.lang.Runnable
            public final void run() {
                this.f22229b.m25254O(j9);
            }
        });
    }

    /* renamed from: H */
    public void m25285H(String str) throws NumberFormatException {
        final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
        if (topicObjM14984n == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: y2.f6
            @Override // java.lang.Runnable
            public final void run() {
                this.f22247b.m25255P(topicObjM14984n);
            }
        });
    }

    /* renamed from: I */
    public final void m25286I() {
        this.f22279d = new c(getActivity());
        this.f22278c.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: y2.d6
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i9, long j9) {
                this.f22221b.m25256Q(adapterView, view, i9, j9);
            }
        });
    }

    /* renamed from: J */
    public final void m25287J() {
        this.f22278c.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: y2.a6
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i9, long j9) {
                C6661h6.m25257R(adapterView, view, i9, j9);
            }
        });
        this.f22280e.setOnClickListener(new View.OnClickListener() { // from class: y2.b6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f22199b.m25258S(view);
            }
        });
        m25290p();
    }

    /* renamed from: T */
    public final void m25288T(boolean z8) {
        if (z8) {
            this.f22278c.setVisibility(4);
            this.f22281f.setVisibility(0);
        } else {
            this.f22278c.setVisibility(0);
            this.f22281f.setVisibility(4);
        }
    }

    /* renamed from: U */
    public final void m25289U() {
        List<TopicObj> list = this.f22284i;
        if (list == null || list.size() <= 0) {
            m25288T(true);
        } else {
            m25288T(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f22282g = new FriendsClient();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_chat_tab_polls, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        b bVar = this.f22277b;
        if (bVar != null) {
            bVar.cancel(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        C5321e.m20824o().m20832B0(this.f22285j);
        this.f22282g.m15717U0();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        m25279B();
        m25278A(view);
        m25287J();
        m25286I();
        b bVar = new b(this, null);
        this.f22277b = bVar;
        bVar.executeOnExecutor(C6385v.f21554b, new Void[0]);
        C5321e.m20824o().m20875k(this.f22285j);
    }

    /* renamed from: p */
    public final void m25290p() {
        Math.ceil((Globals.m7388i0().getResources().getDisplayMetrics().widthPixels / 1080.0f) * 128.0f * (150 / TsExtractor.TS_STREAM_TYPE_SPLICE_INFO));
    }
}
