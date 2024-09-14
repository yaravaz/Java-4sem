package com.example.lab13.command.groupuniversities;

import com.example.lab13.command.*;
import com.example.lab13.exception.*;
import com.example.lab13.model.University;
import com.example.lab13.service.UniversityService;
import com.example.lab13.util.pages.Page;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class WelcomeCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        UniversityService universityService = new UniversityService();
        List<University> universities = universityService.findAll();
        if (!universities.isEmpty()) {
            request.setAttribute("universities", universities);
        }
        request.getRequestDispatcher(Page.WELCOME_PAGE.getPage()).forward(request, response);
        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }
}
