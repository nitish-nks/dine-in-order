package com.example.dio.mapper;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.model.Bill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    BillResponse mapToBillResponse(Bill bill);
}
