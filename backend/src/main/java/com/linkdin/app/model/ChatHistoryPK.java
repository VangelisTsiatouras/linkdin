package com.linkdin.app.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ChatHistoryPK implements Serializable {
    private int chatId;
    private int senderUserId;

    @Column(name = "chat_id")
    @Id
    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @Column(name = "sender_user_id")
    @Id
    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatHistoryPK that = (ChatHistoryPK) o;
        return chatId == that.chatId &&
                senderUserId == that.senderUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, senderUserId);
    }
}
