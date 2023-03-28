package com.PBL3.services;

import com.PBL3.dtos.PlanInspectorDTO;
import com.PBL3.utils.response.Message;

public interface IPlanInspectorService {
    Message createOne(PlanInspectorDTO dto);

    Message deactive(PlanInspectorDTO dto);
}
