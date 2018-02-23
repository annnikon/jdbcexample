package com.aimprosoft.task1.dto;

import com.aimprosoft.task1.model.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class EmployeeDto {

    private final static String EMAIL_REGEXP = "[\\w\\d_.]+[@]([\\w]+[\\.][\\w]+)+";
    private final static String NAME_REGEXP = "[\\p{L}\\s]+";
    private final static String DATE_FORMAT = "yyy-MM-dd";


    private long id;
    private String email, name, birthday, room, departmentName;
    private Employee instance;
    private Map<String, String> errors = new LinkedHashMap<>();

    public long getId() {
        return id;
    }

    public Employee getInstance() {
        return errors.isEmpty() ? instance : null;
    }

    public void setInstance(Employee employee) {
        this.instance = employee;
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.room = String.valueOf(employee.getRoom());
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        this.birthday = format.format(employee.getBirthday());
        this.departmentName = employee.getDepartmentName();
    }

    public EmployeeDto() {
    }


    public EmployeeDto(String email, String name, String birthday,
                       String room, String departmentName) {
        this.id = 0; //for new employee
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.room = room;
        this.departmentName = departmentName;

        instance = new Employee();

        if (Pattern.compile(EMAIL_REGEXP).matcher(email).matches()) {
            instance.setEmail(email);
        } else {
            errors.put("Email", "May contain only letters, _, digits, @ and .");
        }
        if (Pattern.compile(NAME_REGEXP).matcher(name).matches()) {
            instance.setName(name);
        } else {
            errors.put("Name", "Can contain only letters in uppercase or lowercase, and spaces");
        }
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            instance.setBirthday(format.parse(birthday));
        } catch (ParseException e) {
            errors.put("Birthday", "Should be in format: " + DATE_FORMAT);
        }
        try {
            instance.setRoom(Integer.parseInt(room));
        } catch (NumberFormatException | NullPointerException e) {
            errors.put("Room", "Should be an integer number");
        }
        if (departmentName == null || departmentName.isEmpty()) {
            errors.put("Department Name", "please specify a department for employee!");
        } else {
            instance.setDepartmentName(departmentName);
        }


    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getRoom() {
        return room;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                " email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", room='" + room + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", instance=" + instance +
                ", is Valid=" + isValid() +
                '}';
    }
}
