package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.bulletin.AudioItem;
import com.cyberlink.you.bulletin.StickerItem;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.PollOptionObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p115k3.C5123b;
import p116k4.C5170o0;
import p116k4.C5172p;
import p201t3.C6301o;
import p218v2.DialogC6459g;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class CreatePollOptionsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: T */
    public static final String f7479T = "CreatePollOptionsActivity";

    /* renamed from: A */
    public ImageView f7480A;

    /* renamed from: B */
    public RelativeLayout f7481B;

    /* renamed from: C */
    public ImageView f7482C;

    /* renamed from: D */
    public RelativeLayout f7483D;

    /* renamed from: E */
    public TextView f7484E;

    /* renamed from: F */
    public RelativeLayout f7485F;

    /* renamed from: G */
    public TextView f7486G;

    /* renamed from: c */
    public Group f7499c;

    /* renamed from: d */
    public String f7500d;

    /* renamed from: e */
    public C3199c f7501e;

    /* renamed from: f */
    public FriendsClient f7502f;

    /* renamed from: g */
    public ArrayList<ImageItem> f7503g;

    /* renamed from: h */
    public ArrayList<AudioItem> f7504h;

    /* renamed from: i */
    public ArrayList<VideoItem> f7505i;

    /* renamed from: j */
    public ArrayList<StickerItem> f7506j;

    /* renamed from: k */
    public DialogC6459g f7507k;

    /* renamed from: l */
    public LinearLayout f7508l;

    /* renamed from: n */
    public ImageView f7510n;

    /* renamed from: o */
    public TextView f7511o;

    /* renamed from: p */
    public ImageView f7512p;

    /* renamed from: q */
    public TextView f7513q;

    /* renamed from: r */
    public ImageView f7514r;

    /* renamed from: s */
    public TextView f7515s;

    /* renamed from: t */
    public Button f7516t;

    /* renamed from: u */
    public ScrollView f7517u;

    /* renamed from: v */
    public AsyncTask<Void, Void, TopicObj> f7518v;

    /* renamed from: w */
    public boolean f7519w;

    /* renamed from: x */
    public RelativeLayout f7520x;

    /* renamed from: y */
    public LinearLayout f7521y;

    /* renamed from: z */
    public RelativeLayout f7522z;

    /* renamed from: m */
    public List<View> f7509m = new ArrayList(20);

    /* renamed from: H */
    public boolean f7487H = false;

    /* renamed from: I */
    public View.OnClickListener f7488I = new ViewOnClickListenerC1442e();

    /* renamed from: J */
    public DialogC6459g.a f7489J = new C1443f();

    /* renamed from: K */
    public View.OnClickListener f7490K = new ViewOnClickListenerC1444g();

    /* renamed from: L */
    public View.OnClickListener f7491L = new ViewOnClickListenerC1445h();

    /* renamed from: M */
    public View.OnClickListener f7492M = new ViewOnClickListenerC1446i();

    /* renamed from: N */
    public View.OnClickListener f7493N = new ViewOnClickListenerC1447j();

    /* renamed from: O */
    public View.OnClickListener f7494O = new ViewOnClickListenerC1448k();

    /* renamed from: P */
    public View.OnClickListener f7495P = new ViewOnClickListenerC1449l();

    /* renamed from: Q */
    public View.OnClickListener f7496Q = new ViewOnClickListenerC1450m();

    /* renamed from: R */
    public View.OnClickListener f7497R = new ViewOnClickListenerC1438a();

    /* renamed from: S */
    public C3199c.b f7498S = new C1441d();

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$a */
    public class ViewOnClickListenerC1438a implements View.OnClickListener {
        public ViewOnClickListenerC1438a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m7905b(CharSequence[] charSequenceArr, DialogInterface dialogInterface, int i9) {
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
            CreatePollOptionsActivity.this.f7486G.setText(charSequenceArr[i9]);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ArrayList arrayList = new ArrayList();
            if (CreatePollOptionsActivity.this.f7510n.isSelected()) {
                for (int i9 = 1; i9 < CreatePollOptionsActivity.this.m7898o0(); i9++) {
                    arrayList.add(String.valueOf(i9));
                }
            } else {
                arrayList.add(String.valueOf(1));
            }
            final CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[0]);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(CreatePollOptionsActivity.this);
            builderM16382a.setTitle(CreatePollOptionsActivity.this.getString(R.string.vote_count)).setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.e1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    this.f10386b.m7905b(charSequenceArr, dialogInterface, i10);
                }
            });
            builderM16382a.create().show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$b */
    public class C1439b implements TextWatcher {

        /* renamed from: b */
        public final /* synthetic */ ImageView f7524b;

        /* renamed from: c */
        public final /* synthetic */ TextView f7525c;

        public C1439b(ImageView imageView, TextView textView) {
            this.f7524b = imageView;
            this.f7525c = textView;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            this.f7524b.setVisibility(charSequence.length() > 0 ? 0 : 4);
            this.f7525c.setSelected(charSequence.length() > 0);
            this.f7525c.setTextColor(CreatePollOptionsActivity.this.getResources().getColor(charSequence.length() > 0 ? R.color.you_color_normal_blue_text : R.color.you_color_normal_gray));
            CreatePollOptionsActivity.this.m7903z0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$c */
    public class AsyncTaskC1440c extends AsyncTask<Void, Void, TopicObj> {

        /* renamed from: a */
        public ProgressDialog f7527a;

        /* renamed from: b */
        public final /* synthetic */ String f7528b;

        public AsyncTaskC1440c(String str) {
            this.f7528b = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TopicObj doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("groupId", String.valueOf(CreatePollOptionsActivity.this.f7499c.f13727n)));
            arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, CreatePollOptionsActivity.this.f7500d));
            arrayList.add(new C6301o("components", this.f7528b));
            arrayList.add(new C6301o("topicType", "Poll"));
            arrayList.add(new C6301o(TtmlNode.TAG_METADATA, CreatePollOptionsActivity.this.m7896m0()));
            Pair<String, String> pairM15731j = CreatePollOptionsActivity.this.f7502f.m15731j("groupbulletin", "createTopic", arrayList);
            String str = (String) pairM15731j.first;
            String str2 = (String) pairM15731j.second;
            if (str == null) {
                Log.d(CreatePollOptionsActivity.f7479T, "Response is null");
                return null;
            }
            if (str.equals("200")) {
                TopicObj topicObjM20204z = C5172p.m20204z(C5172p.m20195q(str2), CreatePollOptionsActivity.this.f7499c.f13727n);
                C2950b0.m14906e().m14978h(topicObjM20204z, TopicObj.m14828m());
                CreatePollOptionsActivity.this.m7891g0(topicObjM20204z.m14849o());
                C6566a.m25162u("Usage_Data_Discuss");
                return topicObjM20204z;
            }
            Log.d(CreatePollOptionsActivity.f7479T, "statusCode=" + str);
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(TopicObj topicObj) {
            ProgressDialog progressDialog = this.f7527a;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.f7527a.dismiss();
            }
            CreatePollOptionsActivity.this.f7519w = false;
            CLUtility.m16589t1(CreatePollOptionsActivity.this);
            if (topicObj != null) {
                Intent intent = new Intent(CreatePollOptionsActivity.this, (Class<?>) ChatDialogActivity.class);
                intent.setFlags(603979776);
                intent.putExtra("Group", CreatePollOptionsActivity.this.f7499c);
                CreatePollOptionsActivity.this.startActivity(intent);
                Intent intent2 = new Intent(CreatePollOptionsActivity.this, (Class<?>) TopicContentActivity.class);
                intent2.putExtra("Group", CreatePollOptionsActivity.this.f7499c);
                intent2.putExtra("intent_topic", topicObj);
                CreatePollOptionsActivity.this.startActivity(intent2);
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            CreatePollOptionsActivity createPollOptionsActivity = CreatePollOptionsActivity.this;
            this.f7527a = ProgressDialog.show(createPollOptionsActivity, "", createPollOptionsActivity.getString(R.string.processing), true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$d */
    public class C1441d implements C3199c.b {
        public C1441d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m7912h(int i9, int i10, UploadMediaHelper uploadMediaHelper) {
            if (CreatePollOptionsActivity.this.f7507k != null) {
                CreatePollOptionsActivity.this.f7507k.m24771n(Integer.toString(i9 + 1), Integer.toString(i10));
                if (uploadMediaHelper.m16842f1() != null) {
                    CreatePollOptionsActivity.this.f7507k.m24764f(uploadMediaHelper.m16842f1());
                } else if (uploadMediaHelper.m16844g1() != null) {
                    CreatePollOptionsActivity.this.f7507k.m24765g(uploadMediaHelper.m16844g1().m16216e());
                } else if (uploadMediaHelper.m16836c1() != null) {
                    CreatePollOptionsActivity.this.f7507k.m24761c(R.drawable.icon_voice_default_upload);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m7913i() {
            if (CreatePollOptionsActivity.this.f7507k != null) {
                CreatePollOptionsActivity.this.f7507k.m24760b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j */
        public /* synthetic */ void m7914j() {
            if (CreatePollOptionsActivity.this.f7507k == null || !CreatePollOptionsActivity.this.f7507k.isShowing()) {
                return;
            }
            CreatePollOptionsActivity.this.f7507k.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void m7915k(String str) {
            CreatePollOptionsActivity.this.m7893j0(str);
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: a */
        public void mo7916a(final int i9, final int i10, final UploadMediaHelper uploadMediaHelper) {
            C3199c.m17015D(true);
            CreatePollOptionsActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.f1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10421b.m7912h(i9, i10, uploadMediaHelper);
                }
            });
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: b */
        public void mo7917b(C3199c c3199c) throws JSONException {
            if (!CreatePollOptionsActivity.this.isFinishing() && CreatePollOptionsActivity.this.f7507k != null) {
                CreatePollOptionsActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.h1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10706b.m7914j();
                    }
                });
            }
            final String strM19984b = C5123b.m19984b(c3199c.m17042t());
            CreatePollOptionsActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.i1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10738b.m7915k(strM19984b);
                }
            });
            c3199c.m17034E();
            C3199c.m17015D(false);
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        public void onCancel() {
            CreatePollOptionsActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.g1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10679b.m7913i();
                }
            });
            C3199c.m17015D(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$e */
    public class ViewOnClickListenerC1442e implements View.OnClickListener {
        public ViewOnClickListenerC1442e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iIntValue = ((Integer) view.getTag()).intValue();
            boolean zHasFocus = CreatePollOptionsActivity.this.f7508l.getChildAt(iIntValue).hasFocus();
            CreatePollOptionsActivity.this.f7508l.removeViewAt(iIntValue);
            CreatePollOptionsActivity.this.f7509m.remove(iIntValue);
            CreatePollOptionsActivity.this.m7902y0(iIntValue);
            int childCount = CreatePollOptionsActivity.this.f7508l.getChildCount();
            View childAt = CreatePollOptionsActivity.this.f7508l.getChildAt(childCount - 1);
            if (childCount < 3 || childCount == iIntValue || !((EditText) childAt.getTag()).getText().toString().isEmpty()) {
                CreatePollOptionsActivity.this.f7508l.addView(CreatePollOptionsActivity.this.m7895l0(childCount, false));
            }
            if (zHasFocus) {
                CreatePollOptionsActivity.this.f7508l.getChildAt(iIntValue).requestFocus();
            }
            CreatePollOptionsActivity.this.m7903z0();
            CreatePollOptionsActivity.this.m7888C0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$f */
    public class C1443f implements DialogC6459g.a {
        public C1443f() {
        }

        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public void mo7918a() {
            if (CreatePollOptionsActivity.this.f7501e != null) {
                CreatePollOptionsActivity.this.f7501e.m17036d();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$g */
    public class ViewOnClickListenerC1444g implements View.OnClickListener {
        public ViewOnClickListenerC1444g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CreatePollOptionsActivity.this.f7510n.setSelected(!CreatePollOptionsActivity.this.f7510n.isSelected());
            if (CreatePollOptionsActivity.this.f7510n.isSelected() || !CreatePollOptionsActivity.this.f7482C.isSelected()) {
                return;
            }
            CreatePollOptionsActivity.this.f7482C.setSelected(false);
            CreatePollOptionsActivity.this.f7483D.setVisibility(8);
            CreatePollOptionsActivity.this.f7485F.setVisibility(8);
            CreatePollOptionsActivity.this.f7484E.setText(CreatePollOptionsActivity.this.getString(R.string.limit_type_equal));
            CreatePollOptionsActivity.this.f7486G.setText("1");
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$h */
    public class ViewOnClickListenerC1445h implements View.OnClickListener {
        public ViewOnClickListenerC1445h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CreatePollOptionsActivity.this.f7512p.setSelected(!CreatePollOptionsActivity.this.f7512p.isSelected());
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$i */
    public class ViewOnClickListenerC1446i implements View.OnClickListener {
        public ViewOnClickListenerC1446i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CreatePollOptionsActivity.this.f7514r.setSelected(!CreatePollOptionsActivity.this.f7514r.isSelected());
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$j */
    public class ViewOnClickListenerC1447j implements View.OnClickListener {
        public ViewOnClickListenerC1447j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int i9 = 8;
            CreatePollOptionsActivity.this.f7481B.setVisibility((!CreatePollOptionsActivity.this.f7510n.isSelected() || CreatePollOptionsActivity.this.m7898o0() < 2) ? 8 : 0);
            CreatePollOptionsActivity.this.f7483D.setVisibility((CreatePollOptionsActivity.this.f7510n.isSelected() && CreatePollOptionsActivity.this.f7482C.isSelected() && CreatePollOptionsActivity.this.m7898o0() >= 2) ? 0 : 8);
            RelativeLayout relativeLayout = CreatePollOptionsActivity.this.f7485F;
            if (CreatePollOptionsActivity.this.f7510n.isSelected() && CreatePollOptionsActivity.this.f7482C.isSelected() && CreatePollOptionsActivity.this.m7898o0() >= 2) {
                i9 = 0;
            }
            relativeLayout.setVisibility(i9);
            CreatePollOptionsActivity.this.f7521y.setVisibility(0);
            CreatePollOptionsActivity.this.f7517u.setVisibility(4);
            CreatePollOptionsActivity.this.f7516t.setVisibility(4);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$k */
    public class ViewOnClickListenerC1448k implements View.OnClickListener {
        public ViewOnClickListenerC1448k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CreatePollOptionsActivity.this.f7480A.setSelected(!CreatePollOptionsActivity.this.f7480A.isSelected());
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$l */
    public class ViewOnClickListenerC1449l implements View.OnClickListener {
        public ViewOnClickListenerC1449l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CreatePollOptionsActivity.this.f7482C.setSelected(!CreatePollOptionsActivity.this.f7482C.isSelected());
            CreatePollOptionsActivity.this.f7483D.setVisibility(CreatePollOptionsActivity.this.f7482C.isSelected() ? 0 : 8);
            CreatePollOptionsActivity.this.f7485F.setVisibility(CreatePollOptionsActivity.this.f7482C.isSelected() ? 0 : 8);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreatePollOptionsActivity$m */
    public class ViewOnClickListenerC1450m implements View.OnClickListener {
        public ViewOnClickListenerC1450m() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m7920b(CharSequence[] charSequenceArr, DialogInterface dialogInterface, int i9) {
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
            CreatePollOptionsActivity.this.f7484E.setText(charSequenceArr[i9]);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(CreatePollOptionsActivity.this.getString(R.string.limit_type_maximum));
            if (CreatePollOptionsActivity.this.f7510n.isSelected()) {
                arrayList.add(CreatePollOptionsActivity.this.getString(R.string.limit_type_minimum));
            }
            arrayList.add(CreatePollOptionsActivity.this.getString(R.string.limit_type_equal));
            final CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[0]);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(CreatePollOptionsActivity.this);
            builderM16382a.setTitle(CreatePollOptionsActivity.this.getString(R.string.limit_type)).setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.j1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f10769b.m7920b(charSequenceArr, dialogInterface, i9);
                }
            });
            builderM16382a.create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m7877q0(EditText editText) {
        this.f7517u.fullScroll(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
        editText.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m7879r0(ImageView imageView, TextView textView, View view, boolean z8) {
        final EditText editText = (EditText) view;
        int i9 = R.color.you_color_normal_gray;
        if (!z8) {
            if (editText.getText().toString().isEmpty()) {
                textView.setSelected(false);
                textView.setTextColor(getResources().getColor(R.color.you_color_normal_gray));
                return;
            }
            return;
        }
        int iIntValue = ((Integer) imageView.getTag()).intValue();
        if (iIntValue == this.f7509m.size() - 1 && iIntValue < 19) {
            this.f7508l.addView(m7895l0(this.f7509m.size(), false));
            this.f7517u.post(new Runnable() { // from class: com.cyberlink.you.activity.d1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10358b.m7877q0(editText);
                }
            });
        }
        textView.setSelected(z8);
        Resources resources = getResources();
        if (z8) {
            i9 = R.color.you_color_normal_blue_text;
        }
        textView.setTextColor(resources.getColor(i9));
    }

    /* renamed from: s0 */
    public static /* synthetic */ void m7881s0(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m7883u0() {
        this.f7507k.show();
    }

    /* renamed from: C0 */
    public final void m7888C0() {
        if (!this.f7482C.isSelected() || C5170o0.m20170e(this.f7486G.getText())) {
            return;
        }
        int iM7898o0 = m7898o0();
        if (Integer.parseInt(this.f7486G.getText().toString()) >= iM7898o0) {
            if (!this.f7487H) {
                this.f7487H = true;
                AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
                builderM16382a.setMessage(getString(R.string.modify_limit_count_warning));
                builderM16382a.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.c1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        CreatePollOptionsActivity.m7881s0(dialogInterface, i9);
                    }
                });
                builderM16382a.setCancelable(false);
                builderM16382a.create().show();
            }
            this.f7486G.setText(String.valueOf(Math.max(iM7898o0 - 1, 1)));
        }
        if (iM7898o0 == 1) {
            this.f7482C.setSelected(false);
            this.f7484E.setText(getString(R.string.limit_type_equal));
            this.f7486G.setText("1");
        }
    }

    /* renamed from: D0 */
    public final void m7889D0(String str, List<ImageItem> list, List<AudioItem> list2, List<VideoItem> list3) {
        ImageItem imageItem;
        ImageItem imageItem2;
        JSONException e9;
        if (str == null || str.isEmpty() || list == null || list2 == null || list3 == null) {
            return;
        }
        if (this.f7501e == null) {
            C3199c c3199c = new C3199c();
            this.f7501e = c3199c;
            c3199c.m17033C(this.f7498S);
        }
        for (ImageItem imageItem3 : list) {
            if (imageItem3 == null || (TextUtils.isEmpty(imageItem3.m16139l()) && TextUtils.isEmpty(imageItem3.m16140m()))) {
                imageItem = imageItem3;
            } else {
                try {
                    imageItem2 = new ImageItem(new JSONObject(imageItem3.m16127H()));
                    try {
                        imageItem2.m16122C(CLUtility.m16540h0());
                        imageItem2.m16124E(imageItem3.m16139l());
                        imageItem2.m16125F(imageItem3.m16140m());
                    } catch (JSONException e10) {
                        e9 = e10;
                        Log.d(f7479T, Log.getStackTraceString(e9));
                        imageItem = imageItem2;
                        if (imageItem == null) {
                        }
                        if (imageItem != null) {
                        }
                        this.f7501e.m17039q(str, imageItem, null, null, null, "0");
                    }
                } catch (JSONException e11) {
                    imageItem2 = imageItem3;
                    e9 = e11;
                }
                imageItem = imageItem2;
            }
            if (imageItem == null && !TextUtils.isEmpty(imageItem.m16134g())) {
                this.f7501e.m17039q(str, imageItem, null, null, imageItem.m16134g(), "0");
            } else if (imageItem != null || imageItem.m16129b() == null || imageItem.m16129b().isEmpty()) {
                this.f7501e.m17039q(str, imageItem, null, null, null, "0");
            } else {
                this.f7501e.m17039q(str, imageItem, CLUtility.m16497V0(this).f13787l, imageItem.m16129b(), null, imageItem.m16128a());
            }
        }
        Iterator<AudioItem> it = list2.iterator();
        while (it.hasNext()) {
            this.f7501e.m17037o(str, it.next());
        }
        Iterator<VideoItem> it2 = list3.iterator();
        while (it2.hasNext()) {
            this.f7501e.m17041s(str, it2.next());
        }
        if (!this.f7507k.isShowing()) {
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.b1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f9735b.m7883u0();
                }
            });
        }
        this.f7501e.m17044v();
        this.f7501e.m17035F();
    }

    /* renamed from: e0 */
    public final void m7890e0() {
        this.f7517u = (ScrollView) findViewById(R.id.scrollViewCreatePoll);
        this.f7508l = (LinearLayout) findViewById(R.id.linearLayoutCreatePoll);
        this.f7510n = (ImageView) findViewById(R.id.imageViewCheckAllowMultipleChoice);
        this.f7511o = (TextView) findViewById(R.id.textAllowMultipleChoice);
        this.f7512p = (ImageView) findViewById(R.id.imageViewCheckSecretBallot);
        this.f7513q = (TextView) findViewById(R.id.textSecretBallot);
        this.f7514r = (ImageView) findViewById(R.id.imageViewCheckShowAfterAccomplish);
        this.f7515s = (TextView) findViewById(R.id.textShowAfterAccomplish);
        this.f7516t = (Button) findViewById(R.id.post);
        this.f7507k = new DialogC6459g(this, R.style.FriendSelectorDialog);
        this.f7520x = (RelativeLayout) findViewById(R.id.advancePollSetting);
        this.f7521y = (LinearLayout) findViewById(R.id.advancePollOptionLayout);
        this.f7522z = (RelativeLayout) findViewById(R.id.prohibitModifyVotesArea);
        this.f7480A = (ImageView) findViewById(R.id.imageViewProhibitModifyVotes);
        this.f7481B = (RelativeLayout) findViewById(R.id.limitVoteCountArea);
        this.f7482C = (ImageView) findViewById(R.id.imageViewLimitVoteCount);
        this.f7483D = (RelativeLayout) findViewById(R.id.limitTypeArea);
        this.f7484E = (TextView) findViewById(R.id.limitTypeDesc);
        this.f7485F = (RelativeLayout) findViewById(R.id.voteCountArea);
        this.f7486G = (TextView) findViewById(R.id.voteCountDesc);
    }

    /* renamed from: g0 */
    public final void m7891g0(long j9) {
        int childCount = this.f7508l.getChildCount();
        int i9 = 1;
        for (int i10 = 0; i10 < childCount; i10++) {
            EditText editText = (EditText) this.f7508l.getChildAt(i10).findViewById(R.id.editTextDescription);
            if (editText != null && !editText.getText().toString().isEmpty()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("topicId", String.valueOf(j9)));
                arrayList.add(new C6301o("optionOrder", String.valueOf(i9)));
                arrayList.add(new C6301o("optionDescription", editText.getText().toString()));
                Pair<String, String> pairM15731j = this.f7502f.m15731j("groupbulletin", "createPollOption", arrayList);
                String str = (String) pairM15731j.first;
                String str2 = (String) pairM15731j.second;
                if (str == null) {
                    Log.d(f7479T, "Response is null");
                } else if (str.equals("200")) {
                    C2950b0.m14921t().m15234o(C5172p.m20193o(str2), PollOptionObj.f13040k);
                    i9++;
                } else {
                    Log.d(f7479T, "statusCode=" + str);
                }
            }
        }
    }

    /* renamed from: i0 */
    public final void m7892i0() {
        m7893j0(new JSONArray().toString());
    }

    /* renamed from: j0 */
    public final void m7893j0(String str) {
        if (this.f7519w) {
            return;
        }
        this.f7519w = true;
        AsyncTaskC1440c asyncTaskC1440c = new AsyncTaskC1440c(str);
        this.f7518v = asyncTaskC1440c;
        asyncTaskC1440c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: k0 */
    public final void m7894k0(ArrayList<StickerItem> arrayList) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<StickerItem> it = arrayList.iterator();
        while (it.hasNext()) {
            StickerItem next = it.next();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", "Sticker");
                jSONObject.put("value", next.m16285j());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        m7893j0(jSONArray.toString());
    }

    /* renamed from: l0 */
    public final View m7895l0(int i9, boolean z8) {
        View viewInflate = getLayoutInflater().inflate(R.layout.createpollslist_item, (ViewGroup) this.f7508l, false);
        this.f7509m.add(viewInflate);
        EditText editText = (EditText) viewInflate.findViewById(R.id.editTextDescription);
        final TextView textView = (TextView) viewInflate.findViewById(R.id.textViewNumber);
        final ImageView imageView = (ImageView) viewInflate.findViewById(R.id.imageViewRightIcon);
        viewInflate.setTag(editText);
        textView.setText(String.valueOf(i9 + 1));
        imageView.setTag(Integer.valueOf(i9));
        imageView.setOnClickListener(this.f7488I);
        editText.addTextChangedListener(new C1439b(imageView, textView));
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.cyberlink.you.activity.a1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z9) {
                this.f9701b.m7879r0(imageView, textView, view, z9);
            }
        });
        if (z8) {
            editText.requestFocus();
            CLUtility.m16606x2(this);
        }
        return viewInflate;
    }

    /* renamed from: m0 */
    public final String m7896m0() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("poll.multiple.choose", this.f7510n.isSelected());
            if (Globals.m7388i0().m7616t1("POLL_ADVANCE_FEATURE")) {
                jSONObject.put("poll.is.secret.ballot", this.f7512p.isSelected());
                jSONObject.put("poll.show.after.accomplish", this.f7514r.isSelected());
                jSONObject.put("poll.prohibit.vote", this.f7480A.isSelected());
                jSONObject.put("poll.limit.votes.enabled", this.f7482C.isSelected());
                if (this.f7482C.isSelected()) {
                    if (getString(R.string.limit_type_maximum).contentEquals(this.f7484E.getText())) {
                        jSONObject.put("poll.limit.votes.criteria", "le");
                    } else if (getString(R.string.limit_type_minimum).contentEquals(this.f7484E.getText())) {
                        jSONObject.put("poll.limit.votes.criteria", "ge");
                    } else if (getString(R.string.limit_type_equal).contentEquals(this.f7484E.getText())) {
                        jSONObject.put("poll.limit.votes.criteria", "eq");
                    }
                    jSONObject.put("poll.limit.votes.criteria.num", Integer.parseInt(this.f7486G.getText().toString()));
                }
            }
            jSONObject.put("poll.feature.version", 1);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* renamed from: n0 */
    public final void m7897n0() {
        Bundle extras = getIntent().getExtras();
        this.f7499c = (Group) extras.get("Group");
        this.f7500d = extras.getString("INTENT_DESCRIPTION");
        this.f7503g = (ArrayList) extras.getSerializable("INTENT_IMAGE_ITEMS");
        this.f7504h = (ArrayList) extras.getSerializable("INTENT_AUDIO_ITEMS");
        this.f7505i = (ArrayList) extras.getSerializable("INTENT_VIDEO_ITEMS");
        this.f7506j = extras.getParcelableArrayList("INTENT_STICKER_ITEMS");
    }

    /* renamed from: o0 */
    public final int m7898o0() {
        int childCount = this.f7508l.getChildCount();
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            EditText editText = (EditText) this.f7508l.getChildAt(i10).getTag();
            if (editText != null && !editText.getText().toString().trim().isEmpty()) {
                i9++;
            }
        }
        return i9;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m7900w0();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws JSONException {
        int id = view.getId();
        if (id == R.id.back) {
            m7900w0();
        } else {
            if (id != R.id.post) {
                return;
            }
            m7901x0();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_create_poll);
        m7897n0();
        m7890e0();
        m7899p0();
        this.f7502f = new FriendsClient();
        CLUtility.m16606x2(this);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AsyncTask<Void, Void, TopicObj> asyncTask = this.f7518v;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        DialogC6459g dialogC6459g = this.f7507k;
        if (dialogC6459g == null || !dialogC6459g.isShowing()) {
            return;
        }
        this.f7507k.dismiss();
    }

    /* renamed from: p0 */
    public final void m7899p0() {
        findViewById(R.id.back).setOnClickListener(this);
        this.f7516t.setOnClickListener(this);
        this.f7507k.m24770m(this.f7489J);
        View view = null;
        for (int i9 = 0; i9 < 3; i9++) {
            View viewM7895l0 = m7895l0(i9, false);
            if (view == null) {
                view = viewM7895l0;
            }
            this.f7508l.addView(viewM7895l0);
        }
        view.requestFocus();
        this.f7510n.setOnClickListener(this.f7490K);
        this.f7511o.setOnClickListener(this.f7490K);
        this.f7512p.setOnClickListener(this.f7491L);
        this.f7513q.setOnClickListener(this.f7491L);
        this.f7514r.setOnClickListener(this.f7492M);
        this.f7515s.setOnClickListener(this.f7492M);
        this.f7520x.setOnClickListener(this.f7493N);
        if (Globals.m7388i0().m7616t1("POLL_ADVANCE_FEATURE")) {
            this.f7520x.setVisibility(0);
        } else {
            this.f7520x.setVisibility(8);
        }
        this.f7522z.setOnClickListener(this.f7494O);
        this.f7481B.setOnClickListener(this.f7495P);
        this.f7483D.setOnClickListener(this.f7496Q);
        this.f7485F.setOnClickListener(this.f7497R);
    }

    /* renamed from: w0 */
    public final void m7900w0() {
        if (this.f7521y.getVisibility() != 0) {
            finish();
            return;
        }
        this.f7521y.setVisibility(8);
        this.f7517u.setVisibility(0);
        this.f7516t.setVisibility(0);
    }

    /* renamed from: x0 */
    public final void m7901x0() throws JSONException {
        ArrayList<AudioItem> arrayList;
        ArrayList<VideoItem> arrayList2;
        ArrayList<ImageItem> arrayList3 = this.f7503g;
        if ((arrayList3 != null && !arrayList3.isEmpty()) || (((arrayList = this.f7504h) != null && !arrayList.isEmpty()) || ((arrayList2 = this.f7505i) != null && !arrayList2.isEmpty()))) {
            m7889D0(this.f7499c.f13721h, this.f7503g, this.f7504h, this.f7505i);
            return;
        }
        ArrayList<StickerItem> arrayList4 = this.f7506j;
        if (arrayList4 == null || arrayList4.isEmpty()) {
            m7892i0();
        } else {
            m7894k0(this.f7506j);
        }
    }

    /* renamed from: y0 */
    public final void m7902y0(int i9) {
        int childCount = this.f7508l.getChildCount();
        while (i9 < childCount) {
            View childAt = this.f7508l.getChildAt(i9);
            EditText editText = (EditText) childAt.findViewById(R.id.editTextDescription);
            TextView textView = (TextView) childAt.findViewById(R.id.textViewNumber);
            ImageView imageView = (ImageView) childAt.findViewById(R.id.imageViewRightIcon);
            imageView.setTag(Integer.valueOf(i9));
            if (editText != null) {
                textView.setText(String.valueOf(i9 + 1));
                if (!editText.getText().toString().isEmpty()) {
                    imageView.setVisibility(0);
                }
            }
            i9++;
        }
    }

    /* renamed from: z0 */
    public final void m7903z0() {
        this.f7516t.setEnabled(m7898o0() >= 2);
    }
}
