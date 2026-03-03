import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringFile {

    public static void main(String[] args) {

        String sample = "Hello, Jainish!, Playwright!, Kapadia!, How are you!";
        System.out.println("Original String: " + String.join(",", sample.split(",")));

        String sample2 = "Mrs, JinishaKapadia! MRI! Company!, Working!";
        System.out.println("Original String: " + String.join(",", sample2.split(" ")));

        String ContractNumber = "Test1 Test2 Test3 Test4 Test5 Test6 Test7 Test8 Test9 Test10 Test11 Test12 Test13 Test14 Test15";
        System.out.println("Original String Data: " + String.join(",", ContractNumber.split(" ")));

        List<String> contractNumbersList = Arrays.asList(ContractNumber.split(" "));

        // Validate first 10 records for the contract number
        String finalValue = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(i -> ContractNumber.split(" ")[i])
                .collect(Collectors.joining(","));

        System.out.println(finalValue);

        // Validate first 10 records for the contract number
        String[] parts = ContractNumber.split(" ");
        int limit = Math.min(parts.length, 10);
        String finalValue1 = IntStream.range(0, limit)
                .mapToObj(i -> parts[i])
                .collect(Collectors.joining(","));

        System.out.println(finalValue1);

        System.out.println("---------");

        List<String> numbers = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve");
        System.out.println("Original Numbers Data: " + String.join(",", numbers));

        System.out.println("Original Numbers: " + numbers);
        // Validate first 10 records for the numbers and stored in the List
        List<String> firstTenNumbers = numbers.stream()
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("First 10 Numbers: " + firstTenNumbers);


    }
}
