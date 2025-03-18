package com.example.dio.mapper;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.model.TableOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse mapToOrderResponse(TableOrder tableOrder);
}
