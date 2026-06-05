package com.example.demo.Controller.GlobalException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

// TODO: 전역 예외 핸들러 3개를 작성하라.
//  필요한 import : org.springframework.ui.Model,
//                 org.springframework.web.bind.annotation.ExceptionHandler, java.io.FileNotFoundException
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO 1) @ExceptionHandler(FileNotFoundException.class) -> model 에 담고 "global/error1" 반환
    // FileNotFoundException: 미존재 파일 예외 처리
    // 시스템에서 파일을 찾을 수 없을 때 발생하는 예외를 처리한다.
    @ExceptionHandler(FileNotFoundException.class)
    public String exceptionHandler_1(Exception e, Model model){
        log.error("[Global] ExceptionTestController ex1 : " + e);   // 에러로그 출력
        model.addAttribute("e",e);  // 에러로그를 객체로 view에 전달
        return "global/error1"; //  에러 페이지로 이동
    }

    // TODO 2) @ExceptionHandler(ArithmeticException.class)   -> "global/error2" 반환
    // ArithmeticException: 산술 연산 예외 처리
    // 수학적 계산 오류가 생겼을 때 발생하는 예외를 처리한다.
    @ExceptionHandler(ArithmeticException.class)
    public String exceptionHandler_2(Exception e, Model model){
        log.error("[Global] ExceptionTestController ex2 : " + e);   // 에러로그 출력
        model.addAttribute("e",e);  // 에러로그를 객체로 view에 전달
        return "global/error2"; //  에러 페이지로 이동
    }

    // TODO 3) @ExceptionHandler(Exception.class)             -> "global/error3" 반환
    // Exception: 기타 모든 예외 처리
    // 위에 명시되지 않은 나머지 모든 예외를 처리한다.
    @ExceptionHandler(Exception.class)
    public String exceptionHandler_All(Exception e, Model model){
        log.error("[Global] etc exceptions : " + e);   // 에러로그 출력
        model.addAttribute("e",e);  // 에러로그를 객체로 view에 전달
        return "global/error3"; //  에러 페이지로 이동
    }
}
