package com.tigerbk.project1.biz.board.ctrl;

import com.tigerbk.project1.biz.board.svc.BoardSvc;
import com.tigerbk.project1.biz.board.vo.BoardCvo;
import com.tigerbk.project1.biz.board.vo.BoardSvo;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.dto.TbBoardMasterDto;
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

@Tag(name = "BOARD-01.통합게시판 조회", description = "통합게시판 게시물 정보를 조회한다.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardCtrl {

    private final BoardSvc boardSvc;

    @Operation(summary = "01.통합게시판 조회", description = "\n### 통합게시판 조회 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통합게시판 조회 성공", content = @Content(schema = @Schema(implementation = BoardCvo.BoardInVo.class))),
    })
    @PostMapping(value = "/board/searchboardlist")
    public ResponseEntity<?> searchBoardList(@RequestBody BoardCvo.BoardInVo inCvo) throws Exception {
        try {
            BoardSvo.BoardInVo inSvo = new BoardSvo.BoardInVo();
            inSvo.setPageNum(inCvo.getPageNum());
            inSvo.setPageSize(inCvo.getPageSize());
            inSvo.setId(inCvo.getId());
            List<BoardSvo.BoardOutVo> boardOutVo = boardSvc.findAllBoardList(inSvo);
            return ResData.SUCCESS(boardSvc.findAllBoardList(inSvo),"통합게시판 조회 정상 조회하였습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }
}
