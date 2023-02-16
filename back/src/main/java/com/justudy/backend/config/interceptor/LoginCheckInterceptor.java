package com.justudy.backend.config.interceptor;

import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        if (session != null && loginSequence != null && isLoggedIn(loginSequence)) {
            log.info("LoginCheckInterceptor return true");
            return true;
        }

        if (request.getMethod().equals("OPTIONS")) {
            log.debug("If request method is options, return true");
            return true;
        }
        log.info("LoginCheckInterceptor return false");
        throw new NotLogin();
    }

    private boolean isLoggedIn(Long loginSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());
        return true;
    }
}
