package com.google.android.gms.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.firebase.iid.MessengerIpcClient;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
final class zzt implements ServiceConnection {
    int state;
    final Messenger zzch;
    zzy zzci;
    final Queue<zzz<?>> zzcj;
    final SparseArray<zzz<?>> zzck;
    final /* synthetic */ zzr zzcl;

    private zzt(zzr zzrVar) {
        this.zzcl = zzrVar;
        this.state = 0;
        this.zzch = new Messenger(new com.google.android.gms.internal.gcm.zzj(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.android.gms.iid.zzu
            private final zzt zzcm;

            {
                this.zzcm = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zzcm.zzd(message);
            }
        }));
        this.zzcj = new ArrayDeque();
        this.zzck = new SparseArray<>();
    }

    private final void zzt() {
        this.zzcl.zzce.execute(new Runnable(this) { // from class: com.google.android.gms.iid.zzw
            private final zzt zzcm;

            {
                this.zzcm = this;
            }

            @Override // java.lang.Runnable
            public final void run() throws RemoteException {
                final zzt zztVar = this.zzcm;
                while (true) {
                    synchronized (zztVar) {
                        if (zztVar.state != 2) {
                            return;
                        }
                        if (zztVar.zzcj.isEmpty()) {
                            zztVar.zzu();
                            return;
                        }
                        final zzz<?> zzzVarPoll = zztVar.zzcj.poll();
                        zztVar.zzck.put(zzzVarPoll.zzcp, zzzVarPoll);
                        zztVar.zzcl.zzce.schedule(new Runnable(zztVar, zzzVarPoll) { // from class: com.google.android.gms.iid.zzx
                            private final zzt zzcm;
                            private final zzz zzcn;

                            {
                                this.zzcm = zztVar;
                                this.zzcn = zzzVarPoll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzcm.zzg(this.zzcn.zzcp);
                            }
                        }, 30L, TimeUnit.SECONDS);
                        if (Log.isLoggable("MessengerIpcClient", 3)) {
                            String strValueOf = String.valueOf(zzzVarPoll);
                            StringBuilder sb = new StringBuilder(strValueOf.length() + 8);
                            sb.append("Sending ");
                            sb.append(strValueOf);
                            Log.d("MessengerIpcClient", sb.toString());
                        }
                        Context context = zztVar.zzcl.zzl;
                        Messenger messenger = zztVar.zzch;
                        Message messageObtain = Message.obtain();
                        messageObtain.what = zzzVarPoll.what;
                        messageObtain.arg1 = zzzVarPoll.zzcp;
                        messageObtain.replyTo = messenger;
                        Bundle bundle = new Bundle();
                        bundle.putBoolean(MessengerIpcClient.KEY_ONE_WAY, zzzVarPoll.zzw());
                        bundle.putString(MessengerIpcClient.KEY_PACKAGE, context.getPackageName());
                        bundle.putBundle("data", zzzVarPoll.zzcr);
                        messageObtain.setData(bundle);
                        try {
                            zzy zzyVar = zztVar.zzci;
                            Messenger messenger2 = zzyVar.zzad;
                            if (messenger2 != null) {
                                messenger2.send(messageObtain);
                            } else {
                                MessengerCompat messengerCompat = zzyVar.zzco;
                                if (messengerCompat == null) {
                                    throw new IllegalStateException("Both messengers are null");
                                }
                                messengerCompat.send(messageObtain);
                            }
                        } catch (RemoteException e9) {
                            zztVar.zzd(2, e9.getMessage());
                        }
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zzd(0, "Null service connection");
            return;
        }
        try {
            this.zzci = new zzy(iBinder);
            this.state = 2;
            zzt();
        } catch (RemoteException e9) {
            zzd(0, e9.getMessage());
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zzd(2, "Service disconnected");
    }

    public final boolean zzd(Message message) {
        int i9 = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i9);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            zzz<?> zzzVar = this.zzck.get(i9);
            if (zzzVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i9);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zzck.remove(i9);
            zzu();
            Bundle data = message.getData();
            if (data.getBoolean(MessengerIpcClient.KEY_UNSUPPORTED, false)) {
                zzzVar.zzd(new zzaa(4, "Not supported by GmsCore"));
            } else {
                zzzVar.zzh(data);
            }
            return true;
        }
    }

    public final synchronized boolean zze(zzz zzzVar) {
        int i9 = this.state;
        if (i9 == 0) {
            this.zzcj.add(zzzVar);
            Preconditions.checkState(this.state == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.state = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            if (ConnectionTracker.getInstance().bindService(this.zzcl.zzl, intent, this, 1)) {
                this.zzcl.zzce.schedule(new Runnable(this) { // from class: com.google.android.gms.iid.zzv
                    private final zzt zzcm;

                    {
                        this.zzcm = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzcm.zzv();
                    }
                }, 30L, TimeUnit.SECONDS);
            } else {
                zzd(0, "Unable to bind to service");
            }
            return true;
        }
        if (i9 == 1) {
            this.zzcj.add(zzzVar);
            return true;
        }
        if (i9 == 2) {
            this.zzcj.add(zzzVar);
            zzt();
            return true;
        }
        if (i9 != 3 && i9 != 4) {
            int i10 = this.state;
            StringBuilder sb = new StringBuilder(26);
            sb.append("Unknown state: ");
            sb.append(i10);
            throw new IllegalStateException(sb.toString());
        }
        return false;
    }

    public final synchronized void zzg(int i9) {
        zzz<?> zzzVar = this.zzck.get(i9);
        if (zzzVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i9);
            Log.w("MessengerIpcClient", sb.toString());
            this.zzck.remove(i9);
            zzzVar.zzd(new zzaa(3, "Timed out waiting for response"));
            zzu();
        }
    }

    public final synchronized void zzu() {
        if (this.state == 2 && this.zzcj.isEmpty() && this.zzck.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker.getInstance().unbindService(this.zzcl.zzl, this);
        }
    }

    public final synchronized void zzv() {
        if (this.state == 1) {
            zzd(1, "Timed out while binding");
        }
    }

    public final synchronized void zzd(int i9, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", strValueOf.length() != 0 ? "Disconnected: ".concat(strValueOf) : new String("Disconnected: "));
        }
        int i10 = this.state;
        if (i10 == 0) {
            throw new IllegalStateException();
        }
        if (i10 != 1 && i10 != 2) {
            if (i10 != 3) {
                if (i10 == 4) {
                    return;
                }
                int i11 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i11);
                throw new IllegalStateException(sb.toString());
            }
            this.state = 4;
            return;
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.state = 4;
        ConnectionTracker.getInstance().unbindService(this.zzcl.zzl, this);
        zzaa zzaaVar = new zzaa(i9, str);
        Iterator<zzz<?>> it = this.zzcj.iterator();
        while (it.hasNext()) {
            it.next().zzd(zzaaVar);
        }
        this.zzcj.clear();
        for (int i12 = 0; i12 < this.zzck.size(); i12++) {
            this.zzck.valueAt(i12).zzd(zzaaVar);
        }
        this.zzck.clear();
    }
}
