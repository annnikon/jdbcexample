package com.aimprosoft.task1.dto;

import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.utils.ValidationUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class DepartmentDto {

    private long id;
    private String name, info;
    private Department instance;
    private Map<String, String> errors = new LinkedHashMap<>();

    public Department getInstance() {
        return errors.isEmpty()?instance:null;
    }

    public void setInstance(Department department) {
        this.instance = department;
        this.id = department.getId();
        this.name = department.getName();
        this.info = department.getInfo();
    }

    public DepartmentDto() {
    }

    public DepartmentDto(String name, String info) {
        this.id = 0; //our department is new; id will be autogeneraated in db
        this.name = name;
        this.info = info;
        if (name == null || name.isEmpty()) {
            errors.put("Name", "Cannot be empty");
        }

        instance = new Department();
        if(name==null || name.isEmpty()) {
            errors.put("Name", "Cannot be empty");
        }
        else {
            instance.setName(name);
        }
        instance.setInfo(info);

    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", instance=" + instance +
                ", errors=" + errors +
                ", is Valid=" + isValid() +
                '}';
    }
}
