package captcha.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import captcha.models.Captcha;
import captcha.models.CaptchaFactory;
import captcha.models.Operator;

@Configuration
public class TestContext {

	@Bean @Primary
	@Scope("singleton")
	public CaptchaFactory captchaFactory() {
		return new CaptchaFactory() {

			@Override
			protected int randomLeftOperand() {
				return 5;
			}
			
			@Override
			protected int randomRightOperand() {
				return 2;
			}
			
			@Override
			protected Operator randomOperator() {
				return Operator.PLUS;
			}
			
			@Override
			protected boolean isStartWithText() {
				return true;
			}
		};
	}

	@Bean
	@Scope("prototype")
	public Captcha captcha() {
		return captchaFactory().random();
	}
	
}