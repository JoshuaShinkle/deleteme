package com.cyberlink.you.bulletin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.CreateTopicActivity;
import com.cyberlink.you.activity.VideoPlaybackActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import p116k4.C5140e0;
import p173q2.C6136j;

/* loaded from: classes.dex */
public class ExtraMediaAdapter extends ArrayAdapter<Object> {

    /* renamed from: r */
    public static final int f12357r = Math.round(TypedValue.applyDimension(1, 106.66f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: b */
    public final int f12358b;

    /* renamed from: c */
    public Context f12359c;

    /* renamed from: d */
    public LayoutInflater f12360d;

    /* renamed from: e */
    public ImageView f12361e;

    /* renamed from: f */
    public ImageView f12362f;

    /* renamed from: g */
    public C5140e0 f12363g;

    /* renamed from: h */
    public C2830e f12364h;

    /* renamed from: i */
    public Timer f12365i;

    /* renamed from: j */
    public String f12366j;

    /* renamed from: k */
    public boolean f12367k;

    /* renamed from: l */
    public Activity f12368l;

    /* renamed from: m */
    public Map<ItemType, Integer> f12369m;

    /* renamed from: n */
    public View.OnClickListener f12370n;

    /* renamed from: o */
    public View.OnClickListener f12371o;

    /* renamed from: p */
    public View.OnClickListener f12372p;

    /* renamed from: q */
    public View.OnClickListener f12373q;

    public enum ItemType {
        TYPE_PHOTO,
        TYPE_VIDEO,
        TYPE_AUDIO,
        TYPE_STICKER
    }

    /* renamed from: com.cyberlink.you.bulletin.ExtraMediaAdapter$a */
    public class ViewOnClickListenerC2826a implements View.OnClickListener {
        public ViewOnClickListenerC2826a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            Integer num = (Integer) view.getTag(R.id.tag_Position);
            if (num == null) {
                return;
            }
            String strM14002a = ((AudioItem) ExtraMediaAdapter.this.getItem(num.intValue())).m14002a();
            if (strM14002a != null) {
                ExtraMediaAdapter.this.m14022s(strM14002a);
            }
            ExtraMediaAdapter.this.f12367k = true;
            ExtraMediaAdapter.this.f12361e.setVisibility(8);
            ExtraMediaAdapter.this.f12362f.setVisibility(0);
        }
    }

    /* renamed from: com.cyberlink.you.bulletin.ExtraMediaAdapter$b */
    public class ViewOnClickListenerC2827b implements View.OnClickListener {
        public ViewOnClickListenerC2827b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException {
            ExtraMediaAdapter.this.m14023t();
            ExtraMediaAdapter.this.f12367k = false;
            ExtraMediaAdapter.this.f12361e.setVisibility(0);
            ExtraMediaAdapter.this.f12362f.setVisibility(8);
        }
    }

    /* renamed from: com.cyberlink.you.bulletin.ExtraMediaAdapter$c */
    public class ViewOnClickListenerC2828c implements View.OnClickListener {
        public ViewOnClickListenerC2828c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Integer num = (Integer) view.getTag(R.id.tag_Position);
            if (num == null) {
                return;
            }
            ItemType itemTypeM14018n = ExtraMediaAdapter.this.m14018n(num.intValue());
            ExtraMediaAdapter extraMediaAdapter = ExtraMediaAdapter.this;
            extraMediaAdapter.remove(extraMediaAdapter.getItem(num.intValue()));
            ((CreateTopicActivity) ExtraMediaAdapter.this.f12359c).m7967m1(itemTypeM14018n);
        }
    }

    /* renamed from: com.cyberlink.you.bulletin.ExtraMediaAdapter$d */
    public class C2829d implements C5140e0.a {
        public C2829d() {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: a */
        public void mo9127a(int i9) {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: b */
        public void mo9128b() throws IllegalStateException {
            ExtraMediaAdapter.this.f12367k = false;
            ExtraMediaAdapter.this.m14023t();
            ExtraMediaAdapter.this.m14020q();
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: c */
        public void mo9129c(int i9) throws IllegalStateException {
            ExtraMediaAdapter.this.f12367k = false;
            ExtraMediaAdapter.this.m14023t();
            ExtraMediaAdapter.this.m14020q();
        }
    }

    /* renamed from: com.cyberlink.you.bulletin.ExtraMediaAdapter$e */
    public class C2830e extends TimerTask {
        public C2830e() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (ExtraMediaAdapter.this.f12363g == null) {
                cancel();
                return;
            }
            if (ExtraMediaAdapter.this.f12363g.m20014d() != -1) {
                ExtraMediaAdapter extraMediaAdapter = ExtraMediaAdapter.this;
                extraMediaAdapter.f12366j = CLUtility.m16531f(String.valueOf((extraMediaAdapter.f12363g.m20014d() - ExtraMediaAdapter.this.f12363g.m20013c()) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
                if (ExtraMediaAdapter.this.f12366j != null) {
                    ExtraMediaAdapter.this.m14020q();
                }
            }
        }

        public /* synthetic */ C2830e(ExtraMediaAdapter extraMediaAdapter, ViewOnClickListenerC2826a viewOnClickListenerC2826a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.bulletin.ExtraMediaAdapter$f */
    public class C2831f {

        /* renamed from: a */
        public ImageView f12384a;

        /* renamed from: b */
        public ImageView f12385b;

        /* renamed from: c */
        public TextView f12386c;

        /* renamed from: d */
        public View f12387d;

        public C2831f() {
        }

        public /* synthetic */ C2831f(ExtraMediaAdapter extraMediaAdapter, ViewOnClickListenerC2826a viewOnClickListenerC2826a) {
            this();
        }
    }

    public ExtraMediaAdapter(Context context, int i9, List<Object> list) {
        super(context, i9, list);
        this.f12358b = Math.round(TypedValue.applyDimension(1, 75.0f, Globals.m7388i0().getResources().getDisplayMetrics()));
        this.f12363g = null;
        this.f12365i = new Timer();
        this.f12367k = false;
        this.f12370n = new View.OnClickListener() { // from class: k3.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f17605b.m14015o(view);
            }
        };
        this.f12371o = new ViewOnClickListenerC2826a();
        this.f12372p = new ViewOnClickListenerC2827b();
        this.f12373q = new ViewOnClickListenerC2828c();
        this.f12359c = context;
        this.f12360d = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f12369m = new HashMap();
        int i10 = 0;
        for (ItemType itemType : ItemType.values()) {
            this.f12369m.put(itemType, Integer.valueOf(i10));
            i10++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m14015o(View view) {
        VideoItem videoItem;
        String strM16218g;
        Integer num = (Integer) view.getTag(R.id.tag_Position);
        if (num == null || (strM16218g = (videoItem = (VideoItem) getItem(num.intValue())).m16218g()) == null) {
            return;
        }
        Intent intent = new Intent(getContext(), (Class<?>) VideoPlaybackActivity.class);
        intent.putExtra("video_playback_url", strM16218g);
        String strM16218g2 = videoItem.m16218g();
        if (!TextUtils.isEmpty(strM16218g2)) {
            intent.putExtra("video_playback_path", strM16218g2);
        }
        intent.putExtra("mediaId", videoItem.m16217f());
        intent.putExtra("bulletinMode", true);
        getContext().startActivity(intent);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i9) {
        return this.f12369m.get(m14018n(i9)).intValue();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) throws JSONException, IOException {
        C2831f c2831f;
        ItemType itemTypeM14018n = m14018n(i9);
        if (view == null) {
            c2831f = new C2831f(this, null);
            if (itemTypeM14018n.equals(ItemType.TYPE_PHOTO)) {
                view = this.f12360d.inflate(R.layout.view_item_bulletin_create_topic_photo, viewGroup, false);
                c2831f.f12384a = (ImageView) view.findViewById(R.id.ChatMessageContentPhotoView);
            } else if (itemTypeM14018n.equals(ItemType.TYPE_VIDEO)) {
                view = this.f12360d.inflate(R.layout.view_item_bulletin_create_topic_video, viewGroup, false);
                c2831f.f12384a = (ImageView) view.findViewById(R.id.ChatMessageContentPhotoView);
                c2831f.f12386c = (TextView) view.findViewById(R.id.duration);
                c2831f.f12384a.setTag(R.id.tag_Position, Integer.valueOf(i9));
                c2831f.f12384a.setOnClickListener(this.f12370n);
            } else if (itemTypeM14018n.equals(ItemType.TYPE_AUDIO)) {
                view = this.f12360d.inflate(R.layout.view_item_bulletin_create_topic_audio, viewGroup, false);
                c2831f.f12386c = (TextView) view.findViewById(R.id.ChatMessageAudioDuration);
                ImageView imageView = (ImageView) view.findViewById(R.id.ChatMessageStopView);
                c2831f.f12385b = imageView;
                imageView.setOnClickListener(this.f12372p);
                ImageView imageView2 = (ImageView) view.findViewById(R.id.ChatMessageAudioOpView);
                c2831f.f12384a = imageView2;
                imageView2.setTag(R.id.tag_Position, Integer.valueOf(i9));
                c2831f.f12384a.setOnClickListener(this.f12371o);
            } else if (itemTypeM14018n.equals(ItemType.TYPE_STICKER)) {
                view = this.f12360d.inflate(R.layout.view_item_bulletin_create_topic_sticker, viewGroup, false);
                c2831f.f12384a = (ImageView) view.findViewById(R.id.ChatMessageContentStickerView);
            }
            if (view != null) {
                view.setTag(c2831f);
                c2831f.f12387d = view.findViewById(R.id.delete);
            }
            View view2 = c2831f.f12387d;
            if (view2 != null) {
                view2.setOnClickListener(this.f12373q);
            }
        } else {
            c2831f = (C2831f) view.getTag();
        }
        View view3 = c2831f.f12387d;
        if (view3 != null) {
            view3.setTag(R.id.tag_Position, Integer.valueOf(i9));
        }
        if (itemTypeM14018n.equals(ItemType.TYPE_PHOTO)) {
            ImageItem imageItem = (ImageItem) getItem(i9);
            Pair<Integer, Integer> pairM14016l = m14016l(imageItem.m16146s(), imageItem.m16143p());
            c2831f.f12384a.getLayoutParams().height = ((Integer) pairM14016l.second).intValue();
            c2831f.f12384a.getLayoutParams().width = ((Integer) pairM14016l.first).intValue();
            Uri uriM16510Z1 = CLUtility.m16510Z1(imageItem.m16136i());
            if (uriM16510Z1 != null) {
                C6136j.m23596p(this.f12359c, c2831f.f12384a, uriM16510Z1, R.drawable.doc_thumbnail_default, 384);
            } else {
                C6136j.m23601u(this.f12359c, c2831f.f12384a, imageItem.m16135h(), R.drawable.doc_thumbnail_default, 384);
            }
        } else if (itemTypeM14018n.equals(ItemType.TYPE_VIDEO)) {
            VideoItem videoItem = (VideoItem) getItem(i9);
            Pair<Integer, Integer> pairM14016l2 = m14016l(videoItem.m16220i(), videoItem.m16214c());
            c2831f.f12384a.getLayoutParams().height = ((Integer) pairM14016l2.second).intValue();
            c2831f.f12384a.getLayoutParams().width = ((Integer) pairM14016l2.first).intValue();
            C6136j.m23601u(this.f12359c, c2831f.f12384a, videoItem.m16216e(), R.drawable.doc_thumbnail_default, 384);
            c2831f.f12386c.setText(CLUtility.m16519c(String.valueOf(videoItem.m16213b())));
        } else if (itemTypeM14018n.equals(ItemType.TYPE_AUDIO)) {
            AudioItem audioItem = (AudioItem) getItem(i9);
            this.f12362f = c2831f.f12385b;
            this.f12361e = c2831f.f12384a;
            if (this.f12367k) {
                c2831f.f12386c.setText(this.f12366j);
            } else {
                c2831f.f12386c.setText(CLUtility.m16531f(String.valueOf(audioItem.m14003b())));
                c2831f.f12384a.setVisibility(0);
                c2831f.f12385b.setVisibility(8);
            }
        } else if (itemTypeM14018n.equals(ItemType.TYPE_STICKER)) {
            StickerItem stickerItem = (StickerItem) getItem(i9);
            m14019p(stickerItem);
            if (stickerItem.m16289n() == 0 || stickerItem.m16279d() == 0) {
                c2831f.f12384a.getLayoutParams().height = -2;
                c2831f.f12384a.getLayoutParams().width = -2;
            } else {
                Pair<Integer, Integer> pairM14017m = m14017m(stickerItem.m16289n(), stickerItem.m16279d());
                c2831f.f12384a.getLayoutParams().height = ((Integer) pairM14017m.second).intValue();
                c2831f.f12384a.getLayoutParams().width = ((Integer) pairM14017m.first).intValue();
            }
            if ("AnimationPNG".equals(stickerItem.m14024w())) {
                LoadImageUtils.m16634s(getContext(), stickerItem, c2831f.f12384a, false);
            } else {
                LoadImageUtils.m16638w(getContext(), stickerItem, false, c2831f.f12384a, false);
            }
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return this.f12369m.size();
    }

    /* renamed from: l */
    public final Pair<Integer, Integer> m14016l(int i9, int i10) {
        int i11 = f12357r;
        return Pair.create(Integer.valueOf(Math.round(i9 * (i11 / i10))), Integer.valueOf(i11));
    }

    /* renamed from: m */
    public final Pair<Integer, Integer> m14017m(int i9, int i10) {
        return Pair.create(Integer.valueOf(Math.round(i9 * (this.f12358b / i10))), Integer.valueOf(this.f12358b));
    }

    /* renamed from: n */
    public ItemType m14018n(int i9) {
        Object item = getItem(i9);
        ItemType itemType = ItemType.TYPE_PHOTO;
        return item instanceof ImageItem ? itemType : item instanceof VideoItem ? ItemType.TYPE_VIDEO : item instanceof AudioItem ? ItemType.TYPE_AUDIO : item instanceof StickerItem ? ItemType.TYPE_STICKER : itemType;
    }

    /* renamed from: p */
    public final void m14019p(StickerItem stickerItem) throws JSONException, IOException {
        if (stickerItem.m16289n() == 0 || stickerItem.m16279d() == 0) {
            Pair<Integer, Integer> pairM16545i1 = CLUtility.m16545i1(stickerItem.m16282g());
            stickerItem.m16295t(((Integer) pairM16545i1.first).intValue());
            stickerItem.m16292q(((Integer) pairM16545i1.second).intValue());
            ArrayList arrayList = new ArrayList();
            arrayList.add("Height");
            arrayList.add("Width");
            C2950b0.m14924w().m15282j(stickerItem.m16285j(), stickerItem, arrayList);
        }
    }

    /* renamed from: q */
    public final void m14020q() {
        Activity activity = this.f12368l;
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: k3.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17606b.notifyDataSetChanged();
                }
            });
        }
    }

    /* renamed from: r */
    public void m14021r(Activity activity) {
        this.f12368l = activity;
    }

    /* renamed from: s */
    public final void m14022s(String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        m14023t();
        C5140e0 c5140e0 = new C5140e0();
        this.f12363g = c5140e0;
        c5140e0.m20021m(new C2829d());
        this.f12363g.m20018j(str);
        C2830e c2830e = new C2830e(this, null);
        this.f12364h = c2830e;
        Timer timer = this.f12365i;
        if (timer != null) {
            timer.schedule(c2830e, 0L, 1000L);
        }
    }

    /* renamed from: t */
    public final void m14023t() throws IllegalStateException {
        if (this.f12363g == null) {
            return;
        }
        C2830e c2830e = this.f12364h;
        if (c2830e != null) {
            c2830e.cancel();
            this.f12364h = null;
        }
        this.f12363g.m20027s();
        this.f12363g = null;
    }
}
