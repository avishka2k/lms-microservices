package com.lms.course.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEvent {
    private Long courseId;
    private Long departmentId;
    private String eventType;
}
