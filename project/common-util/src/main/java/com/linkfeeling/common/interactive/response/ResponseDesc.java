package com.linkfeeling.common.interactive.response;

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
    UNAUTHORIZED{
        @Override
        public int getCode() {
            return 401;
        }

        @Override
        public String getMessage() {
            return "unauthorized";
        }
    },
    ACCESS_DENIED {
        @Override
        public int getCode() {
            return 403;
        }

        @Override
        public String getMessage() {
            return "access denied";
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
    },
    NO_PERMISSION{
        @Override
        public int getCode() {
            return 441;
        }

        @Override
        public String getMessage() {
            return "no permission";
        }
    },

    SERVICE_ERROR{
        @Override
        public int getCode() {
            return 500;
        }

        @Override
        public String getMessage() {
            return "service error";
        }
    };
    public abstract int getCode();
    public abstract String getMessage();
}
