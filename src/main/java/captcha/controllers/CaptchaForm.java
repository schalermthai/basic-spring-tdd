package captcha.controllers;

import org.hibernate.validator.constraints.NotEmpty;

public class CaptchaForm {

	@NotEmpty
	private String id;
	
	@NotEmpty
	private String answer;
	
    public CaptchaForm(String id) {
    	this.id = id;
    }
    
    public CaptchaForm() {
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getAnswer() {
		return answer;
	}
	
}
