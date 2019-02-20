package cn.jamkong.dao;

import cc.innovator.common.CrudDao;
import cc.innovator.model.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface QuartzJobDao extends CrudDao<QuartzJob> {

}
