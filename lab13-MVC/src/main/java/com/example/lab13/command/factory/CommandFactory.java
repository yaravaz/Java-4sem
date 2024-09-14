package com.example.lab13.command.factory;

import com.example.lab13.command.Command;
import com.example.lab13.command.*;
import com.example.lab13.command.authorithation.*;
import com.example.lab13.command.groupuniversities.*;

public class CommandFactory {
    public static Command create(String command) {
        command = command.toUpperCase();
        System.out.println(command);
        CommandType commandEnum = CommandType.valueOf(command);
        Command resultCommand;
        switch (commandEnum) {
            case LOGIN: {
                resultCommand = new LoginCommand(); break;
            }
            case REGISTER_NEW_USER: {
                resultCommand = new RegisterNewUserCommand(); break;
            }
            case SIGN_OUT: {
                resultCommand = new SingOutCommand(); break;
            }
            case ADD_NEW_UNIVERSITY:{
                resultCommand = new AddNewUniversityCommand(); break;
            }
            case LOGIN_PAGE:{
                resultCommand = new LoginPageCommand(); break;
            }
            case WELCOME:{
                resultCommand = new WelcomeCommand(); break;
            }
            case REGISTRATION_PAGE:{
                resultCommand = new RegisterPageCommand(); break;
            }
            default: {
                throw new IllegalArgumentException("Invalid command" + commandEnum);
            }
        }
        return resultCommand;
    }
}
