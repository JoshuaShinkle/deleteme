package p112k0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* renamed from: k0.a */
/* loaded from: classes.dex */
public final class C5111a {

    /* renamed from: f */
    public static final Object f17576f = new Object();

    /* renamed from: g */
    public static C5111a f17577g;

    /* renamed from: a */
    public final Context f17578a;

    /* renamed from: b */
    public final HashMap<BroadcastReceiver, ArrayList<c>> f17579b = new HashMap<>();

    /* renamed from: c */
    public final HashMap<String, ArrayList<c>> f17580c = new HashMap<>();

    /* renamed from: d */
    public final ArrayList<b> f17581d = new ArrayList<>();

    /* renamed from: e */
    public final Handler f17582e;

    /* renamed from: k0.a$a */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                super.handleMessage(message);
            } else {
                C5111a.this.m19961a();
            }
        }
    }

    /* renamed from: k0.a$b */
    public static final class b {

        /* renamed from: a */
        public final Intent f17584a;

        /* renamed from: b */
        public final ArrayList<c> f17585b;

        public b(Intent intent, ArrayList<c> arrayList) {
            this.f17584a = intent;
            this.f17585b = arrayList;
        }
    }

    /* renamed from: k0.a$c */
    public static final class c {

        /* renamed from: a */
        public final IntentFilter f17586a;

        /* renamed from: b */
        public final BroadcastReceiver f17587b;

        /* renamed from: c */
        public boolean f17588c;

        /* renamed from: d */
        public boolean f17589d;

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f17587b);
            sb.append(" filter=");
            sb.append(this.f17586a);
            if (this.f17589d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public C5111a(Context context) {
        this.f17578a = context;
        this.f17582e = new a(context.getMainLooper());
    }

    /* renamed from: b */
    public static C5111a m19960b(Context context) {
        C5111a c5111a;
        synchronized (f17576f) {
            if (f17577g == null) {
                f17577g = new C5111a(context.getApplicationContext());
            }
            c5111a = f17577g;
        }
        return c5111a;
    }

    /* renamed from: a */
    public void m19961a() {
        int size;
        b[] bVarArr;
        while (true) {
            synchronized (this.f17579b) {
                size = this.f17581d.size();
                if (size <= 0) {
                    return;
                }
                bVarArr = new b[size];
                this.f17581d.toArray(bVarArr);
                this.f17581d.clear();
            }
            for (int i9 = 0; i9 < size; i9++) {
                b bVar = bVarArr[i9];
                int size2 = bVar.f17585b.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    c cVar = bVar.f17585b.get(i10);
                    if (!cVar.f17589d) {
                        cVar.f17587b.onReceive(this.f17578a, bVar.f17584a);
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public boolean m19962c(Intent intent) {
        int i9;
        String str;
        ArrayList arrayList;
        ArrayList<c> arrayList2;
        String str2;
        synchronized (this.f17579b) {
            String action = intent.getAction();
            String strResolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.f17578a.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z8 = (intent.getFlags() & 8) != 0;
            if (z8) {
                Log.v("LocalBroadcastManager", "Resolving type " + strResolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<c> arrayList3 = this.f17580c.get(intent.getAction());
            if (arrayList3 != null) {
                if (z8) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i10 = 0;
                while (i10 < arrayList3.size()) {
                    c cVar = arrayList3.get(i10);
                    if (z8) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + cVar.f17586a);
                    }
                    if (cVar.f17588c) {
                        if (z8) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                        }
                        i9 = i10;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = strResolveTypeIfNeeded;
                        arrayList = arrayList4;
                    } else {
                        i9 = i10;
                        str = action;
                        arrayList = arrayList4;
                        arrayList2 = arrayList3;
                        str2 = strResolveTypeIfNeeded;
                        int iMatch = cVar.f17586a.match(action, strResolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (iMatch >= 0) {
                            if (z8) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(iMatch));
                            }
                            arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList4.add(cVar);
                            cVar.f17588c = true;
                            i10 = i9 + 1;
                            action = str;
                            arrayList3 = arrayList2;
                            strResolveTypeIfNeeded = str2;
                        } else if (z8) {
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + (iMatch != -4 ? iMatch != -3 ? iMatch != -2 ? iMatch != -1 ? "unknown reason" : "type" : "data" : "action" : "category"));
                        }
                    }
                    arrayList4 = arrayList;
                    i10 = i9 + 1;
                    action = str;
                    arrayList3 = arrayList2;
                    strResolveTypeIfNeeded = str2;
                }
                ArrayList arrayList5 = arrayList4;
                if (arrayList5 != null) {
                    for (int i11 = 0; i11 < arrayList5.size(); i11++) {
                        ((c) arrayList5.get(i11)).f17588c = false;
                    }
                    this.f17581d.add(new b(intent, arrayList5));
                    if (!this.f17582e.hasMessages(1)) {
                        this.f17582e.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
