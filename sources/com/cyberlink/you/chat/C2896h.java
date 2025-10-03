package com.cyberlink.you.chat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ShowMediaActivity;
import com.cyberlink.you.activity.VideoPlaybackActivity;
import com.cyberlink.you.bulletin.C2833a;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.chat.C2896h;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5140e0;
import p116k4.C5172p;
import p173q2.C6127a;
import p201t3.C6301o;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.chat.h */
/* loaded from: classes.dex */
public class C2896h extends ArrayAdapter<TopicCommentObj> {

    /* renamed from: v */
    public static final String f12626v = "h";

    /* renamed from: w */
    public static final int f12627w = Math.round(TypedValue.applyDimension(1, 106.66f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: b */
    public Activity f12628b;

    /* renamed from: c */
    public LayoutInflater f12629c;

    /* renamed from: d */
    public Map<String, Friend> f12630d;

    /* renamed from: e */
    public UserInfo f12631e;

    /* renamed from: f */
    public final Handler f12632f;

    /* renamed from: g */
    public FriendsClient f12633g;

    /* renamed from: h */
    public C5140e0 f12634h;

    /* renamed from: i */
    public f f12635i;

    /* renamed from: j */
    public Timer f12636j;

    /* renamed from: k */
    public String f12637k;

    /* renamed from: l */
    public String f12638l;

    /* renamed from: m */
    public TopicCommentObj f12639m;

    /* renamed from: n */
    public boolean f12640n;

    /* renamed from: o */
    public final List<TopicCommentObj> f12641o;

    /* renamed from: p */
    public final Object f12642p;

    /* renamed from: q */
    public View.OnClickListener f12643q;

    /* renamed from: r */
    public View.OnClickListener f12644r;

    /* renamed from: s */
    public View.OnClickListener f12645s;

    /* renamed from: t */
    public View.OnClickListener f12646t;

    /* renamed from: u */
    public View.OnClickListener f12647u;

    /* renamed from: com.cyberlink.you.chat.h$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        /* renamed from: b */
        public static /* synthetic */ void m14363b(TopicCommentObj topicCommentObj, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d(C2896h.f12626v, "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d(C2896h.f12626v, "statusCode=" + str3);
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str4);
                ArrayList arrayList = new ArrayList();
                topicCommentObj.m14044t(jSONObject.getLong("likeCount"));
                arrayList.add("LikeCount");
                topicCommentObj.m14042r(jSONObject.getBoolean("isLike"));
                arrayList.add("isLiked");
                C2950b0.m14905d().m14959v(topicCommentObj.m14033h(), topicCommentObj, arrayList);
            } catch (JSONException e9) {
                Log.d(C2896h.f12626v, Log.getStackTraceString(e9));
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Integer num = (Integer) view.getTag(R.id.tag_Position);
            if (num == null) {
                return;
            }
            final TopicCommentObj topicCommentObj = (TopicCommentObj) C2896h.this.getItem(num.intValue());
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("postId", String.valueOf(topicCommentObj.m14033h())));
            arrayList.add(new C6301o("isLike", String.valueOf(!topicCommentObj.m14034i())));
            C2896h.this.f12633g.m15734m("groupbulletin", "likePost", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.chat.g
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    C2896h.a.m14363b(topicCommentObj, str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.chat.h$b */
    public class b implements C5140e0.a {
        public b() {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: a */
        public void mo9127a(int i9) {
            if (i9 == 1) {
                C2896h.this.f12628b.getWindow().addFlags(128);
                return;
            }
            C2896h.this.f12628b.getWindow().clearFlags(128);
            if (C2896h.this.f12635i != null) {
                C2896h.this.f12635i.cancel();
                C2896h.this.f12635i = null;
            }
            C2896h.this.f12639m = null;
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: b */
        public void mo9128b() throws IllegalStateException {
            if (C2896h.this.f12635i != null) {
                C2896h.this.f12635i.cancel();
                C2896h.this.f12635i = null;
            }
            C2896h.this.m14349O();
            C2896h.this.m14337C();
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: c */
        public void mo9129c(int i9) throws IllegalStateException {
            if (C2896h.this.f12635i != null) {
                C2896h.this.f12635i.cancel();
                C2896h.this.f12635i = null;
            }
            C2896h.this.m14349O();
            C2896h.this.m14337C();
        }
    }

    /* renamed from: com.cyberlink.you.chat.h$c */
    public class c implements C3197a.b {

        /* renamed from: a */
        public final /* synthetic */ TopicCommentObj f12650a;

        public c(TopicCommentObj topicCommentObj) {
            this.f12650a = topicCommentObj;
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: a */
        public void mo9122a() {
            Log.d(C2896h.f12626v, "download fail");
            C2896h.this.m14337C();
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: b */
        public void mo9123b(String str) throws IllegalStateException, IOException, SecurityException {
            Log.d(C2896h.f12626v, "download success");
            MediaScannerConnection.scanFile(Globals.m7388i0().getApplicationContext(), new String[]{str}, null, null);
            try {
                C2896h c2896h = C2896h.this;
                c2896h.m14347M(c2896h.f12638l, this.f12650a);
            } catch (IllegalArgumentException e9) {
                e9.printStackTrace();
            }
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: c */
        public void mo9124c(int i9, int i10, int i11) {
            Log.d(C2896h.f12626v, "download progress=" + String.valueOf(i9));
        }
    }

    /* renamed from: com.cyberlink.you.chat.h$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Integer num = (Integer) view.getTag(R.id.tag_Position);
            if (num == null) {
                return;
            }
            TopicCommentObj topicCommentObj = (TopicCommentObj) C2896h.this.getItem(num.intValue());
            if (topicCommentObj == null) {
                return;
            }
            C2973l0 c2973l0 = (C2973l0) topicCommentObj.m14029d().get(0).f12410c;
            String str = c2973l0.m15148t().f13200d;
            if (str != null) {
                Intent intent = new Intent(C2896h.this.f12628b, (Class<?>) VideoPlaybackActivity.class);
                intent.putExtra("video_playback_url", str);
                String str2 = c2973l0.m15148t().f13201e;
                if (str2 != null && !str2.isEmpty()) {
                    intent.putExtra("video_playback_path", str2);
                }
                intent.putExtra("mediaId", c2973l0.m15144p());
                intent.putExtra("bulletinMode", true);
                C2896h.this.f12628b.startActivity(intent);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.h$e */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Integer num = (Integer) view.getTag(R.id.tag_Position);
            if (num == null) {
                return;
            }
            TopicCommentObj topicCommentObj = (TopicCommentObj) C2896h.this.getItem(num.intValue());
            if (topicCommentObj == null) {
                return;
            }
            C2973l0 c2973l0 = (C2973l0) topicCommentObj.m14029d().get(0).f12410c;
            Intent intent = new Intent(C2896h.this.f12628b, (Class<?>) ShowMediaActivity.class);
            intent.putExtra("albumId", c2973l0.m15131c());
            intent.putExtra("mediaId", c2973l0.m15144p());
            intent.putExtra("bulletinMode", true);
            intent.putExtra("bulletinComment", true);
            C2896h.this.f12628b.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.chat.h$f */
    public class f extends TimerTask {
        public f() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (C2896h.this.f12634h == null) {
                cancel();
            } else if (C2896h.this.f12634h.m20014d() != -1) {
                C2896h c2896h = C2896h.this;
                c2896h.f12637k = CLUtility.m16531f(String.valueOf((c2896h.f12634h.m20014d() - C2896h.this.f12634h.m20013c()) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
                C2896h.this.m14337C();
            }
        }

        public /* synthetic */ f(C2896h c2896h, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.chat.h$g */
    public class g extends AsyncTask<C2973l0, Void, Void> {
        public g() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(C2973l0... c2973l0Arr) {
            if (c2973l0Arr == null) {
                return null;
            }
            boolean z8 = false;
            for (C2973l0 c2973l0 : c2973l0Arr) {
                Bitmap bitmapM7145d = MediaLoader.m7145d(C2896h.this.f12628b, c2973l0, true);
                if (bitmapM7145d != null) {
                    c2973l0.m15124K(bitmapM7145d.getWidth());
                    c2973l0.m15121H(bitmapM7145d.getHeight());
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("Height");
                    arrayList.add("Width");
                    C2950b0.m14914m().m14699I(c2973l0.m15144p(), c2973l0, arrayList);
                    z8 = true;
                }
            }
            if (!z8) {
                return null;
            }
            C2896h c2896h = C2896h.this;
            c2896h.f12632f.post(new RunnableC2878b(c2896h));
            return null;
        }

        public /* synthetic */ g(C2896h c2896h, a aVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.chat.h$h */
    public class h {

        /* renamed from: a */
        public RelativeLayout f12656a;

        /* renamed from: b */
        public RelativeLayout f12657b;

        /* renamed from: c */
        public View f12658c;

        /* renamed from: d */
        public View f12659d;

        /* renamed from: e */
        public View f12660e;

        /* renamed from: f */
        public TextView f12661f;

        /* renamed from: g */
        public ImageView f12662g;

        /* renamed from: h */
        public ImageView f12663h;

        /* renamed from: i */
        public ImageView f12664i;

        /* renamed from: j */
        public ImageView f12665j;

        /* renamed from: k */
        public ImageView f12666k;

        /* renamed from: l */
        public TextView f12667l;

        /* renamed from: m */
        public ImageView f12668m;

        /* renamed from: n */
        public TextView f12669n;

        /* renamed from: o */
        public TextView f12670o;

        /* renamed from: p */
        public View f12671p;

        /* renamed from: q */
        public ImageView f12672q;

        /* renamed from: r */
        public TextView f12673r;

        /* renamed from: s */
        public TextView f12674s;

        /* renamed from: t */
        public View f12675t;

        /* renamed from: u */
        public View f12676u;

        public h() {
        }

        public /* synthetic */ h(C2896h c2896h, a aVar) {
            this();
        }
    }

    public C2896h(Activity activity, int i9, List<TopicCommentObj> list, FriendsClient friendsClient, boolean z8) {
        super(activity, i9);
        this.f12630d = new HashMap();
        this.f12632f = new Handler();
        this.f12634h = null;
        this.f12636j = new Timer();
        this.f12641o = new ArrayList();
        this.f12642p = new Object();
        this.f12643q = new a();
        this.f12644r = new View.OnClickListener() { // from class: com.cyberlink.you.chat.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
                this.f12593b.m14335y(view);
            }
        };
        this.f12645s = new d();
        this.f12646t = new e();
        this.f12647u = new View.OnClickListener() { // from class: com.cyberlink.you.chat.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12596b.m14336z(view);
            }
        };
        this.f12628b = activity;
        this.f12629c = (LayoutInflater) activity.getSystemService("layout_inflater");
        this.f12631e = CLUtility.m16497V0(activity);
        this.f12633g = friendsClient;
        this.f12640n = z8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A */
    public /* synthetic */ void m14318A(h hVar, Object obj, Friend friend) {
        Object tag = hVar.f12667l.getTag();
        if (tag == null || tag != obj) {
            return;
        }
        m14346L(hVar, friend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B */
    public /* synthetic */ void m14319B(String str, final h hVar, final Object obj, String str2, String str3, String str4, String str5) {
        if (str4 == null) {
            Log.d(f12626v, "Response is null");
            return;
        }
        if (str4.equals("200")) {
            final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str5));
            C2950b0.m14899A().m15018j(friendM20184f, false);
            if (!this.f12630d.containsKey(str)) {
                this.f12630d.put(str, friendM20184f);
            }
            this.f12632f.post(new Runnable() { // from class: com.cyberlink.you.chat.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12617b.m14318A(hVar, obj, friendM20184f);
                }
            });
            return;
        }
        Log.d(f12626v, "statusCode=" + str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m14335y(View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        TopicCommentObj topicCommentObj;
        C5140e0 c5140e0;
        if (!(view.findViewById(R.id.bulletin_comment_audio_op).getVisibility() == 0)) {
            m14349O();
            m14337C();
            return;
        }
        Integer num = (Integer) view.getTag(R.id.tag_Position);
        if (num == null || (topicCommentObj = (TopicCommentObj) getItem(num.intValue())) == null) {
            return;
        }
        TopicCommentObj topicCommentObj2 = this.f12639m;
        if (topicCommentObj2 != null && topicCommentObj2.equals(topicCommentObj) && (c5140e0 = this.f12634h) != null && c5140e0.m20016f() == 1) {
            m14349O();
            return;
        }
        C2973l0 c2973l0 = (C2973l0) topicCommentObj.m14029d().get(0).f12410c;
        String str = c2973l0.m15148t().f13201e;
        if (str == null || str.equals("_")) {
            m14355r(c2973l0.m15148t().f13200d, Long.toString(c2973l0.m15144p()), topicCommentObj);
            c2973l0.m15148t().f13201e = this.f12638l;
            C2950b0.m14914m().m14698H(c2973l0.m15144p(), c2973l0, "Original");
        } else {
            m14347M(str, topicCommentObj);
        }
        m14337C();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m14336z(View view) {
        setNotifyOnChange(false);
        clear();
        addAll(this.f12641o);
        setNotifyOnChange(true);
        notifyDataSetChanged();
    }

    /* renamed from: C */
    public final void m14337C() {
        Activity activity = this.f12628b;
        if (activity != null) {
            activity.runOnUiThread(new RunnableC2878b(this));
        }
    }

    /* renamed from: D */
    public void m14338D(long j9) {
        synchronized (this.f12642p) {
            int i9 = 0;
            while (true) {
                if (i9 >= this.f12641o.size()) {
                    break;
                }
                TopicCommentObj topicCommentObj = this.f12641o.get(i9);
                if (topicCommentObj.m14033h() == j9) {
                    this.f12641o.remove(i9);
                    remove(topicCommentObj);
                    break;
                }
                i9++;
            }
        }
    }

    /* renamed from: E */
    public void m14339E(TopicCommentObj topicCommentObj) {
        m14338D(topicCommentObj.m14033h());
    }

    /* renamed from: F */
    public final void m14340F(h hVar, C2833a c2833a) {
        String str = c2833a.f12408a;
        Object obj = c2833a.f12410c;
        if (str.equals("Photo") && obj != null) {
            if (hVar.f12661f.getVisibility() == 0) {
                hVar.f12660e.setVisibility(0);
            }
            hVar.f12662g.setVisibility(0);
            m14344J(hVar.f12662g, (C2973l0) obj);
            return;
        }
        if (!str.equals("Audio") || obj == null) {
            if (str.equals("Video") && obj != null) {
                hVar.f12658c.setVisibility(0);
                C2973l0 c2973l0 = (C2973l0) obj;
                hVar.f12670o.setText(CLUtility.m16519c(c2973l0.m15148t().f13203g));
                m14344J(hVar.f12666k, c2973l0);
                return;
            }
            if (!str.equals("Sticker") || obj == null) {
                hVar.f12661f.setText(str);
                hVar.f12661f.setVisibility(0);
                return;
            } else {
                hVar.f12665j.setVisibility(0);
                LoadImageUtils.m16626k(this.f12628b, (StickerObj) obj, hVar.f12665j);
                return;
            }
        }
        hVar.f12659d.setVisibility(0);
        Log.d(f12626v, "getView type is Audio, commentObj Id() = " + c2833a.f12412e);
        TopicCommentObj topicCommentObj = this.f12639m;
        if (topicCommentObj == null || topicCommentObj.m14033h() != c2833a.f12412e) {
            hVar.f12663h.setVisibility(0);
            hVar.f12664i.setVisibility(8);
            hVar.f12669n.setText(CLUtility.m16531f(((C2973l0) obj).m15148t().f13203g));
        } else {
            hVar.f12663h.setVisibility(8);
            hVar.f12664i.setVisibility(0);
            hVar.f12669n.setText(this.f12637k);
        }
    }

    /* renamed from: G */
    public final void m14341G(h hVar, TopicCommentObj topicCommentObj) {
        hVar.f12673r.setText(this.f12628b.getString(R.string.like_D, Long.valueOf(topicCommentObj.m14037l())));
        int i9 = topicCommentObj.m14034i() ? R.drawable.icons_heart_s : R.drawable.icons_heart_n;
        int i10 = topicCommentObj.m14034i() ? R.color.you_color_normal_blue_text : R.color.text_normal;
        hVar.f12672q.setImageResource(i9);
        hVar.f12673r.setTextColor(this.f12628b.getResources().getColor(i10));
    }

    /* renamed from: H */
    public void m14342H(boolean z8) {
        this.f12640n = z8;
    }

    /* renamed from: I */
    public synchronized void m14343I(List<TopicCommentObj> list) {
        synchronized (this.f12642p) {
            int count = getCount();
            boolean z8 = false;
            if (count == 0) {
                int size = list.size();
                if (size > 10) {
                    setNotifyOnChange(false);
                    add(m14356s());
                    for (int i9 = size - 10; i9 < size; i9++) {
                        add(list.get(i9));
                    }
                    setNotifyOnChange(true);
                    notifyDataSetChanged();
                } else {
                    addAll(list);
                }
            } else if (((TopicCommentObj) getItem(0)).m14039o()) {
                long jM14033h = count > 2 ? ((TopicCommentObj) getItem(1)).m14033h() : 0L;
                setNotifyOnChange(false);
                clear();
                add(m14356s());
                for (TopicCommentObj topicCommentObj : list) {
                    if (z8) {
                        add(topicCommentObj);
                    } else if (topicCommentObj.m14033h() == jM14033h) {
                        add(topicCommentObj);
                        z8 = true;
                    }
                }
                setNotifyOnChange(true);
                notifyDataSetChanged();
            } else {
                setNotifyOnChange(false);
                clear();
                addAll(list);
                setNotifyOnChange(true);
                notifyDataSetChanged();
            }
            this.f12641o.clear();
            this.f12641o.addAll(list);
        }
    }

    /* renamed from: J */
    public final void m14344J(ImageView imageView, C2973l0 c2973l0) {
        int iM15151w = c2973l0.m15151w();
        int iM15141m = c2973l0.m15141m();
        if (iM15151w == 0 || iM15141m == 0) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            int i9 = f12627w;
            layoutParams.height = i9;
            imageView.getLayoutParams().width = i9;
            new g(this, null).executeOnExecutor(C6385v.f21554b, c2973l0);
        } else {
            Pair<Integer, Integer> pairM14354q = m14354q(iM15151w, iM15141m);
            imageView.getLayoutParams().height = ((Integer) pairM14354q.second).intValue();
            imageView.getLayoutParams().width = ((Integer) pairM14354q.first).intValue();
        }
        MediaLoader.m7156o(this.f12628b, imageView, c2973l0, MediaLoader.Option.THUMBNAIL);
    }

    /* renamed from: K */
    public final void m14345K(final h hVar, final String str) {
        Friend friendM15003C;
        if (this.f12630d.containsKey(str)) {
            friendM15003C = this.f12630d.get(str);
        } else {
            friendM15003C = C2950b0.m14899A().m15003C(str);
            this.f12630d.put(str, friendM15003C);
        }
        hVar.f12667l.setTag(null);
        if (friendM15003C != null) {
            m14346L(hVar, friendM15003C);
            return;
        }
        final Object obj = new Object();
        hVar.f12667l.setText("");
        hVar.f12667l.setTag(obj);
        hVar.f12668m.setImageResource(R.drawable.pic_default);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", str));
        this.f12633g.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.chat.c
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f12586a.m14319B(str, hVar, obj, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: L */
    public final void m14346L(h hVar, Friend friend) {
        if (friend != null) {
            UserInfo userInfo = this.f12631e;
            if (userInfo == null || friend.f13645c != userInfo.f13777b) {
                hVar.f12667l.setText(friend.m15621b());
            } else {
                hVar.f12667l.setText(userInfo.f13778c);
            }
            C6127a.m23469j(this.f12628b, hVar.f12668m, friend);
        }
    }

    /* renamed from: M */
    public final void m14347M(String str, TopicCommentObj topicCommentObj) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        m14349O();
        C5140e0 c5140e0 = new C5140e0();
        this.f12634h = c5140e0;
        c5140e0.m20021m(new b());
        this.f12639m = topicCommentObj;
        this.f12634h.m20018j(str);
        f fVar = new f(this, null);
        this.f12635i = fVar;
        Timer timer = this.f12636j;
        if (timer != null) {
            timer.schedule(fVar, 0L, 1000L);
        }
    }

    /* renamed from: N */
    public void m14348N() {
        C5140e0 c5140e0 = this.f12634h;
        if (c5140e0 == null || c5140e0.m20016f() != 1) {
            return;
        }
        m14349O();
        m14337C();
    }

    /* renamed from: O */
    public final void m14349O() throws IllegalStateException {
        if (this.f12634h == null) {
            return;
        }
        f fVar = this.f12635i;
        if (fVar != null) {
            fVar.cancel();
            this.f12635i = null;
        }
        this.f12639m = null;
        this.f12634h.m20027s();
        this.f12634h = null;
    }

    /* renamed from: P */
    public final void m14350P(h hVar, int i9) {
        hVar.f12671p.setTag(R.id.tag_Position, Integer.valueOf(i9));
        hVar.f12659d.setTag(R.id.tag_Position, Integer.valueOf(i9));
        hVar.f12666k.setTag(R.id.tag_Position, Integer.valueOf(i9));
        hVar.f12662g.setTag(R.id.tag_Position, Integer.valueOf(i9));
    }

    /* renamed from: Q */
    public void m14351Q(TopicCommentObj topicCommentObj) {
        for (int i9 = 0; i9 < getCount(); i9++) {
            try {
                TopicCommentObj topicCommentObj2 = (TopicCommentObj) getItem(i9);
                if (topicCommentObj2 != null && topicCommentObj2.m14033h() == topicCommentObj.m14033h()) {
                    topicCommentObj2.m14041q(topicCommentObj.m14032g());
                    notifyDataSetChanged();
                    return;
                }
            } catch (ConcurrentModificationException e9) {
                e9.printStackTrace();
                return;
            }
        }
    }

    /* renamed from: R */
    public void m14352R(TopicCommentObj topicCommentObj) {
        for (int i9 = 0; i9 < getCount(); i9++) {
            try {
                TopicCommentObj topicCommentObj2 = (TopicCommentObj) getItem(i9);
                if (topicCommentObj2 != null && topicCommentObj2.m14033h() == topicCommentObj.m14033h()) {
                    topicCommentObj2.m14044t(topicCommentObj.m14037l());
                    topicCommentObj2.m14042r(topicCommentObj.m14034i());
                    notifyDataSetChanged();
                    return;
                }
            } catch (ConcurrentModificationException e9) {
                e9.printStackTrace();
                return;
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        h hVarM14361x;
        if (view == null) {
            view = this.f12629c.inflate(R.layout.view_item_bulletin_comment, viewGroup, false);
            hVarM14361x = m14361x(view);
        } else {
            hVarM14361x = (h) view.getTag();
        }
        m14359v(hVarM14361x);
        m14358u(hVarM14361x);
        m14360w(hVarM14361x);
        TopicCommentObj topicCommentObj = (TopicCommentObj) getItem(i9);
        if (topicCommentObj.m14039o()) {
            hVarM14361x.f12657b.setVisibility(0);
            hVarM14361x.f12656a.setVisibility(8);
        } else {
            hVarM14361x.f12657b.setVisibility(8);
            hVarM14361x.f12656a.setVisibility(0);
            m14345K(hVarM14361x, String.valueOf(topicCommentObj.m14031f()));
            if (topicCommentObj.m14032g() != null && !topicCommentObj.m14032g().isEmpty()) {
                hVarM14361x.f12661f.setText(topicCommentObj.m14032g());
                hVarM14361x.f12661f.setVisibility(0);
                CLUtility.m16543i(hVarM14361x.f12661f);
            }
            List<C2833a> listM14029d = topicCommentObj.m14029d();
            if (listM14029d.size() > 0) {
                m14340F(hVarM14361x, listM14029d.get(0));
            }
            m14341G(hVarM14361x, topicCommentObj);
            hVarM14361x.f12674s.setText(CLUtility.m16462L2(new Date(topicCommentObj.m14030e())));
            hVarM14361x.f12676u.setVisibility(topicCommentObj.m14035j() ? 0 : 8);
            m14350P(hVarM14361x, i9);
        }
        return view;
    }

    /* renamed from: p */
    public void m14353p(TopicCommentObj topicCommentObj) {
        boolean z8;
        synchronized (this.f12642p) {
            int i9 = 0;
            while (true) {
                try {
                    if (i9 >= getCount()) {
                        z8 = true;
                        break;
                    } else {
                        if (((TopicCommentObj) getItem(i9)).m14033h() == topicCommentObj.m14033h()) {
                            z8 = false;
                            break;
                        }
                        i9++;
                    }
                } catch (ConcurrentModificationException e9) {
                    e9.printStackTrace();
                }
            }
            if (z8) {
                setNotifyOnChange(false);
                add(topicCommentObj);
                sort(new TopicCommentObj.C2832a());
                this.f12641o.add(topicCommentObj);
                Collections.sort(this.f12641o, new TopicCommentObj.C2832a());
                notifyDataSetChanged();
                setNotifyOnChange(true);
            }
        }
    }

    /* renamed from: q */
    public final Pair<Integer, Integer> m14354q(int i9, int i10) {
        int i11 = f12627w;
        return Pair.create(Integer.valueOf(Math.round(i9 * (i11 / i10))), Integer.valueOf(i11));
    }

    /* renamed from: r */
    public void m14355r(String str, String str2, TopicCommentObj topicCommentObj) {
        Log.d(f12626v, "download voice file and play!");
        this.f12638l = CLUtility.m16488S0(this.f12628b, str2);
        new C3197a().m16996s(str, this.f12638l, new c(topicCommentObj));
    }

    /* renamed from: s */
    public final TopicCommentObj m14356s() {
        return new TopicCommentObj(-1L, -1L, -1L, "", 0L, false, Pair.create(TopicCommentObj.FromSource.UnreadItem, ""), 0L, 0L, false);
    }

    /* renamed from: t */
    public TopicCommentObj m14357t(long j9) {
        for (TopicCommentObj topicCommentObj : this.f12641o) {
            if (topicCommentObj.m14033h() == j9) {
                return topicCommentObj;
            }
        }
        return null;
    }

    /* renamed from: u */
    public final void m14358u(h hVar) {
        hVar.f12671p.setOnClickListener(this.f12640n ? this.f12643q : null);
        hVar.f12675t.setOnClickListener(this.f12647u);
        hVar.f12659d.setOnClickListener(this.f12644r);
        hVar.f12666k.setOnClickListener(this.f12645s);
        hVar.f12662g.setOnClickListener(this.f12646t);
    }

    /* renamed from: v */
    public final void m14359v(h hVar) {
        hVar.f12661f.setVisibility(8);
        hVar.f12662g.setVisibility(8);
        hVar.f12665j.setVisibility(8);
        hVar.f12658c.setVisibility(8);
        hVar.f12659d.setVisibility(8);
        hVar.f12660e.setVisibility(8);
    }

    /* renamed from: w */
    public final void m14360w(h hVar) {
        this.f12628b.registerForContextMenu(hVar.f12671p);
        this.f12628b.registerForContextMenu(hVar.f12659d);
        this.f12628b.registerForContextMenu(hVar.f12666k);
        this.f12628b.registerForContextMenu(hVar.f12662g);
    }

    /* renamed from: x */
    public final h m14361x(View view) {
        h hVar = new h(this, null);
        hVar.f12657b = (RelativeLayout) view.findViewById(R.id.bulletin_comment_unread_layout);
        hVar.f12656a = (RelativeLayout) view.findViewById(R.id.clicking_area_bulletin_comment);
        hVar.f12661f = (TextView) view.findViewById(R.id.bulletin_comment_text);
        hVar.f12662g = (ImageView) view.findViewById(R.id.bulletin_comment_photo);
        hVar.f12665j = (ImageView) view.findViewById(R.id.bulletin_comment_sticker);
        hVar.f12658c = view.findViewById(R.id.bulletin_comment_video_area);
        hVar.f12659d = view.findViewById(R.id.bulletin_comment_audio_area);
        hVar.f12660e = view.findViewById(R.id.bulletin_comment_separate);
        hVar.f12675t = view.findViewById(R.id.unread_button);
        hVar.f12666k = (ImageView) view.findViewById(R.id.bulletin_comment_video_thumbnail);
        hVar.f12667l = (TextView) view.findViewById(R.id.bulletin_comment_name);
        hVar.f12668m = (ImageView) view.findViewById(R.id.bulletin_comment_avatar);
        hVar.f12669n = (TextView) view.findViewById(R.id.bulletin_comment_audio_duration);
        hVar.f12670o = (TextView) view.findViewById(R.id.bulletin_comment_video_time);
        hVar.f12671p = view.findViewById(R.id.bulletin_item_like_area);
        hVar.f12672q = (ImageView) view.findViewById(R.id.bulletin_item_like_icon);
        hVar.f12673r = (TextView) view.findViewById(R.id.bulletin_item_string_like_count);
        hVar.f12674s = (TextView) view.findViewById(R.id.bulletin_item_time_text);
        hVar.f12663h = (ImageView) view.findViewById(R.id.bulletin_comment_audio_op);
        hVar.f12664i = (ImageView) view.findViewById(R.id.bulletin_comment_audio_stop);
        hVar.f12676u = view.findViewById(R.id.bulletin_comment_unread_icon);
        view.setTag(hVar);
        return hVar;
    }
}
