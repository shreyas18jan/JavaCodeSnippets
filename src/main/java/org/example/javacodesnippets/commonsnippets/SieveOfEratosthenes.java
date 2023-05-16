package org.example.javacodesnippets.commonsnippets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SieveOfEratosthenes {

    public static void main(String[] args) {
        // Input value, till what number you want to generate prime numbers
        int input = 100;
        System.out.println("Generate prime numbers till " + input);

        // To initialize the List with default value, Arrays.fill() can be used
        Boolean[] boolTempArray = new Boolean[input];
        Arrays.fill(boolTempArray, true);
        List<Boolean> boolArray = Arrays.asList(boolTempArray);
        List<Integer> primeArray = new ArrayList<>();

        //
        // Sieve of Eratosthenes Algorithm :
        // Idea is to mark a number not prime ,by iteratively marking the multiples of each prime number.
        //
        for(int i=2; i<Math.floor(Math.sqrt(input)); i++) {
            if(boolArray.get(i)) {
                int navigator = i * i;
                while(navigator < input) {
                    boolArray.set(navigator, false);
                    navigator += i;
                }
            }
        }

        // Navigate through marked boolean, and collect those data which are marked true
        IntStream.range(1, input).forEach(number ->{
            if (boolArray.get(number)) {
                primeArray.add(number);
            }
        });

        System.out.println("Sieve of Eratosthenes Algorithm - " + primeArray);


        // Alternative, Code snippet.
        List<Integer> alternativePrimeArray = IntStream.range(0, input).boxed().collect(Collectors.toList());

        // Sieve of Eratosthenes Algorithm.
        for(int i=2; i<Math.floor(Math.sqrt(input)); i++) {
            if(null != alternativePrimeArray.get(i)) {
                int navigator = i * i;
                while(navigator < input) {
                    alternativePrimeArray.set(navigator, null);
                    navigator += i;
                }
            }
        }

        System.out.println("Sieve of Eratosthenes Algorithm, Alternative code snippet - " + alternativePrimeArray.stream().filter(Objects::nonNull).filter(num -> num > 0).collect(Collectors.toList()));


    }
}
