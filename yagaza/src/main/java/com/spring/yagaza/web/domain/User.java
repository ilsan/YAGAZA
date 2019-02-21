package com.spring.yagaza.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity @Table(name = "user")
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "이름을 입력해 주세요.")
	@Size(min = 2, max = 10, message = "2자 이상 입력해 주세요.")
	@Column(nullable = false)
	private String name;

	@Email(message = "이메일 형식이 아닙니다.")
	@Column(nullable = false)
	private String email;

	@NotEmpty(message = "아이디를 입력해 주세요.")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{3,20}$", message = "영문 숫자 포함 3글자 이상 20자 미만 입력해 주세요.")
	@Column(nullable = false)
	private String userId;

	@NotEmpty(message = "비밀번호를 입력해 주세요.")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,}$", message = "영문,숫자,특문 포함 6글자 이상 입력해 주세요.")
	@Column(nullable = false)
	private String password;

	@Transient
	@JsonIgnore
	private String passwordCheck;

	@Column(nullable = false)
	private LocalDateTime regDate;
}
