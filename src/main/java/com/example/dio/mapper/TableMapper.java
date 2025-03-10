package com.example.dio.mapper;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.Tables;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    Tables mapToTableEntity(TableRequest tableRequest);

    TableResponse mapToTableResponse(Tables tables);
}
