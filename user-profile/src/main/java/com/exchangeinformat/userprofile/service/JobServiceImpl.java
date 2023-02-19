package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.JobDTO;
import com.exchangeinformat.userprofile.mappers.JobMappers;
import com.exchangeinformat.userprofile.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> getAllJobs() {
        return JobMappers.INSTANCE.jobsToDTOs(jobRepository.findAll());
    }

    @Override
    @Transactional
    public void createJob(JobDTO jobDTO) {
        jobRepository.save(JobMappers.INSTANCE.jobDTOToEntity(jobDTO));
    }

    @Override
    public JobDTO getJobById(Long id) {
        return JobMappers.INSTANCE.jobToDTO(jobRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void updateJob(JobDTO jobDTO) {
        jobRepository.save(JobMappers.INSTANCE.jobDTOToEntity(jobDTO));
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
