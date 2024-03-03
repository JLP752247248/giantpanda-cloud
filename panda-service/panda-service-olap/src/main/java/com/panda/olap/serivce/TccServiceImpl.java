package com.panda.olap.serivce;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-17  14:23
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Service
public class TccServiceImpl implements TccService {

    @Autowired
    private JdbcTemplate template;
    @Override
    public void deduct(String userId, int money) {
        String xid = RootContext.getXID();
        // 查询冻结记录，如果有，就是cancel执行过，不能继续执行
        List<Map<String, Object>> oldfreeze = template.queryForList("select xid from account_freeze_tbl where xid =?",xid);
        if (!CollectionUtils.isEmpty(oldfreeze)){
            return;
        }
        // 扣除
        template.update("update sys_user set name =900 where id  =?",userId);
        // 记录
        template.update("INSERT INTO `panda_auth`.`account_freeze_tbl`(`xid`, `user_id`, `freeze_money`, `state`) VALUES (?,?, 0, 0);",xid,userId);

    }

    @Override
    public boolean confirm(BusinessActionContext ctx) {
        String xid = ctx.getXid();
        int count = template.update("delete from  account_freeze_tbl  where xid  =?",xid);
        return count == 1;
    }

    @Override
    public boolean cancel(BusinessActionContext ctx) {
        String xid = ctx.getXid();
        // 查询冻结记录
        List<Map<String, Object>> freeze = template.queryForList("select * from account_freeze_tbl where xid =?",xid);

        if(CollectionUtils.isEmpty(freeze)){
            // try没有执行，需要空回滚
            template.update("INSERT INTO `panda_auth`.`account_freeze_tbl`(`xid`, `user_id`, `freeze_money`, `state`) VALUES (?,?, 0, 2);",xid,ctx.getActionContext("userId").toString());
            return true;
        }

        // 幂等判断
        if(Integer.valueOf(freeze.get(0).get("state").toString()) == 2){
            return true;
        }
        int count = template.update("update account_freeze_tbl  set freeze_money = ? where user_id  =?",xid,100,freeze.get(0).get("use_id"));
        // 恢复金额
        /*accountMapper.refund(freeze.getUserId(), freeze.getFreezeMoney());
        freeze.setFreezeMoney(0);
        freeze.setState(AccountFreeze.State.CANCEL);
        int count = freezeMapper.updateById(freeze);*/
        return count == 1;
    }
}

