package com.taskmanager.dto;

public class TaskStatsResponse {
    private long totalTasks;
    private long completedTasks;
    private long inProgressTasks;
    private long pendingTasks;
    private double completionRate;

    public TaskStatsResponse() {}

    public TaskStatsResponse(long totalTasks, long completedTasks, long inProgressTasks, long pendingTasks, double completionRate) {
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.inProgressTasks = inProgressTasks;
        this.pendingTasks = pendingTasks;
        this.completionRate = completionRate;
    }

    public static TaskStatsResponseBuilder builder() {
        return new TaskStatsResponseBuilder();
    }

    public static class TaskStatsResponseBuilder {
        private long totalTasks;
        private long completedTasks;
        private long inProgressTasks;
        private long pendingTasks;
        private double completionRate;

        public TaskStatsResponseBuilder totalTasks(long totalTasks) { this.totalTasks = totalTasks; return this; }
        public TaskStatsResponseBuilder completedTasks(long completedTasks) { this.completedTasks = completedTasks; return this; }
        public TaskStatsResponseBuilder inProgressTasks(long inProgressTasks) { this.inProgressTasks = inProgressTasks; return this; }
        public TaskStatsResponseBuilder pendingTasks(long pendingTasks) { this.pendingTasks = pendingTasks; return this; }
        public TaskStatsResponseBuilder completionRate(double completionRate) { this.completionRate = completionRate; return this; }

        public TaskStatsResponse build() {
            return new TaskStatsResponse(totalTasks, completedTasks, inProgressTasks, pendingTasks, completionRate);
        }
    }

    public long getTotalTasks() { return totalTasks; }
    public void setTotalTasks(long totalTasks) { this.totalTasks = totalTasks; }

    public long getCompletedTasks() { return completedTasks; }
    public void setCompletedTasks(long completedTasks) { this.completedTasks = completedTasks; }

    public long getInProgressTasks() { return inProgressTasks; }
    public void setInProgressTasks(long inProgressTasks) { this.inProgressTasks = inProgressTasks; }

    public long getPendingTasks() { return pendingTasks; }
    public void setPendingTasks(long pendingTasks) { this.pendingTasks = pendingTasks; }

    public double getCompletionRate() { return completionRate; }
    public void setCompletionRate(double completionRate) { this.completionRate = completionRate; }
}
