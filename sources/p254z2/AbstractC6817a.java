package p254z2;

import android.view.View;
import com.cyberlink.you.activity.chatdialog.message.ChatDialogMode;
import com.cyberlink.you.activity.chatdialog.message.SelfDestructView;

/* renamed from: z2.a */
/* loaded from: classes.dex */
public abstract class AbstractC6817a {

    /* renamed from: a */
    public C6819c f22575a;

    /* renamed from: z2.a$b */
    public static class b extends AbstractC6817a {
        public b(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22634v.setLongClickable(true);
                this.f22575a.f22634v.setClickable(true);
            } else {
                this.f22575a.f22634v.setLongClickable(false);
                this.f22575a.f22634v.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$c */
    public static class c extends AbstractC6817a {
        public c(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
        }
    }

    /* renamed from: z2.a$d */
    public static class d extends AbstractC6817a {
        @Deprecated
        public d(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
        }
    }

    /* renamed from: z2.a$e */
    public static class e extends AbstractC6817a {
        public e(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22619g.setLongClickable(true);
                this.f22575a.f22619g.setClickable(true);
                this.f22575a.f22613b.setClickable(true);
                this.f22575a.f22625m.setClickable(true);
                return;
            }
            this.f22575a.f22619g.setLongClickable(false);
            this.f22575a.f22619g.setClickable(false);
            this.f22575a.f22613b.setClickable(false);
            this.f22575a.f22625m.setClickable(false);
        }
    }

    /* renamed from: z2.a$f */
    public static class f extends AbstractC6817a {
        public f(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                View view = this.f22575a.f22627o;
                if (view != null) {
                    view.setLongClickable(true);
                    this.f22575a.f22627o.setClickable(true);
                    return;
                }
                return;
            }
            View view2 = this.f22575a.f22627o;
            if (view2 != null) {
                view2.setLongClickable(false);
                this.f22575a.f22627o.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$g */
    public static class g extends AbstractC6817a {

        /* renamed from: b */
        public boolean f22576b;

        /* renamed from: c */
        public boolean f22577c;

        public g(C6819c c6819c) {
            this(c6819c, true, true);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                if (this.f22576b) {
                    this.f22575a.f22638z.setLongClickable(true);
                }
                this.f22575a.f22638z.setClickable(true);
                this.f22575a.f22613b.setClickable(true);
                if (this.f22577c) {
                    this.f22575a.f22625m.setClickable(true);
                    return;
                }
                return;
            }
            if (this.f22576b) {
                this.f22575a.f22638z.setLongClickable(false);
            }
            this.f22575a.f22638z.setClickable(false);
            this.f22575a.f22613b.setClickable(false);
            if (this.f22577c) {
                this.f22575a.f22625m.setClickable(false);
            }
        }

        public g(C6819c c6819c, boolean z8, boolean z9) {
            super(c6819c);
            this.f22576b = z8;
            this.f22577c = z9;
        }
    }

    /* renamed from: z2.a$h */
    public static class h extends AbstractC6817a {
        public h(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22620h.setLongClickable(true);
                this.f22575a.f22620h.setClickable(true);
                this.f22575a.f22627o.setLongClickable(true);
                this.f22575a.f22613b.setClickable(true);
                SelfDestructView selfDestructView = this.f22575a.f22625m;
                if (selfDestructView != null) {
                    selfDestructView.setClickable(true);
                    return;
                }
                return;
            }
            this.f22575a.f22620h.setLongClickable(false);
            this.f22575a.f22620h.setClickable(false);
            this.f22575a.f22627o.setLongClickable(false);
            this.f22575a.f22613b.setClickable(false);
            SelfDestructView selfDestructView2 = this.f22575a.f22625m;
            if (selfDestructView2 != null) {
                selfDestructView2.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$i */
    public static class i extends AbstractC6817a {
        public i(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22615c.setLongClickable(true);
                this.f22575a.f22615c.setClickable(true);
                this.f22575a.f22627o.setClickable(true);
                this.f22575a.f22613b.setClickable(true);
                SelfDestructView selfDestructView = this.f22575a.f22625m;
                if (selfDestructView != null) {
                    selfDestructView.setClickable(true);
                    return;
                }
                return;
            }
            this.f22575a.f22615c.setLongClickable(false);
            this.f22575a.f22615c.setClickable(false);
            this.f22575a.f22627o.setClickable(false);
            this.f22575a.f22613b.setClickable(false);
            SelfDestructView selfDestructView2 = this.f22575a.f22625m;
            if (selfDestructView2 != null) {
                selfDestructView2.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$j */
    public static class j extends AbstractC6817a {
        public j(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22615c.setLongClickable(true);
                this.f22575a.f22615c.setClickable(true);
                this.f22575a.f22613b.setClickable(true);
                this.f22575a.f22625m.setClickable(true);
                this.f22575a.f22638z.setClickable(true);
                return;
            }
            this.f22575a.f22615c.setLongClickable(false);
            this.f22575a.f22615c.setClickable(false);
            this.f22575a.f22613b.setClickable(false);
            this.f22575a.f22625m.setClickable(false);
            this.f22575a.f22638z.setClickable(false);
        }
    }

    /* renamed from: z2.a$k */
    public static class k extends AbstractC6817a {
        public k(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22616d.setLongClickable(true);
                this.f22575a.f22616d.setClickable(true);
                this.f22575a.f22613b.setClickable(true);
                this.f22575a.f22625m.setClickable(true);
                return;
            }
            this.f22575a.f22616d.setLongClickable(false);
            this.f22575a.f22616d.setClickable(false);
            this.f22575a.f22613b.setClickable(false);
            this.f22575a.f22625m.setClickable(false);
        }
    }

    /* renamed from: z2.a$l */
    public static class l extends AbstractC6817a {
        public l(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22619g.setLongClickable(true);
                this.f22575a.f22619g.setClickable(true);
            } else {
                this.f22575a.f22619g.setLongClickable(false);
                this.f22575a.f22619g.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$m */
    public static class m extends AbstractC6817a {

        /* renamed from: b */
        public boolean f22578b;

        public m(C6819c c6819c) {
            this(c6819c, true);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                if (this.f22578b) {
                    this.f22575a.f22638z.setLongClickable(true);
                }
                this.f22575a.f22638z.setClickable(true);
            } else {
                if (this.f22578b) {
                    this.f22575a.f22638z.setLongClickable(false);
                }
                this.f22575a.f22638z.setClickable(false);
            }
        }

        public m(C6819c c6819c, boolean z8) {
            super(c6819c);
            this.f22578b = z8;
        }
    }

    /* renamed from: z2.a$n */
    public static class n extends AbstractC6817a {
        public n(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22620h.setLongClickable(true);
                this.f22575a.f22620h.setClickable(true);
                this.f22575a.f22627o.setClickable(true);
                this.f22575a.f22627o.setLongClickable(true);
                return;
            }
            this.f22575a.f22620h.setLongClickable(false);
            this.f22575a.f22620h.setClickable(false);
            this.f22575a.f22627o.setClickable(false);
            this.f22575a.f22627o.setLongClickable(false);
        }
    }

    /* renamed from: z2.a$o */
    public static class o extends AbstractC6817a {
        public o(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22615c.setLongClickable(true);
                this.f22575a.f22615c.setClickable(true);
                this.f22575a.f22627o.setClickable(true);
            } else {
                this.f22575a.f22615c.setLongClickable(false);
                this.f22575a.f22615c.setClickable(false);
                this.f22575a.f22627o.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$p */
    public static class p extends AbstractC6817a {
        public p(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22615c.setLongClickable(true);
                this.f22575a.f22615c.setClickable(true);
                this.f22575a.f22638z.setClickable(true);
            } else {
                this.f22575a.f22615c.setLongClickable(false);
                this.f22575a.f22615c.setClickable(false);
                this.f22575a.f22638z.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$q */
    public static class q extends AbstractC6817a {
        public q(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22616d.setLongClickable(true);
                this.f22575a.f22616d.setClickable(true);
            } else {
                this.f22575a.f22616d.setLongClickable(false);
                this.f22575a.f22616d.setClickable(false);
            }
        }
    }

    /* renamed from: z2.a$r */
    public static class r extends AbstractC6817a {
        public r(C6819c c6819c) {
            super(c6819c);
        }

        @Override // p254z2.AbstractC6817a
        /* renamed from: b */
        public void mo25389b(ChatDialogMode chatDialogMode) {
            if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
                this.f22575a.f22597M.setLongClickable(true);
            } else {
                this.f22575a.f22597M.setLongClickable(false);
            }
        }
    }

    /* renamed from: a */
    public static AbstractC6817a m25388a(int i9, C6819c c6819c) {
        switch (i9) {
            case 0:
            case 20:
                return new n(c6819c);
            case 1:
            case 4:
            case 11:
                return new q(c6819c);
            case 2:
                return new l(c6819c);
            case 3:
            case 12:
            case 19:
                return new o(c6819c);
            case 5:
            case 9:
            case 10:
            case 13:
            case 15:
            case 18:
                return new m(c6819c);
            case 6:
                return new m(c6819c, false);
            case 7:
            case 16:
            case 17:
            case 34:
            case 36:
            case 41:
            case 42:
            case 45:
            case 53:
                return new d(c6819c);
            case 8:
            case 14:
                return new p(c6819c);
            case 21:
            case 46:
                return new h(c6819c);
            case 22:
            case 25:
            case 32:
                return new k(c6819c);
            case 23:
                return new e(c6819c);
            case 24:
            case 33:
            case 44:
                return new i(c6819c);
            case 26:
            case 28:
            case 30:
            case 31:
                return new g(c6819c);
            case 27:
                return new g(c6819c, false, true);
            case 29:
            case 38:
                return new j(c6819c);
            case 35:
                return new f(c6819c);
            case 37:
            case 40:
            case 43:
                return new g(c6819c, true, false);
            case 39:
                return new r(c6819c);
            case 47:
            case 50:
                return new c(c6819c);
            case 48:
            case 49:
            case 51:
            case 52:
                return new b(c6819c);
            default:
                throw new IllegalArgumentException("wrong actionType:" + i9);
        }
    }

    /* renamed from: b */
    public abstract void mo25389b(ChatDialogMode chatDialogMode);

    public AbstractC6817a(C6819c c6819c) {
        this.f22575a = c6819c;
    }
}
