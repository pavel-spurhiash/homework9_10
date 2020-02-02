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
import java.util.List;

import static com.gmail.pashasimonpure.controller.constant.PageConstant.*;

public class UserListServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserDTO> users = userService.findAll(); //get from service

        if (!users.isEmpty()) {

            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher(PAGE_USER_LIST).forward(req, resp);

        } else {

            logger.info("No users in database.");
            req.setAttribute("msg", "No users in database.");
            getServletContext().getRequestDispatcher(PAGE_MESSAGE).forward(req, resp);

        }

    }

}