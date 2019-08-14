package project.model;

import project.engine.ConsoleInput;
import project.engine.Service;
import project.engine.StartMethod;

@Service
public class UserService {
    @StartMethod
    public void start(@ConsoleInput(displayMessage = "Enter user data :") String input){
        System.out.println("user service -> start -> input");
        System.out.println(input);
    }
}
