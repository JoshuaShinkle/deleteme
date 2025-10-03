package de.measite.smack;

import android.util.Log;
import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.util.C5613g;
import org.jivesoftware.smack.util.C5614h;
import org.jivesoftware.smack.util.InterfaceC5615i;
import org.jivesoftware.smack.util.InterfaceC5617k;
import p222v6.C6488a;
import p222v6.InterfaceC6490c;
import p231w6.InterfaceC6547b;

/* loaded from: classes2.dex */
public class AndroidDebugger implements InterfaceC6547b {
    public static boolean printInterpreted = false;
    private XMPPConnection connection;
    private Reader reader;
    private InterfaceC5615i readerListener;
    private Writer writer;
    private InterfaceC5617k writerListener;
    private InterfaceC5583c listener = null;
    private InterfaceC6490c connListener = null;

    public AndroidDebugger(XMPPConnection xMPPConnection, Writer writer, Reader reader) {
        this.connection = xMPPConnection;
        this.writer = writer;
        this.reader = reader;
        createDebug();
    }

    private void createDebug() {
        C5613g c5613g = new C5613g(this.reader);
        InterfaceC5615i interfaceC5615i = new InterfaceC5615i() { // from class: de.measite.smack.AndroidDebugger.1
            @Override // org.jivesoftware.smack.util.InterfaceC5615i
            public void read(String str) {
                Log.v("SMACK", "RCV (" + AndroidDebugger.this.connection.m22009t() + "): " + str);
            }
        };
        this.readerListener = interfaceC5615i;
        c5613g.m22331f(interfaceC5615i);
        C5614h c5614h = new C5614h(this.writer);
        InterfaceC5617k interfaceC5617k = new InterfaceC5617k() { // from class: de.measite.smack.AndroidDebugger.2
            @Override // org.jivesoftware.smack.util.InterfaceC5617k
            public void write(String str) {
                Log.v("SMACK", "SENT (" + AndroidDebugger.this.connection.m22009t() + "): " + str);
            }
        };
        this.writerListener = interfaceC5617k;
        c5614h.m22333f(interfaceC5617k);
        this.reader = c5613g;
        this.writer = c5614h;
        this.listener = new InterfaceC5583c() { // from class: de.measite.smack.AndroidDebugger.3
            @Override // org.jivesoftware.smack.InterfaceC5583c
            public void processPacket(AbstractC5594b abstractC5594b) {
                if (AndroidDebugger.printInterpreted) {
                    Log.d("SMACK", "RCV PKT (" + AndroidDebugger.this.connection.m22009t() + "): " + ((Object) abstractC5594b.mo22057u()));
                }
            }
        };
        this.connListener = new C6488a() { // from class: de.measite.smack.AndroidDebugger.4
            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void connectionClosed() {
                Log.v("SMACK", "Connection closed (" + AndroidDebugger.this.connection.m22009t() + ")");
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void connectionClosedOnError(Exception exc) {
                Log.v("SMACK", "Connection closed due to an exception (" + AndroidDebugger.this.connection.m22009t() + ")");
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void reconnectingIn(int i9) {
                Log.v("SMACK", "Connection (" + AndroidDebugger.this.connection.m22009t() + ") will reconnect in " + i9);
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void reconnectionFailed(Exception exc) {
                Log.v("SMACK", "Reconnection failed due to an exception (" + AndroidDebugger.this.connection.m22009t() + ")");
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void reconnectionSuccessful() {
                Log.v("SMACK", "Connection reconnected (" + AndroidDebugger.this.connection.m22009t() + ")");
            }
        };
    }

    @Override // p231w6.InterfaceC6547b
    public Reader getReader() {
        return this.reader;
    }

    @Override // p231w6.InterfaceC6547b
    public InterfaceC5583c getReaderListener() {
        return this.listener;
    }

    @Override // p231w6.InterfaceC6547b
    public Writer getWriter() {
        return this.writer;
    }

    @Override // p231w6.InterfaceC6547b
    public InterfaceC5583c getWriterListener() {
        return null;
    }

    @Override // p231w6.InterfaceC6547b
    public Reader newConnectionReader(Reader reader) {
        ((C5613g) this.reader).m22332u(this.readerListener);
        C5613g c5613g = new C5613g(reader);
        c5613g.m22331f(this.readerListener);
        this.reader = c5613g;
        return c5613g;
    }

    @Override // p231w6.InterfaceC6547b
    public Writer newConnectionWriter(Writer writer) {
        ((C5614h) this.writer).m22335v(this.writerListener);
        C5614h c5614h = new C5614h(writer);
        c5614h.m22333f(this.writerListener);
        this.writer = c5614h;
        return c5614h;
    }

    @Override // p231w6.InterfaceC6547b
    public void userHasLogged(String str) {
        Log.d("SMACK", "User logged (" + this.connection.m22009t() + "): " + str + "@" + this.connection.m21966C() + ":" + this.connection.m22014z());
        this.connection.m21992c(this.connListener);
    }
}
