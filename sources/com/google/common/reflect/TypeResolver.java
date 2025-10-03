package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Beta
/* loaded from: classes2.dex */
public final class TypeResolver {
    private final TypeTable typeTable;

    public static final class TypeMappingIntrospector extends TypeVisitor {
        private static final WildcardCapturer wildcardCapturer = new WildcardCapturer();
        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        private TypeMappingIntrospector() {
        }

        public static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type type) {
            TypeMappingIntrospector typeMappingIntrospector = new TypeMappingIntrospector();
            typeMappingIntrospector.visit(wildcardCapturer.capture(type));
            return ImmutableMap.copyOf((Map) typeMappingIntrospector.mappings);
        }

        private void map(TypeVariableKey typeVariableKey, Type type) {
            if (this.mappings.containsKey(typeVariableKey)) {
                return;
            }
            Type type2 = type;
            while (type2 != null) {
                if (typeVariableKey.equalsType(type2)) {
                    while (type != null) {
                        type = this.mappings.remove(TypeVariableKey.forLookup(type));
                    }
                    return;
                }
                type2 = this.mappings.get(TypeVariableKey.forLookup(type2));
            }
            this.mappings.put(typeVariableKey, type);
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitClass(Class<?> cls) {
            visit(cls.getGenericSuperclass());
            visit(cls.getGenericInterfaces());
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitParameterizedType(ParameterizedType parameterizedType) {
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Preconditions.checkState(typeParameters.length == actualTypeArguments.length);
            for (int i9 = 0; i9 < typeParameters.length; i9++) {
                map(new TypeVariableKey(typeParameters[i9]), actualTypeArguments[i9]);
            }
            visit(cls);
            visit(parameterizedType.getOwnerType());
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitTypeVariable(TypeVariable<?> typeVariable) {
            visit(typeVariable.getBounds());
        }

        @Override // com.google.common.reflect.TypeVisitor
        public void visitWildcardType(WildcardType wildcardType) {
            visit(wildcardType.getUpperBounds());
        }
    }

    public static final class TypeVariableKey {
        private final TypeVariable<?> var;

        public TypeVariableKey(TypeVariable<?> typeVariable) {
            this.var = (TypeVariable) Preconditions.checkNotNull(typeVariable);
        }

        private boolean equalsTypeVariable(TypeVariable<?> typeVariable) {
            return this.var.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) && this.var.getName().equals(typeVariable.getName());
        }

        public static TypeVariableKey forLookup(Type type) {
            if (type instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable) type);
            }
            return null;
        }

        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                return equalsTypeVariable(((TypeVariableKey) obj).var);
            }
            return false;
        }

        public boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable) type);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.var.getGenericDeclaration(), this.var.getName());
        }

        public String toString() {
            return this.var.toString();
        }
    }

    public static class WildcardCapturer {

        /* renamed from: id */
        private final AtomicInteger f15465id;

        private Type captureNullable(Type type) {
            if (type == null) {
                return null;
            }
            return capture(type);
        }

        private WildcardCapturer forTypeVariable(final TypeVariable<?> typeVariable) {
            return new WildcardCapturer(this.f15465id) { // from class: com.google.common.reflect.TypeResolver.WildcardCapturer.1
                @Override // com.google.common.reflect.TypeResolver.WildcardCapturer
                public TypeVariable<?> captureAsTypeVariable(Type[] typeArr) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(typeArr));
                    linkedHashSet.addAll(Arrays.asList(typeVariable.getBounds()));
                    if (linkedHashSet.size() > 1) {
                        linkedHashSet.remove(Object.class);
                    }
                    return super.captureAsTypeVariable((Type[]) linkedHashSet.toArray(new Type[0]));
                }
            };
        }

        private WildcardCapturer notForTypeVariable() {
            return new WildcardCapturer(this.f15465id);
        }

        public final Type capture(Type type) {
            Preconditions.checkNotNull(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.newArrayType(notForTypeVariable().capture(((GenericArrayType) type).getGenericComponentType()));
            }
            if (!(type instanceof ParameterizedType)) {
                if (!(type instanceof WildcardType)) {
                    throw new AssertionError("must have been one of the known types");
                }
                WildcardType wildcardType = (WildcardType) type;
                return wildcardType.getLowerBounds().length == 0 ? captureAsTypeVariable(wildcardType.getUpperBounds()) : type;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable<?>[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (int i9 = 0; i9 < actualTypeArguments.length; i9++) {
                actualTypeArguments[i9] = forTypeVariable(typeParameters[i9]).capture(actualTypeArguments[i9]);
            }
            return Types.newParameterizedTypeWithOwner(notForTypeVariable().captureNullable(parameterizedType.getOwnerType()), cls, actualTypeArguments);
        }

        public TypeVariable<?> captureAsTypeVariable(Type[] typeArr) {
            return Types.newArtificialTypeVariable(WildcardCapturer.class, "capture#" + this.f15465id.incrementAndGet() + "-of ? extends " + Joiner.m17549on('&').join(typeArr), typeArr);
        }

        public WildcardCapturer() {
            this(new AtomicInteger());
        }

        private WildcardCapturer(AtomicInteger atomicInteger) {
            this.f15465id = atomicInteger;
        }
    }

    public static TypeResolver accordingTo(Type type) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(type));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T expectArgument(Class<T> cls, Object obj) {
        try {
            return cls.cast(obj);
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException(obj + " is not a " + cls.getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void populateTypeMappings(final Map<TypeVariableKey, Type> map, Type type, final Type type2) {
        if (type.equals(type2)) {
            return;
        }
        new TypeVisitor() { // from class: com.google.common.reflect.TypeResolver.1
            @Override // com.google.common.reflect.TypeVisitor
            public void visitClass(Class<?> cls) {
                if (type2 instanceof WildcardType) {
                    return;
                }
                throw new IllegalArgumentException("No type mapping from " + cls + " to " + type2);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType genericArrayType) {
                Type type3 = type2;
                if (type3 instanceof WildcardType) {
                    return;
                }
                Type componentType = Types.getComponentType(type3);
                Preconditions.checkArgument(componentType != null, "%s is not an array type.", type2);
                TypeResolver.populateTypeMappings(map, genericArrayType.getGenericComponentType(), componentType);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitParameterizedType(ParameterizedType parameterizedType) {
                Type type3 = type2;
                if (type3 instanceof WildcardType) {
                    return;
                }
                ParameterizedType parameterizedType2 = (ParameterizedType) TypeResolver.expectArgument(ParameterizedType.class, type3);
                if (parameterizedType.getOwnerType() != null && parameterizedType2.getOwnerType() != null) {
                    TypeResolver.populateTypeMappings(map, parameterizedType.getOwnerType(), parameterizedType2.getOwnerType());
                }
                Preconditions.checkArgument(parameterizedType.getRawType().equals(parameterizedType2.getRawType()), "Inconsistent raw type: %s vs. %s", parameterizedType, type2);
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
                Preconditions.checkArgument(actualTypeArguments.length == actualTypeArguments2.length, "%s not compatible with %s", parameterizedType, parameterizedType2);
                for (int i9 = 0; i9 < actualTypeArguments.length; i9++) {
                    TypeResolver.populateTypeMappings(map, actualTypeArguments[i9], actualTypeArguments2[i9]);
                }
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> typeVariable) {
                map.put(new TypeVariableKey(typeVariable), type2);
            }

            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType wildcardType) {
                Type type3 = type2;
                if (type3 instanceof WildcardType) {
                    WildcardType wildcardType2 = (WildcardType) type3;
                    Type[] upperBounds = wildcardType.getUpperBounds();
                    Type[] upperBounds2 = wildcardType2.getUpperBounds();
                    Type[] lowerBounds = wildcardType.getLowerBounds();
                    Type[] lowerBounds2 = wildcardType2.getLowerBounds();
                    Preconditions.checkArgument(upperBounds.length == upperBounds2.length && lowerBounds.length == lowerBounds2.length, "Incompatible type: %s vs. %s", wildcardType, type2);
                    for (int i9 = 0; i9 < upperBounds.length; i9++) {
                        TypeResolver.populateTypeMappings(map, upperBounds[i9], upperBounds2[i9]);
                    }
                    for (int i10 = 0; i10 < lowerBounds.length; i10++) {
                        TypeResolver.populateTypeMappings(map, lowerBounds[i10], lowerBounds2[i10]);
                    }
                }
            }
        }.visit(type);
    }

    private Type resolveGenericArrayType(GenericArrayType genericArrayType) {
        return Types.newArrayType(resolveType(genericArrayType.getGenericComponentType()));
    }

    private ParameterizedType resolveParameterizedType(ParameterizedType parameterizedType) {
        Type ownerType = parameterizedType.getOwnerType();
        return Types.newParameterizedTypeWithOwner(ownerType == null ? null : resolveType(ownerType), (Class) resolveType(parameterizedType.getRawType()), resolveTypes(parameterizedType.getActualTypeArguments()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Type[] resolveTypes(Type[] typeArr) {
        Type[] typeArr2 = new Type[typeArr.length];
        for (int i9 = 0; i9 < typeArr.length; i9++) {
            typeArr2[i9] = resolveType(typeArr[i9]);
        }
        return typeArr2;
    }

    private WildcardType resolveWildcardType(WildcardType wildcardType) {
        return new Types.WildcardTypeImpl(resolveTypes(wildcardType.getLowerBounds()), resolveTypes(wildcardType.getUpperBounds()));
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        return type instanceof TypeVariable ? this.typeTable.resolve((TypeVariable) type) : type instanceof ParameterizedType ? resolveParameterizedType((ParameterizedType) type) : type instanceof GenericArrayType ? resolveGenericArrayType((GenericArrayType) type) : type instanceof WildcardType ? resolveWildcardType((WildcardType) type) : type;
    }

    public TypeResolver where(Type type, Type type2) {
        HashMap mapNewHashMap = Maps.newHashMap();
        populateTypeMappings(mapNewHashMap, (Type) Preconditions.checkNotNull(type), (Type) Preconditions.checkNotNull(type2));
        return where(mapNewHashMap);
    }

    public static class TypeTable {
        private final ImmutableMap<TypeVariableKey, Type> map;

        public TypeTable() {
            this.map = ImmutableMap.m17592of();
        }

        public final Type resolve(final TypeVariable<?> typeVariable) {
            return resolveInternal(typeVariable, new TypeTable() { // from class: com.google.common.reflect.TypeResolver.TypeTable.1
                @Override // com.google.common.reflect.TypeResolver.TypeTable
                public Type resolveInternal(TypeVariable<?> typeVariable2, TypeTable typeTable) {
                    return typeVariable2.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) ? typeVariable2 : this.resolveInternal(typeVariable2, typeTable);
                }
            });
        }

        public Type resolveInternal(TypeVariable<?> typeVariable, TypeTable typeTable) {
            Type type = this.map.get(new TypeVariableKey(typeVariable));
            if (type != null) {
                return new TypeResolver(typeTable).resolveType(type);
            }
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length == 0) {
                return typeVariable;
            }
            Type[] typeArrResolveTypes = new TypeResolver(typeTable).resolveTypes(bounds);
            return (Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY && Arrays.equals(bounds, typeArrResolveTypes)) ? typeVariable : Types.newArtificialTypeVariable(typeVariable.getGenericDeclaration(), typeVariable.getName(), typeArrResolveTypes);
        }

        public final TypeTable where(Map<TypeVariableKey, ? extends Type> map) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.putAll(this.map);
            for (Map.Entry<TypeVariableKey, ? extends Type> entry : map.entrySet()) {
                TypeVariableKey key = entry.getKey();
                Type value = entry.getValue();
                Preconditions.checkArgument(!key.equalsType(value), "Type variable %s bound to itself", key);
                builder.put(key, value);
            }
            return new TypeTable(builder.build());
        }

        private TypeTable(ImmutableMap<TypeVariableKey, Type> immutableMap) {
            this.map = immutableMap;
        }
    }

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    private TypeResolver(TypeTable typeTable) {
        this.typeTable = typeTable;
    }

    public TypeResolver where(Map<TypeVariableKey, ? extends Type> map) {
        return new TypeResolver(this.typeTable.where(map));
    }
}
