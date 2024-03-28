package com.tigerbk.project1.biz.board.ctrl;


import com.tigerbk.project1.biz.board.svc.BoardFollowSvc;
import com.tigerbk.project1.biz.board.vo.BoardCvo;
import com.tigerbk.project1.biz.board.vo.BoardSvo;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.entity.TbBoardFollow;
import com.tigerbk.project1.utils.Cmapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.MediaType;
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
@Tag(name = "BOARD-02. Follow 정보 ", description = "게시물 Follow를 조회한다.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardFollowCtrl {
    private final Cmapper cmapper;
    private final BoardFollowSvc boardFollowSvc;

    /**
     *
     * @name     : BoardCtrl.searchBoardList
     * @author   : JuHeon Kim
     * @param    :
     * @return   :
     **/
    @Operation(summary = "01.Follow 하기", description = "\n### Follow 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Follow 성공", content = @Content(schema = @Schema(implementation = TbBoardFollow.class))),
    })
    @PostMapping(value = "/follow/save")
    public ResponseEntity<?> saveFollow(@Required @RequestParam("custId") String custId) throws Exception {
        try {
            return ResData.SUCCESS(boardFollowSvc.saveFollow(custId),"Follow 저장 처리되었습니다.");
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
    @Operation(summary = "01.Follow 취소 하기", description = "\n### Follow 취소서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Follow 취소 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping(value = "/follow/cancle")
    public ResponseEntity<?> cancleFollow(@Required @RequestParam("custId") String custId) throws Exception {
        try {
            boardFollowSvc.cancleFollow(custId);
            return ResData.SUCCESS( true,"Follow 취소 처리되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }
    // findFollow
    @Operation(summary = "01.Follow 리스트 조회 하기", description = "\n### Follow 리스트 조회서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Follow 취소 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping(value = "/follow/find")
    public ResponseEntity<?> findFollow() throws Exception {
        try {
            return ResData.SUCCESS( boardFollowSvc.findFollow() ,"Follow 취소 처리되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }
    //
    @Operation(summary = "01.Follow Count 조회", description = "\n### Follow Count 서비스 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Follow Count 조회 성공", content = @Content(schema = @Schema(implementation = long.class))),
    })
    @PostMapping(value = "/follow/count")
    public ResponseEntity<?> countFollow() throws Exception {
        try {
            return ResData.SUCCESS( boardFollowSvc.countFollow() ,"Follow 카우트 조회 되었습니다.");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResData.FAIL(e.getMessage());
        }
    }

}
