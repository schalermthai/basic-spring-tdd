package captcha.configs;

import captcha.domain.CaptchaGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import captcha.domain.Captcha;
import captcha.domain.CaptchaFactory;
import captcha.validators.CaptchaValidator;

@Configuration
public class CaptchaConfig {
	
	@Bean
	@Scope("singleton")
	public CaptchaFactory factory() {
		return new CaptchaFactory(new CaptchaGenerator());
	}

	@Bean
	@Scope("prototype")
	public Captcha captcha() {
		return factory().random();
	}
	
	@Bean
	public CaptchaValidator validator() {
		return new CaptchaValidator(factory());
	}
}
