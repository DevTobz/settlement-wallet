package com._jasettlement.demo.entity.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private ZonedDateTime timeStamp;
    private Boolean isSuccessful;
    private Object data;
    private int status;
    private String message;


    public ApiResponse(
            ZonedDateTime timeStamp,
             Boolean isSuccessful,
            int status,
            String message
    ){
        this.timeStamp = timeStamp;
        this.isSuccessful = isSuccessful;
        this.status = status;
        this.message = message;
    }
}
