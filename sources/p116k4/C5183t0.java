package p116k4;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.cyberlink.p030U.R;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.Permission.Permission;

/* renamed from: k4.t0 */
/* loaded from: classes.dex */
public class C5183t0 {

    /* renamed from: k4.t0$a */
    public class a implements DialogInterface.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ Activity f17755b;

        public a(Activity activity) {
            this.f17755b = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + this.f17755b.getPackageName()));
            this.f17755b.startActivity(intent);
        }
    }

    /* renamed from: k4.t0$b */
    public static /* synthetic */ class b {

        /* renamed from: a */
        public static final /* synthetic */ int[] f17756a;

        static {
            int[] iArr = new int[Permission.values().length];
            f17756a = iArr;
            try {
                iArr[Permission.MICROPHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17756a[Permission.GET_ACCOUNTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17756a[Permission.CALL_PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17756a[Permission.PHONE_STATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17756a[Permission.CAMERA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f17756a[Permission.LOCATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f17756a[Permission.STORAGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f17756a[Permission.NOTIFICATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f17756a[Permission.IMAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f17756a[Permission.VIDEO.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f17756a[Permission.BLUETOOTH_BLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* renamed from: a */
    public static void m20261a(Activity activity, int i9) {
        C3123g.m16382a(activity).setMessage(i9).setPositiveButton(R.string.permission_go_app_setting, new a(activity)).create().show();
    }

    /* renamed from: b */
    public static void m20262b(Activity activity, Permission permission) {
        int i9;
        switch (b.f17756a[permission.ordinal()]) {
            case 1:
                i9 = R.string.permission_ask_microphone;
                break;
            case 2:
                i9 = R.string.permission_ask_contacts;
                break;
            case 3:
            case 4:
                i9 = R.string.permission_ask_phone;
                break;
            case 5:
                i9 = R.string.permission_ask_camera;
                break;
            case 6:
                i9 = R.string.permission_ask_location;
                break;
            case 7:
                i9 = R.string.permission_ask_storage;
                break;
            case 8:
                i9 = R.string.permission_ask_notifications;
                break;
            case 9:
            case 10:
                i9 = R.string.permission_ask_photo_video;
                break;
            case 11:
                i9 = R.string.permission_ask_nearby_devices;
                break;
            default:
                return;
        }
        m20261a(activity, i9);
    }
}
