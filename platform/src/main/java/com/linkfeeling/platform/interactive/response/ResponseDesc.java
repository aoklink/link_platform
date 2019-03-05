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
    ALREADY_EXIST {
        @Override
        public int getCode() {
            return 414;
        }

        @Override
        public String getMessage() {
            return "already exist";
        }
    },
    INVALID_REQUEST {
        @Override
        public int getCode() {
            return 424;
        }

        @Override
        public String getMessage() {
            return "invalid request";
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
