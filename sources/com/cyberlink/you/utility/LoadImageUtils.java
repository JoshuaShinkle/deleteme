package com.cyberlink.you.utility;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.sticker.view.GifImageView;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.LoadImageUtils;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import p046d4.C4676a;
import p056e4.C4754a;
import p116k4.C5172p;
import p201t3.C6301o;
import p201t3.C6303q;
import p209u2.C6370g;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class LoadImageUtils {

    /* renamed from: a */
    public static final C6303q f14443a = Globals.m7388i0().m7529c1();

    /* renamed from: b */
    public static final Bitmap f14444b = null;

    /* renamed from: c */
    public static final Executor f14445c = Executors.newSingleThreadExecutor();

    public static class LoadBitmapTask extends AsyncTask<Object, Void, Bitmap> {

        /* renamed from: a */
        public String f14446a;

        /* renamed from: b */
        public WeakReference<ImageView> f14447b;

        /* renamed from: c */
        public boolean f14448c;

        public enum SrcType {
            Sticker
        }

        public LoadBitmapTask(String str, String str2, ImageView imageView, SrcType srcType, boolean z8, boolean z9) {
            this.f14446a = str2;
            this.f14447b = new WeakReference<>(imageView);
            this.f14448c = z9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Object... objArr) {
            Thread.currentThread().setName("LoadBitmapTask AsyncTask");
            if (this.f14446a == null || !new File(this.f14446a).exists()) {
                return null;
            }
            Bitmap bitmapM24128b = LoadImageUtils.f14443a.m24128b(this.f14446a);
            if (bitmapM24128b != null) {
                return bitmapM24128b;
            }
            return C4676a.m18676b(this.f14446a, !this.f14448c, -1);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            WeakReference<ImageView> weakReference;
            if (isCancelled()) {
                bitmap = null;
            }
            if (bitmap == null || (weakReference = this.f14447b) == null) {
                return;
            }
            ImageView imageView = weakReference.get();
            if (this == LoadImageUtils.m16631p(imageView)) {
                imageView.setImageBitmap(bitmap);
            }
            LoadImageUtils.f14443a.m24127a(this.f14446a, bitmap);
        }
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$a */
    public class C3140a implements C3197a.b {

        /* renamed from: a */
        public final /* synthetic */ String f14451a;

        /* renamed from: b */
        public final /* synthetic */ AsyncTaskC3147h f14452b;

        public C3140a(String str, AsyncTaskC3147h asyncTaskC3147h) {
            this.f14451a = str;
            this.f14452b = asyncTaskC3147h;
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: a */
        public void mo9122a() {
            Log.d("LoadImageUtils", "download fail");
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: b */
        public void mo9123b(String str) throws Throwable {
            Log.d("LoadImageUtils", "download success=" + str);
            CLUtility.m16474O2(str, this.f14451a);
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            this.f14452b.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: c */
        public void mo9124c(int i9, int i10, int i11) {
            Log.d("LoadImageUtils", "download progress=" + i9);
        }
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$b */
    public class AsyncTaskC3141b extends AsyncTask<Void, Void, Pair<Boolean, StickerPackObj>> {

        /* renamed from: a */
        public final /* synthetic */ StickerObj f14453a;

        /* renamed from: b */
        public final /* synthetic */ String f14454b;

        /* renamed from: c */
        public final /* synthetic */ String f14455c;

        /* renamed from: d */
        public final /* synthetic */ ImageView f14456d;

        /* renamed from: e */
        public final /* synthetic */ AsyncTaskC3147h f14457e;

        public AsyncTaskC3141b(StickerObj stickerObj, String str, String str2, ImageView imageView, AsyncTaskC3147h asyncTaskC3147h) {
            this.f14453a = stickerObj;
            this.f14454b = str;
            this.f14455c = str2;
            this.f14456d = imageView;
            this.f14457e = asyncTaskC3147h;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Pair<Boolean, StickerPackObj> doInBackground(Void... voidArr) {
            StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(this.f14453a.m16284i());
            return Pair.create(Boolean.valueOf((stickerPackObjM15293k == null || !stickerPackObjM15293k.m14811o().equals(StickerPackObj.Status.BUILTIN)) ? true : LoadImageUtils.m16627l(this.f14454b, this.f14455c, String.valueOf(this.f14453a.m16284i()), String.valueOf(this.f14453a.m16285j()))), stickerPackObjM15293k);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Pair<Boolean, StickerPackObj> pair) {
            if (((Boolean) pair.first).booleanValue()) {
                LoadImageUtils.m16628m(this.f14453a.m16283h(), this.f14454b, this.f14455c, this.f14456d, this.f14457e);
            } else {
                this.f14457e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$c */
    public class AsyncTaskC3142c extends AsyncTask<Object, Void, ByteArrayOutputStream> {

        /* renamed from: a */
        public String f14458a;

        /* renamed from: b */
        public GifImageView f14459b;

        /* renamed from: c */
        public String f14460c;

        /* JADX WARN: Removed duplicated region for block: B:19:0x004d A[EXC_TOP_SPLITTER, LOOP:0: B:19:0x004d->B:14:0x0054, LOOP_START, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public ByteArrayOutputStream doInBackground(Object... objArr) throws IOException {
            InputStream inputStream;
            Thread.currentThread().setName("loadGif AsyncTask");
            this.f14458a = (String) objArr[0];
            this.f14459b = (GifImageView) objArr[1];
            this.f14460c = (String) objArr[2];
            try {
                inputStream = new URL(this.f14458a).openConnection().getInputStream();
            } catch (MalformedURLException e9) {
                Log.d("LoadImageUtils", Log.getStackTraceString(e9));
                inputStream = null;
                byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (inputStream != null) {
                }
                return byteArrayOutputStream;
            } catch (IOException e10) {
                Log.d("LoadImageUtils", Log.getStackTraceString(e10));
                inputStream = null;
                byte[] bArr2 = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                if (inputStream != null) {
                }
                return byteArrayOutputStream2;
            }
            byte[] bArr22 = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
            ByteArrayOutputStream byteArrayOutputStream22 = new ByteArrayOutputStream();
            if (inputStream != null) {
                while (true) {
                    try {
                        int i9 = inputStream.read(bArr22);
                        if (i9 == -1) {
                            break;
                        }
                        byteArrayOutputStream22.write(bArr22, 0, i9);
                    } catch (IOException e11) {
                        Log.d("LoadImageUtils", Log.getStackTraceString(e11));
                    }
                }
            }
            return byteArrayOutputStream22;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0036 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onPostExecute(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            FileOutputStream fileOutputStream;
            this.f14459b.setGifImage(byteArrayOutputStream);
            if (this.f14460c.isEmpty() || new File(this.f14460c).exists()) {
                return;
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(this.f14460c);
                try {
                    byteArrayOutputStream.writeTo(fileOutputStream);
                } catch (IOException e9) {
                    e = e9;
                    fileOutputStream2 = fileOutputStream;
                    Log.d("LoadImageUtils", Log.getStackTraceString(e));
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream == null) {
                    }
                }
            } catch (IOException e10) {
                e = e10;
            }
            if (fileOutputStream == null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e11) {
                    Log.d("LoadImageUtils", Log.getStackTraceString(e11));
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$d */
    public class C3143d implements C3197a.b {

        /* renamed from: a */
        public final /* synthetic */ boolean f14461a;

        /* renamed from: b */
        public final /* synthetic */ StickerObj f14462b;

        /* renamed from: c */
        public final /* synthetic */ LoadBitmapTask f14463c;

        public C3143d(boolean z8, StickerObj stickerObj, LoadBitmapTask loadBitmapTask) {
            this.f14461a = z8;
            this.f14462b = stickerObj;
            this.f14463c = loadBitmapTask;
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: a */
        public void mo9122a() {
            Log.d("LoadImageUtils", "download fail");
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: b */
        public void mo9123b(String str) {
            Log.d("LoadImageUtils", "download success");
            if (this.f14461a) {
                this.f14462b.m16294s(str);
            } else {
                this.f14462b.m16293r(str);
            }
            C2950b0.m14924w().m15281i(this.f14462b.m16285j(), this.f14462b);
            if (this.f14463c.getStatus().equals(AsyncTask.Status.RUNNING) || this.f14463c.getStatus().equals(AsyncTask.Status.FINISHED)) {
                return;
            }
            this.f14463c.execute(new Object[0]);
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: c */
        public void mo9124c(int i9, int i10, int i11) {
            Log.d("LoadImageUtils", "download progress=" + i9);
        }
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$f */
    public static class RunnableC3145f implements Runnable {

        /* renamed from: b */
        public Context f14467b;

        /* renamed from: c */
        public StickerObj f14468c;

        /* renamed from: d */
        public ImageView f14469d;

        public RunnableC3145f(Context context, StickerObj stickerObj, ImageView imageView) {
            this.f14467b = context;
            this.f14468c = stickerObj;
            this.f14469d = imageView;
        }

        @Override // java.lang.Runnable
        public void run() {
            StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(this.f14468c.m16284i());
            if (stickerPackObjM15293k != null) {
                LoadImageUtils.m16637v(this.f14467b, stickerPackObjM15293k, this.f14468c, this.f14469d);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("packId", String.valueOf(this.f14468c.m16284i())));
            FriendsClient friendsClient = new FriendsClient();
            Pair<String, String> pairM15731j = friendsClient.m15731j("sticker", "pack.info", arrayList);
            friendsClient.m15717U0();
            String str = (String) pairM15731j.first;
            if (!"200".equals(str)) {
                Log.d("LoadImageUtils", "statusCode = " + str);
                return;
            }
            JSONArray jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second);
            if (jSONArrayM20196r != null) {
                for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                    List<StickerPackObj> listM20177D = C5172p.m20177D(jSONArrayM20196r, false, false);
                    if (listM20177D != null && listM20177D.size() > 0) {
                        C2950b0.m14925x().m15289g(listM20177D);
                        LoadImageUtils.m16637v(this.f14467b, listM20177D.get(0), this.f14468c, this.f14469d);
                    }
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$g */
    public static class AsyncTaskC3146g extends AsyncTask<Object, Void, Bitmap> {

        /* renamed from: a */
        public String f14470a;

        /* renamed from: b */
        public String f14471b;

        /* renamed from: c */
        public WeakReference<ImageView> f14472c;

        public AsyncTaskC3146g(String str, ImageView imageView, String str2) {
            this.f14470a = str;
            this.f14472c = new WeakReference<>(imageView);
            this.f14471b = str2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Object... objArr) throws IOException {
            Thread.currentThread().setName("LoadNetworkStickerTask AsyncTask");
            Bitmap bitmapDecodeFile = (this.f14471b == null || !new File(this.f14471b).exists()) ? null : BitmapFactory.decodeFile(this.f14471b);
            if (bitmapDecodeFile == null) {
                try {
                    bitmapDecodeFile = BitmapFactory.decodeStream(new URL(this.f14470a).openConnection().getInputStream());
                } catch (IOException e9) {
                    e9.printStackTrace();
                } catch (OutOfMemoryError e10) {
                    e10.printStackTrace();
                } catch (MalformedURLException e11) {
                    e11.printStackTrace();
                }
            }
            if (this.f14471b != null && bitmapDecodeFile != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.f14471b);
                    bitmapDecodeFile.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (FileNotFoundException e12) {
                    e12.printStackTrace();
                } catch (IOException e13) {
                    e13.printStackTrace();
                } catch (Exception e14) {
                    e14.printStackTrace();
                }
            }
            return bitmapDecodeFile;
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }
            if (bitmap == null) {
                return;
            }
            WeakReference<ImageView> weakReference = this.f14472c;
            if (weakReference != null) {
                ImageView imageView = weakReference.get();
                if (this == LoadImageUtils.m16632q(imageView)) {
                    imageView.setImageBitmap(bitmap);
                }
            }
            LoadImageUtils.f14443a.m24127a(this.f14470a, bitmap);
        }
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$h */
    public static class AsyncTaskC3147h extends AsyncTask<Object, Void, Pair<Boolean, AnimationDrawable>> {

        /* renamed from: a */
        public StickerObj f14473a;

        /* renamed from: b */
        public String f14474b;

        /* renamed from: c */
        public String f14475c;

        /* renamed from: d */
        public WeakReference<ImageView> f14476d;

        /* renamed from: e */
        public boolean f14477e;

        /* renamed from: f */
        public int f14478f;

        public AsyncTaskC3147h(StickerObj stickerObj, String str, String str2, ImageView imageView, boolean z8) {
            this(stickerObj, str, str2, imageView, z8, 3);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Pair<Boolean, AnimationDrawable> doInBackground(Object... objArr) throws JSONException, IOException {
            Thread.currentThread().setName("loadAnimPNGTask AsyncTask");
            this.f14473a.m16290o();
            List<String> listM16277b = this.f14473a.m16277b();
            if (listM16277b == null || listM16277b.isEmpty()) {
                return Pair.create(Boolean.FALSE, null);
            }
            Context applicationContext = Globals.m7388i0().getApplicationContext();
            AnimationDrawable animationDrawable = new AnimationDrawable();
            boolean z8 = false;
            animationDrawable.setOneShot(false);
            for (String str : listM16277b) {
                try {
                    if (C4754a.m18874d(str)) {
                        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(this.f14474b + File.separator + str);
                        if (bitmapDecodeFile != null) {
                            animationDrawable.addFrame(new BitmapDrawable(applicationContext.getResources(), bitmapDecodeFile), this.f14473a.m16276a());
                            LoadImageUtils.f14443a.m24127a(this.f14474b + str, bitmapDecodeFile);
                        } else {
                            z8 = true;
                        }
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                } catch (OutOfMemoryError e10) {
                    e10.printStackTrace();
                }
            }
            return Pair.create(Boolean.valueOf(z8), animationDrawable);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Pair<Boolean, AnimationDrawable> pair) {
            WeakReference<ImageView> weakReference;
            AnimationDrawable animationDrawable = (AnimationDrawable) pair.second;
            if (animationDrawable == null || (weakReference = this.f14476d) == null) {
                return;
            }
            ImageView imageView = weakReference.get();
            if (this.f14478f <= 0 || !((Boolean) pair.first).booleanValue()) {
                if (this == LoadImageUtils.m16630o(imageView)) {
                    imageView.setImageDrawable(animationDrawable);
                    animationDrawable.start();
                    return;
                }
                return;
            }
            Context applicationContext = Globals.m7388i0().getApplicationContext();
            String strSubstring = this.f14474b.substring(0, r10.length() - 1);
            this.f14474b = strSubstring;
            LoadImageUtils.m16629n(applicationContext, this.f14473a, strSubstring, this.f14475c, imageView, this.f14477e, this.f14478f - 1);
        }

        public AsyncTaskC3147h(StickerObj stickerObj, String str, String str2, ImageView imageView, boolean z8, int i9) {
            this.f14473a = stickerObj;
            this.f14474b = str + File.separator;
            this.f14475c = str2;
            this.f14476d = new WeakReference<>(imageView);
            this.f14477e = z8;
            this.f14478f = i9;
        }
    }

    /* renamed from: j */
    public static boolean m16625j(String str, ImageView imageView) {
        long jLongValue;
        LoadBitmapTask loadBitmapTaskM16631p = m16631p(imageView);
        AsyncTaskC3146g asyncTaskC3146gM16632q = m16632q(imageView);
        AsyncTaskC3147h asyncTaskC3147hM16630o = m16630o(imageView);
        if (loadBitmapTaskM16631p != null) {
            String str2 = loadBitmapTaskM16631p.f14446a;
            if (str2 != null && str2.equals(str)) {
                return false;
            }
            Log.d("LoadImageUtils", "[cancelPotentialWork] previous LoadBitmapTask cancelled .... ");
            loadBitmapTaskM16631p.cancel(true);
        } else if (asyncTaskC3146gM16632q != null) {
            String str3 = asyncTaskC3146gM16632q.f14470a;
            if (str3 != null && str3.equals(str)) {
                return false;
            }
            Log.d("LoadImageUtils", "[cancelPotentialWork] previous LoadNetworkStickerTask cancelled .... ");
            asyncTaskC3146gM16632q.cancel(true);
        } else if (asyncTaskC3147hM16630o != null) {
            StickerObj stickerObj = asyncTaskC3147hM16630o.f14473a;
            try {
                jLongValue = Long.valueOf(str).longValue();
            } catch (Exception e9) {
                e9.printStackTrace();
                jLongValue = 0;
            }
            if (stickerObj != null && stickerObj.m16284i() == jLongValue) {
                return false;
            }
            Log.d("LoadImageUtils", "[cancelPotentialWork] previous loadAnimPNGTask cancelled .... ");
            asyncTaskC3147hM16630o.cancel(true);
        }
        return true;
    }

    /* renamed from: k */
    public static void m16626k(Context context, StickerObj stickerObj, ImageView imageView) {
        Log.d("LoadImageUtils", "[checkAndLoadSticker] start =======");
        f14445c.execute(new RunnableC3145f(context, stickerObj, imageView));
    }

    /* renamed from: l */
    public static boolean m16627l(String str, String str2, String str3, String str4) throws Throwable {
        FileOutputStream fileOutputStream;
        StringBuilder sb = new StringBuilder();
        sb.append("data");
        String str5 = File.separator;
        sb.append(str5);
        sb.append("StickerPack");
        sb.append(str5);
        sb.append(str3);
        sb.append(str5);
        sb.append(str4);
        String string = sb.toString();
        InputStream inputStream = null;
        try {
            AssetManager assets = Globals.m7388i0().getApplicationContext().getAssets();
            if (assets == null) {
                C6370g.m24480b(null);
                C6370g.m24480b(null);
                return false;
            }
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                CLUtility.m16447I(file);
            }
            if (file.exists()) {
                fileOutputStream = null;
            } else {
                InputStream inputStreamOpen = assets.open(string);
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        CLUtility.m16591u(inputStreamOpen, fileOutputStream);
                        fileOutputStream.flush();
                        inputStream = inputStreamOpen;
                    } catch (IOException unused) {
                        inputStream = inputStreamOpen;
                        C6370g.m24480b(inputStream);
                        C6370g.m24480b(fileOutputStream);
                        return true;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamOpen;
                        C6370g.m24480b(inputStream);
                        C6370g.m24480b(fileOutputStream);
                        throw th;
                    }
                } catch (IOException unused2) {
                    fileOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
            }
            try {
                CLUtility.m16474O2(str, str2);
                File file2 = new File(str);
                if (file2.exists()) {
                    file2.delete();
                }
                C6370g.m24480b(inputStream);
                C6370g.m24480b(fileOutputStream);
                return false;
            } catch (IOException unused3) {
                C6370g.m24480b(inputStream);
                C6370g.m24480b(fileOutputStream);
                return true;
            } catch (Throwable th3) {
                th = th3;
                C6370g.m24480b(inputStream);
                C6370g.m24480b(fileOutputStream);
                throw th;
            }
        } catch (IOException unused4) {
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    /* renamed from: m */
    public static void m16628m(String str, String str2, String str3, ImageView imageView, AsyncTaskC3147h asyncTaskC3147h) {
        new C3197a().m16996s(str, str2, new C3140a(str3, asyncTaskC3147h));
    }

    /* renamed from: n */
    public static void m16629n(Context context, StickerObj stickerObj, String str, String str2, ImageView imageView, boolean z8, int i9) {
        String str3 = str + ".tmp";
        AsyncTaskC3147h asyncTaskC3147h = new AsyncTaskC3147h(stickerObj, str, str2, imageView, z8, i9);
        C3144e c3144e = new C3144e(context.getResources(), f14444b, asyncTaskC3147h);
        if (imageView != null) {
            imageView.setImageDrawable(c3144e);
        }
        new AsyncTaskC3141b(stickerObj, str3, str2, imageView, asyncTaskC3147h).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: o */
    public static AsyncTaskC3147h m16630o(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof C3144e) {
            return ((C3144e) drawable).m16648a();
        }
        return null;
    }

    /* renamed from: p */
    public static LoadBitmapTask m16631p(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof C3144e) {
            return ((C3144e) drawable).m16649b();
        }
        return null;
    }

    /* renamed from: q */
    public static AsyncTaskC3146g m16632q(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof C3144e) {
            return ((C3144e) drawable).m16650c();
        }
        return null;
    }

    /* renamed from: r */
    public static /* synthetic */ void m16633r(StickerPackObj stickerPackObj, Context context, StickerObj stickerObj, ImageView imageView) {
        if (stickerPackObj != null) {
            if (stickerPackObj.m14805i().equals("AnimationPNG")) {
                m16634s(context, stickerObj, imageView, false);
            } else {
                m16638w(context, stickerObj, false, imageView, false);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8  */
    /* renamed from: s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m16634s(Context context, StickerObj stickerObj, ImageView imageView, boolean z8) {
        AnimationDrawable animationDrawable;
        boolean z9;
        Log.d("LoadImageUtils", "[loadAnimPNG] start =======");
        if (stickerObj == null) {
            return;
        }
        stickerObj.m16290o();
        String str = CLUtility.m16541h1(stickerObj.m16284i()) + File.separator;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        String str2 = str + stickerObj.m16285j();
        File file2 = new File(str2);
        if (!file2.exists() || !file2.isDirectory()) {
            m16629n(context, stickerObj, str2, str, imageView, z8, 3);
            return;
        }
        List<String> listM16277b = stickerObj.m16277b();
        if (listM16277b != null && !listM16277b.isEmpty()) {
            animationDrawable = new AnimationDrawable();
            animationDrawable.setOneShot(false);
            for (String str3 : listM16277b) {
                Bitmap bitmapM24128b = f14443a.m24128b(str2 + File.separator + str3);
                if (bitmapM24128b != null) {
                    animationDrawable.addFrame(new BitmapDrawable(context.getResources(), bitmapM24128b), stickerObj.m16276a());
                }
            }
            z9 = true;
            if (!z9) {
                imageView.setImageDrawable(animationDrawable);
                animationDrawable.start();
                return;
            } else {
                AsyncTaskC3147h asyncTaskC3147h = new AsyncTaskC3147h(stickerObj, str2, str, imageView, z8);
                imageView.setImageDrawable(new C3144e(context.getResources(), f14444b, asyncTaskC3147h));
                asyncTaskC3147h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
                return;
            }
        }
        animationDrawable = null;
        z9 = false;
        if (!z9) {
        }
    }

    /* renamed from: t */
    public static void m16635t(String str, GifImageView gifImageView, String str2) {
        Log.d("LoadImageUtils", "[loadGif] start =======");
        if (str == null) {
            Log.d("LoadImageUtils", "[loadGif] url is null");
        } else if (gifImageView == null) {
            Log.d("LoadImageUtils", "[loadGif] gifView is null");
        } else {
            new AsyncTaskC3142c().execute(str, gifImageView, str2);
        }
    }

    /* renamed from: u */
    public static void m16636u(Context context, String str, ImageView imageView, String str2) {
        try {
            Log.d("LoadImageUtils", "[loadNetworkSticker] start =======");
            Bitmap bitmapM24128b = f14443a.m24128b(str);
            if (bitmapM24128b != null) {
                imageView.setImageBitmap(bitmapM24128b);
                return;
            }
            if (!m16625j(str, imageView)) {
                Log.d("LoadImageUtils", "[loadNetworkSticker] work already in progress!!");
                return;
            }
            AsyncTaskC3146g asyncTaskC3146g = new AsyncTaskC3146g(str, imageView, str2);
            C3144e c3144e = new C3144e(context.getResources(), (Bitmap) null, asyncTaskC3146g);
            if (imageView != null) {
                imageView.setImageDrawable(c3144e);
            }
            asyncTaskC3146g.executeOnExecutor(C6385v.f21554b, new Object[0]);
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: v */
    public static void m16637v(final Context context, final StickerPackObj stickerPackObj, final StickerObj stickerObj, final ImageView imageView) {
        Activity activityM16479Q = CLUtility.m16479Q(imageView);
        if (activityM16479Q != null) {
            activityM16479Q.runOnUiThread(new Runnable() { // from class: k4.u
                @Override // java.lang.Runnable
                public final void run() {
                    LoadImageUtils.m16633r(stickerPackObj, context, stickerObj, imageView);
                }
            });
        }
    }

    /* renamed from: w */
    public static void m16638w(Context context, StickerObj stickerObj, boolean z8, ImageView imageView, boolean z9) {
        String str;
        Bitmap bitmapM24128b;
        Log.d("LoadImageUtils", "[loadSticker] start =======");
        if (stickerObj == null) {
            return;
        }
        String str2 = String.valueOf(stickerObj.m16284i()) + stickerObj.m16285j();
        if (z8) {
            str2 = str2 + "_thumbnail";
        }
        String str3 = str2;
        String strM16287l = z8 ? stickerObj.m16287l() : stickerObj.m16282g();
        C6303q c6303q = f14443a;
        Bitmap bitmapM24128b2 = c6303q.m24128b(strM16287l);
        if (bitmapM24128b2 != null) {
            imageView.setImageBitmap(bitmapM24128b2);
            return;
        }
        if (!strM16287l.equals("_") && (bitmapM24128b = c6303q.m24128b(str3)) != null) {
            imageView.setImageBitmap(bitmapM24128b);
            return;
        }
        File file = new File(strM16287l);
        if (!file.getPath().equals("_") && file.exists()) {
            LoadBitmapTask loadBitmapTask = new LoadBitmapTask(str3, strM16287l, imageView, LoadBitmapTask.SrcType.Sticker, false, true);
            imageView.setImageDrawable(new C3144e(context.getResources(), f14444b, loadBitmapTask));
            loadBitmapTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
            return;
        }
        File file2 = new File(CLUtility.m16541h1(stickerObj.m16284i()));
        if (!file2.exists()) {
            file2.mkdir();
        }
        if (z8) {
            str = file2 + File.separator + stickerObj.m16285j() + "_thumbnail";
        } else {
            str = file2 + File.separator + stickerObj.m16285j();
        }
        LoadBitmapTask loadBitmapTask2 = new LoadBitmapTask(str3, str, imageView, LoadBitmapTask.SrcType.Sticker, false, true);
        imageView.setImageDrawable(new C3144e(context.getResources(), f14444b, loadBitmapTask2));
        new C3197a().m16996s(z8 ? stickerObj.m16288m() : stickerObj.m16283h(), str, new C3143d(z8, stickerObj, loadBitmapTask2));
    }

    /* renamed from: x */
    public static void m16639x(Context context, StickerObj stickerObj, ImageView imageView) {
        m16636u(context, stickerObj.m16283h(), imageView, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0043  */
    /* renamed from: y */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m16640y(Context context, long j9, String str, ImageView imageView, boolean z8) {
        String str2;
        if (z8) {
            File file = new File(CLUtility.m16541h1(j9));
            if (file.exists()) {
                str2 = file + File.separator + "cover";
            } else if (file.mkdir()) {
                str2 = file + File.separator + "cover";
            } else {
                str2 = null;
            }
        }
        m16636u(context, str, imageView, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005f  */
    /* renamed from: z */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m16641z(Context context, StickerPackObj stickerPackObj, ImageView imageView, boolean z8, boolean z9) {
        String str;
        String str2;
        Log.d("LoadImageUtils", "[loadStickerPack] start =======");
        String str3 = stickerPackObj.m14812p().f13074b;
        if (z9) {
            str = "cover";
        } else {
            str3 = stickerPackObj.m14812p().f13076d;
            str = "thumbnail";
        }
        if (z8) {
            File file = new File(CLUtility.m16541h1(stickerPackObj.m14803g()));
            if (file.exists()) {
                str2 = file + File.separator + str;
            } else if (file.mkdir()) {
                str2 = file + File.separator + str;
            } else {
                str2 = null;
            }
        }
        m16636u(context, str3, imageView, str2);
    }

    /* renamed from: com.cyberlink.you.utility.LoadImageUtils$e */
    public static class C3144e extends BitmapDrawable {

        /* renamed from: a */
        public final WeakReference<LoadBitmapTask> f14464a;

        /* renamed from: b */
        public final WeakReference<AsyncTaskC3146g> f14465b;

        /* renamed from: c */
        public final WeakReference<AsyncTaskC3147h> f14466c;

        public C3144e(Resources resources, Bitmap bitmap, LoadBitmapTask loadBitmapTask) {
            super(resources, bitmap);
            this.f14464a = new WeakReference<>(loadBitmapTask);
            this.f14465b = null;
            this.f14466c = null;
        }

        /* renamed from: a */
        public AsyncTaskC3147h m16648a() {
            WeakReference<AsyncTaskC3147h> weakReference = this.f14466c;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        /* renamed from: b */
        public LoadBitmapTask m16649b() {
            WeakReference<LoadBitmapTask> weakReference = this.f14464a;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        /* renamed from: c */
        public AsyncTaskC3146g m16650c() {
            WeakReference<AsyncTaskC3146g> weakReference = this.f14465b;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        public C3144e(Resources resources, Bitmap bitmap, AsyncTaskC3146g asyncTaskC3146g) {
            super(resources, bitmap);
            this.f14464a = null;
            this.f14465b = new WeakReference<>(asyncTaskC3146g);
            this.f14466c = null;
        }

        public C3144e(Resources resources, Bitmap bitmap, AsyncTaskC3147h asyncTaskC3147h) {
            super(resources, bitmap);
            this.f14464a = null;
            this.f14465b = null;
            this.f14466c = new WeakReference<>(asyncTaskC3147h);
        }
    }
}
