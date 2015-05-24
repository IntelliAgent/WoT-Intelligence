package ca.unknown.replayparser.packets;


import java.nio.ByteBuffer;

public class PacketFactory {
    /**
     * The packet factory method delegates packet construction to the proper packet type
     */
    public Packet createPacket(PacketType type, int length, float clock, ByteBuffer buffer) {
        switch (type) {
            case BATTLE_LEVEL_SETUP:
                return new Packet00(type, length, clock, buffer);
            case TANK_APPEARED:
                return new Packet03(type, length, clock, buffer);
            case TANK_APPEARED_NEXT:
                return new Packet05(type, length, clock, buffer);
            case VARIOUS_TANK_RELATED_UPDATES:
                return new Packet07(type, length, clock, buffer);
            case VARIOUS_GAME_STATE_UPDATES:
                return new Packet08(type, length, clock, buffer);
            case VEHICLE_POSITION_ROTATION_UPDATES:
                return new Packet0a(type, length, clock, buffer);
            case GAME_STATUS:
                return new Packet12(type, length, clock, buffer);
            case FIRST_PACKET:
                return new Packet14(type, length, clock, buffer);
            case MAIN_PLAYER_SPECIFIC_DATA:
                return new Packet16(type, length, clock, buffer);
            case MAIN_PLAYER_ID:
                return new Packet1e(type, length, clock, buffer);
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT:
                return new Packet22(type, length, clock, buffer);
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT_NEXT:
                return new Packet26(type, length, clock, buffer);
            case LAST_PACKET:
                return new PacketFF(type, length, clock, buffer);

        }
        return null;
    }
}
