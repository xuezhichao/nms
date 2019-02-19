package com.byxf.nms.service.impl;

import com.byxf.nms.entity.NmsOsNewInfo;
import com.byxf.nms.entity.NmsOsNewStatusInfo;
import com.byxf.nms.model.NmsOsNewInfoBean;
import com.byxf.nms.model.PageInfo;
import com.byxf.nms.model.PageInfoBean;
import com.byxf.nms.model.PageInfoResponse;
import com.byxf.nms.repository.OsNewInfoDao;
import com.byxf.nms.repository.OsNewStatusInfoDao;
import com.byxf.nms.service.OsNewInfoService;
import com.byxf.nms.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service("osNewInfoService")
public class OsNewInfoServiceImpl implements OsNewInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    OsNewInfoDao osNewInfoDao;
    @Resource
    OsNewStatusInfoDao osNewStatusInfoDao;

    public NmsOsNewInfo saveOrUpdateOsNewInfo(NmsOsNewInfo nmsOsNewInfo) {
        nmsOsNewInfo = osNewInfoDao.save(nmsOsNewInfo);
        NmsOsNewStatusInfo statusInfo = new NmsOsNewStatusInfo(nmsOsNewInfo.getNewId(),0, new Date(),new Date(),0);
        NmsOsNewStatusInfo nmsOsNewStatusInfo = osNewStatusInfoDao.findNmsOsNewStatusInfoByNewId(nmsOsNewInfo.getNewId());
        if(nmsOsNewStatusInfo == null){
            osNewStatusInfoDao.save(statusInfo);
        }
        return nmsOsNewInfo;
    }

    @Override
    public NmsOsNewInfo queryOsNewInfoByNewId(String newId) {
        try{
            NmsOsNewInfo nmsOsNewInfo = osNewInfoDao.findNmsOsNewInfoByNewId(newId);
            return nmsOsNewInfo;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PageInfoResponse<NmsOsNewInfoBean> queryOsNewInfosByPage(PageInfoBean pageInfo) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageInfo.getPageNo(),pageInfo.getPageSize(),sort);
        Page<NmsOsNewInfoBean> datas = osNewInfoDao.findAll(new Specification<NmsOsNewInfoBean>(){
            @Override
            public Predicate toPredicate(Root<NmsOsNewInfoBean> root, CriteriaQuery<?> criteriaQuery,
                    CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("isDelete"), 0));
                if(pageInfo.getNmsOsNewInfo() != null && pageInfo.getNmsOsNewInfo().getNewStatus() != null){
                    Join<NmsOsNewInfo, NmsOsNewStatusInfo> join = root.join("nmsOsNewStatusInfo", JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(join.get("newStatus").as(int.class), pageInfo.getNmsOsNewInfo().getNewStatus()));
                }else{
                    Join<NmsOsNewInfo,NmsOsNewStatusInfo> join = root.join("nmsOsNewStatusInfo", JoinType.INNER);
//                    predicates.add(criteriaBuilder.notEqual(join.get("nmsOsNewStatusInfo").get("newStatus"), ""));
                }
                if(pageInfo.getNmsOsNewInfo() != null){
                    predicates.add(criteriaBuilder.equal(root.get("isDelete").as(int.class),pageInfo.getNmsOsNewInfo().getIsDelete()));
                    if(StringUtils.isNotEmpty(pageInfo.getNmsOsNewInfo().getNewTitle())){
                        predicates.add(criteriaBuilder.like(
                                root.get("newTitle"), "%" + pageInfo.getNmsOsNewInfo().getNewTitle() + "%"));
                    }
                    if(StringUtils.isNotEmpty(pageInfo.getNmsOsNewInfo().getUserId())){
                        predicates.add(criteriaBuilder.equal(root.get("userId"), pageInfo.getNmsOsNewInfo().getUserId()));
                    }
                    if(pageInfo.getNmsOsNewInfo().getStartTime() != null){
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("newTime"), pageInfo.getNmsOsNewInfo().getStartTime()));
                    }
                    if(pageInfo.getNmsOsNewInfo().getEndTime() != null){
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("newTime"), pageInfo.getNmsOsNewInfo().getEndTime()));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
        PageInfoResponse<NmsOsNewInfoBean> responseData = new PageInfoResponse(pageInfo.getPageNo(),
                pageInfo.getPageSize(), datas.getTotalPages(), datas.getTotalElements(), datas.getContent());
        return responseData;
    }

    @Override
    public Integer deleteOsNewInfos(List<String> newIds) {
        try{
            for(String newId:newIds){
                NmsOsNewInfo nmsOsNewInfo = osNewInfoDao.findNmsOsNewInfoByNewId(newId);
                nmsOsNewInfo.setIsDelete(1);
                nmsOsNewInfo = osNewInfoDao.save(nmsOsNewInfo);
            }
            return newIds.size();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<NmsOsNewStatusInfo> saveOrUpdateOsNewStatusInfo(List<String> newIds) {
        try{
            List<NmsOsNewStatusInfo> nmsOsNewStatusInfos = new ArrayList<>();
            for(String newId:newIds){
                NmsOsNewStatusInfo nmsOsNewStatusInfo = osNewStatusInfoDao.findNmsOsNewStatusInfoByNewId(newId);
                nmsOsNewStatusInfo.setNewStatus(1);
                osNewStatusInfoDao.save(nmsOsNewStatusInfo);
                nmsOsNewStatusInfos.add(nmsOsNewStatusInfo);
            }
            return nmsOsNewStatusInfos;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
}
