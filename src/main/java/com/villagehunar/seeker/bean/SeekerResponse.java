package com.villagehunar.seeker.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.villagehunar.seeker.constant.ResponseConstant;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerResponse<T> implements Serializable {

    private int status;
    private String message;
    private T data;

    public static <T> ResponseEntity<SeekerResponse<T>> success(T data, String message) {
        SeekerResponse<T> response = new SeekerResponse<T>(ResponseConstant.STATUS.SUCCESS, message, data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<SeekerResponse<T>> created(T data, String message) {
        SeekerResponse<T> response = new SeekerResponse<T>(ResponseConstant.STATUS.CREATED, message, data);
        return new ResponseEntity<SeekerResponse<T>>(response, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<SeekerResponse<T>> error(String message, HttpStatus status) {
        SeekerResponse<T> response = new SeekerResponse<T>(ResponseConstant.STATUS.ERROR, message, null);
        return new ResponseEntity<SeekerResponse<T>>(response, status);
    }
}