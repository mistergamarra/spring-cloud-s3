package com.arnoldgm.s3.util;

import org.springframework.stereotype.Component;

@Component
public class NameGenerator {

    private int sequence = 0;

    public String getName() {
        sequence++;
        return "image_000".concat(String.valueOf(sequence)).concat(".jpg");
    }
}
