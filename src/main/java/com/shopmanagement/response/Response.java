package com.shopmanagement.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response<T> {

    private Integer status;

    private String message;

    private T result;
}
