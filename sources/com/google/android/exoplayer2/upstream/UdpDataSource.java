package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/* loaded from: classes.dex */
public final class UdpDataSource implements DataSource {
    public static final int DEAFULT_SOCKET_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_MAX_PACKET_SIZE = 2000;
    private InetAddress address;
    private final TransferListener<? super UdpDataSource> listener;
    private MulticastSocket multicastSocket;
    private boolean opened;
    private final DatagramPacket packet;
    private final byte[] packetBuffer;
    private int packetRemaining;
    private DatagramSocket socket;
    private InetSocketAddress socketAddress;
    private final int socketTimeoutMillis;
    private Uri uri;

    public static final class UdpDataSourceException extends IOException {
        public UdpDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public UdpDataSource(TransferListener<? super UdpDataSource> transferListener) {
        this(transferListener, 2000);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        this.uri = null;
        MulticastSocket multicastSocket = this.multicastSocket;
        if (multicastSocket != null) {
            try {
                multicastSocket.leaveGroup(this.address);
            } catch (IOException unused) {
            }
            this.multicastSocket = null;
        }
        DatagramSocket datagramSocket = this.socket;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.socket = null;
        }
        this.address = null;
        this.socketAddress = null;
        this.packetRemaining = 0;
        if (this.opened) {
            this.opened = false;
            TransferListener<? super UdpDataSource> transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onTransferEnd(this);
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        Uri uri = dataSpec.uri;
        this.uri = uri;
        String host = uri.getHost();
        int port = this.uri.getPort();
        try {
            this.address = InetAddress.getByName(host);
            this.socketAddress = new InetSocketAddress(this.address, port);
            if (this.address.isMulticastAddress()) {
                MulticastSocket multicastSocket = new MulticastSocket(this.socketAddress);
                this.multicastSocket = multicastSocket;
                multicastSocket.joinGroup(this.address);
                this.socket = this.multicastSocket;
            } else {
                this.socket = new DatagramSocket(this.socketAddress);
            }
            try {
                this.socket.setSoTimeout(this.socketTimeoutMillis);
                this.opened = true;
                TransferListener<? super UdpDataSource> transferListener = this.listener;
                if (transferListener == null) {
                    return -1L;
                }
                transferListener.onTransferStart(this, dataSpec);
                return -1L;
            } catch (SocketException e9) {
                throw new UdpDataSourceException(e9);
            }
        } catch (IOException e10) {
            throw new UdpDataSourceException(e10);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        if (i10 == 0) {
            return 0;
        }
        if (this.packetRemaining == 0) {
            try {
                this.socket.receive(this.packet);
                int length = this.packet.getLength();
                this.packetRemaining = length;
                TransferListener<? super UdpDataSource> transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onBytesTransferred(this, length);
                }
            } catch (IOException e9) {
                throw new UdpDataSourceException(e9);
            }
        }
        int length2 = this.packet.getLength();
        int i11 = this.packetRemaining;
        int iMin = Math.min(i11, i10);
        System.arraycopy(this.packetBuffer, length2 - i11, bArr, i9, iMin);
        this.packetRemaining -= iMin;
        return iMin;
    }

    public UdpDataSource(TransferListener<? super UdpDataSource> transferListener, int i9) {
        this(transferListener, i9, 8000);
    }

    public UdpDataSource(TransferListener<? super UdpDataSource> transferListener, int i9, int i10) {
        this.listener = transferListener;
        this.socketTimeoutMillis = i10;
        byte[] bArr = new byte[i9];
        this.packetBuffer = bArr;
        this.packet = new DatagramPacket(bArr, 0, i9);
    }
}
