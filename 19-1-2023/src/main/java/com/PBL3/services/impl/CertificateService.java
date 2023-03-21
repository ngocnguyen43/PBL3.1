package com.PBL3.services.impl;

import com.PBL3.dtos.CertificateDTO;
import com.PBL3.models.Certificate;
import com.PBL3.repositories.ICeritificateRepository;
import com.PBL3.services.ICertificateService;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.PBL3.utils.response.Data;
import com.PBL3.utils.response.Message;
import com.PBL3.utils.response.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CertificateService implements ICertificateService {
    @Inject
    private ICeritificateRepository ceritificateRepository;

    public Message createCertificate(CertificateDTO dto) {
        try {
            Certificate domain = Helper.objectMapper(dto, Certificate.class);
            String id = IDGeneration.generate();
            domain.setId(id);
            System.out.println(new ObjectMapper().writeValueAsString(domain));
            ceritificateRepository.createCertificate(domain);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage("Created Certificate Succesfully").build();
            return new Message.Builder(meta).build();

        } catch (CreateFailedException e) {
            Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            Meta meta = new Meta.Builder(500).withError(e.getMessage()).build();
            return new Message.Builder(meta).build();
        }
    }

    @Override
    public Message getAllCertificate() {
        try {
            List<Certificate> certificateList = ceritificateRepository.findAll();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage("Success").build();
            Data data = new Data.Builder(null).withResults(certificateList).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (NotFoundException e) {
            Meta meta = new Meta.Builder(HttpServletResponse.SC_NOT_FOUND).withMessage(e.getMessage()).build();

            return new Message.Builder(meta).build();
        }
    }

}
