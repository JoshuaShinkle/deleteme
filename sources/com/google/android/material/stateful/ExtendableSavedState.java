package com.google.android.material.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import p132m.C5308g;

/* loaded from: classes2.dex */
public class ExtendableSavedState extends AbsSavedState {
    public static final Parcelable.Creator<ExtendableSavedState> CREATOR = new Parcelable.ClassLoaderCreator<ExtendableSavedState>() { // from class: com.google.android.material.stateful.ExtendableSavedState.1
        @Override // android.os.Parcelable.Creator
        public ExtendableSavedState[] newArray(int i9) {
            return new ExtendableSavedState[i9];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public ExtendableSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ExtendableSavedState(parcel, classLoader);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Parcelable.Creator
        public ExtendableSavedState createFromParcel(Parcel parcel) {
            return new ExtendableSavedState(parcel, null);
        }
    };
    public final C5308g<String, Bundle> extendableStates;

    public String toString() {
        return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.extendableStates + "}";
    }

    @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        super.writeToParcel(parcel, i9);
        int size = this.extendableStates.size();
        parcel.writeInt(size);
        String[] strArr = new String[size];
        Bundle[] bundleArr = new Bundle[size];
        for (int i10 = 0; i10 < size; i10++) {
            strArr[i10] = this.extendableStates.m20751i(i10);
            bundleArr[i10] = this.extendableStates.m20755m(i10);
        }
        parcel.writeStringArray(strArr);
        parcel.writeTypedArray(bundleArr, 0);
    }

    public ExtendableSavedState(Parcelable parcelable) {
        super(parcelable);
        this.extendableStates = new C5308g<>();
    }

    private ExtendableSavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int i9 = parcel.readInt();
        String[] strArr = new String[i9];
        parcel.readStringArray(strArr);
        Bundle[] bundleArr = new Bundle[i9];
        parcel.readTypedArray(bundleArr, Bundle.CREATOR);
        this.extendableStates = new C5308g<>(i9);
        for (int i10 = 0; i10 < i9; i10++) {
            this.extendableStates.put(strArr[i10], bundleArr[i10]);
        }
    }
}
