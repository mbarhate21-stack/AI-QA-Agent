package AI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AITestCasePrioritizer {

    public List<AITestCase> prioritize(
            List<AITestCase> testCases) {

        List<AITestCase> prioritizedTestCases =
                new ArrayList<>(testCases);

        prioritizedTestCases.sort(
                Comparator.comparingInt(
                        testCase ->
                                getPriorityValue(testCase.type)
                )
        );

        return prioritizedTestCases;
    }

    public String getPriority(TestCaseType type) {

        return switch (type) {

            case SECURITY -> "P1";

            case NEGATIVE -> "P2";

            case BOUNDARY -> "P3";

            case POSITIVE -> "P4";
        };
    }

    public int getPriorityValue(TestCaseType type) {

        return switch (type) {

            case SECURITY -> 1;

            case NEGATIVE -> 2;

            case BOUNDARY -> 3;

            case POSITIVE -> 4;
        };
    }
}