package com.exchange.payingservice.mappers;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.entity.Payment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-25T20:02:05+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PaymentsMapperImpl implements PaymentsMapper {

    @Override
    public PaymentDTO toDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setId( payment.getId() );
        paymentDTO.setCard( payment.getCard() );
        paymentDTO.setCreateAt( payment.getCreateAt() );
        paymentDTO.setUpdateAt( payment.getUpdateAt() );
        paymentDTO.setUser_id( payment.getUser_id() );
        paymentDTO.setStatus( payment.getStatus() );
        paymentDTO.setMessage( payment.getMessage() );

        return paymentDTO;
    }

    @Override
    public Payment toEntity(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setId( paymentDTO.getId() );
        payment.setCard( paymentDTO.getCard() );
        payment.setCreateAt( paymentDTO.getCreateAt() );
        payment.setUpdateAt( paymentDTO.getUpdateAt() );
        payment.setUser_id( paymentDTO.getUser_id() );
        payment.setStatus( paymentDTO.getStatus() );
        payment.setMessage( paymentDTO.getMessage() );

        return payment;
    }

    @Override
    public void updatePaymentFromDto(PaymentDTO paymentDTO, Payment payment) {
        if ( paymentDTO == null ) {
            return;
        }

        if ( paymentDTO.getId() != null ) {
            payment.setId( paymentDTO.getId() );
        }
        if ( paymentDTO.getCard() != null ) {
            payment.setCard( paymentDTO.getCard() );
        }
        if ( paymentDTO.getCreateAt() != null ) {
            payment.setCreateAt( paymentDTO.getCreateAt() );
        }
        if ( paymentDTO.getUpdateAt() != null ) {
            payment.setUpdateAt( paymentDTO.getUpdateAt() );
        }
        if ( paymentDTO.getUser_id() != null ) {
            payment.setUser_id( paymentDTO.getUser_id() );
        }
        if ( paymentDTO.getStatus() != null ) {
            payment.setStatus( paymentDTO.getStatus() );
        }
        if ( paymentDTO.getMessage() != null ) {
            payment.setMessage( paymentDTO.getMessage() );
        }
    }
}
