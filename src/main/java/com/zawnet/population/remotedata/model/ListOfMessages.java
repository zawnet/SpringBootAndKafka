package com.zawnet.population.remotedata.model;

import com.zawnet.population.kafka.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ListOfMessages {
    private List<Message> messageList;

    public ListOfMessages() {
        messageList = new ArrayList<>();
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
