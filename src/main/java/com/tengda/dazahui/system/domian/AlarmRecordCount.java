package com.tengda.dazahui.system.domian;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author teswell
 * @Date 2020/11/24 17:24
 * @function
 */
@Data
public class AlarmRecordCount implements Serializable {
    private Integer vehicleId;
    private Integer dsmCount;
    private Integer adsCount;

    public AlarmRecordCount() {
    }

    public AlarmRecordCount(Integer vehicleId, Integer dsmCount, Integer adsCount) {
        this.vehicleId = vehicleId;
        this.dsmCount = dsmCount;
        this.adsCount = adsCount;
    }


    @Override
    public String toString() {
        return "AlarmRecordCount{" +
                "vehicleId=" + vehicleId +
                ", dsmCount=" + dsmCount +
                ", adsCount=" + adsCount +
                '}';
    }
}
