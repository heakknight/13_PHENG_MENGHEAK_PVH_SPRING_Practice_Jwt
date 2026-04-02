package com.phengmengheak.demojwtsecurity.repository;

import com.phengmengheak.demojwtsecurity.model.entity.AppUser;
import com.phengmengheak.demojwtsecurity.model.request.AppUserRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppUserRepository {
    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "roles", column = "user_id", many = @Many(select = "getAllRolesByUserId"))
    })
    @Select("""
        SELECT * FROM app_users WHERE email = #{email}
    """)
    AppUser getUserByEmail(String email);

    @Select("""
        SELECT name FROM app_user_role aur
        INNER JOIN app_roles ar
        ON aur.role_id = ar.role_id
        WHERE user_id = #{userId}
    """)
    List<String> getAllRolesByUserId(Long userId);

    @Insert("""
        INSERT INTO app_user_role (user_id, role_id)
        VALUES (#{userId}, (SELECT role_id FROM app_roles WHERE name = #{roleName}))
    """)
    void assignRoleToUser(@Param("userId") Long userId, @Param("roleName") String roleName);

    @Select("""
                INSERT INTO app_users
                VALUES (default, #{request.username}, #{request.email}, #{request.password})
                RETURNING *
            """)
    @ResultMap("appUserMapper")
    AppUser register(@Param("request") AppUserRequest request);

    @Select("""
                SELECT * FROM app_users
                WHERE user_id = #{userId}
            """)
    @ResultMap("appUserMapper")
    AppUser getUserById(Long userId);
}