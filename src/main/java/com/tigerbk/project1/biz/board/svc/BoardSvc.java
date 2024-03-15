package com.tigerbk.project1.biz.board.svc;

import com.tigerbk.project1.biz.board.vo.BoardSvo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardSvc {


    @Transactional
    public BoardSvo.BoardOutVo findBoardList(@Valid BoardSvo.BoardInVo inVo) throws Exception {

        BoardSvo.BoardOutVo outVo = new BoardSvo.BoardOutVo();
        return outVo;
    }

}
