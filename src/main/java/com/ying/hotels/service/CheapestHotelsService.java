package com.ying.hotels.service;

import com.ying.base.rsp.GlobalErrorInfoException;
import com.ying.base.rsp.RspData;
import com.ying.hotels.HotelInfo;
import com.ying.hotels.lib.SearchAirport;

import java.util.List;

/**
 * Created by Chloe on 2018/9/21.
 */
public interface CheapestHotelsService {

    void setSearchAirport(SearchAirport searchAirport);

    List<HotelInfo> getCheapestHotels(String location, String checkIn, String checkOut, int radius, String lang,
                                               String currency, String amenity, int resultNum) throws GlobalErrorInfoException;
}
