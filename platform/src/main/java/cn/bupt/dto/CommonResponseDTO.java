package cn.bupt.dto;

import lombok.Data;

@Data
public class CommonResponseDTO<T> {
    String msg;

    Integer code;

    T data;
}
