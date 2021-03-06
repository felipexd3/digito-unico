package com.fb.onedigit.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Getter
public class CacheUtil {
    Map<String, Integer> cache = new LinkedHashMap<>(10, .100F, true) {
        public boolean removeEldestEntry(Map.Entry eldest) {
            return size() > 10;
        }
    };
}
