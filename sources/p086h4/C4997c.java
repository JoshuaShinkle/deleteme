package p086h4;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.sticker.Emoji.EmojiCategory;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView;
import com.cyberlink.you.widgetpool.clhorizontalgridview.HorizontalGridView;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p096i4.C5050a;
import p096i4.C5051b;
import p189s0.AbstractC6243a;

/* renamed from: h4.c */
/* loaded from: classes.dex */
public class C4997c extends AbstractC5005i {

    /* renamed from: c */
    public C5051b f17211c;

    /* renamed from: d */
    public Map<String, AbstractC6243a> f17212d;

    /* renamed from: e */
    public c f17213e;

    /* renamed from: f */
    public AdapterView.InterfaceC3229e f17214f;

    /* renamed from: g */
    public View.OnClickListener f17215g;

    /* renamed from: h4.c$a */
    public class a implements AdapterView.InterfaceC3229e {
        public a() {
        }

        @Override // com.cyberlink.you.widgetpool.clhorizontalgridview.AdapterView.InterfaceC3229e
        /* renamed from: a */
        public void mo17210a(AdapterView<?> adapterView, View view, int i9, long j9) {
            AbstractC6243a abstractC6243a;
            EmojiCategory emojiCategory = (EmojiCategory) adapterView.getAdapter().getItem(i9);
            if (emojiCategory != null && (abstractC6243a = (AbstractC6243a) C4997c.this.f17212d.get(emojiCategory.toString())) != null) {
                C4997c.this.f17249b.f17224e.setAdapter(abstractC6243a);
                int count = abstractC6243a.getCount();
                C4997c c4997c = C4997c.this;
                c4997c.f17249b.f17221b.m19450a(c4997c.f17248a, count);
            }
            C4997c.this.f17211c.m19751a(i9);
            C4997c.this.f17211c.notifyDataSetChanged();
        }
    }

    /* renamed from: h4.c$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C4997c.this.f17213e.mo19405a((Emojicon) view.getTag());
        }
    }

    /* renamed from: h4.c$c */
    public interface c {
        /* renamed from: a */
        void mo19405a(Emojicon emojicon);

        /* renamed from: b */
        void mo19406b();
    }

    /* renamed from: h4.c$d */
    public class d extends AsyncTask<String, Void, List<Pair<EmojiCategory, List<View>>>> {

        /* renamed from: a */
        public List<EmojiCategory> f17218a;

        public d(List<EmojiCategory> list) {
            this.f17218a = list;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Pair<EmojiCategory, List<View>>> doInBackground(String... strArr) {
            List<View> listM19408b;
            ArrayList arrayList = new ArrayList();
            for (EmojiCategory emojiCategory : this.f17218a) {
                if (C5050a.m19749b(emojiCategory) != null && (listM19408b = m19408b(C5050a.m19749b(emojiCategory))) != null) {
                    arrayList.add(Pair.create(emojiCategory, listM19408b));
                }
            }
            return arrayList;
        }

        /* renamed from: b */
        public final List<View> m19408b(Emojicon[] emojiconArr) {
            ArrayList arrayList = new ArrayList();
            View viewInflate = null;
            for (int i9 = 0; i9 < emojiconArr.length; i9++) {
                try {
                    if (i9 % 21 == 0) {
                        if (viewInflate != null) {
                            arrayList.add(viewInflate);
                        }
                        viewInflate = C4997c.this.f17248a.getLayoutInflater().inflate(R.layout.view_item_emojicon_preview, (ViewGroup) null);
                    }
                    TextView textView = (TextView) viewInflate.findViewById(C4997c.this.f17248a.getResources().getIdentifier("emojiconView" + (i9 % 21), TtmlNode.ATTR_ID, C4997c.this.f17248a.getPackageName()));
                    textView.setTag(emojiconArr[i9]);
                    textView.setOnClickListener(C4997c.this.f17215g);
                } catch (OutOfMemoryError e9) {
                    ULogUtility.m16687w("EmojiPageController", "[initEmojiconViewList(Emojicon[])] ", "Error: ", e9.toString());
                    return null;
                }
            }
            if (viewInflate != null) {
                arrayList.add(viewInflate);
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Pair<EmojiCategory, List<View>>> list) {
            if (list != null) {
                for (Pair<EmojiCategory, List<View>> pair : list) {
                    List list2 = (List) pair.second;
                    ArrayList arrayList = new ArrayList();
                    for (int i9 = 0; i9 < list2.size(); i9++) {
                        arrayList.add(new C4993a(((EmojiCategory) pair.first).name(), (View) list2.get(i9)));
                    }
                    C4997c.this.f17212d.put(((EmojiCategory) pair.first).toString(), new C4996b0(C4997c.this.f17248a, arrayList, true));
                }
            }
            C4997c.this.f17213e.mo19406b();
            this.f17218a = null;
        }
    }

    public C4997c(C4998c0 c4998c0, Activity activity, c cVar) {
        super(activity, c4998c0);
        this.f17212d = new HashMap();
        this.f17214f = new a();
        this.f17215g = new b();
        this.f17213e = cVar;
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: a */
    public AbstractC6243a mo19399a() {
        EmojiCategory emojiCategory = (EmojiCategory) this.f17211c.getItem(0);
        this.f17211c.m19751a(0);
        return this.f17212d.get(emojiCategory.toString());
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: b */
    public void mo19400b() {
        this.f17249b.f17221b.m19450a(this.f17248a, this.f17249b.f17224e.getAdapter().getCount());
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: d */
    public void mo19401d() {
        this.f17249b.f17223d.setVisibility(0);
    }

    @Override // p086h4.AbstractC5005i
    /* renamed from: e */
    public void mo19402e(int i9) {
        this.f17249b.f17221b.m19451b(i9);
    }

    /* renamed from: j */
    public void m19403j() {
        HorizontalGridView horizontalGridView = (HorizontalGridView) this.f17249b.f17220a.findViewById(R.id.emojiListGridView);
        C5051b c5051b = new C5051b(this.f17248a, android.R.layout.simple_list_item_1, new ArrayList());
        this.f17211c = c5051b;
        horizontalGridView.setAdapter((ListAdapter) c5051b);
        horizontalGridView.setOnItemClickListener(this.f17214f);
        m19404k();
    }

    /* renamed from: k */
    public final void m19404k() {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, EmojiCategory.values());
        this.f17211c.addAll(arrayList);
        this.f17211c.notifyDataSetChanged();
        new d(arrayList).execute(new String[0]);
    }
}
