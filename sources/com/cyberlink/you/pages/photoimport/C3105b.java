package com.cyberlink.you.pages.photoimport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.cyberlink.p030U.R;
import java.util.ArrayList;
import p025c4.AbstractC0733k;
import p025c4.C0724b;
import p025c4.C0726d;

/* renamed from: com.cyberlink.you.pages.photoimport.b */
/* loaded from: classes.dex */
public class C3105b extends AbstractC0733k {

    /* renamed from: j */
    public static final String f14254j = "b";

    /* renamed from: b */
    public GridView f14255b;

    /* renamed from: c */
    public C3106c f14256c;

    /* renamed from: d */
    public b f14257d;

    /* renamed from: e */
    public ArrayList<ImageItem> f14258e;

    /* renamed from: f */
    public boolean f14259f = false;

    /* renamed from: g */
    public ProgressDialog f14260g;

    /* renamed from: h */
    public int f14261h;

    /* renamed from: i */
    public a f14262i;

    /* renamed from: com.cyberlink.you.pages.photoimport.b$a */
    public class a extends AsyncTask<Void, Integer, ArrayList<ImageItem>> {

        /* renamed from: a */
        public String f14263a;

        public a(String str) {
            this.f14263a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<ImageItem> doInBackground(Void... voidArr) throws IllegalArgumentException {
            ArrayList<ImageItem> arrayList = new ArrayList<>();
            if (C3105b.this.getActivity() != null) {
                new C0726d(C3105b.this.getActivity()).m3566e(this.f14263a, arrayList);
                C0724b.m3557c().m3560d(arrayList);
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<ImageItem> arrayList) {
            super.onPostExecute(arrayList);
            C3105b.this.m16235l(arrayList);
            if (C3105b.this.f14260g == null || !C3105b.this.f14260g.isShowing()) {
                return;
            }
            C3105b.this.f14260g.dismiss();
            C3105b.this.f14260g = null;
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.b$b */
    public interface b {
        /* renamed from: a */
        void mo16180a(ImageItem imageItem);

        /* renamed from: b */
        void mo16181b(ImageItem imageItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public /* synthetic */ void m16234m(AdapterView adapterView, View view, int i9, long j9) {
        if (this.f14256c.m16244f(i9)) {
            this.f14256c.m16248j();
            return;
        }
        Intent intent = new Intent(getActivity(), (Class<?>) TouchImageActivity.class);
        Bundle arguments = getArguments();
        arguments.putInt("position", i9);
        if (getActivity() != null) {
            arguments.putBoolean("isEnableE2EE", ((PhotoImportActivity) getActivity()).m16171Z0());
        }
        intent.putExtras(arguments);
        getActivity().startActivityForResult(intent, 1);
    }

    @Override // p025c4.AbstractC0733k
    /* renamed from: g */
    public String mo3575g() {
        return f14254j;
    }

    /* renamed from: l */
    public final void m16235l(ArrayList<ImageItem> arrayList) {
        m16236n(arrayList);
        if (isAdded() && arrayList.size() != 0) {
            C3106c c3106c = new C3106c(getActivity(), R.layout.photo_list, arrayList, this.f14255b);
            this.f14256c = c3106c;
            c3106c.m16246h(this.f14257d);
            this.f14256c.m16247i(this.f14259f);
            this.f14256c.m16245g(this.f14261h);
            this.f14255b.setAdapter((ListAdapter) this.f14256c);
        }
    }

    /* renamed from: n */
    public final void m16236n(ArrayList<ImageItem> arrayList) {
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            ImageItem imageItem = arrayList.get(i9);
            if (this.f14258e.contains(imageItem)) {
                imageItem.m16151x(true);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle arguments = getArguments();
        ImageItem imageItem = (ImageItem) arguments.getSerializable("PhotoGridFragment.album");
        this.f14258e = (ArrayList) arguments.getSerializable("PhotoGridFragment.selectedPhotos");
        this.f14259f = arguments.getBoolean("singleSelect4ImportPhoto", false);
        this.f14261h = arguments.getInt("selectLimitCount", -1);
        this.f14257d = ((PhotoImportActivity) activity).m16169X0();
        a aVar = new a(imageItem.m16131d());
        this.f14262i = aVar;
        aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        this.f14260g = progressDialog;
        progressDialog.setIndeterminate(false);
        this.f14260g.setCancelable(false);
        this.f14260g.show();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        GridView gridView = (GridView) layoutInflater.inflate(R.layout.fragment_photo_grid, viewGroup, false);
        this.f14255b = gridView;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: c4.e
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i9, long j9) {
                this.f3436b.m16234m(adapterView, view, i9, j9);
            }
        });
        return this.f14255b;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a aVar = this.f14262i;
        if (aVar != null) {
            aVar.cancel(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        C3106c c3106c = this.f14256c;
        if (c3106c != null) {
            c3106c.notifyDataSetChanged();
            ArrayList<ImageItem> arrayListM3559b = C0724b.m3557c().m3559b();
            for (int i9 = 0; i9 < arrayListM3559b.size(); i9++) {
                ImageItem imageItem = arrayListM3559b.get(i9);
                if (!this.f14258e.contains(imageItem) && imageItem.m16132e()) {
                    this.f14258e.add(imageItem);
                    this.f14257d.mo16180a(imageItem);
                } else if (!imageItem.m16132e()) {
                    this.f14258e.remove(imageItem);
                    this.f14257d.mo16181b(imageItem);
                }
            }
        }
    }
}
