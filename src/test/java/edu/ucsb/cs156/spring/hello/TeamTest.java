package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

    Team team;

    @BeforeEach
    public void setup() {
        team = new Team("test-team");    
    }

    @Test
    public void getName_returns_correct_name() {
       assert(team.getName().equals("test-team"));
    }

    @Test
    public void toString_returns_correct_string() {
        assertEquals("Team(name=test-team, members=[])", team.toString());
    }

    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equals_returns_correct_value() {
        Team t1 = new Team("test-team");
        Team t2 = new Team("test-team");
        t2.addMember("skibidi-bob");
        Team t3 = new Team("other-team");
        Team t4 = new Team("other-team");
        t4.addMember("skibidi-bob");

        assertEquals(team.equals(team), true);              // equals self
        assertEquals(team.equals("a string"), false);   // equals non-team

        assertEquals(team.equals(t1), true);                // same name and members
        assertEquals(team.equals(t2), false);               // member mismatch
        assertEquals(team.equals(t3), false);               // name mismatch
        assertEquals(team.equals(t4), false);               // name, member mismatch
    }

    @Test
    public void hashcode_returns_usable_hash() {
        Team t1 = new Team();
        t1.setName("foo");
        t1.addMember("bar");
        Team t2 = new Team();
        t2.setName("foo");
        t2.addMember("bar");
        assertEquals(t1.hashCode(), t2.hashCode());
        
        // HACK: avoid a mutation test fail by enforcing
        // the way that this hash is computed.
        assertEquals(t1.hashCode(), t1.name.hashCode() | t1.members.hashCode());
    }
}
