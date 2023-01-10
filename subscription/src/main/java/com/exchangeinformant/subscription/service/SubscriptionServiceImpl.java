package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.exception.ResourceNotFoundException;
import com.exchangeinformant.subscription.mappers.SubscriptionMapper;
import com.exchangeinformant.subscription.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    @Transactional
    public void createSubscription(SubscriptionDTO subscriptionDTO) {
        subscriptionRepository.save(SubscriptionMapper
                .INSTANCE
                .subscriptionDTOToModel(subscriptionDTO));
    }

    @Override
    public SubscriptionDTO getSubscription(Long id) {
        return SubscriptionMapper
                .INSTANCE
                .subscriptionToDTO(subscriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Подписка с id '" + id + "' не найдена")));
    }

    @Override
    public List<SubscriptionDTO> getAllSubscription() {
        return subscriptionRepository.findAll()
                .stream()
                .map(SubscriptionMapper.INSTANCE::subscriptionToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Page<SubscriptionDTO> getSubscriptionsWithPagination(String status, int offset, int limit, Pageable pageable) {
        List<SubscriptionDTO> subscriptionDTOList = subscriptionRepository.findAll()
                .stream().filter(n -> String.valueOf(n.getStatus()).equals(status))
                .map(SubscriptionMapper.INSTANCE::subscriptionToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(subscriptionDTOList.subList(offset, limit), pageable, subscriptionDTOList.size());
    }

    @Override
    @Transactional
    public void updateSubscription(SubscriptionDTO subscriptionDTO) {
        subscriptionRepository.save(SubscriptionMapper
                .INSTANCE
                .subscriptionDTOToModel(subscriptionDTO));
    }

    @Override
    @Transactional
    public void deleteSubscription(Long id) {
        try {
            subscriptionRepository.deleteById(id);
        } catch (Exception e){
            throw new ResourceNotFoundException("Невозможно удалить подписку с id '" + id + "': " + e.getMessage());
        }
    }
}
