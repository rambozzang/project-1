package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardFollowDto;
import com.tigerbk.project1.entity.TbBoardFollow;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardFollowMapper extends DefaultMapper<TbBoardFollowDto, TbBoardFollow> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardFollow partialUpdate(TbBoardFollowDto tbBoardFollowDto, @MappingTarget TbBoardFollow tbBoardFollow);
}