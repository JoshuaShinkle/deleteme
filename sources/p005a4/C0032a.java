package p005a4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import p116k4.C5143f0;
import p116k4.C5187v0;
import p193s4.C6263a;
import p201t3.C6288b;
import p201t3.C6289c;
import p201t3.C6301o;

/* renamed from: a4.a */
/* loaded from: classes.dex */
public class C0032a {

    /* renamed from: d */
    public static final String f115d = "a";

    /* renamed from: a */
    public final String f116a;

    /* renamed from: b */
    public final String f117b;

    /* renamed from: c */
    public final String f118c;

    /* renamed from: a4.a$a */
    public class a implements C6288b.d<Friend> {

        /* renamed from: a */
        public final /* synthetic */ FriendsClient f119a;

        /* renamed from: b */
        public final /* synthetic */ Context f120b;

        public a(FriendsClient friendsClient, Context context) {
            this.f119a = friendsClient;
            this.f120b = context;
        }

        @Override // p201t3.C6288b.d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(Friend friend) {
            C0032a c0032a = C0032a.this;
            c0032a.m126f(c0032a.f116a, friend, this.f119a, this.f120b);
        }
    }

    /* renamed from: a4.a$b */
    public class b implements C6288b.h {

        /* renamed from: a */
        public final /* synthetic */ FriendsClient f122a;

        public b(FriendsClient friendsClient) {
            this.f122a = friendsClient;
        }

        @Override // p201t3.C6288b.h
        public void onError(String str) {
            this.f122a.m15717U0();
        }
    }

    /* renamed from: a4.a$c */
    public class c implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public final /* synthetic */ Friend f124a;

        /* renamed from: b */
        public final /* synthetic */ d f125b;

        /* renamed from: c */
        public final /* synthetic */ Context f126c;

        /* renamed from: d */
        public final /* synthetic */ FriendsClient f127d;

        public c(Friend friend, d dVar, Context context, FriendsClient friendsClient) {
            this.f124a = friend;
            this.f125b = dVar;
            this.f126c = context;
            this.f127d = friendsClient;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            if (str3 == null || !str3.equals("200")) {
                Log.i(C0032a.f115d, "friend already!!");
            } else {
                Log.i(C0032a.f115d, "add success!!");
                this.f125b.sendMessage(C0032a.this.m130k(this.f124a));
                this.f124a.f13654l = false;
                C2950b0.m14899A().m15019k(this.f124a, true, true);
            }
            C0032a.this.m127h(this.f124a, this.f126c);
            this.f127d.m15717U0();
        }
    }

    /* renamed from: a4.a$d */
    public static class d extends Handler {

        /* renamed from: a */
        public final WeakReference<Context> f129a;

        public d(Context context) {
            super(Looper.getMainLooper());
            this.f129a = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Context context = this.f129a.get();
            if (context != null && (context instanceof Activity) && message.what == 0) {
                C5187v0.m20268d(Globals.m7372O().getString(R.string.notification_user_accpet_friend_request, message.getData().getString("userName")));
            }
        }
    }

    public C0032a(Intent intent) {
        String strM24005c = C6263a.m24005c(intent);
        this.f118c = strM24005c;
        String[] strArrSplit = strM24005c.split(":");
        this.f117b = strArrSplit[0];
        this.f116a = strArrSplit[1] + ":" + strArrSplit[2];
    }

    /* renamed from: g */
    public static Uri m125g(UserInfo userInfo, Context context) {
        long j9 = userInfo.f13777b;
        String string = context.getSharedPreferences("U", 0).getString("inviteFriendLink", "");
        Log.i(f115d, "friendLink:" + string);
        return Uri.parse("" + j9 + ":" + string);
    }

    /* renamed from: f */
    public final void m126f(String str, Friend friend, FriendsClient friendsClient, Context context) {
        d dVar = new d(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteURL", str));
        friendsClient.m15734m("friend", ProductAction.ACTION_ADD, arrayList, new c(friend, dVar, context, friendsClient));
    }

    /* renamed from: h */
    public final void m127h(Friend friend, Context context) {
        Intent intent = new Intent(context, (Class<?>) FriendProfileActivity.class);
        intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
        intent.putExtra("friendObj", friend);
        context.startActivity(intent);
        Globals.f7307x.m20036i("needOpenDeepLink", false);
    }

    /* renamed from: i */
    public boolean m128i() {
        return Globals.m7388i0().m7464O1();
    }

    /* renamed from: j */
    public boolean m129j() {
        return Globals.f7307x.m20031d("needOpenDeepLink", false);
    }

    /* renamed from: k */
    public final Message m130k(Friend friend) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        Bundle bundle = new Bundle();
        bundle.putString("userName", friend.m15620a());
        messageObtain.setData(bundle);
        return messageObtain;
    }

    /* renamed from: l */
    public void m131l() {
        C5143f0 c5143f0 = Globals.f7307x;
        c5143f0.m20040m("appInviteLink", this.f118c);
        c5143f0.m20036i("needOpenDeepLink", true);
    }

    /* renamed from: m */
    public void m132m(Context context) {
        FriendsClient friendsClient = new FriendsClient();
        C6289c.m24104c(this.f117b, new a(friendsClient, context), new b(friendsClient)).m24088p();
    }

    public C0032a() {
        String strM20035h = Globals.f7307x.m20035h("appInviteLink", "");
        this.f118c = strM20035h;
        String[] strArrSplit = strM20035h.split(":");
        if (strArrSplit.length == 3) {
            this.f117b = strArrSplit[0];
            this.f116a = strArrSplit[1] + ":" + strArrSplit[2];
            return;
        }
        this.f117b = "";
        this.f116a = "";
    }
}
