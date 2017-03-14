package com.ethercamp.starter.ethereum;

import org.ethereum.core.Block;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.facade.Ethereum;
import org.ethereum.listener.EthereumListenerAdapter;
import org.ethereum.util.BIUtil;

import java.math.BigInteger;
import java.util.List;

public class EthereumListener extends EthereumListenerAdapter {

    Ethereum ethereum;
    private boolean syncDone = false;

    public EthereumListener(Ethereum ethereum) {
        this.ethereum = ethereum;
    }

    @Override
    public void onBlock(Block block, List<TransactionReceipt> receipts) {
        System.out.println();
        System.out.println("Do something on block: " + block.getNumber());

        if (syncDone)
            calcNetHashRate(block);

        System.out.println();
    }



    /**
     *  Mark the fact that you are touching
     *  the head of the chain
     */
    @Override
    public void onSyncDone(SyncState state) {
        System.out.println("onSyncDone " + state);
        if (!syncDone) {
            System.out.println(" ** SYNC DONE ** ");
            syncDone = true;
        }
    }

    /**
     * Just small method to estimate total power off all miners on the net
     * @param block
     */
    private void calcNetHashRate(Block block){

        if ( block.getNumber() > 1000){

            long avgTime = 1;
            long cumTimeDiff = 0;
            Block currBlock = block;
            for (int i=0; i < 1000; ++i){

                Block parent = ethereum.getBlockchain().getBlockByHash(currBlock.getParentHash());
                long diff = currBlock.getTimestamp() - parent.getTimestamp();
                cumTimeDiff += Math.abs(diff);
                currBlock = parent;
            }

            avgTime = cumTimeDiff / 1000;

            BigInteger netHashRate = block.getDifficultyBI().divide(BIUtil.toBI(avgTime));
            double hashRate = netHashRate.divide(new BigInteger("1000000000")).doubleValue();

            System.out.println("Net hash rate: " + hashRate + " GH/s");
        }

    }

}
