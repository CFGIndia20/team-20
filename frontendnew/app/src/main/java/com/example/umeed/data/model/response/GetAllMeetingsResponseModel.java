package com.example.umeed.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetAllMeetingsResponseModel {

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
    public GetAllMeetingsResponseModel() {
    }

    /**
     *
     * @param data
     * @param status
     */
    public GetAllMeetingsResponseModel(String status, Data data) {
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
        private List<Message> message = null;

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
        public Data(List<Message> message) {
            super();
            this.message = message;
        }

        public List<Message> getMessage() {
            return message;
        }

        public void setMessage(List<Message> message) {
            this.message = message;
        }
        public class Message {

            @SerializedName("id")
            @Expose
            private int id;
            @SerializedName("attended_users")
            @Expose
            private List<AttendedUser> attendedUsers = null;
            @SerializedName("date_time")
            @Expose
            private String dateTime;

            /**
             * No args constructor for use in serialization
             *
             */
            public Message() {
            }

            /**
             *
             * @param dateTime
             * @param attendedUsers
             * @param id
             */
            public Message(int id, List<AttendedUser> attendedUsers, String dateTime) {
                super();
                this.id = id;
                this.attendedUsers = attendedUsers;
                this.dateTime = dateTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<AttendedUser> getAttendedUsers() {
                return attendedUsers;
            }

            public void setAttendedUsers(List<AttendedUser> attendedUsers) {
                this.attendedUsers = attendedUsers;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public class AttendedUser {

                @SerializedName("id")
                @Expose
                private int id;
                @SerializedName("user_acc")
                @Expose
                private UserAcc userAcc;
                @SerializedName("area")
                @Expose
                private String area;
                @SerializedName("image")
                @Expose
                private String image;
                @SerializedName("skills")
                @Expose
                private String skills;

                /**
                 * No args constructor for use in serialization
                 *
                 */
                public AttendedUser() {
                }

                /**
                 *
                 * @param area
                 * @param skills
                 * @param image
                 * @param id
                 * @param userAcc
                 */
                public AttendedUser(int id, UserAcc userAcc, String area, String image, String skills) {
                    super();
                    this.id = id;
                    this.userAcc = userAcc;
                    this.area = area;
                    this.image = image;
                    this.skills = skills;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public UserAcc getUserAcc() {
                    return userAcc;
                }

                public void setUserAcc(UserAcc userAcc) {
                    this.userAcc = userAcc;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getSkills() {
                    return skills;
                }

                public void setSkills(String skills) {
                    this.skills = skills;
                }

            }
            public class UserAcc {

                @SerializedName("first_name")
                @Expose
                private String firstName;
                @SerializedName("last_name")
                @Expose
                private String lastName;
                @SerializedName("username")
                @Expose
                private String username;

                /**
                 * No args constructor for use in serialization
                 *
                 */
                public UserAcc() {
                }

                /**
                 *
                 * @param firstName
                 * @param lastName
                 * @param username
                 */
                public UserAcc(String firstName, String lastName, String username) {
                    super();
                    this.firstName = firstName;
                    this.lastName = lastName;
                    this.username = username;
                }

                public String getFirstName() {
                    return firstName;
                }

                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }

                public String getLastName() {
                    return lastName;
                }

                public void setLastName(String lastName) {
                    this.lastName = lastName;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

            }

        }



    }

    }



