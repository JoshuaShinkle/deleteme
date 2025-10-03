package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.DescriptorProtos;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Internal;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.commons.lang3.CharEncoding;
import p147n5.C5371i;

/* loaded from: classes2.dex */
public final class Descriptors {
    private static final Logger logger = Logger.getLogger(Descriptors.class.getName());

    /* renamed from: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors$1 */
    public static /* synthetic */ class C43451 {

        /* renamed from: $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType */
        static final /* synthetic */ int[] f15618xdde82548;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[FieldDescriptor.JavaType.values().length];
            f15618xdde82548 = iArr;
            try {
                iArr[FieldDescriptor.JavaType.ENUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15618xdde82548[FieldDescriptor.JavaType.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr2;
            try {
                iArr2[FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.UINT32.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.INT64.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SINT64.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.UINT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FIXED64.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.DOUBLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.BOOL.ordinal()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    public static final class Descriptor extends GenericDescriptor {
        private final Descriptor containingType;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final Descriptor[] nestedTypes;
        private final OneofDescriptor[] oneofs;
        private DescriptorProtos.DescriptorProto proto;

        public /* synthetic */ Descriptor(DescriptorProtos.DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9, C43451 c43451) {
            this(descriptorProto, fileDescriptor, descriptor, i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.nestedTypes) {
                descriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.fields) {
                fieldDescriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor2 : this.extensions) {
                fieldDescriptor2.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.DescriptorProto descriptorProto) {
            this.proto = descriptorProto;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.nestedTypes;
                if (i10 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i10].setProto(descriptorProto.getNestedType(i10));
                i10++;
            }
            int i11 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i11 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i11].setProto(descriptorProto.getEnumType(i11));
                i11++;
            }
            int i12 = 0;
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.fields;
                if (i12 >= fieldDescriptorArr.length) {
                    break;
                }
                fieldDescriptorArr[i12].setProto(descriptorProto.getField(i12));
                i12++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr2 = this.extensions;
                if (i9 >= fieldDescriptorArr2.length) {
                    return;
                }
                fieldDescriptorArr2[i9].setProto(descriptorProto.getExtension(i9));
                i9++;
            }
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            String str2 = this.fullName;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
            sb.append(str2);
            sb.append(".");
            sb.append(str);
            GenericDescriptor genericDescriptorFindSymbol = descriptorPool.findSymbol(sb.toString());
            if (genericDescriptorFindSymbol == null || !(genericDescriptorFindSymbol instanceof EnumDescriptor)) {
                return null;
            }
            return (EnumDescriptor) genericDescriptorFindSymbol;
        }

        public FieldDescriptor findFieldByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            String str2 = this.fullName;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
            sb.append(str2);
            sb.append(".");
            sb.append(str);
            GenericDescriptor genericDescriptorFindSymbol = descriptorPool.findSymbol(sb.toString());
            if (genericDescriptorFindSymbol == null || !(genericDescriptorFindSymbol instanceof FieldDescriptor)) {
                return null;
            }
            return (FieldDescriptor) genericDescriptorFindSymbol;
        }

        public FieldDescriptor findFieldByNumber(int i9) {
            return (FieldDescriptor) this.file.pool.fieldsByNumber.get(new DescriptorPool.DescriptorIntPair(this, i9));
        }

        public Descriptor findNestedTypeByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            String str2 = this.fullName;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
            sb.append(str2);
            sb.append(".");
            sb.append(str);
            GenericDescriptor genericDescriptorFindSymbol = descriptorPool.findSymbol(sb.toString());
            if (genericDescriptorFindSymbol == null || !(genericDescriptorFindSymbol instanceof Descriptor)) {
                return null;
            }
            return (Descriptor) genericDescriptorFindSymbol;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public List<OneofDescriptor> getOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs));
        }

        public DescriptorProtos.MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public boolean isExtendable() {
            return this.proto.getExtensionRangeList().size() != 0;
        }

        public boolean isExtensionNumber(int i9) {
            for (DescriptorProtos.DescriptorProto.ExtensionRange extensionRange : this.proto.getExtensionRangeList()) {
                if (extensionRange.getStart() <= i9 && i9 < extensionRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public Descriptor(String str) {
            String strSubstring;
            String strSubstring2;
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                strSubstring2 = str.substring(iLastIndexOf + 1);
                strSubstring = str.substring(0, iLastIndexOf);
            } else {
                strSubstring = "";
                strSubstring2 = str;
            }
            this.index = 0;
            this.proto = DescriptorProtos.DescriptorProto.newBuilder().setName(strSubstring2).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
            this.fullName = str;
            this.containingType = null;
            this.nestedTypes = new Descriptor[0];
            this.enumTypes = new EnumDescriptor[0];
            this.fields = new FieldDescriptor[0];
            this.extensions = new FieldDescriptor[0];
            this.oneofs = new OneofDescriptor[0];
            this.file = new FileDescriptor(strSubstring, this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.DescriptorProto toProto() {
            return this.proto;
        }

        private Descriptor(DescriptorProtos.DescriptorProto descriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9) throws DescriptorValidationException {
            this.index = i9;
            this.proto = descriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, descriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            this.oneofs = new OneofDescriptor[descriptorProto.getOneofDeclCount()];
            for (int i10 = 0; i10 < descriptorProto.getOneofDeclCount(); i10++) {
                this.oneofs[i10] = new OneofDescriptor(descriptorProto.getOneofDecl(i10), fileDescriptor, this, i10, null);
            }
            this.nestedTypes = new Descriptor[descriptorProto.getNestedTypeCount()];
            for (int i11 = 0; i11 < descriptorProto.getNestedTypeCount(); i11++) {
                this.nestedTypes[i11] = new Descriptor(descriptorProto.getNestedType(i11), fileDescriptor, this, i11);
            }
            this.enumTypes = new EnumDescriptor[descriptorProto.getEnumTypeCount()];
            for (int i12 = 0; i12 < descriptorProto.getEnumTypeCount(); i12++) {
                this.enumTypes[i12] = new EnumDescriptor(descriptorProto.getEnumType(i12), fileDescriptor, this, i12, null);
            }
            this.fields = new FieldDescriptor[descriptorProto.getFieldCount()];
            for (int i13 = 0; i13 < descriptorProto.getFieldCount(); i13++) {
                this.fields[i13] = new FieldDescriptor(descriptorProto.getField(i13), fileDescriptor, this, i13, false, null);
            }
            this.extensions = new FieldDescriptor[descriptorProto.getExtensionCount()];
            for (int i14 = 0; i14 < descriptorProto.getExtensionCount(); i14++) {
                this.extensions[i14] = new FieldDescriptor(descriptorProto.getExtension(i14), fileDescriptor, this, i14, true, null);
            }
            for (int i15 = 0; i15 < descriptorProto.getOneofDeclCount(); i15++) {
                OneofDescriptor oneofDescriptor = this.oneofs[i15];
                oneofDescriptor.fields = new FieldDescriptor[oneofDescriptor.getFieldCount()];
                this.oneofs[i15].fieldCount = 0;
            }
            for (int i16 = 0; i16 < descriptorProto.getFieldCount(); i16++) {
                OneofDescriptor containingOneof = this.fields[i16].getContainingOneof();
                if (containingOneof != null) {
                    containingOneof.fields[OneofDescriptor.access$1808(containingOneof)] = this.fields[i16];
                }
            }
            fileDescriptor.pool.addSymbol(this);
        }
    }

    public static final class DescriptorPool {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean allowUnknownDependencies;
        private final Map<String, GenericDescriptor> descriptorsByName = new HashMap();
        private final Map<DescriptorIntPair, FieldDescriptor> fieldsByNumber = new HashMap();
        private final Map<DescriptorIntPair, EnumValueDescriptor> enumValuesByNumber = new HashMap();
        private final Set<FileDescriptor> dependencies = new HashSet();

        public static final class DescriptorIntPair {
            private final GenericDescriptor descriptor;
            private final int number;

            public DescriptorIntPair(GenericDescriptor genericDescriptor, int i9) {
                this.descriptor = genericDescriptor;
                this.number = i9;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof DescriptorIntPair)) {
                    return false;
                }
                DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
                return this.descriptor == descriptorIntPair.descriptor && this.number == descriptorIntPair.number;
            }

            public int hashCode() {
                return (this.descriptor.hashCode() * 65535) + this.number;
            }
        }

        public static final class PackageDescriptor extends GenericDescriptor {
            private final FileDescriptor file;
            private final String fullName;
            private final String name;

            public PackageDescriptor(String str, String str2, FileDescriptor fileDescriptor) {
                this.file = fileDescriptor;
                this.fullName = str2;
                this.name = str;
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
            public FileDescriptor getFile() {
                return this.file;
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
            public String getFullName() {
                return this.fullName;
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
            public String getName() {
                return this.name;
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
            public Message toProto() {
                return this.file.toProto();
            }
        }

        public enum SearchFilter {
            TYPES_ONLY,
            AGGREGATES_ONLY,
            ALL_SYMBOLS
        }

        public DescriptorPool(FileDescriptor[] fileDescriptorArr, boolean z8) {
            this.allowUnknownDependencies = z8;
            for (int i9 = 0; i9 < fileDescriptorArr.length; i9++) {
                this.dependencies.add(fileDescriptorArr[i9]);
                importPublicDependencies(fileDescriptorArr[i9]);
            }
            for (FileDescriptor fileDescriptor : this.dependencies) {
                try {
                    addPackage(fileDescriptor.getPackage(), fileDescriptor);
                } catch (DescriptorValidationException unused) {
                }
            }
        }

        private void importPublicDependencies(FileDescriptor fileDescriptor) {
            for (FileDescriptor fileDescriptor2 : fileDescriptor.getPublicDependencies()) {
                if (this.dependencies.add(fileDescriptor2)) {
                    importPublicDependencies(fileDescriptor2);
                }
            }
        }

        public static void validateSymbolName(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            String name = genericDescriptor.getName();
            C43451 c43451 = null;
            if (name.length() == 0) {
                throw new DescriptorValidationException(genericDescriptor, "Missing name.", c43451);
            }
            boolean z8 = true;
            for (int i9 = 0; i9 < name.length(); i9++) {
                char cCharAt = name.charAt(i9);
                if (cCharAt >= 128) {
                    z8 = false;
                }
                if (!Character.isLetter(cCharAt) && cCharAt != '_' && (!Character.isDigit(cCharAt) || i9 <= 0)) {
                    z8 = false;
                }
            }
            if (z8) {
                return;
            }
            StringBuilder sb = new StringBuilder(name.length() + 29);
            sb.append("\"");
            sb.append(name);
            sb.append("\" is not a valid identifier.");
            throw new DescriptorValidationException(genericDescriptor, sb.toString(), c43451);
        }

        public void addEnumValueByNumber(EnumValueDescriptor enumValueDescriptor) {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(enumValueDescriptor.getType(), enumValueDescriptor.getNumber());
            EnumValueDescriptor enumValueDescriptorPut = this.enumValuesByNumber.put(descriptorIntPair, enumValueDescriptor);
            if (enumValueDescriptorPut != null) {
                this.enumValuesByNumber.put(descriptorIntPair, enumValueDescriptorPut);
            }
        }

        public void addFieldByNumber(FieldDescriptor fieldDescriptor) throws DescriptorValidationException {
            DescriptorIntPair descriptorIntPair = new DescriptorIntPair(fieldDescriptor.getContainingType(), fieldDescriptor.getNumber());
            FieldDescriptor fieldDescriptorPut = this.fieldsByNumber.put(descriptorIntPair, fieldDescriptor);
            if (fieldDescriptorPut == null) {
                return;
            }
            this.fieldsByNumber.put(descriptorIntPair, fieldDescriptorPut);
            int number = fieldDescriptor.getNumber();
            String fullName = fieldDescriptor.getContainingType().getFullName();
            String name = fieldDescriptorPut.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(fullName).length() + 65 + String.valueOf(name).length());
            sb.append("Field number ");
            sb.append(number);
            sb.append(" has already been used in \"");
            sb.append(fullName);
            sb.append("\" by field \"");
            sb.append(name);
            sb.append("\".");
            throw new DescriptorValidationException(fieldDescriptor, sb.toString(), (C43451) null);
        }

        public void addPackage(String str, FileDescriptor fileDescriptor) throws DescriptorValidationException {
            String strSubstring;
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf == -1) {
                strSubstring = str;
            } else {
                addPackage(str.substring(0, iLastIndexOf), fileDescriptor);
                strSubstring = str.substring(iLastIndexOf + 1);
            }
            GenericDescriptor genericDescriptorPut = this.descriptorsByName.put(str, new PackageDescriptor(strSubstring, str, fileDescriptor));
            if (genericDescriptorPut != null) {
                this.descriptorsByName.put(str, genericDescriptorPut);
                if (genericDescriptorPut instanceof PackageDescriptor) {
                    return;
                }
                String name = genericDescriptorPut.getFile().getName();
                StringBuilder sb = new StringBuilder(String.valueOf(strSubstring).length() + 69 + String.valueOf(name).length());
                sb.append("\"");
                sb.append(strSubstring);
                sb.append("\" is already defined (as something other than a ");
                sb.append("package) in file \"");
                sb.append(name);
                sb.append("\".");
                throw new DescriptorValidationException(fileDescriptor, sb.toString(), (C43451) null);
            }
        }

        public void addSymbol(GenericDescriptor genericDescriptor) throws DescriptorValidationException {
            validateSymbolName(genericDescriptor);
            String fullName = genericDescriptor.getFullName();
            int iLastIndexOf = fullName.lastIndexOf(46);
            GenericDescriptor genericDescriptorPut = this.descriptorsByName.put(fullName, genericDescriptor);
            if (genericDescriptorPut != null) {
                this.descriptorsByName.put(fullName, genericDescriptorPut);
                C43451 c43451 = null;
                if (genericDescriptor.getFile() != genericDescriptorPut.getFile()) {
                    String name = genericDescriptorPut.getFile().getName();
                    StringBuilder sb = new StringBuilder(fullName.length() + 33 + String.valueOf(name).length());
                    sb.append("\"");
                    sb.append(fullName);
                    sb.append("\" is already defined in file \"");
                    sb.append(name);
                    sb.append("\".");
                    throw new DescriptorValidationException(genericDescriptor, sb.toString(), c43451);
                }
                if (iLastIndexOf == -1) {
                    StringBuilder sb2 = new StringBuilder(fullName.length() + 22);
                    sb2.append("\"");
                    sb2.append(fullName);
                    sb2.append("\" is already defined.");
                    throw new DescriptorValidationException(genericDescriptor, sb2.toString(), c43451);
                }
                String strValueOf = String.valueOf(fullName.substring(iLastIndexOf + 1));
                String strValueOf2 = String.valueOf(fullName.substring(0, iLastIndexOf));
                StringBuilder sb3 = new StringBuilder(strValueOf.length() + 28 + strValueOf2.length());
                sb3.append("\"");
                sb3.append(strValueOf);
                sb3.append("\" is already defined in \"");
                sb3.append(strValueOf2);
                sb3.append("\".");
                throw new DescriptorValidationException(genericDescriptor, sb3.toString(), c43451);
            }
        }

        public GenericDescriptor findSymbol(String str) {
            return findSymbol(str, SearchFilter.ALL_SYMBOLS);
        }

        public boolean isAggregate(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor) || (genericDescriptor instanceof PackageDescriptor) || (genericDescriptor instanceof ServiceDescriptor);
        }

        public boolean isType(GenericDescriptor genericDescriptor) {
            return (genericDescriptor instanceof Descriptor) || (genericDescriptor instanceof EnumDescriptor);
        }

        public GenericDescriptor lookupSymbol(String str, GenericDescriptor genericDescriptor, SearchFilter searchFilter) throws DescriptorValidationException {
            GenericDescriptor genericDescriptorFindSymbol;
            String string;
            if (str.startsWith(".")) {
                string = str.substring(1);
                genericDescriptorFindSymbol = findSymbol(string, searchFilter);
            } else {
                int iIndexOf = str.indexOf(46);
                String strSubstring = iIndexOf == -1 ? str : str.substring(0, iIndexOf);
                StringBuilder sb = new StringBuilder(genericDescriptor.getFullName());
                while (true) {
                    int iLastIndexOf = sb.lastIndexOf(".");
                    if (iLastIndexOf == -1) {
                        genericDescriptorFindSymbol = findSymbol(str, searchFilter);
                        string = str;
                        break;
                    }
                    int i9 = iLastIndexOf + 1;
                    sb.setLength(i9);
                    sb.append(strSubstring);
                    GenericDescriptor genericDescriptorFindSymbol2 = findSymbol(sb.toString(), SearchFilter.AGGREGATES_ONLY);
                    if (genericDescriptorFindSymbol2 != null) {
                        if (iIndexOf != -1) {
                            sb.setLength(i9);
                            sb.append(str);
                            genericDescriptorFindSymbol = findSymbol(sb.toString(), searchFilter);
                        } else {
                            genericDescriptorFindSymbol = genericDescriptorFindSymbol2;
                        }
                        string = sb.toString();
                    } else {
                        sb.setLength(iLastIndexOf);
                    }
                }
            }
            if (genericDescriptorFindSymbol != null) {
                return genericDescriptorFindSymbol;
            }
            if (!this.allowUnknownDependencies || searchFilter != SearchFilter.TYPES_ONLY) {
                StringBuilder sb2 = new StringBuilder(str.length() + 18);
                sb2.append("\"");
                sb2.append(str);
                sb2.append("\" is not defined.");
                throw new DescriptorValidationException(genericDescriptor, sb2.toString(), (C43451) null);
            }
            Logger logger = Descriptors.logger;
            StringBuilder sb3 = new StringBuilder(str.length() + 87);
            sb3.append("The descriptor for message type \"");
            sb3.append(str);
            sb3.append("\" can not be found and a placeholder is created for it");
            logger.warning(sb3.toString());
            Descriptor descriptor = new Descriptor(string);
            this.dependencies.add(descriptor.getFile());
            return descriptor;
        }

        public GenericDescriptor findSymbol(String str, SearchFilter searchFilter) {
            GenericDescriptor genericDescriptor = this.descriptorsByName.get(str);
            if (genericDescriptor != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor))))) {
                return genericDescriptor;
            }
            Iterator<FileDescriptor> it = this.dependencies.iterator();
            while (it.hasNext()) {
                GenericDescriptor genericDescriptor2 = it.next().pool.descriptorsByName.get(str);
                if (genericDescriptor2 != null && (searchFilter == SearchFilter.ALL_SYMBOLS || ((searchFilter == SearchFilter.TYPES_ONLY && isType(genericDescriptor2)) || (searchFilter == SearchFilter.AGGREGATES_ONLY && isAggregate(genericDescriptor2))))) {
                    return genericDescriptor2;
                }
            }
            return null;
        }
    }

    public static class DescriptorValidationException extends Exception {
        private static final long serialVersionUID = 5750205775490483148L;
        private final String description;
        private final String name;
        private final Message proto;

        public /* synthetic */ DescriptorValidationException(FileDescriptor fileDescriptor, String str, C43451 c43451) {
            this(fileDescriptor, str);
        }

        public String getDescription() {
            return this.description;
        }

        public Message getProblemProto() {
            return this.proto;
        }

        public String getProblemSymbolName() {
            return this.name;
        }

        public /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, C43451 c43451) {
            this(genericDescriptor, str);
        }

        public /* synthetic */ DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th, C43451 c43451) {
            this(genericDescriptor, str, th);
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str) {
            String fullName = genericDescriptor.getFullName();
            StringBuilder sb = new StringBuilder(String.valueOf(fullName).length() + 2 + String.valueOf(str).length());
            sb.append(fullName);
            sb.append(": ");
            sb.append(str);
            super(sb.toString());
            this.name = genericDescriptor.getFullName();
            this.proto = genericDescriptor.toProto();
            this.description = str;
        }

        private DescriptorValidationException(GenericDescriptor genericDescriptor, String str, Throwable th) {
            this(genericDescriptor, str);
            initCause(th);
        }

        private DescriptorValidationException(FileDescriptor fileDescriptor, String str) {
            String name = fileDescriptor.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 2 + String.valueOf(str).length());
            sb.append(name);
            sb.append(": ");
            sb.append(str);
            super(sb.toString());
            this.name = fileDescriptor.getName();
            this.proto = fileDescriptor.toProto();
            this.description = str;
        }
    }

    public static final class EnumDescriptor extends GenericDescriptor implements Internal.EnumLiteMap<EnumValueDescriptor> {
        private final Descriptor containingType;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumDescriptorProto proto;
        private EnumValueDescriptor[] values;

        public /* synthetic */ EnumDescriptor(DescriptorProtos.EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9, C43451 c43451) {
            this(enumDescriptorProto, fileDescriptor, descriptor, i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumDescriptorProto enumDescriptorProto) {
            this.proto = enumDescriptorProto;
            int i9 = 0;
            while (true) {
                EnumValueDescriptor[] enumValueDescriptorArr = this.values;
                if (i9 >= enumValueDescriptorArr.length) {
                    return;
                }
                enumValueDescriptorArr[i9].setProto(enumDescriptorProto.getValue(i9));
                i9++;
            }
        }

        public EnumValueDescriptor findValueByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            String str2 = this.fullName;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
            sb.append(str2);
            sb.append(".");
            sb.append(str);
            GenericDescriptor genericDescriptorFindSymbol = descriptorPool.findSymbol(sb.toString());
            if (genericDescriptorFindSymbol == null || !(genericDescriptorFindSymbol instanceof EnumValueDescriptor)) {
                return null;
            }
            return (EnumValueDescriptor) genericDescriptorFindSymbol;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public DescriptorProtos.EnumOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<EnumValueDescriptor> getValues() {
            return Collections.unmodifiableList(Arrays.asList(this.values));
        }

        private EnumDescriptor(DescriptorProtos.EnumDescriptorProto enumDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9) throws DescriptorValidationException {
            this.index = i9;
            this.proto = enumDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, enumDescriptorProto.getName());
            this.file = fileDescriptor;
            this.containingType = descriptor;
            if (enumDescriptorProto.getValueCount() == 0) {
                throw new DescriptorValidationException(this, "Enums must contain at least one value.", (C43451) null);
            }
            this.values = new EnumValueDescriptor[enumDescriptorProto.getValueCount()];
            for (int i10 = 0; i10 < enumDescriptorProto.getValueCount(); i10++) {
                this.values[i10] = new EnumValueDescriptor(enumDescriptorProto.getValue(i10), fileDescriptor, this, i10, null);
            }
            fileDescriptor.pool.addSymbol(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Internal.EnumLiteMap
        public EnumValueDescriptor findValueByNumber(int i9) {
            return (EnumValueDescriptor) this.file.pool.enumValuesByNumber.get(new DescriptorPool.DescriptorIntPair(this, i9));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumDescriptorProto toProto() {
            return this.proto;
        }
    }

    public static final class EnumValueDescriptor extends GenericDescriptor implements Internal.EnumLite {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumValueDescriptorProto proto;
        private final EnumDescriptor type;

        public /* synthetic */ EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i9, C43451 c43451) {
            this(enumValueDescriptorProto, fileDescriptor, enumDescriptor, i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto) {
            this.proto = enumValueDescriptorProto;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        public DescriptorProtos.EnumValueOptions getOptions() {
            return this.proto.getOptions();
        }

        public EnumDescriptor getType() {
            return this.type;
        }

        public String toString() {
            return this.proto.getName();
        }

        private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto enumValueDescriptorProto, FileDescriptor fileDescriptor, EnumDescriptor enumDescriptor, int i9) throws DescriptorValidationException {
            this.index = i9;
            this.proto = enumValueDescriptorProto;
            this.file = fileDescriptor;
            this.type = enumDescriptor;
            String fullName = enumDescriptor.getFullName();
            String name = enumValueDescriptorProto.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(fullName).length() + 1 + String.valueOf(name).length());
            sb.append(fullName);
            sb.append(".");
            sb.append(name);
            this.fullName = sb.toString();
            fileDescriptor.pool.addSymbol(this);
            fileDescriptor.pool.addEnumValueByNumber(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumValueDescriptorProto toProto() {
            return this.proto;
        }
    }

    public static final class FieldDescriptor extends GenericDescriptor implements Comparable<FieldDescriptor>, FieldSet.FieldDescriptorLite<FieldDescriptor> {
        private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
        private OneofDescriptor containingOneof;
        private Descriptor containingType;
        private Object defaultValue;
        private EnumDescriptor enumType;
        private final Descriptor extensionScope;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor messageType;
        private DescriptorProtos.FieldDescriptorProto proto;
        private Type type;

        public enum JavaType {
            INT(0),
            LONG(0L),
            FLOAT(Float.valueOf(BitmapDescriptorFactory.HUE_RED)),
            DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
            BOOLEAN(Boolean.FALSE),
            STRING(""),
            BYTE_STRING(ByteString.EMPTY),
            ENUM(null),
            MESSAGE(null);

            private final Object defaultDefault;

            JavaType(Object obj) {
                this.defaultDefault = obj;
            }
        }

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'INT64' uses external variables
        	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
        	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
        	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
         */
        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        public static final class Type {
            private static final /* synthetic */ Type[] $VALUES;
            public static final Type BOOL;
            public static final Type BYTES;
            public static final Type DOUBLE;
            public static final Type ENUM;
            public static final Type FIXED32;
            public static final Type FIXED64;
            public static final Type FLOAT;
            public static final Type GROUP;
            public static final Type INT32;
            public static final Type INT64;
            public static final Type MESSAGE;
            public static final Type SFIXED32;
            public static final Type SFIXED64;
            public static final Type SINT32;
            public static final Type SINT64;
            public static final Type STRING;
            public static final Type UINT32;
            public static final Type UINT64;
            private JavaType javaType;

            static {
                Type type = new Type("DOUBLE", 0, JavaType.DOUBLE);
                DOUBLE = type;
                Type type2 = new Type("FLOAT", 1, JavaType.FLOAT);
                FLOAT = type2;
                JavaType javaType = JavaType.LONG;
                Type type3 = new Type("INT64", 2, javaType);
                INT64 = type3;
                Type type4 = new Type("UINT64", 3, javaType);
                UINT64 = type4;
                JavaType javaType2 = JavaType.INT;
                Type type5 = new Type("INT32", 4, javaType2);
                INT32 = type5;
                Type type6 = new Type("FIXED64", 5, javaType);
                FIXED64 = type6;
                Type type7 = new Type("FIXED32", 6, javaType2);
                FIXED32 = type7;
                Type type8 = new Type("BOOL", 7, JavaType.BOOLEAN);
                BOOL = type8;
                Type type9 = new Type("STRING", 8, JavaType.STRING);
                STRING = type9;
                JavaType javaType3 = JavaType.MESSAGE;
                Type type10 = new Type("GROUP", 9, javaType3);
                GROUP = type10;
                Type type11 = new Type(C5371i.type, 10, javaType3);
                MESSAGE = type11;
                Type type12 = new Type("BYTES", 11, JavaType.BYTE_STRING);
                BYTES = type12;
                Type type13 = new Type("UINT32", 12, javaType2);
                UINT32 = type13;
                Type type14 = new Type("ENUM", 13, JavaType.ENUM);
                ENUM = type14;
                Type type15 = new Type("SFIXED32", 14, javaType2);
                SFIXED32 = type15;
                Type type16 = new Type("SFIXED64", 15, javaType);
                SFIXED64 = type16;
                Type type17 = new Type("SINT32", 16, javaType2);
                SINT32 = type17;
                Type type18 = new Type("SINT64", 17, javaType);
                SINT64 = type18;
                $VALUES = new Type[]{type, type2, type3, type4, type5, type6, type7, type8, type9, type10, type11, type12, type13, type14, type15, type16, type17, type18};
            }

            private Type(String str, int i9, JavaType javaType) {
                this.javaType = javaType;
            }

            public static Type valueOf(String str) {
                return (Type) Enum.valueOf(Type.class, str);
            }

            public static Type[] values() {
                return (Type[]) $VALUES.clone();
            }

            public JavaType getJavaType() {
                return this.javaType;
            }

            public DescriptorProtos.FieldDescriptorProto.Type toProto() {
                return DescriptorProtos.FieldDescriptorProto.Type.valueOf(ordinal() + 1);
            }

            public static Type valueOf(DescriptorProtos.FieldDescriptorProto.Type type) {
                return values()[type.getNumber() - 1];
            }
        }

        static {
            if (Type.values().length != DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Desrciptors.java wasn't updated.");
            }
        }

        public /* synthetic */ FieldDescriptor(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9, boolean z8, C43451 c43451) {
            this(fieldDescriptorProto, fileDescriptor, descriptor, i9, z8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public void crossLink() throws DescriptorValidationException {
            C43451 c43451 = null;
            if (this.proto.hasExtendee()) {
                GenericDescriptor genericDescriptorLookupSymbol = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!(genericDescriptorLookupSymbol instanceof Descriptor)) {
                    String extendee = this.proto.getExtendee();
                    StringBuilder sb = new StringBuilder(String.valueOf(extendee).length() + 25);
                    sb.append("\"");
                    sb.append(extendee);
                    sb.append("\" is not a message type.");
                    throw new DescriptorValidationException(this, sb.toString(), c43451);
                }
                this.containingType = (Descriptor) genericDescriptorLookupSymbol;
                if (!getContainingType().isExtensionNumber(getNumber())) {
                    String fullName = getContainingType().getFullName();
                    int number = getNumber();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(fullName).length() + 55);
                    sb2.append("\"");
                    sb2.append(fullName);
                    sb2.append("\" does not declare ");
                    sb2.append(number);
                    sb2.append(" as an extension number.");
                    throw new DescriptorValidationException(this, sb2.toString(), c43451);
                }
            }
            if (this.proto.hasTypeName()) {
                GenericDescriptor genericDescriptorLookupSymbol2 = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!this.proto.hasType()) {
                    if (genericDescriptorLookupSymbol2 instanceof Descriptor) {
                        this.type = Type.MESSAGE;
                    } else {
                        if (!(genericDescriptorLookupSymbol2 instanceof EnumDescriptor)) {
                            String typeName = this.proto.getTypeName();
                            StringBuilder sb3 = new StringBuilder(String.valueOf(typeName).length() + 17);
                            sb3.append("\"");
                            sb3.append(typeName);
                            sb3.append("\" is not a type.");
                            throw new DescriptorValidationException(this, sb3.toString(), c43451);
                        }
                        this.type = Type.ENUM;
                    }
                }
                if (getJavaType() == JavaType.MESSAGE) {
                    if (!(genericDescriptorLookupSymbol2 instanceof Descriptor)) {
                        String typeName2 = this.proto.getTypeName();
                        StringBuilder sb4 = new StringBuilder(String.valueOf(typeName2).length() + 25);
                        sb4.append("\"");
                        sb4.append(typeName2);
                        sb4.append("\" is not a message type.");
                        throw new DescriptorValidationException(this, sb4.toString(), c43451);
                    }
                    this.messageType = (Descriptor) genericDescriptorLookupSymbol2;
                    if (this.proto.hasDefaultValue()) {
                        throw new DescriptorValidationException(this, "Messages can't have default values.", c43451);
                    }
                } else {
                    if (getJavaType() != JavaType.ENUM) {
                        throw new DescriptorValidationException(this, "Field with primitive type has type_name.", c43451);
                    }
                    if (!(genericDescriptorLookupSymbol2 instanceof EnumDescriptor)) {
                        String typeName3 = this.proto.getTypeName();
                        StringBuilder sb5 = new StringBuilder(String.valueOf(typeName3).length() + 23);
                        sb5.append("\"");
                        sb5.append(typeName3);
                        sb5.append("\" is not an enum type.");
                        throw new DescriptorValidationException(this, sb5.toString(), c43451);
                    }
                    this.enumType = (EnumDescriptor) genericDescriptorLookupSymbol2;
                }
            } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
                throw new DescriptorValidationException(this, "Field with message or enum type missing type_name.", c43451);
            }
            if (this.proto.getOptions().getPacked() && !isPackable()) {
                throw new DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.", c43451);
            }
            if (this.proto.hasDefaultValue()) {
                if (isRepeated()) {
                    throw new DescriptorValidationException(this, "Repeated fields cannot have default values.", c43451);
                }
                try {
                    switch (C43451.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[getType().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
                            break;
                        case 4:
                        case 5:
                            this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
                            break;
                        case 6:
                        case 7:
                        case 8:
                            this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
                            break;
                        case 9:
                        case 10:
                            this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
                            break;
                        case 11:
                            if (!this.proto.getDefaultValue().equals("inf")) {
                                if (!this.proto.getDefaultValue().equals("-inf")) {
                                    if (!this.proto.getDefaultValue().equals("nan")) {
                                        this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                                        break;
                                    } else {
                                        this.defaultValue = Float.valueOf(Float.NaN);
                                        break;
                                    }
                                } else {
                                    this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                                    break;
                                }
                            } else {
                                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                                break;
                            }
                        case 12:
                            if (!this.proto.getDefaultValue().equals("inf")) {
                                if (!this.proto.getDefaultValue().equals("-inf")) {
                                    if (!this.proto.getDefaultValue().equals("nan")) {
                                        this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                                        break;
                                    } else {
                                        this.defaultValue = Double.valueOf(Double.NaN);
                                        break;
                                    }
                                } else {
                                    this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                                    break;
                                }
                            } else {
                                this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                                break;
                            }
                        case 13:
                            this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                            break;
                        case 14:
                            this.defaultValue = this.proto.getDefaultValue();
                            break;
                        case 15:
                            try {
                                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                                break;
                            } catch (TextFormat.InvalidEscapeSequenceException e9) {
                                String strValueOf = String.valueOf(e9.getMessage());
                                throw new DescriptorValidationException(this, strValueOf.length() != 0 ? "Couldn't parse default value: ".concat(strValueOf) : new String("Couldn't parse default value: "), e9, c43451);
                            }
                        case 16:
                            EnumValueDescriptor enumValueDescriptorFindValueByName = this.enumType.findValueByName(this.proto.getDefaultValue());
                            this.defaultValue = enumValueDescriptorFindValueByName;
                            if (enumValueDescriptorFindValueByName == null) {
                                String defaultValue = this.proto.getDefaultValue();
                                StringBuilder sb6 = new StringBuilder(String.valueOf(defaultValue).length() + 30);
                                sb6.append("Unknown enum default value: \"");
                                sb6.append(defaultValue);
                                sb6.append("\"");
                                throw new DescriptorValidationException(this, sb6.toString(), c43451);
                            }
                            break;
                        case 17:
                        case 18:
                            throw new DescriptorValidationException(this, "Message type had default value.", c43451);
                    }
                } catch (NumberFormatException e10) {
                    String defaultValue2 = this.proto.getDefaultValue();
                    StringBuilder sb7 = new StringBuilder(String.valueOf(defaultValue2).length() + 33);
                    sb7.append("Could not parse default value: \"");
                    sb7.append(defaultValue2);
                    sb7.append("\"");
                    throw new DescriptorValidationException(this, sb7.toString(), e10, c43451);
                }
            } else if (isRepeated()) {
                this.defaultValue = Collections.emptyList();
            } else {
                int i9 = C43451.f15618xdde82548[getJavaType().ordinal()];
                if (i9 == 1) {
                    this.defaultValue = this.enumType.getValues().get(0);
                } else if (i9 != 2) {
                    this.defaultValue = getJavaType().defaultDefault;
                } else {
                    this.defaultValue = null;
                }
            }
            if (!isExtension()) {
                this.file.pool.addFieldByNumber(this);
            }
            Descriptor descriptor = this.containingType;
            if (descriptor == null || !descriptor.getOptions().getMessageSetWireFormat()) {
                return;
            }
            if (!isExtension()) {
                throw new DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.", c43451);
            }
            if (!isOptional() || getType() != Type.MESSAGE) {
                throw new DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.", c43451);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto) {
            this.proto = fieldDescriptorProto;
        }

        public OneofDescriptor getContainingOneof() {
            return this.containingOneof;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public Object getDefaultValue() {
            if (getJavaType() != JavaType.MESSAGE) {
                return this.defaultValue;
            }
            throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
        }

        public Descriptor getExtensionScope() {
            if (isExtension()) {
                return this.extensionScope;
            }
            throw new UnsupportedOperationException("This field is not an extension.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        public JavaType getJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return getLiteType().getJavaType();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return table[this.type.ordinal()];
        }

        public Descriptor getMessageType() {
            if (getJavaType() == JavaType.MESSAGE) {
                return this.messageType;
            }
            throw new UnsupportedOperationException("This field is not of message type.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        public DescriptorProtos.FieldOptions getOptions() {
            return this.proto.getOptions();
        }

        public Type getType() {
            return this.type;
        }

        public boolean hasDefaultValue() {
            return this.proto.hasDefaultValue();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Message.Builder) builder).mergeFrom((Message) messageLite);
        }

        public boolean isExtension() {
            return this.proto.hasExtendee();
        }

        public boolean isOptional() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        }

        public boolean isPackable() {
            return isRepeated() && getLiteType().isPackable();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            return getOptions().getPacked();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
        }

        public boolean isRequired() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
        }

        public boolean needsUtf8Check() {
            return this.type == Type.STRING && getFile().getOptions().getJavaStringCheckUtf8();
        }

        private FieldDescriptor(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9, boolean z8) throws DescriptorValidationException {
            this.index = i9;
            this.proto = fieldDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, fieldDescriptorProto.getName());
            this.file = fileDescriptor;
            if (fieldDescriptorProto.hasType()) {
                this.type = Type.valueOf(fieldDescriptorProto.getType());
            }
            C43451 c43451 = null;
            if (getNumber() <= 0) {
                throw new DescriptorValidationException(this, "Field numbers must be positive integers.", c43451);
            }
            if (z8) {
                if (!fieldDescriptorProto.hasExtendee()) {
                    throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field.", c43451);
                }
                this.containingType = null;
                if (descriptor != null) {
                    this.extensionScope = descriptor;
                } else {
                    this.extensionScope = null;
                }
                if (fieldDescriptorProto.hasOneofIndex()) {
                    throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index set for extension field.", c43451);
                }
                this.containingOneof = null;
            } else {
                if (fieldDescriptorProto.hasExtendee()) {
                    throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field.", c43451);
                }
                this.containingType = descriptor;
                if (!fieldDescriptorProto.hasOneofIndex()) {
                    this.containingOneof = null;
                } else {
                    if (fieldDescriptorProto.getOneofIndex() < 0 || fieldDescriptorProto.getOneofIndex() >= descriptor.toProto().getOneofDeclCount()) {
                        String strValueOf = String.valueOf(descriptor.getName());
                        throw new DescriptorValidationException(this, strValueOf.length() != 0 ? "FieldDescriptorProto.oneof_index is out of range for type ".concat(strValueOf) : new String("FieldDescriptorProto.oneof_index is out of range for type "), c43451);
                    }
                    OneofDescriptor oneofDescriptor = descriptor.getOneofs().get(fieldDescriptorProto.getOneofIndex());
                    this.containingOneof = oneofDescriptor;
                    OneofDescriptor.access$1808(oneofDescriptor);
                }
                this.extensionScope = null;
            }
            fileDescriptor.pool.addSymbol(this);
        }

        @Override // java.lang.Comparable
        public int compareTo(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.containingType == this.containingType) {
                return getNumber() - fieldDescriptor.getNumber();
            }
            throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public EnumDescriptor getEnumType() {
            if (getJavaType() == JavaType.ENUM) {
                return this.enumType;
            }
            throw new UnsupportedOperationException("This field is not of enum type.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.FieldDescriptorProto toProto() {
            return this.proto;
        }
    }

    public static final class FileDescriptor extends GenericDescriptor {
        private final FileDescriptor[] dependencies;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final Descriptor[] messageTypes;
        private final DescriptorPool pool;
        private DescriptorProtos.FileDescriptorProto proto;
        private final FileDescriptor[] publicDependencies;
        private final ServiceDescriptor[] services;

        public interface InternalDescriptorAssigner {
            ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor);
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x0075, code lost:
        
            throw new com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.DescriptorValidationException(r11, "Invalid public dependency index.", r4);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private FileDescriptor(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr, DescriptorPool descriptorPool, boolean z8) throws DescriptorValidationException {
            this.pool = descriptorPool;
            this.proto = fileDescriptorProto;
            this.dependencies = (FileDescriptor[]) fileDescriptorArr.clone();
            HashMap map = new HashMap();
            for (FileDescriptor fileDescriptor : fileDescriptorArr) {
                map.put(fileDescriptor.getName(), fileDescriptor);
            }
            ArrayList arrayList = new ArrayList();
            int i9 = 0;
            while (true) {
                C43451 c43451 = null;
                if (i9 >= fileDescriptorProto.getPublicDependencyCount()) {
                    FileDescriptor[] fileDescriptorArr2 = new FileDescriptor[arrayList.size()];
                    this.publicDependencies = fileDescriptorArr2;
                    arrayList.toArray(fileDescriptorArr2);
                    descriptorPool.addPackage(getPackage(), this);
                    this.messageTypes = new Descriptor[fileDescriptorProto.getMessageTypeCount()];
                    for (int i10 = 0; i10 < fileDescriptorProto.getMessageTypeCount(); i10++) {
                        this.messageTypes[i10] = new Descriptor(fileDescriptorProto.getMessageType(i10), this, null, i10, null);
                    }
                    this.enumTypes = new EnumDescriptor[fileDescriptorProto.getEnumTypeCount()];
                    for (int i11 = 0; i11 < fileDescriptorProto.getEnumTypeCount(); i11++) {
                        this.enumTypes[i11] = new EnumDescriptor(fileDescriptorProto.getEnumType(i11), this, null, i11, null);
                    }
                    this.services = new ServiceDescriptor[fileDescriptorProto.getServiceCount()];
                    for (int i12 = 0; i12 < fileDescriptorProto.getServiceCount(); i12++) {
                        this.services[i12] = new ServiceDescriptor(fileDescriptorProto.getService(i12), this, i12, c43451);
                    }
                    this.extensions = new FieldDescriptor[fileDescriptorProto.getExtensionCount()];
                    for (int i13 = 0; i13 < fileDescriptorProto.getExtensionCount(); i13++) {
                        this.extensions[i13] = new FieldDescriptor(fileDescriptorProto.getExtension(i13), this, null, i13, true, null);
                    }
                    return;
                }
                int publicDependency = fileDescriptorProto.getPublicDependency(i9);
                if (publicDependency < 0 || publicDependency >= fileDescriptorProto.getDependencyCount()) {
                    break;
                }
                String dependency = fileDescriptorProto.getDependency(publicDependency);
                FileDescriptor fileDescriptor2 = (FileDescriptor) map.get(dependency);
                if (fileDescriptor2 != null) {
                    arrayList.add(fileDescriptor2);
                } else if (!z8) {
                    String strValueOf = String.valueOf(dependency);
                    throw new DescriptorValidationException(this, strValueOf.length() != 0 ? "Invalid public dependency: ".concat(strValueOf) : new String("Invalid public dependency: "), c43451);
                }
                i9++;
            }
        }

        public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr) {
            return buildFrom(fileDescriptorProto, fileDescriptorArr, false);
        }

        private void crossLink() throws DescriptorValidationException {
            for (Descriptor descriptor : this.messageTypes) {
                descriptor.crossLink();
            }
            for (ServiceDescriptor serviceDescriptor : this.services) {
                serviceDescriptor.crossLink();
            }
            for (FieldDescriptor fieldDescriptor : this.extensions) {
                fieldDescriptor.crossLink();
            }
        }

        public static void internalBuildGeneratedFileFrom(String[] strArr, FileDescriptor[] fileDescriptorArr, InternalDescriptorAssigner internalDescriptorAssigner) throws UnsupportedEncodingException {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
            }
            try {
                byte[] bytes = sb.toString().getBytes(CharEncoding.ISO_8859_1);
                try {
                    DescriptorProtos.FileDescriptorProto from = DescriptorProtos.FileDescriptorProto.parseFrom(bytes);
                    try {
                        FileDescriptor fileDescriptorBuildFrom = buildFrom(from, fileDescriptorArr, true);
                        ExtensionRegistry extensionRegistryAssignDescriptors = internalDescriptorAssigner.assignDescriptors(fileDescriptorBuildFrom);
                        if (extensionRegistryAssignDescriptors != null) {
                            try {
                                fileDescriptorBuildFrom.setProto(DescriptorProtos.FileDescriptorProto.parseFrom(bytes, extensionRegistryAssignDescriptors));
                            } catch (InvalidProtocolBufferException e9) {
                                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e9);
                            }
                        }
                    } catch (DescriptorValidationException e10) {
                        String name = from.getName();
                        StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 35);
                        sb2.append("Invalid embedded descriptor for \"");
                        sb2.append(name);
                        sb2.append("\".");
                        throw new IllegalArgumentException(sb2.toString(), e10);
                    }
                } catch (InvalidProtocolBufferException e11) {
                    throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e11);
                }
            } catch (UnsupportedEncodingException e12) {
                throw new RuntimeException("Standard encoding ISO-8859-1 not supported by JVM.", e12);
            }
        }

        public static void internalUpdateFileDescriptor(FileDescriptor fileDescriptor, ExtensionRegistry extensionRegistry) {
            try {
                fileDescriptor.setProto(DescriptorProtos.FileDescriptorProto.parseFrom(fileDescriptor.proto.toByteString(), extensionRegistry));
            } catch (InvalidProtocolBufferException e9) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e9);
            }
        }

        private void setProto(DescriptorProtos.FileDescriptorProto fileDescriptorProto) {
            this.proto = fileDescriptorProto;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                Descriptor[] descriptorArr = this.messageTypes;
                if (i10 >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i10].setProto(fileDescriptorProto.getMessageType(i10));
                i10++;
            }
            int i11 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i11 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i11].setProto(fileDescriptorProto.getEnumType(i11));
                i11++;
            }
            int i12 = 0;
            while (true) {
                ServiceDescriptor[] serviceDescriptorArr = this.services;
                if (i12 >= serviceDescriptorArr.length) {
                    break;
                }
                serviceDescriptorArr[i12].setProto(fileDescriptorProto.getService(i12));
                i12++;
            }
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.extensions;
                if (i9 >= fieldDescriptorArr.length) {
                    return;
                }
                fieldDescriptorArr[i9].setProto(fileDescriptorProto.getExtension(i9));
                i9++;
            }
        }

        public EnumDescriptor findEnumTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                String str2 = getPackage();
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + str.length());
                sb.append(str2);
                sb.append(".");
                sb.append(str);
                str = sb.toString();
            }
            GenericDescriptor genericDescriptorFindSymbol = this.pool.findSymbol(str);
            if (genericDescriptorFindSymbol != null && (genericDescriptorFindSymbol instanceof EnumDescriptor) && genericDescriptorFindSymbol.getFile() == this) {
                return (EnumDescriptor) genericDescriptorFindSymbol;
            }
            return null;
        }

        public FieldDescriptor findExtensionByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                String str2 = getPackage();
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + str.length());
                sb.append(str2);
                sb.append(".");
                sb.append(str);
                str = sb.toString();
            }
            GenericDescriptor genericDescriptorFindSymbol = this.pool.findSymbol(str);
            if (genericDescriptorFindSymbol != null && (genericDescriptorFindSymbol instanceof FieldDescriptor) && genericDescriptorFindSymbol.getFile() == this) {
                return (FieldDescriptor) genericDescriptorFindSymbol;
            }
            return null;
        }

        public Descriptor findMessageTypeByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                String str2 = getPackage();
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + str.length());
                sb.append(str2);
                sb.append(".");
                sb.append(str);
                str = sb.toString();
            }
            GenericDescriptor genericDescriptorFindSymbol = this.pool.findSymbol(str);
            if (genericDescriptorFindSymbol != null && (genericDescriptorFindSymbol instanceof Descriptor) && genericDescriptorFindSymbol.getFile() == this) {
                return (Descriptor) genericDescriptorFindSymbol;
            }
            return null;
        }

        public ServiceDescriptor findServiceByName(String str) {
            if (str.indexOf(46) != -1) {
                return null;
            }
            if (getPackage().length() > 0) {
                String str2 = getPackage();
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + str.length());
                sb.append(str2);
                sb.append(".");
                sb.append(str);
                str = sb.toString();
            }
            GenericDescriptor genericDescriptorFindSymbol = this.pool.findSymbol(str);
            if (genericDescriptorFindSymbol != null && (genericDescriptorFindSymbol instanceof ServiceDescriptor) && genericDescriptorFindSymbol.getFile() == this) {
                return (ServiceDescriptor) genericDescriptorFindSymbol;
            }
            return null;
        }

        public List<FileDescriptor> getDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.dependencies));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.proto.getName();
        }

        public List<Descriptor> getMessageTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public DescriptorProtos.FileOptions getOptions() {
            return this.proto.getOptions();
        }

        public String getPackage() {
            return this.proto.getPackage();
        }

        public List<FileDescriptor> getPublicDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
        }

        public List<ServiceDescriptor> getServices() {
            return Collections.unmodifiableList(Arrays.asList(this.services));
        }

        private static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto fileDescriptorProto, FileDescriptor[] fileDescriptorArr, boolean z8) throws DescriptorValidationException {
            FileDescriptor fileDescriptor = new FileDescriptor(fileDescriptorProto, fileDescriptorArr, new DescriptorPool(fileDescriptorArr, z8), z8);
            fileDescriptor.crossLink();
            return fileDescriptor;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.FileDescriptorProto toProto() {
            return this.proto;
        }

        public static void internalBuildGeneratedFileFrom(String[] strArr, Class<?> cls, String[] strArr2, String[] strArr3, InternalDescriptorAssigner internalDescriptorAssigner) throws UnsupportedEncodingException {
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < strArr2.length; i9++) {
                try {
                    arrayList.add((FileDescriptor) cls.getClassLoader().loadClass(strArr2[i9]).getField("descriptor").get(null));
                } catch (Exception unused) {
                    Logger logger = Descriptors.logger;
                    String str = strArr3[i9];
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36);
                    sb.append("Descriptors for \"");
                    sb.append(str);
                    sb.append("\" can not be found.");
                    logger.warning(sb.toString());
                }
            }
            FileDescriptor[] fileDescriptorArr = new FileDescriptor[arrayList.size()];
            arrayList.toArray(fileDescriptorArr);
            internalBuildGeneratedFileFrom(strArr, fileDescriptorArr, internalDescriptorAssigner);
        }

        public FileDescriptor(String str, Descriptor descriptor) throws DescriptorValidationException {
            DescriptorPool descriptorPool = new DescriptorPool(new FileDescriptor[0], true);
            this.pool = descriptorPool;
            this.proto = DescriptorProtos.FileDescriptorProto.newBuilder().setName(String.valueOf(descriptor.getFullName()).concat(".placeholder.proto")).setPackage(str).addMessageType(descriptor.toProto()).build();
            this.dependencies = new FileDescriptor[0];
            this.publicDependencies = new FileDescriptor[0];
            this.messageTypes = new Descriptor[]{descriptor};
            this.enumTypes = new EnumDescriptor[0];
            this.services = new ServiceDescriptor[0];
            this.extensions = new FieldDescriptor[0];
            descriptorPool.addPackage(str, this);
            descriptorPool.addSymbol(descriptor);
        }
    }

    public static abstract class GenericDescriptor {
        public abstract FileDescriptor getFile();

        public abstract String getFullName();

        public abstract String getName();

        public abstract Message toProto();
    }

    public static final class MethodDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor inputType;
        private Descriptor outputType;
        private DescriptorProtos.MethodDescriptorProto proto;
        private final ServiceDescriptor service;

        public /* synthetic */ MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i9, C43451 c43451) {
            this(methodDescriptorProto, fileDescriptor, serviceDescriptor, i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            DescriptorPool descriptorPool = this.file.pool;
            String inputType = this.proto.getInputType();
            DescriptorPool.SearchFilter searchFilter = DescriptorPool.SearchFilter.TYPES_ONLY;
            GenericDescriptor genericDescriptorLookupSymbol = descriptorPool.lookupSymbol(inputType, this, searchFilter);
            C43451 c43451 = null;
            if (!(genericDescriptorLookupSymbol instanceof Descriptor)) {
                String inputType2 = this.proto.getInputType();
                StringBuilder sb = new StringBuilder(String.valueOf(inputType2).length() + 25);
                sb.append("\"");
                sb.append(inputType2);
                sb.append("\" is not a message type.");
                throw new DescriptorValidationException(this, sb.toString(), c43451);
            }
            this.inputType = (Descriptor) genericDescriptorLookupSymbol;
            GenericDescriptor genericDescriptorLookupSymbol2 = this.file.pool.lookupSymbol(this.proto.getOutputType(), this, searchFilter);
            if (genericDescriptorLookupSymbol2 instanceof Descriptor) {
                this.outputType = (Descriptor) genericDescriptorLookupSymbol2;
                return;
            }
            String outputType = this.proto.getOutputType();
            StringBuilder sb2 = new StringBuilder(String.valueOf(outputType).length() + 25);
            sb2.append("\"");
            sb2.append(outputType);
            sb2.append("\" is not a message type.");
            throw new DescriptorValidationException(this, sb2.toString(), c43451);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.MethodDescriptorProto methodDescriptorProto) {
            this.proto = methodDescriptorProto;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        public Descriptor getInputType() {
            return this.inputType;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public DescriptorProtos.MethodOptions getOptions() {
            return this.proto.getOptions();
        }

        public Descriptor getOutputType() {
            return this.outputType;
        }

        public ServiceDescriptor getService() {
            return this.service;
        }

        private MethodDescriptor(DescriptorProtos.MethodDescriptorProto methodDescriptorProto, FileDescriptor fileDescriptor, ServiceDescriptor serviceDescriptor, int i9) throws DescriptorValidationException {
            this.index = i9;
            this.proto = methodDescriptorProto;
            this.file = fileDescriptor;
            this.service = serviceDescriptor;
            String fullName = serviceDescriptor.getFullName();
            String name = methodDescriptorProto.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(fullName).length() + 1 + String.valueOf(name).length());
            sb.append(fullName);
            sb.append(".");
            sb.append(name);
            this.fullName = sb.toString();
            fileDescriptor.pool.addSymbol(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.MethodDescriptorProto toProto() {
            return this.proto;
        }
    }

    public static final class OneofDescriptor {
        private Descriptor containingType;
        private int fieldCount;
        private FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.OneofDescriptorProto proto;

        public /* synthetic */ OneofDescriptor(DescriptorProtos.OneofDescriptorProto oneofDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9, C43451 c43451) {
            this(oneofDescriptorProto, fileDescriptor, descriptor, i9);
        }

        public static /* synthetic */ int access$1808(OneofDescriptor oneofDescriptor) {
            int i9 = oneofDescriptor.fieldCount;
            oneofDescriptor.fieldCount = i9 + 1;
            return i9;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public FieldDescriptor getField(int i9) {
            return this.fields[i9];
        }

        public int getFieldCount() {
            return this.fieldCount;
        }

        public FileDescriptor getFile() {
            return this.file;
        }

        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        public String getName() {
            return this.proto.getName();
        }

        private OneofDescriptor(DescriptorProtos.OneofDescriptorProto oneofDescriptorProto, FileDescriptor fileDescriptor, Descriptor descriptor, int i9) {
            this.proto = oneofDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, descriptor, oneofDescriptorProto.getName());
            this.file = fileDescriptor;
            this.index = i9;
            this.containingType = descriptor;
            this.fieldCount = 0;
        }
    }

    public static final class ServiceDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private MethodDescriptor[] methods;
        private DescriptorProtos.ServiceDescriptorProto proto;

        public /* synthetic */ ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i9, C43451 c43451) {
            this(serviceDescriptorProto, fileDescriptor, i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (MethodDescriptor methodDescriptor : this.methods) {
                methodDescriptor.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto) {
            this.proto = serviceDescriptorProto;
            int i9 = 0;
            while (true) {
                MethodDescriptor[] methodDescriptorArr = this.methods;
                if (i9 >= methodDescriptorArr.length) {
                    return;
                }
                methodDescriptorArr[i9].setProto(serviceDescriptorProto.getMethod(i9));
                i9++;
            }
        }

        public MethodDescriptor findMethodByName(String str) {
            DescriptorPool descriptorPool = this.file.pool;
            String str2 = this.fullName;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
            sb.append(str2);
            sb.append(".");
            sb.append(str);
            GenericDescriptor genericDescriptorFindSymbol = descriptorPool.findSymbol(sb.toString());
            if (genericDescriptorFindSymbol == null || !(genericDescriptorFindSymbol instanceof MethodDescriptor)) {
                return null;
            }
            return (MethodDescriptor) genericDescriptorFindSymbol;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public int getIndex() {
            return this.index;
        }

        public List<MethodDescriptor> getMethods() {
            return Collections.unmodifiableList(Arrays.asList(this.methods));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        public DescriptorProtos.ServiceOptions getOptions() {
            return this.proto.getOptions();
        }

        private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto serviceDescriptorProto, FileDescriptor fileDescriptor, int i9) throws DescriptorValidationException {
            this.index = i9;
            this.proto = serviceDescriptorProto;
            this.fullName = Descriptors.computeFullName(fileDescriptor, null, serviceDescriptorProto.getName());
            this.file = fileDescriptor;
            this.methods = new MethodDescriptor[serviceDescriptorProto.getMethodCount()];
            for (int i10 = 0; i10 < serviceDescriptorProto.getMethodCount(); i10++) {
                this.methods[i10] = new MethodDescriptor(serviceDescriptorProto.getMethod(i10), fileDescriptor, this, i10, null);
            }
            fileDescriptor.pool.addSymbol(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.ServiceDescriptorProto toProto() {
            return this.proto;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String computeFullName(FileDescriptor fileDescriptor, Descriptor descriptor, String str) {
        if (descriptor != null) {
            String fullName = descriptor.getFullName();
            StringBuilder sb = new StringBuilder(String.valueOf(fullName).length() + 1 + String.valueOf(str).length());
            sb.append(fullName);
            sb.append(".");
            sb.append(str);
            return sb.toString();
        }
        if (fileDescriptor.getPackage().length() <= 0) {
            return str;
        }
        String str2 = fileDescriptor.getPackage();
        StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str).length());
        sb2.append(str2);
        sb2.append(".");
        sb2.append(str);
        return sb2.toString();
    }
}
