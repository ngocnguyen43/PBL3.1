package com.PBL3.utils.mapper;

import com.PBL3.models.Certificate;

import java.sql.ResultSet;

public class CertificateMapper implements IMapper<Certificate> {
    @Override
    public Certificate mapRow(ResultSet result) {
        Certificate certificate = new Certificate();
        try {
            certificate.setId(result.getString("certificate_id"));
            certificate.setName(result.getString("name"));
            certificate.setDescription(result.getString("description"));
            certificate.setPath(result.getString("image"));
            certificate.setAction(result.getInt("action") == 1);
            certificate.setModifiedBy(result.getString("modified_by"));
            certificate.setCreatedAt(result.getTimestamp("created_at"));
            certificate.setUpdatedAt(result.getTimestamp("updated_at"));
            return certificate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
