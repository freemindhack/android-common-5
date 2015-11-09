package com.pheth.android;

/**
 * Project: android-common
 * Description: com.pheth.android-Bean
 * Author: danhantao
 * Update: danhantao(2015-11-09 20:43)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class Bean {
    private String name;
    private int price;

    public Bean(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + "," + price;
    }
}
