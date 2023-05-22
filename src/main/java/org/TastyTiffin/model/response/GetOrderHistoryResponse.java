package org.TastyTiffin.model.response;

import java.util.List;
import java.util.Optional;

public class GetOrderHistoryResponse {
  Optional<List<String>> orderIds;

    public GetOrderHistoryResponse() {
    }

    public GetOrderHistoryResponse(Optional<List<String>> orderIds) {
        this.orderIds = orderIds;
    }

    public Optional<List<String>> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(Optional<List<String>> orderIds) {
        this.orderIds = orderIds;
    }
}
