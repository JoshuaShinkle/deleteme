package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.activity.CreateAlbumActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import p056e4.C4755b;
import p116k4.C5187v0;
import p173q2.C6136j;

/* loaded from: classes.dex */
public class CreateAlbumActivity extends BaseFragmentActivity {

    /* renamed from: c */
    public ImageView f7449c = null;

    /* renamed from: d */
    public View f7450d = null;

    /* renamed from: e */
    public C1435b f7451e = null;

    /* renamed from: f */
    public View.OnClickListener f7452f = new View.OnClickListener() { // from class: com.cyberlink.you.activity.t0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11385b.m7814N0(view);
        }
    };

    /* renamed from: g */
    public View.OnClickListener f7453g = new View.OnClickListener() { // from class: com.cyberlink.you.activity.u0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11419b.m7815O0(view);
        }
    };

    /* renamed from: com.cyberlink.you.activity.CreateAlbumActivity$b */
    public static class C1435b extends Fragment {

        /* renamed from: b */
        public C1436c f7454b = null;

        /* renamed from: c */
        public C1437d f7455c = null;

        /* renamed from: d */
        public ArrayList<ImageItem> f7456d = null;

        /* renamed from: e */
        public ArrayList<C2973l0> f7457e = null;

        /* renamed from: f */
        public boolean f7458f = false;

        /* renamed from: com.cyberlink.you.activity.CreateAlbumActivity$b$a */
        public class a extends AsyncTask<Void, Void, Void> {

            /* renamed from: a */
            public final /* synthetic */ Intent f7459a;

            public a(Intent intent) {
                this.f7459a = intent;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                Thread.currentThread().setName("resetMediaList AsyncTask");
                Bundle extras = this.f7459a.getExtras();
                if (extras == null) {
                    return null;
                }
                if (!C1435b.this.f7458f) {
                    ArrayList arrayList = (ArrayList) extras.getSerializable("import_images");
                    if (C1435b.this.f7456d == null) {
                        return null;
                    }
                    C1435b.this.f7456d.addAll(arrayList);
                    return null;
                }
                List<C2973l0> listM14726w = C2950b0.m14914m().m14726w(extras.getLongArray("import_mediaIds"));
                if (listM14726w == null) {
                    return null;
                }
                for (C2973l0 c2973l0 : listM14726w) {
                    c2973l0.m15114A(true);
                    C1435b.this.f7457e.add(c2973l0);
                }
                return null;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Void r12) {
                if (C1435b.this.f7458f) {
                    C1435b.this.f7455c.m7849j();
                    C1435b.this.f7455c.notifyDataSetChanged();
                } else {
                    C1435b.this.f7454b.m7839i();
                    C1435b.this.f7454b.notifyDataSetChanged();
                }
            }
        }

        /* renamed from: q */
        public static /* synthetic */ void m7823q(EditText editText, String str, View view) {
            if (editText.getText().toString().equals(str)) {
                editText.setText("");
            }
        }

        /* renamed from: h */
        public boolean m7824h() {
            return this.f7458f;
        }

        /* renamed from: n */
        public ArrayList<ImageItem> m7825n() {
            try {
                return this.f7454b.m7836d();
            } catch (Exception e9) {
                e9.printStackTrace();
                return new ArrayList<>();
            }
        }

        /* renamed from: o */
        public long[] m7826o() {
            try {
                return this.f7455c.m7845d();
            } catch (Exception e9) {
                e9.printStackTrace();
                return new long[0];
            }
        }

        @Override // androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            C4755b.a.m18882b("CreateAlbumFragment", "onCreate()");
            super.onCreate(bundle);
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View viewInflate = layoutInflater.inflate(R.layout.fragment_create_album, viewGroup, false);
            GridView gridView = (GridView) viewInflate.findViewById(R.id.gridView);
            View viewFindViewById = getActivity().findViewById(R.id.btnBottomDone);
            Intent intent = getActivity().getIntent();
            this.f7458f = intent.getBooleanExtra("is_mediaOjb", false);
            this.f7456d = new ArrayList<>();
            this.f7457e = new ArrayList<>();
            if (this.f7458f) {
                C1437d c1437d = new C1437d(getActivity(), R.layout.photo_list, this.f7457e, gridView, viewFindViewById);
                this.f7455c = c1437d;
                if (gridView != null) {
                    gridView.setAdapter((ListAdapter) c1437d);
                }
            } else {
                C1436c c1436c = new C1436c(getActivity(), R.layout.photo_list, this.f7456d, gridView, viewFindViewById);
                this.f7454b = c1436c;
                if (gridView != null) {
                    gridView.setAdapter((ListAdapter) c1436c);
                }
            }
            final EditText editText = (EditText) viewInflate.findViewById(R.id.CreatealbumNameText);
            final String strM7827p = m7827p();
            editText.setText(strM7827p);
            editText.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.v0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CreateAlbumActivity.C1435b.m7823q(editText, strM7827p, view);
                }
            });
            m7828r(intent);
            return viewInflate;
        }

        @Override // androidx.fragment.app.Fragment
        public void onResume() {
            C4755b.a.m18882b("CreateAlbumFragment", "onResume()");
            super.onResume();
            if (this.f7458f) {
                this.f7455c.m7849j();
            } else {
                this.f7454b.m7839i();
            }
        }

        /* renamed from: p */
        public final String m7827p() {
            ArrayList<String> stringArrayListExtra;
            String strM16466M2 = CLUtility.m16466M2(new Date());
            if (getActivity() == null || (stringArrayListExtra = getActivity().getIntent().getStringArrayListExtra("album_name_list")) == null || !stringArrayListExtra.contains(strM16466M2)) {
                return strM16466M2;
            }
            for (int i9 = 1; i9 < 999; i9++) {
                String str = strM16466M2 + "(" + i9 + ")";
                if (!stringArrayListExtra.contains(str)) {
                    return str;
                }
            }
            return strM16466M2;
        }

        /* renamed from: r */
        public final void m7828r(Intent intent) {
            new a(intent).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateAlbumActivity$c */
    public static class C1436c extends ArrayAdapter<ImageItem> {

        /* renamed from: b */
        public Context f7461b;

        /* renamed from: c */
        public GridView f7462c;

        /* renamed from: d */
        public View f7463d;

        /* renamed from: e */
        public int f7464e;

        /* renamed from: f */
        public ArrayList<ImageItem> f7465f;

        /* renamed from: com.cyberlink.you.activity.CreateAlbumActivity$c$a */
        public class a {

            /* renamed from: a */
            public CheckBox f7466a;

            /* renamed from: b */
            public ImageView f7467b;

            /* renamed from: c */
            public View f7468c;

            public a() {
            }
        }

        public C1436c(Context context, int i9, ArrayList<ImageItem> arrayList, GridView gridView, View view) {
            super(context, i9, arrayList);
            this.f7461b = context;
            this.f7464e = i9;
            this.f7465f = arrayList;
            this.f7462c = gridView;
            this.f7463d = view;
        }

        /* renamed from: e */
        public static /* synthetic */ void m7833e(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m7834f(CompoundButton compoundButton, boolean z8) {
            m7837g(((Integer) compoundButton.getTag()).intValue(), z8);
            m7838h(((Integer) compoundButton.getTag()).intValue(), z8);
            m7839i();
        }

        /* renamed from: c */
        public int m7835c() {
            int i9 = 0;
            for (int i10 = 0; i10 < this.f7465f.size(); i10++) {
                if (this.f7465f.get(i10).m16132e()) {
                    i9++;
                }
            }
            return i9;
        }

        /* renamed from: d */
        public ArrayList<ImageItem> m7836d() {
            ArrayList<ImageItem> arrayList = new ArrayList<>();
            for (int i9 = 0; i9 < this.f7465f.size(); i9++) {
                if (this.f7465f.get(i9).m16132e()) {
                    arrayList.add(this.f7465f.get(i9));
                }
            }
            return arrayList;
        }

        /* renamed from: g */
        public void m7837g(int i9, boolean z8) {
            if (this.f7465f.size() == 0) {
                return;
            }
            this.f7465f.get(i9).m16151x(z8);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            Object[] objArr = 0;
            if (view == null) {
                view = ((Activity) this.f7461b).getLayoutInflater().inflate(this.f7464e, viewGroup, false);
                aVar = new a();
                aVar.f7466a = (CheckBox) view.findViewById(R.id.itemCheckBox);
                aVar.f7467b = (ImageView) view.findViewById(R.id.image);
                aVar.f7468c = view.findViewById(R.id.imageBorder);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            CheckBox checkBox = aVar.f7466a;
            if (checkBox != null) {
                checkBox.setTag(Integer.valueOf(i9));
                aVar.f7466a.setChecked(true);
                aVar.f7466a.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.w0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        CreateAlbumActivity.C1436c.m7833e(view2);
                    }
                });
                aVar.f7466a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.cyberlink.you.activity.x0
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                        this.f12241a.m7834f(compoundButton, z8);
                    }
                });
            }
            if (aVar.f7468c != null) {
                if (this.f7465f.get(i9).m16132e()) {
                    aVar.f7468c.setBackgroundColor(Color.argb(255, 0, 150, 225));
                } else {
                    aVar.f7468c.setBackgroundColor(Color.argb(255, 225, 225, 225));
                }
            }
            ImageItem imageItem = this.f7465f.get(i9);
            String strM16135h = imageItem != null ? imageItem.m16135h() : null;
            Uri uriM16510Z1 = imageItem != null ? CLUtility.m16510Z1(imageItem.m16136i()) : null;
            if (uriM16510Z1 != null) {
                C6136j.m23596p(this.f7461b, aVar.f7467b, uriM16510Z1, R.drawable.doc_thumbnail_default, 384);
            } else {
                C6136j.m23601u(this.f7461b, aVar.f7467b, strM16135h, R.drawable.doc_thumbnail_default, 384);
            }
            return view;
        }

        /* renamed from: h */
        public void m7838h(int i9, boolean z8) {
            View view;
            int childCount = this.f7462c.getChildCount();
            int firstVisiblePosition = this.f7462c.getFirstVisiblePosition();
            for (int i10 = 0; i10 < childCount; i10++) {
                if (firstVisiblePosition + i10 == i9 && (view = ((a) this.f7462c.getChildAt(i10).getTag()).f7468c) != null) {
                    if (z8) {
                        view.setBackgroundColor(Color.argb(255, 0, 150, 225));
                    } else {
                        view.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                }
            }
        }

        /* renamed from: i */
        public void m7839i() {
            if (this.f7463d == null) {
                return;
            }
            int iM7835c = m7835c();
            View view = this.f7463d;
            if (view != null) {
                if (iM7835c == 0) {
                    view.setEnabled(false);
                } else {
                    view.setEnabled(true);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateAlbumActivity$d */
    public static class C1437d extends ArrayAdapter<C2973l0> {

        /* renamed from: b */
        public Context f7470b;

        /* renamed from: c */
        public GridView f7471c;

        /* renamed from: d */
        public View f7472d;

        /* renamed from: e */
        public int f7473e;

        /* renamed from: f */
        public ArrayList<C2973l0> f7474f;

        /* renamed from: com.cyberlink.you.activity.CreateAlbumActivity$d$a */
        public class a {

            /* renamed from: a */
            public CheckBox f7475a;

            /* renamed from: b */
            public ImageView f7476b;

            /* renamed from: c */
            public View f7477c;

            public a() {
            }
        }

        public C1437d(Context context, int i9, ArrayList<C2973l0> arrayList, GridView gridView, View view) {
            super(context, i9, arrayList);
            this.f7470b = context;
            this.f7473e = i9;
            this.f7471c = gridView;
            this.f7472d = view;
            this.f7474f = arrayList;
        }

        /* renamed from: f */
        public static /* synthetic */ void m7842f(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m7843g(CompoundButton compoundButton, boolean z8) {
            m7847h(((Integer) compoundButton.getTag()).intValue(), z8);
            m7848i(((Integer) compoundButton.getTag()).intValue(), z8);
            m7849j();
        }

        /* renamed from: c */
        public int m7844c() {
            int i9 = 0;
            for (int i10 = 0; i10 < this.f7474f.size(); i10++) {
                if (this.f7474f.get(i10).m15132d()) {
                    i9++;
                }
            }
            return i9;
        }

        /* renamed from: d */
        public long[] m7845d() {
            ArrayList<C2973l0> arrayListM7846e = m7846e();
            long[] jArr = new long[arrayListM7846e.size()];
            for (int i9 = 0; i9 < arrayListM7846e.size(); i9++) {
                jArr[i9] = arrayListM7846e.get(i9).m15144p();
            }
            return jArr;
        }

        /* renamed from: e */
        public ArrayList<C2973l0> m7846e() {
            ArrayList<C2973l0> arrayList = new ArrayList<>();
            for (int i9 = 0; i9 < this.f7474f.size(); i9++) {
                if (this.f7474f.get(i9).m15132d()) {
                    arrayList.add(this.f7474f.get(i9));
                }
            }
            return arrayList;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            boolean zM15132d;
            if (view == null) {
                view = ((Activity) this.f7470b).getLayoutInflater().inflate(this.f7473e, viewGroup, false);
                aVar = new a();
                aVar.f7475a = (CheckBox) view.findViewById(R.id.itemCheckBox);
                aVar.f7476b = (ImageView) view.findViewById(R.id.image);
                aVar.f7477c = view.findViewById(R.id.imageBorder);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            CheckBox checkBox = aVar.f7475a;
            if (checkBox != null) {
                checkBox.setTag(Integer.valueOf(i9));
                aVar.f7475a.setChecked(true);
                aVar.f7475a.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.y0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        CreateAlbumActivity.C1437d.m7842f(view2);
                    }
                });
                aVar.f7475a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.cyberlink.you.activity.z0
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                        this.f12292a.m7843g(compoundButton, z8);
                    }
                });
            }
            C2973l0 c2973l0 = this.f7474f.get(i9);
            if (c2973l0 != null) {
                zM15132d = c2973l0.m15132d();
                MediaLoader.m7156o(this.f7470b, aVar.f7476b, c2973l0, MediaLoader.Option.THUMBNAIL);
            } else {
                zM15132d = false;
            }
            View view2 = aVar.f7477c;
            if (view2 != null) {
                if (zM15132d) {
                    view2.setBackgroundColor(Color.argb(255, 0, 150, 225));
                } else {
                    view2.setBackgroundColor(Color.argb(255, 225, 225, 225));
                }
            }
            return view;
        }

        /* renamed from: h */
        public void m7847h(int i9, boolean z8) {
            this.f7474f.get(i9).m15114A(z8);
        }

        /* renamed from: i */
        public void m7848i(int i9, boolean z8) {
            View view;
            int childCount = this.f7471c.getChildCount();
            int firstVisiblePosition = this.f7471c.getFirstVisiblePosition();
            for (int i10 = 0; i10 < childCount; i10++) {
                if (firstVisiblePosition + i10 == i9 && (view = ((a) this.f7471c.getChildAt(i10).getTag()).f7477c) != null) {
                    if (z8) {
                        view.setBackgroundColor(Color.argb(255, 0, 150, 225));
                    } else {
                        view.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                }
            }
        }

        /* renamed from: j */
        public void m7849j() {
            if (this.f7472d == null) {
                return;
            }
            int iM7844c = m7844c();
            View view = this.f7472d;
            if (view != null) {
                if (iM7844c == 0) {
                    view.setEnabled(false);
                } else {
                    view.setEnabled(true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m7814N0(View view) {
        CLUtility.m16589t1(m7816M0());
        Intent intent = new Intent();
        intent.putExtra("isImport", false);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O0 */
    public /* synthetic */ void m7815O0(View view) {
        CLUtility.m16589t1(m7816M0());
        String string = ((EditText) findViewById(R.id.CreatealbumNameText)).getText().toString();
        ArrayList<String> stringArrayListExtra = m7816M0().getIntent().getStringArrayListExtra("album_name_list");
        if (string.trim().isEmpty()) {
            C5187v0.m20267c(R.string.input_album_name);
            return;
        }
        if (stringArrayListExtra != null && stringArrayListExtra.contains(string)) {
            C5187v0.m20267c(R.string.duplicate_album_name_notification);
            return;
        }
        Intent intent = new Intent();
        if (this.f7451e.m7824h()) {
            intent.putExtra("import_mediaIds", this.f7451e.m7826o());
            intent.putExtra("is_mediaOjb", true);
        } else {
            intent.putExtra("import_images", this.f7451e.m7825n());
            intent.putExtra("is_mediaOjb", false);
        }
        intent.putExtra("create_album_name", string);
        intent.putExtra("selectPhotoOpType", 1);
        intent.putExtra("isImport", true);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: M0 */
    public final Activity m7816M0() {
        return this;
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_create_album);
        if (bundle != null) {
            this.f7451e = (C1435b) getSupportFragmentManager().mo1850g(bundle, C1435b.class.getName());
        }
        if (this.f7451e == null) {
            this.f7451e = new C1435b();
        }
        if (bundle == null) {
            getSupportFragmentManager().mo1844a().m1980b(R.id.PhotoListFragmentContainer, this.f7451e).mo1794h();
        }
        ImageView imageView = (ImageView) findViewById(R.id.back);
        this.f7449c = imageView;
        imageView.setOnClickListener(this.f7452f);
        View viewFindViewById = findViewById(R.id.btnBottomDone);
        this.f7450d = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f7453g);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        C4755b.a.m18881a("CreateAlbumActivity", "[onSaveInstanceState] in");
        super.onSaveInstanceState(bundle);
        if (this.f7451e != null) {
            getSupportFragmentManager().mo1855l(bundle, C1435b.class.getName(), this.f7451e);
        }
    }
}
