package app.habit.dto.executionintentiondto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExecutionIntentionRs {

    private Long executionIntentionId;
    private String feedback;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateExecutionIntentionRs that = (CreateExecutionIntentionRs) o;
        return Objects.equals(executionIntentionId, that.executionIntentionId) && Objects.equals(
                feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executionIntentionId, feedback);
    }
}