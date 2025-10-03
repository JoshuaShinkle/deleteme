package p086h4;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.sticker.StickerObj;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;

/* renamed from: h4.e */
/* loaded from: classes.dex */
public class C5001e {

    /* renamed from: a */
    public c f17228a;

    /* renamed from: c */
    public Activity f17230c;

    /* renamed from: d */
    public C4998c0 f17231d;

    /* renamed from: b */
    public boolean f17229b = false;

    /* renamed from: e */
    public View.OnClickListener f17232e = new a();

    /* renamed from: h4.e$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C5001e.this.f17228a.mo19424a((StickerObj) view.getTag(), view);
        }
    }

    /* renamed from: h4.e$b */
    public class b extends AsyncTask<String, Void, List<C4993a>> {

        /* renamed from: a */
        public List<StickerObj> f17234a;

        public b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<C4993a> doInBackground(String... strArr) {
            if (C5001e.this.f17229b) {
                return null;
            }
            this.f17234a = m19421b();
            ArrayList arrayList = new ArrayList();
            for (StickerObj stickerObj : this.f17234a) {
                if (C5001e.this.f17229b) {
                    return null;
                }
                arrayList.add(stickerObj);
            }
            return m19422c(arrayList);
        }

        /* renamed from: b */
        public final List<StickerObj> m19421b() {
            List<Long> listM19428c = new C5002f(C5001e.this.f17230c).m19428c();
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < listM19428c.size(); i9++) {
                arrayList.add(C2950b0.m14924w().m15278f(listM19428c.get(i9).longValue()));
            }
            return arrayList;
        }

        /* renamed from: c */
        public final List<C4993a> m19422c(List<StickerObj> list) {
            ArrayList arrayList = new ArrayList();
            View viewInflate = null;
            for (int i9 = 0; i9 < list.size(); i9++) {
                StickerObj stickerObj = list.get(i9);
                int i10 = i9 % 8;
                if (i10 == 0) {
                    if (viewInflate != null) {
                        arrayList.add(new C4993a("-999", viewInflate));
                    }
                    viewInflate = C5001e.this.f17230c.getLayoutInflater().inflate(R.layout.view_item_sticker_preview, (ViewGroup) null);
                }
                if (!C5001e.this.f17229b) {
                    ImageView imageView = (ImageView) viewInflate.findViewById(C5001e.this.f17230c.getResources().getIdentifier("stickerView" + String.valueOf(i10), TtmlNode.ATTR_ID, C5001e.this.f17230c.getPackageName()));
                    if (imageView != null) {
                        imageView.setImageBitmap(null);
                        imageView.setTag(stickerObj);
                        imageView.setOnClickListener(C5001e.this.f17232e);
                    }
                }
            }
            if (viewInflate != null) {
                arrayList.add(new C4993a("-999", viewInflate));
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<C4993a> list) {
            if (C5001e.this.f17229b) {
                this.f17234a = null;
            } else {
                C5001e.this.f17228a.mo19425b(list);
            }
        }

        public /* synthetic */ b(C5001e c5001e, a aVar) {
            this();
        }
    }

    /* renamed from: h4.e$c */
    public interface c {
        /* renamed from: a */
        void mo19424a(StickerObj stickerObj, View view);

        /* renamed from: b */
        void mo19425b(List<C4993a> list);
    }

    public C5001e(Activity activity, c cVar, C4998c0 c4998c0) {
        this.f17228a = cVar;
        this.f17230c = activity;
        this.f17231d = c4998c0;
    }

    /* renamed from: e */
    public void m19418e() {
        new b(this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }

    /* renamed from: f */
    public void m19419f() {
        this.f17229b = true;
    }
}
