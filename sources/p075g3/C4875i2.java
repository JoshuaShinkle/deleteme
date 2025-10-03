package p075g3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.ulauncher.AbstractC2555a;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2993v0;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p042d0.C4619d;
import p116k4.C5170o0;
import p209u2.ThreadFactoryC6373j;

/* renamed from: g3.i2 */
/* loaded from: classes.dex */
public class C4875i2 extends AbstractC2555a {

    /* renamed from: A */
    public static ThreadPoolExecutor f16901A = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("FullTextSearch.SingleThread"));

    /* renamed from: e */
    public EditText f16903e;

    /* renamed from: f */
    public View f16904f;

    /* renamed from: g */
    public ListView f16905g;

    /* renamed from: h */
    public C4855e2 f16906h;

    /* renamed from: i */
    public ProgressBar f16907i;

    /* renamed from: j */
    public View f16908j;

    /* renamed from: k */
    public View f16909k;

    /* renamed from: l */
    public ImageView f16910l;

    /* renamed from: m */
    public TextView f16911m;

    /* renamed from: n */
    public TextView f16912n;

    /* renamed from: o */
    public TextView f16913o;

    /* renamed from: p */
    public TextView f16914p;

    /* renamed from: d */
    public final String f16902d = "FullTextSearchActivity";

    /* renamed from: q */
    public AsyncTask<String, Void, List<C2993v0>> f16915q = null;

    /* renamed from: r */
    public Handler f16916r = new Handler();

    /* renamed from: s */
    public C4619d f16917s = null;

    /* renamed from: t */
    public final int f16918t = 300;

    /* renamed from: u */
    public View.OnClickListener f16919u = new b();

    /* renamed from: v */
    public View.OnClickListener f16920v = new View.OnClickListener() { // from class: g3.f2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16882b.m19204E(view);
        }
    };

    /* renamed from: w */
    public AdapterView.OnItemClickListener f16921w = new c();

    /* renamed from: x */
    public TextView.OnEditorActionListener f16922x = new TextView.OnEditorActionListener() { // from class: g3.g2
        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
            return this.f16889b.m19205F(textView, i9, keyEvent);
        }
    };

    /* renamed from: y */
    public TextWatcher f16923y = new d();

    /* renamed from: z */
    public final Runnable f16924z = new e();

    /* renamed from: g3.i2$a */
    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (f10 < BitmapDescriptorFactory.HUE_RED || f10 > BitmapDescriptorFactory.HUE_RED) {
                CLUtility.m16589t1(C4875i2.this.getActivity());
            }
            return super.onScroll(motionEvent, motionEvent2, f9, f10);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(C4875i2.this.getActivity());
            return false;
        }
    }

    /* renamed from: g3.i2$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C4875i2.this.f16903e.setText("");
            C4875i2.this.f16906h.notifyDataSetChanged();
        }
    }

    /* renamed from: g3.i2$c */
    public class c implements AdapterView.OnItemClickListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C2993v0 c2993v0 = (C2993v0) C4875i2.this.f16906h.getItem(i9);
            if (c2993v0 != null) {
                if (Integer.valueOf(c2993v0.m15253f()).intValue() <= 1) {
                    Group groupM15077n = C2950b0.m14912k().m15077n(c2993v0.m15250c());
                    Intent intent = new Intent(C4875i2.this.getActivity(), (Class<?>) ChatDialogActivity.class);
                    intent.putExtra("Group", groupM15077n);
                    intent.putExtra("messageID", c2993v0.m15254g());
                    intent.putExtra("SearchChat", C4875i2.this.f16903e.getText().toString());
                    C4875i2.this.startActivityForResult(intent, 0);
                    return;
                }
                CLUtility.m16589t1(C4875i2.this.getActivity());
                C4875i2.this.f16908j.setVisibility(8);
                C4875i2.this.f16906h.m19186f(true);
                C4875i2.this.f16906h.m19185e(c2993v0.m15250c());
                C4875i2.this.f16909k.setVisibility(0);
                C4875i2.this.f16911m.setText(c2993v0.m15248a());
                C4875i2.this.f16912n.setText(C4875i2.this.getString(R.string.search_how_many_result_in_group, c2993v0.m15253f(), C4875i2.this.f16903e.getText().toString()));
                ((C4857f) C4875i2.this.getParentFragment()).m19199x(false);
                C4875i2.this.m19223H();
            }
        }
    }

    /* renamed from: g3.i2$d */
    public class d implements TextWatcher {
        public d() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            boolean z8 = !C5170o0.m20169d(charSequence);
            C4875i2.this.f16904f.setVisibility(z8 ? 0 : 8);
            C4875i2.this.f16913o.setVisibility(8);
            if (z8 && charSequence.length() >= 2) {
                C4875i2.this.f16914p.setVisibility(8);
                C4875i2.this.f16906h.m19186f(false);
                C4875i2.this.m19223H();
            } else {
                C4875i2.this.f16914p.setVisibility(0);
                C4875i2.this.f16914p.setText(R.string.search_chat_text_length_hint);
                C4875i2.this.m19224I();
                C4875i2.this.f16906h.clear();
                C4875i2.this.f16906h.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: g3.i2$e */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C4875i2.this.f16915q = new f(C4875i2.this, null);
            C4875i2.this.f16915q.executeOnExecutor(C4875i2.f16901A, C4875i2.this.f16903e.getText().toString());
        }
    }

    /* renamed from: g3.i2$f */
    public class f extends AsyncTask<String, Void, List<C2993v0>> {

        /* renamed from: a */
        public String f16930a;

        public f() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<C2993v0> doInBackground(String... strArr) {
            Thread.currentThread().setName("searchMessageQueryTask");
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strM19183c = C4875i2.this.f16906h.m19183c();
            String str = strArr[0];
            this.f16930a = str;
            List<C2993v0> listM15245h = null;
            if (!C5170o0.m20170e(str)) {
                if (isCancelled()) {
                    return null;
                }
                Log.v("FullTextSearchActivity", "searchMessageQueryTask keyword : " + this.f16930a);
                listM15245h = (!C4875i2.this.f16906h.m19184d() || C5170o0.m20170e(strM19183c)) ? C2950b0.m14922u().m15245h(this.f16930a) : C2950b0.m14922u().m15246i(this.f16930a, strM19183c);
            }
            Log.d("FullTextSearchActivity", "query message list " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
            return listM15245h;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<C2993v0> list) {
            if (C4875i2.this.f16907i != null) {
                C4875i2.this.f16907i.setVisibility(8);
            }
            if (list == null) {
                C4875i2.this.f16913o.setVisibility(0);
            }
            if (list == null || isCancelled()) {
                return;
            }
            if (C4875i2.this.f16906h != null) {
                if (list.isEmpty()) {
                    C4875i2.this.f16913o.setVisibility(0);
                    C4875i2.this.f16906h.clear();
                    C4875i2.this.f16906h.notifyDataSetChanged();
                } else {
                    C4875i2.this.f16906h.m19182a(this.f16930a, list);
                    C4875i2.this.f16913o.setVisibility(8);
                }
            }
            C4875i2.this.f16915q = null;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            C4875i2.this.f16907i.setVisibility(0);
        }

        public /* synthetic */ f(C4875i2 c4875i2, a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m19204E(View view) {
        mo12962j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F */
    public /* synthetic */ boolean m19205F(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 != 3) {
            return false;
        }
        CLUtility.m16589t1(getActivity());
        m19223H();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G */
    public /* synthetic */ boolean m19206G(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f16917s;
        return c4619d != null && c4619d.m18406a(motionEvent);
    }

    /* renamed from: D */
    public void m19222D() {
        EditText editText = this.f16903e;
        if (editText == null || C5170o0.m20170e(editText.getText().toString())) {
            return;
        }
        EditText editText2 = this.f16903e;
        editText2.setText(editText2.getText().toString());
        EditText editText3 = this.f16903e;
        editText3.setSelection(editText3.getText().length());
    }

    /* renamed from: H */
    public final void m19223H() {
        m19224I();
        this.f16906h.clear();
        this.f16906h.notifyDataSetChanged();
        this.f16916r.postDelayed(this.f16924z, 300L);
    }

    /* renamed from: I */
    public final void m19224I() {
        AsyncTask<String, Void, List<C2993v0>> asyncTask = this.f16915q;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        this.f16916r.removeCallbacks(this.f16924z);
        ProgressBar progressBar = this.f16907i;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: g */
    public int mo12959g() {
        return R.layout.activity_full_text_search;
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: j */
    public boolean mo12962j() {
        if (this.f16906h.m19184d()) {
            this.f16908j.setVisibility(0);
            this.f16903e.requestFocus();
            this.f16909k.setVisibility(8);
            this.f16906h.m19186f(false);
            ((C4857f) getParentFragment()).m19199x(true);
            m19223H();
        } else {
            ((C4857f) getParentFragment()).m19197v();
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        EditText editText;
        super.onActivityResult(i9, i10, intent);
        if (i10 == 0 || i9 != 0 || (editText = this.f16903e) == null || C5170o0.m20170e(editText.getText().toString())) {
            return;
        }
        CLUtility.m16606x2(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        m19224I();
        super.onDestroy();
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f16903e.removeTextChangedListener(this.f16923y);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ListView listView = (ListView) view.findViewById(R.id.resultListView);
        this.f16905g = listView;
        listView.setOnTouchListener(new View.OnTouchListener() { // from class: g3.h2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f16895b.m19206G(view2, motionEvent);
            }
        });
        this.f16913o = (TextView) getParentFragment().getView().findViewById(R.id.emptyTextView);
        this.f16914p = (TextView) getParentFragment().getView().findViewById(R.id.hintTextView);
        C4855e2 c4855e2 = new C4855e2(getContext(), R.layout.view_item_search_chat_item);
        this.f16906h = c4855e2;
        this.f16905g.setAdapter((ListAdapter) c4855e2);
        this.f16905g.setOnItemClickListener(this.f16921w);
        this.f16907i = (ProgressBar) getParentFragment().getView().findViewById(R.id.progressbar_searching);
        this.f16908j = getParentFragment().getView().findViewById(R.id.searchChat);
        EditText editText = (EditText) getParentFragment().getView().findViewById(R.id.SearchEditText);
        this.f16903e = editText;
        editText.addTextChangedListener(this.f16923y);
        this.f16903e.setHint(getString(R.string.search_chat_hint_v2));
        this.f16903e.setOnEditorActionListener(this.f16922x);
        View viewFindViewById = getParentFragment().getView().findViewById(R.id.SearchEditXImage);
        this.f16904f = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f16919u);
        this.f16909k = view.findViewById(R.id.searchInGroupHeaderArea);
        ImageView imageView = (ImageView) view.findViewById(R.id.searchInGroupBackBtn);
        this.f16910l = imageView;
        imageView.setOnClickListener(this.f16920v);
        this.f16911m = (TextView) view.findViewById(R.id.searchInGroupTitle);
        this.f16912n = (TextView) view.findViewById(R.id.searchInGroupResult);
        m19222D();
        this.f16917s = new C4619d(getContext(), new a());
    }
}
