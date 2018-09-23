package com.ying.hotels.lib;

import com.ying.base.rsp.GlobalErrorInfoException;
import com.ying.hotels.HotelInfo;

import java.util.List;

/**
 * Created by Chloe on 2018/9/21.
 */
public interface SearchAirport {

    List<HotelInfo> searchAirportHotels (String location, String checkIn, String checkOut, int radius, String lang,
                                                  String currency, String amenity, int numberOfResults) throws GlobalErrorInfoException;
}
