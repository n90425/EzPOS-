package com.finalproject.possystem.order.dto.response;

import com.finalproject.possystem.order.entity.OrderSequence;
import java.util.stream.Collectors;
import lombok.Getter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
public class OrderSequenceResponseDto {
    Date OpenDate;
    Boolean isOpen;
    Integer totalOrders;
    Integer totalSales;
    List<Integer> weeklySales;
    private List<String> Dates;
    public OrderSequenceResponseDto(Date OpenDate, Boolean isOpen, Integer totalOrders, Integer totalSales){
        this.OpenDate = OpenDate;
        this.isOpen = isOpen;
        this.totalOrders = totalOrders;
        this.totalSales = totalSales;
    }

    public static OrderSequenceResponseDto from(OrderSequence orderSequence) {
        return new OrderSequenceResponseDto(
                orderSequence.getOpenDate(),
                orderSequence.getIsOpen(),
                orderSequence.getTotalOrders(),
                orderSequence.getTotalSales());
    }

    public void updateWeeklySales(List<OrderSequence> sequences) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.totalOrders = sequences.stream()
                .mapToInt(OrderSequence::getTotalOrders).sum();
        this.totalSales = sequences.stream()
                .mapToInt(OrderSequence::getTotalSales).sum();
        this.weeklySales = sequences.stream()
                .map(OrderSequence::getTotalSales)
                .toList();
        this.Dates = sequences.stream()
                .map(orderSequence -> dateFormat.format(orderSequence.getOpenDate())) // Date → String 변환
                .collect(Collectors.toList());
    }
}
