package com.sample.domain.user.application;

import com.sample.domain.user.domain.repository.UserRepository;
import com.sample.global.payload.ErrorCode;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private ErrorCode t = ErrorCode.INVALID_CHECK;

}
