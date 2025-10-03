package p173q2;

import android.content.Context;
import android.widget.ImageView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.friends.CorpAccount;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.friends.UserInfo;
import p116k4.C5170o0;
import p209u2.C6383t;

/* renamed from: q2.c */
/* loaded from: classes.dex */
public final class C6129c {
    /* renamed from: a */
    public static void m23480a(Context context, ImageView imageView, CorpAccount corpAccount) {
        m23487h(context, imageView, corpAccount == null ? null : corpAccount.f13619h);
    }

    /* renamed from: b */
    public static void m23481b(Context context, ImageView imageView, DepartmentFriend departmentFriend) {
        m23487h(context, imageView, departmentFriend == null ? null : departmentFriend.f13632h);
    }

    /* renamed from: c */
    public static void m23482c(Context context, ImageView imageView, Friend friend) {
        m23487h(context, imageView, friend == null ? null : friend.f13650h);
    }

    /* renamed from: d */
    public static void m23483d(Context context, ImageView imageView, Group group) {
        m23487h(context, imageView, group == null ? null : C5170o0.m20170e(group.f13725l) ? group.f13724k : group.f13725l);
    }

    /* renamed from: e */
    public static void m23484e(Context context, ImageView imageView, InvitationFriend invitationFriend) {
        m23487h(context, imageView, invitationFriend == null ? null : invitationFriend.f13749j);
    }

    /* renamed from: f */
    public static void m23485f(Context context, ImageView imageView, SuggestionFriend suggestionFriend) {
        m23487h(context, imageView, suggestionFriend == null ? null : suggestionFriend.f13764h);
    }

    /* renamed from: g */
    public static void m23486g(Context context, ImageView imageView, UserInfo userInfo) {
        m23487h(context, imageView, userInfo == null ? null : userInfo.f13785j);
    }

    /* renamed from: h */
    public static void m23487h(Context context, ImageView imageView, String str) {
        if (context == null || imageView == null || C6143q.m23608a(context)) {
            return;
        }
        if (C6383t.m24517f(str) || !(str.startsWith("http://") || str.startsWith("https://"))) {
            C6136j.m23592l(context, imageView, R.drawable.banner_default);
        } else {
            C6132f.m23492b(context).mo23575d().mo23570t(str).m23561T(R.drawable.banner_default).m24422n(imageView);
        }
    }
}
