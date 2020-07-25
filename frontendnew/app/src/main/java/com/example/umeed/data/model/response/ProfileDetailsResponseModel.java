package com.example.umeed.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDetailsResponseModel {

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
    public ProfileDetailsResponseModel() {
    }

    /**
     *
     * @param data
     * @param status
     */
    public ProfileDetailsResponseModel(String status, Data data) {
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

        public class Message {

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
            public Message() {
            }

            /**
             *
             * @param area
             * @param skills
             * @param image
             * @param id
             * @param userAcc
             */
            public Message(int id, UserAcc userAcc, String area, String image, String skills) {
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
