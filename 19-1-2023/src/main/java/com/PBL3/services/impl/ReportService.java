package com.PBL3.services.impl;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.daos.IReportDAO;
import com.PBL3.daos.IUserDAO;
import com.PBL3.dtos.ReportDTO;
import com.PBL3.models.Notification;
import com.PBL3.models.PlanModel;
import com.PBL3.models.ReportModel;
import com.PBL3.services.INotificationService;
import com.PBL3.services.IReportService;
import com.PBL3.utils.exceptions.dbExceptions.*;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.PBL3.utils.response.Response;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReportService implements IReportService {
    @Inject
    private IReportDAO iReportDAO;
    @Inject
    private IPlanDAO iPlanDAO;
    @Inject
    private INotificationService notificationService;
    @Inject
    private IUserDAO userDAO;

    @Override
    public Message createOne(ReportDTO dto, String userId) throws DuplicateEntryException, CreateFailedException, ForeignKeyViolationException {
        ReportModel domain = Helper.objectMapper(dto, ReportModel.class);
        String nname = userDAO.getUserName(userId);
        Notification notification = new Notification
                .Builder(IDGeneration.generate())
                .withCreator(userId)
                .withMessage("The report of plan " + dto.getPlanId() + " has been submitted by " + nname)
                .withAdmin(true)
                .build();
        String id = IDGeneration.generate();
        domain.setId(id);
        PlanModel plan = iPlanDAO.findOneWithoutJoin(domain.getPlanId());
        if (plan == null) throw new ForeignKeyViolationException("Foreign Key Violation");
        ReportModel report = iReportDAO.findOneByPlanId(domain.getPlanId());
        if (report != null) throw new DuplicateEntryException(Response.DUPLICATED);
        try {
            iReportDAO.createOne(domain);
            notificationService.create(notification);
        } catch (Exception e) {
            throw new CreateFailedException(Response.CREATED);
        }

        Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(Response.CREATED).build();
        return new Message.Builder(meta).build();
    }

    @Override
    public Message findOneById(String id) throws NotFoundException {
        ReportModel report = iReportDAO.findOneByPlanId(id);
        if (report == null) throw new NotFoundException(Response.NOT_FOUND);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(report).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message findAll() {
        List<ReportModel> reports = iReportDAO.findAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(Response.OK).build();
        Data data = new Data.Builder(null).withResults(reports).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message updateStatus(String id) throws NotFoundException, UpdateFailedException {
        ReportModel report = iReportDAO.findOneByReportId(id);
        if (report == null) throw new NotFoundException(Response.NOT_FOUND);
        try {
            iReportDAO.updateReportStatus(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NO_CONTENT).withMessage(Response.SUCCESS).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new UpdateFailedException(Response.FAILED);
        }
    }
}
