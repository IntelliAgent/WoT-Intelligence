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
    public Packet createPacket(RawPacket rawPacket) {
        switch (rawPacket.getType()) {
            case BATTLE_LEVEL_SETUP:
                return new Packet00(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case TANK_APPEARED:
                return new Packet03(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case TANK_APPEARED_NEXT:
                return new Packet05(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case VARIOUS_TANK_RELATED_UPDATES:
                switch (rawPacket.getSubType()) {
                    case HEALTH_UPDATE:
                        return new SubPacket0703(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                    case DESTROYED_TRACK:
                        return new SubPacket0707(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                    default:
                        return new Packet07(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                }
            case VARIOUS_GAME_STATE_UPDATES:
                switch (rawPacket.getSubType()) {
                    case UPDATE_VEHICLE:
                        return new SubPacket080B(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                    case UPDATE_VEHICLE_LIST:
                        return new SubPacket0801(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                    case UPDATE_VEHICLE_FRAG_COUNT:
                        return new SubPacket0805(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                    case VEHICLE_TRACKED:
                        return new SubPacket0817(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                    default:
                        return new Packet08(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
                }
            case VEHICLE_POSITION_ROTATION_UPDATES:
                return new Packet0a(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case GAME_STATUS:
                return new Packet12(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case FIRST_PACKET:
                return new Packet14(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case MAIN_PLAYER_SPECIFIC_DATA:
                return new Packet16(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case MAIN_PLAYER_ID:
                return new Packet1e(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT:
                return new Packet22(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT_NEXT:
                return new Packet26(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());
            case LAST_PACKET:
                return new PacketFF(rawPacket.getType(), rawPacket.getPayloadLength(), rawPacket.getClock(), rawPacket.getPayload());

        }
        return null;
    }
}
