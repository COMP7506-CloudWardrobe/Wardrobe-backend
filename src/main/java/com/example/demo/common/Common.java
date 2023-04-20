package com.example.demo.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Common {
    String JPG = ".jpg";

    String USER = "user";

    String CLOTHES_DIR = "clothes";

    String SUIT_DIR = "suit";

    List<String> CLOTHES_TYPES = Arrays.asList("tops", "bottoms", "one_pieces", "shoes", "accessories");
    int CLOTHES_TYPE = CLOTHES_TYPES.size();
}
