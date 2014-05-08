package captcha.validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import captcha.domain.Captcha;
import captcha.domain.CaptchaFactory;
import captcha.domain.NumberOperand;
import captcha.domain.Operator;
import captcha.domain.TextOperand;
import captcha.models.CaptchaForm;

public class CaptchaValidatorTest {
	
	CaptchaFactory factory = Mockito.mock(CaptchaFactory.class);
	
	//unit under test
	CaptchaValidator validator = new CaptchaValidator(factory);
	
	private CaptchaForm form;
	private Errors errors;
	
	@Before
	public void setup() {
		
		Captcha captcha = new Captcha(new TextOperand(9), Operator.MINUS, new NumberOperand(3));

		Mockito.when(factory.find(captcha.getId())).thenReturn(captcha);
		
		form = new CaptchaForm();
		form.setId(captcha.getId());
		form.setQuestion(captcha.getText());
		errors = new BeanPropertyBindingResult(form, "captchaForm");
	}
	
	@Test
	public void supportCaptchaForm() {
		Assert.assertTrue(validator.supports(CaptchaForm.class));
	}
	
	@Test
	public void valid_correct_answer() {
		
		//given
		form.setAnswer("6");
		
		//when
		validator.validate(form, errors);
		
		//then
		Assert.assertFalse(errors.hasErrors());
	}
	
	@Test
	public void invalid_emptyAnswer() {
		
		//given
		form.setAnswer("");
		
		//when
		validator.validate(form, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalid_badAnswerFormat() {
		
		//given
		form.setAnswer("One");
		
		//when
		validator.validate(form, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalid_wrongAnswer() {
		
		//given
		form.setAnswer("2");
		
		//when
		validator.validate(form, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalid_unknownCaptchaId() {
		
		//given
		form.setId("unknown");
		form.setAnswer("6");
		
		//when
		validator.validate(form, errors);
		
		//then
		Assert.assertTrue(errors.hasErrors());
	}
}
