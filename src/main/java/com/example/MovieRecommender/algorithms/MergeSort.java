package com.example.MovieRecommender.algorithms;

import com.example.MovieRecommender.models.Movie;

import java.util.LinkedList;
import java.util.Queue;

public class MergeSort {
    public static void mergeSortMovieQueue(Queue<Movie> queue) {
        if (queue.size() <= 1) {
            return;
        }

        Queue<Movie> left = new LinkedList<>();
        Queue<Movie> right = new LinkedList<>();

        int middle = queue.size() / 2;
        int i = 0;
        while (i < middle) {
            left.offer(queue.poll());
            i++;
        }
        while (!queue.isEmpty()) {
            right.offer(queue.poll());
        }

        mergeSortMovieQueue(left);
        mergeSortMovieQueue(right);

        merge(queue, left, right);
    }

    private static void merge(Queue<Movie> result, Queue<Movie> left, Queue<Movie> right) {
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.peek().getPopularity() >= right.peek().getPopularity()) {
                result.offer(left.poll());
            } else {
                result.offer(right.poll());
            }
        }

        while (!right.isEmpty()) {
            result.offer(right.poll());
        }
        while (!left.isEmpty()) {
            result.offer(left.poll());
        }
    }
}

