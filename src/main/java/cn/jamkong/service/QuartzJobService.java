package cn.jamkong.service;

import cc.innovator.common.CrudService;
import cc.innovator.dao.QuartzJobDao;
import cc.innovator.model.QuartzJob;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QuartzJobService extends CrudService<QuartzJobDao, QuartzJob> {


}
