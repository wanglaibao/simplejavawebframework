package com.laibao.spring.diy.service.v3;

import com.laibao.spring.diy.dao.v3.AccountDao;
import com.laibao.spring.diy.dao.v3.ItemDao;

/**
 * 专门用来进行constructor注入测试使用的
 */
public class PetStoreService {
    private AccountDao accountDao;

    private ItemDao itemDao;

    private int version;

    public PetStoreService(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = -1;
    }

    public PetStoreService(AccountDao accountDao, ItemDao itemDao, int version) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = version;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public int getVersion() {
        return version;
    }
}
