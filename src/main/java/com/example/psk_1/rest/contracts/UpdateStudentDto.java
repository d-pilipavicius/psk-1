package com.example.psk_1.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateStudentDto {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private int optLockVersion;
}
