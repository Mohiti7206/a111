package com.idsargus.akpmsadminservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "user_logs")
@Getter
@Setter
public class UserLogsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.IDENTITY, GenerationType.SEQUENCE, or
														// GenerationType.TABLE
	private Integer id;

	@Column(name="created_on")
	private LocalDateTime createdOn = LocalDateTime.now();

	@Column(name = "logged_in_time")
	private String loginTiming;

	@Column(name = "logged_out_time")
	private String logoutTiming;

	@Column(name = "user_email")
	private String email;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "username")
	private String username;

	@Column(name = "department")
	private String department;

	@Column(name = "session_timeout")
	private String sessionTimeout;

	@Column(name = "request_current_date_time")
	private String requestCurrentDateTime;

	@Column(name = "request_api")
	private String requestAPI;

	@Column(name = "response_time")
	private String responseTime;

}
