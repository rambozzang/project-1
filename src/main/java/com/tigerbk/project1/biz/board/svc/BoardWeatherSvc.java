package com.tigerbk.project1.biz.board.svc;

import com.tigerbk.project1.biz.auth.UserAuthSvc;
import com.tigerbk.project1.dto.TbBoardWeatherDto;
import com.tigerbk.project1.entity.TbBoardWeather;
import com.tigerbk.project1.exception.DefaultException;
import com.tigerbk.project1.repo.TbBoardMasterRepository;
import com.tigerbk.project1.repo.TbBoardWeatherRepository;
import com.tigerbk.project1.utils.Cmapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardWeatherSvc {
    private final ModelMapper modelMapper;
    private final Cmapper cmapper;

    private final UserAuthSvc userAuthSvc;
    private final TbBoardMasterRepository tbBoardMasterRepository;
    private final TbBoardWeatherRepository tbBoardWeatherRepository;


    // 1 저장 하기
    public TbBoardWeather saveWeather(@Valid TbBoardWeatherDto tbBoardWeatherDto) throws Exception {

        tbBoardWeatherDto.setCrtCustId(userAuthSvc.getSessionUser().getCustId());

        TbBoardWeather tbBoardWeather = modelMapper.map(tbBoardWeatherDto, TbBoardWeather.class);
        return tbBoardWeatherRepository.save(tbBoardWeather);
    }

    // 2 삭제 취소
    public void removeWeather(@Valid Long boardId) throws Exception {
        tbBoardWeatherRepository.deleteById(boardId);
    }


    // 4 Weather 조회
    public TbBoardWeather findWeather(Long boardId) throws Exception {
        return tbBoardWeatherRepository.findById(boardId).orElseThrow(
                () -> new DefaultException("팔로우 정보가 없습니다.")
        );
    }
}
