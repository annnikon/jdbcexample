package com.aimprosoft.task1.servlet;


import com.aimprosoft.task1.handler.*;
import com.aimprosoft.task1.handler.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SafeServletContext {

    private static Map<String, Handler> handlers = new ConcurrentHashMap<>();

    private static String DEFAULT = "/departments/list";
    static {

        handlers.put("/departments/edit", new DepartmentEditHandler());
        handlers.put("/departments/delete", new DepartmentDeleteHandler());
        handlers.put("/departments/list", new DepartmentListHandler());
        handlers.put("/departments/submit", new DepartmentSubmitHandler());

        handlers.put("/employees/edit", new EmployeeEditHandler());
        handlers.put("/employees/delete", new EmployeeDeleteHandler());
        handlers.put("/employees/list", new EmployeeListHandler());
        handlers.put("/employees/submit", new EmployeeSubmitHandler());

    }


    public static Handler resolve(HttpServletRequest request) {
        String action = request.getParameter("action");

        String key = request.getServletPath()+ "/" + action;

        return handlers.getOrDefault(key, handlers.get(DEFAULT));
    }


}
