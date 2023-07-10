# banana-event-start
基于DDD的事件组件


## 类型说明
### com.banana.event.starter.base包
EventDomain：抽象的领域分类接口
AggregateType：聚合类型接口，每个聚合所有对应的领域
Identify：对象唯一标识接口
EventType：事件类型，每个事件由聚合触发
Event：事件
EventConsumerTask：消费方处理事件的任务信息

### 核心类
EventConsumer：事件消费者接口，使用的时候实现这个接口
EventCoordinator：事件的协调处理器，包括发布、消费事件
EventConsumerRegister：消费者注册中心，用于绑定、关联事件和消费者
EventStarter：启动类
WrapperEventConsumer：包装用户使用时实现的consumer做一些增强处理

### com.banana.event.starter.extension 扩展接口
EventRepository：事件对象的仓储接口，根据自己的需求进行实现，可以是任何存储。
ConsumerTaskRepository：消费者任务的仓储接口，用户自己实现
EventIdFactory：获取eventId的接口，默认为Long类型时间戳，用户可自己实现
EventWarming：事件预警接口，用户可在事件在发布失败、处理失败时，实现扩展预警逻辑。


```sql


CREATE TABLE `xfyl_merchant_event_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `event_id` bigint(20) NOT NULL COMMENT '事件ID',
  `domain_code` varchar(60) NOT NULL COMMENT '领域编号',
  `aggregate_code` varchar(60) NOT NULL COMMENT '聚合类型编码',
  `aggregate_id` varchar(512) NOT NULL COMMENT '聚合唯一标识',
  `version` int(10) DEFAULT NULL COMMENT '版本号，这个版本号是基于domain_id发布的事件版本号',
  `event_code` varchar(60) NOT NULL COMMENT '事件名称',
  `publish_time` datetime NOT NULL COMMENT '事件发布时间',
  `body` varchar(512) DEFAULT NULL COMMENT '事件主体内容',
  `consumer_ids` varchar(512) NOT NULL COMMENT '事件消费者集合',
  `yn` int(4) NOT NULL DEFAULT '1' COMMENT '逻辑状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `score` int(10) NOT NULL COMMENT '事件分值（代表消费者的数量）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='事件记录表';

CREATE TABLE `event_consumer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `domain_code` varchar(60) NOT NULL COMMENT '事件消费者所属领域编号',
  `event_code` varchar(60) NOT NULL COMMENT '事件类型编号',
  `event_id` bigint(20) NOT NULL COMMENT '事件ID',
  `publish_time` datetime DEFAULT NULL COMMENT '事件发布时间',
  `consumer_code` varchar(60) NOT NULL COMMENT '处理器编号',
  `status` int(4) NOT NULL COMMENT '处理结果-1:处理失败，0:待消费，1:处理成功，2:失效',
  `async` int(4) NOT NULL COMMENT '是否异步执行1:异步，0:同步',
  `num` int(10) DEFAULT NULL COMMENT '执行次数',
  `yn` int(4) NOT NULL DEFAULT '1' COMMENT '逻辑状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='事件消费记录表';



```
