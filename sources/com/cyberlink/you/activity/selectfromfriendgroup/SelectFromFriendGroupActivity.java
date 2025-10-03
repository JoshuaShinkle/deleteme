package com.cyberlink.you.activity.selectfromfriendgroup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TabHost;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupJoinLinkActivity;
import com.cyberlink.you.activity.selectfromfriendgroup.C2455d;
import com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.common.CLFragmentTabHost;
import com.cyberlink.you.widgetpool.tokenautocomplete.PeopleCompleteView;
import com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p116k4.C5179r0;

/* loaded from: classes.dex */
public class SelectFromFriendGroupActivity extends BaseFragmentActivity {

    /* renamed from: c */
    public List<Friend> f11177c;

    /* renamed from: d */
    public List<Friend> f11178d;

    /* renamed from: f */
    public Button f11180f;

    /* renamed from: g */
    public PeopleCompleteView f11181g;

    /* renamed from: e */
    public CLFragmentTabHost f11179e = null;

    /* renamed from: h */
    public Set<Friend> f11182h = new HashSet();

    /* renamed from: i */
    public C2455d f11183i = new C2455d();

    /* renamed from: j */
    public Group f11184j = null;

    /* renamed from: k */
    public String f11185k = "";

    /* renamed from: l */
    public int f11186l = -1;

    /* renamed from: m */
    public View.OnClickListener f11187m = new View.OnClickListener() { // from class: d3.f
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16356b.m12540i1(view);
        }
    };

    /* renamed from: n */
    public C2455d.a f11188n = new C2455d.a() { // from class: d3.g
        @Override // com.cyberlink.you.activity.selectfromfriendgroup.C2455d.a
        /* renamed from: a */
        public final void mo12617a(List list) {
            this.f16357a.m12541j1(list);
        }
    };

    /* renamed from: o */
    public View.OnClickListener f11189o = new ViewOnClickListenerC2450a();

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity$a */
    public class ViewOnClickListenerC2450a implements View.OnClickListener {
        public ViewOnClickListenerC2450a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m12560b() {
            CLUtility.m16589t1(SelectFromFriendGroupActivity.this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int size;
            SelectFromFriendGroupActivity.this.runOnUiThread(new Runnable() { // from class: d3.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16360b.m12560b();
                }
            });
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            ArrayList arrayList = new ArrayList();
            Iterator it = SelectFromFriendGroupActivity.this.f11182h.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Friend friend = (Friend) it.next();
                if (SelectFromFriendGroupActivity.this.f11177c != null && SelectFromFriendGroupActivity.this.f11177c.contains(friend)) {
                    size = 1;
                }
                if (friend != null && size == 0) {
                    arrayList.add(friend);
                }
            }
            if (SelectFromFriendGroupActivity.this.f11184j == null) {
                for (Friend friend2 : SelectFromFriendGroupActivity.this.f11178d) {
                    if (Globals.m7388i0().m7568k1().longValue() == friend2.f13645c) {
                        arrayList.add(friend2);
                    }
                }
            }
            size = SelectFromFriendGroupActivity.this.f11177c != null ? SelectFromFriendGroupActivity.this.f11177c.size() : 0;
            if (SelectFromFriendGroupActivity.this.f11186l != -1 && arrayList.size() + size > SelectFromFriendGroupActivity.this.f11186l) {
                SelectFromFriendGroupActivity selectFromFriendGroupActivity = SelectFromFriendGroupActivity.this;
                selectFromFriendGroupActivity.m12557q1(selectFromFriendGroupActivity.f11186l);
            } else {
                Globals.C1408b.m7655b().m7657d("INTENT_RESULT_SELECTED_USERS_LIST", arrayList);
                intent.putExtras(bundle);
                SelectFromFriendGroupActivity.this.setResult(-1, intent);
                SelectFromFriendGroupActivity.this.finish();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity$b */
    public class C2451b implements TokenCompleteTextView.InterfaceC3249e<Friend> {
        public C2451b() {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: a */
        public void mo5801a(String str) {
            SelectFromFriendGroupActivity.this.m12555o1(str);
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void mo5802b(Friend friend) {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void mo5803c(Friend friend) {
            SelectFromFriendGroupActivity.this.f11183i.m12616f(false, friend);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h1 */
    public /* synthetic */ void m12539h1(Group group, View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", group);
        Intent intent = new Intent(getApplicationContext(), (Class<?>) GroupJoinLinkActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i1 */
    public /* synthetic */ void m12540i1(View view) {
        CLUtility.m16589t1(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j1 */
    public /* synthetic */ void m12541j1(List list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (((Boolean) pair.second).booleanValue()) {
                arrayList.add((Friend) pair.first);
            } else {
                arrayList2.add((Friend) pair.first);
            }
        }
        if (arrayList.size() > 0) {
            m12545Y0(arrayList);
        }
        if (arrayList2.size() > 0) {
            m12554n1(arrayList2);
        }
        m12558r1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k1 */
    public /* synthetic */ boolean m12542k1(TextView textView, int i9, KeyEvent keyEvent) {
        CLUtility.m16589t1(this);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l1 */
    public /* synthetic */ void m12543l1(String str) {
        m12556p1();
    }

    /* renamed from: m1 */
    public static /* synthetic */ void m12544m1(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: Y0 */
    public final void m12545Y0(List<Friend> list) {
        this.f11182h.addAll(list);
        this.f11181g.m17436r(list, null);
    }

    /* renamed from: Z0 */
    public final View m12546Z0(String str) {
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        View viewInflate = getLayoutInflater().inflate(R.layout.chat_album_fragment_selection, (ViewGroup) this.f11179e, false);
        ((TextView) viewInflate.findViewById(R.id.chat_album_fragment_item_text)).setText(str);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams((int) (r0.widthPixels / 2), (int) ((r0 * 0.19444445f) / 1.0d)));
        return viewInflate;
    }

    /* renamed from: a1 */
    public final Filter m12547a1() {
        AbstractC2456e abstractC2456e = (AbstractC2456e) getSupportFragmentManager().mo1848e(this.f11179e.getCurrentTabTag());
        if (abstractC2456e == null) {
            return null;
        }
        return abstractC2456e.mo12570g();
    }

    /* renamed from: b1 */
    public final void m12548b1() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f11178d = Globals.C1408b.m7655b().m7656c("INTENT_PREV_SELECTED_USERS");
            this.f11177c = Globals.C1408b.m7655b().m7656c("INTENT_CANNOT_MODIFIED_USERS_LIST");
            m12552f1();
            Group group = (Group) extras.getParcelable("Group");
            this.f11184j = group;
            m12553g1(group);
            if (extras.getBoolean("invitationOnly", false)) {
                ((TextView) findViewById(R.id.title)).setText(getString(R.string.invited_people_list));
            }
            if (extras.getBoolean("scheduleHost", false)) {
                ((TextView) findViewById(R.id.title)).setText(getString(R.string.host_people_list));
            }
            this.f11186l = extras.getInt("selectLimit", -1);
            this.f11185k = extras.getString("page", "");
        }
    }

    /* renamed from: c1 */
    public C2455d m12549c1() {
        return this.f11183i;
    }

    /* renamed from: d1 */
    public List<Friend> m12550d1() {
        return this.f11178d;
    }

    /* renamed from: e1 */
    public List<Friend> m12551e1() {
        return this.f11177c;
    }

    /* renamed from: f1 */
    public final void m12552f1() {
        List<Friend> list;
        if (this.f11178d != null) {
            long jLongValue = Globals.m7388i0().m7568k1().longValue();
            for (Friend friend : this.f11178d) {
                if (friend.f13645c != jLongValue && ((list = this.f11177c) == null || !list.contains(friend))) {
                    this.f11183i.m12616f(true, friend);
                }
            }
        }
    }

    /* renamed from: g1 */
    public final void m12553g1(final Group group) {
        if (group == null) {
            return;
        }
        if (("Community".equals(group.f13705D) && !group.f13704C) || group.m15750g() || "ChatRoom".equals(group.f13716c)) {
            return;
        }
        View viewFindViewById = findViewById(R.id.groupQrCodeInviteLayout);
        viewFindViewById.setVisibility(0);
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: d3.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f16358b.m12539h1(group, view);
            }
        });
        TextView textView = (TextView) findViewById(R.id.groupQrCodeInviteText);
        C5179r0.m20247b(textView, 1);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
    }

    /* renamed from: n1 */
    public final void m12554n1(List<Friend> list) {
        this.f11182h.removeAll(list);
        this.f11181g.m17427T(list);
    }

    /* renamed from: o1 */
    public final void m12555o1(String str) {
        Filter filterM12547a1 = m12547a1();
        if (filterM12547a1 != null) {
            filterM12547a1.filter(str);
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_select_from_friendgroup);
        this.f11183i.m12613c(this.f11188n);
        C5179r0.m20247b((TextView) findViewById(R.id.title), 1);
        findViewById(R.id.imageViewBackBtn).setOnClickListener(this.f11187m);
        Button button = (Button) findViewById(R.id.buttonDone);
        this.f11180f = button;
        C5179r0.m20247b(button, 1);
        this.f11180f.setOnClickListener(this.f11189o);
        CLFragmentTabHost cLFragmentTabHost = (CLFragmentTabHost) findViewById(R.id.tabhost);
        this.f11179e = cLFragmentTabHost;
        cLFragmentTabHost.m17293g(this, getSupportFragmentManager(), R.id.realtabcontent);
        PeopleCompleteView peopleCompleteView = (PeopleCompleteView) findViewById(R.id.token_complete);
        this.f11181g = peopleCompleteView;
        peopleCompleteView.setTokenListener(new C2451b());
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.getBoolean("scheduleHost", false)) {
            this.f11181g.setPrefix(getString(R.string.group_add_member_search_hint));
        } else {
            this.f11181g.setPrefix(getString(R.string.host_people_prefix));
        }
        this.f11181g.requestFocus();
        this.f11181g.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: d3.d
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
                return this.f16354b.m12542k1(textView, i9, keyEvent);
            }
        });
        m12548b1();
        m12558r1();
        if (extras == null) {
            extras = new Bundle();
        }
        Group group = this.f11184j;
        if (group != null) {
            extras.putParcelable("group", group);
        }
        CLFragmentTabHost cLFragmentTabHost2 = this.f11179e;
        cLFragmentTabHost2.m17287a(cLFragmentTabHost2.newTabSpec("FriendFragment").setIndicator(m12546Z0(getString(R.string.friend))), C2452a.class, extras);
        CLFragmentTabHost cLFragmentTabHost3 = this.f11179e;
        cLFragmentTabHost3.m17287a(cLFragmentTabHost3.newTabSpec("Group").setIndicator(m12546Z0(getString(R.string.group))), C2454c.class, extras);
        this.f11179e.setOnTabChangedListener(new TabHost.OnTabChangeListener() { // from class: d3.e
            @Override // android.widget.TabHost.OnTabChangeListener
            public final void onTabChanged(String str) {
                this.f16355b.m12543l1(str);
            }
        });
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f11183i.m12614d();
    }

    /* renamed from: p1 */
    public void m12556p1() {
        m12555o1(this.f11181g.getFilterSearchText());
    }

    /* renamed from: q1 */
    public final void m12557q1(int i9) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.select_members_error, Integer.valueOf(i9)));
        builderM16382a.setPositiveButton(getString(R.string.bc_dialog_button_ok), new DialogInterface.OnClickListener() { // from class: d3.i
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                SelectFromFriendGroupActivity.m12544m1(dialogInterface, i10);
            }
        });
        builderM16382a.show();
    }

    /* renamed from: r1 */
    public final void m12558r1() {
        if (this.f11182h.size() > 0) {
            this.f11180f.setText(getString(R.string.add_btn) + " (" + this.f11182h.size() + ")");
            this.f11180f.setEnabled(true);
        } else if ("scheduleMeetingPage".equals(this.f11185k)) {
            this.f11180f.setText(R.string.done_btn);
            this.f11180f.setEnabled(true);
        } else {
            this.f11180f.setText(R.string.add_btn);
            this.f11180f.setEnabled(false);
        }
        C5179r0.m20247b(this.f11180f, 1);
    }
}
