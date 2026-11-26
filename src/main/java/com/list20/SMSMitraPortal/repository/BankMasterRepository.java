package com.list20.SMSMitraPortal.repository;


import com.list20.SMSMitraPortal.pojo.BankMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankMasterRepository extends JpaRepository<BankMaster, String> {
    BankMaster findByBankName(String BankName);
}
