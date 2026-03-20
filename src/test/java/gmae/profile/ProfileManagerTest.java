package gmae.profile;

import gmae.model.PlayerProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfileManagerTest {

    private ProfileManager manager;

    @BeforeEach
    void setUp() {
        manager = new ProfileManager();
    }

    @Test
    void getOrCreateReturnsNewProfileForUnknownName() {
        PlayerProfile profile = manager.getOrCreate("Alice");
        assertNotNull(profile);
        assertEquals("Alice", profile.getPlayerName());
    }

    @Test
    void getOrCreateReturnsSameProfileOnSecondCall() {
        PlayerProfile first = manager.getOrCreate("Bob");
        PlayerProfile second = manager.getOrCreate("Bob");
        assertSame(first, second);
    }

    @Test
    void findReturnsNullForMissingProfile() {
        assertNull(manager.find("Ghost"));
    }

    @Test
    void findReturnsProfileAfterGetOrCreate() {
        manager.getOrCreate("Carol");
        assertNotNull(manager.find("Carol"));
        assertEquals("Carol", manager.find("Carol").getPlayerName());
    }

    @Test
    void getAllProfilesReturnsAllCreatedProfiles() {
        manager.getOrCreate("Dave");
        manager.getOrCreate("Eve");
        assertEquals(2, manager.getAllProfiles().size());
    }

    @Test
    void freshManagerHasNoProfiles() {
        assertTrue(manager.getAllProfiles().isEmpty());
    }

    @Test
    void profilesTrackStatsIndependently() {
        PlayerProfile alice = manager.getOrCreate("Alice");
        PlayerProfile bob = manager.getOrCreate("Bob");

        alice.incrementGamesPlayed();
        alice.incrementGamesWin();

        assertEquals(1, alice.getGamesPlayed());
        assertEquals(1, alice.getGamesWin());
        assertEquals(0, bob.getGamesPlayed());
        assertEquals(0, bob.getGamesWin());
    }
}
