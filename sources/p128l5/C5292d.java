package p128l5;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.perfectcorp.utility.C4506a;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.commons.utility.Log;
import com.perfectcorp.ycl.network.NetworkManager;
import com.perfectcorp.ycl.p040bc.model.Message;
import com.perfectcorp.ycl.p040bc.model.network.NetworkMessage;
import com.perfectcorp.ycl.pages.live.C4604x;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import p087h5.C5024b;
import p097i5.C5053b;
import p117k5.C5197b;
import p138m5.C5327b;
import p147n5.C5368f;
import p147n5.C5369g;
import p147n5.InterfaceC5367e;

/* renamed from: l5.d */
/* loaded from: classes2.dex */
public class C5292d extends C5327b {

    /* renamed from: A */
    public static int f17920A;

    /* renamed from: w */
    public static final Executor f17921w = Executors.newFixedThreadPool(20);

    /* renamed from: x */
    public static final Executor f17922x = Executors.newFixedThreadPool(1);

    /* renamed from: y */
    public static int f17923y;

    /* renamed from: z */
    public static int f17924z;

    /* renamed from: b */
    public volatile boolean f17925b;

    /* renamed from: c */
    public volatile boolean f17926c;

    /* renamed from: d */
    public volatile boolean f17927d;

    /* renamed from: e */
    public volatile Message.MsgOffset f17928e;

    /* renamed from: f */
    public volatile Message.MsgOffset f17929f;

    /* renamed from: k */
    public final String f17934k;

    /* renamed from: l */
    public final String f17935l;

    /* renamed from: m */
    public final String f17936m;

    /* renamed from: n */
    public final boolean f17937n;

    /* renamed from: s */
    public String f17942s;

    /* renamed from: g */
    public volatile long f17930g = -1;

    /* renamed from: h */
    public volatile long f17931h = -1;

    /* renamed from: i */
    public volatile long f17932i = -1;

    /* renamed from: j */
    public final Handler f17933j = new Handler();

    /* renamed from: o */
    public final Queue<InterfaceC5367e> f17938o = new ArrayDeque();

    /* renamed from: p */
    public final SortedMap<Long, C5368f> f17939p = Collections.synchronizedSortedMap(new TreeMap());

    /* renamed from: q */
    public final SortedMap<Long, C5368f> f17940q = Collections.synchronizedSortedMap(new TreeMap());

    /* renamed from: r */
    public final SortedMap<Long, C5368f> f17941r = Collections.synchronizedSortedMap(new TreeMap());

    /* renamed from: t */
    public Runnable f17943t = new b();

    /* renamed from: u */
    public Runnable f17944u = new c();

    /* renamed from: v */
    public Runnable f17945v = new d();

    /* renamed from: l5.d$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f17946a;

        static {
            int[] iArr = new int[NetworkMessage.Priority.values().length];
            f17946a = iArr;
            try {
                iArr[NetworkMessage.Priority.LO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17946a[NetworkMessage.Priority.ME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17946a[NetworkMessage.Priority.HI.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: l5.d$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            C5292d c5292d = C5292d.this;
            c5292d.m20621Q(NetworkMessage.Priority.LO, c5292d.f17928e.f15984lo.longValue());
        }
    }

    /* renamed from: l5.d$c */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            C5292d c5292d = C5292d.this;
            c5292d.m20621Q(NetworkMessage.Priority.ME, c5292d.f17928e.f15985me.longValue());
        }
    }

    /* renamed from: l5.d$d */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            C5292d c5292d = C5292d.this;
            c5292d.m20621Q(NetworkMessage.Priority.HI, c5292d.f17928e.f15983hi.longValue());
        }
    }

    /* renamed from: l5.d$e */
    public class e implements FutureCallback<Message.JoinRoomResponse> {
        public e() {
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Message.JoinRoomResponse joinRoomResponse) throws IOException {
            C5292d.this.f17942s = joinRoomResponse.downloadUrl;
            C5292d.this.f17928e = joinRoomResponse.msgOffset;
            C5292d.this.f17929f = new Message.MsgOffset();
            C5292d.this.f17929f.f15984lo = C5292d.this.f17928e.f15984lo;
            C5292d.this.f17929f.f15985me = C5292d.this.f17928e.f15985me;
            C5292d.this.f17929f.f15983hi = C5292d.this.f17928e.f15983hi;
            C5292d c5292d = C5292d.this;
            c5292d.m20621Q(NetworkMessage.Priority.ME, c5292d.f17928e.f15985me.longValue());
            C4604x.m18359s().m18377r(joinRoomResponse.downloadArchiveUrl, C5292d.this.f17934k, C5292d.this.f17928e.f15985me.longValue());
            if (TextUtils.isEmpty(C5292d.this.f17936m)) {
                NetworkMessage.addAnonymousParticipantName(C5292d.this.f17934k);
            }
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            Log.m18147a("LivePollingMessenger", "initJoinRoomPolling fail");
        }
    }

    /* renamed from: l5.d$f */
    public class f extends AsyncTask<Object, Object, NetworkMessage.HeartBeatResponse> {

        /* renamed from: a */
        public final /* synthetic */ String f17951a;

        /* renamed from: l5.d$f$a */
        public class a extends PromisedTask<NetworkMessage.HeartBeatResponse, Object, NetworkMessage.HeartBeatResponse> {
            public a() {
            }

            @Override // com.perfectcorp.utility.PromisedTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public NetworkMessage.HeartBeatResponse doInBackground(NetworkMessage.HeartBeatResponse heartBeatResponse) {
                return heartBeatResponse;
            }
        }

        public f(String str) {
            this.f17951a = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m20635c(String str) {
            C5292d.this.m20627Z(str);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public NetworkMessage.HeartBeatResponse doInBackground(Object... objArr) {
            try {
                return (NetworkMessage.HeartBeatResponse) NetworkMessage.heartBeat(this.f17951a, 0).then(new a()).get();
            } catch (InterruptedException | CancellationException | ExecutionException e9) {
                if (e9 instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                Log.m18148b("LivePollingMessenger", "", e9);
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(NetworkMessage.HeartBeatResponse heartBeatResponse) {
            super.onPostExecute(heartBeatResponse);
            if (heartBeatResponse == null || !C5292d.this.f17927d) {
                return;
            }
            long jIntValue = heartBeatResponse.interval.intValue() * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
            Handler handler = C5292d.this.f17933j;
            final String str = this.f17951a;
            handler.postDelayed(new Runnable() { // from class: l5.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17969b.m20635c(str);
                }
            }, jIntValue);
        }
    }

    /* renamed from: l5.d$g */
    public class g extends AsyncTask<Object, Object, NetworkMessage.SendMessageResponse> {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC5367e f17954a;

        /* renamed from: b */
        public final /* synthetic */ SettableFuture f17955b;

        /* renamed from: l5.d$g$a */
        public class a extends PromisedTask<NetworkMessage.SendMessageResponse, Void, NetworkMessage.SendMessageResponse> {
            public a() {
            }

            @Override // com.perfectcorp.utility.PromisedTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public NetworkMessage.SendMessageResponse doInBackground(NetworkMessage.SendMessageResponse sendMessageResponse) {
                return sendMessageResponse;
            }
        }

        public g(InterfaceC5367e interfaceC5367e, SettableFuture settableFuture) {
            this.f17954a = interfaceC5367e;
            this.f17955b = settableFuture;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public NetworkMessage.SendMessageResponse doInBackground(Object... objArr) {
            try {
                return (NetworkMessage.SendMessageResponse) NetworkMessage.sendMsg(C5292d.this.f17934k, C5369g.typeId(this.f17954a.getClass()), this.f17954a).then(new a()).get();
            } catch (InterruptedException | CancellationException | ExecutionException e9) {
                if (e9 instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                Log.m18148b("LivePollingMessenger", "", e9);
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(NetworkMessage.SendMessageResponse sendMessageResponse) {
            super.onPostExecute(sendMessageResponse);
            if (sendMessageResponse != null) {
                this.f17955b.set(sendMessageResponse);
            }
        }
    }

    /* renamed from: l5.d$h */
    public class h extends AsyncTask<Object, Object, Message.JoinRoomResponse> {

        /* renamed from: a */
        public final /* synthetic */ String f17958a;

        /* renamed from: b */
        public final /* synthetic */ String f17959b;

        /* renamed from: c */
        public final /* synthetic */ SettableFuture f17960c;

        /* renamed from: l5.d$h$a */
        public class a extends PromisedTask<Message.JoinRoomResponse, Void, Message.JoinRoomResponse> {
            public a() {
            }

            @Override // com.perfectcorp.utility.PromisedTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Message.JoinRoomResponse doInBackground(Message.JoinRoomResponse joinRoomResponse) {
                C5292d.this.f17926c = true;
                C5292d.this.f17927d = true;
                C5292d.this.m20624W();
                h hVar = h.this;
                C5292d.this.m20627Z(hVar.f17959b);
                return joinRoomResponse;
            }

            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                super.onError(i9);
                Log.m18147a("LivePollingMessenger", "join room error : " + i9);
            }
        }

        public h(String str, String str2, SettableFuture settableFuture) {
            this.f17958a = str;
            this.f17959b = str2;
            this.f17960c = settableFuture;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Message.JoinRoomResponse doInBackground(Object... objArr) {
            try {
                return (Message.JoinRoomResponse) NetworkMessage.joinRoom(this.f17958a, C4509d.m18120b(C5292d.this.f17935l) ? C5292d.this.f17934k : C5292d.this.f17935l, C5292d.this.f17937n).then(new a()).get();
            } catch (InterruptedException | CancellationException | ExecutionException e9) {
                if (e9 instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                Log.m18148b("LivePollingMessenger", "", e9);
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Message.JoinRoomResponse joinRoomResponse) {
            super.onPostExecute(joinRoomResponse);
            if (joinRoomResponse != null) {
                this.f17960c.set(joinRoomResponse);
            }
        }
    }

    /* renamed from: l5.d$i */
    public class i implements FutureCallback<File> {

        /* renamed from: a */
        public final /* synthetic */ NetworkMessage.Priority f17963a;

        /* renamed from: b */
        public final /* synthetic */ long f17964b;

        /* renamed from: c */
        public final /* synthetic */ File f17965c;

        public i(NetworkMessage.Priority priority, long j9, File file) {
            this.f17963a = priority;
            this.f17964b = j9;
            this.f17965c = file;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(File file) throws IOException {
            C5292d.this.m20625X(this.f17963a, this.f17964b, file);
            int i9 = a.f17946a[this.f17963a.ordinal()];
            if (i9 == 1) {
                C5292d c5292d = C5292d.this;
                NetworkMessage.Priority priority = this.f17963a;
                Message.MsgOffset msgOffset = c5292d.f17928e;
                Long lValueOf = Long.valueOf(msgOffset.f15984lo.longValue() + 1);
                msgOffset.f15984lo = lValueOf;
                c5292d.m20621Q(priority, lValueOf.longValue());
                return;
            }
            if (i9 == 2) {
                C5292d c5292d2 = C5292d.this;
                NetworkMessage.Priority priority2 = this.f17963a;
                Message.MsgOffset msgOffset2 = c5292d2.f17928e;
                Long lValueOf2 = Long.valueOf(msgOffset2.f15985me.longValue() + 1);
                msgOffset2.f15985me = lValueOf2;
                c5292d2.m20621Q(priority2, lValueOf2.longValue());
                return;
            }
            if (i9 != 3) {
                return;
            }
            C5292d c5292d3 = C5292d.this;
            NetworkMessage.Priority priority3 = this.f17963a;
            Message.MsgOffset msgOffset3 = c5292d3.f17928e;
            Long lValueOf3 = Long.valueOf(msgOffset3.f15983hi.longValue() + 1);
            msgOffset3.f15983hi = lValueOf3;
            c5292d3.m20621Q(priority3, lValueOf3.longValue());
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            C5024b.m19593a(this.f17965c);
            if (C5292d.this.f17926c) {
                int i9 = a.f17946a[this.f17963a.ordinal()];
                if (i9 == 1) {
                    if (this.f17964b > C5292d.this.f17930g) {
                        C5292d.this.m20630b0(this.f17963a, this.f17964b);
                        return;
                    }
                    C5292d c5292d = C5292d.this;
                    NetworkMessage.Priority priority = this.f17963a;
                    Message.MsgOffset msgOffset = c5292d.f17928e;
                    Long lValueOf = Long.valueOf(msgOffset.f15984lo.longValue() + 1);
                    msgOffset.f15984lo = lValueOf;
                    c5292d.m20630b0(priority, lValueOf.longValue());
                    synchronized (C5292d.this.f17939p) {
                        int unused = C5292d.f17920A = 0;
                    }
                    return;
                }
                if (i9 == 2) {
                    if (this.f17964b > C5292d.this.f17931h) {
                        C5292d.this.m20630b0(this.f17963a, this.f17964b);
                        return;
                    }
                    C5292d c5292d2 = C5292d.this;
                    NetworkMessage.Priority priority2 = this.f17963a;
                    Message.MsgOffset msgOffset2 = c5292d2.f17928e;
                    Long lValueOf2 = Long.valueOf(msgOffset2.f15985me.longValue() + 1);
                    msgOffset2.f15985me = lValueOf2;
                    c5292d2.m20630b0(priority2, lValueOf2.longValue());
                    synchronized (C5292d.this.f17940q) {
                        int unused2 = C5292d.f17924z = 0;
                    }
                    return;
                }
                if (i9 != 3) {
                    return;
                }
                if (this.f17964b > C5292d.this.f17932i) {
                    C5292d.this.m20630b0(this.f17963a, this.f17964b);
                    return;
                }
                C5292d c5292d3 = C5292d.this;
                NetworkMessage.Priority priority3 = this.f17963a;
                Message.MsgOffset msgOffset3 = c5292d3.f17928e;
                Long lValueOf3 = Long.valueOf(msgOffset3.f15983hi.longValue() + 1);
                msgOffset3.f15983hi = lValueOf3;
                c5292d3.m20630b0(priority3, lValueOf3.longValue());
                synchronized (C5292d.this.f17941r) {
                    int unused3 = C5292d.f17923y = 0;
                }
            }
        }
    }

    /* renamed from: l5.d$j */
    public class j extends AsyncTask<Object, Object, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ File f17967a;

        public j(File file) {
            this.f17967a = file;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v7, types: [java.io.FileInputStream, java.io.InputStream, java.lang.Object] */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Object... objArr) throws Throwable {
            ?? fileInputStream;
            Boolean bool;
            System.nanoTime();
            String str = null;
            try {
                fileInputStream = new FileInputStream(this.f17967a);
                try {
                    try {
                        byte[] bArr = new byte[fileInputStream.available()];
                        fileInputStream.read(bArr);
                        String str2 = new String(bArr, "UTF-8");
                        C4506a.m18099b(fileInputStream);
                        str = str2;
                    } catch (IOException e9) {
                        e = e9;
                        Log.m18148b("LivePollingMessenger", "", e);
                        C4506a.m18099b(fileInputStream);
                        try {
                            C5292d.this.m20928g(C5368f.fromJson(str));
                            bool = Boolean.TRUE;
                            return bool;
                        } finally {
                            C5024b.m19593a(this.f17967a);
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    str = fileInputStream;
                    C4506a.m18099b(str);
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
                fileInputStream = 0;
            } catch (Throwable th2) {
                th = th2;
                C4506a.m18099b(str);
                throw th;
            }
            try {
                C5292d.this.m20928g(C5368f.fromJson(str));
                bool = Boolean.TRUE;
            } catch (InvalidObjectException e11) {
                Log.m18153g("LivePollingMessenger", "Ignoring " + e11.getMessage() + ": " + str);
                bool = Boolean.FALSE;
            }
            return bool;
        }
    }

    public C5292d(String str, String str2, String str3, boolean z8) {
        this.f17934k = str;
        this.f17935l = str2;
        this.f17936m = str3;
        this.f17937n = z8;
        m20622R();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T */
    public /* synthetic */ void m20602T(long j9) throws IOException {
        m20621Q(NetworkMessage.Priority.LO, j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U */
    public /* synthetic */ void m20603U(long j9) throws IOException {
        m20621Q(NetworkMessage.Priority.ME, j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V */
    public /* synthetic */ void m20604V(long j9) throws IOException {
        m20621Q(NetworkMessage.Priority.HI, j9);
    }

    /* renamed from: Q */
    public final void m20621Q(NetworkMessage.Priority priority, long j9) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(NetworkManager.m18157b());
        String str = File.separator;
        sb.append(str);
        sb.append("LivePollingMessenger");
        sb.append(str);
        sb.append(priority.getValue());
        sb.append(str);
        sb.append(j9);
        File file = new File(sb.toString());
        new File(file.getParent()).mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e9) {
            Log.m18148b("LivePollingMessenger", "", e9);
        }
        NetworkManager networkManager = NetworkManager.INSTANCE;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f17942s);
        String str2 = File.separator;
        sb2.append(str2);
        sb2.append(priority.getValue());
        sb2.append(str2);
        sb2.append(j9);
        C5053b.m19752a(networkManager.m18160a(new C5197b(URI.create(sb2.toString()), file), priority == NetworkMessage.Priority.HI ? NetworkManager.TaskPriority.HIGHEST_TASK_PRIORITY : NetworkManager.TaskPriority.LOWEST_TASK_PRIORITY), new i(priority, j9, file));
    }

    /* renamed from: R */
    public final void m20622R() {
        C5053b.m19752a(m20623S(this.f17934k, this.f17936m), new e());
    }

    /* renamed from: S */
    public final ListenableFuture<Message.JoinRoomResponse> m20623S(String str, String str2) {
        SettableFuture settableFutureCreate = SettableFuture.create();
        new h(str2, str, settableFutureCreate).executeOnExecutor(Executors.newFixedThreadPool(1), new Object[0]);
        return settableFutureCreate;
    }

    /* renamed from: W */
    public final void m20624W() {
        synchronized (this.f17938o) {
            while (true) {
                InterfaceC5367e interfaceC5367ePoll = this.f17938o.poll();
                if (interfaceC5367ePoll != null) {
                    m20628a0(interfaceC5367ePoll);
                } else {
                    this.f17925b = true;
                }
            }
        }
    }

    /* renamed from: X */
    public final void m20625X(NetworkMessage.Priority priority, long j9, File file) {
        new j(file).executeOnExecutor(f17921w, new Object[0]);
    }

    /* renamed from: Y */
    public ListenableFuture<NetworkMessage.SendMessageResponse> m20626Y(InterfaceC5367e interfaceC5367e) {
        C5369g.checkMessageType(interfaceC5367e.getClass());
        if (!this.f17925b) {
            synchronized (this.f17938o) {
                if (!this.f17925b) {
                    this.f17938o.add(interfaceC5367e);
                    return null;
                }
            }
        }
        return m20628a0(interfaceC5367e);
    }

    /* renamed from: Z */
    public final AsyncTask<?, ?, ?> m20627Z(String str) {
        return new f(str).executeOnExecutor(f17921w, new Object[0]);
    }

    /* renamed from: a0 */
    public final ListenableFuture<NetworkMessage.SendMessageResponse> m20628a0(InterfaceC5367e interfaceC5367e) {
        SettableFuture settableFutureCreate = SettableFuture.create();
        new g(interfaceC5367e, settableFutureCreate).executeOnExecutor(f17922x, new Object[0]);
        return settableFutureCreate;
    }

    @Override // p138m5.C5327b
    /* renamed from: b */
    public void mo20629b() {
        m20632d0();
        super.mo20629b();
    }

    /* renamed from: b0 */
    public final synchronized void m20630b0(NetworkMessage.Priority priority, final long j9) {
        int i9 = a.f17946a[priority.ordinal()];
        if (i9 == 1) {
            Runnable runnable = new Runnable() { // from class: l5.a
                @Override // java.lang.Runnable
                public final void run() throws IOException {
                    this.f17914b.m20602T(j9);
                }
            };
            this.f17943t = runnable;
            this.f17933j.postDelayed(runnable, 500L);
        } else if (i9 == 2) {
            Runnable runnable2 = new Runnable() { // from class: l5.b
                @Override // java.lang.Runnable
                public final void run() throws IOException {
                    this.f17916b.m20603U(j9);
                }
            };
            this.f17944u = runnable2;
            this.f17933j.postDelayed(runnable2, 500L);
        } else if (i9 == 3) {
            Runnable runnable3 = new Runnable() { // from class: l5.c
                @Override // java.lang.Runnable
                public final void run() throws IOException {
                    this.f17918b.m20604V(j9);
                }
            };
            this.f17945v = runnable3;
            this.f17933j.postDelayed(runnable3, 500L);
        }
    }

    /* renamed from: c0 */
    public synchronized void m20631c0() {
        this.f17927d = false;
    }

    /* renamed from: d0 */
    public final synchronized void m20632d0() {
        this.f17926c = false;
        this.f17933j.removeCallbacks(this.f17943t);
        this.f17933j.removeCallbacks(this.f17944u);
        this.f17933j.removeCallbacks(this.f17945v);
        this.f17933j.removeCallbacksAndMessages(null);
        this.f17939p.clear();
        this.f17940q.clear();
        this.f17941r.clear();
    }
}
