package ru.netology;

import lombok.RequiredArgsConstructor;
import lombok.Value;

    @Value
    @RequiredArgsConstructor

    public class UserInfo {



        private String login;
        private String password;
        private String status;


    }

