package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FormData {
    private String city;
    private LocalDate meetDate;
    private String name;
    private String phoneNumber;
    private boolean agreementChecked;
}
