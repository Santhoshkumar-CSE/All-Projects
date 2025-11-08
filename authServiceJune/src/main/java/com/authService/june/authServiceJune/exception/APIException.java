package com.authService.june.authServiceJune.exception;

import com.authService.june.authServiceJune.DTO.APIResponse;

public class APIException extends  RuntimeException{

   public APIException(){

   }

   public APIException(String message){
       super(message);
   }


}
