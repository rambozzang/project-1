package com.tigerbk.project1.biz.board.ctrl;

import com.tigerbk.project1.biz.board.svc.BoardWeatherSvc;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.dto.TbBoardWeatherDto;
import com.tigerbk.project1.entity.TbBoardFollow;
import com.tigerbk.project1.entity.TbBoardWeather;
import com.tigerbk.project1.utils.Cmapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 *@Package       : com.tigerbk.project1.biz.board.ctrl
 *@name          : BoardCtrl.java
 *@date          : 2024-03-20 오후 1:05
 *@author        : Juheon Kim
 *@version       : 1.0.0
 **/
@Tag(name = "BOARD-04. Weather 정보 ", description = "Weather 를 조회한다.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardWeatherCtrl {
    private final Cmapper cmapper;
    private final BoardWeatherSvc boardWeatherSvc;

    // saveWeather
    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "01.Weather 하기", description = "\n### Weather 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather 성공", content = @Content(schema = @Schema(implementation = TbBoardWeather.class))),
    })
    @PostMapping(value = "/weather/save")
    public ResponseEntity<?> saveWeather(@Valid @RequestBody TbBoardWeatherDto dto) throws Exception {
        try {
            return ResData.SUCCESS(boardWeatherSvc.saveWeather(dto),"Follow 저장 처리되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }
    // removeWeather

    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "01.Weather remove 하기", description = "\n### Weather remove 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather remove 성공", content = @Content(schema = @Schema(implementation = boolean.class))),
    })
    @PostMapping(value = "/weather/remove")
    public ResponseEntity<?> removeWeather(@Required @RequestParam("boardId")  String  boardId) throws Exception {
        try {
            boardWeatherSvc.removeWeather(Long.parseLong(boardId));
            return ResData.SUCCESS(true ,"Weather 저장 처리되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }

    // saveWeather
    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "01.Weather 하기", description = "\n### Weather 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather 성공", content = @Content(schema = @Schema(implementation = TbBoardWeather.class))),
    })
    @PostMapping(value = "/weather/find")
    public ResponseEntity<?> findWeather(@Required @RequestParam("boardId")  String  boardId) throws Exception {
        try {
            return ResData.SUCCESS(boardWeatherSvc.findWeather(Long.parseLong(boardId)),"Weather 죄회 되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }





}
