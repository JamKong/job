package cn.jamkong.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class BaseService<T> {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());




}
