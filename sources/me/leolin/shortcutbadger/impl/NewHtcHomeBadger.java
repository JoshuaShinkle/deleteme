package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import p068f6.InterfaceC4789a;
import p088h6.C5027a;

/* loaded from: classes2.dex */
public class NewHtcHomeBadger implements InterfaceC4789a {
    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return Arrays.asList("com.htc.launcher");
    }

    @Override // p068f6.InterfaceC4789a
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) throws ShortcutBadgeException {
        Intent intent = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        intent.putExtra("com.htc.launcher.extra.COMPONENT", componentName.flattenToShortString());
        intent.putExtra("com.htc.launcher.extra.COUNT", i9);
        Intent intent2 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        intent2.putExtra("packagename", componentName.getPackageName());
        intent2.putExtra("count", i9);
        if (C5027a.m19603a(context, intent) || C5027a.m19603a(context, intent2)) {
            context.sendBroadcast(intent);
            context.sendBroadcast(intent2);
        } else {
            throw new ShortcutBadgeException("unable to resolve intent: " + intent2.toString());
        }
    }
}
