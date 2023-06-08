package org.TastyTiffin.model.response;

import java.util.List;
import java.util.Optional;

public class GetOrderHistoryResponse {
  Optional<List<GetOrderResponse>> orders;

    public GetOrderHistoryResponse() {
    }

    public GetOrderHistoryResponse(Optional<List<GetOrderResponse>> orders) {
        this.orders = orders;
    }

    public Optional<List<GetOrderResponse>> getOrders() {
        return orders;
    }

    public void setOrders(Optional<List<GetOrderResponse>> orders) {
        this.orders = orders;
    }
}
