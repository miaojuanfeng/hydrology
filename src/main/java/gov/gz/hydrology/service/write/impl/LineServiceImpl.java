package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Drainage;
import gov.gz.hydrology.entity.write.Line;
import gov.gz.hydrology.mapper.write.DrainageDao;
import gov.gz.hydrology.mapper.write.LineDao;
import gov.gz.hydrology.service.write.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineDao lineDao;

    @Override
    public List selectList(String stcd) {
        /**
         * 查询列表
         */
        List<Line> listLine = lineDao.selectList(stcd);
        /**
         * 构造返回数据
         */
        List<BigDecimal> retval = new ArrayList<>();
        for (Line line: listLine) {
            retval.add(line.getF());
        }
        return retval;
    }

}
