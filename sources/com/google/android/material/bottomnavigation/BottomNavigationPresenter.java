package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.view.menu.SubMenuC0148r;

/* loaded from: classes2.dex */
public class BottomNavigationPresenter implements InterfaceC0143m {

    /* renamed from: id */
    private int f15375id;
    private C0137g menu;
    private BottomNavigationMenuView menuView;
    private boolean updateSuspended = false;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.material.bottomnavigation.BottomNavigationPresenter.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        };
        int selectedItemId;

        public SavedState() {
        }

        public SavedState(Parcel parcel) {
            this.selectedItemId = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            parcel.writeInt(this.selectedItemId);
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean collapseItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean expandItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public int getId() {
        return this.f15375id;
    }

    public InterfaceC0144n getMenuView(ViewGroup viewGroup) {
        return this.menuView;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void initForMenu(Context context, C0137g c0137g) {
        this.menu = c0137g;
        this.menuView.initialize(c0137g);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onCloseMenu(C0137g c0137g, boolean z8) {
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.menuView.tryRestoreSelectedItemId(((SavedState) parcelable).selectedItemId);
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.selectedItemId = this.menuView.getSelectedItemId();
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) {
        return false;
    }

    public void setBottomNavigationMenuView(BottomNavigationMenuView bottomNavigationMenuView) {
        this.menuView = bottomNavigationMenuView;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void setCallback(InterfaceC0143m.a aVar) {
    }

    public void setId(int i9) {
        this.f15375id = i9;
    }

    public void setUpdateSuspended(boolean z8) {
        this.updateSuspended = z8;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void updateMenuView(boolean z8) {
        if (this.updateSuspended) {
            return;
        }
        if (z8) {
            this.menuView.buildMenuView();
        } else {
            this.menuView.updateMenuView();
        }
    }
}
