package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes2.dex */
final class zzfy extends Thread {
    private final Object zza;
    private final BlockingQueue<zzfz<?>> zzb;
    private boolean zzc = false;
    private final /* synthetic */ zzfu zzd;

    public zzfy(zzfu zzfuVar, String str, BlockingQueue<zzfz<?>> blockingQueue) {
        this.zzd = zzfuVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zza = new Object();
        this.zzb = blockingQueue;
        setName(str);
    }

    private final void zzb() {
        synchronized (this.zzd.zzg) {
            if (!this.zzc) {
                this.zzd.zzh.release();
                this.zzd.zzg.notifyAll();
                if (this == this.zzd.zza) {
                    zzfu.zza(this.zzd, null);
                } else if (this == this.zzd.zzb) {
                    zzfu.zzb(this.zzd, null);
                } else {
                    this.zzd.zzq().zze().zza("Current scheduler thread is neither worker nor network");
                }
                this.zzc = true;
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() throws InterruptedException {
        boolean z8 = false;
        while (!z8) {
            try {
                this.zzd.zzh.acquire();
                z8 = true;
            } catch (InterruptedException e9) {
                zza(e9);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzfz<?> zzfzVarPoll = this.zzb.poll();
                if (zzfzVarPoll == null) {
                    synchronized (this.zza) {
                        if (this.zzb.peek() == null && !this.zzd.zzi) {
                            try {
                                this.zza.wait(SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
                            } catch (InterruptedException e10) {
                                zza(e10);
                            }
                        }
                    }
                    synchronized (this.zzd.zzg) {
                        if (this.zzb.peek() == null) {
                            break;
                        }
                    }
                } else {
                    Process.setThreadPriority(zzfzVarPoll.zza ? threadPriority : 10);
                    zzfzVarPoll.run();
                }
            }
            if (this.zzd.zzs().zza(zzat.zzbs)) {
                zzb();
            }
        } finally {
            zzb();
        }
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.notifyAll();
        }
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzd.zzq().zzh().zza(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }
}
