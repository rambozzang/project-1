package com.tigerbk.project1.repo;

import com.tigerbk.project1.entity.TbBoardWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbBoardWeatherRepository extends JpaRepository<TbBoardWeather, Long> {
}