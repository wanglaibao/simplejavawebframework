package com.laibao.spring.diy.service.v4;

import com.laibao.spring.diy.beans.factory.annotation.Autowired;
import com.laibao.spring.diy.dao.v4.AccountDao;
import com.laibao.spring.diy.dao.v4.ItemDao;
import com.laibao.spring.diy.stereotype.Component;

@Component(value = "petStore")
public class PetStoreService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
