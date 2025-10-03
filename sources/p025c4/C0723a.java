package p025c4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import p173q2.C6136j;

/* renamed from: c4.a */
/* loaded from: classes.dex */
public class C0723a extends ArrayAdapter<ImageItem> {

    /* renamed from: b */
    public Context f3408b;

    /* renamed from: c */
    public int f3409c;

    /* renamed from: d */
    public ListView f3410d;

    /* renamed from: e */
    public ArrayList<ImageItem> f3411e;

    /* renamed from: f */
    @SuppressLint({"UseSparseArrays"})
    public Map<Integer, ArrayList<ImageItem>> f3412f;

    /* renamed from: c4.a$b */
    public static class b {

        /* renamed from: a */
        public TextView f3413a;

        /* renamed from: b */
        public ImageView f3414b;

        /* renamed from: c */
        public TextView f3415c;

        public b() {
        }
    }

    public C0723a(Context context, int i9, ArrayList<ImageItem> arrayList, ListView listView) {
        super(context, i9, arrayList);
        this.f3412f = new HashMap();
        this.f3409c = i9;
        this.f3408b = context;
        this.f3411e = arrayList;
        this.f3410d = listView;
    }

    /* renamed from: a */
    public int m3556a(int i9) {
        ArrayList<ImageItem> arrayList = this.f3412f.get(Integer.valueOf(i9));
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        b bVar;
        Log.d("AlbumListAdapter", "[getView] position = " + i9);
        if (view == null) {
            view = ((Activity) this.f3408b).getLayoutInflater().inflate(this.f3409c, viewGroup, false);
            bVar = new b();
            bVar.f3413a = (TextView) view.findViewById(R.id.txt_name);
            bVar.f3414b = (ImageView) view.findViewById(R.id.img_photo);
            bVar.f3415c = (TextView) view.findViewById(R.id.badge);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        ImageItem imageItem = this.f3411e.get(i9);
        if (CLUtility.m16510Z1(imageItem.m16136i()) != null) {
            C6136j.m23601u(this.f3408b, bVar.f3414b, imageItem.m16136i(), R.drawable.doc_thumbnail_default, 384);
        } else {
            C6136j.m23601u(this.f3408b, bVar.f3414b, imageItem.m16135h(), R.drawable.doc_thumbnail_default, 384);
        }
        TextView textView = bVar.f3413a;
        if (textView != null) {
            textView.setText(imageItem.m16130c() + " (" + imageItem.m16133f() + ")");
        }
        TextView textView2 = bVar.f3415c;
        if (textView2 != null) {
            textView2.setText(String.valueOf(m3556a(i9)));
        }
        return view;
    }
}
