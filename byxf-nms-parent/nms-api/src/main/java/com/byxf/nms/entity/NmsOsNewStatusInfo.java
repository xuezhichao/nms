package com.byxf.nms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "nms_os_new_status_info")
@Getter
@Setter
@ApiModel(value = "NmsOsNewStatusInfo")
public class NmsOsNewStatusInfo implements Serializable {
    @Id
    @GeneratedValue(generator = "NmsOsNewStatusInfoGenerator")
    @GenericGenerator(name = "NmsOsNewStatusInfoGenerator", strategy = "uuid")
    @Column(name = "new_status_id",nullable = false,length = 32,columnDefinition = "VARCHAR(32)")
    @ApiModelProperty(value = "新闻状态id")
    private String newStatusId;
    @Column(name = "new_id",nullable = true,length = 32,columnDefinition = "VARCHAR(32) COMMENT '新闻id'")
    @ApiModelProperty(value = "新闻记录id")
    private String newId;
    @Column(name = "new_status",nullable = true,length = 2,columnDefinition = "int(2) COMMENT '新闻状态0未发布1发布'")
    @ApiModelProperty(value = "新闻状态0未发布1发布")
    private int newStatus;
    @Column(name = "create_time",nullable = true,columnDefinition = "timestamp")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @Column(name = "update_time",nullable = true,columnDefinition = "timestamp")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @Column(name = "is_delete",nullable = true,length = 2,columnDefinition = "int(2) comment '0未删除1删除'")
    @ApiModelProperty(value = "0未删除1删除")
    private int isDelete;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "new_id",insertable = false,updatable = false)
    NmsOsNewInfo nmsOsNewInfo;

    public NmsOsNewStatusInfo() {
    }

    public NmsOsNewStatusInfo(String newId, int newStatus, Date createTime, Date updateTime, int isDelete) {
        this.newId = newId;
        this.newStatus = newStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }
}
