package com.tigerbk.project1.mapper;

import com.tigerbk.project1.entity.TbBoardSingo;
import com.tigerbk.project1.dto.TbBoardSingoDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardSingoMapper extends DefaultMapper<TbBoardSingoDto, TbBoardSingo> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardSingo partialUpdate(TbBoardSingoDto tbBoardSingoDto, @MappingTarget TbBoardSingo tbBoardSingo);
}