package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeResolver;
import com.google.common.reflect.Types;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Beta
/* loaded from: classes2.dex */
public abstract class TypeToken<T> extends TypeCapture<T> implements Serializable {
    private final Type runtimeType;
    private transient TypeResolver typeResolver;

    public static class Bounds {
        private final Type[] bounds;
        private final boolean target;

        public Bounds(Type[] typeArr, boolean z8) {
            this.bounds = typeArr;
            this.target = z8;
        }

        public boolean isSubtypeOf(Type type) {
            for (Type type2 : this.bounds) {
                boolean zIsSubtypeOf = TypeToken.m17712of(type2).isSubtypeOf(type);
                boolean z8 = this.target;
                if (zIsSubtypeOf == z8) {
                    return z8;
                }
            }
            return !this.target;
        }

        public boolean isSupertypeOf(Type type) {
            TypeToken<?> typeTokenM17712of = TypeToken.m17712of(type);
            for (Type type2 : this.bounds) {
                boolean zIsSubtypeOf = typeTokenM17712of.isSubtypeOf(type2);
                boolean z8 = this.target;
                if (zIsSubtypeOf == z8) {
                    return z8;
                }
            }
            return !this.target;
        }
    }

    public static final class SimpleTypeToken<T> extends TypeToken<T> {
        private static final long serialVersionUID = 0;

        public SimpleTypeToken(Type type) {
            super(type);
        }
    }

    public static abstract class TypeCollector<K> {
        static final TypeCollector<TypeToken<?>> FOR_GENERIC_TYPE = new TypeCollector<TypeToken<?>>() { // from class: com.google.common.reflect.TypeToken.TypeCollector.1
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends TypeToken<?>> getInterfaces(TypeToken<?> typeToken) {
                return typeToken.getGenericInterfaces();
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(TypeToken<?> typeToken) {
                return typeToken.getRawType();
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public TypeToken<?> getSuperclass(TypeToken<?> typeToken) {
                return typeToken.getGenericSuperclass();
            }
        };
        static final TypeCollector<Class<?>> FOR_RAW_TYPE = new TypeCollector<Class<?>>() { // from class: com.google.common.reflect.TypeToken.TypeCollector.2
            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(Class<?> cls) {
                return cls;
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends Class<?>> getInterfaces(Class<?> cls) {
                return Arrays.asList(cls.getInterfaces());
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getSuperclass(Class<?> cls) {
                return cls.getSuperclass();
            }
        };

        public static class ForwardingTypeCollector<K> extends TypeCollector<K> {
            private final TypeCollector<K> delegate;

            public ForwardingTypeCollector(TypeCollector<K> typeCollector) {
                super();
                this.delegate = typeCollector;
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Iterable<? extends K> getInterfaces(K k9) {
                return this.delegate.getInterfaces(k9);
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public Class<?> getRawType(K k9) {
                return this.delegate.getRawType(k9);
            }

            @Override // com.google.common.reflect.TypeToken.TypeCollector
            public K getSuperclass(K k9) {
                return this.delegate.getSuperclass(k9);
            }
        }

        private TypeCollector() {
        }

        private static <K, V> ImmutableList<K> sortKeysByValue(final Map<K, V> map, final Comparator<? super V> comparator) {
            return (ImmutableList<K>) new Ordering<K>() { // from class: com.google.common.reflect.TypeToken.TypeCollector.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.collect.Ordering, java.util.Comparator
                public int compare(K k9, K k10) {
                    return comparator.compare(map.get(k9), map.get(k10));
                }
            }.immutableSortedCopy(map.keySet());
        }

        public final TypeCollector<K> classesOnly() {
            return new ForwardingTypeCollector<K>(this) { // from class: com.google.common.reflect.TypeToken.TypeCollector.3
                @Override // com.google.common.reflect.TypeToken.TypeCollector
                public ImmutableList<K> collectTypes(Iterable<? extends K> iterable) {
                    ImmutableList.Builder builder = ImmutableList.builder();
                    for (K k9 : iterable) {
                        if (!getRawType(k9).isInterface()) {
                            builder.add((ImmutableList.Builder) k9);
                        }
                    }
                    return super.collectTypes((Iterable) builder.build());
                }

                @Override // com.google.common.reflect.TypeToken.TypeCollector.ForwardingTypeCollector, com.google.common.reflect.TypeToken.TypeCollector
                public Iterable<? extends K> getInterfaces(K k9) {
                    return ImmutableSet.m17615of();
                }
            };
        }

        public final ImmutableList<K> collectTypes(K k9) {
            return collectTypes((Iterable) ImmutableList.m17574of(k9));
        }

        public abstract Iterable<? extends K> getInterfaces(K k9);

        public abstract Class<?> getRawType(K k9);

        public abstract K getSuperclass(K k9);

        public ImmutableList<K> collectTypes(Iterable<? extends K> iterable) {
            HashMap mapNewHashMap = Maps.newHashMap();
            Iterator<? extends K> it = iterable.iterator();
            while (it.hasNext()) {
                collectTypes(it.next(), mapNewHashMap);
            }
            return sortKeysByValue(mapNewHashMap, Ordering.natural().reverse());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        private int collectTypes(K k9, Map<? super K, Integer> map) {
            Integer num = map.get(k9);
            if (num != null) {
                return num.intValue();
            }
            boolean zIsInterface = getRawType(k9).isInterface();
            Iterator<? extends K> it = getInterfaces(k9).iterator();
            int iMax = zIsInterface;
            while (it.hasNext()) {
                iMax = Math.max(iMax, collectTypes(it.next(), map));
            }
            K superclass = getSuperclass(k9);
            int iMax2 = iMax;
            if (superclass != null) {
                iMax2 = Math.max(iMax, collectTypes(superclass, map));
            }
            int i9 = iMax2 + 1;
            map.put(k9, Integer.valueOf(i9));
            return i9;
        }
    }

    public enum TypeFilter implements Predicate<TypeToken<?>> {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD { // from class: com.google.common.reflect.TypeToken.TypeFilter.1
            @Override // com.google.common.base.Predicate
            public boolean apply(TypeToken<?> typeToken) {
                return ((((TypeToken) typeToken).runtimeType instanceof TypeVariable) || (((TypeToken) typeToken).runtimeType instanceof WildcardType)) ? false : true;
            }
        },
        INTERFACE_ONLY { // from class: com.google.common.reflect.TypeToken.TypeFilter.2
            @Override // com.google.common.base.Predicate
            public boolean apply(TypeToken<?> typeToken) {
                return typeToken.getRawType().isInterface();
            }
        }
    }

    private static Bounds any(Type[] typeArr) {
        return new Bounds(typeArr, true);
    }

    private TypeToken<? super T> boundAsSuperclass(Type type) {
        TypeToken<? super T> typeToken = (TypeToken<? super T>) m17712of(type);
        if (typeToken.getRawType().isInterface()) {
            return null;
        }
        return typeToken;
    }

    private ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] typeArr) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : typeArr) {
            TypeToken<?> typeTokenM17712of = m17712of(type);
            if (typeTokenM17712of.getRawType().isInterface()) {
                builder.add((ImmutableList.Builder) typeTokenM17712of);
            }
        }
        return builder.build();
    }

    private static Bounds every(Type[] typeArr) {
        return new Bounds(typeArr, false);
    }

    private TypeToken<? extends T> getArraySubtype(Class<?> cls) {
        return (TypeToken<? extends T>) m17712of(newArrayClassOrGenericArrayType(getComponentType().getSubtype(cls.getComponentType()).runtimeType));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeToken<? super T> getArraySupertype(Class<? super T> cls) {
        return (TypeToken<? super T>) m17712of(newArrayClassOrGenericArrayType(((TypeToken) Preconditions.checkNotNull(getComponentType(), "%s isn't a super type of %s", cls, this)).getSupertype(cls.getComponentType()).runtimeType));
    }

    private Type getOwnerTypeIfPresent() {
        Type type = this.runtimeType;
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getOwnerType();
        }
        if (type instanceof Class) {
            return ((Class) type).getEnclosingClass();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImmutableSet<Class<? super T>> getRawTypes() {
        final ImmutableSet.Builder builder = ImmutableSet.builder();
        new TypeVisitor() { // from class: com.google.common.reflect.TypeToken.4
            @Override // com.google.common.reflect.TypeVisitor
            public void visitClass(Class<?> cls) {
                builder.add((ImmutableSet.Builder) cls);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType genericArrayType) {
                builder.add((ImmutableSet.Builder) Types.getArrayClass(TypeToken.m17712of(genericArrayType.getGenericComponentType()).getRawType()));
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitParameterizedType(ParameterizedType parameterizedType) {
                builder.add((ImmutableSet.Builder) parameterizedType.getRawType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> typeVariable) {
                visit(typeVariable.getBounds());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType wildcardType) {
                visit(wildcardType.getUpperBounds());
            }
        }.visit(this.runtimeType);
        return builder.build();
    }

    private TypeToken<? extends T> getSubtypeFromLowerBounds(Class<?> cls, Type[] typeArr) {
        if (typeArr.length > 0) {
            return (TypeToken<? extends T>) m17712of(typeArr[0]).getSubtype(cls);
        }
        throw new IllegalArgumentException(cls + " isn't a subclass of " + this);
    }

    private TypeToken<? super T> getSupertypeFromUpperBounds(Class<? super T> cls, Type[] typeArr) {
        for (Type type : typeArr) {
            TypeToken<?> typeTokenM17712of = m17712of(type);
            if (typeTokenM17712of.isSubtypeOf(cls)) {
                return (TypeToken<? super T>) typeTokenM17712of.getSupertype(cls);
            }
        }
        throw new IllegalArgumentException(cls + " isn't a super type of " + this);
    }

    /* renamed from: is */
    private boolean m17710is(Type type) {
        if (this.runtimeType.equals(type)) {
            return true;
        }
        if (!(type instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcardType = (WildcardType) type;
        return every(wildcardType.getUpperBounds()).isSupertypeOf(this.runtimeType) && every(wildcardType.getLowerBounds()).isSubtypeOf(this.runtimeType);
    }

    private boolean isOwnedBySubtypeOf(Type type) {
        Iterator<TypeToken<? super T>> it = getTypes().iterator();
        while (it.hasNext()) {
            Type ownerTypeIfPresent = it.next().getOwnerTypeIfPresent();
            if (ownerTypeIfPresent != null && m17712of(ownerTypeIfPresent).isSubtypeOf(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSubtypeOfArrayType(GenericArrayType genericArrayType) {
        Type type = this.runtimeType;
        if (!(type instanceof Class)) {
            if (type instanceof GenericArrayType) {
                return m17712of(((GenericArrayType) type).getGenericComponentType()).isSubtypeOf(genericArrayType.getGenericComponentType());
            }
            return false;
        }
        Class cls = (Class) type;
        if (cls.isArray()) {
            return m17711of((Class) cls.getComponentType()).isSubtypeOf(genericArrayType.getGenericComponentType());
        }
        return false;
    }

    private boolean isSubtypeOfParameterizedType(ParameterizedType parameterizedType) {
        Class<? super Object> rawType = m17712of(parameterizedType).getRawType();
        if (!someRawTypeIsSubclassOf(rawType)) {
            return false;
        }
        TypeVariable<Class<? super Object>>[] typeParameters = rawType.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i9 = 0; i9 < typeParameters.length; i9++) {
            if (!resolveType(typeParameters[i9]).m17710is(actualTypeArguments[i9])) {
                return false;
            }
        }
        return Modifier.isStatic(((Class) parameterizedType.getRawType()).getModifiers()) || parameterizedType.getOwnerType() == null || isOwnedBySubtypeOf(parameterizedType.getOwnerType());
    }

    private boolean isSupertypeOfArray(GenericArrayType genericArrayType) {
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            return !cls.isArray() ? cls.isAssignableFrom(Object[].class) : m17712of(genericArrayType.getGenericComponentType()).isSubtypeOf(cls.getComponentType());
        }
        if (type instanceof GenericArrayType) {
            return m17712of(genericArrayType.getGenericComponentType()).isSubtypeOf(((GenericArrayType) this.runtimeType).getGenericComponentType());
        }
        return false;
    }

    private boolean isWrapper() {
        return Primitives.allWrapperTypes().contains(this.runtimeType);
    }

    private static Type newArrayClassOrGenericArrayType(Type type) {
        return Types.JavaVersion.JAVA7.newArrayType(type);
    }

    /* renamed from: of */
    public static <T> TypeToken<T> m17711of(Class<T> cls) {
        return new SimpleTypeToken(cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Type[] resolveInPlace(Type[] typeArr) {
        for (int i9 = 0; i9 < typeArr.length; i9++) {
            typeArr[i9] = resolveType(typeArr[i9]).getType();
        }
        return typeArr;
    }

    private TypeToken<?> resolveSupertype(Type type) {
        TypeToken<?> typeTokenResolveType = resolveType(type);
        typeTokenResolveType.typeResolver = this.typeResolver;
        return typeTokenResolveType;
    }

    private Type resolveTypeArgsForSubclass(Class<?> cls) {
        if ((this.runtimeType instanceof Class) && (cls.getTypeParameters().length == 0 || getRawType().getTypeParameters().length != 0)) {
            return cls;
        }
        TypeToken genericType = toGenericType(cls);
        return new TypeResolver().where(genericType.getSupertype(getRawType()).runtimeType, this.runtimeType).resolveType(genericType.runtimeType);
    }

    private boolean someRawTypeIsSubclassOf(Class<?> cls) {
        UnmodifiableIterator<Class<? super T>> it = getRawTypes().iterator();
        while (it.hasNext()) {
            if (cls.isAssignableFrom(it.next())) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public static <T> TypeToken<? extends T> toGenericType(Class<T> cls) {
        if (cls.isArray()) {
            return (TypeToken<? extends T>) m17712of(Types.newArrayType(toGenericType(cls.getComponentType()).runtimeType));
        }
        TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
        Type type = (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) ? null : toGenericType(cls.getEnclosingClass()).runtimeType;
        return (typeParameters.length > 0 || !(type == null || type == cls.getEnclosingClass())) ? (TypeToken<? extends T>) m17712of(Types.newParameterizedTypeWithOwner(type, cls, typeParameters)) : m17711of((Class) cls);
    }

    public final Invokable<T, T> constructor(Constructor<?> constructor) {
        Preconditions.checkArgument(constructor.getDeclaringClass() == getRawType(), "%s not declared by %s", constructor, getRawType());
        return new Invokable.ConstructorInvokable<T>(constructor) { // from class: com.google.common.reflect.TypeToken.2
            @Override // com.google.common.reflect.Invokable.ConstructorInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericExceptionTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericExceptionTypes());
            }

            @Override // com.google.common.reflect.Invokable.ConstructorInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericParameterTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericParameterTypes());
            }

            @Override // com.google.common.reflect.Invokable.ConstructorInvokable, com.google.common.reflect.Invokable
            public Type getGenericReturnType() {
                return TypeToken.this.resolveType(super.getGenericReturnType()).getType();
            }

            @Override // com.google.common.reflect.Invokable, com.google.common.reflect.Element
            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            @Override // com.google.common.reflect.Invokable, com.google.common.reflect.Element
            public String toString() {
                return getOwnerType() + "(" + Joiner.m17550on(", ").join(getGenericParameterTypes()) + ")";
            }
        };
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeToken) {
            return this.runtimeType.equals(((TypeToken) obj).runtimeType);
        }
        return false;
    }

    public final TypeToken<?> getComponentType() {
        Type componentType = Types.getComponentType(this.runtimeType);
        if (componentType == null) {
            return null;
        }
        return m17712of(componentType);
    }

    public final ImmutableList<TypeToken<? super T>> getGenericInterfaces() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundsAsInterfaces(((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return boundsAsInterfaces(((WildcardType) type).getUpperBounds());
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type2 : getRawType().getGenericInterfaces()) {
            builder.add((ImmutableList.Builder) resolveSupertype(type2));
        }
        return builder.build();
    }

    public final TypeToken<? super T> getGenericSuperclass() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundAsSuperclass(((TypeVariable) type).getBounds()[0]);
        }
        if (type instanceof WildcardType) {
            return boundAsSuperclass(((WildcardType) type).getUpperBounds()[0]);
        }
        Type genericSuperclass = getRawType().getGenericSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        return (TypeToken<? super T>) resolveSupertype(genericSuperclass);
    }

    public final Class<? super T> getRawType() {
        return getRawTypes().iterator().next();
    }

    public final TypeToken<? extends T> getSubtype(Class<?> cls) {
        Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", this);
        Type type = this.runtimeType;
        if (type instanceof WildcardType) {
            return getSubtypeFromLowerBounds(cls, ((WildcardType) type).getLowerBounds());
        }
        if (isArray()) {
            return getArraySubtype(cls);
        }
        Preconditions.checkArgument(getRawType().isAssignableFrom(cls), "%s isn't a subclass of %s", cls, this);
        return (TypeToken<? extends T>) m17712of(resolveTypeArgsForSubclass(cls));
    }

    public final TypeToken<? super T> getSupertype(Class<? super T> cls) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(cls), "%s is not a super class of %s", cls, this);
        Type type = this.runtimeType;
        return type instanceof TypeVariable ? getSupertypeFromUpperBounds(cls, ((TypeVariable) type).getBounds()) : type instanceof WildcardType ? getSupertypeFromUpperBounds(cls, ((WildcardType) type).getUpperBounds()) : cls.isArray() ? getArraySupertype(cls) : (TypeToken<? super T>) resolveSupertype(toGenericType(cls).runtimeType);
    }

    public final Type getType() {
        return this.runtimeType;
    }

    public final TypeToken<T>.TypeSet getTypes() {
        return new TypeSet();
    }

    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    public final boolean isArray() {
        return getComponentType() != null;
    }

    public final boolean isPrimitive() {
        Type type = this.runtimeType;
        return (type instanceof Class) && ((Class) type).isPrimitive();
    }

    public final boolean isSubtypeOf(TypeToken<?> typeToken) {
        return isSubtypeOf(typeToken.getType());
    }

    public final boolean isSupertypeOf(TypeToken<?> typeToken) {
        return typeToken.isSubtypeOf(getType());
    }

    public final Invokable<T, Object> method(Method method) {
        Preconditions.checkArgument(someRawTypeIsSubclassOf(method.getDeclaringClass()), "%s not declared by %s", method, this);
        return new Invokable.MethodInvokable<T>(method) { // from class: com.google.common.reflect.TypeToken.1
            @Override // com.google.common.reflect.Invokable.MethodInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericExceptionTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericExceptionTypes());
            }

            @Override // com.google.common.reflect.Invokable.MethodInvokable, com.google.common.reflect.Invokable
            public Type[] getGenericParameterTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericParameterTypes());
            }

            @Override // com.google.common.reflect.Invokable.MethodInvokable, com.google.common.reflect.Invokable
            public Type getGenericReturnType() {
                return TypeToken.this.resolveType(super.getGenericReturnType()).getType();
            }

            @Override // com.google.common.reflect.Invokable, com.google.common.reflect.Element
            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            @Override // com.google.common.reflect.Invokable, com.google.common.reflect.Element
            public String toString() {
                return getOwnerType() + "." + super.toString();
            }
        };
    }

    @CanIgnoreReturnValue
    public final TypeToken<T> rejectTypeVariables() {
        new TypeVisitor() { // from class: com.google.common.reflect.TypeToken.3
            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType genericArrayType) {
                visit(genericArrayType.getGenericComponentType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitParameterizedType(ParameterizedType parameterizedType) {
                visit(parameterizedType.getActualTypeArguments());
                visit(parameterizedType.getOwnerType());
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> typeVariable) {
                throw new IllegalArgumentException(TypeToken.this.runtimeType + "contains a type variable and is not safe for the operation");
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType wildcardType) {
                visit(wildcardType.getLowerBounds());
                visit(wildcardType.getUpperBounds());
            }
        }.visit(this.runtimeType);
        return this;
    }

    public final TypeToken<?> resolveType(Type type) {
        Preconditions.checkNotNull(type);
        TypeResolver typeResolverAccordingTo = this.typeResolver;
        if (typeResolverAccordingTo == null) {
            typeResolverAccordingTo = TypeResolver.accordingTo(this.runtimeType);
            this.typeResolver = typeResolverAccordingTo;
        }
        return m17712of(typeResolverAccordingTo.resolveType(type));
    }

    public String toString() {
        return Types.toString(this.runtimeType);
    }

    public final TypeToken<T> unwrap() {
        return isWrapper() ? m17711of(Primitives.unwrap((Class) this.runtimeType)) : this;
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, TypeToken<X> typeToken) {
        return new SimpleTypeToken(new TypeResolver().where(ImmutableMap.m17593of(new TypeResolver.TypeVariableKey(typeParameter.typeVariable), typeToken.runtimeType)).resolveType(this.runtimeType));
    }

    public final TypeToken<T> wrap() {
        return isPrimitive() ? m17711of(Primitives.wrap((Class) this.runtimeType)) : this;
    }

    public Object writeReplace() {
        return m17712of(new TypeResolver().resolveType(this.runtimeType));
    }

    public final class ClassSet extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> classes;

        private ClassSet() {
            super();
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().classes();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            return this;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            throw new UnsupportedOperationException("classes().interfaces() not supported.");
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) TypeCollector.FOR_RAW_TYPE.classesOnly().collectTypes(TypeToken.this.getRawTypes()));
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.classes;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(TypeCollector.FOR_GENERIC_TYPE.classesOnly().collectTypes((TypeCollector<TypeToken<?>>) TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.classes = set;
            return set;
        }
    }

    public final class InterfaceSet extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        private final transient TypeToken<T>.TypeSet allTypes;
        private transient ImmutableSet<TypeToken<? super T>> interfaces;

        public InterfaceSet(TypeToken<T>.TypeSet typeSet) {
            super();
            this.allTypes = typeSet;
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().interfaces();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            throw new UnsupportedOperationException("interfaces().classes() not supported.");
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            return this;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            return FluentIterable.from(TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getRawTypes())).filter(new Predicate<Class<?>>() { // from class: com.google.common.reflect.TypeToken.InterfaceSet.1
                @Override // com.google.common.base.Predicate
                public boolean apply(Class<?> cls) {
                    return cls.isInterface();
                }
            }).toSet();
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.interfaces;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(this.allTypes).filter(TypeFilter.INTERFACE_ONLY).toSet();
            this.interfaces = set;
            return set;
        }
    }

    public class TypeSet extends ForwardingSet<TypeToken<? super T>> implements Serializable {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> types;

        public TypeSet() {
        }

        public TypeToken<T>.TypeSet classes() {
            return new ClassSet();
        }

        public TypeToken<T>.TypeSet interfaces() {
            return new InterfaceSet(this);
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getRawTypes()));
        }

        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.types;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> set = FluentIterable.from(TypeCollector.FOR_GENERIC_TYPE.collectTypes((TypeCollector<TypeToken<?>>) TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.types = set;
            return set;
        }
    }

    public TypeToken() {
        Type typeCapture = capture();
        this.runtimeType = typeCapture;
        Preconditions.checkState(!(typeCapture instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", typeCapture);
    }

    /* renamed from: of */
    public static TypeToken<?> m17712of(Type type) {
        return new SimpleTypeToken(type);
    }

    public final boolean isSubtypeOf(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof WildcardType) {
            return any(((WildcardType) type).getLowerBounds()).isSupertypeOf(this.runtimeType);
        }
        Type type2 = this.runtimeType;
        if (type2 instanceof WildcardType) {
            return any(((WildcardType) type2).getUpperBounds()).isSubtypeOf(type);
        }
        if (type2 instanceof TypeVariable) {
            return type2.equals(type) || any(((TypeVariable) this.runtimeType).getBounds()).isSubtypeOf(type);
        }
        if (type2 instanceof GenericArrayType) {
            return m17712of(type).isSupertypeOfArray((GenericArrayType) this.runtimeType);
        }
        if (type instanceof Class) {
            return someRawTypeIsSubclassOf((Class) type);
        }
        if (type instanceof ParameterizedType) {
            return isSubtypeOfParameterizedType((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return isSubtypeOfArrayType((GenericArrayType) type);
        }
        return false;
    }

    public final boolean isSupertypeOf(Type type) {
        return m17712of(type).isSubtypeOf(getType());
    }

    public TypeToken(Class<?> cls) {
        Type typeCapture = super.capture();
        if (typeCapture instanceof Class) {
            this.runtimeType = typeCapture;
        } else {
            this.runtimeType = m17711of((Class) cls).resolveType(typeCapture).runtimeType;
        }
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, Class<X> cls) {
        return where(typeParameter, m17711of((Class) cls));
    }

    private TypeToken(Type type) {
        this.runtimeType = (Type) Preconditions.checkNotNull(type);
    }
}
