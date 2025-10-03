package androidx.loader.content;

import android.content.Context;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import p021c0.C0695a;

/* renamed from: androidx.loader.content.b */
/* loaded from: classes.dex */
public class C0406b<D> {
    Context mContext;
    int mId;
    b<D> mListener;
    a<D> mOnLoadCanceledListener;
    boolean mStarted = false;
    boolean mAbandoned = false;
    boolean mReset = true;
    boolean mContentChanged = false;
    boolean mProcessingChange = false;

    /* renamed from: androidx.loader.content.b$a */
    public interface a<D> {
    }

    /* renamed from: androidx.loader.content.b$b */
    public interface b<D> {
        /* renamed from: a */
        void mo2134a(C0406b<D> c0406b, D d9);
    }

    public C0406b(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    public boolean cancelLoad() {
        return onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public String dataToString(D d9) {
        StringBuilder sb = new StringBuilder(64);
        C0695a.m3459a(d9, sb);
        sb.append("}");
        return sb.toString();
    }

    public void deliverCancellation() {
    }

    public void deliverResult(D d9) {
        b<D> bVar = this.mListener;
        if (bVar != null) {
            bVar.mo2134a(this, d9);
        }
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mListener=");
        printWriter.println(this.mListener);
        if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.mStarted);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.mContentChanged);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.mProcessingChange);
        }
        if (this.mAbandoned || this.mReset) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.mAbandoned);
            printWriter.print(" mReset=");
            printWriter.println(this.mReset);
        }
    }

    public void forceLoad() {
        onForceLoad();
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public void onAbandon() {
    }

    public boolean onCancelLoad() {
        return false;
    }

    public void onContentChanged() {
        if (this.mStarted) {
            forceLoad();
        } else {
            this.mContentChanged = true;
        }
    }

    public void onForceLoad() {
    }

    public void onReset() {
    }

    public void onStartLoading() {
    }

    public void onStopLoading() {
    }

    public void registerListener(int i9, b<D> bVar) {
        if (this.mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mListener = bVar;
        this.mId = i9;
    }

    public void registerOnLoadCanceledListener(a<D> aVar) {
    }

    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if (this.mProcessingChange) {
            onContentChanged();
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    public boolean takeContentChanged() {
        boolean z8 = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= z8;
        return z8;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        C0695a.m3459a(this, sb);
        sb.append(" id=");
        sb.append(this.mId);
        sb.append("}");
        return sb.toString();
    }

    public void unregisterListener(b<D> bVar) {
        b<D> bVar2 = this.mListener;
        if (bVar2 == null) {
            throw new IllegalStateException("No listener register");
        }
        if (bVar2 != bVar) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mListener = null;
    }

    public void unregisterOnLoadCanceledListener(a<D> aVar) {
        throw new IllegalStateException("No listener register");
    }
}
