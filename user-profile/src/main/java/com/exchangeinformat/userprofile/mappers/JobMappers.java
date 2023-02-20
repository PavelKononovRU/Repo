package com.exchangeinformat.userprofile.mappers;

import com.exchangeinformat.userprofile.entity.Job;
import com.exchangeinformat.userprofile.entityDTO.JobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMappers {

    JobMappers INSTANCE = Mappers.getMapper(JobMappers.class);

    JobDTO jobToDTO(Job job);

    Job jobDTOToEntity(JobDTO jobDTO);

    List<JobDTO> jobsToDTOs(List<Job> jobs);

}
