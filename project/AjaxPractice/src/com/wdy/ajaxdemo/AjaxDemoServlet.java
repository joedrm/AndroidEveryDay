package com.wdy.ajaxdemo;

import javafx.print.Printer;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wdy on 16/9/28.
 */
public class AjaxDemoServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter printer = response.getWriter();
        printer.print("hello world");
    }
}
