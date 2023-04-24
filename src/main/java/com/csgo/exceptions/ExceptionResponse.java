package com.csgo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ExceptionResponse {
    @NonNull
    @NotNull
    private LocalDate localDate;
    @NotNull
    @NonNull
    private String message;
    private List<String> details;

}
