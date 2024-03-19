package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardMasterDto;
import com.tigerbk.project1.entity.TbBoardMaster;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardMasterMapper extends DefaultMapper<TbBoardMasterDto, TbBoardMaster> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardMaster partialUpdate(TbBoardMasterDto tbBoardMasterDto, @MappingTarget TbBoardMaster tbBoardMaster);
}