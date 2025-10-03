package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ExtensionRegistry;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
public final class TextFormat {
    private static final Printer DEFAULT_PRINTER;
    private static final Printer SINGLE_LINE_PRINTER;
    private static final Printer UNICODE_PRINTER;
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Parser PARSER = Parser.newBuilder().build();

    /* renamed from: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat$3 */
    public static /* synthetic */ class C43663 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    public interface ByteSequence {
        byte byteAt(int i9);

        int size();
    }

    public static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        public InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(String str) {
            this(-1, -1, str);
        }

        public int getColumn() {
            return this.column;
        }

        public int getLine() {
            return this.line;
        }

        public ParseException(int i9, int i10, String str) {
            String strValueOf = String.valueOf(Integer.toString(i9));
            StringBuilder sb = new StringBuilder(strValueOf.length() + 14 + String.valueOf(str).length());
            sb.append(strValueOf);
            sb.append(":");
            sb.append(i10);
            sb.append(": ");
            sb.append(str);
            super(sb.toString());
            this.line = i9;
            this.column = i10;
        }
    }

    public static class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownFields;
        private final SingularOverwritePolicy singularOverwritePolicy;

        public static class Builder {
            private boolean allowUnknownFields = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.singularOverwritePolicy);
            }

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy singularOverwritePolicy) {
                this.singularOverwritePolicy = singularOverwritePolicy;
                return this;
            }
        }

        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo) throws ParseException {
            String str;
            Object objValueOf = null;
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (tokenizer.tryConsume("<")) {
                    str = ">";
                } else {
                    tokenizer.consume("{");
                    str = "}";
                }
                MessageReflection.MergeTarget mergeTargetNewMergeTargetForField = mergeTarget.newMergeTargetForField(fieldDescriptor, extensionInfo != null ? extensionInfo.defaultInstance : null);
                while (!tokenizer.tryConsume(str)) {
                    if (tokenizer.atEnd()) {
                        StringBuilder sb = new StringBuilder(str.length() + 12);
                        sb.append("Expected \"");
                        sb.append(str);
                        sb.append("\".");
                        throw tokenizer.parseException(sb.toString());
                    }
                    mergeField(tokenizer, extensionRegistry, mergeTargetNewMergeTargetForField);
                }
                objValueOf = mergeTargetNewMergeTargetForField.finish();
            } else {
                switch (C43663.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        objValueOf = Integer.valueOf(tokenizer.consumeInt32());
                        break;
                    case 4:
                    case 5:
                    case 6:
                        objValueOf = Long.valueOf(tokenizer.consumeInt64());
                        break;
                    case 7:
                        objValueOf = Boolean.valueOf(tokenizer.consumeBoolean());
                        break;
                    case 8:
                        objValueOf = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    case 9:
                        objValueOf = Double.valueOf(tokenizer.consumeDouble());
                        break;
                    case 10:
                    case 11:
                        objValueOf = Integer.valueOf(tokenizer.consumeUInt32());
                        break;
                    case 12:
                    case 13:
                        objValueOf = Long.valueOf(tokenizer.consumeUInt64());
                        break;
                    case 14:
                        objValueOf = tokenizer.consumeString();
                        break;
                    case 15:
                        objValueOf = tokenizer.consumeByteString();
                        break;
                    case 16:
                        Descriptors.EnumDescriptor enumType = fieldDescriptor.getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int iConsumeInt32 = tokenizer.consumeInt32();
                            objValueOf = enumType.findValueByNumber(iConsumeInt32);
                            if (objValueOf == null) {
                                String fullName = enumType.getFullName();
                                StringBuilder sb2 = new StringBuilder(String.valueOf(fullName).length() + 50);
                                sb2.append("Enum type \"");
                                sb2.append(fullName);
                                sb2.append("\" has no value with number ");
                                sb2.append(iConsumeInt32);
                                sb2.append(".");
                                throw tokenizer.parseExceptionPreviousToken(sb2.toString());
                            }
                        } else {
                            String strConsumeIdentifier = tokenizer.consumeIdentifier();
                            objValueOf = enumType.findValueByName(strConsumeIdentifier);
                            if (objValueOf == null) {
                                String fullName2 = enumType.getFullName();
                                StringBuilder sb3 = new StringBuilder(String.valueOf(fullName2).length() + 35 + String.valueOf(strConsumeIdentifier).length());
                                sb3.append("Enum type \"");
                                sb3.append(fullName2);
                                sb3.append("\" has no value named \"");
                                sb3.append(strConsumeIdentifier);
                                sb3.append("\".");
                                throw tokenizer.parseExceptionPreviousToken(sb3.toString());
                            }
                        }
                        break;
                    case 17:
                    case 18:
                        throw new RuntimeException("Can't get here.");
                }
            }
            if (fieldDescriptor.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptor, objValueOf);
                return;
            }
            SingularOverwritePolicy singularOverwritePolicy = this.singularOverwritePolicy;
            SingularOverwritePolicy singularOverwritePolicy2 = SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES;
            if (singularOverwritePolicy == singularOverwritePolicy2 && mergeTarget.hasField(fieldDescriptor)) {
                String fullName3 = fieldDescriptor.getFullName();
                StringBuilder sb4 = new StringBuilder(String.valueOf(fullName3).length() + 44);
                sb4.append("Non-repeated field \"");
                sb4.append(fullName3);
                sb4.append("\" cannot be overwritten.");
                throw tokenizer.parseExceptionPreviousToken(sb4.toString());
            }
            if (this.singularOverwritePolicy != singularOverwritePolicy2 || fieldDescriptor.getContainingOneof() == null || !mergeTarget.hasOneof(fieldDescriptor.getContainingOneof())) {
                mergeTarget.setField(fieldDescriptor, objValueOf);
                return;
            }
            Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            String fullName4 = fieldDescriptor.getFullName();
            String fullName5 = mergeTarget.getOneofFieldDescriptor(containingOneof).getFullName();
            String name = containingOneof.getName();
            StringBuilder sb5 = new StringBuilder(String.valueOf(fullName4).length() + 70 + String.valueOf(fullName5).length() + String.valueOf(name).length());
            sb5.append("Field \"");
            sb5.append(fullName4);
            sb5.append("\" is specified along with field \"");
            sb5.append(fullName5);
            sb5.append("\", another member of oneof \"");
            sb5.append(name);
            sb5.append("\".");
            throw tokenizer.parseExceptionPreviousToken(sb5.toString());
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget) throws ParseException {
            ExtensionRegistry.ExtensionInfo extensionInfo;
            Descriptors.FieldDescriptor fieldDescriptor;
            Descriptors.Descriptor descriptorForType = mergeTarget.getDescriptorForType();
            Descriptors.FieldDescriptor fieldDescriptor2 = null;
            if (tokenizer.tryConsume("[")) {
                StringBuilder sb = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    sb.append(tokenizer.consumeIdentifier());
                }
                ExtensionRegistry.ExtensionInfo extensionInfoFindExtensionByName = mergeTarget.findExtensionByName(extensionRegistry, sb.toString());
                if (extensionInfoFindExtensionByName == null) {
                    if (!this.allowUnknownFields) {
                        String strValueOf = String.valueOf(sb);
                        StringBuilder sb2 = new StringBuilder(strValueOf.length() + 48);
                        sb2.append("Extension \"");
                        sb2.append(strValueOf);
                        sb2.append("\" not found in the ExtensionRegistry.");
                        throw tokenizer.parseExceptionPreviousToken(sb2.toString());
                    }
                    Logger logger = TextFormat.logger;
                    String strValueOf2 = String.valueOf(sb);
                    StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 48);
                    sb3.append("Extension \"");
                    sb3.append(strValueOf2);
                    sb3.append("\" not found in the ExtensionRegistry.");
                    logger.warning(sb3.toString());
                } else {
                    if (extensionInfoFindExtensionByName.descriptor.getContainingType() != descriptorForType) {
                        String strValueOf3 = String.valueOf(sb);
                        String fullName = descriptorForType.getFullName();
                        StringBuilder sb4 = new StringBuilder(strValueOf3.length() + 45 + String.valueOf(fullName).length());
                        sb4.append("Extension \"");
                        sb4.append(strValueOf3);
                        sb4.append("\" does not extend message type \"");
                        sb4.append(fullName);
                        sb4.append("\".");
                        throw tokenizer.parseExceptionPreviousToken(sb4.toString());
                    }
                    fieldDescriptor2 = extensionInfoFindExtensionByName.descriptor;
                }
                tokenizer.consume("]");
                fieldDescriptor = fieldDescriptor2;
                extensionInfo = extensionInfoFindExtensionByName;
            } else {
                String strConsumeIdentifier = tokenizer.consumeIdentifier();
                Descriptors.FieldDescriptor fieldDescriptorFindFieldByName = descriptorForType.findFieldByName(strConsumeIdentifier);
                if (fieldDescriptorFindFieldByName == null && (fieldDescriptorFindFieldByName = descriptorForType.findFieldByName(strConsumeIdentifier.toLowerCase(Locale.US))) != null && fieldDescriptorFindFieldByName.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                    fieldDescriptorFindFieldByName = null;
                }
                if (fieldDescriptorFindFieldByName != null && fieldDescriptorFindFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP && !fieldDescriptorFindFieldByName.getMessageType().getName().equals(strConsumeIdentifier)) {
                    fieldDescriptorFindFieldByName = null;
                }
                if (fieldDescriptorFindFieldByName == null) {
                    if (!this.allowUnknownFields) {
                        String fullName2 = descriptorForType.getFullName();
                        StringBuilder sb5 = new StringBuilder(String.valueOf(fullName2).length() + 38 + String.valueOf(strConsumeIdentifier).length());
                        sb5.append("Message type \"");
                        sb5.append(fullName2);
                        sb5.append("\" has no field named \"");
                        sb5.append(strConsumeIdentifier);
                        sb5.append("\".");
                        throw tokenizer.parseExceptionPreviousToken(sb5.toString());
                    }
                    Logger logger2 = TextFormat.logger;
                    String fullName3 = descriptorForType.getFullName();
                    StringBuilder sb6 = new StringBuilder(String.valueOf(fullName3).length() + 38 + String.valueOf(strConsumeIdentifier).length());
                    sb6.append("Message type \"");
                    sb6.append(fullName3);
                    sb6.append("\" has no field named \"");
                    sb6.append(strConsumeIdentifier);
                    sb6.append("\".");
                    logger2.warning(sb6.toString());
                }
                extensionInfo = null;
                fieldDescriptor = fieldDescriptorFindFieldByName;
            }
            if (fieldDescriptor == null) {
                if (!tokenizer.tryConsume(":") || tokenizer.lookingAt("{") || tokenizer.lookingAt("<")) {
                    skipFieldMessage(tokenizer);
                    return;
                } else {
                    skipFieldValue(tokenizer);
                    return;
                }
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                tokenizer.tryConsume(":");
            } else {
                tokenizer.consume(":");
            }
            if (!fieldDescriptor.isRepeated() || !tokenizer.tryConsume("[")) {
                consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo);
                return;
            }
            while (true) {
                consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo);
                if (tokenizer.tryConsume("]")) {
                    return;
                } else {
                    tokenizer.consume(",");
                }
            }
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        private void skipField(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsume("[")) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume("]");
            } else {
                tokenizer.consumeIdentifier();
            }
            if (!tokenizer.tryConsume(":") || tokenizer.lookingAt("<") || tokenizer.lookingAt("{")) {
                skipFieldMessage(tokenizer);
            } else {
                skipFieldValue(tokenizer);
            }
            if (tokenizer.tryConsume(";")) {
                return;
            }
            tokenizer.tryConsume(",");
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String str;
            if (tokenizer.tryConsume("<")) {
                str = ">";
            } else {
                tokenizer.consume("{");
                str = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                skipField(tokenizer);
            }
            tokenizer.consume(str);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsumeString()) {
                while (tokenizer.tryConsumeString()) {
                }
                return;
            }
            if (tokenizer.tryConsumeIdentifier() || tokenizer.tryConsumeInt64() || tokenizer.tryConsumeUInt64() || tokenizer.tryConsumeDouble() || tokenizer.tryConsumeFloat()) {
                return;
            }
            String strValueOf = String.valueOf(tokenizer.currentToken);
            throw tokenizer.parseException(strValueOf.length() != 0 ? "Invalid field value: ".concat(strValueOf) : new String("Invalid field value: "));
        }

        private static StringBuilder toStringBuilder(Readable readable) throws IOException {
            StringBuilder sb = new StringBuilder();
            CharBuffer charBufferAllocate = CharBuffer.allocate(4096);
            while (true) {
                int i9 = readable.read(charBufferAllocate);
                if (i9 == -1) {
                    return sb;
                }
                charBufferAllocate.flip();
                sb.append((CharSequence) charBufferAllocate, 0, i9);
            }
        }

        public void merge(Readable readable, Message.Builder builder) throws ParseException {
            merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        private Parser(boolean z8, SingularOverwritePolicy singularOverwritePolicy) {
            this.allowUnknownFields = z8;
            this.singularOverwritePolicy = singularOverwritePolicy;
        }

        public void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
            merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            merge(toStringBuilder(readable), extensionRegistry, builder);
        }

        public void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(charSequence);
            MessageReflection.BuilderAdapter builderAdapter = new MessageReflection.BuilderAdapter(builder);
            while (!tokenizer.atEnd()) {
                mergeField(tokenizer, extensionRegistry, builderAdapter);
            }
        }
    }

    public static final class Printer {
        boolean escapeNonAscii;
        boolean singleLineMode;

        /* JADX INFO: Access modifiers changed from: private */
        public void print(MessageOrBuilder messageOrBuilder, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
                printField(entry.getKey(), entry.getValue(), textGenerator);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), textGenerator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (!fieldDescriptor.isRepeated()) {
                printSingleField(fieldDescriptor, obj, textGenerator);
                return;
            }
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                printSingleField(fieldDescriptor, it.next(), textGenerator);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            switch (C43663.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    textGenerator.print(((Integer) obj).toString());
                    break;
                case 4:
                case 5:
                case 6:
                    textGenerator.print(((Long) obj).toString());
                    break;
                case 7:
                    textGenerator.print(((Boolean) obj).toString());
                    break;
                case 8:
                    textGenerator.print(((Float) obj).toString());
                    break;
                case 9:
                    textGenerator.print(((Double) obj).toString());
                    break;
                case 10:
                case 11:
                    textGenerator.print(TextFormat.unsignedToString(((Integer) obj).intValue()));
                    break;
                case 12:
                case 13:
                    textGenerator.print(TextFormat.unsignedToString(((Long) obj).longValue()));
                    break;
                case 14:
                    textGenerator.print("\"");
                    textGenerator.print(this.escapeNonAscii ? TextFormat.escapeText((String) obj) : TextFormat.escapeDoubleQuotesAndBackslashes((String) obj));
                    textGenerator.print("\"");
                    break;
                case 15:
                    textGenerator.print("\"");
                    if (obj instanceof ByteString) {
                        textGenerator.print(TextFormat.escapeBytes((ByteString) obj));
                    } else {
                        textGenerator.print(TextFormat.escapeBytes((byte[]) obj));
                    }
                    textGenerator.print("\"");
                    break;
                case 16:
                    textGenerator.print(((Descriptors.EnumValueDescriptor) obj).getName());
                    break;
                case 17:
                case 18:
                    print((Message) obj, textGenerator);
                    break;
            }
        }

        private void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isExtension()) {
                textGenerator.print("[");
                if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
                    textGenerator.print(fieldDescriptor.getMessageType().getFullName());
                } else {
                    textGenerator.print(fieldDescriptor.getFullName());
                }
                textGenerator.print("]");
            } else if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                textGenerator.print(fieldDescriptor.getMessageType().getName());
            } else {
                textGenerator.print(fieldDescriptor.getName());
            }
            Descriptors.FieldDescriptor.JavaType javaType = fieldDescriptor.getJavaType();
            Descriptors.FieldDescriptor.JavaType javaType2 = Descriptors.FieldDescriptor.JavaType.MESSAGE;
            if (javaType != javaType2) {
                textGenerator.print(": ");
            } else if (this.singleLineMode) {
                textGenerator.print(" { ");
            } else {
                textGenerator.print(" {\n");
                textGenerator.indent();
            }
            printFieldValue(fieldDescriptor, obj, textGenerator);
            if (fieldDescriptor.getJavaType() != javaType2) {
                if (this.singleLineMode) {
                    textGenerator.print(StringUtils.SPACE);
                    return;
                } else {
                    textGenerator.print("\n");
                    return;
                }
            }
            if (this.singleLineMode) {
                textGenerator.print("} ");
            } else {
                textGenerator.outdent();
                textGenerator.print("}\n");
            }
        }

        private void printUnknownField(int i9, int i10, List<?> list, TextGenerator textGenerator) throws IOException {
            for (Object obj : list) {
                textGenerator.print(String.valueOf(i9));
                textGenerator.print(": ");
                TextFormat.printUnknownFieldValue(i10, obj, textGenerator);
                textGenerator.print(this.singleLineMode ? StringUtils.SPACE : "\n");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printUnknownFields(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
                int iIntValue = entry.getKey().intValue();
                UnknownFieldSet.Field value = entry.getValue();
                printUnknownField(iIntValue, 0, value.getVarintList(), textGenerator);
                printUnknownField(iIntValue, 5, value.getFixed32List(), textGenerator);
                printUnknownField(iIntValue, 1, value.getFixed64List(), textGenerator);
                printUnknownField(iIntValue, 2, value.getLengthDelimitedList(), textGenerator);
                for (UnknownFieldSet unknownFieldSet2 : value.getGroupList()) {
                    textGenerator.print(entry.getKey().toString());
                    if (this.singleLineMode) {
                        textGenerator.print(" { ");
                    } else {
                        textGenerator.print(" {\n");
                        textGenerator.indent();
                    }
                    printUnknownFields(unknownFieldSet2, textGenerator);
                    if (this.singleLineMode) {
                        textGenerator.print("} ");
                    } else {
                        textGenerator.outdent();
                        textGenerator.print("}\n");
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Printer setEscapeNonAscii(boolean z8) {
            this.escapeNonAscii = z8;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Printer setSingleLineMode(boolean z8) {
            this.singleLineMode = z8;
            return this;
        }

        private Printer() {
            this.singleLineMode = false;
            this.escapeNonAscii = true;
        }
    }

    public static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;

        private void write(CharSequence charSequence) throws IOException {
            if (charSequence.length() == 0) {
                return;
            }
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.indent);
            }
            this.output.append(charSequence);
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.delete(length - 2, length);
        }

        public void print(CharSequence charSequence) throws IOException {
            int length = charSequence.length();
            int i9 = 0;
            for (int i10 = 0; i10 < length; i10++) {
                if (charSequence.charAt(i10) == '\n') {
                    int i11 = i10 + 1;
                    write(charSequence.subSequence(i9, i11));
                    this.atStartOfLine = true;
                    i9 = i11;
                }
            }
            write(charSequence.subSequence(i9, length));
        }

        private TextGenerator(Appendable appendable) {
            this.indent = new StringBuilder();
            this.atStartOfLine = true;
            this.output = appendable;
        }
    }

    public static final class Tokenizer {
        private int column;
        private String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            String strValueOf = String.valueOf(numberFormatException.getMessage());
            return parseException(strValueOf.length() != 0 ? "Couldn't parse number: ".concat(strValueOf) : new String("Couldn't parse number: "));
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            String strValueOf = String.valueOf(numberFormatException.getMessage());
            return parseException(strValueOf.length() != 0 ? "Couldn't parse integer: ".concat(strValueOf) : new String("Couldn't parse integer: "));
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void consume(String str) throws ParseException {
            if (tryConsume(str)) {
                return;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
            sb.append("Expected \"");
            sb.append(str);
            sb.append("\".");
            throw parseException(sb.toString());
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                nextToken();
                return true;
            }
            if (!this.currentToken.equals("false") && !this.currentToken.equals("f") && !this.currentToken.equals("0")) {
                throw parseException("Expected \"true\" or \"false\".");
            }
            nextToken();
            return false;
        }

        public ByteString consumeByteString() throws ParseException {
            ArrayList arrayList = new ArrayList();
            consumeByteString(arrayList);
            while (true) {
                if (!this.currentToken.startsWith("'") && !this.currentToken.startsWith("\"")) {
                    return ByteString.copyFrom(arrayList);
                }
                consumeByteString(arrayList);
            }
        }

        public double consumeDouble() throws ParseException, NumberFormatException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean zStartsWith = this.currentToken.startsWith("-");
                nextToken();
                return zStartsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            }
            if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            }
            try {
                double d9 = Double.parseDouble(this.currentToken);
                nextToken();
                return d9;
            } catch (NumberFormatException e9) {
                throw floatParseException(e9);
            }
        }

        public float consumeFloat() throws ParseException, NumberFormatException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean zStartsWith = this.currentToken.startsWith("-");
                nextToken();
                return zStartsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            }
            if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            }
            try {
                float f9 = Float.parseFloat(this.currentToken);
                nextToken();
                return f9;
            } catch (NumberFormatException e9) {
                throw floatParseException(e9);
            }
        }

        public String consumeIdentifier() throws ParseException {
            for (int i9 = 0; i9 < this.currentToken.length(); i9++) {
                char cCharAt = this.currentToken.charAt(i9);
                if (('a' > cCharAt || cCharAt > 'z') && (('A' > cCharAt || cCharAt > 'Z') && !(('0' <= cCharAt && cCharAt <= '9') || cCharAt == '_' || cCharAt == '.'))) {
                    String str = this.currentToken;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 29);
                    sb.append("Expected identifier. Found '");
                    sb.append(str);
                    sb.append("'");
                    throw parseException(sb.toString());
                }
            }
            String str2 = this.currentToken;
            nextToken();
            return str2;
        }

        public int consumeInt32() throws ParseException {
            try {
                int int32 = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return int32;
            } catch (NumberFormatException e9) {
                throw integerParseException(e9);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long int64 = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return int64;
            } catch (NumberFormatException e9) {
                throw integerParseException(e9);
            }
        }

        public String consumeString() {
            return consumeByteString().toStringUtf8();
        }

        public int consumeUInt32() throws ParseException {
            try {
                int uInt32 = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return uInt32;
            } catch (NumberFormatException e9) {
                throw integerParseException(e9);
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long uInt64 = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return uInt64;
            } catch (NumberFormatException e9) {
                throw integerParseException(e9);
            }
        }

        public boolean lookingAt(String str) {
            return this.currentToken.equals(str);
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char cCharAt = this.currentToken.charAt(0);
            return ('0' <= cCharAt && cCharAt <= '9') || cCharAt == '-' || cCharAt == '+';
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                Matcher matcher2 = this.matcher;
                matcher2.region(this.pos + 1, matcher2.regionEnd());
            }
            skipWhitespace();
        }

        public ParseException parseException(String str) {
            return new ParseException(this.line + 1, this.column + 1, str);
        }

        public ParseException parseExceptionPreviousToken(String str) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, str);
        }

        public boolean tryConsume(String str) {
            if (!this.currentToken.equals(str)) {
                return false;
            }
            nextToken();
            return true;
        }

        public boolean tryConsumeDouble() throws NumberFormatException {
            try {
                consumeDouble();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeFloat() throws NumberFormatException {
            try {
                consumeFloat();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeIdentifier() {
            try {
                consumeIdentifier();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeInt64() {
            try {
                consumeInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeString() {
            try {
                consumeString();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                consumeUInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        private Tokenizer(CharSequence charSequence) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char cCharAt = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : (char) 0;
            if (cCharAt != '\"' && cCharAt != '\'') {
                throw parseException("Expected string.");
            }
            if (this.currentToken.length() >= 2) {
                String str = this.currentToken;
                if (str.charAt(str.length() - 1) == cCharAt) {
                    try {
                        String str2 = this.currentToken;
                        ByteString byteStringUnescapeBytes = TextFormat.unescapeBytes(str2.substring(1, str2.length() - 1));
                        nextToken();
                        list.add(byteStringUnescapeBytes);
                        return;
                    } catch (InvalidEscapeSequenceException e9) {
                        throw parseException(e9.getMessage());
                    }
                }
            }
            throw parseException("String missing ending quote.");
        }
    }

    static {
        DEFAULT_PRINTER = new Printer();
        SINGLE_LINE_PRINTER = new Printer().setSingleLineMode(true);
        UNICODE_PRINTER = new Printer().setEscapeNonAscii(false);
    }

    private TextFormat() {
    }

    private static int digitValue(byte b9) {
        if (48 > b9 || b9 > 57) {
            return ((97 > b9 || b9 > 122) ? b9 - 65 : b9 - 97) + 10;
        }
        return b9 - 48;
    }

    private static String escapeBytes(ByteSequence byteSequence) {
        StringBuilder sb = new StringBuilder(byteSequence.size());
        for (int i9 = 0; i9 < byteSequence.size(); i9++) {
            byte bByteAt = byteSequence.byteAt(i9);
            if (bByteAt == 34) {
                sb.append("\\\"");
            } else if (bByteAt == 39) {
                sb.append("\\'");
            } else if (bByteAt != 92) {
                switch (bByteAt) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (bByteAt >= 32) {
                            sb.append((char) bByteAt);
                            break;
                        } else {
                            sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                            sb.append((char) (((bByteAt >>> 6) & 3) + 48));
                            sb.append((char) (((bByteAt >>> 3) & 7) + 48));
                            sb.append((char) ((bByteAt & 7) + 48));
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static Parser getParser() {
        return PARSER;
    }

    private static boolean isHex(byte b9) {
        return (48 <= b9 && b9 <= 57) || (97 <= b9 && b9 <= 102) || (65 <= b9 && b9 <= 70);
    }

    private static boolean isOctal(byte b9) {
        return 48 <= b9 && b9 <= 55;
    }

    public static void merge(Readable readable, Message.Builder builder) throws ParseException {
        PARSER.merge(readable, builder);
    }

    public static int parseInt32(String str) {
        return (int) parseInteger(str, true, false);
    }

    public static long parseInt64(String str) {
        return parseInteger(str, true, true);
    }

    private static long parseInteger(String str, boolean z8, boolean z9) throws NumberFormatException {
        int i9;
        int i10 = 0;
        if (str.startsWith("-", 0)) {
            if (!z8) {
                throw new NumberFormatException(str.length() != 0 ? "Number must be positive: ".concat(str) : new String("Number must be positive: "));
            }
            i10 = 1;
        }
        int i11 = i10;
        if (str.startsWith("0x", i10)) {
            i10 += 2;
            i9 = 16;
        } else {
            i9 = str.startsWith("0", i10) ? 8 : 10;
        }
        String strSubstring = str.substring(i10);
        if (strSubstring.length() < 16) {
            long j9 = Long.parseLong(strSubstring, i9);
            if (i11 != 0) {
                j9 = -j9;
            }
            if (z9) {
                return j9;
            }
            if (z8) {
                if (j9 > 2147483647L || j9 < -2147483648L) {
                    throw new NumberFormatException(str.length() != 0 ? "Number out of range for 32-bit signed integer: ".concat(str) : new String("Number out of range for 32-bit signed integer: "));
                }
                return j9;
            }
            if (j9 >= 4294967296L || j9 < 0) {
                throw new NumberFormatException(str.length() != 0 ? "Number out of range for 32-bit unsigned integer: ".concat(str) : new String("Number out of range for 32-bit unsigned integer: "));
            }
            return j9;
        }
        BigInteger bigInteger = new BigInteger(strSubstring, i9);
        if (i11 != 0) {
            bigInteger = bigInteger.negate();
        }
        if (z9) {
            if (z8) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException(str.length() != 0 ? "Number out of range for 64-bit signed integer: ".concat(str) : new String("Number out of range for 64-bit signed integer: "));
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException(str.length() != 0 ? "Number out of range for 64-bit unsigned integer: ".concat(str) : new String("Number out of range for 64-bit unsigned integer: "));
            }
        } else if (z8) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException(str.length() != 0 ? "Number out of range for 32-bit signed integer: ".concat(str) : new String("Number out of range for 32-bit signed integer: "));
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException(str.length() != 0 ? "Number out of range for 32-bit unsigned integer: ".concat(str) : new String("Number out of range for 32-bit unsigned integer: "));
        }
        return bigInteger.longValue();
    }

    public static int parseUInt32(String str) {
        return (int) parseInteger(str, false, false);
    }

    public static long parseUInt64(String str) {
        return parseInteger(str, false, true);
    }

    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printField(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            printField(fieldDescriptor, obj, sb);
            return sb.toString();
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printFieldValue(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static String printToString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            print(messageOrBuilder, sb);
            return sb.toString();
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(sb));
            return sb.toString();
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static void printUnicode(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void printUnknownFieldValue(int i9, Object obj, Appendable appendable) throws IOException {
        printUnknownFieldValue(i9, obj, new TextGenerator(appendable));
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.print(messageOrBuilder, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequenceException {
        int i9;
        int i10;
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        byte[] bArr = new byte[byteStringCopyFromUtf8.size()];
        int i11 = 0;
        int i12 = 0;
        while (i11 < byteStringCopyFromUtf8.size()) {
            byte bByteAt = byteStringCopyFromUtf8.byteAt(i11);
            if (bByteAt == 92) {
                i11++;
                if (i11 >= byteStringCopyFromUtf8.size()) {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
                byte bByteAt2 = byteStringCopyFromUtf8.byteAt(i11);
                if (isOctal(bByteAt2)) {
                    int iDigitValue = digitValue(bByteAt2);
                    int i13 = i11 + 1;
                    if (i13 < byteStringCopyFromUtf8.size() && isOctal(byteStringCopyFromUtf8.byteAt(i13))) {
                        iDigitValue = (iDigitValue * 8) + digitValue(byteStringCopyFromUtf8.byteAt(i13));
                        i11 = i13;
                    }
                    int i14 = i11 + 1;
                    if (i14 < byteStringCopyFromUtf8.size() && isOctal(byteStringCopyFromUtf8.byteAt(i14))) {
                        iDigitValue = (iDigitValue * 8) + digitValue(byteStringCopyFromUtf8.byteAt(i14));
                        i11 = i14;
                    }
                    i9 = i12 + 1;
                    bArr[i12] = (byte) iDigitValue;
                } else {
                    if (bByteAt2 == 34) {
                        i10 = i12 + 1;
                        bArr[i12] = 34;
                    } else if (bByteAt2 == 39) {
                        i10 = i12 + 1;
                        bArr[i12] = 39;
                    } else if (bByteAt2 == 92) {
                        i10 = i12 + 1;
                        bArr[i12] = 92;
                    } else if (bByteAt2 == 102) {
                        i10 = i12 + 1;
                        bArr[i12] = Ascii.f15382FF;
                    } else if (bByteAt2 == 110) {
                        i10 = i12 + 1;
                        bArr[i12] = 10;
                    } else if (bByteAt2 == 114) {
                        i10 = i12 + 1;
                        bArr[i12] = Ascii.f15380CR;
                    } else if (bByteAt2 == 116) {
                        i10 = i12 + 1;
                        bArr[i12] = 9;
                    } else if (bByteAt2 == 118) {
                        i10 = i12 + 1;
                        bArr[i12] = Ascii.f15393VT;
                    } else if (bByteAt2 == 120) {
                        i11++;
                        if (i11 >= byteStringCopyFromUtf8.size() || !isHex(byteStringCopyFromUtf8.byteAt(i11))) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                        }
                        int iDigitValue2 = digitValue(byteStringCopyFromUtf8.byteAt(i11));
                        int i15 = i11 + 1;
                        if (i15 < byteStringCopyFromUtf8.size() && isHex(byteStringCopyFromUtf8.byteAt(i15))) {
                            iDigitValue2 = (iDigitValue2 * 16) + digitValue(byteStringCopyFromUtf8.byteAt(i15));
                            i11 = i15;
                        }
                        bArr[i12] = (byte) iDigitValue2;
                        i10 = i12 + 1;
                    } else if (bByteAt2 == 97) {
                        i10 = i12 + 1;
                        bArr[i12] = 7;
                    } else {
                        if (bByteAt2 != 98) {
                            StringBuilder sb = new StringBuilder(29);
                            sb.append("Invalid escape sequence: '\\");
                            sb.append((char) bByteAt2);
                            sb.append("'");
                            throw new InvalidEscapeSequenceException(sb.toString());
                        }
                        i10 = i12 + 1;
                        bArr[i12] = 8;
                    }
                    i12 = i10;
                    i11++;
                }
            } else {
                i9 = i12 + 1;
                bArr[i12] = bByteAt;
            }
            i12 = i9;
            i11++;
        }
        return ByteString.copyFrom(bArr, 0, i12);
    }

    public static String unescapeText(String str) {
        return unescapeBytes(str).toStringUtf8();
    }

    public static String unsignedToString(int i9) {
        return i9 >= 0 ? Integer.toString(i9) : Long.toString(i9 & 4294967295L);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, builder);
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    public static void printUnicode(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printUnknownFieldValue(int i9, Object obj, TextGenerator textGenerator) throws IOException {
        int tagWireType = WireFormat.getTagWireType(i9);
        if (tagWireType == 0) {
            textGenerator.print(unsignedToString(((Long) obj).longValue()));
            return;
        }
        if (tagWireType == 1) {
            textGenerator.print(String.format(null, "0x%016x", (Long) obj));
            return;
        }
        if (tagWireType == 2) {
            textGenerator.print("\"");
            textGenerator.print(escapeBytes((ByteString) obj));
            textGenerator.print("\"");
        } else if (tagWireType == 3) {
            DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet) obj, textGenerator);
        } else {
            if (tagWireType == 5) {
                textGenerator.print(String.format(null, "0x%08x", (Integer) obj));
                return;
            }
            StringBuilder sb = new StringBuilder(20);
            sb.append("Bad tag: ");
            sb.append(i9);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(readable, extensionRegistry, builder);
    }

    public static String unsignedToString(long j9) {
        if (j9 >= 0) {
            return Long.toString(j9);
        }
        return BigInteger.valueOf(j9 & Long.MAX_VALUE).setBit(63).toString();
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, extensionRegistry, builder);
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            print(unknownFieldSet, sb);
            return sb.toString();
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb));
            return sb.toString();
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public static String escapeBytes(final ByteString byteString) {
        return escapeBytes(new ByteSequence() { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat.1
            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat.ByteSequence
            public byte byteAt(int i9) {
                return byteString.byteAt(i9);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat.ByteSequence
            public int size() {
                return byteString.size();
            }
        });
    }

    public static String escapeBytes(final byte[] bArr) {
        return escapeBytes(new ByteSequence() { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat.2
            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat.ByteSequence
            public byte byteAt(int i9) {
                return bArr[i9];
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.TextFormat.ByteSequence
            public int size() {
                return bArr.length;
            }
        });
    }
}
