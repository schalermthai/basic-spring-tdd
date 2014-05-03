package captcha.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import captcha.controllers.CaptchaForm;
import captcha.models.Captcha;
import captcha.models.CaptchaFactory;

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
		
		Captcha captcha = captchaFactory.getStorage().get(form.getId());
		if (captcha == null || isValidAnswer(errors, form, captcha) == false) {
			errors.rejectValue("answer", "captcha.answer.invalid", "invalid input");
		}
	}

	private boolean isValidAnswer(Errors errors, CaptchaForm form, Captcha captcha) {
		try {
			return captcha.isCorrect(Integer.parseInt(form.getAnswer()));
		} catch(NumberFormatException ex) {
			return false;
		}
	}
}
