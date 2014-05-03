package captcha.controllers;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class LoginForm {

    @NotBlank
	@Email
	private String email;

    @NotBlank
	private String password;

    @Valid
    private CaptchaForm captchaForm;

    public LoginForm(CaptchaForm captchaForm) {
    	this.captchaForm = captchaForm;
	}
    
    public LoginForm() {
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public CaptchaForm getCaptchaForm() {
		return captchaForm;
	}
	
	public void setCaptchaForm(CaptchaForm captchaForm) {
		this.captchaForm = captchaForm;
	}
	
}
