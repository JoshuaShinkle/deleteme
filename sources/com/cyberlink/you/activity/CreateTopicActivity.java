package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.VoiceRecordFragment;
import com.cyberlink.you.bulletin.AudioItem;
import com.cyberlink.you.bulletin.ExtraMediaAdapter;
import com.cyberlink.you.bulletin.StickerItem;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.PhotoImportActivity;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.widgetpool.clhorizontalgridview.HorizontalGridView;
import com.cyberlink.you.widgetpool.common.EmojiconEditTextWithLengthFilter;
import com.google.android.gms.plus.PlusShare;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p025c4.C0726d;
import p056e4.C4755b;
import p086h4.C5002f;
import p086h4.C5015s;
import p115k3.C5123b;
import p116k4.C5172p;
import p116k4.C5178r;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p201t3.C6301o;
import p209u2.C6385v;
import p218v2.DialogC6459g;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class CreateTopicActivity extends BaseFragmentActivity implements View.OnClickListener {

    /* renamed from: E */
    public static final String f7540E = "CreateTopicActivity";

    /* renamed from: C */
    public boolean f7543C;

    /* renamed from: D */
    public boolean f7544D;

    /* renamed from: c */
    public VoiceRecordFragment f7545c;

    /* renamed from: d */
    public C5015s f7546d;

    /* renamed from: e */
    public ExtraMediaAdapter f7547e;

    /* renamed from: f */
    public HorizontalGridView f7548f;

    /* renamed from: g */
    public EmojiconEditTextWithLengthFilter f7549g;

    /* renamed from: h */
    public TextView f7550h;

    /* renamed from: i */
    public View f7551i;

    /* renamed from: j */
    public View f7552j;

    /* renamed from: k */
    public View f7553k;

    /* renamed from: l */
    public View f7554l;

    /* renamed from: m */
    public View f7555m;

    /* renamed from: n */
    public DialogC6459g f7556n;

    /* renamed from: o */
    public Button f7557o;

    /* renamed from: p */
    public EditText f7558p;

    /* renamed from: q */
    public C3199c f7559q;

    /* renamed from: s */
    public Group f7561s;

    /* renamed from: u */
    public AsyncTask<Void, Void, TopicObj> f7563u;

    /* renamed from: v */
    public AsyncTask<Void, Void, Void> f7564v;

    /* renamed from: r */
    public FriendsClient f7560r = null;

    /* renamed from: t */
    public int f7562t = 1;

    /* renamed from: w */
    public VoiceRecordFragment.InterfaceC1861g f7565w = new C1452b();

    /* renamed from: x */
    public C5015s.g f7566x = new C1453c();

    /* renamed from: y */
    public DialogInterface.OnClickListener f7567y = new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.k1
        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i9) {
            this.f10807b.m7945v1(dialogInterface, i9);
        }
    };

    /* renamed from: z */
    public C3199c.b f7568z = new C1454d();

    /* renamed from: A */
    public DialogC6459g.a f7541A = new C1455e();

    /* renamed from: B */
    public final TextWatcher f7542B = new C1456f();

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$a */
    public static /* synthetic */ class C1451a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f7569a;

        static {
            int[] iArr = new int[ExtraMediaAdapter.ItemType.values().length];
            f7569a = iArr;
            try {
                iArr[ExtraMediaAdapter.ItemType.TYPE_STICKER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7569a[ExtraMediaAdapter.ItemType.TYPE_PHOTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7569a[ExtraMediaAdapter.ItemType.TYPE_VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7569a[ExtraMediaAdapter.ItemType.TYPE_AUDIO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$b */
    public class C1452b implements VoiceRecordFragment.InterfaceC1861g {
        public C1452b() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: a */
        public void mo7976a() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: b */
        public void mo7977b(boolean z8) {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: c */
        public void mo7978c(String str, String str2) {
            CreateTopicActivity.this.m7974y1(new AudioItem(str, str2));
            CreateTopicActivity.this.m7955H1(ExtraMediaAdapter.ItemType.TYPE_AUDIO, true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$c */
    public class C1453c implements C5015s.g {
        public C1453c() {
        }

        @Override // p086h4.C5015s.g
        /* renamed from: a */
        public void mo7979a(StickerObj stickerObj) {
            if (stickerObj != null) {
                CreateTopicActivity.this.f7547e.add(new StickerItem(stickerObj));
                new C5002f(CreateTopicActivity.this).m19426a(stickerObj);
                CreateTopicActivity.this.m7959L1();
                CreateTopicActivity.this.m7962h1();
                CreateTopicActivity.this.m7955H1(ExtraMediaAdapter.ItemType.TYPE_STICKER, true);
            }
        }

        @Override // p086h4.C5015s.g
        /* renamed from: b */
        public void mo7980b(Emojicon emojicon) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$d */
    public class C1454d implements C3199c.b {
        public C1454d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m7985h(int i9, int i10, UploadMediaHelper uploadMediaHelper) {
            if (CreateTopicActivity.this.f7556n != null) {
                CreateTopicActivity.this.f7556n.m24771n(Integer.toString(i9 + 1), Integer.toString(i10));
                if (uploadMediaHelper.m16842f1() != null) {
                    CreateTopicActivity.this.f7556n.m24764f(uploadMediaHelper.m16842f1());
                } else if (uploadMediaHelper.m16844g1() != null) {
                    CreateTopicActivity.this.f7556n.m24765g(uploadMediaHelper.m16844g1().m16216e());
                } else if (uploadMediaHelper.m16836c1() != null) {
                    CreateTopicActivity.this.f7556n.m24761c(R.drawable.icon_voice_default_upload);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m7986i() {
            if (CreateTopicActivity.this.f7556n != null) {
                CreateTopicActivity.this.f7556n.m24760b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j */
        public /* synthetic */ void m7987j() {
            if (CreateTopicActivity.this.f7556n == null || !CreateTopicActivity.this.f7556n.isShowing()) {
                return;
            }
            CreateTopicActivity.this.f7556n.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void m7988k(String str) {
            CreateTopicActivity createTopicActivity = CreateTopicActivity.this;
            createTopicActivity.m7964j1(str, createTopicActivity.f7549g.getText().toString(), CreateTopicActivity.this.f7558p.getText().toString());
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: a */
        public void mo7916a(final int i9, final int i10, final UploadMediaHelper uploadMediaHelper) {
            C3199c.m17015D(true);
            CreateTopicActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.v1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11801b.m7985h(i9, i10, uploadMediaHelper);
                }
            });
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: b */
        public void mo7917b(C3199c c3199c) throws JSONException {
            if (!CreateTopicActivity.this.isFinishing()) {
                CreateTopicActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.s1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11160b.m7987j();
                    }
                });
            }
            final String strM19984b = C5123b.m19984b(c3199c.m17042t());
            CreateTopicActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.t1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11386b.m7988k(strM19984b);
                }
            });
            c3199c.m17034E();
            C3199c.m17015D(false);
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        public void onCancel() {
            CreateTopicActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.u1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11420b.m7986i();
                }
            });
            C3199c.m17015D(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$e */
    public class C1455e implements DialogC6459g.a {
        public C1455e() {
        }

        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public void mo7918a() {
            if (CreateTopicActivity.this.f7559q != null) {
                CreateTopicActivity.this.f7559q.m17036d();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$f */
    public class C1456f implements TextWatcher {
        public C1456f() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            CreateTopicActivity.this.m7959L1();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$g */
    public class AsyncTaskC1457g extends AsyncTask<Void, Void, TopicObj> {

        /* renamed from: a */
        public ProgressDialog f7575a;

        /* renamed from: b */
        public final /* synthetic */ String f7576b;

        /* renamed from: c */
        public final /* synthetic */ String f7577c;

        /* renamed from: d */
        public final /* synthetic */ String f7578d;

        public AsyncTaskC1457g(String str, String str2, String str3) {
            this.f7576b = str;
            this.f7577c = str2;
            this.f7578d = str3;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TopicObj doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("groupId", String.valueOf(CreateTopicActivity.this.f7561s.f13727n)));
            arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.f7576b));
            arrayList.add(new C6301o("components", this.f7577c));
            arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.f7578d));
            arrayList.add(new C6301o("topicType", "Topic"));
            Pair<String, String> pairM15731j = CreateTopicActivity.this.f7560r.m15731j("groupbulletin", "createTopic", arrayList);
            String str = (String) pairM15731j.first;
            String str2 = (String) pairM15731j.second;
            if (str == null) {
                Log.d(CreateTopicActivity.f7540E, "Response is null");
                return null;
            }
            if (str.equals("200")) {
                TopicObj topicObjM20204z = C5172p.m20204z(C5172p.m20195q(str2), CreateTopicActivity.this.f7561s.f13727n);
                C2950b0.m14906e().m14978h(topicObjM20204z, TopicObj.m14828m());
                C6566a.m25162u("Usage_Data_Discuss");
                return topicObjM20204z;
            }
            Log.d(CreateTopicActivity.f7540E, "statusCode=" + str);
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(TopicObj topicObj) {
            ProgressDialog progressDialog = this.f7575a;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.f7575a.dismiss();
            }
            CreateTopicActivity.this.f7543C = false;
            if (topicObj != null) {
                Intent intent = new Intent();
                intent.putExtra("intent_topic", topicObj);
                CreateTopicActivity.this.setResult(-1, intent);
                CLUtility.m16589t1(CreateTopicActivity.this);
                CreateTopicActivity.this.finish();
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            CreateTopicActivity createTopicActivity = CreateTopicActivity.this;
            this.f7575a = ProgressDialog.show(createTopicActivity, "", createTopicActivity.getString(R.string.processing), true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$h */
    public class C1458h implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Runnable f7580a;

        /* renamed from: b */
        public final /* synthetic */ Permission f7581b;

        public C1458h(Runnable runnable, Permission permission) {
            this.f7580a = runnable;
            this.f7581b = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(CreateTopicActivity.this, this.f7581b);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            this.f7580a.run();
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$i */
    public class C1459i implements InterfaceC5288c {
        public C1459i() {
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(CreateTopicActivity.this, Permission.MICROPHONE);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            CreateTopicActivity.this.m7958K1(true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$j */
    public class AsyncTaskC1460j extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ String f7584a;

        /* renamed from: b */
        public final /* synthetic */ ProgressDialog f7585b;

        /* renamed from: com.cyberlink.you.activity.CreateTopicActivity$j$a */
        public class a extends AsyncTask<Void, Void, VideoItem> {

            /* renamed from: a */
            public final /* synthetic */ Uri f7587a;

            /* renamed from: b */
            public final /* synthetic */ ProgressDialog f7588b;

            public a(Uri uri, ProgressDialog progressDialog) {
                this.f7587a = uri;
                this.f7588b = progressDialog;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public VideoItem doInBackground(Void... voidArr) throws FileNotFoundException {
                C0726d c0726d = new C0726d(CreateTopicActivity.this);
                VideoItem videoItemM3573l = c0726d.m3573l(this.f7587a);
                if (videoItemM3573l != null) {
                    String strM3568g = c0726d.m3568g(videoItemM3573l.m16217f());
                    if (!CLUtility.m16613z1(strM3568g, null)) {
                        strM3568g = CLUtility.m16427D(videoItemM3573l.m16218g(), CLUtility.m16510Z1(videoItemM3573l.m16219h()));
                    }
                    videoItemM3573l.m16224m(strM3568g);
                    Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(strM3568g, null);
                    if (((Integer) pairM16503X0.first).intValue() <= 0 || ((Integer) pairM16503X0.second).intValue() <= 0) {
                        int i9 = ExtraMediaAdapter.f12357r;
                        videoItemM3573l.m16226o(i9);
                        videoItemM3573l.m16222k(i9);
                    } else {
                        videoItemM3573l.m16226o(((Integer) pairM16503X0.first).intValue());
                        videoItemM3573l.m16222k(((Integer) pairM16503X0.second).intValue());
                    }
                    if (TextUtils.isEmpty(videoItemM3573l.m16212a())) {
                        videoItemM3573l.m16221j(CLUtility.m16552k0(videoItemM3573l.m16218g(), CLUtility.m16510Z1(videoItemM3573l.m16219h())));
                    }
                }
                return videoItemM3573l;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(VideoItem videoItem) {
                if (videoItem == null) {
                    Log.e(CreateTopicActivity.f7540E, "It can't get VideoItem by URL.");
                } else {
                    CreateTopicActivity.this.f7547e.add(videoItem);
                    CreateTopicActivity.this.f7547e.notifyDataSetChanged();
                    CreateTopicActivity.this.m7959L1();
                    CreateTopicActivity.this.m7955H1(ExtraMediaAdapter.ItemType.TYPE_VIDEO, true);
                }
                if (this.f7588b.isShowing()) {
                    this.f7588b.dismiss();
                }
                CreateTopicActivity.this.f7544D = false;
            }
        }

        public AsyncTaskC1460j(String str, ProgressDialog progressDialog) {
            this.f7584a = str;
            this.f7585b = progressDialog;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m7992c(ProgressDialog progressDialog, String str, Uri uri) {
            Log.d(CreateTopicActivity.f7540E, "[onScanCompleted]:" + str);
            new a(uri, progressDialog).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            CreateTopicActivity createTopicActivity = CreateTopicActivity.this;
            String[] strArr = {this.f7584a};
            final ProgressDialog progressDialog = this.f7585b;
            MediaScannerConnection.scanFile(createTopicActivity, strArr, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.cyberlink.you.activity.w1
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public final void onScanCompleted(String str, Uri uri) {
                    this.f11834a.m7992c(progressDialog, str, uri);
                }
            });
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t1 */
    public /* synthetic */ void m7943t1(View view) {
        m7968n1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u1 */
    public /* synthetic */ void m7944u1(View view) {
        m7968n1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v1 */
    public /* synthetic */ void m7945v1(DialogInterface dialogInterface, int i9) {
        if (i9 != -1) {
            return;
        }
        setResult(0);
        CLUtility.m16589t1(this);
        finish();
    }

    /* renamed from: w1 */
    public static /* synthetic */ void m7946w1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x1 */
    public /* synthetic */ void m7947x1() {
        CLUtility.m16606x2(this);
    }

    /* renamed from: A1 */
    public final void m7948A1() {
        if (m7973s1(ExtraMediaAdapter.ItemType.TYPE_STICKER)) {
            return;
        }
        m7957J1(!this.f7546d.isVisible());
    }

    /* renamed from: B1 */
    public final void m7949B1() {
        if (m7973s1(ExtraMediaAdapter.ItemType.TYPE_AUDIO)) {
            return;
        }
        m7962h1();
        C5287b.m20583f(Permission.MICROPHONE, new C1459i(), this);
    }

    /* renamed from: C1 */
    public final void m7950C1() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>();
        String string = this.f7549g.getText().toString();
        for (int i9 = 0; i9 < this.f7547e.getCount(); i9++) {
            Object item = this.f7547e.getItem(i9);
            if (item instanceof ImageItem) {
                arrayList.add((ImageItem) item);
            } else if (item instanceof VideoItem) {
                arrayList3.add((VideoItem) item);
            } else if (item instanceof AudioItem) {
                arrayList2.add((AudioItem) item);
            } else if (item instanceof StickerItem) {
                arrayList4.add((StickerItem) item);
            }
        }
        Intent intent = new Intent(this, (Class<?>) CreatePollOptionsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f7561s);
        bundle.putString("INTENT_DESCRIPTION", string);
        bundle.putSerializable("INTENT_IMAGE_ITEMS", arrayList);
        bundle.putSerializable("INTENT_AUDIO_ITEMS", arrayList2);
        bundle.putSerializable("INTENT_VIDEO_ITEMS", arrayList3);
        bundle.putParcelableArrayList("INTENT_STICKER_ITEMS", arrayList4);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* renamed from: D1 */
    public final void m7951D1() throws JSONException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        String string = this.f7549g.getText().toString();
        for (int i9 = 0; i9 < this.f7547e.getCount(); i9++) {
            Object item = this.f7547e.getItem(i9);
            if (item instanceof ImageItem) {
                arrayList.add((ImageItem) item);
            } else if (item instanceof VideoItem) {
                arrayList3.add((VideoItem) item);
            } else if (item instanceof AudioItem) {
                arrayList2.add((AudioItem) item);
            } else if (item instanceof StickerItem) {
                arrayList4.add((StickerItem) item);
            }
        }
        if (!arrayList.isEmpty() || !arrayList2.isEmpty() || !arrayList3.isEmpty()) {
            m7960M1(this.f7561s.f13721h, arrayList, arrayList2, arrayList3);
            return;
        }
        if (arrayList4.isEmpty() || string.isEmpty()) {
            if (string.isEmpty()) {
                return;
            }
            m7963i1();
        } else {
            m7965k1(arrayList4);
        }
    }

    /* renamed from: E1 */
    public final void m7952E1() {
        if (m7973s1(ExtraMediaAdapter.ItemType.TYPE_PHOTO)) {
            return;
        }
        m7962h1();
        try {
            Intent intent = new Intent(this, (Class<?>) PhotoImportActivity.class);
            intent.putExtra("hideVoice4ImportPhoto", true);
            intent.putExtra("hideText4ImportPhoto", true);
            intent.putExtra("isCallFromForumsOrPolls", true);
            startActivityForResult(intent, 0);
        } catch (Exception unused) {
            Log.e(f7540E, "TODO: Handle Exception");
        }
    }

    /* renamed from: F1 */
    public final void m7953F1() {
        if (m7973s1(ExtraMediaAdapter.ItemType.TYPE_VIDEO)) {
            return;
        }
        m7962h1();
        C5178r.m20244m(this, 1);
    }

    /* renamed from: G1 */
    public final void m7954G1(Runnable runnable) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C1458h(runnable, permission), this);
    }

    /* renamed from: H1 */
    public void m7955H1(ExtraMediaAdapter.ItemType itemType, boolean z8) {
        int i9 = C1451a.f7569a[itemType.ordinal()];
        if (i9 == 1) {
            this.f7552j.setSelected(z8);
            this.f7553k.setSelected(false);
            this.f7554l.setSelected(false);
            this.f7555m.setSelected(false);
            return;
        }
        if (i9 == 2) {
            this.f7552j.setSelected(false);
            this.f7553k.setSelected(z8);
            this.f7554l.setSelected(false);
            this.f7555m.setSelected(false);
            return;
        }
        if (i9 == 3) {
            this.f7552j.setSelected(false);
            this.f7553k.setSelected(false);
            this.f7554l.setSelected(z8);
            this.f7555m.setSelected(false);
            return;
        }
        if (i9 != 4) {
            return;
        }
        this.f7552j.setSelected(false);
        this.f7553k.setSelected(false);
        this.f7554l.setSelected(false);
        this.f7555m.setSelected(z8);
    }

    /* renamed from: I1 */
    public final void m7956I1(String str, Uri uri) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.processing));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (this.f7544D) {
            return;
        }
        this.f7544D = true;
        AsyncTaskC1460j asyncTaskC1460j = new AsyncTaskC1460j(str, progressDialog);
        this.f7564v = asyncTaskC1460j;
        asyncTaskC1460j.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: J1 */
    public final void m7957J1(boolean z8) {
        if (z8) {
            getSupportFragmentManager().mo1844a().mo1802r(this.f7546d).mo1794h();
            CLUtility.m16589t1(this);
        } else {
            getSupportFragmentManager().mo1844a().mo1799n(this.f7546d).mo1794h();
        }
        this.f7551i.setVisibility(z8 ? 0 : 4);
        m7955H1(ExtraMediaAdapter.ItemType.TYPE_STICKER, z8);
    }

    /* renamed from: K1 */
    public final void m7958K1(boolean z8) {
        if (!z8) {
            getSupportFragmentManager().mo1844a().mo1799n(this.f7545c).mo1794h();
        } else {
            getSupportFragmentManager().mo1844a().mo1802r(this.f7545c).mo1794h();
            CLUtility.m16589t1(this);
        }
    }

    /* renamed from: L1 */
    public final void m7959L1() {
        boolean z8 = true;
        boolean z9 = this.f7558p.getText().toString().trim().length() > 0;
        ExtraMediaAdapter extraMediaAdapter = this.f7547e;
        boolean z10 = extraMediaAdapter != null && extraMediaAdapter.getCount() > 0;
        boolean z11 = this.f7549g.getText().toString().trim().length() > 0 || (z10 && this.f7562t == 0 && !(z10 && this.f7547e.m14018n(0) == ExtraMediaAdapter.ItemType.TYPE_STICKER));
        this.f7548f.setVisibility(z10 ? 0 : 8);
        int i9 = this.f7562t;
        if (i9 != 0) {
            if (i9 == 1) {
                z8 = z11;
            }
        } else if (!z9 || !z11) {
            z8 = false;
        }
        this.f7557o.setEnabled(z8);
    }

    /* renamed from: M1 */
    public final void m7960M1(String str, List<ImageItem> list, List<AudioItem> list2, List<VideoItem> list3) {
        ImageItem imageItem;
        ImageItem imageItem2;
        JSONException e9;
        if (str == null || str.isEmpty() || list == null || list2 == null || list3 == null) {
            return;
        }
        if (this.f7559q == null) {
            C3199c c3199c = new C3199c();
            this.f7559q = c3199c;
            c3199c.m17033C(this.f7568z);
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
                        Log.d(f7540E, Log.getStackTraceString(e9));
                        imageItem = imageItem2;
                        if (imageItem == null) {
                        }
                        if (imageItem != null) {
                        }
                        this.f7559q.m17039q(str, imageItem, null, null, null, "0");
                    }
                } catch (JSONException e11) {
                    imageItem2 = imageItem3;
                    e9 = e11;
                }
                imageItem = imageItem2;
            }
            if (imageItem == null && imageItem.m16134g() != null && !imageItem.m16134g().isEmpty()) {
                this.f7559q.m17039q(str, imageItem, null, null, imageItem.m16134g(), "0");
            } else if (imageItem != null || imageItem.m16129b() == null || imageItem.m16129b().isEmpty()) {
                this.f7559q.m17039q(str, imageItem, null, null, null, "0");
            } else {
                this.f7559q.m17039q(str, imageItem, CLUtility.m16497V0(this).f13787l, imageItem.m16129b(), null, imageItem.m16128a());
            }
        }
        Iterator<AudioItem> it = list2.iterator();
        while (it.hasNext()) {
            this.f7559q.m17037o(str, it.next());
        }
        Iterator<VideoItem> it2 = list3.iterator();
        while (it2.hasNext()) {
            this.f7559q.m17041s(str, it2.next());
        }
        if (!this.f7556n.isShowing()) {
            final DialogC6459g dialogC6459g = this.f7556n;
            Objects.requireNonNull(dialogC6459g);
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.p1
                @Override // java.lang.Runnable
                public final void run() {
                    dialogC6459g.show();
                }
            });
        }
        this.f7559q.m17044v();
        this.f7559q.m17035F();
    }

    /* renamed from: g1 */
    public final void m7961g1() {
        this.f7550h = (TextView) findViewById(R.id.textViewTitle);
        this.f7553k = findViewById(R.id.btn_add_photo);
        this.f7554l = findViewById(R.id.btn_add_video);
        this.f7555m = findViewById(R.id.btn_add_voice);
        this.f7557o = (Button) findViewById(R.id.post);
        this.f7552j = findViewById(R.id.btn_add_sticker);
        this.f7551i = findViewById(R.id.stickerContainer);
        this.f7558p = (EditText) findViewById(R.id.input_title);
        this.f7549g = (EmojiconEditTextWithLengthFilter) findViewById(R.id.input_content);
        this.f7548f = (HorizontalGridView) findViewById(R.id.extraMediaGridView);
    }

    /* renamed from: h1 */
    public final void m7962h1() {
        if (this.f7546d.isVisible()) {
            m7957J1(false);
        }
    }

    /* renamed from: i1 */
    public final void m7963i1() {
        m7964j1(new JSONArray().toString(), this.f7549g.getText().toString(), this.f7558p.getText().toString());
    }

    /* renamed from: j1 */
    public final void m7964j1(String str, String str2, String str3) {
        if (this.f7543C) {
            return;
        }
        this.f7543C = true;
        AsyncTaskC1457g asyncTaskC1457g = new AsyncTaskC1457g(str2, str, str3);
        this.f7563u = asyncTaskC1457g;
        asyncTaskC1457g.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: k1 */
    public final void m7965k1(List<StickerItem> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (StickerItem stickerItem : list) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", "Sticker");
                jSONObject.put("value", stickerItem.m16285j());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        m7964j1(jSONArray.toString(), this.f7549g.getText().toString(), this.f7558p.getText().toString());
    }

    /* renamed from: l1 */
    public final void m7966l1() {
        this.f7562t = getIntent().getIntExtra("INTENT_MODE", 0);
        this.f7561s = (Group) getIntent().getParcelableExtra("Group");
    }

    /* renamed from: m1 */
    public void m7967m1(ExtraMediaAdapter.ItemType itemType) {
        ExtraMediaAdapter extraMediaAdapter = this.f7547e;
        if (extraMediaAdapter == null || extraMediaAdapter.getCount() == 0) {
            m7955H1(itemType, false);
        }
        m7959L1();
    }

    /* renamed from: n1 */
    public final void m7968n1() {
        if (this.f7546d.isVisible()) {
            m7957J1(false);
        }
        if (this.f7545c.isVisible()) {
            m7958K1(false);
        }
    }

    /* renamed from: o1 */
    public final void m7969o1(boolean z8) {
        if (z8) {
            this.f7546d = new C5015s();
            Bundle bundle = new Bundle();
            bundle.putBoolean("Emoji", false);
            bundle.putBoolean("Shop", false);
            bundle.putInt("Mode", 0);
            this.f7546d.setArguments(bundle);
            getSupportFragmentManager().mo1844a().m1981c(R.id.stickerFragmentContainer, this.f7546d, "StickerFragment").mo1799n(this.f7546d).mo1794h();
        } else {
            this.f7546d = (C5015s) getSupportFragmentManager().mo1848e("StickerFragment");
            m7957J1(false);
        }
        this.f7546d.m19511m0(this.f7566x);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) throws SecurityException, IOException, IllegalArgumentException {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0) {
            if (i10 != -1) {
                ExtraMediaAdapter extraMediaAdapter = this.f7547e;
                if (extraMediaAdapter == null || extraMediaAdapter.getCount() == 0) {
                    m7955H1(ExtraMediaAdapter.ItemType.TYPE_PHOTO, false);
                    return;
                } else {
                    m7955H1(ExtraMediaAdapter.ItemType.TYPE_PHOTO, true);
                    return;
                }
            }
            Iterator it = ((ArrayList) intent.getExtras().getSerializable("import_images")).iterator();
            while (it.hasNext()) {
                ImageItem imageItem = (ImageItem) it.next();
                imageItem.m16124E(imageItem.m16144q());
                imageItem.m16125F(imageItem.m16145r());
                this.f7547e.add(imageItem);
                this.f7547e.notifyDataSetChanged();
                m7959L1();
            }
            m7955H1(ExtraMediaAdapter.ItemType.TYPE_PHOTO, true);
            return;
        }
        if (i9 != 1) {
            return;
        }
        if (i10 != -1) {
            m7955H1(ExtraMediaAdapter.ItemType.TYPE_VIDEO, false);
            return;
        }
        Uri data = intent.getData();
        String strM16576q0 = CLUtility.m16576q0(this, data);
        long jM16440G0 = CLUtility.m16440G0(strM16576q0, data);
        if (jM16440G0 <= 600000) {
            if (jM16440G0 < 1000) {
                C5187v0.m20267c(R.string.videos_too_short);
                return;
            } else {
                m7956I1(strM16576q0, data);
                return;
            }
        }
        String str = String.format(getResources().getString(R.string.select_videos_too_long), String.valueOf(10L));
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(str);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.m1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i11) {
                CreateTopicActivity.m7946w1(dialogInterface, i11);
            }
        });
        builderM16382a.create().show();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.f7545c.isVisible()) {
            m7958K1(false);
            this.f7545c.m11001V();
        } else if (this.f7546d.isVisible()) {
            m7957J1(false);
        } else {
            m7975z1();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws JSONException {
        switch (view.getId()) {
            case R.id.back /* 2131297178 */:
                m7975z1();
                break;
            case R.id.btn_add_photo /* 2131297259 */:
                m7954G1(new Runnable() { // from class: com.cyberlink.you.activity.n1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10912b.m7952E1();
                    }
                });
                break;
            case R.id.btn_add_sticker /* 2131297260 */:
                m7948A1();
                break;
            case R.id.btn_add_video /* 2131297262 */:
                m7954G1(new Runnable() { // from class: com.cyberlink.you.activity.o1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10994b.m7953F1();
                    }
                });
                break;
            case R.id.btn_add_voice /* 2131297263 */:
                m7949B1();
                break;
            case R.id.post /* 2131298351 */:
                if (this.f7562t != 1) {
                    m7951D1();
                    break;
                } else {
                    m7950C1();
                    break;
                }
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Resources.NotFoundException {
        super.onCreate(bundle);
        m7966l1();
        setContentView(R.layout.activity_create_bulletin);
        m7961g1();
        m7970p1(bundle);
        this.f7560r = new FriendsClient(true);
        m7366I0().postDelayed(new Runnable() { // from class: com.cyberlink.you.activity.l1
            @Override // java.lang.Runnable
            public final void run() {
                this.f10839b.m7947x1();
            }
        }, 100L);
        CLUtility.m16522c2(null);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        FriendsClient friendsClient = this.f7560r;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        DialogC6459g dialogC6459g = this.f7556n;
        if (dialogC6459g != null && dialogC6459g.isShowing()) {
            this.f7556n.dismiss();
        }
        AsyncTask<Void, Void, TopicObj> asyncTask = this.f7563u;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        AsyncTask<Void, Void, Void> asyncTask2 = this.f7564v;
        if (asyncTask2 != null) {
            asyncTask2.cancel(false);
        }
        super.onDestroy();
    }

    /* renamed from: p1 */
    public final void m7970p1(Bundle bundle) throws Resources.NotFoundException {
        this.f7553k.setOnClickListener(this);
        this.f7554l.setOnClickListener(this);
        this.f7555m.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        if (this.f7562t == 1) {
            this.f7549g.setMaxLength(getResources().getInteger(R.integer.max_input_length_poll_description));
            this.f7549g.setHint(R.string.enter_your_question_to_the_group_here_you_can_set_up_poll_options_in_next_step);
            this.f7549g.requestFocus();
            this.f7550h.setText(R.string.new_poll);
            this.f7558p.setVisibility(8);
            this.f7557o.setText(R.string.registration_phone_registration_next_btn);
        } else {
            this.f7558p.requestFocus();
            this.f7549g.setMaxLength(getResources().getInteger(R.integer.max_input_length_forum_content));
        }
        this.f7557o.setOnClickListener(this);
        this.f7552j.setOnClickListener(this);
        this.f7558p.requestFocus();
        this.f7558p.addTextChangedListener(this.f7542B);
        this.f7558p.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.q1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11068b.m7943t1(view);
            }
        });
        this.f7549g.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.r1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11130b.m7944u1(view);
            }
        });
        this.f7549g.addTextChangedListener(this.f7542B);
        ExtraMediaAdapter extraMediaAdapter = new ExtraMediaAdapter(this, 0, new ArrayList());
        this.f7547e = extraMediaAdapter;
        extraMediaAdapter.m14021r(this);
        this.f7548f.setAdapter((ListAdapter) this.f7547e);
        m7971q1(bundle == null);
        m7969o1(bundle == null);
        DialogC6459g dialogC6459g = new DialogC6459g(this, R.style.FriendSelectorDialog);
        this.f7556n = dialogC6459g;
        dialogC6459g.m24770m(this.f7541A);
    }

    /* renamed from: q1 */
    public final void m7971q1(boolean z8) {
        if (z8) {
            this.f7545c = new VoiceRecordFragment();
            getSupportFragmentManager().mo1844a().m1981c(R.id.voiceRecordFragmentContainer, this.f7545c, "VoiceRecord").mo1799n(this.f7545c).mo1794h();
        } else {
            this.f7545c = (VoiceRecordFragment) getSupportFragmentManager().mo1848e("VoiceRecord");
            m7958K1(false);
        }
        this.f7545c.m11003X(this.f7565w);
    }

    /* renamed from: r1 */
    public final boolean m7972r1() {
        EditText editText = this.f7558p;
        String string = editText == null ? "" : editText.getText().toString();
        String string2 = this.f7549g.getText().toString();
        if (!string.isEmpty() || !string2.isEmpty()) {
            return true;
        }
        ExtraMediaAdapter extraMediaAdapter = this.f7547e;
        return (extraMediaAdapter == null || extraMediaAdapter.getCount() == 0) ? false : true;
    }

    /* renamed from: s1 */
    public final boolean m7973s1(ExtraMediaAdapter.ItemType itemType) {
        ExtraMediaAdapter extraMediaAdapter = this.f7547e;
        if (extraMediaAdapter == null || extraMediaAdapter.getCount() == 0) {
            return false;
        }
        ExtraMediaAdapter.ItemType itemType2 = ExtraMediaAdapter.ItemType.TYPE_PHOTO;
        return (itemType == itemType2 && this.f7547e.m14018n(0) == itemType2) ? false : true;
    }

    /* renamed from: y1 */
    public final void m7974y1(AudioItem audioItem) {
        m7958K1(false);
        this.f7547e.add(audioItem);
        this.f7547e.notifyDataSetChanged();
        m7959L1();
    }

    /* renamed from: z1 */
    public final void m7975z1() {
        if (m7972r1()) {
            DialogInterface.OnClickListener onClickListener = this.f7567y;
            C4755b.m18880c(this, R.string.abandon, R.string.bulletin_topic_ask_abandon_change, R.string.abandon, R.string.cancel, onClickListener, onClickListener);
        } else {
            CLUtility.m16589t1(this);
            finish();
        }
    }
}
