package gmae.model;

import java.util.Objects;

public class Realm {

    private final String id;
    private final String name;
    private final String description;
    private final String coordinates;
    private final LocalTimeRule localTimeRule;

    public Realm(String id, String name) {
        this(id, name, "", "", null);
    }

    public Realm(String id, String name, String description) {
        this(id, name, description, "", null);
    }

    public Realm(String id, String name, String description, String coordinates, LocalTimeRule localTimeRule) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Realm id must not be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Realm name must not be blank");
        }

        this.id = id;
        this.name = name;
        this.description = description == null ? "" : description;
        this.coordinates = coordinates == null ? "" : coordinates;
        this.localTimeRule = localTimeRule;
    }

    public DateTime convertFromWorldClock(DateTime worldTime) {
        if (worldTime == null) {
            throw new IllegalArgumentException("World time must not be null");
        }
        if (localTimeRule == null) {
            return worldTime;
        }
        return localTimeRule.applyRule(worldTime);
    }

    public DateTime convertToWorldClock(DateTime localTime) {
        if (localTime == null) {
            throw new IllegalArgumentException("Local time must not be null");
        }
        // Placeholder until reverse-conversion logic is implemented
        return localTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public LocalTimeRule getLocalTimeRule() {
        return localTimeRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Realm realm)) return false;
        return id.equals(realm.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
