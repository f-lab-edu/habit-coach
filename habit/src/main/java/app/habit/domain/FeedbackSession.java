package app.habit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class FeedbackSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_session_id")
    private Long id;

    @Getter
    private String sessionKey;
    @Getter
    private String question;

    // 4 번째 api 부터 필요한 필드
    @Getter
    private String answer;

    @Column(name = "feedback_module_id")
    private Long feedbackModuleId;

    public FeedbackSession(String sessionKey, String question, String answer) {
        this.sessionKey = sessionKey;
        this.question = question;
        this.answer = answer;
    }

    public FeedbackSession(String sessionKey, String question) {
        this.sessionKey = sessionKey;
        this.question = question;
    }

    public void addAnswer(String answer) {
        this.answer = answer;
    }

    public void addFeedbackModuleId(Long feedbackModuleId) {
        this.feedbackModuleId = feedbackModuleId;
    }
}
