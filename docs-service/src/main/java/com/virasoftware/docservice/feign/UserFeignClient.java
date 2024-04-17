package com.virasoftware.docservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.virasoftware.docservice.domains.dtos.UserDto;

@FeignClient(name = "user-client", url = "user-service")
public interface UserFeignClient {

	@GetMapping("/api/v1/user/{userId}")
	public ResponseEntity<UserDto> getUserById(String id);
}
