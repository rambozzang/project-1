package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardMasterDto;
import com.tigerbk.project1.entity.TbBoardMaster;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardMasterMapper extends DefaultMapper<TbBoardMasterDto, TbBoardMaster> {

    TbBoardMasterMapper INSTANCE = Mappers.getMapper(TbBoardMasterMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardMaster partialUpdate(TbBoardMasterDto tbBoardMasterDto, @MappingTarget TbBoardMaster tbBoardMaster);
}