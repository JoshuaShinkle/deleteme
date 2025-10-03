package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupInfoActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5154j;
import p116k4.C5172p;
import p116k4.C5179r0;
import p136m3.C5321e;
import p173q2.C6127a;
import p173q2.C6129c;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class GroupInfoActivity extends BaseActivity {

    /* renamed from: A */
    public ArrayList<Friend> f7814A;

    /* renamed from: C */
    public int f7816C;

    /* renamed from: G */
    public AsyncTask<Void, ArrayList<Friend>, ArrayList<Friend>> f7820G;

    /* renamed from: H */
    public boolean f7821H;

    /* renamed from: c */
    public ImageView f7823c;

    /* renamed from: d */
    public TextView f7824d;

    /* renamed from: e */
    public TextView f7825e;

    /* renamed from: f */
    public TextView f7826f;

    /* renamed from: g */
    public TextView f7827g;

    /* renamed from: h */
    public TextView f7828h;

    /* renamed from: i */
    public View f7829i;

    /* renamed from: j */
    public GridView f7830j;

    /* renamed from: k */
    public C1507f f7831k;

    /* renamed from: l */
    public GridView f7832l;

    /* renamed from: m */
    public C1507f f7833m;

    /* renamed from: n */
    public View f7834n;

    /* renamed from: o */
    public View f7835o;

    /* renamed from: p */
    public View f7836p;

    /* renamed from: q */
    public View f7837q;

    /* renamed from: r */
    public LinearLayout f7838r;

    /* renamed from: s */
    public LinearLayout f7839s;

    /* renamed from: t */
    public LinearLayout f7840t;

    /* renamed from: u */
    public ImageView f7841u;

    /* renamed from: v */
    public Group f7842v;

    /* renamed from: w */
    public FriendsClient f7843w;

    /* renamed from: y */
    public ArrayList<Friend> f7845y;

    /* renamed from: z */
    public ArrayList<Friend> f7846z;

    /* renamed from: x */
    public boolean f7844x = true;

    /* renamed from: B */
    public final Object f7815B = new Object();

    /* renamed from: D */
    public int f7817D = 4;

    /* renamed from: E */
    public C5321e.m f7818E = new C1503b();

    /* renamed from: F */
    public View.OnClickListener f7819F = new View.OnClickListener() { // from class: com.cyberlink.you.activity.k5
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10811b.m8425M0(view);
        }
    };

    /* renamed from: I */
    public View.OnClickListener f7822I = new ViewOnClickListenerC1505d();

    /* renamed from: com.cyberlink.you.activity.GroupInfoActivity$a */
    public class AsyncTaskC1502a extends AsyncTask<Void, Void, Intent> {

        /* renamed from: a */
        public DialogC3133q f7847a;

        /* renamed from: b */
        public final /* synthetic */ Activity f7848b;

        /* renamed from: c */
        public final /* synthetic */ String f7849c;

        public AsyncTaskC1502a(Activity activity, String str) {
            this.f7848b = activity;
            this.f7849c = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Intent doInBackground(Void... voidArr) {
            FriendsClient friendsClient = new FriendsClient(true);
            Group groupM15711Q = friendsClient.m15711Q(this.f7849c);
            if (groupM15711Q == null) {
                return null;
            }
            List<Friend> listM15715T = friendsClient.m15715T(this.f7849c, groupM15711Q.f13727n);
            Intent intent = new Intent(this.f7848b, (Class<?>) GroupInfoActivity.class);
            intent.putExtra("Group", groupM15711Q);
            intent.putParcelableArrayListExtra("groupMemberList", (ArrayList) listM15715T);
            intent.putExtra("startViaLinkOrQRCode", true);
            friendsClient.m15717U0();
            return intent;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Intent intent) {
            this.f7847a.dismiss();
            if (intent == null) {
                GroupInfoActivity.m8429O0(this.f7848b);
            } else {
                this.f7848b.startActivity(intent);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            this.f7847a = new DialogC3133q.b(this.f7848b).m16411b();
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupInfoActivity$b */
    public class C1503b implements C5321e.m {
        public C1503b() {
        }

        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public boolean mo8241A0(C2904l c2904l, Map<String, String> map) {
            String str = map.get("eventType");
            String str2 = map.get("groupId");
            String str3 = map.get("userId");
            boolean z8 = str3 != null && str3.equals(String.valueOf(Globals.m7388i0().m7568k1()));
            if (str2 == null || GroupInfoActivity.this.f7842v == null || Long.parseLong(str2) != GroupInfoActivity.this.f7842v.f13727n) {
                return false;
            }
            if (str.equals("group.member.created") || str.equals("group.member.created.v2") || ((str.equals("group.member.deleted") && !z8) || ((str.equals("group.member.leaved") && !z8) || str.equals("group.admin.created") || str.equals("group.admin.deleted") || str.equals("group.group.updated") || str.equals("group.display.name.updated")))) {
                new AsyncTaskC1506e(GroupInfoActivity.this, null).executeOnExecutor(C6385v.f21554b, new Void[0]);
            } else if ((str.equals("group.member.deleted") && z8) || (str.equals("group.member.leaved") && z8)) {
                GroupInfoActivity.this.finish();
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupInfoActivity$c */
    public class AsyncTaskC1504c extends AsyncTask<Void, ArrayList<Friend>, ArrayList<Friend>> {

        /* renamed from: a */
        public final /* synthetic */ DialogC3133q f7851a;

        public AsyncTaskC1504c(DialogC3133q dialogC3133q) {
            this.f7851a = dialogC3133q;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<Friend> doInBackground(Void... voidArr) {
            return new ArrayList<>(GroupInfoActivity.this.f7842v.m15747c());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<Friend> arrayList) {
            this.f7851a.dismiss();
            GroupInfoActivity.this.f7821H = false;
            Intent intent = new Intent(GroupInfoActivity.this.getApplicationContext(), (Class<?>) SelectFromFriendGroupActivity.class);
            Bundle bundle = new Bundle();
            Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS", arrayList);
            Globals.C1408b.m7655b().m7657d("INTENT_CANNOT_MODIFIED_USERS_LIST", arrayList);
            bundle.putParcelable("Group", GroupInfoActivity.this.f7842v);
            intent.putExtras(bundle);
            GroupInfoActivity.this.startActivityForResult(intent, 0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupInfoActivity$d */
    public class ViewOnClickListenerC1505d implements View.OnClickListener {
        public ViewOnClickListenerC1505d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m8483c(boolean z8, DialogInterface dialogInterface, int i9) {
            GroupInfoActivity.this.m8473q0(z8);
        }

        /* renamed from: d */
        public static /* synthetic */ void m8484d(DialogInterface dialogInterface, int i9) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final boolean zIsSelected = view.isSelected();
            Button button = C3123g.m16382a(GroupInfoActivity.this).setMessage(zIsSelected ? R.string.group_disable_broadcast_alert : R.string.group_enable_broadcast_alert).setCancelable(false).setNegativeButton(zIsSelected ? R.string.group_disable_broadcast_option : R.string.group_enable_broadcast_option, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.y5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f12269b.m8483c(zIsSelected, dialogInterface, i9);
                }
            }).setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.z5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    GroupInfoActivity.ViewOnClickListenerC1505d.m8484d(dialogInterface, i9);
                }
            }).show().getButton(-2);
            if (button != null) {
                button.setTextColor(GroupInfoActivity.this.getResources().getColor(R.color.you_color_red));
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupInfoActivity$e */
    public class AsyncTaskC1506e extends AsyncTask<Void, Boolean, Boolean> {
        public AsyncTaskC1506e() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            FriendsClient friendsClient = new FriendsClient();
            if (GroupInfoActivity.this.f7844x) {
                GroupInfoActivity.this.f7842v = C2950b0.m14912k().m15077n(String.valueOf(GroupInfoActivity.this.f7842v.f13727n));
            }
            if (GroupInfoActivity.this.f7842v == null) {
                return Boolean.FALSE;
            }
            int i9 = 0;
            if (GroupInfoActivity.this.f7842v.f13702A != null && !GroupInfoActivity.this.f7842v.f13702A.isEmpty()) {
                synchronized (GroupInfoActivity.this.f7815B) {
                    Log.d("GroupInfoActivity", "[AsyncTaskGetGroupInfoFromDB] add to mListMembers start!");
                    GroupInfoActivity.this.f7845y = new ArrayList(GroupInfoActivity.this.f7816C);
                    Iterator<Long> it = GroupInfoActivity.this.f7842v.f13702A.iterator();
                    int i10 = 0;
                    while (it.hasNext()) {
                        long jLongValue = it.next().longValue();
                        Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(jLongValue));
                        if (friendM15003C == null) {
                            friendM15003C = friendsClient.m15727f0(String.valueOf(jLongValue));
                        }
                        if (friendM15003C != null) {
                            if (i10 >= GroupInfoActivity.this.f7816C) {
                                break;
                            }
                            GroupInfoActivity.this.f7845y.add(friendM15003C);
                            i10++;
                        }
                    }
                    Log.d("GroupInfoActivity", "[AsyncTaskGetGroupInfoFromDB] add to mListMembers end!");
                }
            }
            if (GroupInfoActivity.this.f7842v.f13703B != null && !GroupInfoActivity.this.f7842v.f13703B.isEmpty()) {
                synchronized (GroupInfoActivity.this.f7815B) {
                    GroupInfoActivity.this.f7846z = new ArrayList(GroupInfoActivity.this.f7817D);
                    Iterator<Long> it2 = GroupInfoActivity.this.f7842v.f13703B.iterator();
                    while (it2.hasNext()) {
                        long jLongValue2 = it2.next().longValue();
                        Friend friendM15003C2 = C2950b0.m14899A().m15003C(String.valueOf(jLongValue2));
                        if (friendM15003C2 == null) {
                            friendM15003C2 = friendsClient.m15727f0(String.valueOf(jLongValue2));
                        }
                        if (friendM15003C2 != null) {
                            if (i9 >= GroupInfoActivity.this.f7817D) {
                                break;
                            }
                            GroupInfoActivity.this.f7846z.add(friendM15003C2);
                            i9++;
                        }
                    }
                }
            }
            return Boolean.TRUE;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                if (GroupInfoActivity.this.f7846z != null && GroupInfoActivity.this.f7846z.size() > 0) {
                    GroupInfoActivity.this.f7831k.m8490d(GroupInfoActivity.this.f7846z);
                }
                if (GroupInfoActivity.this.f7845y != null && GroupInfoActivity.this.f7845y.size() > 0) {
                    GroupInfoActivity.this.f7833m.m8490d(GroupInfoActivity.this.f7845y);
                }
                if (GroupInfoActivity.this.f7825e != null) {
                    TextView textView = GroupInfoActivity.this.f7825e;
                    GroupInfoActivity groupInfoActivity = GroupInfoActivity.this;
                    textView.setText(groupInfoActivity.getString(R.string.admins_D, Long.valueOf(groupInfoActivity.f7842v.f13729p)));
                }
                TextView textView2 = GroupInfoActivity.this.f7826f;
                GroupInfoActivity groupInfoActivity2 = GroupInfoActivity.this;
                textView2.setText(groupInfoActivity2.getString(R.string.members_D, Long.valueOf(groupInfoActivity2.f7842v.f13728o)));
                GroupInfoActivity.this.f7824d.setText(GroupInfoActivity.this.f7842v.f13717d);
                GroupInfoActivity groupInfoActivity3 = GroupInfoActivity.this;
                C6129c.m23483d(groupInfoActivity3, groupInfoActivity3.f7823c, GroupInfoActivity.this.f7842v);
                GroupInfoActivity.this.f7837q.setVisibility(((!"Community".equals(GroupInfoActivity.this.f7842v.f13705D) || GroupInfoActivity.this.f7842v.f13704C) && !GroupInfoActivity.this.f7842v.m15751i()) ? 0 : 8);
                if (GroupInfoActivity.this.f7834n != null) {
                    GroupInfoActivity.this.f7834n.setVisibility(GroupInfoActivity.this.f7842v.f13711J ? 8 : 0);
                }
                if (GroupInfoActivity.this.f7835o != null) {
                    GroupInfoActivity.this.f7835o.setVisibility((GroupInfoActivity.this.f7842v.f13711J || GroupInfoActivity.this.f7842v.m15751i()) ? 8 : 0);
                }
                if (GroupInfoActivity.this.f7836p != null) {
                    GroupInfoActivity.this.f7836p.setVisibility((GroupInfoActivity.this.f7842v.f13711J || GroupInfoActivity.this.f7842v.m15751i() || GroupInfoActivity.this.f7842v.f13712K) ? 8 : 0);
                }
            }
        }

        public /* synthetic */ AsyncTaskC1506e(GroupInfoActivity groupInfoActivity, AsyncTaskC1502a asyncTaskC1502a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupInfoActivity$f */
    public class C1507f extends BaseAdapter {

        /* renamed from: d */
        public boolean f7857d;

        /* renamed from: b */
        public final int f7855b = 0;

        /* renamed from: c */
        public final int f7856c = 1;

        /* renamed from: e */
        public final ArrayList<Friend> f7858e = new ArrayList<>();

        /* renamed from: com.cyberlink.you.activity.GroupInfoActivity$f$a */
        public class a {

            /* renamed from: a */
            public final ImageView f7860a;

            public a(View view) {
                this.f7860a = (ImageView) view.findViewById(R.id.imageView);
            }
        }

        public C1507f(boolean z8) {
            this.f7857d = z8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m8488c(Friend friend, View view) {
            if (C5179r0.m20246a()) {
                return;
            }
            Intent intent = new Intent(GroupInfoActivity.this.getApplicationContext(), (Class<?>) FriendProfileActivity.class);
            intent.putExtra("friendObj", friend);
            intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
            GroupInfoActivity.this.startActivityForResult(intent, 11);
        }

        /* renamed from: b */
        public final boolean m8489b(int i9) {
            return this.f7857d && i9 == this.f7858e.size();
        }

        /* renamed from: d */
        public void m8490d(ArrayList<Friend> arrayList) {
            this.f7858e.clear();
            this.f7858e.addAll(arrayList);
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f7858e.size() + (this.f7857d ? 1 : 0);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            if (i9 < 0 || i9 >= this.f7858e.size()) {
                return null;
            }
            return this.f7858e.get(i9);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i9) {
            return m8489b(i9) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridview_item_imageview, viewGroup, false);
                aVar = new a(view);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            if (!m8489b(i9)) {
                final Friend friend = this.f7858e.get(i9);
                aVar.f7860a.setVisibility(0);
                C6127a.m23469j(GroupInfoActivity.this, aVar.f7860a, friend);
                aVar.f7860a.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.a6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f9708b.m8488c(friend, view2);
                    }
                });
            } else if (GroupInfoActivity.this.f7844x) {
                aVar.f7860a.setVisibility(0);
                aVar.f7860a.setImageResource(R.drawable.image_selector_group_profile_add);
                aVar.f7860a.setOnClickListener(GroupInfoActivity.this.f7819F);
            } else {
                aVar.f7860a.setVisibility(8);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return this.f7857d ? 2 : 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C0 */
    public /* synthetic */ void m8409C0(View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f7842v);
        bundle.putInt("type", 2);
        if (!this.f7844x) {
            bundle.putParcelableArrayList("originalMembers", this.f7846z);
            bundle.putBoolean("isGroupMember", false);
        }
        Intent intent = new Intent();
        intent.setClass(this, GroupMemberListActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public /* synthetic */ void m8411D0(View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f7842v);
        bundle.putInt("type", 0);
        if (!this.f7844x) {
            bundle.putParcelableArrayList("originalMembers", this.f7814A);
            bundle.putBoolean("isGroupMember", false);
        }
        Intent intent = new Intent();
        intent.setClass(this, GroupMemberListActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E0 */
    public /* synthetic */ void m8413E0(View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) PhotoListActivity.class);
        String string = Long.toString(this.f7842v.f13727n);
        Group group = this.f7842v;
        intent.putExtra("album", new GroupAlbumObj(string, group.f13718e, group.f13717d, "Chat"));
        intent.putExtra("ShowShareToMyAlbum", true);
        intent.putExtra("Group", this.f7842v);
        startActivityForResult(intent, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F0 */
    public /* synthetic */ void m8414F0(View view) {
        Group group = this.f7842v;
        if (!group.f13712K) {
            m8467R0(group, ChatDialogActivity.Tab.Albums);
            return;
        }
        Intent intent = new Intent(this, (Class<?>) GroupAlbumActivity.class);
        intent.putExtra("Group", this.f7842v);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G0 */
    public /* synthetic */ void m8415G0(View view) {
        m8467R0(this.f7842v, ChatDialogActivity.Tab.Bulletins);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H0 */
    public /* synthetic */ void m8417H0(View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) GroupEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f7842v);
        intent.putExtras(bundle);
        startActivityForResult(intent, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I0 */
    public /* synthetic */ void m8419I0(View view) {
        m8467R0(this.f7842v, ChatDialogActivity.Tab.Chats);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J0 */
    public /* synthetic */ void m8421J0(View view) {
        m8474r0();
    }

    /* renamed from: K0 */
    public static /* synthetic */ void m8422K0(Dialog dialog, Activity activity, View view) {
        dialog.dismiss();
        if (activity instanceof GroupInfoActivity) {
            activity.finish();
        }
    }

    /* renamed from: L0 */
    public static /* synthetic */ void m8424L0(Dialog dialog, Activity activity, View view) {
        dialog.dismiss();
        Intent intent = new Intent(activity.getApplicationContext(), (Class<?>) RegisterActivity.class);
        intent.setFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M0 */
    public /* synthetic */ void m8425M0(View view) {
        if (C5179r0.m20246a()) {
            return;
        }
        m8468S0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m8427N0() {
        this.f7844x = true;
        this.f7838r.setVisibility(8);
        this.f7839s.setVisibility(0);
        findViewById(R.id.chatBtn).setVisibility(0);
        this.f7833m.notifyDataSetChanged();
        this.f7832l.invalidateViews();
    }

    /* renamed from: O0 */
    public static void m8429O0(final Activity activity) {
        if (activity.isFinishing()) {
            return;
        }
        final Dialog dialogM16384c = C3123g.m16384c(activity);
        if (Globals.m7388i0().m7464O1()) {
            dialogM16384c.setContentView(R.layout.dialog_group_invitelink_is_expire);
            dialogM16384c.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.i5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GroupInfoActivity.m8422K0(dialogM16384c, activity, view);
                }
            });
        } else {
            dialogM16384c.setContentView(R.layout.dialog_group_invitelink_not_login);
            dialogM16384c.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.p5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GroupInfoActivity.m8424L0(dialogM16384c, activity, view);
                }
            });
        }
        dialogM16384c.setCancelable(false);
        dialogM16384c.setCanceledOnTouchOutside(false);
        dialogM16384c.show();
    }

    /* renamed from: T0 */
    public static void m8433T0(Activity activity, String str) {
        new AsyncTaskC1502a(activity, str).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: w0 */
    public static /* synthetic */ void m8459w0(DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) {
        dialogC3133q.dismiss();
        Log.d("GroupInfoActivity", "add user to group, statusCode = " + str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ void m8460x0(boolean z8) {
        this.f7841u.setSelected(!z8);
        View view = this.f7836p;
        if (view != null) {
            Group group = this.f7842v;
            view.setVisibility((group.f13711J || group.m15751i() || this.f7842v.f13712K) ? 8 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m8462y0(final boolean z8, DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            Group groupM20186h = C5172p.m20186h(C5172p.m20195q(str4));
            C2950b0.m14912k().m15070g(groupM20186h, true);
            this.f7842v = groupM20186h;
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.o5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10999b.m8460x0(z8);
                }
            });
        } else {
            Log.e("GroupInfoActivity", "Update Broadcast config fail");
        }
        if (isFinishing()) {
            return;
        }
        dialogC3133q.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
    /* renamed from: z0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ void m8464z0(DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) {
        Group groupM20186h;
        if ("200".equals(str3)) {
            try {
                try {
                    groupM20186h = C5172p.m20186h(new JSONObject(str4).getJSONObject("result"));
                } catch (JSONException unused) {
                    Log.e("GroupInfoActivity", "[onAddGroupMember] 'results' missing. JSONstr=" + str4);
                }
            } catch (JSONException unused2) {
                Log.e("GroupInfoActivity", "[onAddGroupMember] Parse error. JSONstr=" + str4);
            }
            if (groupM20186h != null) {
                m8465P0();
            }
            dialogC3133q.dismiss();
        }
        Log.d("GroupInfoActivity", "statusCode = " + str3);
        groupM20186h = null;
        if (groupM20186h != null) {
        }
        dialogC3133q.dismiss();
    }

    /* renamed from: P0 */
    public final void m8465P0() {
        if (this.f7845y == null) {
            return;
        }
        synchronized (this.f7815B) {
            Iterator<Friend> it = this.f7845y.iterator();
            while (it.hasNext()) {
                Friend next = it.next();
                if (next != null && this.f7842v != null) {
                    C2950b0.m14913l().m15101l(Long.valueOf(this.f7842v.f13727n), Long.valueOf(next.f13645c));
                }
            }
        }
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.n5
            @Override // java.lang.Runnable
            public final void run() {
                this.f10918b.m8427N0();
            }
        });
    }

    /* renamed from: Q0 */
    public final void m8466Q0(Group group) {
        if (getApplicationContext() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", group);
        Intent intent = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* renamed from: R0 */
    public final void m8467R0(Group group, ChatDialogActivity.Tab tab) {
        if (C5179r0.m20246a()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", group);
        bundle.putInt("chatDialogTabType", tab.ordinal());
        Intent intent = new Intent(this, (Class<?>) ChatDialogActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* renamed from: S0 */
    public final void m8468S0() {
        DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        if (this.f7821H) {
            return;
        }
        this.f7821H = true;
        AsyncTaskC1504c asyncTaskC1504c = new AsyncTaskC1504c(dialogC3133qM16411b);
        this.f7820G = asyncTaskC1504c;
        asyncTaskC1504c.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: m0 */
    public final void m8469m0(int i9, List<Friend> list) {
        final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.q5
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                GroupInfoActivity.m8459w0(dialogC3133qM16411b, str, str2, str3, str4);
            }
        };
        if (i9 == 0) {
            this.f7843w.m15737x(this.f7842v.f13727n, list, interfaceC3051i);
        } else if (i9 == 1) {
            this.f7843w.m15736w(this.f7842v.f13727n, list, interfaceC3051i);
        }
    }

    /* renamed from: n0 */
    public final void m8470n0() {
        this.f7823c = (ImageView) findViewById(R.id.group_avatar);
        this.f7824d = (TextView) findViewById(R.id.topBarTitle);
        this.f7825e = (TextView) findViewById(R.id.adminCount);
        this.f7826f = (TextView) findViewById(R.id.memberCount);
        this.f7827g = (TextView) findViewById(R.id.seeAllAdmins);
        this.f7828h = (TextView) findViewById(R.id.seeAllMembers);
        this.f7829i = findViewById(R.id.chatBtn);
        this.f7834n = findViewById(R.id.LayoutPhotos);
        this.f7835o = findViewById(R.id.LayoutAlbums);
        this.f7836p = findViewById(R.id.LayoutBulletin);
        this.f7837q = findViewById(R.id.LayoutEdit);
        this.f7830j = (GridView) findViewById(R.id.gridViewAdmins);
        this.f7832l = (GridView) findViewById(R.id.gridViewMembers);
        this.f7838r = (LinearLayout) findViewById(R.id.buttonLayout);
        this.f7839s = (LinearLayout) findViewById(R.id.functionLayout);
        View view = this.f7834n;
        int i9 = 8;
        if (view != null) {
            view.setVisibility(this.f7842v.f13711J ? 8 : 0);
        }
        View view2 = this.f7835o;
        if (view2 != null) {
            Group group = this.f7842v;
            view2.setVisibility((group.f13711J || group.m15751i()) ? 8 : 0);
        }
        View view3 = this.f7836p;
        if (view3 != null) {
            Group group2 = this.f7842v;
            if (!group2.f13711J && !group2.m15751i() && !this.f7842v.f13712K) {
                i9 = 0;
            }
            view3.setVisibility(i9);
        }
        this.f7840t = (LinearLayout) findViewById(R.id.enableBroadcastSettingArea);
        this.f7841u = (ImageView) findViewById(R.id.enableBroadcastCheckImageView);
    }

    /* renamed from: o0 */
    public final void m8471o0() {
        Uri data = getIntent().getData();
        if (data != null && "groupInvite".equals(data.getHost())) {
            try {
                m8433T0(this, URLDecoder.decode(data.getQueryParameter("inviteURL"), "UTF-8"));
                return;
            } catch (UnsupportedEncodingException e9) {
                C5154j.m20076j(e9);
                m8429O0(this);
                return;
            }
        }
        Group group = (Group) getIntent().getParcelableExtra("Group");
        this.f7842v = group;
        if (group == null) {
            m8429O0(this);
            return;
        }
        this.f7844x = !getIntent().getBooleanExtra("startViaLinkOrQRCode", false);
        this.f7814A = getIntent().getParcelableArrayListExtra("groupMemberList");
        m8475s0();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        Bundle extras;
        super.onActivityResult(i9, i10, intent);
        CLUtility.m16589t1(this);
        if (i9 == 0) {
            if (i10 != -1 || intent.getExtras() == null) {
                return;
            }
            m8469m0(i9, Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST"));
            return;
        }
        if (i9 != 11 && i9 != 2) {
            if (i9 == 3 && i10 == -1) {
                Bundle extras2 = intent.getExtras();
                ArrayList arrayList = (ArrayList) extras2.getSerializable("import_images");
                if (arrayList == null || arrayList.size() <= 0) {
                    return;
                }
                Intent intent2 = new Intent(this, (Class<?>) ChatDialogActivity.class);
                extras2.putParcelable("Group", this.f7842v);
                intent2.putExtras(extras2);
                startActivity(intent2);
                finish();
                return;
            }
            return;
        }
        if (i10 != -1 || (extras = intent.getExtras()) == null) {
            return;
        }
        FriendProfileActivity.FPA_RETURN_TYPE fpa_return_typeValueOf = FriendProfileActivity.FPA_RETURN_TYPE.RETURN_NONE;
        try {
            fpa_return_typeValueOf = FriendProfileActivity.FPA_RETURN_TYPE.valueOf(extras.getString("type"));
        } catch (Exception e9) {
            Log.e("GroupInfoActivity", "[onActivityResult] REQUEST_SHOW_MEMBER_LIST: " + e9.getMessage());
        }
        if (fpa_return_typeValueOf == FriendProfileActivity.FPA_RETURN_TYPE.RETURN_CHAT_DUAL) {
            Group group = (Group) extras.getParcelable("StartOtherGroup");
            if (group != null) {
                m8466Q0(group);
                return;
            }
            return;
        }
        Log.d("GroupInfoActivity", "[onActivityResult] REQUEST_SHOW_MEMBER_LIST: return type (%s)" + fpa_return_typeValueOf.name());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7843w = new FriendsClient(true);
        m8471o0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        FriendsClient friendsClient = this.f7843w;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        C5321e.m20824o().m20832B0(this.f7818E);
        AsyncTask<Void, ArrayList<Friend>, ArrayList<Friend>> asyncTask = this.f7820G;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        m8471o0();
    }

    /* renamed from: p0 */
    public final boolean m8472p0() {
        Iterator<Friend> it = this.f7814A.iterator();
        while (it.hasNext()) {
            if (it.next().f13645c == Globals.m7388i0().m7568k1().longValue()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: q0 */
    public final void m8473q0(final boolean z8) {
        final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(this.f7842v.f13727n)));
        arrayList.add(new C6301o("isBroadcastingOnlyMode", String.valueOf(!z8)));
        this.f7843w.m15734m("group", "update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.m5
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10880a.m8462y0(z8, dialogC3133qM16411b, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: r0 */
    public final void m8474r0() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", Long.toString(this.f7842v.f13727n)));
        arrayList.add(new C6301o("userId", Long.toString(Globals.m7388i0().m7568k1().longValue())));
        arrayList.add(new C6301o("addOrJoin2Group", "join2Group"));
        final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(this).m16411b();
        this.f7843w.m15734m("group", "addMembers", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.l5
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10845a.m8464z0(dialogC3133qM16411b, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: s0 */
    public final void m8475s0() {
        if (this.f7842v == null) {
            return;
        }
        if (!this.f7844x && this.f7814A != null) {
            this.f7844x = m8472p0();
            Log.d("GroupInfoActivity", "[groupInfoPageSetting]mTotalMembersList = " + this.f7814A);
        }
        Log.d("GroupInfoActivity", "[groupInfoPageSetting]isGroupMember = " + this.f7844x);
        String str = this.f7842v.f13705D;
        if (str == null || str.equals("Small")) {
            setContentView(R.layout.activity_small_group_info);
            this.f7816C = 7;
        } else if (!this.f7842v.f13705D.equals("Community") && !"ChatRoom".equals(this.f7842v.f13716c)) {
            Log.d("GroupInfoActivity", "Unknown circleType");
            return;
        } else {
            setContentView(R.layout.activity_big_community_info);
            this.f7816C = 3;
        }
        m8470n0();
        m8476u0();
        new AsyncTaskC1506e(this, null).executeOnExecutor(C6385v.f21553a, new Void[0]);
        C5321e.m20824o().m20875k(this.f7818E);
    }

    /* renamed from: u0 */
    public final void m8476u0() {
        m7365g(R.id.BackBtn);
        TextView textView = this.f7827g;
        if (textView != null) {
            textView.setPaintFlags(this.f7828h.getPaintFlags() | 8);
            this.f7827g.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.r5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f11134b.m8409C0(view);
                }
            });
        }
        TextView textView2 = this.f7828h;
        textView2.setPaintFlags(textView2.getPaintFlags() | 8);
        this.f7824d.setText(this.f7842v.f13717d);
        TextView textView3 = this.f7825e;
        if (textView3 != null) {
            textView3.setText(getString(R.string.admins_D, Long.valueOf(this.f7842v.f13729p)));
        }
        this.f7826f.setText(getString(R.string.members_D, Long.valueOf(this.f7842v.f13728o)));
        this.f7828h.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.s5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11165b.m8411D0(view);
            }
        });
        View view = this.f7834n;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.t5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f11391b.m8413E0(view2);
                }
            });
        }
        this.f7835o.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.u5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f11427b.m8414F0(view2);
            }
        });
        this.f7836p.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.v5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f11810b.m8415G0(view2);
            }
        });
        String str = this.f7842v.f13705D;
        if (str != null && str.equals("Community") && !this.f7842v.f13704C) {
            this.f7837q.setVisibility(8);
        }
        this.f7837q.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.w5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f11842b.m8417H0(view2);
            }
        });
        if (this.f7830j != null) {
            C1507f c1507f = new C1507f(false);
            this.f7831k = c1507f;
            this.f7830j.setAdapter((ListAdapter) c1507f);
        }
        C1507f c1507f2 = new C1507f(true ^ this.f7842v.m15751i());
        this.f7833m = c1507f2;
        this.f7832l.setAdapter((ListAdapter) c1507f2);
        this.f7829i.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.x5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f12247b.m8419I0(view2);
            }
        });
        C6129c.m23483d(this, this.f7823c, this.f7842v);
        if (!this.f7844x) {
            this.f7838r.setVisibility(0);
            this.f7839s.setVisibility(8);
            findViewById(R.id.chatBtn).setVisibility(8);
            m7365g(R.id.cancelBtn);
            findViewById(R.id.joinBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.j5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f10777b.m8421J0(view2);
                }
            });
        }
        if (this.f7844x) {
            Group group = this.f7842v;
            if (group.f13713L && !group.m15751i()) {
                this.f7840t.setVisibility(0);
                this.f7841u.setSelected(this.f7842v.f13712K);
                this.f7841u.setOnClickListener(this.f7822I);
                return;
            }
        }
        this.f7840t.setVisibility(8);
    }
}
