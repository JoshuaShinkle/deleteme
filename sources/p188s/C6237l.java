package p188s;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p188s.C6232g;

/* renamed from: s.l */
/* loaded from: classes.dex */
public class C6237l implements InterfaceC6231f {

    /* renamed from: a */
    public final Notification.Builder f21033a;

    /* renamed from: b */
    public final C6232g.e f21034b;

    /* renamed from: c */
    public RemoteViews f21035c;

    /* renamed from: d */
    public RemoteViews f21036d;

    /* renamed from: e */
    public final List<Bundle> f21037e = new ArrayList();

    /* renamed from: f */
    public final Bundle f21038f = new Bundle();

    /* renamed from: g */
    public int f21039g;

    /* renamed from: h */
    public RemoteViews f21040h;

    public C6237l(C6232g.e eVar) {
        this.f21034b = eVar;
        Notification.Builder builder = new Notification.Builder(eVar.f21003a, eVar.f20994I);
        this.f21033a = builder;
        Notification notification = eVar.f21000O;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, eVar.f21010h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(eVar.f21006d).setContentText(eVar.f21007e).setContentInfo(eVar.f21012j).setContentIntent(eVar.f21008f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(eVar.f21009g, (notification.flags & 128) != 0).setLargeIcon(eVar.f21011i).setNumber(eVar.f21013k).setProgress(eVar.f21020r, eVar.f21021s, eVar.f21022t);
        builder.setSubText(eVar.f21018p).setUsesChronometer(eVar.f21016n).setPriority(eVar.f21014l);
        Iterator<C6232g.a> it = eVar.f21004b.iterator();
        while (it.hasNext()) {
            m23870b(it.next());
        }
        Bundle bundle = eVar.f20987B;
        if (bundle != null) {
            this.f21038f.putAll(bundle);
        }
        this.f21035c = eVar.f20991F;
        this.f21036d = eVar.f20992G;
        this.f21033a.setShowWhen(eVar.f21015m);
        this.f21033a.setLocalOnly(eVar.f21026x).setGroup(eVar.f21023u).setGroupSummary(eVar.f21024v).setSortKey(eVar.f21025w);
        this.f21039g = eVar.f20998M;
        this.f21033a.setCategory(eVar.f20986A).setColor(eVar.f20988C).setVisibility(eVar.f20989D).setPublicVersion(eVar.f20990E).setSound(notification.sound, notification.audioAttributes);
        Iterator<String> it2 = eVar.f21002Q.iterator();
        while (it2.hasNext()) {
            this.f21033a.addPerson(it2.next());
        }
        this.f21040h = eVar.f20993H;
        if (eVar.f21005c.size() > 0) {
            Bundle bundle2 = eVar.m23838c().getBundle("android.car.EXTENSIONS");
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle();
            for (int i9 = 0; i9 < eVar.f21005c.size(); i9++) {
                bundle3.putBundle(Integer.toString(i9), C6238m.m23873a(eVar.f21005c.get(i9)));
            }
            bundle2.putBundle("invisible_actions", bundle3);
            eVar.m23838c().putBundle("android.car.EXTENSIONS", bundle2);
            this.f21038f.putBundle("android.car.EXTENSIONS", bundle2);
        }
        int i10 = Build.VERSION.SDK_INT;
        this.f21033a.setExtras(eVar.f20987B).setRemoteInputHistory(eVar.f21019q);
        RemoteViews remoteViews = eVar.f20991F;
        if (remoteViews != null) {
            this.f21033a.setCustomContentView(remoteViews);
        }
        RemoteViews remoteViews2 = eVar.f20992G;
        if (remoteViews2 != null) {
            this.f21033a.setCustomBigContentView(remoteViews2);
        }
        RemoteViews remoteViews3 = eVar.f20993H;
        if (remoteViews3 != null) {
            this.f21033a.setCustomHeadsUpContentView(remoteViews3);
        }
        this.f21033a.setBadgeIconType(eVar.f20995J).setShortcutId(eVar.f20996K).setTimeoutAfter(eVar.f20997L).setGroupAlertBehavior(eVar.f20998M);
        if (eVar.f21028z) {
            this.f21033a.setColorized(eVar.f21027y);
        }
        if (!TextUtils.isEmpty(eVar.f20994I)) {
            this.f21033a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        if (i10 >= 29) {
            this.f21033a.setAllowSystemGeneratedContextualActions(eVar.f20999N);
            this.f21033a.setBubbleMetadata(C6232g.d.m23830a(null));
        }
        if (eVar.f21001P) {
            if (this.f21034b.f21024v) {
                this.f21039g = 2;
            } else {
                this.f21039g = 1;
            }
            this.f21033a.setVibrate(null);
            this.f21033a.setSound(null);
            int i11 = notification.defaults & (-2) & (-3);
            notification.defaults = i11;
            this.f21033a.setDefaults(i11);
            if (TextUtils.isEmpty(this.f21034b.f21023u)) {
                this.f21033a.setGroup("silent");
            }
            this.f21033a.setGroupAlertBehavior(this.f21039g);
        }
    }

    @Override // p188s.InterfaceC6231f
    /* renamed from: a */
    public Notification.Builder mo23814a() {
        return this.f21033a;
    }

    /* renamed from: b */
    public final void m23870b(C6232g.a aVar) {
        IconCompat iconCompatM23819d = aVar.m23819d();
        Notification.Action.Builder builder = new Notification.Action.Builder(iconCompatM23819d != null ? iconCompatM23819d.m1514m() : null, aVar.m23823h(), aVar.m23816a());
        if (aVar.m23820e() != null) {
            for (RemoteInput remoteInput : C6240o.m23879b(aVar.m23820e())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = aVar.m23818c() != null ? new Bundle(aVar.m23818c()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.m23817b());
        int i9 = Build.VERSION.SDK_INT;
        builder.setAllowGeneratedReplies(aVar.m23817b());
        bundle.putInt("android.support.action.semanticAction", aVar.m23821f());
        if (i9 >= 28) {
            builder.setSemanticAction(aVar.m23821f());
        }
        if (i9 >= 29) {
            builder.setContextual(aVar.m23824i());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.m23822g());
        builder.addExtras(bundle);
        this.f21033a.addAction(builder.build());
    }

    /* renamed from: c */
    public Notification m23871c() {
        Bundle bundleM23815a;
        RemoteViews remoteViewsM23864e;
        RemoteViews remoteViewsM23862c;
        C6232g.f fVar = this.f21034b.f21017o;
        if (fVar != null) {
            fVar.mo23825b(this);
        }
        RemoteViews remoteViewsM23863d = fVar != null ? fVar.m23863d(this) : null;
        Notification notificationM23872d = m23872d();
        if (remoteViewsM23863d != null) {
            notificationM23872d.contentView = remoteViewsM23863d;
        } else {
            RemoteViews remoteViews = this.f21034b.f20991F;
            if (remoteViews != null) {
                notificationM23872d.contentView = remoteViews;
            }
        }
        if (fVar != null && (remoteViewsM23862c = fVar.m23862c(this)) != null) {
            notificationM23872d.bigContentView = remoteViewsM23862c;
        }
        if (fVar != null && (remoteViewsM23864e = this.f21034b.f21017o.m23864e(this)) != null) {
            notificationM23872d.headsUpContentView = remoteViewsM23864e;
        }
        if (fVar != null && (bundleM23815a = C6232g.m23815a(notificationM23872d)) != null) {
            fVar.m23861a(bundleM23815a);
        }
        return notificationM23872d;
    }

    /* renamed from: d */
    public Notification m23872d() {
        return this.f21033a.build();
    }
}
