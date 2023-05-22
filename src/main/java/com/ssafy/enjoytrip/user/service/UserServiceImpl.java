package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.commons.exception.ErrorCode;
import com.ssafy.enjoytrip.user.dao.UserDao;
import com.ssafy.enjoytrip.user.dto.User;
import com.ssafy.enjoytrip.user.exception.InvalidPasswordException;
import com.ssafy.enjoytrip.user.exception.UserDuplicatedEmailException;
import com.ssafy.enjoytrip.user.exception.UserDuplicatedNicknameException;
import com.ssafy.enjoytrip.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String rawPassword) {
        User user = userDao.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("로그인 실패");
        }
        if (!passwordCheck(rawPassword, user.getPassword())) {
            throw new InvalidPasswordException("로그인 실패");
        }
        return user;
    }

    private boolean passwordCheck(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Transactional
    @Override
    public void signup(User user) {
        checkDupEmail(user.getEmail());
        checkDupNickname(user.getNickname());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDao.insert(user);
    }

    @Transactional
    @Override
    public int modify(User userInfo) {
        checkDupNickname(userInfo.getNickname());
        return userDao.update(userInfo);
    }

    @Transactional
    @Override
    public int dropOutById(int id, String pwd) {
        User user = userDao.findById(id);
        if (!passwordCheck(pwd, user.getPassword())) {
            throw new InvalidPasswordException("비밀번호 불일치");
        }
        return userDao.deleteById(id);
    }


    @Override
    public void checkDupEmail(String email) {
        int dupCnt = userDao.existsByEmail(email);
        if (dupCnt > 0) {
            throw new UserDuplicatedEmailException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    @Override
    public void checkDupNickname(String nickname) {
        int dupCnt = userDao.existsByNickname(nickname);
        if (dupCnt > 0) {
            throw new UserDuplicatedNicknameException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

    @Override
    public User getUserInfo(int id) {
        return userDao.findById(id);
    }


}
