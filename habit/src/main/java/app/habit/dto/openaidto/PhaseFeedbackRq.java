package app.habit.dto.openaidto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhaseFeedbackRq {

    private String habitFormingPhaseType;
    private long feedbackModuleId;
    private List<PhaseAnswerRq> phaseAnswers;
}

