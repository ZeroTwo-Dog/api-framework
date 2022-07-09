package kr.co.jh.framework

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import org.springframework.boot.context.properties.bind.Bindable.listOf
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
@Transactional
class InitService (private val userRepository: UserRepository){

    //FIXME: 그래프큐엘 테스트용으로 데이터 밀어넣기 추후에는 mysql 파일로 데이터 초기화 하는걸로 변경예정
    fun dbInit() {
        val user1 = User("wl507","wl5407@gmail.com");
        val user2 = User("283po1","283po1@naver.com");
        val user3 = User("wls507","wls5047@naver.com");

        val list = mutableListOf<User>(user1, user2, user3)


        userRepository.saveAll(list)


    }
}
