package com.challenge.transactionalapi.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactionalapi.adapter.in.web.dto.response.ApiResponse;
import com.challenge.transactionalapi.adapter.in.web.dto.response.ClienteResponse;
import com.challenge.transactionalapi.common.WebAdapter;
import com.challenge.transactionalapi.config._CONST;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactional/api/v1")
public class IsaliveController {
	
    @GetMapping("/isAlive")
    public ResponseEntity<ApiResponse<?>> isAlive() {
    	ApiResponse<ClienteResponse> successResponse = new ApiResponse<>(_CONST.MENSAJE_ISALIVE, null);
        return ResponseEntity.ok(successResponse);
    }
}
