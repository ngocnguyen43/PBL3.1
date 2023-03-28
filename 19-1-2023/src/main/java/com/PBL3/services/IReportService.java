package com.PBL3.services;

import com.PBL3.dtos.ReportDTO;
import com.PBL3.models.ReportModel;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.ForeignKeyViolationException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.response.Message;

public interface IReportService {
    Message createOne(ReportDTO dto) throws DuplicateEntryException, CreateFailedException, ForeignKeyViolationException;

    ReportModel findOneById(String id) throws NotFoundException;
}
