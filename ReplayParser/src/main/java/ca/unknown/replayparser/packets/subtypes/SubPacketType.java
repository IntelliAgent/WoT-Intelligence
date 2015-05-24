package ca.unknown.replayparser.packets.subtypes;

public enum SubPacketType {
    UPDATE_VEHICLE(0x0B),
    UPDATE_VEHICLE_LIST(0x01),
    UPDATE_VEHICLE_FRAG_COUNT(0x05),
    VEHICLE_TRACKED(0x17),
    HEALTH_UPDATE(0x03),
    DESTROYED_TRACK(0x07);

    private int value;

    SubPacketType(int value) {
        this.value = value;
    }

    public static SubPacketType fromInt(int value) {
        switch (value) {
            case 0x0B:
                return UPDATE_VEHICLE;
            case 0x01:
                return UPDATE_VEHICLE_LIST;
            case 0x05:
                return UPDATE_VEHICLE_FRAG_COUNT;
            case 0x17:
                return VEHICLE_TRACKED;
        }
        return null;
    }
}
