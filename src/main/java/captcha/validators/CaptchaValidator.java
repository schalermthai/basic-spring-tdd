package captcha.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import captcha.domain.Captcha;
import captcha.domain.CaptchaFactory;
import captcha.models.CaptchaForm;

@Component
public class CaptchaValidator implements Validator {
	
	CaptchaFactory captchaFactory;
	
	@Autowired
	public CaptchaValidator(CaptchaFactory captchaFactory) {
		this.captchaFactory = captchaFactory;
	}

	public boolean supports(Class<?> clazz) {
		return CaptchaForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		CaptchaForm form = (CaptchaForm) target;
		Captcha quiz = captchaFactory.find(form.getId());
		
		if (hasError(quiz, form.getAnswer())) {
			errors.rejectValue("answer", "captcha.answer.invalid", "invalid input");
		}
	}

	private boolean hasError(Captcha quiz, String answer) {
		return isUnknownCaptcha(quiz) || hasBadAnswer(quiz, answer);
	}
	
	private boolean isUnknownCaptcha(Captcha questionCaptcha) {
		return questionCaptcha == null;
	}

	private boolean hasBadAnswer(Captcha captcha, String answer) {
		try {
			return !captcha.isCorrect(Integer.parseInt(answer));
		}
		catch (NumberFormatException ex) {
			return true;
		}
	}
}
