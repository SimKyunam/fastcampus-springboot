package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req<T>{

    private Header header;
    private T httpBody;

    @Data
    public static class Header{
        private String responseCode;
    }

}
