package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.Document;

/* renamed from: org.jsoup.nodes.c */
/* loaded from: classes.dex */
public class C5686c extends C5692i {
    public C5686c(String str) {
        super(str);
    }

    @Override // org.jsoup.nodes.C5692i, org.jsoup.nodes.AbstractC5690g
    /* renamed from: A */
    public void mo22849A(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        try {
            appendable.append("]]>");
        } catch (IOException e9) {
            throw new UncheckedIOException(e9);
        }
    }

    @Override // org.jsoup.nodes.C5692i, org.jsoup.nodes.AbstractC5690g
    /* renamed from: v */
    public String mo22827v() {
        return "#cdata";
    }

    @Override // org.jsoup.nodes.C5692i, org.jsoup.nodes.AbstractC5690g
    /* renamed from: z */
    public void mo22878z(Appendable appendable, int i9, Document.OutputSettings outputSettings) throws IOException {
        appendable.append("<![CDATA[").append(m22959T());
    }
}
