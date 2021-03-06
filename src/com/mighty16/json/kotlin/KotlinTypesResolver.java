package com.mighty16.json.kotlin;

import com.mighty16.json.TypesResolver;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

public class KotlinTypesResolver extends TypesResolver {

    private static final String INT = "Int";
    private static final String LONG = "Long";
    private static final String STRING = "String";
    private static final String DOUBLE = "Double";
    private static final String BOOLEAN = "Boolean";


    private HashMap<String, String> types;
    private HashMap<String, Object> defaultvalues;

    public KotlinTypesResolver() {
        types = new HashMap<>();
        types.put(TYPE_INTEGER, INT);
        types.put(TYPE_LONG, LONG);
        types.put(TYPE_STRING, STRING);
        types.put(TYPE_DOUBLE, DOUBLE);
        types.put(TYPE_BOOLEAN, BOOLEAN);

        defaultvalues = new HashMap<>();

        defaultvalues.put(INT, 0);
        defaultvalues.put(LONG, 0);
        defaultvalues.put(STRING, "\"\"");
        defaultvalues.put(DOUBLE, 0.0);
        defaultvalues.put(BOOLEAN, false);
    }

    @Override
    public String resolve(String javaType) {
        String type = types.get(javaType);
        if (type == null) {
            return StringUtils.capitalize(javaType) + "?";
        }
        return type;
    }

    @Override
    public String getClassName(String jsonKey) {
        String result = toCamelCase(jsonKey);
        return StringUtils.capitalize(result);
    }

    @Override
    public String getFieldName(String jsonName) {
        String result = toCamelCase(jsonName);
        return result;
    }

    @Override
    public String getDefaultValue(String type) {
        if (type.endsWith("?")) {
            return "null";
        }
        return String.valueOf(defaultvalues.get(type));
    }

    @Override
    public String getArrayType(String type) {
        String result = toCamelCase(type);
        return "List<" + result + ">?";
    }

    @Override
    public boolean canChangeType(String type) {
        return !type.endsWith("?");
    }

    private String toCamelCase(String name) {
        String result;
        int nonCharPos = getNoCharPosition(name);
        if (nonCharPos != -1) {
            result = name.substring(0, nonCharPos) + StringUtils.capitalize(name.substring(nonCharPos + 1));
            return toCamelCase(result);
        } else {
            result = name;
        }
        return result;
    }


}
