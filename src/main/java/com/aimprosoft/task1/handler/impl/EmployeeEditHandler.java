package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.dto.EmployeeDto;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeEditHandler extends AbstractHandler {
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    public EmployeeEditHandler() {
        setViewName(EMPLOYEE_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            EmployeeDto dto = new EmployeeDto();
            dto.setInstance(employeeService.getById(id));
            request.setAttribute("dto", dto);
        }
        catch (NullPointerException | NumberFormatException e) {
            // just show add page
        }
        request.setAttribute("department", request.getParameter("department"));
        forward(request, response);
    }
}
