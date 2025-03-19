package com.example.dio.service.imp;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.mapper.BillMapper;
import com.example.dio.mapper.OrderMapper;
import com.example.dio.model.Bill;
import com.example.dio.model.Restaurant;
import com.example.dio.model.TableOrder;
import com.example.dio.model.Tables;
import com.example.dio.repository.BillRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.OrderRepository;
import com.example.dio.repository.TablesRepository;
import com.example.dio.service.BillService;
import com.example.dio.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final OrderRepository orderRepository;
    private final TablesRepository tableRepository;
    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final OrderMapper orderMapper;
    private final PdfService pdfService;
    private final FoodItemRepository foodItemRepository;

    @Transactional
    @Override
    public BillResponse createBill(Long tableId) {
        Tables table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found with tableId : " + tableId));

        List<TableOrder> orders = orderRepository.findByOrderStatusAndTable_TableId(OrderStatus.UN_BILLED, tableId);

        Bill bill = new Bill();
        bill.setOrders(orders);
        double totalPayableAmount = calculateTotalPayableAmount(orders);
        bill.setTotalPayableAmount(totalPayableAmount);
        billRepository.save(bill);

        List<Long> tableOrderIds = getOrderIds(orders);

        mapOrdersListToOrderResponses(orders);

        orderRepository.updateTableOrderOrderStatus(tableOrderIds, OrderStatus.BILLED);


        return billMapper.mapToBillResponse(bill);

    }

    @Override
    public BillResponse findByBillId(Long billId) {
        return billRepository.findById(billId)
                .map(billMapper::mapToBillResponse)
                .orElseThrow(() -> new RuntimeException("Failed to find bill, Bill not found by id"));

    }

    @Override
    public byte[] generatePdf(Long billId) throws IOException {
        BillResponse billResponse = findByBillId(billId);

        long foodItemId = billResponse.getOrders().getFirst().getCartItems().getFirst().getFoodItem().getItemId();
        long tableNo = billResponse.getOrders().getFirst().getTable().getTableNo();

        Restaurant restaurant = foodItemRepository.findRestaurant_RestaurantNameByItemId(foodItemId);
        String restaurantName = restaurant != null ? restaurant.getRestaurantName() : "";

        Map<String, Object> data = Map.of("restaurantName", restaurantName, "bill", billResponse, "tableNo", tableNo, "foodItemId", foodItemId);
        byte[] pdfBytes = pdfService.generatePdf("bill_view", data);

        return pdfBytes;
    }

    private void mapOrdersListToOrderResponses(List<TableOrder> orders) {
        List<OrderResponse> orderResponses = orders.stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();
    }

    private static List<Long> getOrderIds(List<TableOrder> orders) {
        List<Long> tableOrderIds = orders.stream()
                .map(TableOrder::getOrderId)
                .toList();
        return tableOrderIds;
    }

    public double calculateTotalPayableAmount(List<TableOrder> orders) {
        double totalPayableAmount = 0;
        for (TableOrder order : orders) {
            totalPayableAmount = totalPayableAmount + order.getTotalAmount();

        }
        return totalPayableAmount;
    }
}