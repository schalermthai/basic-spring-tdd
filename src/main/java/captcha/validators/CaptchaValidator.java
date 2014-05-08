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
		if (hasErrors(form)) {
			errors.rejectValue("answer", "captcha.answer.invalid", "Invalid input");
		}
	}
	
	private boolean hasErrors(CaptchaForm captchaForm) {
		Captcha quiz = factory.find(captchaForm.getId());
		return badQuiz(quiz) || badAnswer(captchaForm, quiz);
	}

	private boolean badQuiz(Captcha quiz) {
		return quiz == null;
	}

	private boolean badAnswer(CaptchaForm captchaForm, Captcha quiz) {
		try {
			return !quiz.isCorrect(Integer.parseInt(captchaForm.getAnswer()));
		} catch(NumberFormatException ex) {
			return true;
		}
		
	}

}
