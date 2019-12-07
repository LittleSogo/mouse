package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsFeightTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 运费模版
 * @Date 2019-11-26
 */
@Repository
public interface PmsFeightTemplateRepository extends JpaRepository<PmsFeightTemplateEntity, Long>, JpaSpecificationExecutor<PmsFeightTemplateEntity> {

}