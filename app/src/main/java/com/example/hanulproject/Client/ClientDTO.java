package com.example.hanulproject.Client;

public class ClientDTO {
        private String name, phone, addr, id, pw, email;
        private String result;


        public ClientDTO() {}

        public ClientDTO(String name, String phone, String addr, String id, String pw, String email){
            this.name = name;
            this.phone = phone;
            this.addr = addr;
            this.id = id;
            this.pw = pw;
            this.email = email;
        }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPw() {
            return pw;
        }

        public void setPw(String pw) {
            this.pw = pw;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone.replaceAll(",", "-");
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
}
