package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.entity.TbCustMaster;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbCustMasterMapper extends DefaultMapper<TbCustMasterDto, TbCustMaster> {

    TbCustMasterMapper INSTANCE = Mappers.getMapper(TbCustMasterMapper.class);

//    TbUserMain toEntity(TbUserMainDto tbUserMainDto);
//
//    TbUserMainDto toDto(TbUserMain tbUserMain);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    TbUserMain partialUpdate(TbUserMainDto tbUserMainDto, @MappingTarget TbUserMain);
}