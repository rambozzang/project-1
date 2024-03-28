package com.tigerbk.project1.biz.board.ctrl;

import com.tigerbk.project1.biz.board.svc.BoardLikeSvc;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.entity.TbBoardFollow;
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
@Tag(name = "BOARD-03. Like 정보 ", description = "게시물 Like 를 조회한다.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardLikeCtrl {
    private final Cmapper cmapper;
    private final BoardLikeSvc boardLikeSvc;


    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "01. Like 하기", description = "\n### Like 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Like 성공", content = @Content(schema = @Schema(implementation = TbBoardLike.class))),
    })
    @PostMapping(value = "/like/save")
    public ResponseEntity<?> saveLike(@Required @RequestParam("boardId") String boardId) throws Exception {
        try {
            return ResData.SUCCESS(boardLikeSvc.saveLike(Long.parseLong(boardId)),"Like 저장 처리되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }

    @Operation(summary = "01. Like 취소 하기", description = "\n### Like 취소 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Like 취소 성공", content = @Content(schema = @Schema(implementation = Boolean.class))),
    })
    @PostMapping(value = "/like/cancle")
    public ResponseEntity<?> cancleLike(@Required @RequestParam("boardId") String boardId) throws Exception {
        try {
            boardLikeSvc.cancleLike(Long.parseLong(boardId));
            return ResData.SUCCESS(true, "Like 취소 처리되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }
    //
    @Operation(summary = "01. Like 건수 조회 하기", description = "\n### Like 건수 조회 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Like 취소 성공", content = @Content(schema = @Schema(implementation = Long.class))),
    })
    @PostMapping(value = "/like/count")
    public ResponseEntity<?> countLike(@Required @RequestParam("boardId") String boardId) throws Exception {
        try {

            return ResData.SUCCESS(boardLikeSvc.countLike(), "Like 건수 조회 되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }

}
