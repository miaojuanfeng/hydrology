package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.read.Zvarl;
import gov.gz.hydrology.entity.write.Drainage;
import gov.gz.hydrology.mapper.write.DrainageDao;
import gov.gz.hydrology.service.write.DrainageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrainageServiceImpl implements DrainageService {

    @Autowired
    private DrainageDao drainageDao;

    @Override
    public void selectList(String stcd, List Z0, List HCOQ) {
        /**
         * 查询列表
         */
        List<Drainage> listDrainage = drainageDao.selectList(stcd);
        /**
         * 构造返回数据
         */
        Z0.clear();
        HCOQ.clear();
        for (Drainage drainage: listDrainage) {
            Z0.add(drainage.getZ0());
            HCOQ.add(drainage.getHcoq());
        }
    }

}
