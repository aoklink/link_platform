package com.linkfeeling.platform.controller;

public interface ActionContract {
    interface OPERATE{
        String ADD = "/add";
        String ADD_ME = "/add_me";
        String DELETE = "/delete";
        String DELETE_ME = "/delete_me";
        /**
         * Edit data
         */
        String UPDATE = "/update";
        String UPDATE_ME = "/update_me";
        /**
         * query for appointed item
         */
        String GET = "/get";

        String GET_BIND = "/get_bind";

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
