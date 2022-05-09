package org.blc.security.dto;

import java.util.List;

public class JwtResponse {
	private String token;
	private Long type;
	private String username;
	private String password;
	private List<String> roles;

	public JwtResponse(String token, Long type, String username, String password, List<String> roles) {
		super();
		this.token = token;
		this.type = type;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
