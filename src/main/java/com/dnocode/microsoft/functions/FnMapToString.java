package com.dnocode.microsoft.functions;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by dino on 24/02/16.
 */
public class FnMapToString implements Function<Map<String,String>,String> {
    @Override
    public String apply(Map<String, String> stringStringMap) {

        return stringStringMap.entrySet().stream().map(e->e.getKey()+"="+e.getValue()).reduce("",(x,y)->x+";"+y);
    }
}