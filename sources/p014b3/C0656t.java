package p014b3;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.C2143a;
import com.cyberlink.you.activity.friend.DepartmentMemberAddActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p116k4.C5152i0;
import p116k4.C5168n1;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p116k4.C5187v0;
import p173q2.C6127a;
import p201t3.C6301o;

/* renamed from: b3.t */
/* loaded from: classes.dex */
public class C0656t extends RecyclerView.AbstractC0446g<RecyclerView.AbstractC0442c0> {

    /* renamed from: a */
    public DepartmentMemberAddActivity f3251a;

    /* renamed from: b */
    public List<DepartmentFriend> f3252b;

    /* renamed from: c */
    public FriendsClient f3253c;

    /* renamed from: d */
    public String f3254d;

    /* renamed from: b3.t$a */
    public class a extends RecyclerView.AbstractC0442c0 {

        /* renamed from: b */
        public TextView f3255b;

        /* renamed from: c */
        public TextView f3256c;

        /* renamed from: d */
        public TextView f3257d;

        /* renamed from: e */
        public ImageView f3258e;

        /* renamed from: f */
        public Button f3259f;

        /* renamed from: g */
        public View f3260g;

        public a(View view) {
            super(view);
            m3375c(view);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m3373d(DepartmentFriend departmentFriend, View view) {
            C0656t.this.m3371t(departmentFriend);
        }

        /* renamed from: b */
        public final String m3374b(long j9) {
            String strM15642G = FriendsClient.m15642G("info", C2143a.m12415o(j9) ? "userAvatarHostGCP" : "userAvatarHost");
            if (C5170o0.m20170e(strM15642G)) {
                return null;
            }
            return strM15642G + "/01/" + (j9 % 5203) + "/" + j9 + "/avatar.jpg";
        }

        /* renamed from: c */
        public final void m3375c(View view) {
            this.f3258e = (ImageView) view.findViewById(R.id.DepartmentMemberAvatar);
            this.f3259f = (Button) view.findViewById(R.id.DepartmentMemberAddBtn);
            this.f3255b = (TextView) view.findViewById(R.id.DepartmentDirectoryDisplayName);
            this.f3256c = (TextView) view.findViewById(R.id.DepartmentMemberDisplayName);
            this.f3257d = (TextView) view.findViewById(R.id.DepartmentDirectoryCount);
            this.f3260g = view.findViewById(R.id.DepartmentMemberHintIcon);
        }

        /* renamed from: e */
        public void m3376e(final DepartmentFriend departmentFriend) {
            if (departmentFriend.f13641q) {
                this.f3258e.setVisibility(4);
                this.f3255b.setVisibility(0);
                this.f3256c.setVisibility(8);
                this.f3259f.setVisibility(8);
                this.f3257d.setVisibility(0);
                this.f3260g.setVisibility(0);
                this.f3255b.setText(departmentFriend.f13629e);
                this.f3257d.setText("(" + departmentFriend.f13642r + ")");
                return;
            }
            this.f3258e.setVisibility(0);
            this.f3255b.setVisibility(8);
            this.f3256c.setVisibility(0);
            this.f3259f.setVisibility(0);
            this.f3257d.setVisibility(8);
            this.f3260g.setVisibility(8);
            this.f3256c.setText(C5170o0.m20170e(departmentFriend.f13629e) ? departmentFriend.f13630f : departmentFriend.f13629e);
            if (departmentFriend.f13626b > 0) {
                Friend friendM15003C = C2950b0.m14899A().m15003C(Long.toString(departmentFriend.f13626b));
                if (friendM15003C == null || !C5168n1.m20149f(friendM15003C.f13645c)) {
                    C6127a.m23474o(C0656t.this.f3251a, this.f3258e, m3374b(departmentFriend.f13626b), R.drawable.pic_default);
                } else {
                    C6127a.m23469j(C0656t.this.f3251a, this.f3258e, friendM15003C);
                }
            } else {
                C6127a.m23468i(C0656t.this.f3251a, this.f3258e, departmentFriend);
            }
            if (departmentFriend.f13626b == Globals.m7388i0().m7568k1().longValue() || departmentFriend.f13637m || departmentFriend.f13626b <= 0) {
                this.f3259f.setVisibility(8);
            } else {
                this.f3259f.setVisibility(0);
                this.f3259f.setOnClickListener(new View.OnClickListener() { // from class: b3.s
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f3245b.m3373d(departmentFriend, view);
                    }
                });
            }
        }
    }

    public C0656t(DepartmentMemberAddActivity departmentMemberAddActivity, FriendsClient friendsClient, List<DepartmentFriend> list, String str) {
        this.f3251a = departmentMemberAddActivity;
        this.f3253c = friendsClient;
        this.f3252b = list;
        this.f3254d = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ void m3362n(final DepartmentFriend departmentFriend, ProgressDialog progressDialog, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            C5187v0.m20267c(R.string.success);
            this.f3251a.runOnUiThread(new Runnable() { // from class: b3.r
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3242b.m3361m(departmentFriend);
                }
            });
        }
        C5152i0.m20065b(progressDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m3363o(DepartmentFriend departmentFriend, View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        C2143a.m12410j(this.f3251a, departmentFriend, this.f3254d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public /* synthetic */ void m3364p(EditText editText, DepartmentFriend departmentFriend, Dialog dialog, View view) {
        String string = editText.getText().toString();
        Globals.m7388i0().m7462N3(string);
        m3369l(departmentFriend, string);
        dialog.dismiss();
        CLUtility.m16589t1(this.f3251a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ void m3365q(Dialog dialog, View view) {
        dialog.dismiss();
        CLUtility.m16589t1(this.f3251a);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public int getItemCount() {
        return this.f3252b.size();
    }

    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public void m3361m(DepartmentFriend departmentFriend) {
        Iterator<DepartmentFriend> it = this.f3252b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DepartmentFriend next = it.next();
            if (next.f13626b == departmentFriend.f13626b) {
                next.f13635k = true;
                break;
            }
        }
        Globals.m7388i0().m7401A3(true);
    }

    /* renamed from: k */
    public void m3368k(DepartmentFriend departmentFriend) {
        Iterator<DepartmentFriend> it = this.f3252b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DepartmentFriend next = it.next();
            if (next.f13626b == departmentFriend.f13626b) {
                next.f13635k = false;
                break;
            }
        }
        Globals.m7388i0().m7401A3(true);
    }

    /* renamed from: l */
    public final void m3369l(final DepartmentFriend departmentFriend, String str) {
        DepartmentMemberAddActivity departmentMemberAddActivity = this.f3251a;
        final ProgressDialog progressDialogShow = ProgressDialog.show(departmentMemberAddActivity, "", departmentMemberAddActivity.getString(R.string.processing));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteeId", String.valueOf(departmentFriend.f13626b)));
        arrayList.add(new C6301o("message", str));
        this.f3253c.m15734m("invite", "inviteFriend", arrayList, new FriendsClient.InterfaceC3051i() { // from class: b3.q
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f3236a.m3362n(departmentFriend, progressDialogShow, str2, str3, str4, str5);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public void onBindViewHolder(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9) {
        final DepartmentFriend departmentFriend = this.f3252b.get(i9);
        abstractC0442c0.itemView.setOnClickListener(new View.OnClickListener() { // from class: b3.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3214b.m3363o(departmentFriend, view);
            }
        });
        ((a) abstractC0442c0).m3376e(this.f3252b.get(i9));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public RecyclerView.AbstractC0442c0 onCreateViewHolder(ViewGroup viewGroup, int i9) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_department_member_list_item, viewGroup, false));
    }

    /* renamed from: s */
    public void m3370s(List<DepartmentFriend> list) {
        this.f3252b = list;
    }

    /* renamed from: t */
    public void m3371t(final DepartmentFriend departmentFriend) {
        if (departmentFriend.f13626b <= 0) {
            if (C5170o0.m20170e(departmentFriend.f13638n)) {
                C5187v0.m20267c(R.string.error_server_response);
                return;
            } else {
                CLUtility.m16418A2(this.f3251a, null, CLUtility.InviteType.EMail, new String[]{departmentFriend.f13638n});
                return;
            }
        }
        final Dialog dialogM16384c = C3123g.m16384c(this.f3251a);
        View viewInflate = LayoutInflater.from(this.f3251a).inflate(R.layout.dialog_friend_invitation, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.invitationDialogTitle);
        final EditText editText = (EditText) viewInflate.findViewById(R.id.invitationEdit);
        textView.setText(this.f3251a.getString(R.string.friend_invitation_info, departmentFriend.f13629e));
        editText.setText(Globals.m7388i0().m7561j0());
        viewInflate.findViewById(R.id.sendInvitationBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3221b.m3364p(editText, departmentFriend, dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.cancelinvitationBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3227b.m3365q(dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.invitationXImage).setOnClickListener(new View.OnClickListener() { // from class: b3.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText("");
            }
        });
        dialogM16384c.setContentView(viewInflate);
        CLUtility.m16590t2(this.f3251a, dialogM16384c);
        dialogM16384c.show();
    }
}
