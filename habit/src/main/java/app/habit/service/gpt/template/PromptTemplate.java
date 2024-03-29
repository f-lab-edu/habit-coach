package app.habit.service.gpt.template;

import static app.habit.service.gpt.prompt.PromptExample.HABIT_PRE_QUESTION_PROMPT;

public enum PromptTemplate {

    HABIT_PRE_QUESTION("사전 질문") {
        @Override
        public boolean supports(String type) {return this.name.equals(type);}

        @Override
        public String createPromptWith(String prompt) {
            return HABIT_PRE_QUESTION_PROMPT;
        }
    };

    protected final String name;

    PromptTemplate(String name) {
        this.name = name;
    }

    public abstract boolean supports(String type);

    public abstract String createPromptWith(String prompt);
}
