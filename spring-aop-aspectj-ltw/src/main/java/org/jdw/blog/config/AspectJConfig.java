package org.jdw.blog.config;

import org.aspectj.lang.Aspects;
import org.jdw.blog.common.aspect.HystrixAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;

// @EnableSpringConfigured enables AnnotationBeanConfigurerAspect for @Configurable beans
// AspectJWeaving.ENABLED makes META-INF/aop.xml required instead of auto-detected
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
@Configuration
public class AspectJConfig {

    /**
     * Makes the aspect a Spring bean, eligible for receiving autowired components.
     */
    @Bean
    public HystrixAspect hystrixAspect() {
        HystrixAspect aspect = Aspects.aspectOf(HystrixAspect.class);
        return aspect;
    }

}
