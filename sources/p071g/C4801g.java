package p071g;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.MenuItemC0140j;
import androidx.appcompat.widget.C0250q0;
import androidx.appcompat.widget.C0262x;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p010b.C0569j;
import p042d0.AbstractC4615b;
import p042d0.C4629i;
import p233x.InterfaceMenuC6559a;

/* renamed from: g.g */
/* loaded from: classes.dex */
public class C4801g extends MenuInflater {

    /* renamed from: e */
    public static final Class<?>[] f16672e;

    /* renamed from: f */
    public static final Class<?>[] f16673f;

    /* renamed from: a */
    public final Object[] f16674a;

    /* renamed from: b */
    public final Object[] f16675b;

    /* renamed from: c */
    public Context f16676c;

    /* renamed from: d */
    public Object f16677d;

    /* renamed from: g.g$a */
    public static class a implements MenuItem.OnMenuItemClickListener {

        /* renamed from: c */
        public static final Class<?>[] f16678c = {MenuItem.class};

        /* renamed from: a */
        public Object f16679a;

        /* renamed from: b */
        public Method f16680b;

        public a(Object obj, String str) {
            this.f16679a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f16680b = cls.getMethod(str, f16678c);
            } catch (Exception e9) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e9);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            try {
                if (this.f16680b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f16680b.invoke(this.f16679a, menuItem)).booleanValue();
                }
                this.f16680b.invoke(this.f16679a, menuItem);
                return true;
            } catch (Exception e9) {
                throw new RuntimeException(e9);
            }
        }
    }

    /* renamed from: g.g$b */
    public class b {

        /* renamed from: A */
        public AbstractC4615b f16681A;

        /* renamed from: B */
        public CharSequence f16682B;

        /* renamed from: C */
        public CharSequence f16683C;

        /* renamed from: D */
        public ColorStateList f16684D = null;

        /* renamed from: E */
        public PorterDuff.Mode f16685E = null;

        /* renamed from: a */
        public Menu f16687a;

        /* renamed from: b */
        public int f16688b;

        /* renamed from: c */
        public int f16689c;

        /* renamed from: d */
        public int f16690d;

        /* renamed from: e */
        public int f16691e;

        /* renamed from: f */
        public boolean f16692f;

        /* renamed from: g */
        public boolean f16693g;

        /* renamed from: h */
        public boolean f16694h;

        /* renamed from: i */
        public int f16695i;

        /* renamed from: j */
        public int f16696j;

        /* renamed from: k */
        public CharSequence f16697k;

        /* renamed from: l */
        public CharSequence f16698l;

        /* renamed from: m */
        public int f16699m;

        /* renamed from: n */
        public char f16700n;

        /* renamed from: o */
        public int f16701o;

        /* renamed from: p */
        public char f16702p;

        /* renamed from: q */
        public int f16703q;

        /* renamed from: r */
        public int f16704r;

        /* renamed from: s */
        public boolean f16705s;

        /* renamed from: t */
        public boolean f16706t;

        /* renamed from: u */
        public boolean f16707u;

        /* renamed from: v */
        public int f16708v;

        /* renamed from: w */
        public int f16709w;

        /* renamed from: x */
        public String f16710x;

        /* renamed from: y */
        public String f16711y;

        /* renamed from: z */
        public String f16712z;

        public b(Menu menu) {
            this.f16687a = menu;
            m19060h();
        }

        /* renamed from: a */
        public void m19053a() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.f16694h = true;
            m19061i(this.f16687a.add(this.f16688b, this.f16695i, this.f16696j, this.f16697k));
        }

        /* renamed from: b */
        public SubMenu m19054b() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.f16694h = true;
            SubMenu subMenuAddSubMenu = this.f16687a.addSubMenu(this.f16688b, this.f16695i, this.f16696j, this.f16697k);
            m19061i(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        /* renamed from: c */
        public final char m19055c(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        /* renamed from: d */
        public boolean m19056d() {
            return this.f16694h;
        }

        /* renamed from: e */
        public final <T> T m19057e(String str, Class<?>[] clsArr, Object[] objArr) throws NoSuchMethodException, SecurityException {
            try {
                Constructor<?> constructor = Class.forName(str, false, C4801g.this.f16676c.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e9) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e9);
                return null;
            }
        }

        /* renamed from: f */
        public void m19058f(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = C4801g.this.f16676c.obtainStyledAttributes(attributeSet, C0569j.MenuGroup);
            this.f16688b = typedArrayObtainStyledAttributes.getResourceId(C0569j.MenuGroup_android_id, 0);
            this.f16689c = typedArrayObtainStyledAttributes.getInt(C0569j.MenuGroup_android_menuCategory, 0);
            this.f16690d = typedArrayObtainStyledAttributes.getInt(C0569j.MenuGroup_android_orderInCategory, 0);
            this.f16691e = typedArrayObtainStyledAttributes.getInt(C0569j.MenuGroup_android_checkableBehavior, 0);
            this.f16692f = typedArrayObtainStyledAttributes.getBoolean(C0569j.MenuGroup_android_visible, true);
            this.f16693g = typedArrayObtainStyledAttributes.getBoolean(C0569j.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        /* renamed from: g */
        public void m19059g(AttributeSet attributeSet) {
            C0250q0 c0250q0M1003u = C0250q0.m1003u(C4801g.this.f16676c, attributeSet, C0569j.MenuItem);
            this.f16695i = c0250q0M1003u.m1018n(C0569j.MenuItem_android_id, 0);
            this.f16696j = (c0250q0M1003u.m1015k(C0569j.MenuItem_android_menuCategory, this.f16689c) & (-65536)) | (c0250q0M1003u.m1015k(C0569j.MenuItem_android_orderInCategory, this.f16690d) & 65535);
            this.f16697k = c0250q0M1003u.m1020p(C0569j.MenuItem_android_title);
            this.f16698l = c0250q0M1003u.m1020p(C0569j.MenuItem_android_titleCondensed);
            this.f16699m = c0250q0M1003u.m1018n(C0569j.MenuItem_android_icon, 0);
            this.f16700n = m19055c(c0250q0M1003u.m1019o(C0569j.MenuItem_android_alphabeticShortcut));
            this.f16701o = c0250q0M1003u.m1015k(C0569j.MenuItem_alphabeticModifiers, 4096);
            this.f16702p = m19055c(c0250q0M1003u.m1019o(C0569j.MenuItem_android_numericShortcut));
            this.f16703q = c0250q0M1003u.m1015k(C0569j.MenuItem_numericModifiers, 4096);
            int i9 = C0569j.MenuItem_android_checkable;
            if (c0250q0M1003u.m1023s(i9)) {
                this.f16704r = c0250q0M1003u.m1005a(i9, false) ? 1 : 0;
            } else {
                this.f16704r = this.f16691e;
            }
            this.f16705s = c0250q0M1003u.m1005a(C0569j.MenuItem_android_checked, false);
            this.f16706t = c0250q0M1003u.m1005a(C0569j.MenuItem_android_visible, this.f16692f);
            this.f16707u = c0250q0M1003u.m1005a(C0569j.MenuItem_android_enabled, this.f16693g);
            this.f16708v = c0250q0M1003u.m1015k(C0569j.MenuItem_showAsAction, -1);
            this.f16712z = c0250q0M1003u.m1019o(C0569j.MenuItem_android_onClick);
            this.f16709w = c0250q0M1003u.m1018n(C0569j.MenuItem_actionLayout, 0);
            this.f16710x = c0250q0M1003u.m1019o(C0569j.MenuItem_actionViewClass);
            String strM1019o = c0250q0M1003u.m1019o(C0569j.MenuItem_actionProviderClass);
            this.f16711y = strM1019o;
            boolean z8 = strM1019o != null;
            if (z8 && this.f16709w == 0 && this.f16710x == null) {
                this.f16681A = (AbstractC4615b) m19057e(strM1019o, C4801g.f16673f, C4801g.this.f16675b);
            } else {
                if (z8) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f16681A = null;
            }
            this.f16682B = c0250q0M1003u.m1020p(C0569j.MenuItem_contentDescription);
            this.f16683C = c0250q0M1003u.m1020p(C0569j.MenuItem_tooltipText);
            int i10 = C0569j.MenuItem_iconTintMode;
            if (c0250q0M1003u.m1023s(i10)) {
                this.f16685E = C0262x.m1076d(c0250q0M1003u.m1015k(i10, -1), this.f16685E);
            } else {
                this.f16685E = null;
            }
            int i11 = C0569j.MenuItem_iconTint;
            if (c0250q0M1003u.m1023s(i11)) {
                this.f16684D = c0250q0M1003u.m1007c(i11);
            } else {
                this.f16684D = null;
            }
            c0250q0M1003u.m1024w();
            this.f16694h = false;
        }

        /* renamed from: h */
        public void m19060h() {
            this.f16688b = 0;
            this.f16689c = 0;
            this.f16690d = 0;
            this.f16691e = 0;
            this.f16692f = true;
            this.f16693g = true;
        }

        /* renamed from: i */
        public final void m19061i(MenuItem menuItem) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            boolean z8 = false;
            menuItem.setChecked(this.f16705s).setVisible(this.f16706t).setEnabled(this.f16707u).setCheckable(this.f16704r >= 1).setTitleCondensed(this.f16698l).setIcon(this.f16699m);
            int i9 = this.f16708v;
            if (i9 >= 0) {
                menuItem.setShowAsAction(i9);
            }
            if (this.f16712z != null) {
                if (C4801g.this.f16676c.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(C4801g.this.m19051b(), this.f16712z));
            }
            if (this.f16704r >= 2) {
                if (menuItem instanceof C0139i) {
                    ((C0139i) menuItem).m540t(true);
                } else if (menuItem instanceof MenuItemC0140j) {
                    ((MenuItemC0140j) menuItem).m547h(true);
                }
            }
            String str = this.f16710x;
            if (str != null) {
                menuItem.setActionView((View) m19057e(str, C4801g.f16672e, C4801g.this.f16674a));
                z8 = true;
            }
            int i10 = this.f16709w;
            if (i10 > 0) {
                if (z8) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    menuItem.setActionView(i10);
                }
            }
            AbstractC4615b abstractC4615b = this.f16681A;
            if (abstractC4615b != null) {
                C4629i.m18462a(menuItem, abstractC4615b);
            }
            C4629i.m18464c(menuItem, this.f16682B);
            C4629i.m18468g(menuItem, this.f16683C);
            C4629i.m18463b(menuItem, this.f16700n, this.f16701o);
            C4629i.m18467f(menuItem, this.f16702p, this.f16703q);
            PorterDuff.Mode mode = this.f16685E;
            if (mode != null) {
                C4629i.m18466e(menuItem, mode);
            }
            ColorStateList colorStateList = this.f16684D;
            if (colorStateList != null) {
                C4629i.m18465d(menuItem, colorStateList);
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f16672e = clsArr;
        f16673f = clsArr;
    }

    public C4801g(Context context) {
        super(context);
        this.f16676c = context;
        Object[] objArr = {context};
        this.f16674a = objArr;
        this.f16675b = objArr;
    }

    /* renamed from: a */
    public final Object m19050a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? m19050a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* renamed from: b */
    public Object m19051b() {
        if (this.f16677d == null) {
            this.f16677d = m19050a(this.f16676c);
        }
        return this.f16677d;
    }

    /* renamed from: c */
    public final void m19052c(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
                eventType = xmlPullParser.next();
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z8 = false;
        boolean z9 = false;
        String str = null;
        while (!z8) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z9 && name2.equals(str)) {
                        z9 = false;
                        str = null;
                    } else if (name2.equals("group")) {
                        bVar.m19060h();
                    } else if (name2.equals("item")) {
                        if (!bVar.m19056d()) {
                            AbstractC4615b abstractC4615b = bVar.f16681A;
                            if (abstractC4615b == null || !abstractC4615b.mo548a()) {
                                bVar.m19053a();
                            } else {
                                bVar.m19054b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z8 = true;
                    }
                }
            } else if (!z9) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.m19058f(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.m19059g(attributeSet);
                } else if (name3.equals("menu")) {
                    m19052c(xmlPullParser, attributeSet, bVar.m19054b());
                } else {
                    str = name3;
                    z9 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    @Override // android.view.MenuInflater
    public void inflate(int i9, Menu menu) {
        if (!(menu instanceof InterfaceMenuC6559a)) {
            super.inflate(i9, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f16676c.getResources().getLayout(i9);
                    m19052c(layout, Xml.asAttributeSet(layout), menu);
                } catch (XmlPullParserException e9) {
                    throw new InflateException("Error inflating menu XML", e9);
                }
            } catch (IOException e10) {
                throw new InflateException("Error inflating menu XML", e10);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }
}
