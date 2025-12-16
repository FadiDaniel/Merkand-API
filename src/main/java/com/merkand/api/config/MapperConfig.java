package com.merkand.api.config;

import com.merkand.api.dto.OrderDto;
import com.merkand.api.entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Mapping config Oreder -> OrderDto
        mapper.addMappings(new PropertyMap<Order, OrderDto>() {
            @Override
            protected void configure() {
                map().setOrderItemListCount(source.getOrderItemList().size());
                map(source.getSupplier().getName()).setSupplierName(null);
                map(source.getSupplier().getId()).setSupplierId(null);
            }
        });

        return new ModelMapper();
    }

}










