package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entityDTO.JobDTO;
import com.exchangeinformat.userprofile.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер работ пользователей", description = "CRUD операции с работой пользователя")
@RestController
@RequestMapping("api/job")
public class RestJobController {

    private final JobService jobService;

    @Autowired
    public RestJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @Operation(summary = "Получение работы", description = "Метод принимает в параметры long ID работы, доступ только у админа")
    @RolesAllowed({"ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @Operation(summary = "Получение всех работ пользователей", description = "Доступ только у админа")
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<List<JobDTO>> getJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @Operation(summary = "Создание работы пользователя", description = "Метод в параметры принимает JobDTO, доступ только у админа, для создания используйте id == 0")
    @RolesAllowed({"ADMIN"})
    @PostMapping
    public ResponseEntity<HttpStatus> createJob(@RequestBody JobDTO jobDTO) {
        jobService.createJob(jobDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Обновление работы пользователя", description = "Метод в параметры принимает JobDTO, доступ только у админа, для обновления используйте id, обновляемой работы")
    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<HttpStatus> updateJob(@RequestBody JobDTO jobDTO) {
        jobService.updateJob(jobDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление работы пользователя", description = "Метод принимает в параметры long ID работы, доступ только у админа")
    @RolesAllowed({"ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
