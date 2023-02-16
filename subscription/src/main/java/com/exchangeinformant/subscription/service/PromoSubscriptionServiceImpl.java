package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.exception.ResourceNotFoundException;
import com.exchangeinformant.subscription.mappers.PromoSubscriptionMapper;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromoSubscriptionServiceImpl implements PromoSubscriptionService {

    private final PromoSubscriptionRepository promoSubscriptionRepository;

    @Autowired
    public PromoSubscriptionServiceImpl(PromoSubscriptionRepository promoSubscriptionRepository) {
        this.promoSubscriptionRepository = promoSubscriptionRepository;
    }

    @Override
    public List<PromoSubscriptionDTO> getAllPromoSubscription() {
        return promoSubscriptionRepository.findAll()
                .stream()
                .map(PromoSubscriptionMapper.INSTANCE::promoSubscriptionToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createPromoSubscription(PromoSubscriptionDTO promoSubscriptionDTO) {
        promoSubscriptionRepository.save(PromoSubscriptionMapper
                .INSTANCE.promoSubscriptionDTOToModel(promoSubscriptionDTO));
    }

    @Override
    public PromoSubscriptionDTO getPromoSubscription(Long id) {
        return PromoSubscriptionMapper
                .INSTANCE
                .promoSubscriptionToDTO(promoSubscriptionRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Промоподписка с id '" + id + "' не найдена")));
    }

    @Override
    @Transactional
    public void updatePromoSubscription(PromoSubscriptionDTO promoSubscriptionDTO) {
        promoSubscriptionRepository.save(PromoSubscriptionMapper
                .INSTANCE
                .promoSubscriptionDTOToModel(promoSubscriptionDTO));
    }

    @Override
    @Transactional
    public void deletePromoSubscription(Long id) {
        try {
            promoSubscriptionRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Не возможно удалить Промоподписку с id '" + id + "': " + e.getMessage());
        }
    }
}
