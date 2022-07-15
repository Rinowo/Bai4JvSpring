package com.example.studentmanagement.controller;


import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    StudentServiceImpl studentService;

    //http://localhost:8080/student
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllUser() {
        List<Student> list = studentService.getAllStudent();
        if (list.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //http://localhost:8080/student
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/student/1
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,
                                              @RequestBody Student student) {
        Student oldStudent = studentService.getOne(id);
        if (oldStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            oldStudent.setStudentId(student.getStudentId());
            oldStudent.setName(student.getName());
            oldStudent.setEmail(student.getEmail());
            oldStudent.setPhone(student.getPhone());
            studentService.saveStudent(oldStudent);
            return ResponseEntity.ok(oldStudent);
        }
    }

    //http://localhost:8080/student/1
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteUser(@PathVariable("id") Long id) {
        Optional<Student> dStudent = studentService.findStudentById(id);
        if (dStudent == null) {
            return ResponseEntity.notFound().build();
        } else {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        }
    }

    //http://localhost:8080/student/1
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentId(@PathVariable("id") long id) {
        Student student = studentService.getOne(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //http://localhost:8080/student/findStudentId?student_Id=Ms1
    @GetMapping("/student/findStudentId")
    public ResponseEntity<List<Student>> getByStudentId(@RequestParam String student_Id) {
        return new ResponseEntity<>(studentService.getStudentId(student_Id), HttpStatus.OK);
    }

    //http://localhost:8080/student/findEmail?email=Rino@gmail.com
    @GetMapping("/student/findEmail")
    public ResponseEntity<List<Student>> getByEmail(@RequestParam String email) {
        return new ResponseEntity<>(studentService.getStudentEmail(email), HttpStatus.OK);
    }

    //http://localhost:8080/student/findPhone?phone=0369122828
    @GetMapping("/student/findPhone")
    public ResponseEntity<List<Student>> getByPhone(@RequestParam String phone) {
        return new ResponseEntity<>(studentService.getStudentPhone(phone), HttpStatus.OK);
    }
}
