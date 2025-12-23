package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;   // ⭐ THIS LINE FIXES EVERYTHING

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SimpleStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain"); // test expects this
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
        out.write("Servlet Alive"); // exact test expectation
    }
}
