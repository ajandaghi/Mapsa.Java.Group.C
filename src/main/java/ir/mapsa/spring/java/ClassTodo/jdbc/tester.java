package ir.mapsa.spring.java.ClassTodo.jdbc;

public class tester {
    public static void main(String[] args) throws Exception {
        Repository<Student> repository=new Repository<>(Student.class);
        Student student=new Student();
        student.setName("Ali");
        student.setFamily("Alavi");
        student.setNationalCode("0000000000");
        student.setPassedCourse(10);
        student.setStudentId("0001");

        repository.add(student);

        Student student1=new Student();
        student1.setId(1L);
        student1.setName("Reza");
        student1.setFamily("Razavi");
        student1.setNationalCode("0000000000");
        student1.setPassedCourse(10);
        student1.setStudentId("0001");
        repository.update(student1);

       // repository.getAll().forEach(System.out::println);

        //repository.removeById(1L);
         Student student3= new Student();
        student3.setName("Reza");
        student3.setFamily("Alavi");
        student3.setNationalCode("0000000000");
        student3.setPassedCourse(10);
        student3.setStudentId("0001");

        repository.findByExample(student3).forEach(System.out::println);







    }
}
