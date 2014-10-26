package captcha.domain;

import org.junit.Assert;
import org.junit.Test;

import captcha.domain.NumberOperand;

public class NumberOperandTest {
	
	@Test
	public void testNumberOperand_value() {
		Assert.assertEquals(1,  new NumberOperand(1).getValue());
		Assert.assertEquals(2,  new NumberOperand(2).getValue());
		Assert.assertEquals(3,  new NumberOperand(3).getValue());
		Assert.assertEquals(4,  new NumberOperand(4).getValue());
		Assert.assertEquals(5,  new NumberOperand(5).getValue());
		Assert.assertEquals(6,  new NumberOperand(6).getValue());
		Assert.assertEquals(7,  new NumberOperand(7).getValue());
		Assert.assertEquals(8,  new NumberOperand(8).getValue());
		Assert.assertEquals(9,  new NumberOperand(9).getValue());
		Assert.assertEquals(0,  new NumberOperand(0).getValue());	
	}

    @Test
    public void testNumberOperand_text() {
        Assert.assertEquals("0", new NumberOperand(0).toText());
        Assert.assertEquals("1", new NumberOperand(1).toText());
        Assert.assertEquals("2", new NumberOperand(2).toText());
        Assert.assertEquals("3", new NumberOperand(3).toText());
        Assert.assertEquals("4", new NumberOperand(4).toText());
        Assert.assertEquals("5", new NumberOperand(5).toText());
        Assert.assertEquals("6", new NumberOperand(6).toText());
        Assert.assertEquals("7", new NumberOperand(7).toText());
        Assert.assertEquals("8", new NumberOperand(8).toText());
        Assert.assertEquals("9", new NumberOperand(9).toText());
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void testNumberOperand_badValue_maxBound() {
		new NumberOperand(10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNumberOperand_badValue_minBound() {
		new NumberOperand(-1);
	}
}
