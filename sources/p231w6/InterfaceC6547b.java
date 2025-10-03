package p231w6;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.InterfaceC5583c;

/* renamed from: w6.b */
/* loaded from: classes.dex */
public interface InterfaceC6547b {
    Reader getReader();

    InterfaceC5583c getReaderListener();

    Writer getWriter();

    InterfaceC5583c getWriterListener();

    Reader newConnectionReader(Reader reader);

    Writer newConnectionWriter(Writer writer);

    void userHasLogged(String str);
}
