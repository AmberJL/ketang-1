package com.example.demo.c;

public class MessageData {
    private String toUserId;
    private String fromUserId;

	private String msgData;
    private long time;
    public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }





    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }



}