package com.org.ukrposhta.mapper;

import com.org.ukrposhta.model.Project;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserDtoMapper.class)
public interface ProjectDtoMapper {
    Project toModel(com.org.ukrposhta.dto.Project projectDto);
    com.org.ukrposhta.dto.Project toDto(Project projectModel);
    List<Project> toModelList(List<com.org.ukrposhta.dto.Project> projectDtoList);
    List<com.org.ukrposhta.dto.Project> toDtoList(List<Project> projectModelList);
}
