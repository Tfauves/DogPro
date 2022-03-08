package com.careerdevs.conqureTheWalk.payload.response.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BreedResponse {
    private List<BreedInfo> breedInfos;

    public BreedResponse() {}

    public BreedResponse(List<BreedInfo> breedInfos) {
        this.breedInfos = new ArrayList<>();
    }

//    public List<BreedInfo> getBreedInfoList() {
//        return breedInfoList;
//    }
//
//    public void setBreedInfoList(List<BreedInfo> breedInfoList) {
//        this.breedInfoList = breedInfoList;
//    }

    public List<BreedInfo> getBreedInfos() {
        return breedInfos;
    }

    public void setBreedInfos(List<BreedInfo> breedInfos) {
        this.breedInfos = breedInfos;
    }
}
