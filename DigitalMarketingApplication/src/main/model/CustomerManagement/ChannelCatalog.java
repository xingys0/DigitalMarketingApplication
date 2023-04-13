package main.model.CustomerManagement;

import java.util.ArrayList;

import main.model.MarketModel.Channel;

public class ChannelCatalog {
    ArrayList<Channel> channelList;

    public ChannelCatalog(ArrayList<Channel> channelList) {
        this.channelList = channelList;
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    public void addChannel(Channel channel) {
        channelList.add(channel);
    }
}
