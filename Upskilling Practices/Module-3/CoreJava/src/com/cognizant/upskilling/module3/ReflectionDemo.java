package com.cognizant.upskilling.module3;

import java.lang.reflect.Method;

public class ReflectionDemo {
    public void secretMethod(String message) {
        System.out.println("SecretMethod executed with message: " + message);
    }
    
    public static void main(String[] args) {
        try {
            // Load class using Class.forName()
            Class<?> clazz = Class.forName("com.cognizant.upskilling.module3.ReflectionDemo");
            Object obj = clazz.getDeclaredConstructor().newInstance();
            
            System.out.println("Inspecting Declared Methods using Reflection API:");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Method Name: " + method.getName() + ", Return Type: " + method.getReturnType().getSimpleName());
            }
            
            // Invoke secretMethod dynamically
            System.out.println("\nInvoking secretMethod dynamically:");
            Method targetMethod = clazz.getDeclaredMethod("secretMethod", String.class);
            targetMethod.invoke(obj, "Reflection calls are powerful!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
