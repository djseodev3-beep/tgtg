package com.spring.tgtg;

import com.spring.tgtg.product.repository.ProductRepository;
import com.spring.tgtg.reservation.repository.ReservationRepository;
import com.spring.tgtg.review.domain.Review;
import com.spring.tgtg.review.repository.ReviewRepository;
import com.spring.tgtg.store.domain.Store;
import com.spring.tgtg.store.repository.StoreRepository;
import com.spring.tgtg.user.domain.User;
import com.spring.tgtg.user.domain.UserRole;
import com.spring.tgtg.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalTime;

@ActiveProfiles("test") // 테스트 시 어떤 profile로 설정할지 구성.
@DataJpaTest // Spring 에서 JPA 관련 테스트 설정만 로드
@TestPropertySource(locations = "classpath:application-test.yml") // 새로운 설정 Source 정의하고 프로퍼티의 값을 오버라이딩 한다.
//자동으로 EmbededDatabase를 사용하기 때문에 내가 설정한 설정 값들을 사용할 수 없다.
//이 설정을 replace 해서 해당 설정이 동작하지 않고, 내가 설정한 설정 파일 대로 Bean 등록.
// yml, properties 에서 내가 설정한 설정 파일대로 사용하려면 NONE 옵션을 반드시 사용.
// !반드시 주의. 기본 H2가 아닌 내가 설정한 H2 설정 하려면 @AutoConfigureTestDatabase이 옵션을 반드시 추가 해야된다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository  reviewRepository;

    static User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("test1234@gmail.com")
                .password("12344")
                .name("test")
                .phoneNumber("01012345678")
                .role(UserRole.CUSTOMER)
                .build();
    }

    @Test
    void userSave(){
        userRepository.save(user);
    }

    @Test
    void storeSave(){
        userRepository.save(user);

        storeRepository.save(Store.builder()
                .owner(user)
                .name("빵집")
                .address("인천광역시 부평구 청안로")
                .phoneNumber("01012345678")
                .description("남은 빵 팝니다")
                .openTime(LocalTime.now())
                .closeTime(LocalTime.now())
                .latitude(57.11)
                .longitude(123.11)
        .build());
    }

}
