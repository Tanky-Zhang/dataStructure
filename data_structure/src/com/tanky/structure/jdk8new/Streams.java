package com.tanky.structure.jdk8new;

/**
 * @Description: $
 * @Author: Tanky
 * @CreateDate: 2020/4/13$ 17:00$
 */
public  class Streams {

    public  enum Status {OPEN, CLOSED};

    public final static class Task {

        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

}
