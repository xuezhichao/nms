package com.byxf.nms.service;

import com.byxf.nms.entity.NmsOsNewInfo;
import com.byxf.nms.entity.NmsOsNewStatusInfo;
import com.byxf.nms.model.NmsOsNewInfoBean;
import com.byxf.nms.model.PageInfo;
import com.byxf.nms.model.PageInfoBean;
import com.byxf.nms.model.PageInfoResponse;
import java.util.List;
import org.springframework.data.domain.Page;

public interface OsNewInfoService {

    NmsOsNewInfo saveOrUpdateOsNewInfo(NmsOsNewInfo nmsOsNewInfo);

    NmsOsNewInfo queryOsNewInfoByNewId(String newId);

    PageInfoResponse<NmsOsNewInfoBean> queryOsNewInfosByPage(PageInfoBean pageInfo);

    Integer deleteOsNewInfos(List<String> newIds);

    List<NmsOsNewStatusInfo> saveOrUpdateOsNewStatusInfo(List<String> newIds);
}
