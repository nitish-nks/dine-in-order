package com.example.dio.dto.request;

import com.example.dio.enums.TableStatus;
import com.example.dio.model.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {
    @NotEmpty(message = "Table can not be null")
    @NotNull(message = "Table can not be null or black")
    @Pattern(regexp = "^[0-9_]+$",message = "Table number is not valid")
    private long tableNo;

    @NotEmpty(message = "Table Capacity can not be null")
    @NotNull(message = "Table Capacity can not be null or black")
    @Pattern(regexp = "^[0-9_]+$",message = "Table Capacity number and underscore")
    private long tableCapacity;

    private TableStatus tableStatus;
}
