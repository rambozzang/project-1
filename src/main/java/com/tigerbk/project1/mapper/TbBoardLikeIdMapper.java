package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardLikeIdDto;
import com.tigerbk.project1.entity.TbBoardLikeId;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardLikeIdMapper extends DefaultMapper<TbBoardLikeIdDto, TbBoardLikeId> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardLikeId partialUpdate(TbBoardLikeIdDto tbBoardLikeIdDto, @MappingTarget TbBoardLikeId tbBoardLikeId);
}