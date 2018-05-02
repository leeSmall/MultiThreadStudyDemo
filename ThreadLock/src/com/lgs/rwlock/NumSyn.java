package com.lgs.rwlock;

/**
 * lgs
 * 创建日期：2017/09/20
 * 创建时间: 15:15
 */
public class NumSyn implements IGoodsNum {

    private GoodsVo goods;

    public NumSyn(GoodsVo goods) {
        this.goods = goods;
    }


    @Override
    public synchronized GoodsVo getGoodsNumber() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.goods;
    }

    @Override
    public synchronized void setGoodsNumber(int changeNumber) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.goods.setGoodsVoNumber(changeNumber);

    }
}
