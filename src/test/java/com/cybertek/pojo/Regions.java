package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
 @JsonIgnoreProperties(ignoreUnknown = true)

public class Regions {

    private List<Region> items;
    private int count;
}
