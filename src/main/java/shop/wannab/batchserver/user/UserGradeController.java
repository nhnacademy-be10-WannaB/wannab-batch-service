package shop.wannab.batchserver.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.wannab.batchserver.user.domain.entity.UserGrade;
import shop.wannab.batchserver.user.repository.UserGradeRepository;

@RestController
@RequiredArgsConstructor
public class UserGradeController {
    private final UserGradeRepository userGradeRepository;

    @GetMapping("/grades")
    List<UserGrade> getGrades() {
        return userGradeRepository.findAll();
    }
}
