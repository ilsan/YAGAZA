package com.spring.yagaza.web.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private String password;
}
