package androidx.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.AbstractC0532m;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: androidx.transition.q */
/* loaded from: classes.dex */
public class C0536q extends AbstractC0532m {

    /* renamed from: d */
    public int f2956d;

    /* renamed from: b */
    public ArrayList<AbstractC0532m> f2954b = new ArrayList<>();

    /* renamed from: c */
    public boolean f2955c = true;

    /* renamed from: e */
    public boolean f2957e = false;

    /* renamed from: f */
    public int f2958f = 0;

    /* renamed from: androidx.transition.q$a */
    public class a extends C0533n {

        /* renamed from: a */
        public final /* synthetic */ AbstractC0532m f2959a;

        public a(AbstractC0532m abstractC0532m) {
            this.f2959a = abstractC0532m;
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: c */
        public void mo3051c(AbstractC0532m abstractC0532m) {
            this.f2959a.runAnimators();
            abstractC0532m.removeListener(this);
        }
    }

    /* renamed from: androidx.transition.q$b */
    public static class b extends C0533n {

        /* renamed from: a */
        public C0536q f2961a;

        public b(C0536q c0536q) {
            this.f2961a = c0536q;
        }

        @Override // androidx.transition.C0533n, androidx.transition.AbstractC0532m.g
        /* renamed from: a */
        public void mo3073a(AbstractC0532m abstractC0532m) {
            C0536q c0536q = this.f2961a;
            if (c0536q.f2957e) {
                return;
            }
            c0536q.start();
            this.f2961a.f2957e = true;
        }

        @Override // androidx.transition.AbstractC0532m.g
        /* renamed from: c */
        public void mo3051c(AbstractC0532m abstractC0532m) {
            C0536q c0536q = this.f2961a;
            int i9 = c0536q.f2956d - 1;
            c0536q.f2956d = i9;
            if (i9 == 0) {
                c0536q.f2957e = false;
                c0536q.end();
            }
            abstractC0532m.removeListener(this);
        }
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C0536q addListener(AbstractC0532m.g gVar) {
        return (C0536q) super.addListener(gVar);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0536q addTarget(int i9) {
        for (int i10 = 0; i10 < this.f2954b.size(); i10++) {
            this.f2954b.get(i10).addTarget(i9);
        }
        return (C0536q) super.addTarget(i9);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public C0536q addTarget(View view) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).addTarget(view);
        }
        return (C0536q) super.addTarget(view);
    }

    @Override // androidx.transition.AbstractC0532m
    public void cancel() {
        super.cancel();
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).cancel();
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void captureEndValues(C0539t c0539t) {
        if (isValidTarget(c0539t.f2966b)) {
            Iterator<AbstractC0532m> it = this.f2954b.iterator();
            while (it.hasNext()) {
                AbstractC0532m next = it.next();
                if (next.isValidTarget(c0539t.f2966b)) {
                    next.captureEndValues(c0539t);
                    c0539t.f2967c.add(next);
                }
            }
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void capturePropagationValues(C0539t c0539t) {
        super.capturePropagationValues(c0539t);
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).capturePropagationValues(c0539t);
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void captureStartValues(C0539t c0539t) {
        if (isValidTarget(c0539t.f2966b)) {
            Iterator<AbstractC0532m> it = this.f2954b.iterator();
            while (it.hasNext()) {
                AbstractC0532m next = it.next();
                if (next.isValidTarget(c0539t.f2966b)) {
                    next.captureStartValues(c0539t);
                    c0539t.f2967c.add(next);
                }
            }
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void createAnimators(ViewGroup viewGroup, C0540u c0540u, C0540u c0540u2, ArrayList<C0539t> arrayList, ArrayList<C0539t> arrayList2) {
        long startDelay = getStartDelay();
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            AbstractC0532m abstractC0532m = this.f2954b.get(i9);
            if (startDelay > 0 && (this.f2955c || i9 == 0)) {
                long startDelay2 = abstractC0532m.getStartDelay();
                if (startDelay2 > 0) {
                    abstractC0532m.setStartDelay(startDelay2 + startDelay);
                } else {
                    abstractC0532m.setStartDelay(startDelay);
                }
            }
            abstractC0532m.createAnimators(viewGroup, c0540u, c0540u2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public C0536q addTarget(Class cls) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).addTarget(cls);
        }
        return (C0536q) super.addTarget(cls);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public C0536q addTarget(String str) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).addTarget(str);
        }
        return (C0536q) super.addTarget(str);
    }

    @Override // androidx.transition.AbstractC0532m
    public AbstractC0532m excludeTarget(View view, boolean z8) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).excludeTarget(view, z8);
        }
        return super.excludeTarget(view, z8);
    }

    @Override // androidx.transition.AbstractC0532m
    public void forceToEnd(ViewGroup viewGroup) {
        super.forceToEnd(viewGroup);
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).forceToEnd(viewGroup);
        }
    }

    /* renamed from: g */
    public C0536q m3101g(AbstractC0532m abstractC0532m) {
        this.f2954b.add(abstractC0532m);
        abstractC0532m.mParent = this;
        long j9 = this.mDuration;
        if (j9 >= 0) {
            abstractC0532m.setDuration(j9);
        }
        if ((this.f2958f & 1) != 0) {
            abstractC0532m.setInterpolator(getInterpolator());
        }
        if ((this.f2958f & 2) != 0) {
            getPropagation();
            abstractC0532m.setPropagation(null);
        }
        if ((this.f2958f & 4) != 0) {
            abstractC0532m.setPathMotion(getPathMotion());
        }
        if ((this.f2958f & 8) != 0) {
            abstractC0532m.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    /* renamed from: h */
    public AbstractC0532m m3102h(int i9) {
        if (i9 < 0 || i9 >= this.f2954b.size()) {
            return null;
        }
        return this.f2954b.get(i9);
    }

    /* renamed from: i */
    public int m3103i() {
        return this.f2954b.size();
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public C0536q removeListener(AbstractC0532m.g gVar) {
        return (C0536q) super.removeListener(gVar);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public C0536q removeTarget(int i9) {
        for (int i10 = 0; i10 < this.f2954b.size(); i10++) {
            this.f2954b.get(i10).removeTarget(i9);
        }
        return (C0536q) super.removeTarget(i9);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public C0536q removeTarget(View view) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).removeTarget(view);
        }
        return (C0536q) super.removeTarget(view);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public C0536q removeTarget(Class cls) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).removeTarget(cls);
        }
        return (C0536q) super.removeTarget(cls);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public C0536q removeTarget(String str) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).removeTarget(str);
        }
        return (C0536q) super.removeTarget(str);
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public C0536q setDuration(long j9) {
        super.setDuration(j9);
        if (this.mDuration >= 0) {
            int size = this.f2954b.size();
            for (int i9 = 0; i9 < size; i9++) {
                this.f2954b.get(i9).setDuration(j9);
            }
        }
        return this;
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public C0536q setInterpolator(TimeInterpolator timeInterpolator) {
        this.f2958f |= 1;
        ArrayList<AbstractC0532m> arrayList = this.f2954b;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i9 = 0; i9 < size; i9++) {
                this.f2954b.get(i9).setInterpolator(timeInterpolator);
            }
        }
        return (C0536q) super.setInterpolator(timeInterpolator);
    }

    @Override // androidx.transition.AbstractC0532m
    public void pause(View view) {
        super.pause(view);
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).pause(view);
        }
    }

    /* renamed from: q */
    public C0536q m3111q(int i9) {
        if (i9 == 0) {
            this.f2955c = true;
        } else {
            if (i9 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i9);
            }
            this.f2955c = false;
        }
        return this;
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public C0536q setSceneRoot(ViewGroup viewGroup) {
        super.setSceneRoot(viewGroup);
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).setSceneRoot(viewGroup);
        }
        return this;
    }

    @Override // androidx.transition.AbstractC0532m
    public void resume(View view) {
        super.resume(view);
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).resume(view);
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void runAnimators() {
        if (this.f2954b.isEmpty()) {
            start();
            end();
            return;
        }
        m3114t();
        if (this.f2955c) {
            Iterator<AbstractC0532m> it = this.f2954b.iterator();
            while (it.hasNext()) {
                it.next().runAnimators();
            }
            return;
        }
        for (int i9 = 1; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9 - 1).addListener(new a(this.f2954b.get(i9)));
        }
        AbstractC0532m abstractC0532m = this.f2954b.get(0);
        if (abstractC0532m != null) {
            abstractC0532m.runAnimators();
        }
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public C0536q setStartDelay(long j9) {
        return (C0536q) super.setStartDelay(j9);
    }

    @Override // androidx.transition.AbstractC0532m
    public void setCanRemoveViews(boolean z8) {
        super.setCanRemoveViews(z8);
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).setCanRemoveViews(z8);
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void setEpicenterCallback(AbstractC0532m.f fVar) {
        super.setEpicenterCallback(fVar);
        this.f2958f |= 8;
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).setEpicenterCallback(fVar);
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void setPathMotion(AbstractC0523g abstractC0523g) {
        super.setPathMotion(abstractC0523g);
        this.f2958f |= 4;
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).setPathMotion(abstractC0523g);
        }
    }

    @Override // androidx.transition.AbstractC0532m
    public void setPropagation(AbstractC0535p abstractC0535p) {
        super.setPropagation(abstractC0535p);
        this.f2958f |= 2;
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.f2954b.get(i9).setPropagation(abstractC0535p);
        }
    }

    /* renamed from: t */
    public final void m3114t() {
        b bVar = new b(this);
        Iterator<AbstractC0532m> it = this.f2954b.iterator();
        while (it.hasNext()) {
            it.next().addListener(bVar);
        }
        this.f2956d = this.f2954b.size();
    }

    @Override // androidx.transition.AbstractC0532m
    public String toString(String str) {
        String string = super.toString(str);
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("\n");
            sb.append(this.f2954b.get(i9).toString(str + "  "));
            string = sb.toString();
        }
        return string;
    }

    @Override // androidx.transition.AbstractC0532m
    /* renamed from: clone */
    public AbstractC0532m mo25565clone() {
        C0536q c0536q = (C0536q) super.mo25565clone();
        c0536q.f2954b = new ArrayList<>();
        int size = this.f2954b.size();
        for (int i9 = 0; i9 < size; i9++) {
            c0536q.m3101g(this.f2954b.get(i9).mo25565clone());
        }
        return c0536q;
    }

    @Override // androidx.transition.AbstractC0532m
    public AbstractC0532m excludeTarget(String str, boolean z8) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).excludeTarget(str, z8);
        }
        return super.excludeTarget(str, z8);
    }

    @Override // androidx.transition.AbstractC0532m
    public AbstractC0532m excludeTarget(int i9, boolean z8) {
        for (int i10 = 0; i10 < this.f2954b.size(); i10++) {
            this.f2954b.get(i10).excludeTarget(i9, z8);
        }
        return super.excludeTarget(i9, z8);
    }

    @Override // androidx.transition.AbstractC0532m
    public AbstractC0532m excludeTarget(Class cls, boolean z8) {
        for (int i9 = 0; i9 < this.f2954b.size(); i9++) {
            this.f2954b.get(i9).excludeTarget(cls, z8);
        }
        return super.excludeTarget(cls, z8);
    }
}
