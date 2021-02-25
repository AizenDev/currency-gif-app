package com.example.converter;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "meta"
})
public class GifBaseDTO {
    @JsonProperty
    private GifDTO data;

    @JsonProperty
    private Map<String, String> meta;


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public GifDTO getData() {
        return data;
    }

    public void setData(GifDTO data) {
        this.data = data;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

