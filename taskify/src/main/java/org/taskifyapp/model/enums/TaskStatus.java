package org.taskifyapp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@Getter
public enum TaskStatus {

    NEW(1), IN_PROGRESS(2), COMPLETED(3), CANCELED(4), UNKNOWN(0);

    private final Integer id;
    private static final Map<Integer, TaskStatus> idToTaskStatusMap = new HashMap<>();

    static {
        for (TaskStatus status : values()) {
            idToTaskStatusMap.put(status.id, status);
        }
    }

    public static TaskStatus fromId(Integer id) {
        return idToTaskStatusMap.getOrDefault(id, UNKNOWN);
    }
}
