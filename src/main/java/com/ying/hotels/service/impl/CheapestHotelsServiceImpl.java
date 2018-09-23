package com.ying.hotels.service.impl;

import com.ying.base.rsp.GlobalErrorInfoException;
import com.ying.base.rsp.RspData;
import com.ying.hotels.HotelInfo;
import com.ying.hotels.lib.SearchAirport;
import com.ying.hotels.service.CheapestHotelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Chloe on 2018/9/21.
 */
@Component
public class CheapestHotelsServiceImpl implements CheapestHotelsService {

    private SearchAirport searchAirport;

    @Override
    public List<HotelInfo>  getCheapestHotels(String location, String checkIn, String checkOut, int radius, String lang,
                                              String currency, String amenity, int resultNum) throws GlobalErrorInfoException {
        return searchAirport.searchAirportHotels(location,checkIn,checkOut,radius,lang,currency,amenity,resultNum);
    }

    public SearchAirport getSearchAirport() {
        return searchAirport;
    }

    @Override
    @Autowired
    public void setSearchAirport(SearchAirport searchAirport) {
        this.searchAirport = searchAirport;
    }
}
