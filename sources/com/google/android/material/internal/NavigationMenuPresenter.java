package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.view.menu.SubMenuC0148r;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.C3476R;
import java.util.ArrayList;
import p042d0.C4628h0;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public class NavigationMenuPresenter implements InterfaceC0143m {
    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HEADER = "android:menu:header";
    private static final String STATE_HIERARCHY = "android:menu:list";
    NavigationMenuAdapter adapter;
    private InterfaceC0143m.a callback;
    LinearLayout headerLayout;
    ColorStateList iconTintList;

    /* renamed from: id */
    private int f15376id;
    Drawable itemBackground;
    int itemHorizontalPadding;
    int itemIconPadding;
    LayoutInflater layoutInflater;
    C0137g menu;
    private NavigationMenuView menuView;
    final View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.google.android.material.internal.NavigationMenuPresenter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NavigationMenuPresenter.this.setUpdateSuspended(true);
            C0139i itemData = ((NavigationMenuItemView) view).getItemData();
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            boolean zPerformItemAction = navigationMenuPresenter.menu.performItemAction(itemData, navigationMenuPresenter, 0);
            if (itemData != null && itemData.isCheckable() && zPerformItemAction) {
                NavigationMenuPresenter.this.adapter.setCheckedItem(itemData);
            }
            NavigationMenuPresenter.this.setUpdateSuspended(false);
            NavigationMenuPresenter.this.updateMenuView(false);
        }
    };
    int paddingSeparator;
    private int paddingTopDefault;
    int textAppearance;
    boolean textAppearanceSet;
    ColorStateList textColor;

    public static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    public class NavigationMenuAdapter extends RecyclerView.AbstractC0446g<ViewHolder> {
        private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
        private static final String STATE_CHECKED_ITEM = "android:menu:checked";
        private static final int VIEW_TYPE_HEADER = 3;
        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SEPARATOR = 2;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private C0139i checkedItem;
        private final ArrayList<NavigationMenuItem> items = new ArrayList<>();
        private boolean updateSuspended;

        public NavigationMenuAdapter() {
            prepareMenuItems();
        }

        private void appendTransparentIconIfMissing(int i9, int i10) {
            while (i9 < i10) {
                ((NavigationMenuTextItem) this.items.get(i9)).needsEmptyIcon = true;
                i9++;
            }
        }

        private void prepareMenuItems() {
            if (this.updateSuspended) {
                return;
            }
            boolean z8 = true;
            this.updateSuspended = true;
            this.items.clear();
            this.items.add(new NavigationMenuHeaderItem());
            int size = NavigationMenuPresenter.this.menu.getVisibleItems().size();
            int i9 = -1;
            int i10 = 0;
            boolean z9 = false;
            int size2 = 0;
            while (i10 < size) {
                C0139i c0139i = NavigationMenuPresenter.this.menu.getVisibleItems().get(i10);
                if (c0139i.isChecked()) {
                    setCheckedItem(c0139i);
                }
                if (c0139i.isCheckable()) {
                    c0139i.m540t(false);
                }
                if (c0139i.hasSubMenu()) {
                    SubMenu subMenu = c0139i.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i10 != 0) {
                            this.items.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.paddingSeparator, 0));
                        }
                        this.items.add(new NavigationMenuTextItem(c0139i));
                        int size3 = this.items.size();
                        int size4 = subMenu.size();
                        int i11 = 0;
                        boolean z10 = false;
                        while (i11 < size4) {
                            C0139i c0139i2 = (C0139i) subMenu.getItem(i11);
                            if (c0139i2.isVisible()) {
                                if (!z10 && c0139i2.getIcon() != null) {
                                    z10 = z8;
                                }
                                if (c0139i2.isCheckable()) {
                                    c0139i2.m540t(false);
                                }
                                if (c0139i.isChecked()) {
                                    setCheckedItem(c0139i);
                                }
                                this.items.add(new NavigationMenuTextItem(c0139i2));
                            }
                            i11++;
                            z8 = true;
                        }
                        if (z10) {
                            appendTransparentIconIfMissing(size3, this.items.size());
                        }
                    }
                } else {
                    int groupId = c0139i.getGroupId();
                    if (groupId != i9) {
                        size2 = this.items.size();
                        z9 = c0139i.getIcon() != null;
                        if (i10 != 0) {
                            size2++;
                            ArrayList<NavigationMenuItem> arrayList = this.items;
                            int i12 = NavigationMenuPresenter.this.paddingSeparator;
                            arrayList.add(new NavigationMenuSeparatorItem(i12, i12));
                        }
                    } else if (!z9 && c0139i.getIcon() != null) {
                        appendTransparentIconIfMissing(size2, this.items.size());
                        z9 = true;
                    }
                    NavigationMenuTextItem navigationMenuTextItem = new NavigationMenuTextItem(c0139i);
                    navigationMenuTextItem.needsEmptyIcon = z9;
                    this.items.add(navigationMenuTextItem);
                    i9 = groupId;
                }
                i10++;
                z8 = true;
            }
            this.updateSuspended = false;
        }

        public Bundle createInstanceState() {
            Bundle bundle = new Bundle();
            C0139i c0139i = this.checkedItem;
            if (c0139i != null) {
                bundle.putInt(STATE_CHECKED_ITEM, c0139i.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.items.size();
            for (int i9 = 0; i9 < size; i9++) {
                NavigationMenuItem navigationMenuItem = this.items.get(i9);
                if (navigationMenuItem instanceof NavigationMenuTextItem) {
                    C0139i menuItem = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem();
                    View actionView = menuItem != null ? menuItem.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(menuItem.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray(STATE_ACTION_VIEWS, sparseArray);
            return bundle;
        }

        public C0139i getCheckedItem() {
            return this.checkedItem;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public int getItemCount() {
            return this.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public long getItemId(int i9) {
            return i9;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public int getItemViewType(int i9) {
            NavigationMenuItem navigationMenuItem = this.items.get(i9);
            if (navigationMenuItem instanceof NavigationMenuSeparatorItem) {
                return 2;
            }
            if (navigationMenuItem instanceof NavigationMenuHeaderItem) {
                return 3;
            }
            if (navigationMenuItem instanceof NavigationMenuTextItem) {
                return ((NavigationMenuTextItem) navigationMenuItem).getMenuItem().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        public void restoreInstanceState(Bundle bundle) {
            C0139i menuItem;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            C0139i menuItem2;
            int i9 = bundle.getInt(STATE_CHECKED_ITEM, 0);
            if (i9 != 0) {
                this.updateSuspended = true;
                int size = this.items.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size) {
                        break;
                    }
                    NavigationMenuItem navigationMenuItem = this.items.get(i10);
                    if ((navigationMenuItem instanceof NavigationMenuTextItem) && (menuItem2 = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem()) != null && menuItem2.getItemId() == i9) {
                        setCheckedItem(menuItem2);
                        break;
                    }
                    i10++;
                }
                this.updateSuspended = false;
                prepareMenuItems();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(STATE_ACTION_VIEWS);
            if (sparseParcelableArray != null) {
                int size2 = this.items.size();
                for (int i11 = 0; i11 < size2; i11++) {
                    NavigationMenuItem navigationMenuItem2 = this.items.get(i11);
                    if ((navigationMenuItem2 instanceof NavigationMenuTextItem) && (menuItem = ((NavigationMenuTextItem) navigationMenuItem2).getMenuItem()) != null && (actionView = menuItem.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(menuItem.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void setCheckedItem(C0139i c0139i) {
            if (this.checkedItem == c0139i || !c0139i.isCheckable()) {
                return;
            }
            C0139i c0139i2 = this.checkedItem;
            if (c0139i2 != null) {
                c0139i2.setChecked(false);
            }
            this.checkedItem = c0139i;
            c0139i.setChecked(true);
        }

        public void setUpdateSuspended(boolean z8) {
            this.updateSuspended = z8;
        }

        public void update() {
            prepareMenuItems();
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public void onBindViewHolder(ViewHolder viewHolder, int i9) {
            int itemViewType = getItemViewType(i9);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    ((TextView) viewHolder.itemView).setText(((NavigationMenuTextItem) this.items.get(i9)).getMenuItem().getTitle());
                    return;
                } else {
                    if (itemViewType != 2) {
                        return;
                    }
                    NavigationMenuSeparatorItem navigationMenuSeparatorItem = (NavigationMenuSeparatorItem) this.items.get(i9);
                    viewHolder.itemView.setPadding(0, navigationMenuSeparatorItem.getPaddingTop(), 0, navigationMenuSeparatorItem.getPaddingBottom());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) viewHolder.itemView;
            navigationMenuItemView.setIconTintList(NavigationMenuPresenter.this.iconTintList);
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            if (navigationMenuPresenter.textAppearanceSet) {
                navigationMenuItemView.setTextAppearance(navigationMenuPresenter.textAppearance);
            }
            ColorStateList colorStateList = NavigationMenuPresenter.this.textColor;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = NavigationMenuPresenter.this.itemBackground;
            C4647u.m18534b0(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            NavigationMenuTextItem navigationMenuTextItem = (NavigationMenuTextItem) this.items.get(i9);
            navigationMenuItemView.setNeedsEmptyIcon(navigationMenuTextItem.needsEmptyIcon);
            navigationMenuItemView.setHorizontalPadding(NavigationMenuPresenter.this.itemHorizontalPadding);
            navigationMenuItemView.setIconPadding(NavigationMenuPresenter.this.itemIconPadding);
            navigationMenuItemView.initialize(navigationMenuTextItem.getMenuItem(), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i9) {
            if (i9 == 0) {
                NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
                return new NormalViewHolder(navigationMenuPresenter.layoutInflater, viewGroup, navigationMenuPresenter.onClickListener);
            }
            if (i9 == 1) {
                return new SubheaderViewHolder(NavigationMenuPresenter.this.layoutInflater, viewGroup);
            }
            if (i9 == 2) {
                return new SeparatorViewHolder(NavigationMenuPresenter.this.layoutInflater, viewGroup);
            }
            if (i9 != 3) {
                return null;
            }
            return new HeaderViewHolder(NavigationMenuPresenter.this.headerLayout);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public void onViewRecycled(ViewHolder viewHolder) {
            if (viewHolder instanceof NormalViewHolder) {
                ((NavigationMenuItemView) viewHolder.itemView).recycle();
            }
        }
    }

    public static class NavigationMenuHeaderItem implements NavigationMenuItem {
    }

    public interface NavigationMenuItem {
    }

    public static class NavigationMenuSeparatorItem implements NavigationMenuItem {
        private final int paddingBottom;
        private final int paddingTop;

        public NavigationMenuSeparatorItem(int i9, int i10) {
            this.paddingTop = i9;
            this.paddingBottom = i10;
        }

        public int getPaddingBottom() {
            return this.paddingBottom;
        }

        public int getPaddingTop() {
            return this.paddingTop;
        }
    }

    public static class NavigationMenuTextItem implements NavigationMenuItem {
        private final C0139i menuItem;
        boolean needsEmptyIcon;

        public NavigationMenuTextItem(C0139i c0139i) {
            this.menuItem = c0139i;
        }

        public C0139i getMenuItem() {
            return this.menuItem;
        }
    }

    public static class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(C3476R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    public static class SeparatorViewHolder extends ViewHolder {
        public SeparatorViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(C3476R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    public static class SubheaderViewHolder extends ViewHolder {
        public SubheaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(C3476R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    public static abstract class ViewHolder extends RecyclerView.AbstractC0442c0 {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public void addHeaderView(View view) {
        this.headerLayout.addView(view);
        NavigationMenuView navigationMenuView = this.menuView;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean collapseItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    public void dispatchApplyWindowInsets(C4628h0 c4628h0) {
        int iM18439g = c4628h0.m18439g();
        if (this.paddingTopDefault != iM18439g) {
            this.paddingTopDefault = iM18439g;
            if (this.headerLayout.getChildCount() == 0) {
                NavigationMenuView navigationMenuView = this.menuView;
                navigationMenuView.setPadding(0, this.paddingTopDefault, 0, navigationMenuView.getPaddingBottom());
            }
        }
        C4647u.m18537d(this.headerLayout, c4628h0);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean expandItemActionView(C0137g c0137g, C0139i c0139i) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean flagActionItems() {
        return false;
    }

    public C0139i getCheckedItem() {
        return this.adapter.getCheckedItem();
    }

    public int getHeaderCount() {
        return this.headerLayout.getChildCount();
    }

    public View getHeaderView(int i9) {
        return this.headerLayout.getChildAt(i9);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public int getId() {
        return this.f15376id;
    }

    public Drawable getItemBackground() {
        return this.itemBackground;
    }

    public int getItemHorizontalPadding() {
        return this.itemHorizontalPadding;
    }

    public int getItemIconPadding() {
        return this.itemIconPadding;
    }

    public ColorStateList getItemTextColor() {
        return this.textColor;
    }

    public ColorStateList getItemTintList() {
        return this.iconTintList;
    }

    public InterfaceC0144n getMenuView(ViewGroup viewGroup) {
        if (this.menuView == null) {
            this.menuView = (NavigationMenuView) this.layoutInflater.inflate(C3476R.layout.design_navigation_menu, viewGroup, false);
            if (this.adapter == null) {
                this.adapter = new NavigationMenuAdapter();
            }
            this.headerLayout = (LinearLayout) this.layoutInflater.inflate(C3476R.layout.design_navigation_item_header, (ViewGroup) this.menuView, false);
            this.menuView.setAdapter(this.adapter);
        }
        return this.menuView;
    }

    public View inflateHeaderView(int i9) {
        View viewInflate = this.layoutInflater.inflate(i9, (ViewGroup) this.headerLayout, false);
        addHeaderView(viewInflate);
        return viewInflate;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void initForMenu(Context context, C0137g c0137g) {
        this.layoutInflater = LayoutInflater.from(context);
        this.menu = c0137g;
        this.paddingSeparator = context.getResources().getDimensionPixelOffset(C3476R.dimen.design_navigation_separator_vertical_padding);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onCloseMenu(C0137g c0137g, boolean z8) {
        InterfaceC0143m.a aVar = this.callback;
        if (aVar != null) {
            aVar.onCloseMenu(c0137g, z8);
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(STATE_HIERARCHY);
            if (sparseParcelableArray != null) {
                this.menuView.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle(STATE_ADAPTER);
            if (bundle2 != null) {
                this.adapter.restoreInstanceState(bundle2);
            }
            SparseArray<Parcelable> sparseParcelableArray2 = bundle.getSparseParcelableArray(STATE_HEADER);
            if (sparseParcelableArray2 != null) {
                this.headerLayout.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (this.menuView != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.menuView.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray(STATE_HIERARCHY, sparseArray);
        }
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            bundle.putBundle(STATE_ADAPTER, navigationMenuAdapter.createInstanceState());
        }
        if (this.headerLayout != null) {
            SparseArray<Parcelable> sparseArray2 = new SparseArray<>();
            this.headerLayout.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray(STATE_HEADER, sparseArray2);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) {
        return false;
    }

    public void removeHeaderView(View view) {
        this.headerLayout.removeView(view);
        if (this.headerLayout.getChildCount() == 0) {
            NavigationMenuView navigationMenuView = this.menuView;
            navigationMenuView.setPadding(0, this.paddingTopDefault, 0, navigationMenuView.getPaddingBottom());
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void setCallback(InterfaceC0143m.a aVar) {
        this.callback = aVar;
    }

    public void setCheckedItem(C0139i c0139i) {
        this.adapter.setCheckedItem(c0139i);
    }

    public void setId(int i9) {
        this.f15376id = i9;
    }

    public void setItemBackground(Drawable drawable) {
        this.itemBackground = drawable;
        updateMenuView(false);
    }

    public void setItemHorizontalPadding(int i9) {
        this.itemHorizontalPadding = i9;
        updateMenuView(false);
    }

    public void setItemIconPadding(int i9) {
        this.itemIconPadding = i9;
        updateMenuView(false);
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.iconTintList = colorStateList;
        updateMenuView(false);
    }

    public void setItemTextAppearance(int i9) {
        this.textAppearance = i9;
        this.textAppearanceSet = true;
        updateMenuView(false);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.textColor = colorStateList;
        updateMenuView(false);
    }

    public void setUpdateSuspended(boolean z8) {
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.setUpdateSuspended(z8);
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void updateMenuView(boolean z8) {
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.update();
        }
    }
}
