package gmae.model;

public final class DateTime implements Comparable<DateTime>{
    private final long worldMillis;

    public static DateTime parse(String dateTimeStr) throws Exception {
        try {
            String[] parts = dateTimeStr.trim().split("/");
            if (parts.length != 3) {
                throw new Exception("Invalid date format. Expected format: day/hour/minute");
            }
            long day = Long.parseLong(parts[0].trim());
            long hour = Long.parseLong(parts[1].trim());
            long minute = Long.parseLong(parts[2].trim());

            if (day < 1 ||hour < 0 || hour >= 24 || minute < 0 || minute >= 60) {
                throw new Exception("Day must be > 1, Hour must be between 0-23, and minute must be between 0-59.");
            }

            long totalMillis = Math.addExact(Math.multiplyExact(day - 1, 86_400_000L), Math.addExact(Math.multiplyExact(hour, 3_600_000L), Math.multiplyExact(minute, 60_000L))
            );
            return new DateTime(totalMillis);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid number format in date string: " + e.getMessage());
        }
    }

    public DateTime(long worldMillis) {
        this.worldMillis = worldMillis;
    }

    public DateTime plusMillis(long deltaMillis) {
        return new DateTime(Math.addExact(this.worldMillis, deltaMillis));
    }

    public DateTime plusMinutes(long deltaMinutes) {
        return plusMillis(Math.multiplyExact(deltaMinutes, 60_000L));
    }

    public long minus(DateTime other) {
        return Math.subtractExact(this.worldMillis, other.worldMillis);
    }

    @Override
    public int compareTo(DateTime other) {
        return Long.compare(this.worldMillis, other.worldMillis);
    }

    @Override
    public String toString() {
        long totalSeconds = worldMillis / 1000L;

        long days = totalSeconds / 86_400L;
        long rem = totalSeconds % 86_400L;

        long hours = rem / 3_600L;
        rem %= 3_600L;

        long minutes = rem / 60L;
        long seconds = rem % 60L;

        return String.format("Day %d %02d:%02d:%02d", days + 1, hours, minutes, seconds);
    }

    // ==================================
    // Getters and setters for the fields
    // ==================================

    public long getWorldMillis() {
        return worldMillis;
    }
}
