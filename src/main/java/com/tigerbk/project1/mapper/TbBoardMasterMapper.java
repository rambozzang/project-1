package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardMasterDto;
import com.tigerbk.project1.entity.TbBoardMaster;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TbBoardMasterMapper extends DefaultMapper<TbBoardMasterDto, TbBoardMaster> {

    TbBoardMasterMapper INSTANCE = Mappers.getMapper(TbBoardMasterMapper.class);
    TbBoardMaster partialUpdate(TbBoardMasterDto tbBoardMasterDto, @MappingTarget TbBoardMaster tbBoardMaster);
}