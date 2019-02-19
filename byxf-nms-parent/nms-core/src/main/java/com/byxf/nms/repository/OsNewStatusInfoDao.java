package com.byxf.nms.repository;

import com.byxf.nms.entity.NmsOsNewStatusInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("osNewStatusInfoDao")
public interface OsNewStatusInfoDao extends JpaRepository<NmsOsNewStatusInfo, String> {
    NmsOsNewStatusInfo findNmsOsNewStatusInfoByNewId(String newId);
}
