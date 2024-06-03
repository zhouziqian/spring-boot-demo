package com.zhouqi.demoauthsecurity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhouqi9
 * date 2024/6/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private List<T> rows;
    private Long total;

    public static <T> PageResult<T> of(List<T> rows, Long total) {
        return new PageResult<>(rows, total);
    }
}
