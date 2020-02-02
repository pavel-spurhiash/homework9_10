package com.gmail.pashasimonpure.controller.servlet;

import com.gmail.pashasimonpure.service.UserService;
import com.gmail.pashasimonpure.service.impl.UserServiceImpl;
import com.gmail.pashasimonpure.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.regex.Pattern;

import static com.gmail.pashasimonpure.controller.constant.PageConstant.*;
import static com.gmail.pashasimonpure.controller.constant.RegexConstant.*;

public class NewUserServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("name");
        String password = req.getParameter("password");
        String isActive = req.getParameter("isActive");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");

        //pattern validation:
        if (Pattern.compile(USER_NAME_REGEX).matcher(userName).matches() &
                Pattern.compile(PASSWORD_REGEX).matcher(password).matches() &
                Pattern.compile(AGE_REGEX).matcher(age).matches() &
                Pattern.compile(ADDRESS_REGEX).matcher(address).matches() &
                Pattern.compile(PHONE_NUMBER_REGEX).matcher(telephone).matches()) {

            UserDTO user = new UserDTO();
            user.setName(userName);
            user.setPassword(password);
            user.setActive(Boolean.parseBoolean(isActive));
            user.setAge(Integer.parseInt(age));
            user.setAddress(address);
            user.setTelephone(telephone);

            if (!userService.add(user)) {
                logger.error("Create user error, check logs.");
                resp.sendError(500, "Create user error, check logs."); //500 - server error
                return;
            }

            req.setAttribute("msg", "User " + userName + " successfully created.");
            getServletContext().getRequestDispatcher(PAGE_MESSAGE).forward(req, resp);

        } else {

            logger.error("Incorrect parameter format.");
            resp.sendError(400, "Incorrect parameter format."); //400 - bad request

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(PAGE_CREATE_USER).forward(req, resp);
    }

}