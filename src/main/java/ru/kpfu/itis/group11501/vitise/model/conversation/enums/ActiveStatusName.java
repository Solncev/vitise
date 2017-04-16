package ru.kpfu.itis.group11501.vitise.model.conversation.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Svintenok Kate
 * Date: 03.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public enum ActiveStatusName {
    ACTIVE(0), DELETED(1), LEFT_OUT(2);

    private static final Map<Integer, ActiveStatusName> lookup
            = new HashMap<Integer, ActiveStatusName>();

    static {
        for (ActiveStatusName statusName : EnumSet.allOf(ActiveStatusName.class))
            lookup.put(statusName.getCode(), statusName);
    }

    private final int code;

    private ActiveStatusName(int code) {
        this.code = code;
    }

    public static ActiveStatusName get(int code) {
        return lookup.get(code);
    }

    public int getCode() {
        return code;
    }
}
