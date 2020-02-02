package com.gmail.pashasimonpure.controller.servlet;

import com.gmail.pashasimonpure.service.UserService;
import com.gmail.pashasimonpure.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static com.gmail.pashasimonpure.controller.constant.PageConstant.*;

public class DeleteUserServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Long id = Long.parseLong(req.getParameter("id"));

            if (userService.deleteUser(id)) {

                logger.info("User#" + id + " successfully  deleted.");
                resp.sendRedirect("users");

            } else {

                logger.error("Delete user error, check logs.");
                resp.sendError(500, "Delete user error, check logs."); //500 - server error

            }

        } catch (NumberFormatException e) {

            logger.error("Incorrect parameter format.");
            resp.sendError(400, "Incorrect parameter format."); //400 - bad request

        }

    }

}