package com.wl.foodspringboot.Utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 事务监听器
 */
@Component
public class TransactionalListener {

    //AFTER_COMMIT是事务提交后执行，默认就是这个
    //fallbackExecution=true则会在没有事务时也执行该方法，不然只有加上事务才会监听，切记。默认是false
    @TransactionalEventListener(fallbackExecution = true,phase = TransactionPhase.AFTER_COMMIT)
    public void handlerPrint(String name){
        System.out.print("事务监听：事务已提交成功！"+name);
    }
}
