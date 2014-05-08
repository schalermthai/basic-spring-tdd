package captcha.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"captcha.controllers" })
public class WebConfig extends WebMvcConfigurationSupport {

    private static final String VIEWS = "/WEB-INF/view/";

    private static final String RESOURCES_HANDLER = "/resources/";
    private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";

    @Bean
    public InternalResourceViewResolver viewResolver() {
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setPrefix(VIEWS);
    	resolver.setSuffix(".jsp");
    	return resolver;
    }

    @Override
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
