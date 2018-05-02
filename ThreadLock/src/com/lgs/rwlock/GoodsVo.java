package com.lgs.rwlock;

/**
 * lgs
 * 创建日期：2017/09/19
 * 创建时间: 21:22
 */
public class GoodsVo {

    private final String id;
    private int totalSaleNumber;//总销售数
    private int depotNumber;//当前库存数

    public GoodsVo(String id, int totalSaleNumber, int depotNumber) {
        this.id = id;
        this.totalSaleNumber = totalSaleNumber;
        this.depotNumber = depotNumber;
    }

    public int getTotalSaleNumber() {
        return totalSaleNumber;
    }

    public int getDepotNumber() {
        return depotNumber;
    }

    public void setGoodsVoNumber(int changeNumber){
        this.totalSaleNumber += changeNumber;
        this.depotNumber -= changeNumber;
    }
}
