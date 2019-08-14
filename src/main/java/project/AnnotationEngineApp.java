package project;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import project.engine.ConsoleInput;
import project.engine.RequestBody;
import project.engine.Service;
import org.reflections.Reflections;
import project.engine.StartMethod;
import project.model.Admin;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Scanner;
import java.util.Set;

public class AnnotationEngineApp {
    @SneakyThrows
    public static void main(String[] args) throws  Exception{
        Reflections reflections = new Reflections();
        Set<Class<?>> services = reflections.getTypesAnnotatedWith(Service.class);
        services.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        for(Class clazz:services){
            Object serviceInstance = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();

            for(Method method:methods){
                if(!method.isAnnotationPresent(StartMethod.class)){ continue;}
                Parameter[] parameters = method.getParameters();

                String input = null;
                Object requestBodyInput = null;
                for(Parameter parameter:parameters){
                    if(parameter.isAnnotationPresent(ConsoleInput.class)){
                       ConsoleInput consoleInput = parameter.getAnnotation(ConsoleInput.class);
                        System.out.println(consoleInput.displayMessage());
                        input = scanner.nextLine();
                    }
                    if(parameter.isAnnotationPresent(RequestBody.class)){
                        System.out.println("Enter JSON:");
                        input = scanner.nextLine();
                        ObjectMapper mapper = new ObjectMapper();
                        requestBodyInput = mapper.readValue(input, parameter.getType());
                    }
                }
                if(input != null && requestBodyInput == null){
                    method.invoke(serviceInstance,input);
                }else if(requestBodyInput != null && method.isAnnotationPresent(RequestBody.class)){
                    method.invoke(serviceInstance,requestBodyInput);
                }
                else{
                    Object[] params = new Object[parameters.length];
                    method.invoke(serviceInstance,params);
              }

            }
        }
        scanner.close();
        }
    }
