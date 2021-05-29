package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4736646656828092302L;
	
	@JsonProperty("user_id")
	int user_id;
	String username;
	String login_hash;
	String full_name;
	String thumb_url;
	String photo_url;
	String email;
	String sms_no;
	String phone_no;
	
	
	int created_at;
	int updated_at;
	int is_deleted;

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
	public String getPhone_no() {
		return phone_no;
	}
	
	public void setSms_no(String sms_no) {
		this.sms_no = sms_no;
	}
	
	public String getSms_no() {
		return sms_no;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	public String getPhoto_url() {
		return photo_url;
	}
	
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	
	public String getThumb_url() {
		return thumb_url;
	}
	
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
	public String getFull_name() {
		return full_name;
	}

	
	public void setLogin_hash(String login_hash) {
		this.login_hash = login_hash;
	}
	
	public String getLogin_hash() {
		return login_hash;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	
	public int getCreated_at() {
		return created_at;
	}
	
	
	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}
	
	public int getUpdated_at() {
		return updated_at;
	}
	
	
	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	public int getIs_deleted() {
		return is_deleted;
	}
}
