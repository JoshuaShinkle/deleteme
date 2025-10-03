package p099i7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;

/* renamed from: i7.c */
/* loaded from: classes.dex */
public class C5060c {

    /* renamed from: h */
    public static C5060c f17465h;

    /* renamed from: a */
    public b f17468a;

    /* renamed from: b */
    public Thread f17469b;

    /* renamed from: c */
    public ServerSocket f17470c;

    /* renamed from: d */
    public final Map<String, Socket> f17471d = new ConcurrentHashMap();

    /* renamed from: e */
    public final List<String> f17472e = Collections.synchronizedList(new LinkedList());

    /* renamed from: f */
    public final Set<String> f17473f;

    /* renamed from: g */
    public static final Logger f17464g = Logger.getLogger(C5060c.class.getName());

    /* renamed from: i */
    public static boolean f17466i = true;

    /* renamed from: j */
    public static int f17467j = -7777;

    /* renamed from: i7.c$b */
    public class b implements Runnable {
        public b() {
        }

        /* renamed from: a */
        public final void m19827a(Socket socket) throws SmackException, IOException {
            boolean z8;
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            if (dataInputStream.read() != 5) {
                throw new SmackException("Only SOCKS5 supported");
            }
            int i9 = dataInputStream.read();
            byte[] bArr = new byte[i9];
            dataInputStream.readFully(bArr);
            byte[] bArr2 = new byte[2];
            bArr2[0] = 5;
            int i10 = 0;
            while (true) {
                if (i10 >= i9) {
                    z8 = false;
                    break;
                } else {
                    if (bArr[i10] == 0) {
                        z8 = true;
                        break;
                    }
                    i10++;
                }
            }
            if (!z8) {
                bArr2[1] = -1;
                dataOutputStream.write(bArr2);
                dataOutputStream.flush();
                throw new SmackException("Authentication method not supported");
            }
            bArr2[1] = 0;
            dataOutputStream.write(bArr2);
            dataOutputStream.flush();
            byte[] bArrM19828a = C5061d.m19828a(dataInputStream);
            String str = new String(bArrM19828a, 5, (int) bArrM19828a[4]);
            if (!C5060c.this.f17472e.contains(str)) {
                bArrM19828a[1] = 5;
                dataOutputStream.write(bArrM19828a);
                dataOutputStream.flush();
                throw new SmackException("Connection is not allowed");
            }
            bArrM19828a[1] = 0;
            dataOutputStream.write(bArrM19828a);
            dataOutputStream.flush();
            C5060c.this.f17471d.put(str, socket);
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            while (true) {
                Socket socket = null;
                try {
                } catch (SocketException | IOException unused) {
                } catch (Exception unused2) {
                    if (0 != 0) {
                        socket.close();
                    }
                }
                if (!C5060c.this.f17470c.isClosed() && !Thread.currentThread().isInterrupted()) {
                    m19827a(C5060c.this.f17470c.accept());
                }
                return;
            }
        }
    }

    public C5060c() {
        Set<String> setSynchronizedSet = Collections.synchronizedSet(new LinkedHashSet());
        this.f17473f = setSynchronizedSet;
        this.f17468a = new b();
        try {
            setSynchronizedSet.add(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException unused) {
        }
    }

    /* renamed from: d */
    public static int m19821d() {
        return f17467j;
    }

    /* renamed from: e */
    public static synchronized C5060c m19822e() {
        if (f17465h == null) {
            f17465h = new C5060c();
        }
        if (m19823f()) {
            f17465h.m19825h();
        }
        return f17465h;
    }

    /* renamed from: f */
    public static boolean m19823f() {
        return f17466i;
    }

    /* renamed from: g */
    public boolean m19824g() {
        return this.f17470c != null;
    }

    /* renamed from: h */
    public synchronized void m19825h() {
        if (m19824g()) {
            return;
        }
        try {
            if (m19821d() < 0) {
                int iAbs = Math.abs(m19821d());
                for (int i9 = 0; i9 < 65535 - iAbs; i9++) {
                    try {
                        this.f17470c = new ServerSocket(iAbs + i9);
                        break;
                    } catch (IOException unused) {
                    }
                }
            } else {
                this.f17470c = new ServerSocket(m19821d());
            }
            if (this.f17470c != null) {
                Thread thread = new Thread(this.f17468a);
                this.f17469b = thread;
                thread.start();
            }
        } catch (IOException e9) {
            f17464g.log(Level.SEVERE, "couldn't setup local SOCKS5 proxy on port " + m19821d(), (Throwable) e9);
        }
    }

    /* renamed from: i */
    public synchronized void m19826i() {
        if (m19824g()) {
            try {
                this.f17470c.close();
            } catch (IOException unused) {
            }
            Thread thread = this.f17469b;
            if (thread != null && thread.isAlive()) {
                try {
                    this.f17469b.interrupt();
                    this.f17469b.join();
                } catch (InterruptedException unused2) {
                }
            }
            this.f17469b = null;
            this.f17470c = null;
        }
    }
}
