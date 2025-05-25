package com.example.psk_1.rest;

import com.example.psk_1.entities.Student;
import com.example.psk_1.persistence.StudentDAO;
import com.example.psk_1.rest.contracts.GeneratedIdDTO;
import com.example.psk_1.rest.contracts.StudentDTO;
import com.example.psk_1.rest.contracts.UpdateStudentDto;
import com.example.psk_1.rest.mappers.StudentMapper;
import com.example.psk_1.service.RandomStudentIdGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") Integer id) {
        Student student = studentDAO.get(id);

        StudentDTO studentDTO = mapper.toStudentDTO(student);

        return Response.ok(studentDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") Integer id, UpdateStudentDto updateStudentDto) {
        Student student = mapper.fromUpdateToStudent(id, updateStudentDto);

        try {
            Student savedStudent = studentDAO.update(student);

            StudentDTO studentDTO = mapper.toStudentDTO(savedStudent);

            return Response.ok(studentDTO).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Transactional
    public Response createStudent(StudentDTO studentDTO) {
        Student student = mapper.toStudent(studentDTO);

        studentDAO.create(student);

        return Response.status(Response.Status.CREATED).build();
    }
}
