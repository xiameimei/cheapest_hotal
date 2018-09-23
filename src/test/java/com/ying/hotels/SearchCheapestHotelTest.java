package com.ying.hotels;

import com.ying.hotels.lib.impl.SearchAirportImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by Chloe on 2018/9/23.
 */
@Profile("test")
@Configuration
public class SearchCheapestHotelTest {
    @Bean
    @Primary
    public SearchAirportImpl searchAirportImpl() {
        return Mockito.mock(SearchAirportImpl.class);
    }
}
