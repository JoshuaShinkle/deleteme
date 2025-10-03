package p054e2;

import android.content.Context;
import android.content.SharedPreferences;
import com.cyberlink.clsm.ISessionCacheStore;
import com.cyberlink.clsm.SmLog;
import java.io.File;
import java.util.UUID;

/* renamed from: e2.d */
/* loaded from: classes.dex */
public class C4713d extends AbstractC4710a implements ISessionCacheStore {

    /* renamed from: b */
    public final SharedPreferences f16471b;

    public C4713d(Context context) {
        super(new File(context.getFilesDir(), "SessionCacheStore"));
        this.f16471b = context.getSharedPreferences("com.cyberlink.clsm.session_cached", 0);
    }

    @Override // p054e2.AbstractC4710a, com.cyberlink.clsm.ISessionCacheStore
    public void clear() {
        super.clear();
        synchronized (this.f16471b) {
            this.f16471b.edit().clear().apply();
        }
    }

    /* renamed from: d */
    public final String m18864d() {
        File file;
        do {
            file = new File(this.f16467a, UUID.randomUUID().toString());
        } while (file.exists());
        return file.getName();
    }

    /* renamed from: e */
    public final String m18865e(String str) {
        String string = this.f16471b.getString(str, null);
        if (string == null) {
            synchronized (this.f16471b) {
                string = this.f16471b.getString(str, null);
                if (string == null) {
                    string = m18864d();
                    this.f16471b.edit().putString(str, string).apply();
                }
            }
        }
        return string;
    }

    @Override // com.cyberlink.clsm.ISessionCacheStore
    public byte[] getChatInfo(String str) {
        try {
            return super.m18853a(str);
        } catch (Throwable th) {
            SmLog.m5268a(th);
            return null;
        }
    }

    @Override // com.cyberlink.clsm.ISessionCacheStore
    public byte[] getSessionKey(String str) {
        try {
            return super.m18853a(m18865e(str));
        } catch (Throwable th) {
            SmLog.m5268a(th);
            return null;
        }
    }

    @Override // com.cyberlink.clsm.ISessionCacheStore
    public boolean removeChatInfo(String str) {
        try {
            return super.m18854b(str);
        } catch (Throwable th) {
            SmLog.m5268a(th);
            return false;
        }
    }

    @Override // com.cyberlink.clsm.ISessionCacheStore
    public boolean removeSessionKey(String str) {
        try {
            return super.m18854b(m18865e(str));
        } catch (Throwable th) {
            SmLog.m5268a(th);
            return false;
        }
    }

    @Override // com.cyberlink.clsm.ISessionCacheStore
    public boolean setChatInfo(String str, byte[] bArr) {
        try {
            return super.m18855c(str, bArr);
        } catch (Throwable th) {
            SmLog.m5268a(th);
            return false;
        }
    }

    @Override // com.cyberlink.clsm.ISessionCacheStore
    public boolean setSessionKey(String str, byte[] bArr) {
        try {
            return super.m18855c(m18865e(str), bArr);
        } catch (Throwable th) {
            SmLog.m5268a(th);
            return false;
        }
    }
}
