package com.homeacc.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LinkUtils {
    private static final String SLASH = "/";

    public String link(List<String> args) {
        StringBuilder sb = new StringBuilder();
        args.stream()
            .map(a -> StringUtils.join(SLASH, a))
            .forEach(sb::append);
        return sb.toString();
    }

}
