package com.PBL3.services;

import com.PBL3.dtos.PlanDTO;
import com.PBL3.utils.response.Message;

public interface IPlanService {
    Message createOne(PlanDTO dto);

    Message getOneById(String id);

    Message updateTime(PlanDTO dto);
}
