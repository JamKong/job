create table quartz_job (
  id VARCHAR(64) NOT NULL COMMENT 'id',
  name varchar(100) NOT NULL COMMENT '任务名称',
  trigger_name varchar(100) NOT NULL COMMENT '触发器名称',
  group_name varchar(100) NULL COMMENT '分组名称',
  cron_exp varchar(20) NOT NULL COMMENT 'cron表达式',
  bean_name varchar(100) NOT NULL COMMENT '任务执行时调用的类名，完全限定名',
  method_name varchar(100) NULL COMMENT '任务调用的方法名',
  status varchar(1) NOT NULL COMMENT '任务状态',
  run_now varchar(1) NULL COMMENT '是否立刻执行一次',
  remarks varchar(200) NULL COMMENT '描述',
  create_date datetime NOT NULL COMMENT '创建时间',
  update_date datetime NOT NULL COMMENT '更新时间',
  del_flag char NULL COMMENT '删除标识' DEFAULT "0",
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '任务';