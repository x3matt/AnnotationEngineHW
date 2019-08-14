package project.model;

import project.engine.ConsoleInput;
import project.engine.RequestBody;
import project.engine.Service;
import project.engine.StartMethod;

@Service
public class UserService {
    @RequestBody
    @StartMethod
    public void start(@ConsoleInput(displayMessage = "User : Write name(String) and password(String) in JSON -> {name: <your name> , password: <your password>}") String input,@RequestBody User user) {
        System.out.println("user service -> start() -> User.class");
        System.out.println(user);
    }
}
