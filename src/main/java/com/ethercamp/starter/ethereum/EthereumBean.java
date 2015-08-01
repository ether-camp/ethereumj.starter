package com.ethercamp.starter.ethereum;

import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;


public class EthereumBean {

    Ethereum ethereum;

    public void start(){
        this.ethereum = EthereumFactory.createEthereum();
        this.ethereum.addListener(new EthereumListener(ethereum));
    }


    public String getBestBlock(){
        return "" + ethereum.getBlockchain().getBestBlock().getNumber();
    }
}
