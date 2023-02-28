package ir.mapsa.spring.java.ClassTodo.jdbc;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Student {

    private Long id;
    private String name;
    private String family;

    private Integer passedCourse;
    private String nationalCode;
    private String studentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getPassedCourse() {
        return passedCourse;
    }

    public void setPassedCourse(Integer passedCourse) {
        this.passedCourse = passedCourse;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", passedCourse=" + passedCourse +
                ", nationalCode='" + nationalCode + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
