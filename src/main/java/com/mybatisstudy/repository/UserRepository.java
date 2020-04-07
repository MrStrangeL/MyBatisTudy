package com.mybatisstudy.repository;

import com.mybatisstudy.entity.User;

import java.util.Map;

public interface UserRepository {
    int addUser(User user);
    int deleteUser(Integer id);
    int updateUser(User user);
    User selectUserById(Integer id);

    User findUserByIdInt(int id);
    User findUserByIdInteger(Integer id);
    User findUserByName(String username);
    User findUserByIdAndName(Integer id,String username);
    User findUserByUser(User user);

    int countUserInt();
    Integer countUserInteger();
    String findNameById(Integer id);
    Map findNameAndAgeById(Integer id);
}
