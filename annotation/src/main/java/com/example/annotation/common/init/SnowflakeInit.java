package com.example.annotation.common.init;

import com.example.annotation.common.util.IPSectionKeyGenerator;
import com.example.annotation.common.util.SnowflakeIdWorker;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeInit {

    private SnowflakeIdWorker idWorker;

    SnowflakeInit(){
        long workerId = IPSectionKeyGenerator.initWorkerId();
        long dataCenterId = IPSectionKeyGenerator.initDataCenterId();
        this.idWorker = new SnowflakeIdWorker(workerId, dataCenterId);
    }

    public String getId() {
        return String.valueOf(idWorker.nextId());
    }
}
