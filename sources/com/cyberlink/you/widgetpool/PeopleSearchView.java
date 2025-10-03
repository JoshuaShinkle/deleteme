package com.cyberlink.you.widgetpool;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.utility.ULogUtility;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.OrgContactList;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p201t3.C6288b;
import p201t3.C6301o;
import p209u2.AbstractC6381r;

/* loaded from: classes.dex */
public class PeopleSearchView extends LinearLayout {

    /* renamed from: h */
    public static final String f14809h = "PeopleSearchView";

    /* renamed from: b */
    public FriendsClient f14810b;

    /* renamed from: c */
    public InterfaceC3209g f14811c;

    /* renamed from: d */
    public String f14812d;

    /* renamed from: e */
    public int f14813e;

    /* renamed from: f */
    public List<DepartmentFriend> f14814f;

    /* renamed from: g */
    public C6288b.h f14815g;

    /* renamed from: com.cyberlink.you.widgetpool.PeopleSearchView$a */
    public class C3203a implements TextWatcher {

        /* renamed from: b */
        public final /* synthetic */ ImageView f14816b;

        public C3203a(ImageView imageView) {
            this.f14816b = imageView;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (PeopleSearchView.this.f14811c != null) {
                PeopleSearchView.this.f14811c.mo12240e();
            }
            String string = charSequence.toString();
            PeopleSearchView.this.f14812d = string;
            if (string.length() >= 2) {
                if (PeopleSearchView.this.f14811c != null) {
                    PeopleSearchView.this.f14811c.mo12237b(string);
                }
                if (PeopleSearchView.this.f14810b == null) {
                    throw new NullPointerException("should set FriendsClient before search!!");
                }
                PeopleSearchView.this.m17076l(string);
            } else if (PeopleSearchView.this.f14811c != null) {
                PeopleSearchView.this.f14811c.onCancel();
            }
            if (string.length() > 0) {
                this.f14816b.setVisibility(0);
            } else {
                this.f14816b.setVisibility(8);
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.PeopleSearchView$b */
    public class C3204b extends AbstractC6381r<Void, Integer> {

        /* renamed from: c */
        public final /* synthetic */ String f14818c;

        /* renamed from: d */
        public final /* synthetic */ List f14819d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C3204b(Handler handler, String str, List list) {
            super(handler);
            this.f14818c = str;
            this.f14819d = list;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r72) {
            if (PeopleSearchView.this.f14812d.equals(this.f14818c)) {
                for (OrgContactList.OrgContacts.ContactInfo contactInfo : this.f14819d) {
                    if (!contactInfo.hidden.booleanValue()) {
                        Boolean bool = Boolean.FALSE;
                        for (DepartmentFriend departmentFriend : PeopleSearchView.this.f14814f) {
                            if (contactInfo.displayName.equals(departmentFriend.f13630f) && contactInfo.email.equals(departmentFriend.f13638n)) {
                                bool = Boolean.TRUE;
                            }
                        }
                        if (!bool.booleanValue()) {
                            DepartmentFriend departmentFriend2 = new DepartmentFriend();
                            Long l9 = contactInfo.uid;
                            if (l9 != null) {
                                String string = Long.toString(l9.longValue());
                                if (string == null) {
                                    string = "-1";
                                }
                                departmentFriend2.f13626b = Long.valueOf(string).longValue();
                            }
                            departmentFriend2.f13630f = contactInfo.displayName;
                            departmentFriend2.f13631g = contactInfo.avatar;
                            departmentFriend2.f13638n = contactInfo.email;
                            departmentFriend2.f13641q = false;
                            PeopleSearchView.this.f14814f.add(departmentFriend2);
                        }
                    }
                }
                if (PeopleSearchView.this.f14811c != null) {
                    InterfaceC3209g interfaceC3209g = PeopleSearchView.this.f14811c;
                    PeopleSearchView peopleSearchView = PeopleSearchView.this;
                    interfaceC3209g.mo12238c(peopleSearchView.m17081s(peopleSearchView.f14814f));
                }
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            if (num.intValue() == NetworkManager.NetworkErrorCode.E_NOT_INITIALIZED.getValue()) {
                ULogUtility.m16670f(PeopleSearchView.f14809h, "[searchOrgContacts] api does not initialize");
                return;
            }
            ULogUtility.m16670f(PeopleSearchView.f14809h, "[searchOrgContacts] error : " + num);
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.PeopleSearchView$c */
    public class C3205c extends PromisedTask.AbstractC4504d<OrgContactList> {

        /* renamed from: a */
        public final /* synthetic */ int f14821a;

        /* renamed from: b */
        public final /* synthetic */ List f14822b;

        /* renamed from: c */
        public final /* synthetic */ String f14823c;

        /* renamed from: d */
        public final /* synthetic */ String f14824d;

        /* renamed from: e */
        public final /* synthetic */ String f14825e;

        /* renamed from: f */
        public final /* synthetic */ AbstractC6381r f14826f;

        public C3205c(int i9, List list, String str, String str2, String str3, AbstractC6381r abstractC6381r) {
            this.f14821a = i9;
            this.f14822b = list;
            this.f14823c = str;
            this.f14824d = str2;
            this.f14825e = str3;
            this.f14826f = abstractC6381r;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(OrgContactList orgContactList) {
            List<OrgContactList.OrgContacts.ContactInfo> list;
            ULogUtility.m16670f(PeopleSearchView.f14809h, "test");
            OrgContactList.OrgContacts orgContacts = orgContactList.contacts;
            if (orgContacts != null && (this.f14821a + 1) * 50 < orgContacts.totalSize) {
                this.f14822b.addAll(orgContacts.results);
                PeopleSearchView.m17075p(this.f14823c, this.f14824d, this.f14825e, this.f14822b, this.f14821a + 1, this.f14826f);
                return;
            }
            if (orgContacts != null && (list = orgContacts.results) != null && list.size() > 0) {
                this.f14822b.addAll(orgContactList.contacts.results);
            }
            this.f14826f.m24505c();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            this.f14826f.m24508f(Integer.valueOf(i9));
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.PeopleSearchView$d */
    public class C3206d implements C6288b.h {
        public C3206d() {
        }

        @Override // p201t3.C6288b.h
        public void onError(String str) {
            if (str != null) {
                Log.e(PeopleSearchView.f14809h, str);
            }
            if (PeopleSearchView.this.f14811c != null) {
                PeopleSearchView.this.f14811c.onCancel();
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.PeopleSearchView$e */
    public class C3207e implements C6288b.i {
        public C3207e() {
        }

        @Override // p201t3.C6288b.i
        /* renamed from: a */
        public void mo17085a() {
            if (PeopleSearchView.this.f14811c != null) {
                PeopleSearchView.this.f14811c.mo12236a();
            }
        }

        public /* synthetic */ C3207e(PeopleSearchView peopleSearchView, C3203a c3203a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.PeopleSearchView$f */
    public class C3208f implements C6288b.d<List<SuggestionFriend>> {

        /* renamed from: a */
        public String f14829a;

        public C3208f(String str) {
            this.f14829a = str;
        }

        @Override // p201t3.C6288b.d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(List<SuggestionFriend> list) {
            if (PeopleSearchView.this.f14812d.equals(this.f14829a)) {
                if (PeopleSearchView.this.f14811c != null) {
                    PeopleSearchView.this.f14811c.mo12239d(list);
                }
                if (list.size() == 20) {
                    PeopleSearchView.this.m17079q();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.PeopleSearchView$g */
    public interface InterfaceC3209g {
        /* renamed from: a */
        void mo12236a();

        /* renamed from: b */
        void mo12237b(String str);

        /* renamed from: c */
        void mo12238c(List<DepartmentFriend> list);

        /* renamed from: d */
        void mo12239d(List<SuggestionFriend> list);

        /* renamed from: e */
        void mo12240e();

        void onCancel();
    }

    public PeopleSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14812d = "";
        this.f14813e = 1;
        this.f14814f = new ArrayList();
        this.f14815g = new C3206d();
        m17078n(context);
    }

    /* renamed from: p */
    public static void m17075p(String str, String str2, String str3, List<OrgContactList.OrgContacts.ContactInfo> list, int i9, AbstractC6381r<Void, Integer> abstractC6381r) {
        NetworkLive.searchOrgContacts(str, str2, null, i9, 50).done(new C3205c(i9, list, str, str2, str3, abstractC6381r));
    }

    /* renamed from: l */
    public final void m17076l(String str) {
        this.f14813e = 1;
        m17080r(str);
        m17077m(str, this.f14813e).m24088p();
    }

    /* renamed from: m */
    public final C6288b<SuggestionFriend> m17077m(String str, int i9) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("keyword", str));
        arrayList.add(new C6301o("pageIndex", Integer.toString(i9)));
        return new C6288b.c("friend", "peopleSearch", arrayList, SuggestionFriend.class).m24100e().m24101f(new C3207e(this, null)).m24097b(new C3208f(str)).m24099d(this.f14815g).m24096a();
    }

    /* renamed from: n */
    public final void m17078n(Context context) {
        LayoutInflater.from(context).inflate(R.layout.search_edit_people, (ViewGroup) this, true);
        final EditText editText = (EditText) findViewById(R.id.SearchEditText);
        ImageView imageView = (ImageView) findViewById(R.id.btn_icon_cancel);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: n4.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText("");
            }
        });
        editText.addTextChangedListener(new C3203a(imageView));
    }

    /* renamed from: q */
    public final void m17079q() {
        int i9 = this.f14813e + 1;
        this.f14813e = i9;
        m17077m(this.f14812d, i9).m24088p();
    }

    /* renamed from: r */
    public final void m17080r(String str) {
        String strM7506X = Globals.m7388i0().m7506X();
        ArrayList arrayList = new ArrayList();
        this.f14814f.clear();
        m17075p(strM7506X, str, null, arrayList, 0, new C3204b(new Handler(Looper.getMainLooper()), str, arrayList));
    }

    /* renamed from: s */
    public final List<DepartmentFriend> m17081s(List<DepartmentFriend> list) {
        for (Friend friend : C2950b0.m14899A().m15024p()) {
            Iterator<DepartmentFriend> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    DepartmentFriend next = it.next();
                    if (next.f13626b == friend.f13645c) {
                        next.f13637m = true;
                        break;
                    }
                }
            }
        }
        return list;
    }

    public void setFriendsClient(FriendsClient friendsClient) {
        this.f14810b = friendsClient;
    }

    public void setUpdateListListener(InterfaceC3209g interfaceC3209g) {
        this.f14811c = interfaceC3209g;
    }
}
