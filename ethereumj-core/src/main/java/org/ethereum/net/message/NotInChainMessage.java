package org.ethereum.net.message;

import static org.ethereum.net.Command.NOT_IN_CHAIN;

import org.ethereum.net.Command;
import org.ethereum.net.message.Message;
import org.ethereum.net.rlp.RLPItem;
import org.ethereum.net.rlp.RLPList;
import org.ethereum.util.Utils;

/**
 * www.ethereumJ.com
 * User: Roman Mandeleil
 * Created on: 06/04/14 14:56
 */
public class NotInChainMessage extends Message {

    private byte[] hash;

    public NotInChainMessage(RLPList rawData) {
        super(rawData);
    }

    @Override
    public void parseRLP() {
        RLPList paramsList = (RLPList) rawData.getElement(0);

        if (Command.fromInt(((RLPItem)(paramsList).getElement(0)).getData()[0] & 0xFF) != NOT_IN_CHAIN){
            throw new Error("NotInChain Message: parsing for mal data");
        }
        hash = ((RLPItem)paramsList.getElement(1)).getData();
    }

    @Override
    public byte[] getPayload() {
        return null;
    }

    public byte[] getHash() {
        return hash;
    }

    public String toString(){
        if (!parsed) 
        	parseRLP();
        return "NotInChain Message [" + Utils.toHexString(hash) + "]";
    }
}