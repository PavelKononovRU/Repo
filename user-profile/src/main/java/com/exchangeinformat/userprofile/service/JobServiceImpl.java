package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Job;
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
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
