package com.perfectcorp.model;

import com.perfectcorp.utility.C4507b;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes2.dex */
public abstract class ModelX {

    public static class Attribute {
        public String value;

        public Attribute(String str) {
            this.value = str;
        }

        public double doubleValue(double d9) {
            String str = this.value;
            if (str == null) {
                return d9;
            }
            try {
                return Double.parseDouble(str);
            } catch (Exception unused) {
                return d9;
            }
        }

        public float floatValue(float f9) {
            return (float) doubleValue(f9);
        }

        public int intValue(int i9) {
            return (int) longValue(i9);
        }

        public long longValue(long j9) {
            String str = this.value;
            if (str == null) {
                return j9;
            }
            try {
                return Long.parseLong(str);
            } catch (Exception unused) {
                return j9;
            }
        }

        public String toString() {
            return this.value;
        }
    }

    public static class ValueElement extends ModelX {
        public String value;
    }

    public static <T> void appendXmlElements(ArrayList<T> arrayList, Element element) throws DOMException {
        Document ownerDocument;
        if (arrayList == null || arrayList.isEmpty() || element == null || !ModelX.class.isAssignableFrom(arrayList.get(0).getClass()) || (ownerDocument = element.getOwnerDocument()) == null) {
            return;
        }
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            element.appendChild(toXmlElement((ModelX) it.next(), ownerDocument));
        }
    }

    public static <T> ArrayList<T> parseArrayFromXml(Class<T> cls, String str) {
        if (cls == null || str == null) {
            return new ArrayList<>();
        }
        try {
            return parseFromXmlNodeList(cls, DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes(Charset.defaultCharset()))).getElementsByTagName(getTagName(cls)));
        } catch (Exception e9) {
            if (C4507b.f15929a) {
                C4507b.m18110k(e9, "; (", str, ")");
            }
            return new ArrayList<>();
        }
    }

    public static <T> T parseFromXml(Class<T> cls, String str) {
        if (cls != null && str != null) {
            try {
                return (T) parseFromXmlElement(cls, (Element) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes(Charset.defaultCharset()))).getElementsByTagName(getTagName(cls)).item(0));
            } catch (Exception e9) {
                if (C4507b.f15929a) {
                    C4507b.m18110k(e9, "; (", str, ")");
                }
                return null;
            }
        }
        if (cls == null) {
            return null;
        }
        try {
            return cls.newInstance();
        } catch (Exception e10) {
            C4507b.m18102c(e10);
            return null;
        }
    }

    public static <T> T parseFromXmlElement(Class<T> cls, Element element) throws Exception {
        if (element != null && cls != null) {
            try {
                T tNewInstance = cls.newInstance();
                for (Field field : cls.getFields()) {
                    int modifiers = field.getModifiers();
                    if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                        String name = field.getName();
                        Class<?> type = field.getType();
                        try {
                            if (Attribute.class.isAssignableFrom(type)) {
                                field.set(tNewInstance, type.getConstructor(String.class).newInstance(element.getAttribute(name)));
                            } else if (type.equals(String.class)) {
                                field.set(tNewInstance, element.getTextContent());
                            } else if (ModelX.class.isAssignableFrom(type)) {
                                field.set(tNewInstance, parseFromXmlElement(type, (Element) element.getElementsByTagName(name).item(0)));
                            } else {
                                if (!type.equals(ArrayList.class)) {
                                    throw new Exception("Unsupported type: " + type.getName());
                                }
                                Class cls2 = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                                field.set(tNewInstance, parseFromXmlNodeList(cls2, element.getElementsByTagName(getTagName(cls2))));
                            }
                        } catch (Exception unused) {
                            if (C4507b.f15929a) {
                                C4507b.m18110k("Parse fail: fieldType: ", type, "; fieldName:", name);
                            }
                        }
                    }
                }
                return tNewInstance;
            } catch (Exception e9) {
                C4507b.m18102c(e9);
            }
        }
        return null;
    }

    public static <T> ArrayList<T> parseFromXmlNodeList(Class<T> cls, NodeList nodeList) {
        Elements elements = (ArrayList<T>) new ArrayList();
        if (nodeList != null && cls != null && ModelX.class.isAssignableFrom(cls)) {
            for (int i9 = 0; i9 < nodeList.getLength(); i9++) {
                Node nodeItem = nodeList.item(i9);
                if (nodeItem != null && nodeItem.getNodeType() == 1) {
                    Element element = (Element) nodeItem;
                    if (element.getTagName().equalsIgnoreCase(getTagName(cls))) {
                        try {
                            elements.add(parseFromXmlElement(cls, element));
                        } catch (Exception e9) {
                            if (C4507b.f15929a) {
                                C4507b.m18110k(e9, "; ", Integer.valueOf(i9));
                            }
                        }
                    }
                }
            }
        }
        return elements;
    }

    public static String toXml(ModelX modelX) throws TransformerException, DOMException {
        try {
            Document documentNewDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            documentNewDocument.appendChild(toXmlElement(modelX, documentNewDocument));
            DOMSource dOMSource = new DOMSource(documentNewDocument);
            StringWriter stringWriter = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(dOMSource, new StreamResult(stringWriter));
            return stringWriter.toString().replaceAll("&amp;#x", "&#x");
        } catch (Exception e9) {
            if (!C4507b.f15929a) {
                return null;
            }
            C4507b.m18110k(e9, "; ", modelX.getClass());
            return null;
        }
    }

    public static Element toXmlElement(ModelX modelX, Document document) {
        return toXmlElement(modelX, getTagName(modelX.getClass()), document);
    }

    public String getTagName() {
        return getClass().getSimpleName().toLowerCase(Locale.US);
    }

    public String toString() {
        return toXml(this);
    }

    private static String getTagName(Class<?> cls) {
        if (ModelX.class.isAssignableFrom(cls)) {
            try {
                return ((ModelX) cls.newInstance()).getTagName();
            } catch (Throwable unused) {
            }
        }
        return cls.getSimpleName().toLowerCase(Locale.US);
    }

    public static Element toXmlElement(ModelX modelX, String str, Document document) throws Exception {
        if (document == null || modelX == null) {
            return null;
        }
        Class<?> cls = modelX.getClass();
        Element elementCreateElement = document.createElement(str);
        Field[] fields = cls.getFields();
        if (fields == null) {
            return elementCreateElement;
        }
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                String name = field.getName();
                Class<?> type = field.getType();
                try {
                    Object obj = field.get(modelX);
                    if (obj != null) {
                        if (Attribute.class.isAssignableFrom(type)) {
                            elementCreateElement.setAttribute(name, obj.toString());
                        } else if (type.equals(String.class)) {
                            elementCreateElement.setTextContent(((String) obj).replaceAll(StringUtils.f19107CR, "&#x" + Integer.toHexString(13) + ";").replaceAll("\n", "&#x" + Integer.toHexString(10) + ";"));
                        } else if (ModelX.class.isAssignableFrom(type)) {
                            elementCreateElement.appendChild(toXmlElement((ModelX) obj, name, document));
                        } else {
                            if (!type.equals(ArrayList.class)) {
                                throw new Exception("Unsupported type: " + type.getName());
                            }
                            appendXmlElements((ArrayList) field.get(modelX), elementCreateElement);
                        }
                    }
                } catch (Exception e9) {
                    if (C4507b.f15929a) {
                        C4507b.m18110k(e9, "; ", cls, "; ", type, StringUtils.SPACE, name);
                    }
                }
            }
        }
        return elementCreateElement;
    }
}
