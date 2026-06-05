package com.example.demo.Controller;

import com.example.demo.Domain.Common.Daos.MemberDAO;
import com.example.demo.Domain.Common.Dtos.MemberDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberDAO memberDAO;

    // TODO: DAO 등에서 발생한 예외를 처리할 @ExceptionHandler 메서드를 작성하라.
    //  - (Exception e, Model model) 를 받아 e 메시지를 model 에 담고 "member/error" 뷰 반환
    //  - import : org.springframework.web.bind.annotation.ExceptionHandler
    @ExceptionHandler
    public String SQLExceptionHandler(Exception e, Model model){
        log.error("MEMO SQLEXCEPTION.." + e); // 예외 내용 로그 출력
        model.addAttribute("ex",e.getMessage()); // 예외 메시지를 모델에 담아 뷰로 전달
        return "member/error"; // 에러 뷰로 이동
    }

    @GetMapping("/add")
    public void memberAdd() {
        log.info("GET /member/add...");
    }

    @PostMapping("/add")
    public String memberAddPost(@Valid MemberDTO memberDTO, BindingResult bindingResult,
                                Model model, RedirectAttributes redirectAttributes) throws SQLException {
        log.info("POST /member/add..." + memberDTO);

        // TODO:
        //  1) bindingResult.hasErrors() 이면 FieldError 들을 돌며
        //     model.addAttribute(error.getField(), error.getDefaultMessage()) 후 "member/add" 반환
        //     (import org.springframework.validation.FieldError)
        //  2) 검증 통과 시 memberDAO.insert(memberDTO) 호출
        //  3) redirectAttributes.addFlashAttribute("message","회원등록 성공!") 후 "redirect:/member/list" 반환
        if (bindingResult.hasErrors()) { // 유효성 검증 오류 확인
            for (FieldError error : bindingResult.getFieldErrors()) { // 오류 필드 순회
                log.info("Error Field : " + error.getField() + " Error Message : " + error.getDefaultMessage()); // 오류 로그 출력
                model.addAttribute(error.getField(), error.getDefaultMessage()); // 오류 메시지 모델에 담기
            }
            return "member/add"; // 유효성 오류 시 입력 폼으로 반환
        }
        int result = memberDAO.insert(memberDTO); // 회원 DB 등록
        if(result>0)
            redirectAttributes.addFlashAttribute("message","회원등록 성공!"); // 성공 메시지 저장
        return "redirect:/member/list"; // 회원 목록으로 리다이렉트
    }

    @GetMapping("/list")
    public void list_get(Model model) throws SQLException {
        log.info("GET /member/list...");
        model.addAttribute("list", memberDAO.selectAll());
    }

}
