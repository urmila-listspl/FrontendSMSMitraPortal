package com.list20.SMSMitraPortal.repository;


import com.list20.SMSMitraPortal.pojo.BranchMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchMasterRepository extends JpaRepository<BranchMaster, String> {
    BranchMaster findByBranchName(String BankName);

    BranchMaster findByBranchNameAndBankCode(String branchName, String bankCode);
}
