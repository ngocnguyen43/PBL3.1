package com.PBL3.daos.impl;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.models.Certificate;

import java.util.List;

public class CertificateDAO  extends AbstractDAO<Certificate> implements ICertificateDAO {

    @Override
    public String save(Certificate domain) {
        String sql = "INSERT INTO certificates (name,description,image,action,modified_by) VALUES (?,?,?,?,?)";
        insert(sql , domain.getName(),domain.getDescription(),domain.getPath(),domain.getAction(),domain.getModifiedBy());
        return domain.getId();
    }

    @Override
    public List<Certificate> findAll() {
        return null;
    }
}
