package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.Job;
import com.exchangeinformat.userprofile.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job")
public class RestJobController {

    private final JobService jobService;

    @Autowired
    public RestJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/findOne")
    public ResponseEntity<Job> getJob(Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping
    public ResponseEntity<List<Job>> getJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateJob(@RequestBody Job job) {
        jobService.updateJob(job);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteJob(Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
