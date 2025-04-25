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
        @NamedQuery(name = "Lecturer.findAll", query = "select t from Lecturer as t")
})
@Table(name = "LECTURER")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 50)
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "lecturer")
    private List<Course> courseList;
}
