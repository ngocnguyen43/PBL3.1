package com.PBL3.services.impl;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.daos.IReportDAO;
import com.PBL3.dtos.ReportDTO;
import com.PBL3.models.PlanModel;
import com.PBL3.models.ReportModel;
import com.PBL3.services.IReportService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.DuplicateEntryException;
import com.PBL3.utils.exceptions.dbExceptions.ForeignKeyViolationException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReportService implements IReportService {
    @Inject
    private IReportDAO iReportDAO;
    @Inject
    private IPlanDAO iPlanDAO;

    @Override
    public Message createOne(ReportDTO dto) throws DuplicateEntryException, CreateFailedException, ForeignKeyViolationException {
        ReportModel domain = Helper.objectMapper(dto, ReportModel.class);
        String id = IDGeneration.generate();
        domain.setId(id);
        PlanModel plan = iPlanDAO.findOneWithoutJoin(domain.getPlanId());
        if (plan == null) throw new ForeignKeyViolationException("Foreign Key Violation");
        ReportModel report = iReportDAO.findOneByPlanId(domain.getPlanId());
        if (report != null) throw new DuplicateEntryException("Duplicate Report");
        try {
            iReportDAO.createOne(domain);
        } catch (Exception e) {
            throw new CreateFailedException("Create New Report Failed");
        }
        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Create Report Successfully").build();
        return new Message.Builder(meta).build();
    }

    @Override
    public ReportModel findOneById(String id) throws NotFoundException {
        ReportModel report = iReportDAO.findOneByPlanId(id);
        if (report == null) throw new NotFoundException("Report Not Found");
        return report;
    }

    @Override
    public Message findAll() throws NotFoundException {
        List<ReportModel> reports = iReportDAO.findAll();
        if (reports == null) throw new NotFoundException("No Reports Found");
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("OK").build();
        Data data = new Data.Builder(null).withResults(reports).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
