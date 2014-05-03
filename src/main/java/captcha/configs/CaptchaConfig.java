package captcha.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import captcha.models.Captcha;
import captcha.models.CaptchaFactory;

@Configuration
public class CaptchaConfig {
	
	@Bean
	@Scope("singleton")
	public CaptchaFactory captchaFactory() {
		return new CaptchaFactory();
	}

	@Bean
	@Scope("prototype")
	public Captcha captcha() {
		return captchaFactory().random();
	}
}
