package com.ethercamp.starter.ethereum;

import org.ethereum.core.Block;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.facade.Ethereum;
import org.ethereum.listener.EthereumListenerAdapter;

import java.util.List;

public class EthereumListener extends EthereumListenerAdapter {

    Ethereum ethereum;

    public EthereumListener(Ethereum ethereum) {
        this.ethereum = ethereum;
    }

    @Override
    public void onBlock(Block block, List<TransactionReceipt> receipts) {
        System.out.println();
        System.out.println("Do something on block: " + block.getNumber());
        System.out.println();
    }


}
