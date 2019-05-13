package com.laibao.spring.diy.service.v2;

import com.laibao.spring.diy.dao.v2.AccountDao;
import com.laibao.spring.diy.dao.v2.ItemDao;

/**
 * 专门用来进行setter注入测试使用的
 */
public class PetStoreService {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private String owner;

    public PetStoreService() {

    }


    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
