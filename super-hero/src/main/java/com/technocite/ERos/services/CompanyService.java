package com.technocite.ERos.services;

import com.technocite.ERos.dao.Impl.CompanyDAOImpl;
import com.technocite.ERos.dto.CompanyDTO;
import com.technocite.ERos.model.Company;
import com.technocite.ERos.utils.ErozUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CompanyService {

    @Autowired
    private CompanyDAOImpl companyDAO;

    public CompanyDTO addCompany(CompanyDTO companyDTO) throws SQLException {
        companyDAO.create(convertToWithoutId(companyDTO));
        return convertToDTO(companyDAO.findByName(companyDTO.getName()));
    }

    public boolean deleteCompany(int id) throws SQLException {
        return companyDAO.delete(id);
    }

    public CompanyDTO find() throws SQLException {
        return convertToDTO(companyDAO.find());
    }

    public boolean exist() throws SQLException {
        return companyDAO.companyExist();
    }

    private Company convertTo(CompanyDTO companyDTO) {
        return new Company(
                companyDTO.getId(),
                companyDTO.getName(),
                companyDTO.getAssets(),
                companyDTO.getTown()
        );
    }

    private Company convertToWithoutId(CompanyDTO companyDTO) {
        return new Company(
                companyDTO.getName(),
                ErozUtils.getRandomNumber(5000,8000),
                companyDTO.getTown()
        );
    }

    private CompanyDTO convertToDTO(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getAssets(),
                company.getTown()
        );
    }

}
