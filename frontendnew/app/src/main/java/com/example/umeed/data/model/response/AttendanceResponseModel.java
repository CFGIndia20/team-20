package com.example.umeed.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class AttendanceResponseModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     *
     */
    public AttendanceResponseModel() {
    }

    /**
     *
     * @param data
     * @param status
     */
    public AttendanceResponseModel(String status, Data data) {
        super();
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("message")
        @Expose
        private Message message;

        /**
         * No args constructor for use in serialization
         *
         */
        public Data() {
        }

        /**
         *
         * @param message
         */
        public Data(Message message) {
            super();
            this.message = message;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

    }
    public class Message {

        @SerializedName("total")
        @Expose
        private int total;
        @SerializedName("attended")
        @Expose
        private int attended;

        /**
         * No args constructor for use in serialization
         *
         */
        public Message() {
        }

        /**
         *
         * @param attended
         * @param total
         */
        public Message(int total, int attended) {
            super();
            this.total = total;
            this.attended = attended;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getAttended() {
            return attended;
        }

        public void setAttended(int attended) {
            this.attended = attended;
        }

    }

}


