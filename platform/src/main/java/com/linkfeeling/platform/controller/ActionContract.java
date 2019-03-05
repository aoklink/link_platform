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
         * query for appointed item
         *
         */
        String GET_ME = "/get_me";
        /**
         * query all item
         */
        String LIST = "/list";

        /**
         * query all item belong to me
         */
        String LIST_ME = "/list_me";

        /**
         * query all item
         */
        String LIST_ALL = "/list_all";

        /**
         * query all item  belong to my group
         */
        String LIST_GROUP = "/list_group";

        String VERIFY = "/verify";
    }
}
