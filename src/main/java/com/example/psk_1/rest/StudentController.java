package com.example.psk_1.rest;

import com.example.psk_1.entities.Student;
import com.example.psk_1.persistence.StudentDAO;
import com.example.psk_1.rest.contracts.StudentDTO;
import com.example.psk_1.rest.contracts.UpdateStudentDto;
import com.example.psk_1.rest.mappers.StudentMapper;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/students")
public class StudentController {
    @Inject
    @Getter @Setter
    private StudentDAO studentDAO;

    @Inject
    private StudentMapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        List<Student> students = studentDAO.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();

        students.forEach(student -> studentDTOS.add(mapper.toStudentDTO(student)));

        return Response.ok(studentDTOS).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateStudent(@PathParam("id") Integer id, UpdateStudentDto updateStudentDto) {
        Student student = mapper.fromUpdateToStudent(id, updateStudentDto);

        Student savedStudent = studentDAO.update(student);

        return Response.ok(savedStudent).build();
    }

    @POST
    @Transactional
    public Response createStudent(StudentDTO studentDTO) {
        Student student = mapper.toStudent(studentDTO);

        studentDAO.create(student);

        return Response.status(Response.Status.CREATED).build();
    }
}
