package com.exchangeinformant.subscription.mappers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.model.PromoSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromoSubscriptionMapper {
    PromoSubscriptionMapper INSTANCE = Mappers.getMapper(PromoSubscriptionMapper.class);
    PromoSubscriptionDTO promoSubscriptionToDTO(PromoSubscription promoSubscription);
    PromoSubscription promoSubscriptionDTOToModel(PromoSubscriptionDTO promoSubscriptionDTO);
}
