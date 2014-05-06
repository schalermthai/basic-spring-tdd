package captcha.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;

import captcha.controllers.CaptchaForm;
import captcha.models.Captcha;
import captcha.models.CaptchaFactory;
import captcha.models.NumberOperand;
import captcha.models.Operator;
import captcha.models.TextOperand;

public class CaptchaValidatorTest {
	
	CaptchaFactory factory = new CaptchaFactory();
	CaptchaValidator validator = new CaptchaValidator(factory);
	
	private Captcha captcha = new Captcha(new TextOperand(9), Operator.MINUS, new NumberOperand(3));
	private CaptchaForm captchaForm;
	private BeanPropertyBindingResult errors;
	
	@Before
	public void setup() {
		
		factory.getStorage().put(captcha.getId(), captcha);
		captchaForm = new CaptchaForm();
		captchaForm.setId(captcha.getId());
		captchaForm.setQuestion(captcha.getText());
		errors = new BeanPropertyBindingResult(captchaForm, "captchaForm");
	}
	
	@Test
	public void supportCaptchaForm() {
		Assert.assertTrue(validator.supports(CaptchaForm.class));
	}
	
	@Test
	public void valid_correct_answer() {
		
		//given
		captchaForm.setAnswer("6");
		
		//when
		validator.validate(captchaForm, errors);
		
		//then
		Assert.assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalid_emptyAnswer() {
		
		//given
		captchaForm.setAnswer("");
		
		//when
		validator.validate(captchaForm, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalid_badAnswerFormat() {
		
		//given
		captchaForm.setAnswer("One");
		
		//when
		validator.validate(captchaForm, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalid_wrongAnswer() {
		
		//given
		captchaForm.setAnswer("2");
		
		//when
		validator.validate(captchaForm, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalid_unknownCaptchaId() {
		
		//given
		captchaForm.setId("unknown");
		captchaForm.setAnswer("6");
		
		//when
		validator.validate(captchaForm, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
}
