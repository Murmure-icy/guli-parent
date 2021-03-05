package com.echo.baseservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/03 20:28
 * @Description: com.echo.baseservice.exception
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    private Integer code;
    private String message;
}
