package ca.unknown.replayparser.packets;


import ca.unknown.common.swapper.ByteSwapper;
import ca.unknown.replayparser.packets.subtypes.SubPacketType;
import ca.unknown.replayparser.packets.subtypes.packet07.SubPacket0703;
import ca.unknown.replayparser.packets.subtypes.packet07.SubPacket0707;
import ca.unknown.replayparser.packets.subtypes.packet08.SubPacket0801;
import ca.unknown.replayparser.packets.subtypes.packet08.SubPacket0805;
import ca.unknown.replayparser.packets.subtypes.packet08.SubPacket080B;
import ca.unknown.replayparser.packets.subtypes.packet08.SubPacket0817;

import java.nio.ByteBuffer;

import static ca.unknown.replayparser.packets.subtypes.SubPacketType.fromInt;

public class PacketFactory {
    /**
     * The packet factory method delegates packet construction to the proper packet type
     */
    public Packet createPacket(PacketType type, int length, float clock, ByteBuffer payload) {
        switch (type) {
            case BATTLE_LEVEL_SETUP:
                return new Packet00(type, length, clock, payload);
            case TANK_APPEARED:
                return new Packet03(type, length, clock, payload);
            case TANK_APPEARED_NEXT:
                return new Packet05(type, length, clock, payload);
            case VARIOUS_TANK_RELATED_UPDATES:
                switch (getSubType(payload)) {
                    case HEALTH_UPDATE:
                        return new SubPacket0703(type, length, clock, payload);
                    case DESTROYED_TRACK:
                        return new SubPacket0707(type, length, clock, payload);
                    default:
                        return new Packet07(type, length, clock, payload);
                }
            case VARIOUS_GAME_STATE_UPDATES:
                switch (getSubType(payload)) {
                    case UPDATE_VEHICLE:
                        return new SubPacket080B(type, length, clock, payload);
                    case UPDATE_VEHICLE_LIST:
                        return new SubPacket0801(type, length, clock, payload);
                    case UPDATE_VEHICLE_FRAG_COUNT:
                        return new SubPacket0805(type, length, clock, payload);
                    case VEHICLE_TRACKED:
                        return new SubPacket0817(type, length, clock, payload);
                    default:
                        return new Packet08(type, length, clock, payload);
                }
            case VEHICLE_POSITION_ROTATION_UPDATES:
                return new Packet0a(type, length, clock, payload);
            case GAME_STATUS:
                return new Packet12(type, length, clock, payload);
            case FIRST_PACKET:
                return new Packet14(type, length, clock, payload);
            case MAIN_PLAYER_SPECIFIC_DATA:
                return new Packet16(type, length, clock, payload);
            case MAIN_PLAYER_ID:
                return new Packet1e(type, length, clock, payload);
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT:
                return new Packet22(type, length, clock, payload);
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT_NEXT:
                return new Packet26(type, length, clock, payload);
            case LAST_PACKET:
                return new PacketFF(type, length, clock, payload);

        }
        return null;
    }

    private SubPacketType getSubType(ByteBuffer payload) {
        int subType = ByteSwapper.swap(payload.getInt(4));
        payload.rewind();
        return fromInt(subType);
    }
}
