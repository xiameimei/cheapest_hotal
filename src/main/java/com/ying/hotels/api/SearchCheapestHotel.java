package com.ying.hotels.api;

import com.ying.base.rsp.GlobalResponseCodeEnum;
import com.ying.base.rsp.GlobalErrorInfoException;
import com.ying.base.rsp.RspData;
import com.ying.hotels.CheapestErrorInfo;
import com.ying.hotels.HotelInfo;
import com.ying.hotels.service.CheapestHotelsService;
import com.ying.util.DateUtil;
import com.ying.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Chloe on 2018/9/21.
 */
@RestController
@RequestMapping("/api/hotels")
public class SearchCheapestHotel {

    private int radius = 5;
    private String lang = "EN";
    private String currency = "USD";
    private String amenity = "";
    private int resultsNum = 3;

    private CheapestHotelsService cheapestHotelsService;


    @Autowired
    public void setCheapestHotelsService(CheapestHotelsService cheapestHotelsService) {
        this.cheapestHotelsService = cheapestHotelsService;
    }

    @RequestMapping(value = "/search_cheapest", method = RequestMethod.GET)
    public RspData<List<HotelInfo>> getCheapestHotels(@RequestParam("location") String location,
                                                      @RequestParam(name="check_in", required=false) String checkIn)
            throws GlobalErrorInfoException{

        if (StringUtil.isEmptyString(checkIn)) {
            throw new GlobalErrorInfoException(new CheapestErrorInfo(GlobalResponseCodeEnum.BAD_REQUEST.getCode(),
                    "check_in data is required"));
        }

        String checkOut = DateUtil.addDay(checkIn,1);

        RspData<List<HotelInfo>>  hotelInfos = new RspData<List<HotelInfo>>(
                cheapestHotelsService.getCheapestHotels(location,checkIn,checkOut,radius,
                lang, currency,amenity,resultsNum));

        return hotelInfos;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public RspData error(){
        return new RspData(GlobalResponseCodeEnum.SERVER_ERR);
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public void setResultsNum(int resultsNum) {
        this.resultsNum = resultsNum;
    }
}
