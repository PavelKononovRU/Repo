package com.exchangeinformat.userprofile.mappers;

import com.exchangeinformat.userprofile.entity.Product;
import com.exchangeinformat.userprofile.entityDTO.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMappers {

    ProductMappers INSTANCE = Mappers.getMapper(ProductMappers.class);

    ProductDTO productToDTO(Product job);

    Product productDTOToEntity(ProductDTO jobDTO);

    List<ProductDTO> productsToDTOs(List<Product> jobs);
}
