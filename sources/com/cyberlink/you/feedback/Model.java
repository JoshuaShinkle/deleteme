package com.cyberlink.you.feedback;

import android.net.Uri;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class Model {

    public static class JSONMap<T> extends LinkedHashMap<String, T> {
        private static final long serialVersionUID = 4947403667374895879L;

        @Override // java.util.AbstractMap
        public String toString() {
            return Model.toJSONObject(this).toString();
        }
    }

    public static <T extends Model> T parseFromJSON(Class<T> cls, String str) throws IllegalAccessException, InstantiationException {
        T tNewInstance;
        if (cls != null) {
            try {
                tNewInstance = cls.newInstance();
            } catch (Exception unused) {
            }
        } else {
            tNewInstance = null;
        }
        if (cls == null || str == null) {
            return tNewInstance;
        }
        try {
            return (T) parseFromJSONObject(cls, new JSONObject(str));
        } catch (Exception unused2) {
            return null;
        }
    }

    public static <T> ArrayList<T> parseFromJSONArray(Class<T> cls, String str) {
        if (cls == null || str == null) {
            return new ArrayList<>();
        }
        try {
            return parseFromJSONArray(cls, new JSONArray(str));
        } catch (Exception e9) {
            e9.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static <T> JSONMap<T> parseFromJSONMap(Class<T> cls, JSONObject jSONObject) throws Exception {
        JSONMap<T> jSONMap = new JSONMap<>();
        if (jSONObject != null && cls != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys != null && itKeys.hasNext()) {
                String next = itKeys.next();
                T tCast = (T) null;
                try {
                    if (cls.equals(Long.class) || cls.equals(Long.TYPE)) {
                        tCast = cls.cast(Long.valueOf(jSONObject.getLong(next)));
                    } else if (cls.equals(Integer.class) || cls.equals(Integer.TYPE)) {
                        tCast = cls.cast(Integer.valueOf(jSONObject.getInt(next)));
                    } else if (cls.equals(Boolean.class) || cls.equals(Boolean.TYPE)) {
                        tCast = cls.cast(Boolean.valueOf(jSONObject.getBoolean(next)));
                    } else if (cls.equals(Float.class) || cls.equals(Float.TYPE) || cls.equals(Double.class) || cls.equals(Double.TYPE)) {
                        tCast = cls.cast(Double.valueOf(jSONObject.getDouble(next)));
                    } else if (cls.equals(Date.class)) {
                        tCast = cls.cast(new Date(jSONObject.getLong(next)));
                    } else if (cls.equals(String.class)) {
                        tCast = cls.cast(jSONObject.isNull(next) ? null : jSONObject.getString(next));
                    } else if (cls.equals(Uri.class)) {
                        tCast = cls.cast(jSONObject.isNull(next) ? null : Uri.parse(jSONObject.getString(next)));
                    } else {
                        if (!Model.class.isAssignableFrom(cls)) {
                            throw new Exception("Unsupported type: " + cls.getName());
                        }
                        tCast = (T) parseFromJSONObject(cls, jSONObject.getJSONObject(next));
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
                if (tCast) {
                    jSONMap.put(next, tCast);
                }
            }
        }
        return jSONMap;
    }

    public static <T extends Model> T parseFromJSONObject(Class<T> cls, JSONObject jSONObject) throws Exception {
        T tNewInstance;
        if (cls != null) {
            try {
                tNewInstance = cls.newInstance();
            } catch (Exception unused) {
            }
        } else {
            tNewInstance = null;
        }
        if (cls != null && jSONObject != null) {
            for (Field field : cls.getFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                    String name = field.getName();
                    Class<?> type = field.getType();
                    try {
                        if (type.equals(Long.class) || type.equals(Long.TYPE)) {
                            field.set(tNewInstance, Long.valueOf(jSONObject.getLong(name)));
                        } else if (type.equals(Integer.class) || type.equals(Integer.TYPE)) {
                            field.set(tNewInstance, Integer.valueOf(jSONObject.getInt(name)));
                        } else if (type.equals(Boolean.class) || type.equals(Boolean.TYPE)) {
                            field.set(tNewInstance, Boolean.valueOf(jSONObject.getBoolean(name)));
                        } else if (type.equals(Float.class) || type.equals(Float.TYPE)) {
                            field.set(tNewInstance, Float.valueOf((float) jSONObject.getDouble(name)));
                        } else if (type.equals(Double.class) || type.equals(Double.TYPE)) {
                            field.set(tNewInstance, Double.valueOf(jSONObject.getDouble(name)));
                        } else if (type.equals(Date.class)) {
                            field.set(tNewInstance, new Date(jSONObject.getLong(name)));
                        } else if (type.equals(String.class)) {
                            field.set(tNewInstance, jSONObject.isNull(name) ? null : jSONObject.getString(name));
                        } else if (type.equals(Uri.class)) {
                            field.set(tNewInstance, jSONObject.isNull(name) ? null : Uri.parse(jSONObject.getString(name)));
                        } else if (Model.class.isAssignableFrom(type)) {
                            field.set(tNewInstance, parseFromJSONObject(type, jSONObject.getJSONObject(name)));
                        } else if (type.equals(ArrayList.class)) {
                            field.set(tNewInstance, parseFromJSONArray((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0], jSONObject.getJSONArray(name)));
                        } else {
                            if (!type.equals(JSONMap.class)) {
                                throw new Exception("Unsupported type: " + type.getName());
                            }
                            field.set(tNewInstance, parseFromJSONMap((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0], jSONObject.getJSONObject(name)));
                        }
                    } catch (Exception unused2) {
                        continue;
                    }
                }
            }
        }
        return tNewInstance;
    }

    public static <T> JSONArray toJSONArray(ArrayList<T> arrayList) throws Exception {
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null && !arrayList.isEmpty()) {
            Class<?> cls = arrayList.get(0).getClass();
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                try {
                    if (cls.equals(Long.class) || cls.equals(Long.TYPE)) {
                        jSONArray.put(next);
                    } else if (cls.equals(Integer.class) || cls.equals(Integer.TYPE)) {
                        jSONArray.put(next);
                    } else if (cls.equals(Boolean.class) || cls.equals(Boolean.TYPE)) {
                        jSONArray.put(next);
                    } else if (cls.equals(Float.class) || cls.equals(Float.TYPE)) {
                        jSONArray.put(next);
                    } else if (cls.equals(Double.class) || cls.equals(Double.TYPE)) {
                        jSONArray.put(next);
                    } else if (cls.equals(Date.class)) {
                        jSONArray.put(((Date) Date.class.cast(next)).getTime());
                    } else if (cls.equals(String.class)) {
                        jSONArray.put(next);
                    } else if (Uri.class.isAssignableFrom(cls)) {
                        jSONArray.put(next);
                    } else {
                        if (!Model.class.isAssignableFrom(cls)) {
                            jSONArray.put(next);
                            throw new Exception("Unsupported type: " + cls.getName());
                        }
                        jSONArray.put(toJSONObject((Model) next));
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public static <T> JSONObject toJSONObject(Model model) throws Exception {
        JSONObject jSONObject = new JSONObject();
        if (model == null) {
            return jSONObject;
        }
        for (Field field : model.getClass().getFields()) {
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                String name = field.getName();
                Class<?> type = field.getType();
                try {
                    if (field.get(model) != null) {
                        if (type.equals(Long.class) || type.equals(Long.TYPE)) {
                            jSONObject.put(name, field.get(model));
                        } else if (type.equals(Integer.class) || type.equals(Integer.TYPE)) {
                            jSONObject.put(name, field.get(model));
                        } else if (type.equals(Boolean.class) || type.equals(Boolean.TYPE)) {
                            jSONObject.put(name, field.get(model));
                        } else if (type.equals(Float.class) || type.equals(Float.TYPE)) {
                            jSONObject.put(name, field.get(model));
                        } else if (type.equals(Double.class) || type.equals(Double.TYPE)) {
                            jSONObject.put(name, field.get(model));
                        } else if (type.equals(Date.class)) {
                            jSONObject.put(name, ((Date) Date.class.cast(field.get(model))).getTime());
                        } else if (type.equals(String.class)) {
                            jSONObject.put(name, String.class.cast(field.get(model)));
                        } else if (type.equals(Uri.class)) {
                            jSONObject.put(name, ((Uri) Uri.class.cast(field.get(model))).toString());
                        } else if (Model.class.isAssignableFrom(type)) {
                            jSONObject.put(name, toJSONObject((Model) field.get(model)));
                        } else if (type.equals(ArrayList.class)) {
                            jSONObject.put(name, toJSONArray((ArrayList) field.get(model)));
                        } else {
                            if (!type.equals(JSONMap.class)) {
                                throw new Exception("Unsupported type: " + type.getName());
                            }
                            jSONObject.put(name, toJSONObject((JSONMap) field.get(model)));
                        }
                    }
                } catch (Exception unused) {
                    continue;
                }
            }
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject(this).toString();
    }

    public static <T> ArrayList<T> parseFromJSONArray(Class<T> cls, JSONArray jSONArray) throws Exception {
        ArrayList<T> arrayList = new ArrayList<>();
        if (jSONArray != null && cls != null) {
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                try {
                    if (!cls.equals(Long.class) && !cls.equals(Long.TYPE)) {
                        if (!cls.equals(Integer.class) && !cls.equals(Integer.TYPE)) {
                            if (!cls.equals(Boolean.class) && !cls.equals(Boolean.TYPE)) {
                                if (!cls.equals(Float.class) && !cls.equals(Float.TYPE)) {
                                    if (!cls.equals(Double.class) && !cls.equals(Double.TYPE)) {
                                        if (cls.equals(Date.class)) {
                                            arrayList.add(cls.cast(Long.valueOf(jSONArray.getLong(i9))));
                                        } else {
                                            T tCast = null;
                                            if (cls.equals(String.class)) {
                                                if (!jSONArray.isNull(i9)) {
                                                    tCast = cls.cast(jSONArray.getString(i9));
                                                }
                                                arrayList.add(tCast);
                                            } else if (cls.equals(Uri.class)) {
                                                if (!jSONArray.isNull(i9)) {
                                                    tCast = cls.cast(Uri.parse(jSONArray.getString(i9)));
                                                }
                                                arrayList.add(tCast);
                                            } else if (Model.class.isAssignableFrom(cls)) {
                                                arrayList.add(parseFromJSONObject(cls, jSONArray.getJSONObject(i9)));
                                            } else {
                                                throw new Exception("Unsupported type: " + cls.getName());
                                            }
                                        }
                                    } else {
                                        arrayList.add(cls.cast(Double.valueOf(jSONArray.getDouble(i9))));
                                    }
                                } else {
                                    arrayList.add(cls.cast(Double.valueOf(jSONArray.getDouble(i9))));
                                }
                            } else {
                                arrayList.add(cls.cast(Boolean.valueOf(jSONArray.getBoolean(i9))));
                            }
                        } else {
                            arrayList.add(cls.cast(Integer.valueOf(jSONArray.getInt(i9))));
                        }
                    } else {
                        arrayList.add(cls.cast(Long.valueOf(jSONArray.getLong(i9))));
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static <T> JSONObject toJSONObject(JSONMap<T> jSONMap) throws Exception {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, T> entry : jSONMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                Class<?> cls = value.getClass();
                try {
                    if (!cls.equals(Long.class) && !cls.equals(Long.TYPE)) {
                        if (!cls.equals(Integer.class) && !cls.equals(Integer.TYPE)) {
                            if (!cls.equals(Boolean.class) && !cls.equals(Boolean.TYPE)) {
                                if (!cls.equals(Float.class) && !cls.equals(Float.TYPE)) {
                                    if (!cls.equals(Double.class) && !cls.equals(Double.TYPE)) {
                                        if (cls.equals(Date.class)) {
                                            jSONObject.put(key, ((Date) Date.class.cast(value)).getTime());
                                        } else if (cls.equals(String.class)) {
                                            jSONObject.put(key, value);
                                        } else if (Uri.class.isAssignableFrom(cls)) {
                                            jSONObject.put(key, value);
                                        } else if (Model.class.isAssignableFrom(cls)) {
                                            jSONObject.put(key, toJSONObject((Model) value));
                                        } else {
                                            throw new Exception("Unsupported type: " + cls.getName());
                                        }
                                    } else {
                                        jSONObject.put(key, value);
                                    }
                                } else {
                                    jSONObject.put(key, value);
                                }
                            } else {
                                jSONObject.put(key, value);
                            }
                        } else {
                            jSONObject.put(key, value);
                        }
                    } else {
                        jSONObject.put(key, value);
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
        return jSONObject;
    }
}
