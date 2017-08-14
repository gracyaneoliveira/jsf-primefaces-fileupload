package com.projeto.upload.model;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class Teste {

	
	public static void main(String[] args) {
		User user = new User();
		user.setId(10);
//		user.setAge(10);
//		user.setCity("Fortaleza");
//		user.setEmail("gracyane@hotmail.com");
//		user.setFirstname("gracyane");
//		user.setLastname("oliveira");
//		user.setInfo("grande");
//		user.setPhone("3229 2946");
//		user.setPostalCode("60842220");
//		user.setStreet("Rua Alves Ribeiro");
	
		User userClone = null;
		
		try {
			userClone = (User) BeanUtils.cloneBean(user);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		System.out.println(userClone.hashCode() +" e "+ user.hashCode());
		System.out.println(userClone.equals(user));
		System.out.println(userClone.getClass().equals(user.getClass()));
		
		user.setId(11);
		System.out.println();
		System.out.println(userClone.hashCode() +" e "+ user.hashCode());
		System.out.println(userClone.equals(user));
		System.out.println(userClone.getClass().equals(user.getClass()));
		
	}

}
