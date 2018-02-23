package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.dto.EmployeeDto;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EmployeeSubmitHandler extends AbstractHandler {

    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    public EmployeeSubmitHandler() {
        setViewName(EMPLOYEE_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id;
        try {
            id = Long.parseLong(request.getParameter("id")); //edit existing
        }
        catch (NullPointerException | NumberFormatException e) {
            id = 0; //add employee
        }

        EmployeeDto dto = getDto(request);

        if (!dto.isValid()) {
            request.setAttribute("warning", "Please fill this fields correctly:");
            request.setAttribute("errors", dto.getErrors());
        }

        else if (!isEmailUnique(id,dto.getEmail())) {
            request.setAttribute("warning", "Email already belongs to another employee:"+dto.getEmail());
        }

        else try {
            if (id == 0) employeeService.addEmployee(dto.getInstance());
            else employeeService.editEmployee(id, dto.getInstance());
            redirect(response, "employees?action=list&department=" + dto.getDepartmentName()); //success

        } catch (SQLException e) {
            request.setAttribute("warning", "No such department: " + dto.getDepartmentName());
        }

        request.setAttribute("dto", dto);
        forward(request, response);

    }

    private EmployeeDto getDto(HttpServletRequest request) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String room = request.getParameter("room");
        String departmentName = request.getParameter("departmentName");
        return new EmployeeDto(email, name, birthday, room, departmentName);
    }

    private boolean isEmailUnique(long id, String newEmail) throws SQLException{

        if (id==0) return employeeService.getByEmail(newEmail) == null;
        return employeeService.getById(id).getEmail().equals(newEmail) || employeeService.getByEmail(newEmail) == null;
    }

}
