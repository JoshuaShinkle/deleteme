package com.cyberlink.meeting.page.p032m;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.C0487p;
import com.cyberlink.clrtc.model.ChatMsg;
import com.cyberlink.meeting.page.p032m.AvatarDrawableCache;
import com.cyberlink.meeting.page.p032m.C1375e;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.message.SelfDestructView;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.transcode.TranscodeUtility;
import com.cyberlink.you.utility.CLUtility;
import com.rockerhieu.emojicon.EmojiconTextView;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p003a2.C0011a;
import p096i4.C5050a;
import p116k4.C5169o;
import p164p2.C5926f;
import p173q2.C6136j;
import p209u2.C6385v;
import p245y2.C6598a;
import p254z2.C6819c;
import p254z2.C6820d;
import p254z2.C6821e;
import p254z2.C6822f;

/* renamed from: com.cyberlink.meeting.page.m.e */
/* loaded from: classes.dex */
public final class C1375e extends BaseAdapter {

    /* renamed from: f */
    public final Activity f7066f;

    /* renamed from: g */
    public final AvatarDrawableCache f7067g;

    /* renamed from: j */
    public final int f7070j;

    /* renamed from: k */
    public final C6820d f7071k;

    /* renamed from: l */
    public C0011a f7072l;

    /* renamed from: m */
    public c f7073m;

    /* renamed from: n */
    public c f7074n;

    /* renamed from: o */
    public c f7075o;

    /* renamed from: b */
    public final int f7062b = (int) (((Globals.m7388i0().getResources().getDisplayMetrics().widthPixels * 0.53d) * m7123l()) * 1.02d);

    /* renamed from: c */
    public final int f7063c = (int) (((Globals.m7388i0().getResources().getDisplayMetrics().heightPixels * 0.4d) * m7123l()) * 1.02d);

    /* renamed from: d */
    public final int f7064d = Math.round(TypedValue.applyDimension(1, 270.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: e */
    public int f7065e = Math.round(TypedValue.applyDimension(1, 180.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: i */
    public final List<MessageObj> f7069i = new ArrayList();

    /* renamed from: h */
    public final C0487p<ChatMsg> f7068h = m7121j();

    /* renamed from: com.cyberlink.meeting.page.m.e$a */
    public class a extends C0487p.b<ChatMsg> {
        public a() {
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: a */
        public void mo2761a(int i9, int i10) {
            C1375e.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: b */
        public void mo2762b(int i9, int i10) {
            C1375e.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.InterfaceC0481j
        /* renamed from: c */
        public void mo2763c(int i9, int i10) {
            C1375e.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: h */
        public void mo2933h(int i9, int i10) {
            C1375e.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public boolean mo2930e(ChatMsg chatMsg, ChatMsg chatMsg2) {
            return chatMsg.equals(chatMsg2);
        }

        @Override // androidx.recyclerview.widget.C0487p.b
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public boolean mo2931f(ChatMsg chatMsg, ChatMsg chatMsg2) {
            return chatMsg.equals(chatMsg2);
        }

        @Override // androidx.recyclerview.widget.C0487p.b, java.util.Comparator
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public int compare(ChatMsg chatMsg, ChatMsg chatMsg2) {
            return chatMsg.compareTo(chatMsg2);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.e$b */
    public static class b extends C6819c {

        /* renamed from: c0 */
        public final boolean f7077c0;

        public b(boolean z8) {
            this.f7077c0 = z8;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.e$c */
    public interface c {
        /* renamed from: a */
        void mo7137a(C5926f c5926f);
    }

    public C1375e(Activity activity, AvatarDrawableCache avatarDrawableCache) {
        this.f7066f = activity;
        this.f7067g = avatarDrawableCache;
        this.f7070j = Math.round(TypedValue.applyDimension(1, 32.0f, activity.getResources().getDisplayMetrics()));
        this.f7071k = new C6820d(activity);
    }

    /* renamed from: G */
    public static void m7101G(final C2973l0 c2973l0) {
        C6385v.m24526d(new Runnable() { // from class: p2.h6
            @Override // java.lang.Runnable
            public final void run() {
                C1375e.m7109s(c2973l0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ void m7107q(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            m7106p((ChatMsg) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m7108r(int i9, ChatMsg chatMsg, int i10, View view) {
        if (i9 < 0 || i9 >= this.f7068h.m2926u()) {
            return;
        }
        C5926f c5926f = new C5926f(chatMsg.f5388h);
        if (i10 == 3 || i10 == 24) {
            c cVar = this.f7073m;
            if (cVar != null) {
                cVar.mo7137a(c5926f);
                return;
            }
            return;
        }
        if (i10 != 33) {
            if (i10 != 37) {
                if (i10 != 12) {
                    if (i10 != 13) {
                        return;
                    }
                }
            }
            c cVar2 = this.f7075o;
            if (cVar2 != null) {
                cVar2.mo7137a(c5926f);
                return;
            }
            return;
        }
        c cVar3 = this.f7074n;
        if (cVar3 != null) {
            cVar3.mo7137a(c5926f);
        }
    }

    /* renamed from: s */
    public static /* synthetic */ void m7109s(C2973l0 c2973l0) {
        Bitmap bitmapM7145d = MediaLoader.m7145d(Globals.m7372O(), c2973l0, true);
        if (bitmapM7145d == null) {
            return;
        }
        c2973l0.m15124K(bitmapM7145d.getWidth());
        c2973l0.m15121H(bitmapM7145d.getHeight());
        ArrayList arrayList = new ArrayList();
        arrayList.add("Height");
        arrayList.add("Width");
        C2950b0.m14914m().m14699I(c2973l0.m15144p(), c2973l0, arrayList);
    }

    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final void m7106p(ChatMsg chatMsg) {
        if (m7125n(chatMsg.f5388h)) {
            C2950b0.m14914m().m14701K(new C5926f(chatMsg.f5388h).m23392n());
        }
    }

    /* renamed from: B */
    public void m7111B(ChatMsg chatMsg, ChatMsg chatMsg2) {
        int iM2919n = this.f7068h.m2919n(chatMsg);
        if (-1 != iM2919n) {
            this.f7068h.m2929x(iM2919n, chatMsg2);
            return;
        }
        if (m7125n(chatMsg.f5388h) && this.f7069i != null) {
            C5926f c5926f = new C5926f(chatMsg.f5388h);
            ListIterator<MessageObj> listIterator = this.f7069i.listIterator();
            while (true) {
                if (!listIterator.hasNext()) {
                    break;
                }
                long j9 = NumberUtils.toLong(listIterator.next().m14747I("mediaId"), -1L);
                if (j9 != -1 && j9 == c5926f.m23383e()) {
                    listIterator.remove();
                    break;
                }
            }
        }
        this.f7068h.m2906a(chatMsg2);
    }

    /* renamed from: C */
    public void m7112C(MessageObj messageObj) {
        ListIterator<MessageObj> listIterator = this.f7069i.listIterator();
        while (listIterator.hasNext()) {
            MessageObj next = listIterator.next();
            if (next.m14777o().compareTo(messageObj.m14777o()) == 0) {
                next.m14771g0(messageObj);
                return;
            }
        }
    }

    /* renamed from: D */
    public final void m7113D(ChatMsg chatMsg, C6819c c6819c) {
        if (chatMsg.m5013d() == ChatMsg.Status.NORMAL) {
            View view = c6819c.f22594J;
            if (view != null) {
                view.setVisibility(8);
            }
            ImageView imageView = c6819c.f22593I;
            if (imageView != null) {
                imageView.setVisibility(8);
                return;
            }
            return;
        }
        if (chatMsg.m5013d() == ChatMsg.Status.SENDING) {
            View view2 = c6819c.f22594J;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            ImageView imageView2 = c6819c.f22593I;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
                return;
            }
            return;
        }
        if (chatMsg.m5013d() == ChatMsg.Status.FAILED) {
            View view3 = c6819c.f22594J;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            ImageView imageView3 = c6819c.f22593I;
            if (imageView3 != null) {
                imageView3.setVisibility(0);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0200  */
    /* renamed from: E */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m7114E(ChatMsg chatMsg, int i9, C6819c c6819c, int i10) {
        String str;
        int iM23390l;
        int iM23380b;
        int iM23390l2;
        int iM23380b2;
        C5926f c5926f = new C5926f(chatMsg.f5388h);
        C2973l0 c2973l0M23392n = c5926f.m23392n();
        if (!C6598a.m25244f(21, i9)) {
            if (C6598a.m25244f(0, i9)) {
                ImageView imageView = c6819c.f22593I;
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                TextView textView = c6819c.f22623k;
                if (textView != null) {
                    str = "ex:";
                    textView.setText(CLUtility.m16454J2(new Date(chatMsg.f5385e)));
                    C5169o.m20164i(c6819c.f22623k);
                } else {
                    str = "ex:";
                }
                View view = c6819c.f22594J;
                if (view != null) {
                    view.setVisibility(8);
                }
                if (i9 != 3) {
                    if (i9 != 12) {
                        if (i9 != 13) {
                            return;
                        }
                        c6819c.f22624l.setVisibility(8);
                        String strM23384f = c5926f.m23384f();
                        String str2 = this.f7066f.getResources().getString(R.string.file_size) + ": " + CLUtility.m16592u0(this.f7066f, c5926f.m23385g());
                        c6819c.f22620h.setText(strM23384f);
                        c6819c.f22631s.setText(str2);
                        c6819c.f22618f.setImageResource(CLUtility.m16560m0(strM23384f));
                        return;
                    }
                    c6819c.f22591G.setVisibility(4);
                    c6819c.f22624l.setVisibility(8);
                    if (c2973l0M23392n == null) {
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.f7066f.getResources().getDrawable(R.drawable.icon_photo_failed);
                        int height = bitmapDrawable.getBitmap().getHeight();
                        float width = bitmapDrawable.getBitmap().getWidth();
                        float f9 = this.f7062b / width;
                        int i11 = (int) (width * f9);
                        float f10 = height;
                        int i12 = (int) (f9 * f10);
                        int i13 = this.f7063c;
                        if (i12 > i13) {
                            i11 = (int) (width * (i13 / f10));
                            i12 = i13;
                        }
                        c6819c.f22615c.getLayoutParams().height = i12;
                        c6819c.f22615c.getLayoutParams().width = i11;
                        C6136j.m23593m(this.f7066f, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                        return;
                    }
                    int iM15151w = c2973l0M23392n.m15151w();
                    int iM15141m = c2973l0M23392n.m15141m();
                    if (iM15151w == 0 || iM15141m == 0) {
                        c6819c.f22615c.getLayoutParams().height = this.f7065e;
                        c6819c.f22615c.getLayoutParams().width = this.f7064d;
                        m7101G(c2973l0M23392n);
                    } else {
                        float f11 = iM15151w;
                        float f12 = this.f7062b / f11;
                        int i14 = (int) (f11 * f12);
                        float f13 = iM15141m;
                        int i15 = (int) (f12 * f13);
                        int i16 = this.f7063c;
                        if (i15 > i16) {
                            i14 = (int) (f11 * (i16 / f13));
                            i15 = i16;
                        }
                        c6819c.f22615c.getLayoutParams().height = i15;
                        c6819c.f22615c.getLayoutParams().width = i14;
                    }
                    MediaLoader.m7156o(this.f7066f, c6819c.f22615c, c2973l0M23392n, MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                    return;
                }
                c6819c.f22591G.setVisibility(4);
                c6819c.f22624l.setVisibility(8);
                String strM23387i = c5926f.m23387i();
                if (TextUtils.isEmpty(strM23387i)) {
                    BitmapDrawable bitmapDrawable2 = (BitmapDrawable) Globals.m7388i0().getResources().getDrawable(R.drawable.icon_photo_failed);
                    int height2 = bitmapDrawable2.getBitmap().getHeight();
                    float width2 = bitmapDrawable2.getBitmap().getWidth();
                    float f14 = this.f7062b / width2;
                    int i17 = (int) (width2 * f14);
                    float f15 = height2;
                    int i18 = (int) (f14 * f15);
                    int i19 = this.f7063c;
                    if (i18 > i19) {
                        i17 = (int) (width2 * (i19 / f15));
                        i18 = i19;
                    }
                    c6819c.f22615c.getLayoutParams().height = i18;
                    c6819c.f22615c.getLayoutParams().width = i17;
                    C6136j.m23593m(this.f7066f, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                    return;
                }
                try {
                    iM23390l = c5926f.m23390l();
                    try {
                        iM23380b = c5926f.m23380b();
                    } catch (NumberFormatException e9) {
                        e = e9;
                        Log.d("MessageAdapter", str + e.getMessage());
                        iM23380b = 0;
                        if (iM23390l != 0) {
                            c6819c.f22615c.getLayoutParams().height = this.f7065e;
                            c6819c.f22615c.getLayoutParams().width = this.f7064d;
                        }
                        C2973l0.a aVar = new C2973l0.a();
                        aVar.f13200d = strM23387i;
                        MediaLoader.m7156o(this.f7066f, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar, aVar, iM23390l, iM23380b, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                        return;
                    }
                } catch (NumberFormatException e10) {
                    e = e10;
                    iM23390l = 0;
                }
                if (iM23390l != 0 || iM23380b == 0) {
                    c6819c.f22615c.getLayoutParams().height = this.f7065e;
                    c6819c.f22615c.getLayoutParams().width = this.f7064d;
                } else {
                    float f16 = iM23390l;
                    float f17 = this.f7062b / f16;
                    int i20 = (int) (f16 * f17);
                    float f18 = iM23380b;
                    int i21 = (int) (f17 * f18);
                    int i22 = this.f7063c;
                    if (i21 > i22) {
                        i20 = (int) (f16 * (i22 / f18));
                        i21 = i22;
                    }
                    c6819c.f22615c.getLayoutParams().height = i21;
                    c6819c.f22615c.getLayoutParams().width = i20;
                }
                C2973l0.a aVar2 = new C2973l0.a();
                aVar2.f13200d = strM23387i;
                MediaLoader.m7156o(this.f7066f, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar2, aVar2, iM23390l, iM23380b, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                return;
            }
            return;
        }
        c6819c.f22611a.setText(chatMsg.m5014e().m68c());
        c6819c.f22623k.setText(CLUtility.m16454J2(new Date(chatMsg.f5385e)));
        this.f7067g.m6013r(chatMsg.m5014e(), c6819c.f22613b, AvatarDrawableCache.AvatarSize.SMALL);
        C5169o.m20164i(c6819c.f22623k);
        if (i9 != 24) {
            if (i9 != 33) {
                if (i9 != 37) {
                    return;
                }
                String strM23384f2 = c5926f.m23384f();
                String str3 = this.f7066f.getString(R.string.file_size) + ": " + CLUtility.m16592u0(this.f7066f, c5926f.m23385g());
                c6819c.f22625m.setVisibility(8);
                c6819c.f22620h.setText(strM23384f2);
                c6819c.f22631s.setText(str3);
                c6819c.f22618f.setImageResource(CLUtility.m16560m0(strM23384f2));
                return;
            }
            if (c2973l0M23392n == null) {
                BitmapDrawable bitmapDrawable3 = (BitmapDrawable) this.f7066f.getResources().getDrawable(R.drawable.icon_photo_failed);
                int height3 = bitmapDrawable3.getBitmap().getHeight();
                float width3 = bitmapDrawable3.getBitmap().getWidth();
                float f19 = this.f7062b / width3;
                int i23 = (int) (width3 * f19);
                float f20 = height3;
                int i24 = (int) (f19 * f20);
                int i25 = this.f7063c;
                if (i24 > i25) {
                    i23 = (int) (width3 * (i25 / f20));
                    i24 = i25;
                }
                c6819c.f22615c.getLayoutParams().height = i24;
                c6819c.f22615c.getLayoutParams().width = i23;
                C6136j.m23593m(this.f7066f, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                return;
            }
            int iM15151w2 = c2973l0M23392n.m15151w();
            int iM15141m2 = c2973l0M23392n.m15141m();
            if (iM15151w2 == 0 || iM15141m2 == 0) {
                c6819c.f22615c.getLayoutParams().height = this.f7065e;
                c6819c.f22615c.getLayoutParams().width = this.f7064d;
                m7101G(c2973l0M23392n);
            } else {
                float f21 = iM15151w2;
                float f22 = this.f7062b / f21;
                int i26 = (int) (f21 * f22);
                float f23 = iM15141m2;
                int i27 = (int) (f22 * f23);
                int i28 = this.f7063c;
                if (i27 > i28) {
                    i26 = (int) (f21 * (i28 / f23));
                    i27 = i28;
                }
                c6819c.f22615c.getLayoutParams().height = i27;
                c6819c.f22615c.getLayoutParams().width = i26;
            }
            MediaLoader.m7156o(this.f7066f, c6819c.f22615c, c2973l0M23392n, MediaLoader.Option.ROUNDED_THUMBNAIL);
            return;
        }
        c6819c.f22591G.setVisibility(4);
        if (!TextUtils.isEmpty(c2973l0M23392n.m15147s())) {
            int iM23390l3 = c5926f.m23390l();
            int iM23380b3 = c5926f.m23380b();
            if (iM23390l3 == 0 || iM23380b3 == 0) {
                c6819c.f22615c.getLayoutParams().height = this.f7065e;
                c6819c.f22615c.getLayoutParams().width = this.f7064d;
                m7101G(c2973l0M23392n);
            } else {
                float f24 = iM23390l3;
                float f25 = this.f7062b / f24;
                int i29 = (int) (f24 * f25);
                float f26 = iM23380b3;
                int i30 = (int) (f25 * f26);
                int i31 = this.f7063c;
                if (i30 > i31) {
                    i29 = (int) (f24 * (i31 / f26));
                    i30 = i31;
                }
                c6819c.f22615c.getLayoutParams().height = i30;
                c6819c.f22615c.getLayoutParams().width = i29;
            }
            MediaLoader.m7156o(this.f7066f, c6819c.f22615c, c2973l0M23392n, MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
            return;
        }
        if (TextUtils.isEmpty(c5926f.m23387i())) {
            BitmapDrawable bitmapDrawable4 = (BitmapDrawable) this.f7066f.getResources().getDrawable(R.drawable.icon_photo_failed);
            int height4 = bitmapDrawable4.getBitmap().getHeight();
            float width4 = bitmapDrawable4.getBitmap().getWidth();
            float f27 = this.f7062b / width4;
            int i32 = (int) (width4 * f27);
            float f28 = height4;
            int i33 = (int) (f27 * f28);
            int i34 = this.f7063c;
            if (i33 > i34) {
                i32 = (int) (width4 * (i34 / f28));
                i33 = i34;
            }
            c6819c.f22615c.getLayoutParams().height = i33;
            c6819c.f22615c.getLayoutParams().width = i32;
            C6136j.m23593m(this.f7066f, c6819c.f22615c, R.drawable.icon_photo_failed, true);
            return;
        }
        try {
            iM23390l2 = c5926f.m23390l();
        } catch (NumberFormatException e11) {
            e = e11;
            iM23390l2 = 0;
        }
        try {
            iM23380b2 = c5926f.m23380b();
        } catch (NumberFormatException e12) {
            e = e12;
            Log.d("MessageAdapter", "ex:" + e.getMessage());
            iM23380b2 = 0;
            if (iM23390l2 != 0) {
                c6819c.f22615c.getLayoutParams().height = this.f7065e;
                c6819c.f22615c.getLayoutParams().width = this.f7064d;
            }
            C2973l0.a aVar3 = new C2973l0.a();
            aVar3.f13200d = c5926f.m23387i();
            MediaLoader.m7156o(this.f7066f, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar3, aVar3, iM23390l2, iM23380b2, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
        }
        if (iM23390l2 != 0 || iM23380b2 == 0) {
            c6819c.f22615c.getLayoutParams().height = this.f7065e;
            c6819c.f22615c.getLayoutParams().width = this.f7064d;
        } else {
            float f29 = iM23390l2;
            float f30 = this.f7062b / f29;
            int i35 = (int) (f29 * f30);
            float f31 = iM23380b2;
            int i36 = (int) (f30 * f31);
            int i37 = this.f7063c;
            if (i36 > i37) {
                i35 = (int) (f29 * (i37 / f31));
                i36 = i37;
            }
            c6819c.f22615c.getLayoutParams().height = i36;
            c6819c.f22615c.getLayoutParams().width = i35;
        }
        C2973l0.a aVar32 = new C2973l0.a();
        aVar32.f13200d = c5926f.m23387i();
        MediaLoader.m7156o(this.f7066f, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar32, aVar32, iM23390l2, iM23380b2, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
    }

    /* JADX WARN: Removed duplicated region for block: B:137:0x033d  */
    /* renamed from: F */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m7115F(MessageObj messageObj, int i9, C6819c c6819c, int i10) throws JSONException, NumberFormatException, FileNotFoundException {
        int i11;
        int i12 = 0;
        if (C6598a.m25244f(0, i9)) {
            if (C2925v.m14600i0(messageObj)) {
                ImageView imageView = c6819c.f22593I;
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                TextView textView = c6819c.f22623k;
                if (textView != null) {
                    textView.setText(CLUtility.m16454J2(messageObj.m14788z()));
                    C5169o.m20164i(c6819c.f22623k);
                }
                View view = c6819c.f22594J;
                if (view != null) {
                    view.setVisibility(8);
                }
            } else if (C2925v.m14596g0(messageObj)) {
                ImageView imageView2 = c6819c.f22593I;
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                }
                TextView textView2 = c6819c.f22623k;
                if (textView2 != null) {
                    textView2.setText("");
                }
                View view2 = c6819c.f22594J;
                if (view2 != null) {
                    view2.setVisibility(8);
                }
            } else if (C2925v.m14598h0(messageObj) || C2925v.m14602j0(messageObj)) {
                ImageView imageView3 = c6819c.f22593I;
                if (imageView3 != null) {
                    imageView3.setVisibility(8);
                }
                TextView textView3 = c6819c.f22623k;
                if (textView3 != null) {
                    textView3.setText("");
                }
                View view3 = c6819c.f22594J;
                if (view3 != null) {
                    view3.setVisibility(8);
                }
            } else {
                ImageView imageView4 = c6819c.f22593I;
                if (imageView4 != null) {
                    imageView4.setVisibility(8);
                }
                TextView textView4 = c6819c.f22623k;
                if (textView4 != null) {
                    textView4.setText("");
                }
                View view4 = c6819c.f22594J;
                if (view4 != null) {
                    view4.setVisibility(8);
                }
            }
        } else if (C6598a.m25244f(21, i9)) {
            c6819c.f22623k.setText(CLUtility.m16454J2(messageObj.m14788z()));
            C5169o.m20164i(c6819c.f22623k);
        }
        if (i9 == 3) {
            c6819c.f22591G.setVisibility(4);
            if (C2925v.m14598h0(messageObj)) {
                c6819c.f22624l.setVisibility(0);
                if (messageObj.m14740B().equals("2") && messageObj.m14744F().equals("3")) {
                    ProgressBar progressBar = c6819c.f22624l;
                    progressBar.setProgress(progressBar.getMax());
                    c6819c.f22623k.setText(CLUtility.m16454J2(messageObj.m14788z()));
                } else {
                    c6819c.f22624l.setProgress(messageObj.f12959z);
                }
                m7132y(c6819c, messageObj, i9);
                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
            } else if (messageObj.m14744F().equals("4") || messageObj.m14740B().equals("3")) {
                c6819c.f22624l.setVisibility(8);
                m7132y(c6819c, messageObj, i9);
                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
            } else {
                c6819c.f22624l.setVisibility(8);
                String strM14747I = messageObj.m14747I("imageUrl");
                if (TextUtils.isEmpty(strM14747I)) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) Globals.m7388i0().getResources().getDrawable(R.drawable.icon_photo_failed);
                    int height = bitmapDrawable.getBitmap().getHeight();
                    float width = bitmapDrawable.getBitmap().getWidth();
                    float f9 = this.f7062b / width;
                    int i13 = (int) (width * f9);
                    float f10 = height;
                    int i14 = (int) (f9 * f10);
                    int i15 = this.f7063c;
                    if (i14 > i15) {
                        i13 = (int) (width * (i15 / f10));
                        i14 = i15;
                    }
                    c6819c.f22615c.getLayoutParams().height = i14;
                    c6819c.f22615c.getLayoutParams().width = i13;
                    C6136j.m23593m(this.f7066f, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                } else {
                    try {
                        i11 = Integer.parseInt(messageObj.m14747I("width"));
                        try {
                            i12 = Integer.parseInt(messageObj.m14747I("height"));
                        } catch (NumberFormatException e9) {
                            e = e9;
                            Log.d("MessageAdapter", "ex:" + e.getMessage());
                            if (i11 != 0) {
                                c6819c.f22615c.getLayoutParams().height = this.f7065e;
                                c6819c.f22615c.getLayoutParams().width = this.f7064d;
                                C2973l0.a aVar = new C2973l0.a();
                                aVar.f13200d = strM14747I;
                                MediaLoader.m7156o(this.f7066f, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar, aVar, i11, i12, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                            }
                            m7133z(c6819c);
                        }
                    } catch (NumberFormatException e10) {
                        e = e10;
                        i11 = 0;
                    }
                    if (i11 != 0 || i12 == 0) {
                        c6819c.f22615c.getLayoutParams().height = this.f7065e;
                        c6819c.f22615c.getLayoutParams().width = this.f7064d;
                    } else {
                        float f11 = i11;
                        float f12 = this.f7062b / f11;
                        int i16 = (int) (f11 * f12);
                        float f13 = i12;
                        int i17 = (int) (f12 * f13);
                        int i18 = this.f7063c;
                        if (i17 > i18) {
                            i16 = (int) (f11 * (i18 / f13));
                            i17 = i18;
                        }
                        c6819c.f22615c.getLayoutParams().height = i17;
                        c6819c.f22615c.getLayoutParams().width = i16;
                    }
                    C2973l0.a aVar2 = new C2973l0.a();
                    aVar2.f13200d = strM14747I;
                    MediaLoader.m7156o(this.f7066f, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar2, aVar2, i11, i12, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                }
            }
        } else if (i9 == 9) {
            RelativeLayout relativeLayout = c6819c.f22638z;
            if (relativeLayout != null) {
                relativeLayout.setTag(Integer.valueOf(i10));
            }
            c6819c.f22617e.setVisibility(0);
            c6819c.f22586B.setVisibility(8);
            try {
                c6819c.f22620h.setText(CLUtility.m16531f(new JSONObject(messageObj.m14779q()).getString("duration")));
            } catch (Exception unused) {
                Log.d("MessageAdapter", "Parse audio content duration exception");
            }
            if (messageObj.m14753O()) {
                RelativeLayout relativeLayout2 = c6819c.f22638z;
                if (relativeLayout2 != null) {
                    relativeLayout2.setBackgroundResource(R.drawable.chat_bg_r_selfd);
                }
            } else {
                RelativeLayout relativeLayout3 = c6819c.f22638z;
                if (relativeLayout3 != null) {
                    relativeLayout3.setBackgroundResource(R.drawable.chat_bg_r_s2);
                }
            }
            if (C2925v.m14598h0(messageObj)) {
                c6819c.f22624l.setVisibility(0);
                c6819c.f22624l.setProgress(messageObj.f12959z);
            } else {
                c6819c.f22624l.setVisibility(8);
            }
        } else if (i9 == 12) {
            c6819c.f22591G.setVisibility(4);
            if (C2925v.m14598h0(messageObj)) {
                c6819c.f22624l.setVisibility(0);
                c6819c.f22624l.setProgress(messageObj.f12959z);
                m7132y(c6819c, messageObj, i9);
                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
            } else if (messageObj.m14744F().equals("4") || messageObj.m14740B().equals("3")) {
                c6819c.f22624l.setVisibility(8);
                m7132y(c6819c, messageObj, i9);
                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
            } else {
                c6819c.f22624l.setVisibility(8);
                if (C2950b0.m14914m().m14725v(NumberUtils.toLong(messageObj.m14747I("mediaId"), -1L)) != null) {
                    m7132y(c6819c, messageObj, i9);
                } else {
                    BitmapDrawable bitmapDrawable2 = (BitmapDrawable) this.f7066f.getResources().getDrawable(R.drawable.icon_photo_failed);
                    int height2 = bitmapDrawable2.getBitmap().getHeight();
                    float width2 = bitmapDrawable2.getBitmap().getWidth();
                    float f14 = this.f7062b / width2;
                    int i19 = (int) (width2 * f14);
                    float f15 = height2;
                    int i20 = (int) (f14 * f15);
                    int i21 = this.f7063c;
                    if (i20 > i21) {
                        i19 = (int) (width2 * (i21 / f15));
                        i20 = i21;
                    }
                    c6819c.f22615c.getLayoutParams().height = i20;
                    c6819c.f22615c.getLayoutParams().width = i19;
                    C6136j.m23593m(this.f7066f, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                }
            }
            String strM14747I2 = messageObj.m14747I("duration");
            if (strM14747I2 != null) {
                c6819c.f22630r.setText(CLUtility.m16519c(strM14747I2));
            }
        } else if (i9 == 13) {
            try {
                if (C2925v.m14598h0(messageObj)) {
                    c6819c.f22624l.setVisibility(0);
                    c6819c.f22624l.setProgress(messageObj.f12959z);
                } else {
                    c6819c.f22624l.setVisibility(8);
                }
                JSONObject jSONObject = new JSONObject(messageObj.m14779q());
                String string = jSONObject.getString("mediaName");
                String str = this.f7066f.getResources().getString(R.string.file_size) + ": " + CLUtility.m16592u0(this.f7066f, Long.parseLong(jSONObject.getString("mediaSize")));
                c6819c.f22620h.setText(string);
                c6819c.f22631s.setText(str);
                c6819c.f22618f.setImageResource(CLUtility.m16560m0(string));
            } catch (JSONException e11) {
                e11.printStackTrace();
            }
        }
        m7133z(c6819c);
    }

    /* renamed from: e */
    public void m7116e(final ChatMsg chatMsg) {
        new Thread(new Runnable() { // from class: p2.g6
            @Override // java.lang.Runnable
            public final void run() {
                this.f20452b.m7106p(chatMsg);
            }
        }).start();
        this.f7068h.m2906a(chatMsg);
        notifyDataSetChanged();
    }

    /* renamed from: f */
    public void m7117f(final Collection<ChatMsg> collection) {
        new Thread(new Runnable() { // from class: p2.i6
            @Override // java.lang.Runnable
            public final void run() {
                this.f20477b.m7107q(collection);
            }
        }).start();
        this.f7068h.m2908c(collection);
        notifyDataSetChanged();
    }

    /* renamed from: g */
    public void m7118g(MessageObj messageObj) {
        this.f7069i.add(messageObj);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f7068h.m2926u() + this.f7069i.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i9) {
        if (i9 < 0) {
            return null;
        }
        if (i9 < this.f7068h.m2926u()) {
            return this.f7068h.m2918m(i9);
        }
        int iM2926u = i9 - this.f7068h.m2926u();
        if (iM2926u >= this.f7069i.size()) {
            return null;
        }
        return this.f7069i.get(iM2926u);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i9) {
        if (i9 >= this.f7068h.m2926u()) {
            MessageObj messageObj = (MessageObj) getItem(i9);
            if (messageObj == null) {
                return Long.MIN_VALUE;
            }
            return messageObj.m14774l();
        }
        if (((ChatMsg) getItem(i9)) == null) {
            return Long.MIN_VALUE;
        }
        return r4.f5384d;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0082 A[Catch: JSONException -> 0x008d, TRY_LEAVE, TryCatch #0 {JSONException -> 0x008d, blocks: (B:12:0x0023, B:33:0x006a, B:39:0x0076, B:45:0x0082, B:19:0x0044, B:22:0x004e, B:25:0x0058), top: B:57:0x0023 }] */
    @Override // android.widget.BaseAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getItemViewType(int i9) {
        char c9;
        if (i9 >= this.f7068h.m2926u()) {
            MessageObj messageObj = (MessageObj) getItem(i9);
            if (messageObj == null) {
                return 47;
            }
            return C6598a.m25239a(messageObj);
        }
        ChatMsg chatMsg = (ChatMsg) getItem(i9);
        String str = chatMsg.f5388h;
        if (str != null) {
            try {
                String string = new JSONObject(str).getString("mediaType");
                int iHashCode = string.hashCode();
                if (iHashCode == 2189724) {
                    if (string.equals("File")) {
                        c9 = 2;
                    }
                    if (c9 == 0) {
                    }
                } else if (iHashCode != 77090322) {
                    c9 = (iHashCode == 82650203 && string.equals("Video")) ? (char) 1 : (char) 65535;
                    if (c9 == 0) {
                        return m7126o(chatMsg) ? 3 : 24;
                    }
                    if (c9 == 1) {
                        return m7126o(chatMsg) ? 12 : 33;
                    }
                    if (c9 == 2) {
                        return m7126o(chatMsg) ? 13 : 37;
                    }
                } else {
                    if (string.equals("Photo")) {
                        c9 = 0;
                    }
                    if (c9 == 0) {
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return m7126o(chatMsg) ? 0 : 21;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003f A[PHI: r5
      0x003f: PHI (r5v11 com.cyberlink.meeting.page.m.e$b) = (r5v0 com.cyberlink.meeting.page.m.e$b), (r5v16 com.cyberlink.meeting.page.m.e$b) binds: [B:9:0x0032, B:11:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View getView(final int i9, View view, ViewGroup viewGroup) throws JSONException, NumberFormatException, FileNotFoundException {
        boolean z8;
        View viewM25450f;
        boolean z9;
        View viewM25442t;
        if (i9 < this.f7068h.m2926u()) {
            final ChatMsg chatMsg = (ChatMsg) getItem(i9);
            if (!m7125n(chatMsg.f5388h)) {
                boolean zM7126o = m7126o(chatMsg);
                z8 = view == null;
                if (view != null) {
                    bVar = (b) view.getTag();
                    z9 = bVar.f7077c0 == zM7126o ? z8 : true;
                }
                if (z9) {
                    bVar = new b(zM7126o);
                    LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f7066f);
                    if (bVar.f7077c0) {
                        viewM25442t = C6822f.m25461q(bVar, layoutInflaterFrom, viewGroup);
                        C5169o.m20161f(bVar.f22620h);
                    } else {
                        viewM25442t = C6821e.m25442t(bVar, layoutInflaterFrom, viewGroup);
                        C5169o.m20161f(bVar.f22620h);
                    }
                    bVar.f22596L.setVisibility(8);
                    bVar.f22628p.setVisibility(8);
                    viewM25442t.setTag(bVar);
                    this.f7066f.registerForContextMenu(bVar.f22620h);
                } else {
                    viewM25442t = view;
                }
                bVar.f22620h.setTag(R.id.tag_Position, Integer.valueOf(i9));
                if (zM7126o) {
                    if (C5050a.m19750c(this.f7066f, chatMsg.f5388h)) {
                        ((EmojiconTextView) bVar.f22620h).setEmojiconSize(this.f7070j);
                    }
                    bVar.f22620h.setText(chatMsg.f5388h);
                    bVar.f22621i.setBackgroundResource(R.drawable.chat_bg_r_s2);
                } else {
                    if (C5050a.m19750c(this.f7066f, chatMsg.f5388h)) {
                        ((EmojiconTextView) bVar.f22620h).setEmojiconSize(48);
                    }
                    bVar.f22621i.setBackgroundResource(R.drawable.chat_bg_l_r);
                    SelfDestructView selfDestructView = bVar.f22625m;
                    if (selfDestructView != null) {
                        selfDestructView.setVisibility(8);
                    }
                    bVar.f22620h.setText(chatMsg.f5388h);
                    bVar.f22611a.setText(chatMsg.m5014e().m68c());
                    this.f7067g.m6013r(chatMsg.m5014e(), bVar.f22613b, AvatarDrawableCache.AvatarSize.SMALL);
                }
                if (chatMsg.m5013d() == ChatMsg.Status.NORMAL) {
                    bVar.f22623k.setText(CLUtility.m16454J2(new Date(chatMsg.f5385e)));
                } else {
                    bVar.f22623k.setText("");
                }
                CLUtility.m16543i(bVar.f22620h);
                m7120i(bVar, getItemViewType(i9));
                m7113D(chatMsg, bVar);
                return viewM25442t;
            }
            final int itemViewType = getItemViewType(i9);
            z8 = view == null;
            bVar = view != null ? (b) view.getTag() : null;
            if (z8) {
                bVar = new b(true);
                LayoutInflater layoutInflaterFrom2 = LayoutInflater.from(this.f7066f);
                viewM25450f = itemViewType != 3 ? itemViewType != 9 ? itemViewType != 24 ? itemViewType != 33 ? itemViewType != 37 ? itemViewType != 12 ? itemViewType != 13 ? view : C6822f.m25450f(bVar, layoutInflaterFrom2, viewGroup) : C6822f.m25463s(bVar, layoutInflaterFrom2, viewGroup) : C6821e.m25429g(bVar, layoutInflaterFrom2, viewGroup) : C6821e.m25444v(bVar, layoutInflaterFrom2, viewGroup) : C6821e.m25435m(bVar, layoutInflaterFrom2, viewGroup) : C6822f.m25447c(bVar, layoutInflaterFrom2, viewGroup) : C6822f.m25455k(bVar, layoutInflaterFrom2, viewGroup);
                bVar.f22596L.setVisibility(8);
                bVar.f22628p.setVisibility(8);
                if (viewM25450f != null) {
                    viewM25450f.setTag(bVar);
                }
            } else {
                viewM25450f = view;
            }
            m7114E(chatMsg, itemViewType, bVar, i9);
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: p2.f6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f20436b.m7108r(i9, chatMsg, itemViewType, view2);
                }
            };
            if (itemViewType == 3 || itemViewType == 12 || itemViewType == 24 || itemViewType == 33) {
                this.f7066f.registerForContextMenu(bVar.f22615c);
                bVar.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i9));
                bVar.f22615c.setOnClickListener(onClickListener);
            } else if (itemViewType == 13 || itemViewType == 37) {
                this.f7066f.registerForContextMenu(bVar.f22638z);
                bVar.f22638z.setTag(R.id.tag_Position, Integer.valueOf(i9));
                bVar.f22638z.setOnClickListener(onClickListener);
            }
        } else {
            int itemViewType2 = getItemViewType(i9);
            z8 = view == null;
            bVar = view != null ? (b) view.getTag() : null;
            if (z8) {
                bVar = new b(true);
                LayoutInflater layoutInflaterFrom3 = LayoutInflater.from(this.f7066f);
                viewM25450f = itemViewType2 != 3 ? itemViewType2 != 9 ? itemViewType2 != 12 ? itemViewType2 != 13 ? view : C6822f.m25450f(bVar, layoutInflaterFrom3, viewGroup) : C6822f.m25463s(bVar, layoutInflaterFrom3, viewGroup) : C6822f.m25447c(bVar, layoutInflaterFrom3, viewGroup) : C6822f.m25455k(bVar, layoutInflaterFrom3, viewGroup);
                if (viewM25450f != null) {
                    viewM25450f.setTag(bVar);
                }
            } else {
                viewM25450f = view;
            }
            m7115F((MessageObj) getItem(i9), itemViewType2, bVar, i9);
        }
        return viewM25450f;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 53;
    }

    /* renamed from: h */
    public void m7119h(List<MessageObj> list) {
        this.f7069i.addAll(list);
        notifyDataSetChanged();
    }

    /* renamed from: i */
    public final void m7120i(C6819c c6819c, int i9) {
        int iM25422n = this.f7071k.m25422n(i9, false);
        if (c6819c.f22632t == iM25422n || iM25422n <= 0) {
            return;
        }
        c6819c.f22632t = iM25422n;
        c6819c.f22620h.setMaxWidth(iM25422n);
    }

    /* renamed from: j */
    public final C0487p<ChatMsg> m7121j() {
        return new C0487p<>(ChatMsg.class, new a());
    }

    /* renamed from: k */
    public ChatMsg m7122k() {
        int iM2926u = this.f7068h.m2926u();
        if (iM2926u > 0) {
            return this.f7068h.m2918m(iM2926u - 1);
        }
        return null;
    }

    /* renamed from: l */
    public final float m7123l() {
        float f9 = Globals.m7388i0().getResources().getDisplayMetrics().densityDpi / 440.0f;
        if (f9 >= 1.0f) {
            return f9;
        }
        return 1.0f;
    }

    /* renamed from: m */
    public List<MessageObj> m7124m() {
        return this.f7069i;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0047 A[ADDED_TO_REGION] */
    /* renamed from: n */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m7125n(String str) throws JSONException {
        char c9;
        if (str != null) {
            try {
                String string = new JSONObject(str).getString("mediaType");
                if (string != null) {
                    int iHashCode = string.hashCode();
                    if (iHashCode == 2189724) {
                        if (string.equals("File")) {
                            c9 = 2;
                        }
                        if (c9 != 0) {
                        }
                        return true;
                    }
                    if (iHashCode == 77090322) {
                        if (string.equals("Photo")) {
                            c9 = 0;
                        }
                        if (c9 != 0) {
                        }
                        return true;
                    }
                    c9 = (iHashCode == 82650203 && string.equals("Video")) ? (char) 1 : (char) 65535;
                    if (c9 != 0 || c9 == 1 || c9 == 2) {
                        return true;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    /* renamed from: o */
    public final boolean m7126o(ChatMsg chatMsg) {
        return (chatMsg == null || chatMsg.m5014e() == null || (chatMsg.m5014e().f63b != this.f7072l.f63b && (chatMsg.m5010a() || chatMsg.m5014e().f65d != this.f7072l.f65d))) ? false : true;
    }

    /* renamed from: t */
    public void m7127t() {
        this.f7069i.remove(0);
        notifyDataSetChanged();
    }

    /* renamed from: u */
    public void m7128u(C0011a c0011a) {
        this.f7072l = c0011a;
    }

    /* renamed from: v */
    public void m7129v(c cVar) {
        this.f7075o = cVar;
    }

    /* renamed from: w */
    public void m7130w(c cVar) {
        this.f7073m = cVar;
    }

    /* renamed from: x */
    public void m7131x(c cVar) {
        this.f7074n = cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0114  */
    /* renamed from: y */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m7132y(C6819c c6819c, MessageObj messageObj, int i9) throws NumberFormatException, FileNotFoundException {
        String strM14747I;
        int iIntValue;
        int iIntValue2;
        int i10;
        int i11;
        if (c6819c == null || messageObj == null) {
            return;
        }
        if (i9 == 14) {
            C6136j.m23599s(this.f7066f, c6819c.f22615c, messageObj.m14747I("imageItem"));
            return;
        }
        long j9 = NumberUtils.toLong(messageObj.m14747I("mediaId"), -1L);
        Uri uriM16510Z1 = null;
        C2973l0 c2973l0M14725v = j9 != -1 ? C2950b0.m14914m().m14725v(j9) : null;
        if (c2973l0M14725v != null) {
            int iM15151w = c2973l0M14725v.m15151w();
            int iM15141m = c2973l0M14725v.m15141m();
            if (iM15151w == 0 || iM15141m == 0) {
                c6819c.f22615c.getLayoutParams().height = this.f7065e;
                c6819c.f22615c.getLayoutParams().width = this.f7064d;
                m7101G(c2973l0M14725v);
            } else {
                float f9 = iM15151w;
                float f10 = this.f7062b / f9;
                int i12 = (int) (f9 * f10);
                float f11 = iM15141m;
                int i13 = (int) (f10 * f11);
                int i14 = this.f7063c;
                if (i13 > i14) {
                    i12 = (int) (f9 * (i14 / f11));
                    i13 = i14;
                }
                c6819c.f22615c.getLayoutParams().height = i13;
                c6819c.f22615c.getLayoutParams().width = i12;
            }
            MediaLoader.m7156o(this.f7066f, c6819c.f22615c, c2973l0M14725v, MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
            return;
        }
        if (12 == i9) {
            strM14747I = messageObj.m14747I("videoThumbPath");
        } else {
            strM14747I = messageObj.m14747I("imageItem");
            uriM16510Z1 = CLUtility.m16510Z1(messageObj.m14747I("imageItemUri"));
        }
        String str = strM14747I;
        if (12 == i9) {
            iIntValue2 = 0;
            try {
                iIntValue = Integer.parseInt(messageObj.m14747I("width"));
            } catch (NumberFormatException e9) {
                e = e9;
                iIntValue = 0;
            }
            try {
                iIntValue2 = Integer.parseInt(messageObj.m14747I("height"));
            } catch (NumberFormatException e10) {
                e = e10;
                Log.d("MessageAdapter", "ex:" + e.getMessage());
                if (iIntValue != 0) {
                    TranscodeUtility.m16303a(messageObj.m14747I("videoPath"), CLUtility.m16510Z1(messageObj.m14747I("videoUri")));
                }
                float f12 = iIntValue;
                float f13 = this.f7062b / f12;
                int i15 = (int) (f12 * f13);
                float f14 = iIntValue2;
                i10 = (int) (f13 * f14);
                i11 = this.f7063c;
                if (i10 > i11) {
                }
                c6819c.f22615c.getLayoutParams().height = i10;
                c6819c.f22615c.getLayoutParams().width = i15;
                C6136j.m23604x(this.f7066f, c6819c.f22615c, str, 0, 384, true);
            }
            if (iIntValue != 0 || iIntValue2 == 0) {
                TranscodeUtility.m16303a(messageObj.m14747I("videoPath"), CLUtility.m16510Z1(messageObj.m14747I("videoUri")));
            }
        } else {
            Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(str, uriM16510Z1);
            iIntValue = ((Integer) pairM16503X0.first).intValue();
            iIntValue2 = ((Integer) pairM16503X0.second).intValue();
        }
        float f122 = iIntValue;
        float f132 = this.f7062b / f122;
        int i152 = (int) (f122 * f132);
        float f142 = iIntValue2;
        i10 = (int) (f132 * f142);
        i11 = this.f7063c;
        if (i10 > i11) {
            i152 = (int) (f122 * (i11 / f142));
            i10 = i11;
        }
        c6819c.f22615c.getLayoutParams().height = i10;
        c6819c.f22615c.getLayoutParams().width = i152;
        C6136j.m23604x(this.f7066f, c6819c.f22615c, str, 0, 384, true);
    }

    /* renamed from: z */
    public final void m7133z(C6819c c6819c) {
        View view = c6819c.f22596L;
        if (view != null) {
            view.setVisibility(4);
        }
    }
}
