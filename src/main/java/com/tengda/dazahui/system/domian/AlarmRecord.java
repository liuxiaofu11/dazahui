package com.tengda.dazahui.system.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Author teswell
 * @Date 2020/11/18 14:07
 * @function
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "alarmrecord", type = "tb_alarm_record")
public class AlarmRecord implements Serializable {
    @Id
    private Long id;
    @Field(type = FieldType.Auto, index = true)
    private Integer ararmTypeId;
    @Field(type = FieldType.Auto, index = true)
    private Integer startTime;
    private Integer endTime;
    @Field(type = FieldType.Auto, index = true)
    private Integer alarmSource;
    @Field(type = FieldType.Auto, index = true)
    private Integer vehicleId;
    private Integer gpsLng;
    private String uuid;
    private Integer gpsLat;
    private Integer gpsStatus;
    private Integer altitude;
    private Integer speed;
    private Integer angle;
    private Integer endGpsLng;
    private Integer endGpsLat;
    private Integer endGpsStatus;
    private Integer endAltitude;
    private Integer endSpeed;
    private Integer endAngle;
    private String alarmDesc;
    @Field(type = FieldType.Auto, index = true)
    private Integer alarmGrade;
    private Integer handlePeople;
    private Integer handleTime;
    private Integer handleMethod;
    private String handleDesc;

    private String urlPath;

}
