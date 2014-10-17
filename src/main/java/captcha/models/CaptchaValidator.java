package captcha.models;

import captcha.domain.Captcha;
import captcha.domain.CaptchaFactory;
import captcha.models.CaptchaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CaptchaValidator implements Validator {
    final private CaptchaFactory factory;

    @Autowired
    public CaptchaValidator(CaptchaFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CaptchaForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CaptchaForm form = (CaptchaForm) target;

        if (hasErrors(form)) {
            errors.rejectValue("answer", "captcha.answer.invalid", "Invalid input");
        }
    }

    public boolean hasErrors(CaptchaForm captchaForm) {
        Captcha quiz = factory.find(captchaForm.getId());
        return !knownQuiz(quiz) || !validAnswer(captchaForm, quiz);
    }

    private boolean knownQuiz(Captcha quiz) {
        return quiz != null;
    }

    private boolean validAnswer(CaptchaForm captchaForm, Captcha quiz) {
        try {
            return quiz.isCorrect(Integer.parseInt(captchaForm.getAnswer()));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
