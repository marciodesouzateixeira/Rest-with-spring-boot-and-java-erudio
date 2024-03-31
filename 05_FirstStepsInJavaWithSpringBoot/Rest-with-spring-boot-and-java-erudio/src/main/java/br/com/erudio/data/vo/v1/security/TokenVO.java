package br.com.erudio.data.vo.v1.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Boolean authenticated;
	private Date created;
	private Date expiration;
	private String AccessToken;
	private String RefreshToken;
	
	public TokenVO(
			String username, 
			Boolean authenticated, 
			Date created, 
			Date expiration,
			String accessToken, 
			String refreshToken) {
		this.username = username;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		AccessToken = accessToken;
		RefreshToken = refreshToken;
	}

	public TokenVO() {
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

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getAccessToken() {
		return AccessToken;
	}

	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}

	public String getRefreshToken() {
		return RefreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(AccessToken, RefreshToken, authenticated, created, expiration, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenVO other = (TokenVO) obj;
		return Objects.equals(
				AccessToken, other.AccessToken) && 
				Objects.equals(RefreshToken, other.RefreshToken) && 
				Objects.equals(authenticated, other.authenticated) && 
				Objects.equals(created, other.created) && 
				Objects.equals(expiration, other.expiration) && 
				Objects.equals(password, other.password) && 
				Objects.equals(username, other.username);
	}
	
}
