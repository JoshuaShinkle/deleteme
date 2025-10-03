package p256z4;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;
import p006a5.C0035c;

@SuppressLint({"CommitPrefEdits"})
/* renamed from: z4.d */
/* loaded from: classes2.dex */
public class C6828d {

    /* renamed from: p */
    public static Integer f22686p = null;

    /* renamed from: q */
    public static Boolean f22687q = null;

    /* renamed from: r */
    public static boolean f22688r = true;

    /* renamed from: s */
    public static final Object f22689s = new Object();

    /* renamed from: a */
    public final Future<SharedPreferences> f22690a;

    /* renamed from: b */
    public final Future<SharedPreferences> f22691b;

    /* renamed from: c */
    public final Future<SharedPreferences> f22692c;

    /* renamed from: d */
    public final Future<SharedPreferences> f22693d;

    /* renamed from: j */
    public String f22699j;

    /* renamed from: k */
    public boolean f22700k;

    /* renamed from: l */
    public String f22701l;

    /* renamed from: m */
    public String f22702m;

    /* renamed from: n */
    public boolean f22703n;

    /* renamed from: o */
    public Boolean f22704o;

    /* renamed from: g */
    public final Object f22696g = new Object();

    /* renamed from: f */
    public JSONObject f22695f = null;

    /* renamed from: h */
    public Map<String, String> f22697h = null;

    /* renamed from: i */
    public boolean f22698i = false;

    /* renamed from: e */
    public final SharedPreferences.OnSharedPreferenceChangeListener f22694e = new a();

    /* renamed from: z4.d$a */
    public class a implements SharedPreferences.OnSharedPreferenceChangeListener {
        public a() {
        }

        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            synchronized (C6828d.f22689s) {
                C6828d.this.m25524x();
                boolean unused = C6828d.f22688r = false;
            }
        }
    }

    public C6828d(Future<SharedPreferences> future, Future<SharedPreferences> future2, Future<SharedPreferences> future3, Future<SharedPreferences> future4) {
        this.f22691b = future;
        this.f22690a = future2;
        this.f22692c = future3;
        this.f22693d = future4;
    }

    /* renamed from: F */
    public static void m25493F(SharedPreferences.Editor editor) {
        editor.apply();
    }

    /* renamed from: n */
    public static String m25497n(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("people_distinct_id", null);
    }

    /* renamed from: A */
    public void m25498A(String str) {
        try {
            SharedPreferences.Editor editorEdit = this.f22692c.get().edit();
            editorEdit.remove(str);
            m25493F(editorEdit);
        } catch (InterruptedException e9) {
            e9.printStackTrace();
        } catch (ExecutionException e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: B */
    public synchronized void m25499B(String str) {
        try {
            try {
                SharedPreferences.Editor editorEdit = this.f22693d.get().edit();
                editorEdit.putBoolean("has_launched_" + str, true);
                m25493F(editorEdit);
            } catch (ExecutionException e9) {
                C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't write internal Mixpanel shared preferences.", e9.getCause());
            }
        } catch (InterruptedException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't write internal Mixpanel shared preferences.", e10);
        }
    }

    /* renamed from: C */
    public synchronized void m25500C(String str) {
        try {
            try {
                SharedPreferences.Editor editorEdit = this.f22693d.get().edit();
                editorEdit.putBoolean(str, true);
                m25493F(editorEdit);
            } catch (ExecutionException e9) {
                C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't write internal Mixpanel shared preferences.", e9.getCause());
            }
        } catch (InterruptedException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't write internal Mixpanel from shared preferences.", e10);
        }
    }

    /* renamed from: D */
    public synchronized void m25501D(boolean z8, String str) {
        this.f22704o = Boolean.valueOf(z8);
        m25504H(str);
    }

    /* renamed from: E */
    public final void m25502E() {
        JSONObject jSONObject = this.f22695f;
        if (jSONObject == null) {
            C0035c.m141c("MixpanelAPI.PIdentity", "storeSuperProperties should not be called with uninitialized superPropertiesCache.");
            return;
        }
        String string = jSONObject.toString();
        C0035c.m147i("MixpanelAPI.PIdentity", "Storing Super Properties " + string);
        try {
            SharedPreferences.Editor editorEdit = this.f22690a.get().edit();
            editorEdit.putString("super_properties", string);
            m25493F(editorEdit);
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot store superProperties in shared preferences.", e9);
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot store superProperties in shared preferences.", e10.getCause());
        }
    }

    /* renamed from: G */
    public final void m25503G() {
        try {
            SharedPreferences.Editor editorEdit = this.f22690a.get().edit();
            editorEdit.putString("events_distinct_id", this.f22699j);
            editorEdit.putBoolean("events_user_id_present", this.f22700k);
            editorEdit.putString("people_distinct_id", this.f22701l);
            editorEdit.putString("anonymous_id", this.f22702m);
            editorEdit.putBoolean("had_persisted_distinct_id", this.f22703n);
            m25493F(editorEdit);
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Can't write distinct ids to shared preferences.", e9);
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Can't write distinct ids to shared preferences.", e10.getCause());
        }
    }

    /* renamed from: H */
    public final void m25504H(String str) {
        try {
            SharedPreferences.Editor editorEdit = this.f22693d.get().edit();
            editorEdit.putBoolean("opt_out_" + str, this.f22704o.booleanValue());
            m25493F(editorEdit);
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Can't write opt-out shared preferences.", e9);
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Can't write opt-out shared preferences.", e10.getCause());
        }
    }

    /* renamed from: d */
    public void m25505d(JSONObject jSONObject) {
        synchronized (this.f22696g) {
            JSONObject jSONObjectM25516p = m25516p();
            Iterator<String> itKeys = jSONObjectM25516p.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    jSONObject.put(next, jSONObjectM25516p.get(next));
                } catch (JSONException e9) {
                    C0035c.m142d("MixpanelAPI.PIdentity", "Object read from one JSON Object cannot be written to another", e9);
                }
            }
        }
    }

    /* renamed from: e */
    public synchronized void m25506e() {
        try {
            SharedPreferences.Editor editorEdit = this.f22690a.get().edit();
            editorEdit.clear();
            m25493F(editorEdit);
            m25525y();
            m25522v();
        } catch (InterruptedException e9) {
            throw new RuntimeException(e9.getCause());
        } catch (ExecutionException e10) {
            throw new RuntimeException(e10.getCause());
        }
    }

    /* renamed from: f */
    public void m25507f() {
        synchronized (f22689s) {
            try {
                try {
                    SharedPreferences.Editor editorEdit = this.f22691b.get().edit();
                    editorEdit.clear();
                    m25493F(editorEdit);
                } catch (ExecutionException e9) {
                    C0035c.m142d("MixpanelAPI.PIdentity", "Cannot load referrer properties from shared preferences.", e9.getCause());
                }
            } catch (InterruptedException e10) {
                C0035c.m142d("MixpanelAPI.PIdentity", "Cannot load referrer properties from shared preferences.", e10);
            }
        }
    }

    /* renamed from: g */
    public void m25508g() {
        try {
            SharedPreferences.Editor editorEdit = this.f22692c.get().edit();
            editorEdit.clear();
            m25493F(editorEdit);
        } catch (InterruptedException e9) {
            e9.printStackTrace();
        } catch (ExecutionException e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: h */
    public synchronized String m25509h() {
        if (!this.f22698i) {
            m25522v();
        }
        return this.f22702m;
    }

    /* renamed from: i */
    public synchronized String m25510i() {
        if (!this.f22698i) {
            m25522v();
        }
        return this.f22699j;
    }

    /* renamed from: j */
    public synchronized String m25511j() {
        if (!this.f22698i) {
            m25522v();
        }
        if (!this.f22700k) {
            return null;
        }
        return this.f22699j;
    }

    /* renamed from: k */
    public synchronized boolean m25512k() {
        if (!this.f22698i) {
            m25522v();
        }
        return this.f22703n;
    }

    /* renamed from: l */
    public synchronized boolean m25513l(String str) {
        if (this.f22704o == null) {
            m25523w(str);
        }
        return this.f22704o.booleanValue();
    }

    /* renamed from: m */
    public synchronized String m25514m() {
        if (!this.f22698i) {
            m25522v();
        }
        return this.f22701l;
    }

    /* renamed from: o */
    public Map<String, String> m25515o() {
        synchronized (f22689s) {
            if (f22688r || this.f22697h == null) {
                m25524x();
                f22688r = false;
            }
        }
        return this.f22697h;
    }

    /* renamed from: p */
    public final JSONObject m25516p() {
        if (this.f22695f == null) {
            m25525y();
        }
        return this.f22695f;
    }

    /* renamed from: q */
    public Map<String, Long> m25517q() {
        HashMap map = new HashMap();
        try {
            for (Map.Entry<String, ?> entry : this.f22692c.get().getAll().entrySet()) {
                map.put(entry.getKey(), Long.valueOf(entry.getValue().toString()));
            }
        } catch (InterruptedException e9) {
            e9.printStackTrace();
        } catch (ExecutionException e10) {
            e10.printStackTrace();
        }
        return map;
    }

    /* renamed from: r */
    public boolean m25518r(String str) throws ExecutionException, InterruptedException {
        try {
            return this.f22693d.get().contains("opt_out_" + str);
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Can't read opt-out shared preferences.", e9);
            return false;
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Can't read opt-out shared preferences.", e10.getCause());
            return false;
        }
    }

    /* renamed from: s */
    public synchronized boolean m25519s(String str) {
        boolean z8;
        z8 = false;
        try {
            z8 = this.f22693d.get().getBoolean(str, false);
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't read internal Mixpanel from shared preferences.", e9);
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't read internal Mixpanel shared preferences.", e10.getCause());
        }
        return z8;
    }

    /* renamed from: t */
    public synchronized boolean m25520t(boolean z8, String str) {
        if (f22687q == null) {
            try {
                if (this.f22693d.get().getBoolean("has_launched_" + str, false)) {
                    f22687q = Boolean.FALSE;
                } else {
                    Boolean boolValueOf = Boolean.valueOf(z8 ? false : true);
                    f22687q = boolValueOf;
                    if (!boolValueOf.booleanValue()) {
                        m25499B(str);
                    }
                }
            } catch (InterruptedException unused) {
                f22687q = Boolean.FALSE;
            } catch (ExecutionException unused2) {
                f22687q = Boolean.FALSE;
            }
        }
        return f22687q.booleanValue();
    }

    /* renamed from: u */
    public synchronized boolean m25521u(String str) {
        if (str == null) {
            return false;
        }
        Integer numValueOf = Integer.valueOf(str);
        try {
            try {
                if (f22686p == null) {
                    Integer numValueOf2 = Integer.valueOf(this.f22693d.get().getInt("latest_version_code", -1));
                    f22686p = numValueOf2;
                    if (numValueOf2.intValue() == -1) {
                        f22686p = numValueOf;
                        SharedPreferences.Editor editorEdit = this.f22693d.get().edit();
                        editorEdit.putInt("latest_version_code", numValueOf.intValue());
                        m25493F(editorEdit);
                    }
                }
                if (f22686p.intValue() < numValueOf.intValue()) {
                    SharedPreferences.Editor editorEdit2 = this.f22693d.get().edit();
                    editorEdit2.putInt("latest_version_code", numValueOf.intValue());
                    m25493F(editorEdit2);
                    return true;
                }
            } catch (InterruptedException e9) {
                C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't write internal Mixpanel from shared preferences.", e9);
            }
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Couldn't write internal Mixpanel shared preferences.", e10.getCause());
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001f  */
    /* renamed from: v */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m25522v() throws ExecutionException, InterruptedException {
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = this.f22690a.get();
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot read distinct ids from sharedPreferences.", e9);
            sharedPreferences = null;
            if (sharedPreferences == null) {
            }
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot read distinct ids from sharedPreferences.", e10.getCause());
            sharedPreferences = null;
            if (sharedPreferences == null) {
            }
        }
        if (sharedPreferences == null) {
            return;
        }
        this.f22699j = sharedPreferences.getString("events_distinct_id", null);
        this.f22700k = sharedPreferences.getBoolean("events_user_id_present", false);
        this.f22701l = sharedPreferences.getString("people_distinct_id", null);
        this.f22702m = sharedPreferences.getString("anonymous_id", null);
        this.f22703n = sharedPreferences.getBoolean("had_persisted_distinct_id", false);
        if (this.f22699j == null) {
            this.f22702m = UUID.randomUUID().toString();
            this.f22699j = "$device:" + this.f22702m;
            this.f22700k = false;
            m25503G();
        }
        this.f22698i = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001e  */
    /* renamed from: w */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m25523w(String str) throws ExecutionException, InterruptedException {
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = this.f22693d.get();
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot read opt out flag from sharedPreferences.", e9);
            sharedPreferences = null;
            if (sharedPreferences == null) {
            }
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot read opt out flag from sharedPreferences.", e10.getCause());
            sharedPreferences = null;
            if (sharedPreferences == null) {
            }
        }
        if (sharedPreferences == null) {
            return;
        }
        this.f22704o = Boolean.valueOf(sharedPreferences.getBoolean("opt_out_" + str, false));
    }

    /* renamed from: x */
    public final void m25524x() throws ExecutionException, InterruptedException {
        this.f22697h = new HashMap();
        try {
            SharedPreferences sharedPreferences = this.f22691b.get();
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this.f22694e);
            sharedPreferences.registerOnSharedPreferenceChangeListener(this.f22694e);
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                this.f22697h.put(entry.getKey(), entry.getValue().toString());
            }
        } catch (InterruptedException e9) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot load referrer properties from shared preferences.", e9);
        } catch (ExecutionException e10) {
            C0035c.m142d("MixpanelAPI.PIdentity", "Cannot load referrer properties from shared preferences.", e10.getCause());
        }
    }

    /* renamed from: y */
    public final void m25525y() {
        JSONObject jSONObject;
        try {
            try {
                try {
                    try {
                        String string = this.f22690a.get().getString("super_properties", "{}");
                        C0035c.m147i("MixpanelAPI.PIdentity", "Loading Super Properties " + string);
                        this.f22695f = new JSONObject(string);
                    } catch (ExecutionException e9) {
                        C0035c.m142d("MixpanelAPI.PIdentity", "Cannot load superProperties from SharedPreferences.", e9.getCause());
                        if (this.f22695f == null) {
                            jSONObject = new JSONObject();
                            this.f22695f = jSONObject;
                        }
                    }
                } catch (JSONException unused) {
                    C0035c.m141c("MixpanelAPI.PIdentity", "Cannot parse stored superProperties");
                    m25502E();
                    if (this.f22695f == null) {
                        jSONObject = new JSONObject();
                        this.f22695f = jSONObject;
                    }
                }
            } catch (InterruptedException e10) {
                C0035c.m142d("MixpanelAPI.PIdentity", "Cannot load superProperties from SharedPreferences.", e10);
                if (this.f22695f == null) {
                    jSONObject = new JSONObject();
                    this.f22695f = jSONObject;
                }
            }
        } catch (Throwable th) {
            if (this.f22695f == null) {
                this.f22695f = new JSONObject();
            }
            throw th;
        }
    }

    /* renamed from: z */
    public void m25526z(JSONObject jSONObject) {
        synchronized (this.f22696g) {
            JSONObject jSONObjectM25516p = m25516p();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    jSONObjectM25516p.put(next, jSONObject.get(next));
                } catch (JSONException e9) {
                    C0035c.m142d("MixpanelAPI.PIdentity", "Exception registering super property.", e9);
                }
            }
            m25502E();
        }
    }
}
