# spring-aop-common

Becomes a common JAR injected into the other projects; it does not execute on its own.

Contains a HystrixAdvice Spring bean aspect that is triggered by using the custom @HystrixWrapper annotation, along with several classes that can use them together for AOP functionality when configured correctly.
