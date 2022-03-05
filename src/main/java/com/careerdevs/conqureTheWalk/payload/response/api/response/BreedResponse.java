package com.careerdevs.conqureTheWalk.payload.response.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BreedResponse {
    private List<BreedInfo> breedInfoList;

    public BreedResponse() {}

    public BreedResponse(List<BreedInfo> breedInfoList) {
        this.breedInfoList = breedInfoList;
    }

    public List<BreedInfo> getBreedInfoList() {
        return breedInfoList;
    }

    public void setBreedInfoList(List<BreedInfo> breedInfoList) {
        this.breedInfoList = breedInfoList;
    }
}
