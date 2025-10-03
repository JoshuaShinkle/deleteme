package p204t6;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;
import kotlin.text.StringsKt__StringsKt;
import p007a6.C0042f;

/* renamed from: t6.m */
/* loaded from: classes.dex */
public final /* synthetic */ class C6332m {

    /* renamed from: a */
    public static final Logger f21353a = Logger.getLogger("okio.Okio");

    /* renamed from: b */
    public static final boolean m24263b(AssertionError assertionError) {
        C0042f.m158e(assertionError, "<this>");
        if (assertionError.getCause() == null) {
            return false;
        }
        String message = assertionError.getMessage();
        return message != null ? StringsKt__StringsKt.m20451C(message, "getsockname failed", false, 2, null) : false;
    }

    /* renamed from: c */
    public static final InterfaceC6340u m24264c(Socket socket) throws IOException {
        C0042f.m158e(socket, "<this>");
        C6341v c6341v = new C6341v(socket);
        OutputStream outputStream = socket.getOutputStream();
        C0042f.m157d(outputStream, "getOutputStream(...)");
        return c6341v.m24178z(new C6335p(outputStream, c6341v));
    }

    /* renamed from: d */
    public static final InterfaceC6342w m24265d(File file) {
        C0042f.m158e(file, "<this>");
        return new C6330k(new FileInputStream(file), C6343x.f21381e);
    }

    /* renamed from: e */
    public static final InterfaceC6342w m24266e(InputStream inputStream) {
        C0042f.m158e(inputStream, "<this>");
        return new C6330k(inputStream, new C6343x());
    }

    /* renamed from: f */
    public static final InterfaceC6342w m24267f(Socket socket) throws IOException {
        C0042f.m158e(socket, "<this>");
        C6341v c6341v = new C6341v(socket);
        InputStream inputStream = socket.getInputStream();
        C0042f.m157d(inputStream, "getInputStream(...)");
        return c6341v.m24173A(new C6330k(inputStream, c6341v));
    }
}
