package com.example.psk_1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select t from Student as t"),
        @NamedQuery(name = "Student.findAllStudentId", query = "select t.studentId from Student as t"),
})
@Table(name = "STUDENT")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(200000)
    @Max(299999)
    @EqualsAndHashCode.Include
    @Column(name = "STUDENT_ID", unique = true, nullable = false)
    private Integer studentId;

    @Size(max = 50)
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @ManyToMany
    private List<Course> courseList;

    @Version
    @Column(name= "OPT_LOCK_VERSION")
    private Integer optLockVersion;
}
