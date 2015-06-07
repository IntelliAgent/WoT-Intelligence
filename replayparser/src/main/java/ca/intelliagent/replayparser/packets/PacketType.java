package ca.intelliagent.replayparser.packets;


public enum PacketType {
    BATTLE_LEVEL_SETUP(0x00),
    TANK_APPEARED(0x03),
    TANK_APPEARED_NEXT(0x05),
    VARIOUS_TANK_RELATED_UPDATES(0x07),
    VARIOUS_GAME_STATE_UPDATES(0x08),
    VEHICLE_POSITION_ROTATION_UPDATES(0x0A),
    GAME_STATUS(0x12),
    FIRST_PACKET(0x14),
    MAIN_PLAYER_SPECIFIC_DATA_NEXT_NEXT(0x26), LAST_PACKET(0xFFFFFFFF);

    private final int value;

    PacketType(int value) {
        this.value = value;
    }

    public static PacketType fromInt(int value) {
        switch (value) {
            case 0x00:
                return BATTLE_LEVEL_SETUP;
            case 0x03:
                return TANK_APPEARED;
            case 0x05:
                return TANK_APPEARED_NEXT;
            case 0x07:
                return VARIOUS_TANK_RELATED_UPDATES;
            case 0x08:
                return VARIOUS_GAME_STATE_UPDATES;
            case 0x0A:
                return VEHICLE_POSITION_ROTATION_UPDATES;
            case 0x12:
                return GAME_STATUS;
            case 0x14:
                return FIRST_PACKET;
            case 0xFFFFFFFF:
                return LAST_PACKET;

        }
        return null;
    }
}
