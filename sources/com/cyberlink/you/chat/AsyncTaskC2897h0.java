package com.cyberlink.you.chat;

import android.os.AsyncTask;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p095i3.C5049b;

/* renamed from: com.cyberlink.you.chat.h0 */
/* loaded from: classes.dex */
public class AsyncTaskC2897h0 extends AsyncTask<Group, Void, Void> {

    /* renamed from: a */
    public boolean f12678a = true;

    /* renamed from: b */
    public List<a> f12679b = new ArrayList();

    /* renamed from: com.cyberlink.you.chat.h0$a */
    public interface a {
        void onComplete();
    }

    /* renamed from: a */
    public void m14365a(a aVar) {
        synchronized (this.f12679b) {
            this.f12679b.add(aVar);
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Group... groupArr) {
        Thread.currentThread().setName("QueryMessageTask");
        if (groupArr.length == 0) {
            return null;
        }
        this.f12678a = false;
        C5049b.m19733l(groupArr[0]);
        return null;
    }

    /* renamed from: c */
    public boolean m14367c() {
        return this.f12678a;
    }

    /* renamed from: d */
    public final void m14368d() {
        synchronized (this.f12679b) {
            Iterator<a> it = this.f12679b.iterator();
            while (it.hasNext()) {
                it.next().onComplete();
            }
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Void r12) {
        this.f12678a = true;
        m14368d();
    }

    /* renamed from: f */
    public void m14370f(a aVar) {
        synchronized (this.f12679b) {
            this.f12679b.remove(aVar);
        }
    }
}
