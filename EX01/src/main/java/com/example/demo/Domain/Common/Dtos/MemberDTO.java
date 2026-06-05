package com.example.demo.Domain.Common.Dtos;

// TODO: Lombok( @Data @NoArgsConstructor @AllArgsConstructor @Builder ) 과
//       검증( jakarta.validation.constraints.NotBlank, Email ) import 를 추가하라.

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// TODO: 아래 클래스에 Lombok 애너테이션을 붙여 getter/setter/builder 가 생성되도록 하라.
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class MemberDTO {

    // TODO: 각 필드에 알맞은 검증 애너테이션을 추가하라.
    private Long id;                  // 식별자 (검증 없음, DB AUTO_INCREMENT)
    //@NotBlank(message = "이름은 필수입니다.")
    private String name;              // TODO: @NotBlank
    //@NotBlank(message = "이메일은 필수입니다.")
    //@Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;             // TODO: @NotBlank + @Email
    //@NotBlank(message = "전화번호 입력은 필수입니다.")
    private String phone;             // TODO: @NotBlank
    private LocalDateTime createAt;   // 등록일시 (검증 없음)

}
