package testsystem.console;

import testsystem.model.*;
import testsystem.repository.*;
import testsystem.service.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int PAGE_SIZE = 10;

   private final AnswerRepository answerRepository = new AnswerRepository();
   private final TestRepository testRepository = new TestRepository();
   private final UserRepository userRepository = new UserRepository();
   private final QuestionRepository questionRepository = new QuestionRepository();
   private final TestAttemptRepository testAttemptRepository = new TestAttemptRepository();
   private final UserAnswerRepository userAnswerRepository = new UserAnswerRepository();

    private final TestService testService = new TestService(testRepository);
    private final UserService userService = new UserService(userRepository);
    private final QuestionService questionService = new QuestionService(questionRepository);
    private final AnswerService answerService = new AnswerService(answerRepository);
    private final TestAttemptService testAttemptService = new TestAttemptService(testAttemptRepository);
    private final UserAnswerService userAnswerService = new UserAnswerService(userAnswerRepository);

    public static void main(String[] args) {
        new ConsoleApp().run();
    }

    public void run() {
        System.out.println("=== 🧪 Тестовая система (консоль) ===");
        while (true) {
            showMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    viewTablesMenu();
                    break;
                case "2":
                    crudMenu();
                    break;
                case "3":
                    searchMenu();
                    break;
                case "4":
                    showStatistics();
                    break;
                case "5":
                    System.out.println("✅ Выход из приложения...");
                    return;
                default:
                    System.out.println("❌ Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // --- МЕНЮ ---

    private void showMainMenu() {
        System.out.println("\n--- 🔹 Главное меню ---");
        System.out.println("1. Просмотр таблиц");
        System.out.println("2. Добавить/изменить/удалить (CRUD)");
        System.out.println("3. Поиск");
        System.out.println("4. Статистика");
        System.out.println("5. Выход");
        System.out.print("Выберите пункт: ");
    }

    private void viewTablesMenu() {
        while (true) {
            System.out.println("\n--- 📋 Просмотр таблиц ---");
            System.out.println("1. Тесты");
            System.out.println("2. Пользователи");
            System.out.println("3. Вопросы");
            System.out.println("4. Ответы");
            System.out.println("5. Попытки тестирования");
            System.out.println("6. Ответы пользователей");
            System.out.println("0. Назад");
            System.out.print("Выберите таблицу: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    viewTests();
                    break;
                case "2":
                    viewUsers();
                    break;
                case "3":
                    viewQuestions();
                    break;
                case "4":
                    viewAnswers();
                    break;
                case "5":
                    viewAttempts();
                    break;
                case "6":
                    viewUserAnswers();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Неверный выбор.");
            }
        }
    }

    // --- ПРОСМОТР ТАБЛИЦ ---

    private void viewTests() {
        int total = testService.getTotalTestsCount();
        if (total == 0) {
            System.out.println("Тесты отсутствуют.");
            return;
        }
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
        System.out.println("\n=== Тесты (всего: " + total + ") ===");
        navigatePages(totalPages, (page, size) -> testService.getAllTests(page, size));
    }

    private void viewUsers() {
        int total = userService.getTotalUsersCount();
        if (total == 0) {
            System.out.println("Пользователи отсутствуют.");
            return;
        }
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
        System.out.println("\n=== Пользователи (всего: " + total + ") ===");
        navigatePages(totalPages, (page, size) -> userService.getAllUsers(page, size));
    }

    private void viewQuestions() {
        int total = questionService.getTotalQuestionsCount();
        if (total == 0) {
            System.out.println("Вопросы отсутствуют.");
            return;
        }
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
        System.out.println("\n=== Вопросы (всего: " + total + ") ===");
        navigatePages(totalPages, (page, size) -> questionService.getAllQuestions(page, size));
    }

    private void viewAnswers() {
        int total = answerService.getTotalAnswersCount();
        if (total == 0) {
            System.out.println("Ответы отсутствуют.");
            return;
        }
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
        System.out.println("\n=== Ответы (всего: " + total + ") ===");
        navigatePages(totalPages, (page, size) -> answerService.getAllAnswers(page, size));
    }

    private void viewAttempts() {
        int total = testAttemptService.getTotalAttemptsCount();
        if (total == 0) {
            System.out.println("Попытки отсутствуют.");
            return;
        }
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
        System.out.println("\n=== Попытки (всего: " + total + ") ===");
        navigatePages(totalPages, (page, size) -> testAttemptService.getAllAttempts(page, size));
    }

    private void viewUserAnswers() {
        int total = userAnswerService.getTotalUserAnswersCount();
        if (total == 0) {
            System.out.println("Ответы пользователей отсутствуют.");
            return;
        }
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
        System.out.println("\n=== Ответы пользователей (всего: " + total + ") ===");
        navigatePages(totalPages, (page, size) -> userAnswerService.getAllUserAnswers(page, size));
    }

    private <T> void navigatePages(int totalPages, PageFetcher<T> fetcher) {
        while (true) {
            System.out.print("Введите номер страницы (1-" + totalPages + ") или 0 для выхода: ");
            String input = scanner.nextLine().trim();
            try {
                int page = Integer.parseInt(input);
                if (page == 0) break;
                if (page < 1 || page > totalPages) {
                    System.out.println("⚠️ Номер страницы вне диапазона.");
                    continue;
                }
                List<T> data = fetcher.fetch(page, PAGE_SIZE);
                if (data.isEmpty()) {
                    System.out.println("Нет данных.");
                } else {
                    data.forEach(System.out::println);
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Некорректный номер страницы.");
            }
        }
    }

    // --- CRUD МЕНЮ ---

    private void crudMenu() {
        System.out.println("\n--- ✏️ CRUD операции ---");
        System.out.println("1. Тесты");
        System.out.println("2. Пользователи");
        System.out.println("3. Вопросы");
        System.out.println("4. Ответы");
        System.out.println("5. Попытки");
        System.out.println("6. Ответы пользователей");
        System.out.print("Выберите сущность: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                manageTests();
                break;
            case "2":
                manageUsers();
                break;
            case "3":
                manageQuestions();
                break;
            case "4":
                manageAnswers();
                break;
            case "5":
                manageAttempts();
                break;
            case "6":
                manageUserAnswers();
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    // --- УПРАВЛЕНИЕ ТЕСТАМИ ---

    private void manageTests() {
        System.out.println("\n--- 📝 Управление тестами ---");
        System.out.println("1. Добавить");
        System.out.println("2. Изменить");
        System.out.println("3. Удалить");
        System.out.print("Выберите действие: ");
        String op = scanner.nextLine().trim();
        switch (op) {
            case "1":
                addTest();
                break;
            case "2":
                updateTest();
                break;
            case "3":
                deleteTest();
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    private void addTest() {
        Test test = new Test();
        System.out.print("Название теста: ");
        test.setTitle(scanner.nextLine());
        System.out.print("Описание: ");
        test.setDescription(scanner.nextLine());
        System.out.print("Активен? (true/false): ");
        test.setActive(Boolean.parseBoolean(scanner.nextLine()));
        try {
            testService.createTest(test);
            System.out.println("✅ Тест добавлен.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void updateTest() {
        System.out.print("ID теста: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        Test test = new Test();
        test.setId(id);
        System.out.print("Новое название: ");
        test.setTitle(scanner.nextLine());
        System.out.print("Новое описание: ");
        test.setDescription(scanner.nextLine());
        System.out.print("Активен? (true/false): ");
        test.setActive(Boolean.parseBoolean(scanner.nextLine()));
        try {
            testService.updateTest(test);
            System.out.println("✅ Тест обновлён.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void deleteTest() {
        System.out.print("ID теста для удаления: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        System.out.print("Подтвердите удаление (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            testService.deleteTest(id);
            System.out.println("✅ Удалено.");
        }
    }

    // --- УПРАВЛЕНИЕ ПОЛЬЗОВАТЕЛЯМИ ---

    private void manageUsers() {
        System.out.println("\n--- 👤 Управление пользователями ---");
        System.out.println("1. Добавить");
        System.out.println("2. Изменить");
        System.out.println("3. Удалить");
        System.out.print("Выберите действие: ");
        String op = scanner.nextLine().trim();
        switch (op) {
            case "1":
                addUser();
                break;
            case "2":
                updateUser();
                break;
            case "3":
                deleteUser();
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    private void addUser() {
        User user = new User();
        System.out.print("Имя пользователя: ");
        user.setUsername(scanner.nextLine());
        System.out.print("Email: ");
        user.setEmail(scanner.nextLine());
        try {
            userService.createUser(user);
            System.out.println("✅ Пользователь добавлен.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void updateUser() {
        System.out.print("ID пользователя: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        User user = new User();
        user.setId(id);
        System.out.print("Новое имя: ");
        user.setUsername(scanner.nextLine());
        System.out.print("Новый email: ");
        user.setEmail(scanner.nextLine());
        try {
            userService.updateUser(user);
            System.out.println("✅ Пользователь обновлён.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.print("ID пользователя: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        System.out.print("Подтвердите удаление (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            userService.deleteUser(id);
            System.out.println("✅ Удалено.");
        }
    }

    // --- УПРАВЛЕНИЕ ВОПРОСАМИ ---

    private void manageQuestions() {
        System.out.println("\n--- ❓ Управление вопросами ---");
        System.out.println("1. Добавить");
        System.out.println("2. Изменить");
        System.out.println("3. Удалить");
        System.out.print("Выберите действие: ");
        String op = scanner.nextLine().trim();
        switch (op) {
            case "1":
                addQuestion();
                break;
            case "2":
                updateQuestion();
                break;
            case "3":
                deleteQuestion();
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    private void addQuestion() {
        Question q = new Question();
        System.out.print("ID теста: ");
        q.setTestId(parseLongOrZero(scanner.nextLine()));
        System.out.print("Текст вопроса: ");
        q.setText(scanner.nextLine());
        System.out.print("Тип (single_choice/multiple_choice/text/true_false): ");
        q.setType(scanner.nextLine());
        try {
            questionService.createQuestion(q);
            System.out.println("✅ Вопрос добавлен.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void updateQuestion() {
        System.out.print("ID вопроса: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        Question q = new Question();
        q.setId(id);
        System.out.print("ID теста: ");
        q.setTestId(parseLongOrZero(scanner.nextLine()));
        System.out.print("Новый текст: ");
        q.setText(scanner.nextLine());
        System.out.print("Новый тип: ");
        q.setType(scanner.nextLine());
        try {
            questionService.updateQuestion(q);
            System.out.println("✅ Вопрос обновлён.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void deleteQuestion() {
        System.out.print("ID вопроса: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        System.out.print("Подтвердите удаление (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            questionService.deleteQuestion(id);
            System.out.println("✅ Удалено.");
        }
    }

    // --- УПРАВЛЕНИЕ ОТВЕТАМИ ---

    private void manageAnswers() {
        System.out.println("\n--- ✅ Управление ответами ---");
        System.out.println("1. Добавить");
        System.out.println("2. Изменить");
        System.out.println("3. Удалить");
        System.out.print("Выберите действие: ");
        String op = scanner.nextLine().trim();
        switch (op) {
            case "1":
                addAnswer();
                break;
            case "2":
                updateAnswer();
                break;
            case "3":
                deleteAnswer();
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    private void addAnswer() {
        Answer a = new Answer();
        System.out.print("ID вопроса: ");
        a.setQuestionId(parseLongOrZero(scanner.nextLine()));
        System.out.print("Текст ответа: ");
        a.setText(scanner.nextLine());
        System.out.print("Правильный? (true/false): ");
        a.setCorrect(Boolean.parseBoolean(scanner.nextLine()));
        System.out.print("Баллы: ");
        a.setPoints(Integer.parseInt(scanner.nextLine()));
        try {
            answerService.createAnswer(a);
            System.out.println("✅ Ответ добавлен.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void updateAnswer() {
        System.out.print("ID ответа: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        Answer a = new Answer();
        a.setId(id);
        System.out.print("ID вопроса: ");
        a.setQuestionId(parseLongOrZero(scanner.nextLine()));
        System.out.print("Новый текст: ");
        a.setText(scanner.nextLine());
        System.out.print("Правильный? (true/false): ");
        a.setCorrect(Boolean.parseBoolean(scanner.nextLine()));
        System.out.print("Баллы: ");
        a.setPoints(Integer.parseInt(scanner.nextLine()));
        try {
            answerService.updateAnswer(a);
            System.out.println("✅ Ответ обновлён.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void deleteAnswer() {
        System.out.print("ID ответа: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        System.out.print("Подтвердите удаление (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            answerService.deleteAnswer(id);
            System.out.println("✅ Удалено.");
        }
    }

    // --- УПРАВЛЕНИЕ ПОПЫТКАМИ ---

    private void manageAttempts() {
        System.out.println("\n--- 🕒 Управление попытками ---");
        System.out.println("1. Добавить");
        System.out.println("2. Изменить");
        System.out.println("3. Удалить");
        System.out.print("Выберите действие: ");
        String op = scanner.nextLine().trim();
        switch (op) {
            case "1":
                addAttempt();
                break;
            case "2":
                updateAttempt();
                break;
            case "3":
                deleteAttempt();
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    private void addAttempt() {
        TestAttempt ta = new TestAttempt();
        System.out.print("ID пользователя: ");
        ta.setUserId(parseLongOrZero(scanner.nextLine()));
        System.out.print("ID теста: ");
        ta.setTestId(parseLongOrZero(scanner.nextLine()));
        System.out.print("Баллы: ");
        ta.setScore(Integer.parseInt(scanner.nextLine()));
        // Для упрощения — дата = сейчас
        ta.setStartedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        ta.setFinishedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        try {
            testAttemptService.createAttempt(ta);
            System.out.println("✅ Попытка добавлена.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void updateAttempt() {
        System.out.print("ID попытки: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        TestAttempt ta = new TestAttempt();
        ta.setId(id);
        System.out.print("ID пользователя: ");
        ta.setUserId(parseLongOrZero(scanner.nextLine()));
        System.out.print("ID теста: ");
        ta.setTestId(parseLongOrZero(scanner.nextLine()));
        System.out.print("Баллы: ");
        ta.setScore(Integer.parseInt(scanner.nextLine()));
        ta.setStartedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        ta.setFinishedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        try {
            testAttemptService.updateAttempt(ta);
            System.out.println("✅ Попытка обновлена.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void deleteAttempt() {
        System.out.print("ID попытки: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        System.out.print("Подтвердите удаление (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            testAttemptService.deleteAttempt(id);
            System.out.println("✅ Удалено.");
        }
    }

    // --- УПРАВЛЕНИЕ ОТВЕТАМИ ПОЛЬЗОВАТЕЛЕЙ ---

    private void manageUserAnswers() {
        System.out.println("\n--- 📝 Управление ответами пользователей ---");
        System.out.println("1. Добавить");
        System.out.println("2. Изменить");
        System.out.println("3. Удалить");
        System.out.print("Выберите действие: ");
        String op = scanner.nextLine().trim();
        switch (op) {
            case "1":
                addUserAnswer();
                break;
            case "2":
                updateUserAnswer();
                break;
            case "3":
                deleteUserAnswer();
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    private void addUserAnswer() {
        UserAnswer ua = new UserAnswer();
        System.out.print("ID попытки: ");
        ua.setAttemptId(parseLongOrZero(scanner.nextLine()));
        System.out.print("ID вопроса: ");
        ua.setQuestionId(parseLongOrZero(scanner.nextLine()));
        System.out.print("ID ответа (или оставьте пустым для текстового): ");
        String ansId = scanner.nextLine().trim();
        if (!ansId.isEmpty()) {
            ua.setAnswerId(Long.parseLong(ansId));
        }
        System.out.print("Текстовый ответ (или оставьте пустым): ");
        String textAns = scanner.nextLine();
        if (!textAns.trim().isEmpty()) {
            ua.setTextAnswer(textAns);
        }
        try {
            userAnswerService.createUserAnswer(ua);
            System.out.println("✅ Ответ пользователя добавлен.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void updateUserAnswer() {
        System.out.print("ID ответа пользователя: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        UserAnswer ua = new UserAnswer();
        ua.setId(id);
        System.out.print("ID попытки: ");
        ua.setAttemptId(parseLongOrZero(scanner.nextLine()));
        System.out.print("ID вопроса: ");
        ua.setQuestionId(parseLongOrZero(scanner.nextLine()));
        System.out.print("ID ответа (или оставьте пустым): ");
        String ansId = scanner.nextLine().trim();
        if (!ansId.isEmpty()) {
            ua.setAnswerId(Long.parseLong(ansId));
        }
        System.out.print("Текстовый ответ (или оставьте пустым): ");
        String textAns = scanner.nextLine();
        if (!textAns.trim().isEmpty()) {
            ua.setTextAnswer(textAns);
        }
        try {
            userAnswerService.updateUserAnswer(ua);
            System.out.println("✅ Ответ пользователя обновлён.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }
    }

    private void deleteUserAnswer() {
        System.out.print("ID ответа пользователя: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) return;
        System.out.print("Подтвердите удаление (y/n): ");
        if ("y".equalsIgnoreCase(scanner.nextLine())) {
            userAnswerService.deleteUserAnswer(id);
            System.out.println("✅ Удалено.");
        }
    }

    // --- ПОИСК ---

    private void searchMenu() {
        System.out.println("\n--- 🔍 Поиск ---");
        System.out.println("1. Найти тест по ID");
        System.out.println("2. Найти пользователя по ID");
        System.out.print("Выберите: ");
        String choice = scanner.nextLine().trim();

        System.out.print("Введите ID: ");
        long id = parseLongOrZero(scanner.nextLine());
        if (id <= 0) {
            System.out.println("⚠️ Некорректный ID.");
            return;
        }

        switch (choice) {
            case "1":
                searchTestById(id);
                break;
            case "2":
                searchUserById(id);
                break;
            default:
                System.out.println("❌ Неверный выбор.");
        }
    }

    private void searchTestById(long id) {
        List<Test> tests = testService.searchTests(id);
        if (tests.isEmpty()) {
            System.out.println("❌ Тест не найден.");
        } else {
            tests.forEach(System.out::println);
        }
    }

    private void searchUserById(long id) {
        List<User> users = userService.searchUsers(id);
        if (users.isEmpty()) {
            System.out.println("❌ Пользователь не найден.");
        } else {
            users.forEach(System.out::println);
        }
    }


    // --- СТАТИСТИКА ---

    private void showStatistics() {
        System.out.println("\n=== 📊 Статистика ===");
        System.out.println("🔹 Тестов: " + testService.getTotalTestsCount());
        System.out.println("🔹 Пользователей: " + userService.getTotalUsersCount());
        System.out.println("🔹 Вопросов: " + questionService.getTotalQuestionsCount());
        System.out.println("🔹 Ответов: " + answerService.getTotalAnswersCount());
        System.out.println("🔹 Попыток: " + testAttemptService.getTotalAttemptsCount());
        System.out.println("🔹 Ответов пользователей: " + userAnswerService.getTotalUserAnswersCount());
    }

    // --- УТИЛИТЫ ---

    private long parseLongOrZero(String input) {
        try {
            return Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Некорректный ID.");
            return 0;
        }
    }

    @FunctionalInterface
    interface PageFetcher<T> {
        List<T> fetch(int page, int size);
    }
}