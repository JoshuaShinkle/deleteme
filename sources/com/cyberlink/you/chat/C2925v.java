package com.cyberlink.you.chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.UpgradeToProUserActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.p159io.FilenameUtils;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONException;
import org.json.JSONObject;
import p074g2.C4831a;
import p116k4.C5154j;
import p116k4.C5164m0;
import p116k4.C5170o0;
import p116k4.C5187v0;
import p182r2.C6196d0;
import p182r2.C6201i;
import p209u2.AbstractC6381r;
import p209u2.C6370g;
import p209u2.C6385v;
import p209u2.ThreadFactoryC6373j;
import p218v2.DialogC6459g;

/* renamed from: com.cyberlink.you.chat.v */
/* loaded from: classes.dex */
public class C2925v {

    /* renamed from: a */
    public static ThreadPoolExecutor f12824a = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("ChatUtility.SingleThread"));

    /* renamed from: b */
    public static int f12825b = 0;

    /* renamed from: c */
    public static int f12826c = 0;

    /* renamed from: d */
    public static boolean f12827d = false;

    /* renamed from: e */
    public static DialogC6459g f12828e;

    /* renamed from: f */
    public static boolean f12829f;

    /* renamed from: com.cyberlink.you.chat.v$a */
    public class a implements C3197a.b {

        /* renamed from: a */
        public final /* synthetic */ String f12830a;

        /* renamed from: b */
        public final /* synthetic */ List f12831b;

        /* renamed from: c */
        public final /* synthetic */ Activity f12832c;

        /* renamed from: d */
        public final /* synthetic */ int f12833d;

        /* renamed from: e */
        public final /* synthetic */ C2973l0 f12834e;

        /* renamed from: f */
        public final /* synthetic */ d f12835f;

        public a(String str, List list, Activity activity, int i9, C2973l0 c2973l0, d dVar) {
            this.f12830a = str;
            this.f12831b = list;
            this.f12832c = activity;
            this.f12833d = i9;
            this.f12834e = c2973l0;
            this.f12835f = dVar;
        }

        /* renamed from: h */
        public static /* synthetic */ void m14638h(DialogInterface dialogInterface, int i9) {
        }

        /* renamed from: i */
        public static /* synthetic */ void m14639i(int i9, int i10, int i11, int i12, C2973l0 c2973l0) {
            try {
                if (C2925v.f12828e != null && !C2925v.f12828e.isShowing()) {
                    C2925v.f12828e.show();
                }
                if (C2925v.f12828e != null) {
                    C2925v.f12828e.m24773p(i9, i10, i11);
                    C2925v.f12828e.m24775r(Integer.toString(C2925v.f12825b + C2925v.f12826c + 1), Integer.toString(i12));
                    C2925v.f12828e.m24762d(c2973l0);
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* renamed from: j */
        public static /* synthetic */ void m14640j(int i9, int i10, int i11) {
            if (C2925v.f12828e != null) {
                C2925v.f12828e.m24773p(i9, i10, i11);
            }
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: a */
        public void mo9122a() {
            m14641g(null);
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: b */
        public void mo9123b(String str) throws Throwable {
            String string;
            Log.d("ChatUtility", "download success");
            Uri uriM14624u0 = null;
            try {
                uriM14624u0 = C2925v.m14624u0(str, C2925v.f12829f, this.f12830a);
                string = null;
            } catch (IllegalStateException e9) {
                ULogUtility.m16670f("ChatUtility", "saveMedias exception:" + e9.getMessage());
                string = Globals.m7372O().getString(R.string.error_download_fail);
            }
            if (uriM14624u0 == null) {
                m14641g(string);
                return;
            }
            C2925v.m14601j();
            boolean unused = C2925v.f12827d = false;
            this.f12831b.add(uriM14624u0);
            C2925v.m14627w(this.f12832c, this.f12833d, C2925v.f12825b, C2925v.f12826c, this.f12834e, this.f12831b, this.f12835f);
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: c */
        public void mo9124c(final int i9, final int i10, final int i11) {
            if (!C2925v.f12827d) {
                Activity activity = this.f12832c;
                final int i12 = this.f12833d;
                final C2973l0 c2973l0 = this.f12834e;
                activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.chat.t
                    @Override // java.lang.Runnable
                    public final void run() {
                        C2925v.a.m14639i(i9, i10, i11, i12, c2973l0);
                    }
                });
                boolean unused = C2925v.f12827d = true;
            }
            this.f12832c.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.chat.u
                @Override // java.lang.Runnable
                public final void run() {
                    C2925v.a.m14640j(i9, i10, i11);
                }
            });
            Log.v("ChatUtility", "download progress=" + i9);
        }

        /* renamed from: g */
        public final void m14641g(String str) {
            Log.d("ChatUtility", "download fail");
            C2925v.m14609n();
            if (this.f12833d == C2925v.f12825b + C2925v.f12826c) {
                if (!this.f12832c.isFinishing()) {
                    C2925v.m14631y();
                }
                if (this.f12835f == null) {
                    if (C2925v.f12825b != 0) {
                        String strM15147s = this.f12834e.m15147s();
                        strM15147s.hashCode();
                        switch (strM15147s) {
                            case "File":
                                C5187v0.m20268d(Globals.m7372O().getResources().getQuantityString(R.plurals.download_file_success, C2925v.f12825b, Integer.valueOf(C2925v.f12825b)));
                                break;
                            case "Photo":
                                C5187v0.m20268d(Globals.m7372O().getResources().getQuantityString(R.plurals.download_photo_success, C2925v.f12825b, Integer.valueOf(C2925v.f12825b)));
                                break;
                            case "Video":
                                C5187v0.m20268d(Globals.m7372O().getResources().getQuantityString(R.plurals.download_video_success, C2925v.f12825b, Integer.valueOf(C2925v.f12825b)));
                                break;
                        }
                    } else if (TextUtils.isEmpty(str)) {
                        Context contextM7372O = Globals.m7372O();
                        AlertDialog.Builder builderM16382a = C3123g.m16382a(this.f12832c);
                        builderM16382a.setMessage(contextM7372O.getString(R.string.open_file_no_exist_on_server));
                        builderM16382a.setPositiveButton(contextM7372O.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.chat.s
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i9) {
                                C2925v.a.m14638h(dialogInterface, i9);
                            }
                        });
                        builderM16382a.show();
                    } else {
                        C5187v0.m20268d(str);
                    }
                }
                if (this.f12835f != null) {
                    if (C2925v.f12825b == this.f12833d) {
                        this.f12835f.mo9092b(this.f12831b);
                    } else {
                        this.f12835f.mo9091a();
                    }
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.v$b */
    public class b extends AsyncTask<Void, Void, C2973l0> {

        /* renamed from: a */
        public final /* synthetic */ C2973l0 f12836a;

        /* renamed from: b */
        public final /* synthetic */ Context f12837b;

        /* renamed from: c */
        public final /* synthetic */ C3197a.b f12838c;

        /* renamed from: com.cyberlink.you.chat.v$b$a */
        public class a extends AbstractC6381r<File, Void> {
            public a() {
            }

            /* renamed from: l */
            public static /* synthetic */ void m14645l(boolean z8, C3197a.b bVar, File file) {
                if (z8) {
                    bVar.mo9123b(file.getPath());
                } else {
                    bVar.mo9122a();
                }
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: m, reason: merged with bridge method [inline-methods] */
            public void m24503g(final File file) {
                b.this.f12838c.mo9124c(99, 99, 100);
                final boolean zExists = file.exists();
                final C3197a.b bVar = b.this.f12838c;
                C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.chat.w
                    @Override // java.lang.Runnable
                    public final void run() {
                        C2925v.b.a.m14645l(zExists, bVar, file);
                    }
                });
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: n, reason: merged with bridge method [inline-methods] */
            public void m24504h(Void r12) {
                b.this.f12838c.mo9122a();
            }
        }

        public b(C2973l0 c2973l0, Context context, C3197a.b bVar) {
            this.f12836a = c2973l0;
            this.f12837b = context;
            this.f12838c = bVar;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C2973l0 doInBackground(Void... voidArr) {
            C2973l0 c2973l0 = this.f12836a;
            return TextUtils.isEmpty(c2973l0.m15148t().f13200d) ? (C2973l0) FriendsClient.m15657X(this.f12836a.m15131c(), this.f12836a.m15144p()).first : c2973l0;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C2973l0 c2973l0) {
            MediaLoader.m7151j(this.f12837b, c2973l0, false, new a());
        }
    }

    /* renamed from: com.cyberlink.you.chat.v$c */
    public class c extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ Map f12840a;

        /* renamed from: b */
        public final /* synthetic */ e f12841b;

        /* renamed from: c */
        public final /* synthetic */ List f12842c;

        public c(Map map, e eVar, List list) {
            this.f12840a = map;
            this.f12841b = eVar;
            this.f12842c = list;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Thread.currentThread().setName("insert_messageObj");
            Iterator it = this.f12840a.values().iterator();
            while (it.hasNext()) {
                C2950b0.m14916o().m15157B((MessageObj) it.next());
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r52) {
            e eVar = this.f12841b;
            if (eVar != null) {
                eVar.mo11876b(this.f12842c);
            }
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : this.f12840a.entrySet()) {
                arrayList.add(new C6201i((String) entry.getKey(), (MessageObj) entry.getValue()));
            }
            C6196d0.m23692d().m23699i(arrayList);
            e eVar2 = this.f12841b;
            if (eVar2 != null) {
                eVar2.mo11875a(this.f12842c);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.v$d */
    public interface d {
        /* renamed from: a */
        void mo9091a();

        /* renamed from: b */
        void mo9092b(List<Uri> list);
    }

    /* renamed from: com.cyberlink.you.chat.v$e */
    public interface e {
        /* renamed from: a */
        void mo11875a(List<MessageObj> list);

        /* renamed from: b */
        void mo11876b(List<MessageObj> list);
    }

    /* renamed from: A */
    public static String m14542A() {
        return "A" + C5616j.m22349n(20);
    }

    /* renamed from: A0 */
    public static void m14543A0(Group group, String str, String str2, String str3) {
        m14559I0(group, m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.VideoDelete, m14554G(str, str2, str3), 0, null));
    }

    /* renamed from: B */
    public static String m14544B(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        HashMap map = new HashMap();
        map.put("type", str);
        map.put("announcementId", str2);
        map.put("template", str3);
        map.put("url", str4);
        map.put("titleOfUrl", str5);
        map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str6);
        if (str7 != null) {
            try {
                map.put("image", new JSONObject(str7));
            } catch (Exception e9) {
                Log.d("ChatUtility", "[generateAnnouncement] Put image exception = " + e9.getMessage());
            }
        }
        if (str8 != null) {
            try {
                map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, new JSONObject(str8));
            } catch (Exception e10) {
                Log.d("ChatUtility", "[generateAnnouncement] Put description exception = " + e10.getMessage());
            }
        }
        if (str9 != null) {
            map.put("shareToFB", Boolean.valueOf(Boolean.valueOf(str9).booleanValue()));
        }
        return new JSONObject(map).toString();
    }

    /* renamed from: B0 */
    public static List<MessageObj> m14545B0(MessageObj.MessageType messageType, String str, int i9, Date date, List<Group> list, e eVar, boolean z8) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Group group : list) {
            MessageObj messageObjM14562K = m14562K(String.valueOf(group.f13727n), messageType, str, i9, date, z8);
            messageObjM14562K.m14764Z(new Date());
            map.put(group.f13723j, messageObjM14562K);
            arrayList.add(messageObjM14562K);
        }
        new c(map, eVar, arrayList).executeOnExecutor(f12824a, new Void[0]);
        return arrayList;
    }

    /* renamed from: C */
    public static String m14546C(String str) {
        HashMap map = new HashMap();
        map.put(FirebaseAnalytics.Param.CONTENT, str);
        return new JSONObject(map).toString();
    }

    /* renamed from: C0 */
    public static void m14547C0(AbstractC5594b abstractC5594b, XMPPManager.InterfaceC2873x interfaceC2873x) {
        XMPPManager.m14184g0().m14241e1(abstractC5594b, interfaceC2873x);
    }

    /* renamed from: D */
    public static String m14548D(String str, String str2, String str3) {
        HashMap map = new HashMap();
        map.put("src", str);
        map.put("width", str2);
        map.put("height", str3);
        return new JSONObject(map).toString();
    }

    /* renamed from: D0 */
    public static List<MessageObj> m14549D0(ImageItem imageItem, int i9, Date date, List<Group> list, boolean z8, boolean z9) {
        ImageItem imageItem2;
        UploadUtils.m16965l("Upload Performance", "0.1 sendPhoto(ImageItem); ==== start");
        String strM16137j = imageItem.m16137j();
        Uri uriM16510Z1 = CLUtility.m16510Z1(imageItem.m16138k());
        ArrayList arrayList = new ArrayList();
        if (CLUtility.m16613z1(strM16137j, uriM16510Z1)) {
            if (TextUtils.isEmpty(imageItem.m16139l()) && TextUtils.isEmpty(imageItem.m16140m())) {
                imageItem2 = imageItem;
            } else {
                try {
                    imageItem2 = new ImageItem(new JSONObject(imageItem.m16127H()));
                } catch (JSONException e9) {
                    Log.d("ChatUtility", Log.getStackTraceString(e9));
                    imageItem2 = imageItem;
                }
                imageItem2.m16122C(CLUtility.m16540h0());
            }
            String strValueOf = String.valueOf(imageItem2.m16142o());
            UploadUtils.m16965l("Upload Performance", "0.1 generateShareMediaContent();");
            String strM14568N = m14568N(strM16137j, uriM16510Z1, strValueOf, "-1");
            Iterator<Group> it = list.iterator();
            while (it.hasNext()) {
                Group next = it.next();
                UploadUtils.m16965l("Upload Performance", "0.1 generateMessage() at group:" + next);
                MessageObj messageObjM14562K = next == null ? m14562K("", MessageObj.MessageType.Photo, strM14568N, i9, date, z8) : m14562K(String.valueOf(next.f13727n), MessageObj.MessageType.Photo, strM14568N, i9, date, z8);
                UploadUtils.m16965l("Upload Performance", "0.1 getMessageDao().insert() at group:" + next);
                C2950b0.m14916o().m15157B(messageObjM14562K);
                arrayList.add(messageObjM14562K);
                UploadUtils.m16965l("Upload Performance", "0.1 sendPhoto(messageObj) at group:" + next);
                m14551E0(messageObjM14562K, next, z9);
            }
            UploadUtils.m16965l("Upload Performance", "0.1 sendPhoto(ImageItem); ==== end");
        } else {
            UploadUtils.m16965l("ChatUtility", "[sendPhoto] image is not exist");
        }
        UploadUtils.m16965l("Upload Performance", "0.1 sendPhoto(ImageItem); ==== end");
        return arrayList;
    }

    /* renamed from: E */
    public static String m14550E(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        HashMap map = new HashMap();
        map.put("albumId", str);
        map.put("mediaId", str2);
        if (str5 == null) {
            str5 = "";
        }
        map.put("commentId", str5);
        str4.hashCode();
        if (str4.equals("CommentText")) {
            map.put("commentType", "Text");
            map.put("comment", str3);
            map.put("duration", "0");
        } else if (str4.equals("Doodle")) {
            map.put("commentType", "Doodle");
            map.put("comment", str3);
            map.put("thumbnail", str7);
            map.put(TtmlNode.ATTR_TTS_COLOR, str8);
        } else {
            map.put("commentType", "Audio");
            map.put("comment", str6);
            map.put("duration", str3);
        }
        return new JSONObject(map).toString();
    }

    /* renamed from: E0 */
    public static void m14551E0(MessageObj messageObj, Group group, boolean z8) {
        UploadUtils.m16965l("Upload Performance", "0.2 sendPhoto(MessageObj); ==== start");
        if (messageObj == null) {
            return;
        }
        messageObj.f12924A = false;
        messageObj.f12959z = 0;
        String strM14747I = messageObj.m14747I("imageItem");
        String strM14747I2 = messageObj.m14747I("imageItemUri");
        Uri uriM16510Z1 = CLUtility.m16510Z1(strM14747I2);
        String strM14747I3 = messageObj.m14747I("imageId");
        if (strM14747I == null && strM14747I2 == null) {
            UploadUtils.m16965l("ChatUtility", "[sendPhoto] Can't get image path");
            UploadUtils.m16965l("ChatUtility", "[sendPhoto] Can't get image uri");
            return;
        }
        if (!CLUtility.m16613z1(strM14747I, uriM16510Z1)) {
            UploadUtils.m16965l("ChatUtility", "[sendPhoto] Image file is not exist.");
            return;
        }
        ImageItem imageItem = new ImageItem("", Long.parseLong(strM14747I3), "", strM14747I, strM14747I2, CLUtility.m16552k0(strM14747I, uriM16510Z1), 0, -1, "");
        if (group != null) {
            boolean z9 = group.f13711J;
            String str = group.f13718e;
            if (messageObj.m14752N() || messageObj.m14753O()) {
                str = group.f13720g;
            }
            UploadUtils.m16965l("Upload Performance", "0.2 sendPhoto(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
            UploadMultipleChatMediaHelperQueue.m16892F().m16943v(z9, str, imageItem, messageObj, group.f13723j, z8);
        } else {
            UploadUtils.m16965l("Upload Performance", "0.2 sendPhoto(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
            UploadMultipleChatMediaHelperQueue.m16892F().m16943v(false, null, imageItem, messageObj, null, z8);
        }
        UploadUtils.m16965l("Upload Performance", "0.2 sendPhoto(MessageObj); ==== end");
    }

    /* renamed from: F */
    public static String m14552F(C2973l0 c2973l0, String str) {
        GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(c2973l0.m15131c());
        String strM14676c = groupAlbumObjM15056i != null ? groupAlbumObjM15056i.m14676c() : "";
        HashMap map = new HashMap();
        map.put("albumId", c2973l0.m15131c());
        map.put("albumName", strM14676c);
        map.put("mediaId", String.valueOf(c2973l0.m15144p()));
        map.put("mediaType", c2973l0.m15147s());
        map.put("width", String.valueOf(c2973l0.m15151w()));
        map.put("height", String.valueOf(c2973l0.m15141m()));
        map.put("numberUpload", str);
        map.put(FirebaseAnalytics.Param.CONTENT, c2973l0.m15149u().f13200d);
        return new JSONObject(map).toString();
    }

    /* renamed from: F0 */
    public static List<MessageObj> m14553F0(ImageItem imageItem, int i9, Date date, List<Group> list, boolean z8) {
        ImageItem imageItem2;
        UploadUtils.m16965l("Upload Performance", "0.1 sendPhotoWithNotes(ImageItem); ==== start");
        String strM16137j = imageItem.m16137j();
        Uri uriM16510Z1 = CLUtility.m16510Z1(imageItem.m16138k());
        ArrayList arrayList = new ArrayList();
        if (CLUtility.m16613z1(strM16137j, uriM16510Z1)) {
            if (TextUtils.isEmpty(imageItem.m16139l()) && TextUtils.isEmpty(imageItem.m16140m())) {
                imageItem2 = imageItem;
            } else {
                try {
                    imageItem2 = new ImageItem(new JSONObject(imageItem.m16127H()));
                } catch (JSONException e9) {
                    Log.d("ChatUtility", Log.getStackTraceString(e9));
                    imageItem2 = imageItem;
                }
                imageItem2.m16122C(CLUtility.m16540h0());
            }
            String strValueOf = String.valueOf(imageItem2.m16142o());
            UploadUtils.m16965l("Upload Performance", "0.1. generateShareMediaWithNotesContent4Preview();");
            String strM14576T = m14576T(strM16137j, uriM16510Z1, strValueOf, imageItem2.m16129b(), imageItem2.m16134g(), imageItem2.m16128a());
            UploadUtils.m16965l("Upload Performance", "0.1. generateMessage();");
            for (Group group : list) {
                MessageObj messageObjM14562K = m14562K(String.valueOf(group.f13727n), MessageObj.MessageType.PhotoNote, strM14576T, i9, date, z8);
                UploadUtils.m16965l("Upload Performance", "0.1. getMessageDao().insert() at group:" + group.f13723j);
                C2950b0.m14916o().m15157B(messageObjM14562K);
                arrayList.add(messageObjM14562K);
                UploadUtils.m16965l("Upload Performance", "0.1 sendPhotoWithNotes(messageObj) at group:" + group.f13723j);
                m14555G0(messageObjM14562K, group);
            }
        }
        UploadUtils.m16965l("Upload Performance", "0.1 sendPhotoWithNotes(ImageItem); ==== end");
        return arrayList;
    }

    /* renamed from: G */
    public static String m14554G(String str, String str2, String str3) {
        HashMap map = new HashMap();
        map.put("albumId", str);
        map.put("albumName", str2);
        map.put("numberDelete", str3);
        return new JSONObject(map).toString();
    }

    /* renamed from: G0 */
    public static void m14555G0(MessageObj messageObj, Group group) {
        UploadUtils.m16965l("Upload Performance", "0.2 sendPhotoWithNotes(MessageObj); ==== start");
        if (messageObj == null) {
            return;
        }
        messageObj.f12924A = false;
        messageObj.f12959z = 0;
        String strM14747I = messageObj.m14747I("imageItem");
        String strM14747I2 = messageObj.m14747I("imageItemUri");
        Uri uriM16510Z1 = CLUtility.m16510Z1(strM14747I2);
        if (CLUtility.m16613z1(strM14747I, uriM16510Z1)) {
            String strM16552k0 = CLUtility.m16552k0(strM14747I, uriM16510Z1);
            long j9 = Long.parseLong(messageObj.m14747I("imageId"));
            String strM14747I3 = messageObj.m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
            String strM14747I4 = messageObj.m14747I("mediaNotes");
            ImageItem imageItem = new ImageItem("", j9, "", strM14747I, strM14747I2, strM16552k0, 0, -1, "", strM14747I3, strM14747I4, messageObj.m14747I("noteMediaDescription"), "", null, "", false);
            boolean z8 = group.f13711J;
            String str = group.f13718e;
            if (messageObj.m14752N() || messageObj.m14753O()) {
                str = group.f13720g;
            }
            String str2 = group.f13720g;
            UploadUtils.m16965l("Upload Performance", "0.2 sendPhotoWithNotes(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
            if (!messageObj.m14778p().equals(MessageObj.MessageType.PhotoNote) || TextUtils.isEmpty(strM14747I4)) {
                UploadMultipleChatMediaHelperQueue.m16892F().m16943v(z8, str, imageItem, messageObj, group.f13723j, false);
            } else {
                UploadMultipleChatMediaHelperQueue.m16892F().m16944w(z8, str, imageItem, str2, strM14747I4, null, null, null, null, messageObj, group.f13723j, null, false);
            }
            UploadUtils.m16965l("Upload Performance", "0.2 sendPhotoWithNotes(MessageObj); ==== end");
        }
    }

    /* renamed from: H */
    public static String m14556H(String str, String str2, double d9, double d10, String str3, int i9, int i10, String str4, String str5) {
        HashMap map = new HashMap();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        map.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        map.put("address", str2);
        map.put("latitude", Double.valueOf(d9));
        map.put("longitude", Double.valueOf(d10));
        map.put("snapshotUrl", str3);
        map.put("snapshotWidth", Integer.valueOf(i9));
        map.put("snapshotHeight", Integer.valueOf(i10));
        map.put("snapshotMediaId", str5);
        map.put("imageItem", str4);
        return new JSONObject(map).toString();
    }

    /* renamed from: H0 */
    public static List<MessageObj> m14557H0(ArrayList<ImageItem> arrayList, int i9, Date date, List<Group> list, boolean z8, boolean z9) {
        List<MessageObj> listM14553F0;
        UploadUtils.m16965l("Upload Performance", "Start to Trace >>>>>>>>>>>>>>>>>>>>>>>>>>>");
        UploadUtils.m16965l("Upload Performance", "0. sendPhotos( ArrayList<ImageItem>); ==== start");
        ArrayList arrayList2 = new ArrayList();
        for (Group group : list) {
            Iterator<ImageItem> it = arrayList.iterator();
            while (it.hasNext()) {
                ImageItem next = it.next();
                if (next.m16134g().isEmpty() && next.m16129b().isEmpty()) {
                    UploadUtils.m16965l("Upload Performance", "0. sendPhoto(importImage) at group:" + group);
                    listM14553F0 = m14549D0(next, i9, date, Collections.singletonList(group), z8, z9);
                } else {
                    UploadUtils.m16965l("Upload Performance", "0. sendPhotoWithNotes(importImage) at group:" + group);
                    listM14553F0 = m14553F0(next, i9, date, Collections.singletonList(group), z8);
                }
                arrayList2.addAll(listM14553F0);
                UploadUtils.m16965l("Upload Performance", "0. sendPhotos( ArrayList<ImageItem>); ==== end");
            }
        }
        return arrayList2;
    }

    /* renamed from: I */
    public static MessageObj m14558I(String str, MessageObj.MessageType messageType, String str2, int i9, Date date) {
        return m14560J(str, messageType, str2, i9, date, "", "", "", false);
    }

    /* renamed from: I0 */
    public static void m14559I0(Group group, MessageObj messageObj) {
        messageObj.m14762X("2");
        C2950b0.m14916o().m15157B(messageObj);
        C6196d0.m23692d().m23700j(new C6201i(group.f13723j, messageObj));
    }

    /* renamed from: J */
    public static MessageObj m14560J(String str, MessageObj.MessageType messageType, String str2, int i9, Date date, String str3, String str4, String str5, boolean z8) {
        MessageObj.TTLStatus tTLStatus;
        int i10;
        String strM14542A = m14542A();
        Date date2 = new Date();
        String strValueOf = String.valueOf(Globals.m7388i0().m7568k1());
        String strM14239d0 = XMPPManager.m14184g0().m14239d0();
        String str6 = m14592e0(messageType) ? "1" : "0";
        MessageObj.TTLStatus tTLStatus2 = MessageObj.TTLStatus.NO_TTL;
        MessageObj.MemberStatus memberStatus = MessageObj.MemberStatus.NO_MemberStatus;
        if (i9 > 0) {
            i10 = i9;
            tTLStatus = MessageObj.TTLStatus.NOT_START;
        } else {
            tTLStatus = tTLStatus2;
            i10 = 0;
        }
        return new MessageObj(-1L, strM14542A, str, date2.getTime(), messageType, str2, 0, strValueOf, strM14239d0, "2", tTLStatus, 0L, i10, date != null ? date.getTime() : 0L, memberStatus, false, "", str6, str3, str4, str5, z8, 0);
    }

    /* renamed from: J0 */
    public static List<MessageObj> m14561J0(StickerObj stickerObj, int i9, Date date, List<Group> list, e eVar, boolean z8) {
        StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerObj.m16284i());
        if (stickerPackObjM15293k == null) {
            return new ArrayList();
        }
        String strM14805i = stickerPackObjM15293k.m14805i();
        strM14805i.hashCode();
        MessageObj.MessageType messageType = !strM14805i.equals("Static") ? !strM14805i.equals("Animation") ? MessageObj.MessageType.AnimPngSticker : MessageObj.MessageType.AnimSticker : MessageObj.MessageType.Sticker;
        C5164m0.m20108m().m20116i(stickerObj);
        return m14545B0(messageType, Long.toString(stickerObj.m16285j()), i9, date, list, eVar, z8);
    }

    /* renamed from: K */
    public static MessageObj m14562K(String str, MessageObj.MessageType messageType, String str2, int i9, Date date, boolean z8) {
        return m14560J(str, messageType, str2, i9, date, "", "", "", z8);
    }

    /* renamed from: K0 */
    public static List<MessageObj> m14563K0(String str, String str2, int i9, Date date, List<Group> list, boolean z8) {
        UploadUtils.m16965l("Upload Performance", "0.1 sendVoice(voicePath); ==== start");
        File file = new File(str);
        ArrayList arrayList = new ArrayList();
        if (!file.exists()) {
            return arrayList;
        }
        UploadUtils.m16965l("Upload Performance", "0.1. generateVoiceContent();");
        String strM14580X = m14580X(str, null, str2);
        for (Group group : list) {
            UploadUtils.m16965l("Upload Performance", "0.1. generateMessage() at group:" + group.f13723j);
            MessageObj messageObjM14562K = m14562K(String.valueOf(group.f13727n), MessageObj.MessageType.Audio, strM14580X, i9, date, z8);
            UploadUtils.m16965l("Upload Performance", "0.1. getMessageDao().insert() at group:" + group.f13723j);
            C2950b0.m14916o().m15157B(messageObjM14562K);
            arrayList.add(messageObjM14562K);
            UploadUtils.m16965l("Upload Performance", "0.1 sendVoice(messageObj) at group:" + group.f13723j);
            m14565L0(messageObjM14562K, group);
        }
        UploadUtils.m16965l("Upload Performance", "0.1 sendVoice(voicePath); ==== end");
        return arrayList;
    }

    /* renamed from: L */
    public static String m14564L(String str, String str2, String str3, String str4, String str5) {
        HashMap map = new HashMap();
        map.put("replyMessageId", str);
        map.put("replySender", str3);
        map.put("replyMessage", str2);
        map.put("replyText", str4);
        if (!C5170o0.m20170e(str5)) {
            map.put("sourceType", str5);
        }
        return new JSONObject(map).toString();
    }

    /* renamed from: L0 */
    public static void m14565L0(MessageObj messageObj, Group group) {
        UploadUtils.m16965l("Upload Performance", "0.2. sendVoice(MessageObj); ==== start");
        if (messageObj == null) {
            return;
        }
        messageObj.f12924A = false;
        messageObj.f12959z = 0;
        String strM14747I = messageObj.m14747I("audioPath");
        String str = group.f13720g;
        UploadUtils.m16965l("Upload Performance", "0.2 sendVoice(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
        UploadMultipleChatMediaHelperQueue.m16892F().m16916A(group.f13711J, str, strM14747I, messageObj, group.f13723j);
        UploadUtils.m16965l("Upload Performance", "0.2. sendVoice(MessageObj); ==== end");
    }

    /* renamed from: M */
    public static String m14566M(C2973l0 c2973l0) {
        HashMap map = new HashMap();
        map.put("albumId", c2973l0.m15131c());
        map.put("mediaId", String.valueOf(c2973l0.m15144p()));
        map.put("mediaType", "File");
        map.put("mediaSize", String.valueOf(c2973l0.m15148t().f13197a));
        map.put("original", c2973l0.m15148t().f13200d);
        map.put("mediaName", c2973l0.m15145q());
        map.put("expirationTime", String.valueOf(c2973l0.m15140l()));
        return new JSONObject(map).toString();
    }

    /* renamed from: M0 */
    public static void m14567M0(C2898i c2898i, JSONObject jSONObject, Long l9) {
        try {
            c2898i.m14375g("commentCount", jSONObject.getString("commentCount"));
        } catch (JSONException e9) {
            Log.d("ChatUtility", "[setPhotoCommentCount] photo-" + l9 + " not containing commentCount");
            e9.printStackTrace();
        }
    }

    /* renamed from: N */
    public static String m14568N(String str, Uri uri, String str2, String str3) {
        return m14570O(str, uri, null, str2, str3, null, null, 0, 0);
    }

    /* renamed from: N0 */
    public static void m14569N0(final Activity activity, final Group group, final boolean z8, final String str, final String str2) {
        final Dialog dialogM16386e = C3123g.m16386e(activity, "", activity.getResources().getString(R.string.account_hold_suggestion), false);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.h_btn_cancel);
        textView.setText(R.string.cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.chat.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C2925v.m14616q0(dialogM16386e, str, activity, group, z8, str2, view);
            }
        });
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.h_btn_ok);
        textView2.setText(R.string.account_hold_fix);
        textView2.setTextColor(activity.getResources().getColor(R.color.you_color_normal_blue));
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.chat.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C2925v.m14618r0(dialogM16386e, activity, view);
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: O */
    public static String m14570O(String str, Uri uri, String str2, String str3, String str4, C1199a c1199a, C1199a c1199a2, int i9, int i10) throws NumberFormatException {
        HashMap map = new HashMap();
        if (uri != null) {
            map.put("imageItemUri", uri.toString());
        }
        if (!TextUtils.isEmpty(str)) {
            map.put("imageItem", str);
        }
        long jM16568o0 = CLUtility.m16568o0(str, uri);
        if (jM16568o0 > 0) {
            map.put("lastModified", Long.valueOf(jM16568o0));
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("imageUrl", str2);
        }
        map.put("imageId", str3);
        map.put("mediaId", str4);
        if (i9 != 0) {
            map.put("width", Integer.valueOf(i9));
        }
        if (i10 != 0) {
            map.put("height", Integer.valueOf(i10));
        }
        try {
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.parseLong(str4));
            if (c2973l0M14725v != null) {
                if (c2973l0M14725v.m15150v() > 0) {
                    map.put("commentCount", Integer.valueOf(c2973l0M14725v.m15150v()));
                }
                if (i9 == 0) {
                    map.put("width", Integer.valueOf(c2973l0M14725v.m15151w()));
                }
                if (i10 == 0) {
                    map.put("height", Integer.valueOf(c2973l0M14725v.m15141m()));
                }
            }
        } catch (Exception e9) {
            Log.d("ChatUtility", "[generateShareMediaContent] Parse mediaId exception = " + e9.getMessage());
        }
        if (c1199a != null) {
            map.put("thumbKey", c1199a.f5810a);
            map.put("thumbAuth", c1199a.f5811b);
        }
        if (c1199a2 != null) {
            map.put("key", c1199a2.f5810a);
            map.put("auth", c1199a2.f5811b);
        }
        return new JSONObject(map).toString();
    }

    /* renamed from: O0 */
    public static void m14571O0(Activity activity, Group group, boolean z8, String str, String str2) {
        if (activity == null) {
            return;
        }
        if (m14620s0()) {
            m14569N0(activity, group, z8, str, str2);
        } else if (m14622t0()) {
            m14586b0(activity, group, z8, str, str2).show();
        } else {
            MeetingActivity.m6370f9(activity, group, z8, str, str2);
        }
    }

    /* renamed from: P */
    public static String m14572P(FileItem fileItem, C2973l0 c2973l0, C1199a c1199a) {
        HashMap map = new HashMap();
        map.put("albumId", c2973l0.m15131c());
        map.put("mediaId", String.valueOf(c2973l0.m15144p()));
        map.put("mediaType", "File");
        map.put("mediaSize", String.valueOf(c2973l0.m15148t().f13197a));
        map.put("mediaName", fileItem.m16114a());
        map.put("original", c2973l0.m15148t().f13200d);
        map.put("expirationTime", String.valueOf(c2973l0.m15140l()));
        if (c1199a != null) {
            map.put("key", c1199a.f5810a);
            map.put("auth", c1199a.f5811b);
        }
        return new JSONObject(map).toString();
    }

    /* renamed from: Q */
    public static String m14573Q(String str, FileItem fileItem) {
        HashMap map = new HashMap();
        map.put("albumId", str);
        map.put("mediaType", "File");
        map.put("mediaSize", String.valueOf(fileItem.m16116c()));
        map.put("mediaName", fileItem.m16114a());
        return new JSONObject(map).toString();
    }

    /* renamed from: R */
    public static String m14574R(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, String str7) {
        return m14575S(str, str2, str3, str4, str5, str6, uri, null, str7, 0, 0);
    }

    /* renamed from: S */
    public static String m14575S(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, String str7, String str8, int i9, int i10) {
        HashMap map = new HashMap();
        map.put("mediaId", str);
        map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str2);
        map.put("noteMediaType", str3);
        map.put("noteMediaDescription", str4);
        map.put("commentCount", str5);
        map.put("imageId", str8);
        if (CLUtility.m16613z1(str6, uri)) {
            if (!TextUtils.isEmpty(str6)) {
                map.put("imageItem", str6);
            }
            if (uri != null) {
                map.put("imageItemUri", uri.toString());
            }
            long jM16568o0 = CLUtility.m16568o0(str6, uri);
            if (jM16568o0 > 0) {
                map.put("lastModified", Long.valueOf(jM16568o0));
            }
        }
        if (!TextUtils.isEmpty(str7)) {
            map.put("imageUrl", str7);
        }
        if (i9 != 0) {
            map.put("width", Integer.valueOf(i9));
        }
        if (i10 != 0) {
            map.put("height", Integer.valueOf(i10));
        }
        return new JSONObject(map).toString();
    }

    /* renamed from: T */
    public static String m14576T(String str, Uri uri, String str2, String str3, String str4, String str5) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map.put("imageItem", str);
        }
        if (uri != null) {
            map.put("imageItemUri", uri.toString());
        }
        map.put("imageId", str2);
        map.put("mediaNotes", str3);
        map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str4);
        map.put("noteMediaDescription", str5);
        long jM16568o0 = CLUtility.m16568o0(str, uri);
        if (jM16568o0 > 0) {
            map.put("lastModified", Long.valueOf(jM16568o0));
        }
        return new JSONObject(map).toString();
    }

    /* renamed from: U */
    public static String m14577U(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put("stickerId", str2);
        map.put("packId", str);
        map.put("width", str3);
        map.put("height", str4);
        return new JSONObject(map).toString();
    }

    /* renamed from: V */
    public static String m14578V(String str, String str2) {
        HashMap map = new HashMap();
        map.put("userId", str);
        map.put("displayName", str2);
        return new JSONObject(map).toString();
    }

    /* renamed from: W */
    public static String m14579W(String str, Uri uri, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map.put("videoPath", str);
        }
        if (uri != null) {
            map.put("videoUri", uri.toString());
        }
        map.put("mediaId", str2);
        map.put("duration", str3);
        map.put("videoThumbPath", str4);
        map.put("videoId", str5);
        map.put("isServerTranscode", str6);
        map.put("width", str7);
        map.put("height", str8);
        return new JSONObject(map).toString();
    }

    /* renamed from: X */
    public static String m14580X(String str, String str2, String str3) {
        HashMap map = new HashMap();
        map.put("audioPath", str);
        map.put("mediaId", str2);
        map.put("duration", str3);
        return new JSONObject(map).toString();
    }

    /* renamed from: Y */
    public static int m14581Y(String str) {
        String str2 = CLUtility.m16444H0("download") + File.separator + str;
        Cursor cursorQuery = Globals.m7372O().getContentResolver().query(MediaStore.Files.getContentUri("external"), null, "_data LIKE ?", new String[]{"%" + str2 + "%"}, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    int count = cursorQuery.getCount();
                    cursorQuery.close();
                    return count;
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return 0;
    }

    /* renamed from: Z */
    public static String m14582Z(MessageObj messageObj, C2973l0 c2973l0) {
        if (c2973l0 == null) {
            return null;
        }
        long jM15144p = c2973l0.m15144p();
        return jM15144p != 0 ? m14584a0(String.valueOf(jM15144p)) : messageObj.m14777o();
    }

    /* renamed from: a0 */
    public static String m14584a0(String str) {
        return str + "-video";
    }

    /* renamed from: b0 */
    public static Dialog m14586b0(final Activity activity, final Group group, final boolean z8, final String str, final String str2) {
        final Dialog dialogM16386e = C3123g.m16386e(activity, activity.getResources().getString(R.string.upgrade_pro_title), activity.getResources().getString(R.string.dialog_upgrade_pro_content_with_duration, Integer.valueOf(Globals.m7388i0().m7408C0())), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(activity.getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        textView2.setTextColor(activity.getResources().getColor(R.color.you_color_normal_blue));
        textView2.setText(activity.getResources().getString(R.string.dialog_upgrade_pro_view));
        textView2.setTypeface(textView2.getTypeface(), 1);
        textView2.setTextSize(2, 16.0f);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.chat.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C2925v.m14612o0(dialogM16386e, activity, view);
            }
        });
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(activity.getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(R.string.dialog_upgrade_pro_later);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.chat.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C2925v.m14614p0(dialogM16386e, activity, group, z8, str, str2, view);
            }
        });
        return dialogM16386e;
    }

    /* renamed from: c0 */
    public static Uri m14588c0(long j9) {
        String name = new File(CLUtility.m16528e0(String.valueOf(j9))).getName();
        ContentResolver contentResolver = Globals.m7372O().getContentResolver();
        Uri contentUri = Build.VERSION.SDK_INT >= 29 ? MediaStore.Downloads.EXTERNAL_CONTENT_URI : MediaStore.Files.getContentUri("external");
        Cursor cursorQuery = contentResolver.query(contentUri, null, "_display_name=?", new String[]{name}, null);
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                int columnIndex = cursorQuery.getColumnIndex("_id");
                if (columnIndex != -1) {
                    Uri uriWithAppendedId = ContentUris.withAppendedId(contentUri, cursorQuery.getLong(columnIndex));
                    if (CLUtility.m16613z1(null, uriWithAppendedId)) {
                        return uriWithAppendedId;
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: d0 */
    public static boolean m14590d0(List<C2973l0> list) {
        Iterator<C2973l0> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().m15147s().equals("File")) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e0 */
    public static boolean m14592e0(MessageObj.MessageType messageType) {
        return messageType.equals(MessageObj.MessageType.Photo) || messageType.equals(MessageObj.MessageType.PhotoNote) || messageType.equals(MessageObj.MessageType.Audio) || messageType.equals(MessageObj.MessageType.Video) || messageType.equals(MessageObj.MessageType.File);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8, types: [org.jivesoftware.smack.packet.Message] */
    /* JADX WARN: Type inference failed for: r0v9, types: [org.jivesoftware.smack.packet.Message] */
    /* renamed from: f */
    public static Message m14593f(String str, String str2, MessageObj messageObj) {
        AdvancedMessage message;
        Date dateM14788z;
        if (messageObj.m14784v() == 0) {
            message = str.equals("Dual") ? new Message(str2, Message.Type.chat) : new Message(str2, Message.Type.groupchat);
        } else {
            AdvancedMessage advancedMessage = str.equals("Dual") ? new AdvancedMessage(str2, Message.Type.chat) : new AdvancedMessage(str2, Message.Type.groupchat);
            advancedMessage.m14055d0("1");
            message = advancedMessage;
        }
        if (str.equals("Dual")) {
            message.m22165r(XMPPManager.m14184g0().m14239d0());
        }
        message.m22166s(messageObj.m14777o());
        m14617r(message, messageObj.m14778p(), messageObj.m14779q());
        if (!m14594f0(messageObj) && messageObj.m14784v() == 0 && (dateM14788z = messageObj.m14788z()) != null) {
            message.m22095U(new Date(dateM14788z.getTime() + FriendsClient.f13679k));
        }
        if (messageObj.m14787y() > 0) {
            m14623u(message, messageObj.m14787y());
        }
        if (messageObj.m14784v() != 0) {
            m14621t(message, messageObj.m14784v());
        }
        if (messageObj.m14749K()) {
            m14619s(message, messageObj);
        }
        if (messageObj.m14750L()) {
            m14615q(message);
        }
        return message;
    }

    /* renamed from: f0 */
    public static boolean m14594f0(MessageObj messageObj) {
        return m14592e0(messageObj.m14778p());
    }

    /* renamed from: g0 */
    public static boolean m14596g0(MessageObj messageObj) {
        if (messageObj == null) {
            return false;
        }
        return messageObj.m14740B().equals("3") || messageObj.m14744F().equals("5") || messageObj.m14744F().equals("4");
    }

    /* renamed from: h0 */
    public static boolean m14598h0(MessageObj messageObj) {
        if (messageObj == null) {
            return false;
        }
        if (m14594f0(messageObj)) {
            if (!messageObj.m14744F().equals("1") && !messageObj.m14744F().equals("2") && (!messageObj.m14740B().equals("2") || !messageObj.m14744F().equals("3"))) {
                return false;
            }
        } else if (!messageObj.m14740B().equals("2")) {
            return false;
        }
        return true;
    }

    /* renamed from: i0 */
    public static boolean m14600i0(MessageObj messageObj) {
        if (messageObj == null) {
            return false;
        }
        if (m14594f0(messageObj)) {
            if (!messageObj.m14740B().equals("0") || !messageObj.m14744F().equals("3")) {
                return false;
            }
        } else if (!messageObj.m14740B().equals("0")) {
            return false;
        }
        return true;
    }

    /* renamed from: j */
    public static /* synthetic */ int m14601j() {
        int i9 = f12825b;
        f12825b = i9 + 1;
        return i9;
    }

    /* renamed from: j0 */
    public static boolean m14602j0(MessageObj messageObj) {
        return messageObj != null && messageObj.m14740B().equals("10");
    }

    /* renamed from: k0 */
    public static boolean m14604k0(MessageObj messageObj) {
        try {
            return Boolean.valueOf(messageObj.m14747I("isServerTranscode")).booleanValue();
        } catch (Exception e9) {
            e9.printStackTrace();
            return false;
        }
    }

    /* renamed from: l0 */
    public static boolean m14606l0() {
        return !Arrays.asList(C4831a.m19169c("upgrade_pro_country_tier2").split(",")).contains(Locale.getDefault().getCountry());
    }

    /* renamed from: m0 */
    public static boolean m14608m0(String str) {
        return str.startsWith("A") && str.length() == 21 && str.substring(1).matches("[a-zA-Z0-9]{20}");
    }

    /* renamed from: n */
    public static /* synthetic */ int m14609n() {
        int i9 = f12826c;
        f12826c = i9 + 1;
        return i9;
    }

    /* renamed from: n0 */
    public static /* synthetic */ void m14610n0(Activity activity) {
        if (activity.isFinishing()) {
            return;
        }
        m14631y();
    }

    /* renamed from: o0 */
    public static /* synthetic */ void m14612o0(Dialog dialog, Activity activity, View view) {
        dialog.dismiss();
        activity.startActivity(new Intent(activity, (Class<?>) UpgradeToProUserActivity.class));
    }

    /* renamed from: p0 */
    public static /* synthetic */ void m14614p0(Dialog dialog, Activity activity, Group group, boolean z8, String str, String str2, View view) {
        dialog.dismiss();
        MeetingActivity.m6370f9(activity, group, z8, str, str2);
    }

    /* renamed from: q */
    public static void m14615q(Message message) {
        C2898i c2898i = new C2898i("broadcast", "U");
        c2898i.m14376h(C5616j.m22341f("1").toString());
        message.m22154b(c2898i);
    }

    /* renamed from: q0 */
    public static /* synthetic */ void m14616q0(Dialog dialog, String str, Activity activity, Group group, boolean z8, String str2, View view) {
        dialog.dismiss();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MeetingActivity.m6370f9(activity, group, z8, str, str2);
    }

    /* renamed from: r */
    public static String m14617r(Message message, MessageObj.MessageType messageType, String str) throws JSONException, FileNotFoundException {
        String str2;
        C2898i c2898i;
        if (messageType.equals(MessageObj.MessageType.Text)) {
            message.m22090P(str);
            return str;
        }
        if (!messageType.equals(MessageObj.MessageType.Photo)) {
            if (messageType.equals(MessageObj.MessageType.Sticker) || messageType.equals(MessageObj.MessageType.AnimSticker) || messageType.equals(MessageObj.MessageType.AnimPngSticker)) {
                StickerObj stickerObjM15278f = C2950b0.m14924w().m15278f(Long.valueOf(str).longValue());
                if (stickerObjM15278f == null) {
                    return str;
                }
                C2898i c2898i2 = new C2898i("sticker", "U");
                c2898i2.m14375g("stickerId", Long.toString(stickerObjM15278f.m16285j()));
                c2898i2.m14375g("packId", Long.toString(stickerObjM15278f.m16284i()));
                c2898i2.m14375g("width", String.valueOf(stickerObjM15278f.m16289n()));
                c2898i2.m14375g("height", String.valueOf(stickerObjM15278f.m16279d()));
                message.m22154b(c2898i2);
                return str;
            }
            if (messageType.equals(MessageObj.MessageType.Comment)) {
                try {
                    str2 = str;
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.isNull("commentType") || !jSONObject.getString("commentType").equalsIgnoreCase("Doodle")) {
                            c2898i = new C2898i("photoComment", "U");
                        } else {
                            c2898i = new C2898i("doodleComment", "U");
                            c2898i.m14375g("thumbnail", jSONObject.getString("thumbnail"));
                            c2898i.m14375g(TtmlNode.ATTR_TTS_COLOR, jSONObject.getString(TtmlNode.ATTR_TTS_COLOR));
                        }
                        c2898i.m14375g("albumId", jSONObject.getString("albumId"));
                        c2898i.m14375g("mediaId", jSONObject.getString("mediaId"));
                        String string = jSONObject.getString("commentId");
                        if (!TextUtils.isEmpty(string)) {
                            c2898i.m14375g("commentId", string);
                            c2898i.m14375g("commentType", jSONObject.getString("commentType"));
                            if (!jSONObject.isNull("duration")) {
                                c2898i.m14375g("duration", jSONObject.getString("duration"));
                            }
                        }
                        c2898i.m14376h(C5616j.m22341f(jSONObject.getString("comment")).toString());
                        message.m22154b(c2898i);
                    } catch (JSONException e9) {
                        e = e9;
                        Log.d("ChatUtility", Log.getStackTraceString(e));
                        return str2;
                    }
                } catch (JSONException e10) {
                    e = e10;
                    str2 = str;
                }
            } else {
                str2 = str;
                if (messageType.equals(MessageObj.MessageType.CommentUpdate)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str2);
                        C2898i c2898i3 = new C2898i("photoCommentUpdate", "U");
                        c2898i3.m14375g("albumId", jSONObject2.getString("albumId"));
                        c2898i3.m14375g("mediaId", jSONObject2.getString("mediaId"));
                        c2898i3.m14375g("commentType", jSONObject2.getString("commentType"));
                        c2898i3.m14375g("commentId", jSONObject2.getString("commentId"));
                        c2898i3.m14376h(jSONObject2.getString("comment"));
                        message.m22154b(c2898i3);
                    } catch (JSONException e11) {
                        Log.d("ChatUtility", Log.getStackTraceString(e11));
                    }
                } else if (messageType.equals(MessageObj.MessageType.CreateMedia)) {
                    try {
                        JSONObject jSONObject3 = new JSONObject(str2);
                        C2898i c2898i4 = new C2898i("groupAlbum", "U");
                        c2898i4.m14375g("albumId", jSONObject3.getString("albumId"));
                        c2898i4.m14375g("albumName", jSONObject3.getString("albumName"));
                        c2898i4.m14375g("mediaId", jSONObject3.getString("mediaId"));
                        c2898i4.m14375g("mediaType", jSONObject3.getString("mediaType"));
                        c2898i4.m14375g("width", jSONObject3.getString("width"));
                        c2898i4.m14375g("height", jSONObject3.getString("height"));
                        c2898i4.m14375g("numberUpload", jSONObject3.getString("numberUpload"));
                        c2898i4.m14376h(C5616j.m22341f(jSONObject3.getString(FirebaseAnalytics.Param.CONTENT)).toString());
                        message.m22154b(c2898i4);
                    } catch (JSONException e12) {
                        Log.d("ChatUtility", Log.getStackTraceString(e12));
                    }
                } else if (messageType.equals(MessageObj.MessageType.DeleteMedia)) {
                    try {
                        JSONObject jSONObject4 = new JSONObject(str2);
                        C2898i c2898i5 = new C2898i("photoDelete", "U");
                        c2898i5.m14375g("albumId", jSONObject4.getString("albumId"));
                        c2898i5.m14375g("albumName", jSONObject4.getString("albumName"));
                        c2898i5.m14375g("numberDelete", jSONObject4.getString("numberDelete"));
                        message.m22154b(c2898i5);
                    } catch (JSONException e13) {
                        Log.d("ChatUtility", Log.getStackTraceString(e13));
                    }
                } else if (messageType.equals(MessageObj.MessageType.VideoDelete)) {
                    try {
                        JSONObject jSONObject5 = new JSONObject(str2);
                        C2898i c2898i6 = new C2898i("videoDelete", "U");
                        c2898i6.m14375g("albumId", jSONObject5.getString("albumId"));
                        c2898i6.m14375g("albumName", jSONObject5.getString("albumName"));
                        c2898i6.m14375g("numberDelete", jSONObject5.getString("numberDelete"));
                        message.m22154b(c2898i6);
                    } catch (JSONException e14) {
                        Log.d("ChatUtility", Log.getStackTraceString(e14));
                    }
                } else if (messageType.equals(MessageObj.MessageType.FileDelete)) {
                    try {
                        JSONObject jSONObject6 = new JSONObject(str2);
                        C2898i c2898i7 = new C2898i("fileDelete", "U");
                        c2898i7.m14375g("albumId", jSONObject6.getString("albumId"));
                        c2898i7.m14375g("albumName", jSONObject6.getString("albumName"));
                        c2898i7.m14375g("numberDelete", jSONObject6.getString("numberDelete"));
                        message.m22154b(c2898i7);
                    } catch (JSONException e15) {
                        Log.d("ChatUtility", Log.getStackTraceString(e15));
                    }
                } else if (messageType.equals(MessageObj.MessageType.PhotoNote)) {
                    try {
                        JSONObject jSONObject7 = new JSONObject(str2);
                        C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.valueOf(jSONObject7.getString("mediaId")).longValue());
                        if (c2973l0M14725v != null) {
                            C2898i c2898i8 = new C2898i("media", "U");
                            c2898i8.m14375g("mediaId", Long.toString(c2973l0M14725v.m15144p()));
                            c2898i8.m14375g("mediaType", c2973l0M14725v.m15147s());
                            c2898i8.m14375g("albumId", c2973l0M14725v.m15131c());
                            c2898i8.m14375g("thumbnail", c2973l0M14725v.m15149u().f13200d);
                            c2898i8.m14375g("original", c2973l0M14725v.m15148t().f13200d);
                            c2898i8.m14375g("width", String.valueOf(c2973l0M14725v.m15151w()));
                            c2898i8.m14375g("height", String.valueOf(c2973l0M14725v.m15141m()));
                            c2898i8.m14375g(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, CLUtility.m16467N(jSONObject7.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION)));
                            c2898i8.m14375g("noteMediaType", jSONObject7.getString("noteMediaType"));
                            c2898i8.m14375g("noteMediaDescription", jSONObject7.getString("noteMediaDescription"));
                            c2898i8.m14375g("commentCount", jSONObject7.getString("commentCount"));
                            message.m22154b(c2898i8);
                        }
                    } catch (JSONException e16) {
                        Log.d("ChatUtility", Log.getStackTraceString(e16));
                    }
                } else if (messageType.equals(MessageObj.MessageType.Audio)) {
                    try {
                        JSONObject jSONObject8 = new JSONObject(str2);
                        C2973l0 c2973l0M14725v2 = C2950b0.m14914m().m14725v(Long.valueOf(jSONObject8.getString("mediaId")).longValue());
                        if (c2973l0M14725v2 != null) {
                            C2898i c2898i9 = new C2898i(MimeTypes.BASE_TYPE_AUDIO, "U");
                            c2898i9.m14375g("mediaId", Long.toString(c2973l0M14725v2.m15144p()));
                            c2898i9.m14375g("mediaType", c2973l0M14725v2.m15147s());
                            c2898i9.m14375g("albumId", c2973l0M14725v2.m15131c());
                            c2898i9.m14375g("thumbnail", c2973l0M14725v2.m15149u().f13200d);
                            c2898i9.m14375g("original", c2973l0M14725v2.m15148t().f13200d);
                            c2898i9.m14375g("duration", jSONObject8.getString("duration"));
                            message.m22154b(c2898i9);
                        }
                    } catch (JSONException e17) {
                        e17.printStackTrace();
                    }
                } else if (messageType.equals(MessageObj.MessageType.UserContact)) {
                    try {
                        JSONObject jSONObject9 = new JSONObject(str2);
                        String string2 = jSONObject9.getString("userId");
                        String string3 = jSONObject9.getString("displayName");
                        C2898i c2898i10 = new C2898i("userContact", "urn:xmpp:contact:0");
                        c2898i10.m14375g("userId", string2);
                        c2898i10.m14375g("displayName", string3);
                        message.m22154b(c2898i10);
                    } catch (JSONException e18) {
                        e18.printStackTrace();
                    }
                } else if (messageType.equals(MessageObj.MessageType.Video)) {
                    try {
                        JSONObject jSONObject10 = new JSONObject(str2);
                        C2973l0 c2973l0M14725v3 = C2950b0.m14914m().m14725v(Long.valueOf(jSONObject10.getString("mediaId")).longValue());
                        if (c2973l0M14725v3 != null) {
                            C2898i c2898i11 = new C2898i(MimeTypes.BASE_TYPE_VIDEO, "urn:xmpp:video:0");
                            c2898i11.m14375g("mediaId", Long.toString(c2973l0M14725v3.m15144p()));
                            c2898i11.m14375g("mediaType", c2973l0M14725v3.m15147s());
                            c2898i11.m14375g("albumId", c2973l0M14725v3.m15131c());
                            c2898i11.m14375g("thumbnail", c2973l0M14725v3.m15149u().f13200d);
                            c2898i11.m14375g("original", c2973l0M14725v3.m15148t().f13200d);
                            c2898i11.m14375g("duration", jSONObject10.getString("duration"));
                            if ((c2973l0M14725v3.m15141m() == 0 || c2973l0M14725v3.m15151w() == 0) && jSONObject10.has("videoThumbPath")) {
                                Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(jSONObject10.getString("videoThumbPath"), null);
                                c2973l0M14725v3.m15124K(((Integer) pairM16503X0.first).intValue());
                                c2973l0M14725v3.m15121H(((Integer) pairM16503X0.second).intValue());
                            }
                            c2898i11.m14375g("width", String.valueOf(c2973l0M14725v3.m15151w()));
                            c2898i11.m14375g("height", String.valueOf(c2973l0M14725v3.m15141m()));
                            message.m22154b(c2898i11);
                        }
                    } catch (JSONException e19) {
                        e19.printStackTrace();
                    }
                } else if (messageType.equals(MessageObj.MessageType.ShareLocation)) {
                    try {
                        JSONObject jSONObject11 = new JSONObject(str2);
                        String strReplace = jSONObject11.getString("snapshotUrl").replace("&amp;", "&");
                        C2898i c2898i12 = new C2898i(FirebaseAnalytics.Param.LOCATION, "urn:xmpp:location:0");
                        c2898i12.m14375g(AppMeasurementSdk.ConditionalUserProperty.NAME, jSONObject11.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        c2898i12.m14375g("address", jSONObject11.optString("address"));
                        c2898i12.m14375g("latitude", String.valueOf(jSONObject11.getDouble("latitude")));
                        c2898i12.m14375g("longitude", String.valueOf(jSONObject11.getDouble("longitude")));
                        c2898i12.m14375g("snapshotUrl", strReplace);
                        c2898i12.m14375g("snapshotWidth", String.valueOf(jSONObject11.getInt("snapshotWidth")));
                        c2898i12.m14375g("snapshotHeight", String.valueOf(jSONObject11.getInt("snapshotHeight")));
                        c2898i12.m14375g("snapshotMediaId", jSONObject11.getString("snapshotMediaId"));
                        message.m22154b(c2898i12);
                    } catch (JSONException e20) {
                        e20.printStackTrace();
                    }
                } else if (messageType.equals(MessageObj.MessageType.File)) {
                    try {
                        JSONObject jSONObject12 = new JSONObject(str2);
                        C2898i c2898i13 = new C2898i("file", "U");
                        c2898i13.m14375g("mediaId", jSONObject12.getString("mediaId"));
                        c2898i13.m14375g("mediaType", jSONObject12.getString("mediaType"));
                        c2898i13.m14375g("albumId", jSONObject12.getString("albumId"));
                        c2898i13.m14375g("mediaSize", jSONObject12.getString("mediaSize"));
                        c2898i13.m14375g("mediaName", jSONObject12.getString("mediaName"));
                        c2898i13.m14375g("expirationTime", jSONObject12.getString("expirationTime"));
                        c2898i13.m14375g("original", jSONObject12.getString("original"));
                        if (jSONObject12.has("key")) {
                            c2898i13.m14375g("key", jSONObject12.getString("key"));
                        }
                        if (jSONObject12.has("auth")) {
                            c2898i13.m14375g("auth", jSONObject12.getString("auth"));
                        }
                        message.m22154b(c2898i13);
                    } catch (JSONException e21) {
                        e21.printStackTrace();
                    }
                } else if (messageType.equals(MessageObj.MessageType.RTC)) {
                    try {
                        JSONObject jSONObject13 = str2 != null ? new JSONObject(str2) : new JSONObject();
                        C2898i c2898i14 = new C2898i("event", "urn:xmpp:custom:event");
                        Iterator<String> itKeys = jSONObject13.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            c2898i14.m14375g(next, jSONObject13.getString(next));
                        }
                        message.m22154b(c2898i14);
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                } else if (messageType.equals(MessageObj.MessageType.Call)) {
                    try {
                        JSONObject jSONObject14 = str2 != null ? new JSONObject(str2) : new JSONObject();
                        C2898i c2898i15 = new C2898i("call", "urn:xmpp:call:0");
                        Iterator<String> itKeys2 = jSONObject14.keys();
                        while (itKeys2.hasNext()) {
                            String next2 = itKeys2.next();
                            c2898i15.m14375g(next2, jSONObject14.getString(next2));
                        }
                        message.m22154b(c2898i15);
                    } catch (JSONException e23) {
                        e23.printStackTrace();
                    }
                } else if (messageType.equals(MessageObj.MessageType.ReplyText)) {
                    if (str2 != null) {
                        try {
                            JSONObject jSONObject15 = new JSONObject(str2);
                            String string4 = jSONObject15.getString("replyMessageId");
                            boolean z8 = jSONObject15.has("sourceType") && !C5170o0.m20170e(jSONObject15.getString("sourceType"));
                            String string5 = jSONObject15.getString("replyMessage");
                            String string6 = jSONObject15.getString("replySender");
                            String string7 = jSONObject15.getString("replyText");
                            C2898i c2898i16 = new C2898i("textReply", "urn:xmpp:textreply:0");
                            c2898i16.m14375g("sourceId", string4);
                            c2898i16.m14375g("sourceSender", string6);
                            if (z8) {
                                c2898i16.m14375g("sourceType", "File");
                            }
                            c2898i16.m14376h(C2929x.m14650a("source", string5, "", null) + C2929x.m14650a(TtmlNode.TAG_BODY, string7, "", null));
                            message.m22154b(c2898i16);
                        } catch (JSONException e24) {
                            e24.printStackTrace();
                        }
                    }
                } else {
                    if (!messageType.equals(MessageObj.MessageType.AnnouncementType02)) {
                        if (MessageObj.MessageType.TOPIC != messageType && MessageObj.MessageType.SUPERVISE != messageType) {
                            message.m22097W(messageType.toString());
                            message.m22090P(str);
                            return str;
                        }
                        try {
                            JSONObject jSONObject16 = new JSONObject(str);
                            C2898i c2898i17 = new C2898i("event", "urn:xmpp:custom:event");
                            Iterator<String> itKeys3 = jSONObject16.keys();
                            while (itKeys3.hasNext()) {
                                String next3 = itKeys3.next();
                                c2898i17.m14375g(next3, jSONObject16.getString(next3));
                            }
                            message.m22154b(c2898i17);
                            return str;
                        } catch (Exception e25) {
                            C5154j.m20076j(e25);
                            return str;
                        }
                    }
                    if (str2 != null) {
                        try {
                            JSONObject jSONObject17 = new JSONObject(str2);
                            String string8 = jSONObject17.getString("type");
                            String string9 = jSONObject17.getString("url");
                            String string10 = jSONObject17.getString("titleOfUrl");
                            String string11 = jSONObject17.getString("template");
                            String string12 = jSONObject17.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
                            String string13 = jSONObject17.getString("announcementId");
                            String string14 = jSONObject17.getJSONObject(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION).getString(FirebaseAnalytics.Param.CONTENT);
                            JSONObject jSONObject18 = jSONObject17.getJSONObject("image");
                            String string15 = jSONObject18.getString("src");
                            String string16 = jSONObject18.getString("width");
                            String string17 = jSONObject18.getString("height");
                            C2898i c2898i18 = new C2898i("announcement", "urn:xmpp:announcement:0");
                            c2898i18.m14375g("type", string8);
                            c2898i18.m14375g("url", string9);
                            c2898i18.m14375g("titleOfUrl", string10);
                            c2898i18.m14375g("template", string11);
                            c2898i18.m14375g(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, string12);
                            c2898i18.m14375g(TtmlNode.ATTR_ID, string13);
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(Pair.create("src", string15));
                            arrayList.add(Pair.create("width", string16));
                            arrayList.add(Pair.create("height", string17));
                            c2898i18.m14376h(C2929x.m14650a(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, string14, "urn:xmpp:announcement:description:0", null) + C2929x.m14650a("image", null, "urn:xmpp:announcement:image:0", arrayList));
                            message.m22154b(c2898i18);
                        } catch (JSONException e26) {
                            e26.printStackTrace();
                        }
                    }
                }
            }
            return str2;
        }
        try {
            JSONObject jSONObject19 = new JSONObject(str);
            C2973l0 c2973l0M14725v4 = C2950b0.m14914m().m14725v(Long.valueOf(jSONObject19.getString("mediaId")).longValue());
            if (c2973l0M14725v4 != null) {
                C2898i c2898i19 = new C2898i("media", "U");
                c2898i19.m14375g("mediaId", Long.toString(c2973l0M14725v4.m15144p()));
                c2898i19.m14375g("mediaType", c2973l0M14725v4.m15147s());
                c2898i19.m14375g("albumId", c2973l0M14725v4.m15131c());
                if (c2973l0M14725v4.m15149u() != null) {
                    c2898i19.m14375g("thumbnail", c2973l0M14725v4.m15149u().f13200d);
                }
                if (c2973l0M14725v4.m15148t() != null) {
                    c2898i19.m14375g("original", c2973l0M14725v4.m15148t().f13200d);
                }
                if (c2973l0M14725v4.m15141m() == 0 || c2973l0M14725v4.m15151w() == 0) {
                    Pair<Integer, Integer> pairM16503X02 = CLUtility.m16503X0(jSONObject19.optString("imageItem"), CLUtility.m16510Z1(jSONObject19.optString("imageItemUri")));
                    int iIntValue = ((Integer) pairM16503X02.first).intValue();
                    int iIntValue2 = ((Integer) pairM16503X02.second).intValue();
                    if (iIntValue > 0 && iIntValue2 > 0) {
                        c2973l0M14725v4.m15124K(iIntValue);
                        c2973l0M14725v4.m15121H(iIntValue2);
                    }
                }
                if (jSONObject19.has("key")) {
                    c2898i19.m14375g("key", jSONObject19.getString("key"));
                }
                if (jSONObject19.has("auth")) {
                    c2898i19.m14375g("auth", jSONObject19.getString("auth"));
                }
                if (jSONObject19.has("thumbKey")) {
                    c2898i19.m14375g("thumbKey", jSONObject19.getString("thumbKey"));
                }
                if (jSONObject19.has("thumbAuth")) {
                    c2898i19.m14375g("thumbAuth", jSONObject19.getString("thumbAuth"));
                }
                c2898i19.m14375g("width", String.valueOf(c2973l0M14725v4.m15151w()));
                c2898i19.m14375g("height", String.valueOf(c2973l0M14725v4.m15141m()));
                m14567M0(c2898i19, jSONObject19, Long.valueOf(c2973l0M14725v4.m15144p()));
                message.m22154b(c2898i19);
            }
        } catch (JSONException e27) {
            e27.printStackTrace();
        }
        return str;
    }

    /* renamed from: r0 */
    public static /* synthetic */ void m14618r0(Dialog dialog, Activity activity, View view) {
        dialog.dismiss();
        CLUtility.m16473O1(activity, Globals.m7388i0().m7427G().get(0));
    }

    /* renamed from: s */
    public static void m14619s(Message message, MessageObj messageObj) {
        C2898i c2898i = new C2898i("info", "U");
        c2898i.m14375g("sourceId", messageObj.m14782t());
        c2898i.m14375g("msgFwId", messageObj.m14781s());
        if (!C5170o0.m20170e(messageObj.m14775m())) {
            c2898i.m14375g("lastFwId", messageObj.m14775m());
        }
        message.m22154b(c2898i);
    }

    /* renamed from: s0 */
    public static boolean m14620s0() {
        if (Globals.m7388i0().m7427G().isEmpty()) {
            return false;
        }
        String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        long jM14629x = m14629x(Globals.m7388i0().m7615t0(), str);
        if (jM14629x < 1 && jM14629x != -1) {
            return false;
        }
        Globals.m7388i0().m7594p3(str);
        return true;
    }

    /* renamed from: t */
    public static void m14621t(Message message, long j9) {
        C2898i c2898i = new C2898i("schedule", "U");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm':00Z'", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        c2898i.m14376h(C5616j.m22341f(simpleDateFormat.format(new Date(j9))).toString());
        message.m22154b(c2898i);
    }

    /* renamed from: t0 */
    public static boolean m14622t0() {
        boolean z8;
        boolean zM7591p = Globals.m7388i0().m7591p();
        if (!zM7591p) {
            return zM7591p;
        }
        int i9 = 0;
        if (m14606l0()) {
            String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            long jM14629x = m14629x(Globals.m7388i0().m7627v0(), str);
            if (jM14629x < C4831a.m19168b("upgrade_pro_frequency_tier1") && jM14629x != -1) {
                return false;
            }
            Globals.m7388i0().m7606r3(str);
            return true;
        }
        long jM19168b = C4831a.m19168b("upgrade_pro_frequency_tier2");
        int iM7467P = Globals.m7388i0().m7467P() + 1;
        if (jM19168b == iM7467P) {
            z8 = true;
        } else {
            i9 = iM7467P;
            z8 = false;
        }
        Globals.m7388i0().m7415D2(i9);
        return z8;
    }

    /* renamed from: u */
    public static void m14623u(Message message, int i9) {
        C2898i c2898i = new C2898i(Constants.FirelogAnalytics.PARAM_TTL, "U");
        c2898i.m14376h(C5616j.m22341f(String.valueOf(i9)).toString());
        message.m22154b(c2898i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01f5 A[Catch: all -> 0x0229, TRY_ENTER, TryCatch #5 {all -> 0x0229, blocks: (B:52:0x01f5, B:53:0x01fb), top: B:90:0x01f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01fb A[Catch: all -> 0x0229, TRY_LEAVE, TryCatch #5 {all -> 0x0229, blocks: (B:52:0x01f5, B:53:0x01fb), top: B:90:0x01f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0214 A[Catch: all -> 0x0226, LOOP:0: B:56:0x020e->B:58:0x0214, LOOP_END, TryCatch #3 {all -> 0x0226, blocks: (B:55:0x020a, B:56:0x020e, B:58:0x0214, B:60:0x021b, B:54:0x0200), top: B:91:0x0200 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x021b A[Catch: all -> 0x0226, TRY_LEAVE, TryCatch #3 {all -> 0x0226, blocks: (B:55:0x020a, B:56:0x020e, B:58:0x0214, B:60:0x021b, B:54:0x0200), top: B:91:0x0200 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0219 A[EDGE_INSN: B:96:0x0219->B:59:0x0219 BREAK  A[LOOP:0: B:56:0x020e->B:58:0x0214], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v3, types: [int] */
    /* JADX WARN: Type inference failed for: r8v6, types: [int] */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.lang.StringBuilder] */
    /* renamed from: u0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Uri m14624u0(String str, boolean z8, String str2) throws Throwable {
        String string;
        StringBuilder sb;
        String str3;
        Uri uriInsert;
        String string2;
        boolean z9;
        boolean z10;
        ?? r8;
        FileInputStream fileInputStream;
        Throwable th;
        OutputStream fileOutputStream;
        byte[] bArr;
        int i9;
        boolean z11;
        File file = new File(str);
        Object obj = null;
        if (!file.exists()) {
            return null;
        }
        ContentResolver contentResolver = Globals.m7372O().getContentResolver();
        ContentValues contentValues = new ContentValues();
        boolean z12 = "Photo".equals(str2) && "0".equals(FilenameUtils.getExtension(str));
        boolean zEquals = str2.equals("Video");
        String str4 = "";
        try {
            try {
                try {
                    try {
                        if (zEquals) {
                            contentValues.put("_display_name", file.getName());
                            contentValues.put("date_modified", Long.valueOf(file.lastModified()));
                            contentValues.put("mime_type", CLUtility.m16452J0(str, null));
                            contentValues.put("duration", Long.valueOf(CLUtility.m16440G0(str, null)));
                            contentValues.put("_size", Long.valueOf(file.length()));
                            String strM16444H0 = CLUtility.m16444H0(MimeTypes.BASE_TYPE_VIDEO);
                            if (Build.VERSION.SDK_INT >= 29) {
                                contentValues.put("relative_path", strM16444H0);
                            } else {
                                String str5 = strM16444H0 + File.separator + file.getName();
                                contentValues.put("_data", str5);
                                str4 = str5;
                            }
                            uriInsert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                            z11 = zEquals;
                        } else if (str2.equals("Photo") && !z8) {
                            File file2 = new File(CLUtility.m16464M0());
                            if (z12) {
                                contentValues.put("_display_name", file2.getName());
                                contentValues.put("mime_type", CLUtility.m16452J0(file2.getPath(), null));
                            } else {
                                contentValues.put("_display_name", file.getName());
                                contentValues.put("mime_type", CLUtility.m16452J0(str, null));
                            }
                            contentValues.put("date_modified", Long.valueOf(file.lastModified()));
                            contentValues.put("_size", Long.valueOf(file.length()));
                            String strM16444H02 = CLUtility.m16444H0("image");
                            ?? r82 = Build.VERSION.SDK_INT;
                            if (r82 >= 29) {
                                contentValues.put("relative_path", strM16444H02);
                                z10 = r82;
                            } else {
                                if (z12) {
                                    ?? sb2 = new StringBuilder();
                                    sb2.append(strM16444H02);
                                    sb2.append(File.separator);
                                    sb2.append(file2.getName());
                                    string2 = sb2.toString();
                                    z9 = sb2;
                                } else {
                                    string2 = strM16444H02 + File.separator + file.getName();
                                    z9 = r82;
                                }
                                contentValues.put("_data", string2);
                                str4 = string2;
                                z10 = z9;
                            }
                            uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                            z11 = z10;
                        } else if (str2.equals("File") || z8) {
                            File file3 = new File(CLUtility.m16464M0());
                            if (z12) {
                                contentValues.put("_display_name", file3.getName());
                                contentValues.put("mime_type", CLUtility.m16452J0(file3.getPath(), null));
                            } else {
                                contentValues.put("_display_name", file.getName());
                            }
                            contentValues.put("date_modified", Long.valueOf(file.lastModified()));
                            contentValues.put("_size", Long.valueOf(file.length()));
                            String strM16444H03 = CLUtility.m16444H0("download");
                            ?? r83 = Build.VERSION.SDK_INT;
                            if (r83 < 29) {
                                if (z12) {
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append(strM16444H03);
                                    sb3.append(File.separator);
                                    sb3.append(file3.getName());
                                    string = sb3.toString();
                                    sb = sb3;
                                } else {
                                    string = strM16444H03 + File.separator + file.getName();
                                    sb = r83;
                                }
                                contentValues.put("_data", string);
                                Uri uriInsert2 = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues);
                                str3 = string;
                                uriInsert = uriInsert2;
                                r8 = sb;
                                fileInputStream = new FileInputStream(str);
                                if (uriInsert == null) {
                                    fileOutputStream = contentResolver.openOutputStream(uriInsert);
                                } else {
                                    fileOutputStream = new FileOutputStream(str3);
                                    try {
                                        uriInsert = Uri.fromFile(new File(str3));
                                    } catch (Throwable th2) {
                                        th = th2;
                                        try {
                                            fileInputStream.close();
                                            throw th;
                                        } catch (Throwable th3) {
                                            th.addSuppressed(th3);
                                            throw th;
                                        }
                                    }
                                }
                                bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
                                while (true) {
                                    i9 = fileInputStream.read(bArr);
                                    if (i9 > 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, i9);
                                }
                                if (!z12) {
                                    file.delete();
                                }
                                fileInputStream.close();
                                C6370g.m24480b(fileOutputStream);
                                return uriInsert;
                            }
                            contentValues.put("relative_path", strM16444H03);
                            uriInsert = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
                            z11 = r83;
                        } else {
                            uriInsert = null;
                            z11 = zEquals;
                        }
                        if (uriInsert == null) {
                        }
                        bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
                        while (true) {
                            i9 = fileInputStream.read(bArr);
                            if (i9 > 0) {
                            }
                            fileOutputStream.write(bArr, 0, i9);
                        }
                        if (!z12) {
                        }
                        fileInputStream.close();
                        C6370g.m24480b(fileOutputStream);
                        return uriInsert;
                    } catch (IOException e9) {
                        e = e9;
                        e.printStackTrace();
                        if (CLUtility.m16597v1(uriInsert)) {
                            contentResolver.delete(uriInsert, null, null);
                        }
                        C6370g.m24480b(r8);
                        return null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th5) {
                th = th5;
                obj = r8;
                C6370g.m24480b(obj);
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            r8 = 0;
        } catch (Throwable th6) {
            th = th6;
            C6370g.m24480b(obj);
            throw th;
        }
        str3 = str4;
        r8 = z11;
    }

    /* renamed from: v */
    public static boolean m14625v(Group group) {
        if (group.f13712K) {
            return Globals.m7388i0().m7616t1("BROADCASTER");
        }
        return true;
    }

    /* renamed from: v0 */
    public static void m14626v0(Activity activity, List<C2973l0> list) {
        m14628w0(activity, list, null);
    }

    /* renamed from: w */
    public static void m14627w(final Activity activity, int i9, int i10, int i11, C2973l0 c2973l0, List<Uri> list, d dVar) {
        if (i9 == f12825b + f12826c) {
            activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.chat.n
                @Override // java.lang.Runnable
                public final void run() {
                    C2925v.m14610n0(activity);
                }
            });
            if (dVar != null) {
                if (i10 == i9) {
                    dVar.mo9092b(list);
                    return;
                } else {
                    dVar.mo9091a();
                    return;
                }
            }
            if (f12829f) {
                C5187v0.m20271g(Globals.m7372O().getResources().getString(R.string.download_file_complete_hint) + CLUtility.m16444H0("download"));
                return;
            }
            String strM15147s = c2973l0.m15147s();
            strM15147s.hashCode();
            if (strM15147s.equals("Photo")) {
                C5187v0.m20268d(Globals.m7372O().getResources().getQuantityString(R.plurals.download_photo_success, i10, Integer.valueOf(i10)));
            } else if (strM15147s.equals("Video")) {
                C5187v0.m20268d(Globals.m7372O().getResources().getQuantityString(R.plurals.download_video_success, i10, Integer.valueOf(i10)));
            }
        }
    }

    /* renamed from: w0 */
    public static void m14628w0(Activity activity, List<C2973l0> list, d dVar) throws IOException {
        String str;
        ArrayList arrayList;
        Iterator<C2973l0> it;
        boolean z8;
        String str2;
        String str3;
        String str4;
        ArrayList arrayList2;
        String strM16464M0;
        Uri uriM14588c0;
        String str5 = ".temp";
        if (activity == null || list == null || list.size() == 0) {
            return;
        }
        f12829f = m14590d0(list);
        ArrayList arrayList3 = new ArrayList();
        DialogC6459g dialogC6459g = new DialogC6459g(activity, R.style.FriendSelectorDialog);
        f12828e = dialogC6459g;
        dialogC6459g.m24766h(true);
        f12828e.m24767i(true);
        int size = list.size();
        f12825b = 0;
        f12826c = 0;
        f12827d = false;
        Iterator<C2973l0> it2 = list.iterator();
        while (it2.hasNext()) {
            C2973l0 next = it2.next();
            try {
                String strM15147s = next.m15147s();
                String str6 = next.m15148t().f13200d;
                if (strM15147s.equals("Video")) {
                    uriM14588c0 = m14588c0(next.m15144p());
                    String strM16528e0 = CLUtility.m16528e0(String.valueOf(next.m15144p()));
                    if (uriM14588c0 != null) {
                        Log.d("ChatUtility", "Already has video file; and not do copy");
                    } else {
                        Log.d("ChatUtility", "Need to Download File");
                    }
                    strM16464M0 = strM16528e0;
                    str2 = "Photo";
                    it = it2;
                } else {
                    if (!strM15147s.equals("Photo") || f12829f) {
                        if (!strM15147s.equals("File") && !f12829f) {
                            str2 = "Photo";
                            it = it2;
                            uriM14588c0 = null;
                            strM16464M0 = null;
                        }
                        String strM16556l0 = CLUtility.m16556l0(next.m15145q());
                        File file = new File(strM16556l0);
                        String path = FilenameUtils.getPath(strM16556l0);
                        String baseName = FilenameUtils.getBaseName(strM16556l0);
                        String extension = FilenameUtils.getExtension(strM16556l0);
                        int iM14581Y = m14581Y(baseName);
                        String str7 = strM16556l0;
                        File file2 = file;
                        if (iM14581Y > 0) {
                            str2 = "Photo";
                            String str8 = path + baseName + "(" + iM14581Y + ")." + extension;
                            str7 = str8;
                            file2 = new File(str8);
                            iM14581Y = iM14581Y;
                        } else {
                            str2 = "Photo";
                        }
                        while (true) {
                            if (!file2.exists()) {
                                it = it2;
                                StringBuilder sb = new StringBuilder();
                                str3 = extension;
                                sb.append(file2.getPath());
                                sb.append(str5);
                                if (!new File(sb.toString()).exists()) {
                                    break;
                                }
                                str4 = str5;
                                arrayList2 = arrayList3;
                            } else {
                                str3 = extension;
                                str4 = str5;
                                arrayList2 = arrayList3;
                                it = it2;
                            }
                            String str9 = str2;
                            iM14581Y++;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(path);
                            sb2.append(baseName);
                            sb2.append("(");
                            sb2.append(iM14581Y);
                            sb2.append(").");
                            String str10 = str3;
                            sb2.append(str10);
                            String string = sb2.toString();
                            str2 = str9;
                            arrayList3 = arrayList2;
                            it2 = it;
                            file2 = new File(string);
                            str5 = str4;
                            str7 = string;
                            extension = str10;
                        }
                        new File(file2.getPath() + str5).createNewFile();
                        strM16464M0 = str7;
                    } else {
                        strM16464M0 = CLUtility.m16464M0();
                        str2 = "Photo";
                        it = it2;
                    }
                    uriM14588c0 = null;
                }
                if (uriM14588c0 != null) {
                    try {
                        DialogC6459g dialogC6459g2 = f12828e;
                        if (dialogC6459g2 != null) {
                            if (!dialogC6459g2.isShowing()) {
                                f12828e.show();
                            }
                            f12828e.m24775r(Integer.toString(f12825b + f12826c + 1), Integer.toString(size));
                            f12828e.m24762d(next);
                        }
                        Log.d("ChatUtility", "The file is exist, don't copy");
                        f12825b++;
                        arrayList3.add(uriM14588c0);
                        m14627w(activity, size, f12825b, f12826c, next, arrayList3, dVar);
                        str = str5;
                        arrayList = arrayList3;
                        z8 = true;
                    } catch (Exception e9) {
                        e = e9;
                        str = str5;
                        arrayList = arrayList3;
                        e.printStackTrace();
                        z8 = true;
                        f12826c++;
                        str5 = str;
                        arrayList3 = arrayList;
                        it2 = it;
                    }
                } else {
                    C3197a c3197a = new C3197a();
                    ArrayList arrayList4 = arrayList3;
                    str = str5;
                    arrayList = arrayList3;
                    String str11 = str2;
                    try {
                        a aVar = new a(strM15147s, arrayList4, activity, size, next, dVar);
                        if (str11.equals(strM15147s)) {
                            m14633z(activity, next, strM16464M0, aVar);
                        } else {
                            c3197a.m16994q(str6, C1199a.m5277a(next.m15148t().f13204h, next.m15148t().f13205i), strM16464M0, aVar);
                        }
                        z8 = true;
                    } catch (Exception e10) {
                        e = e10;
                        e.printStackTrace();
                        z8 = true;
                        f12826c++;
                        str5 = str;
                        arrayList3 = arrayList;
                        it2 = it;
                    }
                }
            } catch (Exception e11) {
                e = e11;
                str = str5;
                arrayList = arrayList3;
                it = it2;
            }
            str5 = str;
            arrayList3 = arrayList;
            it2 = it;
        }
    }

    /* renamed from: x */
    public static long m14629x(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1L;
        }
        try {
            calendar.setTime(simpleDateFormat.parse(str));
            long timeInMillis = calendar.getTimeInMillis();
            calendar.setTime(simpleDateFormat.parse(str2));
            return (calendar.getTimeInMillis() - timeInMillis) / DateUtils.MILLIS_PER_DAY;
        } catch (ParseException unused) {
            return -1L;
        }
    }

    /* renamed from: x0 */
    public static void m14630x0(Group group, C2973l0 c2973l0, String str) {
        m14559I0(group, m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.CreateMedia, m14552F(c2973l0, str), 0, null));
    }

    /* renamed from: y */
    public static void m14631y() {
        DialogC6459g dialogC6459g = f12828e;
        if (dialogC6459g == null || !dialogC6459g.isShowing()) {
            return;
        }
        f12828e.dismiss();
        f12828e = null;
    }

    /* renamed from: y0 */
    public static void m14632y0(Group group, String str, String str2, String str3) {
        m14559I0(group, m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.FileDelete, m14554G(str, str2, str3), 0, null));
    }

    /* renamed from: z */
    public static void m14633z(Context context, C2973l0 c2973l0, String str, C3197a.b bVar) {
        new b(c2973l0, context, bVar).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: z0 */
    public static void m14634z0(Group group, String str, String str2, String str3) {
        m14559I0(group, m14558I(String.valueOf(group.f13727n), MessageObj.MessageType.DeleteMedia, m14554G(str, str2, str3), 0, null));
    }
}
