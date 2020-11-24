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


    public AlarmRecord() {
    }

    public AlarmRecord(Long id, Integer ararmTypeId, Integer startTime, Integer endTime, Integer alarmSource, Integer vehicleId, Integer gpsLng, String uuid, Integer gpsLat, Integer gpsStatus, Integer altitude, Integer speed, Integer angle, Integer endGpsLng, Integer endGpsLat, Integer endGpsStatus, Integer endAltitude, Integer endSpeed, Integer endAngle, String alarmDesc, Integer alarmGrade, Integer handlePeople, Integer handleTime, Integer handleMethod, String handleDesc, String urlPath) {
        this.id = id;
        this.ararmTypeId = ararmTypeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.alarmSource = alarmSource;
        this.vehicleId = vehicleId;
        this.gpsLng = gpsLng;
        this.uuid = uuid;
        this.gpsLat = gpsLat;
        this.gpsStatus = gpsStatus;
        this.altitude = altitude;
        this.speed = speed;
        this.angle = angle;
        this.endGpsLng = endGpsLng;
        this.endGpsLat = endGpsLat;
        this.endGpsStatus = endGpsStatus;
        this.endAltitude = endAltitude;
        this.endSpeed = endSpeed;
        this.endAngle = endAngle;
        this.alarmDesc = alarmDesc;
        this.alarmGrade = alarmGrade;
        this.handlePeople = handlePeople;
        this.handleTime = handleTime;
        this.handleMethod = handleMethod;
        this.handleDesc = handleDesc;
        this.urlPath = urlPath;
    }
}
