package org.jdw.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Can use this instead of spring.aop.proxy-target-class=true
// to force CGLIB proxies for all Spring bean proxies.
// @EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

}
