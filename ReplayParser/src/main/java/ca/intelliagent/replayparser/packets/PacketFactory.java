package ca.intelliagent.replayparser.packets;


import ca.intelliagent.replayparser.packets.subtypes.packet07.SubPacket0703;
import ca.intelliagent.replayparser.packets.subtypes.packet07.SubPacket0707;
import ca.intelliagent.replayparser.packets.subtypes.packet08.*;

public class PacketFactory {
    /**
     * The packet factory method delegates packet construction to the proper packet type
     */
    public Packet createPacket(RawPacket rawPacket) {
        switch (rawPacket.getType()) {
            case BATTLE_LEVEL_SETUP:
                return new Packet00(rawPacket);
            case TANK_APPEARED:
                return new Packet03(rawPacket);
            case TANK_APPEARED_NEXT:
                return new Packet05(rawPacket);
            case VARIOUS_TANK_RELATED_UPDATES:
                switch (rawPacket.getSubType()) {
                    case HEALTH_UPDATE:
                        return new SubPacket0703(rawPacket);
                    case DESTROYED_TRACK:
                        return new SubPacket0707(rawPacket);
                    default:
                        return new Packet07(rawPacket);
                }
            case VARIOUS_GAME_STATE_UPDATES:
                switch (rawPacket.getSubType()) {
                    case UPDATE_VEHICLE:
                        return new SubPacket080B(rawPacket);
                    case UPDATE_VEHICLE_LIST:
                        return new SubPacket0801(rawPacket);
                    case UPDATE_VEHICLE_FRAG_COUNT:
                        return new SubPacket0805(rawPacket);
                    case VEHICLE_TRACKED:
                        return new SubPacket0817(rawPacket);
                    default:
                        return new Packet08(rawPacket);
                }
            case VEHICLE_POSITION_ROTATION_UPDATES:
                return new Packet0a(rawPacket);
            case GAME_STATUS:
                return new Packet12(rawPacket);
            case FIRST_PACKET:
                return new Packet14(rawPacket);
            case MAIN_PLAYER_SPECIFIC_DATA:
                return new Packet16(rawPacket);
            case MAIN_PLAYER_ID:
                return new Packet1e(rawPacket);
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT:
                return new Packet22(rawPacket);
            case MAIN_PLAYER_SPECIFIC_DATA_NEXT_NEXT:
                return new Packet26(rawPacket);
            case LAST_PACKET:
                return new PacketFF(rawPacket);

        }
        return null;
    }
}
