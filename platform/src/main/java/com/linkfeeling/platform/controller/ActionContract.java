package com.linkfeeling.platform.controller;

public interface ActionContract {
    interface OPERATE{
        String ADD = "/add";
        String DELETE = "/delete";
        /**
         * Edit data
         */
        String UPDATE = "/update";
        /**
         * query for appointed item
         */
        String GET = "/get";
        /**
         * query all item
         */
        String LIST = "/list";

        String VERIFY = "/verify";
    }
}
