package com.ssafy.enjoytrip.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ModifyPwdRequest {

    @NotBlank(message = "비밀번호는 필수 입력사항입니다.")
    private String curPwd;

    @NotBlank(message = "비밀번호는 필수 입력사항입니다.")
    @Pattern(regexp = "^[0-9a-z].{6,10}$", message = "영문 소문자, 숫자 6~10자 이내로 입력하세요.”")
    private String newPwd;

}
