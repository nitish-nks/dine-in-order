package com.example.dio.dto.response;

import com.example.dio.model.TableOrder;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {

    private LocalDateTime generatedAt;

    private double totalPayableAmount;

    private List<OrderResponse> orders;

}
