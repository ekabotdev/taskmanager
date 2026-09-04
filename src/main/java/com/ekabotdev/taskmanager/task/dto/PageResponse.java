package com.ekabotdev.taskmanager.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.List;


@Getter
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private long totalPages;
    private boolean first;
    private boolean last;
}
