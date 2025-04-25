package com.example.psk_1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "select c from Course as c")
})
@Table(name = "COURSE")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 150)
    @Column(name = "COURSE_NAME", nullable = false)
    private String courseName;

    @ManyToOne
    @EqualsAndHashCode.Include
    @JoinColumn(name = "LECTURER_ID", nullable = false)
    private Lecturer lecturer;

    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList;
}
