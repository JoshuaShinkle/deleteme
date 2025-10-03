package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p173q2.C6127a;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class BlockUserActivity extends BaseActivity {

    /* renamed from: c */
    public ListView f7386c;

    /* renamed from: d */
    public ArrayAdapter<Friend> f7387d;

    /* renamed from: e */
    public FriendsClient f7388e;

    /* renamed from: f */
    public View.OnClickListener f7389f = new ViewOnClickListenerC1419a();

    /* renamed from: com.cyberlink.you.activity.BlockUserActivity$a */
    public class ViewOnClickListenerC1419a implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.BlockUserActivity$a$a */
        public class a implements View.OnClickListener {

            /* renamed from: b */
            public final /* synthetic */ Dialog f7391b;

            /* renamed from: c */
            public final /* synthetic */ Friend f7392c;

            public a(Dialog dialog, Friend friend) {
                this.f7391b = dialog;
                this.f7392c = friend;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f7391b.dismiss();
                BlockUserActivity.this.m7776q(this.f7392c);
            }
        }

        /* renamed from: com.cyberlink.you.activity.BlockUserActivity$a$b */
        public class b implements View.OnClickListener {

            /* renamed from: b */
            public final /* synthetic */ Dialog f7394b;

            /* renamed from: c */
            public final /* synthetic */ Friend f7395c;

            public b(Dialog dialog, Friend friend) {
                this.f7394b = dialog;
                this.f7395c = friend;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.f7394b.dismiss();
                BlockUserActivity.this.m7777r(this.f7395c);
            }
        }

        public ViewOnClickListenerC1419a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Friend friend = (Friend) BlockUserActivity.this.f7387d.getItem(((Integer) view.getTag()).intValue());
            Dialog dialogM16384c = C3123g.m16384c(BlockUserActivity.this);
            dialogM16384c.setContentView(R.layout.dialog_edit_blocked_friends);
            ((TextView) dialogM16384c.findViewById(R.id.friend_name)).setText(friend.m15621b());
            dialogM16384c.findViewById(R.id.unblock_click).setOnClickListener(new a(dialogM16384c, friend));
            dialogM16384c.findViewById(R.id.unfriend_click).setOnClickListener(new b(dialogM16384c, friend));
            dialogM16384c.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserActivity$b */
    public class DialogInterfaceOnClickListenerC1420b implements DialogInterface.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ Friend f7397b;

        public DialogInterfaceOnClickListenerC1420b(Friend friend) {
            this.f7397b = friend;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            BlockUserActivity.this.m7780v(this.f7397b);
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserActivity$c */
    public class DialogInterfaceOnClickListenerC1421c implements DialogInterface.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ Friend f7399b;

        /* renamed from: com.cyberlink.you.activity.BlockUserActivity$c$a */
        public class a extends AsyncTask<Void, Void, Boolean> {
            public a() {
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) {
                Thread.currentThread().setName("checkDialog.findViewById AsyncTask");
                DialogInterfaceOnClickListenerC1421c dialogInterfaceOnClickListenerC1421c = DialogInterfaceOnClickListenerC1421c.this;
                return BlockUserActivity.this.m7781w(String.valueOf(dialogInterfaceOnClickListenerC1421c.f7399b.f13645c));
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    C2950b0.m14899A().m15022n(String.valueOf(DialogInterfaceOnClickListenerC1421c.this.f7399b.f13645c));
                    BlockUserActivity.this.f7387d.remove(DialogInterfaceOnClickListenerC1421c.this.f7399b);
                }
            }
        }

        public DialogInterfaceOnClickListenerC1421c(Friend friend) {
            this.f7399b = friend;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            new a().executeOnExecutor(C6385v.f21553a, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserActivity$d */
    public class C1422d extends ArrayAdapter<Friend> {
        public C1422d(Context context, int i9, List list) {
            super(context, i9, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            View view2;
            C1424f c1424f;
            if (view == null) {
                c1424f = BlockUserActivity.this.new C1424f(viewGroup);
                view2 = c1424f.f7409d;
                view2.setTag(c1424f);
            } else {
                view2 = view;
                c1424f = (C1424f) view.getTag();
            }
            c1424f.f7408c.setTag(Integer.valueOf(i9));
            Friend friend = (Friend) getItem(i9);
            if (friend != null) {
                c1424f.f7407b.setText(friend.m15621b());
            }
            C6127a.m23469j(BlockUserActivity.this, c1424f.f7406a, friend);
            return view2;
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserActivity$e */
    public class C1423e implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public final /* synthetic */ Friend f7403a;

        /* renamed from: com.cyberlink.you.activity.BlockUserActivity$e$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BlockUserActivity.this.f7387d.remove(C1423e.this.f7403a);
            }
        }

        public C1423e(Friend friend) {
            this.f7403a = friend;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            if (!"200".equals(str3)) {
                Log.d("BlockUserActivity", "Block Friend Fail");
                return;
            }
            Log.d("BlockUserActivity", "Block Friend success");
            Friend friend = this.f7403a;
            friend.f13655m = false;
            friend.f13654l = false;
            C2950b0.m14899A().m15008H(Long.toString(this.f7403a.f13645c), this.f7403a, true);
            Group groupM15081r = C2950b0.m14912k().m15081r(this.f7403a.f13648f);
            if (groupM15081r != null) {
                groupM15081r.f13732s = false;
                C2950b0.m14912k().m15062A(String.valueOf(groupM15081r.f13727n), groupM15081r, "isDisabled");
            }
            BlockUserActivity.this.runOnUiThread(new a());
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserActivity$f */
    public class C1424f {

        /* renamed from: a */
        public ImageView f7406a;

        /* renamed from: b */
        public TextView f7407b;

        /* renamed from: c */
        public Button f7408c;

        /* renamed from: d */
        public View f7409d;

        public C1424f(ViewGroup viewGroup) {
            View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_setting_block_user, viewGroup, false);
            this.f7409d = viewInflate;
            this.f7407b = (TextView) viewInflate.findViewById(R.id.BlockUserDisplayName);
            this.f7406a = (ImageView) this.f7409d.findViewById(R.id.BlockUserAvatar);
            Button button = (Button) this.f7409d.findViewById(R.id.BlockUserEditBtn);
            this.f7408c = button;
            button.setOnClickListener(BlockUserActivity.this.f7389f);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        setResult(-1, null);
        finish();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_block_user);
        this.f7388e = new FriendsClient();
        m7779u();
        m7778s();
    }

    /* renamed from: q */
    public final void m7776q(Friend friend) {
        if (friend == null) {
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.dialog_unblock_friend_title));
        builderM16382a.setMessage(getString(R.string.dialog_unblock_friend_msg));
        builderM16382a.setPositiveButton(getString(R.string.confirm_btn), new DialogInterfaceOnClickListenerC1420b(friend));
        builderM16382a.setNegativeButton(getString(R.string.cancel_text), (DialogInterface.OnClickListener) null);
        builderM16382a.show();
    }

    /* renamed from: r */
    public final void m7777r(Friend friend) {
        if (friend == null) {
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.dialog_remove_friend_title));
        builderM16382a.setMessage(getString(R.string.dialog_remove_friend_msg, friend.m15621b(), friend.m15621b()));
        builderM16382a.setPositiveButton(getString(R.string.confirm_btn), new DialogInterfaceOnClickListenerC1421c(friend));
        builderM16382a.setNegativeButton(getString(R.string.cancel_text), (DialogInterface.OnClickListener) null);
        builderM16382a.show();
    }

    /* renamed from: s */
    public final void m7778s() {
        List<Friend> listM15034z = C2950b0.m14899A().m15034z();
        Collections.sort(listM15034z, new Friend.C3041b());
        C1422d c1422d = new C1422d(this, R.layout.view_item_friends_group_edit, listM15034z);
        this.f7387d = c1422d;
        this.f7386c.setAdapter((ListAdapter) c1422d);
    }

    /* renamed from: u */
    public final void m7779u() {
        this.f7386c = (ListView) findViewById(R.id.BlockUserActivityFreindsListView);
        m7365g(R.id.BlockUserActivityBackBtn);
    }

    /* renamed from: v */
    public final void m7780v(Friend friend) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", String.valueOf(friend.f13645c)));
        this.f7388e.m15734m("friend", "unblockFriend", arrayList, new C1423e(friend));
    }

    /* renamed from: w */
    public final Boolean m7781w(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", str));
        return Boolean.valueOf("200".equals((String) this.f7388e.m15731j("friend", "delete", arrayList).first));
    }
}
