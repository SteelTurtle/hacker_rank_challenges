package com.gorillacorp.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

// HackerLand National Bank has a simple policy for warning clients about possible fraudulent account
// activity. If the amount spent by a client on a particular day is greater than or equal to 2X the client's
// median spending for a trailing number of days, they send the client a notification about potential fraud.
// The bank doesn't send the client any notifications until they have at least that trailing number of prior
// days' transaction data.
// Given the number of trailing days d and a client's total daily expenditures for a period of n days, find
// and print the number of times the client will receive a notification over all n days.
// For example, d = 3 and expenditures = [10,20,30,40,50]. On the first three days, they just collect
// spending data.At day 4, we have trailing expenditures of [10,20,30]. The median is 20 and the day's
// expenditure is 40. Because 40 >= 20, there will be a notice. The next day, our trailing expenditures are
// [20,30,40], and the expenditures are 50. This is less than 2 X 30 so no notice will be sent. Over the
// period, there was one notice sent.
// Note: The median of a list of numbers can be found by arranging all the numbers from smallest to
// greatest. If there is an odd number of numbers, the middle one is picked. If there is an even number of
// numbers, median is then defined to be the average of the two middle values. (Wikipedia)
//
// Input Format:
// The first line contains two space-separated integers n and d, the number of days of transaction data, and
// the number of trailing days' data used to calculate median spending.
// The second line contains n space-separated non-negative integers where each integer i denotes
// expenditure[i].
//
// Output Format:
// Print an integer denoting the total number of times the client receives a notification over
// a period of n days.
//
// Sample input:
// 9 5
// 2 3 4 2 3 6 8 4 5
//
// Sample output:
// 2
public class FraudulentActivityNotification {
    // Complete the activityNotifications function below. The function returns a double
    // representing the number of client notifications. Given the following parameters:
    //
    // expenditure: an array of integers representing daily expenditures
    // days: an integer, the look-back days for median spending
    //
    // Notice that, being HackerRank as usual total RUBBISH, it was necessary to change the return value
    // of the function from int to double to make it work; it was also necessary to change most part of the
    // main function to have the output generation backed by a Queue instead of an array.
    //
    // #HackerRankShit
    static double activityNotifications(final int[] expenditure, final int days) {
        var index = 0;
        //Find median of EVEN number of elements
        if (days % 2 == 0) {
            var counter = (days / 2);
            while (counter > 0) {
                counter -= expenditure[index];
                index++;
            }
            //Remove extra iteration
            index--;
            //This index covers both medians
            if (counter <= -1) return index;
            else {
                //else (counter == 0), meaning we need to find the next median index
                var secondIndex = index + 1;
                //Find next non-zero transaction
                while (expenditure[secondIndex] == 0) secondIndex++;
                //Calculate the average of middle two elements
                return (index + secondIndex) / 2.0;
            }
        }
        //Find median of ODD number of elements
        int counter = (days / 2);
        while (counter >= 0) {
            counter -= expenditure[index];
            index++;
        }
        return index - 1;
    }


    public static void main(String[] args) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
             var scanner = new Scanner(System.in)) {

            var transactions = scanner.nextInt();
            var days = scanner.nextInt();
            var notifications = 0;
            Queue<Integer> queue = new LinkedList<>();
            var pastActivity = new int[201];
            //Wait for a certain number of days with transactions before any notifications
            IntStream.range(0, days).map(i -> scanner.nextInt()).forEach(transaction -> {
                queue.offer(transaction);
                pastActivity[transaction] = pastActivity[transaction] + 1;
            });
            for (var i = 0; i < transactions - days; i++) {
                var newTransaction = scanner.nextInt();
                //Check if fraudulent activity may have occurred
                if (newTransaction >= (2 * activityNotifications(pastActivity, days)))
                    notifications++;
                //Remove the oldest transaction
                var oldestTransaction = Objects.requireNonNull(queue.poll());
                pastActivity[oldestTransaction] = pastActivity[oldestTransaction] - 1;
                //Add the new transaction
                queue.offer(newTransaction);
                pastActivity[newTransaction] = pastActivity[newTransaction] + 1;
            }
            bufferedWriter.write(String.valueOf(notifications));
            bufferedWriter.newLine();
        }
    }
}
