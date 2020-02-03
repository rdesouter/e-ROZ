package com.technocite.ERos.controllers;

import com.technocite.ERos.dto.CompanyDTO;
import com.technocite.ERos.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public CompanyDTO getCompany() throws SQLException {
        return companyService.find();
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO companyDTO) throws SQLException {
        CompanyDTO comp = companyService.addCompany(companyDTO);
        if(comp != null){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CompanyDTO> delCompany(@PathVariable int id) throws SQLException {
        if(companyService.deleteCompany(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/exist")
    public boolean companyExist() throws SQLException {
        return companyService.exist();
    }
}
