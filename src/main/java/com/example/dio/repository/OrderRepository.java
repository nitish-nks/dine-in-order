package com.example.dio.repository;

import com.example.dio.enums.OrderStatus;
import com.example.dio.model.CartItem;
import com.example.dio.model.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TableOrder,Long> {

    List<TableOrder> findByOrderStatusAndTable_TableId(OrderStatus status, Long tableId);

    @Modifying
    @Transactional
    @Query("UPDATE TableOrder t SET t.orderStatus = :status WHERE t.orderId IN :orderIds")
    void updateTableOrderOrderStatus(@Param("orderIds") List<Long> orderIds,
                                     @Param("status") OrderStatus status);

}
