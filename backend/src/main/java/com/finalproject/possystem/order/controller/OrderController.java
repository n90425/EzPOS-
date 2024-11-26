package com.finalproject.possystem.order.controller;


import com.finalproject.possystem.order.entity.Order;
import com.finalproject.possystem.order.entity.OrderDetail;
import com.finalproject.possystem.order.service.OrderDetailService;
import com.finalproject.possystem.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /* 전체주문조회 */
    @GetMapping("/all")
    public List<Order> getOrder(){
        System.out.println(orderService.allOrder());
        return orderService.allOrder();
    }

    /* 선택된 테이블에서 주문생성 */
    @PostMapping("/order/{tableNo}")
    public ResponseEntity<String> addToOrder(@PathVariable int tableNo, @RequestBody List<OrderDetail> orderDetail){
        try {
            /* 주문생성 또는 가져오기 */
            String orderNo = orderService.createOrGetOrder(tableNo);

            for(OrderDetail detail : orderDetail){
                /* orderDetail에 주문번호 set */
                detail.setOrderNo(orderNo);
                /* orderDetail orderAddNo 추가주문 생성 또는 값증가, 총금액계산 생성 */
                orderDetailService.addItemToOrder(detail);
            }
            return ResponseEntity.ok(orderNo);
        } catch (RuntimeException e){
            logger.error("Error fail logger: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("주문생성/조회중 오류발생"+e.getMessage());
        }
    }

    /* 주문 1건 조회 */
    /* 주문 업데이트 */
    /* 주문 삭제 */

}
