package org.TastyTiffin.model.response;

import java.util.List;
import java.util.Optional;

public class GetAllProviderResponse {
    Optional<List<ProviderResponse>> providerResponseList;

    public GetAllProviderResponse() {
        providerResponseList=Optional.empty();
    }

    public GetAllProviderResponse(Optional<List<ProviderResponse>> providerResponseList) {
        this.providerResponseList = providerResponseList;
    }

    public Optional<List<ProviderResponse>> getProviderResponseList() {
        return providerResponseList;
    }

    public void setProviderResponseList(Optional<List<ProviderResponse>> providerResponseList) {
        this.providerResponseList = providerResponseList;
    }
}
