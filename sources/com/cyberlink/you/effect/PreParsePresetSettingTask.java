package com.cyberlink.you.effect;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import com.cyberlink.you.Globals;
import com.cyberlink.you.effect.C3008b;
import com.cyberlink.you.widgetpool.panel.coloreffectpanel.ColorEffectUtility;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p209u2.ThreadFactoryC6373j;

@SuppressLint({"UseSparseArrays"})
/* loaded from: classes.dex */
public class PreParsePresetSettingTask {

    /* renamed from: g */
    public static final int f13270g;

    /* renamed from: h */
    public static final int f13271h;

    /* renamed from: i */
    public static final int f13272i;

    /* renamed from: j */
    public static final BlockingQueue<Runnable> f13273j;

    /* renamed from: k */
    public static Executor f13274k;

    /* renamed from: a */
    public C3008b f13275a;

    /* renamed from: b */
    public InterfaceC3004c f13276b;

    /* renamed from: c */
    public Map<Integer, AsyncTaskC3005d> f13277c;

    /* renamed from: d */
    public Map<Long, AsyncTaskC3005d> f13278d;

    /* renamed from: e */
    public Map<Integer, AsyncTaskC3005d> f13279e;

    /* renamed from: f */
    public Map<Integer, AsyncTaskC3005d> f13280f;

    public enum EffectApplicationMode {
        Live,
        Capture,
        Edit,
        LiveEdit,
        CaptureEdit
    }

    public enum PresetContentType {
        Sample,
        Download
    }

    /* renamed from: com.cyberlink.you.effect.PreParsePresetSettingTask$a */
    public static /* synthetic */ class C3002a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f13290a;

        static {
            int[] iArr = new int[EffectApplicationMode.values().length];
            f13290a = iArr;
            try {
                iArr[EffectApplicationMode.Edit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13290a[EffectApplicationMode.Live.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13290a[EffectApplicationMode.Capture.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13290a[EffectApplicationMode.LiveEdit.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13290a[EffectApplicationMode.CaptureEdit.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.effect.PreParsePresetSettingTask$b */
    public interface InterfaceC3003b {
    }

    /* renamed from: com.cyberlink.you.effect.PreParsePresetSettingTask$c */
    public interface InterfaceC3004c {
        void onComplete();
    }

    /* renamed from: com.cyberlink.you.effect.PreParsePresetSettingTask$d */
    public class AsyncTaskC3005d extends AsyncTask<String, Integer, C3008b.d> {

        /* renamed from: a */
        public int f13291a;

        /* renamed from: b */
        public long f13292b;

        /* renamed from: c */
        public String f13293c;

        /* renamed from: d */
        public PresetContentType f13294d;

        /* renamed from: e */
        public EffectApplicationMode f13295e;

        public AsyncTaskC3005d(int i9, long j9, String str, PresetContentType presetContentType, EffectApplicationMode effectApplicationMode) {
            this.f13291a = i9;
            this.f13292b = j9;
            this.f13293c = str;
            this.f13294d = presetContentType;
            this.f13295e = effectApplicationMode;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C3008b.d doInBackground(String... strArr) {
            if (isCancelled() || strArr[0] == null) {
                return null;
            }
            return this.f13293c == null ? PreParsePresetSettingTask.this.f13275a.m15332O(strArr[0]) : PreParsePresetSettingTask.this.f13275a.m15333P(strArr[0], this.f13293c);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C3008b.d dVar) {
            if (this.f13294d == PresetContentType.Sample && C3002a.f13290a[this.f13295e.ordinal()] == 3) {
                Globals.m7388i0().f7310b.put(Integer.valueOf(this.f13291a), dVar);
                PreParsePresetSettingTask.this.f13280f.remove(Integer.valueOf(this.f13291a));
            }
            if (PreParsePresetSettingTask.this.f13277c.isEmpty() && PreParsePresetSettingTask.this.f13278d.isEmpty() && PreParsePresetSettingTask.this.f13279e.isEmpty() && PreParsePresetSettingTask.this.f13280f.isEmpty()) {
                if (PreParsePresetSettingTask.this.f13276b != null) {
                    PreParsePresetSettingTask.this.f13276b.onComplete();
                    PreParsePresetSettingTask.this.f13276b = null;
                }
                PreParsePresetSettingTask.m15310h(PreParsePresetSettingTask.this);
            }
        }
    }

    /* renamed from: com.cyberlink.you.effect.PreParsePresetSettingTask$e */
    public static class C3006e {

        /* renamed from: a */
        public static final PreParsePresetSettingTask f13297a = new PreParsePresetSettingTask(null);
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        f13270g = iAvailableProcessors;
        int i9 = iAvailableProcessors + 1;
        f13271h = i9;
        int i10 = (iAvailableProcessors * 2) + 1;
        f13272i = i10;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(512);
        f13273j = linkedBlockingQueue;
        f13274k = new ThreadPoolExecutor(i9, i10, 1L, TimeUnit.SECONDS, linkedBlockingQueue, new ThreadFactoryC6373j("PreParsePresetSettingTask"));
    }

    public /* synthetic */ PreParsePresetSettingTask(C3002a c3002a) {
        this();
    }

    /* renamed from: h */
    public static /* synthetic */ InterfaceC3003b m15310h(PreParsePresetSettingTask preParsePresetSettingTask) {
        preParsePresetSettingTask.getClass();
        return null;
    }

    /* renamed from: i */
    public static PreParsePresetSettingTask m15311i() {
        return C3006e.f13297a;
    }

    /* renamed from: j */
    public void m15312j(EffectApplicationMode effectApplicationMode, InterfaceC3004c interfaceC3004c) {
        this.f13276b = interfaceC3004c;
        m15313k(effectApplicationMode);
        if (interfaceC3004c != null && this.f13277c.isEmpty() && this.f13279e.isEmpty() && this.f13280f.isEmpty()) {
            interfaceC3004c.onComplete();
        }
    }

    /* renamed from: k */
    public final void m15313k(EffectApplicationMode effectApplicationMode) {
        if (C3002a.f13290a[effectApplicationMode.ordinal()] == 3) {
            int iM17322i = ColorEffectUtility.m17322i(ColorEffectUtility.ColorEffectMode.Capture);
            for (int i9 = 0; i9 < iM17322i; i9++) {
                int iM17321h = ColorEffectUtility.m17321h(ColorEffectUtility.ColorEffectMode.Capture, i9);
                for (int i10 = 0; i10 < iM17321h; i10++) {
                    int i11 = (i9 * 100) + i10 + 1;
                    if (!Globals.m7388i0().f7310b.containsKey(Integer.valueOf(i11)) || Globals.m7388i0().f7310b.get(Integer.valueOf(i11)) == null) {
                        this.f13280f.put(Integer.valueOf(i11), (AsyncTaskC3005d) new AsyncTaskC3005d(i11, 0L, null, PresetContentType.Sample, effectApplicationMode).executeOnExecutor(f13274k, "asset://capture/" + ColorEffectUtility.m17325l(ColorEffectUtility.ColorEffectMode.Capture, i9, i10)));
                    }
                }
            }
        }
    }

    public PreParsePresetSettingTask() {
        this.f13277c = new HashMap();
        this.f13278d = new HashMap();
        this.f13279e = new HashMap();
        this.f13280f = new HashMap();
        if (Globals.m7388i0().f7310b == null) {
            Globals.m7388i0().f7310b = new HashMap();
        }
        this.f13275a = C3008b.m15318G();
    }
}
