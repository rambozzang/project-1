package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardLikeDto;
import com.tigerbk.project1.entity.TbBoardLike;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardLikeMapper extends DefaultMapper<TbBoardLikeDto, TbBoardLike> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardLike partialUpdate(TbBoardLikeDto tbBoardLikeDto, @MappingTarget TbBoardLike tbBoardLike);
}