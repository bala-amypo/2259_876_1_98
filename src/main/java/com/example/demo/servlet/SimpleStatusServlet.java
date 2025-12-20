package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class SimpleStatusServlet extends HttpServlet {

    // ✅ MUST be public (test calls it directly)
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("OK");
    }
}
