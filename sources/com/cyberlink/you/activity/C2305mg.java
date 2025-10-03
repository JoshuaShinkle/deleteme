package com.cyberlink.you.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.utility.LoadImageUtils;
import com.mobeta.android.dslv.DragSortListView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import p016b5.C0690f;
import p116k4.C5164m0;

/* renamed from: com.cyberlink.you.activity.mg */
/* loaded from: classes.dex */
public class C2305mg extends Fragment {

    /* renamed from: g */
    public static List<Long> f10895g = new ArrayList();

    /* renamed from: b */
    public c f10896b;

    /* renamed from: c */
    public String f10897c;

    /* renamed from: d */
    public long f10898d = -1;

    /* renamed from: e */
    public DragSortListView.InterfaceC4497p f10899e = new a();

    /* renamed from: f */
    public DragSortListView.InterfaceC4488g f10900f = new b();

    /* renamed from: com.cyberlink.you.activity.mg$a */
    public class a implements DragSortListView.InterfaceC4497p {
        public a() {
        }

        @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4497p
        public void remove(int i9) {
            Cursor cursorMo19006d = C2305mg.this.f10896b.mo19006d();
            if (cursorMo19006d.moveToPosition(C2305mg.this.f10896b.m18091m(i9))) {
                long j9 = cursorMo19006d.getLong(cursorMo19006d.getColumnIndex("packID"));
                StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(j9);
                if (stickerPackObjM15293k != null) {
                    stickerPackObjM15293k.m14818v(false);
                    C2950b0.m14925x().m15301s(j9, stickerPackObjM15293k);
                }
                C2305mg.this.f10896b.remove(i9);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.mg$b */
    public class b implements DragSortListView.InterfaceC4488g {
        public b() {
        }

        @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4488g
        /* renamed from: a */
        public float mo12438a(float f9, long j9) {
            return f9 > 0.8f ? C2305mg.this.f10896b.getCount() / 0.001f : f9 * 10.0f;
        }
    }

    /* renamed from: com.cyberlink.you.activity.mg$c */
    public class c extends C0690f {

        /* renamed from: t */
        public LayoutInflater f10903t;

        public c(Context context, int i9, Cursor cursor, String[] strArr, int[] iArr, int i10) {
            super(context, i9, cursor, strArr, iArr, i10);
            this.f10903t = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        @Override // p062f0.AbstractC4774a, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            FragmentActivity activity = C2305mg.this.getActivity();
            if (view == null) {
                view = this.f10903t.inflate(R.layout.view_item_sticker_pack_setting, viewGroup, false);
            }
            Cursor cursorMo19006d = mo19006d();
            if (cursorMo19006d.moveToPosition(m18091m(i9))) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imageCover);
                TextView textView = (TextView) view.findViewById(R.id.textPublisher);
                TextView textView2 = (TextView) view.findViewById(R.id.textPackName);
                int columnIndex = cursorMo19006d.getColumnIndex("packID");
                int columnIndex2 = cursorMo19006d.getColumnIndex("coverURL");
                int columnIndex3 = cursorMo19006d.getColumnIndex("publisher");
                int columnIndex4 = cursorMo19006d.getColumnIndex("pack");
                long j9 = cursorMo19006d.getLong(columnIndex);
                textView.setText(cursorMo19006d.getString(columnIndex3));
                textView2.setText(cursorMo19006d.getString(columnIndex4));
                LoadImageUtils.m16640y(activity, j9, cursorMo19006d.getString(columnIndex2), imageView, true);
                if (j9 == C2305mg.this.f10898d) {
                    view.findViewById(R.id.imageRemoveLayout).setVisibility(8);
                    view.findViewById(R.id.dummyRemoveLayout).setVisibility(0);
                } else {
                    view.findViewById(R.id.imageRemoveLayout).setVisibility(0);
                    view.findViewById(R.id.dummyRemoveLayout).setVisibility(8);
                }
            }
            return view;
        }
    }

    /* renamed from: i */
    public final long m12435i() {
        boolean z8;
        long jM20107l = C5164m0.m20107l();
        List<Long> list = f10895g;
        if (list != null) {
            Iterator<Long> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().longValue() == jM20107l) {
                    z8 = true;
                    break;
                }
            }
            z8 = false;
        } else {
            z8 = false;
        }
        if (z8) {
            return jM20107l;
        }
        return 205L;
    }

    /* renamed from: j */
    public String m12436j() {
        return this.f10897c;
    }

    /* renamed from: k */
    public boolean m12437k() {
        c cVar = this.f10896b;
        if (cVar == null) {
            Log.d("StickerListFragment", "[saveOrderResults] mAdapter == null");
            return false;
        }
        Cursor cursorMo19006d = cVar.mo19006d();
        int columnIndex = cursorMo19006d.getColumnIndex("packID");
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.f10896b.m18092n().iterator();
        while (it.hasNext()) {
            if (cursorMo19006d.moveToPosition(it.next().intValue())) {
                arrayList.add(Long.valueOf(cursorMo19006d.getLong(columnIndex)));
            }
        }
        SharedPreferences sharedPreferences = Globals.m7388i0().getApplicationContext().getSharedPreferences("U", 0);
        String string = new JSONArray((Collection) arrayList).toString();
        this.f10897c = string.substring(1, string.length() - 1);
        sharedPreferences.edit().putString("stickerPacksOrder", string).apply();
        return !arrayList.equals(f10895g);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_current_sticker_list, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        String[] strArr = {"publisher", "pack", "packID", "coverURL"};
        int i9 = 0;
        int[] iArr = new int[0];
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id", "publisher", "pack", "packID", "coverURL"});
        List<StickerPackObj> listM15294l = C2950b0.m14925x().m15294l();
        f10895g = new ArrayList();
        for (StickerPackObj stickerPackObj : listM15294l) {
            Long lValueOf = Long.valueOf(stickerPackObj.m14803g());
            matrixCursor.newRow().add(Integer.valueOf(i9)).add(stickerPackObj.m14808l()).add(stickerPackObj.m14804h()).add(lValueOf).add(stickerPackObj.m14812p().f13074b);
            f10895g.add(lValueOf);
            i9++;
        }
        this.f10898d = m12435i();
        Log.d("StickerListFragment", "mCanNotDeleteStickerId = " + this.f10898d);
        this.f10896b = new c(getActivity(), R.layout.view_item_sticker_pack_setting, matrixCursor, strArr, iArr, 0);
        DragSortListView dragSortListView = (DragSortListView) getActivity().findViewById(R.id.list);
        dragSortListView.setAdapter((ListAdapter) this.f10896b);
        dragSortListView.m18057n0(3, getString(R.string.delete_sticker_pack_title), getString(R.string.delete_sticker_pack_text), getString(R.string.ok), getString(R.string.cancel_text));
        dragSortListView.setRemoveListener(this.f10899e);
        dragSortListView.setDragScrollProfile(this.f10900f);
    }
}
