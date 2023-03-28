package com.PBL3.repositories;

import com.PBL3.models.Certificate;
import com.PBL3.utils.exceptions.authExceptions.InvalidCredentialsException;
import com.PBL3.utils.exceptions.dbExceptions.CreateFailedException;
import com.PBL3.utils.exceptions.dbExceptions.NotFoundException;

import java.util.List;

public interface ICeritificateRepository {
    void createCertificate(Certificate domain) throws CreateFailedException;

    List<Certificate> findAll() throws NotFoundException;

    void deleteCertificate(String id) throws InvalidCredentialsException;

    void updateCertificate(Certificate domain) throws InvalidCredentialsException;

    Certificate findOne(String id) throws InvalidCredentialsException, NotFoundException;
}
