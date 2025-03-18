package com.example.dio.controller;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.service.BillService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class BillController {
    private BillService billService;

    @PostMapping("/bills/tables/{tableId}")
    ResponseEntity<ResponseStructure<BillResponse>> createBill(@PathVariable Long tableId){
        BillResponse billResponse=billService.createBill(tableId);
        return ResponseBuilder.created("Bill created",billResponse);

    }

    @GetMapping("/bills/{billId}")
    ResponseEntity<ResponseStructure<BillResponse>> findByBillId(@PathVariable Long billId){
        BillResponse billResponse=billService.findByBillId(billId);
        return ResponseBuilder.ok("Bill found",billResponse);
    }

    @GetMapping("/bills/{billId}/pdf")
    ResponseEntity<Byte[]> generatePdf(@PathVariable Long billId){
        Byte[] bill=billService.generatePdf(billId);
        return ResponseEntity.ok().contentType();
    }
}

