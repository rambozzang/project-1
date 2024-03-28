package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardFollowIdDto;
import com.tigerbk.project1.entity.TbBoardFollowId;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardFollowIdMapper extends DefaultMapper<TbBoardFollowIdDto, TbBoardFollowId> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardFollowId partialUpdate(TbBoardFollowIdDto tbBoardFollowIdDto, @MappingTarget TbBoardFollowId tbBoardFollowId);
}