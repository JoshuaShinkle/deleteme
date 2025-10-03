package p116k4;

import android.os.AsyncTask;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import p173q2.C6127a;
import p201t3.C6301o;
import p209u2.C6385v;

/* renamed from: k4.n1 */
/* loaded from: classes.dex */
public class C5168n1 {

    /* renamed from: a */
    public static Map<String, Friend> f17710a;

    /* renamed from: k4.n1$a */
    public class a extends AsyncTask<Void, Void, Friend> {

        /* renamed from: a */
        public final /* synthetic */ String f17711a;

        /* renamed from: b */
        public final /* synthetic */ FriendsClient f17712b;

        /* renamed from: c */
        public final /* synthetic */ TextView f17713c;

        /* renamed from: d */
        public final /* synthetic */ Object f17714d;

        /* renamed from: e */
        public final /* synthetic */ ImageView f17715e;

        /* renamed from: f */
        public final /* synthetic */ TextView f17716f;

        public a(String str, FriendsClient friendsClient, TextView textView, Object obj, ImageView imageView, TextView textView2) {
            this.f17711a = str;
            this.f17712b = friendsClient;
            this.f17713c = textView;
            this.f17714d = obj;
            this.f17715e = imageView;
            this.f17716f = textView2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Friend doInBackground(Void... voidArr) {
            Thread.currentThread().setName("getUserInfoTask");
            Friend friendM15003C = C2950b0.m14899A().m15003C(this.f17711a);
            return friendM15003C == null ? C5168n1.m20148e(this.f17712b, this.f17711a) : friendM15003C;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Friend friend) {
            if (friend == null) {
                return;
            }
            C5168n1.f17710a.put(String.valueOf(friend.f13645c), friend);
            Object tag = this.f17713c.getTag();
            if (tag == null || tag != this.f17714d) {
                return;
            }
            C5168n1.m20153j(this.f17715e, this.f17713c, this.f17716f, friend);
        }
    }

    /* renamed from: d */
    public static Friend m20147d(String str) {
        if (f17710a == null) {
            f17710a = new HashMap();
        }
        boolean z8 = false;
        if (f17710a.containsKey(str) && f17710a.get(str) != null) {
            z8 = true;
        }
        if (z8) {
            return f17710a.get(str);
        }
        return null;
    }

    /* renamed from: e */
    public static Friend m20148e(FriendsClient friendsClient, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", str));
        Pair<String, String> pairM15731j = friendsClient.m15731j("user", "userInfoV2", arrayList);
        String str2 = (String) pairM15731j.first;
        String str3 = (String) pairM15731j.second;
        if (str2 == null || !str2.equals("200")) {
            return null;
        }
        Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str3));
        C2950b0.m14899A().m15018j(friendM20184f, false);
        return friendM20184f;
    }

    /* renamed from: f */
    public static boolean m20149f(long j9) {
        Friend friendM15001A = C2950b0.m14899A().m15001A(String.valueOf(j9));
        return (friendM15001A == null || friendM15001A.f13654l) ? false : true;
    }

    /* renamed from: g */
    public static boolean m20150g(long j9) {
        Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(j9));
        if (friendM15003C == null) {
            return false;
        }
        return friendM15003C.f13660r.equals("Official");
    }

    /* renamed from: h */
    public static void m20151h(ImageView imageView, TextView textView, TextView textView2, String str, FriendsClient friendsClient) {
        if (str == null) {
            m20153j(imageView, textView, textView2, null);
        }
        Friend friendM20147d = m20147d(str);
        if (friendM20147d != null) {
            m20153j(imageView, textView, textView2, friendM20147d);
            return;
        }
        Object obj = new Object();
        textView.setText("");
        C6127a.m23474o(imageView.getContext(), imageView, "", R.drawable.pic_default);
        textView.setTag(obj);
        new a(str, friendsClient, textView, obj, imageView, textView2).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: i */
    public static void m20152i(ImageView imageView, TextView textView, String str, FriendsClient friendsClient) {
        m20151h(imageView, textView, null, str, friendsClient);
    }

    /* renamed from: j */
    public static void m20153j(ImageView imageView, TextView textView, TextView textView2, Friend friend) {
        if (friend == null) {
            textView.setText("");
            C6127a.m23474o(imageView.getContext(), imageView, "", R.drawable.pic_default);
            return;
        }
        if (friend.m15621b() == null || friend.m15621b().isEmpty()) {
            textView.setText("");
        } else {
            textView.setText(friend.m15621b());
            if (textView2 != null) {
                textView2.setText(friend.m15621b() + StringUtils.SPACE + textView2.getText().toString().toLowerCase());
            }
        }
        C6127a.m23469j(imageView.getContext(), imageView, friend);
    }
}
