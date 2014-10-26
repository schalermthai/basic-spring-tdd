package captcha.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import captcha.domain.Captcha;
import captcha.domain.CaptchaFactory;
import captcha.models.CaptchaForm;

@Component
public class CaptchaValidator implements Validator {
	
	CaptchaFactory factory;
	
	public CaptchaValidator(CaptchaFactory factory) {
		this.factory = factory;
	}
	
	public boolean supports(Class<?> clazz) {
		return CaptchaForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		CaptchaForm form = (CaptchaForm) target;
        Captcha quiz = factory.find(form.getId());

        if (!form.isValid(quiz)) {
			errors.rejectValue("answer", "captcha.answer.invalid", "Invalid input");
		}
	}
}
