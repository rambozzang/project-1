package com.tigerbk.project1.biz.board.ctrl;

import com.tigerbk.project1.biz.board.svc.BoardSvc;
import com.tigerbk.project1.biz.board.vo.BoardCvo;
import com.tigerbk.project1.biz.board.vo.BoardSvo;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.dto.TbBoardMasterDto;
import com.tigerbk.project1.utils.Cmapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
*
*@Package       : com.tigerbk.project1.biz.board.ctrl
*@name          : BoardCtrl.java
*@date          : 2024-03-20 오후 1:05
*@author        : Juheon Kim
*@version       : 1.0.0
**/
@Tag(name = "BOARD-01.통합게시판 조회", description = "통합게시판 게시물 정보를 조회한다.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardCtrl {

    private final BoardSvc boardSvc;

    private final Cmapper cmapper;

    /**
    *
    * @name     : BoardCtrl.searchBoardList
    * @author   : JuHeon Kim
    * @param    : 
    * @return   :
    **/
    @Operation(summary = "01.통합게시판 병렬형 전체 조회", description = "\n### 통합게시판 조회 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통합게시판 조회 성공", content = @Content(schema = @Schema(implementation = BoardCvo.BoardInVo.class))),
    })
    @PostMapping(value = "/board/searchParallel")
    public ResponseEntity<?> searchParallel(@RequestBody BoardCvo.BoardInVo inCvo) throws Exception {
        try {
            BoardSvo.BoardInVo inSvo = cmapper.run(inCvo, BoardSvo.BoardInVo.class);
            return ResData.SUCCESS(boardSvc.findAllPallelList(inSvo),"통합게시판 정상 조회하였습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }

    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "02.통합게시판 원글 조회", description = "\n### 통합게시판 원글 조회 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통합게시판 조회 성공", content = @Content(schema = @Schema(implementation = BoardCvo.BoardInVo.class))),
    })
    @PostMapping(value = "/board/searchOriginById")
    public ResponseEntity<?> searchOriginById(@RequestBody BoardCvo.BoardInVo inCvo) throws Exception {
        try {
            BoardSvo.BoardInVo inSvo = cmapper.run(inCvo, BoardSvo.BoardInVo.class);
            return ResData.SUCCESS(boardSvc.findOriginById(inSvo),"통합게시판 정상 조회하였습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }

    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "03.통합게시판 댓글 조회", description = "\n### 통합게시판 원글 조회 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통합게시판 조회 성공", content = @Content(schema = @Schema(implementation = BoardCvo.BoardInVo.class))),
    })
    @PostMapping(value = "/board/searchCommentById")
    public ResponseEntity<?> searchCommentById(@RequestBody BoardCvo.BoardInVo inCvo) throws Exception {
        try {
            BoardSvo.BoardInVo inSvo = cmapper.run(inCvo, BoardSvo.BoardInVo.class);
            return ResData.SUCCESS(boardSvc.findCommentById(inSvo),"통합게시판 정상 조회하였습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }

    /**
    *
    * @name     : BoardCtrl.searchHierichilist
    * @author   : JuHeon Kim
    * @param    :
    * @return   :
    **/
//    @Operation(summary = "02.통합게시판 계층형 조회", description = "\n### 통합게시판 조회 서비스 API")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "통합게시판 조회 성공", content = @Content(schema = @Schema(implementation = BoardCvo.BoardInVo.class))),
//    })
//    @PostMapping(value = "/board/searchHierichilist")
//    public ResponseEntity<?> searchHierichilist(@RequestBody BoardCvo.BoardInVo inCvo) throws Exception {
//        try {
//            BoardSvo.BoardInVo inSvo = cmapper.run(inCvo, BoardSvo.BoardInVo.class);
//            return ResData.SUCCESS(boardSvc.findAllHierachiList(inSvo),"통합게시판 정상 조회하였습니다.");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//            return ResData.FAIL(e.getMessage());
//        }
//    }
}
