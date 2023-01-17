package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.util.enums.Type;
import lombok.Data;
import org.mapstruct.Mapper;

@Mapper
@Data
public class PromoSubscriptionDTO {
    private Long id;
    private Boolean isPromocodeApplied;
    private String promocode;
    private Type type;
}
