package com.cyberlink.you.pages.photoimport;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.cyberlink.p030U.R;
import java.util.ArrayList;
import p025c4.AbstractC0733k;
import p025c4.C0723a;

/* renamed from: com.cyberlink.you.pages.photoimport.a */
/* loaded from: classes.dex */
public class C3104a extends AbstractC0733k {

    /* renamed from: f */
    public static final String f14248f = "a";

    /* renamed from: b */
    public b f14249b;

    /* renamed from: c */
    public ArrayList<ImageItem> f14250c;

    /* renamed from: d */
    public C0723a f14251d;

    /* renamed from: e */
    public ListView f14252e;

    /* renamed from: com.cyberlink.you.pages.photoimport.a$a */
    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C3104a.this.f14249b.mo3574a((ImageItem) C3104a.this.f14250c.get(i9));
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.a$b */
    public interface b {
        /* renamed from: a */
        void mo3574a(ImageItem imageItem);
    }

    @Override // p025c4.AbstractC0733k
    /* renamed from: g */
    public String mo3575g() {
        return f14248f;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f14249b = ((PhotoImportActivity) activity).m16167V0();
        this.f14250c = (ArrayList) getArguments().getSerializable("AlbumListFragment.albums");
        this.f14251d = new C0723a(activity, R.layout.album_list, this.f14250c, this.f14252e);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ListView listView = (ListView) layoutInflater.inflate(R.layout.fragment_album_list, viewGroup, false);
        this.f14252e = listView;
        listView.setAdapter((ListAdapter) this.f14251d);
        this.f14252e.setOnItemClickListener(new a());
        return this.f14252e;
    }
}
