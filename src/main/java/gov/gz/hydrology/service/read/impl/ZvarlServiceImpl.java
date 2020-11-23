package gov.gz.hydrology.service.read.impl;

import gov.gz.hydrology.entity.read.Zvarl;
import gov.gz.hydrology.mapper.read.ZvarlDao;
import gov.gz.hydrology.service.read.ZvarlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ZvarlServiceImpl implements ZvarlService {

    @Autowired
    private ZvarlDao zvarlDao;

    @Override
    public void selectList(String stcd, String date, List Z_CUR, List V_CUR) {
        /**
         * 查询最近的数据
         */
        Zvarl lastTime = zvarlDao.selectLastTime(stcd, date);
        /**
         * 查询列表
         */
        List<Zvarl> listZvarl = zvarlDao.selectList(stcd, lastTime.getMstm());
        /**
         * 构造返回数据
         */
        Z_CUR.clear();
        V_CUR.clear();
        for (Zvarl zvarl: listZvarl) {
            Z_CUR.add(zvarl.getRz());
            V_CUR.add(zvarl.getW());
        }
    }

}
