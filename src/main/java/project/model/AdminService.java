package project.model;

import project.engine.ConsoleInput;
import project.engine.RequestBody;
import project.engine.Service;
import project.engine.StartMethod;

@Service
public class AdminService {
    @RequestBody
    @StartMethod
    public void run(@ConsoleInput(displayMessage = "Admin : Write name(String) and password(String) in JSON -> {name: <your name> , password: <your password>}") String input,@RequestBody Admin admin) {
        System.out.println("admin service -> run() -> Admin.class");
        System.out.println(admin);
    }
    public void unRun() {
        System.out.println("DO NOT CALL ME");
    }
}
