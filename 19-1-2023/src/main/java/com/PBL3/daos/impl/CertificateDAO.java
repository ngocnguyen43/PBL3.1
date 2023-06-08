package com.PBL3.daos.impl;

import com.PBL3.daos.ICertificateDAO;
import com.PBL3.models.Certificate;
import com.PBL3.models.pagination.CertificatePaginationModel;
import com.PBL3.utils.mapper.CertificateMapper;
import com.PBL3.utils.mapper.CountMapper;

import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class CertificateDAO extends AbstractDAO<Certificate> implements ICertificateDAO {

    @Override
    public String save(Certificate domain) {
        Integer action = domain.getAction() ? 1 : 0;
        String sql = "INSERT INTO certificates (certificate_id,name,description,image,action,modified_by) VALUES (?,?,?,?,?,?)";
        insert(sql, domain.getId(), domain.getName(), domain.getDescription(), domain.getPath(), action, domain.getModifiedBy());
        return domain.getId();
    }

    @Override
    public List<Certificate> findAll(CertificatePaginationModel domain) {
        String sql = "SELECT * FROM certificates"
                + " ORDER BY name, created_at"
                + " LIMIT " + PER_PAGE + " OFFSET " + (domain.getPage() - 1) * PER_PAGE;
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

    @Override
    public Integer coutAllCertificates() {
        String sql = "SELECT COUNT(*) as total FROM login.certificates ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }

    @Override
    public List<Certificate> findAll() {
        String sql = "SELECT * FROM login.certificates ORDER BY created_at DESC";
        return query(sql, new CertificateMapper());
    }

}
