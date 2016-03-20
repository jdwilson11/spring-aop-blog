# spring-aop-blog
_Three examples of how to configure AOP in a multi-project Spring Boot application._

**spring-aop-common**

Becomes a common JAR injected into the other projects; it does not execute on its own.

Contains a HystrixAdvice Spring bean aspect that is triggered by using the custom @HystrixWrapper annotation, along with several classes that can use them together for AOP functionality when configured correctly.

**spring-aop-proxy**

Demonstrates default JDK proxy based Spring AOP.

**spring-aop-proxy-cglib**

Demonstrates Spring AOP using CGLIB.

**spring-aop-aspectj-ltw**

Demonstrates how to set up real AspectJ loadtime weaving in Spring Boot.

_Requires both of these JVM arguments to run (insert your own paths to the provided JARs):_
-javaagent:path/to/spring-aop-aspectj-ltw/spring-instrument-4.2.5.RELEASE.jar
-javaagent:path/to/spring-aop-aspectj-ltw/aspectjweaver-1.8.8.jar
