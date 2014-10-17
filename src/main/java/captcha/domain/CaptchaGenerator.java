package captcha.domain;

import java.util.Random;

public class CaptchaGenerator {

    final Random randomizer = new Random(System.nanoTime());

    protected int randomRightOperand() {
        return randomizer.nextInt(10);
    }

    protected int randomLeftOperand() {
        return randomizer.nextInt(10);
    }

    protected Operator randomOperator() {
        Operator[] ops = Operator.values();
        return ops[randomizer.nextInt(ops.length)];
    }

    protected boolean isStartWithText() {
        return randomizer.nextBoolean();
    }

}
