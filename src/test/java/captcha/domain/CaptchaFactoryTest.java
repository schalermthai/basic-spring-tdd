package captcha.domain;

import org.junit.Assert;
import org.junit.Test;

import captcha.domain.Captcha;
import captcha.domain.CaptchaFactory;
import captcha.domain.Operator;

public class CaptchaFactoryTest {

	@Test
	public void test() {
		Captcha captcha = new MockCaptchaFactory().random();
		Assert.assertEquals("One + 9 = ?", captcha.getText());
	}
	
	private class MockCaptchaFactory extends CaptchaFactory {
		
		@Override
		protected boolean isStartWithText() {
			return true;
		}
		
		@Override
		protected int randomLeftOperand() {
			return 1;
		}
		
		@Override
		protected int randomRightOperand() {
			return 9;
		}
		
		@Override
		protected Operator randomOperator() {
			return Operator.PLUS;
		}
	}
}
