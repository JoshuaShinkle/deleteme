package org.jivesoftware.smackx.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: org.jivesoftware.smackx.search.a */
/* loaded from: classes.dex */
public class C5668a {

    /* renamed from: a */
    public List<a> f19885a = new ArrayList();

    /* renamed from: b */
    public List<c> f19886b = new ArrayList();

    /* renamed from: c */
    public String f19887c = "";

    /* renamed from: org.jivesoftware.smackx.search.a$a */
    public static class a {

        /* renamed from: a */
        public String f19888a;

        /* renamed from: b */
        public String f19889b;

        /* renamed from: c */
        public String f19890c;

        public a(String str, String str2, String str3) {
            this.f19888a = str;
            this.f19889b = str2;
            this.f19890c = str3;
        }

        /* renamed from: a */
        public String m22711a() {
            return this.f19889b;
        }
    }

    /* renamed from: org.jivesoftware.smackx.search.a$b */
    public static class b {

        /* renamed from: a */
        public String f19891a;

        /* renamed from: b */
        public List<String> f19892b;

        public b(String str, List<String> list) {
            this.f19891a = str;
            this.f19892b = list;
        }
    }

    /* renamed from: org.jivesoftware.smackx.search.a$c */
    public static class c {

        /* renamed from: a */
        public List<b> f19893a;

        public c(List<b> list) {
            new ArrayList();
            this.f19893a = list;
        }
    }

    /* renamed from: a */
    public void m22708a(a aVar) {
        this.f19885a.add(aVar);
    }

    /* renamed from: b */
    public void m22709b(c cVar) {
        this.f19886b.add(cVar);
    }

    /* renamed from: c */
    public List<a> m22710c() {
        return Collections.unmodifiableList(new ArrayList(this.f19885a));
    }
}
