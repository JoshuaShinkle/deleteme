package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class RepeatedFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> implements GeneratedMessage.BuilderParent {
    private List<SingleFieldBuilder<MType, BType, IType>> builders;
    private BuilderExternalList<MType, BType, IType> externalBuilderList;
    private MessageExternalList<MType, BType, IType> externalMessageList;
    private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;
    private boolean isClean;
    private boolean isMessagesListMutable;
    private List<MType> messages;
    private GeneratedMessage.BuilderParent parent;

    public static class BuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<BType> implements List<BType> {
        RepeatedFieldBuilder<MType, BType, IType> builder;

        public BuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> repeatedFieldBuilder) {
            this.builder = repeatedFieldBuilder;
        }

        public void incrementModCount() {
            ((AbstractList) this).modCount++;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.builder.getCount();
        }

        @Override // java.util.AbstractList, java.util.List
        public BType get(int i9) {
            return (BType) this.builder.getBuilder(i9);
        }
    }

    public static class MessageExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<MType> implements List<MType> {
        RepeatedFieldBuilder<MType, BType, IType> builder;

        public MessageExternalList(RepeatedFieldBuilder<MType, BType, IType> repeatedFieldBuilder) {
            this.builder = repeatedFieldBuilder;
        }

        public void incrementModCount() {
            ((AbstractList) this).modCount++;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.builder.getCount();
        }

        @Override // java.util.AbstractList, java.util.List
        public MType get(int i9) {
            return (MType) this.builder.getMessage(i9);
        }
    }

    public static class MessageOrBuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<IType> implements List<IType> {
        RepeatedFieldBuilder<MType, BType, IType> builder;

        public MessageOrBuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> repeatedFieldBuilder) {
            this.builder = repeatedFieldBuilder;
        }

        public void incrementModCount() {
            ((AbstractList) this).modCount++;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.builder.getCount();
        }

        @Override // java.util.AbstractList, java.util.List
        public IType get(int i9) {
            return (IType) this.builder.getMessageOrBuilder(i9);
        }
    }

    public RepeatedFieldBuilder(List<MType> list, boolean z8, GeneratedMessage.BuilderParent builderParent, boolean z9) {
        this.messages = list;
        this.isMessagesListMutable = z8;
        this.parent = builderParent;
        this.isClean = z9;
    }

    private void ensureBuilders() {
        if (this.builders == null) {
            this.builders = new ArrayList(this.messages.size());
            for (int i9 = 0; i9 < this.messages.size(); i9++) {
                this.builders.add(null);
            }
        }
    }

    private void ensureMutableMessageList() {
        if (this.isMessagesListMutable) {
            return;
        }
        this.messages = new ArrayList(this.messages);
        this.isMessagesListMutable = true;
    }

    private void incrementModCounts() {
        MessageExternalList<MType, BType, IType> messageExternalList = this.externalMessageList;
        if (messageExternalList != null) {
            messageExternalList.incrementModCount();
        }
        BuilderExternalList<MType, BType, IType> builderExternalList = this.externalBuilderList;
        if (builderExternalList != null) {
            builderExternalList.incrementModCount();
        }
        MessageOrBuilderExternalList<MType, BType, IType> messageOrBuilderExternalList = this.externalMessageOrBuilderList;
        if (messageOrBuilderExternalList != null) {
            messageOrBuilderExternalList.incrementModCount();
        }
    }

    private void onChanged() {
        GeneratedMessage.BuilderParent builderParent;
        if (!this.isClean || (builderParent = this.parent) == null) {
            return;
        }
        builderParent.markDirty();
        this.isClean = false;
    }

    public RepeatedFieldBuilder<MType, BType, IType> addAllMessages(Iterable<? extends MType> iterable) {
        Iterator<? extends MType> it = iterable.iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
        if (!(iterable instanceof Collection)) {
            ensureMutableMessageList();
            Iterator<? extends MType> it2 = iterable.iterator();
            while (it2.hasNext()) {
                addMessage(it2.next());
            }
        } else {
            if (((Collection) iterable).size() == 0) {
                return this;
            }
            ensureMutableMessageList();
            Iterator<? extends MType> it3 = iterable.iterator();
            while (it3.hasNext()) {
                addMessage(it3.next());
            }
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public BType addBuilder(MType mtype) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilder<MType, BType, IType> singleFieldBuilder = new SingleFieldBuilder<>(mtype, this, this.isClean);
        this.messages.add(null);
        this.builders.add(singleFieldBuilder);
        onChanged();
        incrementModCounts();
        return (BType) singleFieldBuilder.getBuilder();
    }

    public RepeatedFieldBuilder<MType, BType, IType> addMessage(MType mtype) {
        mtype.getClass();
        ensureMutableMessageList();
        this.messages.add(mtype);
        List<SingleFieldBuilder<MType, BType, IType>> list = this.builders;
        if (list != null) {
            list.add(null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public List<MType> build() {
        boolean z8;
        this.isClean = true;
        boolean z9 = this.isMessagesListMutable;
        if (!z9 && this.builders == null) {
            return this.messages;
        }
        if (!z9) {
            int i9 = 0;
            while (true) {
                if (i9 >= this.messages.size()) {
                    z8 = true;
                    break;
                }
                MType mtype = this.messages.get(i9);
                SingleFieldBuilder<MType, BType, IType> singleFieldBuilder = this.builders.get(i9);
                if (singleFieldBuilder != null && singleFieldBuilder.build() != mtype) {
                    z8 = false;
                    break;
                }
                i9++;
            }
            if (z8) {
                return this.messages;
            }
        }
        ensureMutableMessageList();
        for (int i10 = 0; i10 < this.messages.size(); i10++) {
            this.messages.set(i10, getMessage(i10, true));
        }
        List<MType> listUnmodifiableList = Collections.unmodifiableList(this.messages);
        this.messages = listUnmodifiableList;
        this.isMessagesListMutable = false;
        return listUnmodifiableList;
    }

    public void clear() {
        this.messages = Collections.emptyList();
        this.isMessagesListMutable = false;
        List<SingleFieldBuilder<MType, BType, IType>> list = this.builders;
        if (list != null) {
            for (SingleFieldBuilder<MType, BType, IType> singleFieldBuilder : list) {
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.dispose();
                }
            }
            this.builders = null;
        }
        onChanged();
        incrementModCounts();
    }

    public void dispose() {
        this.parent = null;
    }

    public BType getBuilder(int i9) {
        ensureBuilders();
        SingleFieldBuilder<MType, BType, IType> singleFieldBuilder = this.builders.get(i9);
        if (singleFieldBuilder == null) {
            SingleFieldBuilder<MType, BType, IType> singleFieldBuilder2 = new SingleFieldBuilder<>(this.messages.get(i9), this, this.isClean);
            this.builders.set(i9, singleFieldBuilder2);
            singleFieldBuilder = singleFieldBuilder2;
        }
        return (BType) singleFieldBuilder.getBuilder();
    }

    public List<BType> getBuilderList() {
        if (this.externalBuilderList == null) {
            this.externalBuilderList = new BuilderExternalList<>(this);
        }
        return this.externalBuilderList;
    }

    public int getCount() {
        return this.messages.size();
    }

    public MType getMessage(int i9) {
        return (MType) getMessage(i9, false);
    }

    public List<MType> getMessageList() {
        if (this.externalMessageList == null) {
            this.externalMessageList = new MessageExternalList<>(this);
        }
        return this.externalMessageList;
    }

    public IType getMessageOrBuilder(int i9) {
        SingleFieldBuilder<MType, BType, IType> singleFieldBuilder;
        List<SingleFieldBuilder<MType, BType, IType>> list = this.builders;
        if (list != null && (singleFieldBuilder = list.get(i9)) != null) {
            return (IType) singleFieldBuilder.getMessageOrBuilder();
        }
        return this.messages.get(i9);
    }

    public List<IType> getMessageOrBuilderList() {
        if (this.externalMessageOrBuilderList == null) {
            this.externalMessageOrBuilderList = new MessageOrBuilderExternalList<>(this);
        }
        return this.externalMessageOrBuilderList;
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.BuilderParent
    public void markDirty() {
        onChanged();
    }

    public void remove(int i9) {
        SingleFieldBuilder<MType, BType, IType> singleFieldBuilderRemove;
        ensureMutableMessageList();
        this.messages.remove(i9);
        List<SingleFieldBuilder<MType, BType, IType>> list = this.builders;
        if (list != null && (singleFieldBuilderRemove = list.remove(i9)) != null) {
            singleFieldBuilderRemove.dispose();
        }
        onChanged();
        incrementModCounts();
    }

    public RepeatedFieldBuilder<MType, BType, IType> setMessage(int i9, MType mtype) {
        SingleFieldBuilder<MType, BType, IType> singleFieldBuilder;
        mtype.getClass();
        ensureMutableMessageList();
        this.messages.set(i9, mtype);
        List<SingleFieldBuilder<MType, BType, IType>> list = this.builders;
        if (list != null && (singleFieldBuilder = list.set(i9, null)) != null) {
            singleFieldBuilder.dispose();
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    private MType getMessage(int i9, boolean z8) {
        List<SingleFieldBuilder<MType, BType, IType>> list = this.builders;
        if (list == null) {
            return this.messages.get(i9);
        }
        SingleFieldBuilder<MType, BType, IType> singleFieldBuilder = list.get(i9);
        return singleFieldBuilder == null ? this.messages.get(i9) : z8 ? (MType) singleFieldBuilder.build() : (MType) singleFieldBuilder.getMessage();
    }

    public RepeatedFieldBuilder<MType, BType, IType> addMessage(int i9, MType mtype) {
        mtype.getClass();
        ensureMutableMessageList();
        this.messages.add(i9, mtype);
        List<SingleFieldBuilder<MType, BType, IType>> list = this.builders;
        if (list != null) {
            list.add(i9, null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public BType addBuilder(int i9, MType mtype) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilder<MType, BType, IType> singleFieldBuilder = new SingleFieldBuilder<>(mtype, this, this.isClean);
        this.messages.add(i9, null);
        this.builders.add(i9, singleFieldBuilder);
        onChanged();
        incrementModCounts();
        return (BType) singleFieldBuilder.getBuilder();
    }
}
