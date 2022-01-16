import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StringHandler {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Oleksandr");
        names.add("Ilya");
        names.add("Artem");
        names.add("Yulia");
        names.add("Lesli");

        // TASK - 1

        String oddNumbers = names.stream()
                .map(name -> (names.indexOf(name) + 1) + ". " + name)
                .filter(name -> name.charAt(0) % 2 != 0)
                .reduce("", (acc, name) -> acc + ", " + name).replaceFirst(",", "");
        System.out.println("Task 1: " + oddNumbers);

        // TASK - 2

        List<String> sortNames = names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Task 2: " + sortNames);

        // TASK - 3

        List<String> numbers = Arrays.asList("1, 2, 0", "4, 5");
        List<Integer> result = Arrays.stream(String.join(", ", numbers)
                .split(", "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Task 3: " + result);

        // TASK - 4

        List<Long> myRandomNumbers = randomNumbers(25214903917L, 11, 2 ^ 48).limit(7).collect(Collectors.toList());
        System.out.println("Task 4: " + myRandomNumbers);

        // TASK - 5

        Stream<Integer> firstStream = Stream.of(1, 3, 5, 7, 9, 11, 13);
        Stream<Integer> secondStream = Stream.of(2, 4, 6, 8, 10);

        List<Integer> myZip = zip(firstStream, secondStream).collect(Collectors.toList());
        System.out.println("Task 5: " + myZip);
    }

    public static Stream<Long> randomNumbers(long a, long c, long m) {
        return Stream.iterate(a, (x) -> (a*x+c)%m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        ArrayList<T> concatStream = new ArrayList<>();

        Iterator<T> iterFirst = first.collect(Collectors.toList()).iterator();
        Iterator<T> iterSecond = second.collect(Collectors.toList()).iterator();
        while(iterFirst.hasNext() && iterSecond.hasNext()){
            concatStream.add(iterFirst.next());
            concatStream.add(iterSecond.next());
        }

        return concatStream.stream();
    }
}
