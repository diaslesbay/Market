package com.example.test.mapper;

import com.example.test.dto.ProductDto;
//import com.example.test.mapper.impl.Mappable;
import com.example.test.model.Product;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "Spring")
//public interface ProductMapper extends Mappable<Product, ProductDto> {
public interface ProductMapper {
    ProductDto toDto(Product product);
}
