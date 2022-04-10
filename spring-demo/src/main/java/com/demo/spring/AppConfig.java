package com.demo.spring;

import com.demo.spring.bean.Person;
import com.demo.spring.bean.User;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan("com.demo.spring")
@Configuration
public class AppConfig {

	public static void main(String[] args) {
		testBeanDefinition();
	}

	public static void testGetBean() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getBean("personService");
		System.out.println("finish");
	}

	public static void testChild() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
		Person obj = context.getBean("child", Person.class);
		System.out.println(context.getBeanFactory().getBeanDefinition("child"));
		System.out.println(obj);
	}

	public static void testBeanDefinition() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		GenericBeanDefinition dbd = new GenericBeanDefinition();
		dbd.setBeanClass(Person.class);
		dbd.setParentName("user");

		context.registerBeanDefinition("person", dbd);
		//context.refresh();
		System.out.println(context.getBeanDefinition("person"));
	}
}
