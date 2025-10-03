package com.cyberlink.you.database;

import android.content.SharedPreferences;
import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.ULogUtility;
import java.util.Objects;

/* renamed from: com.cyberlink.you.database.p0 */
/* loaded from: classes.dex */
public class C2981p0 extends Thread {

    /* renamed from: b */
    public final String f13225b = "isSearchMessageDone";

    /* renamed from: c */
    public int f13226c;

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int iM15244g;
        SharedPreferences sharedPreferences = Globals.m7388i0().getApplicationContext().getSharedPreferences("U", 0);
        if (sharedPreferences.getBoolean("isSearchMessageDone", false)) {
            Log.v("ParseSearchMessage", "already parse");
            return;
        }
        Thread.currentThread().setName("Parse Message");
        Thread.currentThread().setPriority(1);
        long jCurrentTimeMillis = System.currentTimeMillis();
        do {
            iM15244g = C2950b0.m14922u().m15244g();
            this.f13226c += iM15244g;
            Objects.requireNonNull(C2950b0.m14922u());
        } while (iM15244g == 500);
        ULogUtility.m16683s("ParseSearchMessage", "parse " + this.f13226c + " messages cost " + ((System.currentTimeMillis() - jCurrentTimeMillis) / 1000.0d) + " seconds");
        if (iM15244g >= 0) {
            sharedPreferences.edit().putBoolean("isSearchMessageDone", true).apply();
        }
    }
}
