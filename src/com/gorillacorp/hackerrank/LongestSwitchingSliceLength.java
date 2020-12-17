package com.gorillacorp.hackerrank;

// This code challenges is not from HackerRank... but I cannot remember the name of the site :D
public class LongestSwitchingSliceLength {
    public int getMaxSpliceLength(final int[] a) {
        var arrLength = a.length;
        if (arrLength <= 1) return arrLength;
        var longestSwitchingLength = 0;

        var currentPosition = 0;
        while (currentPosition < arrLength) {
            var firstNumber = currentPosition;
            var secondNumber = currentPosition + 1;
            while (secondNumber + 2 < arrLength &&
                    a[firstNumber] == a[firstNumber + 2] &&
                    a[secondNumber] == a[secondNumber + 2]) {
                firstNumber += 2;
                secondNumber += 2;
            }
            if ((firstNumber + 2 < arrLength) && (a[firstNumber] == a[firstNumber + 2])) firstNumber += 2;
            var currentMax = Math.max(firstNumber, secondNumber);
            longestSwitchingLength = Math.max(longestSwitchingLength, currentMax - currentPosition + 1);
            currentPosition = currentMax;
        }
        return longestSwitchingLength;
    }
}
