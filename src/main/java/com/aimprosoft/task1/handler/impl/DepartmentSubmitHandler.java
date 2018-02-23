package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.dto.DepartmentDto;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.service.impl.DepartmentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DepartmentSubmitHandler extends AbstractHandler {

    DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    public DepartmentSubmitHandler() {
        setViewName(DEPARTMENT_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long id;
        try {
            id = Long.parseLong(request.getParameter("id")); //update
        } catch (NumberFormatException | NullPointerException e) {
            id = 0; //create
        }
        String newName = request.getParameter("name");
        String newInfo = request.getParameter("info");
        DepartmentDto dto = new DepartmentDto(newName, newInfo);

        if (!dto.isValid()) {
            request.setAttribute("warning", "Please fill this fields correctly:");
            request.setAttribute("errors", dto.getErrors());
            request.setAttribute("dto", dto);
            forward(request, response);
        }

        if (!isNameUnique(id, dto.getName())) {
            request.setAttribute("warning", "This department already exists: " + dto.getName());
            request.setAttribute("dto", dto);
            forward(request, response);
        }

        Department department = dto.getInstance();

        if (id == 0) {
            departmentService.addDepartment(department);
        } else {
            departmentService.editDepartment(id, department);
        }

        redirect(response, "departments?action=list");

    }


    private boolean isNameUnique(long id, String newName) throws SQLException {
        if (id == 0) return departmentService.getByName(newName) == null;
        return departmentService.getById(id).getName().equals(newName) || departmentService.getByName(newName) == null;
    }


}
