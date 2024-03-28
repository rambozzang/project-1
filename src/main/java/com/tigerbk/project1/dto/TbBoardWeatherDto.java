package com.tigerbk.project1.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbBoardWeather}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TbBoardWeatherDto implements Serializable {
    Long id;
    @Size(max = 100)
    String lat;
    @Size(max = 100)
    String lon;
    @Size(max = 150)
    String location;
    @Size(max = 150)
    String weatherInfo;
    @Size(max = 250)
    String filePath;
    @Size(max = 100)
    String currentTemp;
    @Size(max = 100)
    String foolsTemp;
    LocalDateTime crtDtm;
    @Size(max = 100)
    String crtCustId;
    @Size(max = 10)
    String tempMin;
    @Size(max = 10)
    String tempMax;
    @Size(max = 10)
    String humidity;
    @Size(max = 10)
    String speed;
    @Size(max = 100)
    String country;
    @Size(max = 100)
    String city;
}