package com.ying.hotels.lib.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ying.base.rsp.GlobalResponseCodeEnum;
import com.ying.base.rsp.GlobalErrorInfoException;
import com.ying.hotels.HotelInfo;
import com.ying.hotels.lib.SearchAirport;
import com.ying.hotels.CheapestErrorInfo;
import com.ying.util.JsonUtil;
import com.ying.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


/**
 * Created by Chloe on 2018/9/21.
 */
@Component
public class SearchAirportImpl implements SearchAirport {
    private static final Logger logger = LoggerFactory.getLogger(SearchAirportImpl.class);
    @Value("${hotels.searchairport.apiurl}")
    private String apiUrl;
    @Value("${hotels.searchairport.apikey}")
    private String apiKey;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<HotelInfo> searchAirportHotels(String location, String checkIn, String checkOut, int radius,
                                               String lang, String currency, String amenity, int numberOfResults)
            throws GlobalErrorInfoException {

        List<HotelInfo> hotels = new ArrayList<HotelInfo>();
        String reqUrl = apiUrl + "?"+ buildRequestParams(location, checkIn, checkOut, radius, lang, currency, amenity, numberOfResults);
        String response = sendGet(reqUrl);

        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(response);

            ArrayNode results = (ArrayNode) rootNode.get("results");
            for (JsonNode result : results) {
                HotelInfo info = new HotelInfo();
                info.setName(result.path("property_name").asText());
                info.setAddress(result.path("address").path("line1").asText() + " , "
                        + result.path("address").path("city").asText() + " , "
                        + result.path("address").path("region").asText() + " , "
                        + result.path("address").path("postal_code").asText() + " , "
                        + result.path("address").path("country").asText());
                info.setPrice(result.path("total_price").path("currency").asText()
                        + result.path("total_price").path("amount").asText());

                ArrayNode contacts = (ArrayNode) result.path("contacts");
                if (contacts != null) {
                    for (JsonNode contact : contacts) {
                        if (contact.path("type").asText().equalsIgnoreCase("PHONE")) {
                            info.setPhone(contact.path("detail").asText());
                        }
                    }
                }

                hotels.add(info);
            }

        } catch (JsonParseException | JsonMappingException e) {
            throw new GlobalErrorInfoException(GlobalResponseCodeEnum.PRECONDITION_FAILED);
        } catch (IOException e) {
            throw new GlobalErrorInfoException(GlobalResponseCodeEnum.SERVER_ERR);
        }

        return hotels;
    }

    private String sendGet(String reqUrl) throws GlobalErrorInfoException {
        String result = "";
        BufferedReader in = null;
        try {

            URL httpUrl = new URL(reqUrl);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(5000);

            logger.debug("request:" + reqUrl);
            connection.connect();
            int responseCode = connection.getResponseCode();

            logger.debug("responseCode:" + result);
            String line;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(
                        connection.getErrorStream()));
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                SearchAirportRspErr err = JsonUtil.readValue(result,SearchAirportRspErr.class);
                throw new GlobalErrorInfoException(new CheapestErrorInfo(responseCode, err.getMessage()));
            }
            while ((line = in.readLine()) != null) {
                result += line;
            }

            logger.debug("response:" + result);

        } catch (MalformedURLException e) {
            throw new GlobalErrorInfoException(GlobalResponseCodeEnum.BAD_REQUEST);
        } catch (IOException e) {
            throw new GlobalErrorInfoException(new CheapestErrorInfo(GlobalResponseCodeEnum.CONNECTION_EXCEPTION.getCode(),e.getMessage()));
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {

            }
        }

        return result;

    }


    private String buildRequestParams(String location, String checkIn, String checkOut, int radius,
                                      String lang, String currency, String amenity, int numberOfResults) {
        String reqParam = "apikey=" + this.apiKey;
        reqParam += "&location=" + location;
        reqParam += "&check_in=" + checkIn;
        if (!StringUtil.isEmptyString(checkOut)) reqParam += "&check_out=" + checkOut;
        if (radius > 0) reqParam += "&radius=" + radius;
        if (!StringUtil.isEmptyString(lang)) reqParam += "&lang=" + lang;
        if (!StringUtil.isEmptyString(currency)) reqParam += "&currency=" + currency;
        if (!StringUtil.isEmptyString(amenity)) reqParam += "&amenity=" + amenity;
        if (numberOfResults > 0) reqParam += "&number_of_results=" + numberOfResults;


        return reqParam;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
