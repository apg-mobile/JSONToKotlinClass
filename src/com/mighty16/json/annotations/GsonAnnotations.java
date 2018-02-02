package com.mighty16.json.annotations;


public class GsonAnnotations implements AnnotationGenerator {


    @Override
    public String getSerializeName(String jsonKey) {
        return "@Expose\n@SerializedName(\"" + jsonKey + "\")";
    }
}
