package Questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionGenerator {

    public static List<Question> generateQuestions() {
        List<Question> questions = new ArrayList<>();
        List<Question> multipleChoiceQuestions = new ArrayList<>();
        List<Question> dragNDropQuestions = new ArrayList<>();
        List<Question> fillInTheBlankQuestions = new ArrayList<>();
        List<Question> codeComparisonQuestions = new ArrayList<>();
        List<Question> codeDebuggingQuestions = new ArrayList<>();
        List<Question> algorithmTracingQuestions = new ArrayList<>();
        List<Question> trueFalseQuestions = new ArrayList<>();
        



        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1001,
                "Which of the following is NOT a valid Java identifier?",
                Arrays.asList("_myVariable", "$value", "123name", "userName"),
                "123name"
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1002,
                "Which of the following is the correct syntax for a for loop in Java?",
                Arrays.asList(
                        "for (int i = 0; i < 5; i++) { ... }",
                        "for int i = 0; i < 5; i++ { ... }",
                        "for (int i = 0; i < 5; i++) { ... } else { ... }",
                        "for (i = 0; i < 5; i++) { ... }"
                ),
                "for (int i = 0; i < 5; i++) { ... }"
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1003,
                "What does the break statement do in a loop?",
                Arrays.asList(
                        "Ends the current iteration and continues to the next iteration.",
                        "Ends the loop entirely.",
                        "Skips a specified number of iterations.",
                        "Restarts the loop from the beginning."
                ),
                "Ends the loop entirely."
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1004,
                "Which of the following is NOT a valid access modifier in Java?",
                Arrays.asList("private", "protected", "default", "public"),
                "default"
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1005,
                "Which sorting algorithm has the best-case performance for already sorted data?",
                Arrays.asList("Merge Sort", "Quick Sort", "Bubble Sort", "Insertion Sort"),
                "Insertion Sort"
        );

        addMultipleChoiceQuestion(
                trueFalseQuestions,
                2001,
                "In Java, a static method can directly access instance variables of its class.",
                Arrays.asList("True", "False"),
                "False"
        );
        
        addMultipleChoiceQuestion(
                trueFalseQuestions,
                2002,
                "A `finally` block can execute even after a return statement in a `try` block.",
                Arrays.asList("True", "False"),
                "True"
        );
        
        addMultipleChoiceQuestion(
                trueFalseQuestions,
                2003,
                "An abstract class in Java can be instantiated.",
                Arrays.asList("True", "False"),
                "False"
        );
        
        addMultipleChoiceQuestion(
                trueFalseQuestions,
                2004,
                "If a method in a subclass has the same name, return type, and parameter list as a method in its superclass, it is considered method overriding.",
                Arrays.asList("True", "False"),
                "True"
        );
        
        addMultipleChoiceQuestion(
                trueFalseQuestions,
                2005,
                "A race condition can occur if two threads access a non-synchronized method simultaneously.",
                Arrays.asList("True", "False"),
                "True"
        );
        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1006,
                "Which of the following approaches is more efficient for inserting an element into the middle of an array?",
                Arrays.asList(
                        "Create a new array, copy elements to the new array, and insert the element at the middle.",
                        "Shift all elements after the insertion point by one position and insert the element.",
                        "Insert the element at the middle and shift the array to the right.",
                        "Add the element to the middle of the array directly without shifting any elements."
                ),
                "Shift all elements after the insertion point by one position and insert the element."
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1007,
                "Which of the following algorithms is the most efficient for searching an element in a sorted array?",
                Arrays.asList(
                        "Linear Search",
                        "Binary Search",
                        "Jump Search",
                        "Exponential Search"
                ),
                "Binary Search"
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1008,
                "Which approach is more efficient for finding the maximum value in an unsorted array?",
                Arrays.asList(
                        "Iterate through the entire array once and track the maximum value.",
                        "Sort the array and return the last element.",
                        "Use a divide and conquer approach to find the maximum.",
                        "Check every pair of elements and find the largest."
                ),
                "Iterate through the entire array once and track the maximum value."
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1009,
                "Which of the following sorting algorithms is the most efficient in the worst case?",
                Arrays.asList(
                        "Quick Sort",
                        "Merge Sort",
                        "Bubble Sort",
                        "Insertion Sort"
                ),
                "Merge Sort"
        );

        addMultipleChoiceQuestion(
                multipleChoiceQuestions,
                1010,
                "Which of the following methods is more efficient for reversing a string?",
                Arrays.asList(
                        "Iterate through the string from end to start and build a new string.",
                        "Convert the string to a character array, reverse the array, and convert it back to a string.",
                        "Use a stack to push characters and then pop them to reverse the string.",
                        "Create a new string by appending characters from the end to the start."
                ),
                "Iterate through the string from end to start and build a new string."
        );
        addMultipleChoiceQuestion(
                codeComparisonQuestions,
                3005,
                "Which of the following code snippets is more efficient for searching an element in an unsorted array?",
                Arrays.asList(
                        "for (int i = 0; i < arr.length; i++) { if (arr[i] == target) return true; } return false;",
                        "for (int i = 0; i < arr.length / 2; i++) { if (arr[i] == target) return true; } return false;",
                        "for (int i = 0; i < arr.length; i++) { if (arr[i] == target) return true; } return false;",
                        "for (int i = arr.length - 1; i >= 0; i--) { if (arr[i] == target) return true; } return false;"
                ),
                "for (int i = 0; i < arr.length; i++) { if (arr[i] == target) return true; } return false;"
        );

        addMultipleChoiceQuestion(
                codeComparisonQuestions,
                3006,
                "Which code snippet will correctly merge two sorted arrays into one sorted array?",
                Arrays.asList(
                        "int[] merged = new int[arr1.length + arr2.length]; for (int i = 0; i < arr1.length; i++) merged[i] = arr1[i]; for (int i = 0; i < arr2.length; i++) merged[i + arr1.length] = arr2[i];",
                        "int[] merged = new int[arr1.length + arr2.length]; System.arraycopy(arr1, 0, merged, 0, arr1.length); System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);",
                        "int[] merged = new int[arr1.length + arr2.length]; merge(arr1, arr2);",
                        "int[] merged = new int[arr1.length + arr2.length]; for (int i = 0; i < arr2.length; i++) merged[i] = arr2[i]; for (int i = 0; i < arr1.length; i++) merged[arr2.length + i] = arr1[i];"
                ),
                "int[] merged = new int[arr1.length + arr2.length]; System.arraycopy(arr1, 0, merged, 0, arr1.length); System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);"
        );

        addMultipleChoiceQuestion(
                codeComparisonQuestions,
                3007,
                "Which of the following code snippets correctly handles an exception and continues the program execution?",
                Arrays.asList(
                        "try { int x = 1 / 0; } catch (ArithmeticException e) { System.out.println('Error'); } finally { System.out.println('Done'); }",
                        "try { int x = 1 / 0; } catch (ArithmeticException e) { break; } finally { System.out.println('Done'); }",
                        "try { int x = 1 / 0; } catch (ArithmeticException e) { return; } finally { System.out.println('Done'); }",
                        "try { int x = 1 / 0; } catch (ArithmeticException e) { System.out.println('Handled'); } finally { System.out.println('Done'); }"
                ),
                "try { int x = 1 / 0; } catch (ArithmeticException e) { System.out.println('Error'); } finally { System.out.println('Done'); }"
        );

        addMultipleChoiceQuestion(
                codeComparisonQuestions,
                3008,
                "Which of the following code snippets correctly implements a `HashMap` with a custom key type in Java?",
                Arrays.asList(
                        "HashMap<MyClass, Integer> map = new HashMap<>(); map.put(new MyClass(), 1);",
                        "HashMap<MyClass, Integer> map = new HashMap<>(); map.put(1, new MyClass());",
                        "HashMap<MyClass, Integer> map = new HashMap<>(); map.put(1, 1);",
                        "HashMap<MyClass, Integer> map = new HashMap<>(); map.put(1, 1); map.put(new MyClass(), 1);"
                ),
                "HashMap<MyClass, Integer> map = new HashMap<>(); map.put(new MyClass(), 1);"
        );

        addMultipleChoiceQuestion(
                codeComparisonQuestions,
                3009,
                "Which of the following code snippets correctly implements a thread-safe counter using `synchronized` in Java?",
                Arrays.asList(
                        "public synchronized void increment() { count++; }",
                        "public void increment() { synchronized(this) { count++; } }",
                        "public void increment() { synchronized(count) { count++; } }",
                        "public synchronized void increment() { synchronized(this) { count++; } }"
                ),
                "public synchronized void increment() { count++; }"
        );

        addCodeDebuggingQuestion(
                codeDebuggingQuestions,
                4001,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Change 'i++' to 'i--' to iterate backward.",
                        "Change the condition to 'i > 0'.",
                        "Change 'i < arr.length' to 'i <= arr.length'.",
                        "Change 'arr[i]' to 'arr[i - 1]'."
                ),
                "int[] arr = {1, 2, 3, 4, 5};\nfor (int i = 0; i < arr.length; i++) {\n    System.out.println(arr[i]);\n}",
                "int[] arr = {1, 2, 3, 4, 5};\nfor (int i = 0; i < arr.length - 1; i++) {\n    System.out.println(arr[i]);\n}"
        );

        addCodeDebuggingQuestion(
                codeDebuggingQuestions,
                4002,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Change 'i' to 'i + 1' to correctly access the next element.",
                        "Change 'i + 1' to 'i' to avoid accessing an out-of-bounds index.",
                        "Ensure 'i' starts from 1 instead of 0.",
                        "Change the loop condition to 'i < arr.length' instead of 'i <= arr.length'."
                ),
                "int[] arr = {10, 20, 30, 40};\nfor (int i = 0; i <= arr.length; i++) {\n    System.out.println(arr[i]);\n}",
                "int[] arr = {10, 20, 30, 40};\nfor (int i = 0; i < arr.length; i++) {\n    System.out.println(arr[i]);\n}"
        );

        addCodeDebuggingQuestion(
                codeDebuggingQuestions,
                4003,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Change 'x = y' to 'x == y' to perform comparison.",
                        "Add a semicolon at the end of the if statement.",
                        "Replace 'x' with 'y' in the if condition.",
                        "Use '== 0' instead of '== y' for comparing with zero."
                ),
                "int x = 5, y = 10;\nif (x = y) {\n    System.out.println('Equal');\n}",
                "int x = 5, y = 10;\nif (x == y) {\n    System.out.println('Equal');\n}"
        );

        addCodeDebuggingQuestion(
                codeDebuggingQuestions,
                4004,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Replace 'while' with 'for' to ensure the loop executes a fixed number of times.",
                        "Add a break statement to prevent an infinite loop.",
                        "Change the condition to 'i < 10' to limit the loop execution.",
                        "Change the increment inside the loop to avoid an infinite loop."
                ),
                "int i = 0;\nwhile (true) {\n    System.out.println(i);\n    i++;\n}",
                "int i = 0;\nwhile (i < 10) {\n    System.out.println(i);\n    i++;\n}"
        );

        addCodeDebuggingQuestion(
                codeDebuggingQuestions,
                4005,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Use a synchronized block to prevent race condition.",
                        "Change 'count++' to 'count--' to correctly decrement.",
                        "Replace 'static' with 'synchronized' to ensure thread safety.",
                        "Add 'volatile' to the 'count' variable declaration."
                ),
                "public class Counter {\n    private static int count = 0;\n    public void increment() {\n        count++;\n    }\n    public void decrement() {\n        count--;\n    }\n}",
                "public class Counter {\n    private static int count = 0;\n    public synchronized void increment() {\n        count++;\n    }\n    public synchronized void decrement() {\n        count--;\n    }\n}"
        );

        addCodeDebuggingQuestion(
                questions,
                4006,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Change 'i++' to 'i--' to iterate backward.",
                        "Change the condition to 'i > 0'.",
                        "Change 'i < arr.length' to 'i <= arr.length'.",
                        "Change 'arr[i]' to 'arr[i - 1]'."
                ),
                "int[] arr = {1, 2, 3, 4, 5};\nfor (int i = 0; i < arr.length; i++) {\n    System.out.println(arr[i]);\n}",
                "int[] arr = {1, 2, 3, 4, 5};\nfor (int i = 0; i < arr.length - 1; i++) {\n    System.out.println(arr[i]);\n}"
        );

        addCodeDebuggingQuestion(
                questions,
                4007,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Change 'i' to 'i + 1' to correctly access the next element.",
                        "Change 'i + 1' to 'i' to avoid accessing an out-of-bounds index.",
                        "Ensure 'i' starts from 1 instead of 0.",
                        "Change the loop condition to 'i < arr.length' instead of 'i <= arr.length'."
                ),
                "int[] arr = {10, 20, 30, 40};\nfor (int i = 0; i <= arr.length; i++) {\n    System.out.println(arr[i]);\n}",
                "int[] arr = {10, 20, 30, 40};\nfor (int i = 0; i < arr.length; i++) {\n    System.out.println(arr[i]);\n}"
        );

        addCodeDebuggingQuestion(
                questions,
                4008,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Change 'x = y' to 'x == y' to perform comparison.",
                        "Add a semicolon at the end of the if statement.",
                        "Replace 'x' with 'y' in the if condition.",
                        "Use '== 0' instead of '== y' for comparing with zero."
                ),
                "int x = 5, y = 10;\nif (x = y) {\n    System.out.println('Equal');\n}",
                "int x = 5, y = 10;\nif (x == y) {\n    System.out.println('Equal');\n}"
        );

        addCodeDebuggingQuestion(
                questions,
                4009,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Replace 'while' with 'for' to ensure the loop executes a fixed number of times.",
                        "Add a break statement to prevent an infinite loop.",
                        "Change the condition to 'i < 10' to limit the loop execution.",
                        "Change the increment inside the loop to avoid an infinite loop."
                ),
                "int i = 0;\nwhile (true) {\n    System.out.println(i);\n    i++;\n}",
                "int i = 0;\nwhile (i < 10) {\n    System.out.println(i);\n    i++;\n}"
        );

        addCodeDebuggingQuestion(
                questions,
                4010,
                "Identify the bug in the following code snippet and select the correct fix.",
                Arrays.asList(
                        "Use a synchronized block to prevent race condition.",
                        "Change 'count++' to 'count--' to correctly decrement.",
                        "Replace 'static' with 'synchronized' to ensure thread safety.",
                        "Add 'volatile' to the 'count' variable declaration."
                ),
                "public class Counter {\n    private static int count = 0;\n    public void increment() {\n        count++;\n    }\n    public void decrement() {\n        count--;\n    }\n}",
                "public class Counter {\n    private static int count = 0;\n    public synchronized void increment() {\n        count++;\n    }\n    public synchronized void decrement() {\n        count--;\n    }\n}"
        );

        addAlgorithmTracingQuestion(
                algorithmTracingQuestions,
                5001,
                "What is the output of the following algorithm?",
                "int[] arr = {1, 2, 3, 4, 5};\n"
                + "int sum = 0;\n"
                + "for (int i = 0; i < arr.length; i++) {\n"
                + "    sum += arr[i];\n"
                + "}\n"
                + "return sum;",
                Arrays.asList("15", "10", "5", "0"),
                "15"
        );

       
        addAlgorithmTracingQuestion(
                algorithmTracingQuestions,
                5002,
                "What is the output of the following algorithm?",
                "int factorial(int n) {\n"
                + "    if (n == 0) return 1;\n"
                + "    return n * factorial(n - 1);\n"
                + "}\n"
                + "return factorial(5);",
                Arrays.asList("120", "60", "24", "5"),
                "120"
        );

        
        addAlgorithmTracingQuestion(
                algorithmTracingQuestions,
                5003,
                "What is the output of the following algorithm?", """
                                                                  int fibonacci(int n) {
                                                                      if (n <= 1) return n;
                                                                      return fibonacci(n - 1) + fibonacci(n - 2);
                                                                  }
                                                                  return fibonacci(6);""",
                Arrays.asList("8", "13", "5", "3"),
                "8"
        );

       
        addAlgorithmTracingQuestion(
                algorithmTracingQuestions,
                5004,
                "What is the array after the first iteration of the bubble sort algorithm?", """
                                                                                             int[] arr = {5, 3, 8, 4, 2};
                                                                                             for (int i = 0; i < arr.length - 1; i++) {
                                                                                                 for (int j = 0; j < arr.length - i - 1; j++) {
                                                                                                     if (arr[j] > arr[j + 1]) {
                                                                                                         int temp = arr[j];
                                                                                                         arr[j] = arr[j + 1];
                                                                                                         arr[j + 1] = temp;
                                                                                                     }
                                                                                                 }
                                                                                             }
                                                                                             return arr;""",
                Arrays.asList("{3, 5, 4, 2, 8}", "{3, 4, 5, 2, 8}", "{5, 3, 8, 4, 2}", "{5, 3, 4, 2, 8}"),
                "{3, 5, 4, 2, 8}"
        );

        
        addAlgorithmTracingQuestion(
                algorithmTracingQuestions,
                5005,
                "What is the output of the following binary search algorithm?", """
                                                                                int binarySearch(int[] arr, int target) {
                                                                                    int left = 0, right = arr.length - 1;
                                                                                    while (left <= right) {
                                                                                        int mid = left + (right - left) / 2;
                                                                                        if (arr[mid] == target) return mid;
                                                                                        if (arr[mid] < target) left = mid + 1;
                                                                                        else right = mid - 1;
                                                                                    }
                                                                                    return -1;
                                                                                }
                                                                                int[] arr = {1, 3, 5, 7, 9};
                                                                                return binarySearch(arr, 7);""",
                Arrays.asList("3", "2", "4", "-1"),
                "3"
        );

        questions.addAll(multipleChoiceQuestions);
        questions.addAll(dragNDropQuestions);
        questions.addAll(algorithmTracingQuestions);
        questions.addAll(codeComparisonQuestions);
        questions.addAll(codeDebuggingQuestions);
        questions.addAll(trueFalseQuestions);
        questions.addAll(fillInTheBlankQuestions);



        return questions;
    }

    private static void addMultipleChoiceQuestion(List<Question> questions, int id, String questionText, List<String> options, String correctAnswer) {
        questions.add(new MultipleChoiceQuestion(id, questionText, options, correctAnswer));
    }

    private static void addCodeDebuggingQuestion(List<Question> questions, int id, String questionText, List<String> options, String codeSnippet, String correctAnswer) {
        questions.add(new CodeDebuggingQuestion(id, questionText, options, codeSnippet, correctAnswer));
    }

    private static void addAlgorithmTracingQuestion(List<Question> questions, int id, String questionText, String algorithmCode, List<String> options, String correctAnswer) {

        questions.add(new AlgorithmTracingQuestion(id, questionText, algorithmCode, options, correctAnswer));
    }
}
