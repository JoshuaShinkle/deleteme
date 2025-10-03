package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class PlaybackStateCompatApi22 {
    private PlaybackStateCompatApi22() {
    }

    public static Bundle getExtras(Object obj) {
        return ((PlaybackState) obj).getExtras();
    }

    public static Object newInstance(int i9, long j9, long j10, float f9, long j11, CharSequence charSequence, long j12, List<Object> list, long j13, Bundle bundle) {
        PlaybackState.Builder builder = new PlaybackState.Builder();
        builder.setState(i9, j9, f9, j12);
        builder.setBufferedPosition(j10);
        builder.setActions(j11);
        builder.setErrorMessage(charSequence);
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            builder.addCustomAction((PlaybackState.CustomAction) it.next());
        }
        builder.setActiveQueueItemId(j13);
        builder.setExtras(bundle);
        return builder.build();
    }
}
