package com.cyberlink.you.pages.photoimport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.pages.photoimport.C3104a;
import com.cyberlink.you.pages.photoimport.C3105b;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.Permission.Permission;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import p025c4.AbstractC0733k;
import p025c4.C0724b;
import p025c4.C0726d;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;

/* loaded from: classes.dex */
public class PhotoImportActivity extends BaseFragmentActivity {

    /* renamed from: n */
    public static final String f14205n = "PhotoImportActivity";

    /* renamed from: c */
    public ArrayList<ImageItem> f14206c;

    /* renamed from: e */
    public TextView f14208e;

    /* renamed from: f */
    public Button f14209f;

    /* renamed from: g */
    public AbstractC0733k f14210g;

    /* renamed from: d */
    public ArrayList<ImageItem> f14207d = new ArrayList<>();

    /* renamed from: h */
    public AsyncTaskC3100c f14211h = new AsyncTaskC3100c(this, null);

    /* renamed from: i */
    public boolean f14212i = false;

    /* renamed from: j */
    public boolean f14213j = false;

    /* renamed from: k */
    public boolean f14214k = false;

    /* renamed from: l */
    public boolean f14215l = false;

    /* renamed from: m */
    public View.OnClickListener f14216m = new View.OnClickListener() { // from class: c4.g
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3438b.m16165b1(view);
        }
    };

    /* renamed from: com.cyberlink.you.pages.photoimport.PhotoImportActivity$a */
    public class C3098a implements InterfaceC5288c {
        public C3098a() {
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
            PhotoImportActivity.this.finish();
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            PhotoImportActivity.this.f14211h.executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.PhotoImportActivity$b */
    public class C3099b implements C3105b.b {
        public C3099b() {
        }

        @Override // com.cyberlink.you.pages.photoimport.C3105b.b
        /* renamed from: a */
        public void mo16180a(ImageItem imageItem) {
            PhotoImportActivity.this.f14207d.add(imageItem);
            PhotoImportActivity.this.m16179j1();
        }

        @Override // com.cyberlink.you.pages.photoimport.C3105b.b
        /* renamed from: b */
        public void mo16181b(ImageItem imageItem) {
            PhotoImportActivity.this.f14207d.remove(imageItem);
            PhotoImportActivity.this.m16179j1();
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.PhotoImportActivity$c */
    public class AsyncTaskC3100c extends AsyncTask<Void, Integer, ArrayList<ImageItem>> {
        public AsyncTaskC3100c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<ImageItem> doInBackground(Void... voidArr) throws IllegalArgumentException {
            C0726d c0726d = new C0726d(PhotoImportActivity.this);
            ArrayList<ImageItem> arrayList = new ArrayList<>();
            try {
                c0726d.m3567f(arrayList);
            } catch (SecurityException unused) {
                Log.d(PhotoImportActivity.f14205n, "Need to request permission first.");
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<ImageItem> arrayList) {
            super.onPostExecute(arrayList);
            PhotoImportActivity.this.f14206c = arrayList;
            if (PhotoImportActivity.this.f14206c.size() == 0) {
                C5187v0.m20267c(R.string.no_local_photos);
            } else {
                PhotoImportActivity photoImportActivity = PhotoImportActivity.this;
                photoImportActivity.m16175f1((ImageItem) photoImportActivity.f14206c.get(0));
            }
        }

        public /* synthetic */ AsyncTaskC3100c(PhotoImportActivity photoImportActivity, C3098a c3098a) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a1 */
    public /* synthetic */ void m16164a1(View view) {
        m16172c1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b1 */
    public /* synthetic */ void m16165b1(View view) {
        if (this.f14214k || this.f14212i || this.f14207d.size() != 1) {
            m16177h1();
        } else {
            m16176g1();
        }
    }

    /* renamed from: U0 */
    public final void m16166U0(String str) {
        getSupportFragmentManager().mo1844a().m1986p(R.id.fragmentContainer, this.f14210g, str).mo1794h();
    }

    /* renamed from: V0 */
    public C3104a.b m16167V0() {
        return new C3104a.b() { // from class: c4.h
            @Override // com.cyberlink.you.pages.photoimport.C3104a.b
            /* renamed from: a */
            public final void mo3574a(ImageItem imageItem) {
                this.f3439a.m16175f1(imageItem);
            }
        };
    }

    /* renamed from: W0 */
    public final Bundle m16168W0() {
        Bundle extras = getIntent().getExtras();
        return extras == null ? new Bundle() : extras;
    }

    /* renamed from: X0 */
    public C3105b.b m16169X0() {
        return new C3099b();
    }

    /* renamed from: Y0 */
    public final void m16170Y0() {
        this.f14208e = (TextView) findViewById(R.id.txt_title);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: c4.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3437b.m16164a1(view);
            }
        });
        Button button = (Button) findViewById(R.id.btnBottomDone);
        this.f14209f = button;
        button.setOnClickListener(this.f14216m);
        m16179j1();
    }

    /* renamed from: Z0 */
    public boolean m16171Z0() {
        return this.f14214k;
    }

    /* renamed from: c1 */
    public final void m16172c1() {
        if (this.f14210g instanceof C3105b) {
            m16174e1();
        } else {
            finish();
            C0724b.m3557c().m3558a();
        }
    }

    /* renamed from: d1 */
    public final void m16173d1(String str) {
        this.f14208e.setText(str);
    }

    /* renamed from: e1 */
    public final void m16174e1() {
        m16173d1(getString(R.string.albums));
        this.f14210g = new C3104a();
        Bundle bundleM16168W0 = m16168W0();
        bundleM16168W0.putSerializable("AlbumListFragment.albums", this.f14206c);
        this.f14210g.setArguments(bundleM16168W0);
        m16166U0(this.f14210g.mo3575g());
    }

    /* renamed from: f1 */
    public final void m16175f1(ImageItem imageItem) {
        m16173d1(imageItem.m16130c());
        Bundle bundleM16168W0 = m16168W0();
        bundleM16168W0.putSerializable("PhotoGridFragment.album", imageItem);
        bundleM16168W0.putSerializable("PhotoGridFragment.selectedPhotos", this.f14207d);
        C3105b c3105b = new C3105b();
        this.f14210g = c3105b;
        c3105b.setArguments(bundleM16168W0);
        m16166U0(this.f14210g.mo3575g());
    }

    /* renamed from: g1 */
    public final void m16176g1() {
        Intent intent = new Intent(this, (Class<?>) TouchImageActivity.class);
        Bundle bundleM16168W0 = m16168W0();
        ArrayList<ImageItem> arrayListM3559b = C0724b.m3557c().m3559b();
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i10 >= arrayListM3559b.size()) {
                break;
            }
            if (arrayListM3559b.get(i10).equals(this.f14207d.get(0))) {
                i9 = i10;
                break;
            }
            i10++;
        }
        bundleM16168W0.putInt("position", i9);
        bundleM16168W0.putBoolean("isEnterColorPresetPage", true);
        bundleM16168W0.putBoolean("enableMeetingShareDoc", this.f14215l);
        intent.putExtras(bundleM16168W0);
        startActivityForResult(intent, 1);
    }

    /* renamed from: h1 */
    public final void m16177h1() {
        if (this.f14214k || this.f14207d.size() >= 1) {
            Intent intent = new Intent();
            intent.putExtra("import_images", this.f14207d);
            setResult(-1, intent);
            finish();
            C0724b.m3557c().m3558a();
        }
    }

    /* renamed from: i1 */
    public final void m16178i1() {
        C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new C3098a(), this);
    }

    /* renamed from: j1 */
    public final void m16179j1() {
        int size = this.f14207d.size();
        if (this.f14212i) {
            if (size == 0) {
                this.f14209f.setEnabled(false);
                this.f14209f.setText(R.string.select_album_title);
                return;
            }
            this.f14209f.setEnabled(true);
            this.f14209f.setText(getString(R.string.add_btn) + " (" + size + ")");
            return;
        }
        if (size == 0) {
            this.f14209f.setEnabled(false);
            this.f14209f.setText(R.string.select_album_title);
            return;
        }
        if (size == 1) {
            this.f14209f.setEnabled(true);
            if (!this.f14214k) {
                this.f14209f.setText(R.string.registration_phone_registration_next_btn);
                return;
            }
            this.f14209f.setText(getString(R.string.send_btn) + " (1)");
            return;
        }
        this.f14209f.setEnabled(true);
        if (this.f14213j) {
            this.f14209f.setText(getString(R.string.add_btn) + " (" + size + ")");
            return;
        }
        this.f14209f.setText(getString(R.string.send_btn) + " (" + size + ")");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        ArrayList<ImageItem> arrayListM3559b;
        if (i9 == 1 && i10 == -1 && (arrayListM3559b = C0724b.m3557c().m3559b()) != null) {
            this.f14207d.clear();
            for (int i11 = 0; i11 < arrayListM3559b.size(); i11++) {
                ImageItem imageItem = arrayListM3559b.get(i11);
                if (imageItem.m16132e() && !this.f14207d.contains(imageItem)) {
                    this.f14207d.add(imageItem);
                }
            }
            if (intent.getExtras().getBoolean("start_import", false)) {
                if (intent.getExtras().getBoolean("update_notes", false) && intent.getExtras().getInt("position") == -1) {
                    return;
                }
                m16177h1();
            }
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_photo_import);
        Globals.m7384g4(0);
        m16178i1();
        this.f14212i = getIntent().getBooleanExtra("isImportedToAlbums", false);
        this.f14213j = getIntent().getBooleanExtra("isCallFromForumsOrPolls", false);
        this.f14214k = getIntent().getBooleanExtra("isEnableE2EE", false);
        this.f14215l = getIntent().getBooleanExtra("enableMeetingShareDoc", false);
        m16170Y0();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f14211h.cancel(false);
        Globals.m7384g4(0);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m16179j1();
    }
}
