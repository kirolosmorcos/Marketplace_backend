package com.example.Market_place.BLL_Layer.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecificationDTO {
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String label;
    private String value;
}
