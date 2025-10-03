package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* renamed from: j */
    public static final PorterDuff.Mode f1781j = PorterDuff.Mode.SRC_IN;

    /* renamed from: a */
    public int f1782a;

    /* renamed from: b */
    public Object f1783b;

    /* renamed from: c */
    public byte[] f1784c;

    /* renamed from: d */
    public Parcelable f1785d;

    /* renamed from: e */
    public int f1786e;

    /* renamed from: f */
    public int f1787f;

    /* renamed from: g */
    public ColorStateList f1788g;

    /* renamed from: h */
    public PorterDuff.Mode f1789h;

    /* renamed from: i */
    public String f1790i;

    public IconCompat() {
        this.f1782a = -1;
        this.f1784c = null;
        this.f1785d = null;
        this.f1786e = 0;
        this.f1787f = 0;
        this.f1788g = null;
        this.f1789h = f1781j;
        this.f1790i = null;
    }

    /* renamed from: a */
    public static IconCompat m1501a(Resources resources, String str, int i9) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if (i9 == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.f1786e = i9;
        if (resources != null) {
            try {
                iconCompat.f1783b = resources.getResourceName(i9);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.f1783b = str;
        }
        return iconCompat;
    }

    /* renamed from: c */
    public static int m1502c(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e9) {
            Log.e("IconCompat", "Unable to get icon resource", e9);
            return 0;
        } catch (NoSuchMethodException e10) {
            Log.e("IconCompat", "Unable to get icon resource", e10);
            return 0;
        } catch (InvocationTargetException e11) {
            Log.e("IconCompat", "Unable to get icon resource", e11);
            return 0;
        }
    }

    /* renamed from: e */
    public static String m1503e(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e9) {
            Log.e("IconCompat", "Unable to get icon package", e9);
            return null;
        } catch (NoSuchMethodException e10) {
            Log.e("IconCompat", "Unable to get icon package", e10);
            return null;
        } catch (InvocationTargetException e11) {
            Log.e("IconCompat", "Unable to get icon package", e11);
            return null;
        }
    }

    /* renamed from: g */
    public static int m1504g(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e9) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e9);
            return -1;
        } catch (NoSuchMethodException e10) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e10);
            return -1;
        } catch (InvocationTargetException e11) {
            Log.e("IconCompat", "Unable to get icon type " + icon, e11);
            return -1;
        }
    }

    /* renamed from: i */
    public static Uri m1505i(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e9) {
            Log.e("IconCompat", "Unable to get icon uri", e9);
            return null;
        } catch (NoSuchMethodException e10) {
            Log.e("IconCompat", "Unable to get icon uri", e10);
            return null;
        } catch (InvocationTargetException e11) {
            Log.e("IconCompat", "Unable to get icon uri", e11);
            return null;
        }
    }

    /* renamed from: o */
    public static String m1506o(int i9) {
        switch (i9) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case 6:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    /* renamed from: b */
    public int m1507b() {
        int i9 = this.f1782a;
        if (i9 == -1) {
            return m1502c((Icon) this.f1783b);
        }
        if (i9 == 2) {
            return this.f1786e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    /* renamed from: d */
    public String m1508d() {
        int i9 = this.f1782a;
        if (i9 == -1) {
            return m1503e((Icon) this.f1783b);
        }
        if (i9 == 2) {
            return ((String) this.f1783b).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    /* renamed from: f */
    public int m1509f() {
        int i9 = this.f1782a;
        return i9 == -1 ? m1504g((Icon) this.f1783b) : i9;
    }

    /* renamed from: h */
    public Uri m1510h() {
        int i9 = this.f1782a;
        if (i9 == -1) {
            return m1505i((Icon) this.f1783b);
        }
        if (i9 == 4 || i9 == 6) {
            return Uri.parse((String) this.f1783b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    /* renamed from: j */
    public final InputStream m1511j(Context context) {
        Uri uriM1510h = m1510h();
        String scheme = uriM1510h.getScheme();
        if (FirebaseAnalytics.Param.CONTENT.equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(uriM1510h);
            } catch (Exception e9) {
                Log.w("IconCompat", "Unable to load image from URI: " + uriM1510h, e9);
                return null;
            }
        }
        try {
            return new FileInputStream(new File((String) this.f1783b));
        } catch (FileNotFoundException e10) {
            Log.w("IconCompat", "Unable to load image from path: " + uriM1510h, e10);
            return null;
        }
    }

    /* renamed from: k */
    public void m1512k() {
        this.f1789h = PorterDuff.Mode.valueOf(this.f1790i);
        switch (this.f1782a) {
            case -1:
                Parcelable parcelable = this.f1785d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f1783b = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.f1785d;
                if (parcelable2 != null) {
                    this.f1783b = parcelable2;
                    return;
                }
                byte[] bArr = this.f1784c;
                this.f1783b = bArr;
                this.f1782a = 3;
                this.f1786e = 0;
                this.f1787f = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                this.f1783b = new String(this.f1784c, Charset.forName("UTF-16"));
                return;
            case 3:
                this.f1783b = this.f1784c;
                return;
        }
    }

    /* renamed from: l */
    public void m1513l(boolean z8) {
        this.f1790i = this.f1789h.name();
        switch (this.f1782a) {
            case -1:
                if (z8) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.f1785d = (Parcelable) this.f1783b;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z8) {
                    this.f1785d = (Parcelable) this.f1783b;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f1783b;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f1784c = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f1784c = ((String) this.f1783b).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f1784c = (byte[]) this.f1783b;
                return;
            case 4:
            case 6:
                this.f1784c = this.f1783b.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    @Deprecated
    /* renamed from: m */
    public Icon m1514m() {
        return m1515n(null);
    }

    /* renamed from: n */
    public Icon m1515n(Context context) {
        Icon iconCreateWithBitmap;
        switch (this.f1782a) {
            case -1:
                return (Icon) this.f1783b;
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                iconCreateWithBitmap = Icon.createWithBitmap((Bitmap) this.f1783b);
                break;
            case 2:
                iconCreateWithBitmap = Icon.createWithResource(m1508d(), this.f1786e);
                break;
            case 3:
                iconCreateWithBitmap = Icon.createWithData((byte[]) this.f1783b, this.f1786e, this.f1787f);
                break;
            case 4:
                iconCreateWithBitmap = Icon.createWithContentUri((String) this.f1783b);
                break;
            case 5:
                iconCreateWithBitmap = Icon.createWithAdaptiveBitmap((Bitmap) this.f1783b);
                break;
            case 6:
                if (context == null) {
                    throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + m1510h());
                }
                InputStream inputStreamM1511j = m1511j(context);
                if (inputStreamM1511j == null) {
                    throw new IllegalStateException("Cannot load adaptive icon from uri: " + m1510h());
                }
                iconCreateWithBitmap = Icon.createWithAdaptiveBitmap(BitmapFactory.decodeStream(inputStreamM1511j));
                break;
        }
        ColorStateList colorStateList = this.f1788g;
        if (colorStateList != null) {
            iconCreateWithBitmap.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.f1789h;
        if (mode != f1781j) {
            iconCreateWithBitmap.setTintMode(mode);
        }
        return iconCreateWithBitmap;
    }

    public String toString() {
        if (this.f1782a == -1) {
            return String.valueOf(this.f1783b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(m1506o(this.f1782a));
        switch (this.f1782a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f1783b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f1783b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(m1508d());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(m1507b())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f1786e);
                if (this.f1787f != 0) {
                    sb.append(" off=");
                    sb.append(this.f1787f);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f1783b);
                break;
        }
        if (this.f1788g != null) {
            sb.append(" tint=");
            sb.append(this.f1788g);
        }
        if (this.f1789h != f1781j) {
            sb.append(" mode=");
            sb.append(this.f1789h);
        }
        sb.append(")");
        return sb.toString();
    }

    public IconCompat(int i9) {
        this.f1784c = null;
        this.f1785d = null;
        this.f1786e = 0;
        this.f1787f = 0;
        this.f1788g = null;
        this.f1789h = f1781j;
        this.f1790i = null;
        this.f1782a = i9;
    }
}
