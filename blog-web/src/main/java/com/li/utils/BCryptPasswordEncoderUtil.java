package com.li.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

/**
 * BCrypt手动加盐
 */
public class BCryptPasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        System.out.println("请输入要加盐的原密码");
        Scanner scanner=new Scanner(System.in);
        while (true) {
            if (scanner.hasNext()) {
                String password = scanner.next();
                System.out.println(bCryptPasswordEncoder.encode(password));
            }
        }
    }
}
