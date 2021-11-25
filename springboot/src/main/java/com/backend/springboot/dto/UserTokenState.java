package com.backend.springboot.dto;

//dto koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu

public class UserTokenState {
	private String accessToken;
    private Long expiresIn;

    private Integer id;
    private String username;
    private String role;
    private Boolean firstLogin;
    
    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
    }
    
    public UserTokenState(String accessToken, long expiresIn, Integer id, String username, String role) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.id = id;
        this.username = username;
        this.role = role;
        //this.firstLogin = firstLogin;
    }
    
    /*
    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }*/

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
    
}
