package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJobs();

    void createJob(JobDTO jobDTO);

    JobDTO getJobById(Long id);

    void updateJob(JobDTO jobDTO);

    void deleteJob(Long id);
}
