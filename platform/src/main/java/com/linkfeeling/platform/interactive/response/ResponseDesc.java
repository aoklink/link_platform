package com.linkfeeling.platform.interactive.response;

public enum ResponseDesc {
    OK{
        @Override
        public int getCode() {
            return 200;
        }

        @Override
        public String getMessage() {
            return "OK";
        }
    },
    NOT_EXIST {
        @Override
        public int getCode() {
            return 404;
        }

        @Override
        public String getMessage() {
            return "not exist";
        }
    },
    PASSWORD_ERROR{
        @Override
        public int getCode() {
            return 440;
        }

        @Override
        public String getMessage() {
            return "password error";
        }
    };;
    public abstract int getCode();
    public abstract String getMessage();
}
