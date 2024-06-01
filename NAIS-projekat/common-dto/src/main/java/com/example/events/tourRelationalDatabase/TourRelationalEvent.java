package com.example.events.order;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

import com.example.dto.TourGraphDatabaseDto;
import com.example.events.Event;

@Data
public class TourRelationalEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private TourGraphDatabaseDto purchaseOrder;
    private TourRelationalStatus orderStatus;

    public TourRelationalEvent() {
    }

    public TourRelationalEvent(TourGraphDatabaseDto purchaseOrder, TourRelationalStatus orderStatus) {
        this.purchaseOrder = purchaseOrder;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public TourGraphDatabaseDto getPurchaseOrder() {
        return purchaseOrder;
    }

    public TourRelationalStatus getOrderStatus() {
        return orderStatus;
    }
}
