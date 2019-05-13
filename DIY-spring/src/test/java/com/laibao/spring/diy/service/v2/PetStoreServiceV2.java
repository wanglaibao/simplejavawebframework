package com.laibao.spring.diy.service.v2;

import com.laibao.spring.diy.dao.v2.AccountDao;
import com.laibao.spring.diy.dao.v2.ItemDao;

/**
 * 专门用来进行setter注入测试使用的
 * 在之前的基础上面增加了值属性value
 * eg: <property name="owner" value="金戈"/>
 */
public class PetStoreServiceV2 {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private String owner;

    private int version;

    public PetStoreServiceV2() {

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
