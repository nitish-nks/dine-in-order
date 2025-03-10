package com.example.dio.controller;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.service.TablesService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Table Controller", description = "Table API Endpoints Dealing with Table data.")
public class TableController {
    private TablesService tablesService;

    @PostMapping("/restaurants/{restaurantId}/tables")
    public ResponseEntity<ResponseStructure<TableResponse>> registerTable(@RequestBody @Valid TableRequest tableRequest, @PathVariable Restaurant restaurantId) {
        TableResponse tableResponse = tablesService.registerTable(tableRequest, restaurantId);
        return ResponseBuilder.success(HttpStatus.CREATED, "Table is Created", tableResponse);
    }
}
