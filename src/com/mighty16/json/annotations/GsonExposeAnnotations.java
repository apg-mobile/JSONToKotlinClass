package com.mighty16.json.annotations;


public class GsonExposeAnnotations implements AnnotationGenerator {

    @Override
    public String getSerializeName(String jsonKey) {
        return "@SerializedName(\"" + jsonKey + "\")";
    }

    public String getExposeName() {
        return "@Expose";
    }
}
