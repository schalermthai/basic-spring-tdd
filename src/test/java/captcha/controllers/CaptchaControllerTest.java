package captcha.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import captcha.domain.CaptchaGenerator;
import captcha.models.CaptchaValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import captcha.configs.WebConfig;
import captcha.controllers.CaptchaControllerTest.TestCapchaConfig;
import captcha.domain.Captcha;
import captcha.domain.Operator;
import captcha.domain.CaptchaFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestCapchaConfig.class, WebConfig.class })
public class CaptchaControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    Captcha expectedCaptcha;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void get_showQuestion() throws Exception {
    	
    	mockMvc.perform(get("/captcha"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("captcha-form"))
	        .andExpect(forwardedUrl("/WEB-INF/view/captcha-form.jsp"))
	        .andExpect(model().attribute("captchaForm", hasProperty("id")))
	        .andExpect(model().attribute("captchaForm", hasProperty("question", equalTo("Five + 2 = ?"))));
    }
    
    @Test
    public void post_correctCaptcha_navigateToSuccessPage() throws Exception {
    	
    	mockMvc.perform(post("/captcha")
    			.param("id", expectedCaptcha.getId())
    			.param("answer", "7"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("captcha-correct"))
	        .andExpect(forwardedUrl("/WEB-INF/view/captcha-correct.jsp"));
    }

    @Test
    public void post_invalidCaptcha_stayOnSamePage() throws Exception {

        postCaptcha("-1")
                .andExpect(view().name("captcha-form"));
    }

    @Test
    public void post_invalidCaptcha_reAskQuestion() throws Exception {

        postCaptcha("-1")
                .andExpect(model().attribute("captchaForm", hasProperty("id")))
                .andExpect(model().attribute("captchaForm", hasProperty("question", equalTo("Five + 2 = ?"))));
    }

    @Test
    public void post_invalidCaptcha_showErrorMessage() throws Exception {

        postCaptcha("-1")
                .andExpect(model().attributeHasFieldErrors("captchaForm", "answer"));

    }

    @Test
    public void post_anotherCaptcher_showErrorMessage() throws Exception {

        postCaptcha("1000")
                .andExpect(model().attributeHasFieldErrors("captchaForm", "answer"));
    }

    @Test
    public void post_invalidCaptcha_handleStringAnswer() throws Exception {

        postCaptcha("Invalid answer")
                .andExpect(model().attributeHasFieldErrors("captchaForm", "answer"));
    }

    @Test
    public void post_invalidCaptcha_handleUnknownQuiz() throws Exception {

         mockMvc.perform(post("/captcha")
                .param("id", "unknown id")
                .param("answer", "7"))
                .andExpect(model().attributeHasFieldErrors("captchaForm", "answer"));

    }

    private ResultActions postCaptcha(String answer) throws Exception {
        return mockMvc.perform(post("/captcha")
                .param("id", expectedCaptcha.getId())
                .param("answer", answer));
    }

    @Configuration
    public static class TestCapchaConfig {

    	@Bean
    	@Scope("prototype")
    	public Captcha captcha() {
    		return factory().random();
    	}
    	
        @Bean
        public CaptchaFactory factory() {
            return new CaptchaFactory(generator());
        }

        @Bean
        public CaptchaValidator validator() {
            return new CaptchaValidator(factory());
        }

        @Bean
        public CaptchaGenerator generator() {
            return new CaptchaGenerator() {
                @Override
                protected int randomRightOperand() {
                    return 2;
                }

                @Override
                protected int randomLeftOperand() {
                    return 5;
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
    }
}