package com.example.dio.service.impl;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.exception.handler.UserNotFoundByIdException;
import com.example.dio.mapper.BillMapper;
import com.example.dio.mapper.OrderMapper;
import com.example.dio.model.Bill;
import com.example.dio.model.CartItem;
import com.example.dio.model.RestaurantTable;
import com.example.dio.model.TableOrder;
import com.example.dio.repository.BillRepository;
import com.example.dio.repository.OrderRepository;
import com.example.dio.repository.RestaurantTableRepository;
import com.example.dio.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final OrderRepository orderRepository;
    private final RestaurantTableRepository tableRepository;
    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public BillResponse createBill(Long tableId) {
       RestaurantTable table=tableRepository.findById(tableId)
               .orElseThrow(()-> new RuntimeException("Table not found with tableId : "+tableId));

        List<TableOrder> orders=orderRepository.findByOrderStatusAndTable_TableId(OrderStatus.UN_BILLED,tableId);

        Bill bill=new Bill();
        bill.setOrders(orders);
        double totalPayableAmount = calculateTotalPayableAmount(orders);
        bill.setTotalPayableAmount(totalPayableAmount);
        billRepository.save(bill);

        List<Long> tableOrderIds = getOrderIds(orders);

        mapOrdersListToOrderResponses(orders);

        orderRepository.updateTableOrderOrderStatus(tableOrderIds,OrderStatus.BILLED);


        return billMapper.mapToBillResponse(bill);

    }

    @Override
    public BillResponse findByBillId(Long billId) {
        return billRepository.findById(billId)
                .map(billMapper::mapToBillResponse)
                .orElseThrow(()->new RuntimeException("Failed to find bill, Bill not found by id"));

    }

    @Override
    public Byte[] generatePdf(Long billId) {
       BillResponse billResponse= findByBillId(billId);
    }

    private void mapOrdersListToOrderResponses(List<TableOrder> orders) {
        List<OrderResponse> orderResponses= orders.stream()
                        .map(orderMapper::mapToOrderResponse)
                                .toList();
    }

    private static List<Long> getOrderIds(List<TableOrder> orders) {
        List<Long> tableOrderIds = orders.stream()
                .map(TableOrder::getOrderId)
                .toList();
        return tableOrderIds;
    }

    public double calculateTotalPayableAmount(List<TableOrder> orders){
        double totalPayableAmount=0;
        for(TableOrder order:orders){
            totalPayableAmount=totalPayableAmount+order.getTotalAmount();

        }
        return totalPayableAmount;
    }
}
