package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.stereotype.Service;


public interface AppShowBehaviorService {
    ResponseResult saveShowBehaivor(ShowBehaviorDto dto);
}
