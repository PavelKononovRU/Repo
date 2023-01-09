package com.exchange.payingservice.mappers;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.entity.Payment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {

    PaymentsMapper INSTANCE = Mappers.getMapper(PaymentsMapper.class);

    PaymentDTO toDTO(Payment payment);
    Payment toEntity(PaymentDTO paymentDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentFromDto(PaymentDTO paymentDTO, @MappingTarget Payment payment);

}
