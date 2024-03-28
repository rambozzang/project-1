package com.tigerbk.project1.mapper;

import com.tigerbk.project1.dto.TbBoardWeatherDto;
import com.tigerbk.project1.entity.TbBoardWeather;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TbBoardWeatherMapper extends DefaultMapper<TbBoardWeatherDto, TbBoardWeather> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TbBoardWeather partialUpdate(TbBoardWeatherDto tbBoardWeatherDto, @MappingTarget TbBoardWeather tbBoardWeather);
}