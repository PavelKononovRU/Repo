package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    void createJob(Job job);

    Job getJobById(Long id);

    void updateJob(Job job);

    void deleteJob(Long id);
}
