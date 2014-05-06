package captcha.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import captcha.configs.WebConfig;
import captcha.controllers.CaptchaControllerTest.TestCapchaConfig;
import captcha.models.Captcha;
import captcha.models.CaptchaFactory;
import captcha.models.NumberOperand;
import captcha.models.Operator;
import captcha.models.TextOperand;

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
    public void post_validateCaptcha_fail() throws Exception {
    	
    	mockMvc.perform(post("/captcha")
    			.param("id", expectedCaptcha.getId())
    			.param("answer", "2"))
	        .andExpect(status().isOk())
	        .andExpect(model().hasErrors())
	        .andExpect(model().attributeHasFieldErrors("captchaForm", "answer"));
    }
    
    @Test
    public void post_invalidCaptcha_rerenderQuestion() throws Exception {
    		
    	mockMvc.perform(post("/captcha")
    			.param("id", expectedCaptcha.getId())
    			.param("answer", "2"))
	        .andExpect(model().hasErrors())
	        .andExpect(view().name("captcha-form"))
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
    
    @Configuration
    public static class TestCapchaConfig {

    	@Bean
    	@Scope("singleton")
    	public CaptchaFactory captchaFactory() {
    		return new CaptchaFactory() {
    			@Override
    			protected Captcha generateCaptcha() {
    				return new Captcha(new TextOperand(5), Operator.PLUS, new NumberOperand(2));
    			}
    		};
    	}

    	@Bean
    	@Scope("prototype")
    	public Captcha captcha() {
    		return captchaFactory().random();
    	}
    	
    }



}