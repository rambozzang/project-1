package com.tigerbk.project1.biz.board.ctrl;

import com.tigerbk.project1.biz.board.svc.BoardSingoSvc;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.entity.TbBoardLike;
import com.tigerbk.project1.utils.Cmapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *@Package       : com.tigerbk.project1.biz.board.ctrl
 *@name          : BoardCtrl.java
 *@date          : 2024-03-20 오후 1:05
 *@author        : Juheon Kim
 *@version       : 1.0.0
 **/
@Tag(name = "BOARD-05.Singo 정보 ", description = "게시물 Singo 를 조회한다.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardSingoCtrl {
    private final Cmapper cmapper;
    private final BoardSingoSvc boardSingoSvc;

    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "01. Singo 하기", description = "\n### Singo 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Singo 성공", content = @Content(schema = @Schema(implementation = TbBoardLike.class))),
    })
    @PostMapping(value = "/singo/save")
    public ResponseEntity<?> saveLike(@Required @RequestParam("boardId") String boardId) throws Exception {
        try {
            return ResData.SUCCESS(boardSingoSvc.saveSingo(Long.parseLong(boardId)),"Like 저장 처리되었습니다.");
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
    @Operation(summary = "01. Singo 취소 하기", description = "\n### Singo 취소 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Singo 성공", content = @Content(schema = @Schema(implementation = TbBoardLike.class))),
    })
    @PostMapping(value = "/singo/cancle")
    public ResponseEntity<?> cancleSingo(@Required @RequestParam("boardId") String boardId) throws Exception {
        try {
            boardSingoSvc.cancleSingo(Long.parseLong(boardId));
            return ResData.SUCCESS(true,"Like 저장 처리되었습니다.");
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
    @Operation(summary = "01. 게시물의 Singo 건수 하기", description = "\n### Singo 건수 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Singo 성공", content = @Content(schema = @Schema(implementation = Long.class))),
    })
    @PostMapping(value = "/singo/count")
    public ResponseEntity<?> countSingo(@Required @RequestParam("boardId") String boardId) throws Exception {
        try {

            return ResData.SUCCESS( boardSingoSvc.countSingobyBoardId(Long.parseLong(boardId)),"Like 저장 처리되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }


}
