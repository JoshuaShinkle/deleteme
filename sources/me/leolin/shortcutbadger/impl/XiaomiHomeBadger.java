package me.leolin.shortcutbadger.impl;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import com.google.android.exoplayer2.C3322C;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import p068f6.InterfaceC4789a;
import p088h6.C5027a;

@Deprecated
/* loaded from: classes2.dex */
public class XiaomiHomeBadger implements InterfaceC4789a {

    /* renamed from: a */
    public ResolveInfo f18191a;

    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return Arrays.asList("com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2", "com.i.miui.launcher");
    }

    @Override // p068f6.InterfaceC4789a
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) throws IllegalAccessException, ShortcutBadgeException, NoSuchFieldException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Object objValueOf;
        try {
            Object objNewInstance = Class.forName("android.app.MiuiNotification").newInstance();
            Field declaredField = objNewInstance.getClass().getDeclaredField("messageCount");
            declaredField.setAccessible(true);
            if (i9 == 0) {
                objValueOf = "";
            } else {
                try {
                    objValueOf = Integer.valueOf(i9);
                } catch (Exception unused) {
                    declaredField.set(objNewInstance, Integer.valueOf(i9));
                }
            }
            declaredField.set(objNewInstance, String.valueOf(objValueOf));
        } catch (Exception unused2) {
            Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
            intent.putExtra("android.intent.extra.update_application_component_name", componentName.getPackageName() + "/" + componentName.getClassName());
            intent.putExtra("android.intent.extra.update_application_message_text", String.valueOf(i9 != 0 ? Integer.valueOf(i9) : ""));
            if (C5027a.m19603a(context, intent)) {
                context.sendBroadcast(intent);
            }
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            m20992c(context, i9);
        }
    }

    @TargetApi(16)
    /* renamed from: c */
    public final void m20992c(Context context, int i9) throws IllegalAccessException, ShortcutBadgeException, IllegalArgumentException, InvocationTargetException {
        if (this.f18191a == null) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            this.f18191a = context.getPackageManager().resolveActivity(intent, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
        }
        if (this.f18191a != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            Notification notificationBuild = new Notification.Builder(context).setContentTitle("").setContentText("").setSmallIcon(this.f18191a.getIconResource()).build();
            try {
                Object obj = notificationBuild.getClass().getDeclaredField("extraNotification").get(notificationBuild);
                obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(i9));
                notificationManager.notify(0, notificationBuild);
            } catch (Exception e9) {
                throw new ShortcutBadgeException("not able to set badge", e9);
            }
        }
    }
}
