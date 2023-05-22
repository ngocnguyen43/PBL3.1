package com.PBL3.services;

import com.PBL3.dtos.ReportDTO;
import com.PBL3.utils.exceptions.dbExceptions.*;
import com.PBL3.utils.response.Message;

public interface IReportService {
    Message createOne(ReportDTO dto, String id) throws DuplicateEntryException, CreateFailedException, ForeignKeyViolationException;

    Message findOneById(String id) throws NotFoundException;

    Message findAll();

    Message updateStatus(String id) throws NotFoundException, UpdateFailedException;
}
