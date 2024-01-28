package com.ssafy.yourpilling.pill_heeju.controller;

import com.ssafy.yourpilling.pill_heeju.controller.mapper.HPillControllerMapper;
import com.ssafy.yourpilling.pill_heeju.model.dao.entity.PillDetail;
import com.ssafy.yourpilling.pill_heeju.model.service.PillService;
import com.ssafy.yourpilling.pill_heeju.model.service.vo.response.ResponsePillSearchListVo.ResponsePillSearchListData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pill")
public class HPillController {

    private final PillService pillService;
    private final HPillControllerMapper mapper;
    @GetMapping("/detail")
    ResponseEntity<PillDetail> pillDetail(@RequestParam(value = "pillId") Long pillId){
        // 영양제 아이디를 받으면 영양제에 대한 영양제상세정보(영양소 정보 포함) 반환
        PillDetail pillDetail = pillService.pillDetail(mapper.mapToPillIdVo(pillId));

        return ResponseEntity.ok(pillDetail);
    }

    @GetMapping("/search")
    ResponseEntity<ResponsePillSearchListData> pillSearch(@RequestParam(value = "pillName") String pillName){
        // 영양제 이름(제품명)을 받으면 영양제에 대한 영양제 리스트 반환
        ResponsePillSearchListData data = pillService.pillSearchList(mapper.mapToPillNameVo(pillName));

        return ResponseEntity.ok(data);
    }
}