package com.byxf.nms.repository;

import com.byxf.nms.entity.NmsOsNewInfo;
import com.byxf.nms.model.NmsOsNewInfoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("osNewInfoDao")
public interface OsNewInfoDao extends JpaRepository<NmsOsNewInfo, String> {

    NmsOsNewInfo findNmsOsNewInfoByNewId(String newId);

    Page<NmsOsNewInfoBean> findAll(Specification<NmsOsNewInfoBean> nmsOsNewInfoSpecification, Pageable pageable);
}
