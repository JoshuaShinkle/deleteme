package p054e2;

import android.text.TextUtils;
import com.cyberlink.clsm.C1206h;
import com.cyberlink.clsm.Device;
import com.cyberlink.clsm.IRemoteClient;
import com.cyberlink.clsm.SmLog;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.AbstractC5483a0;
import okhttp3.AbstractC5524y;
import okhttp3.C5521v;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p209u2.C6370g;

/* renamed from: e2.c */
/* loaded from: classes.dex */
public abstract class AbstractC4712c implements IRemoteClient {

    /* renamed from: d */
    public static final String f16468d = String.valueOf(200);

    /* renamed from: b */
    public final AtomicLong f16469b;

    /* renamed from: c */
    public final C5522w f16470c;

    public AbstractC4712c() {
        AtomicLong atomicLong = new AtomicLong(20L);
        this.f16469b = atomicLong;
        C5522w.a aVar = new C5522w.a();
        long j9 = atomicLong.get();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.f16470c = aVar.m21777c(j9, timeUnit).m21773H(atomicLong.get(), timeUnit).m21774I(atomicLong.get(), timeUnit).m21775a();
    }

    /* renamed from: d */
    public static boolean m18856d(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: g */
    public static Device m18857g(JSONObject jSONObject) {
        try {
            return new Device(Long.parseLong(jSONObject.getString("user")), jSONObject.getString("deviceId"), jSONObject.getString("publicKey"));
        } catch (JSONException e9) {
            SmLog.m5268a(e9);
            return null;
        }
    }

    /* renamed from: i */
    public static ArrayList<Device> m18858i(JSONObject jSONObject, AtomicReference<String> atomicReference) throws JSONException {
        if (jSONObject.has("pagingState")) {
            atomicReference.set(jSONObject.getString("pagingState"));
        } else {
            atomicReference.set(null);
        }
        ArrayList<Device> arrayList = new ArrayList<>();
        JSONArray jSONArray = jSONObject.getJSONArray("results");
        int i9 = jSONObject.getInt("returnSize");
        int length = jSONArray.length();
        if (i9 != length) {
            SmLog.m5270c("Size is not equivalent: " + i9 + " vs " + length);
        }
        for (int i10 = 0; i10 < length; i10++) {
            Device deviceM18857g = m18857g(jSONArray.getJSONObject(i10));
            if (deviceM18857g != null) {
                arrayList.add(deviceM18857g);
            }
        }
        return arrayList;
    }

    /* renamed from: j */
    public static ArrayList<Device> m18859j(String str, AtomicReference<String> atomicReference) {
        try {
            return m18858i(new JSONObject(str), atomicReference);
        } catch (JSONException e9) {
            SmLog.m5268a(e9);
            return null;
        }
    }

    @Override // com.cyberlink.clsm.IRemoteClient
    /* renamed from: b */
    public ArrayList<Device> mo5235b(Collection<Long> collection) throws Throwable {
        String strM5281d = C1206h.m5278a().m5281d();
        if (collection == null || collection.isEmpty() || m18856d(strM5281d)) {
            return null;
        }
        ArrayList<Device> arrayList = new ArrayList<>();
        AtomicReference<String> atomicReference = new AtomicReference<>();
        do {
            C5521v.a aVarM21720a = new C5521v.a().m21724e(C5521v.f18974l).m21720a("token", strM5281d).m21720a("pageSize", f16468d);
            Iterator<Long> it = collection.iterator();
            while (it.hasNext()) {
                aVarM21720a.m21720a("userIds", String.valueOf(it.next()));
            }
            if (atomicReference.get() != null) {
                aVarM21720a.m21720a("pagingState", atomicReference.get());
            }
            ArrayList<Device> arrayListM18863k = m18863k(aVarM21720a.m21723d(), atomicReference);
            if (arrayListM18863k == null) {
                return null;
            }
            arrayList.addAll(arrayListM18863k);
        } while (atomicReference.get() != null);
        return arrayList;
    }

    /* renamed from: c */
    public final void m18860c(long j9) {
        this.f16469b.compareAndSet(j9, Math.min(10 + j9, 90L));
    }

    /* renamed from: e */
    public final C5522w m18861e(long j9) {
        C5522w.a aVarM21764y = this.f16470c.m21764y();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return aVarM21764y.m21777c(j9, timeUnit).m21773H(j9, timeUnit).m21774I(j9, timeUnit).m21775a();
    }

    /* renamed from: f */
    public final boolean m18862f(Exception exc, long j9) {
        if (!(exc instanceof SocketTimeoutException)) {
            return true;
        }
        m18860c(j9);
        SmLog.m5268a(new IOException("Timeout: " + j9 + ", " + exc.getMessage(), exc));
        return false;
    }

    /* renamed from: k */
    public final ArrayList<Device> m18863k(AbstractC5524y abstractC5524y, AtomicReference<String> atomicReference) throws Throwable {
        C5525z c5525zExecute;
        String strM5283f = C1206h.m5278a().m5283f();
        C5523x c5523x = null;
        if (m18856d(strM5283f)) {
            return null;
        }
        C5523x c5523xM21813b = new C5523x.a().m21820i(strM5283f).m21818g(abstractC5524y).m21813b();
        long j9 = this.f16469b.get();
        try {
            try {
                c5525zExecute = m18861e(j9).mo21256a(c5523xM21813b).execute();
                try {
                    if (!c5525zExecute.m21840D()) {
                        throw new IOException("HttpClient called unsuccessfully: " + c5525zExecute);
                    }
                    AbstractC5483a0 abstractC5483a0M21849f = c5525zExecute.m21849f();
                    if (abstractC5483a0M21849f == null) {
                        throw new IOException("HttpClient response empty body");
                    }
                    ArrayList<Device> arrayListM18859j = m18859j(abstractC5483a0M21849f.m21231y(), atomicReference);
                    C6370g.m24480b(c5525zExecute);
                    return arrayListM18859j;
                } catch (IOException e9) {
                    e = e9;
                    if (m18862f(e, j9)) {
                        SmLog.m5268a(e);
                    }
                    C6370g.m24480b(c5525zExecute);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                c5523x = c5523xM21813b;
                C6370g.m24480b(c5523x);
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            c5525zExecute = null;
        } catch (Throwable th2) {
            th = th2;
            C6370g.m24480b(c5523x);
            throw th;
        }
    }

    @Override // com.cyberlink.clsm.IRemoteClient
    public ArrayList<Device> queryDeviceList(String str) throws Throwable {
        String strM5281d = C1206h.m5278a().m5281d();
        if (m18856d(str, strM5281d)) {
            return null;
        }
        ArrayList<Device> arrayList = new ArrayList<>();
        AtomicReference<String> atomicReference = new AtomicReference<>();
        do {
            C5521v.a aVarM21720a = new C5521v.a().m21724e(C5521v.f18974l).m21720a("token", strM5281d).m21720a("groupIds", str).m21720a("pageSize", f16468d);
            if (atomicReference.get() != null) {
                aVarM21720a.m21720a("pagingState", atomicReference.get());
            }
            ArrayList<Device> arrayListM18863k = m18863k(aVarM21720a.m21723d(), atomicReference);
            if (arrayListM18863k == null) {
                return null;
            }
            arrayList.addAll(arrayListM18863k);
        } while (atomicReference.get() != null);
        return arrayList;
    }

    @Override // com.cyberlink.clsm.IRemoteClient
    public boolean register(String str, String str2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String strM5281d = C1206h.m5278a().m5281d();
        String strM5284g = C1206h.m5278a().m5284g();
        boolean zM5280c = C1206h.m5278a().m5280c();
        if (m18856d(str, str2, strM5281d, strM5284g)) {
            return false;
        }
        C5523x c5523xM21813b = new C5523x.a().m21820i(strM5284g).m21818g(new C5521v.a().m21724e(C5521v.f18974l).m21720a("token", strM5281d).m21720a("deviceType", zM5280c ? "1" : "0").m21720a("deviceId", str).m21720a("publicKey", str2).m21723d()).m21813b();
        long j9 = this.f16469b.get();
        C5525z c5525zExecute = null;
        try {
            c5525zExecute = m18861e(j9).mo21256a(c5523xM21813b).execute();
            if (c5525zExecute.m21840D()) {
                return true;
            }
            throw new IOException("HttpClient called unsuccessfully: " + c5525zExecute);
        } catch (IOException e9) {
            if (m18862f(e9, j9)) {
                SmLog.m5268a(e9);
            }
            return false;
        } finally {
            C6370g.m24480b(c5525zExecute);
        }
    }
}
