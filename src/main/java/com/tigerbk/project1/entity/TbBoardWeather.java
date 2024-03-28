package com.tigerbk.project1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_BOARD_WEATHER")
public class TbBoardWeather {
    @Id
    @Column(name = "BOARD_ID", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "LAT", length = 100)
    private String lat;

    @Size(max = 100)
    @Column(name = "LON", length = 100)
    private String lon;

    @Size(max = 150)
    @Column(name = "LOCATION", length = 150)
    private String location;

    @Size(max = 150)
    @Column(name = "WEATHER_INFO", length = 150)
    private String weatherInfo;

    @Size(max = 250)
    @Column(name = "FILE_PATH", length = 250)
    private String filePath;

    @Size(max = 100)
    @Column(name = "CURRENT_TEMP", length = 100)
    private String currentTemp;

    @Size(max = 100)
    @Column(name = "FOOLS_TEMP", length = 100)
    private String foolsTemp;

    @Column(name = "CRT_DTM")
    private LocalDateTime crtDtm;

    @Size(max = 100)
    @Column(name = "CRT_CUST_ID", length = 100)
    private String crtCustId;

    @Size(max = 10)
    @Column(name = "TEMP_MIN", length = 10)
    private String tempMin;

    @Size(max = 10)
    @Column(name = "TEMP_MAX", length = 10)
    private String tempMax;

    @Size(max = 10)
    @Column(name = "HUMIDITY", length = 10)
    private String humidity;

    @Size(max = 10)
    @Column(name = "SPEED", length = 10)
    private String speed;

    @Size(max = 100)
    @Column(name = "COUNTRY", length = 100)
    private String country;

    @Size(max = 100)
    @Column(name = "CITY", length = 100)
    private String city;

}