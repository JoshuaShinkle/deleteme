package com.cyberlink.meeting.page.p032m;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.LongSparseArray;
import android.util.Pair;
import android.widget.ImageView;
import com.cyberlink.meeting.model.InviteeList;
import com.cyberlink.meeting.page.p032m.AvatarDrawableCache;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.C2143a;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;
import p003a2.C0011a;
import p116k4.C5170o0;
import p173q2.C6127a;
import p173q2.C6136j;
import p198t0.C6275a;
import p209u2.AbstractC6381r;
import p209u2.ThreadFactoryC6373j;

/* loaded from: classes.dex */
public final class AvatarDrawableCache {

    /* renamed from: i */
    public static final List<Integer> f6473i = Collections.singletonList(Integer.valueOf(Color.parseColor("#008af5")));

    /* renamed from: a */
    public final Context f6474a;

    /* renamed from: c */
    public final LongSparseArray<Friend> f6476c;

    /* renamed from: d */
    public final LongSparseArray<InviteeList.Invitee> f6477d;

    /* renamed from: h */
    public C0011a f6481h;

    /* renamed from: b */
    public final Handler f6475b = new Handler(Looper.getMainLooper());

    /* renamed from: e */
    public final HashMap<String, Drawable> f6478e = new HashMap<>();

    /* renamed from: f */
    public final HashSet<String> f6479f = new HashSet<>();

    /* renamed from: g */
    public final ExecutorService f6480g = Executors.newFixedThreadPool(4, new ThreadFactoryC6373j("MeetingAvatarPool"));

    public enum AvatarSize {
        LARGE(R.dimen.clm_meeting_active_participant_avatar),
        MIDDLE(R.dimen.clm_meeting_participant_list_avatar),
        SMALL(R.dimen.recv_avatar_image_width),
        GALLERY(R.dimen.clm_meeting_gallery_participant_avatar),
        DESKTOP(R.dimen.clm_meeting_desktop_avatar);

        private final int dimenResId;

        AvatarSize(int i9) {
            this.dimenResId = i9;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.AvatarDrawableCache$a */
    public class C1289a extends AbstractC6381r<Void, Void> {

        /* renamed from: c */
        public final /* synthetic */ String f6488c;

        /* renamed from: d */
        public final /* synthetic */ C0011a f6489d;

        /* renamed from: e */
        public final /* synthetic */ ImageView f6490e;

        /* renamed from: f */
        public final /* synthetic */ AvatarSize f6491f;

        public C1289a(String str, C0011a c0011a, ImageView imageView, AvatarSize avatarSize) {
            this.f6488c = str;
            this.f6489d = c0011a;
            this.f6490e = imageView;
            this.f6491f = avatarSize;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l */
        public /* synthetic */ void m6017l(C0011a c0011a, ImageView imageView, AvatarSize avatarSize) {
            AvatarDrawableCache.this.m6014s(c0011a, imageView, avatarSize);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: m, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r12) {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: n, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r52) {
            AvatarDrawableCache.this.f6479f.add(this.f6488c);
            Handler handler = AvatarDrawableCache.this.f6475b;
            final C0011a c0011a = this.f6489d;
            final ImageView imageView = this.f6490e;
            final AvatarSize avatarSize = this.f6491f;
            handler.post(new Runnable() { // from class: com.cyberlink.meeting.page.m.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7053b.m6017l(c0011a, imageView, avatarSize);
                }
            });
        }
    }

    public AvatarDrawableCache(Context context, LongSparseArray<Friend> longSparseArray, LongSparseArray<InviteeList.Invitee> longSparseArray2) {
        this.f6474a = context;
        this.f6476c = longSparseArray;
        this.f6477d = longSparseArray2;
    }

    /* renamed from: n */
    public static /* synthetic */ void m6001n(ImageView imageView, C0011a c0011a, Drawable drawable) {
        Object tag = imageView.getTag(R.id.tag_UserId);
        if ((tag instanceof Integer) && ((Integer) tag).intValue() == c0011a.f63b) {
            C6136j.m23591k(imageView.getContext(), imageView);
            imageView.setImageDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m6002o(String str, final C0011a c0011a, AvatarSize avatarSize, final ImageView imageView) {
        final Drawable drawable;
        if (this.f6478e.containsKey(str)) {
            drawable = this.f6478e.get(str);
        } else {
            Drawable drawableM6006i = m6006i(c0011a, avatarSize);
            this.f6478e.put(str, drawableM6006i);
            drawable = drawableM6006i;
        }
        this.f6475b.post(new Runnable() { // from class: p2.a
            @Override // java.lang.Runnable
            public final void run() {
                AvatarDrawableCache.m6001n(imageView, c0011a, drawable);
            }
        });
    }

    /* renamed from: f */
    public final String m6003f() {
        String strM15642G = FriendsClient.m15642G("info", "userAvatarHostGCP");
        if (C5170o0.m20170e(strM15642G)) {
            return null;
        }
        return strM15642G;
    }

    public void finalize() throws Throwable {
        super.finalize();
        this.f6480g.shutdown();
    }

    /* renamed from: g */
    public final String m6004g() {
        String strM15642G = FriendsClient.m15642G("info", "userAvatarHost");
        if (C5170o0.m20170e(strM15642G)) {
            return null;
        }
        return strM15642G;
    }

    /* renamed from: h */
    public final String m6005h(long j9, long j10) {
        if (j9 <= 0) {
            throw new IllegalArgumentException("Invalid argument value: " + j9);
        }
        Friend friend = this.f6476c.get(j9);
        if (friend != null && !C5170o0.m20170e(friend.f13647e)) {
            return friend.f13647e;
        }
        String strM6003f = C2143a.m12415o(j9) ? m6003f() : m6004g();
        if (strM6003f == null) {
            return null;
        }
        return strM6003f + "/01/" + (j9 % 5203) + "/" + j9 + "/avatar.jpg?" + j10;
    }

    /* renamed from: i */
    public final Drawable m6006i(C0011a c0011a, AvatarSize avatarSize) {
        return C6275a.m24032a().mo24045a(m6009l(c0011a), m6007j(c0011a), ((int) this.f6474a.getResources().getDimension(avatarSize.dimenResId)) / 2);
    }

    /* renamed from: j */
    public final int m6007j(C0011a c0011a) {
        return f6473i.get(m6008k(c0011a)).intValue();
    }

    /* renamed from: k */
    public final int m6008k(C0011a c0011a) {
        return c0011a.f63b % f6473i.size();
    }

    /* renamed from: l */
    public final String m6009l(C0011a c0011a) {
        String strM68c = c0011a.m68c();
        if (C5170o0.m20169d(strM68c)) {
            strM68c = StringUtils.SPACE;
        }
        ArrayList<Pair<Integer, Integer>> arrayListM16483R = CLUtility.m16483R(strM68c);
        return strM68c.substring(0, (arrayListM16483R.isEmpty() || ((Integer) arrayListM16483R.get(0).first).intValue() != 0) ? 1 : ((Integer) arrayListM16483R.get(0).second).intValue()).toUpperCase();
    }

    /* renamed from: m */
    public final String m6010m(C0011a c0011a, AvatarSize avatarSize) {
        return avatarSize.name() + "_" + m6009l(c0011a) + "_" + m6008k(c0011a);
    }

    /* renamed from: p */
    public void m6011p(C0011a c0011a) {
        this.f6481h = c0011a;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /* renamed from: q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m6012q(C0011a c0011a, ImageView imageView, AvatarSize avatarSize) {
        int i9 = c0011a.f63b;
        C0011a c0011a2 = this.f6481h;
        String strM6005h = null;
        if (i9 != c0011a2.f63b) {
            long j9 = c0011a.f65d;
            if (j9 == c0011a2.f65d) {
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(this.f6474a);
                if (Globals.m7388i0().m7464O1()) {
                    strM6005h = (userInfoM16497V0 == null || C5170o0.m20170e(userInfoM16497V0.f13779d)) ? m6005h(c0011a.f65d, c0011a.f63b) : userInfoM16497V0.f13779d;
                }
            } else if (j9 > 0) {
                strM6005h = this.f6477d.get(j9) != null ? this.f6477d.get(c0011a.f65d).avatar : m6005h(c0011a.f65d, c0011a.f63b);
            }
        }
        if (strM6005h != null && this.f6479f.contains(strM6005h)) {
            m6014s(c0011a, imageView, avatarSize);
        } else {
            C6127a.m23475p(imageView.getContext(), imageView, strM6005h, R.drawable.pic_default, new C1289a(strM6005h, c0011a, imageView, avatarSize));
        }
    }

    /* renamed from: r */
    public void m6013r(C0011a c0011a, ImageView imageView, AvatarSize avatarSize) {
        imageView.setTag(R.id.tag_UserId, Integer.valueOf(c0011a.f63b));
        if (c0011a.f65d > 0) {
            m6012q(c0011a, imageView, avatarSize);
        } else {
            m6014s(c0011a, imageView, avatarSize);
        }
    }

    /* renamed from: s */
    public final void m6014s(final C0011a c0011a, final ImageView imageView, final AvatarSize avatarSize) {
        final String strM6010m = m6010m(c0011a, avatarSize);
        Drawable drawable = this.f6478e.get(strM6010m);
        if (drawable != null) {
            C6136j.m23591k(imageView.getContext(), imageView);
            imageView.setImageDrawable(drawable);
        } else {
            C6127a.m23464e(imageView.getContext(), imageView, R.drawable.pic_default);
            this.f6480g.execute(new Runnable() { // from class: com.cyberlink.meeting.page.m.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7048b.m6002o(strM6010m, c0011a, avatarSize, imageView);
                }
            });
        }
    }
}
