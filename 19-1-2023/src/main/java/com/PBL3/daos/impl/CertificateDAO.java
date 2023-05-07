package com.PBL3.daos.impl;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.models.Certificate;
import com.PBL3.utils.mapper.CertificateMapper;

import java.util.List;

public class CertificateDAO extends AbstractDAO<Certificate> implements ICertificateDAO {


    @Override
    public String save(Certificate domain) {
        Integer action = domain.getAction() ? 1 : 0;
        String sql = "INSERT INTO certificates (certificate_id,name,description,image,action,modified_by) VALUES (?,?,?,?,?,?)";
        insert(sql, domain.getId(), domain.getName(), domain.getDescription(), domain.getPath(), action, domain.getModifiedBy());
        return domain.getId();
    }

    @Override
    public List<Certificate> findAll() {
        String sql = "SELECT * FROM certificates";
        return query(sql, new CertificateMapper());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM certificates WHERE certificate_id = ?";
        insert(sql, id);
    }

    @Override
    public void updateCertificateDao(Certificate domain) {
        String sql = "UPDATE certificates SET name = ?,description = ?,image = ?,action = ?,modified_by = ?  WHERE certificate_id = ?";
        update(sql, domain.getName(), domain.getDescription(), domain.getPath(), domain.getAction() ? 1 : 0, domain.getModifiedBy(), domain.getId());
    }

    @Override
    public Certificate findOne(String id) {

        String sql = "SELECT * FROM certificates WHERE certificate_id = ?";
        List<Certificate> certificates = query(sql, new CertificateMapper(), id);
        return certificates.isEmpty() ? null : certificates.get(0);
    }

}
