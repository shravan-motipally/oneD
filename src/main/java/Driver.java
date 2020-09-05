import utils.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

import static java.lang.String.format;
import static utils.Constants.*;

public class Driver {
    private static Integer count = 0;

    public static void main(String[] args) {
        welcome();

        Direction dir = getDirection();

        while (count > -100 && count < 100) {
            switch (dir) {
                case LEFT:
                    count -= getSpotsToMove();
                    System.out.println(LEFT_MESSAGE);
                    break;
                case RIGHT:
                    count += getSpotsToMove();
                    System.out.println(RIGHT_MESSAGE);
                    break;
            }
            System.out.println(format(STATUS_MESSAGE, count));
        }
    }

    private static void welcome() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println();
    }

    private static Direction getDirection() {
        Function<String, Boolean> directionValidationFunction = (str) ->
                str != null && (str.equals("-1") || str.equals("1"));
        String input = getInputWithRetryValidation(WHICH_WAY, RETRY_MESSAGE + WHICH_WAY, directionValidationFunction);
        if (input == null) {
            throw new RuntimeException(UNEXPECTED_ERROR);
        }
        return Direction.from(input);
    }

    private static Integer getSpotsToMove() {
        Function<String, Boolean> spotsToMoveValidationFunction = (str) -> {
            if (str == null) {
                return false;
            }
            try {
                int paces = Integer.parseInt(str);
                if (paces >= 1 && paces <= 10) {
                    return true;
                }

            } catch (NumberFormatException nfe) {
                return false;
            }
            return false;
        };
        String input = getInputWithRetryValidation(HOW_MANY_PACES, RETRY_MESSAGE + HOW_MANY_PACES, spotsToMoveValidationFunction);
        if (input == null) {
            throw new RuntimeException(UNEXPECTED_ERROR);
        }
        return Integer.parseInt(input);
    }

    private static String getInputWithRetryValidation(String getInputMessage, String retryMessage, Function<String, Boolean> inputValidationFunction) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            System.out.println(getInputMessage);
            while (true) {
                input = reader.readLine();
                if (validateInput(input, inputValidationFunction)) {
                    break;
                } else {
                    System.out.println(retryMessage);
                }
            }
            return input;
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            return null;
        }
    }

    private static boolean validateInput(String str, Function<String, Boolean> validationFunction) {
        return validationFunction.apply(str);
    }
}